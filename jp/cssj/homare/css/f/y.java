/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class y
/*    */   implements ad
/*    */ {
/* 10 */   public static final y a = new y((byte)1);
/*    */   
/* 12 */   public static final y b = new y((byte)2);
/*    */   
/* 14 */   public static final y c = new y((byte)3);
/*    */   
/*    */   private final byte d;
/*    */   
/*    */   private y(byte fontStyle) {
/* 19 */     this.d = fontStyle;
/*    */   }
/*    */   
/*    */   public short a() {
/* 23 */     return 1030;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte b() {
/* 32 */     return this.d;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 36 */     switch (this.d) {
/*    */       case 1:
/* 38 */         return "normal";
/*    */       
/*    */       case 2:
/* 41 */         return "italic";
/*    */       
/*    */       case 3:
/* 44 */         return "oblique";
/*    */     } 
/*    */     
/* 47 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/y.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */