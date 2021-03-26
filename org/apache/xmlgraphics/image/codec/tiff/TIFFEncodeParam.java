/*     */ package org.apache.xmlgraphics.image.codec.tiff;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import org.apache.xmlgraphics.image.codec.util.ImageEncodeParam;
/*     */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFEncodeParam
/*     */   implements ImageEncodeParam
/*     */ {
/*     */   private static final long serialVersionUID = 2471949735040024055L;
/*  49 */   private CompressionValue compression = CompressionValue.NONE;
/*     */   
/*     */   private boolean writeTiled;
/*     */   
/*     */   private int tileWidth;
/*     */   
/*     */   private int tileHeight;
/*     */   
/*     */   private Iterator extraImages;
/*     */   
/*     */   private TIFFField[] extraFields;
/*     */   private boolean convertJPEGRGBToYCbCr = true;
/*  61 */   private int deflateLevel = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompressionValue getCompression() {
/*  73 */     return this.compression;
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
/*     */   public void setCompression(CompressionValue compression) {
/*  95 */     switch (compression) {
/*     */       case NONE:
/*     */       case PACKBITS:
/*     */       case DEFLATE:
/*     */         break;
/*     */       
/*     */       default:
/* 102 */         throw new RuntimeException(PropertyUtil.getString("TIFFEncodeParam0"));
/*     */     } 
/*     */     
/* 105 */     this.compression = compression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getWriteTiled() {
/* 112 */     return this.writeTiled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWriteTiled(boolean writeTiled) {
/* 123 */     this.writeTiled = writeTiled;
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
/*     */   public void setTileSize(int tileWidth, int tileHeight) {
/* 144 */     this.tileWidth = tileWidth;
/* 145 */     this.tileHeight = tileHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTileWidth() {
/* 152 */     return this.tileWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTileHeight() {
/* 159 */     return this.tileHeight;
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
/*     */   public synchronized void setExtraImages(Iterator extraImages) {
/* 180 */     this.extraImages = extraImages;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Iterator getExtraImages() {
/* 189 */     return this.extraImages;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDeflateLevel(int deflateLevel) {
/* 200 */     if (deflateLevel != -1) {
/* 201 */       throw new RuntimeException(PropertyUtil.getString("TIFFEncodeParam1"));
/*     */     }
/*     */     
/* 204 */     this.deflateLevel = deflateLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDeflateLevel() {
/* 211 */     return this.deflateLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJPEGCompressRGBToYCbCr(boolean convertJPEGRGBToYCbCr) {
/* 220 */     this.convertJPEGRGBToYCbCr = convertJPEGRGBToYCbCr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getJPEGCompressRGBToYCbCr() {
/* 227 */     return this.convertJPEGRGBToYCbCr;
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
/*     */   public void setExtraFields(TIFFField[] extraFields) {
/* 241 */     this.extraFields = extraFields;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFField[] getExtraFields() {
/* 248 */     if (this.extraFields == null) {
/* 249 */       return new TIFFField[0];
/*     */     }
/* 251 */     return this.extraFields;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/TIFFEncodeParam.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */