/*     */ package org.apache.pdfbox.pdmodel.common;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
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
/*     */ public class PDObjectStream
/*     */   extends PDStream
/*     */ {
/*     */   public PDObjectStream(COSStream str) {
/*  42 */     super(str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PDObjectStream createStream(PDDocument document) {
/*  53 */     COSStream cosStream = document.getDocument().createCOSStream();
/*  54 */     PDObjectStream strm = new PDObjectStream(cosStream);
/*  55 */     strm.getCOSObject().setItem(COSName.TYPE, (COSBase)COSName.OBJ_STM);
/*  56 */     return strm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/*  66 */     return getCOSObject().getNameAsString(COSName.TYPE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfObjects() {
/*  76 */     return getCOSObject().getInt(COSName.N, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumberOfObjects(int n) {
/*  86 */     getCOSObject().setInt(COSName.N, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFirstByteOffset() {
/*  96 */     return getCOSObject().getInt(COSName.FIRST, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstByteOffset(int n) {
/* 106 */     getCOSObject().setInt(COSName.FIRST, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDObjectStream getExtends() {
/* 117 */     PDObjectStream retval = null;
/* 118 */     COSStream stream = (COSStream)getCOSObject().getDictionaryObject(COSName.EXTENDS);
/* 119 */     if (stream != null)
/*     */     {
/* 121 */       retval = new PDObjectStream(stream);
/*     */     }
/* 123 */     return retval;
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
/*     */   public void setExtends(PDObjectStream stream) {
/* 135 */     getCOSObject().setItem(COSName.EXTENDS, stream);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/PDObjectStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */