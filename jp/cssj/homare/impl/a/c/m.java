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
/*    */ import jp.cssj.sakae.c.c.b;
/*    */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class m
/*    */   extends b
/*    */ {
/* 26 */   public static final j a = (j)new m();
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
/* 38 */             info = q.a;
/*    */             break;
/*    */           case 3:
/* 41 */             info = f.a;
/*    */             break;
/*    */         } 
/* 44 */         info = a;
/*    */         break;
/*    */ 
/*    */       
/*    */       case 3:
/* 49 */         switch (c.c(style)) {
/*    */           case 1:
/* 51 */             info = f.a;
/*    */             break;
/*    */         } 
/* 54 */         info = a;
/*    */         break;
/*    */ 
/*    */       
/*    */       default:
/* 59 */         throw new IllegalStateException();
/*    */     } 
/* 61 */     ad value = style.a(info);
/* 62 */     if (value.a() == 1009) {
/* 63 */       return null;
/*    */     }
/* 65 */     return (b)value;
/*    */   }
/*    */   
/*    */   protected m() {
/* 69 */     super("border-right-color");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 73 */     return (ad)r.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 77 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 81 */     if (value == r.a) {
/* 82 */       value = style.a(u.a);
/*    */     }
/* 84 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, jp.cssj.homare.ua.m ua, URI uri) throws l {
/* 88 */     if (c.a(lu)) {
/* 89 */       return (ad)aa.a;
/*    */     }
/* 91 */     n n = c.a(ua, lu);
/* 92 */     if (n == null) {
/* 93 */       throw new l();
/*    */     }
/* 95 */     return (ad)n;
/*    */   }
/*    */   
/*    */   public int a() {
/* 99 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/m.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */