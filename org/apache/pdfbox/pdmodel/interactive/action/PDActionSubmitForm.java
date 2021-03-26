/*     */ package org.apache.pdfbox.pdmodel.interactive.action;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
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
/*     */ public class PDActionSubmitForm
/*     */   extends PDAction
/*     */ {
/*     */   public static final String SUB_TYPE = "SubmitForm";
/*     */   
/*     */   public PDActionSubmitForm() {
/*  46 */     setSubType("SubmitForm");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDActionSubmitForm(COSDictionary a) {
/*  56 */     super(a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFileSpecification getFile() throws IOException {
/*  67 */     return PDFileSpecification.createFS(this.action.getDictionaryObject(COSName.F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFile(PDFileSpecification fs) {
/*  77 */     this.action.setItem(COSName.F, (COSObjectable)fs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getFields() {
/*  88 */     COSBase retval = this.action.getDictionaryObject(COSName.FIELDS);
/*  89 */     return (retval instanceof COSArray) ? (COSArray)retval : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFields(COSArray array) {
/*  97 */     this.action.setItem(COSName.FIELDS, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 107 */     return this.action.getInt(COSName.FLAGS, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int flags) {
/* 115 */     this.action.setInt(COSName.FLAGS, flags);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionSubmitForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */