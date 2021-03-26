/*     */ package org.apache.pdfbox.pdmodel.graphics.optionalcontent;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDOptionalContentMembershipDictionary
/*     */   extends PDPropertyList
/*     */ {
/*     */   public PDOptionalContentMembershipDictionary() {
/*  39 */     this.dict.setItem(COSName.TYPE, (COSBase)COSName.OCMD);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDOptionalContentMembershipDictionary(COSDictionary dict) {
/*  48 */     super(dict);
/*  49 */     if (!dict.getItem(COSName.TYPE).equals(COSName.OCMD))
/*     */     {
/*  51 */       throw new IllegalArgumentException("Provided dictionary is not of type '" + COSName.OCMD + "'");
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
/*     */   public List<PDPropertyList> getOCGs() {
/*  63 */     List<PDPropertyList> list = new ArrayList<PDPropertyList>();
/*  64 */     COSBase base = this.dict.getDictionaryObject(COSName.OCGS);
/*  65 */     if (base instanceof COSDictionary) {
/*     */       
/*  67 */       list.add(PDPropertyList.create((COSDictionary)base));
/*     */     }
/*  69 */     else if (base instanceof COSArray) {
/*     */       
/*  71 */       COSArray ar = (COSArray)base;
/*  72 */       for (int i = 0; i < ar.size(); i++) {
/*     */         
/*  74 */         COSBase elem = ar.getObject(i);
/*  75 */         if (elem instanceof COSDictionary)
/*     */         {
/*  77 */           list.add(PDPropertyList.create((COSDictionary)elem));
/*     */         }
/*     */       } 
/*     */     } 
/*  81 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOCGs(List<PDPropertyList> ocgs) {
/*  91 */     COSArray ar = new COSArray();
/*  92 */     for (PDPropertyList prop : ocgs)
/*     */     {
/*  94 */       ar.add((COSObjectable)prop);
/*     */     }
/*  96 */     this.dict.setItem(COSName.OCGS, (COSBase)ar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSName getVisibilityPolicy() {
/* 106 */     return this.dict.getCOSName(COSName.P, COSName.ANY_ON);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisibilityPolicy(COSName visibilityPolicy) {
/* 115 */     this.dict.setItem(COSName.P, (COSBase)visibilityPolicy);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/optionalcontent/PDOptionalContentMembershipDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */