/*     */ package org.apache.batik.css.engine.value;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSContext;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.w3c.css.sac.LexicalUnit;
/*     */ import org.w3c.dom.DOMException;
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
/*     */ public abstract class LengthManager
/*     */   extends AbstractValueManager
/*     */ {
/*  42 */   static final double SQRT2 = Math.sqrt(2.0D);
/*     */   
/*     */   protected static final int HORIZONTAL_ORIENTATION = 0;
/*     */   protected static final int VERTICAL_ORIENTATION = 1;
/*     */   protected static final int BOTH_ORIENTATION = 2;
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*  49 */     switch (lu.getLexicalUnitType()) {
/*     */       case 15:
/*  51 */         return new FloatValue((short)3, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 16:
/*  55 */         return new FloatValue((short)4, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 17:
/*  59 */         return new FloatValue((short)5, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 19:
/*  63 */         return new FloatValue((short)6, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 20:
/*  67 */         return new FloatValue((short)7, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 18:
/*  71 */         return new FloatValue((short)8, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 21:
/*  75 */         return new FloatValue((short)9, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 22:
/*  79 */         return new FloatValue((short)10, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 13:
/*  83 */         return new FloatValue((short)1, lu.getIntegerValue());
/*     */ 
/*     */       
/*     */       case 14:
/*  87 */         return new FloatValue((short)1, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 23:
/*  91 */         return new FloatValue((short)2, lu.getFloatValue());
/*     */     } 
/*     */     
/*  94 */     throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createFloatValue(short type, float floatValue) throws DOMException {
/* 102 */     switch (type) {
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/* 113 */         return new FloatValue(type, floatValue);
/*     */     } 
/* 115 */     throw createInvalidFloatTypeDOMException(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine, int idx, StyleMap sm, Value value) {
/*     */     CSSContext ctx;
/*     */     float v;
/*     */     int fsidx;
/*     */     float fs;
/*     */     double w;
/*     */     double h;
/* 128 */     if (value.getCssValueType() != 1) {
/* 129 */       return value;
/*     */     }
/*     */     
/* 132 */     switch (value.getPrimitiveType()) {
/*     */       case 1:
/*     */       case 5:
/* 135 */         return value;
/*     */       
/*     */       case 7:
/* 138 */         ctx = engine.getCSSContext();
/* 139 */         v = value.getFloatValue();
/* 140 */         return new FloatValue((short)1, v / ctx.getPixelUnitToMillimeter());
/*     */ 
/*     */       
/*     */       case 6:
/* 144 */         ctx = engine.getCSSContext();
/* 145 */         v = value.getFloatValue();
/* 146 */         return new FloatValue((short)1, v * 10.0F / ctx.getPixelUnitToMillimeter());
/*     */ 
/*     */       
/*     */       case 8:
/* 150 */         ctx = engine.getCSSContext();
/* 151 */         v = value.getFloatValue();
/* 152 */         return new FloatValue((short)1, v * 25.4F / ctx.getPixelUnitToMillimeter());
/*     */ 
/*     */       
/*     */       case 9:
/* 156 */         ctx = engine.getCSSContext();
/* 157 */         v = value.getFloatValue();
/* 158 */         return new FloatValue((short)1, v * 25.4F / 72.0F * ctx.getPixelUnitToMillimeter());
/*     */ 
/*     */ 
/*     */       
/*     */       case 10:
/* 163 */         ctx = engine.getCSSContext();
/* 164 */         v = value.getFloatValue();
/* 165 */         return new FloatValue((short)1, v * 25.4F / 6.0F * ctx.getPixelUnitToMillimeter());
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 170 */         sm.putFontSizeRelative(idx, true);
/*     */         
/* 172 */         v = value.getFloatValue();
/* 173 */         fsidx = engine.getFontSizeIndex();
/*     */         
/* 175 */         fs = engine.getComputedStyle(elt, pseudo, fsidx).getFloatValue();
/* 176 */         return new FloatValue((short)1, v * fs);
/*     */ 
/*     */       
/*     */       case 4:
/* 180 */         sm.putFontSizeRelative(idx, true);
/*     */         
/* 182 */         v = value.getFloatValue();
/* 183 */         fsidx = engine.getFontSizeIndex();
/* 184 */         fs = engine.getComputedStyle(elt, pseudo, fsidx).getFloatValue();
/* 185 */         return new FloatValue((short)1, v * fs * 0.5F);
/*     */       
/*     */       case 2:
/* 188 */         ctx = engine.getCSSContext();
/* 189 */         switch (getOrientation())
/*     */         { case 0:
/* 191 */             sm.putBlockWidthRelative(idx, true);
/* 192 */             fs = value.getFloatValue() * ctx.getBlockWidth((Element)elt) / 100.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 206 */             return new FloatValue((short)1, fs);case 1: sm.putBlockHeightRelative(idx, true); fs = value.getFloatValue() * ctx.getBlockHeight((Element)elt) / 100.0F; return new FloatValue((short)1, fs); }  sm.putBlockWidthRelative(idx, true); sm.putBlockHeightRelative(idx, true); w = ctx.getBlockWidth((Element)elt); h = ctx.getBlockHeight((Element)elt); fs = (float)(value.getFloatValue() * Math.sqrt(w * w + h * h) / SQRT2 / 100.0D); return new FloatValue((short)1, fs);
/*     */     } 
/* 208 */     return value;
/*     */   }
/*     */   
/*     */   protected abstract int getOrientation();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/LengthManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */