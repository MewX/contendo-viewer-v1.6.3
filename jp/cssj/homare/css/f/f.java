/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   implements ad
/*    */ {
/* 12 */   public static final f a = new f((byte)0);
/*    */   
/* 14 */   public static final f b = new f((byte)1);
/*    */   
/* 16 */   public static final f c = new f((byte)2);
/*    */   
/* 18 */   public static final f d = new f((byte)3);
/*    */   
/*    */   private final byte e;
/*    */   
/*    */   private f(byte backgroundRepeat) {
/* 23 */     this.e = backgroundRepeat;
/*    */   }
/*    */   
/*    */   public short a() {
/* 27 */     return 1027;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 31 */     return this.e;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 35 */     switch (this.e) {
/*    */       case 0:
/* 37 */         return "no-repeat";
/*    */       
/*    */       case 1:
/* 40 */         return "repeat-x";
/*    */       
/*    */       case 2:
/* 43 */         return "repeat-y";
/*    */       
/*    */       case 3:
/* 46 */         return "repeat";
/*    */     } 
/*    */     
/* 49 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */