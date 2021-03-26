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
/*    */ 
/*    */ public class RadialShadingPaint
/*    */   extends ShadingPaint<PDShadingType3>
/*    */ {
/* 38 */   private static final Log LOG = LogFactory.getLog(RadialShadingPaint.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   RadialShadingPaint(PDShadingType3 shading, Matrix matrix) {
/* 48 */     super(shading, matrix);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTransparency() {
/* 54 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
/*    */     try {
/* 63 */       return new RadialShadingContext(this.shading, cm, xform, this.matrix, deviceBounds);
/*    */     }
/* 65 */     catch (IOException e) {
/*    */       
/* 67 */       LOG.error("An error occurred while painting", e);
/* 68 */       return (new Color(0, 0, 0, 0)).createContext(cm, deviceBounds, userBounds, xform, hints);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/RadialShadingPaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */