/*     */ package jp.cssj.homare.impl.a.c;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.c.b;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.t;
/*     */ import jp.cssj.homare.impl.a.c.a.c;
/*     */ import jp.cssj.homare.impl.a.c.c.e;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class G
/*     */   extends b
/*     */ {
/*  24 */   public static final j a = (j)new G();
/*     */   
/*     */   public static byte c(c style) {
/*  27 */     t value = (t)style.a(a);
/*  28 */     return value.b();
/*     */   }
/*     */   
/*     */   protected G() {
/*  32 */     super("display"); } public ad a(ad value, c style) { t t; byte b1;
/*     */     short position;
/*     */     c parentStyle;
/*     */     short s1;
/*  36 */     byte display = ((t)value).b();
/*     */ 
/*     */     
/*  39 */     switch (display) {
/*     */       case 7:
/*  41 */         b1 = y.c(style);
/*  42 */         if (b1 == 2 || b1 == 3 || b1 == 4) {
/*     */           break;
/*     */         }
/*     */         
/*  46 */         if (v.c(style) != 0) {
/*  47 */           t = t.w;
/*  48 */           display = 6;
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/*  53 */         position = (short)y.c(style);
/*  54 */         if (position == 2 || position == 3 || position == 4) {
/*     */           
/*  56 */           t = t.t;
/*  57 */           display = 3;
/*     */           break;
/*     */         } 
/*  60 */         if (v.c(style) != 0) {
/*  61 */           t = t.r;
/*  62 */           display = 1;
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 3:
/*  67 */         position = (short)y.c(style);
/*  68 */         if (position == 2 || position == 3 || position == 4) {
/*     */           break;
/*     */         }
/*     */         
/*  72 */         if (v.c(style) != 0) {
/*  73 */           t = t.r;
/*  74 */           display = 1;
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 15:
/*  80 */         parentStyle = style.c();
/*  81 */         if (parentStyle != null) {
/*  82 */           switch (c(parentStyle)) {
/*     */             case 6:
/*     */             case 8:
/*     */             case 9:
/*     */             case 10:
/*     */             case 11:
/*     */             case 12:
/*     */             case 13:
/*     */               break;
/*     */             default:
/*  92 */               t = t.r;
/*  93 */               display = 1;
/*     */               break;
/*     */           } 
/*     */         }
/*     */       case 5:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/*     */       case 14:
/* 105 */         s1 = (short)y.c(style);
/* 106 */         if (v.c(style) != 0 || (s1 != 0 && s1 != 1)) {
/*     */           
/* 108 */           t = t.r;
/* 109 */           display = 1;
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 0:
/*     */       case 1:
/*     */       case 4:
/*     */       case 6:
/*     */         break;
/*     */       
/*     */       default:
/* 120 */         throw new IllegalStateException();
/*     */     } 
/*     */ 
/*     */     
/* 124 */     switch (display) {
/*     */       case 7:
/* 126 */         if (e.c(style) != null) {
/* 127 */           return (ad)t.s;
/*     */         }
/*     */         break;
/*     */       
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 8:
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/*     */       case 14:
/*     */       case 15:
/* 140 */         if (e.c(style) != null) {
/* 141 */           return (ad)t.r;
/*     */         }
/*     */         break;
/*     */       
/*     */       case 9:
/*     */       case 10:
/* 147 */         if (e.c(style) != null) {
/* 148 */           return (ad)t.q;
/*     */         }
/*     */         break;
/*     */       
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */         break;
/*     */       default:
/* 158 */         throw new IllegalStateException();
/*     */     } 
/*     */ 
/*     */     
/* 162 */     if (display == 2) {
/* 163 */       parentStyle = style.c();
/* 164 */       if (parentStyle != null && e.a(c.c(parentStyle)) != 
/* 165 */         e.a(c.c(style))) {
/* 166 */         return (ad)t.t;
/*     */       }
/*     */     } 
/*     */     
/* 170 */     return (ad)t; }
/*     */ 
/*     */   
/*     */   public ad b(c style) {
/* 174 */     return (ad)t.s;
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 178 */     return false;
/*     */   }
/*     */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*     */     String ident;
/* 182 */     short luType = lu.getLexicalUnitType();
/* 183 */     switch (luType) {
/*     */       case 35:
/* 185 */         ident = lu.getStringValue().toLowerCase();
/* 186 */         if (ident.equals("none"))
/* 187 */           return (ad)t.q; 
/* 188 */         if (ident.equals("block"))
/* 189 */           return (ad)t.r; 
/* 190 */         if (ident.equals("inline"))
/* 191 */           return (ad)t.s; 
/* 192 */         if (ident.equals("inline-block"))
/* 193 */           return (ad)t.t; 
/* 194 */         if (ident.equals("list-item"))
/* 195 */           return (ad)t.u; 
/* 196 */         if (ident.equals("run-in")) {
/* 197 */           return (ad)t.v;
/*     */         }
/* 199 */         if (ident.equals("table"))
/* 200 */           return (ad)t.w; 
/* 201 */         if (ident.equals("inline-table"))
/* 202 */           return (ad)t.x; 
/* 203 */         if (ident.equals("table-row-group"))
/* 204 */           return (ad)t.y; 
/* 205 */         if (ident.equals("table-column"))
/* 206 */           return (ad)t.z; 
/* 207 */         if (ident.equals("table-column-group"))
/* 208 */           return (ad)t.A; 
/* 209 */         if (ident.equals("table-header-group"))
/* 210 */           return (ad)t.B; 
/* 211 */         if (ident.equals("table-footer-group"))
/* 212 */           return (ad)t.C; 
/* 213 */         if (ident.equals("table-row"))
/* 214 */           return (ad)t.D; 
/* 215 */         if (ident.equals("table-cell"))
/* 216 */           return (ad)t.E; 
/* 217 */         if (ident.equals("table-caption")) {
/* 218 */           return (ad)t.F;
/*     */         }
/*     */         break;
/*     */     } 
/*     */     
/* 223 */     throw new l();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/G.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */