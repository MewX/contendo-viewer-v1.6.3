/*     */ package c.a.e;
/*     */ 
/*     */ import c.a.d;
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
/*     */ public class b
/*     */   extends d
/*     */ {
/*     */   public b(int nt, int nc, byte type) {
/*  77 */     super(nt, nc, type);
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
/*     */   public boolean a() {
/*  90 */     if (((Integer)this.R_).intValue() != 0) {
/*  91 */       return true;
/*     */     }
/*     */     
/*  94 */     if (this.o != null) {
/*  95 */       for (int t = this.j - 1; t >= 0; t--) {
/*  96 */         if (this.o[t] != null && ((Integer)this.o[t])
/*  97 */           .intValue() != 0) {
/*  98 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 102 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/e/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */