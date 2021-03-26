/*     */ package org.apache.pdfbox.pdmodel.interactive.pagenavigation;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.pdmodel.PDDocumentInformation;
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
/*     */ 
/*     */ public class PDThread
/*     */   implements COSObjectable
/*     */ {
/*     */   private COSDictionary thread;
/*     */   
/*     */   public PDThread(COSDictionary t) {
/*  42 */     this.thread = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDThread() {
/*  51 */     this.thread = new COSDictionary();
/*  52 */     this.thread.setName("Type", "Thread");
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
/*  63 */     return this.thread;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocumentInformation getThreadInfo() {
/*  73 */     PDDocumentInformation retval = null;
/*  74 */     COSDictionary info = (COSDictionary)this.thread.getDictionaryObject("I");
/*  75 */     if (info != null)
/*     */     {
/*  77 */       retval = new PDDocumentInformation(info);
/*     */     }
/*     */     
/*  80 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThreadInfo(PDDocumentInformation info) {
/*  90 */     this.thread.setItem("I", (COSObjectable)info);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDThreadBead getFirstBead() {
/* 101 */     PDThreadBead retval = null;
/* 102 */     COSDictionary bead = (COSDictionary)this.thread.getDictionaryObject("F");
/* 103 */     if (bead != null)
/*     */     {
/* 105 */       retval = new PDThreadBead(bead);
/*     */     }
/*     */     
/* 108 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstBead(PDThreadBead bead) {
/* 119 */     if (bead != null)
/*     */     {
/* 121 */       bead.setThread(this);
/*     */     }
/* 123 */     this.thread.setItem("F", bead);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/pagenavigation/PDThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */