/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class U
/*    */   implements ad
/*    */ {
/*    */   public static final short a = 1;
/*    */   public static final short b = 2;
/* 12 */   public static final U c = new U((short)1);
/*    */   
/* 14 */   public static final U d = new U((short)2);
/*    */   
/*    */   private final short e;
/*    */   
/*    */   private U(short relativeSize) {
/* 19 */     this.e = relativeSize;
/*    */   }
/*    */   
/*    */   public short a() {
/* 23 */     return 1014;
/*    */   }
/*    */   
/*    */   public short b() {
/* 27 */     return this.e;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/U.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */