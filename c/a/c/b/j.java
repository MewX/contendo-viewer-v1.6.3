/*     */ package c.a.c.b;
/*     */ 
/*     */ import c.a.a.b.b;
/*     */ import c.a.a.b.d;
/*     */ import c.a.e.f;
/*     */ import c.a.e.g;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KImageWriteParamJava;
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.io.StringReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class j
/*     */   extends g
/*     */ {
/*     */   public static final char a = 'A';
/*  80 */   private static final String[][] g = new String[][] { { "Aptype", "[<tile idx>] res|layer|res-pos|pos-comp|comp-pos [res_start comp_start layer_end res_end comp_end prog] [[res_start comp_start ly_end res_end comp_end prog] ...] [[<tile-component idx>] ...]", "Specifies which type of progression should be used when generating the codestream. The 'res' value generates a resolution progressive codestream with the number of layers specified by 'Alayers' option. The 'layer' value generates a layer progressive codestream with multiple layers. In any case the rate-allocation algorithm optimizes for best quality in each layer. The quality measure is mean squared error (MSE) or a weighted version of it (WMSE). If no progression type is specified or imposed by other modules, the default value is 'layer'.\nIt is also possible to describe progression order changes. In this case, 'res_start' is the index (from 0) of the first resolution level, 'comp_start' is the index (from 0) of the first component, 'ly_end' is the index (from 0) of the first layer not included, 'res_end' is the index (from 0) of the first resolution level not included, 'comp_end' is index (from 0) of the first component not included and 'prog' is the progression type to be used for the rest of the tile/image. Several progression order changes can be specified, one after the other.", null }, { "Alayers", "<rate> [+<layers>] [<rate [+<layers>] [...]]", "Explicitly specifies the codestream layer formation parameters. The <rate> parameter specifies the bitrate to which the first layer should be optimized. The <layers> parameter, if present, specifies the number of extra layers that should be added for scalability. These extra layers are not optimized. Any extra <rate> and <layers> parameters add more layers, in the same way. An additional layer is always added at the end, which is optimized to the overall target bitrate of the bit stream. Any layers (optimized or not) whose target bitrate is higher that the overall target bitrate are silently ignored. The bitrates of the extra layers that are added through the <layers> parameter are approximately log-spaced between the other target bitrates. If several <rate> [+<layers>] constructs appear the <rate> parameters must appear in increasing order. The rate allocation algorithm ensures that all coded layers have a minimal reasonable size, if not these layers are silently ignored.", "0.015 +20 2.0 +10" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected d b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected J2KImageWriteParamJava c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   b e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   d f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public j(d src, int nl, b bw, J2KImageWriteParamJava wp) {
/* 158 */     super((f)src);
/* 159 */     this.b = src;
/* 160 */     this.c = wp;
/* 161 */     this.d = nl;
/* 162 */     this.e = bw;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(d headEnc) {
/* 171 */     this.f = headEnc;
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
/*     */   public abstract void b() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void a() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int c() {
/* 201 */     return this.d;
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
/*     */   public static String[][] d() {
/* 218 */     return g;
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
/*     */   public static j a(d src, float rate, b bw, J2KImageWriteParamJava wp) {
/* 240 */     String lyropt = wp.getLayers();
/* 241 */     if (lyropt == null) {
/* 242 */       if (wp.getROIs().a() == null) {
/* 243 */         lyropt = "res";
/*     */       } else {
/*     */         
/* 246 */         lyropt = "layer";
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 251 */     h lyrs = a(lyropt, rate);
/*     */     
/* 253 */     int nTiles = wp.getNumTiles();
/* 254 */     int nComp = wp.getNumComponents();
/* 255 */     int numLayers = lyrs.b();
/*     */ 
/*     */     
/* 258 */     wp.setProgressionType(lyrs, wp.getProgressionName());
/*     */     
/* 260 */     return new f(src, lyrs, bw, wp);
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
/*     */   private static h a(String params, float rate) {
/* 278 */     h lyrs = new h(rate);
/* 279 */     StreamTokenizer stok = new StreamTokenizer(new StringReader(params));
/* 280 */     stok.eolIsSignificant(false);
/*     */     
/*     */     try {
/* 283 */       stok.nextToken();
/*     */     }
/* 285 */     catch (IOException e) {
/* 286 */       throw new Error("An IOException has ocurred where it should never occur");
/*     */     } 
/*     */     
/* 289 */     boolean ratepending = false;
/* 290 */     boolean islayer = false;
/* 291 */     float r = 0.0F;
/* 292 */     while (stok.ttype != -1) {
/* 293 */       switch (stok.ttype) {
/*     */         case -2:
/* 295 */           if (islayer) {
/*     */             try {
/* 297 */               lyrs.a(r, (int)stok.nval);
/*     */             }
/* 299 */             catch (IllegalArgumentException e) {
/* 300 */               throw new IllegalArgumentException("Error in 'Alayers' option: " + e
/*     */                   
/* 302 */                   .getMessage());
/*     */             } 
/* 304 */             ratepending = false;
/* 305 */             islayer = false;
/*     */             break;
/*     */           } 
/* 308 */           if (ratepending) {
/*     */             try {
/* 310 */               lyrs.a(r, 0);
/*     */             }
/* 312 */             catch (IllegalArgumentException e) {
/* 313 */               throw new IllegalArgumentException("Error in 'Alayers' option: " + e
/*     */ 
/*     */                   
/* 316 */                   .getMessage());
/*     */             } 
/*     */           }
/*     */           
/* 320 */           r = (float)stok.nval;
/* 321 */           ratepending = true;
/*     */           break;
/*     */         
/*     */         case 43:
/* 325 */           if (!ratepending || islayer) {
/* 326 */             throw new IllegalArgumentException("Layer parameter without previous rate parameter in 'Alayers' option");
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 331 */           islayer = true;
/*     */           break;
/*     */         case -3:
/*     */           try {
/* 335 */             stok.nextToken();
/* 336 */           } catch (IOException e) {
/* 337 */             throw new Error("An IOException has ocurred where it should never occur");
/*     */           } 
/*     */           
/* 340 */           if (stok.ttype != -1) {
/* 341 */             throw new IllegalArgumentException("'sl' argument of '-Alayers' option must be used alone.");
/*     */           }
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 348 */           throw new IllegalArgumentException("Error parsing 'Alayers' option");
/*     */       } 
/*     */       
/*     */       try {
/* 352 */         stok.nextToken();
/*     */       }
/* 354 */       catch (IOException e) {
/* 355 */         throw new Error("An IOException has ocurred where it should never occur");
/*     */       } 
/*     */     } 
/*     */     
/* 359 */     if (islayer) {
/* 360 */       throw new IllegalArgumentException("Error parsing 'Alayers' option");
/*     */     }
/*     */     
/* 363 */     if (ratepending) {
/*     */       try {
/* 365 */         lyrs.a(r, 0);
/*     */       }
/* 367 */       catch (IllegalArgumentException e) {
/* 368 */         throw new IllegalArgumentException("Error in 'Alayers' option: " + e
/*     */ 
/*     */             
/* 371 */             .getMessage());
/*     */       } 
/*     */     }
/* 374 */     return lyrs;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/b/j.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */