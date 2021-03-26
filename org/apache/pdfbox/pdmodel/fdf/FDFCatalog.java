/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
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
/*     */ public class FDFCatalog
/*     */   implements COSObjectable
/*     */ {
/*     */   private COSDictionary catalog;
/*     */   
/*     */   public FDFCatalog() {
/*  42 */     this.catalog = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFCatalog(COSDictionary cat) {
/*  52 */     this.catalog = cat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFCatalog(Element element) {
/*  62 */     this();
/*  63 */     FDFDictionary fdfDict = new FDFDictionary(element);
/*  64 */     setFDF(fdfDict);
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
/*  76 */     FDFDictionary fdf = getFDF();
/*  77 */     fdf.writeXML(output);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  88 */     return this.catalog;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVersion() {
/*  98 */     return this.catalog.getNameAsString(COSName.VERSION);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(String version) {
/* 108 */     this.catalog.setName(COSName.VERSION, version);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFDictionary getFDF() {
/*     */     FDFDictionary retval;
/* 118 */     COSDictionary fdf = (COSDictionary)this.catalog.getDictionaryObject(COSName.FDF);
/*     */     
/* 120 */     if (fdf != null) {
/*     */       
/* 122 */       retval = new FDFDictionary(fdf);
/*     */     }
/*     */     else {
/*     */       
/* 126 */       retval = new FDFDictionary();
/* 127 */       setFDF(retval);
/*     */     } 
/* 129 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFDF(FDFDictionary fdf) {
/* 139 */     this.catalog.setItem(COSName.FDF, fdf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSignature getSignature() {
/* 149 */     PDSignature signature = null;
/* 150 */     COSDictionary sig = (COSDictionary)this.catalog.getDictionaryObject(COSName.SIG);
/* 151 */     if (sig != null)
/*     */     {
/* 153 */       signature = new PDSignature(sig);
/*     */     }
/* 155 */     return signature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSignature(PDSignature sig) {
/* 165 */     this.catalog.setItem(COSName.SIG, (COSObjectable)sig);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFCatalog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */