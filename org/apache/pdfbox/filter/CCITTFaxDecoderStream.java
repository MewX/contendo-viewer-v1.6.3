/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class CCITTFaxDecoderStream
/*     */   extends FilterInputStream
/*     */ {
/*     */   private final int columns;
/*     */   private final byte[] decodedRow;
/*     */   private int decodedLength;
/*     */   private int decodedPos;
/*     */   private final int fillOrder;
/*     */   private final int type;
/*     */   private int[] changesReferenceRow;
/*     */   private int[] changesCurrentRow;
/*     */   private int changesReferenceRowCount;
/*     */   private int changesCurrentRowCount;
/*     */   private boolean optionG32D = false;
/*     */   private boolean optionG3Fill = false;
/*     */   private boolean optionUncompressed = false;
/*     */   private boolean optionByteAligned = false;
/*     */   int buffer;
/*     */   int bufferPos;
/*     */   
/*     */   private void fetch() throws IOException {
/*     */     if (this.decodedPos >= this.decodedLength) {
/*     */       this.decodedLength = 0;
/*     */       try {
/*     */         decodeRow();
/*     */       } catch (EOFException e) {
/*     */         if (this.decodedLength != 0)
/*     */           throw e; 
/*     */         this.decodedLength = -1;
/*     */       } 
/*     */       this.decodedPos = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void decode1D() throws IOException {
/*     */     int index = 0;
/*     */     boolean white = true;
/*     */     this.changesCurrentRowCount = 0;
/*     */     do {
/*     */       int completeRun;
/*     */       if (white) {
/*     */         completeRun = decodeRun(whiteRunTree);
/*     */       } else {
/*     */         completeRun = decodeRun(blackRunTree);
/*     */       } 
/*     */       if (completeRun == -2000)
/*     */         continue; 
/*     */       index += completeRun;
/*     */       this.changesCurrentRow[this.changesCurrentRowCount++] = index;
/*     */       white = !white;
/*     */     } while (index < this.columns);
/*     */   }
/*     */   
/*  70 */   private int lastChangingElement = 0; private void decode2D() throws IOException { this.changesReferenceRowCount = this.changesCurrentRowCount; int[] tmp = this.changesCurrentRow; this.changesCurrentRow = this.changesReferenceRow; this.changesReferenceRow = tmp; boolean white = true; int index = 0; this.changesCurrentRowCount = 0; label39: while (index < this.columns) { int runLength, pChangingElement; Node n = codeTree.root; do { n = n.walk(readBit()); if (n == null)
/*     */           continue label39;  }
/*     */       while (!n.isLeaf); switch (n.value) { case -4000:
/*     */           runLength = decodeRun(white ? whiteRunTree : blackRunTree); index += runLength; this.changesCurrentRow[this.changesCurrentRowCount++] = index; runLength = decodeRun(white ? blackRunTree : whiteRunTree); index += runLength; this.changesCurrentRow[this.changesCurrentRowCount++] = index; continue;
/*     */         case -3000:
/*     */           pChangingElement = getNextChangingElement(index, white) + 1; if (pChangingElement >= this.changesReferenceRowCount) { index = this.columns; continue; }
/*     */            index = this.changesReferenceRow[pChangingElement]; continue; }
/*     */        int vChangingElement = getNextChangingElement(index, white); if (vChangingElement >= this.changesReferenceRowCount || vChangingElement == -1) { index = this.columns + n.value; }
/*     */       else
/*     */       { index = this.changesReferenceRow[vChangingElement] + n.value; }
/*     */        this.changesCurrentRow[this.changesCurrentRowCount] = index; this.changesCurrentRowCount++; white = !white; }
/*     */      }
/*  82 */   CCITTFaxDecoderStream(InputStream stream, int columns, int type, int fillOrder, long options) { super(stream);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 389 */     this.buffer = -1;
/* 390 */     this.bufferPos = -1; this.columns = columns; this.decodedRow = new byte[(columns + 7) / 8]; this.type = type; this.fillOrder = fillOrder; this.changesReferenceRow = new int[columns + 2]; this.changesCurrentRow = new int[columns + 2]; switch (type) { case 2: this.optionByteAligned = ((options & 0x8L) != 0L); break;case 3: this.optionG32D = ((options & 0x1L) != 0L); this.optionG3Fill = ((options & 0x4L) != 0L); this.optionUncompressed = ((options & 0x2L) != 0L); this.optionByteAligned = ((options & 0x8L) != 0L); break;case 4: this.optionUncompressed = ((options & 0x2L) != 0L); this.optionByteAligned = ((options & 0x4L) != 0L); break; }  } private int getNextChangingElement(int a0, boolean white) { int start = (this.lastChangingElement & 0xFFFFFFFE) + (white ? 0 : 1); if (start > 2) start -= 2;  if (a0 == 0) return start;  for (int i = start; i < this.changesReferenceRowCount; i += 2) { if (a0 < this.changesReferenceRow[i]) { this.lastChangingElement = i; return i; }  }  return -1; }
/*     */   private void decodeRowType2() throws IOException { if (this.optionByteAligned)
/*     */       resetBuffer();  decode1D(); }
/* 393 */   private boolean readBit() throws IOException { boolean isSet; if (this.bufferPos < 0 || this.bufferPos > 7) {
/* 394 */       this.buffer = this.in.read();
/*     */       
/* 396 */       if (this.buffer == -1) {
/* 397 */         throw new EOFException("Unexpected end of Huffman RLE stream");
/*     */       }
/*     */       
/* 400 */       this.bufferPos = 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 405 */     if (this.fillOrder == 1) {
/* 406 */       isSet = ((this.buffer >> 7 - this.bufferPos & 0x1) == 1);
/*     */     } else {
/*     */       
/* 409 */       isSet = ((this.buffer >> this.bufferPos & 0x1) == 1);
/*     */     } 
/*     */     
/* 412 */     this.bufferPos++;
/*     */     
/* 414 */     if (this.bufferPos > 7) {
/* 415 */       this.bufferPos = -1;
/*     */     }
/*     */     
/* 418 */     return isSet; } private void decodeRowType4() throws IOException { if (this.optionByteAligned)
/*     */       resetBuffer();  label17: while (true) { Node n = eolOnlyTree.root; do { n = n.walk(readBit()); if (n == null)
/*     */           continue label17;  } while (!n.isLeaf); break; }  if (!this.optionG32D || readBit()) { decode1D(); } else { decode2D(); }  }
/*     */   private void decodeRowType6() throws IOException { if (this.optionByteAligned)
/*     */       resetBuffer();  decode2D(); }
/* 423 */   public int read() throws IOException { if (this.decodedLength < 0) {
/* 424 */       return 0;
/*     */     }
/*     */     
/* 427 */     if (this.decodedPos >= this.decodedLength) {
/* 428 */       fetch();
/*     */       
/* 430 */       if (this.decodedLength < 0) {
/* 431 */         return 0;
/*     */       }
/*     */     } 
/*     */     
/* 435 */     return this.decodedRow[this.decodedPos++] & 0xFF; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 440 */     if (this.decodedLength < 0) {
/*     */       
/* 442 */       Arrays.fill(b, off, off + len, (byte)0);
/* 443 */       return len;
/*     */     } 
/*     */     
/* 446 */     if (this.decodedPos >= this.decodedLength) {
/* 447 */       fetch();
/*     */       
/* 449 */       if (this.decodedLength < 0) {
/* 450 */         Arrays.fill(b, off, off + len, (byte)0);
/* 451 */         return len;
/*     */       } 
/*     */     } 
/*     */     
/* 455 */     int read = Math.min(this.decodedLength - this.decodedPos, len);
/* 456 */     System.arraycopy(this.decodedRow, this.decodedPos, b, off, read);
/* 457 */     this.decodedPos += read;
/*     */     
/* 459 */     return read; }
/*     */   private void decodeRow() throws IOException { switch (this.type) { case 2: decodeRowType2(); break;case 3: decodeRowType4(); break;case 4: decodeRowType6(); break; }  int index = 0; boolean white = true; this.lastChangingElement = 0; for (int i = 0; i <= this.changesCurrentRowCount; i++) { int nextChange = this.columns; if (i != this.changesCurrentRowCount) nextChange = this.changesCurrentRow[i];  if (nextChange > this.columns) nextChange = this.columns;  int byteIndex = index / 8; while (index % 8 != 0 && nextChange - index > 0) { this.decodedRow[byteIndex] = (byte)(this.decodedRow[byteIndex] | (white ? 0 : (1 << 7 - index % 8))); index++; }  if (index % 8 == 0) { byteIndex = index / 8; byte value = (byte)(white ? 0 : 255); while (nextChange - index > 7) { this.decodedRow[byteIndex] = value; index += 8; byteIndex++; }  }  while (nextChange - index > 0) { if (index % 8 == 0) this.decodedRow[byteIndex] = 0;  this.decodedRow[byteIndex] = (byte)(this.decodedRow[byteIndex] | (white ? 0 : (1 << 7 - index % 8))); index++; }  white = !white; }  if (index != this.columns) throw new IOException("Sum of run-lengths does not equal scan line width: " + index + " > " + this.columns);  this.decodedLength = (index + 7) / 8; }
/*     */   private int decodeRun(Tree tree) throws IOException { int total = 0; Node n = tree.root; while (true) { boolean bit = readBit(); n = n.walk(bit); if (n == null)
/*     */         throw new IOException("Unknown code in Huffman RLE stream");  if (n.isLeaf) { total += n.value; if (n.value < 64)
/*     */           return total;  n = tree.root; }  }  }
/* 464 */   private void resetBuffer() throws IOException { this.bufferPos = -1; } public long skip(long n) throws IOException { if (this.decodedLength < 0) {
/* 465 */       return -1L;
/*     */     }
/*     */     
/* 468 */     if (this.decodedPos >= this.decodedLength) {
/* 469 */       fetch();
/*     */       
/* 471 */       if (this.decodedLength < 0) {
/* 472 */         return -1L;
/*     */       }
/*     */     } 
/*     */     
/* 476 */     int skipped = (int)Math.min((this.decodedLength - this.decodedPos), n);
/* 477 */     this.decodedPos += skipped;
/*     */     
/* 479 */     return skipped; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean markSupported() {
/* 484 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void reset() throws IOException {
/* 489 */     throw new IOException("mark/reset not supported");
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class Node
/*     */   {
/*     */     Node left;
/*     */     Node right;
/*     */     int value;
/*     */     boolean canBeFill = false;
/*     */     boolean isLeaf = false;
/*     */     
/*     */     void set(boolean next, Node node) {
/* 502 */       if (!next) {
/* 503 */         this.left = node;
/*     */       } else {
/*     */         
/* 506 */         this.right = node;
/*     */       } 
/*     */     }
/*     */     
/*     */     Node walk(boolean next) {
/* 511 */       return next ? this.right : this.left;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 516 */       return "[leaf=" + this.isLeaf + ", value=" + this.value + ", canBeFill=" + this.canBeFill + "]";
/*     */     }
/*     */     
/*     */     private Node() {} }
/*     */   
/* 521 */   private static final class Tree { final CCITTFaxDecoderStream.Node root = new CCITTFaxDecoderStream.Node();
/*     */     
/*     */     void fill(int depth, int path, int value) throws IOException {
/* 524 */       CCITTFaxDecoderStream.Node current = this.root;
/*     */       
/* 526 */       for (int i = 0; i < depth; i++) {
/* 527 */         int bitPos = depth - 1 - i;
/* 528 */         boolean isSet = ((path >> bitPos & 0x1) == 1);
/* 529 */         CCITTFaxDecoderStream.Node next = current.walk(isSet);
/*     */         
/* 531 */         if (next == null) {
/* 532 */           next = new CCITTFaxDecoderStream.Node();
/*     */           
/* 534 */           if (i == depth - 1) {
/* 535 */             next.value = value;
/* 536 */             next.isLeaf = true;
/*     */           } 
/*     */           
/* 539 */           if (path == 0) {
/* 540 */             next.canBeFill = true;
/*     */           }
/*     */           
/* 543 */           current.set(isSet, next);
/*     */         
/*     */         }
/* 546 */         else if (next.isLeaf) {
/* 547 */           throw new IOException("node is leaf, no other following");
/*     */         } 
/*     */ 
/*     */         
/* 551 */         current = next;
/*     */       } 
/*     */     }
/*     */     
/*     */     void fill(int depth, int path, CCITTFaxDecoderStream.Node node) throws IOException {
/* 556 */       CCITTFaxDecoderStream.Node current = this.root;
/*     */       
/* 558 */       for (int i = 0; i < depth; i++) {
/* 559 */         int bitPos = depth - 1 - i;
/* 560 */         boolean isSet = ((path >> bitPos & 0x1) == 1);
/* 561 */         CCITTFaxDecoderStream.Node next = current.walk(isSet);
/*     */         
/* 563 */         if (next == null) {
/* 564 */           if (i == depth - 1) {
/* 565 */             next = node;
/*     */           } else {
/*     */             
/* 568 */             next = new CCITTFaxDecoderStream.Node();
/*     */           } 
/*     */           
/* 571 */           if (path == 0) {
/* 572 */             next.canBeFill = true;
/*     */           }
/*     */           
/* 575 */           current.set(isSet, next);
/*     */         
/*     */         }
/* 578 */         else if (next.isLeaf) {
/* 579 */           throw new IOException("node is leaf, no other following");
/*     */         } 
/*     */ 
/*     */         
/* 583 */         current = next;
/*     */       } 
/*     */     }
/*     */     
/*     */     private Tree() {} }
/* 588 */   static final short[][] BLACK_CODES = new short[][] { { 2, 3 }, { 2, 3 }, { 2, 3 }, { 3 }, { 4, 5 }, { 4, 5, 7 }, { 4, 7 }, { 24 }, { 23, 24, 55, 8, 15 }, { 23, 24, 40, 55, 103, 104, 108, 8, 12, 13 }, { 18, 19, 20, 21, 22, 23, 28, 29, 30, 31, 36, 39, 40, 43, 44, 51, 52, 53, 55, 56, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 200, 201, 202, 203, 204, 205, 210, 211, 212, 213, 214, 215, 218, 219 }, { 74, 75, 76, 77, 82, 83, 84, 85, 90, 91, 100, 101, 108, 109, 114, 115, 116, 117, 118, 119 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 630 */   static final short[][] BLACK_RUN_LENGTHS = new short[][] { { 3, 2 }, { 1, 4 }, { 6, 5 }, { 7 }, { 9, 8 }, { 10, 11, 12 }, { 13, 14 }, { 15 }, { 16, 17, 0, 18, 64 }, { 24, 25, 23, 22, 19, 20, 21, 1792, 1856, 1920 }, { 1984, 2048, 2112, 2176, 2240, 2304, 2368, 2432, 2496, 2560, 52, 55, 56, 59, 60, 320, 384, 448, 53, 54, 50, 51, 44, 45, 46, 47, 57, 58, 61, 256, 48, 49, 62, 63, 30, 31, 32, 33, 40, 41, 128, 192, 26, 27, 28, 29, 34, 35, 36, 37, 38, 39, 42, 43 }, { 640, 704, 768, 832, 1280, 1344, 1408, 1472, 1536, 1600, 1664, 1728, 512, 576, 896, 960, 1024, 1088, 1152, 1216 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 672 */   public static final short[][] WHITE_CODES = new short[][] { { 7, 8, 11, 12, 14, 15 }, { 18, 19, 20, 27, 7, 8 }, { 23, 24, 42, 43, 3, 52, 53, 7, 8 }, { 19, 23, 24, 36, 39, 40, 43, 3, 55, 4, 8, 12 }, { 18, 19, 20, 21, 22, 23, 26, 27, 2, 36, 37, 40, 41, 42, 43, 44, 45, 3, 50, 51, 52, 53, 54, 55, 4, 74, 75, 5, 82, 83, 84, 85, 88, 89, 90, 91, 100, 101, 103, 104, 10, 11 }, { 152, 153, 154, 155, 204, 205, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219 }, {}, { 8, 12, 13 }, { 18, 19, 20, 21, 22, 23, 28, 29, 30, 31 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 703 */   public static final short[][] WHITE_RUN_LENGTHS = new short[][] { { 2, 3, 4, 5, 6, 7 }, { 128, 8, 9, 64, 10, 11 }, { 192, 1664, 16, 17, 13, 14, 15, 1, 12 }, { 26, 21, 28, 27, 18, 24, 25, 22, 256, 23, 20, 19 }, { 33, 34, 35, 36, 37, 38, 31, 32, 29, 53, 54, 39, 40, 41, 42, 43, 44, 30, 61, 62, 63, 0, 320, 384, 45, 59, 60, 46, 49, 50, 51, 52, 55, 56, 57, 58, 448, 512, 640, 576, 47, 48 }, { 1472, 1536, 1600, 1728, 704, 768, 832, 896, 960, 1024, 1088, 1152, 1216, 1280, 1344, 1408 }, {}, { 1792, 1856, 1920 }, { 1984, 2048, 2112, 2176, 2240, 2304, 2368, 2432, 2496, 2560 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 746 */   static final Node EOL = new Node(); static {
/* 747 */     EOL.isLeaf = true;
/* 748 */     EOL.value = -2000;
/* 749 */   } static final Node FILL = new Node(); static final Tree blackRunTree; static final Tree whiteRunTree; static {
/* 750 */     FILL.value = -1000;
/* 751 */     FILL.left = FILL;
/* 752 */     FILL.right = EOL;
/*     */   }
/* 754 */   static final Tree eolOnlyTree = new Tree(); static final Tree codeTree; static final int VALUE_EOL = -2000; static final int VALUE_FILL = -1000; static final int VALUE_PASSMODE = -3000; static final int VALUE_HMODE = -4000; static {
/*     */     try {
/* 756 */       eolOnlyTree.fill(12, 0, FILL);
/* 757 */       eolOnlyTree.fill(12, 1, EOL);
/*     */     }
/* 759 */     catch (IOException e) {
/* 760 */       throw new AssertionError(e);
/*     */     } 
/*     */     
/* 763 */     blackRunTree = new Tree();
/*     */     try {
/* 765 */       for (int i = 0; i < BLACK_CODES.length; i++) {
/* 766 */         for (int j = 0; j < (BLACK_CODES[i]).length; j++) {
/* 767 */           blackRunTree.fill(i + 2, BLACK_CODES[i][j], BLACK_RUN_LENGTHS[i][j]);
/*     */         }
/*     */       } 
/* 770 */       blackRunTree.fill(12, 0, FILL);
/* 771 */       blackRunTree.fill(12, 1, EOL);
/*     */     }
/* 773 */     catch (IOException e) {
/* 774 */       throw new AssertionError(e);
/*     */     } 
/*     */     
/* 777 */     whiteRunTree = new Tree();
/*     */     try {
/* 779 */       for (int i = 0; i < WHITE_CODES.length; i++) {
/* 780 */         for (int j = 0; j < (WHITE_CODES[i]).length; j++) {
/* 781 */           whiteRunTree.fill(i + 4, WHITE_CODES[i][j], WHITE_RUN_LENGTHS[i][j]);
/*     */         }
/*     */       } 
/*     */       
/* 785 */       whiteRunTree.fill(12, 0, FILL);
/* 786 */       whiteRunTree.fill(12, 1, EOL);
/*     */     }
/* 788 */     catch (IOException e) {
/* 789 */       throw new AssertionError(e);
/*     */     } 
/*     */     
/* 792 */     codeTree = new Tree();
/*     */     try {
/* 794 */       codeTree.fill(4, 1, -3000);
/* 795 */       codeTree.fill(3, 1, -4000);
/* 796 */       codeTree.fill(1, 1, 0);
/* 797 */       codeTree.fill(3, 3, 1);
/* 798 */       codeTree.fill(6, 3, 2);
/* 799 */       codeTree.fill(7, 3, 3);
/* 800 */       codeTree.fill(3, 2, -1);
/* 801 */       codeTree.fill(6, 2, -2);
/* 802 */       codeTree.fill(7, 2, -3);
/*     */     }
/* 804 */     catch (IOException e) {
/* 805 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/CCITTFaxDecoderStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */