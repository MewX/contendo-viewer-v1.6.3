/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class v
/*    */   implements ad
/*    */ {
/* 10 */   public static final v a = new v((byte)2);
/*    */   
/* 12 */   public static final v b = new v((byte)1);
/*    */   
/*    */   private final byte c;
/*    */   
/*    */   private v(byte emptyCells) {
/* 17 */     this.c = emptyCells;
/*    */   }
/*    */   
/*    */   public short a() {
/* 21 */     return 1026;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 25 */     return this.c;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 29 */     switch (this.c) {
/*    */       case 2:
/* 31 */         return "show";
/*    */       
/*    */       case 1:
/* 34 */         return "hide";
/*    */     } 
/*    */     
/* 37 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/v.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */