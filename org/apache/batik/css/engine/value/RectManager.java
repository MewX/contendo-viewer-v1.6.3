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
/*     */ 
/*     */ public abstract class RectManager
/*     */   extends LengthManager
/*     */ {
/*     */   protected int orientation;
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/*     */     Value top;
/*     */     Value right;
/*     */     Value bottom;
/*     */     Value left;
/*  49 */     switch (lu.getLexicalUnitType()) {
/*     */       case 41:
/*  51 */         if (!lu.getFunctionName().equalsIgnoreCase("rect")) {
/*     */           break;
/*     */         }
/*     */       case 38:
/*  55 */         lu = lu.getParameters();
/*  56 */         top = createRectComponent(lu);
/*  57 */         lu = lu.getNextLexicalUnit();
/*  58 */         if (lu == null || lu.getLexicalUnitType() != 0)
/*     */         {
/*  60 */           throw createMalformedRectDOMException();
/*     */         }
/*  62 */         lu = lu.getNextLexicalUnit();
/*  63 */         right = createRectComponent(lu);
/*  64 */         lu = lu.getNextLexicalUnit();
/*  65 */         if (lu == null || lu.getLexicalUnitType() != 0)
/*     */         {
/*  67 */           throw createMalformedRectDOMException();
/*     */         }
/*  69 */         lu = lu.getNextLexicalUnit();
/*  70 */         bottom = createRectComponent(lu);
/*  71 */         lu = lu.getNextLexicalUnit();
/*  72 */         if (lu == null || lu.getLexicalUnitType() != 0)
/*     */         {
/*  74 */           throw createMalformedRectDOMException();
/*     */         }
/*  76 */         lu = lu.getNextLexicalUnit();
/*  77 */         left = createRectComponent(lu);
/*  78 */         return new RectValue(top, right, bottom, left);
/*     */     } 
/*  80 */     throw createMalformedRectDOMException();
/*     */   }
/*     */   
/*     */   private Value createRectComponent(LexicalUnit lu) throws DOMException {
/*  84 */     switch (lu.getLexicalUnitType()) {
/*     */       case 35:
/*  86 */         if (lu.getStringValue().equalsIgnoreCase("auto"))
/*     */         {
/*  88 */           return ValueConstants.AUTO_VALUE;
/*     */         }
/*     */         break;
/*     */       case 15:
/*  92 */         return new FloatValue((short)3, lu.getFloatValue());
/*     */       
/*     */       case 16:
/*  95 */         return new FloatValue((short)4, lu.getFloatValue());
/*     */       
/*     */       case 17:
/*  98 */         return new FloatValue((short)5, lu.getFloatValue());
/*     */       
/*     */       case 19:
/* 101 */         return new FloatValue((short)6, lu.getFloatValue());
/*     */       
/*     */       case 20:
/* 104 */         return new FloatValue((short)7, lu.getFloatValue());
/*     */       
/*     */       case 18:
/* 107 */         return new FloatValue((short)8, lu.getFloatValue());
/*     */       
/*     */       case 21:
/* 110 */         return new FloatValue((short)9, lu.getFloatValue());
/*     */       
/*     */       case 22:
/* 113 */         return new FloatValue((short)10, lu.getFloatValue());
/*     */       
/*     */       case 13:
/* 116 */         return new FloatValue((short)1, lu.getIntegerValue());
/*     */       
/*     */       case 14:
/* 119 */         return new FloatValue((short)1, lu.getFloatValue());
/*     */       
/*     */       case 23:
/* 122 */         return new FloatValue((short)2, lu.getFloatValue());
/*     */     } 
/*     */     
/* 125 */     throw createMalformedRectDOMException();
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
/* 138 */     if (value.getCssValueType() != 1) {
/* 139 */       return value;
/*     */     }
/* 141 */     if (value.getPrimitiveType() != 24) {
/* 142 */       return value;
/*     */     }
/* 144 */     RectValue rect = (RectValue)value;
/*     */     
/* 146 */     this.orientation = 1;
/* 147 */     Value top = super.computeValue(elt, pseudo, engine, idx, sm, rect.getTop());
/*     */     
/* 149 */     Value bottom = super.computeValue(elt, pseudo, engine, idx, sm, rect.getBottom());
/*     */     
/* 151 */     this.orientation = 0;
/* 152 */     Value left = super.computeValue(elt, pseudo, engine, idx, sm, rect.getLeft());
/*     */     
/* 154 */     Value right = super.computeValue(elt, pseudo, engine, idx, sm, rect.getRight());
/*     */     
/* 156 */     if (top != rect.getTop() || right != rect.getRight() || bottom != rect.getBottom() || left != rect.getLeft())
/*     */     {
/*     */ 
/*     */       
/* 160 */       return new RectValue(top, right, bottom, left);
/*     */     }
/* 162 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getOrientation() {
/* 171 */     return this.orientation;
/*     */   }
/*     */   
/*     */   private DOMException createMalformedRectDOMException() {
/* 175 */     Object[] p = { getPropertyName() };
/* 176 */     String s = Messages.formatMessage("malformed.rect", p);
/* 177 */     return new DOMException((short)12, s);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/RectManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */