/*    */ package jp.cssj.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class b
/*    */ {
/* 10 */   private int a = 0;
/*    */   
/* 12 */   private short[] b = new short[10];
/*    */   
/*    */   public void a(short code) {
/* 15 */     if (this.a == this.b.length) {
/* 16 */       short[] newarray = new short[this.a + 10];
/* 17 */       System.arraycopy(this.b, 0, newarray, 0, this.a);
/* 18 */       this.b = newarray;
/*    */     } 
/* 20 */     this.b[this.a++] = code;
/*    */   }
/*    */   
/*    */   public short a() {
/* 24 */     return this.b[this.a - 1];
/*    */   }
/*    */   
/*    */   public boolean b(short code) {
/* 28 */     for (int i = 0; i < this.a; i++) {
/* 29 */       if (this.b[i] == code) {
/* 30 */         return true;
/*    */       }
/*    */     } 
/* 33 */     return false;
/*    */   }
/*    */   
/*    */   public short b() {
/* 37 */     return this.b[--this.a];
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 41 */     return (this.a == 0);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */