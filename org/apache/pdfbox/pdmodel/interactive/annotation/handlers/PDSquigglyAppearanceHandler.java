/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*     */ import org.apache.pdfbox.pdmodel.PDFormContentStream;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationTextMarkup;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDSquigglyAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  46 */   private static final Log LOG = LogFactory.getLog(PDSquigglyAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDSquigglyAppearanceHandler(PDAnnotation annotation) {
/*  50 */     super(annotation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateAppearanceStreams() {
/*  56 */     generateNormalAppearance();
/*  57 */     generateRolloverAppearance();
/*  58 */     generateDownAppearance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNormalAppearance() {
/*  64 */     PDAnnotationTextMarkup annotation = (PDAnnotationTextMarkup)getAnnotation();
/*  65 */     PDRectangle rect = annotation.getRectangle();
/*  66 */     float[] pathsArray = annotation.getQuadPoints();
/*  67 */     if (pathsArray == null) {
/*     */       return;
/*     */     }
/*     */     
/*  71 */     AnnotationBorder ab = AnnotationBorder.getAnnotationBorder((PDAnnotation)annotation, annotation.getBorderStyle());
/*  72 */     PDColor color = annotation.getColor();
/*  73 */     if (color == null || (color.getComponents()).length == 0) {
/*     */       return;
/*     */     }
/*     */     
/*  77 */     if (Float.compare(ab.width, 0.0F) == 0)
/*     */     {
/*     */       
/*  80 */       ab.width = 1.5F;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     float minX = Float.MAX_VALUE;
/*  88 */     float minY = Float.MAX_VALUE;
/*  89 */     float maxX = Float.MIN_VALUE;
/*  90 */     float maxY = Float.MIN_VALUE;
/*  91 */     for (int i = 0; i < pathsArray.length / 2; i++) {
/*     */       
/*  93 */       float x = pathsArray[i * 2];
/*  94 */       float y = pathsArray[i * 2 + 1];
/*  95 */       minX = Math.min(minX, x);
/*  96 */       minY = Math.min(minY, y);
/*  97 */       maxX = Math.max(maxX, x);
/*  98 */       maxY = Math.max(maxY, y);
/*     */     } 
/* 100 */     rect.setLowerLeftX(Math.min(minX - ab.width / 2.0F, rect.getLowerLeftX()));
/* 101 */     rect.setLowerLeftY(Math.min(minY - ab.width / 2.0F, rect.getLowerLeftY()));
/* 102 */     rect.setUpperRightX(Math.max(maxX + ab.width / 2.0F, rect.getUpperRightX()));
/* 103 */     rect.setUpperRightY(Math.max(maxY + ab.width / 2.0F, rect.getUpperRightY()));
/* 104 */     annotation.setRectangle(rect);
/*     */     
/* 106 */     PDAppearanceContentStream cs = null;
/*     */ 
/*     */     
/*     */     try {
/* 110 */       cs = getNormalAppearanceAsContentStream();
/*     */       
/* 112 */       setOpacity(cs, annotation.getConstantOpacity());
/*     */       
/* 114 */       cs.setStrokingColor(color);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 121 */       for (int j = 0; j < pathsArray.length / 8; j++)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 127 */         float height = pathsArray[j * 8 + 1] - pathsArray[j * 8 + 5];
/* 128 */         cs.transform(new Matrix(height / 40.0F, 0.0F, 0.0F, height / 40.0F / 1.8F, pathsArray[j * 8 + 4], pathsArray[j * 8 + 5]));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 133 */         PDFormXObject form = new PDFormXObject(new COSStream());
/* 134 */         form.setBBox(new PDRectangle(-0.5F, -0.5F, (pathsArray[j * 8 + 2] - pathsArray[j * 8]) / height * 40.0F + 0.5F, 13.0F));
/* 135 */         form.setResources(new PDResources());
/* 136 */         form.setMatrix(AffineTransform.getTranslateInstance(0.5D, 0.5D));
/* 137 */         cs.drawForm(form);
/*     */         
/* 139 */         PDFormContentStream formCS = null;
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
/*     */       }
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
/*     */     }
/* 186 */     catch (IOException ex) {
/*     */       
/* 188 */       LOG.error(ex);
/*     */     }
/*     */     finally {
/*     */       
/* 192 */       IOUtils.closeQuietly((Closeable)cs);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void generateRolloverAppearance() {}
/*     */   
/*     */   public void generateDownAppearance() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDSquigglyAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */