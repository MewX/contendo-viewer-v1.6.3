/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class CCITTFaxFilter
/*     */   extends Filter
/*     */ {
/*     */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
/*     */     int type;
/*     */     long tiffOptions;
/*  41 */     COSDictionary decodeParms = getDecodeParams(parameters, index);
/*     */ 
/*     */     
/*  44 */     int cols = decodeParms.getInt(COSName.COLUMNS, 1728);
/*  45 */     int rows = decodeParms.getInt(COSName.ROWS, 0);
/*  46 */     int height = parameters.getInt(COSName.HEIGHT, COSName.H, 0);
/*  47 */     if (rows > 0 && height > 0) {
/*     */ 
/*     */       
/*  50 */       rows = height;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  55 */       rows = Math.max(rows, height);
/*     */     } 
/*     */ 
/*     */     
/*  59 */     int k = decodeParms.getInt(COSName.K, 0);
/*  60 */     boolean encodedByteAlign = decodeParms.getBoolean(COSName.ENCODED_BYTE_ALIGN, false);
/*  61 */     int arraySize = (cols + 7) / 8 * rows;
/*     */     
/*  63 */     byte[] decompressed = new byte[arraySize];
/*     */ 
/*     */ 
/*     */     
/*  67 */     if (k == 0) {
/*     */       
/*  69 */       tiffOptions = encodedByteAlign ? 8L : 0L;
/*  70 */       type = 2;
/*     */ 
/*     */     
/*     */     }
/*  74 */     else if (k > 0) {
/*     */       
/*  76 */       tiffOptions = encodedByteAlign ? 8L : 0L;
/*  77 */       tiffOptions |= 0x1L;
/*  78 */       type = 3;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  83 */       tiffOptions = encodedByteAlign ? 4L : 0L;
/*  84 */       type = 4;
/*     */     } 
/*     */     
/*  87 */     CCITTFaxDecoderStream s = new CCITTFaxDecoderStream(encoded, cols, type, 1, tiffOptions);
/*  88 */     readFromDecoderStream(s, decompressed);
/*     */ 
/*     */     
/*  91 */     boolean blackIsOne = decodeParms.getBoolean(COSName.BLACK_IS_1, false);
/*  92 */     if (!blackIsOne)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       invertBitmap(decompressed);
/*     */     }
/*     */     
/* 101 */     decoded.write(decompressed);
/* 102 */     return new DecodeResult(parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void readFromDecoderStream(CCITTFaxDecoderStream decoderStream, byte[] result) throws IOException {
/* 108 */     int pos = 0;
/*     */     int read;
/* 110 */     while ((read = decoderStream.read(result, pos, result.length - pos)) > -1) {
/*     */       
/* 112 */       pos += read;
/* 113 */       if (pos >= result.length) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 118 */     decoderStream.close();
/*     */   }
/*     */ 
/*     */   
/*     */   private void invertBitmap(byte[] bufferData) {
/* 123 */     for (int i = 0, c = bufferData.length; i < c; i++)
/*     */     {
/* 125 */       bufferData[i] = (byte)((bufferData[i] ^ 0xFFFFFFFF) & 0xFF);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void encode(InputStream input, OutputStream encoded, COSDictionary parameters) throws IOException {
/* 133 */     int cols = parameters.getInt(COSName.COLUMNS);
/* 134 */     int rows = parameters.getInt(COSName.ROWS);
/* 135 */     CCITTFaxEncoderStream ccittFaxEncoderStream = new CCITTFaxEncoderStream(encoded, cols, rows, 1);
/*     */     
/* 137 */     IOUtils.copy(input, ccittFaxEncoderStream);
/* 138 */     input.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/CCITTFaxFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */