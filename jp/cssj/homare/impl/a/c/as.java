/*     */ package jp.cssj.homare.impl.a.c;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.b.a.a.a;
/*     */ import jp.cssj.homare.b.a.a.e;
/*     */ import jp.cssj.homare.b.a.a.j;
/*     */ import jp.cssj.homare.b.a.a.m;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.c.b;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.E;
/*     */ import jp.cssj.homare.css.f.M;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.af;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class as
/*     */   extends b
/*     */ {
/*  30 */   public static final j a = (j)new as();
/*     */   
/*     */   public static m c(c style) {
/*  33 */     ad value = style.a(a);
/*  34 */     switch (value.a()) {
/*     */       case 1000:
/*  36 */         return (m)new a(((a)value).c());
/*     */       
/*     */       case 23:
/*  39 */         return (m)new j(((M)value).c());
/*     */       
/*     */       case 1015:
/*  42 */         return (m)value;
/*     */     } 
/*  44 */     throw new IllegalStateException();
/*     */   }
/*     */   
/*     */   public static byte d(c style) {
/*     */     e va;
/*  49 */     ad value = style.a(a);
/*  50 */     switch (value.a()) {
/*     */       case 1015:
/*  52 */         va = (e)value;
/*  53 */         switch (va.b()) {
/*     */           case 6:
/*  55 */             return 2;
/*     */           case 1:
/*  57 */             return 3;
/*     */           case 7:
/*  59 */             return 4;
/*     */         } 
/*     */       
/*     */       case 23:
/*     */       case 1000:
/*  64 */         return 1;
/*     */     } 
/*  66 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   protected as() {
/*  71 */     super("vertical-align");
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  75 */     return (ad)af.a;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  79 */     return false;
/*     */   }
/*     */   
/*     */   public ad a(ad value, c style) {
/*  83 */     return l.a(value, style);
/*     */   }
/*     */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*     */     String ident;
/*  87 */     short luType = lu.getLexicalUnitType();
/*  88 */     switch (luType) {
/*     */       case 35:
/*  90 */         ident = lu.getStringValue().toLowerCase();
/*  91 */         if (ident.equals("baseline"))
/*  92 */           return (ad)af.a; 
/*  93 */         if (ident.equals("middle"))
/*  94 */           return (ad)af.b; 
/*  95 */         if (ident.equals("sub"))
/*  96 */           return (ad)af.c; 
/*  97 */         if (ident.equals("super"))
/*  98 */           return (ad)af.d; 
/*  99 */         if (ident.equals("text-top"))
/* 100 */           return (ad)af.e; 
/* 101 */         if (ident.equals("text-bottom"))
/* 102 */           return (ad)af.f; 
/* 103 */         if (ident.equals("top"))
/* 104 */           return (ad)af.g; 
/* 105 */         if (ident.equals("bottom")) {
/* 106 */           return (ad)af.h;
/*     */         }
/* 108 */         throw new l();
/*     */     } 
/*     */     
/* 111 */     E e = l.a(ua, lu);
/* 112 */     if (e == null) {
/* 113 */       return (ad)l.d(lu);
/*     */     }
/* 115 */     return (ad)e;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/as.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */