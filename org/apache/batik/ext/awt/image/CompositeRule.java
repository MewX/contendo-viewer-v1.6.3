/*     */ package org.apache.batik.ext.awt.image;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
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
/*     */ public final class CompositeRule
/*     */   implements Serializable
/*     */ {
/*     */   public static final int RULE_OVER = 1;
/*     */   public static final int RULE_IN = 2;
/*     */   public static final int RULE_OUT = 3;
/*     */   public static final int RULE_ATOP = 4;
/*     */   public static final int RULE_XOR = 5;
/*     */   public static final int RULE_ARITHMETIC = 6;
/*     */   public static final int RULE_MULTIPLY = 7;
/*     */   public static final int RULE_SCREEN = 8;
/*     */   public static final int RULE_DARKEN = 9;
/*     */   public static final int RULE_LIGHTEN = 10;
/*  71 */   public static final CompositeRule OVER = new CompositeRule(1);
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
/*  83 */   public static final CompositeRule IN = new CompositeRule(2);
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
/*  95 */   public static final CompositeRule OUT = new CompositeRule(3);
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
/* 108 */   public static final CompositeRule ATOP = new CompositeRule(4);
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
/* 119 */   public static final CompositeRule XOR = new CompositeRule(5);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CompositeRule ARITHMETIC(float k1, float k2, float k3, float k4) {
/* 128 */     return new CompositeRule(k1, k2, k3, k4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static final CompositeRule MULTIPLY = new CompositeRule(7);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public static final CompositeRule SCREEN = new CompositeRule(8);
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
/* 159 */   public static final CompositeRule DARKEN = new CompositeRule(9);
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
/* 171 */   public static final CompositeRule LIGHTEN = new CompositeRule(10);
/*     */   private int rule;
/*     */   private float k1;
/*     */   private float k2;
/*     */   private float k3;
/*     */   private float k4;
/*     */   
/*     */   public int getRule() {
/* 179 */     return this.rule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CompositeRule(int rule) {
/* 191 */     this.rule = rule;
/*     */   }
/*     */   
/*     */   private CompositeRule(float k1, float k2, float k3, float k4) {
/* 195 */     this.rule = 6;
/* 196 */     this.k1 = k1;
/* 197 */     this.k2 = k2;
/* 198 */     this.k3 = k3;
/* 199 */     this.k4 = k4;
/*     */   }
/*     */   
/*     */   public float[] getCoefficients() {
/* 203 */     if (this.rule != 6) {
/* 204 */       return null;
/*     */     }
/* 206 */     return new float[] { this.k1, this.k2, this.k3, this.k4 };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 217 */     switch (this.rule) {
/*     */       case 1:
/* 219 */         return OVER;
/*     */       case 2:
/* 221 */         return IN;
/*     */       case 3:
/* 223 */         return OUT;
/*     */       case 4:
/* 225 */         return ATOP;
/*     */       case 5:
/* 227 */         return XOR;
/*     */       case 6:
/* 229 */         return this;
/*     */       case 7:
/* 231 */         return MULTIPLY;
/*     */       case 8:
/* 233 */         return SCREEN;
/*     */       case 9:
/* 235 */         return DARKEN;
/*     */       case 10:
/* 237 */         return LIGHTEN;
/*     */     } 
/* 239 */     throw new RuntimeException("Unknown Composite Rule type");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 251 */     switch (this.rule) {
/*     */       case 1:
/* 253 */         return "[CompositeRule: OVER]";
/*     */       case 2:
/* 255 */         return "[CompositeRule: IN]";
/*     */       case 3:
/* 257 */         return "[CompositeRule: OUT]";
/*     */       case 4:
/* 259 */         return "[CompositeRule: ATOP]";
/*     */       case 5:
/* 261 */         return "[CompositeRule: XOR]";
/*     */       case 6:
/* 263 */         return "[CompositeRule: ARITHMATIC k1:" + this.k1 + " k2: " + this.k2 + " k3: " + this.k3 + " k4: " + this.k4 + ']';
/*     */       
/*     */       case 7:
/* 266 */         return "[CompositeRule: MULTIPLY]";
/*     */       case 8:
/* 268 */         return "[CompositeRule: SCREEN]";
/*     */       case 9:
/* 270 */         return "[CompositeRule: DARKEN]";
/*     */       case 10:
/* 272 */         return "[CompositeRule: LIGHTEN]";
/*     */     } 
/* 274 */     throw new RuntimeException("Unknown Composite Rule type");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/CompositeRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */