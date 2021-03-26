/*     */ package jp.cssj.homare.css.e;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.f.E;
/*     */ import jp.cssj.homare.css.f.M;
/*     */ import jp.cssj.homare.css.f.S;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.css.f.a.d;
/*     */ import jp.cssj.homare.css.f.a.h;
/*     */ import jp.cssj.homare.css.f.ab;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.u;
/*     */ import jp.cssj.homare.css.f.w;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.e.d;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class l
/*     */ {
/*     */   public static boolean a(LexicalUnit lu) {
/*  38 */     return (lu.getLexicalUnitType() == 35 && lu.getStringValue().equalsIgnoreCase("auto"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean b(LexicalUnit lu) {
/*  48 */     return (lu.getLexicalUnitType() == 35 && lu.getStringValue().equalsIgnoreCase("none"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean c(LexicalUnit lu) {
/*  58 */     return (lu.getLexicalUnitType() == 35 && lu.getStringValue().equalsIgnoreCase("normal"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static E a(m ua, LexicalUnit lu) {
/*  69 */     short luType = lu.getLexicalUnitType();
/*  70 */     switch (luType) {
/*     */       case 15:
/*  72 */         return (E)u.a(lu.getFloatValue());
/*     */       
/*     */       case 16:
/*  75 */         return (E)w.a(lu.getFloatValue());
/*     */       
/*     */       case 43:
/*  78 */         return (E)h.a(lu.getFloatValue());
/*     */       
/*     */       case 44:
/*  81 */         return (E)d.a(lu.getFloatValue());
/*     */     } 
/*     */     
/*  84 */     return (E)b(ua, lu);
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
/*     */   public static E a(m ua, boolean legacy, String s) {
/*     */     try {
/*  98 */       s = s.toLowerCase().trim();
/*  99 */       if (s.endsWith("em")) {
/* 100 */         double len = d.a(s.substring(0, s.length() - 2));
/* 101 */         return (E)u.a(len);
/* 102 */       }  if (s.endsWith("ex")) {
/* 103 */         double len = d.a(s.substring(0, s.length() - 2));
/* 104 */         return (E)w.a(len);
/* 105 */       }  if (s.endsWith("ch")) {
/* 106 */         double len = d.a(s.substring(0, s.length() - 2));
/* 107 */         return (E)d.a(len);
/* 108 */       }  if (s.endsWith("rem")) {
/* 109 */         double len = d.a(s.substring(0, s.length() - 3));
/* 110 */         return (E)h.a(len);
/*     */       } 
/* 112 */       return (E)b(ua, legacy, s);
/*     */     }
/* 114 */     catch (NumberFormatException e) {
/* 115 */       return null;
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
/*     */   public static a b(m ua, boolean legacy, String s) {
/* 128 */     if (s == null) {
/* 129 */       return null;
/*     */     }
/* 131 */     s = s.toLowerCase().trim();
/*     */     try {
/* 133 */       if (s.endsWith("mm")) {
/* 134 */         double d = d.a(s.substring(0, s.length() - 2));
/* 135 */         return a.a(ua, d, (short)20);
/* 136 */       }  if (s.endsWith("cm")) {
/* 137 */         double d = d.a(s.substring(0, s.length() - 2));
/* 138 */         return a.a(ua, d, (short)19);
/* 139 */       }  if (s.endsWith("pt")) {
/* 140 */         double d = d.a(s.substring(0, s.length() - 2));
/* 141 */         return a.a(ua, d, (short)21);
/* 142 */       }  if (s.endsWith("px")) {
/* 143 */         double d = d.a(s.substring(0, s.length() - 2));
/* 144 */         return a.a(ua, d, (short)17);
/* 145 */       }  if (s.endsWith("pc")) {
/* 146 */         double d = d.a(s.substring(0, s.length() - 2));
/* 147 */         return a.a(ua, d, (short)22);
/* 148 */       }  if (s.endsWith("in")) {
/* 149 */         double d = d.a(s.substring(0, s.length() - 2));
/* 150 */         return a.a(ua, d, (short)18);
/*     */       } 
/* 152 */       double len = d.a(s);
/* 153 */       if (len == 0.0D) {
/* 154 */         return a.a;
/*     */       }
/* 156 */       if (legacy) {
/* 157 */         return a.a(ua, len, (short)17);
/*     */       }
/* 159 */       return null;
/*     */     }
/* 161 */     catch (NumberFormatException e) {
/* 162 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ad a(ad value, c style) {
/*     */     a a;
/*     */     u em;
/*     */     w ex;
/*     */     h rem;
/*     */     d ch;
/* 174 */     switch (value.a()) {
/*     */       case 1001:
/* 176 */         em = (u)value;
/* 177 */         a = em.a(style);
/*     */         break;
/*     */       
/*     */       case 1002:
/* 181 */         ex = (w)a;
/* 182 */         a = ex.a(style);
/*     */         break;
/*     */       
/*     */       case 3008:
/* 186 */         rem = (h)a;
/* 187 */         a = rem.a(style);
/*     */         break;
/*     */       
/*     */       case 3009:
/* 191 */         ch = (d)a;
/* 192 */         a = ch.a(style);
/*     */         break;
/*     */     } 
/* 195 */     return (ad)a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static a b(m ua, LexicalUnit lu) {
/*     */     int i;
/*     */     double val;
/* 206 */     short luType = lu.getLexicalUnitType();
/* 207 */     switch (luType) {
/*     */       case 17:
/*     */       case 18:
/*     */       case 19:
/*     */       case 20:
/*     */       case 21:
/*     */       case 22:
/* 214 */         return a.a(ua, lu.getFloatValue(), luType);
/*     */       
/*     */       case 13:
/* 217 */         i = lu.getIntegerValue();
/* 218 */         if (i == 0) {
/* 219 */           return a.a(ua, i, (short)17);
/*     */         }
/* 221 */         return null;
/*     */ 
/*     */       
/*     */       case 14:
/* 225 */         val = lu.getFloatValue();
/* 226 */         if (val == 0.0D) {
/* 227 */           return a.a(ua, val, (short)17);
/*     */         }
/* 229 */         return null;
/*     */     } 
/*     */ 
/*     */     
/* 233 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static M d(LexicalUnit lu) {
/* 244 */     if (lu.getLexicalUnitType() == 23) {
/* 245 */       return M.a(lu.getFloatValue());
/*     */     }
/* 247 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static S e(LexicalUnit lu) {
/* 258 */     if (lu.getLexicalUnitType() == 14)
/* 259 */       return S.a(lu.getFloatValue()); 
/* 260 */     if (lu.getLexicalUnitType() == 13) {
/* 261 */       return S.a(lu.getIntegerValue());
/*     */     }
/* 263 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ab a(m ua, URI baseURI, LexicalUnit lu) throws URISyntaxException {
/* 274 */     if (lu.getLexicalUnitType() == 24) {
/* 275 */       String href = lu.getStringValue();
/* 276 */       return k.a(ua.c().c(), baseURI, href);
/*     */     } 
/* 278 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/e/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */