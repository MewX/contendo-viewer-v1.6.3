/*    */ package jp.cssj.homare.impl.a.c.a;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.b.a.c.A;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.a;
/*    */ import jp.cssj.homare.css.e.l;
/*    */ import jp.cssj.homare.css.f.E;
/*    */ import jp.cssj.homare.css.f.a;
/*    */ import jp.cssj.homare.css.f.a.a;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.impl.a.c.b.b;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class e
/*    */   extends b
/*    */ {
/* 26 */   public static final j a = (j)new e();
/*    */   
/*    */   public static A.a c(c style) {
/*    */     j info;
/*    */     a r;
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
/*    */ 
/*    */         
/* 61 */         r = (a)style.a(info);
/* 62 */         return A.a.a(((a)r.b).c(), ((a)r.c).c());case 2: switch (c.c(style)) { case 2: info = h.a; r = (a)style.a(info); return A.a.a(((a)r.b).c(), ((a)r.c).c());case 3: info = g.a; r = (a)style.a(info); return A.a.a(((a)r.b).c(), ((a)r.c).c()); }  info = a; r = (a)style.a(info); return A.a.a(((a)r.b).c(), ((a)r.c).c());case 3: switch (c.c(style)) { case 1: info = d.a; r = (a)style.a(info); return A.a.a(((a)r.b).c(), ((a)r.c).c()); }  info = a; r = (a)style.a(info); return A.a.a(((a)r.b).c(), ((a)r.c).c());
/*    */     } 
/*    */     throw new IllegalStateException();
/*    */   } protected e() {
/* 66 */     super("border-bottom-right-radius");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 70 */     return (ad)a.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 74 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 78 */     a r = (a)value;
/* 79 */     a hr = (a)l.a((ad)r.b, style);
/* 80 */     a vr = (a)l.a((ad)r.c, style);
/* 81 */     return (ad)a.a((E)hr, (E)vr);
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 85 */     a value = a.b(ua, lu);
/* 86 */     if (value == null) {
/* 87 */       throw new l();
/*    */     }
/* 89 */     return (ad)value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */