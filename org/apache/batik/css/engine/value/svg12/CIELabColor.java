/*    */ package org.apache.batik.css.engine.value.svg12;
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
/*    */ public class CIELabColor
/*    */   extends AbstractCIEColor
/*    */ {
/*    */   public static final String CIE_LAB_COLOR_FUNCTION = "cielab";
/*    */   
/*    */   public CIELabColor(float l, float a, float b, float[] whitepoint) {
/* 38 */     super(new float[] { l, a, b }, whitepoint);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CIELabColor(float l, float a, float b) {
/* 45 */     this(l, a, b, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFunctionName() {
/* 50 */     return "cielab";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg12/CIELabColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */