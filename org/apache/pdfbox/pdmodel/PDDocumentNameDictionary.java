/*     */ package org.apache.pdfbox.pdmodel;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
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
/*     */ public class PDDocumentNameDictionary
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary nameDictionary;
/*     */   private final PDDocumentCatalog catalog;
/*     */   
/*     */   public PDDocumentNameDictionary(PDDocumentCatalog cat) {
/*  41 */     COSBase names = cat.getCOSObject().getDictionaryObject(COSName.NAMES);
/*  42 */     if (names != null) {
/*     */       
/*  44 */       this.nameDictionary = (COSDictionary)names;
/*     */     }
/*     */     else {
/*     */       
/*  48 */       this.nameDictionary = new COSDictionary();
/*  49 */       cat.getCOSObject().setItem(COSName.NAMES, (COSBase)this.nameDictionary);
/*     */     } 
/*  51 */     this.catalog = cat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocumentNameDictionary(PDDocumentCatalog cat, COSDictionary names) {
/*  62 */     this.catalog = cat;
/*  63 */     this.nameDictionary = names;
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
/*  74 */     return this.nameDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDestinationNameTreeNode getDests() {
/*  85 */     PDDestinationNameTreeNode dests = null;
/*     */     
/*  87 */     COSDictionary dic = (COSDictionary)this.nameDictionary.getDictionaryObject(COSName.DESTS);
/*     */ 
/*     */ 
/*     */     
/*  91 */     if (dic == null)
/*     */     {
/*  93 */       dic = (COSDictionary)this.catalog.getCOSObject().getDictionaryObject(COSName.DESTS);
/*     */     }
/*     */     
/*  96 */     if (dic != null)
/*     */     {
/*  98 */       dests = new PDDestinationNameTreeNode(dic);
/*     */     }
/*     */     
/* 101 */     return dests;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDests(PDDestinationNameTreeNode dests) {
/* 111 */     this.nameDictionary.setItem(COSName.DESTS, (COSObjectable)dests);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     this.catalog.getCOSObject().setItem(COSName.DESTS, (COSObjectable)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDEmbeddedFilesNameTreeNode getEmbeddedFiles() {
/* 128 */     PDEmbeddedFilesNameTreeNode retval = null;
/*     */     
/* 130 */     COSDictionary dic = (COSDictionary)this.nameDictionary.getDictionaryObject(COSName.EMBEDDED_FILES);
/*     */     
/* 132 */     if (dic != null)
/*     */     {
/* 134 */       retval = new PDEmbeddedFilesNameTreeNode(dic);
/*     */     }
/*     */     
/* 137 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEmbeddedFiles(PDEmbeddedFilesNameTreeNode ef) {
/* 147 */     this.nameDictionary.setItem(COSName.EMBEDDED_FILES, (COSObjectable)ef);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDJavascriptNameTreeNode getJavaScript() {
/* 158 */     PDJavascriptNameTreeNode retval = null;
/*     */     
/* 160 */     COSDictionary dic = (COSDictionary)this.nameDictionary.getDictionaryObject(COSName.JAVA_SCRIPT);
/*     */     
/* 162 */     if (dic != null)
/*     */     {
/* 164 */       retval = new PDJavascriptNameTreeNode(dic);
/*     */     }
/*     */     
/* 167 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJavascript(PDJavascriptNameTreeNode js) {
/* 177 */     this.nameDictionary.setItem(COSName.JAVA_SCRIPT, (COSObjectable)js);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDDocumentNameDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */