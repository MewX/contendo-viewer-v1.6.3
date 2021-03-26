/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class K
/*    */   implements ad
/*    */ {
/* 10 */   public static final K a = new K((byte)0);
/*    */   
/* 12 */   public static final K b = new K((byte)1);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final byte c;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private K(byte pageBreakInside) {
/* 25 */     this.c = pageBreakInside;
/*    */   }
/*    */   
/*    */   public short a() {
/* 29 */     return 1048;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 33 */     return this.c;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 37 */     switch (this.c) {
/*    */       case 0:
/* 39 */         return "auto";
/*    */       
/*    */       case 1:
/* 42 */         return "avoid";
/*    */     } 
/*    */     
/* 45 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/K.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */