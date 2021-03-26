/*     */ package c.a.c.a;
/*     */ 
/*     */ import c.a.c.b;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   extends b
/*     */ {
/*     */   public int e;
/*     */   public int f;
/*     */   public int g;
/*     */   public int h;
/*     */   public int i;
/*     */   public boolean j;
/*     */   public int k;
/*     */   public int l;
/*     */   public int m;
/*     */   public int[] n;
/*     */   
/*     */   public String toString() {
/* 118 */     String str = "Coded code-block (" + this.b + "," + this.a + "): " + this.c + " MSB skipped, " + this.i + " bytes, " + this.m + " truncation points, " + this.k + " layers, " + "progressive= " + this.j + ", ulx= " + this.e + ", uly= " + this.f + ", w= " + this.g + ", h= " + this.h + ", ftpIdx=" + this.l;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (this.n != null) {
/* 124 */       str = str + " {";
/* 125 */       for (int i = 0; i < this.n.length; i++)
/* 126 */         str = str + " " + this.n[i]; 
/* 127 */       str = str + " }";
/*     */     } 
/* 129 */     return str;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/a/d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */