/*     */ package org.apache.xmlgraphics.util.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Base64DecodeStream
/*     */   extends InputStream
/*     */ {
/*     */   InputStream src;
/*     */   
/*     */   public Base64DecodeStream(InputStream src) {
/* 101 */     this.decodeBuffer = new byte[4];
/* 102 */     this.outBuffer = new byte[3];
/* 103 */     this.outOffset = 3;
/*     */     this.src = src;
/*     */   }
/*     */   private static final byte[] PEM_ARRAY = new byte[256]; byte[] decodeBuffer; byte[] outBuffer; int outOffset; boolean eof;
/*     */   
/* 108 */   public int read() throws IOException { if (this.outOffset == 3 && (
/* 109 */       this.eof || getNextAtom())) {
/* 110 */       this.eof = true;
/* 111 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/* 115 */     return this.outBuffer[this.outOffset++] & 0xFF; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] out, int offset, int len) throws IOException {
/* 121 */     int idx = 0;
/* 122 */     while (idx < len) {
/* 123 */       if (this.outOffset == 3 && (
/* 124 */         this.eof || getNextAtom())) {
/* 125 */         this.eof = true;
/* 126 */         if (idx == 0) {
/* 127 */           return -1;
/*     */         }
/* 129 */         return idx;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 134 */       out[offset + idx] = this.outBuffer[this.outOffset++];
/*     */       
/* 136 */       idx++;
/*     */     } 
/* 138 */     return idx;
/*     */   }
/*     */   static { for (int i = 0; i < PEM_ARRAY.length; i++)
/*     */       PEM_ARRAY[i] = -1;  int idx = 0; char c; for (c = 'A'; c <= 'Z'; c = (char)(c + 1))
/*     */       PEM_ARRAY[c] = (byte)idx++;  for (c = 'a'; c <= 'z'; c = (char)(c + 1))
/*     */       PEM_ARRAY[c] = (byte)idx++;  for (c = '0'; c <= '9'; c = (char)(c + 1))
/*     */       PEM_ARRAY[c] = (byte)idx++; 
/*     */     PEM_ARRAY[43] = (byte)idx++;
/*     */     PEM_ARRAY[47] = (byte)idx++; }
/*     */   public boolean markSupported() { return false; }
/* 148 */   public void close() throws IOException { this.eof = true; } public int available() throws IOException { return 3 - this.outOffset; } final boolean getNextAtom() throws IOException { int off = 0;
/* 149 */     while (off != 4) {
/* 150 */       int count = this.src.read(this.decodeBuffer, off, 4 - off);
/* 151 */       if (count == -1) {
/* 152 */         return true;
/*     */       }
/*     */       
/* 155 */       int in = off;
/* 156 */       int out = off;
/* 157 */       while (in < off + count) {
/* 158 */         if (this.decodeBuffer[in] != 10 && this.decodeBuffer[in] != 13 && this.decodeBuffer[in] != 32)
/*     */         {
/*     */           
/* 161 */           this.decodeBuffer[out++] = this.decodeBuffer[in];
/*     */         }
/* 163 */         in++;
/*     */       } 
/*     */       
/* 166 */       off = out;
/*     */     } 
/*     */     
/* 169 */     int a = PEM_ARRAY[this.decodeBuffer[0] & 0xFF];
/* 170 */     int b = PEM_ARRAY[this.decodeBuffer[1] & 0xFF];
/* 171 */     int c = PEM_ARRAY[this.decodeBuffer[2] & 0xFF];
/* 172 */     int d = PEM_ARRAY[this.decodeBuffer[3] & 0xFF];
/*     */     
/* 174 */     this.outBuffer[0] = (byte)(a << 2 | b >>> 4);
/* 175 */     this.outBuffer[1] = (byte)(b << 4 | c >>> 2);
/* 176 */     this.outBuffer[2] = (byte)(c << 6 | d);
/*     */     
/* 178 */     if (this.decodeBuffer[3] != 61) {
/*     */       
/* 180 */       this.outOffset = 0;
/* 181 */     } else if (this.decodeBuffer[2] == 61) {
/*     */       
/* 183 */       this.outBuffer[2] = this.outBuffer[0];
/* 184 */       this.outOffset = 2;
/* 185 */       this.eof = true;
/*     */     } else {
/*     */       
/* 188 */       this.outBuffer[2] = this.outBuffer[1];
/* 189 */       this.outBuffer[1] = this.outBuffer[0];
/* 190 */       this.outOffset = 1;
/* 191 */       this.eof = true;
/*     */     } 
/*     */     
/* 194 */     return false; }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/io/Base64DecodeStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */