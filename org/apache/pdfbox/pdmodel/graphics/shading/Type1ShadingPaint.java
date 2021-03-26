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
/*    */ class Type1ShadingPaint
/*    */   extends ShadingPaint<PDShadingType1>
/*    */ {
/* 36 */   private static final Log LOG = LogFactory.getLog(Type1ShadingPaint.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   Type1ShadingPaint(PDShadingType1 shading, Matrix matrix) {
/* 46 */     super(shading, matrix);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTransparency() {
/* 52 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
/*    */     try {
/* 61 */       return new Type1ShadingContext(this.shading, cm, xform, this.matrix);
/*    */     }
/* 63 */     catch (IOException e) {
/*    */       
/* 65 */       LOG.error("An error occurred while painting", e);
/* 66 */       return (new Color(0, 0, 0, 0)).createContext(cm, deviceBounds, userBounds, xform, hints);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/Type1ShadingPaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */