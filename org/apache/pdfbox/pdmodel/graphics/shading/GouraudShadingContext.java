/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRange;
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
/*     */ abstract class GouraudShadingContext
/*     */   extends TriangleBasedShadingContext
/*     */ {
/*  43 */   private static final Log LOG = LogFactory.getLog(GouraudShadingContext.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   private List<ShadedTriangle> triangleList = new ArrayList<ShadedTriangle>();
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
/*     */   protected GouraudShadingContext(PDShading shading, ColorModel colorModel, AffineTransform xform, Matrix matrix) throws IOException {
/*  62 */     super(shading, colorModel, xform, matrix);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Vertex readVertex(ImageInputStream input, long maxSrcCoord, long maxSrcColor, PDRange rangeX, PDRange rangeY, PDRange[] colRangeTab, Matrix matrix, AffineTransform xform) throws IOException {
/*  82 */     float[] colorComponentTab = new float[this.numberOfColorComponents];
/*  83 */     long x = input.readBits(this.bitsPerCoordinate);
/*  84 */     long y = input.readBits(this.bitsPerCoordinate);
/*  85 */     float dstX = interpolate((float)x, maxSrcCoord, rangeX.getMin(), rangeX.getMax());
/*  86 */     float dstY = interpolate((float)y, maxSrcCoord, rangeY.getMin(), rangeY.getMax());
/*  87 */     LOG.debug("coord: " + String.format("[%06X,%06X] -> [%f,%f]", new Object[] { Long.valueOf(x), Long.valueOf(y), Float.valueOf(dstX), Float.valueOf(dstY) }));
/*  88 */     Point2D p = matrix.transformPoint(dstX, dstY);
/*  89 */     xform.transform(p, p);
/*     */     
/*  91 */     for (int n = 0; n < this.numberOfColorComponents; n++) {
/*     */       
/*  93 */       int color = (int)input.readBits(this.bitsPerColorComponent);
/*  94 */       colorComponentTab[n] = interpolate(color, maxSrcColor, colRangeTab[n].getMin(), colRangeTab[n]
/*  95 */           .getMax());
/*  96 */       LOG.debug("color[" + n + "]: " + color + "/" + String.format("%02x", new Object[] { Integer.valueOf(color) }) + "-> color[" + n + "]: " + colorComponentTab[n]);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     int bitOffset = input.getBitOffset();
/* 104 */     if (bitOffset != 0)
/*     */     {
/* 106 */       input.readBits(8 - bitOffset);
/*     */     }
/*     */     
/* 109 */     return new Vertex(p, colorComponentTab);
/*     */   }
/*     */ 
/*     */   
/*     */   final void setTriangleList(List<ShadedTriangle> triangleList) {
/* 114 */     this.triangleList = triangleList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map<Point, Integer> calcPixelTable(Rectangle deviceBounds) throws IOException {
/* 120 */     Map<Point, Integer> map = new HashMap<Point, Integer>();
/* 121 */     calcPixelTable(this.triangleList, map, deviceBounds);
/* 122 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 128 */     this.triangleList = null;
/* 129 */     super.dispose();
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
/*     */ 
/*     */   
/*     */   private float interpolate(float src, long srcMax, float dstMin, float dstMax) {
/* 143 */     return dstMin + src * (dstMax - dstMin) / (float)srcMax;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isDataEmpty() {
/* 149 */     return this.triangleList.isEmpty();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/GouraudShadingContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */