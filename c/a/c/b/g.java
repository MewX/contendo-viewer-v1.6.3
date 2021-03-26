/*     */ package c.a.c.b;
/*     */ 
/*     */ import c.a.c.a;
/*     */ import c.a.c.c;
/*     */ import c.a.c.f;
/*     */ import c.a.e.f;
/*     */ import c.a.e.g;
/*     */ import c.a.g.b.a;
/*     */ import c.a.j.a.o;
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
/*     */ 
/*     */ 
/*     */ public abstract class g
/*     */   extends g
/*     */   implements d, f
/*     */ {
/*     */   public static final char a = 'C';
/*  86 */   private static final String[][] c = new String[][] { { "Cblksiz", "[<tile-component idx>] <width> <height> [[<tile-component idx>] <width> <height>]", "Specifies the maximum code-block size to use for tile-component. The maximum width and height is 1024, however the surface area (i.e. width x height) must not exceed 4096. The minimum width and height is 4.", "64 64" }, { "Cbypass", "[<tile-component idx>] true|false[ [<tile-component idx>] true|false ...]", "Uses the lazy coding mode with the entropy coder. This will bypass the MQ coder for some of the coding passes, where the distribution is often close to uniform. Since the MQ codeword will be terminated at least once per lazy pass, it is important to use an efficient termination algorithm, see the 'Cterm' option.'true' enables, 'false' disables it.", "false" }, { "CresetMQ", "[<tile-component idx>] true|false[ [<tile-component idx>] true|false ...]", "If this is enabled the probability estimates of the MQ coder are reset after each arithmetically coded (i.e. non-lazy) coding pass. 'true' enables, 'false' disables it.", "false" }, { "Creg_term", "[<tile-component idx>] true|false[ [<tile-component idx>] true|false ...]", "If this is enabled the codeword (raw or MQ) is terminated on a byte boundary after each coding pass. In this case it is important to use an efficient termination algorithm, see the 'Cterm' option. 'true' enables, 'false' disables it.", "false" }, { "Ccausal", "[<tile-component idx>] true|false[ [<tile-component idx>] true|false ...]", "Uses vertically stripe causal context formation. If this is enabled the context formation process in one stripe is independant of the next stripe (i.e. the one below it). 'true' enables, 'false' disables it.", "false" }, { "Cseg_symbol", "[<tile-component idx>] true|false[ [<tile-component idx>] true|false ...]", "Inserts an error resilience segmentation symbol in the MQ codeword at the end of each bit-plane (cleanup pass). Decoders can use this information to detect and conceal errors.'true' enables, 'false' disables it.", "false" }, { "Cterm", "[<tile-component idx>] near_opt|easy|predict|full[ [<tile-component idx>] near_opt|easy|predict|full ...]", "Specifies the algorithm used to terminate the MQ codeword. The most efficient one is 'near_opt', which delivers a codeword which in almost all cases is the shortest possible. The 'easy' is a simpler algorithm that delivers a codeword length that is close to the previous one (in average 1 bit longer). The 'predict' is almost the same as the 'easy' but it leaves error resilient information on the spare least significant bits (in average 3.5 bits), which can be used by a decoder to detect errors. The 'full' algorithm performs a full flush of the MQ coder and is highly inefficient.\nIt is important to use a good termination policy since the MQ codeword can be terminated quite often, specially if the 'Cbypass' or 'Creg_term' options are enabled (in the normal case it would be terminated once per code-block, while if 'Creg_term' is specified it will be done almost 3 times per bit-plane in each code-block).", "near_opt" }, { "Clen_calc", "[<tile-component idx>] near_opt|lazy_good|lazy[ [<tile-component idx>] ...]", "Specifies the algorithm to use in calculating the necessary MQ length for each decoding pass. The best one is 'near_opt', which performs a rather sophisticated calculation and provides the best results. The 'lazy_good' and 'lazy' are very simple algorithms that provide rather conservative results, 'lazy_good' one being slightly better. Do not change this option unless you want to experiment the effect of different length calculation algorithms.", "near_opt" }, { "Cpp", "[<tile-component idx>] <dim> <dim> [<dim> <dim>] [ [<tile-component idx>] ...]", "Specifies precinct partition dimensions for tile-component. The first two values apply to the highest resolution and the following ones (if any) apply to the remaining resolutions in decreasing order. If less values than the number of decomposition levels are specified, then the last two values are used for the remaining resolutions.", null } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected a b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public g(a src) {
/* 181 */     super((f)src);
/* 182 */     this.b = src;
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
/*     */   public abstract int b(int paramInt1, int paramInt2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int c(int paramInt1, int paramInt2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean d(int t, int c) {
/* 225 */     return this.b.d(t, c);
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
/*     */   public o e(int t, int c) {
/* 243 */     return this.b.e(t, c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 251 */     return this.b.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 259 */     return this.b.b();
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
/*     */   public static String[][] c() {
/* 277 */     return c;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static g a(a src, J2KImageWriteParamJava wp, a cblks, c pss, c.a.g bms, c.a.g mqrs, c.a.g rts, c.a.g css, c.a.g sss, c.a.g lcs, c.a.g tts) {
/* 320 */     return new k(src, cblks, pss, bms, mqrs, rts, css, sss, lcs, tts);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/b/g.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */