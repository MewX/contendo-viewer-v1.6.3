/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.markedcontent;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDArtifactMarkedContent;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*     */ import org.apache.pdfbox.text.TextPosition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDMarkedContent
/*     */ {
/*     */   private final String tag;
/*     */   private final COSDictionary properties;
/*     */   private final List<Object> contents;
/*     */   
/*     */   public static PDMarkedContent create(COSName tag, COSDictionary properties) {
/*  45 */     if (COSName.ARTIFACT.equals(tag))
/*     */     {
/*  47 */       return (PDMarkedContent)new PDArtifactMarkedContent(properties);
/*     */     }
/*  49 */     return new PDMarkedContent(tag, properties);
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
/*     */   public PDMarkedContent(COSName tag, COSDictionary properties) {
/*  66 */     this.tag = (tag == null) ? null : tag.getName();
/*  67 */     this.properties = properties;
/*  68 */     this.contents = new ArrayList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTag() {
/*  79 */     return this.tag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getProperties() {
/*  89 */     return this.properties;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMCID() {
/*  99 */     return (getProperties() == null) ? -1 : 
/* 100 */       getProperties().getInt(COSName.MCID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLanguage() {
/* 110 */     return (getProperties() == null) ? null : 
/* 111 */       getProperties().getNameAsString(COSName.LANG);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getActualText() {
/* 121 */     return (getProperties() == null) ? null : 
/* 122 */       getProperties().getString(COSName.ACTUAL_TEXT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateDescription() {
/* 132 */     return (getProperties() == null) ? null : 
/* 133 */       getProperties().getString(COSName.ALT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExpandedForm() {
/* 143 */     return (getProperties() == null) ? null : 
/* 144 */       getProperties().getString(COSName.E);
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
/*     */   public List<Object> getContents() {
/* 159 */     return this.contents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addText(TextPosition text) {
/* 169 */     getContents().add(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMarkedContent(PDMarkedContent markedContent) {
/* 179 */     getContents().add(markedContent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addXObject(PDXObject xobject) {
/* 189 */     getContents().add(xobject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 196 */     return "tag=" + this.tag + ", properties=" + this.properties + ", contents=" + this.contents;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/markedcontent/PDMarkedContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */