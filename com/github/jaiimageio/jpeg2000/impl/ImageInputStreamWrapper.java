/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageInputStreamWrapper
/*     */   extends InputStream
/*     */ {
/*     */   private ImageInputStream src;
/*     */   
/*     */   public ImageInputStreamWrapper(ImageInputStream src) {
/*  70 */     this.src = src;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  75 */     return this.src.read();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  79 */     this.src.close();
/*     */   }
/*     */   
/*     */   public synchronized void mark(int readlimit) {
/*  83 */     this.src.mark();
/*     */   }
/*     */   
/*     */   public boolean markSupported() {
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/*  91 */     return this.src.read(b, 0, b.length);
/*     */   }
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/*  95 */     return this.src.read(b, off, len);
/*     */   }
/*     */   
/*     */   public synchronized void reset() throws IOException {
/*  99 */     this.src.reset();
/*     */   }
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 103 */     return this.src.skipBytes(n);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/ImageInputStreamWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */