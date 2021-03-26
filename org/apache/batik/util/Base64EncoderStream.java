/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Base64EncoderStream
/*     */   extends OutputStream
/*     */ {
/*  41 */   private static final byte[] pem_array = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   byte[] atom = new byte[3];
/*  54 */   int atomLen = 0;
/*  55 */   byte[] encodeBuf = new byte[4];
/*  56 */   int lineLen = 0;
/*     */   
/*     */   PrintStream out;
/*     */   boolean closeOutOnClose;
/*     */   
/*     */   public Base64EncoderStream(OutputStream out) {
/*  62 */     this.out = new PrintStream(out);
/*  63 */     this.closeOutOnClose = true;
/*     */   }
/*     */   
/*     */   public Base64EncoderStream(OutputStream out, boolean closeOutOnClose) {
/*  67 */     this.out = new PrintStream(out);
/*  68 */     this.closeOutOnClose = closeOutOnClose;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  72 */     if (this.out != null) {
/*  73 */       encodeAtom();
/*  74 */       this.out.flush();
/*  75 */       if (this.closeOutOnClose)
/*  76 */         this.out.close(); 
/*  77 */       this.out = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/*  88 */     this.out.flush();
/*     */   }
/*     */   
/*     */   public void write(int b) throws IOException {
/*  92 */     this.atom[this.atomLen++] = (byte)b;
/*  93 */     if (this.atomLen == 3)
/*  94 */       encodeAtom(); 
/*     */   }
/*     */   
/*     */   public void write(byte[] data) throws IOException {
/*  98 */     encodeFromArray(data, 0, data.length);
/*     */   }
/*     */   
/*     */   public void write(byte[] data, int off, int len) throws IOException {
/* 102 */     encodeFromArray(data, off, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void encodeAtom() throws IOException {
/*     */     byte a, b, c;
/* 114 */     switch (this.atomLen) { case 0:
/*     */         return;
/*     */       case 1:
/* 117 */         a = this.atom[0];
/* 118 */         this.encodeBuf[0] = pem_array[a >>> 2 & 0x3F];
/* 119 */         this.encodeBuf[1] = pem_array[a << 4 & 0x30];
/* 120 */         this.encodeBuf[3] = 61; this.encodeBuf[2] = 61;
/*     */         break;
/*     */       case 2:
/* 123 */         a = this.atom[0];
/* 124 */         b = this.atom[1];
/* 125 */         this.encodeBuf[0] = pem_array[a >>> 2 & 0x3F];
/* 126 */         this.encodeBuf[1] = pem_array[a << 4 & 0x30 | b >>> 4 & 0xF];
/* 127 */         this.encodeBuf[2] = pem_array[b << 2 & 0x3C];
/* 128 */         this.encodeBuf[3] = 61;
/*     */         break;
/*     */       default:
/* 131 */         a = this.atom[0];
/* 132 */         b = this.atom[1];
/* 133 */         c = this.atom[2];
/* 134 */         this.encodeBuf[0] = pem_array[a >>> 2 & 0x3F];
/* 135 */         this.encodeBuf[1] = pem_array[a << 4 & 0x30 | b >>> 4 & 0xF];
/* 136 */         this.encodeBuf[2] = pem_array[b << 2 & 0x3C | c >>> 6 & 0x3];
/* 137 */         this.encodeBuf[3] = pem_array[c & 0x3F]; break; }
/*     */     
/* 139 */     if (this.lineLen == 64) {
/* 140 */       this.out.println();
/* 141 */       this.lineLen = 0;
/*     */     } 
/* 143 */     this.out.write(this.encodeBuf);
/*     */     
/* 145 */     this.lineLen += 4;
/* 146 */     this.atomLen = 0;
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
/*     */   void encodeFromArray(byte[] data, int offset, int len) throws IOException {
/* 158 */     if (len == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     if (this.atomLen != 0) {
/* 166 */       switch (this.atomLen) {
/*     */         case 1:
/* 168 */           this.atom[1] = data[offset++]; len--; this.atomLen++;
/* 169 */           if (len == 0)
/* 170 */             return;  this.atom[2] = data[offset++]; len--; this.atomLen++;
/*     */           break;
/*     */         case 2:
/* 173 */           this.atom[2] = data[offset++]; len--; this.atomLen++;
/*     */           break;
/*     */       } 
/*     */       
/* 177 */       encodeAtom();
/*     */     } 
/*     */     
/* 180 */     while (len >= 3) {
/* 181 */       byte a = data[offset++];
/* 182 */       byte b = data[offset++];
/* 183 */       byte c = data[offset++];
/*     */       
/* 185 */       this.encodeBuf[0] = pem_array[a >>> 2 & 0x3F];
/* 186 */       this.encodeBuf[1] = pem_array[a << 4 & 0x30 | b >>> 4 & 0xF];
/* 187 */       this.encodeBuf[2] = pem_array[b << 2 & 0x3C | c >>> 6 & 0x3];
/* 188 */       this.encodeBuf[3] = pem_array[c & 0x3F];
/* 189 */       this.out.write(this.encodeBuf);
/*     */       
/* 191 */       this.lineLen += 4;
/* 192 */       if (this.lineLen == 64) {
/* 193 */         this.out.println();
/* 194 */         this.lineLen = 0;
/*     */       } 
/*     */       
/* 197 */       len -= 3;
/*     */     } 
/*     */     
/* 200 */     switch (len) {
/*     */       case 1:
/* 202 */         this.atom[0] = data[offset];
/*     */         break;
/*     */       case 2:
/* 205 */         this.atom[0] = data[offset];
/* 206 */         this.atom[1] = data[offset + 1];
/*     */         break;
/*     */     } 
/*     */     
/* 210 */     this.atomLen = len;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/Base64EncoderStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */