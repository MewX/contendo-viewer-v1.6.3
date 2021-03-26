/*     */ package c.a.c.a;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */ {
/*     */   private byte[] a;
/*     */   private int b;
/*     */   private int c;
/*     */   
/*     */   public a(byte[] buf) {
/*  92 */     this.a = buf;
/*  93 */     this.b = buf.length;
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
/*     */   public a(byte[] buf, int offset, int length) {
/* 112 */     this.a = buf;
/* 113 */     this.c = offset;
/* 114 */     this.b = offset + length;
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
/*     */   public void a(byte[] buf, int offset, int length) {
/* 136 */     if (buf == null) {
/* 137 */       if (length < 0 || this.b + length > this.a.length) {
/* 138 */         throw new IllegalArgumentException();
/*     */       }
/* 140 */       if (offset < 0) {
/* 141 */         this.c = this.b;
/* 142 */         this.b += length;
/*     */       } else {
/* 144 */         this.b = offset + length;
/* 145 */         this.c = offset;
/*     */       } 
/*     */     } else {
/* 148 */       if (offset < 0 || length < 0 || offset + length > buf.length) {
/* 149 */         throw new IllegalArgumentException();
/*     */       }
/* 151 */       this.a = buf;
/* 152 */       this.b = offset + length;
/* 153 */       this.c = offset;
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
/*     */ 
/*     */   
/*     */   public synchronized void b(byte[] data, int off, int len) {
/* 173 */     if (len < 0 || off < 0 || len + off > this.a.length) {
/* 174 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 177 */     if (this.b + len <= this.a.length) {
/* 178 */       System.arraycopy(data, off, this.a, this.b, len);
/* 179 */       this.b += len;
/*     */     } else {
/* 181 */       if (this.b - this.c + len <= this.a.length) {
/*     */ 
/*     */         
/* 184 */         System.arraycopy(this.a, this.c, this.a, 0, this.b - this.c);
/*     */       } else {
/* 186 */         byte[] oldbuf = this.a;
/* 187 */         this.a = new byte[this.b - this.c + len];
/*     */         
/* 189 */         System.arraycopy(oldbuf, this.b, this.a, 0, this.b - this.c);
/*     */       } 
/* 191 */       this.b -= this.c;
/* 192 */       this.c = 0;
/*     */       
/* 194 */       System.arraycopy(data, off, this.a, this.b, len);
/* 195 */       this.b += len;
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
/*     */   
/*     */   public int a() throws IOException {
/* 214 */     if (this.c < this.b) {
/* 215 */       return this.a[this.c++] & 0xFF;
/*     */     }
/* 217 */     throw new EOFException();
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
/*     */   public int b() {
/* 234 */     if (this.c < this.b) {
/* 235 */       return this.a[this.c++] & 0xFF;
/*     */     }
/* 237 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/a/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */