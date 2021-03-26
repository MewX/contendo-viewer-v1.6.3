/*     */ package org.apache.regexp;
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
/*     */ public final class StreamCharacterIterator
/*     */   implements CharacterIterator
/*     */ {
/*     */   private final InputStream is;
/*     */   private final StringBuffer buff;
/*     */   private boolean closed;
/*     */   
/*     */   public StreamCharacterIterator(InputStream paramInputStream) {
/*  81 */     this.is = paramInputStream;
/*  82 */     this.buff = new StringBuffer(512);
/*  83 */     this.closed = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String substring(int paramInt1, int paramInt2) {
/*     */     try {
/*  91 */       ensure(paramInt1 + paramInt2);
/*  92 */       return this.buff.toString().substring(paramInt1, paramInt2);
/*     */     }
/*  94 */     catch (IOException iOException) {
/*     */       
/*  96 */       throw new StringIndexOutOfBoundsException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String substring(int paramInt) {
/*     */     try {
/* 105 */       readAll();
/* 106 */       return this.buff.toString().substring(paramInt);
/*     */     }
/* 108 */     catch (IOException iOException) {
/*     */       
/* 110 */       throw new StringIndexOutOfBoundsException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char charAt(int paramInt) {
/*     */     try {
/* 120 */       ensure(paramInt);
/* 121 */       return this.buff.charAt(paramInt);
/*     */     }
/* 123 */     catch (IOException iOException) {
/*     */       
/* 125 */       throw new StringIndexOutOfBoundsException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnd(int paramInt) {
/* 132 */     if (this.buff.length() > paramInt)
/*     */     {
/* 134 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 140 */       ensure(paramInt);
/* 141 */       return !(this.buff.length() > paramInt);
/*     */     }
/* 143 */     catch (IOException iOException) {
/*     */       
/* 145 */       throw new StringIndexOutOfBoundsException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int read(int paramInt) throws IOException {
/* 153 */     if (this.closed)
/*     */     {
/* 155 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 159 */     int i = paramInt;
/* 160 */     while (--i >= 0) {
/*     */       
/* 162 */       int j = this.is.read();
/* 163 */       if (j < 0) {
/*     */         
/* 165 */         this.closed = true;
/*     */         break;
/*     */       } 
/* 168 */       this.buff.append((char)j);
/*     */     } 
/* 170 */     return paramInt - i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void readAll() throws IOException {
/* 176 */     while (!this.closed)
/*     */     {
/* 178 */       read(1000);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensure(int paramInt) throws IOException {
/* 185 */     if (this.closed) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 190 */     if (paramInt < this.buff.length()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 195 */     read(paramInt + 1 - this.buff.length());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/regexp/StreamCharacterIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */