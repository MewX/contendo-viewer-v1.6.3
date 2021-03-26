/*     */ package org.apache.xmlgraphics.util.io;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ASCII85OutputStream
/*     */   extends FilterOutputStream
/*     */   implements ASCII85Constants, Finalizable
/*     */ {
/*     */   private static final boolean DEBUG = false;
/*     */   private int pos;
/*     */   private long buffer;
/*     */   private int posinline;
/*     */   private int bw;
/*     */   
/*     */   public ASCII85OutputStream(OutputStream out) {
/*  43 */     super(out);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/*  48 */     if (this.pos == 0) {
/*  49 */       this.buffer += (b << 24) & 0xFF000000L;
/*  50 */     } else if (this.pos == 1) {
/*  51 */       this.buffer += (b << 16) & 0xFF0000L;
/*  52 */     } else if (this.pos == 2) {
/*  53 */       this.buffer += (b << 8) & 0xFF00L;
/*     */     } else {
/*  55 */       this.buffer += b & 0xFFL;
/*     */     } 
/*  57 */     this.pos++;
/*     */     
/*  59 */     if (this.pos > 3) {
/*  60 */       checkedWrite(convertWord(this.buffer));
/*  61 */       this.buffer = 0L;
/*  62 */       this.pos = 0;
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
/*     */   private void checkedWrite(byte[] buf) throws IOException {
/*  78 */     checkedWrite(buf, buf.length, false);
/*     */   }
/*     */   
/*     */   private void checkedWrite(byte[] buf, boolean nosplit) throws IOException {
/*  82 */     checkedWrite(buf, buf.length, nosplit);
/*     */   }
/*     */   
/*     */   private void checkedWrite(byte[] buf, int len) throws IOException {
/*  86 */     checkedWrite(buf, len, false);
/*     */   }
/*     */   
/*     */   private void checkedWrite(byte[] buf, int len, boolean nosplit) throws IOException {
/*  90 */     if (this.posinline + len > 80) {
/*  91 */       int firstpart = nosplit ? 0 : (len - this.posinline + len - 80);
/*  92 */       if (firstpart > 0) {
/*  93 */         this.out.write(buf, 0, firstpart);
/*     */       }
/*  95 */       this.out.write(10);
/*  96 */       this.bw++;
/*  97 */       int rest = len - firstpart;
/*  98 */       if (rest > 0) {
/*  99 */         this.out.write(buf, firstpart, rest);
/*     */       }
/* 101 */       this.posinline = rest;
/*     */     } else {
/* 103 */       this.out.write(buf, 0, len);
/* 104 */       this.posinline += len;
/*     */     } 
/* 106 */     this.bw += len;
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
/*     */   private byte[] convertWord(long word) {
/* 119 */     word &= 0xFFFFFFFFFFFFFFFFL;
/*     */     
/* 121 */     if (word == 0L) {
/* 122 */       return ZERO_ARRAY;
/*     */     }
/* 124 */     if (word < 0L) {
/* 125 */       word = -word;
/*     */     }
/* 127 */     byte c1 = (byte)(int)(word / POW85[0] & 0xFFL);
/*     */ 
/*     */     
/* 130 */     byte c2 = (byte)(int)((word - c1 * POW85[0]) / POW85[1] & 0xFFL);
/*     */ 
/*     */     
/* 133 */     byte c3 = (byte)(int)((word - c1 * POW85[0] - c2 * POW85[1]) / POW85[2] & 0xFFL);
/*     */ 
/*     */ 
/*     */     
/* 137 */     byte c4 = (byte)(int)((word - c1 * POW85[0] - c2 * POW85[1] - c3 * POW85[2]) / POW85[3] & 0xFFL);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     byte c5 = (byte)(int)(word - c1 * POW85[0] - c2 * POW85[1] - c3 * POW85[2] - c4 * POW85[3] & 0xFFL);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     byte[] ret = { (byte)(c1 + 33), (byte)(c2 + 33), (byte)(c3 + 33), (byte)(c4 + 33), (byte)(c5 + 33) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finalizeStream() throws IOException {
/* 173 */     if (this.pos > 0) {
/* 174 */       byte[] conv; int rest = this.pos;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 194 */       if (this.buffer != 0L) {
/* 195 */         conv = convertWord(this.buffer);
/*     */       } else {
/* 197 */         conv = new byte[5];
/* 198 */         for (int j = 0; j < 5; j++) {
/* 199 */           conv[j] = 33;
/*     */         }
/*     */       } 
/*     */       
/* 203 */       checkedWrite(conv, rest + 1);
/*     */     } 
/*     */     
/* 206 */     checkedWrite(EOD, true);
/*     */     
/* 208 */     flush();
/* 209 */     if (this.out instanceof Finalizable) {
/* 210 */       ((Finalizable)this.out).finalizeStream();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 216 */     finalizeStream();
/* 217 */     super.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/io/ASCII85OutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */