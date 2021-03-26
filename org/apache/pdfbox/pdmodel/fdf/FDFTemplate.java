/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
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
/*     */ public class FDFTemplate
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary template;
/*     */   
/*     */   public FDFTemplate() {
/*  42 */     this.template = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFTemplate(COSDictionary t) {
/*  52 */     this.template = t;
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
/*  63 */     return this.template;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFNamedPageReference getTemplateReference() {
/*  73 */     FDFNamedPageReference retval = null;
/*  74 */     COSDictionary dict = (COSDictionary)this.template.getDictionaryObject(COSName.TREF);
/*  75 */     if (dict != null)
/*     */     {
/*  77 */       retval = new FDFNamedPageReference(dict);
/*     */     }
/*  79 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTemplateReference(FDFNamedPageReference tRef) {
/*  89 */     this.template.setItem(COSName.TREF, tRef);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<FDFField> getFields() {
/*     */     COSArrayList cOSArrayList;
/*  99 */     List<FDFField> retval = null;
/* 100 */     COSArray array = (COSArray)this.template.getDictionaryObject(COSName.FIELDS);
/* 101 */     if (array != null) {
/*     */       
/* 103 */       List<FDFField> fields = new ArrayList<FDFField>();
/* 104 */       for (int i = 0; i < array.size(); i++)
/*     */       {
/* 106 */         fields.add(new FDFField((COSDictionary)array.getObject(i)));
/*     */       }
/* 108 */       cOSArrayList = new COSArrayList(fields, array);
/*     */     } 
/* 110 */     return (List<FDFField>)cOSArrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFields(List<FDFField> fields) {
/* 120 */     this.template.setItem(COSName.FIELDS, (COSBase)COSArrayList.converterToCOSArray(fields));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRename() {
/* 130 */     return this.template.getBoolean(COSName.RENAME, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRename(boolean value) {
/* 140 */     this.template.setBoolean(COSName.RENAME, value);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFTemplate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */