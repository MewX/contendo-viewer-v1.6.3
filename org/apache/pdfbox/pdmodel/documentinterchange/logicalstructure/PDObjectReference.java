/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDObjectReference
/*     */   implements COSObjectable
/*     */ {
/*     */   public static final String TYPE = "OBJR";
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  55 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDObjectReference() {
/*  64 */     this.dictionary = new COSDictionary();
/*  65 */     this.dictionary.setName(COSName.TYPE, "OBJR");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDObjectReference(COSDictionary theDictionary) {
/*  75 */     this.dictionary = theDictionary;
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
/*     */   public COSObjectable getReferencedObject() {
/*  87 */     COSBase obj = getCOSObject().getDictionaryObject(COSName.OBJ);
/*  88 */     if (!(obj instanceof COSDictionary))
/*     */     {
/*  90 */       return null;
/*     */     }
/*     */     
/*     */     try {
/*  94 */       if (obj instanceof org.apache.pdfbox.cos.COSStream) {
/*     */         
/*  96 */         PDXObject xobject = PDXObject.createXObject(obj, null);
/*  97 */         if (xobject != null)
/*     */         {
/*  99 */           return (COSObjectable)xobject;
/*     */         }
/*     */       } 
/* 102 */       COSDictionary objDictionary = (COSDictionary)obj;
/* 103 */       PDAnnotation annotation = PDAnnotation.createAnnotation(obj);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 110 */       if (!(annotation instanceof org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationUnknown) || COSName.ANNOT
/* 111 */         .equals(objDictionary.getDictionaryObject(COSName.TYPE)))
/*     */       {
/* 113 */         return (COSObjectable)annotation;
/*     */       }
/*     */     }
/* 116 */     catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */     
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReferencedObject(PDAnnotation annotation) {
/* 130 */     getCOSObject().setItem(COSName.OBJ, (COSObjectable)annotation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReferencedObject(PDXObject xobject) {
/* 140 */     getCOSObject().setItem(COSName.OBJ, (COSObjectable)xobject);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/logicalstructure/PDObjectReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */