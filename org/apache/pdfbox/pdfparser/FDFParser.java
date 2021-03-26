/*     */ package org.apache.pdfbox.pdfparser;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSDocument;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.io.RandomAccessBuffer;
/*     */ import org.apache.pdfbox.io.RandomAccessFile;
/*     */ import org.apache.pdfbox.io.RandomAccessRead;
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
/*     */ public class FDFParser
/*     */   extends COSParser
/*     */ {
/*  35 */   private static final Log LOG = LogFactory.getLog(FDFParser.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFParser(String filename) throws IOException {
/*  46 */     this(new File(filename));
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
/*     */   public FDFParser(File file) throws IOException {
/*  59 */     super((RandomAccessRead)new RandomAccessFile(file, "r"));
/*  60 */     this.fileLen = file.length();
/*  61 */     init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFParser(InputStream input) throws IOException {
/*  72 */     super((RandomAccessRead)new RandomAccessBuffer(input));
/*  73 */     this.fileLen = this.source.length();
/*  74 */     init();
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
/*     */   protected final boolean isCatalog(COSDictionary dictionary) {
/*  86 */     return dictionary.containsKey(COSName.FDF);
/*     */   }
/*     */ 
/*     */   
/*     */   private void init() throws IOException {
/*  91 */     String eofLookupRangeStr = System.getProperty("org.apache.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange");
/*  92 */     if (eofLookupRangeStr != null) {
/*     */       
/*     */       try {
/*     */         
/*  96 */         setEOFLookupRange(Integer.parseInt(eofLookupRangeStr));
/*     */       }
/*  98 */       catch (NumberFormatException nfe) {
/*     */         
/* 100 */         LOG.warn("System property org.apache.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange does not contain an integer value, but: '" + eofLookupRangeStr + "'");
/*     */       } 
/*     */     }
/*     */     
/* 104 */     this.document = new COSDocument();
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
/*     */   private void initialParse() throws IOException {
/* 116 */     COSDictionary trailer = null;
/* 117 */     boolean rebuildTrailer = false;
/*     */ 
/*     */     
/*     */     try {
/* 121 */       long startXRefOffset = getStartxrefOffset();
/* 122 */       if (startXRefOffset > 0L)
/*     */       {
/* 124 */         trailer = parseXref(startXRefOffset);
/*     */       }
/* 126 */       else if (isLenient())
/*     */       {
/* 128 */         rebuildTrailer = true;
/*     */       }
/*     */     
/* 131 */     } catch (IOException exception) {
/*     */       
/* 133 */       if (isLenient()) {
/*     */         
/* 135 */         rebuildTrailer = true;
/*     */       }
/*     */       else {
/*     */         
/* 139 */         throw exception;
/*     */       } 
/*     */     } 
/* 142 */     if (rebuildTrailer)
/*     */     {
/* 144 */       trailer = rebuildTrailer();
/*     */     }
/*     */     
/* 147 */     COSBase rootObject = parseTrailerValuesDynamically(trailer);
/*     */ 
/*     */ 
/*     */     
/* 151 */     if (rootObject instanceof COSDictionary)
/*     */     {
/* 153 */       parseDictObjects((COSDictionary)rootObject, (COSName[])null);
/*     */     }
/* 155 */     this.initialParseDone = true;
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
/*     */   public void parse() throws IOException {
/* 167 */     boolean exceptionOccurred = true;
/*     */     
/*     */     try {
/* 170 */       if (!parseFDFHeader())
/*     */       {
/* 172 */         throw new IOException("Error: Header doesn't contain versioninfo");
/*     */       }
/* 174 */       initialParse();
/* 175 */       exceptionOccurred = false;
/*     */     }
/*     */     finally {
/*     */       
/* 179 */       if (exceptionOccurred && this.document != null) {
/*     */         
/* 181 */         IOUtils.closeQuietly((Closeable)this.document);
/* 182 */         this.document = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/FDFParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */