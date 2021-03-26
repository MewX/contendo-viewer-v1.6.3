/*    */ package org.apache.batik.ext.awt;
/*    */ 
/*    */ import java.awt.RenderingHints;
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
/*    */ public final class ColorSpaceHintKey
/*    */   extends RenderingHints.Key
/*    */ {
/* 34 */   public static Object VALUE_COLORSPACE_ARGB = new Object();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public static Object VALUE_COLORSPACE_RGB = new Object();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public static Object VALUE_COLORSPACE_GREY = new Object();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public static Object VALUE_COLORSPACE_AGREY = new Object();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 57 */   public static Object VALUE_COLORSPACE_ALPHA = new Object();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public static Object VALUE_COLORSPACE_ALPHA_CONVERT = new Object();
/*    */ 
/*    */   
/*    */   public static final String PROPERTY_COLORSPACE = "org.apache.batik.gvt.filter.Colorspace";
/*    */ 
/*    */ 
/*    */   
/*    */   ColorSpaceHintKey(int number) {
/* 72 */     super(number);
/*    */   }
/*    */   public boolean isCompatibleValue(Object val) {
/* 75 */     if (val == VALUE_COLORSPACE_ARGB) return true; 
/* 76 */     if (val == VALUE_COLORSPACE_RGB) return true; 
/* 77 */     if (val == VALUE_COLORSPACE_GREY) return true; 
/* 78 */     if (val == VALUE_COLORSPACE_AGREY) return true; 
/* 79 */     if (val == VALUE_COLORSPACE_ALPHA) return true; 
/* 80 */     if (val == VALUE_COLORSPACE_ALPHA_CONVERT) return true; 
/* 81 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/ColorSpaceHintKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */