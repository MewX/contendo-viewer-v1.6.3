/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDArtifactMarkedContent
/*     */   extends PDMarkedContent
/*     */ {
/*     */   public PDArtifactMarkedContent(COSDictionary properties) {
/*  36 */     super(COSName.ARTIFACT, properties);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  47 */     return getProperties().getNameAsString(COSName.TYPE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getBBox() {
/*  57 */     PDRectangle retval = null;
/*  58 */     COSArray a = (COSArray)getProperties().getDictionaryObject(COSName.BBOX);
/*     */     
/*  60 */     if (a != null)
/*     */     {
/*  62 */       retval = new PDRectangle(a);
/*     */     }
/*  64 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTopAttached() {
/*  75 */     return isAttached("Top");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBottomAttached() {
/*  86 */     return isAttached("Bottom");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLeftAttached() {
/*  97 */     return isAttached("Left");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRightAttached() {
/* 108 */     return isAttached("Right");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubtype() {
/* 118 */     return getProperties().getNameAsString(COSName.SUBTYPE);
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
/*     */   private boolean isAttached(String edge) {
/* 131 */     COSArray a = (COSArray)getProperties().getDictionaryObject(COSName.ATTACHED);
/*     */     
/* 133 */     if (a != null)
/*     */     {
/* 135 */       for (int i = 0; i < a.size(); i++) {
/*     */         
/* 137 */         if (edge.equals(a.getName(i)))
/*     */         {
/* 139 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 143 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/taggedpdf/PDArtifactMarkedContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */