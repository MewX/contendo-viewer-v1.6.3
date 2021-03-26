/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import com.github.jaiimageio.jpeg2000.J2KImageReadParam;
/*     */ import javax.imageio.ImageReadParam;
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
/*     */ 
/*     */ public class J2KImageReadParamJava
/*     */   extends J2KImageReadParam
/*     */ {
/*     */   private boolean noROIDescaling;
/*     */   private boolean parsingEnabled;
/*     */   
/*     */   public J2KImageReadParamJava() {
/* 103 */     this.noROIDescaling = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     this.parsingEnabled = true; } public J2KImageReadParamJava(ImageReadParam param) { J2KImageReadParam j2kParam; this.noROIDescaling = true; this.parsingEnabled = true;
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
/* 122 */     if (param.hasController()) {
/* 123 */       setController(param.getController());
/*     */     }
/* 125 */     setSourceRegion(param.getSourceRegion());
/* 126 */     setSourceBands(param.getSourceBands());
/* 127 */     setDestinationBands(param.getDestinationBands());
/* 128 */     setDestination(param.getDestination());
/*     */     
/* 130 */     setDestinationOffset(param.getDestinationOffset());
/* 131 */     setSourceSubsampling(param.getSourceXSubsampling(), param
/* 132 */         .getSourceYSubsampling(), param
/* 133 */         .getSubsamplingXOffset(), param
/* 134 */         .getSubsamplingYOffset());
/* 135 */     setDestinationType(param.getDestinationType());
/*     */ 
/*     */ 
/*     */     
/* 139 */     if (param instanceof J2KImageReadParam) {
/* 140 */       j2kParam = (J2KImageReadParam)param;
/*     */     } else {
/* 142 */       j2kParam = new J2KImageReadParam();
/*     */     } 
/* 144 */     setDecodingRate(j2kParam.getDecodingRate());
/* 145 */     setResolution(j2kParam.getResolution()); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoROIDescaling(boolean value) {
/* 150 */     this.noROIDescaling = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getNoROIDescaling() {
/* 155 */     return this.noROIDescaling;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParsingEnabled(boolean value) {
/* 160 */     this.parsingEnabled = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getParsingEnabled() {
/* 165 */     return this.parsingEnabled;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/J2KImageReadParamJava.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */