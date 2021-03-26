/*    */ package org.apache.xmlgraphics.java2d.color;
/*    */ 
/*    */ import java.awt.Color;
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
/*    */ public final class GrayScaleColorConverter
/*    */   implements ColorConverter
/*    */ {
/*    */   private static final int RED_WEIGHT = 77;
/*    */   private static final int GREEN_WEIGTH = 150;
/*    */   private static final int BLUE_WEIGHT = 28;
/* 34 */   private static final GrayScaleColorConverter SINGLETON = new GrayScaleColorConverter();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static GrayScaleColorConverter getInstance() {
/* 45 */     return SINGLETON;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Color convert(Color color) {
/* 54 */     float kValue = (77 * color.getRed() + 150 * color.getGreen() + 28 * color.getBlue()) / 255.0F / 255.0F;
/*    */ 
/*    */ 
/*    */     
/* 58 */     return ColorUtil.toCMYKGrayColor(kValue);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/GrayScaleColorConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */