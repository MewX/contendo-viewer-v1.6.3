/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.homare.css.f.aa;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.n;
/*    */ import jp.cssj.homare.css.f.r;
/*    */ import jp.cssj.homare.impl.a.c.a.c;
/*    */ import jp.cssj.homare.impl.a.c.b.b;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.c.b;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   extends b
/*    */ {
/* 26 */   public static final j a = (j)new f();
/*    */ 
/*    */   
/*    */   public static b c(c style) {
/*    */     j info;
/* 31 */     switch (b.c(style)) {
/*    */       case 1:
/* 33 */         info = a;
/*    */         break;
/*    */       case 2:
/* 36 */         switch (c.c(style)) {
/*    */           case 2:
/*    */           case 3:
/* 39 */             info = m.a;
/*    */             break;
/*    */         } 
/* 42 */         info = a;
/*    */         break;
/*    */ 
/*    */       
/*    */       case 3:
/* 47 */         switch (c.c(style)) {
/*    */           case 1:
/* 49 */             info = j.a;
/*    */             break;
/*    */         } 
/* 52 */         info = a;
/*    */         break;
/*    */ 
/*    */       
/*    */       default:
/* 57 */         throw new IllegalStateException();
/*    */     } 
/* 59 */     ad value = style.a(info);
/* 60 */     if (value.a() == 1009) {
/* 61 */       return null;
/*    */     }
/* 63 */     return (b)value;
/*    */   }
/*    */   
/*    */   protected f() {
/* 67 */     super("border-bottom-color");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 71 */     return (ad)r.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 75 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 79 */     if (value == r.a) {
/* 80 */       value = style.a(u.a);
/*    */     }
/* 82 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 86 */     if (c.a(lu)) {
/* 87 */       return (ad)aa.a;
/*    */     }
/* 89 */     n n = c.a(ua, lu);
/* 90 */     if (n == null) {
/* 91 */       throw new l();
/*    */     }
/* 93 */     return (ad)n;
/*    */   }
/*    */   
/*    */   public int a() {
/* 97 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */