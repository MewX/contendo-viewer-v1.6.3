/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import org.w3c.dom.Element;
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
/*     */ public abstract class UnitProcessor
/*     */ {
/*     */   public static final short HORIZONTAL_LENGTH = 2;
/*     */   public static final short VERTICAL_LENGTH = 1;
/*     */   public static final short OTHER_LENGTH = 0;
/*  52 */   static final double SQRT2 = Math.sqrt(2.0D);
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
/*     */   public static float svgToObjectBoundingBox(String s, String attr, short d, Context ctx) throws ParseException {
/*  74 */     LengthParser lengthParser = new LengthParser();
/*  75 */     UnitResolver ur = new UnitResolver();
/*  76 */     lengthParser.setLengthHandler(ur);
/*  77 */     lengthParser.parse(s);
/*  78 */     return svgToObjectBoundingBox(ur.value, ur.unit, d, ctx);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgToObjectBoundingBox(float value, short type, short d, Context ctx) {
/*  94 */     switch (type) {
/*     */       
/*     */       case 1:
/*  97 */         return value;
/*     */ 
/*     */       
/*     */       case 2:
/* 101 */         return value / 100.0F;
/*     */ 
/*     */       
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/* 112 */         return svgToUserSpace(value, type, d, ctx);
/*     */     } 
/* 114 */     throw new IllegalArgumentException("Length has unknown type");
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
/*     */   public static float svgToUserSpace(String s, String attr, short d, Context ctx) throws ParseException {
/* 136 */     LengthParser lengthParser = new LengthParser();
/* 137 */     UnitResolver ur = new UnitResolver();
/* 138 */     lengthParser.setLengthHandler(ur);
/* 139 */     lengthParser.parse(s);
/* 140 */     return svgToUserSpace(ur.value, ur.unit, d, ctx);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float svgToUserSpace(float v, short type, short d, Context ctx) {
/* 156 */     switch (type) {
/*     */       case 1:
/*     */       case 5:
/* 159 */         return v;
/*     */       case 7:
/* 161 */         return v / ctx.getPixelUnitToMillimeter();
/*     */       case 6:
/* 163 */         return v * 10.0F / ctx.getPixelUnitToMillimeter();
/*     */       case 8:
/* 165 */         return v * 25.4F / ctx.getPixelUnitToMillimeter();
/*     */       case 9:
/* 167 */         return v * 25.4F / 72.0F * ctx.getPixelUnitToMillimeter();
/*     */       case 10:
/* 169 */         return v * 25.4F / 6.0F * ctx.getPixelUnitToMillimeter();
/*     */       case 3:
/* 171 */         return emsToPixels(v, d, ctx);
/*     */       case 4:
/* 173 */         return exsToPixels(v, d, ctx);
/*     */       case 2:
/* 175 */         return percentagesToPixels(v, d, ctx);
/*     */     } 
/* 177 */     throw new IllegalArgumentException("Length has unknown type");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float userSpaceToSVG(float v, short type, short d, Context ctx) {
/* 194 */     switch (type) {
/*     */       case 1:
/*     */       case 5:
/* 197 */         return v;
/*     */       case 7:
/* 199 */         return v * ctx.getPixelUnitToMillimeter();
/*     */       case 6:
/* 201 */         return v * ctx.getPixelUnitToMillimeter() / 10.0F;
/*     */       case 8:
/* 203 */         return v * ctx.getPixelUnitToMillimeter() / 25.4F;
/*     */       case 9:
/* 205 */         return v * 72.0F * ctx.getPixelUnitToMillimeter() / 25.4F;
/*     */       case 10:
/* 207 */         return v * 6.0F * ctx.getPixelUnitToMillimeter() / 25.4F;
/*     */       case 3:
/* 209 */         return pixelsToEms(v, d, ctx);
/*     */       case 4:
/* 211 */         return pixelsToExs(v, d, ctx);
/*     */       case 2:
/* 213 */         return pixelsToPercentages(v, d, ctx);
/*     */     } 
/* 215 */     throw new IllegalArgumentException("Length has unknown type");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static float percentagesToPixels(float v, short d, Context ctx) {
/* 231 */     if (d == 2) {
/* 232 */       float f = ctx.getViewportWidth();
/* 233 */       return f * v / 100.0F;
/* 234 */     }  if (d == 1) {
/* 235 */       float f = ctx.getViewportHeight();
/* 236 */       return f * v / 100.0F;
/*     */     } 
/* 238 */     double w = ctx.getViewportWidth();
/* 239 */     double h = ctx.getViewportHeight();
/* 240 */     double vpp = Math.sqrt(w * w + h * h) / SQRT2;
/* 241 */     return (float)(vpp * v / 100.0D);
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
/*     */   protected static float pixelsToPercentages(float v, short d, Context ctx) {
/* 253 */     if (d == 2) {
/* 254 */       float f = ctx.getViewportWidth();
/* 255 */       return v * 100.0F / f;
/* 256 */     }  if (d == 1) {
/* 257 */       float f = ctx.getViewportHeight();
/* 258 */       return v * 100.0F / f;
/*     */     } 
/* 260 */     double w = ctx.getViewportWidth();
/* 261 */     double h = ctx.getViewportHeight();
/* 262 */     double vpp = Math.sqrt(w * w + h * h) / SQRT2;
/* 263 */     return (float)(v * 100.0D / vpp);
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
/*     */   protected static float pixelsToEms(float v, short d, Context ctx) {
/* 275 */     return v / ctx.getFontSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static float emsToPixels(float v, short d, Context ctx) {
/* 286 */     return v * ctx.getFontSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static float pixelsToExs(float v, short d, Context ctx) {
/* 297 */     float xh = ctx.getXHeight();
/* 298 */     return v / xh / ctx.getFontSize();
/*     */   }
/*     */   public static interface Context {
/*     */     Element getElement();
/*     */     float getPixelUnitToMillimeter();
/*     */     float getPixelToMM();
/*     */     float getFontSize();
/*     */     float getXHeight();
/*     */     float getViewportWidth();
/*     */     float getViewportHeight(); }
/*     */   protected static float exsToPixels(float v, short d, Context ctx) {
/* 309 */     float xh = ctx.getXHeight();
/* 310 */     return v * xh * ctx.getFontSize();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static class UnitResolver
/*     */     implements LengthHandler
/*     */   {
/* 327 */     public short unit = 1;
/*     */ 
/*     */     
/*     */     public float value;
/*     */ 
/*     */ 
/*     */     
/*     */     public void startLength() throws ParseException {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void lengthValue(float v) throws ParseException {
/* 339 */       this.value = v;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void em() throws ParseException {
/* 346 */       this.unit = 3;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void ex() throws ParseException {
/* 353 */       this.unit = 4;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void in() throws ParseException {
/* 360 */       this.unit = 8;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void cm() throws ParseException {
/* 367 */       this.unit = 6;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mm() throws ParseException {
/* 374 */       this.unit = 7;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void pc() throws ParseException {
/* 381 */       this.unit = 10;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void pt() throws ParseException {
/* 388 */       this.unit = 9;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void px() throws ParseException {
/* 395 */       this.unit = 5;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void percentage() throws ParseException {
/* 402 */       this.unit = 2;
/*     */     }
/*     */     
/*     */     public void endLength() throws ParseException {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/UnitProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */