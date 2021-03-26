/*     */ package jp.cssj.homare.impl.a.c.b;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.b.f.b;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.c.a;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.f.C;
/*     */ import jp.cssj.homare.css.f.H;
/*     */ import jp.cssj.homare.css.f.V;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.b.f;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */   extends a
/*     */ {
/*  25 */   public static final j a = (j)new e();
/*  26 */   public static final j b = (j)new e();
/*     */   
/*  28 */   private static final j[] c = new j[] { a, b };
/*     */   
/*     */   public static String c(c style) {
/*  31 */     ad value = style.a(a);
/*  32 */     if (value.a() == 1007) {
/*  33 */       return null;
/*     */     }
/*  35 */     return ((V)value).b();
/*     */   }
/*     */   
/*     */   public static byte[] d(c style) {
/*  39 */     ad value = style.a(b);
/*  40 */     if (value.a() == 1007) {
/*  41 */       return null;
/*     */     }
/*  43 */     return ((f)value).b();
/*     */   }
/*     */   
/*     */   private e() {
/*  47 */     super("-cssj-page-content");
/*     */   }
/*     */   
/*     */   public ad a(ad value, c style) {
/*  51 */     return value;
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  55 */     return (ad)H.a;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  59 */     return false;
/*     */   }
/*     */   
/*     */   protected j[] a() {
/*  63 */     return c; } protected jp.cssj.homare.css.c.e.a[] a(LexicalUnit lu, m ua, URI uri) throws l {
/*     */     V v2, v1;
/*     */     f f;
/*     */     String ident;
/*  67 */     if (lu.getLexicalUnitType() == 12) {
/*  68 */       return new jp.cssj.homare.css.c.e.a[] { new jp.cssj.homare.css.c.e.a(a, (ad)C.a), new jp.cssj.homare.css.c.e.a(b, (ad)C.a) };
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  73 */     short luType = lu.getLexicalUnitType();
/*  74 */     switch (luType) {
/*     */       case 35:
/*  76 */         ident = lu.getStringValue().toLowerCase();
/*  77 */         if (ident.equals("none")) {
/*  78 */           H h = H.a; break;
/*     */         } 
/*  80 */         v2 = new V(lu.getStringValue());
/*     */         break;
/*     */ 
/*     */       
/*     */       case 36:
/*  85 */         v1 = new V(lu.getStringValue());
/*     */         break;
/*     */       
/*     */       default:
/*  89 */         throw new l();
/*     */     } 
/*     */ 
/*     */     
/*  93 */     lu = lu.getNextLexicalUnit();
/*  94 */     if (lu == null)
/*  95 */     { H h = H.a; }
/*     */     else
/*  97 */     { b list = new b(); while (true)
/*     */       { String str;
/*  99 */         short s = lu.getLexicalUnitType();
/* 100 */         switch (s) {
/*     */           case 35:
/* 102 */             str = lu.getStringValue().toLowerCase();
/* 103 */             if (str.equals("first")) {
/* 104 */               list.a((byte)1); break;
/*     */             } 
/* 106 */             if (str.equals("right")) {
/* 107 */               list.a((byte)3); break;
/*     */             } 
/* 109 */             if (str.equals("left")) {
/* 110 */               list.a((byte)2); break;
/*     */             } 
/* 112 */             if (str.equals("single")) {
/* 113 */               list.a((byte)0);
/*     */               break;
/*     */             } 
/*     */           
/*     */           default:
/* 118 */             throw new l();
/*     */         } 
/* 120 */         lu = lu.getNextLexicalUnit();
/* 121 */         if (lu == null)
/* 122 */         { f = new f(list.a());
/*     */ 
/*     */           
/* 125 */           return new jp.cssj.homare.css.c.e.a[] { new jp.cssj.homare.css.c.e.a(a, (ad)v1), new jp.cssj.homare.css.c.e.a(b, (ad)f) }; }  }  }  return new jp.cssj.homare.css.c.e.a[] { new jp.cssj.homare.css.c.e.a(a, (ad)v1), new jp.cssj.homare.css.c.e.a(b, (ad)f) };
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/b/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */