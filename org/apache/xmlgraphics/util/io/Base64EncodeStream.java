/*     */ package org.apache.xmlgraphics.util.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Base64EncodeStream
/*     */   extends OutputStream
/*     */ {
/*  50 */   private static final byte[] PEM_ARRAY = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   byte[] atom = new byte[3];
/*     */   int atomLen;
/*  64 */   byte[] encodeBuf = new byte[4];
/*     */   
/*     */   int lineLen;
/*     */   PrintStream out;
/*     */   boolean closeOutOnClose;
/*     */   
/*     */   public Base64EncodeStream(OutputStream out) {
/*     */     try {
/*  72 */       this.out = new PrintStream(out, false, "UTF-8");
/*  73 */     } catch (UnsupportedEncodingException e) {
/*  74 */       e.printStackTrace();
/*     */     } 
/*  76 */     this.closeOutOnClose = true;
/*     */   }
/*     */   
/*     */   public Base64EncodeStream(OutputStream out, boolean closeOutOnClose) {
/*     */     try {
/*  81 */       this.out = new PrintStream(out, false, "UTF-8");
/*  82 */     } catch (UnsupportedEncodingException e) {
/*  83 */       e.printStackTrace();
/*     */     } 
/*  85 */     this.closeOutOnClose = closeOutOnClose;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  89 */     if (this.out != null) {
/*  90 */       encodeAtom();
/*  91 */       this.out.flush();
/*  92 */       if (this.closeOutOnClose) {
/*  93 */         this.out.close();
/*     */       }
/*  95 */       this.out = null;
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
/* 106 */     this.out.flush();
/*     */   }
/*     */   
/*     */   public void write(int b) throws IOException {
/* 110 */     this.atom[this.atomLen++] = (byte)b;
/* 111 */     if (this.atomLen == 3) {
/* 112 */       encodeAtom();
/*     */     }
/*     */   }
/*     */   
/*     */   public void write(byte[] data) throws IOException {
/* 117 */     encodeFromArray(data, 0, data.length);
/*     */   }
/*     */   
/*     */   public void write(byte[] data, int off, int len) throws IOException {
/* 121 */     encodeFromArray(data, off, len);
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
/*     */   void encodeAtom() throws IOException {
/*     */     byte a, b, c;
/* 135 */     switch (this.atomLen) { case 0:
/*     */         return;
/*     */       case 1:
/* 138 */         a = this.atom[0];
/* 139 */         this.encodeBuf[0] = PEM_ARRAY[a >>> 2 & 0x3F];
/* 140 */         this.encodeBuf[1] = PEM_ARRAY[a << 4 & 0x30];
/* 141 */         this.encodeBuf[3] = 61; this.encodeBuf[2] = 61;
/*     */         break;
/*     */       case 2:
/* 144 */         a = this.atom[0];
/* 145 */         b = this.atom[1];
/* 146 */         this.encodeBuf[0] = PEM_ARRAY[a >>> 2 & 0x3F];
/* 147 */         this.encodeBuf[1] = PEM_ARRAY[a << 4 & 0x30 | b >>> 4 & 0xF];
/* 148 */         this.encodeBuf[2] = PEM_ARRAY[b << 2 & 0x3C];
/* 149 */         this.encodeBuf[3] = 61;
/*     */         break;
/*     */       default:
/* 152 */         a = this.atom[0];
/* 153 */         b = this.atom[1];
/* 154 */         c = this.atom[2];
/* 155 */         this.encodeBuf[0] = PEM_ARRAY[a >>> 2 & 0x3F];
/* 156 */         this.encodeBuf[1] = PEM_ARRAY[a << 4 & 0x30 | b >>> 4 & 0xF];
/* 157 */         this.encodeBuf[2] = PEM_ARRAY[b << 2 & 0x3C | c >>> 6 & 0x3];
/* 158 */         this.encodeBuf[3] = PEM_ARRAY[c & 0x3F]; break; }
/*     */     
/* 160 */     if (this.lineLen == 64) {
/* 161 */       this.out.println();
/* 162 */       this.lineLen = 0;
/*     */     } 
/* 164 */     this.out.write(this.encodeBuf);
/*     */     
/* 166 */     this.lineLen += 4;
/* 167 */     this.atomLen = 0;
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
/*     */   void encodeFromArray(byte[] data, int offset, int len) throws IOException {
/* 181 */     if (len == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 189 */     if (this.atomLen != 0) {
/* 190 */       switch (this.atomLen) {
/*     */         case 1:
/* 192 */           this.atom[1] = data[offset++];
/* 193 */           len--;
/* 194 */           this.atomLen++;
/* 195 */           if (len == 0) {
/*     */             return;
/*     */           }
/* 198 */           this.atom[2] = data[offset++];
/* 199 */           len--;
/* 200 */           this.atomLen++;
/*     */           break;
/*     */         case 2:
/* 203 */           this.atom[2] = data[offset++];
/* 204 */           len--;
/* 205 */           this.atomLen++;
/*     */           break;
/*     */       } 
/*     */       
/* 209 */       encodeAtom();
/*     */     } 
/*     */     
/* 212 */     while (len >= 3) {
/* 213 */       byte a = data[offset++];
/* 214 */       byte b = data[offset++];
/* 215 */       byte c = data[offset++];
/*     */       
/* 217 */       this.encodeBuf[0] = PEM_ARRAY[a >>> 2 & 0x3F];
/* 218 */       this.encodeBuf[1] = PEM_ARRAY[a << 4 & 0x30 | b >>> 4 & 0xF];
/* 219 */       this.encodeBuf[2] = PEM_ARRAY[b << 2 & 0x3C | c >>> 6 & 0x3];
/* 220 */       this.encodeBuf[3] = PEM_ARRAY[c & 0x3F];
/* 221 */       this.out.write(this.encodeBuf);
/*     */       
/* 223 */       this.lineLen += 4;
/* 224 */       if (this.lineLen == 64) {
/* 225 */         this.out.println();
/* 226 */         this.lineLen = 0;
/*     */       } 
/*     */       
/* 229 */       len -= 3;
/*     */     } 
/*     */     
/* 232 */     switch (len) {
/*     */       case 1:
/* 234 */         this.atom[0] = data[offset];
/*     */         break;
/*     */       case 2:
/* 237 */         this.atom[0] = data[offset];
/* 238 */         this.atom[1] = data[offset + 1];
/*     */         break;
/*     */     } 
/*     */     
/* 242 */     this.atomLen = len;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/io/Base64EncodeStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */