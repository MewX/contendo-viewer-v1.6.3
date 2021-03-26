/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import org.apache.batik.parser.AngleHandler;
/*     */ import org.apache.batik.parser.AngleParser;
/*     */ import org.apache.batik.parser.DefaultAngleHandler;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAngle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMAngle
/*     */   implements SVGAngle
/*     */ {
/*     */   private short unitType;
/*     */   protected float value;
/*  49 */   protected static final String[] UNITS = new String[] { "", "", "deg", "rad", "grad" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getUnitType() {
/*  57 */     revalidate();
/*  58 */     return this.unitType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getValue() {
/*  65 */     revalidate();
/*  66 */     return toUnit(getUnitType(), this.value, (short)2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(float value) throws DOMException {
/*  73 */     revalidate();
/*  74 */     setUnitType((short)2);
/*  75 */     this.value = value;
/*  76 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getValueInSpecifiedUnits() {
/*  83 */     revalidate();
/*  84 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValueInSpecifiedUnits(float value) throws DOMException {
/*  92 */     revalidate();
/*  93 */     this.value = value;
/*  94 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValueAsString() {
/* 101 */     revalidate();
/* 102 */     return Float.toString(this.value) + UNITS[getUnitType()];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValueAsString(String value) throws DOMException {
/* 109 */     parse(value);
/* 110 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void newValueSpecifiedUnits(short unit, float value) {
/* 118 */     setUnitType(unit);
/* 119 */     this.value = value;
/* 120 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void convertToSpecifiedUnits(short unit) {
/* 128 */     this.value = toUnit(getUnitType(), this.value, unit);
/* 129 */     setUnitType(unit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reset() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void revalidate() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parse(String s) {
/*     */     try {
/* 153 */       AngleParser angleParser = new AngleParser();
/* 154 */       angleParser.setAngleHandler((AngleHandler)new DefaultAngleHandler() {
/*     */             public void angleValue(float v) throws ParseException {
/* 156 */               SVGOMAngle.this.value = v;
/*     */             }
/*     */             public void deg() throws ParseException {
/* 159 */               SVGOMAngle.this.setUnitType((short)2);
/*     */             }
/*     */             public void rad() throws ParseException {
/* 162 */               SVGOMAngle.this.setUnitType((short)3);
/*     */             }
/*     */             public void grad() throws ParseException {
/* 165 */               SVGOMAngle.this.setUnitType((short)4);
/*     */             }
/*     */           });
/* 168 */       setUnitType((short)1);
/* 169 */       angleParser.parse(s);
/* 170 */     } catch (ParseException e) {
/* 171 */       setUnitType((short)0);
/* 172 */       this.value = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   protected static double[][] K = new double[][] { { 1.0D, 0.017453292519943295D, 0.015707963267948967D }, { 57.29577951308232D, 1.0D, 63.66197723675813D }, { 0.9D, 0.015707963267948967D, 1.0D } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float toUnit(short fromUnit, float value, short toUnit) {
/* 189 */     if (fromUnit == 1) {
/* 190 */       fromUnit = 2;
/*     */     }
/* 192 */     if (toUnit == 1) {
/* 193 */       toUnit = 2;
/*     */     }
/* 195 */     return (float)(K[fromUnit - 2][toUnit - 2] * value);
/*     */   }
/*     */   
/*     */   public void setUnitType(short unitType) {
/* 199 */     this.unitType = unitType;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGOMAngle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */