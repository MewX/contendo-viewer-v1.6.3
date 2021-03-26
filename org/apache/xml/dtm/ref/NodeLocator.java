/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import javax.xml.transform.SourceLocator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NodeLocator
/*     */   implements SourceLocator
/*     */ {
/*     */   protected String m_publicId;
/*     */   protected String m_systemId;
/*     */   protected int m_lineNumber;
/*     */   protected int m_columnNumber;
/*     */   
/*     */   public NodeLocator(String publicId, String systemId, int lineNumber, int columnNumber) {
/*  49 */     this.m_publicId = publicId;
/*  50 */     this.m_systemId = systemId;
/*  51 */     this.m_lineNumber = lineNumber;
/*  52 */     this.m_columnNumber = columnNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPublicId() {
/*  62 */     return this.m_publicId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSystemId() {
/*  72 */     return this.m_systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineNumber() {
/*  82 */     return this.m_lineNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumnNumber() {
/*  93 */     return this.m_columnNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 104 */     return "file '" + this.m_systemId + "', line #" + this.m_lineNumber + ", column #" + this.m_columnNumber;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/NodeLocator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */