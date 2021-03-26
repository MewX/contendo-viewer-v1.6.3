/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.b;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.O;
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
/*    */ public class ac
/*    */   extends b
/*    */ {
/* 25 */   public static final j a = (j)new ac();
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
/* 58 */         return style.a(info);case 2: switch (c.c(style)) { case 2: case 3: info = ae.a; return style.a(info); }  info = a; return style.a(info);case 3: switch (c.c(style)) { case 1: info = ad.a; return style.a(info); }  info = a; return style.a(info);
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   } protected ac() {
/* 62 */     super("padding-bottom");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 66 */     return (ad)a.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 70 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 74 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 78 */     O o = b.c(ua, lu);
/* 79 */     if (o == null) {
/* 80 */       throw new l();
/*    */     }
/* 82 */     return (ad)o;
/*    */   }
/*    */   
/*    */   public int a() {
/* 86 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/ac.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */