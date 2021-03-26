/*     */ package org.apache.pdfbox.pdfwriter;
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
/*     */ public class COSStandardOutputStream
/*     */   extends FilterOutputStream
/*     */ {
/*  33 */   public static final byte[] CRLF = new byte[] { 13, 10 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public static final byte[] LF = new byte[] { 10 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public static final byte[] EOL = new byte[] { 10 };
/*     */ 
/*     */   
/*  46 */   private long position = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean onNewLine = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSStandardOutputStream(OutputStream out) {
/*  58 */     super(out);
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
/*     */   @Deprecated
/*     */   public COSStandardOutputStream(OutputStream out, int position) {
/*  71 */     super(out);
/*  72 */     this.position = position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSStandardOutputStream(OutputStream out, long position) {
/*  83 */     super(out);
/*  84 */     this.position = position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getPos() {
/*  94 */     return this.position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOnNewLine() {
/* 104 */     return this.onNewLine;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOnNewLine(boolean newOnNewLine) {
/* 113 */     this.onNewLine = newOnNewLine;
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
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 128 */     setOnNewLine(false);
/* 129 */     this.out.write(b, off, len);
/* 130 */     this.position += len;
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
/* 143 */     setOnNewLine(false);
/* 144 */     this.out.write(b);
/* 145 */     this.position++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeCRLF() throws IOException {
/* 155 */     write(CRLF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEOL() throws IOException {
/* 165 */     if (!isOnNewLine()) {
/*     */       
/* 167 */       write(EOL);
/* 168 */       setOnNewLine(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLF() throws IOException {
/* 179 */     write(LF);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfwriter/COSStandardOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */