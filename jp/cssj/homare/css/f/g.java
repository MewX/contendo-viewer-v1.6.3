/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class g
/*    */   implements ad
/*    */ {
/* 10 */   public static final g a = new g((byte)1);
/*    */   
/* 12 */   public static final g b = new g((byte)2);
/*    */   
/* 14 */   public static final g c = new g((byte)3);
/*    */   
/*    */   private final byte d;
/*    */   
/*    */   private g(byte blockProgresion) {
/* 19 */     this.d = blockProgresion;
/*    */   }
/*    */   
/*    */   public short a() {
/* 23 */     return 1036;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 27 */     return this.d;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 31 */     switch (this.d) {
/*    */       case 1:
/* 33 */         return "tb";
/*    */       
/*    */       case 2:
/* 36 */         return "rl";
/*    */       
/*    */       case 3:
/* 39 */         return "lr";
/*    */     } 
/*    */     
/* 42 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */