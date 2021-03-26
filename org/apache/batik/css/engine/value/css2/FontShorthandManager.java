/*     */ package org.apache.batik.css.engine.value.css2;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.value.AbstractValueFactory;
/*     */ import org.apache.batik.css.engine.value.IdentifierManager;
/*     */ import org.apache.batik.css.engine.value.ShorthandManager;
/*     */ import org.apache.batik.css.engine.value.StringMap;
/*     */ import org.apache.batik.css.engine.value.ValueManager;
/*     */ import org.apache.batik.css.parser.CSSLexicalUnit;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FontShorthandManager
/*     */   extends AbstractValueFactory
/*     */   implements ShorthandManager
/*     */ {
/*     */   public String getPropertyName() {
/*  63 */     return "font";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatableProperty() {
/*  70 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdditiveProperty() {
/*  77 */     return false;
/*     */   }
/*     */   
/*  80 */   static LexicalUnit NORMAL_LU = (LexicalUnit)CSSLexicalUnit.createString((short)35, "normal", null);
/*     */   
/*  82 */   static LexicalUnit BOLD_LU = (LexicalUnit)CSSLexicalUnit.createString((short)35, "bold", null);
/*     */ 
/*     */   
/*  85 */   static LexicalUnit MEDIUM_LU = (LexicalUnit)CSSLexicalUnit.createString((short)35, "medium", null);
/*     */ 
/*     */   
/*  88 */   static LexicalUnit SZ_10PT_LU = (LexicalUnit)CSSLexicalUnit.createFloat((short)21, 10.0F, null);
/*     */   
/*  90 */   static LexicalUnit SZ_8PT_LU = (LexicalUnit)CSSLexicalUnit.createFloat((short)21, 8.0F, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   static LexicalUnit FONT_FAMILY_LU = (LexicalUnit)CSSLexicalUnit.createString((short)35, "Dialog", null);
/*     */   static {
/*  99 */     CSSLexicalUnit cSSLexicalUnit = CSSLexicalUnit.createString((short)35, "Helvetica", FONT_FAMILY_LU);
/*     */     
/* 101 */     CSSLexicalUnit.createString((short)35, "sans-serif", (LexicalUnit)cSSLexicalUnit);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 106 */   protected static final Set values = new HashSet();
/*     */   static {
/* 108 */     values.add("caption");
/* 109 */     values.add("icon");
/* 110 */     values.add("menu");
/* 111 */     values.add("message-box");
/* 112 */     values.add("small-caption");
/* 113 */     values.add("status-bar");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleSystemFont(CSSEngine eng, ShorthandManager.PropertyHandler ph, String s, boolean imp) {
/* 121 */     LexicalUnit fontSize, fontStyle = NORMAL_LU;
/* 122 */     LexicalUnit fontVariant = NORMAL_LU;
/* 123 */     LexicalUnit fontWeight = NORMAL_LU;
/* 124 */     LexicalUnit lineHeight = NORMAL_LU;
/* 125 */     LexicalUnit fontFamily = FONT_FAMILY_LU;
/*     */ 
/*     */     
/* 128 */     if (s.equals("small-caption")) {
/* 129 */       fontSize = SZ_8PT_LU;
/*     */     } else {
/* 131 */       fontSize = SZ_10PT_LU;
/*     */     } 
/* 133 */     ph.property("font-family", fontFamily, imp);
/* 134 */     ph.property("font-style", fontStyle, imp);
/* 135 */     ph.property("font-variant", fontVariant, imp);
/* 136 */     ph.property("font-weight", fontWeight, imp);
/* 137 */     ph.property("font-size", fontSize, imp);
/* 138 */     ph.property("line-height", lineHeight, imp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValues(CSSEngine eng, ShorthandManager.PropertyHandler ph, LexicalUnit lu, boolean imp) {
/*     */     String s, str1;
/* 148 */     switch (lu.getLexicalUnitType()) { case 12:
/*     */         return;
/*     */       case 35:
/* 151 */         s = lu.getStringValue().toLowerCase();
/* 152 */         if (values.contains(s)) {
/* 153 */           handleSystemFont(eng, ph, s, imp);
/*     */           return;
/*     */         } 
/*     */         break; }
/*     */ 
/*     */     
/* 159 */     LexicalUnit fontStyle = null;
/* 160 */     LexicalUnit fontVariant = null;
/* 161 */     LexicalUnit fontWeight = null;
/* 162 */     LexicalUnit fontSize = null;
/* 163 */     LexicalUnit lineHeight = null;
/* 164 */     LexicalUnit fontFamily = null;
/*     */     
/* 166 */     ValueManager[] vMgrs = eng.getValueManagers();
/*     */     
/* 168 */     int fst = eng.getPropertyIndex("font-style");
/* 169 */     int fv = eng.getPropertyIndex("font-variant");
/* 170 */     int fw = eng.getPropertyIndex("font-weight");
/* 171 */     int fsz = eng.getPropertyIndex("font-size");
/* 172 */     int lh = eng.getPropertyIndex("line-height");
/*     */     
/* 174 */     IdentifierManager fstVM = (IdentifierManager)vMgrs[fst];
/* 175 */     IdentifierManager fvVM = (IdentifierManager)vMgrs[fv];
/* 176 */     IdentifierManager fwVM = (IdentifierManager)vMgrs[fw];
/* 177 */     FontSizeManager fszVM = (FontSizeManager)vMgrs[fsz];
/*     */     
/* 179 */     StringMap fstSM = fstVM.getIdentifiers();
/* 180 */     StringMap fvSM = fvVM.getIdentifiers();
/* 181 */     StringMap fwSM = fwVM.getIdentifiers();
/* 182 */     StringMap fszSM = fszVM.getIdentifiers();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     boolean svwDone = false;
/* 189 */     LexicalUnit intLU = null;
/* 190 */     while (!svwDone && lu != null) {
/* 191 */       String str; switch (lu.getLexicalUnitType()) {
/*     */         case 35:
/* 193 */           str = lu.getStringValue().toLowerCase().intern();
/* 194 */           if (fontStyle == null && fstSM.get(str) != null) {
/* 195 */             fontStyle = lu;
/* 196 */             if (intLU != null) {
/* 197 */               if (fontWeight == null) {
/* 198 */                 fontWeight = intLU;
/* 199 */                 intLU = null; break;
/*     */               } 
/* 201 */               throw createInvalidLexicalUnitDOMException(intLU.getLexicalUnitType());
/*     */             } 
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 208 */           if (fontVariant == null && fvSM.get(str) != null) {
/* 209 */             fontVariant = lu;
/* 210 */             if (intLU != null) {
/* 211 */               if (fontWeight == null) {
/* 212 */                 fontWeight = intLU;
/* 213 */                 intLU = null; break;
/*     */               } 
/* 215 */               throw createInvalidLexicalUnitDOMException(intLU.getLexicalUnitType());
/*     */             } 
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 222 */           if (intLU == null && fontWeight == null && fwSM.get(str) != null) {
/*     */             
/* 224 */             fontWeight = lu;
/*     */             
/*     */             break;
/*     */           } 
/* 228 */           svwDone = true;
/*     */           break;
/*     */         
/*     */         case 13:
/* 232 */           if (intLU == null && fontWeight == null) {
/* 233 */             intLU = lu;
/*     */             break;
/*     */           } 
/* 236 */           svwDone = true;
/*     */           break;
/*     */         
/*     */         default:
/* 240 */           svwDone = true;
/*     */           break;
/*     */       } 
/* 243 */       if (!svwDone) lu = lu.getNextLexicalUnit();
/*     */     
/*     */     } 
/*     */     
/* 247 */     if (lu == null) {
/* 248 */       throw createMalformedLexicalUnitDOMException();
/*     */     }
/*     */     
/* 251 */     switch (lu.getLexicalUnitType()) {
/*     */       case 35:
/* 253 */         str1 = lu.getStringValue().toLowerCase().intern();
/* 254 */         if (fszSM.get(str1) != null) {
/* 255 */           fontSize = lu;
/* 256 */           lu = lu.getNextLexicalUnit();
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 13:
/*     */       case 14:
/*     */       case 15:
/*     */       case 16:
/*     */       case 17:
/*     */       case 18:
/*     */       case 19:
/*     */       case 20:
/*     */       case 21:
/*     */       case 22:
/*     */       case 23:
/* 272 */         fontSize = lu;
/* 273 */         lu = lu.getNextLexicalUnit();
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 278 */     if (fontSize == null)
/*     */     {
/* 280 */       if (intLU != null) {
/* 281 */         fontSize = intLU;
/* 282 */         intLU = null;
/*     */       } else {
/* 284 */         throw createInvalidLexicalUnitDOMException(lu.getLexicalUnitType());
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 289 */     if (intLU != null)
/*     */     {
/* 291 */       if (fontWeight == null) {
/* 292 */         fontWeight = intLU;
/*     */       } else {
/*     */         
/* 295 */         throw createInvalidLexicalUnitDOMException(intLU.getLexicalUnitType());
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 301 */     if (lu == null) {
/* 302 */       throw createMalformedLexicalUnitDOMException();
/*     */     }
/*     */ 
/*     */     
/* 306 */     switch (lu.getLexicalUnitType()) {
/*     */       case 4:
/* 308 */         lu = lu.getNextLexicalUnit();
/* 309 */         if (lu == null)
/* 310 */           throw createMalformedLexicalUnitDOMException(); 
/* 311 */         lineHeight = lu;
/* 312 */         lu = lu.getNextLexicalUnit();
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 317 */     if (lu == null)
/* 318 */       throw createMalformedLexicalUnitDOMException(); 
/* 319 */     fontFamily = lu;
/*     */     
/* 321 */     if (fontStyle == null) fontStyle = NORMAL_LU; 
/* 322 */     if (fontVariant == null) fontVariant = NORMAL_LU; 
/* 323 */     if (fontWeight == null) fontWeight = NORMAL_LU; 
/* 324 */     if (lineHeight == null) lineHeight = NORMAL_LU;
/*     */     
/* 326 */     ph.property("font-family", fontFamily, imp);
/* 327 */     ph.property("font-style", fontStyle, imp);
/* 328 */     ph.property("font-variant", fontVariant, imp);
/* 329 */     ph.property("font-weight", fontWeight, imp);
/* 330 */     ph.property("font-size", fontSize, imp);
/* 331 */     if (lh != -1)
/* 332 */       ph.property("line-height", lineHeight, imp); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/css2/FontShorthandManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */