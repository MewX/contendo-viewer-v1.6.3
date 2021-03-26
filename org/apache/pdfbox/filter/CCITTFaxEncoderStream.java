/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class CCITTFaxEncoderStream
/*     */   extends OutputStream
/*     */ {
/*  48 */   private int currentBufferLength = 0;
/*     */   
/*     */   private final byte[] inputBuffer;
/*     */   private final int inputBufferLength;
/*     */   private final int columns;
/*     */   private final int rows;
/*     */   private int[] changesCurrentRow;
/*     */   private int[] changesReferenceRow;
/*  56 */   private int currentRow = 0;
/*  57 */   private int changesCurrentRowLength = 0;
/*  58 */   private int changesReferenceRowLength = 0;
/*  59 */   private byte outputBuffer = 0;
/*  60 */   private byte outputBufferBitLength = 0;
/*     */   
/*     */   private final int fillOrder;
/*     */   private final OutputStream stream;
/*     */   
/*     */   CCITTFaxEncoderStream(OutputStream stream, int columns, int rows, int fillOrder) {
/*  66 */     this.stream = stream;
/*  67 */     this.columns = columns;
/*  68 */     this.rows = rows;
/*  69 */     this.fillOrder = fillOrder;
/*     */     
/*  71 */     this.changesReferenceRow = new int[columns];
/*  72 */     this.changesCurrentRow = new int[columns];
/*     */     
/*  74 */     this.inputBufferLength = (columns + 7) / 8;
/*  75 */     this.inputBuffer = new byte[this.inputBufferLength];
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/*  80 */     this.inputBuffer[this.currentBufferLength] = (byte)b;
/*  81 */     this.currentBufferLength++;
/*     */     
/*  83 */     if (this.currentBufferLength == this.inputBufferLength) {
/*  84 */       encodeRow();
/*  85 */       this.currentBufferLength = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/*  91 */     this.stream.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  96 */     this.stream.close();
/*     */   }
/*     */   
/*     */   private void encodeRow() throws IOException {
/* 100 */     this.currentRow++;
/* 101 */     int[] tmp = this.changesReferenceRow;
/* 102 */     this.changesReferenceRow = this.changesCurrentRow;
/* 103 */     this.changesCurrentRow = tmp;
/* 104 */     this.changesReferenceRowLength = this.changesCurrentRowLength;
/* 105 */     this.changesCurrentRowLength = 0;
/*     */     
/* 107 */     int index = 0;
/* 108 */     boolean white = true;
/* 109 */     while (index < this.columns) {
/* 110 */       int byteIndex = index / 8;
/* 111 */       int bit = index % 8;
/* 112 */       if ((((this.inputBuffer[byteIndex] >> 7 - bit & 0x1) == 1)) == white) {
/* 113 */         this.changesCurrentRow[this.changesCurrentRowLength] = index;
/* 114 */         this.changesCurrentRowLength++;
/* 115 */         white = !white;
/*     */       } 
/* 117 */       index++;
/*     */     } 
/*     */     
/* 120 */     encodeRowType6();
/*     */     
/* 122 */     if (this.currentRow == this.rows) {
/* 123 */       writeEOL();
/* 124 */       writeEOL();
/* 125 */       fill();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void encodeRowType6() throws IOException {
/* 131 */     encode2D();
/*     */   }
/*     */   
/*     */   private int[] getNextChanges(int pos, boolean white) {
/* 135 */     int[] result = { this.columns, this.columns };
/* 136 */     for (int i = 0; i < this.changesCurrentRowLength; i++) {
/* 137 */       if (pos < this.changesCurrentRow[i] || (pos == 0 && white)) {
/* 138 */         result[0] = this.changesCurrentRow[i];
/* 139 */         if (i + 1 < this.changesCurrentRowLength) {
/* 140 */           result[1] = this.changesCurrentRow[i + 1];
/*     */         }
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 146 */     return result;
/*     */   }
/*     */   
/*     */   private void writeRun(int runLength, boolean white) throws IOException {
/* 150 */     int nonterm = runLength / 64;
/* 151 */     Code[] codes = white ? WHITE_NONTERMINATING_CODES : BLACK_NONTERMINATING_CODES;
/* 152 */     while (nonterm > 0) {
/* 153 */       if (nonterm >= codes.length) {
/* 154 */         write((codes[codes.length - 1]).code, (codes[codes.length - 1]).length);
/* 155 */         nonterm -= codes.length;
/*     */         continue;
/*     */       } 
/* 158 */       write((codes[nonterm - 1]).code, (codes[nonterm - 1]).length);
/* 159 */       nonterm = 0;
/*     */     } 
/*     */ 
/*     */     
/* 163 */     Code c = white ? WHITE_TERMINATING_CODES[runLength % 64] : BLACK_TERMINATING_CODES[runLength % 64];
/* 164 */     write(c.code, c.length);
/*     */   }
/*     */   
/*     */   private void encode2D() throws IOException {
/* 168 */     boolean white = true;
/* 169 */     int index = 0;
/* 170 */     while (index < this.columns) {
/* 171 */       int[] nextChanges = getNextChanges(index, white);
/*     */       
/* 173 */       int[] nextRefs = getNextRefChanges(index, white);
/*     */       
/* 175 */       int difference = nextChanges[0] - nextRefs[0];
/* 176 */       if (nextChanges[0] > nextRefs[1]) {
/*     */         
/* 178 */         write(1, 4);
/* 179 */         index = nextRefs[1]; continue;
/*     */       } 
/* 181 */       if (difference > 3 || difference < -3) {
/*     */         
/* 183 */         write(1, 3);
/* 184 */         writeRun(nextChanges[0] - index, white);
/* 185 */         writeRun(nextChanges[1] - nextChanges[0], !white);
/* 186 */         index = nextChanges[1];
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 191 */       switch (difference) {
/*     */         case 0:
/* 193 */           write(1, 1);
/*     */           break;
/*     */         case 1:
/* 196 */           write(3, 3);
/*     */           break;
/*     */         case 2:
/* 199 */           write(3, 6);
/*     */           break;
/*     */         case 3:
/* 202 */           write(3, 7);
/*     */           break;
/*     */         case -1:
/* 205 */           write(2, 3);
/*     */           break;
/*     */         case -2:
/* 208 */           write(2, 6);
/*     */           break;
/*     */         case -3:
/* 211 */           write(2, 7);
/*     */           break;
/*     */       } 
/* 214 */       white = !white;
/* 215 */       index = nextRefs[0] + difference;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int[] getNextRefChanges(int a0, boolean white) {
/* 221 */     int[] result = { this.columns, this.columns };
/* 222 */     for (int i = white ? 0 : 1; i < this.changesReferenceRowLength; i += 2) {
/* 223 */       if (this.changesReferenceRow[i] > a0 || (a0 == 0 && i == 0)) {
/* 224 */         result[0] = this.changesReferenceRow[i];
/* 225 */         if (i + 1 < this.changesReferenceRowLength) {
/* 226 */           result[1] = this.changesReferenceRow[i + 1];
/*     */         }
/*     */         break;
/*     */       } 
/*     */     } 
/* 231 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private void write(int code, int codeLength) throws IOException {
/* 236 */     for (int i = 0; i < codeLength; i++) {
/* 237 */       boolean codeBit = ((code >> codeLength - i - 1 & 0x1) == 1);
/* 238 */       if (this.fillOrder == 1) {
/* 239 */         this.outputBuffer = (byte)(this.outputBuffer | (codeBit ? (1 << 7 - this.outputBufferBitLength % 8) : 0));
/*     */       } else {
/*     */         
/* 242 */         this.outputBuffer = (byte)(this.outputBuffer | (codeBit ? (1 << this.outputBufferBitLength % 8) : 0));
/*     */       } 
/* 244 */       this.outputBufferBitLength = (byte)(this.outputBufferBitLength + 1);
/*     */       
/* 246 */       if (this.outputBufferBitLength == 8) {
/* 247 */         this.stream.write(this.outputBuffer);
/* 248 */         clearOutputBuffer();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeEOL() throws IOException {
/* 254 */     write(1, 12);
/*     */   }
/*     */   
/*     */   private void fill() throws IOException {
/* 258 */     if (this.outputBufferBitLength != 0) {
/* 259 */       this.stream.write(this.outputBuffer);
/*     */     }
/* 261 */     clearOutputBuffer();
/*     */   }
/*     */   
/*     */   private void clearOutputBuffer() {
/* 265 */     this.outputBuffer = 0;
/* 266 */     this.outputBufferBitLength = 0;
/*     */   }
/*     */   private static class Code { final int code;
/*     */     
/*     */     private Code(int code, int length) {
/* 271 */       this.code = code;
/* 272 */       this.length = length;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final int length; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   private static final Code[] WHITE_TERMINATING_CODES = new Code[64];
/* 290 */   private static final Code[] WHITE_NONTERMINATING_CODES = new Code[40]; static { int i;
/* 291 */     for (i = 0; i < CCITTFaxDecoderStream.WHITE_CODES.length; i++) {
/* 292 */       int bitLength = i + 4;
/* 293 */       for (int j = 0; j < (CCITTFaxDecoderStream.WHITE_CODES[i]).length; j++) {
/* 294 */         int value = CCITTFaxDecoderStream.WHITE_RUN_LENGTHS[i][j];
/* 295 */         int code = CCITTFaxDecoderStream.WHITE_CODES[i][j];
/*     */         
/* 297 */         if (value < 64) {
/* 298 */           WHITE_TERMINATING_CODES[value] = new Code(code, bitLength);
/*     */         } else {
/*     */           
/* 301 */           WHITE_NONTERMINATING_CODES[value / 64 - 1] = new Code(code, bitLength);
/*     */         } 
/*     */       } 
/*     */     }  }
/*     */   
/* 306 */   private static final Code[] BLACK_TERMINATING_CODES = new Code[64];
/* 307 */   private static final Code[] BLACK_NONTERMINATING_CODES = new Code[40]; static {
/* 308 */     for (i = 0; i < CCITTFaxDecoderStream.BLACK_CODES.length; i++) {
/* 309 */       int bitLength = i + 2;
/* 310 */       for (int j = 0; j < (CCITTFaxDecoderStream.BLACK_CODES[i]).length; j++) {
/* 311 */         int value = CCITTFaxDecoderStream.BLACK_RUN_LENGTHS[i][j];
/* 312 */         int code = CCITTFaxDecoderStream.BLACK_CODES[i][j];
/*     */         
/* 314 */         if (value < 64) {
/* 315 */           BLACK_TERMINATING_CODES[value] = new Code(code, bitLength);
/*     */         } else {
/*     */           
/* 318 */           BLACK_NONTERMINATING_CODES[value / 64 - 1] = new Code(code, bitLength);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/CCITTFaxEncoderStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */