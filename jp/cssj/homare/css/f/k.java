/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class k
/*    */   implements ad
/*    */ {
/*    */   public static final byte a = 0;
/*    */   public static final byte b = 1;
/*    */   public static final byte c = 2;
/*    */   public static final byte d = 3;
/*    */   public static final byte e = 4;
/* 18 */   public static final k f = new k((byte)0);
/*    */   
/* 20 */   public static final k g = new k((byte)1);
/*    */   
/* 22 */   public static final k h = new k((byte)2);
/*    */   
/* 24 */   public static final k i = new k((byte)3);
/*    */   
/* 26 */   public static final k j = new k((byte)4);
/*    */   
/*    */   private final byte k;
/*    */   
/*    */   private k(byte floating) {
/* 31 */     this.k = floating;
/*    */   }
/*    */   
/*    */   public short a() {
/* 35 */     return 1012;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 39 */     return this.k;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 43 */     switch (this.k) {
/*    */       case 0:
/* 45 */         return "none";
/*    */       
/*    */       case 1:
/* 48 */         return "left";
/*    */       
/*    */       case 2:
/* 51 */         return "right";
/*    */       
/*    */       case 3:
/* 54 */         return "start";
/*    */       
/*    */       case 4:
/* 57 */         return "end";
/*    */     } 
/*    */     
/* 60 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */