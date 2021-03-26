/*     */ package org.apache.batik.css.engine.value.css2;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSContext;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.FloatValue;
/*     */ import org.apache.batik.css.engine.value.LengthManager;
/*     */ import org.apache.batik.css.engine.value.StringMap;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.css.engine.value.ValueConstants;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FontSizeManager
/*     */   extends LengthManager
/*     */ {
/*  50 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  52 */     values.put("all", ValueConstants.ALL_VALUE);
/*     */     
/*  54 */     values.put("large", ValueConstants.LARGE_VALUE);
/*     */     
/*  56 */     values.put("larger", ValueConstants.LARGER_VALUE);
/*     */     
/*  58 */     values.put("medium", ValueConstants.MEDIUM_VALUE);
/*     */     
/*  60 */     values.put("small", ValueConstants.SMALL_VALUE);
/*     */     
/*  62 */     values.put("smaller", ValueConstants.SMALLER_VALUE);
/*     */     
/*  64 */     values.put("x-large", ValueConstants.X_LARGE_VALUE);
/*     */     
/*  66 */     values.put("x-small", ValueConstants.X_SMALL_VALUE);
/*     */     
/*  68 */     values.put("xx-large", ValueConstants.XX_LARGE_VALUE);
/*     */     
/*  70 */     values.put("xx-small", ValueConstants.XX_SMALL_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/*  78 */     return values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  85 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/* 106 */     return "font-size";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/* 113 */     return 39;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 120 */     return ValueConstants.MEDIUM_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*     */     String s;
/*     */     Object v;
/* 128 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/* 130 */         return ValueConstants.INHERIT_VALUE;
/*     */       
/*     */       case 35:
/* 133 */         s = lu.getStringValue().toLowerCase().intern();
/* 134 */         v = values.get(s);
/* 135 */         if (v == null) {
/* 136 */           throw createInvalidIdentifierDOMException(s);
/*     */         }
/* 138 */         return (Value)v;
/*     */     } 
/*     */ 
/*     */     
/* 142 */     return super.createValue(lu, engine);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createStringValue(short type, String value, CSSEngine engine) throws DOMException {
/* 151 */     if (type != 21) {
/* 152 */       throw createInvalidStringTypeDOMException(type);
/*     */     }
/* 154 */     Object v = values.get(value.toLowerCase().intern());
/* 155 */     if (v == null) {
/* 156 */       throw createInvalidIdentifierDOMException(value);
/*     */     }
/* 158 */     return (Value)v;
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
/*     */   public Value computeValue(CSSStylableElement elt, String pseudo, CSSEngine engine, int idx, StyleMap sm, Value value) {
/* 171 */     float v, scale = 1.0F;
/* 172 */     boolean doParentRelative = false;
/*     */     
/* 174 */     switch (value.getPrimitiveType()) {
/*     */       case 1:
/*     */       case 5:
/* 177 */         return value;
/*     */       
/*     */       case 7:
/* 180 */         ctx = engine.getCSSContext();
/* 181 */         v = value.getFloatValue();
/* 182 */         return (Value)new FloatValue((short)1, v / ctx.getPixelUnitToMillimeter());
/*     */ 
/*     */       
/*     */       case 6:
/* 186 */         ctx = engine.getCSSContext();
/* 187 */         v = value.getFloatValue();
/* 188 */         return (Value)new FloatValue((short)1, v * 10.0F / ctx.getPixelUnitToMillimeter());
/*     */ 
/*     */       
/*     */       case 8:
/* 192 */         ctx = engine.getCSSContext();
/* 193 */         v = value.getFloatValue();
/* 194 */         return (Value)new FloatValue((short)1, v * 25.4F / ctx.getPixelUnitToMillimeter());
/*     */ 
/*     */       
/*     */       case 9:
/* 198 */         ctx = engine.getCSSContext();
/* 199 */         v = value.getFloatValue();
/* 200 */         return (Value)new FloatValue((short)1, v * 25.4F / 72.0F * ctx.getPixelUnitToMillimeter());
/*     */ 
/*     */ 
/*     */       
/*     */       case 10:
/* 205 */         ctx = engine.getCSSContext();
/* 206 */         v = value.getFloatValue();
/* 207 */         return (Value)new FloatValue((short)1, v * 25.4F / 6.0F * ctx.getPixelUnitToMillimeter());
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 212 */         doParentRelative = true;
/* 213 */         scale = value.getFloatValue();
/*     */         break;
/*     */       case 4:
/* 216 */         doParentRelative = true;
/* 217 */         scale = value.getFloatValue() * 0.5F;
/*     */         break;
/*     */       case 2:
/* 220 */         doParentRelative = true;
/* 221 */         scale = value.getFloatValue() * 0.01F;
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 226 */     if (value == ValueConstants.LARGER_VALUE) {
/* 227 */       doParentRelative = true;
/* 228 */       scale = 1.2F;
/* 229 */     } else if (value == ValueConstants.SMALLER_VALUE) {
/* 230 */       doParentRelative = true;
/* 231 */       scale = 0.8333333F;
/*     */     } 
/*     */     
/* 234 */     if (doParentRelative) {
/* 235 */       float f; sm.putParentRelative(idx, true);
/*     */ 
/*     */       
/* 238 */       CSSStylableElement p = CSSEngine.getParentCSSStylableElement((Element)elt);
/*     */       
/* 240 */       if (p == null) {
/* 241 */         CSSContext cSSContext = engine.getCSSContext();
/* 242 */         f = cSSContext.getMediumFontSize();
/*     */       } else {
/* 244 */         f = engine.getComputedStyle(p, null, idx).getFloatValue();
/*     */       } 
/* 246 */       return (Value)new FloatValue((short)1, f * scale);
/*     */     } 
/*     */ 
/*     */     
/* 250 */     CSSContext ctx = engine.getCSSContext();
/* 251 */     float fs = ctx.getMediumFontSize();
/* 252 */     String s = value.getStringValue();
/* 253 */     switch (s.charAt(0))
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 'm':
/* 289 */         return (Value)new FloatValue((short)1, fs);
/*     */       case 's':
/*     */         fs = (float)(fs / 1.2D);
/*     */       case 'l':
/*     */         fs = (float)(fs * 1.2D); }  switch (s.charAt(1)) { case 'x':
/*     */         switch (s.charAt(3)) { case 's':
/*     */             fs = (float)(fs / 1.2D / 1.2D / 1.2D); }  fs = (float)(fs * 1.2D * 1.2D * 1.2D); }
/*     */      switch (s.charAt(2)) { case 's':
/*     */         fs = (float)(fs / 1.2D / 1.2D); }
/* 298 */      fs = (float)(fs * 1.2D * 1.2D); } protected int getOrientation() { return 1; }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/FontSizeManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */