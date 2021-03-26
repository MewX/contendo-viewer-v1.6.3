/*     */ package org.apache.pdfbox.pdfparser;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSDocument;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.io.RandomAccessRead;
/*     */ import org.apache.pdfbox.io.ScratchFile;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
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
/*     */ public class PDFParser
/*     */   extends COSParser
/*     */ {
/*  38 */   private static final Log LOG = LogFactory.getLog(PDFParser.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFParser(RandomAccessRead source) throws IOException {
/*  49 */     this(source, "", ScratchFile.getMainMemoryOnlyInstance());
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
/*     */   public PDFParser(RandomAccessRead source, ScratchFile scratchFile) throws IOException {
/*  62 */     this(source, "", scratchFile);
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
/*     */   public PDFParser(RandomAccessRead source, String decryptionPassword) throws IOException {
/*  75 */     this(source, decryptionPassword, ScratchFile.getMainMemoryOnlyInstance());
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
/*     */   public PDFParser(RandomAccessRead source, String decryptionPassword, ScratchFile scratchFile) throws IOException {
/*  90 */     this(source, decryptionPassword, (InputStream)null, (String)null, scratchFile);
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
/*     */   public PDFParser(RandomAccessRead source, String decryptionPassword, InputStream keyStore, String alias) throws IOException {
/* 107 */     this(source, decryptionPassword, keyStore, alias, ScratchFile.getMainMemoryOnlyInstance());
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
/*     */   
/*     */   public PDFParser(RandomAccessRead source, String decryptionPassword, InputStream keyStore, String alias, ScratchFile scratchFile) throws IOException {
/* 125 */     super(source, decryptionPassword, keyStore, alias);
/* 126 */     this.fileLen = source.length();
/* 127 */     init(scratchFile);
/*     */   }
/*     */ 
/*     */   
/*     */   private void init(ScratchFile scratchFile) throws IOException {
/* 132 */     String eofLookupRangeStr = System.getProperty("org.apache.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange");
/* 133 */     if (eofLookupRangeStr != null) {
/*     */       
/*     */       try {
/*     */         
/* 137 */         setEOFLookupRange(Integer.parseInt(eofLookupRangeStr));
/*     */       }
/* 139 */       catch (NumberFormatException nfe) {
/*     */         
/* 141 */         LOG.warn("System property org.apache.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange does not contain an integer value, but: '" + eofLookupRangeStr + "'");
/*     */       } 
/*     */     }
/*     */     
/* 145 */     this.document = new COSDocument(scratchFile);
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
/*     */   public PDDocument getPDDocument() throws IOException {
/* 158 */     PDDocument doc = new PDDocument(getDocument(), this.source, getAccessPermission());
/* 159 */     doc.setEncryptionDictionary(getEncryption());
/* 160 */     return doc;
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
/*     */   protected void initialParse() throws InvalidPasswordException, IOException {
/* 173 */     COSDictionary trailer = retrieveTrailer();
/*     */     
/* 175 */     COSBase base = parseTrailerValuesDynamically(trailer);
/* 176 */     if (!(base instanceof COSDictionary))
/*     */     {
/* 178 */       throw new IOException("Expected root dictionary, but got this: " + base);
/*     */     }
/* 180 */     COSDictionary root = (COSDictionary)base;
/*     */     
/* 182 */     if (isLenient() && !root.containsKey(COSName.TYPE))
/*     */     {
/* 184 */       root.setItem(COSName.TYPE, (COSBase)COSName.CATALOG);
/*     */     }
/*     */     
/* 187 */     parseDictObjects(root, (COSName[])null);
/*     */     
/* 189 */     COSBase infoBase = trailer.getDictionaryObject(COSName.INFO);
/* 190 */     if (infoBase instanceof COSDictionary)
/*     */     {
/* 192 */       parseDictObjects((COSDictionary)infoBase, (COSName[])null);
/*     */     }
/*     */     
/* 195 */     checkPages(root);
/* 196 */     if (!(root.getDictionaryObject(COSName.PAGES) instanceof COSDictionary))
/*     */     {
/* 198 */       throw new IOException("Page tree root must be a dictionary");
/*     */     }
/* 200 */     this.document.setDecrypted();
/* 201 */     this.initialParseDone = true;
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
/*     */   public void parse() throws InvalidPasswordException, IOException {
/* 215 */     boolean exceptionOccurred = true;
/*     */ 
/*     */     
/*     */     try {
/* 219 */       if (!parsePDFHeader() && !parseFDFHeader())
/*     */       {
/* 221 */         throw new IOException("Error: Header doesn't contain versioninfo");
/*     */       }
/*     */       
/* 224 */       if (!this.initialParseDone)
/*     */       {
/* 226 */         initialParse();
/*     */       }
/* 228 */       exceptionOccurred = false;
/*     */     }
/*     */     finally {
/*     */       
/* 232 */       if (exceptionOccurred && this.document != null) {
/*     */         
/* 234 */         IOUtils.closeQuietly((Closeable)this.document);
/* 235 */         this.document = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/PDFParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */