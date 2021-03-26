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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DecimalToRoman
/*    */ {
/*    */   public long m_postValue;
/*    */   public String m_postLetter;
/*    */   public long m_preValue;
/*    */   public String m_preLetter;
/*    */   
/*    */   public DecimalToRoman(long postValue, String postLetter, long preValue, String preLetter) {
/* 43 */     this.m_postValue = postValue;
/* 44 */     this.m_postLetter = postLetter;
/* 45 */     this.m_preValue = preValue;
/* 46 */     this.m_preLetter = preLetter;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/DecimalToRoman.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */