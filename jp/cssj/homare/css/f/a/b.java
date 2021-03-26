/*    */ package jp.cssj.homare.css.f.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   implements c
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/* 12 */   public static final b c = new b((byte)1);
/*    */   
/* 14 */   public static final b d = new b((byte)2);
/*    */   
/*    */   private final byte q;
/*    */   
/*    */   private b(byte boxSizing) {
/* 19 */     this.q = boxSizing;
/*    */   }
/*    */   
/*    */   public short a() {
/* 23 */     return 3010;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 27 */     return this.q;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 31 */     switch (this.q) {
/*    */       case 1:
/* 33 */         return "content-box";
/*    */       
/*    */       case 2:
/* 36 */         return "border-box";
/*    */     } 
/*    */     
/* 39 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */