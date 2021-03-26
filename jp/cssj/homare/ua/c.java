/*    */ package jp.cssj.homare.ua;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */ {
/*    */   public final String a;
/* 11 */   public int b = 0;
/*    */   
/*    */   public c(String name) {
/* 14 */     this.a = name;
/*    */   }
/*    */   
/*    */   public c(String name, int value) {
/* 18 */     this(name);
/* 19 */     this.b = value;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 23 */     return this.a + ":" + this.b;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */