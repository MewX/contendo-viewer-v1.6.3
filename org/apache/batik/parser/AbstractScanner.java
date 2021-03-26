/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import org.apache.batik.util.io.NormalizingReader;
/*     */ import org.apache.batik.util.io.StreamNormalizingReader;
/*     */ import org.apache.batik.util.io.StringNormalizingReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractScanner
/*     */ {
/*     */   protected NormalizingReader reader;
/*     */   protected int current;
/*  51 */   protected char[] buffer = new char[128];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int position;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int type;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int previousType;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int start;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int end;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int blankCharacters;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractScanner(Reader r) throws ParseException {
/*     */     try {
/*  90 */       this.reader = (NormalizingReader)new StreamNormalizingReader(r);
/*  91 */       this.current = nextChar();
/*  92 */     } catch (IOException e) {
/*  93 */       throw new ParseException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractScanner(InputStream is, String enc) throws ParseException {
/*     */     try {
/* 104 */       this.reader = (NormalizingReader)new StreamNormalizingReader(is, enc);
/* 105 */       this.current = nextChar();
/* 106 */     } catch (IOException e) {
/* 107 */       throw new ParseException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractScanner(String s) throws ParseException {
/*     */     try {
/* 117 */       this.reader = (NormalizingReader)new StringNormalizingReader(s);
/* 118 */       this.current = nextChar();
/* 119 */     } catch (IOException e) {
/* 120 */       throw new ParseException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLine() {
/* 128 */     return this.reader.getLine();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumn() {
/* 135 */     return this.reader.getColumn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char[] getBuffer() {
/* 142 */     return this.buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStart() {
/* 149 */     return this.start;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEnd() {
/* 156 */     return this.end;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearBuffer() {
/* 163 */     if (this.position <= 0) {
/* 164 */       this.position = 0;
/*     */     } else {
/* 166 */       this.buffer[0] = this.buffer[this.position - 1];
/* 167 */       this.position = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 175 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 182 */     return new String(this.buffer, this.start, this.end - this.start);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int next() throws ParseException {
/* 189 */     this.blankCharacters = 0;
/* 190 */     this.start = this.position - 1;
/* 191 */     this.previousType = this.type;
/* 192 */     nextToken();
/* 193 */     this.end = this.position - endGap();
/* 194 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract int endGap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void nextToken() throws ParseException;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean isEqualIgnoreCase(int i, char c) {
/* 211 */     return (i == -1) ? false : ((Character.toLowerCase((char)i) == c));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int nextChar() throws IOException {
/* 219 */     this.current = this.reader.read();
/*     */     
/* 221 */     if (this.current == -1) {
/* 222 */       return this.current;
/*     */     }
/*     */     
/* 225 */     if (this.position == this.buffer.length) {
/* 226 */       char[] t = new char[1 + this.position + this.position / 2];
/* 227 */       System.arraycopy(this.buffer, 0, t, 0, this.position);
/* 228 */       this.buffer = t;
/*     */     } 
/*     */     
/* 231 */     this.buffer[this.position++] = (char)this.current; return (char)this.current;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/AbstractScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */