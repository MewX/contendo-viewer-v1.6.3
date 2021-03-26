/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
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
/*     */ 
/*     */ class Type4ShadingContext
/*     */   extends GouraudShadingContext
/*     */ {
/*  45 */   private static final Log LOG = LogFactory.getLog(Type4ShadingContext.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int bitsPerFlag;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Type4ShadingContext(PDShadingType4 shading, ColorModel cm, AffineTransform xform, Matrix matrix, Rectangle deviceBounds) throws IOException {
/*  59 */     super(shading, cm, xform, matrix);
/*  60 */     LOG.debug("Type4ShadingContext");
/*     */     
/*  62 */     this.bitsPerFlag = shading.getBitsPerFlag();
/*     */     
/*  64 */     LOG.debug("bitsPerFlag: " + this.bitsPerFlag);
/*  65 */     setTriangleList(collectTriangles(shading, xform, matrix));
/*  66 */     createPixelTable(deviceBounds);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List<ShadedTriangle> collectTriangles(PDShadingType4 freeTriangleShadingType, AffineTransform xform, Matrix matrix) throws IOException {
/*  72 */     COSDictionary dict = freeTriangleShadingType.getCOSObject();
/*  73 */     if (!(dict instanceof COSStream))
/*     */     {
/*  75 */       return Collections.emptyList();
/*     */     }
/*  77 */     PDRange rangeX = freeTriangleShadingType.getDecodeForParameter(0);
/*  78 */     PDRange rangeY = freeTriangleShadingType.getDecodeForParameter(1);
/*  79 */     if (Float.compare(rangeX.getMin(), rangeX.getMax()) == 0 || 
/*  80 */       Float.compare(rangeY.getMin(), rangeY.getMax()) == 0)
/*     */     {
/*  82 */       return Collections.emptyList();
/*     */     }
/*  84 */     PDRange[] colRange = new PDRange[this.numberOfColorComponents];
/*  85 */     for (int i = 0; i < this.numberOfColorComponents; i++)
/*     */     {
/*  87 */       colRange[i] = freeTriangleShadingType.getDecodeForParameter(2 + i);
/*     */     }
/*  89 */     List<ShadedTriangle> list = new ArrayList<ShadedTriangle>();
/*  90 */     long maxSrcCoord = (long)Math.pow(2.0D, this.bitsPerCoordinate) - 1L;
/*  91 */     long maxSrcColor = (long)Math.pow(2.0D, this.bitsPerColorComponent) - 1L;
/*  92 */     COSStream stream = (COSStream)dict;
/*     */     
/*  94 */     ImageInputStream mciis = new MemoryCacheImageInputStream((InputStream)stream.createInputStream());
/*     */     
/*     */     try {
/*  97 */       byte flag = 0;
/*     */       
/*     */       try {
/* 100 */         flag = (byte)(int)(mciis.readBits(this.bitsPerFlag) & 0x3L);
/*     */       }
/* 102 */       catch (EOFException ex) {
/*     */         
/* 104 */         LOG.error(ex);
/*     */       } 
/*     */       
/* 107 */       boolean eof = false;
/* 108 */       while (!eof) {
/*     */         try {
/*     */           Vertex p0, p1, p2;
/*     */           
/*     */           Point2D[] ps;
/*     */           float[][] cs;
/*     */           int lastIndex;
/*     */           ShadedTriangle preTri;
/* 116 */           switch (flag) {
/*     */             
/*     */             case 0:
/* 119 */               p0 = readVertex(mciis, maxSrcCoord, maxSrcColor, rangeX, rangeY, colRange, matrix, xform);
/*     */               
/* 121 */               flag = (byte)(int)(mciis.readBits(this.bitsPerFlag) & 0x3L);
/* 122 */               if (flag != 0)
/*     */               {
/* 124 */                 LOG.error("bad triangle: " + flag);
/*     */               }
/* 126 */               p1 = readVertex(mciis, maxSrcCoord, maxSrcColor, rangeX, rangeY, colRange, matrix, xform);
/*     */               
/* 128 */               mciis.readBits(this.bitsPerFlag);
/* 129 */               if (flag != 0)
/*     */               {
/* 131 */                 LOG.error("bad triangle: " + flag);
/*     */               }
/* 133 */               p2 = readVertex(mciis, maxSrcCoord, maxSrcColor, rangeX, rangeY, colRange, matrix, xform);
/*     */               
/* 135 */               ps = new Point2D[] { p0.point, p1.point, p2.point };
/* 136 */               cs = new float[][] { p0.color, p1.color, p2.color };
/* 137 */               list.add(new ShadedTriangle(ps, cs));
/* 138 */               flag = (byte)(int)(mciis.readBits(this.bitsPerFlag) & 0x3L);
/*     */               continue;
/*     */             case 1:
/*     */             case 2:
/* 142 */               lastIndex = list.size() - 1;
/* 143 */               if (lastIndex < 0) {
/*     */                 
/* 145 */                 LOG.error("broken data stream: " + list.size());
/*     */                 
/*     */                 continue;
/*     */               } 
/* 149 */               preTri = list.get(lastIndex);
/* 150 */               p2 = readVertex(mciis, maxSrcCoord, maxSrcColor, rangeX, rangeY, colRange, matrix, xform);
/*     */               
/* 152 */               ps = new Point2D[] { (flag == 1) ? preTri.corner[1] : preTri.corner[0], preTri.corner[2], p2.point };
/*     */ 
/*     */               
/* 155 */               cs = new float[][] { (flag == 1) ? preTri.color[1] : preTri.color[0], preTri.color[2], p2.color };
/*     */ 
/*     */               
/* 158 */               list.add(new ShadedTriangle(ps, cs));
/* 159 */               flag = (byte)(int)(mciis.readBits(this.bitsPerFlag) & 0x3L);
/*     */               continue;
/*     */           } 
/*     */           
/* 163 */           LOG.warn("bad flag: " + flag);
/*     */ 
/*     */         
/*     */         }
/* 167 */         catch (EOFException ex) {
/*     */           
/* 169 */           eof = true;
/*     */         }
/*     */       
/*     */       } 
/*     */     } finally {
/*     */       
/* 175 */       mciis.close();
/*     */     } 
/* 177 */     return list;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/Type4ShadingContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */