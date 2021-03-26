/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.fontbox.util.Charsets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class TTFDataStream
/*     */   implements Closeable
/*     */ {
/*     */   public float read32Fixed() throws IOException {
/*  49 */     float retval = 0.0F;
/*  50 */     retval = readSignedShort();
/*  51 */     retval = (float)(retval + readUnsignedShort() / 65536.0D);
/*  52 */     return retval;
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
/*     */   public String readString(int length) throws IOException {
/*  64 */     return readString(length, Charsets.ISO_8859_1);
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
/*     */   public String readString(int length, String charset) throws IOException {
/*  77 */     byte[] buffer = read(length);
/*  78 */     return new String(buffer, charset);
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
/*     */   public String readString(int length, Charset charset) throws IOException {
/*  91 */     byte[] buffer = read(length);
/*  92 */     return new String(buffer, charset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int read() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract long readLong() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readSignedByte() throws IOException {
/* 118 */     int signedByte = read();
/* 119 */     return (signedByte <= 127) ? signedByte : (signedByte - 256);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readUnsignedByte() throws IOException {
/* 130 */     int unsignedByte = read();
/* 131 */     if (unsignedByte == -1)
/*     */     {
/* 133 */       throw new EOFException("premature EOF");
/*     */     }
/* 135 */     return unsignedByte;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long readUnsignedInt() throws IOException {
/* 146 */     long byte1 = read();
/* 147 */     long byte2 = read();
/* 148 */     long byte3 = read();
/* 149 */     long byte4 = read();
/* 150 */     if (byte4 < 0L)
/*     */     {
/* 152 */       throw new EOFException();
/*     */     }
/* 154 */     return (byte1 << 24L) + (byte2 << 16L) + (byte3 << 8L) + (byte4 << 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int readUnsignedShort() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] readUnsignedByteArray(int length) throws IOException {
/* 174 */     int[] array = new int[length];
/* 175 */     for (int i = 0; i < length; i++)
/*     */     {
/* 177 */       array[i] = read();
/*     */     }
/* 179 */     return array;
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
/*     */   public int[] readUnsignedShortArray(int length) throws IOException {
/* 191 */     int[] array = new int[length];
/* 192 */     for (int i = 0; i < length; i++)
/*     */     {
/* 194 */       array[i] = readUnsignedShort();
/*     */     }
/* 196 */     return array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract short readSignedShort() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar readInternationalDate() throws IOException {
/* 215 */     long secondsSince1904 = readLong();
/* 216 */     Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));
/* 217 */     cal.set(1904, 0, 1, 0, 0, 0);
/* 218 */     cal.set(14, 0);
/* 219 */     long millisFor1904 = cal.getTimeInMillis();
/* 220 */     millisFor1904 += secondsSince1904 * 1000L;
/* 221 */     cal.setTimeInMillis(millisFor1904);
/* 222 */     return cal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String readTag() throws IOException {
/* 231 */     return new String(read(4), Charsets.US_ASCII);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void close() throws IOException;
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
/*     */   public byte[] read(int numberOfBytes) throws IOException {
/* 259 */     byte[] data = new byte[numberOfBytes];
/* 260 */     int amountRead = 0;
/* 261 */     int totalAmountRead = 0;
/*     */     
/* 263 */     while (totalAmountRead < numberOfBytes && (
/* 264 */       amountRead = read(data, totalAmountRead, numberOfBytes - totalAmountRead)) != -1)
/*     */     {
/* 266 */       totalAmountRead += amountRead;
/*     */     }
/* 268 */     if (totalAmountRead == numberOfBytes)
/*     */     {
/* 270 */       return data;
/*     */     }
/*     */ 
/*     */     
/* 274 */     throw new IOException("Unexpected end of TTF stream reached");
/*     */   }
/*     */   
/*     */   public abstract int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract long getCurrentPosition() throws IOException;
/*     */   
/*     */   public abstract InputStream getOriginalData() throws IOException;
/*     */   
/*     */   public abstract long getOriginalDataSize();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/TTFDataStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */