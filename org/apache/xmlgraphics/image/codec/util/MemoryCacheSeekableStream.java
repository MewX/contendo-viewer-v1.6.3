/*     */ package org.apache.xmlgraphics.image.codec.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MemoryCacheSeekableStream
/*     */   extends SeekableStream
/*     */ {
/*     */   private InputStream src;
/*     */   private long pointer;
/*     */   private static final int SECTOR_SHIFT = 9;
/*     */   private static final int SECTOR_SIZE = 512;
/*     */   private static final int SECTOR_MASK = 511;
/*  58 */   private List data = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int length;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean foundEOS;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MemoryCacheSeekableStream(InputStream src) {
/*  75 */     this.src = src;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long readUntil(long pos) throws IOException {
/*  86 */     if (pos < this.length) {
/*  87 */       return pos;
/*     */     }
/*     */     
/*  90 */     if (this.foundEOS) {
/*  91 */       return this.length;
/*     */     }
/*     */     
/*  94 */     int sector = (int)(pos >> 9L);
/*     */ 
/*     */     
/*  97 */     int startSector = this.length >> 9;
/*     */ 
/*     */     
/* 100 */     for (int i = startSector; i <= sector; i++) {
/* 101 */       byte[] buf = new byte[512];
/* 102 */       this.data.add(buf);
/*     */ 
/*     */       
/* 105 */       int len = 512;
/* 106 */       int off = 0;
/* 107 */       while (len > 0) {
/* 108 */         int nbytes = this.src.read(buf, off, len);
/*     */         
/* 110 */         if (nbytes == -1) {
/* 111 */           this.foundEOS = true;
/* 112 */           return this.length;
/*     */         } 
/* 114 */         off += nbytes;
/* 115 */         len -= nbytes;
/*     */ 
/*     */         
/* 118 */         this.length += nbytes;
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSeekBackwards() {
/* 131 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getFilePointer() {
/* 141 */     return this.pointer;
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
/*     */   public void seek(long pos) throws IOException {
/* 155 */     if (pos < 0L) {
/* 156 */       throw new IOException(PropertyUtil.getString("MemoryCacheSeekableStream0"));
/*     */     }
/* 158 */     this.pointer = pos;
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
/*     */   public int read() throws IOException {
/* 173 */     long next = this.pointer + 1L;
/* 174 */     long pos = readUntil(next);
/* 175 */     if (pos >= next) {
/* 176 */       byte[] buf = this.data.get((int)(this.pointer >> 9L));
/*     */       
/* 178 */       return buf[(int)(this.pointer++ & 0x1FFL)] & 0xFF;
/*     */     } 
/* 180 */     return -1;
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
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 232 */     if (b == null) {
/* 233 */       throw new NullPointerException();
/*     */     }
/* 235 */     if (off < 0 || len < 0 || off + len > b.length) {
/* 236 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 238 */     if (len == 0) {
/* 239 */       return 0;
/*     */     }
/*     */     
/* 242 */     long pos = readUntil(this.pointer + len);
/*     */     
/* 244 */     if (pos <= this.pointer) {
/* 245 */       return -1;
/*     */     }
/*     */     
/* 248 */     byte[] buf = this.data.get((int)(this.pointer >> 9L));
/* 249 */     int nbytes = Math.min(len, 512 - (int)(this.pointer & 0x1FFL));
/* 250 */     System.arraycopy(buf, (int)(this.pointer & 0x1FFL), b, off, nbytes);
/*     */     
/* 252 */     this.pointer += nbytes;
/* 253 */     return nbytes;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/util/MemoryCacheSeekableStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */