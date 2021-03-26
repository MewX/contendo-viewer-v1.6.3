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
/*     */ public class FDFPage
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary page;
/*     */   
/*     */   public FDFPage() {
/*  42 */     this.page = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFPage(COSDictionary p) {
/*  52 */     this.page = p;
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
/*  63 */     return this.page;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<FDFTemplate> getTemplates() {
/*     */     COSArrayList cOSArrayList;
/*  73 */     List<FDFTemplate> retval = null;
/*  74 */     COSArray array = (COSArray)this.page.getDictionaryObject(COSName.TEMPLATES);
/*  75 */     if (array != null) {
/*     */       
/*  77 */       List<FDFTemplate> objects = new ArrayList<FDFTemplate>();
/*  78 */       for (int i = 0; i < array.size(); i++)
/*     */       {
/*  80 */         objects.add(new FDFTemplate((COSDictionary)array.getObject(i)));
/*     */       }
/*  82 */       cOSArrayList = new COSArrayList(objects, array);
/*     */     } 
/*  84 */     return (List<FDFTemplate>)cOSArrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTemplates(List<FDFTemplate> templates) {
/*  94 */     this.page.setItem(COSName.TEMPLATES, (COSBase)COSArrayList.converterToCOSArray(templates));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFPageInfo getPageInfo() {
/* 104 */     FDFPageInfo retval = null;
/* 105 */     COSDictionary dict = this.page.getCOSDictionary(COSName.INFO);
/* 106 */     if (dict != null)
/*     */     {
/* 108 */       retval = new FDFPageInfo(dict);
/*     */     }
/* 110 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPageInfo(FDFPageInfo info) {
/* 120 */     this.page.setItem(COSName.INFO, info);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFPage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */