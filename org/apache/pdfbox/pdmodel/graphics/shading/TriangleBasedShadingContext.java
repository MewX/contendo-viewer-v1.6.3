/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.PaintContext;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ abstract class TriangleBasedShadingContext
/*     */   extends ShadingContext
/*     */   implements PaintContext
/*     */ {
/*  41 */   private static final Log LOG = LogFactory.getLog(TriangleBasedShadingContext.class);
/*     */ 
/*     */ 
/*     */   
/*     */   protected int bitsPerCoordinate;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int bitsPerColorComponent;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int numberOfColorComponents;
/*     */ 
/*     */   
/*     */   private final boolean hasFunction;
/*     */ 
/*     */   
/*     */   private Map<Point, Integer> pixelTable;
/*     */ 
/*     */ 
/*     */   
/*     */   TriangleBasedShadingContext(PDShading shading, ColorModel cm, AffineTransform xform, Matrix matrix) throws IOException {
/*  64 */     super(shading, cm, xform, matrix);
/*  65 */     PDTriangleBasedShadingType triangleBasedShadingType = (PDTriangleBasedShadingType)shading;
/*  66 */     this.hasFunction = (shading.getFunction() != null);
/*  67 */     this.bitsPerCoordinate = triangleBasedShadingType.getBitsPerCoordinate();
/*  68 */     LOG.debug("bitsPerCoordinate: " + (Math.pow(2.0D, this.bitsPerCoordinate) - 1.0D));
/*  69 */     this.bitsPerColorComponent = triangleBasedShadingType.getBitsPerComponent();
/*  70 */     LOG.debug("bitsPerColorComponent: " + this.bitsPerColorComponent);
/*  71 */     this.numberOfColorComponents = this.hasFunction ? 1 : getShadingColorSpace().getNumberOfComponents();
/*  72 */     LOG.debug("numberOfColorComponents: " + this.numberOfColorComponents);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void createPixelTable(Rectangle deviceBounds) throws IOException {
/*  80 */     this.pixelTable = calcPixelTable(deviceBounds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract Map<Point, Integer> calcPixelTable(Rectangle paramRectangle) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void calcPixelTable(List<ShadedTriangle> triangleList, Map<Point, Integer> map, Rectangle deviceBounds) throws IOException {
/*  96 */     for (ShadedTriangle tri : triangleList) {
/*     */       
/*  98 */       int degree = tri.getDeg();
/*  99 */       if (degree == 2) {
/*     */         
/* 101 */         Line line = tri.getLine();
/* 102 */         for (Point p : line.linePoints)
/*     */         {
/* 104 */           map.put(p, Integer.valueOf(evalFunctionAndConvertToRGB(line.calcColor(p))));
/*     */         }
/*     */         
/*     */         continue;
/*     */       } 
/* 109 */       int[] boundary = tri.getBoundary();
/* 110 */       boundary[0] = Math.max(boundary[0], deviceBounds.x);
/* 111 */       boundary[1] = Math.min(boundary[1], deviceBounds.x + deviceBounds.width);
/* 112 */       boundary[2] = Math.max(boundary[2], deviceBounds.y);
/* 113 */       boundary[3] = Math.min(boundary[3], deviceBounds.y + deviceBounds.height);
/* 114 */       for (int x = boundary[0]; x <= boundary[1]; x++) {
/*     */         
/* 116 */         for (int y = boundary[2]; y <= boundary[3]; y++) {
/*     */           
/* 118 */           Point p = new IntPoint(x, y);
/* 119 */           if (tri.contains(p))
/*     */           {
/* 121 */             map.put(p, Integer.valueOf(evalFunctionAndConvertToRGB(tri.calcColor(p))));
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 129 */       Point p0 = new IntPoint((int)Math.round(tri.corner[0].getX()), (int)Math.round(tri.corner[0].getY()));
/*     */       
/* 131 */       Point p1 = new IntPoint((int)Math.round(tri.corner[1].getX()), (int)Math.round(tri.corner[1].getY()));
/*     */       
/* 133 */       Point p2 = new IntPoint((int)Math.round(tri.corner[2].getX()), (int)Math.round(tri.corner[2].getY()));
/* 134 */       Line l1 = new Line(p0, p1, tri.color[0], tri.color[1]);
/* 135 */       Line l2 = new Line(p1, p2, tri.color[1], tri.color[2]);
/* 136 */       Line l3 = new Line(p2, p0, tri.color[2], tri.color[0]);
/* 137 */       for (Point p : l1.linePoints)
/*     */       {
/* 139 */         map.put(p, Integer.valueOf(evalFunctionAndConvertToRGB(l1.calcColor(p))));
/*     */       }
/* 141 */       for (Point p : l2.linePoints)
/*     */       {
/* 143 */         map.put(p, Integer.valueOf(evalFunctionAndConvertToRGB(l2.calcColor(p))));
/*     */       }
/* 145 */       for (Point p : l3.linePoints)
/*     */       {
/* 147 */         map.put(p, Integer.valueOf(evalFunctionAndConvertToRGB(l3.calcColor(p))));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int evalFunctionAndConvertToRGB(float[] values) throws IOException {
/* 159 */     if (this.hasFunction)
/*     */     {
/* 161 */       values = getShading().evalFunction(values);
/*     */     }
/* 163 */     return convertToRGB(values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean isDataEmpty();
/*     */ 
/*     */ 
/*     */   
/*     */   public final ColorModel getColorModel() {
/* 174 */     return super.getColorModel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 180 */     super.dispose();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Raster getRaster(int x, int y, int w, int h) {
/* 186 */     WritableRaster raster = getColorModel().createCompatibleWritableRaster(w, h);
/* 187 */     int[] data = new int[w * h * 4];
/* 188 */     if (!isDataEmpty() || getBackground() != null)
/*     */     {
/* 190 */       for (int row = 0; row < h; row++) {
/*     */         
/* 192 */         for (int col = 0; col < w; col++) {
/*     */           int value;
/* 194 */           Point p = new IntPoint(x + col, y + row);
/*     */           
/* 196 */           Integer v = this.pixelTable.get(p);
/* 197 */           if (v != null) {
/*     */             
/* 199 */             value = v.intValue();
/*     */           }
/*     */           else {
/*     */             
/* 203 */             if (getBackground() == null) {
/*     */               continue;
/*     */             }
/*     */             
/* 207 */             value = getRgbBackground();
/*     */           } 
/* 209 */           int index = (row * w + col) * 4;
/* 210 */           data[index] = value & 0xFF;
/* 211 */           value >>= 8;
/* 212 */           data[index + 1] = value & 0xFF;
/* 213 */           value >>= 8;
/* 214 */           data[index + 2] = value & 0xFF;
/* 215 */           data[index + 3] = 255;
/*     */           continue;
/*     */         } 
/*     */       }  } 
/* 219 */     raster.setPixels(0, 0, w, h, data);
/* 220 */     return raster;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/TriangleBasedShadingContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */