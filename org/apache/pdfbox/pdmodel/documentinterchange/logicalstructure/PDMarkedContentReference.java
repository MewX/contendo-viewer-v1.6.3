/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
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
/*     */ public class PDMarkedContentReference
/*     */   implements COSObjectable
/*     */ {
/*     */   public static final String TYPE = "MCR";
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  41 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDMarkedContentReference() {
/*  49 */     this.dictionary = new COSDictionary();
/*  50 */     this.dictionary.setName(COSName.TYPE, "MCR");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDMarkedContentReference(COSDictionary dictionary) {
/*  60 */     this.dictionary = dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPage getPage() {
/*  70 */     COSDictionary pg = (COSDictionary)getCOSObject().getDictionaryObject(COSName.PG);
/*  71 */     if (pg != null)
/*     */     {
/*  73 */       return new PDPage(pg);
/*     */     }
/*  75 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPage(PDPage page) {
/*  85 */     getCOSObject().setItem(COSName.PG, (COSObjectable)page);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMCID() {
/*  95 */     return getCOSObject().getInt(COSName.MCID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMCID(int mcid) {
/* 105 */     getCOSObject().setInt(COSName.MCID, mcid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 112 */     return "mcid=" + getMCID();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/logicalstructure/PDMarkedContentReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */