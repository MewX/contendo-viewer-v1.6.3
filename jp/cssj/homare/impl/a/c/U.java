/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.b;
/*    */ import jp.cssj.homare.css.e.l;
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
/*    */ public class U
/*    */   extends b
/*    */ {
/* 25 */   public static final j a = (j)new U();
/*    */ 
/*    */   
/*    */   public static ad c(c style) {
/*    */     j info;
/* 30 */     switch (b.c(style)) {
/*    */       case 1:
/* 32 */         info = a;
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
/* 60 */         return style.a(info);case 2: switch (c.c(style)) { case 2: info = V.a; return style.a(info);case 3: info = S.a; return style.a(info); }  info = a; return style.a(info);case 3: switch (c.c(style)) { case 1: info = S.a; return style.a(info); }  info = a; return style.a(info);
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   } protected U() {
/* 64 */     super("margin-right");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 68 */     return (ad)a.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 72 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 76 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 80 */     ad value = b.a(ua, lu);
/* 81 */     if (value != null) {
/* 82 */       return value;
/*    */     }
/* 84 */     throw new l();
/*    */   }
/*    */   
/*    */   public int a() {
/* 88 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/U.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */