/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FilterFactory
/*     */ {
/*  35 */   public static final FilterFactory INSTANCE = new FilterFactory();
/*     */   
/*  37 */   private final Map<COSName, Filter> filters = new HashMap<COSName, Filter>();
/*     */ 
/*     */   
/*     */   private FilterFactory() {
/*  41 */     Filter flate = new FlateFilter();
/*  42 */     Filter dct = new DCTFilter();
/*  43 */     Filter ccittFax = new CCITTFaxFilter();
/*  44 */     Filter lzw = new LZWFilter();
/*  45 */     Filter asciiHex = new ASCIIHexFilter();
/*  46 */     Filter ascii85 = new ASCII85Filter();
/*  47 */     Filter runLength = new RunLengthDecodeFilter();
/*  48 */     Filter crypt = new CryptFilter();
/*  49 */     Filter jpx = new JPXFilter();
/*  50 */     Filter jbig2 = new JBIG2Filter();
/*     */     
/*  52 */     this.filters.put(COSName.FLATE_DECODE, flate);
/*  53 */     this.filters.put(COSName.FLATE_DECODE_ABBREVIATION, flate);
/*  54 */     this.filters.put(COSName.DCT_DECODE, dct);
/*  55 */     this.filters.put(COSName.DCT_DECODE_ABBREVIATION, dct);
/*  56 */     this.filters.put(COSName.CCITTFAX_DECODE, ccittFax);
/*  57 */     this.filters.put(COSName.CCITTFAX_DECODE_ABBREVIATION, ccittFax);
/*  58 */     this.filters.put(COSName.LZW_DECODE, lzw);
/*  59 */     this.filters.put(COSName.LZW_DECODE_ABBREVIATION, lzw);
/*  60 */     this.filters.put(COSName.ASCII_HEX_DECODE, asciiHex);
/*  61 */     this.filters.put(COSName.ASCII_HEX_DECODE_ABBREVIATION, asciiHex);
/*  62 */     this.filters.put(COSName.ASCII85_DECODE, ascii85);
/*  63 */     this.filters.put(COSName.ASCII85_DECODE_ABBREVIATION, ascii85);
/*  64 */     this.filters.put(COSName.RUN_LENGTH_DECODE, runLength);
/*  65 */     this.filters.put(COSName.RUN_LENGTH_DECODE_ABBREVIATION, runLength);
/*  66 */     this.filters.put(COSName.CRYPT, crypt);
/*  67 */     this.filters.put(COSName.JPX_DECODE, jpx);
/*  68 */     this.filters.put(COSName.JBIG2_DECODE, jbig2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getFilter(String filterName) throws IOException {
/*  79 */     return getFilter(COSName.getPDFName(filterName));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getFilter(COSName filterName) throws IOException {
/*  90 */     Filter filter = this.filters.get(filterName);
/*  91 */     if (filter == null)
/*     */     {
/*  93 */       throw new IOException("Invalid filter: " + filterName);
/*     */     }
/*  95 */     return filter;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   Collection<Filter> getAllFilters() {
/* 101 */     return this.filters.values();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/FilterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */