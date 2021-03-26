/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
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
/*     */ public class PDAnnotationFileAttachment
/*     */   extends PDAnnotationMarkup
/*     */ {
/*     */   public static final String ATTACHMENT_NAME_PUSH_PIN = "PushPin";
/*     */   public static final String ATTACHMENT_NAME_GRAPH = "Graph";
/*     */   public static final String ATTACHMENT_NAME_PAPERCLIP = "Paperclip";
/*     */   public static final String ATTACHMENT_NAME_TAG = "Tag";
/*     */   public static final String SUB_TYPE = "FileAttachment";
/*     */   
/*     */   public PDAnnotationFileAttachment() {
/*  59 */     getCOSObject().setName(COSName.SUBTYPE, "FileAttachment");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationFileAttachment(COSDictionary field) {
/*  69 */     super(field);
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
/*     */   public PDFileSpecification getFile() throws IOException {
/*  81 */     return PDFileSpecification.createFS(getCOSObject().getDictionaryObject("FS"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFile(PDFileSpecification file) {
/*  91 */     getCOSObject().setItem("FS", (COSObjectable)file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAttachmentName() {
/* 101 */     return getCOSObject().getNameAsString(COSName.NAME, "PushPin");
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
/*     */   public void setAttachementName(String name) {
/* 113 */     getCOSObject().setName(COSName.NAME, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttachmentName(String name) {
/* 123 */     getCOSObject().setName(COSName.NAME, name);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAnnotationFileAttachment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */