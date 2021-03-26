/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class J
/*    */   implements ad
/*    */ {
/* 10 */   public static final J a = new J((byte)0);
/*    */   
/* 12 */   public static final J b = new J((byte)1);
/*    */   
/* 14 */   public static final J c = new J((byte)2);
/*    */   
/* 16 */   public static final J d = new J((byte)3);
/*    */   
/*    */   private final byte e;
/*    */   
/*    */   private J(byte overflow) {
/* 21 */     this.e = overflow;
/*    */   }
/*    */   
/*    */   public short a() {
/* 25 */     return 1022;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 29 */     return this.e;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 33 */     switch (this.e) {
/*    */       case 0:
/* 35 */         return "visible";
/*    */       
/*    */       case 1:
/* 38 */         return "hidden";
/*    */       
/*    */       case 2:
/* 41 */         return "scroll";
/*    */       
/*    */       case 3:
/* 44 */         return "auto";
/*    */     } 
/*    */     
/* 47 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/J.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */