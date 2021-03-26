/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class F
/*    */   implements ad
/*    */ {
/*    */   public static final byte a = 0;
/*    */   public static final byte b = 1;
/* 12 */   public static final F c = new F((byte)0);
/*    */   
/* 14 */   public static final F d = new F((byte)1);
/*    */   
/*    */   private final byte e;
/*    */   
/*    */   private F(byte listStylePosition) {
/* 19 */     this.e = listStylePosition;
/*    */   }
/*    */   
/*    */   public short a() {
/* 23 */     return 1039;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 27 */     return this.e;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 31 */     switch (this.e) {
/*    */       case 1:
/* 33 */         return "outside";
/*    */       
/*    */       case 0:
/* 36 */         return "inside";
/*    */     } 
/*    */     
/* 39 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/F.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */