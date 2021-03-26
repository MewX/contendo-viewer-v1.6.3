/*     */ package c.a.f;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends d
/*     */   implements e, f
/*     */ {
/*     */   public a(File file, String mode, int bufferSize) throws IOException {
/*  80 */     super(file, mode, bufferSize);
/*  81 */     this.g = 0;
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
/*     */   public a(File file, String mode) throws IOException {
/*  99 */     super(file, mode);
/* 100 */     this.g = 0;
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
/*     */   public a(String name, String mode, int bufferSize) throws IOException {
/* 120 */     super(name, mode, bufferSize);
/* 121 */     this.g = 0;
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
/*     */   public a(String name, String mode) throws IOException {
/* 139 */     super(name, mode);
/* 140 */     this.g = 0;
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
/*     */   public final void writeShort(int v) throws IOException {
/* 158 */     write(v >>> 8);
/* 159 */     write(v);
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
/*     */   public final void writeInt(int v) throws IOException {
/* 172 */     write(v >>> 24);
/* 173 */     write(v >>> 16);
/* 174 */     write(v >>> 8);
/* 175 */     write(v);
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
/*     */   public final void writeLong(long v) throws IOException {
/* 188 */     write((int)(v >>> 56L));
/* 189 */     write((int)(v >>> 48L));
/* 190 */     write((int)(v >>> 40L));
/* 191 */     write((int)(v >>> 32L));
/* 192 */     write((int)(v >>> 24L));
/* 193 */     write((int)(v >>> 16L));
/* 194 */     write((int)(v >>> 8L));
/* 195 */     write((int)v);
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
/*     */   public final void writeFloat(float v) throws IOException {
/* 208 */     int intV = Float.floatToIntBits(v);
/*     */     
/* 210 */     write(intV >>> 24);
/* 211 */     write(intV >>> 16);
/* 212 */     write(intV >>> 8);
/* 213 */     write(intV);
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
/*     */   public final void writeDouble(double v) throws IOException {
/* 226 */     long longV = Double.doubleToLongBits(v);
/*     */     
/* 228 */     write((int)(longV >>> 56L));
/* 229 */     write((int)(longV >>> 48L));
/* 230 */     write((int)(longV >>> 40L));
/* 231 */     write((int)(longV >>> 32L));
/* 232 */     write((int)(longV >>> 24L));
/* 233 */     write((int)(longV >>> 16L));
/* 234 */     write((int)(longV >>> 8L));
/* 235 */     write((int)longV);
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
/*     */   public final short readShort() throws IOException, EOFException {
/* 253 */     return (short)(read() << 8 | read());
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
/*     */   public final int readUnsignedShort() throws IOException, EOFException {
/* 274 */     return read() << 8 | read();
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
/*     */   public final int readInt() throws IOException, EOFException {
/* 295 */     return read() << 24 | read() << 16 | read() << 8 | read();
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
/*     */   public final long readUnsignedInt() throws IOException, EOFException {
/* 317 */     return (read() << 24 | read() << 16 | read() << 8 | read());
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
/*     */   public final long readLong() throws IOException, EOFException {
/* 342 */     return read() << 56L | read() << 48L | read() << 40L | read() << 32L | read() << 24L | read() << 16L | read() << 8L | read();
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
/*     */   public final float readFloat() throws EOFException, IOException {
/* 360 */     return Float.intBitsToFloat(
/* 361 */         read() << 24 | 
/* 362 */         read() << 16 | 
/* 363 */         read() << 8 | 
/* 364 */         read());
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
/*     */   public final double readDouble() throws IOException, EOFException {
/* 382 */     return Double.longBitsToDouble(
/* 383 */         read() << 56L | 
/* 384 */         read() << 48L | 
/* 385 */         read() << 40L | 
/* 386 */         read() << 32L | 
/* 387 */         read() << 24L | 
/* 388 */         read() << 16L | 
/* 389 */         read() << 8L | 
/* 390 */         read());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 398 */     return super.toString() + "\nBig-Endian ordering";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/f/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */