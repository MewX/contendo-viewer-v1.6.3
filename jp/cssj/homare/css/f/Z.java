/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Z
/*    */   implements ad
/*    */ {
/* 10 */   public static final Z a = new Z((byte)0);
/*    */   
/* 12 */   public static final Z b = new Z((byte)1);
/*    */ 
/*    */   
/* 15 */   public static final Z c = new Z((byte)2);
/*    */ 
/*    */   
/* 18 */   public static final Z d = new Z((byte)3);
/*    */   
/*    */   private final byte e;
/*    */ 
/*    */   
/*    */   private Z(byte textTransform) {
/* 24 */     this.e = textTransform;
/*    */   }
/*    */   
/*    */   public short a() {
/* 28 */     return 1035;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 32 */     return this.e;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 36 */     switch (this.e) {
/*    */       case 0:
/* 38 */         return "none";
/*    */       
/*    */       case 1:
/* 41 */         return "capitalize";
/*    */       
/*    */       case 2:
/* 44 */         return "uppercase";
/*    */       
/*    */       case 3:
/* 47 */         return "lowercase";
/*    */     } 
/*    */     
/* 50 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/Z.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */