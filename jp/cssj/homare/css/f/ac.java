/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ac
/*    */   implements ad
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   public static final byte c = 3;
/* 14 */   public static final ac d = new ac((byte)1);
/*    */   
/* 16 */   public static final ac e = new ac((byte)2);
/*    */   
/* 18 */   public static final ac f = new ac((byte)3);
/*    */   
/*    */   private final byte g;
/*    */   
/*    */   private ac(byte unicodeBidi) {
/* 23 */     this.g = unicodeBidi;
/*    */   }
/*    */   
/*    */   public short a() {
/* 27 */     return 1050;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 31 */     return this.g;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 35 */     switch (this.g) {
/*    */       case 1:
/* 37 */         return "normal";
/*    */       
/*    */       case 2:
/* 40 */         return "embed";
/*    */       
/*    */       case 3:
/* 43 */         return "bidi-override";
/*    */     } 
/*    */     
/* 46 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/ac.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */