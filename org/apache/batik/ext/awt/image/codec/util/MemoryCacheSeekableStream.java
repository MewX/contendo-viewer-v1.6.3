/*     */ package org.apache.batik.ext.awt.image.codec.util;
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
/*     */ public final class MemoryCacheSeekableStream
/*     */   extends SeekableStream
/*     */ {
/*     */   private InputStream src;
/*  47 */   private long pointer = 0L;
/*     */ 
/*     */   
/*     */   private static final int SECTOR_SHIFT = 9;
/*     */ 
/*     */   
/*     */   private static final int SECTOR_SIZE = 512;
/*     */ 
/*     */   
/*     */   private static final int SECTOR_MASK = 511;
/*     */ 
/*     */   
/*  59 */   private List data = new ArrayList();
/*     */ 
/*     */   
/*  62 */   int sectors = 0;
/*     */ 
/*     */   
/*  65 */   int length = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean foundEOS = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MemoryCacheSeekableStream(InputStream src) {
/*  76 */     this.src = src;
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
/*  87 */     if (pos < this.length) {
/*  88 */       return pos;
/*     */     }
/*     */     
/*  91 */     if (this.foundEOS) {
/*  92 */       return this.length;
/*     */     }
/*     */     
/*  95 */     int sector = (int)(pos >> 9L);
/*     */ 
/*     */     
/*  98 */     int startSector = this.length >> 9;
/*     */ 
/*     */     
/* 101 */     for (int i = startSector; i <= sector; i++) {
/* 102 */       byte[] buf = new byte[512];
/* 103 */       this.data.add(buf);
/*     */ 
/*     */       
/* 106 */       int len = 512;
/* 107 */       int off = 0;
/* 108 */       while (len > 0) {
/* 109 */         int nbytes = this.src.read(buf, off, len);
/*     */         
/* 111 */         if (nbytes == -1) {
/* 112 */           this.foundEOS = true;
/* 113 */           return this.length;
/*     */         } 
/* 115 */         off += nbytes;
/* 116 */         len -= nbytes;
/*     */ 
/*     */         
/* 119 */         this.length += nbytes;
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSeekBackwards() {
/* 132 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getFilePointer() {
/* 142 */     return this.pointer;
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
/* 156 */     if (pos < 0L) {
/* 157 */       throw new IOException(PropertyUtil.getString("MemoryCacheSeekableStream0"));
/*     */     }
/* 159 */     this.pointer = pos;
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
/* 174 */     long next = this.pointer + 1L;
/* 175 */     long pos = readUntil(next);
/* 176 */     if (pos >= next) {
/* 177 */       byte[] buf = this.data.get((int)(this.pointer >> 9L));
/*     */       
/* 179 */       return buf[(int)(this.pointer++ & 0x1FFL)] & 0xFF;
/*     */     } 
/* 181 */     return -1;
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
/* 233 */     if (b == null) {
/* 234 */       throw new NullPointerException();
/*     */     }
/* 236 */     if (off < 0 || len < 0 || off + len > b.length) {
/* 237 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 239 */     if (len == 0) {
/* 240 */       return 0;
/*     */     }
/*     */     
/* 243 */     long pos = readUntil(this.pointer + len);
/*     */     
/* 245 */     if (pos <= this.pointer) {
/* 246 */       return -1;
/*     */     }
/*     */     
/* 249 */     byte[] buf = this.data.get((int)(this.pointer >> 9L));
/* 250 */     int nbytes = Math.min(len, 512 - (int)(this.pointer & 0x1FFL));
/* 251 */     System.arraycopy(buf, (int)(this.pointer & 0x1FFL), b, off, nbytes);
/*     */     
/* 253 */     this.pointer += nbytes;
/* 254 */     return nbytes;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/util/MemoryCacheSeekableStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */