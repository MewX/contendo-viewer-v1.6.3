/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class z
/*    */   implements ad
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/* 12 */   public static final z c = new z((byte)1);
/*    */   
/* 14 */   public static final z d = new z((byte)2);
/*    */   
/*    */   private final byte e;
/*    */   
/*    */   private z(byte fontVariant) {
/* 19 */     this.e = fontVariant;
/*    */   }
/*    */   
/*    */   public short a() {
/* 23 */     return 1031;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte b() {
/* 32 */     return this.e;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 36 */     switch (this.e) {
/*    */       case 1:
/* 38 */         return "normal";
/*    */       
/*    */       case 2:
/* 41 */         return "small-caps";
/*    */     } 
/*    */     
/* 44 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/z.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */