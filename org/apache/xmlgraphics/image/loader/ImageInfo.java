/*     */ package org.apache.xmlgraphics.image.loader;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class ImageInfo
/*     */ {
/*  37 */   public static final Object ORIGINAL_IMAGE = Image.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public static final Object HAS_MORE_IMAGES = "HAS_MORE_IMAGES";
/*     */ 
/*     */ 
/*     */   
/*     */   private String originalURI;
/*     */ 
/*     */ 
/*     */   
/*     */   private String mimeType;
/*     */ 
/*     */   
/*     */   private ImageSize size;
/*     */ 
/*     */   
/*  60 */   private Map customObjects = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageInfo(String originalURI, String mimeType) {
/*  68 */     this.originalURI = originalURI;
/*  69 */     this.mimeType = mimeType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOriginalURI() {
/*  77 */     return this.originalURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMimeType() {
/*  85 */     return this.mimeType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageSize getSize() {
/*  93 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(ImageSize size) {
/* 101 */     this.size = size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getCustomObjects() {
/* 109 */     return this.customObjects;
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
/*     */   
/*     */   public Image getOriginalImage() {
/* 123 */     return (Image)this.customObjects.get(ORIGINAL_IMAGE);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 128 */     return getOriginalURI() + " (" + getMimeType() + ")";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/ImageInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */