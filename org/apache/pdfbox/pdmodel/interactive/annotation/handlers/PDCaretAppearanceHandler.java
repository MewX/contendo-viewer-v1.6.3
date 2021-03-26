/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
/*     */ import org.apache.pdfbox.util.Matrix;
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
/*     */ public class PDCaretAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  36 */   private static final Log LOG = LogFactory.getLog(PDCaretAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDCaretAppearanceHandler(PDAnnotation annotation) {
/*  40 */     super(annotation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateAppearanceStreams() {
/*  46 */     generateNormalAppearance();
/*  47 */     generateRolloverAppearance();
/*  48 */     generateDownAppearance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNormalAppearance() {
/*  54 */     PDAnnotationMarkup annotation = (PDAnnotationMarkup)getAnnotation();
/*  55 */     PDAppearanceContentStream contentStream = null;
/*     */ 
/*     */     
/*     */     try {
/*  59 */       contentStream = getNormalAppearanceAsContentStream();
/*     */       
/*  61 */       contentStream.setStrokingColor(getColor());
/*  62 */       contentStream.setNonStrokingColor(getColor());
/*     */       
/*  64 */       setOpacity(contentStream, annotation.getConstantOpacity());
/*     */       
/*  66 */       PDRectangle rect = getRectangle();
/*  67 */       PDRectangle bbox = new PDRectangle(rect.getWidth(), rect.getHeight());
/*  68 */       if (!annotation.getCOSObject().containsKey(COSName.RD)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  76 */         float rd = Math.min(rect.getHeight() / 10.0F, 5.0F);
/*  77 */         annotation.setRectDifferences(rd);
/*  78 */         bbox = new PDRectangle(-rd, -rd, rect.getWidth() + 2.0F * rd, rect.getHeight() + 2.0F * rd);
/*  79 */         Matrix matrix = annotation.getNormalAppearanceStream().getMatrix();
/*  80 */         matrix.transformPoint(rd, rd);
/*  81 */         annotation.getNormalAppearanceStream().setMatrix(matrix.createAffineTransform());
/*     */         
/*  83 */         PDRectangle rect2 = new PDRectangle(rect.getLowerLeftX() - rd, rect.getLowerLeftY() - rd, rect.getWidth() + 2.0F * rd, rect.getHeight() + 2.0F * rd);
/*  84 */         annotation.setRectangle(rect2);
/*     */       } 
/*  86 */       annotation.getNormalAppearanceStream().setBBox(bbox);
/*     */       
/*  88 */       float halfX = rect.getWidth() / 2.0F;
/*  89 */       float halfY = rect.getHeight() / 2.0F;
/*  90 */       contentStream.moveTo(0.0F, 0.0F);
/*  91 */       contentStream.curveTo(halfX, 0.0F, halfX, halfY, halfX, rect
/*     */           
/*  93 */           .getHeight());
/*  94 */       contentStream.curveTo(halfX, halfY, halfX, 0.0F, rect
/*     */           
/*  96 */           .getWidth(), 0.0F);
/*  97 */       contentStream.closePath();
/*  98 */       contentStream.fill();
/*     */ 
/*     */     
/*     */     }
/* 102 */     catch (IOException e) {
/*     */       
/* 104 */       LOG.error(e);
/*     */     }
/*     */     finally {
/*     */       
/* 108 */       IOUtils.closeQuietly((Closeable)contentStream);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void generateRolloverAppearance() {}
/*     */   
/*     */   public void generateDownAppearance() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDCaretAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */