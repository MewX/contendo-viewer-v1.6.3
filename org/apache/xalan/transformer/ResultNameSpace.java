/*    */ package org.apache.xalan.transformer;
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
/*    */ 
/*    */ public class ResultNameSpace
/*    */ {
/* 31 */   public ResultNameSpace m_next = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String m_prefix;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String m_uri;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultNameSpace(String prefix, String uri) {
/* 48 */     this.m_prefix = prefix;
/* 49 */     this.m_uri = uri;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/ResultNameSpace.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */