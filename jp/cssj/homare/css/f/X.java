/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class X
/*    */   implements ad
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   public static final byte c = 3;
/*    */   public static final byte d = 4;
/*    */   public static final byte e = 5;
/*    */   public static final byte f = 6;
/*    */   public static final byte g = 101;
/* 22 */   public static final X h = new X((byte)1);
/*    */   
/* 24 */   public static final X i = new X((byte)2);
/*    */   
/* 26 */   public static final X j = new X((byte)3);
/*    */   
/* 28 */   public static final X k = new X((byte)4);
/* 29 */   public static final X l = new X((byte)5);
/*    */   
/* 31 */   public static final X m = new X((byte)6);
/*    */   
/* 33 */   public static final X n = new X((byte)101);
/*    */   
/*    */   private final byte o;
/*    */   
/*    */   private X(byte textAlign) {
/* 38 */     this.o = textAlign;
/*    */   }
/*    */   
/*    */   public short a() {
/* 42 */     return 1033;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 46 */     return this.o;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 50 */     switch (this.o) {
/*    */       case 1:
/* 52 */         return "left";
/*    */       
/*    */       case 3:
/* 55 */         return "center";
/*    */       
/*    */       case 2:
/* 58 */         return "right";
/*    */       
/*    */       case 4:
/* 61 */         return "justify";
/*    */       
/*    */       case 5:
/* 64 */         return "start";
/*    */       
/*    */       case 6:
/* 67 */         return "end";
/*    */       
/*    */       case 101:
/* 70 */         return "-cssj-justify-center";
/*    */     } 
/*    */     
/* 73 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/X.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */