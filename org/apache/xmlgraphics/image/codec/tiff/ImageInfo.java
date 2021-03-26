/*     */ package org.apache.xmlgraphics.image.codec.tiff;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.RenderedImage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class ImageInfo
/*     */ {
/*     */   private static final int DEFAULT_ROWS_PER_STRIP = 8;
/*     */   private final int numExtraSamples;
/*     */   private final ExtraSamplesType extraSampleType;
/*     */   private final ImageType imageType;
/*     */   private final int colormapSize;
/*     */   private final char[] colormap;
/*     */   private final int tileWidth;
/*     */   private final int tileHeight;
/*     */   private final int numTiles;
/*     */   private final long bytesPerRow;
/*     */   private final long bytesPerTile;
/*     */   
/*     */   private ImageInfo(ImageInfoBuilder builder) {
/*  42 */     this.numExtraSamples = builder.numExtraSamples;
/*  43 */     this.extraSampleType = builder.extraSampleType;
/*  44 */     this.imageType = builder.imageType;
/*  45 */     this.colormapSize = builder.colormapSize;
/*  46 */     this.colormap = copyColormap(builder.colormap);
/*  47 */     this.tileWidth = builder.tileWidth;
/*  48 */     this.tileHeight = builder.tileHeight;
/*  49 */     this.numTiles = builder.numTiles;
/*  50 */     this.bytesPerRow = builder.bytesPerRow;
/*  51 */     this.bytesPerTile = builder.bytesPerTile;
/*     */   }
/*     */   
/*     */   private static char[] copyColormap(char[] colorMap) {
/*  55 */     if (colorMap == null) {
/*  56 */       return null;
/*     */     }
/*  58 */     char[] copy = new char[colorMap.length];
/*  59 */     System.arraycopy(colorMap, 0, copy, 0, colorMap.length);
/*  60 */     return copy;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int getNumberOfExtraSamplesForColorSpace(ColorSpace colorSpace, ImageType imageType, int numBands) {
/*  65 */     if (imageType == ImageType.GENERIC)
/*  66 */       return numBands - 1; 
/*  67 */     if (numBands > 1) {
/*  68 */       return numBands - colorSpace.getNumComponents();
/*     */     }
/*  70 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static char[] createColormap(int sizeOfColormap, byte[] r, byte[] g, byte[] b) {
/*  75 */     int redIndex = 0;
/*  76 */     int greenIndex = sizeOfColormap;
/*  77 */     int blueIndex = 2 * sizeOfColormap;
/*  78 */     char[] colormap = new char[sizeOfColormap * 3];
/*  79 */     for (int i = 0; i < sizeOfColormap; i++) {
/*     */       
/*  81 */       colormap[redIndex++] = convertColorToColormapChar(0xFF & r[i]);
/*  82 */       colormap[greenIndex++] = convertColorToColormapChar(0xFF & g[i]);
/*  83 */       colormap[blueIndex++] = convertColorToColormapChar(0xFF & b[i]);
/*     */     } 
/*  85 */     return colormap;
/*     */   }
/*     */   
/*     */   private static char convertColorToColormapChar(int color) {
/*  89 */     return (char)(color << 8 | color);
/*     */   }
/*     */   
/*     */   int getNumberOfExtraSamples() {
/*  93 */     return this.numExtraSamples;
/*     */   }
/*     */   
/*     */   ExtraSamplesType getExtraSamplesType() {
/*  97 */     return this.extraSampleType;
/*     */   }
/*     */   
/*     */   ImageType getType() {
/* 101 */     return this.imageType;
/*     */   }
/*     */   
/*     */   int getColormapSize() {
/* 105 */     return this.colormapSize;
/*     */   }
/*     */   
/*     */   char[] getColormap() {
/* 109 */     return copyColormap(this.colormap);
/*     */   }
/*     */   
/*     */   int getTileWidth() {
/* 113 */     return this.tileWidth;
/*     */   }
/*     */   
/*     */   int getTileHeight() {
/* 117 */     return this.tileHeight;
/*     */   }
/*     */   
/*     */   int getNumTiles() {
/* 121 */     return this.numTiles;
/*     */   }
/*     */   
/*     */   long getBytesPerRow() {
/* 125 */     return this.bytesPerRow;
/*     */   }
/*     */   
/*     */   long getBytesPerTile() {
/* 129 */     return this.bytesPerTile;
/*     */   }
/*     */ 
/*     */   
/*     */   static ImageInfo newInstance(RenderedImage im, int dataTypeSize, int numBands, ColorModel colorModel, TIFFEncodeParam params) {
/* 134 */     ImageInfoBuilder builder = new ImageInfoBuilder();
/* 135 */     if (colorModel instanceof IndexColorModel) {
/* 136 */       IndexColorModel indexColorModel = (IndexColorModel)colorModel;
/* 137 */       int colormapSize = indexColorModel.getMapSize();
/* 138 */       byte[] r = new byte[colormapSize];
/* 139 */       indexColorModel.getReds(r);
/* 140 */       byte[] g = new byte[colormapSize];
/* 141 */       indexColorModel.getGreens(g);
/* 142 */       byte[] b = new byte[colormapSize];
/* 143 */       indexColorModel.getBlues(b);
/*     */       
/* 145 */       builder.imageType = ImageType.getTypeFromRGB(colormapSize, r, g, b, dataTypeSize, numBands);
/*     */       
/* 147 */       if (builder.imageType == ImageType.PALETTE) {
/* 148 */         builder.colormap = createColormap(colormapSize, r, g, b);
/* 149 */         builder.colormapSize = colormapSize * 3;
/*     */       } 
/* 151 */     } else if (colorModel == null) {
/* 152 */       if (dataTypeSize == 1 && numBands == 1) {
/* 153 */         builder.imageType = ImageType.BILEVEL_BLACK_IS_ZERO;
/*     */       } else {
/* 155 */         builder.imageType = ImageType.GENERIC;
/* 156 */         builder.numExtraSamples = (numBands > 1) ? (numBands - 1) : 0;
/*     */       } 
/*     */     } else {
/* 159 */       ColorSpace colorSpace = colorModel.getColorSpace();
/* 160 */       builder.imageType = ImageType.getTypeFromColorSpace(colorSpace, params);
/* 161 */       builder.numExtraSamples = getNumberOfExtraSamplesForColorSpace(colorSpace, builder.imageType, numBands);
/*     */       
/* 163 */       builder.extraSampleType = ExtraSamplesType.getValue(colorModel, builder.numExtraSamples);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 168 */     int width = im.getWidth();
/* 169 */     int height = im.getHeight();
/* 170 */     if (params.getWriteTiled()) {
/* 171 */       builder.tileWidth = (params.getTileWidth() > 0) ? params.getTileWidth() : width;
/* 172 */       builder.tileHeight = (params.getTileHeight() > 0) ? params.getTileHeight() : height;
/*     */       
/* 174 */       builder.numTiles = (width + builder.tileWidth - 1) / builder.tileWidth * (height + builder.tileHeight - 1) / builder.tileHeight;
/*     */     } else {
/*     */       
/* 177 */       builder.tileWidth = width;
/* 178 */       builder.tileHeight = (params.getTileHeight() > 0) ? params.getTileHeight() : 8;
/*     */       
/* 180 */       builder.numTiles = (int)Math.ceil(height / builder.tileHeight);
/*     */     } 
/* 182 */     builder.setBytesPerRow(dataTypeSize, numBands).setBytesPerTile();
/*     */     
/* 184 */     return builder.build();
/*     */   }
/*     */   
/*     */   private static final class ImageInfoBuilder {
/* 188 */     private ImageType imageType = ImageType.UNSUPPORTED;
/*     */     private int numExtraSamples;
/*     */     private char[] colormap;
/*     */     private int colormapSize;
/* 192 */     private ExtraSamplesType extraSampleType = ExtraSamplesType.UNSPECIFIED;
/*     */     private int tileWidth;
/*     */     private int tileHeight;
/*     */     private int numTiles;
/*     */     private long bytesPerRow;
/*     */     private long bytesPerTile;
/*     */     
/*     */     private ImageInfoBuilder setBytesPerRow(int dataTypeSize, int numBands) {
/* 200 */       this.bytesPerRow = (long)Math.ceil(dataTypeSize / 8.0D * this.tileWidth * numBands);
/* 201 */       return this;
/*     */     }
/*     */     
/*     */     private ImageInfoBuilder setBytesPerTile() {
/* 205 */       this.bytesPerTile = this.bytesPerRow * this.tileHeight;
/* 206 */       return this;
/*     */     }
/*     */     
/*     */     private ImageInfo build() {
/* 210 */       return new ImageInfo(this);
/*     */     }
/*     */     
/*     */     private ImageInfoBuilder() {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/ImageInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */