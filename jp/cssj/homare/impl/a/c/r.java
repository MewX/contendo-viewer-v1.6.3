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
/*    */ public class r
/*    */   extends b
/*    */ {
/* 24 */   public static final j a = (j)new r(); public static short c(c style) { j j1;
/*    */     b b1;
/*    */     j info;
/*    */     i value;
/* 28 */     switch (b.c(style)) {
/*    */       case 1:
/* 30 */         j1 = a;
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
/* 56 */         value = (i)style.a(j1);
/* 57 */         return value.b();case 2: switch (c.c(style)) { case 2: case 3: b1 = k.a; value = (i)style.a((j)b1); return value.b(); }  info = a; value = (i)style.a(info); return value.b();case 3: switch (c.c(style)) { case 1: info = n.a; value = (i)style.a(info); return value.b(); }  info = a; value = (i)style.a(info); return value.b();
/*    */     } 
/*    */     throw new IllegalStateException(); }
/*    */    protected r() {
/* 61 */     super("border-top-style");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 65 */     return (ad)i.k;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 69 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 73 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 77 */     i i = a.a(lu);
/* 78 */     if (i == null) {
/* 79 */       throw new l();
/*    */     }
/* 81 */     return (ad)i;
/*    */   }
/*    */   
/*    */   public int a() {
/* 85 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/r.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */