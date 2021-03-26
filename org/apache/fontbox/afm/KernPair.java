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
/*    */ public class KernPair
/*    */ {
/*    */   private String firstKernCharacter;
/*    */   private String secondKernCharacter;
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public String getFirstKernCharacter() {
/* 36 */     return this.firstKernCharacter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFirstKernCharacter(String firstKernCharacterValue) {
/* 44 */     this.firstKernCharacter = firstKernCharacterValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSecondKernCharacter() {
/* 52 */     return this.secondKernCharacter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSecondKernCharacter(String secondKernCharacterValue) {
/* 60 */     this.secondKernCharacter = secondKernCharacterValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getX() {
/* 68 */     return this.x;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setX(float xValue) {
/* 76 */     this.x = xValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getY() {
/* 84 */     return this.y;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setY(float yValue) {
/* 92 */     this.y = yValue;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/afm/KernPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */