/*     */ package org.apache.batik.svggen.font.table;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GlyfCompositeComp
/*     */ {
/*     */   public static final short ARG_1_AND_2_ARE_WORDS = 1;
/*     */   public static final short ARGS_ARE_XY_VALUES = 2;
/*     */   public static final short ROUND_XY_TO_GRID = 4;
/*     */   public static final short WE_HAVE_A_SCALE = 8;
/*     */   public static final short MORE_COMPONENTS = 32;
/*     */   public static final short WE_HAVE_AN_X_AND_Y_SCALE = 64;
/*     */   public static final short WE_HAVE_A_TWO_BY_TWO = 128;
/*     */   public static final short WE_HAVE_INSTRUCTIONS = 256;
/*     */   public static final short USE_MY_METRICS = 512;
/*     */   private int firstIndex;
/*     */   private int firstContour;
/*     */   private short argument1;
/*     */   private short argument2;
/*     */   private short flags;
/*     */   private int glyphIndex;
/*  45 */   private double xscale = 1.0D;
/*  46 */   private double yscale = 1.0D;
/*  47 */   private double scale01 = 0.0D;
/*  48 */   private double scale10 = 0.0D;
/*  49 */   private int xtranslate = 0;
/*  50 */   private int ytranslate = 0;
/*  51 */   private int point1 = 0;
/*  52 */   private int point2 = 0;
/*     */   
/*     */   protected GlyfCompositeComp(ByteArrayInputStream bais) {
/*  55 */     this.flags = (short)(bais.read() << 8 | bais.read());
/*  56 */     this.glyphIndex = bais.read() << 8 | bais.read();
/*     */ 
/*     */     
/*  59 */     if ((this.flags & 0x1) != 0) {
/*  60 */       this.argument1 = (short)(bais.read() << 8 | bais.read());
/*  61 */       this.argument2 = (short)(bais.read() << 8 | bais.read());
/*     */     } else {
/*  63 */       this.argument1 = (short)bais.read();
/*  64 */       this.argument2 = (short)bais.read();
/*     */     } 
/*     */ 
/*     */     
/*  68 */     if ((this.flags & 0x2) != 0) {
/*  69 */       this.xtranslate = this.argument1;
/*  70 */       this.ytranslate = this.argument2;
/*     */     } else {
/*  72 */       this.point1 = this.argument1;
/*  73 */       this.point2 = this.argument2;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  78 */     int i = (short)(bais.read() << 8 | bais.read());
/*  79 */     this.xscale = this.yscale = i / 16384.0D;
/*  80 */     if ((this.flags & 0x40) != 0) {
/*  81 */       short s = (short)(bais.read() << 8 | bais.read());
/*  82 */       this.xscale = s / 16384.0D;
/*  83 */       s = (short)(bais.read() << 8 | bais.read());
/*  84 */       this.yscale = s / 16384.0D;
/*  85 */     } else if ((this.flags & 0x80) != 0) {
/*  86 */       i = (short)(bais.read() << 8 | bais.read());
/*  87 */       this.xscale = i / 16384.0D;
/*  88 */       i = (short)(bais.read() << 8 | bais.read());
/*  89 */       this.scale01 = i / 16384.0D;
/*  90 */       i = (short)(bais.read() << 8 | bais.read());
/*  91 */       this.scale10 = i / 16384.0D;
/*  92 */       i = (short)(bais.read() << 8 | bais.read());
/*  93 */       this.yscale = i / 16384.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFirstIndex(int idx) {
/*  98 */     this.firstIndex = idx;
/*     */   }
/*     */   
/*     */   public int getFirstIndex() {
/* 102 */     return this.firstIndex;
/*     */   }
/*     */   
/*     */   public void setFirstContour(int idx) {
/* 106 */     this.firstContour = idx;
/*     */   }
/*     */   public int getFirstContour() {
/* 109 */     return this.firstContour;
/*     */   }
/*     */   
/*     */   public short getArgument1() {
/* 113 */     return this.argument1;
/*     */   }
/*     */   
/*     */   public short getArgument2() {
/* 117 */     return this.argument2;
/*     */   }
/*     */   
/*     */   public short getFlags() {
/* 121 */     return this.flags;
/*     */   }
/*     */   
/*     */   public int getGlyphIndex() {
/* 125 */     return this.glyphIndex;
/*     */   }
/*     */   
/*     */   public double getScale01() {
/* 129 */     return this.scale01;
/*     */   }
/*     */   
/*     */   public double getScale10() {
/* 133 */     return this.scale10;
/*     */   }
/*     */   
/*     */   public double getXScale() {
/* 137 */     return this.xscale;
/*     */   }
/*     */   
/*     */   public double getYScale() {
/* 141 */     return this.yscale;
/*     */   }
/*     */   
/*     */   public int getXTranslate() {
/* 145 */     return this.xtranslate;
/*     */   }
/*     */   
/*     */   public int getYTranslate() {
/* 149 */     return this.ytranslate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int scaleX(int x, int y) {
/* 159 */     return Math.round((float)(x * this.xscale + y * this.scale10));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int scaleY(int x, int y) {
/* 169 */     return Math.round((float)(x * this.scale01 + y * this.yscale));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/GlyfCompositeComp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */