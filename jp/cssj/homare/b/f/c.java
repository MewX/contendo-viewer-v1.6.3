/*    */ package jp.cssj.homare.b.f;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   implements Serializable
/*    */ {
/*    */   private static final long a = 0L;
/* 14 */   private static final double[] b = new double[0];
/*    */   
/* 16 */   private double[] c = b;
/*    */   
/*    */   private double d;
/*    */   
/* 20 */   private int e = 0;
/*    */   
/*    */   public c() {
/* 23 */     this(0.0D);
/*    */   }
/*    */   
/*    */   public c(double defaultValue) {
/* 27 */     this.d = defaultValue;
/*    */   }
/*    */   
/*    */   public void a(int pos, double value) {
/* 31 */     if (this.e <= pos) {
/* 32 */       this.e = pos + 1;
/* 33 */       if (this.c.length <= pos) {
/* 34 */         double[] array = new double[Math.max(this.e + 10, this.c.length * 3 / 2)];
/* 35 */         for (int i = this.c.length; i < array.length; i++) {
/* 36 */           array[i] = this.d;
/*    */         }
/* 38 */         System.arraycopy(this.c, 0, array, 0, this.c.length);
/* 39 */         this.c = array;
/*    */       } 
/*    */     } 
/* 42 */     this.c[pos] = value;
/*    */   }
/*    */   
/*    */   public void a(double b) {
/* 46 */     a(this.e, b);
/*    */   }
/*    */   
/*    */   public void b(int i, double b) {
/* 50 */     a(b);
/* 51 */     for (int j = this.e - 1; j > i; j--) {
/* 52 */       this.c[j] = this.c[j - 1];
/*    */     }
/* 54 */     this.c[i] = b;
/*    */   }
/*    */   
/*    */   public double[] a() {
/* 58 */     c();
/* 59 */     return this.c;
/*    */   }
/*    */   
/*    */   public double a(int i) {
/* 63 */     if (i >= this.c.length) {
/* 64 */       return this.d;
/*    */     }
/* 66 */     return this.c[i];
/*    */   }
/*    */   
/*    */   public double b(int i) {
/* 70 */     double v = this.c[i];
/* 71 */     this.e--;
/* 72 */     for (int j = i; j < this.e; j++) {
/* 73 */       this.c[j] = this.c[j + 1];
/*    */     }
/* 75 */     return v;
/*    */   }
/*    */   
/*    */   public int b() {
/* 79 */     return this.e;
/*    */   }
/*    */   
/*    */   public void c() {
/* 83 */     if (this.e != this.c.length) {
/* 84 */       double[] array = new double[this.e];
/* 85 */       System.arraycopy(this.c, 0, array, 0, this.e);
/* 86 */       this.c = array;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 91 */     return (this.e == 0);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/f/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */