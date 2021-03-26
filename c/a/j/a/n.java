/*     */ package c.a.j.a;
/*     */ 
/*     */ import c.a.e.a;
/*     */ import c.a.e.f;
/*     */ import c.a.e.g;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class n
/*     */   extends g
/*     */   implements j, k
/*     */ {
/*     */   public static final int e = 0;
/*     */   public static final char f = 'W';
/*  81 */   private static final String[][] c = new String[][] { { "Wlev", "<number of decomposition levels>", "Specifies the number of wavelet decomposition levels to apply to the image. If 0 no wavelet transform is performed. All components and all tiles have the same number of decomposition levels.", "5" }, { "Wwt", "[full]", "Specifies the wavelet transform to be used. Possible value is: 'full' (full page). The value 'full' performs a normal DWT.", "full" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected n(f src) {
/*  99 */     super(src);
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
/*     */   public static String[][] c() {
/* 116 */     return c;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static n a(a src, J2KImageWriteParamJava wp) {
/* 148 */     int deflev = ((Integer)wp.getDecompositionLevel().d()).intValue();
/*     */ 
/*     */     
/* 151 */     int prefx = 0;
/* 152 */     int prefy = 0;
/*     */     
/* 154 */     return new m(src, wp, prefx, prefy);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/a/n.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */