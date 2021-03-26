/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.color.ColorSpace;
/*    */ import java.awt.color.ICC_ColorSpace;
/*    */ import java.awt.color.ICC_Profile;
/*    */ import java.awt.image.RenderedImage;
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
/*    */ public class ImageRendered
/*    */   extends AbstractImage
/*    */ {
/*    */   private final RenderedImage red;
/*    */   private final Color transparentColor;
/*    */   private final ColorSpace colorSpace;
/*    */   private final ICC_Profile iccProfile;
/*    */   
/*    */   public ImageRendered(ImageInfo info, RenderedImage red, Color transparentColor) {
/* 48 */     super(info);
/* 49 */     this.red = red;
/* 50 */     this.transparentColor = transparentColor;
/* 51 */     this.colorSpace = red.getColorModel().getColorSpace();
/* 52 */     if (this.colorSpace instanceof ICC_ColorSpace) {
/* 53 */       ICC_ColorSpace icccs = (ICC_ColorSpace)this.colorSpace;
/* 54 */       this.iccProfile = icccs.getProfile();
/*    */     } else {
/* 56 */       this.iccProfile = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor getFlavor() {
/* 62 */     return ImageFlavor.RENDERED_IMAGE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCacheable() {
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RenderedImage getRenderedImage() {
/* 75 */     return this.red;
/*    */   }
/*    */ 
/*    */   
/*    */   public ColorSpace getColorSpace() {
/* 80 */     return this.colorSpace;
/*    */   }
/*    */ 
/*    */   
/*    */   public ICC_Profile getICCProfile() {
/* 85 */     return this.iccProfile;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Color getTransparentColor() {
/* 93 */     return this.transparentColor;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageRendered.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */