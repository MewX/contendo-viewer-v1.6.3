/*     */ package org.apache.xmlgraphics.ps;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.io.IOException;
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
/*     */ public class ImageFormGenerator
/*     */   extends FormGenerator
/*     */ {
/*     */   private RenderedImage image;
/*     */   private ImageEncoder encoder;
/*     */   private ColorSpace colorSpace;
/*  40 */   private int bitsPerComponent = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean invertImage;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Dimension pixelDimensions;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageFormGenerator(String formName, String title, Dimension2D dimensions, RenderedImage image, boolean invertImage) {
/*  56 */     super(formName, title, dimensions);
/*  57 */     this.image = image;
/*  58 */     this.encoder = ImageEncodingHelper.createRenderedImageEncoder(image);
/*  59 */     this.invertImage = invertImage;
/*  60 */     this.pixelDimensions = new Dimension(image.getWidth(), image.getHeight());
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageFormGenerator(String formName, String title, Dimension2D dimensions, Dimension dimensionsPx, ImageEncoder encoder, ColorSpace colorSpace, int bitsPerComponent, boolean invertImage) {
/*  78 */     super(formName, title, dimensions);
/*  79 */     this.pixelDimensions = dimensionsPx;
/*  80 */     this.encoder = encoder;
/*  81 */     this.colorSpace = colorSpace;
/*  82 */     this.bitsPerComponent = bitsPerComponent;
/*  83 */     this.invertImage = invertImage;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageFormGenerator(String formName, String title, Dimension2D dimensions, Dimension dimensionsPx, ImageEncoder encoder, ColorSpace colorSpace, boolean invertImage) {
/* 100 */     this(formName, title, dimensions, dimensionsPx, encoder, colorSpace, 8, invertImage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDataName() {
/* 108 */     return getFormName() + ":Data";
/*     */   }
/*     */   
/*     */   private String getAdditionalFilters(PSGenerator gen) {
/* 112 */     String implicitFilter = this.encoder.getImplicitFilter();
/* 113 */     if (implicitFilter != null) {
/* 114 */       return "/ASCII85Decode filter " + implicitFilter + " filter";
/*     */     }
/* 116 */     if (gen.getPSLevel() >= 3) {
/* 117 */       return "/ASCII85Decode filter /FlateDecode filter";
/*     */     }
/* 119 */     return "/ASCII85Decode filter /RunLengthDecode filter";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generatePaintProc(PSGenerator gen) throws IOException {
/*     */     String dataSource;
/* 126 */     if (gen.getPSLevel() == 2) {
/* 127 */       gen.writeln("    userdict /i 0 put");
/*     */     } else {
/* 129 */       gen.writeln("    " + getDataName() + " 0 setfileposition");
/*     */     } 
/*     */     
/* 132 */     if (gen.getPSLevel() == 2) {
/* 133 */       dataSource = "{ " + getDataName() + " i get /i i 1 add store } bind";
/*     */     } else {
/* 135 */       dataSource = getDataName();
/*     */     } 
/* 137 */     AffineTransform at = new AffineTransform();
/* 138 */     at.scale(getDimensions().getWidth(), getDimensions().getHeight());
/* 139 */     gen.concatMatrix(at);
/* 140 */     PSDictionary imageDict = new PSDictionary();
/* 141 */     imageDict.put((K)"/DataSource", (V)dataSource);
/* 142 */     if (this.image != null) {
/* 143 */       PSImageUtils.writeImageCommand(this.image, imageDict, gen);
/*     */     } else {
/* 145 */       imageDict.put((K)"/BitsPerComponent", (V)Integer.toString(this.bitsPerComponent));
/* 146 */       PSImageUtils.writeImageCommand(imageDict, this.pixelDimensions, this.colorSpace, this.invertImage, gen);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateAdditionalDataStream(PSGenerator gen) throws IOException {
/* 154 */     gen.writeln("/" + getDataName() + " currentfile");
/* 155 */     gen.writeln(getAdditionalFilters(gen));
/* 156 */     if (gen.getPSLevel() == 2) {
/*     */       
/* 158 */       gen.writeln("{ /temp exch def [ { temp 16384 string readstring not {exit } if } loop ] } exec");
/*     */     } else {
/*     */       
/* 161 */       gen.writeln("/ReusableStreamDecode filter");
/*     */     } 
/* 163 */     PSImageUtils.compressAndWriteBitmap(this.encoder, gen);
/* 164 */     gen.writeln("def");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/ImageFormGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */