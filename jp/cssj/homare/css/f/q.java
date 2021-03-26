/*    */ package jp.cssj.homare.css.f;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class q
/*    */   implements ad
/*    */ {
/*    */   private final String a;
/*    */   private final String b;
/*    */   private final G c;
/*    */   
/*    */   public q(String name, String delimiter, G style) {
/* 13 */     this.a = name;
/* 14 */     this.b = delimiter;
/* 15 */     this.c = style;
/*    */   }
/*    */   
/*    */   public q(String name, String delimiter) {
/* 19 */     this(name, delimiter, G.B);
/*    */   }
/*    */   
/*    */   public short a() {
/* 23 */     return 1043;
/*    */   }
/*    */   
/*    */   public String b() {
/* 27 */     return this.a;
/*    */   }
/*    */   
/*    */   public short c() {
/* 31 */     return this.c.b();
/*    */   }
/*    */   
/*    */   public String d() {
/* 35 */     return this.b;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 39 */     return "counters(" + this.a + ",'" + this.b + "'," + this.c + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/f/q.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */