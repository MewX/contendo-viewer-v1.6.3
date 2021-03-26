/*     */ package org.apache.pdfbox.io;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
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
/*     */ class ScratchFileBuffer
/*     */   implements RandomAccess
/*     */ {
/*     */   private final int pageSize;
/*     */   private ScratchFile pageHandler;
/*  40 */   private long size = 0L;
/*     */ 
/*     */ 
/*     */   
/*     */   private int currentPagePositionInPageIndexes;
/*     */ 
/*     */ 
/*     */   
/*     */   private long currentPageOffset;
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] currentPage;
/*     */ 
/*     */ 
/*     */   
/*     */   private int positionInPage;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean currentPageContentChanged = false;
/*     */ 
/*     */   
/*  63 */   private int[] pageIndexes = new int[16];
/*     */   
/*  65 */   private int pageCount = 0;
/*     */   
/*  67 */   private static final Log LOG = LogFactory.getLog(ScratchFileBuffer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ScratchFileBuffer(ScratchFile pageHandler) throws IOException {
/*  78 */     pageHandler.checkClosed();
/*     */     
/*  80 */     this.pageHandler = pageHandler;
/*     */     
/*  82 */     this.pageSize = this.pageHandler.getPageSize();
/*     */     
/*  84 */     addPage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkClosed() throws IOException {
/*  95 */     if (this.pageHandler == null)
/*     */     {
/*  97 */       throw new IOException("Buffer already closed");
/*     */     }
/*  99 */     this.pageHandler.checkClosed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addPage() throws IOException {
/* 109 */     if (this.pageCount + 1 >= this.pageIndexes.length) {
/*     */       
/* 111 */       int newSize = this.pageIndexes.length * 2;
/*     */       
/* 113 */       if (newSize < this.pageIndexes.length) {
/*     */         
/* 115 */         if (this.pageIndexes.length == Integer.MAX_VALUE)
/*     */         {
/* 117 */           throw new IOException("Maximum buffer size reached.");
/*     */         }
/* 119 */         newSize = Integer.MAX_VALUE;
/*     */       } 
/* 121 */       int[] newPageIndexes = new int[newSize];
/* 122 */       System.arraycopy(this.pageIndexes, 0, newPageIndexes, 0, this.pageCount);
/* 123 */       this.pageIndexes = newPageIndexes;
/*     */     } 
/*     */     
/* 126 */     int newPageIdx = this.pageHandler.getNewPage();
/*     */     
/* 128 */     this.pageIndexes[this.pageCount] = newPageIdx;
/* 129 */     this.currentPagePositionInPageIndexes = this.pageCount;
/* 130 */     this.currentPageOffset = this.pageCount * this.pageSize;
/* 131 */     this.pageCount++;
/* 132 */     this.currentPage = new byte[this.pageSize];
/* 133 */     this.positionInPage = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long length() throws IOException {
/* 142 */     return this.size;
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
/*     */   private boolean ensureAvailableBytesInPage(boolean addNewPageIfNeeded) throws IOException {
/* 164 */     if (this.positionInPage >= this.pageSize) {
/*     */ 
/*     */       
/* 167 */       if (this.currentPageContentChanged) {
/*     */ 
/*     */         
/* 170 */         this.pageHandler.writePage(this.pageIndexes[this.currentPagePositionInPageIndexes], this.currentPage);
/* 171 */         this.currentPageContentChanged = false;
/*     */       } 
/*     */       
/* 174 */       if (this.currentPagePositionInPageIndexes + 1 < this.pageCount) {
/*     */ 
/*     */         
/* 177 */         this.currentPage = this.pageHandler.readPage(this.pageIndexes[++this.currentPagePositionInPageIndexes]);
/* 178 */         this.currentPageOffset = this.currentPagePositionInPageIndexes * this.pageSize;
/* 179 */         this.positionInPage = 0;
/*     */       }
/* 181 */       else if (addNewPageIfNeeded) {
/*     */ 
/*     */         
/* 184 */         addPage();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 189 */         return false;
/*     */       } 
/*     */     } 
/* 192 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 201 */     checkClosed();
/*     */     
/* 203 */     ensureAvailableBytesInPage(true);
/*     */     
/* 205 */     this.currentPage[this.positionInPage++] = (byte)b;
/* 206 */     this.currentPageContentChanged = true;
/*     */     
/* 208 */     if (this.currentPageOffset + this.positionInPage > this.size)
/*     */     {
/* 210 */       this.size = this.currentPageOffset + this.positionInPage;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 220 */     write(b, 0, b.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 229 */     checkClosed();
/*     */     
/* 231 */     int remain = len;
/* 232 */     int bOff = off;
/*     */     
/* 234 */     while (remain > 0) {
/*     */       
/* 236 */       ensureAvailableBytesInPage(true);
/*     */       
/* 238 */       int bytesToWrite = Math.min(remain, this.pageSize - this.positionInPage);
/*     */       
/* 240 */       System.arraycopy(b, bOff, this.currentPage, this.positionInPage, bytesToWrite);
/*     */       
/* 242 */       this.positionInPage += bytesToWrite;
/* 243 */       this.currentPageContentChanged = true;
/*     */       
/* 245 */       bOff += bytesToWrite;
/* 246 */       remain -= bytesToWrite;
/*     */     } 
/*     */     
/* 249 */     if (this.currentPageOffset + this.positionInPage > this.size)
/*     */     {
/* 251 */       this.size = this.currentPageOffset + this.positionInPage;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clear() throws IOException {
/* 261 */     checkClosed();
/*     */ 
/*     */     
/* 264 */     this.pageHandler.markPagesAsFree(this.pageIndexes, 1, this.pageCount - 1);
/* 265 */     this.pageCount = 1;
/*     */ 
/*     */     
/* 268 */     if (this.currentPagePositionInPageIndexes > 0) {
/*     */       
/* 270 */       this.currentPage = this.pageHandler.readPage(this.pageIndexes[0]);
/* 271 */       this.currentPagePositionInPageIndexes = 0;
/* 272 */       this.currentPageOffset = 0L;
/*     */     } 
/* 274 */     this.positionInPage = 0;
/* 275 */     this.size = 0L;
/* 276 */     this.currentPageContentChanged = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getPosition() throws IOException {
/* 285 */     checkClosed();
/* 286 */     return this.currentPageOffset + this.positionInPage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void seek(long seekToPosition) throws IOException {
/* 295 */     checkClosed();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 300 */     if (seekToPosition > this.size)
/*     */     {
/* 302 */       throw new EOFException();
/*     */     }
/*     */     
/* 305 */     if (seekToPosition < 0L)
/*     */     {
/* 307 */       throw new IOException("Negative seek offset: " + seekToPosition);
/*     */     }
/*     */     
/* 310 */     if (seekToPosition >= this.currentPageOffset && seekToPosition <= this.currentPageOffset + this.pageSize) {
/*     */ 
/*     */       
/* 313 */       this.positionInPage = (int)(seekToPosition - this.currentPageOffset);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 320 */       if (this.currentPageContentChanged) {
/*     */         
/* 322 */         this.pageHandler.writePage(this.pageIndexes[this.currentPagePositionInPageIndexes], this.currentPage);
/* 323 */         this.currentPageContentChanged = false;
/*     */       } 
/*     */       
/* 326 */       int newPagePosition = (int)(seekToPosition / this.pageSize);
/*     */       
/* 328 */       this.currentPage = this.pageHandler.readPage(this.pageIndexes[newPagePosition]);
/* 329 */       this.currentPagePositionInPageIndexes = newPagePosition;
/* 330 */       this.currentPageOffset = this.currentPagePositionInPageIndexes * this.pageSize;
/* 331 */       this.positionInPage = (int)(seekToPosition - this.currentPageOffset);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 341 */     return (this.pageHandler == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int peek() throws IOException {
/* 350 */     int result = read();
/* 351 */     if (result != -1)
/*     */     {
/* 353 */       rewind(1);
/*     */     }
/* 355 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rewind(int bytes) throws IOException {
/* 364 */     seek(this.currentPageOffset + this.positionInPage - bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] readFully(int len) throws IOException {
/* 373 */     byte[] b = new byte[len];
/*     */     
/* 375 */     int n = 0;
/*     */     
/*     */     do {
/* 378 */       int count = read(b, n, len - n);
/* 379 */       if (count < 0)
/*     */       {
/* 381 */         throw new EOFException();
/*     */       }
/* 383 */       n += count;
/* 384 */     } while (n < len);
/*     */     
/* 386 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEOF() throws IOException {
/* 395 */     checkClosed();
/* 396 */     return (this.currentPageOffset + this.positionInPage >= this.size);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/* 405 */     checkClosed();
/* 406 */     return (int)Math.min(this.size - this.currentPageOffset + this.positionInPage, 2147483647L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 415 */     checkClosed();
/*     */     
/* 417 */     if (this.currentPageOffset + this.positionInPage >= this.size)
/*     */     {
/* 419 */       return -1;
/*     */     }
/*     */     
/* 422 */     if (!ensureAvailableBytesInPage(false))
/*     */     {
/*     */       
/* 425 */       throw new IOException("Unexpectedly no bytes available for read in buffer.");
/*     */     }
/*     */     
/* 428 */     return this.currentPage[this.positionInPage++] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 437 */     return read(b, 0, b.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 446 */     checkClosed();
/*     */     
/* 448 */     if (this.currentPageOffset + this.positionInPage >= this.size)
/*     */     {
/* 450 */       return -1;
/*     */     }
/*     */     
/* 453 */     int remain = (int)Math.min(len, this.size - this.currentPageOffset + this.positionInPage);
/*     */     
/* 455 */     int totalBytesRead = 0;
/* 456 */     int bOff = off;
/*     */     
/* 458 */     while (remain > 0) {
/*     */       
/* 460 */       if (!ensureAvailableBytesInPage(false))
/*     */       {
/*     */         
/* 463 */         throw new IOException("Unexpectedly no bytes available for read in buffer.");
/*     */       }
/*     */       
/* 466 */       int readBytes = Math.min(remain, this.pageSize - this.positionInPage);
/*     */       
/* 468 */       System.arraycopy(this.currentPage, this.positionInPage, b, bOff, readBytes);
/*     */       
/* 470 */       this.positionInPage += readBytes;
/* 471 */       totalBytesRead += readBytes;
/* 472 */       bOff += readBytes;
/* 473 */       remain -= readBytes;
/*     */     } 
/*     */     
/* 476 */     return totalBytesRead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 485 */     if (this.pageHandler != null) {
/*     */       
/* 487 */       this.pageHandler.markPagesAsFree(this.pageIndexes, 0, this.pageCount);
/* 488 */       this.pageHandler = null;
/*     */       
/* 490 */       this.pageIndexes = null;
/* 491 */       this.currentPage = null;
/* 492 */       this.currentPageOffset = 0L;
/* 493 */       this.currentPagePositionInPageIndexes = -1;
/* 494 */       this.positionInPage = 0;
/* 495 */       this.size = 0L;
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
/*     */   protected void finalize() throws Throwable {
/*     */     try {
/* 514 */       if (this.pageHandler != null && LOG.isDebugEnabled())
/*     */       {
/* 516 */         LOG.debug("ScratchFileBuffer not closed!");
/*     */       }
/* 518 */       close();
/*     */     }
/*     */     finally {
/*     */       
/* 522 */       super.finalize();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/ScratchFileBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */