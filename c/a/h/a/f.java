/*     */ package c.a.h.a;
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
/*     */ public abstract class f
/*     */ {
/*     */   protected f a;
/*     */   protected f b;
/*     */   protected f c;
/*     */   protected f d;
/*     */   protected boolean e;
/*     */   public int f;
/*     */   public int g;
/*     */   public int h;
/*     */   public int i;
/*     */   
/*     */   public f(int ulx, int uly, int w, int h) {
/*  94 */     this.f = ulx;
/*  95 */     this.g = uly;
/*  96 */     this.h = w;
/*  97 */     this.i = h;
/*     */   }
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
/*     */   public f a(int x, int y) {
/* 114 */     if (x < this.f || y < this.g || x >= this.f + this.h || y >= this.g + this.i) {
/* 115 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 118 */     f cur = this;
/* 119 */     while (cur.e) {
/* 120 */       f hhs = cur.d;
/*     */       
/* 122 */       if (x < hhs.f) {
/*     */         
/* 124 */         if (y < hhs.g) {
/*     */           
/* 126 */           cur = cur.a;
/*     */           
/*     */           continue;
/*     */         } 
/* 130 */         cur = cur.b;
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 135 */       if (y < hhs.g) {
/*     */         
/* 137 */         cur = cur.c;
/*     */         
/*     */         continue;
/*     */       } 
/* 141 */       cur = cur.d;
/*     */     } 
/*     */ 
/*     */     
/* 145 */     return cur;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/h/a/f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */