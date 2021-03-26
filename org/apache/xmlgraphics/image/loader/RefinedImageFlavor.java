/*    */ package org.apache.xmlgraphics.image.loader;
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
/*    */ public abstract class RefinedImageFlavor
/*    */   extends ImageFlavor
/*    */ {
/*    */   private ImageFlavor parentFlavor;
/*    */   
/*    */   protected RefinedImageFlavor(ImageFlavor parentFlavor) {
/* 35 */     this(parentFlavor.getName(), parentFlavor);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected RefinedImageFlavor(String name, ImageFlavor parentFlavor) {
/* 44 */     super(name);
/* 45 */     this.parentFlavor = parentFlavor;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageFlavor getParentFlavor() {
/* 53 */     return this.parentFlavor;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMimeType() {
/* 58 */     return this.parentFlavor.getMimeType();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNamespace() {
/* 63 */     return this.parentFlavor.getNamespace();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCompatible(ImageFlavor flavor) {
/* 68 */     return (getParentFlavor().isCompatible(flavor) || super.isCompatible(flavor));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/RefinedImageFlavor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */