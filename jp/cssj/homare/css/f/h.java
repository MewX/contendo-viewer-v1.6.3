/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class h
/*    */   implements ad
/*    */ {
/* 10 */   public static final h a = new h((byte)0);
/*    */   
/* 12 */   public static final h b = new h((byte)1);
/*    */   
/*    */   private final byte c;
/*    */   
/*    */   private h(byte borderCollapse) {
/* 17 */     this.c = borderCollapse;
/*    */   }
/*    */   
/*    */   public short a() {
/* 21 */     return 1025;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 25 */     return this.c;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 29 */     switch (this.c) {
/*    */       case 1:
/* 31 */         return "collapse";
/*    */       
/*    */       case 0:
/* 34 */         return "separate";
/*    */     } 
/*    */     
/* 37 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */