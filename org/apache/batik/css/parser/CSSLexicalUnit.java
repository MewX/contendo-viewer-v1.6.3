/*     */ package org.apache.batik.css.parser;
/*     */ 
/*     */ import org.w3c.css.sac.LexicalUnit;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CSSLexicalUnit
/*     */   implements LexicalUnit
/*     */ {
/*     */   public static final String UNIT_TEXT_CENTIMETER = "cm";
/*     */   public static final String UNIT_TEXT_DEGREE = "deg";
/*     */   public static final String UNIT_TEXT_EM = "em";
/*     */   public static final String UNIT_TEXT_EX = "ex";
/*     */   public static final String UNIT_TEXT_GRADIAN = "grad";
/*     */   public static final String UNIT_TEXT_HERTZ = "Hz";
/*     */   public static final String UNIT_TEXT_INCH = "in";
/*     */   public static final String UNIT_TEXT_KILOHERTZ = "kHz";
/*     */   public static final String UNIT_TEXT_MILLIMETER = "mm";
/*     */   public static final String UNIT_TEXT_MILLISECOND = "ms";
/*     */   public static final String UNIT_TEXT_PERCENTAGE = "%";
/*     */   public static final String UNIT_TEXT_PICA = "pc";
/*     */   public static final String UNIT_TEXT_PIXEL = "px";
/*     */   public static final String UNIT_TEXT_POINT = "pt";
/*     */   public static final String UNIT_TEXT_RADIAN = "rad";
/*     */   public static final String UNIT_TEXT_REAL = "";
/*     */   public static final String UNIT_TEXT_SECOND = "s";
/*     */   public static final String TEXT_RGBCOLOR = "rgb";
/*     */   public static final String TEXT_RECT_FUNCTION = "rect";
/*     */   public static final String TEXT_COUNTER_FUNCTION = "counter";
/*     */   public static final String TEXT_COUNTERS_FUNCTION = "counters";
/*     */   protected short lexicalUnitType;
/*     */   protected LexicalUnit nextLexicalUnit;
/*     */   protected LexicalUnit previousLexicalUnit;
/*     */   
/*     */   protected CSSLexicalUnit(short t, LexicalUnit prev) {
/*  73 */     this.lexicalUnitType = t;
/*  74 */     this.previousLexicalUnit = prev;
/*  75 */     if (prev != null) {
/*  76 */       ((CSSLexicalUnit)prev).nextLexicalUnit = this;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getLexicalUnitType() {
/*  84 */     return this.lexicalUnitType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexicalUnit getNextLexicalUnit() {
/*  91 */     return this.nextLexicalUnit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNextLexicalUnit(LexicalUnit lu) {
/*  98 */     this.nextLexicalUnit = lu;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexicalUnit getPreviousLexicalUnit() {
/* 105 */     return this.previousLexicalUnit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPreviousLexicalUnit(LexicalUnit lu) {
/* 112 */     this.previousLexicalUnit = lu;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntegerValue() {
/* 119 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloatValue() {
/* 126 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDimensionUnitText() {
/* 133 */     switch (this.lexicalUnitType) { case 19:
/* 134 */         return "cm";
/* 135 */       case 28: return "deg";
/* 136 */       case 15: return "em";
/* 137 */       case 16: return "ex";
/* 138 */       case 29: return "grad";
/* 139 */       case 33: return "Hz";
/* 140 */       case 18: return "in";
/* 141 */       case 34: return "kHz";
/* 142 */       case 20: return "mm";
/* 143 */       case 31: return "ms";
/* 144 */       case 23: return "%";
/* 145 */       case 22: return "pc";
/* 146 */       case 17: return "px";
/* 147 */       case 21: return "pt";
/* 148 */       case 30: return "rad";
/* 149 */       case 14: return "";
/* 150 */       case 32: return "s"; }
/*     */     
/* 152 */     throw new IllegalStateException("No Unit Text for type: " + this.lexicalUnitType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFunctionName() {
/* 161 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexicalUnit getParameters() {
/* 168 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 175 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexicalUnit getSubValues() {
/* 182 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createSimple(short t, LexicalUnit prev) {
/* 189 */     return new SimpleLexicalUnit(t, prev);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class SimpleLexicalUnit
/*     */     extends CSSLexicalUnit
/*     */   {
/*     */     public SimpleLexicalUnit(short t, LexicalUnit prev) {
/* 201 */       super(t, prev);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createInteger(int val, LexicalUnit prev) {
/* 209 */     return new IntegerLexicalUnit(val, prev);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class IntegerLexicalUnit
/*     */     extends CSSLexicalUnit
/*     */   {
/*     */     protected int value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public IntegerLexicalUnit(int val, LexicalUnit prev) {
/* 226 */       super((short)13, prev);
/* 227 */       this.value = val;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getIntegerValue() {
/* 234 */       return this.value;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createFloat(short t, float val, LexicalUnit prev) {
/* 242 */     return new FloatLexicalUnit(t, val, prev);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FloatLexicalUnit
/*     */     extends CSSLexicalUnit
/*     */   {
/*     */     protected float value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FloatLexicalUnit(short t, float val, LexicalUnit prev) {
/* 259 */       super(t, prev);
/* 260 */       this.value = val;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getFloatValue() {
/* 267 */       return this.value;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createDimension(float val, String dim, LexicalUnit prev) {
/* 276 */     return new DimensionLexicalUnit(val, dim, prev);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class DimensionLexicalUnit
/*     */     extends CSSLexicalUnit
/*     */   {
/*     */     protected float value;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String dimension;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DimensionLexicalUnit(float val, String dim, LexicalUnit prev) {
/* 298 */       super((short)42, prev);
/* 299 */       this.value = val;
/* 300 */       this.dimension = dim;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getFloatValue() {
/* 307 */       return this.value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getDimensionUnitText() {
/* 314 */       return this.dimension;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createFunction(String f, LexicalUnit params, LexicalUnit prev) {
/* 323 */     return new FunctionLexicalUnit(f, params, prev);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FunctionLexicalUnit
/*     */     extends CSSLexicalUnit
/*     */   {
/*     */     protected String name;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected LexicalUnit parameters;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FunctionLexicalUnit(String f, LexicalUnit params, LexicalUnit prev) {
/* 345 */       super((short)41, prev);
/* 346 */       this.name = f;
/* 347 */       this.parameters = params;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getFunctionName() {
/* 354 */       return this.name;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public LexicalUnit getParameters() {
/* 361 */       return this.parameters;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createPredefinedFunction(short t, LexicalUnit params, LexicalUnit prev) {
/* 371 */     return new PredefinedFunctionLexicalUnit(t, params, prev);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class PredefinedFunctionLexicalUnit
/*     */     extends CSSLexicalUnit
/*     */   {
/*     */     protected LexicalUnit parameters;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public PredefinedFunctionLexicalUnit(short t, LexicalUnit params, LexicalUnit prev) {
/* 389 */       super(t, prev);
/* 390 */       this.parameters = params;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String getFunctionName() {
/* 396 */       switch (this.lexicalUnitType) { case 27:
/* 397 */           return "rgb";
/* 398 */         case 38: return "rect";
/* 399 */         case 25: return "counter";
/* 400 */         case 26: return "counters"; }
/*     */ 
/*     */       
/* 403 */       return super.getFunctionName();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public LexicalUnit getParameters() {
/* 411 */       return this.parameters;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createString(short t, String val, LexicalUnit prev) {
/* 419 */     return new StringLexicalUnit(t, val, prev);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class StringLexicalUnit
/*     */     extends CSSLexicalUnit
/*     */   {
/*     */     protected String value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public StringLexicalUnit(short t, String val, LexicalUnit prev) {
/* 436 */       super(t, prev);
/* 437 */       this.value = val;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getStringValue() {
/* 444 */       return this.value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/CSSLexicalUnit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */