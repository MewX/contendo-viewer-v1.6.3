/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.a;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.i;
/*    */ import jp.cssj.homare.impl.a.c.a.c;
/*    */ import jp.cssj.homare.impl.a.c.b.b;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class k
/*    */   extends b
/*    */ {
/* 24 */   public static final b a = new k(); public static short c(c style) { b b4; b b3; j j1;
/*    */     b b2;
/*    */     j info;
/*    */     b b1;
/*    */     i value;
/* 29 */     switch (b.c(style)) {
/*    */       case 1:
/* 31 */         b4 = a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 59 */         value = (i)style.a((j)b4);
/* 60 */         return value.b();case 2: switch (c.c(style)) { case 2: b3 = g.a; value = (i)style.a((j)b3); return value.b();case 3: j1 = r.a; value = (i)style.a(j1); return value.b(); }  b2 = a; value = (i)style.a((j)b2); return value.b();case 3: switch (c.c(style)) { case 1: info = r.a; value = (i)style.a(info); return value.b(); }  b1 = a; value = (i)style.a((j)b1); return value.b();
/*    */     } 
/*    */     throw new IllegalStateException(); }
/*    */    protected k() {
/* 64 */     super("border-left-style");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 68 */     return (ad)i.k;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 72 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 76 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 80 */     i i = a.a(lu);
/* 81 */     if (i == null) {
/* 82 */       throw new l();
/*    */     }
/* 84 */     return (ad)i;
/*    */   }
/*    */   
/*    */   public int a() {
/* 88 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */