/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W
/*    */   implements ad
/*    */ {
/* 10 */   public static final W a = new W((byte)0);
/*    */   
/* 12 */   public static final W b = new W((byte)1);
/*    */   
/*    */   private final byte c;
/*    */   
/*    */   private W(byte tableLayout) {
/* 17 */     this.c = tableLayout;
/*    */   }
/*    */   
/*    */   public short a() {
/* 21 */     return 1024;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 25 */     return this.c;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 29 */     switch (b()) {
/*    */       case 0:
/* 31 */         return "auto";
/*    */       case 1:
/* 33 */         return "fixed";
/*    */     } 
/* 35 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/W.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */