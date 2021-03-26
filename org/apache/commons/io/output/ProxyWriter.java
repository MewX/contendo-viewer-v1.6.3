/*     */ package org.apache.commons.io.output;
/*     */ 
/*     */ import java.io.FilterWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProxyWriter
/*     */   extends FilterWriter
/*     */ {
/*     */   public ProxyWriter(Writer proxy) {
/*  40 */     super(proxy);
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
/*     */   public Writer append(char c) throws IOException {
/*     */     try {
/*  54 */       beforeWrite(1);
/*  55 */       this.out.append(c);
/*  56 */       afterWrite(1);
/*  57 */     } catch (IOException e) {
/*  58 */       handleIOException(e);
/*     */     } 
/*  60 */     return this;
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
/*     */   public Writer append(CharSequence csq, int start, int end) throws IOException {
/*     */     try {
/*  75 */       beforeWrite(end - start);
/*  76 */       this.out.append(csq, start, end);
/*  77 */       afterWrite(end - start);
/*  78 */     } catch (IOException e) {
/*  79 */       handleIOException(e);
/*     */     } 
/*  81 */     return this;
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
/*     */   public Writer append(CharSequence csq) throws IOException {
/*     */     try {
/*  94 */       int len = 0;
/*  95 */       if (csq != null) {
/*  96 */         len = csq.length();
/*     */       }
/*     */       
/*  99 */       beforeWrite(len);
/* 100 */       this.out.append(csq);
/* 101 */       afterWrite(len);
/* 102 */     } catch (IOException e) {
/* 103 */       handleIOException(e);
/*     */     } 
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int idx) throws IOException {
/*     */     try {
/* 116 */       beforeWrite(1);
/* 117 */       this.out.write(idx);
/* 118 */       afterWrite(1);
/* 119 */     } catch (IOException e) {
/* 120 */       handleIOException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(char[] chr) throws IOException {
/*     */     try {
/* 132 */       int len = 0;
/* 133 */       if (chr != null) {
/* 134 */         len = chr.length;
/*     */       }
/*     */       
/* 137 */       beforeWrite(len);
/* 138 */       this.out.write(chr);
/* 139 */       afterWrite(len);
/* 140 */     } catch (IOException e) {
/* 141 */       handleIOException(e);
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
/*     */   public void write(char[] chr, int st, int len) throws IOException {
/*     */     try {
/* 155 */       beforeWrite(len);
/* 156 */       this.out.write(chr, st, len);
/* 157 */       afterWrite(len);
/* 158 */     } catch (IOException e) {
/* 159 */       handleIOException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(String str) throws IOException {
/*     */     try {
/* 171 */       int len = 0;
/* 172 */       if (str != null) {
/* 173 */         len = str.length();
/*     */       }
/*     */       
/* 176 */       beforeWrite(len);
/* 177 */       this.out.write(str);
/* 178 */       afterWrite(len);
/* 179 */     } catch (IOException e) {
/* 180 */       handleIOException(e);
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
/*     */   public void write(String str, int st, int len) throws IOException {
/*     */     try {
/* 194 */       beforeWrite(len);
/* 195 */       this.out.write(str, st, len);
/* 196 */       afterWrite(len);
/* 197 */     } catch (IOException e) {
/* 198 */       handleIOException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/*     */     try {
/* 209 */       this.out.flush();
/* 210 */     } catch (IOException e) {
/* 211 */       handleIOException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*     */     try {
/* 222 */       this.out.close();
/* 223 */     } catch (IOException e) {
/* 224 */       handleIOException(e);
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
/*     */   protected void beforeWrite(int n) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void afterWrite(int n) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleIOException(IOException e) throws IOException {
/* 271 */     throw e;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/output/ProxyWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */