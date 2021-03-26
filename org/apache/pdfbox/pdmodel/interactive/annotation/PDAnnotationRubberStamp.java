/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSDictionary;
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
/*     */ public class PDAnnotationRubberStamp
/*     */   extends PDAnnotationMarkup
/*     */ {
/*     */   public static final String NAME_APPROVED = "Approved";
/*     */   public static final String NAME_EXPERIMENTAL = "Experimental";
/*     */   public static final String NAME_NOT_APPROVED = "NotApproved";
/*     */   public static final String NAME_AS_IS = "AsIs";
/*     */   public static final String NAME_EXPIRED = "Expired";
/*     */   public static final String NAME_NOT_FOR_PUBLIC_RELEASE = "NotForPublicRelease";
/*     */   public static final String NAME_FOR_PUBLIC_RELEASE = "ForPublicRelease";
/*     */   public static final String NAME_DRAFT = "Draft";
/*     */   public static final String NAME_FOR_COMMENT = "ForComment";
/*     */   public static final String NAME_TOP_SECRET = "TopSecret";
/*     */   public static final String NAME_DEPARTMENTAL = "Departmental";
/*     */   public static final String NAME_CONFIDENTIAL = "Confidential";
/*     */   public static final String NAME_FINAL = "Final";
/*     */   public static final String NAME_SOLD = "Sold";
/*     */   public static final String SUB_TYPE = "Stamp";
/*     */   
/*     */   public PDAnnotationRubberStamp() {
/* 101 */     getCOSObject().setName(COSName.SUBTYPE, "Stamp");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationRubberStamp(COSDictionary field) {
/* 111 */     super(field);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 122 */     getCOSObject().setName(COSName.NAME, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 133 */     return getCOSObject().getNameAsString(COSName.NAME, "Draft");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAnnotationRubberStamp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */