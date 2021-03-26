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
/*     */ public class PDStrikeoutAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  34 */   private static final Log LOG = LogFactory.getLog(PDStrikeoutAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDStrikeoutAppearanceHandler(PDAnnotation annotation) {
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
/*  74 */     float minX = Float.MAX_VALUE;
/*  75 */     float minY = Float.MAX_VALUE;
/*  76 */     float maxX = Float.MIN_VALUE;
/*  77 */     float maxY = Float.MIN_VALUE;
/*  78 */     for (int i = 0; i < pathsArray.length / 2; i++) {
/*     */       
/*  80 */       float x = pathsArray[i * 2];
/*  81 */       float y = pathsArray[i * 2 + 1];
/*  82 */       minX = Math.min(minX, x);
/*  83 */       minY = Math.min(minY, y);
/*  84 */       maxX = Math.max(maxX, x);
/*  85 */       maxY = Math.max(maxY, y);
/*     */     } 
/*  87 */     rect.setLowerLeftX(Math.min(minX - ab.width / 2.0F, rect.getLowerLeftX()));
/*  88 */     rect.setLowerLeftY(Math.min(minY - ab.width / 2.0F, rect.getLowerLeftY()));
/*  89 */     rect.setUpperRightX(Math.max(maxX + ab.width / 2.0F, rect.getUpperRightX()));
/*  90 */     rect.setUpperRightY(Math.max(maxY + ab.width / 2.0F, rect.getUpperRightY()));
/*  91 */     annotation.setRectangle(rect);
/*     */     
/*  93 */     PDAppearanceContentStream cs = null;
/*     */ 
/*     */     
/*     */     try {
/*  97 */       cs = getNormalAppearanceAsContentStream();
/*     */       
/*  99 */       setOpacity(cs, annotation.getConstantOpacity());
/*     */       
/* 101 */       cs.setStrokingColor(color);
/* 102 */       if (ab.dashArray != null)
/*     */       {
/* 104 */         cs.setLineDashPattern(ab.dashArray, 0.0F);
/*     */       }
/* 106 */       cs.setLineWidth(ab.width);
/*     */ 
/*     */ 
/*     */       
/* 110 */       for (int j = 0; j < pathsArray.length / 8; j++) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 117 */         float len0 = (float)Math.sqrt(Math.pow((pathsArray[j * 8] - pathsArray[j * 8 + 4]), 2.0D) + 
/* 118 */             Math.pow((pathsArray[j * 8 + 1] - pathsArray[j * 8 + 5]), 2.0D));
/* 119 */         float x0 = pathsArray[j * 8 + 4];
/* 120 */         float y0 = pathsArray[j * 8 + 5];
/* 121 */         if (Float.compare(len0, 0.0F) != 0) {
/*     */ 
/*     */           
/* 124 */           x0 += (pathsArray[j * 8] - pathsArray[j * 8 + 4]) / len0 * (len0 / 2.0F - ab.width);
/* 125 */           y0 += (pathsArray[j * 8 + 1] - pathsArray[j * 8 + 5]) / len0 * (len0 / 2.0F - ab.width);
/*     */         } 
/* 127 */         float len1 = (float)Math.sqrt(Math.pow((pathsArray[j * 8 + 2] - pathsArray[j * 8 + 6]), 2.0D) + 
/* 128 */             Math.pow((pathsArray[j * 8 + 3] - pathsArray[j * 8 + 7]), 2.0D));
/* 129 */         float x1 = pathsArray[j * 8 + 6];
/* 130 */         float y1 = pathsArray[j * 8 + 7];
/* 131 */         if (Float.compare(len1, 0.0F) != 0) {
/*     */ 
/*     */           
/* 134 */           x1 += (pathsArray[j * 8 + 2] - pathsArray[j * 8 + 6]) / len1 * (len1 / 2.0F - ab.width);
/* 135 */           y1 += (pathsArray[j * 8 + 3] - pathsArray[j * 8 + 7]) / len1 * (len1 / 2.0F - ab.width);
/*     */         } 
/* 137 */         cs.moveTo(x0, y0);
/* 138 */         cs.lineTo(x1, y1);
/*     */       } 
/* 140 */       cs.stroke();
/*     */     }
/* 142 */     catch (IOException ex) {
/*     */       
/* 144 */       LOG.error(ex);
/*     */     }
/*     */     finally {
/*     */       
/* 148 */       IOUtils.closeQuietly((Closeable)cs);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void generateRolloverAppearance() {}
/*     */   
/*     */   public void generateDownAppearance() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDStrikeoutAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */