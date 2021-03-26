/*    */ package org.apache.pdfbox.pdmodel.interactive.pagenavigation;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSInteger;
/*    */ import org.apache.pdfbox.cos.COSName;
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
/*    */ public enum PDTransitionDirection
/*    */ {
/* 34 */   LEFT_TO_RIGHT(0),
/*    */ 
/*    */ 
/*    */   
/* 38 */   BOTTOM_TO_TOP(90),
/*    */ 
/*    */ 
/*    */   
/* 42 */   RIGHT_TO_LEFT(180), TOP_TO_BOTTOM(270),
/*    */ 
/*    */ 
/*    */   
/* 46 */   TOP_LEFT_TO_BOTTOM_RIGHT(315),
/*    */ 
/*    */ 
/*    */   
/* 50 */   NONE(0)
/*    */   {
/*    */     
/*    */     public COSBase getCOSBase()
/*    */     {
/* 55 */       return (COSBase)COSName.NONE;
/*    */     }
/*    */   };
/*    */ 
/*    */   
/*    */   private final int degrees;
/*    */   
/*    */   PDTransitionDirection(int degrees) {
/* 63 */     this.degrees = degrees;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public COSBase getCOSBase() {
/* 71 */     return (COSBase)COSInteger.get(this.degrees);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/pagenavigation/PDTransitionDirection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */