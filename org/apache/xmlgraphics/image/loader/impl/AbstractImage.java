/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.awt.color.ColorSpace;
/*    */ import java.awt.color.ICC_ColorSpace;
/*    */ import java.awt.color.ICC_Profile;
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.image.loader.ImageSize;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractImage
/*    */   implements Image
/*    */ {
/*    */   private ImageInfo info;
/*    */   
/*    */   public AbstractImage(ImageInfo info) {
/* 42 */     this.info = info;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageInfo getInfo() {
/* 47 */     return this.info;
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageSize getSize() {
/* 52 */     return getInfo().getSize();
/*    */   }
/*    */ 
/*    */   
/*    */   public ColorSpace getColorSpace() {
/* 57 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public ICC_Profile getICCProfile() {
/* 62 */     if (getColorSpace() instanceof ICC_ColorSpace) {
/* 63 */       return ((ICC_ColorSpace)getColorSpace()).getProfile();
/*    */     }
/* 65 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     return getClass().getName() + ": " + getInfo();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/AbstractImage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */