/*    */ package jp.cssj.homare.css.f.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class h
/*    */   implements j
/*    */ {
/*    */   public static final byte a = 0;
/*    */   public static final byte b = 1;
/*    */   public static final byte c = 2;
/*    */   public static final byte d = 3;
/* 16 */   public static final h e = new h((byte)0);
/*    */   
/* 18 */   public static final h f = new h((byte)1);
/*    */   
/* 20 */   public static final h g = new h((byte)2);
/*    */   
/* 22 */   public static final h h = new h((byte)3);
/*    */   
/*    */   private final byte r;
/*    */   
/*    */   private h(byte ruby) {
/* 27 */     this.r = ruby;
/*    */   }
/*    */   
/*    */   public short a() {
/* 31 */     return 2006;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 35 */     return this.r;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 39 */     switch (this.r) {
/*    */       case 0:
/* 41 */         return "none";
/*    */       case 1:
/* 43 */         return "ruby";
/*    */       case 2:
/* 45 */         return "rb";
/*    */       case 3:
/* 47 */         return "rt";
/*    */     } 
/* 49 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/b/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */