/*    */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.PaintContext;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.image.ColorModel;
/*    */ import java.io.IOException;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.pdfbox.util.Matrix;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AxialShadingPaint
/*    */   extends ShadingPaint<PDShadingType2>
/*    */ {
/* 37 */   private static final Log LOG = LogFactory.getLog(AxialShadingPaint.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   AxialShadingPaint(PDShadingType2 shadingType2, Matrix matrix) {
/* 47 */     super(shadingType2, matrix);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTransparency() {
/* 53 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
/*    */     try {
/* 62 */       return new AxialShadingContext(this.shading, cm, xform, this.matrix, deviceBounds);
/*    */     }
/* 64 */     catch (IOException e) {
/*    */       
/* 66 */       LOG.error("An error occurred while painting", e);
/* 67 */       return (new Color(0, 0, 0, 0)).createContext(cm, deviceBounds, userBounds, xform, hints);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/AxialShadingPaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */