/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.b.a.c.u;
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
/*    */ import jp.cssj.homare.impl.a.c.c.e;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Z
/*    */   extends b
/*    */ {
/* 27 */   public static final j a = (j)new Z();
/*    */   
/*    */   public static ad c(c style) {
/*    */     j info;
/* 31 */     if (e.c(style) != null)
/*    */     
/* 33 */     { info = a; }
/*    */     else
/*    */     
/* 36 */     { switch (b.c(style))
/*    */       { case 1:
/* 38 */           info = a;
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
/* 65 */           return style.a(info);case 2: switch (c.c(style)) { case 2: case 3: info = Y.a; return style.a(info); }  info = a; return style.a(info);case 3: switch (c.c(style)) { case 1: info = Y.a; return style.a(info); }  info = a; return style.a(info); }  throw new IllegalStateException(); }  return style.a(info);
/*    */   }
/*    */   
/*    */   public static u d(c style) {
/* 69 */     return b.a(c(style));
/*    */   }
/*    */   
/*    */   private Z() {
/* 73 */     super("min-width");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 77 */     return (ad)a.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 81 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 85 */     return l.a(value, style);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 89 */     O o = b.c(ua, lu);
/* 90 */     if (o == null) {
/* 91 */       throw new l();
/*    */     }
/* 93 */     return (ad)o;
/*    */   }
/*    */   
/*    */   public int a() {
/* 97 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/Z.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */