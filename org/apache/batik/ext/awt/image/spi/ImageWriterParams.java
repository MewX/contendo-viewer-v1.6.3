/*    */ package org.apache.batik.ext.awt.image.spi;
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
/*    */ 
/*    */ 
/*    */ public class ImageWriterParams
/*    */ {
/*    */   private Integer resolution;
/*    */   private Float jpegQuality;
/*    */   private Boolean jpegForceBaseline;
/*    */   private String compressionMethod;
/*    */   
/*    */   public Integer getResolution() {
/* 45 */     return this.resolution;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Float getJPEGQuality() {
/* 53 */     return this.jpegQuality;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean getJPEGForceBaseline() {
/* 61 */     return this.jpegForceBaseline;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCompressionMethod() {
/* 66 */     return this.compressionMethod;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setResolution(int dpi) {
/* 74 */     this.resolution = Integer.valueOf(dpi);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setJPEGQuality(float quality, boolean forceBaseline) {
/* 83 */     this.jpegQuality = Float.valueOf(quality);
/* 84 */     this.jpegForceBaseline = forceBaseline ? Boolean.TRUE : Boolean.FALSE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCompressionMethod(String method) {
/* 92 */     this.compressionMethod = method;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/spi/ImageWriterParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */