/*    */ package org.apache.batik.bridge;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextHit
/*    */ {
/*    */   private int charIndex;
/*    */   private boolean leadingEdge;
/*    */   
/*    */   public TextHit(int charIndex, boolean leadingEdge) {
/* 46 */     this.charIndex = charIndex;
/* 47 */     this.leadingEdge = leadingEdge;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCharIndex() {
/* 56 */     return this.charIndex;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isLeadingEdge() {
/* 65 */     return this.leadingEdge;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/TextHit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */