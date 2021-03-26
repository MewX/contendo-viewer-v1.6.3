/*     */ package org.apache.batik.util;
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
/*     */ public class Base64DecodeStream
/*     */   extends InputStream
/*     */ {
/*     */   InputStream src;
/*     */   
/*     */   public Base64DecodeStream(InputStream src) {
/*  91 */     this.decode_buffer = new byte[4];
/*  92 */     this.out_buffer = new byte[3];
/*  93 */     this.out_offset = 3;
/*  94 */     this.EOF = false; this.src = src;
/*     */   } private static final byte[] pem_array = new byte[256]; byte[] decode_buffer; byte[] out_buffer; int out_offset; boolean EOF; static { for (int i = 0; i < pem_array.length; i++) pem_array[i] = -1;  int idx = 0; char c; for (c = 'A'; c <= 'Z'; c = (char)(c + 1))
/*     */       pem_array[c] = (byte)idx++;  for (c = 'a'; c <= 'z'; c = (char)(c + 1))
/*     */       pem_array[c] = (byte)idx++;  for (c = '0'; c <= '9'; c = (char)(c + 1))
/*  98 */       pem_array[c] = (byte)idx++;  pem_array[43] = (byte)idx++; pem_array[47] = (byte)idx++; } public int read() throws IOException { if (this.out_offset == 3 && (
/*  99 */       this.EOF || getNextAtom())) {
/* 100 */       this.EOF = true;
/* 101 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/* 105 */     return this.out_buffer[this.out_offset++] & 0xFF; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] out, int offset, int len) throws IOException {
/* 111 */     int idx = 0;
/* 112 */     while (idx < len) {
/* 113 */       if (this.out_offset == 3 && (
/* 114 */         this.EOF || getNextAtom())) {
/* 115 */         this.EOF = true;
/* 116 */         if (idx == 0) return -1; 
/* 117 */         return idx;
/*     */       } 
/*     */ 
/*     */       
/* 121 */       out[offset + idx] = this.out_buffer[this.out_offset++];
/*     */       
/* 123 */       idx++;
/*     */     } 
/* 125 */     return idx; } public boolean markSupported() { return false; } public void close() throws IOException {
/*     */     this.EOF = true;
/*     */   } public int available() throws IOException {
/*     */     return 3 - this.out_offset;
/*     */   }
/*     */   final boolean getNextAtom() throws IOException {
/* 131 */     int off = 0;
/* 132 */     while (off != 4) {
/* 133 */       int count = this.src.read(this.decode_buffer, off, 4 - off);
/* 134 */       if (count == -1) {
/* 135 */         return true;
/*     */       }
/* 137 */       int in = off, out = off;
/* 138 */       while (in < off + count) {
/* 139 */         if (this.decode_buffer[in] != 10 && this.decode_buffer[in] != 13 && this.decode_buffer[in] != 32)
/*     */         {
/*     */           
/* 142 */           this.decode_buffer[out++] = this.decode_buffer[in]; } 
/* 143 */         in++;
/*     */       } 
/*     */       
/* 146 */       off = out;
/*     */     } 
/*     */     
/* 149 */     int a = pem_array[this.decode_buffer[0] & 0xFF];
/* 150 */     int b = pem_array[this.decode_buffer[1] & 0xFF];
/* 151 */     int c = pem_array[this.decode_buffer[2] & 0xFF];
/* 152 */     int d = pem_array[this.decode_buffer[3] & 0xFF];
/*     */     
/* 154 */     this.out_buffer[0] = (byte)(a << 2 | b >>> 4);
/* 155 */     this.out_buffer[1] = (byte)(b << 4 | c >>> 2);
/* 156 */     this.out_buffer[2] = (byte)(c << 6 | d);
/*     */     
/* 158 */     if (this.decode_buffer[3] != 61) {
/*     */       
/* 160 */       this.out_offset = 0;
/* 161 */     } else if (this.decode_buffer[2] == 61) {
/*     */       
/* 163 */       this.out_buffer[2] = this.out_buffer[0];
/* 164 */       this.out_offset = 2;
/* 165 */       this.EOF = true;
/*     */     } else {
/*     */       
/* 168 */       this.out_buffer[2] = this.out_buffer[1];
/* 169 */       this.out_buffer[1] = this.out_buffer[0];
/* 170 */       this.out_offset = 1;
/* 171 */       this.EOF = true;
/*     */     } 
/*     */     
/* 174 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/Base64DecodeStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */