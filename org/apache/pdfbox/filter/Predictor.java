/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Arrays;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Predictor
/*     */ {
/*     */   static void decodePredictorRow(int predictor, int colors, int bitsPerComponent, int columns, byte[] actline, byte[] lastline) {
/*     */     int elements, p;
/*  53 */     if (predictor == 1) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  58 */     int bitsPerPixel = colors * bitsPerComponent;
/*  59 */     int bytesPerPixel = (bitsPerPixel + 7) / 8;
/*  60 */     int rowlength = actline.length;
/*  61 */     switch (predictor) {
/*     */ 
/*     */       
/*     */       case 2:
/*  65 */         if (bitsPerComponent == 8) {
/*     */ 
/*     */           
/*  68 */           for (int i = bytesPerPixel; i < rowlength; i++) {
/*     */             
/*  70 */             int sub = actline[i] & 0xFF;
/*  71 */             int left = actline[i - bytesPerPixel] & 0xFF;
/*  72 */             actline[i] = (byte)(sub + left);
/*     */           } 
/*     */           break;
/*     */         } 
/*  76 */         if (bitsPerComponent == 16) {
/*     */           
/*  78 */           for (int i = bytesPerPixel; i < rowlength; i += 2) {
/*     */             
/*  80 */             int sub = ((actline[i] & 0xFF) << 8) + (actline[i + 1] & 0xFF);
/*  81 */             int left = ((actline[i - bytesPerPixel] & 0xFF) << 8) + (actline[i - bytesPerPixel + 1] & 0xFF);
/*     */             
/*  83 */             actline[i] = (byte)(sub + left >> 8 & 0xFF);
/*  84 */             actline[i + 1] = (byte)(sub + left & 0xFF);
/*     */           } 
/*     */           break;
/*     */         } 
/*  88 */         if (bitsPerComponent == 1 && colors == 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  94 */           for (int i = 0; i < rowlength; i++) {
/*     */             
/*  96 */             for (int bit = 7; bit >= 0; bit--) {
/*     */               
/*  98 */               int sub = actline[i] >> bit & 0x1;
/*  99 */               if (i != 0 || bit != 7) {
/*     */                 int left;
/*     */ 
/*     */ 
/*     */                 
/* 104 */                 if (bit == 7) {
/*     */ 
/*     */                   
/* 107 */                   left = actline[i - 1] & 0x1;
/*     */                 
/*     */                 }
/*     */                 else {
/*     */                   
/* 112 */                   left = actline[i] >> bit + 1 & 0x1;
/*     */                 } 
/* 114 */                 if ((sub + left & 0x1) == 0) {
/*     */ 
/*     */                   
/* 117 */                   actline[i] = (byte)(actline[i] & (1 << bit ^ 0xFFFFFFFF));
/*     */                 
/*     */                 }
/*     */                 else {
/*     */                   
/* 122 */                   actline[i] = (byte)(actline[i] | 1 << bit);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         } 
/* 129 */         elements = columns * colors;
/* 130 */         for (p = colors; p < elements; p++) {
/*     */           
/* 132 */           int bytePosSub = p * bitsPerComponent / 8;
/* 133 */           int bitPosSub = 8 - p * bitsPerComponent % 8 - bitsPerComponent;
/* 134 */           int bytePosLeft = (p - colors) * bitsPerComponent / 8;
/* 135 */           int bitPosLeft = 8 - (p - colors) * bitsPerComponent % 8 - bitsPerComponent;
/*     */           
/* 137 */           int sub = getBitSeq(actline[bytePosSub], bitPosSub, bitsPerComponent);
/* 138 */           int left = getBitSeq(actline[bytePosLeft], bitPosLeft, bitsPerComponent);
/* 139 */           actline[bytePosSub] = (byte)calcSetBitSeq(actline[bytePosSub], bitPosSub, bitsPerComponent, sub + left);
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 11:
/* 148 */         for (p = bytesPerPixel; p < rowlength; p++) {
/*     */           
/* 150 */           int sub = actline[p];
/* 151 */           int left = actline[p - bytesPerPixel];
/* 152 */           actline[p] = (byte)(sub + left);
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 12:
/* 157 */         for (p = 0; p < rowlength; p++) {
/*     */           
/* 159 */           int up = actline[p] & 0xFF;
/* 160 */           int prior = lastline[p] & 0xFF;
/* 161 */           actline[p] = (byte)(up + prior & 0xFF);
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 13:
/* 166 */         for (p = 0; p < rowlength; p++) {
/*     */           
/* 168 */           int avg = actline[p] & 0xFF;
/* 169 */           int left = (p - bytesPerPixel >= 0) ? (actline[p - bytesPerPixel] & 0xFF) : 0;
/* 170 */           int up = lastline[p] & 0xFF;
/* 171 */           actline[p] = (byte)(avg + (left + up) / 2 & 0xFF);
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 14:
/* 176 */         for (p = 0; p < rowlength; p++) {
/*     */           
/* 178 */           int paeth = actline[p] & 0xFF;
/* 179 */           int a = (p - bytesPerPixel >= 0) ? (actline[p - bytesPerPixel] & 0xFF) : 0;
/* 180 */           int b = lastline[p] & 0xFF;
/* 181 */           int c = (p - bytesPerPixel >= 0) ? (lastline[p - bytesPerPixel] & 0xFF) : 0;
/* 182 */           int value = a + b - c;
/* 183 */           int absa = Math.abs(value - a);
/* 184 */           int absb = Math.abs(value - b);
/* 185 */           int absc = Math.abs(value - c);
/*     */           
/* 187 */           if (absa <= absb && absa <= absc) {
/*     */             
/* 189 */             actline[p] = (byte)(paeth + a & 0xFF);
/*     */           }
/* 191 */           else if (absb <= absc) {
/*     */             
/* 193 */             actline[p] = (byte)(paeth + b & 0xFF);
/*     */           }
/*     */           else {
/*     */             
/* 197 */             actline[p] = (byte)(paeth + c & 0xFF);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void decodePredictor(int predictor, int colors, int bitsPerComponent, int columns, InputStream in, OutputStream out) throws IOException {
/* 209 */     if (predictor == 1) {
/*     */ 
/*     */       
/* 212 */       IOUtils.copy(in, out);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 217 */       int rowlength = calculateRowLength(colors, bitsPerComponent, columns);
/* 218 */       byte[] actline = new byte[rowlength];
/* 219 */       byte[] lastline = new byte[rowlength];
/*     */       
/* 221 */       int linepredictor = predictor;
/*     */       
/* 223 */       while (in.available() > 0) {
/*     */ 
/*     */         
/* 226 */         if (predictor >= 10) {
/*     */ 
/*     */ 
/*     */           
/* 230 */           linepredictor = in.read();
/* 231 */           if (linepredictor == -1) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/* 236 */           linepredictor += 10;
/*     */         } 
/*     */ 
/*     */         
/* 240 */         int offset = 0; int i;
/* 241 */         while (offset < rowlength && (i = in.read(actline, offset, rowlength - offset)) != -1)
/*     */         {
/* 243 */           offset += i;
/*     */         }
/*     */         
/* 246 */         decodePredictorRow(linepredictor, colors, bitsPerComponent, columns, actline, lastline);
/* 247 */         System.arraycopy(actline, 0, lastline, 0, rowlength);
/* 248 */         out.write(actline);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static int calculateRowLength(int colors, int bitsPerComponent, int columns) {
/* 255 */     int bitsPerPixel = colors * bitsPerComponent;
/* 256 */     return (columns * bitsPerPixel + 7) / 8;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static int getBitSeq(int by, int startBit, int bitSize) {
/* 262 */     int mask = (1 << bitSize) - 1;
/* 263 */     return by >>> startBit & mask;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static int calcSetBitSeq(int by, int startBit, int bitSize, int val) {
/* 269 */     int mask = (1 << bitSize) - 1;
/* 270 */     int truncatedVal = val & mask;
/* 271 */     mask = mask << startBit ^ 0xFFFFFFFF;
/* 272 */     return by & mask | truncatedVal << startBit;
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
/*     */   static OutputStream wrapPredictor(OutputStream out, COSDictionary decodeParams) {
/* 286 */     int predictor = decodeParams.getInt(COSName.PREDICTOR);
/* 287 */     if (predictor > 1) {
/*     */       
/* 289 */       int colors = Math.min(decodeParams.getInt(COSName.COLORS, 1), 32);
/* 290 */       int bitsPerPixel = decodeParams.getInt(COSName.BITS_PER_COMPONENT, 8);
/* 291 */       int columns = decodeParams.getInt(COSName.COLUMNS, 1);
/*     */       
/* 293 */       return new PredictorOutputStream(out, predictor, colors, bitsPerPixel, columns);
/*     */     } 
/*     */ 
/*     */     
/* 297 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class PredictorOutputStream
/*     */     extends FilterOutputStream
/*     */   {
/*     */     private int predictor;
/*     */ 
/*     */     
/*     */     private final int colors;
/*     */     
/*     */     private final int bitsPerComponent;
/*     */     
/*     */     private final int columns;
/*     */     
/*     */     private final int rowLength;
/*     */     
/*     */     private final boolean predictorPerRow;
/*     */     
/*     */     private byte[] currentRow;
/*     */     
/*     */     private byte[] lastRow;
/*     */     
/* 322 */     private int currentRowData = 0;
/*     */     
/*     */     private boolean predictorRead = false;
/*     */ 
/*     */     
/*     */     PredictorOutputStream(OutputStream out, int predictor, int colors, int bitsPerComponent, int columns) {
/* 328 */       super(out);
/* 329 */       this.predictor = predictor;
/* 330 */       this.colors = colors;
/* 331 */       this.bitsPerComponent = bitsPerComponent;
/* 332 */       this.columns = columns;
/* 333 */       this.rowLength = Predictor.calculateRowLength(colors, bitsPerComponent, columns);
/* 334 */       this.predictorPerRow = (predictor >= 10);
/* 335 */       this.currentRow = new byte[this.rowLength];
/* 336 */       this.lastRow = new byte[this.rowLength];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void write(byte[] bytes) throws IOException {
/* 342 */       write(bytes, 0, bytes.length);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void write(byte[] bytes, int off, int len) throws IOException {
/* 348 */       int currentOffset = off;
/* 349 */       int maxOffset = currentOffset + len;
/* 350 */       while (currentOffset < maxOffset) {
/*     */         
/* 352 */         if (this.predictorPerRow && this.currentRowData == 0 && !this.predictorRead) {
/*     */ 
/*     */ 
/*     */           
/* 356 */           this.predictor = bytes[currentOffset] + 10;
/* 357 */           currentOffset++;
/* 358 */           this.predictorRead = true;
/*     */           
/*     */           continue;
/*     */         } 
/* 362 */         int toRead = Math.min(this.rowLength - this.currentRowData, maxOffset - currentOffset);
/* 363 */         System.arraycopy(bytes, currentOffset, this.currentRow, this.currentRowData, toRead);
/* 364 */         this.currentRowData += toRead;
/* 365 */         currentOffset += toRead;
/*     */ 
/*     */ 
/*     */         
/* 369 */         if (this.currentRowData == this.currentRow.length)
/*     */         {
/* 371 */           decodeAndWriteRow();
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private void decodeAndWriteRow() throws IOException {
/* 379 */       Predictor.decodePredictorRow(this.predictor, this.colors, this.bitsPerComponent, this.columns, this.currentRow, this.lastRow);
/* 380 */       this.out.write(this.currentRow);
/* 381 */       flipRows();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void flipRows() {
/* 390 */       byte[] temp = this.lastRow;
/* 391 */       this.lastRow = this.currentRow;
/* 392 */       this.currentRow = temp;
/* 393 */       this.currentRowData = 0;
/* 394 */       this.predictorRead = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void flush() throws IOException {
/* 401 */       if (this.currentRowData > 0) {
/*     */         
/* 403 */         Arrays.fill(this.currentRow, this.currentRowData, this.rowLength, (byte)0);
/* 404 */         decodeAndWriteRow();
/*     */       } 
/* 406 */       super.flush();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void write(int i) throws IOException {
/* 412 */       throw new UnsupportedOperationException("Not supported");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/Predictor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */