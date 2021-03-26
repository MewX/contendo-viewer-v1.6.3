/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.e.a;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.css.f.ad;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class l
/*    */   extends b
/*    */ {
/* 29 */   public static final j a = (j)new l();
/*    */ 
/*    */   
/*    */   public static double c(c style) {
/*    */     j info;
/* 34 */     switch (b.c(style)) {
/*    */       case 1:
/* 36 */         info = a;
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
/* 64 */         return ((a)style.a(info)).c();case 2: switch (c.c(style)) { case 2: info = h.a; return ((a)style.a(info)).c();case 3: info = s.a; return ((a)style.a(info)).c(); }  info = a; return ((a)style.a(info)).c();case 3: switch (c.c(style)) { case 1: info = s.a; return ((a)style.a(info)).c(); }  info = a; return ((a)style.a(info)).c();
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   } protected l() {
/* 68 */     super("border-left-width");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 72 */     return (ad)style.b().c((byte)2);
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 76 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 80 */     return jp.cssj.homare.css.e.l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws jp.cssj.homare.css.c.l {
/* 84 */     E value = a.a(ua, lu);
/* 85 */     if (value == null) {
/* 86 */       throw new jp.cssj.homare.css.c.l();
/*    */     }
/* 88 */     return (ad)value;
/*    */   }
/*    */   
/*    */   public int a() {
/* 92 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */