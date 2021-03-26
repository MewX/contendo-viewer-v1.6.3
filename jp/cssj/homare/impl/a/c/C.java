/*     */ package jp.cssj.homare.impl.a.c;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.c.b;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.e.e;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.G;
/*     */ import jp.cssj.homare.css.f.H;
/*     */ import jp.cssj.homare.css.f.P;
/*     */ import jp.cssj.homare.css.f.V;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.ae;
/*     */ import jp.cssj.homare.css.f.b.c;
/*     */ import jp.cssj.homare.css.f.b.e;
/*     */ import jp.cssj.homare.css.f.b.g;
/*     */ import jp.cssj.homare.css.f.b.i;
/*     */ import jp.cssj.homare.css.f.c;
/*     */ import jp.cssj.homare.css.f.p;
/*     */ import jp.cssj.homare.css.f.q;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class C
/*     */   extends b
/*     */ {
/*  35 */   public static final j a = (j)new C();
/*     */   
/*     */   public static ad[] c(c style) {
/*  38 */     ad value = style.a(a);
/*  39 */     if (value.a() == 1007) {
/*  40 */       return null;
/*     */     }
/*  42 */     return ((ae)value).b();
/*     */   }
/*     */   
/*     */   protected C() {
/*  46 */     super("content");
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  50 */     return (ad)H.a;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   public ad a(ad value, c style) {
/*  58 */     return value;
/*     */   }
/*     */   
/*     */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*  62 */     if (lu.getLexicalUnitType() == 35) {
/*  63 */       String ident = lu.getStringValue();
/*  64 */       if (ident.equalsIgnoreCase("none") || ident.equalsIgnoreCase("normal")) {
/*  65 */         if (lu.getNextLexicalUnit() != null) {
/*  66 */           throw new l();
/*     */         }
/*  68 */         return (ad)H.a;
/*     */       } 
/*     */     } 
/*     */     
/*  72 */     ArrayList<ad> values = new ArrayList<>();
/*  73 */     while (lu != null) {
/*  74 */       p counter; q counters; String ident, funcName; LexicalUnit param; String id, delimiter; switch (lu.getLexicalUnitType()) {
/*     */         case 36:
/*  76 */           values.add(new V(lu.getStringValue()));
/*     */           break;
/*     */ 
/*     */         
/*     */         case 24:
/*     */           try {
/*  82 */             values.add(l.a(ua, uri, lu));
/*  83 */           } catch (URISyntaxException e) {
/*  84 */             ua.a((short)10252, lu.getStringValue());
/*     */           } 
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 25:
/*  91 */           param = lu.getParameters();
/*  92 */           id = param.getStringValue();
/*  93 */           param = param.getNextLexicalUnit();
/*  94 */           if (param == null) {
/*  95 */             counter = new p(id);
/*     */           } else {
/*  97 */             param = param.getNextLexicalUnit();
/*  98 */             switch (param.getLexicalUnitType()) {
/*     */               case 35:
/*     */               case 36:
/*     */                 break;
/*     */               default:
/* 103 */                 throw new l();
/*     */             } 
/* 105 */             String listStyle = param.getStringValue();
/* 106 */             G styleType = e.a(listStyle);
/* 107 */             if (styleType == null) {
/* 108 */               throw new l();
/*     */             }
/* 110 */             counter = new p(id, styleType.b());
/*     */           } 
/* 112 */           values.add(counter);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 26:
/* 118 */           param = lu.getParameters();
/* 119 */           id = param.getStringValue();
/* 120 */           param = param.getNextLexicalUnit();
/* 121 */           param = param.getNextLexicalUnit();
/* 122 */           delimiter = param.getStringValue();
/* 123 */           param = param.getNextLexicalUnit();
/* 124 */           if (param == null) {
/* 125 */             counters = new q(id, delimiter);
/*     */           } else {
/* 127 */             param = param.getNextLexicalUnit();
/* 128 */             String listStyle = param.getStringValue();
/* 129 */             G styleType = e.a(listStyle);
/* 130 */             if (styleType == null) {
/* 131 */               throw new l();
/*     */             }
/* 133 */             counters = new q(id, delimiter, styleType);
/*     */           } 
/* 135 */           values.add(counters);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 37:
/* 140 */           values.add(new c(lu.getStringValue()));
/*     */           break;
/*     */ 
/*     */         
/*     */         case 35:
/* 145 */           ident = lu.getStringValue();
/* 146 */           ident = ident.toLowerCase();
/* 147 */           if (ident.equals("open-quote")) {
/* 148 */             values.add(P.e); break;
/* 149 */           }  if (ident.equals("close-quote")) {
/* 150 */             values.add(P.f); break;
/* 151 */           }  if (ident.equals("no-open-quote")) {
/* 152 */             values.add(P.g); break;
/* 153 */           }  if (ident.equals("no-close-quote")) {
/* 154 */             values.add(P.h); break;
/* 155 */           }  if (ident.equals("-cssj-title")) {
/* 156 */             values.add(i.a); break;
/*     */           } 
/* 158 */           throw new l();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 41:
/* 164 */           funcName = lu.getFunctionName();
/* 165 */           if (funcName.equalsIgnoreCase("-cssj-heading") || funcName.equalsIgnoreCase("-cssj-last-heading")) {
/*     */             e heading;
/*     */             
/* 168 */             LexicalUnit lexicalUnit = lu.getParameters();
/* 169 */             if (lexicalUnit == null) {
/* 170 */               heading = new e(1);
/*     */             } else {
/* 172 */               if (lexicalUnit.getLexicalUnitType() != 13 || lexicalUnit
/* 173 */                 .getNextLexicalUnit() != null) {
/* 174 */                 throw new l();
/*     */               }
/* 176 */               int level = lexicalUnit.getIntegerValue();
/* 177 */               heading = new e(level);
/*     */             } 
/* 179 */             values.add(heading); break;
/*     */           } 
/* 181 */           if (funcName.equalsIgnoreCase("-cssj-first-heading")) {
/*     */             c heading;
/*     */             
/* 184 */             LexicalUnit lexicalUnit = lu.getParameters();
/* 185 */             if (lexicalUnit == null) {
/* 186 */               heading = new c(1);
/*     */             } else {
/* 188 */               if (lexicalUnit.getLexicalUnitType() != 13 || lexicalUnit
/* 189 */                 .getNextLexicalUnit() != null) {
/* 190 */                 throw new l();
/*     */               }
/* 192 */               int level = lexicalUnit.getIntegerValue();
/* 193 */               heading = new c(level);
/*     */             } 
/* 195 */             values.add(heading); break;
/*     */           } 
/* 197 */           if (funcName.equalsIgnoreCase("-cssj-page-ref")) {
/*     */             byte type; short numberStyleType;
/* 199 */             LexicalUnit lexicalUnit = lu.getParameters();
/* 200 */             if (lexicalUnit == null) {
/* 201 */               throw new l();
/*     */             }
/*     */             
/* 204 */             switch (lexicalUnit.getLexicalUnitType()) {
/*     */               case 35:
/*     */               case 36:
/* 207 */                 type = 1;
/*     */                 break;
/*     */               
/*     */               case 37:
/* 211 */                 type = 2;
/*     */                 break;
/*     */               
/*     */               default:
/* 215 */                 throw new l("IDが必要です");
/*     */             } 
/* 217 */             String ref = lexicalUnit.getStringValue();
/* 218 */             lexicalUnit = lexicalUnit.getNextLexicalUnit();
/* 219 */             if (lexicalUnit == null || lexicalUnit.getLexicalUnitType() != 0) {
/* 220 */               throw new l("カンマが必要です");
/*     */             }
/* 222 */             lexicalUnit = lexicalUnit.getNextLexicalUnit();
/* 223 */             if (lexicalUnit == null || (lexicalUnit.getLexicalUnitType() != 35 && lexicalUnit
/* 224 */               .getLexicalUnitType() != 36)) {
/* 225 */               throw new l("カウンタ名が必要です");
/*     */             }
/* 227 */             String str1 = lexicalUnit.getStringValue();
/*     */             
/* 229 */             String separator = null;
/* 230 */             lexicalUnit = lexicalUnit.getNextLexicalUnit();
/* 231 */             if (lexicalUnit == null) {
/* 232 */               numberStyleType = 4;
/*     */             } else {
/* 234 */               if (lexicalUnit.getLexicalUnitType() != 0) {
/* 235 */                 throw new l("カンマが必要です");
/*     */               }
/* 237 */               lexicalUnit = lexicalUnit.getNextLexicalUnit();
/* 238 */               if (lexicalUnit == null || lexicalUnit.getLexicalUnitType() != 35) {
/* 239 */                 throw new l("数字タイプが必要です");
/*     */               }
/* 241 */               String typeStr = lexicalUnit.getStringValue();
/* 242 */               G typeValue = e.a(typeStr);
/* 243 */               if (typeValue == null) {
/* 244 */                 throw new l("数字タイプが不正です");
/*     */               }
/* 246 */               numberStyleType = typeValue.b();
/*     */               
/* 248 */               lexicalUnit = lexicalUnit.getNextLexicalUnit();
/* 249 */               if (lexicalUnit != null) {
/* 250 */                 if (lexicalUnit.getLexicalUnitType() != 0) {
/* 251 */                   throw new l("カンマが必要です");
/*     */                 }
/* 253 */                 lexicalUnit = lexicalUnit.getNextLexicalUnit();
/*     */                 
/* 255 */                 if (lexicalUnit == null || lexicalUnit.getLexicalUnitType() != 36)
/* 256 */                   throw new l("区切り文字が必要です"); 
/* 257 */                 separator = lexicalUnit.getStringValue();
/*     */               } 
/*     */             } 
/*     */             
/* 261 */             g pageRef = new g(type, ref, str1, numberStyleType, separator);
/* 262 */             values.add(pageRef);
/*     */             break;
/*     */           } 
/*     */         
/*     */         default:
/* 267 */           throw new l();
/*     */       } 
/*     */       
/* 270 */       lu = lu.getNextLexicalUnit();
/*     */     } 
/* 272 */     if (values.isEmpty()) {
/* 273 */       throw new l();
/*     */     }
/* 275 */     return (ad)new ae(values.<ad>toArray(new ad[values.size()]));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/C.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */