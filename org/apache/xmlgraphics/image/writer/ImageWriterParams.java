/*     */ package org.apache.xmlgraphics.image.writer;
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
/*     */ public class ImageWriterParams
/*     */ {
/*     */   public static final int SINGLE_STRIP = -1;
/*     */   public static final int ONE_ROW_PER_STRIP = 1;
/*     */   private Integer xResolution;
/*     */   private Integer yResolution;
/*     */   private Float jpegQuality;
/*     */   private Boolean jpegForceBaseline;
/*     */   private String compressionMethod;
/*  41 */   private ResolutionUnit resolutionUnit = ResolutionUnit.INCH;
/*  42 */   private int rowsPerStrip = 1;
/*  43 */   private Endianness endianness = Endianness.DEFAULT;
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
/*     */   public boolean hasResolution() {
/*  56 */     return (getXResolution() != null && getYResolution() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getResolution() {
/*  63 */     return getXResolution();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float getJPEGQuality() {
/*  71 */     return this.jpegQuality;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean getJPEGForceBaseline() {
/*  79 */     return this.jpegForceBaseline;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCompressionMethod() {
/*  84 */     return this.compressionMethod;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolution(int resolution) {
/*  93 */     setXResolution(resolution);
/*  94 */     setYResolution(resolution);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJPEGQuality(float quality, boolean forceBaseline) {
/* 103 */     this.jpegQuality = Float.valueOf(quality);
/* 104 */     this.jpegForceBaseline = forceBaseline ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompressionMethod(String method) {
/* 112 */     this.compressionMethod = method;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSingleStrip() {
/* 120 */     return (this.rowsPerStrip == -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSingleStrip(boolean isSingle) {
/* 129 */     this.rowsPerStrip = isSingle ? -1 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRowsPerStrip(int rowsPerStrip) {
/* 139 */     this.rowsPerStrip = rowsPerStrip;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRowsPerStrip() {
/* 149 */     return this.rowsPerStrip;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResolutionUnit getResolutionUnit() {
/* 157 */     return this.resolutionUnit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolutionUnit(ResolutionUnit resolutionUnit) {
/* 165 */     this.resolutionUnit = resolutionUnit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getXResolution() {
/* 172 */     return this.xResolution;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXResolution(int resolution) {
/* 180 */     this.xResolution = Integer.valueOf(resolution);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getYResolution() {
/* 187 */     return this.yResolution;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYResolution(int resolution) {
/* 195 */     this.yResolution = Integer.valueOf(resolution);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Endianness getEndianness() {
/* 203 */     return this.endianness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndianness(Endianness endianness) {
/* 211 */     this.endianness = endianness;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/ImageWriterParams.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */