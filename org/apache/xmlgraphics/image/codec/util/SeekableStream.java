/*     */ package org.apache.xmlgraphics.image.codec.util;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SeekableStream
/*     */   extends InputStream
/*     */   implements DataInput
/*     */ {
/*     */   public static SeekableStream wrapInputStream(InputStream is, boolean canSeekBackwards) {
/* 100 */     SeekableStream stream = null;
/*     */     
/* 102 */     if (canSeekBackwards) {
/*     */       try {
/* 104 */         stream = new FileCacheSeekableStream(is);
/* 105 */       } catch (Exception e) {
/* 106 */         stream = new MemoryCacheSeekableStream(is);
/*     */       } 
/*     */     } else {
/* 109 */       stream = new ForwardSeekableStream(is);
/*     */     } 
/* 111 */     return stream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   protected long markPos = -1L;
/*     */   
/*     */   public abstract int read() throws IOException;
/*     */   
/*     */   public abstract int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public synchronized void mark(int readLimit) {
/*     */     try {
/* 200 */       this.markPos = getFilePointer();
/* 201 */     } catch (IOException e) {
/* 202 */       this.markPos = -1L;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void reset() throws IOException {
/* 212 */     if (this.markPos != -1L) {
/* 213 */       seek(this.markPos);
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
/*     */   public boolean markSupported() {
/* 225 */     return canSeekBackwards();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSeekBackwards() {
/* 234 */     return false;
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
/*     */   public abstract long getFilePointer() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void seek(long paramLong) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void readFully(byte[] b) throws IOException {
/* 278 */     readFully(b, 0, b.length);
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
/*     */   public final void readFully(byte[] b, int off, int len) throws IOException {
/* 297 */     int n = 0;
/*     */     do {
/* 299 */       int count = read(b, off + n, len - n);
/* 300 */       if (count < 0) {
/* 301 */         throw new EOFException();
/*     */       }
/* 303 */       n += count;
/* 304 */     } while (n < len);
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
/*     */   public int skipBytes(int n) throws IOException {
/* 326 */     if (n <= 0) {
/* 327 */       return 0;
/*     */     }
/* 329 */     return (int)skip(n);
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
/*     */   public final boolean readBoolean() throws IOException {
/* 345 */     int ch = read();
/* 346 */     if (ch < 0) {
/* 347 */       throw new EOFException();
/*     */     }
/* 349 */     return (ch != 0);
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
/*     */   public final byte readByte() throws IOException {
/* 371 */     int ch = read();
/* 372 */     if (ch < 0) {
/* 373 */       throw new EOFException();
/*     */     }
/* 375 */     return (byte)ch;
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
/*     */   public final int readUnsignedByte() throws IOException {
/* 392 */     int ch = read();
/* 393 */     if (ch < 0) {
/* 394 */       throw new EOFException();
/*     */     }
/* 396 */     return ch;
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
/*     */   public final short readShort() throws IOException {
/* 421 */     int ch1 = read();
/* 422 */     int ch2 = read();
/* 423 */     if ((ch1 | ch2) < 0) {
/* 424 */       throw new EOFException();
/*     */     }
/* 426 */     return (short)((ch1 << 8) + (ch2 << 0));
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
/*     */   public final short readShortLE() throws IOException {
/* 451 */     int ch1 = read();
/* 452 */     int ch2 = read();
/* 453 */     if ((ch1 | ch2) < 0) {
/* 454 */       throw new EOFException();
/*     */     }
/* 456 */     return (short)((ch2 << 8) + (ch1 << 0));
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
/*     */   public final int readUnsignedShort() throws IOException {
/* 480 */     int ch1 = read();
/* 481 */     int ch2 = read();
/* 482 */     if ((ch1 | ch2) < 0) {
/* 483 */       throw new EOFException();
/*     */     }
/* 485 */     return (ch1 << 8) + (ch2 << 0);
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
/*     */   public final int readUnsignedShortLE() throws IOException {
/* 510 */     int ch1 = read();
/* 511 */     int ch2 = read();
/* 512 */     if ((ch1 | ch2) < 0) {
/* 513 */       throw new EOFException();
/*     */     }
/* 515 */     return (ch2 << 8) + (ch1 << 0);
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
/*     */   public final char readChar() throws IOException {
/* 538 */     int ch1 = read();
/* 539 */     int ch2 = read();
/* 540 */     if ((ch1 | ch2) < 0) {
/* 541 */       throw new EOFException();
/*     */     }
/* 543 */     return (char)((ch1 << 8) + (ch2 << 0));
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
/*     */   public final char readCharLE() throws IOException {
/* 567 */     int ch1 = read();
/* 568 */     int ch2 = read();
/* 569 */     if ((ch1 | ch2) < 0) {
/* 570 */       throw new EOFException();
/*     */     }
/* 572 */     return (char)((ch2 << 8) + (ch1 << 0));
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
/*     */   public final int readInt() throws IOException {
/* 596 */     int ch1 = read();
/* 597 */     int ch2 = read();
/* 598 */     int ch3 = read();
/* 599 */     int ch4 = read();
/* 600 */     if ((ch1 | ch2 | ch3 | ch4) < 0) {
/* 601 */       throw new EOFException();
/*     */     }
/* 603 */     return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
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
/*     */   public final int readIntLE() throws IOException {
/* 628 */     int ch1 = read();
/* 629 */     int ch2 = read();
/* 630 */     int ch3 = read();
/* 631 */     int ch4 = read();
/* 632 */     if ((ch1 | ch2 | ch3 | ch4) < 0) {
/* 633 */       throw new EOFException();
/*     */     }
/* 635 */     return (ch4 << 24) + (ch3 << 16) + (ch2 << 8) + (ch1 << 0);
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
/*     */   public final long readUnsignedInt() throws IOException {
/* 659 */     long ch1 = read();
/* 660 */     long ch2 = read();
/* 661 */     long ch3 = read();
/* 662 */     long ch4 = read();
/* 663 */     if ((ch1 | ch2 | ch3 | ch4) < 0L) {
/* 664 */       throw new EOFException();
/*     */     }
/* 666 */     return (ch1 << 24L) + (ch2 << 16L) + (ch3 << 8L) + (ch4 << 0L);
/*     */   }
/*     */   
/* 669 */   private byte[] ruileBuf = new byte[4];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final long readUnsignedIntLE() throws IOException {
/* 693 */     readFully(this.ruileBuf);
/* 694 */     long ch1 = (this.ruileBuf[0] & 0xFF);
/* 695 */     long ch2 = (this.ruileBuf[1] & 0xFF);
/* 696 */     long ch3 = (this.ruileBuf[2] & 0xFF);
/* 697 */     long ch4 = (this.ruileBuf[3] & 0xFF);
/*     */     
/* 699 */     return (ch4 << 24L) + (ch3 << 16L) + (ch2 << 8L) + (ch1 << 0L);
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
/*     */   public final long readLong() throws IOException {
/* 731 */     return (readInt() << 32L) + (readInt() & 0xFFFFFFFFL);
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
/*     */   public final long readLongLE() throws IOException {
/* 764 */     int i1 = readIntLE();
/* 765 */     int i2 = readIntLE();
/* 766 */     return (i2 << 32L) + (i1 & 0xFFFFFFFFL);
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
/*     */   public final float readFloat() throws IOException {
/* 787 */     return Float.intBitsToFloat(readInt());
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
/*     */   public final float readFloatLE() throws IOException {
/* 809 */     return Float.intBitsToFloat(readIntLE());
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
/*     */   public final double readDouble() throws IOException {
/* 830 */     return Double.longBitsToDouble(readLong());
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
/*     */   public final double readDoubleLE() throws IOException {
/* 852 */     return Double.longBitsToDouble(readLongLE());
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
/*     */   public final String readLine() throws IOException {
/* 879 */     StringBuffer input = new StringBuffer();
/* 880 */     int c = -1;
/* 881 */     boolean eol = false;
/*     */     
/* 883 */     while (!eol) {
/* 884 */       long cur; c = read();
/* 885 */       switch (c) {
/*     */         case -1:
/*     */         case 10:
/* 888 */           eol = true;
/*     */           continue;
/*     */         case 13:
/* 891 */           eol = true;
/* 892 */           cur = getFilePointer();
/* 893 */           if (read() != 10) {
/* 894 */             seek(cur);
/*     */           }
/*     */           continue;
/*     */       } 
/* 898 */       input.append((char)c);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 903 */     if (c == -1 && input.length() == 0) {
/* 904 */       return null;
/*     */     }
/* 906 */     return input.toString();
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
/*     */   public final String readUTF() throws IOException {
/* 932 */     return DataInputStream.readUTF(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 940 */     super.finalize();
/* 941 */     close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/util/SeekableStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */