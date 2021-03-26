/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
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
/*     */ public class PDVisibleSignDesigner
/*     */ {
/*     */   private Float imageWidth;
/*     */   private Float imageHeight;
/*     */   private float xAxis;
/*     */   private float yAxis;
/*     */   private float pageHeight;
/*     */   private float pageWidth;
/*     */   private BufferedImage image;
/*  50 */   private String signatureFieldName = "sig";
/*  51 */   private byte[] formatterRectangleParams = new byte[] { 0, 0, 100, 50 };
/*  52 */   private int[] formatterRectangleParameters = new int[] { 0, 0, 100, 50 };
/*  53 */   private AffineTransform affineTransform = new AffineTransform();
/*     */   private float imageSizeInPercents;
/*  55 */   private int rotation = 0;
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
/*     */   public PDVisibleSignDesigner(String filename, InputStream imageStream, int page) throws IOException {
/*  69 */     readImageStream(imageStream);
/*     */ 
/*     */     
/*  72 */     calculatePageSizeFromFile(filename, page);
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
/*     */   public PDVisibleSignDesigner(InputStream documentStream, InputStream imageStream, int page) throws IOException {
/*  87 */     readImageStream(imageStream);
/*     */ 
/*     */     
/*  90 */     calculatePageSizeFromStream(documentStream, page);
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
/*     */   public PDVisibleSignDesigner(PDDocument document, InputStream imageStream, int page) throws IOException {
/* 103 */     readImageStream(imageStream);
/* 104 */     calculatePageSize(document, page);
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
/*     */   public PDVisibleSignDesigner(String filename, BufferedImage image, int page) throws IOException {
/* 119 */     setImage(image);
/*     */ 
/*     */     
/* 122 */     calculatePageSizeFromFile(filename, page);
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
/*     */   public PDVisibleSignDesigner(InputStream documentStream, BufferedImage image, int page) throws IOException {
/* 137 */     setImage(image);
/*     */ 
/*     */     
/* 140 */     calculatePageSizeFromStream(documentStream, page);
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
/*     */   public PDVisibleSignDesigner(PDDocument document, BufferedImage image, int page) {
/* 152 */     setImage(image);
/* 153 */     calculatePageSize(document, page);
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
/*     */   public PDVisibleSignDesigner(InputStream imageStream) throws IOException {
/* 165 */     readImageStream(imageStream);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculatePageSizeFromFile(String filename, int page) throws IOException {
/* 171 */     PDDocument document = PDDocument.load(new File(filename));
/*     */ 
/*     */     
/* 174 */     calculatePageSize(document, page);
/*     */     
/* 176 */     document.close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculatePageSizeFromStream(InputStream documentStream, int page) throws IOException {
/* 182 */     PDDocument document = PDDocument.load(documentStream);
/*     */ 
/*     */     
/* 185 */     calculatePageSize(document, page);
/*     */     
/* 187 */     document.close();
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
/*     */   private void calculatePageSize(PDDocument document, int page) {
/* 200 */     if (page < 1)
/*     */     {
/* 202 */       throw new IllegalArgumentException("First page of pdf is 1, not " + page);
/*     */     }
/*     */     
/* 205 */     PDPage firstPage = document.getPage(page - 1);
/* 206 */     PDRectangle mediaBox = firstPage.getMediaBox();
/* 207 */     pageHeight(mediaBox.getHeight());
/* 208 */     this.pageWidth = mediaBox.getWidth();
/* 209 */     this.imageSizeInPercents = 100.0F;
/* 210 */     this.rotation = firstPage.getRotation() % 360;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner adjustForRotation() {
/*     */     float temp;
/*     */     float newX;
/*     */     float newY;
/* 221 */     switch (this.rotation) {
/*     */ 
/*     */       
/*     */       case 90:
/* 225 */         temp = this.yAxis;
/* 226 */         this.yAxis = this.pageHeight - this.xAxis - this.imageWidth.floatValue();
/* 227 */         this.xAxis = temp;
/*     */         
/* 229 */         this
/* 230 */           .affineTransform = new AffineTransform(0.0F, this.imageHeight.floatValue() / this.imageWidth.floatValue(), -this.imageWidth.floatValue() / this.imageHeight.floatValue(), 0.0F, this.imageWidth.floatValue(), 0.0F);
/*     */         
/* 232 */         temp = this.imageHeight.floatValue();
/* 233 */         this.imageHeight = this.imageWidth;
/* 234 */         this.imageWidth = Float.valueOf(temp);
/*     */         break;
/*     */       
/*     */       case 180:
/* 238 */         newX = this.pageWidth - this.xAxis - this.imageWidth.floatValue();
/* 239 */         newY = this.pageHeight - this.yAxis - this.imageHeight.floatValue();
/* 240 */         this.xAxis = newX;
/* 241 */         this.yAxis = newY;
/*     */         
/* 243 */         this.affineTransform = new AffineTransform(-1.0F, 0.0F, 0.0F, -1.0F, this.imageWidth.floatValue(), this.imageHeight.floatValue());
/*     */         break;
/*     */       
/*     */       case 270:
/* 247 */         temp = this.xAxis;
/* 248 */         this.xAxis = this.pageWidth - this.yAxis - this.imageHeight.floatValue();
/* 249 */         this.yAxis = temp;
/*     */         
/* 251 */         this
/* 252 */           .affineTransform = new AffineTransform(0.0F, -this.imageHeight.floatValue() / this.imageWidth.floatValue(), this.imageWidth.floatValue() / this.imageHeight.floatValue(), 0.0F, 0.0F, this.imageHeight.floatValue());
/*     */         
/* 254 */         temp = this.imageHeight.floatValue();
/* 255 */         this.imageHeight = this.imageWidth;
/* 256 */         this.imageWidth = Float.valueOf(temp);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 263 */     return this;
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
/*     */   public PDVisibleSignDesigner signatureImage(String path) throws IOException {
/* 275 */     InputStream in = null;
/*     */     
/*     */     try {
/* 278 */       in = new BufferedInputStream(new FileInputStream(path));
/* 279 */       readImageStream(in);
/*     */     }
/*     */     finally {
/*     */       
/* 283 */       IOUtils.closeQuietly(in);
/*     */     } 
/* 285 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner zoom(float percent) {
/* 296 */     this.imageHeight = Float.valueOf(this.imageHeight.floatValue() + this.imageHeight.floatValue() * percent / 100.0F);
/* 297 */     this.imageWidth = Float.valueOf(this.imageWidth.floatValue() + this.imageWidth.floatValue() * percent / 100.0F);
/* 298 */     this.formatterRectangleParameters[2] = (int)this.imageWidth.floatValue();
/* 299 */     this.formatterRectangleParameters[3] = (int)this.imageHeight.floatValue();
/* 300 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner coordinates(float x, float y) {
/* 311 */     xAxis(x);
/* 312 */     yAxis(y);
/* 313 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getxAxis() {
/* 322 */     return this.xAxis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner xAxis(float xAxis) {
/* 332 */     this.xAxis = xAxis;
/* 333 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getyAxis() {
/* 342 */     return this.yAxis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner yAxis(float yAxis) {
/* 352 */     this.yAxis = yAxis;
/* 353 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth() {
/* 362 */     return this.imageWidth.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner width(float width) {
/* 372 */     this.imageWidth = Float.valueOf(width);
/* 373 */     this.formatterRectangleParameters[2] = (int)width;
/* 374 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHeight() {
/* 383 */     return this.imageHeight.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner height(float height) {
/* 393 */     this.imageHeight = Float.valueOf(height);
/* 394 */     this.formatterRectangleParameters[3] = (int)height;
/* 395 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getTemplateHeight() {
/* 404 */     return getPageHeight();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PDVisibleSignDesigner pageHeight(float templateHeight) {
/* 414 */     this.pageHeight = templateHeight;
/* 415 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSignatureFieldName() {
/* 424 */     return this.signatureFieldName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner signatureFieldName(String signatureFieldName) {
/* 434 */     this.signatureFieldName = signatureFieldName;
/* 435 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getImage() {
/* 444 */     return this.image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readImageStream(InputStream stream) throws IOException {
/* 455 */     ImageIO.setUseCache(false);
/* 456 */     setImage(ImageIO.read(stream));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setImage(BufferedImage image) {
/* 466 */     this.image = image;
/* 467 */     this.imageHeight = Float.valueOf(image.getHeight());
/* 468 */     this.imageWidth = Float.valueOf(image.getWidth());
/* 469 */     this.formatterRectangleParameters[2] = image.getWidth();
/* 470 */     this.formatterRectangleParameters[3] = image.getHeight();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public byte[] getAffineTransformParams() {
/* 481 */     return new byte[] {
/*     */         
/* 483 */         (byte)(int)this.affineTransform.getScaleX(), 
/* 484 */         (byte)(int)this.affineTransform.getShearY(), 
/* 485 */         (byte)(int)this.affineTransform.getShearX(), 
/* 486 */         (byte)(int)this.affineTransform.getScaleY(), 
/* 487 */         (byte)(int)this.affineTransform.getTranslateX(), 
/* 488 */         (byte)(int)this.affineTransform.getTranslateY()
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getTransform() {
/* 497 */     return this.affineTransform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public PDVisibleSignDesigner affineTransformParams(byte[] affineTransformParams) {
/* 509 */     this.affineTransform = new AffineTransform(affineTransformParams[0], affineTransformParams[1], affineTransformParams[2], affineTransformParams[3], affineTransformParams[4], affineTransformParams[5]);
/*     */ 
/*     */     
/* 512 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner transform(AffineTransform affineTransform) {
/* 522 */     this.affineTransform = new AffineTransform(affineTransform);
/* 523 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public byte[] getFormatterRectangleParams() {
/* 533 */     return this.formatterRectangleParams;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getFormatterRectangleParameters() {
/* 542 */     return this.formatterRectangleParameters;
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
/*     */   @Deprecated
/*     */   public PDVisibleSignDesigner formatterRectangleParams(byte[] formatterRectangleParams) {
/* 555 */     this.formatterRectangleParams = formatterRectangleParams;
/* 556 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner formatterRectangleParameters(int[] formatterRectangleParameters) {
/* 567 */     this.formatterRectangleParameters = formatterRectangleParameters;
/* 568 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPageWidth() {
/* 577 */     return this.pageWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner pageWidth(float pageWidth) {
/* 587 */     this.pageWidth = pageWidth;
/* 588 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPageHeight() {
/* 597 */     return this.pageHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getImageSizeInPercents() {
/* 606 */     return this.imageSizeInPercents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void imageSizeInPercents(float imageSizeInPercents) {
/* 615 */     this.imageSizeInPercents = imageSizeInPercents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSignatureText() {
/* 624 */     throw new UnsupportedOperationException("That method is not yet implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDVisibleSignDesigner signatureText(String signatureText) {
/* 634 */     throw new UnsupportedOperationException("That method is not yet implemented");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/visible/PDVisibleSignDesigner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */