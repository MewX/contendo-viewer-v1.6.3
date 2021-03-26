/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.pdfbox.cos.COSDocument;
/*     */ import org.apache.pdfbox.io.RandomAccessBuffer;
/*     */ import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
/*     */ import org.apache.pdfbox.io.RandomAccessRead;
/*     */ import org.apache.pdfbox.pdfparser.PDFParser;
/*     */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSigProperties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SignatureOptions
/*     */   implements Closeable
/*     */ {
/*     */   private COSDocument visualSignature;
/*     */   private int preferredSignatureSize;
/*     */   private int pageNo;
/*  42 */   private RandomAccessRead pdfSource = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int DEFAULT_SIGNATURE_SIZE = 9472;
/*     */ 
/*     */ 
/*     */   
/*     */   public SignatureOptions() {
/*  51 */     this.pageNo = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPage(int pageNo) {
/*  61 */     this.pageNo = pageNo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPage() {
/*  71 */     return this.pageNo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisualSignature(File file) throws IOException {
/*  82 */     initFromRandomAccessRead((RandomAccessRead)new RandomAccessBufferedFileInputStream(file));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisualSignature(InputStream is) throws IOException {
/*  93 */     initFromRandomAccessRead((RandomAccessRead)new RandomAccessBuffer(is));
/*     */   }
/*     */ 
/*     */   
/*     */   private void initFromRandomAccessRead(RandomAccessRead rar) throws IOException {
/*  98 */     this.pdfSource = rar;
/*  99 */     PDFParser parser = new PDFParser(this.pdfSource);
/* 100 */     parser.parse();
/* 101 */     this.visualSignature = parser.getDocument();
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
/*     */   public void setVisualSignature(PDVisibleSigProperties visSignatureProperties) throws IOException {
/* 114 */     setVisualSignature(visSignatureProperties.getVisibleSignature());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDocument getVisualSignature() {
/* 124 */     return this.visualSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPreferredSignatureSize() {
/* 134 */     return this.preferredSignatureSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPreferredSignatureSize(int size) {
/* 144 */     if (size > 0)
/*     */     {
/* 146 */       this.preferredSignatureSize = size;
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
/*     */   public void close() throws IOException {
/* 160 */     if (this.visualSignature != null)
/*     */     {
/* 162 */       this.visualSignature.close();
/*     */     }
/* 164 */     if (this.pdfSource != null)
/*     */     {
/* 166 */       this.pdfSource.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/SignatureOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */