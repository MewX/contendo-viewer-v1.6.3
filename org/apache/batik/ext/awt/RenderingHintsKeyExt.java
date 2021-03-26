/*     */ package org.apache.batik.ext.awt;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class RenderingHintsKeyExt
/*     */ {
/*     */   public static final int KEY_BASE;
/*     */   public static final RenderingHints.Key KEY_TRANSCODING;
/*     */   public static final String VALUE_TRANSCODING_PRINTING = "Printing";
/*     */   public static final String VALUE_TRANSCODING_VECTOR = "Vector";
/*     */   public static final RenderingHints.Key KEY_AREA_OF_INTEREST;
/*     */   public static final RenderingHints.Key KEY_BUFFERED_IMAGE;
/*     */   public static final RenderingHints.Key KEY_COLORSPACE;
/*     */   public static final RenderingHints.Key KEY_AVOID_TILE_PAINTING;
/*  76 */   public static final Object VALUE_AVOID_TILE_PAINTING_ON = new Object();
/*  77 */   public static final Object VALUE_AVOID_TILE_PAINTING_OFF = new Object();
/*  78 */   public static final Object VALUE_AVOID_TILE_PAINTING_DEFAULT = new Object();
/*     */   
/*     */   static {
/*  81 */     int base = 10100;
/*  82 */     RenderingHints.Key trans = null, aoi = null, bi = null, cs = null, atp = null;
/*     */     while (true) {
/*  84 */       int val = base;
/*     */       
/*     */       try {
/*  87 */         trans = new TranscodingHintKey(val++);
/*  88 */         aoi = new AreaOfInterestHintKey(val++);
/*  89 */         bi = new BufferedImageHintKey(val++);
/*  90 */         cs = new ColorSpaceHintKey(val++);
/*  91 */         atp = new AvoidTilingHintKey(val++); break;
/*  92 */       } catch (Exception e) {
/*  93 */         System.err.println("You have loaded the Batik jar files more than once\nin the same JVM this is likely a problem with the\nway you are loading the Batik jar files.");
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  98 */         base = (int)(Math.random() * 2000000.0D);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 103 */     KEY_BASE = base;
/* 104 */     KEY_TRANSCODING = trans;
/* 105 */     KEY_AREA_OF_INTEREST = aoi;
/* 106 */     KEY_BUFFERED_IMAGE = bi;
/* 107 */     KEY_COLORSPACE = cs;
/* 108 */     KEY_AVOID_TILE_PAINTING = atp;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/RenderingHintsKeyExt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */