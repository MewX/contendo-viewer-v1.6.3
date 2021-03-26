/*     */ package org.apache.batik.apps.rasterizer;
/*     */ 
/*     */ import org.apache.batik.transcoder.Transcoder;
/*     */ import org.apache.batik.transcoder.image.JPEGTranscoder;
/*     */ import org.apache.batik.transcoder.image.PNGTranscoder;
/*     */ import org.apache.batik.transcoder.image.TIFFTranscoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DestinationType
/*     */ {
/*     */   public static final String PNG_STR = "image/png";
/*     */   public static final String JPEG_STR = "image/jpeg";
/*     */   public static final String TIFF_STR = "image/tiff";
/*     */   public static final String PDF_STR = "application/pdf";
/*     */   public static final int PNG_CODE = 0;
/*     */   public static final int JPEG_CODE = 1;
/*     */   public static final int TIFF_CODE = 2;
/*     */   public static final int PDF_CODE = 3;
/*     */   public static final String PNG_EXTENSION = ".png";
/*     */   public static final String JPEG_EXTENSION = ".jpg";
/*     */   public static final String TIFF_EXTENSION = ".tif";
/*     */   public static final String PDF_EXTENSION = ".pdf";
/*  50 */   public static final DestinationType PNG = new DestinationType("image/png", 0, ".png");
/*     */   
/*  52 */   public static final DestinationType JPEG = new DestinationType("image/jpeg", 1, ".jpg");
/*     */   
/*  54 */   public static final DestinationType TIFF = new DestinationType("image/tiff", 2, ".tif");
/*     */   
/*  56 */   public static final DestinationType PDF = new DestinationType("application/pdf", 3, ".pdf");
/*     */   
/*     */   private String type;
/*     */   
/*     */   private int code;
/*     */   private String extension;
/*     */   
/*     */   private DestinationType(String type, int code, String extension) {
/*  64 */     this.type = type;
/*  65 */     this.code = code;
/*  66 */     this.extension = extension;
/*     */   }
/*     */   
/*     */   public String getExtension() {
/*  70 */     return this.extension;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  74 */     return this.type;
/*     */   }
/*     */   
/*     */   public int toInt() {
/*  78 */     return this.code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Transcoder getTranscoder() {
/*  87 */     switch (this.code) {
/*     */       case 0:
/*  89 */         return (Transcoder)new PNGTranscoder();
/*     */       case 1:
/*  91 */         return (Transcoder)new JPEGTranscoder();
/*     */       case 2:
/*  93 */         return (Transcoder)new TIFFTranscoder();
/*     */       case 3:
/*     */         try {
/*  96 */           Class<?> pdfClass = Class.forName("org.apache.fop.svg.PDFTranscoder");
/*  97 */           return pdfClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/*  98 */         } catch (Exception e) {
/*  99 */           return null;
/*     */         } 
/*     */     } 
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DestinationType[] getValues() {
/* 113 */     return new DestinationType[] { PNG, JPEG, TIFF, PDF };
/*     */   }
/*     */   
/*     */   public Object readResolve() {
/* 117 */     switch (this.code) {
/*     */       case 0:
/* 119 */         return PNG;
/*     */       case 1:
/* 121 */         return JPEG;
/*     */       case 2:
/* 123 */         return TIFF;
/*     */       case 3:
/* 125 */         return PDF;
/*     */     } 
/* 127 */     throw new RuntimeException("unknown code:" + this.code);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/rasterizer/DestinationType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */