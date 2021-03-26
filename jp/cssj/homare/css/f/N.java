/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class N
/*    */   implements ad
/*    */ {
/*    */   public static final byte a = 0;
/*    */   public static final byte b = 1;
/*    */   public static final byte c = 2;
/*    */   public static final byte d = 3;
/*    */   public static final byte e = 4;
/* 18 */   public static final N f = new N((byte)0);
/*    */   
/* 20 */   public static final N g = new N((byte)1);
/*    */   
/* 22 */   public static final N h = new N((byte)2);
/*    */   
/* 24 */   public static final N i = new N((byte)3);
/*    */   
/* 26 */   public static final N j = new N((byte)4);
/*    */   
/*    */   private final byte k;
/*    */   
/*    */   private N(byte position) {
/* 31 */     this.k = position;
/*    */   }
/*    */   
/*    */   public short a() {
/* 35 */     return 1005;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 39 */     return this.k;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 43 */     switch (this.k) {
/*    */       case 0:
/* 45 */         return "static";
/*    */       
/*    */       case 1:
/* 48 */         return "relative";
/*    */       
/*    */       case 2:
/* 51 */         return "absolute";
/*    */       
/*    */       case 3:
/* 54 */         return "fixed";
/*    */       
/*    */       case 4:
/* 57 */         return "-cssj-current-page";
/*    */     } 
/*    */     
/* 60 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/N.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */