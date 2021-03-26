/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.pdfbox.filter.DecodeOptions;
/*     */ import org.apache.pdfbox.filter.DecodeResult;
/*     */ import org.apache.pdfbox.filter.Filter;
/*     */ import org.apache.pdfbox.io.RandomAccess;
/*     */ import org.apache.pdfbox.io.RandomAccessInputStream;
/*     */ import org.apache.pdfbox.io.RandomAccessOutputStream;
/*     */ import org.apache.pdfbox.io.RandomAccessRead;
/*     */ import org.apache.pdfbox.io.RandomAccessWrite;
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
/*     */ public final class COSInputStream
/*     */   extends FilterInputStream
/*     */ {
/*     */   private final List<DecodeResult> decodeResults;
/*     */   
/*     */   static COSInputStream create(List<Filter> filters, COSDictionary parameters, InputStream in, ScratchFile scratchFile) throws IOException {
/*  58 */     return create(filters, parameters, in, scratchFile, DecodeOptions.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static COSInputStream create(List<Filter> filters, COSDictionary parameters, InputStream in, ScratchFile scratchFile, DecodeOptions options) throws IOException {
/*  64 */     List<DecodeResult> results = new ArrayList<DecodeResult>();
/*  65 */     InputStream inputStream = in;
/*  66 */     if (filters.isEmpty()) {
/*     */       
/*  68 */       inputStream = in;
/*     */     }
/*     */     else {
/*     */       
/*  72 */       Set<Filter> filterSet = new HashSet<Filter>(filters);
/*  73 */       if (filterSet.size() != filters.size())
/*     */       {
/*  75 */         throw new IOException("Duplicate");
/*     */       }
/*     */       
/*  78 */       for (int i = 0; i < filters.size(); i++) {
/*     */         RandomAccessInputStream randomAccessInputStream;
/*  80 */         if (scratchFile != null) {
/*     */ 
/*     */           
/*  83 */           final RandomAccess buffer = scratchFile.createBuffer();
/*  84 */           DecodeResult result = ((Filter)filters.get(i)).decode(inputStream, (OutputStream)new RandomAccessOutputStream((RandomAccessWrite)buffer), parameters, i, options);
/*  85 */           results.add(result);
/*  86 */           randomAccessInputStream = new RandomAccessInputStream((RandomAccessRead)buffer)
/*     */             {
/*     */               
/*     */               public void close() throws IOException
/*     */               {
/*  91 */                 buffer.close();
/*     */               }
/*     */             };
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  98 */           ByteArrayOutputStream output = new ByteArrayOutputStream();
/*  99 */           DecodeResult result = ((Filter)filters.get(i)).decode((InputStream)randomAccessInputStream, output, parameters, i, options);
/* 100 */           results.add(result);
/* 101 */           inputStream = new ByteArrayInputStream(output.toByteArray());
/*     */         } 
/*     */       } 
/*     */     } 
/* 105 */     return new COSInputStream(inputStream, results);
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
/*     */   private COSInputStream(InputStream input, List<DecodeResult> decodeResults) {
/* 118 */     super(input);
/* 119 */     this.decodeResults = decodeResults;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DecodeResult getDecodeResult() {
/* 129 */     if (this.decodeResults.isEmpty())
/*     */     {
/* 131 */       return DecodeResult.DEFAULT;
/*     */     }
/*     */ 
/*     */     
/* 135 */     return this.decodeResults.get(this.decodeResults.size() - 1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */