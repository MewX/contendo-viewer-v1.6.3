/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.util.Hex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class ASCIIHexFilter
/*     */   extends Filter
/*     */ {
/*  35 */   private static final Log LOG = LogFactory.getLog(ASCIIHexFilter.class);
/*     */   
/*  37 */   private static final int[] REVERSE_HEX = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
/*     */     int firstByte;
/*  71 */     while ((firstByte = encoded.read()) != -1) {
/*     */ 
/*     */       
/*  74 */       while (isWhitespace(firstByte))
/*     */       {
/*  76 */         firstByte = encoded.read();
/*     */       }
/*  78 */       if (firstByte == -1 || isEOD(firstByte)) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/*  83 */       if (REVERSE_HEX[firstByte] == -1)
/*     */       {
/*  85 */         LOG.error("Invalid hex, int: " + firstByte + " char: " + (char)firstByte);
/*     */       }
/*  87 */       int value = REVERSE_HEX[firstByte] * 16;
/*  88 */       int secondByte = encoded.read();
/*     */       
/*  90 */       if (secondByte == -1 || isEOD(secondByte)) {
/*     */ 
/*     */         
/*  93 */         decoded.write(value);
/*     */         break;
/*     */       } 
/*  96 */       if (secondByte >= 0) {
/*     */         
/*  98 */         if (REVERSE_HEX[secondByte] == -1)
/*     */         {
/* 100 */           LOG.error("Invalid hex, int: " + secondByte + " char: " + (char)secondByte);
/*     */         }
/* 102 */         value += REVERSE_HEX[secondByte];
/*     */       } 
/* 104 */       decoded.write(value);
/*     */     } 
/* 106 */     decoded.flush();
/* 107 */     return new DecodeResult(parameters);
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
/*     */   private boolean isWhitespace(int c) {
/* 119 */     return (c == 0 || c == 9 || c == 10 || c == 12 || c == 13 || c == 32);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isEOD(int c) {
/* 124 */     return (c == 62);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void encode(InputStream input, OutputStream encoded, COSDictionary parameters) throws IOException {
/*     */     int byteRead;
/* 132 */     while ((byteRead = input.read()) != -1)
/*     */     {
/* 134 */       Hex.writeHexByte((byte)byteRead, encoded);
/*     */     }
/* 136 */     encoded.flush();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/ASCIIHexFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */