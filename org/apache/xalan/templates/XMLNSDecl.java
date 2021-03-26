/*    */ package org.apache.xalan.templates;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XMLNSDecl
/*    */   implements Serializable
/*    */ {
/*    */   private String m_prefix;
/*    */   private String m_uri;
/*    */   private boolean m_isExcluded;
/*    */   
/*    */   public XMLNSDecl(String prefix, String uri, boolean isExcluded) {
/* 38 */     this.m_prefix = prefix;
/* 39 */     this.m_uri = uri;
/* 40 */     this.m_isExcluded = isExcluded;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPrefix() {
/* 54 */     return this.m_prefix;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getURI() {
/* 67 */     return this.m_uri;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getIsExcluded() {
/* 82 */     return this.m_isExcluded;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/XMLNSDecl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */