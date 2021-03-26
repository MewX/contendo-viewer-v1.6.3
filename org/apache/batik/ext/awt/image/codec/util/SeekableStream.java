/*     */ package org.apache.batik.ext.awt.image.codec.util;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SeekableStream
/*     */   extends InputStream
/*     */   implements DataInput
/*     */ {
/*     */   public static SeekableStream wrapInputStream(InputStream is, boolean canSeekBackwards) {
/* 104 */     SeekableStream stream = null;
/*     */     
/* 106 */     if (canSeekBackwards) {
/*     */       try {
/* 108 */         stream = new FileCacheSeekableStream(is);
/* 109 */       } catch (Exception e) {
/* 110 */         stream = new MemoryCacheSeekableStream(is);
/*     */       } 
/*     */     } else {
/* 113 */       stream = new ForwardSeekableStream(is);
/*     */     } 
/* 115 */     return stream;
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
/* 196 */   protected long markPos = -1L;
/*     */   
/*     */   public abstract int read() throws IOException;
/*     */   
/*     */   public abstract int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public synchronized void mark(int readLimit) {
/*     */     try {
/* 204 */       this.markPos = getFilePointer();
/* 205 */     } catch (IOException e) {
/* 206 */       this.markPos = -1L;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void reset() throws IOException {
/* 216 */     if (this.markPos != -1L) {
/* 217 */       seek(this.markPos);
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
/* 229 */     return canSeekBackwards();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSeekBackwards() {
/* 238 */     return false;
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
/* 282 */     readFully(b, 0, b.length);
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
/* 301 */     int n = 0;
/*     */     do {
/* 303 */       int count = read(b, off + n, len - n);
/* 304 */       if (count < 0)
/* 305 */         throw new EOFException(); 
/* 306 */       n += count;
/* 307 */     } while (n < len);
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
/* 329 */     if (n <= 0) {
/* 330 */       return 0;
/*     */     }
/* 332 */     return (int)skip(n);
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
/* 348 */     int ch = read();
/* 349 */     if (ch < 0)
/* 350 */       throw new EOFException(); 
/* 351 */     return (ch != 0);
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
/* 373 */     int ch = read();
/* 374 */     if (ch < 0)
/* 375 */       throw new EOFException(); 
/* 376 */     return (byte)ch;
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
/* 393 */     int ch = read();
/* 394 */     if (ch < 0)
/* 395 */       throw new EOFException(); 
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
/* 423 */     if ((ch1 | ch2) < 0)
/* 424 */       throw new EOFException(); 
/* 425 */     return (short)((ch1 << 8) + (ch2 << 0));
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
/* 450 */     int ch1 = read();
/* 451 */     int ch2 = read();
/* 452 */     if ((ch1 | ch2) < 0)
/* 453 */       throw new EOFException(); 
/* 454 */     return (short)((ch2 << 8) + (ch1 << 0));
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
/* 478 */     int ch1 = read();
/* 479 */     int ch2 = read();
/* 480 */     if ((ch1 | ch2) < 0)
/* 481 */       throw new EOFException(); 
/* 482 */     return (ch1 << 8) + (ch2 << 0);
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
/* 507 */     int ch1 = read();
/* 508 */     int ch2 = read();
/* 509 */     if ((ch1 | ch2) < 0)
/* 510 */       throw new EOFException(); 
/* 511 */     return (ch2 << 8) + (ch1 << 0);
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
/* 534 */     int ch1 = read();
/* 535 */     int ch2 = read();
/* 536 */     if ((ch1 | ch2) < 0)
/* 537 */       throw new EOFException(); 
/* 538 */     return (char)((ch1 << 8) + (ch2 << 0));
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
/* 562 */     int ch1 = read();
/* 563 */     int ch2 = read();
/* 564 */     if ((ch1 | ch2) < 0)
/* 565 */       throw new EOFException(); 
/* 566 */     return (char)((ch2 << 8) + (ch1 << 0));
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
/* 590 */     int ch1 = read();
/* 591 */     int ch2 = read();
/* 592 */     int ch3 = read();
/* 593 */     int ch4 = read();
/* 594 */     if ((ch1 | ch2 | ch3 | ch4) < 0)
/* 595 */       throw new EOFException(); 
/* 596 */     return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
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
/* 621 */     int ch1 = read();
/* 622 */     int ch2 = read();
/* 623 */     int ch3 = read();
/* 624 */     int ch4 = read();
/* 625 */     if ((ch1 | ch2 | ch3 | ch4) < 0)
/* 626 */       throw new EOFException(); 
/* 627 */     return (ch4 << 24) + (ch3 << 16) + (ch2 << 8) + (ch1 << 0);
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
/* 651 */     long ch1 = read();
/* 652 */     long ch2 = read();
/* 653 */     long ch3 = read();
/* 654 */     long ch4 = read();
/* 655 */     if ((ch1 | ch2 | ch3 | ch4) < 0L)
/* 656 */       throw new EOFException(); 
/* 657 */     return (ch1 << 24L) + (ch2 << 16L) + (ch3 << 8L) + (ch4 << 0L);
/*     */   }
/*     */   
/* 660 */   private byte[] ruileBuf = new byte[4];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 684 */     readFully(this.ruileBuf);
/* 685 */     long ch1 = (this.ruileBuf[0] & 0xFF);
/* 686 */     long ch2 = (this.ruileBuf[1] & 0xFF);
/* 687 */     long ch3 = (this.ruileBuf[2] & 0xFF);
/* 688 */     long ch4 = (this.ruileBuf[3] & 0xFF);
/*     */     
/* 690 */     return (ch4 << 24L) + (ch3 << 16L) + (ch2 << 8L) + (ch1 << 0L);
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
/* 722 */     return (readInt() << 32L) + (readInt() & 0xFFFFFFFFL);
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
/* 755 */     int i1 = readIntLE();
/* 756 */     int i2 = readIntLE();
/* 757 */     return (i2 << 32L) + (i1 & 0xFFFFFFFFL);
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
/* 778 */     return Float.intBitsToFloat(readInt());
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
/* 800 */     return Float.intBitsToFloat(readIntLE());
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
/* 821 */     return Double.longBitsToDouble(readLong());
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
/* 843 */     return Double.longBitsToDouble(readLongLE());
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
/* 870 */     StringBuffer input = new StringBuffer();
/* 871 */     int c = -1;
/* 872 */     boolean eol = false;
/*     */     
/* 874 */     while (!eol) {
/* 875 */       long cur; switch (c = read()) {
/*     */         case -1:
/*     */         case 10:
/* 878 */           eol = true;
/*     */           continue;
/*     */         case 13:
/* 881 */           eol = true;
/* 882 */           cur = getFilePointer();
/* 883 */           if (read() != 10) {
/* 884 */             seek(cur);
/*     */           }
/*     */           continue;
/*     */       } 
/* 888 */       input.append((char)c);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 893 */     if (c == -1 && input.length() == 0) {
/* 894 */       return null;
/*     */     }
/* 896 */     return input.toString();
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
/* 922 */     return DataInputStream.readUTF(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 930 */     super.finalize();
/* 931 */     close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/util/SeekableStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */