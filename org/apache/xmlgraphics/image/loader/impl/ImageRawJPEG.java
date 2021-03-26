/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.awt.color.ColorSpace;
/*    */ import java.awt.color.ICC_Profile;
/*    */ import java.io.InputStream;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageRawJPEG
/*    */   extends ImageRawStream
/*    */ {
/*    */   private int sofType;
/*    */   private ColorSpace colorSpace;
/*    */   private ICC_Profile iccProfile;
/*    */   private boolean invertImage;
/*    */   
/*    */   public ImageRawJPEG(ImageInfo info, InputStream in, int sofType, ColorSpace colorSpace, ICC_Profile iccProfile, boolean invertImage) {
/* 50 */     super(info, ImageFlavor.RAW_JPEG, in);
/* 51 */     this.sofType = sofType;
/* 52 */     this.colorSpace = colorSpace;
/* 53 */     this.iccProfile = iccProfile;
/* 54 */     this.invertImage = invertImage;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSOFType() {
/* 62 */     return this.sofType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ICC_Profile getICCProfile() {
/* 70 */     return this.iccProfile;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInverted() {
/* 78 */     return this.invertImage;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ColorSpace getColorSpace() {
/* 86 */     return this.colorSpace;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageRawJPEG.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */