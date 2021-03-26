/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDAppearanceContentStream;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationMarkup;
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
/*     */ public class PDInkAppearanceHandler
/*     */   extends PDAbstractAppearanceHandler
/*     */ {
/*  34 */   private static final Log LOG = LogFactory.getLog(PDInkAppearanceHandler.class);
/*     */ 
/*     */   
/*     */   public PDInkAppearanceHandler(PDAnnotation annotation) {
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
/*  52 */     PDAnnotationMarkup ink = (PDAnnotationMarkup)getAnnotation();
/*     */     
/*  54 */     AnnotationBorder ab = AnnotationBorder.getAnnotationBorder((PDAnnotation)ink, ink.getBorderStyle());
/*  55 */     PDColor color = ink.getColor();
/*  56 */     if (color == null || (color.getComponents()).length == 0 || Float.compare(ab.width, 0.0F) == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  61 */     PDAppearanceContentStream cs = null;
/*     */ 
/*     */     
/*     */     try {
/*  65 */       cs = getNormalAppearanceAsContentStream();
/*     */       
/*  67 */       setOpacity(cs, ink.getConstantOpacity());
/*     */       
/*  69 */       cs.setStrokingColor(color);
/*  70 */       if (ab.dashArray != null)
/*     */       {
/*  72 */         cs.setLineDashPattern(ab.dashArray, 0.0F);
/*     */       }
/*  74 */       cs.setLineWidth(ab.width);
/*     */       
/*  76 */       for (float[] pathArray : ink.getInkList())
/*     */       {
/*  78 */         int nPoints = pathArray.length / 2;
/*     */ 
/*     */ 
/*     */         
/*  82 */         for (int i = 0; i < nPoints; i++) {
/*     */           
/*  84 */           float x = pathArray[i * 2];
/*  85 */           float y = pathArray[i * 2 + 1];
/*     */           
/*  87 */           if (i == 0) {
/*     */             
/*  89 */             cs.moveTo(x, y);
/*     */           }
/*     */           else {
/*     */             
/*  93 */             cs.lineTo(x, y);
/*     */           } 
/*     */         } 
/*  96 */         cs.stroke();
/*     */       }
/*     */     
/*  99 */     } catch (IOException ex) {
/*     */       
/* 101 */       LOG.error(ex);
/*     */     }
/*     */     finally {
/*     */       
/* 105 */       IOUtils.closeQuietly((Closeable)cs);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void generateRolloverAppearance() {}
/*     */   
/*     */   public void generateDownAppearance() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDInkAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */