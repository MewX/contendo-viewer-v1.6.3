/*     */ package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.PDPageTree;
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
/*     */ public abstract class PDPageDestination
/*     */   extends PDDestination
/*     */ {
/*     */   protected COSArray array;
/*     */   
/*     */   protected PDPageDestination() {
/*  45 */     this.array = new COSArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PDPageDestination(COSArray arr) {
/*  55 */     this.array = arr;
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
/*     */   public PDPage getPage() {
/*  68 */     PDPage retval = null;
/*  69 */     if (this.array.size() > 0) {
/*     */       
/*  71 */       COSBase page = this.array.getObject(0);
/*  72 */       if (page instanceof COSDictionary)
/*     */       {
/*  74 */         retval = new PDPage((COSDictionary)page);
/*     */       }
/*     */     } 
/*  77 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPage(PDPage page) {
/*  87 */     this.array.set(0, (COSObjectable)page);
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
/*     */   public int getPageNumber() {
/* 100 */     int retval = -1;
/* 101 */     if (this.array.size() > 0) {
/*     */       
/* 103 */       COSBase page = this.array.getObject(0);
/* 104 */       if (page instanceof COSNumber)
/*     */       {
/* 106 */         retval = ((COSNumber)page).intValue();
/*     */       }
/*     */     } 
/* 109 */     return retval;
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
/*     */   @Deprecated
/*     */   public int findPageNumber() {
/* 126 */     int retval = -1;
/* 127 */     if (this.array.size() > 0) {
/*     */       
/* 129 */       COSBase page = this.array.getObject(0);
/* 130 */       if (page instanceof COSNumber) {
/*     */         
/* 132 */         retval = ((COSNumber)page).intValue();
/*     */       }
/* 134 */       else if (page instanceof COSDictionary) {
/*     */         
/* 136 */         COSBase parent = page;
/* 137 */         while (((COSDictionary)parent).getDictionaryObject(COSName.PARENT, COSName.P) != null)
/*     */         {
/* 139 */           parent = ((COSDictionary)parent).getDictionaryObject(COSName.PARENT, COSName.P);
/*     */         }
/*     */         
/* 142 */         PDPageTree pages = new PDPageTree((COSDictionary)parent);
/* 143 */         return pages.indexOf(new PDPage((COSDictionary)page)) + 1;
/*     */       } 
/*     */     } 
/* 146 */     return retval;
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
/*     */   public int retrievePageNumber() {
/* 158 */     int retval = -1;
/* 159 */     if (this.array.size() > 0) {
/*     */       
/* 161 */       COSBase page = this.array.getObject(0);
/* 162 */       if (page instanceof COSNumber) {
/*     */         
/* 164 */         retval = ((COSNumber)page).intValue();
/*     */       }
/* 166 */       else if (page instanceof COSDictionary) {
/*     */         
/* 168 */         return indexOfPageTree((COSDictionary)page);
/*     */       } 
/*     */     } 
/* 171 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int indexOfPageTree(COSDictionary pageDict) {
/* 177 */     COSDictionary parent = pageDict;
/* 178 */     while (parent.getDictionaryObject(COSName.PARENT, COSName.P) instanceof COSDictionary)
/*     */     {
/* 180 */       parent = (COSDictionary)parent.getDictionaryObject(COSName.PARENT, COSName.P);
/*     */     }
/* 182 */     if (parent.containsKey(COSName.KIDS) && COSName.PAGES.equals(parent.getItem(COSName.TYPE))) {
/*     */ 
/*     */       
/* 185 */       PDPageTree pages = new PDPageTree(parent);
/* 186 */       return pages.indexOf(new PDPage(pageDict));
/*     */     } 
/* 188 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPageNumber(int pageNumber) {
/* 199 */     this.array.set(0, pageNumber);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getCOSObject() {
/* 210 */     return this.array;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/destination/PDPageDestination.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */