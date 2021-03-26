/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.filter.Filter;
/*     */ import org.apache.pdfbox.io.RandomAccess;
/*     */ import org.apache.pdfbox.io.RandomAccessInputStream;
/*     */ import org.apache.pdfbox.io.RandomAccessRead;
/*     */ import org.apache.pdfbox.io.ScratchFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class COSOutputStream
/*     */   extends FilterOutputStream
/*     */ {
/*     */   private final List<Filter> filters;
/*     */   private final COSDictionary parameters;
/*     */   private final ScratchFile scratchFile;
/*     */   private RandomAccess buffer;
/*     */   
/*     */   COSOutputStream(List<Filter> filters, COSDictionary parameters, OutputStream output, ScratchFile scratchFile) throws IOException {
/*  56 */     super(output);
/*  57 */     this.filters = filters;
/*  58 */     this.parameters = parameters;
/*  59 */     this.scratchFile = scratchFile;
/*     */     
/*  61 */     if (filters.isEmpty()) {
/*     */       
/*  63 */       this.buffer = null;
/*     */     }
/*     */     else {
/*     */       
/*  67 */       this.buffer = scratchFile.createBuffer();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/*  74 */     if (this.buffer != null) {
/*     */       
/*  76 */       this.buffer.write(b);
/*     */     }
/*     */     else {
/*     */       
/*  80 */       super.write(b);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/*  87 */     if (this.buffer != null) {
/*     */       
/*  89 */       this.buffer.write(b, off, len);
/*     */     }
/*     */     else {
/*     */       
/*  93 */       super.write(b, off, len);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 100 */     if (this.buffer != null) {
/*     */       
/* 102 */       this.buffer.write(b);
/*     */     }
/*     */     else {
/*     */       
/* 106 */       super.write(b);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*     */     try {
/* 119 */       if (this.buffer != null) {
/*     */         try
/*     */         {
/*     */ 
/*     */           
/* 124 */           for (int i = this.filters.size() - 1; i >= 0; i--)
/*     */           {
/* 126 */             RandomAccessInputStream randomAccessInputStream = new RandomAccessInputStream((RandomAccessRead)this.buffer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         finally
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 170 */           this.buffer.close();
/* 171 */           this.buffer = null;
/*     */         }
/*     */       
/*     */       }
/*     */     } finally {
/*     */       
/* 177 */       super.close();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */