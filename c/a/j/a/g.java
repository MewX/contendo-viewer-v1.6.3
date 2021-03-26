/*     */ package c.a.j.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class g
/*     */ {
/*     */   public int a;
/*     */   public int b;
/*     */   public int c;
/*     */   public int d;
/*     */   public o e;
/*     */   public int f;
/*     */   public int g;
/*     */   public int h;
/*     */   public int i;
/*     */   public int j;
/* 118 */   public float k = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public double l = 1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public double m = 1.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public int n = 0;
/*     */ 
/*     */   
/* 138 */   public int o = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int a();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Object b();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void a(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 193 */     String typeString = "";
/* 194 */     switch (a()) {
/*     */       case 0:
/* 196 */         typeString = "Unsigned Byte";
/*     */         break;
/*     */       case 1:
/* 199 */         typeString = "Short";
/*     */         break;
/*     */       case 3:
/* 202 */         typeString = "Integer";
/*     */         break;
/*     */       case 4:
/* 205 */         typeString = "Float";
/*     */         break;
/*     */     } 
/*     */     
/* 209 */     return "CBlkWTData: ulx= " + this.a + ", uly= " + this.b + ", code-block(" + this.d + "," + this.c + "), width= " + this.f + ", height= " + this.g + ", offset= " + this.h + ", scan-width=" + this.i + ", type= " + typeString + ", sb= " + this.e + ", num. ROI coeff=" + this.n + ", num. ROI bit-planes=" + this.o;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/a/g.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */