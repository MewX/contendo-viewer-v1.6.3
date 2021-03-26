/*    */ package org.apache.batik.css.engine.value;
/*    */ 
/*    */ import org.w3c.dom.DOMException;
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
/*    */ public class FloatValue
/*    */   extends AbstractValue
/*    */ {
/*    */   public static String getCssText(short unit, float value) {
/* 36 */     if (unit < 0 || unit >= UNITS.length) {
/* 37 */       throw new DOMException((short)12, "");
/*    */     }
/* 39 */     String s = String.valueOf(value);
/* 40 */     if (s.endsWith(".0")) {
/* 41 */       s = s.substring(0, s.length() - 2);
/*    */     }
/* 43 */     return s + UNITS[unit - 1];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   protected static final String[] UNITS = new String[] { "", "%", "em", "ex", "px", "cm", "mm", "in", "pt", "pc", "deg", "rad", "grad", "ms", "s", "Hz", "kHz", "" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected float floatValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected short unitType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FloatValue(short unitType, float floatValue) {
/* 68 */     this.unitType = unitType;
/* 69 */     this.floatValue = floatValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getPrimitiveType() {
/* 76 */     return this.unitType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getFloatValue() {
/* 83 */     return this.floatValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCssText() {
/* 90 */     return getCssText(this.unitType, this.floatValue);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 97 */     return getCssText();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/FloatValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */