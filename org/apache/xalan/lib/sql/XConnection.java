/*      */ package org.apache.xalan.lib.sql;
/*      */ 
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.util.Properties;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.ErrorListener;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xalan.extensions.ExpressionContext;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.ref.DTMManagerDefault;
/*      */ import org.apache.xpath.XPathContext;
/*      */ import org.apache.xpath.objects.XBooleanStatic;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class XConnection
/*      */ {
/*      */   private static final boolean DEBUG = false;
/*   83 */   private ConnectionPool m_ConnectionPool = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   90 */   private Connection m_Connection = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_DefaultPoolingEnabled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  114 */   private Vector m_OpenSQLDocuments = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  122 */   private ConnectionPoolManager m_PoolMgr = new ConnectionPoolManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  128 */   private Vector m_ParameterList = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  136 */   private Exception m_Error = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  144 */   private SQLDocument m_LastSQLDocumentWithError = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_FullErrors = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  160 */   private SQLQueryParser m_QueryParser = new SQLQueryParser();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_IsDefaultPool = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_IsStreamingEnabled = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_InlineVariables = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_IsMultipleResultsEnabled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_IsStatementCachingEnabled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XConnection() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XConnection(ExpressionContext exprContext, String ConnPoolName) {
/*  210 */     connect(exprContext, ConnPoolName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XConnection(ExpressionContext exprContext, String driver, String dbURL) {
/*  220 */     connect(exprContext, driver, dbURL);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XConnection(ExpressionContext exprContext, NodeList list) {
/*  229 */     connect(exprContext, list);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XConnection(ExpressionContext exprContext, String driver, String dbURL, String user, String password) {
/*  241 */     connect(exprContext, driver, dbURL, user, password);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XConnection(ExpressionContext exprContext, String driver, String dbURL, Element protocolElem) {
/*  252 */     connect(exprContext, driver, dbURL, protocolElem);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XBooleanStatic connect(ExpressionContext exprContext, String ConnPoolName) {
/*      */     
/*  266 */     try { this.m_ConnectionPool = this.m_PoolMgr.getPool(ConnPoolName);
/*      */       
/*  268 */       if (this.m_ConnectionPool == null) {
/*  269 */         throw new IllegalArgumentException("Invalid Pool Name");
/*      */       }
/*  271 */       this.m_IsDefaultPool = false;
/*  272 */       return new XBooleanStatic(true); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  276 */       setError(e, exprContext);
/*  277 */       return new XBooleanStatic(false); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XBooleanStatic connect(ExpressionContext exprContext, String driver, String dbURL) {
/*      */     
/*  293 */     try { init(driver, dbURL, new Properties());
/*  294 */       return new XBooleanStatic(true); } catch (SQLException e)
/*      */     
/*      */     { 
/*      */       
/*  298 */       setError(e, exprContext);
/*  299 */       return new XBooleanStatic(false); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  303 */       setError(e, exprContext);
/*  304 */       return new XBooleanStatic(false); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XBooleanStatic connect(ExpressionContext exprContext, Element protocolElem) {
/*      */     
/*  317 */     try { initFromElement(protocolElem);
/*  318 */       return new XBooleanStatic(true); } catch (SQLException e)
/*      */     
/*      */     { 
/*      */       
/*  322 */       setError(e, exprContext);
/*  323 */       return new XBooleanStatic(false); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  327 */       setError(e, exprContext);
/*  328 */       return new XBooleanStatic(false); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XBooleanStatic connect(ExpressionContext exprContext, NodeList list) {
/*      */     
/*  341 */     try { initFromElement((Element)list.item(0));
/*  342 */       return new XBooleanStatic(true); } catch (SQLException e)
/*      */     
/*      */     { 
/*      */       
/*  346 */       setError(e, exprContext);
/*  347 */       return new XBooleanStatic(false); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  351 */       setError(e, exprContext);
/*  352 */       return new XBooleanStatic(false); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XBooleanStatic connect(ExpressionContext exprContext, String driver, String dbURL, String user, String password) {
/*      */     
/*  369 */     try { Properties prop = new Properties();
/*  370 */       prop.put("user", user);
/*  371 */       prop.put("password", password);
/*      */       
/*  373 */       init(driver, dbURL, prop);
/*      */       
/*  375 */       return new XBooleanStatic(true); } catch (SQLException e)
/*      */     
/*      */     { 
/*      */       
/*  379 */       setError(e, exprContext);
/*  380 */       return new XBooleanStatic(false); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  384 */       setError(e, exprContext);
/*  385 */       return new XBooleanStatic(false); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XBooleanStatic connect(ExpressionContext exprContext, String driver, String dbURL, Element protocolElem) {
/*      */     
/*  403 */     try { Properties prop = new Properties();
/*      */       
/*  405 */       NamedNodeMap atts = protocolElem.getAttributes();
/*      */       
/*  407 */       for (int i = 0; i < atts.getLength(); i++)
/*      */       {
/*  409 */         prop.put(atts.item(i).getNodeName(), atts.item(i).getNodeValue());
/*      */       }
/*      */       
/*  412 */       init(driver, dbURL, prop);
/*      */       
/*  414 */       return new XBooleanStatic(true); } catch (SQLException e)
/*      */     
/*      */     { 
/*      */       
/*  418 */       setError(e, exprContext);
/*  419 */       return new XBooleanStatic(false); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  423 */       setError(e, exprContext);
/*  424 */       return new XBooleanStatic(false); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initFromElement(Element e) throws SQLException {
/*  459 */     Properties prop = new Properties();
/*  460 */     String driver = "";
/*  461 */     String dbURL = "";
/*  462 */     Node n = e.getFirstChild();
/*      */     
/*  464 */     if (null == n) {
/*      */       return;
/*      */     }
/*      */     do {
/*  468 */       String nName = n.getNodeName();
/*      */       
/*  470 */       if (nName.equalsIgnoreCase("dbdriver")) {
/*      */         
/*  472 */         driver = "";
/*  473 */         Node node = n.getFirstChild();
/*  474 */         if (null != node)
/*      */         {
/*  476 */           driver = node.getNodeValue();
/*      */         }
/*      */       } 
/*      */       
/*  480 */       if (nName.equalsIgnoreCase("dburl")) {
/*      */         
/*  482 */         dbURL = "";
/*  483 */         Node node = n.getFirstChild();
/*  484 */         if (null != node)
/*      */         {
/*  486 */           dbURL = node.getNodeValue();
/*      */         }
/*      */       } 
/*      */       
/*  490 */       if (nName.equalsIgnoreCase("password")) {
/*      */         
/*  492 */         String str = "";
/*  493 */         Node node = n.getFirstChild();
/*  494 */         if (null != node)
/*      */         {
/*  496 */           str = node.getNodeValue();
/*      */         }
/*  498 */         prop.put("password", str);
/*      */       } 
/*      */       
/*  501 */       if (nName.equalsIgnoreCase("user")) {
/*      */         
/*  503 */         String str = "";
/*  504 */         Node node = n.getFirstChild();
/*  505 */         if (null != node)
/*      */         {
/*  507 */           str = node.getNodeValue();
/*      */         }
/*  509 */         prop.put("user", str);
/*      */       } 
/*      */       
/*  512 */       if (!nName.equalsIgnoreCase("protocol"))
/*      */         continue; 
/*  514 */       String Name = "";
/*      */       
/*  516 */       NamedNodeMap attrs = n.getAttributes();
/*  517 */       Node n1 = attrs.getNamedItem("name");
/*  518 */       if (null == n1)
/*      */         continue; 
/*  520 */       String s = "";
/*  521 */       Name = n1.getNodeValue();
/*      */       
/*  523 */       Node n2 = n.getFirstChild();
/*  524 */       if (null != n2) s = n2.getNodeValue();
/*      */       
/*  526 */       prop.put(Name, s);
/*      */ 
/*      */     
/*      */     }
/*  530 */     while ((n = n.getNextSibling()) != null);
/*      */     
/*  532 */     init(driver, dbURL, prop);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void init(String driver, String dbURL, Properties prop) throws SQLException {
/*  549 */     Connection con = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  554 */     String user = prop.getProperty("user");
/*  555 */     if (user == null) user = "";
/*      */     
/*  557 */     String passwd = prop.getProperty("password");
/*  558 */     if (passwd == null) passwd = "";
/*      */ 
/*      */     
/*  561 */     String poolName = driver + dbURL + user + passwd;
/*  562 */     ConnectionPool cpool = this.m_PoolMgr.getPool(poolName);
/*      */     
/*  564 */     if (cpool == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  577 */       DefaultConnectionPool defpool = new DefaultConnectionPool();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  582 */       defpool.setDriver(driver);
/*  583 */       defpool.setURL(dbURL);
/*  584 */       defpool.setProtocol(prop);
/*      */ 
/*      */ 
/*      */       
/*  588 */       if (this.m_DefaultPoolingEnabled) defpool.setPoolEnabled(true);
/*      */       
/*  590 */       this.m_PoolMgr.registerPool(poolName, defpool);
/*  591 */       this.m_ConnectionPool = defpool;
/*      */     }
/*      */     else {
/*      */       
/*  595 */       this.m_ConnectionPool = cpool;
/*      */     } 
/*      */     
/*  598 */     this.m_IsDefaultPool = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  606 */     try { con = this.m_ConnectionPool.getConnection(); } catch (SQLException e)
/*      */     
/*      */     { 
/*      */       
/*  610 */       if (con != null) {
/*      */         
/*  612 */         this.m_ConnectionPool.releaseConnectionOnError(con);
/*  613 */         con = null;
/*      */       } 
/*  615 */       throw e; }
/*      */     
/*      */     finally
/*      */     
/*  619 */     { if (con != null) this.m_ConnectionPool.releaseConnection(con);
/*      */        }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ConnectionPool getConnectionPool() {
/*  629 */     return this.m_ConnectionPool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTM query(ExpressionContext exprContext, String queryString) {
/*  647 */     SQLDocument doc = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  654 */     try { if (null == this.m_ConnectionPool) return null;
/*      */       
/*  656 */       SQLQueryParser query = this.m_QueryParser.parse(this, queryString, 1);
/*      */ 
/*      */ 
/*      */       
/*  660 */       doc = SQLDocument.getNewDocument(exprContext);
/*  661 */       doc.execute(this, query);
/*      */ 
/*      */       
/*  664 */       this.m_OpenSQLDocuments.addElement(doc); } catch (Exception e)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  673 */       if (doc != null) {
/*      */         
/*  675 */         if (doc.hasErrors())
/*      */         {
/*  677 */           setError(e, doc, doc.checkWarnings());
/*      */         }
/*      */         
/*  680 */         doc.close();
/*  681 */         doc = null;
/*      */       }  }
/*      */     finally {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  690 */     return (DTM)doc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTM pquery(ExpressionContext exprContext, String queryString) {
/*  707 */     return pquery(exprContext, queryString, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTM pquery(ExpressionContext exprContext, String queryString, String typeInfo) {
/*  729 */     SQLDocument doc = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  736 */     try { if (null == this.m_ConnectionPool) return null;
/*      */       
/*  738 */       SQLQueryParser query = this.m_QueryParser.parse(this, queryString, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  744 */       if (!this.m_InlineVariables) {
/*      */         
/*  746 */         addTypeToData(typeInfo);
/*  747 */         query.setParameters(this.m_ParameterList);
/*      */       } 
/*      */       
/*  750 */       doc = SQLDocument.getNewDocument(exprContext);
/*  751 */       doc.execute(this, query);
/*      */ 
/*      */       
/*  754 */       this.m_OpenSQLDocuments.addElement(doc); } catch (Exception e)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  763 */       if (doc != null) {
/*      */         
/*  765 */         if (doc.hasErrors())
/*      */         {
/*  767 */           setError(e, doc, doc.checkWarnings());
/*      */         }
/*      */         
/*  770 */         doc.close();
/*  771 */         doc = null;
/*      */       }  }
/*      */     finally {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  780 */     return (DTM)doc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addTypeToData(String typeInfo) {
/*  787 */     if (typeInfo != null && this.m_ParameterList != null) {
/*      */ 
/*      */ 
/*      */       
/*  791 */       StringTokenizer plist = new StringTokenizer(typeInfo);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  797 */       int indx = 0;
/*  798 */       while (plist.hasMoreTokens()) {
/*      */         
/*  800 */         String value = plist.nextToken();
/*  801 */         QueryParameter qp = this.m_ParameterList.elementAt(indx);
/*  802 */         if (null != qp)
/*      */         {
/*  804 */           qp.setTypeName(value);
/*      */         }
/*      */         
/*  807 */         indx++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameter(String value) {
/*  819 */     addParameterWithType(value, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameterWithType(String value, String Type) {
/*  830 */     this.m_ParameterList.addElement(new QueryParameter(value, Type));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameterFromElement(Element e) {
/*  842 */     NamedNodeMap attrs = e.getAttributes();
/*  843 */     Node Type = attrs.getNamedItem("type");
/*  844 */     Node n1 = e.getFirstChild();
/*  845 */     if (null != n1) {
/*      */       
/*  847 */       String value = n1.getNodeValue();
/*  848 */       if (value == null) value = ""; 
/*  849 */       this.m_ParameterList.addElement(new QueryParameter(value, Type.getNodeValue()));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addParameterFromElement(NodeList nl) {
/*  878 */     int count = nl.getLength();
/*  879 */     for (int x = 0; x < count; x++)
/*      */     {
/*  881 */       addParameters((Element)nl.item(x));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addParameters(Element elem) {
/*  902 */     Node n = elem.getFirstChild();
/*      */     
/*  904 */     if (null == n)
/*      */       return; 
/*      */     do {
/*      */       String str1;
/*  908 */       if (n.getNodeType() != 1)
/*      */         continue; 
/*  910 */       NamedNodeMap attrs = n.getAttributes();
/*  911 */       Node Type = attrs.getNamedItem("type");
/*      */ 
/*      */       
/*  914 */       if (Type == null) { str1 = "string"; }
/*  915 */       else { str1 = Type.getNodeValue(); }
/*      */       
/*  917 */       Node n1 = n.getFirstChild();
/*  918 */       if (null == n1)
/*      */         continue; 
/*  920 */       String value = n1.getNodeValue();
/*  921 */       if (value == null) value = "";
/*      */ 
/*      */       
/*  924 */       this.m_ParameterList.addElement(new QueryParameter(value, str1));
/*      */ 
/*      */     
/*      */     }
/*  928 */     while ((n = n.getNextSibling()) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearParameters() {
/*  936 */     this.m_ParameterList.removeAllElements();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void enableDefaultConnectionPool() {
/*  957 */     this.m_DefaultPoolingEnabled = true;
/*      */     
/*  959 */     if (this.m_ConnectionPool == null)
/*  960 */       return;  if (this.m_IsDefaultPool)
/*      */       return; 
/*  962 */     this.m_ConnectionPool.setPoolEnabled(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void disableDefaultConnectionPool() {
/*  976 */     this.m_DefaultPoolingEnabled = false;
/*      */     
/*  978 */     if (this.m_ConnectionPool == null)
/*  979 */       return;  if (!this.m_IsDefaultPool)
/*      */       return; 
/*  981 */     this.m_ConnectionPool.setPoolEnabled(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void enableStreamingMode() {
/*  999 */     this.m_IsStreamingEnabled = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void disableStreamingMode() {
/* 1016 */     this.m_IsStreamingEnabled = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTM getError() {
/* 1026 */     if (this.m_FullErrors)
/*      */     {
/* 1028 */       for (int idx = 0; idx < this.m_OpenSQLDocuments.size(); idx++) {
/*      */         
/* 1030 */         SQLDocument doc = this.m_OpenSQLDocuments.elementAt(idx);
/* 1031 */         SQLWarning warn = doc.checkWarnings();
/* 1032 */         if (warn != null) setError(null, doc, warn);
/*      */       
/*      */       } 
/*      */     }
/* 1036 */     return (DTM)buildErrorDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() throws SQLException {
/* 1054 */     while (this.m_OpenSQLDocuments.size() != 0) {
/*      */       
/* 1056 */       SQLDocument d = this.m_OpenSQLDocuments.elementAt(0);
/*      */ 
/*      */       
/* 1059 */       try { d.close(); } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */       
/* 1063 */       this.m_OpenSQLDocuments.removeElementAt(0);
/*      */     } 
/*      */     
/* 1066 */     if (null != this.m_Connection) {
/*      */       
/* 1068 */       this.m_ConnectionPool.releaseConnection(this.m_Connection);
/* 1069 */       this.m_Connection = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close(SQLDocument sqldoc) throws SQLException {
/* 1088 */     int size = this.m_OpenSQLDocuments.size();
/*      */     
/* 1090 */     for (int x = 0; x < size; x++) {
/*      */       
/* 1092 */       SQLDocument d = this.m_OpenSQLDocuments.elementAt(x);
/* 1093 */       if (d == sqldoc) {
/*      */         
/* 1095 */         d.close();
/* 1096 */         this.m_OpenSQLDocuments.removeElementAt(x);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private SQLErrorDocument buildErrorDocument() {
/* 1109 */     SQLErrorDocument eDoc = null;
/*      */     
/* 1111 */     if (this.m_LastSQLDocumentWithError != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1117 */       ExpressionContext ctx = this.m_LastSQLDocumentWithError.getExpressionContext();
/* 1118 */       SQLWarning warn = this.m_LastSQLDocumentWithError.checkWarnings();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1123 */       try { DTMManager mgr = ((XPathContext.XPathExpressionContext)ctx).getDTMManager();
/*      */         
/* 1125 */         DTMManagerDefault mgrDefault = (DTMManagerDefault)mgr;
/* 1126 */         int dtmIdent = mgrDefault.getFirstFreeDTMID();
/*      */         
/* 1128 */         eDoc = new SQLErrorDocument(mgr, dtmIdent << 16, this.m_Error, warn, this.m_FullErrors);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1133 */         mgrDefault.addDTM((DTM)eDoc, dtmIdent);
/*      */ 
/*      */         
/* 1136 */         this.m_Error = null;
/* 1137 */         this.m_LastSQLDocumentWithError = null; } catch (Exception e)
/*      */       
/*      */       { 
/*      */         
/* 1141 */         eDoc = null; }
/*      */     
/*      */     } 
/*      */     
/* 1145 */     return eDoc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setError(Exception excp, ExpressionContext expr) {
/*      */     
/* 1158 */     try { ErrorListener listen = expr.getErrorListener();
/* 1159 */       if (listen != null && excp != null)
/*      */       {
/* 1161 */         listen.warning(new TransformerException(excp.toString(), expr.getXPathContext().getSAXLocator(), excp)); }  } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setError(Exception excp, SQLDocument doc, SQLWarning warn) {
/* 1178 */     ExpressionContext cont = doc.getExpressionContext();
/* 1179 */     this.m_LastSQLDocumentWithError = doc;
/*      */ 
/*      */ 
/*      */     
/* 1183 */     try { ErrorListener listen = cont.getErrorListener();
/* 1184 */       if (listen != null && excp != null) {
/* 1185 */         listen.warning(new TransformerException(excp.toString(), cont.getXPathContext().getSAXLocator(), excp));
/*      */       }
/*      */ 
/*      */       
/* 1189 */       if (listen != null && warn != null)
/*      */       {
/* 1191 */         listen.warning(new TransformerException(warn.toString(), cont.getXPathContext().getSAXLocator(), warn));
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1196 */       if (excp != null) this.m_Error = excp;
/*      */       
/* 1198 */       if (warn != null)
/*      */       
/*      */       { 
/*      */         
/* 1202 */         SQLWarning tw = new SQLWarning(warn.getMessage(), warn.getSQLState(), warn.getErrorCode());
/*      */ 
/*      */         
/* 1205 */         SQLWarning nw = warn.getNextWarning();
/* 1206 */         while (nw != null) {
/*      */           
/* 1208 */           tw.setNextWarning(new SQLWarning(nw.getMessage(), nw.getSQLState(), nw.getErrorCode()));
/*      */ 
/*      */           
/* 1211 */           nw = nw.getNextWarning();
/*      */         } 
/*      */         
/* 1214 */         tw.setNextWarning(new SQLWarning(warn.getMessage(), warn.getSQLState(), warn.getErrorCode())); }  } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeature(String feature, String setting) {
/* 1236 */     boolean value = false;
/*      */     
/* 1238 */     if ("true".equalsIgnoreCase(setting)) value = true;
/*      */     
/* 1240 */     if ("streaming".equalsIgnoreCase(feature)) {
/*      */       
/* 1242 */       this.m_IsStreamingEnabled = value;
/*      */     }
/* 1244 */     else if ("inline-variables".equalsIgnoreCase(feature)) {
/*      */       
/* 1246 */       this.m_InlineVariables = value;
/*      */     }
/* 1248 */     else if ("multiple-results".equalsIgnoreCase(feature)) {
/*      */       
/* 1250 */       this.m_IsMultipleResultsEnabled = value;
/*      */     }
/* 1252 */     else if ("cache-statements".equalsIgnoreCase(feature)) {
/*      */       
/* 1254 */       this.m_IsStatementCachingEnabled = value;
/*      */     }
/* 1256 */     else if ("default-pool-enabled".equalsIgnoreCase(feature)) {
/*      */       
/* 1258 */       this.m_DefaultPoolingEnabled = value;
/*      */       
/* 1260 */       if (this.m_ConnectionPool == null)
/* 1261 */         return;  if (this.m_IsDefaultPool)
/*      */         return; 
/* 1263 */       this.m_ConnectionPool.setPoolEnabled(value);
/*      */     }
/* 1265 */     else if ("full-errors".equalsIgnoreCase(feature)) {
/*      */       
/* 1267 */       this.m_FullErrors = value;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getFeature(String feature) {
/* 1278 */     String value = null;
/*      */     
/* 1280 */     if ("streaming".equalsIgnoreCase(feature)) {
/* 1281 */       value = this.m_IsStreamingEnabled ? "true" : "false";
/* 1282 */     } else if ("inline-variables".equalsIgnoreCase(feature)) {
/* 1283 */       value = this.m_InlineVariables ? "true" : "false";
/* 1284 */     } else if ("multiple-results".equalsIgnoreCase(feature)) {
/* 1285 */       value = this.m_IsMultipleResultsEnabled ? "true" : "false";
/* 1286 */     } else if ("cache-statements".equalsIgnoreCase(feature)) {
/* 1287 */       value = this.m_IsStatementCachingEnabled ? "true" : "false";
/* 1288 */     } else if ("default-pool-enabled".equalsIgnoreCase(feature)) {
/* 1289 */       value = this.m_DefaultPoolingEnabled ? "true" : "false";
/* 1290 */     } else if ("full-errors".equalsIgnoreCase(feature)) {
/* 1291 */       value = this.m_FullErrors ? "true" : "false";
/*      */     } 
/* 1293 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void finalize() {
/*      */     
/* 1306 */     try { close(); } catch (Exception exception) {}
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/sql/XConnection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */