/*    */ package jp.cssj.homare.css.f.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   implements c
/*    */ {
/*    */   public static final byte a = -1;
/* 10 */   public static final f b = new f((byte)1);
/*    */   
/* 12 */   public static final f c = new f((byte)-1);
/*    */   
/*    */   private final byte d;
/*    */   
/*    */   private f(byte textAlign) {
/* 17 */     this.d = textAlign;
/*    */   }
/*    */   
/*    */   public short a() {
/* 21 */     return 3004;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 25 */     return this.d;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 29 */     switch (this.d) {
/*    */       case -1:
/* 31 */         return "all";
/*    */     } 
/*    */     
/* 34 */     return String.valueOf(this.d);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/a/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */