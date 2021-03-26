/*     */ package org.apache.xmlgraphics.image.loader;
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
/*     */ public class ImageFlavor
/*     */ {
/*  33 */   public static final ImageFlavor RENDERED_IMAGE = new ImageFlavor("RenderedImage");
/*     */   
/*  35 */   public static final ImageFlavor BUFFERED_IMAGE = new SimpleRefinedImageFlavor(RENDERED_IMAGE, "BufferedImage");
/*     */ 
/*     */   
/*  38 */   private static final ImageFlavor DOM = new ImageFlavor("DOM");
/*     */   
/*  40 */   public static final ImageFlavor XML_DOM = new MimeEnabledImageFlavor(DOM, "text/xml");
/*     */   
/*  42 */   public static final ImageFlavor RAW = new ImageFlavor("Raw");
/*     */   
/*  44 */   public static final ImageFlavor RAW_PNG = new MimeEnabledImageFlavor(RAW, "image/png");
/*     */ 
/*     */   
/*  47 */   public static final ImageFlavor RAW_JPEG = new MimeEnabledImageFlavor(RAW, "image/jpeg");
/*     */ 
/*     */   
/*  50 */   public static final ImageFlavor RAW_TIFF = new MimeEnabledImageFlavor(RAW, "image/tiff");
/*     */ 
/*     */   
/*  53 */   public static final ImageFlavor RAW_EMF = new MimeEnabledImageFlavor(RAW, "image/x-emf");
/*     */ 
/*     */   
/*  56 */   public static final ImageFlavor RAW_EPS = new MimeEnabledImageFlavor(RAW, "application/postscript");
/*     */ 
/*     */   
/*  59 */   public static final ImageFlavor RAW_LZW = new ImageFlavor("RawLZW");
/*     */   
/*  61 */   public static final ImageFlavor RAW_CCITTFAX = new ImageFlavor("RawCCITTFax");
/*     */   
/*  63 */   public static final ImageFlavor GRAPHICS2D = new ImageFlavor("Graphics2DImage");
/*     */ 
/*     */ 
/*     */   
/*     */   private String name;
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageFlavor(String name) {
/*  72 */     this.name = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  80 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMimeType() {
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespace() {
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCompatible(ImageFlavor flavor) {
/* 109 */     return equals(flavor);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 114 */     int prime = 31;
/* 115 */     int result = 1;
/* 116 */     result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
/* 117 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 122 */     if (this == obj) {
/* 123 */       return true;
/*     */     }
/* 125 */     if (obj == null) {
/* 126 */       return false;
/*     */     }
/* 128 */     if (getClass() != obj.getClass()) {
/* 129 */       return false;
/*     */     }
/* 131 */     ImageFlavor other = (ImageFlavor)obj;
/* 132 */     if (this.name == null) {
/* 133 */       if (other.name != null) {
/* 134 */         return false;
/*     */       }
/* 136 */     } else if (!this.name.equals(other.name)) {
/* 137 */       return false;
/*     */     } 
/* 139 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 144 */     return getName();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/ImageFlavor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */