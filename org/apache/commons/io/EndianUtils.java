/*     */ package org.apache.commons.io;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EndianUtils
/*     */ {
/*     */   public static short swapShort(short value) {
/*  57 */     return (short)(((value >> 0 & 0xFF) << 8) + ((value >> 8 & 0xFF) << 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int swapInteger(int value) {
/*  67 */     return ((value >> 0 & 0xFF) << 24) + ((value >> 8 & 0xFF) << 16) + ((value >> 16 & 0xFF) << 8) + ((value >> 24 & 0xFF) << 0);
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
/*     */   public static long swapLong(long value) {
/*  80 */     return ((value >> 0L & 0xFFL) << 56L) + ((value >> 8L & 0xFFL) << 48L) + ((value >> 16L & 0xFFL) << 40L) + ((value >> 24L & 0xFFL) << 32L) + ((value >> 32L & 0xFFL) << 24L) + ((value >> 40L & 0xFFL) << 16L) + ((value >> 48L & 0xFFL) << 8L) + ((value >> 56L & 0xFFL) << 0L);
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
/*     */   public static float swapFloat(float value) {
/*  97 */     return Float.intBitsToFloat(swapInteger(Float.floatToIntBits(value)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double swapDouble(double value) {
/* 106 */     return Double.longBitsToDouble(swapLong(Double.doubleToLongBits(value)));
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
/*     */   public static void writeSwappedShort(byte[] data, int offset, short value) {
/* 119 */     data[offset + 0] = (byte)(value >> 0 & 0xFF);
/* 120 */     data[offset + 1] = (byte)(value >> 8 & 0xFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short readSwappedShort(byte[] data, int offset) {
/* 131 */     return (short)(((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8));
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
/*     */   public static int readSwappedUnsignedShort(byte[] data, int offset) {
/* 144 */     return ((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8);
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
/*     */   public static void writeSwappedInteger(byte[] data, int offset, int value) {
/* 156 */     data[offset + 0] = (byte)(value >> 0 & 0xFF);
/* 157 */     data[offset + 1] = (byte)(value >> 8 & 0xFF);
/* 158 */     data[offset + 2] = (byte)(value >> 16 & 0xFF);
/* 159 */     data[offset + 3] = (byte)(value >> 24 & 0xFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int readSwappedInteger(byte[] data, int offset) {
/* 170 */     return ((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8) + ((data[offset + 2] & 0xFF) << 16) + ((data[offset + 3] & 0xFF) << 24);
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
/*     */   public static long readSwappedUnsignedInteger(byte[] data, int offset) {
/* 185 */     long low = (((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8) + ((data[offset + 2] & 0xFF) << 16));
/*     */ 
/*     */ 
/*     */     
/* 189 */     long high = (data[offset + 3] & 0xFF);
/*     */     
/* 191 */     return (high << 24L) + (0xFFFFFFFFL & low);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeSwappedLong(byte[] data, int offset, long value) {
/* 202 */     data[offset + 0] = (byte)(int)(value >> 0L & 0xFFL);
/* 203 */     data[offset + 1] = (byte)(int)(value >> 8L & 0xFFL);
/* 204 */     data[offset + 2] = (byte)(int)(value >> 16L & 0xFFL);
/* 205 */     data[offset + 3] = (byte)(int)(value >> 24L & 0xFFL);
/* 206 */     data[offset + 4] = (byte)(int)(value >> 32L & 0xFFL);
/* 207 */     data[offset + 5] = (byte)(int)(value >> 40L & 0xFFL);
/* 208 */     data[offset + 6] = (byte)(int)(value >> 48L & 0xFFL);
/* 209 */     data[offset + 7] = (byte)(int)(value >> 56L & 0xFFL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long readSwappedLong(byte[] data, int offset) {
/* 220 */     long low = readSwappedInteger(data, offset);
/* 221 */     long high = readSwappedInteger(data, offset + 4);
/* 222 */     return (high << 32L) + (0xFFFFFFFFL & low);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeSwappedFloat(byte[] data, int offset, float value) {
/* 233 */     writeSwappedInteger(data, offset, Float.floatToIntBits(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float readSwappedFloat(byte[] data, int offset) {
/* 244 */     return Float.intBitsToFloat(readSwappedInteger(data, offset));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeSwappedDouble(byte[] data, int offset, double value) {
/* 255 */     writeSwappedLong(data, offset, Double.doubleToLongBits(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double readSwappedDouble(byte[] data, int offset) {
/* 266 */     return Double.longBitsToDouble(readSwappedLong(data, offset));
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
/*     */   public static void writeSwappedShort(OutputStream output, short value) throws IOException {
/* 279 */     output.write((byte)(value >> 0 & 0xFF));
/* 280 */     output.write((byte)(value >> 8 & 0xFF));
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
/*     */   public static short readSwappedShort(InputStream input) throws IOException {
/* 293 */     return 
/* 294 */       (short)(((read(input) & 0xFF) << 0) + ((read(input) & 0xFF) << 8));
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
/*     */   public static int readSwappedUnsignedShort(InputStream input) throws IOException {
/* 307 */     int value1 = read(input);
/* 308 */     int value2 = read(input);
/*     */     
/* 310 */     return ((value1 & 0xFF) << 0) + ((value2 & 0xFF) << 8);
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
/*     */   public static void writeSwappedInteger(OutputStream output, int value) throws IOException {
/* 324 */     output.write((byte)(value >> 0 & 0xFF));
/* 325 */     output.write((byte)(value >> 8 & 0xFF));
/* 326 */     output.write((byte)(value >> 16 & 0xFF));
/* 327 */     output.write((byte)(value >> 24 & 0xFF));
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
/*     */   public static int readSwappedInteger(InputStream input) throws IOException {
/* 340 */     int value1 = read(input);
/* 341 */     int value2 = read(input);
/* 342 */     int value3 = read(input);
/* 343 */     int value4 = read(input);
/*     */     
/* 345 */     return ((value1 & 0xFF) << 0) + ((value2 & 0xFF) << 8) + ((value3 & 0xFF) << 16) + ((value4 & 0xFF) << 24);
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
/*     */   public static long readSwappedUnsignedInteger(InputStream input) throws IOException {
/* 361 */     int value1 = read(input);
/* 362 */     int value2 = read(input);
/* 363 */     int value3 = read(input);
/* 364 */     int value4 = read(input);
/*     */     
/* 366 */     long low = (((value1 & 0xFF) << 0) + ((value2 & 0xFF) << 8) + ((value3 & 0xFF) << 16));
/*     */ 
/*     */ 
/*     */     
/* 370 */     long high = (value4 & 0xFF);
/*     */     
/* 372 */     return (high << 24L) + (0xFFFFFFFFL & low);
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
/*     */   public static void writeSwappedLong(OutputStream output, long value) throws IOException {
/* 385 */     output.write((byte)(int)(value >> 0L & 0xFFL));
/* 386 */     output.write((byte)(int)(value >> 8L & 0xFFL));
/* 387 */     output.write((byte)(int)(value >> 16L & 0xFFL));
/* 388 */     output.write((byte)(int)(value >> 24L & 0xFFL));
/* 389 */     output.write((byte)(int)(value >> 32L & 0xFFL));
/* 390 */     output.write((byte)(int)(value >> 40L & 0xFFL));
/* 391 */     output.write((byte)(int)(value >> 48L & 0xFFL));
/* 392 */     output.write((byte)(int)(value >> 56L & 0xFFL));
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
/*     */   public static long readSwappedLong(InputStream input) throws IOException {
/* 405 */     byte[] bytes = new byte[8];
/* 406 */     for (int i = 0; i < 8; i++) {
/* 407 */       bytes[i] = (byte)read(input);
/*     */     }
/* 409 */     return readSwappedLong(bytes, 0);
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
/*     */   public static void writeSwappedFloat(OutputStream output, float value) throws IOException {
/* 422 */     writeSwappedInteger(output, Float.floatToIntBits(value));
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
/*     */   public static float readSwappedFloat(InputStream input) throws IOException {
/* 435 */     return Float.intBitsToFloat(readSwappedInteger(input));
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
/*     */   public static void writeSwappedDouble(OutputStream output, double value) throws IOException {
/* 448 */     writeSwappedLong(output, Double.doubleToLongBits(value));
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
/*     */   public static double readSwappedDouble(InputStream input) throws IOException {
/* 461 */     return Double.longBitsToDouble(readSwappedLong(input));
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
/*     */   private static int read(InputStream input) throws IOException {
/* 473 */     int value = input.read();
/*     */     
/* 475 */     if (-1 == value) {
/* 476 */       throw new EOFException("Unexpected EOF reached");
/*     */     }
/*     */     
/* 479 */     return value;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/EndianUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */