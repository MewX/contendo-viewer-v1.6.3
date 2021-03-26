/*     */ package org.apache.xmlgraphics.image.loader;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.xmlgraphics.image.loader.util.ImageInputStreamAdapter;
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
/*     */ public class ImageSource
/*     */   implements Source
/*     */ {
/*     */   private String systemId;
/*     */   private ImageInputStream iin;
/*     */   private boolean fastSource;
/*     */   
/*     */   public ImageSource(ImageInputStream in, String systemId, boolean fastSource) {
/*  45 */     assert in != null : "InputStream is null";
/*  46 */     this.iin = in;
/*  47 */     this.systemId = systemId;
/*  48 */     this.fastSource = fastSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getInputStream() {
/*  56 */     if (this.iin == null) {
/*  57 */       return null;
/*     */     }
/*  59 */     return (InputStream)new ImageInputStreamAdapter(this.iin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageInputStream getImageInputStream() {
/*  68 */     return this.iin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setImageInputStream(ImageInputStream in) {
/*  76 */     this.iin = in;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSystemId() {
/*  81 */     return this.systemId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSystemId(String systemId) {
/*  86 */     this.systemId = systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFastSource() {
/*  95 */     return this.fastSource;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     return (isFastSource() ? "FAST " : "") + "ImageSource: " + getSystemId() + " " + getImageInputStream();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/ImageSource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */