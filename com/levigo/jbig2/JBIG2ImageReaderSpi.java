/*     */ package com.levigo.jbig2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Locale;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.spi.ImageReaderSpi;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JBIG2ImageReaderSpi
/*     */   extends ImageReaderSpi
/*     */ {
/*     */   private static final String VENDOR = "levigo solutions gmbh";
/*     */   private static final String VERSION = "1.4.1";
/*     */   private static final String READER_CLASS_NAME = "com.levigo.jbig2.JBIG2ImageReader";
/*  37 */   private static final String[] NAMES = new String[] { "jbig2", "JBIG2" };
/*     */ 
/*     */   
/*  40 */   private static final String[] SUFFIXES = new String[] { "jb2", "jbig2", "JB2", "JBIG2" };
/*     */ 
/*     */   
/*  43 */   private static final String[] MIME_TYPES = new String[] { "image/x-jbig2", "image/x-jb2" };
/*     */ 
/*     */   
/*  46 */   private static final Class<?>[] INPUT_TYPES = new Class[] { ImageInputStream.class };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   private static final int[] FILEHEADER_PREAMBLE = new int[] { 151, 74, 66, 50, 13, 10, 26, 10 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private static final String[] WRITER_SPI_NAMES = new String[0];
/*     */   
/*     */   static final boolean SUPPORTS_STANDARD_STREAM_METADATE_FORMAT = false;
/*     */   static final String NATIVE_STREAM_METADATA_FORMAT_NAME = "JBIG2 Stream Metadata";
/*     */   static final String NATIVE_STREAM_METADATA_FORMAT_CLASSNAME = "JBIG2Metadata";
/*  65 */   static final String[] EXTRA_STREAM_METADATA_FORMAT_NAME = null;
/*  66 */   static final String[] EXTRA_STREAM_METADATA_FORMAT_CLASSNAME = null;
/*     */   
/*     */   static final boolean SUPPORTS_STANDARD_IMAGE_METADATA_FORMAT = false;
/*     */   static final String NATIVE_IMAGE_METADATA_FORMAT_NAME = "JBIG2 File Metadata";
/*     */   static final String NATIVE_IMAGE_METADATA_FORMAT_CLASSNAME = "JBIG2Metadata";
/*  71 */   static final String[] EXTRA_IMAGE_METADATA_FORMAT_NAME = null;
/*  72 */   static final String[] EXTRA_IMAGE_METADATA_FORMAT_CLASSNAME = null;
/*     */   
/*     */   public JBIG2ImageReaderSpi() {
/*  75 */     super("levigo solutions gmbh", "1.4.1", NAMES, SUFFIXES, MIME_TYPES, "com.levigo.jbig2.JBIG2ImageReader", INPUT_TYPES, WRITER_SPI_NAMES, false, "JBIG2 Stream Metadata", "JBIG2Metadata", EXTRA_STREAM_METADATA_FORMAT_NAME, EXTRA_STREAM_METADATA_FORMAT_CLASSNAME, false, "JBIG2 File Metadata", "JBIG2Metadata", EXTRA_IMAGE_METADATA_FORMAT_NAME, EXTRA_IMAGE_METADATA_FORMAT_CLASSNAME);
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
/*     */   public boolean canDecodeInput(Object paramObject) throws IOException {
/*  92 */     if (paramObject == null) {
/*  93 */       throw new IllegalArgumentException("source must not be null");
/*     */     }
/*  95 */     if (!(paramObject instanceof ImageInputStream)) {
/*  96 */       System.out.println("source is not an ImageInputStream");
/*  97 */       return false;
/*     */     } 
/*     */     
/* 100 */     ImageInputStream imageInputStream = (ImageInputStream)paramObject;
/* 101 */     imageInputStream.mark();
/*     */     
/* 103 */     for (byte b = 0; b < FILEHEADER_PREAMBLE.length; b++) {
/* 104 */       int i = imageInputStream.read() & 0xFF;
/* 105 */       if (i != FILEHEADER_PREAMBLE[b]) {
/* 106 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 110 */     imageInputStream.reset();
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageReader createReaderInstance(Object paramObject) throws IOException {
/* 116 */     return new JBIG2ImageReader(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription(Locale paramLocale) {
/* 121 */     return "JBIG2 Image Reader";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/JBIG2ImageReaderSpi.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */