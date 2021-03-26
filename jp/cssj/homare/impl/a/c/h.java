/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.a;
/*    */ import jp.cssj.homare.css.e.l;
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
/*    */ public class h
/*    */   extends b
/*    */ {
/* 26 */   public static final j a = (j)new h();
/*    */ 
/*    */   
/*    */   public static double c(c style) {
/*    */     j info;
/* 31 */     switch (b.c(style)) {
/*    */       case 1:
/* 33 */         info = a;
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
/* 59 */         return ((a)style.a(info)).c();case 2: switch (c.c(style)) { case 2: case 3: info = o.a; return ((a)style.a(info)).c(); }  info = a; return ((a)style.a(info)).c();case 3: switch (c.c(style)) { case 1: info = l.a; return ((a)style.a(info)).c(); }  info = a; return ((a)style.a(info)).c();
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   } protected h() {
/* 63 */     super("border-bottom-width");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 67 */     return (ad)style.b().c((byte)2);
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 71 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 75 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 79 */     E value = a.a(ua, lu);
/* 80 */     if (value == null) {
/* 81 */       throw new l();
/*    */     }
/* 83 */     return (ad)value;
/*    */   }
/*    */   
/*    */   public int a() {
/* 87 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */