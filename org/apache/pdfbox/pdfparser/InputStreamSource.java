/*     */ package org.apache.pdfbox.pdfparser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PushbackInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class InputStreamSource
/*     */   implements SequentialSource
/*     */ {
/*     */   private final PushbackInputStream input;
/*     */   private int position;
/*     */   
/*     */   InputStreamSource(InputStream input) {
/*  39 */     this.input = new PushbackInputStream(input, 32767);
/*  40 */     this.position = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  46 */     int b = this.input.read();
/*  47 */     this.position++;
/*  48 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/*  54 */     int n = this.input.read(b);
/*  55 */     if (n > 0) {
/*     */       
/*  57 */       this.position += n;
/*  58 */       return n;
/*     */     } 
/*     */ 
/*     */     
/*  62 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int offset, int length) throws IOException {
/*  69 */     int n = this.input.read(b, offset, length);
/*  70 */     if (n > 0) {
/*     */       
/*  72 */       this.position += n;
/*  73 */       return n;
/*     */     } 
/*     */ 
/*     */     
/*  77 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getPosition() throws IOException {
/*  84 */     return this.position;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int peek() throws IOException {
/*  90 */     int b = this.input.read();
/*  91 */     if (b != -1)
/*     */     {
/*  93 */       this.input.unread(b);
/*     */     }
/*  95 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unread(int b) throws IOException {
/* 101 */     this.input.unread(b);
/* 102 */     this.position--;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unread(byte[] bytes) throws IOException {
/* 108 */     this.input.unread(bytes);
/* 109 */     this.position -= bytes.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unread(byte[] bytes, int start, int len) throws IOException {
/* 115 */     this.input.unread(bytes, start, len);
/* 116 */     this.position -= len - start;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] readFully(int length) throws IOException {
/* 122 */     byte[] bytes = new byte[length];
/* 123 */     int off = 0;
/* 124 */     int len = length;
/* 125 */     while (len > 0) {
/*     */       
/* 127 */       int n = read(bytes, off, len);
/* 128 */       if (n > 0) {
/*     */         
/* 130 */         off += n;
/* 131 */         len -= n;
/* 132 */         this.position += n;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     return bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEOF() throws IOException {
/* 145 */     return (peek() == -1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 151 */     this.input.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/InputStreamSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */