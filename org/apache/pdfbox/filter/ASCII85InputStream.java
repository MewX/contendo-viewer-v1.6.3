/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.io.FilterInputStream;
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
/*     */ final class ASCII85InputStream
/*     */   extends FilterInputStream
/*     */ {
/*     */   private int index;
/*     */   private int n;
/*     */   private boolean eof;
/*     */   private byte[] ascii;
/*     */   private byte[] b;
/*     */   private static final char TERMINATOR = '~';
/*     */   private static final char OFFSET = '!';
/*     */   private static final char NEWLINE = '\n';
/*     */   private static final char RETURN = '\r';
/*     */   private static final char SPACE = ' ';
/*     */   private static final char PADDING_U = 'u';
/*     */   private static final char Z = 'z';
/*     */   
/*     */   ASCII85InputStream(InputStream is) {
/*  53 */     super(is);
/*  54 */     this.index = 0;
/*  55 */     this.n = 0;
/*  56 */     this.eof = false;
/*  57 */     this.ascii = new byte[5];
/*  58 */     this.b = new byte[4];
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
/*     */   public int read() throws IOException {
/*  71 */     if (this.index >= this.n) {
/*     */       byte z;
/*  73 */       if (this.eof)
/*     */       {
/*  75 */         return -1;
/*     */       }
/*  77 */       this.index = 0;
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/*  82 */         int zz = (byte)this.in.read();
/*  83 */         if (zz == -1) {
/*     */           
/*  85 */           this.eof = true;
/*  86 */           return -1;
/*     */         } 
/*  88 */         z = (byte)zz;
/*  89 */       } while (z == 10 || z == 13 || z == 32);
/*     */       
/*  91 */       if (z == 126) {
/*     */         
/*  93 */         this.eof = true;
/*  94 */         this.ascii = this.b = null;
/*  95 */         this.n = 0;
/*  96 */         return -1;
/*     */       } 
/*  98 */       if (z == 122) {
/*     */         
/* 100 */         this.b[3] = 0; this.b[2] = 0; this.b[1] = 0; this.b[0] = 0;
/* 101 */         this.n = 4;
/*     */       }
/*     */       else {
/*     */         
/* 105 */         this.ascii[0] = z; int k;
/* 106 */         for (k = 1; k < 5;) {
/*     */           
/*     */           while (true) {
/*     */             
/* 110 */             int zz = (byte)this.in.read();
/* 111 */             if (zz == -1) {
/*     */               
/* 113 */               this.eof = true;
/* 114 */               return -1;
/*     */             } 
/* 116 */             z = (byte)zz;
/* 117 */             if (z != 10 && z != 13 && z != 32) {
/* 118 */               this.ascii[k] = z;
/* 119 */               if (z == 126) {
/*     */ 
/*     */                 
/* 122 */                 this.ascii[k] = 117; break;
/*     */               }  k++;
/*     */             } 
/*     */           } 
/* 126 */         }  this.n = k - 1;
/* 127 */         if (this.n == 0) {
/*     */           
/* 129 */           this.eof = true;
/* 130 */           this.ascii = null;
/* 131 */           this.b = null;
/* 132 */           return -1;
/*     */         } 
/* 134 */         if (k < 5) {
/*     */           
/* 136 */           for (; ++k < 5; k++)
/*     */           {
/*     */             
/* 139 */             this.ascii[k] = 117;
/*     */           }
/* 141 */           this.eof = true;
/*     */         } 
/*     */         
/* 144 */         long t = 0L;
/* 145 */         for (k = 0; k < 5; k++) {
/*     */           
/* 147 */           z = (byte)(this.ascii[k] - 33);
/* 148 */           if (z < 0 || z > 93) {
/*     */             
/* 150 */             this.n = 0;
/* 151 */             this.eof = true;
/* 152 */             this.ascii = null;
/* 153 */             this.b = null;
/* 154 */             throw new IOException("Invalid data in Ascii85 stream");
/*     */           } 
/* 156 */           t = t * 85L + z;
/*     */         } 
/* 158 */         for (k = 3; k >= 0; k--) {
/*     */           
/* 160 */           this.b[k] = (byte)(int)(t & 0xFFL);
/* 161 */           t >>>= 8L;
/*     */         } 
/*     */       } 
/*     */     } 
/* 165 */     return this.b[this.index++] & 0xFF;
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
/*     */   public int read(byte[] data, int offset, int len) throws IOException {
/* 182 */     if (this.eof && this.index >= this.n)
/*     */     {
/* 184 */       return -1;
/*     */     }
/* 186 */     for (int i = 0; i < len; i++) {
/*     */       
/* 188 */       if (this.index < this.n) {
/*     */         
/* 190 */         data[i + offset] = this.b[this.index++];
/*     */       }
/*     */       else {
/*     */         
/* 194 */         int t = read();
/* 195 */         if (t == -1)
/*     */         {
/* 197 */           return i;
/*     */         }
/* 199 */         data[i + offset] = (byte)t;
/*     */       } 
/*     */     } 
/* 202 */     return len;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 213 */     this.ascii = null;
/* 214 */     this.eof = true;
/* 215 */     this.b = null;
/* 216 */     super.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean markSupported() {
/* 227 */     return false;
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
/*     */   public long skip(long nValue) {
/* 240 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int available() {
/* 251 */     return 0;
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
/*     */   public void mark(int readlimit) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() throws IOException {
/* 272 */     throw new IOException("Reset is not supported");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/ASCII85InputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */