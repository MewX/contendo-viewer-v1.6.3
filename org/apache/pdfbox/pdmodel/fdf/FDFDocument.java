/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSDocument;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdfparser.FDFParser;
/*     */ import org.apache.pdfbox.pdfwriter.COSWriter;
/*     */ import org.apache.pdfbox.util.XMLUtil;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class FDFDocument
/*     */   implements Closeable
/*     */ {
/*     */   private COSDocument document;
/*     */   
/*     */   public FDFDocument() {
/*  54 */     this.document = new COSDocument();
/*  55 */     this.document.setVersion(1.2F);
/*     */ 
/*     */     
/*  58 */     this.document.setTrailer(new COSDictionary());
/*     */ 
/*     */     
/*  61 */     FDFCatalog catalog = new FDFCatalog();
/*  62 */     setCatalog(catalog);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFDocument(COSDocument doc) {
/*  72 */     this.document = doc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFDocument(Document doc) throws IOException {
/*  83 */     this();
/*  84 */     Element xfdf = doc.getDocumentElement();
/*  85 */     if (!xfdf.getNodeName().equals("xfdf"))
/*     */     {
/*  87 */       throw new IOException("Error while importing xfdf document, root should be 'xfdf' and not '" + xfdf
/*  88 */           .getNodeName() + "'");
/*     */     }
/*  90 */     FDFCatalog cat = new FDFCatalog(xfdf);
/*  91 */     setCatalog(cat);
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
/*     */   public void writeXML(Writer output) throws IOException {
/* 103 */     output.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
/* 104 */     output.write("<xfdf xmlns=\"http://ns.adobe.com/xfdf/\" xml:space=\"preserve\">\n");
/*     */     
/* 106 */     getCatalog().writeXML(output);
/*     */     
/* 108 */     output.write("</xfdf>\n");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDocument getDocument() {
/* 118 */     return this.document;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFCatalog getCatalog() {
/* 128 */     FDFCatalog retval = null;
/* 129 */     COSDictionary trailer = this.document.getTrailer();
/* 130 */     COSDictionary root = trailer.getCOSDictionary(COSName.ROOT);
/* 131 */     if (root == null) {
/*     */       
/* 133 */       retval = new FDFCatalog();
/* 134 */       setCatalog(retval);
/*     */     }
/*     */     else {
/*     */       
/* 138 */       retval = new FDFCatalog(root);
/*     */     } 
/* 140 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCatalog(FDFCatalog cat) {
/* 150 */     COSDictionary trailer = this.document.getTrailer();
/* 151 */     trailer.setItem(COSName.ROOT, cat);
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
/*     */   public static FDFDocument load(String filename) throws IOException {
/* 165 */     FDFParser parser = new FDFParser(filename);
/* 166 */     parser.parse();
/* 167 */     return new FDFDocument(parser.getDocument());
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
/*     */   public static FDFDocument load(File file) throws IOException {
/* 181 */     FDFParser parser = new FDFParser(file);
/* 182 */     parser.parse();
/* 183 */     return new FDFDocument(parser.getDocument());
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
/*     */   public static FDFDocument load(InputStream input) throws IOException {
/* 197 */     FDFParser parser = new FDFParser(input);
/* 198 */     parser.parse();
/* 199 */     return new FDFDocument(parser.getDocument());
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
/*     */   public static FDFDocument loadXFDF(String filename) throws IOException {
/* 213 */     return loadXFDF(new BufferedInputStream(new FileInputStream(filename)));
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
/*     */   public static FDFDocument loadXFDF(File file) throws IOException {
/* 227 */     return loadXFDF(new BufferedInputStream(new FileInputStream(file)));
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
/*     */   public static FDFDocument loadXFDF(InputStream input) throws IOException {
/* 241 */     return new FDFDocument(XMLUtil.parse(input));
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
/*     */   public void save(File fileName) throws IOException {
/* 253 */     save(new FileOutputStream(fileName));
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
/*     */   public void save(String fileName) throws IOException {
/* 265 */     save(new FileOutputStream(fileName));
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
/*     */   public void save(OutputStream output) throws IOException {
/* 277 */     COSWriter writer = null;
/*     */     
/*     */     try {
/* 280 */       writer = new COSWriter(output);
/* 281 */       writer.write(this);
/* 282 */       writer.close();
/*     */     }
/*     */     finally {
/*     */       
/* 286 */       if (writer != null)
/*     */       {
/* 288 */         writer.close();
/*     */       }
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
/*     */   public void saveXFDF(File fileName) throws IOException {
/* 302 */     saveXFDF(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8")));
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
/*     */   public void saveXFDF(String fileName) throws IOException {
/* 315 */     saveXFDF(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8")));
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
/*     */   public void saveXFDF(Writer output) throws IOException {
/*     */     try {
/* 330 */       writeXML(output);
/*     */     }
/*     */     finally {
/*     */       
/* 334 */       if (output != null)
/*     */       {
/* 336 */         output.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 348 */     this.document.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */