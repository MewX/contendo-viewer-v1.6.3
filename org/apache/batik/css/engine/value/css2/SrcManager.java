/*     */ package org.apache.batik.css.engine.value.css2;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.IdentifierManager;
/*     */ import org.apache.batik.css.engine.value.ListValue;
/*     */ import org.apache.batik.css.engine.value.StringMap;
/*     */ import org.apache.batik.css.engine.value.StringValue;
/*     */ import org.apache.batik.css.engine.value.URIValue;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.css.engine.value.ValueConstants;
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
/*     */ public class SrcManager
/*     */   extends IdentifierManager
/*     */ {
/*  50 */   protected static final StringMap values = new StringMap();
/*     */   static {
/*  52 */     values.put("none", ValueConstants.NONE_VALUE);
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
/*     */   public boolean isInheritedProperty() {
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyType() {
/*  85 */     return 38;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/*  93 */     return "src";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value getDefaultValue() {
/* 101 */     return ValueConstants.NONE_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Value createValue(LexicalUnit lu, CSSEngine engine) throws DOMException {
/* 111 */     switch (lu.getLexicalUnitType()) {
/*     */       case 12:
/* 113 */         return ValueConstants.INHERIT_VALUE;
/*     */       
/*     */       default:
/* 116 */         throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */       
/*     */       case 24:
/*     */       case 35:
/*     */       case 36:
/*     */         break;
/*     */     } 
/*     */     
/* 124 */     ListValue result = new ListValue(); while (true) {
/*     */       String uri; StringBuffer sb; String id, s; Value v;
/* 126 */       switch (lu.getLexicalUnitType()) {
/*     */         case 36:
/* 128 */           result.append((Value)new StringValue((short)19, lu.getStringValue()));
/*     */           
/* 130 */           lu = lu.getNextLexicalUnit();
/*     */           break;
/*     */         
/*     */         case 24:
/* 134 */           uri = resolveURI(engine.getCSSBaseURI(), lu.getStringValue());
/*     */ 
/*     */           
/* 137 */           result.append((Value)new URIValue(lu.getStringValue(), uri));
/* 138 */           lu = lu.getNextLexicalUnit();
/* 139 */           if (lu != null && lu.getLexicalUnitType() == 41) {
/*     */             
/* 141 */             if (!lu.getFunctionName().equalsIgnoreCase("format")) {
/*     */               break;
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 148 */             lu = lu.getNextLexicalUnit();
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 35:
/* 153 */           sb = new StringBuffer(lu.getStringValue());
/* 154 */           lu = lu.getNextLexicalUnit();
/* 155 */           if (lu != null && lu.getLexicalUnitType() == 35) {
/*     */             
/*     */             do {
/* 158 */               sb.append(' ');
/* 159 */               sb.append(lu.getStringValue());
/* 160 */               lu = lu.getNextLexicalUnit();
/*     */             }
/* 162 */             while (lu != null && lu.getLexicalUnitType() == 35);
/* 163 */             result.append((Value)new StringValue((short)19, sb.toString()));
/*     */             break;
/*     */           } 
/* 166 */           id = sb.toString();
/* 167 */           s = id.toLowerCase().intern();
/* 168 */           v = (Value)values.get(s);
/* 169 */           result.append((v != null) ? v : (Value)new StringValue((short)19, id));
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 176 */       if (lu == null) {
/* 177 */         return (Value)result;
/*     */       }
/* 179 */       if (lu.getLexicalUnitType() != 0) {
/* 180 */         throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */       }
/*     */       
/* 183 */       lu = lu.getNextLexicalUnit();
/* 184 */       if (lu == null) {
/* 185 */         throw createMalformedLexicalUnitDOMException();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringMap getIdentifiers() {
/* 194 */     return values;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/SrcManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */