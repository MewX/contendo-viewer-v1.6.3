/*     */ package com.github.jaiimageio.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.impl.plugins.tiff.TIFFImageWriter;
/*     */ import java.util.Locale;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFImageWriteParam
/*     */   extends ImageWriteParam
/*     */ {
/* 168 */   TIFFCompressor compressor = null;
/*     */   
/* 170 */   TIFFColorConverter colorConverter = null;
/*     */ 
/*     */ 
/*     */   
/*     */   int photometricInterpretation;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean appendedCompressionType = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFImageWriteParam(Locale locale) {
/* 183 */     super(locale);
/* 184 */     this.canWriteCompressed = true;
/* 185 */     this.canWriteTiles = true;
/* 186 */     this.compressionTypes = TIFFImageWriter.TIFFCompressionTypes;
/*     */   }
/*     */   
/*     */   public boolean isCompressionLossless() {
/* 190 */     if (getCompressionMode() != 2) {
/* 191 */       throw new IllegalStateException("Compression mode not MODE_EXPLICIT!");
/*     */     }
/*     */ 
/*     */     
/* 195 */     if (this.compressionType == null) {
/* 196 */       throw new IllegalStateException("No compression type set!");
/*     */     }
/*     */     
/* 199 */     if (this.compressor != null && this.compressionType
/* 200 */       .equals(this.compressor.getCompressionType())) {
/* 201 */       return this.compressor.isCompressionLossless();
/*     */     }
/*     */     
/* 204 */     for (int i = 0; i < this.compressionTypes.length; i++) {
/* 205 */       if (this.compressionType.equals(this.compressionTypes[i])) {
/* 206 */         return TIFFImageWriter.isCompressionLossless[i];
/*     */       }
/*     */     } 
/*     */     
/* 210 */     return false;
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
/*     */   
/*     */   public void setTIFFCompressor(TIFFCompressor compressor) {
/* 254 */     if (getCompressionMode() != 2) {
/* 255 */       throw new IllegalStateException("Compression mode not MODE_EXPLICIT!");
/*     */     }
/*     */ 
/*     */     
/* 259 */     this.compressor = compressor;
/*     */     
/* 261 */     if (this.appendedCompressionType) {
/*     */       
/* 263 */       int len = this.compressionTypes.length - 1;
/* 264 */       String[] types = new String[len];
/* 265 */       System.arraycopy(this.compressionTypes, 0, types, 0, len);
/* 266 */       this.compressionTypes = types;
/* 267 */       this.appendedCompressionType = false;
/*     */     } 
/*     */     
/* 270 */     if (compressor != null) {
/*     */       
/* 272 */       String compressorType = compressor.getCompressionType();
/* 273 */       int len = this.compressionTypes.length;
/* 274 */       boolean appendCompressionType = true;
/* 275 */       for (int i = 0; i < len; i++) {
/* 276 */         if (compressorType.equals(this.compressionTypes[i])) {
/* 277 */           appendCompressionType = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 282 */       if (appendCompressionType) {
/*     */         
/* 284 */         String[] types = new String[len + 1];
/* 285 */         System.arraycopy(this.compressionTypes, 0, types, 0, len);
/* 286 */         types[len] = compressorType;
/* 287 */         this.compressionTypes = types;
/* 288 */         this.appendedCompressionType = true;
/*     */       } 
/*     */     } 
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
/*     */   public TIFFCompressor getTIFFCompressor() {
/* 308 */     if (getCompressionMode() != 2) {
/* 309 */       throw new IllegalStateException("Compression mode not MODE_EXPLICIT!");
/*     */     }
/*     */     
/* 312 */     return this.compressor;
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
/*     */   public void setColorConverter(TIFFColorConverter colorConverter, int photometricInterpretation) {
/* 332 */     this.colorConverter = colorConverter;
/* 333 */     this.photometricInterpretation = photometricInterpretation;
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
/*     */   public TIFFColorConverter getColorConverter() {
/* 347 */     return this.colorConverter;
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
/*     */   public int getPhotometricInterpretation() {
/* 364 */     if (this.colorConverter == null) {
/* 365 */       throw new IllegalStateException("Color converter not set!");
/*     */     }
/* 367 */     return this.photometricInterpretation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetColorConverter() {
/* 377 */     this.colorConverter = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/plugins/tiff/TIFFImageWriteParam.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */