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
/*    */ public class ae
/*    */   extends b
/*    */ {
/* 25 */   public static final j a = (j)new ae();
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
/* 60 */         return style.a(info);case 2: switch (c.c(style)) { case 2: info = af.a; return style.a(info);case 3: info = ac.a; return style.a(info); }  info = a; return style.a(info);case 3: switch (c.c(style)) { case 1: info = ac.a; return style.a(info); }  info = a; return style.a(info);
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   } protected ae() {
/* 64 */     super("padding-right");
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
/* 80 */     O o = b.c(ua, lu);
/* 81 */     if (o == null) {
/* 82 */       throw new l();
/*    */     }
/* 84 */     return (ad)o;
/*    */   }
/*    */   
/*    */   public int a() {
/* 88 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/ae.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */