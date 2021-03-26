/*    */ package net.zamasoft.a.a;
/*    */ 
/*    */ class b {
/*  4 */   private int a = 0;
/*    */   private final Number[] b;
/*    */   
/*    */   public b() {
/*  8 */     int size = 48;
/*  9 */     this.b = new Number[size];
/*    */   }
/*    */   
/*    */   public void a(Number value) {
/* 13 */     this.b[this.a] = value;
/* 14 */     this.a++;
/*    */   }
/*    */   
/*    */   public Number a(int ix) {
/* 18 */     if (ix >= this.a) {
/* 19 */       return Integer.valueOf(0);
/*    */     }
/* 21 */     return this.b[ix];
/*    */   }
/*    */   
/*    */   public Number a() {
/* 25 */     if (this.a == 0) {
/* 26 */       return Integer.valueOf(0);
/*    */     }
/* 28 */     return this.b[--this.a];
/*    */   }
/*    */   
/*    */   public void b() {
/* 32 */     this.a = 0;
/*    */   }
/*    */   
/*    */   public void b(int count) {
/* 36 */     this.a -= count;
/* 37 */     System.arraycopy(this.b, count, this.b, 0, this.a);
/*    */   }
/*    */   
/*    */   public int c() {
/* 41 */     return this.a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */