/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class P
/*    */   implements ad
/*    */ {
/*    */   public static final short a = 0;
/*    */   public static final short b = 1;
/*    */   public static final short c = 2;
/*    */   public static final short d = 3;
/* 16 */   public static final P e = new P((short)0);
/*    */   
/* 18 */   public static final P f = new P((short)1);
/*    */   
/* 20 */   public static final P g = new P((short)2);
/*    */   
/* 22 */   public static final P h = new P((short)3);
/*    */   
/*    */   private final short i;
/*    */   
/*    */   private P(short quote) {
/* 27 */     this.i = quote;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short a() {
/* 36 */     return 1040;
/*    */   }
/*    */   
/*    */   public short b() {
/* 40 */     return this.i;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 44 */     switch (this.i) {
/*    */       case 0:
/* 46 */         return "open-quote";
/*    */       
/*    */       case 1:
/* 49 */         return "close-quote";
/*    */       
/*    */       case 2:
/* 52 */         return "no-open-quote";
/*    */       
/*    */       case 3:
/* 55 */         return "no-close-quote";
/*    */     } 
/*    */     
/* 58 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/P.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */