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
/*    */ public class SetStrokingColor
/*    */   extends SetColor
/*    */ {
/*    */   protected PDColor getColor() {
/* 37 */     return this.context.getGraphicsState().getStrokingColor();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setColor(PDColor color) {
/* 47 */     this.context.getGraphicsState().setStrokingColor(color);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDColorSpace getColorSpace() {
/* 57 */     return this.context.getGraphicsState().getStrokingColorSpace();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 63 */     return "SC";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/color/SetStrokingColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */