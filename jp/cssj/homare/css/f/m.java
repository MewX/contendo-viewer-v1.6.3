/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class m
/*    */   implements ad
/*    */ {
/* 10 */   public static final m a = new m((byte)0);
/*    */   
/* 12 */   public static final m b = new m((byte)1);
/*    */   
/* 14 */   public static final m c = new m((byte)2);
/*    */   
/* 16 */   public static final m d = new m((byte)1);
/*    */   
/* 18 */   public static final m e = new m((byte)2);
/*    */   
/* 20 */   public static final m f = new m((byte)3);
/*    */   
/*    */   private final byte g;
/*    */   
/*    */   private m(byte clear) {
/* 25 */     this.g = clear;
/*    */   }
/*    */   
/*    */   public short a() {
/* 29 */     return 1013;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 33 */     return this.g;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 37 */     switch (this.g) {
/*    */       case 0:
/* 39 */         return "none";
/*    */       
/*    */       case 1:
/* 42 */         return "left";
/*    */       
/*    */       case 2:
/* 45 */         return "right";
/*    */       
/*    */       case 3:
/* 48 */         return "both";
/*    */     } 
/*    */     
/* 51 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/m.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */