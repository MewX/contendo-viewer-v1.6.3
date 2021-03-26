/*     */ package org.apache.pdfbox.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomAccessBufferedFileInputStream
/*     */   extends InputStream
/*     */   implements RandomAccessRead
/*     */ {
/*     */   private static final String TMP_FILE_PREFIX = "tmpPDFBox";
/*  45 */   private int pageSizeShift = 12;
/*  46 */   private int pageSize = 1 << this.pageSizeShift;
/*  47 */   private long pageOffsetMask = -1L << this.pageSizeShift;
/*  48 */   private int maxCachedPages = 1000;
/*     */   
/*     */   private File tempFile;
/*  51 */   private byte[] lastRemovedCachePage = null;
/*     */ 
/*     */   
/*  54 */   private final Map<Long, byte[]> pageCache = (Map<Long, byte[]>)new LinkedHashMap<Long, byte[]>(this.maxCachedPages, 0.75F, true)
/*     */     {
/*     */       private static final long serialVersionUID = -6302488539257741101L;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       protected boolean removeEldestEntry(Map.Entry<Long, byte[]> eldest) {
/*  62 */         boolean doRemove = (size() > RandomAccessBufferedFileInputStream.this.maxCachedPages);
/*  63 */         if (doRemove)
/*     */         {
/*  65 */           RandomAccessBufferedFileInputStream.this.lastRemovedCachePage = eldest.getValue();
/*     */         }
/*  67 */         return doRemove;
/*     */       }
/*     */     };
/*     */   
/*  71 */   private long curPageOffset = -1L;
/*  72 */   private byte[] curPage = new byte[this.pageSize];
/*  73 */   private int offsetWithinPage = 0;
/*     */   
/*     */   private final RandomAccessFile raFile;
/*     */   private final long fileLength;
/*  77 */   private long fileOffset = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isClosed;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomAccessBufferedFileInputStream(String filename) throws IOException {
/*  88 */     this(new File(filename));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomAccessBufferedFileInputStream(File file) throws IOException {
/*  99 */     this.raFile = new RandomAccessFile(file, "r");
/* 100 */     this.fileLength = file.length();
/* 101 */     seek(0L);
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
/*     */   public RandomAccessBufferedFileInputStream(InputStream input) throws IOException {
/* 113 */     this.tempFile = createTmpFile(input);
/* 114 */     this.fileLength = this.tempFile.length();
/* 115 */     this.raFile = new RandomAccessFile(this.tempFile, "r");
/* 116 */     seek(0L);
/*     */   }
/*     */ 
/*     */   
/*     */   private File createTmpFile(InputStream input) throws IOException {
/* 121 */     FileOutputStream fos = null;
/*     */     
/*     */     try {
/* 124 */       File tmpFile = File.createTempFile("tmpPDFBox", ".pdf");
/* 125 */       fos = new FileOutputStream(tmpFile);
/* 126 */       IOUtils.copy(input, fos);
/* 127 */       return tmpFile;
/*     */     }
/*     */     finally {
/*     */       
/* 131 */       IOUtils.closeQuietly(input);
/* 132 */       IOUtils.closeQuietly(fos);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void deleteTempFile() {
/* 141 */     if (this.tempFile != null)
/*     */     {
/* 143 */       this.tempFile.delete();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getPosition() {
/* 151 */     return this.fileOffset;
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
/*     */   public void seek(long newOffset) throws IOException {
/* 164 */     long newPageOffset = newOffset & this.pageOffsetMask;
/* 165 */     if (newPageOffset != this.curPageOffset) {
/*     */       
/* 167 */       byte[] newPage = this.pageCache.get(Long.valueOf(newPageOffset));
/* 168 */       if (newPage == null) {
/*     */         
/* 170 */         this.raFile.seek(newPageOffset);
/* 171 */         newPage = readPage();
/* 172 */         this.pageCache.put(Long.valueOf(newPageOffset), newPage);
/*     */       } 
/* 174 */       this.curPageOffset = newPageOffset;
/* 175 */       this.curPage = newPage;
/*     */     } 
/*     */     
/* 178 */     this.offsetWithinPage = (int)(newOffset - this.curPageOffset);
/* 179 */     this.fileOffset = newOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] readPage() throws IOException {
/*     */     byte[] page;
/* 191 */     if (this.lastRemovedCachePage != null) {
/*     */       
/* 193 */       page = this.lastRemovedCachePage;
/* 194 */       this.lastRemovedCachePage = null;
/*     */     }
/*     */     else {
/*     */       
/* 198 */       page = new byte[this.pageSize];
/*     */     } 
/*     */     
/* 201 */     int readBytes = 0;
/* 202 */     while (readBytes < this.pageSize) {
/*     */       
/* 204 */       int curBytesRead = this.raFile.read(page, readBytes, this.pageSize - readBytes);
/* 205 */       if (curBytesRead < 0) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 210 */       readBytes += curBytesRead;
/*     */     } 
/*     */     
/* 213 */     return page;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 219 */     if (this.fileOffset >= this.fileLength)
/*     */     {
/* 221 */       return -1;
/*     */     }
/*     */     
/* 224 */     if (this.offsetWithinPage == this.pageSize)
/*     */     {
/* 226 */       seek(this.fileOffset);
/*     */     }
/*     */     
/* 229 */     this.fileOffset++;
/* 230 */     return this.curPage[this.offsetWithinPage++] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 236 */     return read(b, 0, b.length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 242 */     if (this.fileOffset >= this.fileLength)
/*     */     {
/* 244 */       return -1;
/*     */     }
/*     */     
/* 247 */     if (this.offsetWithinPage == this.pageSize)
/*     */     {
/* 249 */       seek(this.fileOffset);
/*     */     }
/*     */     
/* 252 */     int commonLen = Math.min(this.pageSize - this.offsetWithinPage, len);
/* 253 */     if (this.fileLength - this.fileOffset < this.pageSize)
/*     */     {
/* 255 */       commonLen = Math.min(commonLen, (int)(this.fileLength - this.fileOffset));
/*     */     }
/*     */     
/* 258 */     System.arraycopy(this.curPage, this.offsetWithinPage, b, off, commonLen);
/*     */     
/* 260 */     this.offsetWithinPage += commonLen;
/* 261 */     this.fileOffset += commonLen;
/*     */     
/* 263 */     return commonLen;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/* 269 */     return (int)Math.min(this.fileLength - this.fileOffset, 2147483647L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 276 */     long toSkip = n;
/*     */     
/* 278 */     if (this.fileLength - this.fileOffset < toSkip)
/*     */     {
/* 280 */       toSkip = this.fileLength - this.fileOffset;
/*     */     }
/*     */     
/* 283 */     if (toSkip < this.pageSize && this.offsetWithinPage + toSkip <= this.pageSize) {
/*     */ 
/*     */       
/* 286 */       this.offsetWithinPage = (int)(this.offsetWithinPage + toSkip);
/* 287 */       this.fileOffset += toSkip;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 292 */       seek(this.fileOffset + toSkip);
/*     */     } 
/*     */     
/* 295 */     return toSkip;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long length() throws IOException {
/* 301 */     return this.fileLength;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 307 */     this.raFile.close();
/* 308 */     deleteTempFile();
/* 309 */     this.pageCache.clear();
/* 310 */     this.isClosed = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 316 */     return this.isClosed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int peek() throws IOException {
/* 322 */     int result = read();
/* 323 */     if (result != -1)
/*     */     {
/* 325 */       rewind(1);
/*     */     }
/* 327 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void rewind(int bytes) throws IOException {
/* 333 */     seek(getPosition() - bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] readFully(int length) throws IOException {
/* 339 */     byte[] b = new byte[length];
/* 340 */     int bytesRead = read(b);
/* 341 */     while (bytesRead < length)
/*     */     {
/* 343 */       bytesRead += read(b, bytesRead, length - bytesRead);
/*     */     }
/* 345 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEOF() throws IOException {
/* 351 */     int peek = peek();
/* 352 */     return (peek == -1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/RandomAccessBufferedFileInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */