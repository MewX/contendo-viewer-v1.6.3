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
/*    */ public class g
/*    */   extends b
/*    */ {
/* 24 */   public static final b a = new g(); public static short c(c style) { b b4; j info;
/*    */     b b3;
/*    */     b b2;
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
/* 57 */         value = (i)style.a((j)b4);
/* 58 */         return value.b();case 2: switch (c.c(style)) { case 2: case 3: info = n.a; value = (i)style.a(info); return value.b(); }  b3 = a; value = (i)style.a((j)b3); return value.b();case 3: switch (c.c(style)) { case 1: b2 = k.a; value = (i)style.a((j)b2); return value.b(); }  b1 = a; value = (i)style.a((j)b1); return value.b();
/*    */     } 
/*    */     throw new IllegalStateException(); }
/*    */    protected g() {
/* 62 */     super("border-bottom-style");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 66 */     return (ad)i.k;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 70 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 74 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 78 */     i i = a.a(lu);
/* 79 */     if (i == null) {
/* 80 */       throw new l();
/*    */     }
/* 82 */     return (ad)i;
/*    */   }
/*    */   
/*    */   public int a() {
/* 86 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */