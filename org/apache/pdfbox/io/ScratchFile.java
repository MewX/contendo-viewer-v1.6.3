/*     */ package org.apache.pdfbox.io;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.BitSet;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ScratchFile
/*     */   implements Closeable
/*     */ {
/*  53 */   private static final Log LOG = LogFactory.getLog(ScratchFile.class);
/*     */ 
/*     */   
/*     */   private static final int ENLARGE_PAGE_COUNT = 16;
/*     */   
/*     */   private static final int INIT_UNRESTRICTED_MAINMEM_PAGECOUNT = 100000;
/*     */   
/*     */   private static final int PAGE_SIZE = 4096;
/*     */   
/*  62 */   private final Object ioLock = new Object();
/*     */   
/*     */   private final File scratchFileDirectory;
/*     */   
/*     */   private File file;
/*     */   private RandomAccessFile raf;
/*  68 */   private volatile int pageCount = 0;
/*  69 */   private final BitSet freePages = new BitSet();
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile byte[][] inMemoryPages;
/*     */ 
/*     */ 
/*     */   
/*     */   private final int inMemoryMaxPageCount;
/*     */ 
/*     */   
/*     */   private final int maxPageCount;
/*     */ 
/*     */   
/*     */   private final boolean useScratchFile;
/*     */ 
/*     */   
/*     */   private final boolean maxMainMemoryIsRestricted;
/*     */ 
/*     */   
/*     */   private volatile boolean isClosed = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public ScratchFile(File scratchFileDirectory) throws IOException {
/*  94 */     this(MemoryUsageSetting.setupTempFileOnly().setTempDir(scratchFileDirectory));
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
/*     */   public ScratchFile(MemoryUsageSetting memUsageSetting) throws IOException {
/* 110 */     this.maxMainMemoryIsRestricted = (!memUsageSetting.useMainMemory() || memUsageSetting.isMainMemoryRestricted());
/* 111 */     this.useScratchFile = this.maxMainMemoryIsRestricted ? memUsageSetting.useTempFile() : false;
/* 112 */     this.scratchFileDirectory = this.useScratchFile ? memUsageSetting.getTempDir() : null;
/*     */     
/* 114 */     if (this.scratchFileDirectory != null && !this.scratchFileDirectory.isDirectory())
/*     */     {
/* 116 */       throw new IOException("Scratch file directory does not exist: " + this.scratchFileDirectory);
/*     */     }
/*     */     
/* 119 */     this
/* 120 */       .maxPageCount = memUsageSetting.isStorageRestricted() ? (int)Math.min(2147483647L, memUsageSetting.getMaxStorageBytes() / 4096L) : Integer.MAX_VALUE;
/*     */ 
/*     */     
/* 123 */     this
/*     */       
/* 125 */       .inMemoryMaxPageCount = memUsageSetting.useMainMemory() ? (memUsageSetting.isMainMemoryRestricted() ? (int)Math.min(2147483647L, memUsageSetting.getMaxMainMemoryBytes() / 4096L) : Integer.MAX_VALUE) : 0;
/*     */ 
/*     */     
/* 128 */     this.inMemoryPages = new byte[this.maxMainMemoryIsRestricted ? this.inMemoryMaxPageCount : 100000][];
/*     */     
/* 130 */     this.freePages.set(0, this.inMemoryPages.length);
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
/*     */   public static ScratchFile getMainMemoryOnlyInstance() {
/*     */     try {
/* 143 */       return new ScratchFile(MemoryUsageSetting.setupMainMemoryOnly());
/*     */     }
/* 145 */     catch (IOException ioe) {
/*     */ 
/*     */       
/* 148 */       LOG.error("Unexpected exception occurred creating main memory scratch file instance: " + ioe.getMessage());
/* 149 */       return null;
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
/*     */   int getNewPage() throws IOException {
/* 161 */     synchronized (this.freePages) {
/*     */       
/* 163 */       int idx = this.freePages.nextSetBit(0);
/*     */       
/* 165 */       if (idx < 0) {
/*     */         
/* 167 */         enlarge();
/*     */         
/* 169 */         idx = this.freePages.nextSetBit(0);
/* 170 */         if (idx < 0)
/*     */         {
/* 172 */           throw new IOException("Maximum allowed scratch file memory exceeded.");
/*     */         }
/*     */       } 
/*     */       
/* 176 */       this.freePages.clear(idx);
/*     */       
/* 178 */       if (idx >= this.pageCount)
/*     */       {
/* 180 */         this.pageCount = idx + 1;
/*     */       }
/*     */       
/* 183 */       return idx;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void enlarge() throws IOException {
/* 203 */     synchronized (this.ioLock) {
/*     */       
/* 205 */       checkClosed();
/*     */       
/* 207 */       if (this.pageCount >= this.maxPageCount) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 212 */       if (this.useScratchFile) {
/*     */ 
/*     */         
/* 215 */         if (this.raf == null) {
/*     */           
/* 217 */           this.file = File.createTempFile("PDFBox", ".tmp", this.scratchFileDirectory);
/*     */           
/*     */           try {
/* 220 */             this.raf = new RandomAccessFile(this.file, "rw");
/*     */           }
/* 222 */           catch (IOException e) {
/*     */             
/* 224 */             if (!this.file.delete())
/*     */             {
/* 226 */               LOG.warn("Error deleting scratch file: " + this.file.getAbsolutePath());
/*     */             }
/* 228 */             throw e;
/*     */           } 
/*     */         } 
/*     */         
/* 232 */         long fileLen = this.raf.length();
/* 233 */         long expectedFileLen = (this.pageCount - this.inMemoryMaxPageCount) * 4096L;
/*     */         
/* 235 */         if (expectedFileLen != fileLen)
/*     */         {
/* 237 */           throw new IOException("Expected scratch file size of " + expectedFileLen + " but found " + fileLen);
/*     */         }
/*     */ 
/*     */         
/* 241 */         if (this.pageCount + 16 > this.pageCount)
/*     */         {
/* 243 */           fileLen += 65536L;
/*     */           
/* 245 */           this.raf.setLength(fileLen);
/*     */           
/* 247 */           this.freePages.set(this.pageCount, this.pageCount + 16);
/*     */         }
/*     */       
/* 250 */       } else if (!this.maxMainMemoryIsRestricted) {
/*     */ 
/*     */         
/* 253 */         int oldSize = this.inMemoryPages.length;
/* 254 */         int newSize = (int)Math.min(oldSize * 2L, 2147483647L);
/* 255 */         if (newSize > oldSize) {
/*     */           
/* 257 */           byte[][] newInMemoryPages = new byte[newSize][];
/* 258 */           System.arraycopy(this.inMemoryPages, 0, newInMemoryPages, 0, oldSize);
/* 259 */           this.inMemoryPages = newInMemoryPages;
/*     */           
/* 261 */           this.freePages.set(oldSize, newSize);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getPageSize() {
/* 274 */     return 4096;
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
/*     */   byte[] readPage(int pageIdx) throws IOException {
/* 288 */     if (pageIdx < 0 || pageIdx >= this.pageCount) {
/*     */       
/* 290 */       checkClosed();
/* 291 */       throw new IOException("Page index out of range: " + pageIdx + ". Max value: " + (this.pageCount - 1));
/*     */     } 
/*     */ 
/*     */     
/* 295 */     if (pageIdx < this.inMemoryMaxPageCount) {
/*     */       
/* 297 */       byte[] page = this.inMemoryPages[pageIdx];
/*     */ 
/*     */       
/* 300 */       if (page == null) {
/*     */         
/* 302 */         checkClosed();
/* 303 */         throw new IOException("Requested page with index " + pageIdx + " was not written before.");
/*     */       } 
/*     */       
/* 306 */       return page;
/*     */     } 
/*     */     
/* 309 */     synchronized (this.ioLock) {
/*     */       
/* 311 */       if (this.raf == null) {
/*     */         
/* 313 */         checkClosed();
/* 314 */         throw new IOException("Missing scratch file to read page with index " + pageIdx + " from.");
/*     */       } 
/*     */       
/* 317 */       byte[] page = new byte[4096];
/* 318 */       this.raf.seek((pageIdx - this.inMemoryMaxPageCount) * 4096L);
/* 319 */       this.raf.readFully(page);
/*     */       
/* 321 */       return page;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void writePage(int pageIdx, byte[] page) throws IOException {
/* 340 */     if (pageIdx < 0 || pageIdx >= this.pageCount) {
/*     */       
/* 342 */       checkClosed();
/* 343 */       throw new IOException("Page index out of range: " + pageIdx + ". Max value: " + (this.pageCount - 1));
/*     */     } 
/*     */     
/* 346 */     if (page.length != 4096)
/*     */     {
/* 348 */       throw new IOException("Wrong page size to write: " + page.length + ". Expected: " + 'က');
/*     */     }
/*     */     
/* 351 */     if (pageIdx < this.inMemoryMaxPageCount) {
/*     */       
/* 353 */       if (this.maxMainMemoryIsRestricted) {
/*     */         
/* 355 */         this.inMemoryPages[pageIdx] = page;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 360 */         synchronized (this.ioLock) {
/*     */           
/* 362 */           this.inMemoryPages[pageIdx] = page;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 367 */       checkClosed();
/*     */     }
/*     */     else {
/*     */       
/* 371 */       synchronized (this.ioLock) {
/*     */         
/* 373 */         checkClosed();
/* 374 */         this.raf.seek((pageIdx - this.inMemoryMaxPageCount) * 4096L);
/* 375 */         this.raf.write(page);
/*     */       } 
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
/*     */   void checkClosed() throws IOException {
/* 388 */     if (this.isClosed)
/*     */     {
/* 390 */       throw new IOException("Scratch file already closed");
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
/*     */   
/*     */   public RandomAccess createBuffer() throws IOException {
/* 403 */     return new ScratchFileBuffer(this);
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
/*     */   public RandomAccess createBuffer(InputStream input) throws IOException {
/* 418 */     ScratchFileBuffer buf = new ScratchFileBuffer(this);
/*     */     
/* 420 */     byte[] byteBuffer = new byte[8192];
/* 421 */     int bytesRead = 0;
/* 422 */     while ((bytesRead = input.read(byteBuffer)) > -1)
/*     */     {
/* 424 */       buf.write(byteBuffer, 0, bytesRead);
/*     */     }
/* 426 */     buf.seek(0L);
/*     */     
/* 428 */     return buf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void markPagesAsFree(int[] pageIndexes, int off, int count) {
/* 439 */     synchronized (this.freePages) {
/*     */       
/* 441 */       for (int aIdx = off; aIdx < count; aIdx++) {
/*     */         
/* 443 */         int pageIdx = pageIndexes[aIdx];
/* 444 */         if (pageIdx >= 0 && pageIdx < this.pageCount && !this.freePages.get(pageIdx)) {
/*     */           
/* 446 */           this.freePages.set(pageIdx);
/* 447 */           if (pageIdx < this.inMemoryMaxPageCount)
/*     */           {
/* 449 */             this.inMemoryPages[pageIdx] = null;
/*     */           }
/*     */         } 
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 468 */     IOException ioexc = null;
/*     */     
/* 470 */     synchronized (this.ioLock) {
/*     */       
/* 472 */       if (this.isClosed) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 477 */       this.isClosed = true;
/*     */       
/* 479 */       if (this.raf != null) {
/*     */         
/*     */         try {
/*     */           
/* 483 */           this.raf.close();
/*     */         }
/* 485 */         catch (IOException ioe) {
/*     */           
/* 487 */           ioexc = ioe;
/*     */         } 
/*     */       }
/*     */       
/* 491 */       if (this.file != null)
/*     */       {
/* 493 */         if (!this.file.delete())
/*     */         {
/* 495 */           if (this.file.exists() && ioexc == null)
/*     */           {
/* 497 */             ioexc = new IOException("Error deleting scratch file: " + this.file.getAbsolutePath());
/*     */           }
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 503 */     synchronized (this.freePages) {
/*     */       
/* 505 */       this.freePages.clear();
/* 506 */       this.pageCount = 0;
/*     */     } 
/*     */     
/* 509 */     if (ioexc != null)
/*     */     {
/* 511 */       throw ioexc;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/ScratchFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */