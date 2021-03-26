/*    */ package jp.cssj.homare.impl.a.c;
/*    */ 
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.css.c;
/*    */ import jp.cssj.homare.css.c.b;
/*    */ import jp.cssj.homare.css.c.j;
/*    */ import jp.cssj.homare.css.c.l;
/*    */ import jp.cssj.homare.css.e.c;
/*    */ import jp.cssj.homare.css.f.ad;
/*    */ import jp.cssj.homare.css.f.f;
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
/*    */ public class e
/*    */   extends b
/*    */ {
/* 25 */   public static final j a = (j)new e();
/*    */   
/*    */   public static byte c(c style) {
/* 28 */     byte repeat = ((f)style.a(a)).b();
/* 29 */     switch (b.c(style)) {
/*    */       
/*    */       case 2:
/* 32 */         switch (c.c(style)) {
/*    */           case 2:
/*    */           case 3:
/* 35 */             switch (repeat) {
/*    */               case 1:
/* 37 */                 repeat = 2;
/*    */                 break;
/*    */               case 2:
/* 40 */                 repeat = 1;
/*    */                 break;
/*    */             } 
/*    */             
/*    */             break;
/*    */         } 
/*    */       
/*    */       
/*    */       case 3:
/* 49 */         switch (c.c(style)) {
/*    */           case 1:
/* 51 */             switch (repeat) {
/*    */               case 1:
/* 53 */                 repeat = 2;
/*    */                 break;
/*    */               case 2:
/* 56 */                 repeat = 1;
/*    */                 break;
/*    */             } 
/*    */ 
/*    */             
/*    */             break;
/*    */         } 
/*    */         
/*    */         break;
/*    */     } 
/*    */     
/* 67 */     return repeat;
/*    */   }
/*    */   
/*    */   protected e() {
/* 71 */     super("background-repeat");
/*    */   }
/*    */   
/*    */   public ad b(c style) {
/* 75 */     return (ad)f.d;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 79 */     return false;
/*    */   }
/*    */   
/*    */   public ad a(ad value, c style) {
/* 83 */     return value;
/*    */   }
/*    */   
/*    */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/* 87 */     f f = c.b(lu);
/* 88 */     if (f != null) {
/* 89 */       return (ad)f;
/*    */     }
/* 91 */     throw new l();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */