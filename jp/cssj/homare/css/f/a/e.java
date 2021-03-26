/*    */ package jp.cssj.homare.css.f.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class e
/*    */   implements c
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/* 12 */   public static final e c = new e((byte)1);
/*    */   
/* 14 */   public static final e d = new e((byte)2);
/*    */   
/*    */   private final byte q;
/*    */   
/*    */   private e(byte textAlign) {
/* 19 */     this.q = textAlign;
/*    */   }
/*    */   
/*    */   public short a() {
/* 23 */     return 3003;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 27 */     return this.q;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 31 */     switch (this.q) {
/*    */       case 1:
/* 33 */         return "auto";
/*    */       
/*    */       case 2:
/* 36 */         return "balance";
/*    */     } 
/*    */     
/* 39 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */