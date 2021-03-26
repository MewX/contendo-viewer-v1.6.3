/*     */ package org.apache.batik.css.engine.value;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
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
/*     */ public abstract class AbstractColorManager
/*     */   extends IdentifierManager
/*     */ {
/*  41 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  43 */     values.put("aqua", ValueConstants.AQUA_VALUE);
/*     */     
/*  45 */     values.put("black", ValueConstants.BLACK_VALUE);
/*     */     
/*  47 */     values.put("blue", ValueConstants.BLUE_VALUE);
/*     */     
/*  49 */     values.put("fuchsia", ValueConstants.FUCHSIA_VALUE);
/*     */     
/*  51 */     values.put("gray", ValueConstants.GRAY_VALUE);
/*     */     
/*  53 */     values.put("green", ValueConstants.GREEN_VALUE);
/*     */     
/*  55 */     values.put("lime", ValueConstants.LIME_VALUE);
/*     */     
/*  57 */     values.put("maroon", ValueConstants.MAROON_VALUE);
/*     */     
/*  59 */     values.put("navy", ValueConstants.NAVY_VALUE);
/*     */     
/*  61 */     values.put("olive", ValueConstants.OLIVE_VALUE);
/*     */     
/*  63 */     values.put("purple", ValueConstants.PURPLE_VALUE);
/*     */     
/*  65 */     values.put("red", ValueConstants.RED_VALUE);
/*     */     
/*  67 */     values.put("silver", ValueConstants.SILVER_VALUE);
/*     */     
/*  69 */     values.put("teal", ValueConstants.TEAL_VALUE);
/*     */     
/*  71 */     values.put("white", ValueConstants.WHITE_VALUE);
/*     */     
/*  73 */     values.put("yellow", ValueConstants.YELLOW_VALUE);
/*     */ 
/*     */     
/*  76 */     values.put("activeborder", ValueConstants.ACTIVEBORDER_VALUE);
/*     */     
/*  78 */     values.put("activecaption", ValueConstants.ACTIVECAPTION_VALUE);
/*     */     
/*  80 */     values.put("appworkspace", ValueConstants.APPWORKSPACE_VALUE);
/*     */     
/*  82 */     values.put("background", ValueConstants.BACKGROUND_VALUE);
/*     */     
/*  84 */     values.put("buttonface", ValueConstants.BUTTONFACE_VALUE);
/*     */     
/*  86 */     values.put("buttonhighlight", ValueConstants.BUTTONHIGHLIGHT_VALUE);
/*     */     
/*  88 */     values.put("buttonshadow", ValueConstants.BUTTONSHADOW_VALUE);
/*     */     
/*  90 */     values.put("buttontext", ValueConstants.BUTTONTEXT_VALUE);
/*     */     
/*  92 */     values.put("captiontext", ValueConstants.CAPTIONTEXT_VALUE);
/*     */     
/*  94 */     values.put("graytext", ValueConstants.GRAYTEXT_VALUE);
/*     */     
/*  96 */     values.put("highlight", ValueConstants.HIGHLIGHT_VALUE);
/*     */     
/*  98 */     values.put("highlighttext", ValueConstants.HIGHLIGHTTEXT_VALUE);
/*     */     
/* 100 */     values.put("inactiveborder", ValueConstants.INACTIVEBORDER_VALUE);
/*     */     
/* 102 */     values.put("inactivecaption", ValueConstants.INACTIVECAPTION_VALUE);
/*     */     
/* 104 */     values.put("inactivecaptiontext", ValueConstants.INACTIVECAPTIONTEXT_VALUE);
/*     */     
/* 106 */     values.put("infobackground", ValueConstants.INFOBACKGROUND_VALUE);
/*     */     
/* 108 */     values.put("infotext", ValueConstants.INFOTEXT_VALUE);
/*     */     
/* 110 */     values.put("menu", ValueConstants.MENU_VALUE);
/*     */     
/* 112 */     values.put("menutext", ValueConstants.MENUTEXT_VALUE);
/*     */     
/* 114 */     values.put("scrollbar", ValueConstants.SCROLLBAR_VALUE);
/*     */     
/* 116 */     values.put("threeddarkshadow", ValueConstants.THREEDDARKSHADOW_VALUE);
/*     */     
/* 118 */     values.put("threedface", ValueConstants.THREEDFACE_VALUE);
/*     */     
/* 120 */     values.put("threedhighlight", ValueConstants.THREEDHIGHLIGHT_VALUE);
/*     */     
/* 122 */     values.put("threedlightshadow", ValueConstants.THREEDLIGHTSHADOW_VALUE);
/*     */     
/* 124 */     values.put("threedshadow", ValueConstants.THREEDSHADOW_VALUE);
/*     */     
/* 126 */     values.put("window", ValueConstants.WINDOW_VALUE);
/*     */     
/* 128 */     values.put("windowframe", ValueConstants.WINDOWFRAME_VALUE);
/*     */     
/* 130 */     values.put("windowtext", ValueConstants.WINDOWTEXT_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   protected static final StringMap computedValues = new StringMap();
/*     */   static {
/* 139 */     computedValues.put("black", ValueConstants.BLACK_RGB_VALUE);
/*     */     
/* 141 */     computedValues.put("silver", ValueConstants.SILVER_RGB_VALUE);
/*     */     
/* 143 */     computedValues.put("gray", ValueConstants.GRAY_RGB_VALUE);
/*     */     
/* 145 */     computedValues.put("white", ValueConstants.WHITE_RGB_VALUE);
/*     */     
/* 147 */     computedValues.put("maroon", ValueConstants.MAROON_RGB_VALUE);
/*     */     
/* 149 */     computedValues.put("red", ValueConstants.RED_RGB_VALUE);
/*     */     
/* 151 */     computedValues.put("purple", ValueConstants.PURPLE_RGB_VALUE);
/*     */     
/* 153 */     computedValues.put("fuchsia", ValueConstants.FUCHSIA_RGB_VALUE);
/*     */     
/* 155 */     computedValues.put("green", ValueConstants.GREEN_RGB_VALUE);
/*     */     
/* 157 */     computedValues.put("lime", ValueConstants.LIME_RGB_VALUE);
/*     */     
/* 159 */     computedValues.put("olive", ValueConstants.OLIVE_RGB_VALUE);
/*     */     
/* 161 */     computedValues.put("yellow", ValueConstants.YELLOW_RGB_VALUE);
/*     */     
/* 163 */     computedValues.put("navy", ValueConstants.NAVY_RGB_VALUE);
/*     */     
/* 165 */     computedValues.put("blue", ValueConstants.BLUE_RGB_VALUE);
/*     */     
/* 167 */     computedValues.put("teal", ValueConstants.TEAL_RGB_VALUE);
/*     */     
/* 169 */     computedValues.put("aqua", ValueConstants.AQUA_RGB_VALUE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/* 178 */     if (lu.getLexicalUnitType() == 27) {
/* 179 */       lu = lu.getParameters();
/* 180 */       Value red = createColorComponent(lu);
/* 181 */       lu = lu.getNextLexicalUnit().getNextLexicalUnit();
/* 182 */       Value green = createColorComponent(lu);
/* 183 */       lu = lu.getNextLexicalUnit().getNextLexicalUnit();
/* 184 */       Value blue = createColorComponent(lu);
/* 185 */       return createRGBColor(red, green, blue);
/*     */     } 
/* 187 */     return super.createValue(lu, engine);
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
/* 200 */     if (value.getPrimitiveType() == 21) {
/* 201 */       String ident = value.getStringValue();
/*     */       
/* 203 */       Value v = (Value)computedValues.get(ident);
/* 204 */       if (v != null) {
/* 205 */         return v;
/*     */       }
/*     */       
/* 208 */       if (values.get(ident) == null) {
/* 209 */         throw new IllegalStateException("Not a system-color:" + ident);
/*     */       }
/* 211 */       return engine.getCSSContext().getSystemColor(ident);
/*     */     } 
/* 213 */     return super.computeValue(elt, pseudo, engine, idx, sm, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Value createRGBColor(Value r, Value g, Value b) {
/* 220 */     return new RGBColorValue(r, g, b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Value createColorComponent(LexicalUnit lu) throws DOMException {
/* 227 */     switch (lu.getLexicalUnitType()) {
/*     */       case 13:
/* 229 */         return new FloatValue((short)1, lu.getIntegerValue());
/*     */ 
/*     */       
/*     */       case 14:
/* 233 */         return new FloatValue((short)1, lu.getFloatValue());
/*     */ 
/*     */       
/*     */       case 23:
/* 237 */         return new FloatValue((short)2, lu.getFloatValue());
/*     */     } 
/*     */     
/* 240 */     throw createInvalidRGBComponentUnitDOMException(lu.getLexicalUnitType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/* 248 */     return values;
/*     */   }
/*     */ 
/*     */   
/*     */   private DOMException createInvalidRGBComponentUnitDOMException(short type) {
/* 253 */     Object[] p = { getPropertyName(), Integer.valueOf(type) };
/*     */     
/* 255 */     String s = Messages.formatMessage("invalid.rgb.component.unit", p);
/* 256 */     return new DOMException((short)9, s);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/AbstractColorManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */