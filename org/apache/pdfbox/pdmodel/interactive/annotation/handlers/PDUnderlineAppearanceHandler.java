/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationTextMarkup;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDUnderlineAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  34 */   private static final Log LOG = LogFactory.getLog(PDUnderlineAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDUnderlineAppearanceHandler(PDAnnotation annotation) {
/*  38 */     super(annotation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateAppearanceStreams() {
/*  44 */     generateNormalAppearance();
/*  45 */     generateRolloverAppearance();
/*  46 */     generateDownAppearance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNormalAppearance() {
/*  52 */     PDAnnotationTextMarkup annotation = (PDAnnotationTextMarkup)getAnnotation();
/*  53 */     PDRectangle rect = annotation.getRectangle();
/*  54 */     float[] pathsArray = annotation.getQuadPoints();
/*  55 */     if (pathsArray == null) {
/*     */       return;
/*     */     }
/*     */     
/*  59 */     AnnotationBorder ab = AnnotationBorder.getAnnotationBorder((PDAnnotation)annotation, annotation.getBorderStyle());
/*  60 */     PDColor color = annotation.getColor();
/*  61 */     if (color == null || (color.getComponents()).length == 0) {
/*     */       return;
/*     */     }
/*     */     
/*  65 */     if (Float.compare(ab.width, 0.0F) == 0)
/*     */     {
/*     */       
/*  68 */       ab.width = 1.5F;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     float minX = Float.MAX_VALUE;
/*  76 */     float minY = Float.MAX_VALUE;
/*  77 */     float maxX = Float.MIN_VALUE;
/*  78 */     float maxY = Float.MIN_VALUE;
/*  79 */     for (int i = 0; i < pathsArray.length / 2; i++) {
/*     */       
/*  81 */       float x = pathsArray[i * 2];
/*  82 */       float y = pathsArray[i * 2 + 1];
/*  83 */       minX = Math.min(minX, x);
/*  84 */       minY = Math.min(minY, y);
/*  85 */       maxX = Math.max(maxX, x);
/*  86 */       maxY = Math.max(maxY, y);
/*     */     } 
/*  88 */     rect.setLowerLeftX(Math.min(minX - ab.width / 2.0F, rect.getLowerLeftX()));
/*  89 */     rect.setLowerLeftY(Math.min(minY - ab.width / 2.0F, rect.getLowerLeftY()));
/*  90 */     rect.setUpperRightX(Math.max(maxX + ab.width / 2.0F, rect.getUpperRightX()));
/*  91 */     rect.setUpperRightY(Math.max(maxY + ab.width / 2.0F, rect.getUpperRightY()));
/*  92 */     annotation.setRectangle(rect);
/*     */     
/*  94 */     PDAppearanceContentStream cs = null;
/*     */ 
/*     */     
/*     */     try {
/*  98 */       cs = getNormalAppearanceAsContentStream();
/*     */       
/* 100 */       setOpacity(cs, annotation.getConstantOpacity());
/*     */       
/* 102 */       cs.setStrokingColor(color);
/* 103 */       if (ab.dashArray != null)
/*     */       {
/* 105 */         cs.setLineDashPattern(ab.dashArray, 0.0F);
/*     */       }
/* 107 */       cs.setLineWidth(ab.width);
/*     */ 
/*     */ 
/*     */       
/* 111 */       for (int j = 0; j < pathsArray.length / 8; j++) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 116 */         float len0 = (float)Math.sqrt(Math.pow((pathsArray[j * 8] - pathsArray[j * 8 + 4]), 2.0D) + 
/* 117 */             Math.pow((pathsArray[j * 8 + 1] - pathsArray[j * 8 + 5]), 2.0D));
/* 118 */         float x0 = pathsArray[j * 8 + 4];
/* 119 */         float y0 = pathsArray[j * 8 + 5];
/* 120 */         if (Float.compare(len0, 0.0F) != 0) {
/*     */ 
/*     */           
/* 123 */           x0 += (pathsArray[j * 8] - pathsArray[j * 8 + 4]) / len0 * len0 / 7.0F;
/* 124 */           y0 += (pathsArray[j * 8 + 1] - pathsArray[j * 8 + 5]) / len0 * len0 / 7.0F;
/*     */         } 
/* 126 */         float len1 = (float)Math.sqrt(Math.pow((pathsArray[j * 8 + 2] - pathsArray[j * 8 + 6]), 2.0D) + 
/* 127 */             Math.pow((pathsArray[j * 8 + 3] - pathsArray[j * 8 + 7]), 2.0D));
/* 128 */         float x1 = pathsArray[j * 8 + 6];
/* 129 */         float y1 = pathsArray[j * 8 + 7];
/* 130 */         if (Float.compare(len1, 0.0F) != 0) {
/*     */ 
/*     */           
/* 133 */           x1 += (pathsArray[j * 8 + 2] - pathsArray[j * 8 + 6]) / len1 * len1 / 7.0F;
/* 134 */           y1 += (pathsArray[j * 8 + 3] - pathsArray[j * 8 + 7]) / len1 * len1 / 7.0F;
/*     */         } 
/* 136 */         cs.moveTo(x0, y0);
/* 137 */         cs.lineTo(x1, y1);
/*     */       } 
/* 139 */       cs.stroke();
/*     */     }
/* 141 */     catch (IOException ex) {
/*     */       
/* 143 */       LOG.error(ex);
/*     */     }
/*     */     finally {
/*     */       
/* 147 */       IOUtils.closeQuietly((Closeable)cs);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void generateRolloverAppearance() {}
/*     */   
/*     */   public void generateDownAppearance() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDUnderlineAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */