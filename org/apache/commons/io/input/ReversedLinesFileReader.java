/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import org.apache.commons.io.Charsets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReversedLinesFileReader
/*     */   implements Closeable
/*     */ {
/*     */   private final int blockSize;
/*     */   private final Charset encoding;
/*     */   private final RandomAccessFile randomAccessFile;
/*     */   private final long totalByteLength;
/*     */   private final long totalBlockCount;
/*     */   private final byte[][] newLineSequences;
/*     */   private final int avoidNewlineSplitBufferSize;
/*     */   private final int byteDecrement;
/*     */   private FilePart currentFilePart;
/*     */   private boolean trailingNewlineOfFileSkipped = false;
/*     */   
/*     */   @Deprecated
/*     */   public ReversedLinesFileReader(File file) throws IOException {
/*  65 */     this(file, 4096, Charset.defaultCharset());
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
/*     */   public ReversedLinesFileReader(File file, Charset charset) throws IOException {
/*  79 */     this(file, 4096, charset);
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
/*     */   public ReversedLinesFileReader(File file, int blockSize, Charset encoding) throws IOException {
/*  96 */     this.blockSize = blockSize;
/*  97 */     this.encoding = encoding;
/*     */ 
/*     */     
/* 100 */     Charset charset = Charsets.toCharset(encoding);
/* 101 */     CharsetEncoder charsetEncoder = charset.newEncoder();
/* 102 */     float maxBytesPerChar = charsetEncoder.maxBytesPerChar();
/* 103 */     if (maxBytesPerChar == 1.0F)
/*     */     
/* 105 */     { this.byteDecrement = 1; }
/* 106 */     else if (charset == StandardCharsets.UTF_8)
/*     */     
/*     */     { 
/* 109 */       this.byteDecrement = 1; }
/* 110 */     else if (charset == Charset.forName("Shift_JIS") || charset == 
/*     */       
/* 112 */       Charset.forName("windows-31j") || charset == 
/* 113 */       Charset.forName("x-windows-949") || charset == 
/* 114 */       Charset.forName("gbk") || charset == 
/* 115 */       Charset.forName("x-windows-950"))
/* 116 */     { this.byteDecrement = 1; }
/* 117 */     else if (charset == StandardCharsets.UTF_16BE || charset == StandardCharsets.UTF_16LE)
/*     */     
/*     */     { 
/* 120 */       this.byteDecrement = 2; }
/* 121 */     else { if (charset == StandardCharsets.UTF_16) {
/* 122 */         throw new UnsupportedEncodingException("For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)");
/*     */       }
/*     */       
/* 125 */       throw new UnsupportedEncodingException("Encoding " + encoding + " is not supported yet (feel free to submit a patch)"); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     this.newLineSequences = new byte[][] { "\r\n".getBytes(encoding), "\n".getBytes(encoding), "\r".getBytes(encoding) };
/*     */     
/* 132 */     this.avoidNewlineSplitBufferSize = (this.newLineSequences[0]).length;
/*     */ 
/*     */     
/* 135 */     this.randomAccessFile = new RandomAccessFile(file, "r");
/* 136 */     this.totalByteLength = this.randomAccessFile.length();
/* 137 */     int lastBlockLength = (int)(this.totalByteLength % blockSize);
/* 138 */     if (lastBlockLength > 0) {
/* 139 */       this.totalBlockCount = this.totalByteLength / blockSize + 1L;
/*     */     } else {
/* 141 */       this.totalBlockCount = this.totalByteLength / blockSize;
/* 142 */       if (this.totalByteLength > 0L) {
/* 143 */         lastBlockLength = blockSize;
/*     */       }
/*     */     } 
/* 146 */     this.currentFilePart = new FilePart(this.totalBlockCount, lastBlockLength, null);
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
/*     */   public ReversedLinesFileReader(File file, int blockSize, String encoding) throws IOException {
/* 165 */     this(file, blockSize, Charsets.toCharset(encoding));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String readLine() throws IOException {
/* 176 */     String line = this.currentFilePart.readLine();
/* 177 */     while (line == null) {
/* 178 */       this.currentFilePart = this.currentFilePart.rollOver();
/* 179 */       if (this.currentFilePart != null) {
/* 180 */         line = this.currentFilePart.readLine();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     if ("".equals(line) && !this.trailingNewlineOfFileSkipped) {
/* 189 */       this.trailingNewlineOfFileSkipped = true;
/* 190 */       line = readLine();
/*     */     } 
/*     */     
/* 193 */     return line;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 203 */     this.randomAccessFile.close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class FilePart
/*     */   {
/*     */     private final long no;
/*     */ 
/*     */     
/*     */     private final byte[] data;
/*     */ 
/*     */     
/*     */     private byte[] leftOver;
/*     */ 
/*     */     
/*     */     private int currentLastBytePos;
/*     */ 
/*     */     
/*     */     private FilePart(long no, int length, byte[] leftOverOfLastFilePart) throws IOException {
/* 223 */       this.no = no;
/* 224 */       int dataLength = length + ((leftOverOfLastFilePart != null) ? leftOverOfLastFilePart.length : 0);
/* 225 */       this.data = new byte[dataLength];
/* 226 */       long off = (no - 1L) * ReversedLinesFileReader.this.blockSize;
/*     */ 
/*     */       
/* 229 */       if (no > 0L) {
/* 230 */         ReversedLinesFileReader.this.randomAccessFile.seek(off);
/* 231 */         int countRead = ReversedLinesFileReader.this.randomAccessFile.read(this.data, 0, length);
/* 232 */         if (countRead != length) {
/* 233 */           throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
/*     */         }
/*     */       } 
/*     */       
/* 237 */       if (leftOverOfLastFilePart != null) {
/* 238 */         System.arraycopy(leftOverOfLastFilePart, 0, this.data, length, leftOverOfLastFilePart.length);
/*     */       }
/* 240 */       this.currentLastBytePos = this.data.length - 1;
/* 241 */       this.leftOver = null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private FilePart rollOver() throws IOException {
/* 252 */       if (this.currentLastBytePos > -1) {
/* 253 */         throw new IllegalStateException("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=" + this.currentLastBytePos);
/*     */       }
/*     */ 
/*     */       
/* 257 */       if (this.no > 1L) {
/* 258 */         return new FilePart(this.no - 1L, ReversedLinesFileReader.this.blockSize, this.leftOver);
/*     */       }
/*     */       
/* 261 */       if (this.leftOver != null) {
/* 262 */         throw new IllegalStateException("Unexpected leftover of the last block: leftOverOfThisFilePart=" + new String(this.leftOver, ReversedLinesFileReader.this
/* 263 */               .encoding));
/*     */       }
/* 265 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String readLine() throws IOException {
/* 277 */       String line = null;
/*     */ 
/*     */       
/* 280 */       boolean isLastFilePart = (this.no == 1L);
/*     */       
/* 282 */       int i = this.currentLastBytePos;
/* 283 */       while (i > -1) {
/*     */         
/* 285 */         if (!isLastFilePart && i < ReversedLinesFileReader.this.avoidNewlineSplitBufferSize) {
/*     */ 
/*     */           
/* 288 */           createLeftOver();
/*     */           
/*     */           break;
/*     */         } 
/*     */         int newLineMatchByteCount;
/* 293 */         if ((newLineMatchByteCount = getNewLineMatchByteCount(this.data, i)) > 0) {
/* 294 */           int lineStart = i + 1;
/* 295 */           int lineLengthBytes = this.currentLastBytePos - lineStart + 1;
/*     */           
/* 297 */           if (lineLengthBytes < 0) {
/* 298 */             throw new IllegalStateException("Unexpected negative line length=" + lineLengthBytes);
/*     */           }
/* 300 */           byte[] lineData = new byte[lineLengthBytes];
/* 301 */           System.arraycopy(this.data, lineStart, lineData, 0, lineLengthBytes);
/*     */           
/* 303 */           line = new String(lineData, ReversedLinesFileReader.this.encoding);
/*     */           
/* 305 */           this.currentLastBytePos = i - newLineMatchByteCount;
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/* 310 */         i -= ReversedLinesFileReader.this.byteDecrement;
/*     */ 
/*     */         
/* 313 */         if (i < 0) {
/* 314 */           createLeftOver();
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 320 */       if (isLastFilePart && this.leftOver != null) {
/*     */         
/* 322 */         line = new String(this.leftOver, ReversedLinesFileReader.this.encoding);
/* 323 */         this.leftOver = null;
/*     */       } 
/*     */       
/* 326 */       return line;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void createLeftOver() {
/* 333 */       int lineLengthBytes = this.currentLastBytePos + 1;
/* 334 */       if (lineLengthBytes > 0) {
/*     */         
/* 336 */         this.leftOver = new byte[lineLengthBytes];
/* 337 */         System.arraycopy(this.data, 0, this.leftOver, 0, lineLengthBytes);
/*     */       } else {
/* 339 */         this.leftOver = null;
/*     */       } 
/* 341 */       this.currentLastBytePos = -1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int getNewLineMatchByteCount(byte[] data, int i) {
/* 352 */       for (byte[] newLineSequence : ReversedLinesFileReader.this.newLineSequences) {
/* 353 */         int k; boolean match = true;
/* 354 */         for (int j = newLineSequence.length - 1; j >= 0; j--) {
/* 355 */           int m = i + j - newLineSequence.length - 1;
/* 356 */           k = match & ((m >= 0 && data[m] == newLineSequence[j]) ? 1 : 0);
/*     */         } 
/* 358 */         if (k != 0) {
/* 359 */           return newLineSequence.length;
/*     */         }
/*     */       } 
/* 362 */       return 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/ReversedLinesFileReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */