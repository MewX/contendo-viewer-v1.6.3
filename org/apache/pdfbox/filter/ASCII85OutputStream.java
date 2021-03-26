/*     */ package org.apache.pdfbox.filter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class ASCII85OutputStream
/*     */   extends FilterOutputStream
/*     */ {
/*     */   private int lineBreak;
/*     */   private int count;
/*     */   private byte[] indata;
/*     */   private byte[] outdata;
/*     */   private int maxline;
/*     */   private boolean flushed;
/*     */   private char terminator;
/*     */   private static final char OFFSET = '!';
/*     */   private static final char NEWLINE = '\n';
/*     */   private static final char Z = 'z';
/*     */   
/*     */   ASCII85OutputStream(OutputStream out) {
/*  56 */     super(out);
/*  57 */     this.lineBreak = 72;
/*  58 */     this.maxline = 72;
/*  59 */     this.count = 0;
/*  60 */     this.indata = new byte[4];
/*  61 */     this.outdata = new byte[5];
/*  62 */     this.flushed = true;
/*  63 */     this.terminator = '~';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTerminator(char term) {
/*  73 */     if (term < 'v' || term > '~' || term == 'z')
/*     */     {
/*  75 */       throw new IllegalArgumentException("Terminator must be 118-126 excluding z");
/*     */     }
/*  77 */     this.terminator = term;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getTerminator() {
/*  87 */     return this.terminator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineLength(int l) {
/*  97 */     if (this.lineBreak > l)
/*     */     {
/*  99 */       this.lineBreak = l;
/*     */     }
/* 101 */     this.maxline = l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineLength() {
/* 111 */     return this.maxline;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void transformASCII85() {
/* 119 */     long word = ((this.indata[0] << 8 | this.indata[1] & 0xFF) << 16 | (this.indata[2] & 0xFF) << 8 | this.indata[3] & 0xFF) & 0xFFFFFFFFL;
/*     */     
/* 121 */     if (word == 0L) {
/*     */       
/* 123 */       this.outdata[0] = 122;
/* 124 */       this.outdata[1] = 0;
/*     */       
/*     */       return;
/*     */     } 
/* 128 */     long x = word / 52200625L;
/* 129 */     this.outdata[0] = (byte)(int)(x + 33L);
/* 130 */     word -= x * 85L * 85L * 85L * 85L;
/*     */     
/* 132 */     x = word / 614125L;
/* 133 */     this.outdata[1] = (byte)(int)(x + 33L);
/* 134 */     word -= x * 85L * 85L * 85L;
/*     */     
/* 136 */     x = word / 7225L;
/* 137 */     this.outdata[2] = (byte)(int)(x + 33L);
/* 138 */     word -= x * 85L * 85L;
/*     */     
/* 140 */     x = word / 85L;
/* 141 */     this.outdata[3] = (byte)(int)(x + 33L);
/*     */     
/* 143 */     this.outdata[4] = (byte)(int)(word % 85L + 33L);
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
/*     */   public void write(int b) throws IOException {
/* 156 */     this.flushed = false;
/* 157 */     this.indata[this.count++] = (byte)b;
/* 158 */     if (this.count < 4) {
/*     */       return;
/*     */     }
/*     */     
/* 162 */     transformASCII85();
/* 163 */     for (int i = 0; i < 5; i++) {
/*     */       
/* 165 */       if (this.outdata[i] == 0) {
/*     */         break;
/*     */       }
/*     */       
/* 169 */       this.out.write(this.outdata[i]);
/* 170 */       if (--this.lineBreak == 0) {
/*     */         
/* 172 */         this.out.write(10);
/* 173 */         this.lineBreak = this.maxline;
/*     */       } 
/*     */     } 
/* 176 */     this.count = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/* 187 */     if (this.flushed) {
/*     */       return;
/*     */     }
/*     */     
/* 191 */     if (this.count > 0) {
/*     */       int i;
/* 193 */       for (i = this.count; i < 4; i++)
/*     */       {
/* 195 */         this.indata[i] = 0;
/*     */       }
/* 197 */       transformASCII85();
/* 198 */       if (this.outdata[0] == 122)
/*     */       {
/* 200 */         for (i = 0; i < 5; i++)
/*     */         {
/* 202 */           this.outdata[i] = 33;
/*     */         }
/*     */       }
/* 205 */       for (i = 0; i < this.count + 1; i++) {
/*     */         
/* 207 */         this.out.write(this.outdata[i]);
/* 208 */         if (--this.lineBreak == 0) {
/*     */           
/* 210 */           this.out.write(10);
/* 211 */           this.lineBreak = this.maxline;
/*     */         } 
/*     */       } 
/*     */     } 
/* 215 */     if (--this.lineBreak == 0)
/*     */     {
/* 217 */       this.out.write(10);
/*     */     }
/* 219 */     this.out.write(this.terminator);
/* 220 */     this.out.write(62);
/* 221 */     this.out.write(10);
/* 222 */     this.count = 0;
/* 223 */     this.lineBreak = this.maxline;
/* 224 */     this.flushed = true;
/* 225 */     super.flush();
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
/*     */   public void close() throws IOException {
/*     */     try {
/* 238 */       flush();
/* 239 */       super.close();
/*     */     }
/*     */     finally {
/*     */       
/* 243 */       this.indata = this.outdata = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/ASCII85OutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */