/*    */ package org.apache.xmlgraphics.java2d;
/*    */ 
/*    */ import java.awt.color.ColorSpace;
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
/*    */ public class CMYKColorSpace
/*    */   extends ColorSpace
/*    */ {
/*    */   private static final long serialVersionUID = 2925508946083542974L;
/*    */   private static CMYKColorSpace instance;
/*    */   
/*    */   protected CMYKColorSpace(int type, int numcomponents) {
/* 38 */     super(type, numcomponents);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static CMYKColorSpace getInstance() {
/* 46 */     if (instance == null) {
/* 47 */       instance = new CMYKColorSpace(9, 4);
/*    */     }
/* 49 */     return instance;
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] toRGB(float[] colorvalue) {
/* 54 */     return new float[] { (1.0F - colorvalue[0]) * (1.0F - colorvalue[3]), (1.0F - colorvalue[1]) * (1.0F - colorvalue[3]), (1.0F - colorvalue[2]) * (1.0F - colorvalue[3]) };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] fromRGB(float[] rgbvalue) {
/* 62 */     throw new UnsupportedOperationException("NYI");
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] toCIEXYZ(float[] colorvalue) {
/* 67 */     throw new UnsupportedOperationException("NYI");
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] fromCIEXYZ(float[] colorvalue) {
/* 72 */     throw new UnsupportedOperationException("NYI");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/CMYKColorSpace.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */