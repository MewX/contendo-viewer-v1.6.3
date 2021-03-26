/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class s
/*    */   implements ad
/*    */ {
/* 10 */   public static final s a = new s((byte)1);
/*    */   
/* 12 */   public static final s b = new s((byte)2);
/*    */   
/*    */   private final byte c;
/*    */   
/*    */   private s(byte direction) {
/* 17 */     this.c = direction;
/*    */   }
/*    */   
/*    */   public short a() {
/* 21 */     return 1049;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 25 */     return this.c;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 29 */     switch (this.c) {
/*    */       case 1:
/* 31 */         return "ltr";
/*    */       
/*    */       case 2:
/* 34 */         return "rtl";
/*    */     } 
/*    */     
/* 37 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/s.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */