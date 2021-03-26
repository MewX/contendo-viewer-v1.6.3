/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   protected static final short ARG_1_AND_2_ARE_WORDS = 1;
/*     */   protected static final short ARGS_ARE_XY_VALUES = 2;
/*     */   protected static final short ROUND_XY_TO_GRID = 4;
/*     */   protected static final short WE_HAVE_A_SCALE = 8;
/*     */   protected static final short MORE_COMPONENTS = 32;
/*     */   protected static final short WE_HAVE_AN_X_AND_Y_SCALE = 64;
/*     */   protected static final short WE_HAVE_A_TWO_BY_TWO = 128;
/*     */   protected static final short WE_HAVE_INSTRUCTIONS = 256;
/*     */   protected static final short USE_MY_METRICS = 512;
/*     */   private int firstIndex;
/*     */   private int firstContour;
/*     */   private final short argument1;
/*     */   private final short argument2;
/*     */   private final short flags;
/*     */   private final int glyphIndex;
/*  75 */   private double xscale = 1.0D;
/*  76 */   private double yscale = 1.0D;
/*  77 */   private double scale01 = 0.0D;
/*  78 */   private double scale10 = 0.0D;
/*  79 */   private int xtranslate = 0;
/*  80 */   private int ytranslate = 0;
/*  81 */   private int point1 = 0;
/*  82 */   private int point2 = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GlyfCompositeComp(TTFDataStream bais) throws IOException {
/*  92 */     this.flags = bais.readSignedShort();
/*  93 */     this.glyphIndex = bais.readUnsignedShort();
/*     */ 
/*     */     
/*  96 */     if ((this.flags & 0x1) != 0) {
/*     */       
/*  98 */       this.argument1 = bais.readSignedShort();
/*  99 */       this.argument2 = bais.readSignedShort();
/*     */     }
/*     */     else {
/*     */       
/* 103 */       this.argument1 = (short)bais.readSignedByte();
/* 104 */       this.argument2 = (short)bais.readSignedByte();
/*     */     } 
/*     */ 
/*     */     
/* 108 */     if ((this.flags & 0x2) != 0) {
/*     */       
/* 110 */       this.xtranslate = this.argument1;
/* 111 */       this.ytranslate = this.argument2;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 116 */       this.point1 = this.argument1;
/* 117 */       this.point2 = this.argument2;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     int i = bais.readSignedShort();
/* 124 */     this.xscale = this.yscale = i / 16384.0D;
/*     */     
/* 126 */     if ((this.flags & 0x40) != 0) {
/*     */       
/* 128 */       short s = bais.readSignedShort();
/* 129 */       this.xscale = s / 16384.0D;
/* 130 */       s = bais.readSignedShort();
/* 131 */       this.yscale = s / 16384.0D;
/*     */     }
/* 133 */     else if ((this.flags & 0x80) != 0) {
/*     */       
/* 135 */       i = bais.readSignedShort();
/* 136 */       this.xscale = i / 16384.0D;
/* 137 */       i = bais.readSignedShort();
/* 138 */       this.scale01 = i / 16384.0D;
/* 139 */       i = bais.readSignedShort();
/* 140 */       this.scale10 = i / 16384.0D;
/* 141 */       i = bais.readSignedShort();
/* 142 */       this.yscale = i / 16384.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstIndex(int idx) {
/* 153 */     this.firstIndex = idx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFirstIndex() {
/* 163 */     return this.firstIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstContour(int idx) {
/* 173 */     this.firstContour = idx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFirstContour() {
/* 183 */     return this.firstContour;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getArgument1() {
/* 193 */     return this.argument1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getArgument2() {
/* 203 */     return this.argument2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getFlags() {
/* 213 */     return this.flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGlyphIndex() {
/* 223 */     return this.glyphIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getScale01() {
/* 233 */     return this.scale01;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getScale10() {
/* 243 */     return this.scale10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getXScale() {
/* 253 */     return this.xscale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getYScale() {
/* 263 */     return this.yscale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXTranslate() {
/* 273 */     return this.xtranslate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getYTranslate() {
/* 283 */     return this.ytranslate;
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
/*     */   public int scaleX(int x, int y) {
/* 295 */     return Math.round((float)(x * this.xscale + y * this.scale10));
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
/*     */   public int scaleY(int x, int y) {
/* 307 */     return Math.round((float)(x * this.scale01 + y * this.yscale));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/GlyfCompositeComp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */