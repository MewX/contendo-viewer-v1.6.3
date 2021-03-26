/*    */ package jp.cssj.sakae.c.a;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class d
/*    */   implements Serializable
/*    */ {
/*    */   private static final long e = -1L;
/*    */   protected final f[] a;
/* 16 */   protected double b = -1.0D, c = -1.0D, d = -1.0D;
/*    */   
/*    */   public d(f[] fontMetricses) {
/* 19 */     this.a = fontMetricses;
/*    */   }
/*    */   
/*    */   protected void a() {
/* 23 */     double ascent = 0.0D, descent = 0.0D, xHeight = 0.0D;
/* 24 */     for (int i = 0; i < this.a.length; i++) {
/* 25 */       f fontMetrics = this.a[i];
/* 26 */       ascent = Math.max(ascent, fontMetrics.c());
/* 27 */       descent = Math.max(descent, fontMetrics.d());
/* 28 */       xHeight = Math.max(xHeight, fontMetrics.f());
/*    */     } 
/* 30 */     this.b = ascent;
/* 31 */     this.c = descent;
/* 32 */     this.d = xHeight;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int b() {
/* 39 */     return this.a.length;
/*    */   }
/*    */   
/*    */   public f a(int i) {
/* 43 */     return this.a[i];
/*    */   }
/*    */   
/*    */   public double c() {
/* 47 */     if (this.b == -1.0D) {
/* 48 */       a();
/*    */     }
/* 50 */     return this.b;
/*    */   }
/*    */   
/*    */   public double d() {
/* 54 */     if (this.c == -1.0D) {
/* 55 */       a();
/*    */     }
/* 57 */     return this.c;
/*    */   }
/*    */   
/*    */   public double e() {
/* 61 */     if (this.d == -1.0D) {
/* 62 */       a();
/*    */     }
/* 64 */     return this.d;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 68 */     StringBuffer buff = new StringBuffer(super.toString());
/* 69 */     buff.append(":[");
/* 70 */     for (int i = 0; i < this.a.length; i++) {
/* 71 */       buff.append(this.a[i]).append(',');
/*    */     }
/* 73 */     buff.append("]");
/* 74 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */