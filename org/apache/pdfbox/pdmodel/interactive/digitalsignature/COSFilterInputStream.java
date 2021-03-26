/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class COSFilterInputStream
/*     */   extends FilterInputStream
/*     */ {
/*     */   private final int[] byteRange;
/*  32 */   private long position = 0L;
/*     */ 
/*     */   
/*     */   public COSFilterInputStream(InputStream in, int[] byteRange) {
/*  36 */     super(in);
/*  37 */     this.byteRange = byteRange;
/*     */   }
/*     */ 
/*     */   
/*     */   public COSFilterInputStream(byte[] in, int[] byteRange) {
/*  42 */     super(new ByteArrayInputStream(in));
/*  43 */     this.byteRange = byteRange;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  49 */     nextAvailable();
/*  50 */     int i = super.read();
/*  51 */     if (i > -1)
/*     */     {
/*  53 */       this.position++;
/*     */     }
/*  55 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/*  61 */     return read(b, 0, b.length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/*  67 */     if (len == 0)
/*     */     {
/*  69 */       return 0;
/*     */     }
/*     */     
/*  72 */     int c = read();
/*  73 */     if (c == -1)
/*     */     {
/*  75 */       return -1;
/*     */     }
/*  77 */     b[off] = (byte)c;
/*     */     
/*  79 */     int i = 1;
/*     */     
/*     */     try {
/*  82 */       for (; i < len; i++)
/*     */       {
/*  84 */         c = read();
/*  85 */         if (c == -1) {
/*     */           break;
/*     */         }
/*     */         
/*  89 */         b[off + i] = (byte)c;
/*     */       }
/*     */     
/*  92 */     } catch (IOException iOException) {}
/*     */ 
/*     */     
/*  95 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean inRange() throws IOException {
/* 100 */     long pos = this.position;
/* 101 */     for (int i = 0; i < this.byteRange.length / 2; i++) {
/*     */       
/* 103 */       if (this.byteRange[i * 2] <= pos && (this.byteRange[i * 2] + this.byteRange[i * 2 + 1]) > pos)
/*     */       {
/* 105 */         return true;
/*     */       }
/*     */     } 
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void nextAvailable() throws IOException {
/* 113 */     while (!inRange()) {
/*     */       
/* 115 */       this.position++;
/* 116 */       if (super.read() < 0) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] toByteArray() throws IOException {
/* 125 */     return IOUtils.toByteArray(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/COSFilterInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */