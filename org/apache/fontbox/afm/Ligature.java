/*    */ package org.apache.fontbox.afm;
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
/*    */ public class Ligature
/*    */ {
/*    */   private String successor;
/*    */   private String ligature;
/*    */   
/*    */   public String getLigature() {
/* 34 */     return this.ligature;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLigature(String lig) {
/* 42 */     this.ligature = lig;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSuccessor() {
/* 50 */     return this.successor;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSuccessor(String successorValue) {
/* 58 */     this.successor = successorValue;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/afm/Ligature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */