/*     */ package org.apache.pdfbox.pdmodel.interactive.pagenavigation;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDThreadBead
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary bead;
/*     */   
/*     */   public PDThreadBead(COSDictionary b) {
/*  45 */     this.bead = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDThreadBead() {
/*  54 */     this.bead = new COSDictionary();
/*  55 */     this.bead.setName("Type", "Bead");
/*  56 */     setNextBead(this);
/*  57 */     setPreviousBead(this);
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
/*  68 */     return this.bead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDThread getThread() {
/*  79 */     PDThread retval = null;
/*  80 */     COSDictionary dic = (COSDictionary)this.bead.getDictionaryObject("T");
/*  81 */     if (dic != null)
/*     */     {
/*  83 */       retval = new PDThread(dic);
/*     */     }
/*  85 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThread(PDThread thread) {
/*  96 */     this.bead.setItem("T", thread);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDThreadBead getNextBead() {
/* 107 */     return new PDThreadBead((COSDictionary)this.bead.getDictionaryObject("N"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setNextBead(PDThreadBead next) {
/* 117 */     this.bead.setItem("N", next);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDThreadBead getPreviousBead() {
/* 128 */     return new PDThreadBead((COSDictionary)this.bead.getDictionaryObject("V"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setPreviousBead(PDThreadBead previous) {
/* 138 */     this.bead.setItem("V", previous);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendBead(PDThreadBead append) {
/* 149 */     PDThreadBead nextBead = getNextBead();
/* 150 */     nextBead.setPreviousBead(append);
/* 151 */     append.setNextBead(nextBead);
/* 152 */     setNextBead(append);
/* 153 */     append.setPreviousBead(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPage getPage() {
/* 163 */     PDPage page = null;
/* 164 */     COSDictionary dic = (COSDictionary)this.bead.getDictionaryObject("P");
/* 165 */     if (dic != null)
/*     */     {
/* 167 */       page = new PDPage(dic);
/*     */     }
/* 169 */     return page;
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
/*     */   public void setPage(PDPage page) {
/* 181 */     this.bead.setItem("P", (COSObjectable)page);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getRectangle() {
/* 191 */     PDRectangle rect = null;
/* 192 */     COSArray array = (COSArray)this.bead.getDictionaryObject(COSName.R);
/* 193 */     if (array != null)
/*     */     {
/* 195 */       rect = new PDRectangle(array);
/*     */     }
/* 197 */     return rect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRectangle(PDRectangle rect) {
/* 207 */     this.bead.setItem(COSName.R, (COSObjectable)rect);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/pagenavigation/PDThreadBead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */