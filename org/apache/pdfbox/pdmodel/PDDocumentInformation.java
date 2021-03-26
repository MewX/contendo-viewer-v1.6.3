/*     */ package org.apache.pdfbox.pdmodel;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDDocumentInformation
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary info;
/*     */   
/*     */   public PDDocumentInformation() {
/*  46 */     this.info = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocumentInformation(COSDictionary dic) {
/*  56 */     this.info = dic;
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
/*  67 */     return this.info;
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
/*     */   public Object getPropertyStringValue(String propertyKey) {
/*  82 */     return this.info.getString(propertyKey);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/*  92 */     return this.info.getString(COSName.TITLE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/* 102 */     this.info.setString(COSName.TITLE, title);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAuthor() {
/* 112 */     return this.info.getString(COSName.AUTHOR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAuthor(String author) {
/* 122 */     this.info.setString(COSName.AUTHOR, author);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubject() {
/* 132 */     return this.info.getString(COSName.SUBJECT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubject(String subject) {
/* 142 */     this.info.setString(COSName.SUBJECT, subject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getKeywords() {
/* 152 */     return this.info.getString(COSName.KEYWORDS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeywords(String keywords) {
/* 162 */     this.info.setString(COSName.KEYWORDS, keywords);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCreator() {
/* 172 */     return this.info.getString(COSName.CREATOR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreator(String creator) {
/* 182 */     this.info.setString(COSName.CREATOR, creator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProducer() {
/* 192 */     return this.info.getString(COSName.PRODUCER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProducer(String producer) {
/* 202 */     this.info.setString(COSName.PRODUCER, producer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getCreationDate() {
/* 212 */     return this.info.getDate(COSName.CREATION_DATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreationDate(Calendar date) {
/* 222 */     this.info.setDate(COSName.CREATION_DATE, date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getModificationDate() {
/* 232 */     return this.info.getDate(COSName.MOD_DATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModificationDate(Calendar date) {
/* 242 */     this.info.setDate(COSName.MOD_DATE, date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTrapped() {
/* 253 */     return this.info.getNameAsString(COSName.TRAPPED);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> getMetadataKeys() {
/* 264 */     Set<String> keys = new TreeSet<String>();
/* 265 */     for (COSName key : this.info.keySet())
/*     */     {
/* 267 */       keys.add(key.getName());
/*     */     }
/* 269 */     return keys;
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
/*     */   public String getCustomMetadataValue(String fieldName) {
/* 282 */     return this.info.getString(fieldName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomMetadataValue(String fieldName, String fieldValue) {
/* 293 */     this.info.setString(fieldName, fieldValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrapped(String value) {
/* 304 */     if (value != null && 
/* 305 */       !value.equals("True") && 
/* 306 */       !value.equals("False") && 
/* 307 */       !value.equals("Unknown"))
/*     */     {
/* 309 */       throw new RuntimeException("Valid values for trapped are 'True', 'False', or 'Unknown'");
/*     */     }
/*     */ 
/*     */     
/* 313 */     this.info.setName(COSName.TRAPPED, value);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDDocumentInformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */