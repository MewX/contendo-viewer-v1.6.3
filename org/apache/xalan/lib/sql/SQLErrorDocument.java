/*     */ package org.apache.xalan.lib.sql;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.sql.SQLWarning;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xml.dtm.ref.DTMDefaultBase;
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
/*     */ public class SQLErrorDocument
/*     */   extends DTMDocument
/*     */ {
/*     */   private static final String S_EXT_ERROR = "ext-error";
/*     */   private static final String S_SQL_ERROR = "sql-error";
/*     */   private static final String S_MESSAGE = "message";
/*     */   private static final String S_CODE = "code";
/*     */   private static final String S_STATE = "state";
/*     */   private static final String S_SQL_WARNING = "sql-warning";
/*  72 */   private int m_ErrorExt_TypeID = -1;
/*     */ 
/*     */   
/*  75 */   private int m_Message_TypeID = -1;
/*     */ 
/*     */   
/*  78 */   private int m_Code_TypeID = -1;
/*     */ 
/*     */ 
/*     */   
/*  82 */   private int m_State_TypeID = -1;
/*     */ 
/*     */ 
/*     */   
/*  86 */   private int m_SQLWarning_TypeID = -1;
/*     */ 
/*     */ 
/*     */   
/*  90 */   private int m_SQLError_TypeID = -1;
/*     */ 
/*     */ 
/*     */   
/*  94 */   private int m_rootID = -1;
/*     */ 
/*     */   
/*  97 */   private int m_extErrorID = -1;
/*     */ 
/*     */   
/* 100 */   private int m_MainMessageID = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SQLErrorDocument(DTMManager mgr, int ident, SQLException error) {
/* 111 */     super(mgr, ident);
/*     */     
/* 113 */     createExpandedNameTable();
/* 114 */     buildBasicStructure(error);
/*     */     
/* 116 */     int sqlError = addElement(2, this.m_SQLError_TypeID, this.m_extErrorID, this.m_MainMessageID);
/* 117 */     int element = -1;
/*     */     
/* 119 */     element = addElementWithData(new Integer(error.getErrorCode()), 3, this.m_Code_TypeID, sqlError, element);
/*     */ 
/*     */ 
/*     */     
/* 123 */     element = addElementWithData(error.getLocalizedMessage(), 3, this.m_Message_TypeID, sqlError, element);
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
/*     */   public SQLErrorDocument(DTMManager mgr, int ident, Exception error) {
/* 139 */     super(mgr, ident);
/* 140 */     createExpandedNameTable();
/* 141 */     buildBasicStructure(error);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SQLErrorDocument(DTMManager mgr, int ident, Exception error, SQLWarning warning, boolean full) {
/* 152 */     super(mgr, ident);
/* 153 */     createExpandedNameTable();
/* 154 */     buildBasicStructure(error);
/*     */     
/* 156 */     SQLException se = null;
/* 157 */     int prev = this.m_MainMessageID;
/* 158 */     boolean inWarnings = false;
/*     */     
/* 160 */     if (error != null && error instanceof SQLException) {
/* 161 */       se = (SQLException)error;
/* 162 */     } else if (full && warning != null) {
/*     */       
/* 164 */       se = warning;
/* 165 */       inWarnings = true;
/*     */     } 
/*     */     
/* 168 */     while (se != null) {
/*     */       
/* 170 */       int sqlError = addElement(2, inWarnings ? this.m_SQLWarning_TypeID : this.m_SQLError_TypeID, this.m_extErrorID, prev);
/* 171 */       prev = sqlError;
/* 172 */       int element = -1;
/*     */       
/* 174 */       element = addElementWithData(new Integer(se.getErrorCode()), 3, this.m_Code_TypeID, sqlError, element);
/*     */ 
/*     */ 
/*     */       
/* 178 */       element = addElementWithData(se.getLocalizedMessage(), 3, this.m_Message_TypeID, sqlError, element);
/*     */ 
/*     */ 
/*     */       
/* 182 */       if (full) {
/*     */         
/* 184 */         String state = se.getSQLState();
/* 185 */         if (state != null && state.length() > 0) {
/* 186 */           element = addElementWithData(state, 3, this.m_State_TypeID, sqlError, element);
/*     */         }
/*     */ 
/*     */         
/* 190 */         if (inWarnings) {
/* 191 */           se = ((SQLWarning)se).getNextWarning(); continue;
/*     */         } 
/* 193 */         se = se.getNextException();
/*     */         continue;
/*     */       } 
/* 196 */       se = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void buildBasicStructure(Exception e) {
/* 207 */     this.m_rootID = addElement(0, this.m_Document_TypeID, -1, -1);
/* 208 */     this.m_extErrorID = addElement(1, this.m_ErrorExt_TypeID, this.m_rootID, -1);
/* 209 */     this.m_MainMessageID = addElementWithData((e != null) ? e.getLocalizedMessage() : "SQLWarning", 2, this.m_Message_TypeID, this.m_extErrorID, -1);
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
/*     */   protected void createExpandedNameTable() {
/* 221 */     super.createExpandedNameTable();
/*     */     
/* 223 */     this.m_ErrorExt_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "ext-error", 1);
/*     */ 
/*     */     
/* 226 */     this.m_SQLError_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "sql-error", 1);
/*     */ 
/*     */     
/* 229 */     this.m_Message_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "message", 1);
/*     */ 
/*     */     
/* 232 */     this.m_Code_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "code", 1);
/*     */ 
/*     */     
/* 235 */     this.m_State_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "state", 1);
/*     */ 
/*     */     
/* 238 */     this.m_SQLWarning_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "sql-warning", 1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/sql/SQLErrorDocument.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */