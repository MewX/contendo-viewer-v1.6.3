/*    */ package jp.cssj.homare.ua;
/*    */ 
/*    */ 
/*    */ public class a
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long c = 0L;
/*    */   private final byte d;
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   
/*    */   public a(byte state) {
/* 13 */     this.d = state;
/*    */   }
/*    */   
/*    */   public a() {
/* 17 */     this((byte)1);
/*    */   }
/*    */   
/*    */   public byte a() {
/* 21 */     return this.d;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */