/*     */ package org.apache.pdfbox.pdmodel.common.filespecification;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
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
/*     */ public class PDComplexFileSpecification
/*     */   extends PDFileSpecification
/*     */ {
/*     */   private final COSDictionary fs;
/*     */   private COSDictionary efDictionary;
/*     */   
/*     */   public PDComplexFileSpecification() {
/*  40 */     this.fs = new COSDictionary();
/*  41 */     this.fs.setItem(COSName.TYPE, (COSBase)COSName.FILESPEC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDComplexFileSpecification(COSDictionary dict) {
/*  51 */     if (dict == null) {
/*     */       
/*  53 */       this.fs = new COSDictionary();
/*  54 */       this.fs.setItem(COSName.TYPE, (COSBase)COSName.FILESPEC);
/*     */     }
/*     */     else {
/*     */       
/*  58 */       this.fs = dict;
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
/*     */   public COSDictionary getCOSObject() {
/*  70 */     return this.fs;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSDictionary getEFDictionary() {
/*  75 */     if (this.efDictionary == null && this.fs != null)
/*     */     {
/*  77 */       this.efDictionary = (COSDictionary)this.fs.getDictionaryObject(COSName.EF);
/*     */     }
/*  79 */     return this.efDictionary;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSBase getObjectFromEFDictionary(COSName key) {
/*  84 */     COSDictionary ef = getEFDictionary();
/*  85 */     if (ef != null)
/*     */     {
/*  87 */       return ef.getDictionaryObject(key);
/*     */     }
/*  89 */     return null;
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
/*     */   public String getFilename() {
/* 103 */     String filename = getFileUnicode();
/* 104 */     if (filename == null)
/*     */     {
/* 106 */       filename = getFileDos();
/*     */     }
/* 108 */     if (filename == null)
/*     */     {
/* 110 */       filename = getFileMac();
/*     */     }
/* 112 */     if (filename == null)
/*     */     {
/* 114 */       filename = getFileUnix();
/*     */     }
/* 116 */     if (filename == null)
/*     */     {
/* 118 */       filename = getFile();
/*     */     }
/* 120 */     return filename;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileUnicode() {
/* 130 */     return this.fs.getString(COSName.UF);
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
/*     */   public void setFileUnicode(String file) {
/* 142 */     this.fs.setString(COSName.UF, file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFile() {
/* 153 */     return this.fs.getString(COSName.F);
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
/*     */   public void setFile(String file) {
/* 166 */     this.fs.setString(COSName.F, file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileDos() {
/* 176 */     return this.fs.getString(COSName.DOS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setFileDos(String file) {
/* 188 */     this.fs.setString(COSName.DOS, file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileMac() {
/* 198 */     return this.fs.getString(COSName.MAC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setFileMac(String file) {
/* 210 */     this.fs.setString(COSName.MAC, file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileUnix() {
/* 220 */     return this.fs.getString(COSName.UNIX);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setFileUnix(String file) {
/* 232 */     this.fs.setString(COSName.UNIX, file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVolatile(boolean fileIsVolatile) {
/* 243 */     this.fs.setBoolean(COSName.V, fileIsVolatile);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVolatile() {
/* 253 */     return this.fs.getBoolean(COSName.V, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDEmbeddedFile getEmbeddedFile() {
/* 263 */     PDEmbeddedFile file = null;
/* 264 */     COSStream stream = (COSStream)getObjectFromEFDictionary(COSName.F);
/* 265 */     if (stream != null)
/*     */     {
/* 267 */       file = new PDEmbeddedFile(stream);
/*     */     }
/* 269 */     return file;
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
/*     */   public void setEmbeddedFile(PDEmbeddedFile file) {
/* 281 */     COSDictionary ef = getEFDictionary();
/* 282 */     if (ef == null && file != null) {
/*     */       
/* 284 */       ef = new COSDictionary();
/* 285 */       this.fs.setItem(COSName.EF, (COSBase)ef);
/*     */     } 
/* 287 */     if (ef != null)
/*     */     {
/* 289 */       ef.setItem(COSName.F, (COSObjectable)file);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDEmbeddedFile getEmbeddedFileDos() {
/* 300 */     PDEmbeddedFile file = null;
/* 301 */     COSStream stream = (COSStream)getObjectFromEFDictionary(COSName.DOS);
/* 302 */     if (stream != null)
/*     */     {
/* 304 */       file = new PDEmbeddedFile(stream);
/*     */     }
/* 306 */     return file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setEmbeddedFileDos(PDEmbeddedFile file) {
/* 318 */     COSDictionary ef = getEFDictionary();
/* 319 */     if (ef == null && file != null) {
/*     */       
/* 321 */       ef = new COSDictionary();
/* 322 */       this.fs.setItem(COSName.EF, (COSBase)ef);
/*     */     } 
/* 324 */     if (ef != null)
/*     */     {
/* 326 */       ef.setItem(COSName.DOS, (COSObjectable)file);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDEmbeddedFile getEmbeddedFileMac() {
/* 337 */     PDEmbeddedFile file = null;
/* 338 */     COSStream stream = (COSStream)getObjectFromEFDictionary(COSName.MAC);
/* 339 */     if (stream != null)
/*     */     {
/* 341 */       file = new PDEmbeddedFile(stream);
/*     */     }
/* 343 */     return file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setEmbeddedFileMac(PDEmbeddedFile file) {
/* 355 */     COSDictionary ef = getEFDictionary();
/* 356 */     if (ef == null && file != null) {
/*     */       
/* 358 */       ef = new COSDictionary();
/* 359 */       this.fs.setItem(COSName.EF, (COSBase)ef);
/*     */     } 
/* 361 */     if (ef != null)
/*     */     {
/* 363 */       ef.setItem(COSName.MAC, (COSObjectable)file);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDEmbeddedFile getEmbeddedFileUnix() {
/* 374 */     PDEmbeddedFile file = null;
/* 375 */     COSStream stream = (COSStream)getObjectFromEFDictionary(COSName.UNIX);
/* 376 */     if (stream != null)
/*     */     {
/* 378 */       file = new PDEmbeddedFile(stream);
/*     */     }
/* 380 */     return file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setEmbeddedFileUnix(PDEmbeddedFile file) {
/* 392 */     COSDictionary ef = getEFDictionary();
/* 393 */     if (ef == null && file != null) {
/*     */       
/* 395 */       ef = new COSDictionary();
/* 396 */       this.fs.setItem(COSName.EF, (COSBase)ef);
/*     */     } 
/* 398 */     if (ef != null)
/*     */     {
/* 400 */       ef.setItem(COSName.UNIX, (COSObjectable)file);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDEmbeddedFile getEmbeddedFileUnicode() {
/* 411 */     PDEmbeddedFile file = null;
/* 412 */     COSStream stream = (COSStream)getObjectFromEFDictionary(COSName.UF);
/* 413 */     if (stream != null)
/*     */     {
/* 415 */       file = new PDEmbeddedFile(stream);
/*     */     }
/* 417 */     return file;
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
/*     */   public void setEmbeddedFileUnicode(PDEmbeddedFile file) {
/* 430 */     COSDictionary ef = getEFDictionary();
/* 431 */     if (ef == null && file != null) {
/*     */       
/* 433 */       ef = new COSDictionary();
/* 434 */       this.fs.setItem(COSName.EF, (COSBase)ef);
/*     */     } 
/* 436 */     if (ef != null)
/*     */     {
/* 438 */       ef.setItem(COSName.UF, (COSObjectable)file);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileDescription(String description) {
/* 449 */     this.fs.setString(COSName.DESC, description);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileDescription() {
/* 459 */     return this.fs.getString(COSName.DESC);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/filespecification/PDComplexFileSpecification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */