/*     */ package org.apache.pdfbox.pdmodel.common.filespecification;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Calendar;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
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
/*     */ public class PDEmbeddedFile
/*     */   extends PDStream
/*     */ {
/*     */   public PDEmbeddedFile(PDDocument document) {
/*  44 */     super(document);
/*  45 */     getCOSObject().setName(COSName.TYPE, "EmbeddedFile");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDEmbeddedFile(COSStream str) {
/*  56 */     super(str);
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
/*     */   public PDEmbeddedFile(PDDocument doc, InputStream str) throws IOException {
/*  69 */     super(doc, str);
/*  70 */     getCOSObject().setName(COSName.TYPE, "EmbeddedFile");
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
/*     */   public PDEmbeddedFile(PDDocument doc, InputStream input, COSName filter) throws IOException {
/*  84 */     super(doc, input, filter);
/*  85 */     getCOSObject().setName(COSName.TYPE, "EmbeddedFile");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubtype(String mimeType) {
/*  95 */     getCOSObject().setName(COSName.SUBTYPE, mimeType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubtype() {
/* 105 */     return getCOSObject().getNameAsString(COSName.SUBTYPE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 115 */     return getCOSObject().getEmbeddedInt("Params", "Size");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(int size) {
/* 125 */     getCOSObject().setEmbeddedInt("Params", "Size", size);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getCreationDate() throws IOException {
/* 136 */     return getCOSObject().getEmbeddedDate("Params", "CreationDate");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreationDate(Calendar creation) {
/* 146 */     getCOSObject().setEmbeddedDate("Params", "CreationDate", creation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getModDate() throws IOException {
/* 157 */     return getCOSObject().getEmbeddedDate("Params", "ModDate");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModDate(Calendar mod) {
/* 167 */     getCOSObject().setEmbeddedDate("Params", "ModDate", mod);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCheckSum() {
/* 177 */     return getCOSObject().getEmbeddedString("Params", "CheckSum");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCheckSum(String checksum) {
/* 187 */     getCOSObject().setEmbeddedString("Params", "CheckSum", checksum);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMacSubtype() {
/* 197 */     String retval = null;
/* 198 */     COSDictionary params = (COSDictionary)getCOSObject().getDictionaryObject(COSName.PARAMS);
/* 199 */     if (params != null)
/*     */     {
/* 201 */       retval = params.getEmbeddedString("Mac", "Subtype");
/*     */     }
/* 203 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMacSubtype(String macSubtype) {
/* 213 */     COSDictionary params = (COSDictionary)getCOSObject().getDictionaryObject(COSName.PARAMS);
/* 214 */     if (params == null && macSubtype != null) {
/*     */       
/* 216 */       params = new COSDictionary();
/* 217 */       getCOSObject().setItem(COSName.PARAMS, (COSBase)params);
/*     */     } 
/* 219 */     if (params != null)
/*     */     {
/* 221 */       params.setEmbeddedString("Mac", "Subtype", macSubtype);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMacCreator() {
/* 232 */     String retval = null;
/* 233 */     COSDictionary params = (COSDictionary)getCOSObject().getDictionaryObject(COSName.PARAMS);
/* 234 */     if (params != null)
/*     */     {
/* 236 */       retval = params.getEmbeddedString("Mac", "Creator");
/*     */     }
/* 238 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMacCreator(String macCreator) {
/* 248 */     COSDictionary params = (COSDictionary)getCOSObject().getDictionaryObject(COSName.PARAMS);
/* 249 */     if (params == null && macCreator != null) {
/*     */       
/* 251 */       params = new COSDictionary();
/* 252 */       getCOSObject().setItem(COSName.PARAMS, (COSBase)params);
/*     */     } 
/* 254 */     if (params != null)
/*     */     {
/* 256 */       params.setEmbeddedString("Mac", "Creator", macCreator);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMacResFork() {
/* 267 */     String retval = null;
/* 268 */     COSDictionary params = (COSDictionary)getCOSObject().getDictionaryObject(COSName.PARAMS);
/* 269 */     if (params != null)
/*     */     {
/* 271 */       retval = params.getEmbeddedString("Mac", "ResFork");
/*     */     }
/* 273 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMacResFork(String macResFork) {
/* 283 */     COSDictionary params = (COSDictionary)getCOSObject().getDictionaryObject(COSName.PARAMS);
/* 284 */     if (params == null && macResFork != null) {
/*     */       
/* 286 */       params = new COSDictionary();
/* 287 */       getCOSObject().setItem(COSName.PARAMS, (COSBase)params);
/*     */     } 
/* 289 */     if (params != null)
/*     */     {
/* 291 */       params.setEmbeddedString("Mac", "ResFork", macResFork);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/filespecification/PDEmbeddedFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */