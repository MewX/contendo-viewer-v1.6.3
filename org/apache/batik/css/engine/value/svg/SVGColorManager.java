/*     */ package org.apache.batik.css.engine.value.svg;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.ListValue;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.css.engine.value.svg12.CIELCHColor;
/*     */ import org.apache.batik.css.engine.value.svg12.CIELabColor;
/*     */ import org.apache.batik.css.engine.value.svg12.DeviceColor;
/*     */ import org.apache.batik.css.engine.value.svg12.ICCNamedColor;
/*     */ import org.w3c.css.sac.LexicalUnit;
/*     */ import org.w3c.dom.DOMException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGColorManager
/*     */   extends ColorManager
/*     */ {
/*     */   protected String property;
/*     */   protected Value defaultValue;
/*     */   
/*     */   public SVGColorManager(String prop) {
/*  61 */     this(prop, SVGValueConstants.BLACK_RGB_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGColorManager(String prop, Value v) {
/*  68 */     this.property = prop;
/*  69 */     this.defaultValue = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInheritedProperty() {
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  97 */     return 6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/* 104 */     return this.property;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 113 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/* 121 */     if (lu.getLexicalUnitType() == 35 && 
/* 122 */       lu.getStringValue().equalsIgnoreCase("currentcolor"))
/*     */     {
/* 124 */       return SVGValueConstants.CURRENTCOLOR_VALUE;
/*     */     }
/*     */     
/* 127 */     Value v = super.createValue(lu, engine);
/* 128 */     lu = lu.getNextLexicalUnit();
/* 129 */     if (lu == null) {
/* 130 */       return v;
/*     */     }
/*     */ 
/*     */     
/* 134 */     if (lu.getLexicalUnitType() != 41) {
/* 135 */       throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */     }
/*     */ 
/*     */     
/* 139 */     ListValue result = new ListValue(' ');
/* 140 */     result.append(v);
/*     */     
/* 142 */     Value colorValue = parseColorFunction(lu, v);
/* 143 */     if (colorValue != null) {
/* 144 */       result.append(colorValue);
/*     */     } else {
/* 146 */       return v;
/*     */     } 
/* 148 */     return (Value)result;
/*     */   }
/*     */   
/*     */   private Value parseColorFunction(LexicalUnit lu, Value v) {
/* 152 */     String functionName = lu.getFunctionName();
/* 153 */     if (functionName.equalsIgnoreCase("icc-color")) {
/* 154 */       return createICCColorValue(lu, v);
/*     */     }
/* 156 */     return parseColor12Function(lu, v);
/*     */   }
/*     */   
/*     */   private Value parseColor12Function(LexicalUnit lu, Value v) {
/* 160 */     String functionName = lu.getFunctionName();
/* 161 */     if (functionName.equalsIgnoreCase("icc-named-color"))
/* 162 */       return createICCNamedColorValue(lu, v); 
/* 163 */     if (functionName.equalsIgnoreCase("cielab"))
/* 164 */       return createCIELabColorValue(lu, v); 
/* 165 */     if (functionName.equalsIgnoreCase("cielch"))
/* 166 */       return createCIELCHColorValue(lu, v); 
/* 167 */     if (functionName.equalsIgnoreCase("device-cmyk"))
/* 168 */       return createDeviceColorValue(lu, v, 4); 
/* 169 */     if (functionName.equalsIgnoreCase("device-rgb"))
/* 170 */       return createDeviceColorValue(lu, v, 3); 
/* 171 */     if (functionName.equalsIgnoreCase("device-gray"))
/* 172 */       return createDeviceColorValue(lu, v, 1); 
/* 173 */     if (functionName.equalsIgnoreCase("device-nchannel")) {
/* 174 */       return createDeviceColorValue(lu, v, 0);
/*     */     }
/* 176 */     return null;
/*     */   }
/*     */   
/*     */   private Value createICCColorValue(LexicalUnit lu, Value v) {
/* 180 */     lu = lu.getParameters();
/* 181 */     expectIdent(lu);
/*     */     
/* 183 */     ICCColor icc = new ICCColor(lu.getStringValue());
/*     */     
/* 185 */     lu = lu.getNextLexicalUnit();
/* 186 */     while (lu != null) {
/* 187 */       expectComma(lu);
/* 188 */       lu = lu.getNextLexicalUnit();
/* 189 */       icc.append(getColorValue(lu));
/* 190 */       lu = lu.getNextLexicalUnit();
/*     */     } 
/* 192 */     return (Value)icc;
/*     */   }
/*     */   
/*     */   private Value createICCNamedColorValue(LexicalUnit lu, Value v) {
/* 196 */     lu = lu.getParameters();
/* 197 */     expectIdent(lu);
/* 198 */     String profileName = lu.getStringValue();
/*     */     
/* 200 */     lu = lu.getNextLexicalUnit();
/* 201 */     expectComma(lu);
/* 202 */     lu = lu.getNextLexicalUnit();
/* 203 */     expectIdent(lu);
/* 204 */     String colorName = lu.getStringValue();
/*     */     
/* 206 */     ICCNamedColor icc = new ICCNamedColor(profileName, colorName);
/*     */     
/* 208 */     lu = lu.getNextLexicalUnit();
/* 209 */     return (Value)icc;
/*     */   }
/*     */   
/*     */   private Value createCIELabColorValue(LexicalUnit lu, Value v) {
/* 213 */     lu = lu.getParameters();
/* 214 */     float l = getColorValue(lu);
/* 215 */     lu = lu.getNextLexicalUnit();
/* 216 */     expectComma(lu);
/* 217 */     lu = lu.getNextLexicalUnit();
/* 218 */     float a = getColorValue(lu);
/* 219 */     lu = lu.getNextLexicalUnit();
/* 220 */     expectComma(lu);
/* 221 */     lu = lu.getNextLexicalUnit();
/* 222 */     float b = getColorValue(lu);
/*     */     
/* 224 */     CIELabColor icc = new CIELabColor(l, a, b);
/*     */     
/* 226 */     lu = lu.getNextLexicalUnit();
/* 227 */     return (Value)icc;
/*     */   }
/*     */   
/*     */   private Value createCIELCHColorValue(LexicalUnit lu, Value v) {
/* 231 */     lu = lu.getParameters();
/* 232 */     float l = getColorValue(lu);
/* 233 */     lu = lu.getNextLexicalUnit();
/* 234 */     expectComma(lu);
/* 235 */     lu = lu.getNextLexicalUnit();
/* 236 */     float c = getColorValue(lu);
/* 237 */     lu = lu.getNextLexicalUnit();
/* 238 */     expectComma(lu);
/* 239 */     lu = lu.getNextLexicalUnit();
/* 240 */     float h = getColorValue(lu);
/*     */     
/* 242 */     CIELCHColor icc = new CIELCHColor(l, c, h);
/*     */     
/* 244 */     lu = lu.getNextLexicalUnit();
/* 245 */     return (Value)icc;
/*     */   }
/*     */   
/*     */   private Value createDeviceColorValue(LexicalUnit lu, Value v, int expectedComponents) {
/* 249 */     lu = lu.getParameters();
/*     */     
/* 251 */     boolean nChannel = (expectedComponents <= 0);
/* 252 */     DeviceColor col = new DeviceColor(nChannel);
/*     */     
/* 254 */     col.append(getColorValue(lu));
/* 255 */     LexicalUnit lastUnit = lu;
/* 256 */     lu = lu.getNextLexicalUnit();
/* 257 */     while (lu != null) {
/* 258 */       expectComma(lu);
/* 259 */       lu = lu.getNextLexicalUnit();
/* 260 */       col.append(getColorValue(lu));
/* 261 */       lastUnit = lu;
/* 262 */       lu = lu.getNextLexicalUnit();
/*     */     } 
/* 264 */     if (!nChannel && expectedComponents != col.getNumberOfColors()) {
/* 265 */       throw createInvalidLexicalUnitDOMException(lastUnit.getLexicalUnitType());
/*     */     }
/* 267 */     return (Value)col;
/*     */   }
/*     */   
/*     */   private void expectIdent(LexicalUnit lu) {
/* 271 */     if (lu.getLexicalUnitType() != 35) {
/* 272 */       throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void expectComma(LexicalUnit lu) {
/* 278 */     if (lu.getLexicalUnitType() != 0) {
/* 279 */       throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void expectNonNull(LexicalUnit lu) {
/* 285 */     if (lu == null) {
/* 286 */       throw createInvalidLexicalUnitDOMException((short)-1);
/*     */     }
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
/* 300 */     if (value == SVGValueConstants.CURRENTCOLOR_VALUE) {
/* 301 */       sm.putColorRelative(idx, true);
/*     */       
/* 303 */       int ci = engine.getColorIndex();
/* 304 */       return engine.getComputedStyle(elt, pseudo, ci);
/*     */     } 
/* 306 */     if (value.getCssValueType() == 2) {
/* 307 */       ListValue lv = (ListValue)value;
/* 308 */       Value v = lv.item(0);
/* 309 */       Value t = super.computeValue(elt, pseudo, engine, idx, sm, v);
/* 310 */       if (t != v) {
/* 311 */         ListValue result = new ListValue(' ');
/* 312 */         result.append(t);
/* 313 */         result.append(lv.item(1));
/* 314 */         return (Value)result;
/*     */       } 
/* 316 */       return value;
/*     */     } 
/* 318 */     return super.computeValue(elt, pseudo, engine, idx, sm, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getColorValue(LexicalUnit lu) {
/* 325 */     expectNonNull(lu);
/* 326 */     switch (lu.getLexicalUnitType()) {
/*     */       case 13:
/* 328 */         return lu.getIntegerValue();
/*     */       case 14:
/* 330 */         return lu.getFloatValue();
/*     */     } 
/* 332 */     throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg/SVGColorManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */