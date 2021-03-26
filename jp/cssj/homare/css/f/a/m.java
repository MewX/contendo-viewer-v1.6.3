/*    */ package jp.cssj.homare.css.f.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class m
/*    */   implements c
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   public static final byte c = 3;
/* 14 */   public static final m d = new m((byte)1);
/*    */   
/* 16 */   public static final m q = new m((byte)2);
/*    */   
/* 18 */   public static final m r = new m((byte)3);
/*    */   
/*    */   private final byte s;
/*    */   
/*    */   private m(byte wordBreak) {
/* 23 */     this.s = wordBreak;
/*    */   }
/*    */   
/*    */   public short a() {
/* 27 */     return 3012;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 31 */     return this.s;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 35 */     switch (this.s) {
/*    */       case 1:
/* 37 */         return "normal";
/*    */       
/*    */       case 2:
/* 40 */         return "break-all";
/*    */       
/*    */       case 3:
/* 43 */         return "keep-all";
/*    */     } 
/*    */     
/* 46 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/a/m.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */