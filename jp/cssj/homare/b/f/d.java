/*    */ package jp.cssj.homare.b.f;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class d
/*    */   implements Serializable
/*    */ {
/*    */   private static final long a = 0L;
/* 14 */   private static final int[] b = new int[0];
/*    */   
/* 16 */   private int[] c = b;
/*    */   
/*    */   private int d;
/*    */   
/* 20 */   private int e = 0;
/*    */   
/*    */   public d() {
/* 23 */     this(0);
/*    */   }
/*    */   
/*    */   public d(int defaultValue) {
/* 27 */     this.d = defaultValue;
/*    */   }
/*    */   
/*    */   public void a(int pos, int value) {
/* 31 */     if (this.e <= pos) {
/* 32 */       this.e = pos + 1;
/* 33 */       if (this.c.length <= pos) {
/* 34 */         int[] array = new int[Math.max(this.e + 10, this.c.length * 3 / 2)];
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
/*    */   public void a(int b) {
/* 46 */     a(this.e, b);
/*    */   }
/*    */   
/*    */   public int[] a() {
/* 50 */     c();
/* 51 */     return this.c;
/*    */   }
/*    */   
/*    */   public int b(int i) {
/* 55 */     if (i >= this.c.length) {
/* 56 */       return this.d;
/*    */     }
/* 58 */     return this.c[i];
/*    */   }
/*    */   
/*    */   public int c(int i) {
/* 62 */     int v = this.c[i];
/* 63 */     this.e--;
/* 64 */     for (int j = i; j < this.e; j++) {
/* 65 */       this.c[j] = this.c[j + 1];
/*    */     }
/* 67 */     return v;
/*    */   }
/*    */   
/*    */   public int b() {
/* 71 */     return this.e;
/*    */   }
/*    */   
/*    */   public void c() {
/* 75 */     if (this.e != this.c.length) {
/* 76 */       int[] array = new int[this.e];
/* 77 */       System.arraycopy(this.c, 0, array, 0, this.e);
/* 78 */       this.c = array;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean d(int v) {
/* 83 */     for (int i = 0; i < this.e; i++) {
/* 84 */       if (this.c[i] == v) {
/* 85 */         return true;
/*    */       }
/*    */     } 
/* 88 */     return false;
/*    */   }
/*    */   
/*    */   public void d() {
/* 92 */     this.e = 0;
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 96 */     return (this.e == 0);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/f/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */