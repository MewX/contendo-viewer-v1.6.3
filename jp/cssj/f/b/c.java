/*    */ package jp.cssj.f.b;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class c
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
/*    */   public c() {
/* 23 */     this(0);
/*    */   }
/*    */   
/*    */   public c(int defaultValue) {
/* 27 */     this.d = defaultValue;
/*    */   }
/*    */   
/*    */   public void a(int value) {
/* 31 */     a(this.e, value);
/*    */   }
/*    */   
/*    */   public void a(int pos, int value) {
/* 35 */     if (this.e <= pos) {
/* 36 */       this.e = pos + 1;
/* 37 */       if (this.c.length <= pos) {
/* 38 */         int[] array = new int[Math.max(this.e + 10, this.c.length * 3 / 2)];
/* 39 */         for (int i = this.c.length; i < array.length; i++) {
/* 40 */           array[i] = this.d;
/*    */         }
/* 42 */         System.arraycopy(this.c, 0, array, 0, this.c.length);
/* 43 */         this.c = array;
/*    */       } 
/*    */     } 
/* 46 */     this.c[pos] = value;
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
/*    */   public int b() {
/* 62 */     return this.e;
/*    */   }
/*    */   
/*    */   public void c() {
/* 66 */     if (this.e != this.c.length) {
/* 67 */       int[] array = new int[this.e];
/* 68 */       System.arraycopy(this.c, 0, array, 0, this.e);
/* 69 */       this.c = array;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 74 */     return (this.e == 0);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/f/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */