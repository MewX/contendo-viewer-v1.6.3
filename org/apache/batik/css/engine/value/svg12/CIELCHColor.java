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
/*    */ public class CIELCHColor
/*    */   extends AbstractCIEColor
/*    */ {
/*    */   public static final String CIE_LCH_COLOR_FUNCTION = "cielch";
/*    */   
/*    */   public CIELCHColor(float l, float c, float h, float[] whitepoint) {
/* 38 */     super(new float[] { l, c, h }, whitepoint);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CIELCHColor(float l, float c, float h) {
/* 48 */     this(l, c, h, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFunctionName() {
/* 53 */     return "cielch";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg12/CIELCHColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */