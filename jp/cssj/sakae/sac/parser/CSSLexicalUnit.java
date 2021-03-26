/*     */ package jp.cssj.sakae.sac.parser;
/*     */ 
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
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
/*     */   public static final String UNIT_TEXT_REM = "rem";
/*     */   public static final String UNIT_TEXT_CH = "ch";
/*     */   protected short lexicalUnitType;
/*     */   protected LexicalUnit nextLexicalUnit;
/*     */   protected LexicalUnit previousLexicalUnit;
/*     */   
/*     */   protected CSSLexicalUnit(short t, LexicalUnit prev) {
/* 120 */     this.lexicalUnitType = t;
/* 121 */     this.previousLexicalUnit = prev;
/* 122 */     if (prev != null) {
/* 123 */       ((CSSLexicalUnit)prev).nextLexicalUnit = this;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getLexicalUnitType() {
/* 131 */     return this.lexicalUnitType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexicalUnit getNextLexicalUnit() {
/* 138 */     return this.nextLexicalUnit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNextLexicalUnit(LexicalUnit lu) {
/* 145 */     this.nextLexicalUnit = lu;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexicalUnit getPreviousLexicalUnit() {
/* 152 */     return this.previousLexicalUnit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPreviousLexicalUnit(LexicalUnit lu) {
/* 159 */     this.previousLexicalUnit = lu;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntegerValue() {
/* 166 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloatValue() {
/* 173 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDimensionUnitText() {
/* 180 */     switch (this.lexicalUnitType) {
/*     */       case 19:
/* 182 */         return "cm";
/*     */       case 28:
/* 184 */         return "deg";
/*     */       case 15:
/* 186 */         return "em";
/*     */       case 16:
/* 188 */         return "ex";
/*     */       case 29:
/* 190 */         return "grad";
/*     */       case 33:
/* 192 */         return "Hz";
/*     */       case 18:
/* 194 */         return "in";
/*     */       case 34:
/* 196 */         return "kHz";
/*     */       case 20:
/* 198 */         return "mm";
/*     */       case 31:
/* 200 */         return "ms";
/*     */       case 23:
/* 202 */         return "%";
/*     */       case 22:
/* 204 */         return "pc";
/*     */       case 17:
/* 206 */         return "px";
/*     */       case 21:
/* 208 */         return "pt";
/*     */       case 30:
/* 210 */         return "rad";
/*     */       case 14:
/* 212 */         return "";
/*     */       case 32:
/* 214 */         return "s";
/*     */       case 43:
/* 216 */         return "rem";
/*     */       case 44:
/* 218 */         return "ch";
/*     */     } 
/* 220 */     throw new IllegalStateException("No Unit Text for type: " + this.lexicalUnitType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFunctionName() {
/* 228 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexicalUnit getParameters() {
/* 235 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 242 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexicalUnit getSubValues() {
/* 249 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createSimple(short t, LexicalUnit prev) {
/* 256 */     return new SimpleLexicalUnit(t, prev);
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
/* 268 */       super(t, prev);
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       String s;
/* 273 */       switch (this.lexicalUnitType) {
/*     */         case 12:
/* 275 */           s = "inherit";
/*     */           break;
/*     */         
/*     */         case 0:
/* 279 */           s = ",";
/*     */           break;
/*     */         case 6:
/* 282 */           s = "^";
/*     */           break;
/*     */         case 10:
/* 285 */           s = ">=";
/*     */           break;
/*     */         case 8:
/* 288 */           s = ">";
/*     */           break;
/*     */         case 9:
/* 291 */           s = "<=";
/*     */           break;
/*     */         case 7:
/* 294 */           s = "<";
/*     */           break;
/*     */         case 2:
/* 297 */           s = "-";
/*     */           break;
/*     */         case 5:
/* 300 */           s = "%";
/*     */           break;
/*     */         case 3:
/* 303 */           s = "*";
/*     */           break;
/*     */         case 1:
/* 306 */           s = "+";
/*     */           break;
/*     */         case 4:
/* 309 */           s = "/";
/*     */           break;
/*     */         case 11:
/* 312 */           s = "~";
/*     */           break;
/*     */         
/*     */         default:
/* 316 */           throw new IllegalStateException();
/*     */       } 
/* 318 */       return s + ((this.nextLexicalUnit == null) ? "" : (" " + this.nextLexicalUnit));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createInteger(int val, LexicalUnit prev) {
/* 326 */     return new IntegerLexicalUnit(val, prev);
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
/* 343 */       super((short)13, prev);
/* 344 */       this.value = val;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getIntegerValue() {
/* 351 */       return this.value;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 355 */       return String.valueOf(this.value) + ((this.nextLexicalUnit == null) ? "" : (" " + this.nextLexicalUnit));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createFloat(short t, float val, LexicalUnit prev) {
/* 363 */     return new FloatLexicalUnit(t, val, prev);
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
/* 380 */       super(t, prev);
/* 381 */       this.value = val;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getFloatValue() {
/* 388 */       return this.value;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 392 */       return String.valueOf(this.value) + ((this.nextLexicalUnit == null) ? "" : (" " + this.nextLexicalUnit));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createDimension(float val, String dim, LexicalUnit prev) {
/* 400 */     return new DimensionLexicalUnit(val, dim, prev);
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
/* 422 */       super((short)42, prev);
/* 423 */       this.value = val;
/* 424 */       this.dimension = dim;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getFloatValue() {
/* 431 */       return this.value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getDimensionUnitText() {
/* 438 */       return this.dimension;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 442 */       return this.value + this.dimension + ((this.nextLexicalUnit == null) ? "" : (" " + this.nextLexicalUnit));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createFunction(String f, LexicalUnit params, LexicalUnit prev) {
/* 450 */     return new FunctionLexicalUnit(f, params, prev);
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
/* 472 */       super((short)41, prev);
/* 473 */       this.name = f;
/* 474 */       this.parameters = params;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getFunctionName() {
/* 481 */       return this.name;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public LexicalUnit getParameters() {
/* 488 */       return this.parameters;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 492 */       return this.name + '(' + this.parameters + ')' + ((this.nextLexicalUnit == null) ? "" : (" " + this.nextLexicalUnit));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createPredefinedFunction(short t, LexicalUnit params, LexicalUnit prev) {
/* 501 */     return new PredefinedFunctionLexicalUnit(t, params, prev);
/*     */   }
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
/* 518 */       super(t, prev);
/* 519 */       this.parameters = params;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public LexicalUnit getParameters() {
/* 526 */       return this.parameters;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       String name;
/* 531 */       switch (this.lexicalUnitType) {
/*     */         case 37:
/* 533 */           name = "attr";
/*     */           break;
/*     */         case 25:
/* 536 */           name = "counter";
/*     */           break;
/*     */         case 26:
/* 539 */           name = "counters";
/*     */           break;
/*     */         case 38:
/* 542 */           name = "rect";
/*     */           break;
/*     */         case 27:
/* 545 */           name = "rgb";
/*     */           break;
/*     */         case 24:
/* 548 */           name = "uri";
/*     */           break;
/*     */         case 40:
/* 551 */           name = "";
/*     */           break;
/*     */         default:
/* 554 */           throw new IllegalStateException();
/*     */       } 
/*     */       
/* 557 */       return name + '(' + this.parameters + ')' + ((this.nextLexicalUnit == null) ? "" : (" " + this.nextLexicalUnit));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSSLexicalUnit createString(short t, String val, LexicalUnit prev) {
/* 566 */     return new StringLexicalUnit(t, val, prev);
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
/* 583 */       super(t, prev);
/* 584 */       this.value = val;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getStringValue() {
/* 591 */       return this.value;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 595 */       return getStringValue() + ((this.nextLexicalUnit == null) ? "" : (" " + this.nextLexicalUnit));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/CSSLexicalUnit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */