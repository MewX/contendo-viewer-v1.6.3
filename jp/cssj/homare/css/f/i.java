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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class i
/*    */   implements ad
/*    */ {
/*    */   public static final short a = 0;
/*    */   public static final short b = 1;
/*    */   public static final short c = 2;
/*    */   public static final short d = 3;
/*    */   public static final short e = 4;
/*    */   public static final short f = 5;
/*    */   public static final short g = 6;
/*    */   public static final short h = 7;
/*    */   public static final short i = 8;
/*    */   public static final short j = 9;
/* 32 */   public static final i k = new i((short)0);
/*    */   
/* 34 */   public static final i l = new i((short)1);
/*    */   
/* 36 */   public static final i m = new i((short)2);
/*    */   
/* 38 */   public static final i n = new i((short)3);
/*    */   
/* 40 */   public static final i o = new i((short)4);
/*    */   
/* 42 */   public static final i p = new i((short)5);
/*    */   
/* 44 */   public static final i q = new i((short)6);
/*    */   
/* 46 */   public static final i r = new i((short)7);
/*    */   
/* 48 */   public static final i s = new i((short)8);
/*    */   
/* 50 */   public static final i t = new i((short)9);
/*    */   
/*    */   private final short u;
/*    */   
/*    */   private i(short borderStyle) {
/* 55 */     this.u = borderStyle;
/*    */   }
/*    */   
/*    */   public short a() {
/* 59 */     return 1011;
/*    */   }
/*    */   
/*    */   public short b() {
/* 63 */     return this.u;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 67 */     switch (this.u) {
/*    */       case 0:
/* 69 */         return "none";
/*    */       
/*    */       case 1:
/* 72 */         return "hidden";
/*    */       
/*    */       case 5:
/* 75 */         return "dotted";
/*    */       
/*    */       case 4:
/* 78 */         return "dashed";
/*    */       
/*    */       case 3:
/* 81 */         return "solid";
/*    */       
/*    */       case 2:
/* 84 */         return "double";
/*    */       
/*    */       case 8:
/* 87 */         return "groove";
/*    */       
/*    */       case 6:
/* 90 */         return "ridge";
/*    */       
/*    */       case 9:
/* 93 */         return "inset";
/*    */       
/*    */       case 7:
/* 96 */         return "outset";
/*    */     } 
/*    */     
/* 99 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */