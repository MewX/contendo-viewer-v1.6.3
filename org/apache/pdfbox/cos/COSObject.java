/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ public class COSObject
/*     */   extends COSBase
/*     */   implements COSUpdateInfo
/*     */ {
/*     */   private COSBase baseObject;
/*     */   private long objectNumber;
/*     */   private int generationNumber;
/*     */   private boolean needToBeUpdated;
/*     */   
/*     */   public COSObject(COSBase object) throws IOException {
/*  43 */     setObject(object);
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
/*     */   public COSBase getDictionaryObject(COSName key) {
/*  56 */     COSBase retval = null;
/*  57 */     if (this.baseObject instanceof COSDictionary)
/*     */     {
/*  59 */       retval = ((COSDictionary)this.baseObject).getDictionaryObject(key);
/*     */     }
/*  61 */     return retval;
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
/*     */   public COSBase getItem(COSName key) {
/*  73 */     COSBase retval = null;
/*  74 */     if (this.baseObject instanceof COSDictionary)
/*     */     {
/*  76 */       retval = ((COSDictionary)this.baseObject).getItem(key);
/*     */     }
/*  78 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getObject() {
/*  88 */     return this.baseObject;
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
/*     */   public final void setObject(COSBase object) throws IOException {
/* 100 */     this.baseObject = object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 109 */     return "COSObject{" + Long.toString(this.objectNumber) + ", " + Integer.toString(this.generationNumber) + "}";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getObjectNumber() {
/* 118 */     return this.objectNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectNumber(long objectNum) {
/* 127 */     this.objectNumber = objectNum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGenerationNumber() {
/* 136 */     return this.generationNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGenerationNumber(int generationNumberValue) {
/* 145 */     this.generationNumber = generationNumberValue;
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
/*     */   public Object accept(ICOSVisitor visitor) throws IOException {
/* 158 */     return (getObject() != null) ? getObject().accept(visitor) : COSNull.NULL.accept(visitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNeedToBeUpdated() {
/* 169 */     return this.needToBeUpdated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNeedToBeUpdated(boolean flag) {
/* 180 */     this.needToBeUpdated = flag;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */