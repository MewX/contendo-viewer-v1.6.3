/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ag
/*    */   implements ad
/*    */ {
/*    */   public static final byte a = 0;
/*    */   public static final byte b = 1;
/*    */   public static final byte c = 2;
/* 14 */   public static final ag d = new ag((byte)0);
/*    */   
/* 16 */   public static final ag e = new ag((byte)1);
/*    */   
/* 18 */   public static final ag f = new ag((byte)2);
/*    */   
/*    */   private final byte g;
/*    */   
/*    */   private ag(byte visibility) {
/* 23 */     this.g = visibility;
/*    */   }
/*    */   
/*    */   public short a() {
/* 27 */     return 1021;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 31 */     return this.g;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 35 */     switch (this.g) {
/*    */       case 0:
/* 37 */         return "visible";
/*    */       
/*    */       case 1:
/* 40 */         return "hidden";
/*    */       
/*    */       case 2:
/* 43 */         return "collapse";
/*    */     } 
/*    */     
/* 46 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/ag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */