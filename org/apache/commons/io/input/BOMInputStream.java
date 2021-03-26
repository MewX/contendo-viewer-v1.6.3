/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.io.ByteOrderMark;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BOMInputStream
/*     */   extends ProxyInputStream
/*     */ {
/*     */   private final boolean include;
/*     */   private final List<ByteOrderMark> boms;
/*     */   private ByteOrderMark byteOrderMark;
/*     */   private int[] firstBytes;
/*     */   private int fbLength;
/*     */   private int fbIndex;
/*     */   private int markFbIndex;
/*     */   private boolean markedAtStart;
/*     */   
/*     */   public BOMInputStream(InputStream delegate) {
/* 110 */     this(delegate, false, new ByteOrderMark[] { ByteOrderMark.UTF_8 });
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
/*     */   public BOMInputStream(InputStream delegate, boolean include) {
/* 122 */     this(delegate, include, new ByteOrderMark[] { ByteOrderMark.UTF_8 });
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
/*     */   public BOMInputStream(InputStream delegate, ByteOrderMark... boms) {
/* 134 */     this(delegate, false, boms);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   private static final Comparator<ByteOrderMark> ByteOrderMarkLengthComparator = new Comparator<ByteOrderMark>()
/*     */     {
/*     */       public int compare(ByteOrderMark bom1, ByteOrderMark bom2)
/*     */       {
/* 144 */         int len1 = bom1.length();
/* 145 */         int len2 = bom2.length();
/* 146 */         if (len1 > len2) {
/* 147 */           return -1;
/*     */         }
/* 149 */         if (len2 > len1) {
/* 150 */           return 1;
/*     */         }
/* 152 */         return 0;
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BOMInputStream(InputStream delegate, boolean include, ByteOrderMark... boms) {
/* 167 */     super(delegate);
/* 168 */     if (boms == null || boms.length == 0) {
/* 169 */       throw new IllegalArgumentException("No BOMs specified");
/*     */     }
/* 171 */     this.include = include;
/* 172 */     List<ByteOrderMark> list = Arrays.asList(boms);
/*     */     
/* 174 */     Collections.sort(list, ByteOrderMarkLengthComparator);
/* 175 */     this.boms = list;
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
/*     */   public boolean hasBOM() throws IOException {
/* 187 */     return (getBOM() != null);
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
/*     */   public boolean hasBOM(ByteOrderMark bom) throws IOException {
/* 202 */     if (!this.boms.contains(bom)) {
/* 203 */       throw new IllegalArgumentException("Stream not configure to detect " + bom);
/*     */     }
/* 205 */     getBOM();
/* 206 */     return (this.byteOrderMark != null && this.byteOrderMark.equals(bom));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteOrderMark getBOM() throws IOException {
/* 217 */     if (this.firstBytes == null) {
/* 218 */       this.fbLength = 0;
/*     */       
/* 220 */       int maxBomSize = ((ByteOrderMark)this.boms.get(0)).length();
/* 221 */       this.firstBytes = new int[maxBomSize];
/*     */       
/* 223 */       for (int i = 0; i < this.firstBytes.length; i++) {
/* 224 */         this.firstBytes[i] = this.in.read();
/* 225 */         this.fbLength++;
/* 226 */         if (this.firstBytes[i] < 0) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 231 */       this.byteOrderMark = find();
/* 232 */       if (this.byteOrderMark != null && 
/* 233 */         !this.include) {
/* 234 */         if (this.byteOrderMark.length() < this.firstBytes.length) {
/* 235 */           this.fbIndex = this.byteOrderMark.length();
/*     */         } else {
/* 237 */           this.fbLength = 0;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 242 */     return this.byteOrderMark;
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
/*     */   public String getBOMCharsetName() throws IOException {
/* 254 */     getBOM();
/* 255 */     return (this.byteOrderMark == null) ? null : this.byteOrderMark.getCharsetName();
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
/*     */   private int readFirstBytes() throws IOException {
/* 268 */     getBOM();
/* 269 */     return (this.fbIndex < this.fbLength) ? this.firstBytes[this.fbIndex++] : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ByteOrderMark find() {
/* 278 */     for (ByteOrderMark bom : this.boms) {
/* 279 */       if (matches(bom)) {
/* 280 */         return bom;
/*     */       }
/*     */     } 
/* 283 */     return null;
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
/*     */   private boolean matches(ByteOrderMark bom) {
/* 298 */     for (int i = 0; i < bom.length(); i++) {
/* 299 */       if (bom.get(i) != this.firstBytes[i]) {
/* 300 */         return false;
/*     */       }
/*     */     } 
/* 303 */     return true;
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
/*     */   public int read() throws IOException {
/* 319 */     int b = readFirstBytes();
/* 320 */     return (b >= 0) ? b : this.in.read();
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
/*     */   public int read(byte[] buf, int off, int len) throws IOException {
/* 338 */     int firstCount = 0;
/* 339 */     int b = 0;
/* 340 */     while (len > 0 && b >= 0) {
/* 341 */       b = readFirstBytes();
/* 342 */       if (b >= 0) {
/* 343 */         buf[off++] = (byte)(b & 0xFF);
/* 344 */         len--;
/* 345 */         firstCount++;
/*     */       } 
/*     */     } 
/* 348 */     int secondCount = this.in.read(buf, off, len);
/* 349 */     return (secondCount < 0) ? ((firstCount > 0) ? firstCount : -1) : (firstCount + secondCount);
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
/*     */   public int read(byte[] buf) throws IOException {
/* 363 */     return read(buf, 0, buf.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void mark(int readlimit) {
/* 374 */     this.markFbIndex = this.fbIndex;
/* 375 */     this.markedAtStart = (this.firstBytes == null);
/* 376 */     this.in.mark(readlimit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void reset() throws IOException {
/* 387 */     this.fbIndex = this.markFbIndex;
/* 388 */     if (this.markedAtStart) {
/* 389 */       this.firstBytes = null;
/*     */     }
/*     */     
/* 392 */     this.in.reset();
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
/*     */   public long skip(long n) throws IOException {
/* 406 */     int skipped = 0;
/* 407 */     while (n > skipped && readFirstBytes() >= 0) {
/* 408 */       skipped++;
/*     */     }
/* 410 */     return this.in.skip(n - skipped) + skipped;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/BOMInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */