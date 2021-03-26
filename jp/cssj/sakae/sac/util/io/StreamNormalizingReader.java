/*     */ package jp.cssj.sakae.sac.util.io;
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
/*     */ public class StreamNormalizingReader
/*     */   extends NormalizingReader
/*     */ {
/*     */   protected final Reader in;
/*  73 */   protected int nextChar = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   protected int line = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int column;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StreamNormalizingReader(Reader in) throws IOException {
/*  92 */     this.in = in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 100 */     int c, result = this.nextChar;
/* 101 */     if (result != -1) {
/* 102 */       this.nextChar = -1;
/* 103 */       if (result == 13) {
/* 104 */         this.column = 0;
/* 105 */         this.line++;
/*     */       } else {
/* 107 */         this.column++;
/*     */       } 
/* 109 */       return result;
/*     */     } 
/* 111 */     result = this.in.read();
/* 112 */     switch (result) {
/*     */       case 13:
/* 114 */         this.column = 0;
/* 115 */         this.line++;
/* 116 */         c = this.in.read();
/* 117 */         if (c == 10) {
/* 118 */           return 10;
/*     */         }
/* 120 */         this.nextChar = c;
/* 121 */         return 10;
/*     */       
/*     */       case 10:
/* 124 */         this.column = 0;
/* 125 */         this.line++; break;
/*     */     } 
/* 127 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLine() {
/* 134 */     return this.line;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumn() {
/* 141 */     return this.column;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 148 */     this.in.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/util/io/StreamNormalizingReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */