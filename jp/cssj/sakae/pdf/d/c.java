/*    */ package jp.cssj.sakae.pdf.d;
/*    */ 
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   implements b
/*    */ {
/*    */   private final String a;
/*    */   private final double b;
/*    */   private final double c;
/*    */   
/*    */   public c(String name, double width, double height) {
/* 20 */     this.a = name;
/* 21 */     this.b = width;
/* 22 */     this.c = height;
/*    */   }
/*    */   
/*    */   public void a(b _gc) throws jp.cssj.sakae.c.c {
/* 26 */     a gc = (a)_gc;
/* 27 */     gc.a(this.a, this.b, this.c);
/*    */   }
/*    */   
/*    */   public double a() {
/* 31 */     return this.b;
/*    */   }
/*    */   
/*    */   public double b() {
/* 35 */     return this.c;
/*    */   }
/*    */   
/*    */   public String c() {
/* 39 */     return null;
/*    */   }
/*    */   
/*    */   public String d() {
/* 43 */     return this.a;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 47 */     return this.a;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 51 */     if (o instanceof c) {
/* 52 */       return ((c)o).a.equals(this.a);
/*    */     }
/* 54 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 58 */     return this.a.hashCode();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/d/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */