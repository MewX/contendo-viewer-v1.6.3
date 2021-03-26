/*     */ package org.apache.commons.io;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LineIterator
/*     */   implements Closeable, Iterator<String>
/*     */ {
/*     */   private final BufferedReader bufferedReader;
/*     */   private String cachedLine;
/*     */   private boolean finished = false;
/*     */   
/*     */   public LineIterator(Reader reader) throws IllegalArgumentException {
/*  68 */     if (reader == null) {
/*  69 */       throw new IllegalArgumentException("Reader must not be null");
/*     */     }
/*  71 */     if (reader instanceof BufferedReader) {
/*  72 */       this.bufferedReader = (BufferedReader)reader;
/*     */     } else {
/*  74 */       this.bufferedReader = new BufferedReader(reader);
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
/*     */   public boolean hasNext() {
/*  89 */     if (this.cachedLine != null)
/*  90 */       return true; 
/*  91 */     if (this.finished) {
/*  92 */       return false;
/*     */     }
/*     */     try {
/*     */       while (true) {
/*  96 */         String line = this.bufferedReader.readLine();
/*  97 */         if (line == null) {
/*  98 */           this.finished = true;
/*  99 */           return false;
/* 100 */         }  if (isValidLine(line)) {
/* 101 */           this.cachedLine = line;
/* 102 */           return true;
/*     */         } 
/*     */       } 
/* 105 */     } catch (IOException ioe) {
/*     */       try {
/* 107 */         close();
/* 108 */       } catch (IOException e) {
/* 109 */         ioe.addSuppressed(e);
/*     */       } 
/* 111 */       throw new IllegalStateException(ioe);
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
/*     */   protected boolean isValidLine(String line) {
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String next() {
/* 134 */     return nextLine();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextLine() {
/* 144 */     if (!hasNext()) {
/* 145 */       throw new NoSuchElementException("No more lines");
/*     */     }
/* 147 */     String currentLine = this.cachedLine;
/* 148 */     this.cachedLine = null;
/* 149 */     return currentLine;
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
/*     */   public void close() throws IOException {
/* 163 */     this.finished = true;
/* 164 */     this.cachedLine = null;
/* 165 */     if (this.bufferedReader != null) {
/* 166 */       this.bufferedReader.close();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove() {
/* 177 */     throw new UnsupportedOperationException("Remove unsupported on LineIterator");
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
/*     */   @Deprecated
/*     */   public static void closeQuietly(LineIterator iterator) {
/*     */     try {
/* 192 */       if (iterator != null) {
/* 193 */         iterator.close();
/*     */       }
/* 195 */     } catch (IOException iOException) {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/LineIterator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */