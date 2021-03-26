/*     */ package jp.cssj.homare.impl.a.c.a;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.c.b;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.H;
/*     */ import jp.cssj.homare.css.f.V;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class A
/*     */   extends b
/*     */ {
/*  23 */   public static final j a = (j)new A();
/*     */   
/*  25 */   protected static final ad b = (ad)new V("filled");
/*  26 */   protected static final ad c = (ad)new V("open");
/*  27 */   protected static final ad d = (ad)new V("•");
/*  28 */   protected static final ad e = (ad)new V("◦");
/*  29 */   protected static final ad f = (ad)new V("●");
/*  30 */   protected static final ad g = (ad)new V("○");
/*  31 */   protected static final ad h = (ad)new V("◉");
/*  32 */   protected static final ad i = (ad)new V("◎");
/*  33 */   protected static final ad j = (ad)new V("▲");
/*  34 */   protected static final ad k = (ad)new V("△");
/*  35 */   protected static final ad l = (ad)new V("﹅");
/*  36 */   protected static final ad m = (ad)new V("﹆");
/*     */   
/*     */   public static String c(c style) {
/*  39 */     ad value = style.a(a);
/*  40 */     if (value.a() == 1007) {
/*  41 */       return null;
/*     */     }
/*  43 */     return ((V)value).b();
/*     */   }
/*     */   
/*     */   protected A() {
/*  47 */     super("-cssj-text-emphasis-style");
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  51 */     return (ad)H.a;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  55 */     return true;
/*     */   }
/*     */   
/*     */   public ad a(ad value, c style) {
/*  59 */     if (value == b) {
/*  60 */       if (e.a(c.c(style))) {
/*  61 */         value = l;
/*     */       } else {
/*  63 */         value = f;
/*     */       } 
/*  65 */     } else if (value == c) {
/*  66 */       if (e.a(c.c(style))) {
/*  67 */         value = m;
/*     */       } else {
/*  69 */         value = g;
/*     */       } 
/*     */     } 
/*  72 */     return value;
/*     */   }
/*     */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*     */     ad str;
/*  76 */     byte fill = 0, type = 0; do {
/*     */       String ident;
/*  78 */       switch (lu.getLexicalUnitType()) {
/*     */         case 35:
/*  80 */           if (l.b(lu)) {
/*  81 */             if (lu.getNextLexicalUnit() != null) {
/*  82 */               throw new l();
/*     */             }
/*  84 */             return (ad)H.a;
/*     */           } 
/*  86 */           ident = lu.getStringValue().toLowerCase();
/*  87 */           if (ident.equals("filled")) {
/*  88 */             if (fill != 0) {
/*  89 */               throw new l();
/*     */             }
/*  91 */             fill = 1; break;
/*  92 */           }  if (ident.equals("open")) {
/*  93 */             if (fill != 0) {
/*  94 */               throw new l();
/*     */             }
/*  96 */             fill = 2; break;
/*  97 */           }  if (ident.equals("dot")) {
/*  98 */             if (type != 0) {
/*  99 */               throw new l();
/*     */             }
/* 101 */             type = 1; break;
/* 102 */           }  if (ident.equals("circle")) {
/* 103 */             if (type != 0) {
/* 104 */               throw new l();
/*     */             }
/* 106 */             type = 2; break;
/* 107 */           }  if (ident.equals("double-circle")) {
/* 108 */             if (type != 0) {
/* 109 */               throw new l();
/*     */             }
/* 111 */             type = 3; break;
/* 112 */           }  if (ident.equals("triangle")) {
/* 113 */             if (type != 0) {
/* 114 */               throw new l();
/*     */             }
/* 116 */             type = 4; break;
/* 117 */           }  if (ident.equals("sesame")) {
/* 118 */             if (type != 0) {
/* 119 */               throw new l();
/*     */             }
/* 121 */             type = 5; break;
/*     */           } 
/* 123 */           throw new l();
/*     */ 
/*     */         
/*     */         case 36:
/* 127 */           if (fill != 0 || lu.getNextLexicalUnit() != null) {
/* 128 */             throw new l();
/*     */           }
/* 130 */           return (ad)new V(lu.getStringValue());
/*     */         
/*     */         default:
/* 133 */           throw new l();
/*     */       } 
/* 135 */       lu = lu.getNextLexicalUnit();
/* 136 */     } while (lu != null);
/* 137 */     if (type == 0) {
/* 138 */       type = -1;
/*     */     }
/*     */     
/* 141 */     switch (type) {
/*     */       case -1:
/* 143 */         if (fill != 2) {
/* 144 */           str = b;
/*     */         } else {
/* 146 */           str = c;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 187 */         return str;case 1: if (fill != 2) { str = d; } else { str = e; }  return str;case 2: if (fill != 2) { str = f; } else { str = g; }  return str;case 3: if (fill != 2) { str = h; } else { str = i; }  return str;case 4: if (fill != 2) { str = j; } else { str = k; }  return str;case 5: if (fill != 2) { str = l; } else { str = m; }  return str;
/*     */     } 
/*     */     throw new l();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/A.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */