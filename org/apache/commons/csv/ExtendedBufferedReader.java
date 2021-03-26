/*     */ package org.apache.commons.csv;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class ExtendedBufferedReader
/*     */   extends BufferedReader
/*     */ {
/*  39 */   private int lastChar = -2;
/*     */ 
/*     */   
/*     */   private long eolCounter;
/*     */ 
/*     */   
/*     */   private long position;
/*     */ 
/*     */   
/*     */   private boolean closed;
/*     */ 
/*     */ 
/*     */   
/*     */   ExtendedBufferedReader(Reader reader) {
/*  53 */     super(reader);
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  58 */     int current = super.read();
/*  59 */     if (current == 13 || (current == 10 && this.lastChar != 13)) {
/*  60 */       this.eolCounter++;
/*     */     }
/*  62 */     this.lastChar = current;
/*  63 */     this.position++;
/*  64 */     return this.lastChar;
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
/*     */   int getLastChar() {
/*  76 */     return this.lastChar;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(char[] buf, int offset, int length) throws IOException {
/*  81 */     if (length == 0) {
/*  82 */       return 0;
/*     */     }
/*     */     
/*  85 */     int len = super.read(buf, offset, length);
/*     */     
/*  87 */     if (len > 0) {
/*     */       
/*  89 */       for (int i = offset; i < offset + len; i++) {
/*  90 */         char ch = buf[i];
/*  91 */         if (ch == '\n') {
/*  92 */           if (13 != ((i > 0) ? buf[i - 1] : this.lastChar)) {
/*  93 */             this.eolCounter++;
/*     */           }
/*  95 */         } else if (ch == '\r') {
/*  96 */           this.eolCounter++;
/*     */         } 
/*     */       } 
/*     */       
/* 100 */       this.lastChar = buf[offset + len - 1];
/*     */     }
/* 102 */     else if (len == -1) {
/* 103 */       this.lastChar = -1;
/*     */     } 
/*     */     
/* 106 */     this.position += len;
/* 107 */     return len;
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
/*     */   public String readLine() throws IOException {
/* 122 */     String line = super.readLine();
/*     */     
/* 124 */     if (line != null) {
/* 125 */       this.lastChar = 10;
/* 126 */       this.eolCounter++;
/*     */     } else {
/* 128 */       this.lastChar = -1;
/*     */     } 
/*     */     
/* 131 */     return line;
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
/*     */   int lookAhead() throws IOException {
/* 144 */     mark(1);
/* 145 */     int c = super.read();
/* 146 */     reset();
/*     */     
/* 148 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long getCurrentLineNumber() {
/* 158 */     if (this.lastChar == 13 || this.lastChar == 10 || this.lastChar == -2 || this.lastChar == -1) {
/* 159 */       return this.eolCounter;
/*     */     }
/* 161 */     return this.eolCounter + 1L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long getPosition() {
/* 170 */     return this.position;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 174 */     return this.closed;
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
/* 186 */     this.closed = true;
/* 187 */     this.lastChar = -1;
/* 188 */     super.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/csv/ExtendedBufferedReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */