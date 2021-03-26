/*      */ package org.apache.xalan.lib.sql;
/*      */ 
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Statement;
/*      */ import java.util.Vector;
/*      */ import org.apache.xalan.extensions.ExpressionContext;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.ref.DTMDefaultBase;
/*      */ import org.apache.xml.dtm.ref.DTMManagerDefault;
/*      */ import org.apache.xpath.XPathContext;
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
/*      */ public class SQLDocument
/*      */   extends DTMDocument
/*      */ {
/*      */   private boolean DEBUG = false;
/*      */   private static final String S_NAMESPACE = "http://xml.apache.org/xalan/SQLExtension";
/*      */   private static final String S_SQL = "sql";
/*      */   private static final String S_ROW_SET = "row-set";
/*      */   private static final String S_METADATA = "metadata";
/*      */   private static final String S_COLUMN_HEADER = "column-header";
/*      */   private static final String S_ROW = "row";
/*      */   private static final String S_COL = "col";
/*      */   private static final String S_OUT_PARAMETERS = "out-parameters";
/*      */   private static final String S_CATALOGUE_NAME = "catalogue-name";
/*      */   private static final String S_DISPLAY_SIZE = "column-display-size";
/*      */   private static final String S_COLUMN_LABEL = "column-label";
/*      */   private static final String S_COLUMN_NAME = "column-name";
/*      */   private static final String S_COLUMN_TYPE = "column-type";
/*      */   private static final String S_COLUMN_TYPENAME = "column-typename";
/*      */   private static final String S_PRECISION = "precision";
/*      */   private static final String S_SCALE = "scale";
/*      */   private static final String S_SCHEMA_NAME = "schema-name";
/*      */   private static final String S_TABLE_NAME = "table-name";
/*      */   private static final String S_CASESENSITIVE = "case-sensitive";
/*      */   private static final String S_DEFINITELYWRITABLE = "definitely-writable";
/*      */   private static final String S_ISNULLABLE = "nullable";
/*      */   private static final String S_ISSIGNED = "signed";
/*      */   private static final String S_ISWRITEABLE = "writable";
/*      */   private static final String S_ISSEARCHABLE = "searchable";
/*  131 */   private int m_SQL_TypeID = 0;
/*      */ 
/*      */   
/*  134 */   private int m_MetaData_TypeID = 0;
/*      */ 
/*      */   
/*  137 */   private int m_ColumnHeader_TypeID = 0;
/*      */ 
/*      */   
/*  140 */   private int m_RowSet_TypeID = 0;
/*      */ 
/*      */   
/*  143 */   private int m_Row_TypeID = 0;
/*      */ 
/*      */   
/*  146 */   private int m_Col_TypeID = 0;
/*      */ 
/*      */   
/*  149 */   private int m_OutParameter_TypeID = 0;
/*      */ 
/*      */ 
/*      */   
/*  153 */   private int m_ColAttrib_CATALOGUE_NAME_TypeID = 0;
/*      */ 
/*      */   
/*  156 */   private int m_ColAttrib_DISPLAY_SIZE_TypeID = 0;
/*      */ 
/*      */   
/*  159 */   private int m_ColAttrib_COLUMN_LABEL_TypeID = 0;
/*      */ 
/*      */   
/*  162 */   private int m_ColAttrib_COLUMN_NAME_TypeID = 0;
/*      */ 
/*      */   
/*  165 */   private int m_ColAttrib_COLUMN_TYPE_TypeID = 0;
/*      */ 
/*      */   
/*  168 */   private int m_ColAttrib_COLUMN_TYPENAME_TypeID = 0;
/*      */ 
/*      */   
/*  171 */   private int m_ColAttrib_PRECISION_TypeID = 0;
/*      */ 
/*      */   
/*  174 */   private int m_ColAttrib_SCALE_TypeID = 0;
/*      */ 
/*      */   
/*  177 */   private int m_ColAttrib_SCHEMA_NAME_TypeID = 0;
/*      */ 
/*      */   
/*  180 */   private int m_ColAttrib_TABLE_NAME_TypeID = 0;
/*      */ 
/*      */   
/*  183 */   private int m_ColAttrib_CASESENSITIVE_TypeID = 0;
/*      */ 
/*      */   
/*  186 */   private int m_ColAttrib_DEFINITELYWRITEABLE_TypeID = 0;
/*      */ 
/*      */   
/*  189 */   private int m_ColAttrib_ISNULLABLE_TypeID = 0;
/*      */ 
/*      */   
/*  192 */   private int m_ColAttrib_ISSIGNED_TypeID = 0;
/*      */ 
/*      */   
/*  195 */   private int m_ColAttrib_ISWRITEABLE_TypeID = 0;
/*      */ 
/*      */   
/*  198 */   private int m_ColAttrib_ISSEARCHABLE_TypeID = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  203 */   private Statement m_Statement = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  209 */   private ExpressionContext m_ExpressionContext = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  215 */   private ConnectionPool m_ConnectionPool = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  220 */   private ResultSet m_ResultSet = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  226 */   private SQLQueryParser m_QueryParser = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] m_ColHeadersIdx;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int m_ColCount;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  245 */   private int m_MetaDataIdx = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  251 */   private int m_RowSetIdx = -1;
/*      */ 
/*      */ 
/*      */   
/*  255 */   private int m_SQLIdx = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  261 */   private int m_FirstRowIdx = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  268 */   private int m_LastRowIdx = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_StreamingMode = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_MultipleResults = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_HasErrors = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_IsStatementCachingEnabled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  296 */   private XConnection m_XConnection = null;
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
/*      */   public SQLDocument(DTMManager mgr, int ident) {
/*  314 */     super(mgr, ident);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static SQLDocument getNewDocument(ExpressionContext exprContext) {
/*  325 */     DTMManager mgr = ((XPathContext.XPathExpressionContext)exprContext).getDTMManager();
/*      */     
/*  327 */     DTMManagerDefault mgrDefault = (DTMManagerDefault)mgr;
/*      */ 
/*      */     
/*  330 */     int dtmIdent = mgrDefault.getFirstFreeDTMID();
/*      */     
/*  332 */     SQLDocument doc = new SQLDocument(mgr, dtmIdent << 16);
/*      */ 
/*      */ 
/*      */     
/*  336 */     mgrDefault.addDTM((DTM)doc, dtmIdent);
/*  337 */     doc.setExpressionContext(exprContext);
/*      */     
/*  339 */     return doc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setExpressionContext(ExpressionContext expr) {
/*  350 */     this.m_ExpressionContext = expr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ExpressionContext getExpressionContext() {
/*  358 */     return this.m_ExpressionContext;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void execute(XConnection xconn, SQLQueryParser query) throws SQLException {
/*      */     
/*  367 */     try { this.m_StreamingMode = "true".equals(xconn.getFeature("streaming"));
/*  368 */       this.m_MultipleResults = "true".equals(xconn.getFeature("multiple-results"));
/*  369 */       this.m_IsStatementCachingEnabled = "true".equals(xconn.getFeature("cache-statements"));
/*  370 */       this.m_XConnection = xconn;
/*  371 */       this.m_QueryParser = query;
/*      */       
/*  373 */       executeSQLStatement();
/*      */       
/*  375 */       createExpandedNameTable();
/*      */ 
/*      */       
/*  378 */       this.m_DocumentIdx = addElement(0, this.m_Document_TypeID, -1, -1);
/*  379 */       this.m_SQLIdx = addElement(1, this.m_SQL_TypeID, this.m_DocumentIdx, -1);
/*      */ 
/*      */       
/*  382 */       if (!this.m_MultipleResults) {
/*  383 */         extractSQLMetaData(this.m_ResultSet.getMetaData());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  389 */       addRowToDTMFromResultSet(); } catch (SQLException e)
/*      */     
/*      */     { 
/*      */       
/*  393 */       this.m_HasErrors = true;
/*  394 */       throw e; }
/*      */   
/*      */   }
/*      */ 
/*      */   
/*      */   private void executeSQLStatement() throws SQLException {
/*  400 */     this.m_ConnectionPool = this.m_XConnection.getConnectionPool();
/*      */     
/*  402 */     Connection conn = this.m_ConnectionPool.getConnection();
/*      */     
/*  404 */     if (!this.m_QueryParser.hasParameters()) {
/*      */       
/*  406 */       this.m_Statement = conn.createStatement();
/*  407 */       if (!this.m_Statement.execute(this.m_QueryParser.getSQLQuery()))
/*      */       {
/*  409 */         throw new SQLException("Error in Query");
/*      */       }
/*      */     }
/*  412 */     else if (this.m_QueryParser.isCallable()) {
/*      */       
/*  414 */       CallableStatement cstmt = conn.prepareCall(this.m_QueryParser.getSQLQuery());
/*      */       
/*  416 */       this.m_QueryParser.registerOutputParameters(cstmt);
/*  417 */       this.m_QueryParser.populateStatement(cstmt, this.m_ExpressionContext);
/*  418 */       this.m_Statement = cstmt;
/*  419 */       if (!cstmt.execute()) throw new SQLException("Error in Callable Statement");
/*      */     
/*      */     } else {
/*      */       
/*  423 */       PreparedStatement stmt = conn.prepareStatement(this.m_QueryParser.getSQLQuery());
/*      */       
/*  425 */       this.m_QueryParser.populateStatement(stmt, this.m_ExpressionContext);
/*  426 */       this.m_Statement = stmt;
/*  427 */       if (!stmt.execute()) throw new SQLException("Error in Prepared Statement");
/*      */     
/*      */     } 
/*  430 */     this.m_ResultSet = this.m_Statement.getResultSet();
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
/*      */   private void extractSQLMetaData(ResultSetMetaData meta) {
/*  447 */     this.m_MetaDataIdx = addElement(1, this.m_MetaData_TypeID, this.m_MultipleResults ? this.m_RowSetIdx : this.m_SQLIdx, -1);
/*      */ 
/*      */ 
/*      */     
/*  451 */     try { this.m_ColCount = meta.getColumnCount();
/*  452 */       this.m_ColHeadersIdx = new int[this.m_ColCount]; } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  456 */       this.m_XConnection.setError(e, this, checkWarnings()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  462 */     int lastColHeaderIdx = -1;
/*      */ 
/*      */     
/*  465 */     int i = 1;
/*  466 */     for (i = 1; i <= this.m_ColCount; i++) {
/*      */       
/*  468 */       this.m_ColHeadersIdx[i - 1] = addElement(2, this.m_ColumnHeader_TypeID, this.m_MetaDataIdx, lastColHeaderIdx);
/*      */ 
/*      */       
/*  471 */       lastColHeaderIdx = this.m_ColHeadersIdx[i - 1];
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  476 */       try { addAttributeToNode(meta.getColumnName(i), this.m_ColAttrib_COLUMN_NAME_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  482 */         addAttributeToNode("Not Supported", this.m_ColAttrib_COLUMN_NAME_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  489 */       try { addAttributeToNode(meta.getColumnLabel(i), this.m_ColAttrib_COLUMN_LABEL_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  495 */         addAttributeToNode("Not Supported", this.m_ColAttrib_COLUMN_LABEL_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  502 */       try { addAttributeToNode(meta.getCatalogName(i), this.m_ColAttrib_CATALOGUE_NAME_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  508 */         addAttributeToNode("Not Supported", this.m_ColAttrib_CATALOGUE_NAME_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  515 */       try { addAttributeToNode(new Integer(meta.getColumnDisplaySize(i)), this.m_ColAttrib_DISPLAY_SIZE_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  521 */         addAttributeToNode("Not Supported", this.m_ColAttrib_DISPLAY_SIZE_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  528 */       try { addAttributeToNode(new Integer(meta.getColumnType(i)), this.m_ColAttrib_COLUMN_TYPE_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  534 */         addAttributeToNode("Not Supported", this.m_ColAttrib_COLUMN_TYPE_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  541 */       try { addAttributeToNode(meta.getColumnTypeName(i), this.m_ColAttrib_COLUMN_TYPENAME_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  547 */         addAttributeToNode("Not Supported", this.m_ColAttrib_COLUMN_TYPENAME_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  554 */       try { addAttributeToNode(new Integer(meta.getPrecision(i)), this.m_ColAttrib_PRECISION_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  560 */         addAttributeToNode("Not Supported", this.m_ColAttrib_PRECISION_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  566 */       try { addAttributeToNode(new Integer(meta.getScale(i)), this.m_ColAttrib_SCALE_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  572 */         addAttributeToNode("Not Supported", this.m_ColAttrib_SCALE_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  579 */       try { addAttributeToNode(meta.getSchemaName(i), this.m_ColAttrib_SCHEMA_NAME_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  585 */         addAttributeToNode("Not Supported", this.m_ColAttrib_SCHEMA_NAME_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  591 */       try { addAttributeToNode(meta.getTableName(i), this.m_ColAttrib_TABLE_NAME_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  597 */         addAttributeToNode("Not Supported", this.m_ColAttrib_TABLE_NAME_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  604 */       try { addAttributeToNode(meta.isCaseSensitive(i) ? "true" : "false", this.m_ColAttrib_CASESENSITIVE_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  610 */         addAttributeToNode("Not Supported", this.m_ColAttrib_CASESENSITIVE_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  617 */       try { addAttributeToNode(meta.isDefinitelyWritable(i) ? "true" : "false", this.m_ColAttrib_DEFINITELYWRITEABLE_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  623 */         addAttributeToNode("Not Supported", this.m_ColAttrib_DEFINITELYWRITEABLE_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  630 */       try { addAttributeToNode((meta.isNullable(i) != 0) ? "true" : "false", this.m_ColAttrib_ISNULLABLE_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  636 */         addAttributeToNode("Not Supported", this.m_ColAttrib_ISNULLABLE_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  643 */       try { addAttributeToNode(meta.isSigned(i) ? "true" : "false", this.m_ColAttrib_ISSIGNED_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  649 */         addAttributeToNode("Not Supported", this.m_ColAttrib_ISSIGNED_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  656 */       try { addAttributeToNode((meta.isWritable(i) == true) ? "true" : "false", this.m_ColAttrib_ISWRITEABLE_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  662 */         addAttributeToNode("Not Supported", this.m_ColAttrib_ISWRITEABLE_TypeID, lastColHeaderIdx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  669 */       try { addAttributeToNode((meta.isSearchable(i) == true) ? "true" : "false", this.m_ColAttrib_ISSEARCHABLE_TypeID, lastColHeaderIdx); } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  675 */         addAttributeToNode("Not Supported", this.m_ColAttrib_ISSEARCHABLE_TypeID, lastColHeaderIdx); }
/*      */     
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
/*      */   protected void createExpandedNameTable() {
/*  689 */     super.createExpandedNameTable();
/*      */     
/*  691 */     this.m_SQL_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "sql", 1);
/*      */ 
/*      */     
/*  694 */     this.m_MetaData_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "metadata", 1);
/*      */ 
/*      */     
/*  697 */     this.m_ColumnHeader_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-header", 1);
/*      */     
/*  699 */     this.m_RowSet_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "row-set", 1);
/*      */     
/*  701 */     this.m_Row_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "row", 1);
/*      */     
/*  703 */     this.m_Col_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "col", 1);
/*      */     
/*  705 */     this.m_OutParameter_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "out-parameters", 1);
/*      */ 
/*      */     
/*  708 */     this.m_ColAttrib_CATALOGUE_NAME_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "catalogue-name", 2);
/*      */     
/*  710 */     this.m_ColAttrib_DISPLAY_SIZE_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-display-size", 2);
/*      */     
/*  712 */     this.m_ColAttrib_COLUMN_LABEL_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-label", 2);
/*      */     
/*  714 */     this.m_ColAttrib_COLUMN_NAME_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-name", 2);
/*      */     
/*  716 */     this.m_ColAttrib_COLUMN_TYPE_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-type", 2);
/*      */     
/*  718 */     this.m_ColAttrib_COLUMN_TYPENAME_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "column-typename", 2);
/*      */     
/*  720 */     this.m_ColAttrib_PRECISION_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "precision", 2);
/*      */     
/*  722 */     this.m_ColAttrib_SCALE_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "scale", 2);
/*      */     
/*  724 */     this.m_ColAttrib_SCHEMA_NAME_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "schema-name", 2);
/*      */     
/*  726 */     this.m_ColAttrib_TABLE_NAME_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "table-name", 2);
/*      */     
/*  728 */     this.m_ColAttrib_CASESENSITIVE_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "case-sensitive", 2);
/*      */     
/*  730 */     this.m_ColAttrib_DEFINITELYWRITEABLE_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "definitely-writable", 2);
/*      */     
/*  732 */     this.m_ColAttrib_ISNULLABLE_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "nullable", 2);
/*      */     
/*  734 */     this.m_ColAttrib_ISSIGNED_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "signed", 2);
/*      */     
/*  736 */     this.m_ColAttrib_ISWRITEABLE_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "writable", 2);
/*      */     
/*  738 */     this.m_ColAttrib_ISSEARCHABLE_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "searchable", 2);
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
/*      */   private boolean addRowToDTMFromResultSet() {
/*      */     try {
/*  757 */       if (this.m_FirstRowIdx == -1) {
/*      */         
/*  759 */         this.m_RowSetIdx = addElement(1, this.m_RowSet_TypeID, this.m_SQLIdx, this.m_MultipleResults ? this.m_RowSetIdx : this.m_MetaDataIdx);
/*      */         
/*  761 */         if (this.m_MultipleResults) extractSQLMetaData(this.m_ResultSet.getMetaData());
/*      */       
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  767 */       if (!this.m_ResultSet.next()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  774 */         if (this.m_StreamingMode && this.m_LastRowIdx != -1)
/*      */         {
/*      */           
/*  777 */           ((DTMDefaultBase)this).m_nextsib.setElementAt(-1, this.m_LastRowIdx);
/*      */         }
/*      */         
/*  780 */         this.m_ResultSet.close();
/*  781 */         if (this.m_MultipleResults) { do {
/*      */           
/*  783 */           } while (!this.m_Statement.getMoreResults() && this.m_Statement.getUpdateCount() >= 0);
/*  784 */           this.m_ResultSet = this.m_Statement.getResultSet(); }
/*      */         else
/*      */         
/*  787 */         { this.m_ResultSet = null; }
/*      */         
/*  789 */         if (this.m_ResultSet != null) {
/*      */           
/*  791 */           this.m_FirstRowIdx = -1;
/*  792 */           addRowToDTMFromResultSet();
/*      */         }
/*      */         else {
/*      */           
/*  796 */           Vector parameters = this.m_QueryParser.getParameters();
/*      */           
/*  798 */           if (parameters != null) {
/*      */             
/*  800 */             int outParamIdx = addElement(1, this.m_OutParameter_TypeID, this.m_SQLIdx, this.m_RowSetIdx);
/*  801 */             int lastColID = -1;
/*  802 */             for (int indx = 0; indx < parameters.size(); indx++) {
/*      */               
/*  804 */               QueryParameter parm = parameters.elementAt(indx);
/*  805 */               if (parm.isOutput()) {
/*      */                 
/*  807 */                 Object rawobj = ((CallableStatement)this.m_Statement).getObject(indx + 1);
/*  808 */                 lastColID = addElementWithData(rawobj, 2, this.m_Col_TypeID, outParamIdx, lastColID);
/*  809 */                 addAttributeToNode(parm.getName(), this.m_ColAttrib_COLUMN_NAME_TypeID, lastColID);
/*  810 */                 addAttributeToNode(parm.getName(), this.m_ColAttrib_COLUMN_LABEL_TypeID, lastColID);
/*  811 */                 addAttributeToNode(new Integer(parm.getType()), this.m_ColAttrib_COLUMN_TYPE_TypeID, lastColID);
/*  812 */                 addAttributeToNode(parm.getTypeName(), this.m_ColAttrib_COLUMN_TYPENAME_TypeID, lastColID);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/*  817 */           SQLWarning warn = checkWarnings();
/*  818 */           if (warn != null) this.m_XConnection.setError(null, null, warn);
/*      */         
/*      */         } 
/*  821 */         return false;
/*      */       } 
/*      */ 
/*      */       
/*  825 */       if (this.m_FirstRowIdx == -1) {
/*      */         
/*  827 */         this.m_FirstRowIdx = addElement(2, this.m_Row_TypeID, this.m_RowSetIdx, this.m_MultipleResults ? this.m_MetaDataIdx : -1);
/*      */ 
/*      */         
/*  830 */         this.m_LastRowIdx = this.m_FirstRowIdx;
/*      */         
/*  832 */         if (this.m_StreamingMode)
/*      */         {
/*      */           
/*  835 */           ((DTMDefaultBase)this).m_nextsib.setElementAt(this.m_LastRowIdx, this.m_LastRowIdx);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*  843 */       else if (!this.m_StreamingMode) {
/*      */         
/*  845 */         this.m_LastRowIdx = addElement(2, this.m_Row_TypeID, this.m_RowSetIdx, this.m_LastRowIdx);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  851 */       int colID = _firstch(this.m_LastRowIdx);
/*      */ 
/*      */       
/*  854 */       int pcolID = -1;
/*      */ 
/*      */       
/*  857 */       for (int i = 1; i <= this.m_ColCount; i++)
/*      */       {
/*      */ 
/*      */         
/*  861 */         Object o = this.m_ResultSet.getObject(i);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  866 */         if (colID == -1) {
/*      */           
/*  868 */           pcolID = addElementWithData(o, 3, this.m_Col_TypeID, this.m_LastRowIdx, pcolID);
/*  869 */           cloneAttributeFromNode(pcolID, this.m_ColHeadersIdx[i - 1]);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/*  875 */           int dataIdent = _firstch(colID);
/*  876 */           if (dataIdent == -1) {
/*      */             
/*  878 */             error("Streaming Mode, Data Error");
/*      */           }
/*      */           else {
/*      */             
/*  882 */             this.m_ObjectArray.setAt(dataIdent, o);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  889 */         if (colID != -1)
/*      */         {
/*  891 */           colID = _nextsib(colID);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*      */     catch (Exception e) {
/*      */       
/*  898 */       if (this.DEBUG)
/*      */       {
/*  900 */         System.out.println("SQL Error Fetching next row [" + e.getLocalizedMessage() + "]");
/*      */       }
/*      */ 
/*      */       
/*  904 */       this.m_XConnection.setError(e, this, checkWarnings());
/*  905 */       this.m_HasErrors = true;
/*      */     } 
/*      */ 
/*      */     
/*  909 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasErrors() {
/*  919 */     return this.m_HasErrors;
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
/*      */   public void close() {
/*      */     
/*  934 */     try { SQLWarning warn = checkWarnings();
/*  935 */       if (warn != null) this.m_XConnection.setError(null, null, warn);  } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  941 */     try { if (null != this.m_ResultSet)
/*      */       
/*  943 */       { this.m_ResultSet.close();
/*  944 */         this.m_ResultSet = null; }  } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  950 */     Connection conn = null;
/*      */ 
/*      */ 
/*      */     
/*  954 */     try { if (null != this.m_Statement)
/*      */       
/*  956 */       { conn = this.m_Statement.getConnection();
/*  957 */         this.m_Statement.close();
/*  958 */         this.m_Statement = null; }  } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  965 */     try { if (conn != null)
/*      */       {
/*  967 */         if (this.m_HasErrors) { this.m_ConnectionPool.releaseConnectionOnError(conn); }
/*  968 */         else { this.m_ConnectionPool.releaseConnection(conn); }  }  } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  973 */     getManager().release((DTM)this, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean nextNode() {
/*  981 */     if (this.DEBUG) System.out.println("nextNode()");
/*      */ 
/*      */     
/*  984 */     try { return false; } catch (Exception e)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  989 */       return false; }
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
/*      */   protected int _nextsib(int identity) {
/* 1004 */     if (this.m_ResultSet != null) {
/*      */       
/* 1006 */       int id = _exptype(identity);
/* 1007 */       if (id == this.m_Row_TypeID && identity >= this.m_LastRowIdx) {
/*      */ 
/*      */ 
/*      */         
/* 1011 */         if (this.DEBUG) System.out.println("reading from the ResultSet"); 
/* 1012 */         addRowToDTMFromResultSet();
/*      */       }
/* 1014 */       else if (this.m_MultipleResults && identity == this.m_RowSetIdx) {
/*      */         
/* 1016 */         if (this.DEBUG) System.out.println("reading for next ResultSet"); 
/* 1017 */         int startIdx = this.m_RowSetIdx;
/* 1018 */         while (startIdx == this.m_RowSetIdx && this.m_ResultSet != null) {
/* 1019 */           addRowToDTMFromResultSet();
/*      */         }
/*      */       } 
/*      */     } 
/* 1023 */     return super._nextsib(identity);
/*      */   }
/*      */ 
/*      */   
/*      */   public void documentRegistration() {
/* 1028 */     if (this.DEBUG) System.out.println("Document Registration");
/*      */   
/*      */   }
/*      */   
/*      */   public void documentRelease() {
/* 1033 */     if (this.DEBUG) System.out.println("Document Release");
/*      */   
/*      */   }
/*      */   
/*      */   public SQLWarning checkWarnings() {
/* 1038 */     SQLWarning warn = null;
/* 1039 */     if (this.m_Statement != null) {
/*      */ 
/*      */       
/*      */       try { 
/* 1043 */         warn = this.m_Statement.getWarnings();
/* 1044 */         this.m_Statement.clearWarnings(); } catch (SQLException sQLException) {}
/*      */     }
/*      */ 
/*      */     
/* 1048 */     return warn;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/sql/SQLDocument.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */