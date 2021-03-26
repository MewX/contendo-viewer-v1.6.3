/*    */ package org.apache.pdfbox.contentstream.operator.color;
/*    */ 
/*    */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*    */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
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
/*    */ public class SetNonStrokingColor
/*    */   extends SetColor
/*    */ {
/*    */   protected PDColor getColor() {
/* 37 */     return this.context.getGraphicsState().getNonStrokingColor();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setColor(PDColor color) {
/* 47 */     this.context.getGraphicsState().setNonStrokingColor(color);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDColorSpace getColorSpace() {
/* 57 */     return this.context.getGraphicsState().getNonStrokingColorSpace();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 63 */     return "sc";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/color/SetNonStrokingColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */