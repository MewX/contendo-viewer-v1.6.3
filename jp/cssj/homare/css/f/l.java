/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class l
/*    */   implements ad
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   public static final byte c = 3;
/*    */   public static final byte d = 4;
/* 13 */   public static final l e = new l((byte)1);
/*    */   
/* 15 */   public static final l f = new l((byte)2);
/*    */   
/* 17 */   public static final l g = new l((byte)3);
/*    */   
/* 19 */   public static final l h = new l((byte)4);
/*    */   
/*    */   private final byte i;
/*    */   
/*    */   private l(byte captionSide) {
/* 24 */     this.i = captionSide;
/*    */   }
/*    */   
/*    */   public short a() {
/* 28 */     return 1023;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 32 */     return this.i;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 36 */     switch (this.i) {
/*    */       case 1:
/* 38 */         return "top";
/*    */       
/*    */       case 2:
/* 41 */         return "bottom";
/*    */       
/*    */       case 3:
/* 44 */         return "before";
/*    */       
/*    */       case 4:
/* 47 */         return "after";
/*    */     } 
/*    */     
/* 50 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */