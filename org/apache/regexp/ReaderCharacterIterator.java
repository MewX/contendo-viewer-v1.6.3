/*     */ package org.apache.regexp;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ReaderCharacterIterator
/*     */   implements CharacterIterator
/*     */ {
/*     */   private final Reader reader;
/*     */   private final StringBuffer buff;
/*     */   private boolean closed;
/*     */   
/*     */   public ReaderCharacterIterator(Reader paramReader) {
/*  81 */     this.reader = paramReader;
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
/*     */   public char charAt(int paramInt) {
/*     */     try {
/* 119 */       ensure(paramInt);
/* 120 */       return this.buff.charAt(paramInt);
/*     */     }
/* 122 */     catch (IOException iOException) {
/*     */       
/* 124 */       throw new StringIndexOutOfBoundsException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnd(int paramInt) {
/* 131 */     if (this.buff.length() > paramInt)
/*     */     {
/* 133 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 139 */       ensure(paramInt);
/* 140 */       return !(this.buff.length() > paramInt);
/*     */     }
/* 142 */     catch (IOException iOException) {
/*     */       
/* 144 */       throw new StringIndexOutOfBoundsException(iOException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int read(int paramInt) throws IOException {
/* 152 */     if (this.closed)
/*     */     {
/* 154 */       return 0;
/*     */     }
/*     */     
/* 157 */     char[] arrayOfChar = new char[paramInt];
/* 158 */     int i = 0;
/* 159 */     int j = 0;
/*     */ 
/*     */     
/*     */     do {
/* 163 */       j = this.reader.read(arrayOfChar);
/* 164 */       if (j < 0) {
/*     */         
/* 166 */         this.closed = true;
/*     */         break;
/*     */       } 
/* 169 */       i += j;
/* 170 */       this.buff.append(arrayOfChar, 0, j);
/*     */     }
/* 172 */     while (i < paramInt);
/*     */     
/* 174 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void readAll() throws IOException {
/* 180 */     while (!this.closed)
/*     */     {
/* 182 */       read(1000);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensure(int paramInt) throws IOException {
/* 189 */     if (this.closed) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 194 */     if (paramInt < this.buff.length()) {
/*     */       return;
/*     */     }
/*     */     
/* 198 */     read(paramInt + 1 - this.buff.length());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/regexp/ReaderCharacterIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */