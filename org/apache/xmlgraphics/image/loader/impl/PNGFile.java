/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.color.ICC_ColorSpace;
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.SequenceInputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.zip.Inflater;
/*     */ import java.util.zip.InflaterInputStream;
/*     */ import org.apache.xmlgraphics.image.codec.png.PNGChunk;
/*     */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
/*     */ import org.apache.xmlgraphics.image.loader.ImageException;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class PNGFile
/*     */   implements PNGConstants
/*     */ {
/*     */   private ColorModel colorModel;
/*     */   private ICC_Profile iccProfile;
/*  55 */   private int sRGBRenderingIntent = -1;
/*     */   private int bitDepth;
/*     */   private int colorType;
/*     */   private boolean isTransparent;
/*     */   private int grayTransparentAlpha;
/*     */   private int redTransparentAlpha;
/*     */   private int greenTransparentAlpha;
/*     */   private int blueTransparentAlpha;
/*  63 */   private List<InputStream> streamVec = new ArrayList<InputStream>();
/*     */   private int paletteEntries;
/*     */   private byte[] redPalette;
/*     */   private byte[] greenPalette;
/*     */   private byte[] bluePalette;
/*     */   private byte[] alphaPalette;
/*     */   private boolean hasPalette;
/*     */   private boolean hasAlphaPalette;
/*     */   
/*     */   public PNGFile(InputStream stream) throws IOException, ImageException {
/*  73 */     if (!stream.markSupported()) {
/*  74 */       stream = new BufferedInputStream(stream);
/*     */     }
/*  76 */     DataInputStream distream = new DataInputStream(stream);
/*  77 */     long magic = distream.readLong();
/*  78 */     if (magic != -8552249625308161526L) {
/*  79 */       String msg = PropertyUtil.getString("PNGImageDecoder0");
/*  80 */       throw new ImageException(msg);
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/*     */       while (true) {
/*  86 */         String chunkType = PNGChunk.getChunkType(distream);
/*  87 */         if (chunkType.equals(PNGChunk.ChunkType.IHDR.name())) {
/*  88 */           PNGChunk chunk = PNGChunk.readChunk(distream);
/*  89 */           parse_IHDR_chunk(chunk); continue;
/*  90 */         }  if (chunkType.equals(PNGChunk.ChunkType.PLTE.name())) {
/*  91 */           PNGChunk chunk = PNGChunk.readChunk(distream);
/*  92 */           parse_PLTE_chunk(chunk); continue;
/*  93 */         }  if (chunkType.equals(PNGChunk.ChunkType.IDAT.name())) {
/*  94 */           PNGChunk chunk = PNGChunk.readChunk(distream);
/*  95 */           this.streamVec.add(new ByteArrayInputStream(chunk.getData())); continue;
/*  96 */         }  if (chunkType.equals(PNGChunk.ChunkType.IEND.name())) {
/*     */           
/*  98 */           PNGChunk.skipChunk(distream); break;
/*     */         } 
/* 100 */         if (chunkType.equals(PNGChunk.ChunkType.tRNS.name())) {
/* 101 */           PNGChunk chunk = PNGChunk.readChunk(distream);
/* 102 */           parse_tRNS_chunk(chunk); continue;
/* 103 */         }  if (chunkType.equals(PNGChunk.ChunkType.iCCP.name())) {
/* 104 */           PNGChunk chunk = PNGChunk.readChunk(distream);
/* 105 */           parse_iCCP_chunk(chunk); continue;
/* 106 */         }  if (chunkType.equals(PNGChunk.ChunkType.sRGB.name())) {
/* 107 */           PNGChunk chunk = PNGChunk.readChunk(distream);
/* 108 */           parse_sRGB_chunk(chunk);
/*     */           continue;
/*     */         } 
/* 111 */         PNGChunk.skipChunk(distream);
/*     */       } 
/* 113 */     } catch (Exception e) {
/* 114 */       String msg = PropertyUtil.getString("PNGImageDecoder2");
/* 115 */       throw new RuntimeException(msg, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageRawPNG getImageRawPNG(ImageInfo info) throws ImageException {
/* 121 */     InputStream seqStream = new SequenceInputStream(Collections.enumeration(this.streamVec));
/* 122 */     ColorSpace rgbCS = null;
/* 123 */     switch (this.colorType) {
/*     */       case 0:
/* 125 */         if (this.hasPalette) {
/* 126 */           throw new ImageException("Corrupt PNG: color palette is not allowed!");
/*     */         }
/* 128 */         this.colorModel = new ComponentColorModel(ColorSpace.getInstance(1003), false, false, 1, 0);
/*     */         break;
/*     */       
/*     */       case 2:
/* 132 */         if (this.iccProfile != null) {
/* 133 */           rgbCS = new ICC_ColorSpace(this.iccProfile);
/* 134 */         } else if (this.sRGBRenderingIntent != -1) {
/* 135 */           rgbCS = ColorSpace.getInstance(1000);
/*     */         } else {
/* 137 */           rgbCS = ColorSpace.getInstance(1004);
/*     */         } 
/* 139 */         this.colorModel = new ComponentColorModel(rgbCS, false, false, 1, 0);
/*     */         break;
/*     */       case 3:
/* 142 */         if (this.hasAlphaPalette) {
/* 143 */           this.colorModel = new IndexColorModel(this.bitDepth, this.paletteEntries, this.redPalette, this.greenPalette, this.bluePalette, this.alphaPalette);
/*     */           break;
/*     */         } 
/* 146 */         this.colorModel = new IndexColorModel(this.bitDepth, this.paletteEntries, this.redPalette, this.greenPalette, this.bluePalette);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 151 */         if (this.hasPalette) {
/* 152 */           throw new ImageException("Corrupt PNG: color palette is not allowed!");
/*     */         }
/* 154 */         this.colorModel = new ComponentColorModel(ColorSpace.getInstance(1003), true, false, 3, 0);
/*     */         break;
/*     */       
/*     */       case 6:
/* 158 */         if (this.iccProfile != null) {
/* 159 */           rgbCS = new ICC_ColorSpace(this.iccProfile);
/* 160 */         } else if (this.sRGBRenderingIntent != -1) {
/* 161 */           rgbCS = ColorSpace.getInstance(1000);
/*     */         } else {
/* 163 */           rgbCS = ColorSpace.getInstance(1004);
/*     */         } 
/* 165 */         this.colorModel = new ComponentColorModel(rgbCS, true, false, 3, 0);
/*     */         break;
/*     */       
/*     */       default:
/* 169 */         throw new ImageException("Unsupported color type: " + this.colorType);
/*     */     } 
/*     */     
/* 172 */     ImageRawPNG rawImage = new ImageRawPNG(info, seqStream, this.colorModel, this.bitDepth, this.iccProfile);
/* 173 */     if (this.isTransparent) {
/* 174 */       if (this.colorType == 0) {
/* 175 */         rawImage.setGrayTransparentAlpha(this.grayTransparentAlpha);
/* 176 */       } else if (this.colorType == 2) {
/* 177 */         rawImage.setRGBTransparentAlpha(this.redTransparentAlpha, this.greenTransparentAlpha, this.blueTransparentAlpha);
/*     */       }
/* 179 */       else if (this.colorType == 3) {
/* 180 */         rawImage.setTransparent();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 185 */     if (this.sRGBRenderingIntent != -1) {
/* 186 */       rawImage.setRenderingIntent(this.sRGBRenderingIntent);
/*     */     }
/* 188 */     return rawImage;
/*     */   }
/*     */   
/*     */   private void parse_IHDR_chunk(PNGChunk chunk) {
/* 192 */     this.bitDepth = chunk.getInt1(8);
/* 193 */     if (this.bitDepth != 8)
/*     */     {
/* 195 */       throw new RuntimeException("Unsupported bit depth: " + this.bitDepth);
/*     */     }
/* 197 */     this.colorType = chunk.getInt1(9);
/* 198 */     int compressionMethod = chunk.getInt1(10);
/* 199 */     if (compressionMethod != 0) {
/* 200 */       throw new RuntimeException("Unsupported PNG compression method: " + compressionMethod);
/*     */     }
/* 202 */     int filterMethod = chunk.getInt1(11);
/* 203 */     if (filterMethod != 0) {
/* 204 */       throw new RuntimeException("Unsupported PNG filter method: " + filterMethod);
/*     */     }
/* 206 */     int interlaceMethod = chunk.getInt1(12);
/* 207 */     if (interlaceMethod != 0)
/*     */     {
/* 209 */       throw new RuntimeException("Unsupported PNG interlace method: " + interlaceMethod);
/*     */     }
/*     */   }
/*     */   
/*     */   private void parse_PLTE_chunk(PNGChunk chunk) {
/* 214 */     this.paletteEntries = chunk.getLength() / 3;
/* 215 */     this.redPalette = new byte[this.paletteEntries];
/* 216 */     this.greenPalette = new byte[this.paletteEntries];
/* 217 */     this.bluePalette = new byte[this.paletteEntries];
/* 218 */     this.hasPalette = true;
/*     */     
/* 220 */     int pltIndex = 0;
/* 221 */     for (int i = 0; i < this.paletteEntries; i++) {
/* 222 */       this.redPalette[i] = chunk.getByte(pltIndex++);
/* 223 */       this.greenPalette[i] = chunk.getByte(pltIndex++);
/* 224 */       this.bluePalette[i] = chunk.getByte(pltIndex++);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void parse_tRNS_chunk(PNGChunk chunk) {
/* 229 */     if (this.colorType == 3) {
/* 230 */       int entries = chunk.getLength();
/* 231 */       if (entries > this.paletteEntries) {
/*     */         
/* 233 */         String msg = PropertyUtil.getString("PNGImageDecoder14");
/* 234 */         throw new RuntimeException(msg);
/*     */       } 
/*     */       
/* 237 */       this.alphaPalette = new byte[this.paletteEntries]; int i;
/* 238 */       for (i = 0; i < entries; i++) {
/* 239 */         this.alphaPalette[i] = chunk.getByte(i);
/*     */       }
/*     */       
/* 242 */       for (i = entries; i < this.paletteEntries; i++) {
/* 243 */         this.alphaPalette[i] = -1;
/*     */       }
/* 245 */       this.hasAlphaPalette = true;
/* 246 */     } else if (this.colorType == 0) {
/* 247 */       this.grayTransparentAlpha = chunk.getInt2(0);
/* 248 */     } else if (this.colorType == 2) {
/* 249 */       this.redTransparentAlpha = chunk.getInt2(0);
/* 250 */       this.greenTransparentAlpha = chunk.getInt2(2);
/* 251 */       this.blueTransparentAlpha = chunk.getInt2(4);
/* 252 */     } else if (this.colorType == 4 || this.colorType == 6) {
/*     */       
/* 254 */       String msg = PropertyUtil.getString("PNGImageDecoder15");
/* 255 */       throw new RuntimeException(msg);
/*     */     } 
/* 257 */     this.isTransparent = true;
/*     */   }
/*     */   
/*     */   private void parse_iCCP_chunk(PNGChunk chunk) {
/* 261 */     int length = chunk.getLength();
/* 262 */     int textIndex = 0;
/* 263 */     while (chunk.getByte(textIndex++) != 0);
/*     */ 
/*     */     
/* 266 */     textIndex++;
/* 267 */     byte[] profile = new byte[length - textIndex];
/* 268 */     System.arraycopy(chunk.getData(), textIndex, profile, 0, length - textIndex);
/* 269 */     ByteArrayInputStream bais = new ByteArrayInputStream(profile);
/* 270 */     InflaterInputStream iis = new InflaterInputStream(bais, new Inflater());
/*     */     try {
/* 272 */       this.iccProfile = ICC_Profile.getInstance(iis);
/* 273 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void parse_sRGB_chunk(PNGChunk chunk) {
/* 279 */     this.sRGBRenderingIntent = chunk.getByte(0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/PNGFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */