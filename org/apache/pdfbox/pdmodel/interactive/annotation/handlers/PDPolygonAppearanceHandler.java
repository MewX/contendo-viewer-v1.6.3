/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderEffectDictionary;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
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
/*     */ public class PDPolygonAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  42 */   private static final Log LOG = LogFactory.getLog(PDPolygonAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDPolygonAppearanceHandler(PDAnnotation annotation) {
/*  46 */     super(annotation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateAppearanceStreams() {
/*  52 */     generateNormalAppearance();
/*  53 */     generateRolloverAppearance();
/*  54 */     generateDownAppearance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNormalAppearance() {
/*  60 */     PDAnnotationMarkup annotation = (PDAnnotationMarkup)getAnnotation();
/*  61 */     float lineWidth = getLineWidth();
/*  62 */     PDRectangle rect = annotation.getRectangle();
/*     */ 
/*     */ 
/*     */     
/*  66 */     float minX = Float.MAX_VALUE;
/*  67 */     float minY = Float.MAX_VALUE;
/*  68 */     float maxX = Float.MIN_VALUE;
/*  69 */     float maxY = Float.MIN_VALUE;
/*     */     
/*  71 */     float[][] pathArray = getPathArray(annotation);
/*  72 */     if (pathArray == null) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     for (int i = 0; i < pathArray.length; i++) {
/*     */       
/*  78 */       for (int j = 0; j < (pathArray[i]).length / 2; j++) {
/*     */         
/*  80 */         float x = pathArray[i][j * 2];
/*  81 */         float y = pathArray[i][j * 2 + 1];
/*  82 */         minX = Math.min(minX, x);
/*  83 */         minY = Math.min(minY, y);
/*  84 */         maxX = Math.max(maxX, x);
/*  85 */         maxY = Math.max(maxY, y);
/*     */       } 
/*     */     } 
/*     */     
/*  89 */     rect.setLowerLeftX(Math.min(minX - lineWidth, rect.getLowerLeftX()));
/*  90 */     rect.setLowerLeftY(Math.min(minY - lineWidth, rect.getLowerLeftY()));
/*  91 */     rect.setUpperRightX(Math.max(maxX + lineWidth, rect.getUpperRightX()));
/*  92 */     rect.setUpperRightY(Math.max(maxY + lineWidth, rect.getUpperRightY()));
/*  93 */     annotation.setRectangle(rect);
/*     */     
/*  95 */     PDAppearanceContentStream contentStream = null;
/*     */ 
/*     */     
/*     */     try {
/*  99 */       contentStream = getNormalAppearanceAsContentStream();
/*     */       
/* 101 */       boolean hasStroke = contentStream.setStrokingColorOnDemand(getColor());
/*     */ 
/*     */       
/* 104 */       boolean hasBackground = contentStream.setNonStrokingColorOnDemand(annotation.getInteriorColor());
/*     */       
/* 106 */       setOpacity(contentStream, annotation.getConstantOpacity());
/*     */       
/* 108 */       contentStream.setBorderLine(lineWidth, annotation.getBorderStyle(), annotation.getBorder());
/*     */       
/* 110 */       PDBorderEffectDictionary borderEffect = annotation.getBorderEffect();
/* 111 */       if (borderEffect != null && borderEffect.getStyle().equals("C")) {
/*     */ 
/*     */         
/* 114 */         CloudyBorder cloudyBorder = new CloudyBorder(contentStream, borderEffect.getIntensity(), lineWidth, getRectangle());
/* 115 */         cloudyBorder.createCloudyPolygon(pathArray);
/* 116 */         annotation.setRectangle(cloudyBorder.getRectangle());
/* 117 */         PDAppearanceStream appearanceStream = annotation.getNormalAppearanceStream();
/* 118 */         appearanceStream.setBBox(cloudyBorder.getBBox());
/* 119 */         appearanceStream.setMatrix(cloudyBorder.getMatrix());
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 126 */         for (int j = 0; j < pathArray.length; j++) {
/*     */           
/* 128 */           float[] pointsArray = pathArray[j];
/*     */           
/* 130 */           if (j == 0 && pointsArray.length == 2) {
/*     */             
/* 132 */             contentStream.moveTo(pointsArray[0], pointsArray[1]);
/*     */ 
/*     */ 
/*     */           
/*     */           }
/* 137 */           else if (pointsArray.length == 2) {
/*     */             
/* 139 */             contentStream.lineTo(pointsArray[0], pointsArray[1]);
/*     */           }
/* 141 */           else if (pointsArray.length == 6) {
/*     */             
/* 143 */             contentStream.curveTo(pointsArray[0], pointsArray[1], pointsArray[2], pointsArray[3], pointsArray[4], pointsArray[5]);
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 149 */         contentStream.closePath();
/*     */       } 
/* 151 */       contentStream.drawShape(lineWidth, hasStroke, hasBackground);
/*     */     }
/* 153 */     catch (IOException e) {
/*     */       
/* 155 */       LOG.error(e);
/*     */     }
/*     */     finally {
/*     */       
/* 159 */       IOUtils.closeQuietly((Closeable)contentStream);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float[][] getPathArray(PDAnnotationMarkup annotation) {
/* 166 */     float[][] pathArray = annotation.getPath();
/* 167 */     if (pathArray == null) {
/*     */ 
/*     */       
/* 170 */       float[] verticesArray = annotation.getVertices();
/* 171 */       if (verticesArray == null)
/*     */       {
/* 173 */         return (float[][])null;
/*     */       }
/* 175 */       int points = verticesArray.length / 2;
/* 176 */       pathArray = new float[points][2];
/* 177 */       for (int i = 0; i < points; i++) {
/*     */         
/* 179 */         pathArray[i][0] = verticesArray[i * 2];
/* 180 */         pathArray[i][1] = verticesArray[i * 2 + 1];
/*     */       } 
/*     */     } 
/* 183 */     return pathArray;
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
/*     */   public void generateRolloverAppearance() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateDownAppearance() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float getLineWidth() {
/* 215 */     PDAnnotationMarkup annotation = (PDAnnotationMarkup)getAnnotation();
/*     */     
/* 217 */     PDBorderStyleDictionary bs = annotation.getBorderStyle();
/*     */     
/* 219 */     if (bs != null)
/*     */     {
/* 221 */       return bs.getWidth();
/*     */     }
/*     */     
/* 224 */     COSArray borderCharacteristics = annotation.getBorder();
/* 225 */     if (borderCharacteristics.size() >= 3) {
/*     */       
/* 227 */       COSBase base = borderCharacteristics.getObject(2);
/* 228 */       if (base instanceof COSNumber)
/*     */       {
/* 230 */         return ((COSNumber)base).floatValue();
/*     */       }
/*     */     } 
/*     */     
/* 234 */     return 1.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDPolygonAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */