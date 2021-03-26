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
/*    */ public class q
/*    */   extends b
/*    */ {
/* 26 */   public static final j a = (j)new q();
/*    */   
/*    */   public static b c(c style) {
/*    */     j info;
/* 30 */     switch (b.c(style)) {
/*    */       case 1:
/* 32 */         info = a;
/*    */         break;
/*    */       case 2:
/* 35 */         switch (c.c(style)) {
/*    */           case 2:
/*    */           case 3:
/* 38 */             info = j.a;
/*    */             break;
/*    */         } 
/* 41 */         info = a;
/*    */         break;
/*    */ 
/*    */       
/*    */       case 3:
/* 46 */         switch (c.c(style)) {
/*    */           case 1:
/* 48 */             info = m.a;
/*    */             break;
/*    */         } 
/* 51 */         info = a;
/*    */         break;
/*    */ 
/*    */       
/*    */       default:
/* 56 */         throw new IllegalStateException();
/*    */     } 
/* 58 */     ad value = style.a(info);
/* 59 */     if (value.a() == 1009) {
/* 60 */       return null;
/*    */     }
/* 62 */     return (b)value;
/*    */   }
/*    */   
/*    */   protected q() {
/* 66 */     super("border-top-color");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 70 */     return (ad)r.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 74 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 78 */     if (value == r.a) {
/* 79 */       value = style.a(u.a);
/*    */     }
/* 81 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 85 */     if (c.a(lu)) {
/* 86 */       return (ad)aa.a;
/*    */     }
/* 88 */     n n = c.a(ua, lu);
/* 89 */     if (n == null) {
/* 90 */       throw new l();
/*    */     }
/* 92 */     return (ad)n;
/*    */   }
/*    */   
/*    */   public int a() {
/* 96 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/q.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */