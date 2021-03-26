/*     */ package org.apache.xalan.lib;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Hashtable;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.apache.xalan.extensions.ExpressionContext;
/*     */ import org.apache.xalan.xslt.EnvironmentCheck;
/*     */ import org.apache.xml.utils.Hashtree2Node;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xpath.NodeSet;
/*     */ import org.apache.xpath.objects.XBoolean;
/*     */ import org.apache.xpath.objects.XNumber;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentFragment;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.Text;
/*     */ import org.w3c.dom.traversal.NodeIterator;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Extensions
/*     */ {
/*     */   public static NodeSet nodeset(ExpressionContext myProcessor, Object rtf) {
/*     */     String str;
/*  87 */     if (rtf instanceof NodeIterator)
/*     */     {
/*  89 */       return new NodeSet((NodeIterator)rtf);
/*     */     }
/*     */ 
/*     */     
/*  93 */     if (rtf instanceof String) {
/*     */       
/*  95 */       str = (String)rtf;
/*     */     }
/*  97 */     else if (rtf instanceof Boolean) {
/*     */       
/*  99 */       str = (new XBoolean(((Boolean)rtf).booleanValue())).str();
/*     */     }
/* 101 */     else if (rtf instanceof Double) {
/*     */       
/* 103 */       str = (new XNumber(((Double)rtf).doubleValue())).str();
/*     */     }
/*     */     else {
/*     */       
/* 107 */       str = rtf.toString();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     try { DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
/* 115 */       DocumentBuilder db = dbf.newDocumentBuilder();
/* 116 */       Document myDoc = db.newDocument();
/*     */       
/* 118 */       Text textNode = myDoc.createTextNode(str);
/* 119 */       DocumentFragment docFrag = myDoc.createDocumentFragment();
/*     */       
/* 121 */       docFrag.appendChild(textNode);
/*     */       
/* 123 */       return new NodeSet(docFrag); } catch (ParserConfigurationException pce)
/*     */     
/*     */     { 
/*     */       
/* 127 */       throw new WrappedRuntimeException(pce); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeList intersection(NodeList nl1, NodeList nl2) {
/* 145 */     return ExsltSets.intersection(nl1, nl2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeList difference(NodeList nl1, NodeList nl2) {
/* 161 */     return ExsltSets.difference(nl1, nl2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeList distinct(NodeList nl) {
/* 178 */     return ExsltSets.distinct(nl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasSameNodes(NodeList nl1, NodeList nl2) {
/* 191 */     NodeSet ns1 = new NodeSet(nl1);
/* 192 */     NodeSet ns2 = new NodeSet(nl2);
/*     */     
/* 194 */     if (ns1.getLength() != ns2.getLength()) {
/* 195 */       return false;
/*     */     }
/* 197 */     for (int i = 0; i < ns1.getLength(); i++) {
/*     */       
/* 199 */       Node n = ns1.elementAt(i);
/*     */       
/* 201 */       if (!ns2.contains(n)) {
/* 202 */         return false;
/*     */       }
/*     */     } 
/* 205 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static XObject evaluate(ExpressionContext myContext, String xpathExpr) throws SAXNotSupportedException {
/* 228 */     return ExsltDynamic.evaluate(myContext, xpathExpr);
/*     */   }
/*     */   public static NodeList tokenize(String toTokenize) { return tokenize(toTokenize, " \t\n\r"); }
/*     */   public static Node checkEnvironment(ExpressionContext myContext) { Document factoryDocument; try {
/*     */       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); DocumentBuilder db = dbf.newDocumentBuilder(); factoryDocument = db.newDocument();
/*     */     } catch (ParserConfigurationException pce) {
/*     */       throw new WrappedRuntimeException(pce);
/*     */     }  Node resultNode = null; try {
/*     */       resultNode = checkEnvironmentUsingWhich(myContext, factoryDocument); if (null != resultNode)
/*     */         return resultNode;  EnvironmentCheck envChecker = new EnvironmentCheck(); Hashtable h = envChecker.getEnvironmentHash(); resultNode = factoryDocument.createElement("checkEnvironmentExtension"); envChecker.appendEnvironmentReport(resultNode, factoryDocument, h); envChecker = null;
/*     */     } catch (Exception e) {
/*     */       throw new WrappedRuntimeException(e);
/*     */     }  return resultNode; } private static Node checkEnvironmentUsingWhich(ExpressionContext myContext, Document factoryDocument) { String WHICH_CLASSNAME = "org.apache.env.Which"; String WHICH_METHODNAME = "which"; Class[] WHICH_METHOD_ARGS = { Hashtable.class, String.class, String.class }; try {
/*     */       Class clazz = ObjectFactory.findProviderClass("org.apache.env.Which", ObjectFactory.findClassLoader(), true); if (null == clazz)
/*     */         return null;  Method method = clazz.getMethod("which", WHICH_METHOD_ARGS); Hashtable report = new Hashtable(); Object[] methodArgs = { report, "XmlCommons;Xalan;Xerces;Crimson;Ant", "" };
/*     */       Object returnValue = method.invoke(null, methodArgs);
/*     */       Node resultNode = factoryDocument.createElement("checkEnvironmentExtension");
/*     */       Hashtree2Node.appendHashToNode(report, "whichReport", resultNode, factoryDocument);
/*     */       return resultNode;
/*     */     } catch (Throwable t) {
/*     */       return null;
/* 249 */     }  } public static NodeList tokenize(String toTokenize, String delims) { Document doc = 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 402 */       DocumentHolder.m_doc;
/*     */     StringTokenizer lTokenizer = new StringTokenizer(toTokenize, delims);
/*     */     NodeSet resultSet = new NodeSet();
/*     */     synchronized (doc) {
/*     */       while (lTokenizer.hasMoreTokens())
/*     */         resultSet.addNode(doc.createTextNode(lTokenizer.nextToken())); 
/*     */     } 
/*     */     return (NodeList)resultSet; } private static class DocumentHolder { static { 
/* 410 */       try { m_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument(); } catch (ParserConfigurationException pce)
/*     */       
/*     */       { 
/*     */ 
/*     */         
/* 415 */         throw new WrappedRuntimeException(pce); }
/*     */        }
/*     */ 
/*     */     
/*     */     private static final Document m_doc; }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/Extensions.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */