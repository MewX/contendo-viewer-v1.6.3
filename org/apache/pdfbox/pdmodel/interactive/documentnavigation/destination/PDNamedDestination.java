/*     */ package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
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
/*     */ public class PDNamedDestination
/*     */   extends PDDestination
/*     */ {
/*     */   private COSBase namedDestination;
/*     */   
/*     */   public PDNamedDestination(COSString dest) {
/*  41 */     this.namedDestination = (COSBase)dest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNamedDestination(COSName dest) {
/*  51 */     this.namedDestination = (COSBase)dest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNamedDestination() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNamedDestination(String dest) {
/*  69 */     this.namedDestination = (COSBase)new COSString(dest);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/*  79 */     return this.namedDestination;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamedDestination() {
/*  89 */     String retval = null;
/*  90 */     if (this.namedDestination instanceof COSString) {
/*     */       
/*  92 */       retval = ((COSString)this.namedDestination).getString();
/*     */     }
/*  94 */     else if (this.namedDestination instanceof COSName) {
/*     */       
/*  96 */       retval = ((COSName)this.namedDestination).getName();
/*     */     } 
/*     */     
/*  99 */     return retval;
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
/*     */   public void setNamedDestination(String dest) throws IOException {
/* 111 */     if (dest == null) {
/*     */       
/* 113 */       this.namedDestination = null;
/*     */     }
/*     */     else {
/*     */       
/* 117 */       this.namedDestination = (COSBase)new COSString(dest);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/destination/PDNamedDestination.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */