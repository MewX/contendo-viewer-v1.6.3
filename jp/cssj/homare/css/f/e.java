/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class e
/*    */   implements ad
/*    */ {
/* 13 */   public static final e a = new e((byte)0);
/*    */ 
/*    */   
/* 16 */   public static final e b = new e((byte)1);
/*    */   
/*    */   private final byte c;
/*    */ 
/*    */   
/*    */   private e(byte backgroundAttachment) {
/* 22 */     this.c = backgroundAttachment;
/*    */   }
/*    */   
/*    */   public short a() {
/* 26 */     return 1028;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 30 */     return this.c;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 34 */     switch (this.c) {
/*    */       case 0:
/* 36 */         return "scroll";
/*    */       
/*    */       case 1:
/* 39 */         return "fixed";
/*    */     } 
/*    */     
/* 42 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */