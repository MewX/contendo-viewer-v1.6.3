/*     */ package jp.cssj.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class e
/*     */ {
/*     */   public int a;
/*  99 */   public d[] b = new d[10];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(d info) {
/* 107 */     if (this.a == this.b.length) {
/* 108 */       d[] newarray = new d[this.a + 10];
/* 109 */       System.arraycopy(this.b, 0, newarray, 0, this.a);
/* 110 */       this.b = newarray;
/*     */     } 
/* 112 */     this.b[this.a++] = info;
/*     */   }
/*     */ 
/*     */   
/*     */   public d a() {
/* 117 */     return this.b[this.a - 1];
/*     */   }
/*     */ 
/*     */   
/*     */   public d b() {
/* 122 */     return this.b[--this.a];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 129 */     StringBuffer sb = new StringBuffer("InfoStack(");
/* 130 */     for (int i = this.a - 1; i >= 0; i--) {
/* 131 */       sb.append(this.b[i]);
/* 132 */       if (i != 0)
/* 133 */         sb.append(", "); 
/*     */     } 
/* 135 */     sb.append(")");
/* 136 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */