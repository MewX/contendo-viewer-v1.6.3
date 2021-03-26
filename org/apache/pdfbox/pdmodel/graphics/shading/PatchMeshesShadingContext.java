/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.MemoryCacheImageInputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSStream;
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
/*     */ abstract class PatchMeshesShadingContext
/*     */   extends TriangleBasedShadingContext
/*     */ {
/*  47 */   private static final Log LOG = LogFactory.getLog(PatchMeshesShadingContext.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   private List<Patch> patchList = new ArrayList<Patch>();
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
/*     */   protected PatchMeshesShadingContext(PDShadingType6 shading, ColorModel colorModel, AffineTransform xform, Matrix matrix, Rectangle deviceBounds, int controlPoints) throws IOException {
/*  69 */     super(shading, colorModel, xform, matrix);
/*  70 */     this.patchList = collectPatches(shading, xform, matrix, controlPoints);
/*  71 */     createPixelTable(deviceBounds);
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
/*     */   final List<Patch> collectPatches(PDShadingType6 shadingType, AffineTransform xform, Matrix matrix, int controlPoints) throws IOException {
/*  88 */     COSDictionary dict = shadingType.getCOSObject();
/*  89 */     if (!(dict instanceof COSStream))
/*     */     {
/*  91 */       return Collections.emptyList();
/*     */     }
/*  93 */     PDRange rangeX = shadingType.getDecodeForParameter(0);
/*  94 */     PDRange rangeY = shadingType.getDecodeForParameter(1);
/*  95 */     if (Float.compare(rangeX.getMin(), rangeX.getMax()) == 0 || 
/*  96 */       Float.compare(rangeY.getMin(), rangeY.getMax()) == 0)
/*     */     {
/*  98 */       return Collections.emptyList();
/*     */     }
/* 100 */     int bitsPerFlag = shadingType.getBitsPerFlag();
/* 101 */     PDRange[] colRange = new PDRange[this.numberOfColorComponents];
/* 102 */     for (int i = 0; i < this.numberOfColorComponents; i++) {
/*     */       
/* 104 */       colRange[i] = shadingType.getDecodeForParameter(2 + i);
/* 105 */       if (colRange[i] == null)
/*     */       {
/* 107 */         throw new IOException("Range missing in shading /Decode entry");
/*     */       }
/*     */     } 
/* 110 */     List<Patch> list = new ArrayList<Patch>();
/* 111 */     long maxSrcCoord = (long)Math.pow(2.0D, this.bitsPerCoordinate) - 1L;
/* 112 */     long maxSrcColor = (long)Math.pow(2.0D, this.bitsPerColorComponent) - 1L;
/* 113 */     COSStream cosStream = (COSStream)dict;
/*     */     
/* 115 */     ImageInputStream mciis = new MemoryCacheImageInputStream((InputStream)cosStream.createInputStream());
/*     */     
/*     */     try {
/* 118 */       Point2D[] implicitEdge = new Point2D[4];
/* 119 */       float[][] implicitCornerColor = new float[2][this.numberOfColorComponents];
/* 120 */       byte flag = 0;
/*     */ 
/*     */       
/*     */       try {
/* 124 */         flag = (byte)(int)(mciis.readBits(bitsPerFlag) & 0x3L);
/*     */       }
/* 126 */       catch (EOFException ex) {
/*     */         
/* 128 */         LOG.error(ex);
/*     */       } 
/*     */       
/* 131 */       boolean eof = false;
/* 132 */       while (!eof) {
/*     */         
/*     */         try
/*     */         {
/* 136 */           boolean isFree = (flag == 0);
/* 137 */           Patch current = readPatch(mciis, isFree, implicitEdge, implicitCornerColor, maxSrcCoord, maxSrcColor, rangeX, rangeY, colRange, matrix, xform, controlPoints);
/*     */           
/* 139 */           if (current == null) {
/*     */             break;
/*     */           }
/*     */           
/* 143 */           list.add(current);
/* 144 */           flag = (byte)(int)(mciis.readBits(bitsPerFlag) & 0x3L);
/* 145 */           switch (flag) {
/*     */             case 0:
/*     */               continue;
/*     */             
/*     */             case 1:
/* 150 */               implicitEdge = current.getFlag1Edge();
/* 151 */               implicitCornerColor = current.getFlag1Color();
/*     */               continue;
/*     */             case 2:
/* 154 */               implicitEdge = current.getFlag2Edge();
/* 155 */               implicitCornerColor = current.getFlag2Color();
/*     */               continue;
/*     */             case 3:
/* 158 */               implicitEdge = current.getFlag3Edge();
/* 159 */               implicitCornerColor = current.getFlag3Color();
/*     */               continue;
/*     */           } 
/* 162 */           LOG.warn("bad flag: " + flag);
/*     */ 
/*     */         
/*     */         }
/* 166 */         catch (EOFException ex)
/*     */         {
/* 168 */           eof = true;
/*     */         }
/*     */       
/*     */       } 
/*     */     } finally {
/*     */       
/* 174 */       mciis.close();
/*     */     } 
/* 176 */     return list;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Patch readPatch(ImageInputStream input, boolean isFree, Point2D[] implicitEdge, float[][] implicitCornerColor, long maxSrcCoord, long maxSrcColor, PDRange rangeX, PDRange rangeY, PDRange[] colRange, Matrix matrix, AffineTransform xform, int controlPoints) throws IOException {
/* 203 */     float[][] color = new float[4][this.numberOfColorComponents];
/* 204 */     Point2D[] points = new Point2D[controlPoints];
/* 205 */     int pStart = 4, cStart = 2;
/* 206 */     if (isFree) {
/*     */       
/* 208 */       pStart = 0;
/* 209 */       cStart = 0;
/*     */     }
/*     */     else {
/*     */       
/* 213 */       points[0] = implicitEdge[0];
/* 214 */       points[1] = implicitEdge[1];
/* 215 */       points[2] = implicitEdge[2];
/* 216 */       points[3] = implicitEdge[3];
/*     */       
/* 218 */       for (int i = 0; i < this.numberOfColorComponents; i++) {
/*     */         
/* 220 */         color[0][i] = implicitCornerColor[0][i];
/* 221 */         color[1][i] = implicitCornerColor[1][i];
/*     */       } 
/*     */     } 
/*     */     
/*     */     try {
/*     */       int i;
/* 227 */       for (i = pStart; i < controlPoints; i++) {
/*     */         
/* 229 */         long x = input.readBits(this.bitsPerCoordinate);
/* 230 */         long y = input.readBits(this.bitsPerCoordinate);
/* 231 */         float px = interpolate((float)x, maxSrcCoord, rangeX.getMin(), rangeX.getMax());
/* 232 */         float py = interpolate((float)y, maxSrcCoord, rangeY.getMin(), rangeY.getMax());
/* 233 */         Point2D p = matrix.transformPoint(px, py);
/* 234 */         xform.transform(p, p);
/* 235 */         points[i] = p;
/*     */       } 
/* 237 */       for (i = cStart; i < 4; i++) {
/*     */         
/* 239 */         for (int j = 0; j < this.numberOfColorComponents; j++)
/*     */         {
/* 241 */           long c = input.readBits(this.bitsPerColorComponent);
/* 242 */           color[i][j] = interpolate((float)c, maxSrcColor, colRange[j].getMin(), colRange[j]
/* 243 */               .getMax());
/*     */         }
/*     */       
/*     */       } 
/* 247 */     } catch (EOFException ex) {
/*     */       
/* 249 */       LOG.debug("EOF");
/* 250 */       return null;
/*     */     } 
/* 252 */     return generatePatch(points, color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract Patch generatePatch(Point2D[] paramArrayOfPoint2D, float[][] paramArrayOffloat);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float interpolate(float x, long maxValue, float rangeMin, float rangeMax) {
/* 271 */     return rangeMin + x / (float)maxValue * (rangeMax - rangeMin);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map<Point, Integer> calcPixelTable(Rectangle deviceBounds) throws IOException {
/* 277 */     Map<Point, Integer> map = new HashMap<Point, Integer>();
/* 278 */     for (Patch it : this.patchList)
/*     */     {
/* 280 */       calcPixelTable(it.listOfTriangles, map, deviceBounds);
/*     */     }
/* 282 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 288 */     this.patchList = null;
/* 289 */     super.dispose();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isDataEmpty() {
/* 295 */     return this.patchList.isEmpty();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/PatchMeshesShadingContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */