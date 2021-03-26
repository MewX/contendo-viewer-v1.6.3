/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*     */ import org.apache.pdfbox.pdmodel.PDFormContentStream;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
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
/*     */ public class PDHighlightAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  43 */   private static final Log LOG = LogFactory.getLog(PDHighlightAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDHighlightAppearanceHandler(PDAnnotation annotation) {
/*  47 */     super(annotation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateAppearanceStreams() {
/*  53 */     generateNormalAppearance();
/*  54 */     generateRolloverAppearance();
/*  55 */     generateDownAppearance();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNormalAppearance() {
/*  61 */     PDAnnotationTextMarkup annotation = (PDAnnotationTextMarkup)getAnnotation();
/*  62 */     PDRectangle rect = annotation.getRectangle();
/*  63 */     float[] pathsArray = annotation.getQuadPoints();
/*  64 */     if (pathsArray == null) {
/*     */       return;
/*     */     }
/*     */     
/*  68 */     AnnotationBorder ab = AnnotationBorder.getAnnotationBorder((PDAnnotation)annotation, annotation.getBorderStyle());
/*  69 */     PDColor color = annotation.getColor();
/*  70 */     if (color == null || (color.getComponents()).length == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     float minX = Float.MAX_VALUE;
/*  80 */     float minY = Float.MAX_VALUE;
/*  81 */     float maxX = Float.MIN_VALUE;
/*  82 */     float maxY = Float.MIN_VALUE;
/*  83 */     for (int i = 0; i < pathsArray.length / 2; i++) {
/*     */       
/*  85 */       float x = pathsArray[i * 2];
/*  86 */       float y = pathsArray[i * 2 + 1];
/*  87 */       minX = Math.min(minX, x);
/*  88 */       minY = Math.min(minY, y);
/*  89 */       maxX = Math.max(maxX, x);
/*  90 */       maxY = Math.max(maxY, y);
/*     */     } 
/*     */ 
/*     */     
/*  94 */     float maxDelta = 0.0F;
/*  95 */     for (int j = 0; j < pathsArray.length / 8; j++) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 100 */       float delta = Math.max((pathsArray[j + 0] - pathsArray[j + 4]) / 4.0F, (pathsArray[j + 1] - pathsArray[j + 5]) / 4.0F);
/*     */       
/* 102 */       maxDelta = Math.max(delta, maxDelta);
/*     */     } 
/*     */     
/* 105 */     rect.setLowerLeftX(Math.min(minX - ab.width / 2.0F - maxDelta, rect.getLowerLeftX()));
/* 106 */     rect.setLowerLeftY(Math.min(minY - ab.width / 2.0F - maxDelta, rect.getLowerLeftY()));
/* 107 */     rect.setUpperRightX(Math.max(maxX + ab.width + maxDelta, rect.getUpperRightX()));
/* 108 */     rect.setUpperRightY(Math.max(maxY + ab.width + maxDelta, rect.getUpperRightY()));
/* 109 */     annotation.setRectangle(rect);
/*     */     
/* 111 */     PDAppearanceContentStream cs = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 116 */       cs = getNormalAppearanceAsContentStream();
/*     */       
/* 118 */       PDExtendedGraphicsState r0 = new PDExtendedGraphicsState();
/* 119 */       PDExtendedGraphicsState r1 = new PDExtendedGraphicsState();
/* 120 */       r0.setAlphaSourceFlag(false);
/* 121 */       r0.setStrokingAlphaConstant(Float.valueOf(annotation.getConstantOpacity()));
/* 122 */       r0.setNonStrokingAlphaConstant(Float.valueOf(annotation.getConstantOpacity()));
/* 123 */       r1.setAlphaSourceFlag(false);
/* 124 */       r1.setBlendMode((BlendMode)BlendMode.MULTIPLY);
/* 125 */       cs.setGraphicsStateParameters(r0);
/* 126 */       cs.setGraphicsStateParameters(r1);
/*     */ 
/*     */       
/* 129 */       PDFormXObject frm1 = new PDFormXObject(new COSStream());
/* 130 */       PDFormXObject frm2 = new PDFormXObject(new COSStream());
/* 131 */       frm1.setResources(new PDResources());
/*     */       
/* 133 */       PDFormContentStream mwfofrmCS = null;
/*     */       
/*     */       try {
/* 136 */         mwfofrmCS = new PDFormContentStream(frm1);
/* 137 */         mwfofrmCS.drawForm(frm2);
/*     */       }
/*     */       finally {
/*     */         
/* 141 */         IOUtils.closeQuietly((Closeable)mwfofrmCS);
/*     */       } 
/* 143 */       frm1.setBBox(annotation.getRectangle());
/* 144 */       COSDictionary groupDict = new COSDictionary();
/* 145 */       groupDict.setItem(COSName.S, (COSBase)COSName.TRANSPARENCY);
/*     */       
/* 147 */       frm1.getCOSObject().setItem(COSName.GROUP, (COSBase)groupDict);
/* 148 */       cs.drawForm(frm1);
/* 149 */       frm2.setBBox(annotation.getRectangle());
/*     */       
/* 151 */       PDFormContentStream frm2CS = null;
/*     */ 
/*     */       
/*     */       try {
/* 155 */         frm2CS = new PDFormContentStream(frm2);
/* 156 */         frm2CS.setNonStrokingColor(color);
/* 157 */         int of = 0;
/* 158 */         while (of + 7 < pathsArray.length)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 166 */           float delta = 0.0F;
/* 167 */           if (Float.compare(pathsArray[of + 0], pathsArray[of + 4]) == 0 && 
/* 168 */             Float.compare(pathsArray[of + 1], pathsArray[of + 3]) == 0 && 
/* 169 */             Float.compare(pathsArray[of + 2], pathsArray[of + 6]) == 0 && 
/* 170 */             Float.compare(pathsArray[of + 5], pathsArray[of + 7]) == 0) {
/*     */ 
/*     */             
/* 173 */             delta = (pathsArray[of + 1] - pathsArray[of + 5]) / 4.0F;
/*     */           }
/* 175 */           else if (Float.compare(pathsArray[of + 1], pathsArray[of + 5]) == 0 && 
/* 176 */             Float.compare(pathsArray[of + 0], pathsArray[of + 2]) == 0 && 
/* 177 */             Float.compare(pathsArray[of + 3], pathsArray[of + 7]) == 0 && 
/* 178 */             Float.compare(pathsArray[of + 4], pathsArray[of + 6]) == 0) {
/*     */ 
/*     */             
/* 181 */             delta = (pathsArray[of + 0] - pathsArray[of + 4]) / 4.0F;
/*     */           } 
/*     */           
/* 184 */           frm2CS.moveTo(pathsArray[of + 4], pathsArray[of + 5]);
/*     */           
/* 186 */           if (Float.compare(pathsArray[of + 0], pathsArray[of + 4]) == 0) {
/*     */ 
/*     */             
/* 189 */             frm2CS.curveTo(pathsArray[of + 4] - delta, pathsArray[of + 5] + delta, pathsArray[of + 0] - delta, pathsArray[of + 1] - delta, pathsArray[of + 0], pathsArray[of + 1]);
/*     */ 
/*     */           
/*     */           }
/* 193 */           else if (Float.compare(pathsArray[of + 5], pathsArray[of + 1]) == 0) {
/*     */ 
/*     */             
/* 196 */             frm2CS.curveTo(pathsArray[of + 4] + delta, pathsArray[of + 5] + delta, pathsArray[of + 0] - delta, pathsArray[of + 1] + delta, pathsArray[of + 0], pathsArray[of + 1]);
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */             
/* 202 */             frm2CS.lineTo(pathsArray[of + 0], pathsArray[of + 1]);
/*     */           } 
/* 204 */           frm2CS.lineTo(pathsArray[of + 2], pathsArray[of + 3]);
/* 205 */           if (Float.compare(pathsArray[of + 2], pathsArray[of + 6]) == 0) {
/*     */ 
/*     */             
/* 208 */             frm2CS.curveTo(pathsArray[of + 2] + delta, pathsArray[of + 3] - delta, pathsArray[of + 6] + delta, pathsArray[of + 7] + delta, pathsArray[of + 6], pathsArray[of + 7]);
/*     */ 
/*     */           
/*     */           }
/* 212 */           else if (Float.compare(pathsArray[of + 3], pathsArray[of + 7]) == 0) {
/*     */ 
/*     */             
/* 215 */             frm2CS.curveTo(pathsArray[of + 2] - delta, pathsArray[of + 3] - delta, pathsArray[of + 6] + delta, pathsArray[of + 7] - delta, pathsArray[of + 6], pathsArray[of + 7]);
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */             
/* 221 */             frm2CS.lineTo(pathsArray[of + 6], pathsArray[of + 7]);
/*     */           } 
/*     */           
/* 224 */           frm2CS.fill();
/* 225 */           of += 8;
/*     */         }
/*     */       
/*     */       } finally {
/*     */         
/* 230 */         IOUtils.closeQuietly((Closeable)frm2CS);
/*     */       }
/*     */     
/* 233 */     } catch (IOException ex) {
/*     */       
/* 235 */       LOG.error(ex);
/*     */     }
/*     */     finally {
/*     */       
/* 239 */       IOUtils.closeQuietly((Closeable)cs);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void generateRolloverAppearance() {}
/*     */   
/*     */   public void generateDownAppearance() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDHighlightAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */