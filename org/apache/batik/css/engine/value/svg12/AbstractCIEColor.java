/*    */ package org.apache.batik.css.engine.value.svg12;
/*    */ 
/*    */ import org.apache.batik.css.engine.value.AbstractValue;
/*    */ import org.apache.xmlgraphics.java2d.color.ColorSpaces;
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
/*    */ public abstract class AbstractCIEColor
/*    */   extends AbstractValue
/*    */ {
/* 35 */   protected float[] values = new float[3];
/*    */ 
/*    */   
/* 38 */   protected float[] whitepoint = ColorSpaces.getCIELabColorSpaceD50().getWhitePoint();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected AbstractCIEColor(float[] components, float[] whitepoint) {
/* 46 */     System.arraycopy(components, 0, this.values, 0, this.values.length);
/* 47 */     if (whitepoint != null) {
/* 48 */       System.arraycopy(whitepoint, 0, this.whitepoint, 0, this.whitepoint.length);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getColorValues() {
/* 57 */     float[] copy = new float[3];
/* 58 */     System.arraycopy(this.values, 0, copy, 0, copy.length);
/* 59 */     return copy;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getWhitePoint() {
/* 67 */     float[] copy = new float[3];
/* 68 */     System.arraycopy(this.whitepoint, 0, copy, 0, copy.length);
/* 69 */     return copy;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract String getFunctionName();
/*    */ 
/*    */ 
/*    */   
/*    */   public short getCssValueType() {
/* 79 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCssText() {
/* 86 */     StringBuffer sb = new StringBuffer(getFunctionName());
/* 87 */     sb.append('(');
/* 88 */     sb.append(this.values[0]);
/* 89 */     sb.append(", ");
/* 90 */     sb.append(this.values[1]);
/* 91 */     sb.append(", ");
/* 92 */     sb.append(this.values[2]);
/* 93 */     sb.append(')');
/* 94 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 99 */     return getCssText();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg12/AbstractCIEColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */