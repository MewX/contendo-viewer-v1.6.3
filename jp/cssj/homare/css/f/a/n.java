/*    */ package jp.cssj.homare.css.f.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class n
/*    */   implements c
/*    */ {
/* 10 */   public static final n a = new n((byte)1);
/*    */   
/* 12 */   public static final n b = new n((byte)2);
/*    */   
/*    */   private final byte c;
/*    */   
/*    */   private n(byte wordWrap) {
/* 17 */     this.c = wordWrap;
/*    */   }
/*    */   
/*    */   public short a() {
/* 21 */     return 3011;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 25 */     return this.c;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 29 */     switch (this.c) {
/*    */       case 1:
/* 31 */         return "normal";
/*    */       
/*    */       case 2:
/* 34 */         return "break-word";
/*    */     } 
/*    */     
/* 37 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/a/n.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */