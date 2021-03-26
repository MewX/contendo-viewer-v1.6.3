/*     */ package jp.cssj.homare.impl.a.c.d;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import jp.cssj.homare.css.c.d;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.c.o;
/*     */ import jp.cssj.homare.css.e.c;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.C;
/*     */ import jp.cssj.homare.css.f.E;
/*     */ import jp.cssj.homare.css.f.H;
/*     */ import jp.cssj.homare.css.f.M;
/*     */ import jp.cssj.homare.css.f.aa;
/*     */ import jp.cssj.homare.css.f.ab;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.e;
/*     */ import jp.cssj.homare.css.f.f;
/*     */ import jp.cssj.homare.css.f.n;
/*     */ import jp.cssj.homare.impl.a.c.b;
/*     */ import jp.cssj.homare.impl.a.c.c;
/*     */ import jp.cssj.homare.impl.a.c.d;
/*     */ import jp.cssj.homare.impl.a.c.e;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends d
/*     */ {
/*  32 */   public static final o a = (o)new a();
/*     */   
/*     */   protected a() {
/*  35 */     super("background");
/*     */   }
/*     */   
/*     */   public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/*  39 */     if (lu.getLexicalUnitType() == 12) {
/*  40 */       primitives.a(b.a, (ad)C.a);
/*  41 */       primitives.a(c.a, (ad)C.a);
/*  42 */       primitives.a(jp.cssj.homare.impl.a.c.a.a, (ad)C.a);
/*  43 */       primitives.a(e.a, (ad)C.a);
/*  44 */       primitives.a(d.a, (ad)C.a);
/*  45 */       primitives.a(d.b, (ad)C.a);
/*     */       
/*     */       return;
/*     */     } 
/*  49 */     primitives.a(b.a, (ad)aa.a);
/*  50 */     primitives.a(c.a, (ad)H.a);
/*  51 */     primitives.a(jp.cssj.homare.impl.a.c.a.a, (ad)e.a);
/*  52 */     primitives.a(e.a, (ad)f.d);
/*  53 */     primitives.a(d.a, (ad)M.a);
/*  54 */     primitives.a(d.b, (ad)M.a);
/*  55 */     boolean color = false, none = false, uriValue = false, repeat = false, attachment = false, position = false; while (true) {
/*  56 */       if (lu != null) {
/*  57 */         if (c.a(lu)) {
/*  58 */           primitives.a(b.a, (ad)aa.a);
/*     */         } else {
/*     */           
/*  61 */           n n = c.a(ua, lu);
/*  62 */           if (n != null) {
/*  63 */             if (color) {
/*  64 */               throw new l("colorが2度指定されています");
/*     */             }
/*  66 */             color = true;
/*     */             
/*  68 */             primitives.a(b.a, (ad)n);
/*     */           
/*     */           }
/*  71 */           else if (l.b(lu)) {
/*  72 */             if (none) {
/*  73 */               throw new l("noneが2度指定されています");
/*     */             }
/*  75 */             none = true;
/*  76 */             primitives.a(c.a, (ad)H.a);
/*     */           } else {
/*     */             
/*     */             try {
/*  80 */               ab ab = l.a(ua, uri, lu);
/*  81 */               if (ab != null) {
/*  82 */                 if (uriValue) {
/*  83 */                   throw new l("urlが2度指定されています");
/*     */                 }
/*  85 */                 uriValue = true;
/*  86 */                 primitives.a(c.a, (ad)ab);
/*     */ 
/*     */               
/*     */               }
/*     */               else {
/*     */ 
/*     */ 
/*     */                 
/*  94 */                 f f = c.b(lu);
/*  95 */                 if (f != null) {
/*  96 */                   if (repeat) {
/*  97 */                     throw new l("repeatが2度指定されています");
/*     */                   }
/*  99 */                   repeat = true;
/* 100 */                   primitives.a(e.a, (ad)f);
/*     */                 } else {
/*     */                   
/* 103 */                   e e = c.c(lu);
/* 104 */                   if (e != null) {
/* 105 */                     if (attachment) {
/* 106 */                       throw new l("attachmentが2度指定されています");
/*     */                     }
/* 108 */                     attachment = true;
/* 109 */                     primitives.a(jp.cssj.homare.impl.a.c.a.a, (ad)e);
/*     */                   }
/*     */                   else {
/*     */                     
/* 113 */                     if (position) {
/* 114 */                       throw new l("positionが2度指定されています");
/*     */                     }
/* 116 */                     position = true;
/*     */ 
/*     */                     
/* 119 */                     if (lu.getLexicalUnitType() == 35) {
/* 120 */                       M m1, m2; String kw2, kw1 = lu.getStringValue().toLowerCase();
/* 121 */                       if (!kw1.equals("top") && !kw1.equals("bottom") && !kw1.equals("center") && !kw1.equals("left") && 
/* 122 */                         !kw1.equals("right")) {
/* 123 */                         throw new l();
/*     */                       }
/*     */                       
/* 126 */                       LexicalUnit nextlu = lu.getNextLexicalUnit();
/* 127 */                       if (nextlu == null) {
/* 128 */                         kw2 = null;
/* 129 */                       } else if (nextlu.getLexicalUnitType() == 35) {
/* 130 */                         kw2 = nextlu.getStringValue().toLowerCase();
/* 131 */                         if (!kw2.equals("top") && !kw2.equals("bottom") && !kw2.equals("center") && !kw2.equals("left") && 
/* 132 */                           !kw2.equals("right")) {
/* 133 */                           kw2 = null;
/*     */                         } else {
/* 135 */                           lu = nextlu;
/*     */                         } 
/*     */                       } else {
/* 138 */                         E e1; M m3 = l.d(nextlu);
/* 139 */                         if (m3 == null) {
/* 140 */                           e1 = l.a(ua, nextlu);
/*     */                         }
/* 142 */                         if (e1 == null) {
/* 143 */                           kw2 = null;
/*     */                         } else {
/* 145 */                           lu = nextlu;
/*     */                           
/* 147 */                           if (kw1.equals("left")) {
/* 148 */                             m1 = M.a;
/* 149 */                           } else if (kw1.equals("center")) {
/* 150 */                             m1 = M.b;
/* 151 */                           } else if (kw1.equals("right")) {
/* 152 */                             m1 = M.c;
/*     */                           } else {
/* 154 */                             throw new l();
/*     */                           } 
/*     */                           
/* 157 */                           primitives.a(d.a, (ad)m1);
/* 158 */                           primitives.a(d.b, (ad)e1);
/*     */                           
/*     */                           lu = lu.getNextLexicalUnit();
/*     */                         } 
/*     */                       } 
/* 163 */                       if (("top".equals(kw1) && "left".equals(kw2)) || ("left".equals(kw1) && "top".equals(kw2))) {
/* 164 */                         m1 = m2 = M.a;
/* 165 */                       } else if (("top".equals(kw1) && kw2 == null) || ("top".equals(kw1) && "center".equals(kw2)) || ("center"
/* 166 */                         .equals(kw1) && "top".equals(kw2))) {
/* 167 */                         m1 = M.b;
/* 168 */                         m2 = M.a;
/* 169 */                       } else if (("right".equals(kw1) && "top".equals(kw2)) || ("top".equals(kw1) && "right".equals(kw2))) {
/* 170 */                         m1 = M.c;
/* 171 */                         m2 = M.a;
/* 172 */                       } else if (("left".equals(kw1) && kw2 == null) || ("left".equals(kw1) && "center".equals(kw2)) || ("center"
/* 173 */                         .equals(kw1) && "left".equals(kw2))) {
/* 174 */                         m1 = M.a;
/* 175 */                         m2 = M.b;
/* 176 */                       } else if (("center".equals(kw1) && kw2 == null) || ("center".equals(kw1) && "center".equals(kw2))) {
/* 177 */                         m1 = m2 = M.b;
/* 178 */                       } else if (("right".equals(kw1) && kw2 == null) || ("right".equals(kw1) && "center".equals(kw2)) || ("center"
/* 179 */                         .equals(kw1) && "right".equals(kw2))) {
/* 180 */                         m1 = M.c;
/* 181 */                         m2 = M.b;
/* 182 */                       } else if (("left".equals(kw1) && "bottom".equals(kw2)) || ("bottom"
/* 183 */                         .equals(kw1) && "left".equals(kw2))) {
/* 184 */                         m1 = M.a;
/* 185 */                         m2 = M.c;
/* 186 */                       } else if (("bottom".equals(kw1) && kw2 == null) || ("bottom".equals(kw1) && "center".equals(kw2)) || ("center"
/* 187 */                         .equals(kw1) && "bottom".equals(kw2))) {
/* 188 */                         m1 = M.b;
/* 189 */                         m2 = M.c;
/* 190 */                       } else if (("bottom".equals(kw1) && "right".equals(kw2)) || ("right"
/* 191 */                         .equals(kw1) && "bottom".equals(kw2))) {
/* 192 */                         m1 = m2 = M.c;
/*     */                       } else {
/* 194 */                         throw new l();
/*     */                       } 
/*     */                       
/* 197 */                       primitives.a(d.a, (ad)m1);
/* 198 */                       primitives.a(d.b, (ad)m2);
/*     */                     } else {
/*     */                       E e1;
/*     */                       
/* 202 */                       M m1 = l.d(lu);
/* 203 */                       if (m1 == null) {
/* 204 */                         e1 = l.a(ua, lu);
/*     */                       }
/* 206 */                       if (e1 == null) {
/* 207 */                         throw new l();
/*     */                       }
/*     */                       
/* 210 */                       LexicalUnit nextlu = lu.getNextLexicalUnit();
/* 211 */                       if (nextlu == null) {
/* 212 */                         E e2 = e1;
/* 213 */                         primitives.a(d.a, (ad)e1);
/* 214 */                         primitives.a(d.b, (ad)e2);
/*     */                       } else {
/*     */                         E e2;
/*     */                         
/* 218 */                         if (nextlu.getLexicalUnitType() == 35) {
/* 219 */                           String kw2 = nextlu.getStringValue().toLowerCase();
/* 220 */                           if (kw2.equals("top")) {
/* 221 */                             lu = nextlu;
/* 222 */                             M m2 = M.a;
/* 223 */                           } else if (kw2.equals("center")) {
/* 224 */                             lu = nextlu;
/* 225 */                             M m2 = M.b;
/* 226 */                           } else if (kw2.equals("bottom")) {
/* 227 */                             lu = nextlu;
/* 228 */                             M m2 = M.c;
/*     */                           } else {
/* 230 */                             e2 = e1;
/*     */                           } 
/*     */                         } else {
/* 233 */                           M m2 = l.d(nextlu);
/* 234 */                           if (m2 == null) {
/* 235 */                             e2 = l.a(ua, nextlu);
/*     */                           }
/* 237 */                           if (e2 == null) {
/* 238 */                             e2 = e1;
/*     */                           } else {
/* 240 */                             lu = nextlu;
/*     */                           } 
/*     */                         } 
/* 243 */                         primitives.a(d.a, (ad)e1);
/* 244 */                         primitives.a(d.b, (ad)e2);
/*     */                       } 
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } catch (URISyntaxException e) {
/*     */               uriValue = true;
/*     */               ua.a((short)10252, lu.getStringValue());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         break;
/*     */       } 
/*     */       lu = lu.getNextLexicalUnit();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/d/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */