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
/*     */ class Type5ShadingContext
/*     */   extends GouraudShadingContext
/*     */ {
/*  45 */   private static final Log LOG = LogFactory.getLog(Type5ShadingContext.class);
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
/*     */   Type5ShadingContext(PDShadingType5 shading, ColorModel cm, AffineTransform xform, Matrix matrix, Rectangle deviceBounds) throws IOException {
/*  59 */     super(shading, cm, xform, matrix);
/*     */     
/*  61 */     LOG.debug("Type5ShadingContext");
/*     */     
/*  63 */     setTriangleList(collectTriangles(shading, xform, matrix));
/*  64 */     createPixelTable(deviceBounds);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List<ShadedTriangle> collectTriangles(PDShadingType5 latticeTriangleShadingType, AffineTransform xform, Matrix matrix) throws IOException {
/*  70 */     COSDictionary dict = latticeTriangleShadingType.getCOSObject();
/*  71 */     if (!(dict instanceof COSStream))
/*     */     {
/*  73 */       return Collections.emptyList();
/*     */     }
/*  75 */     PDRange rangeX = latticeTriangleShadingType.getDecodeForParameter(0);
/*  76 */     PDRange rangeY = latticeTriangleShadingType.getDecodeForParameter(1);
/*  77 */     if (Float.compare(rangeX.getMin(), rangeX.getMax()) == 0 || 
/*  78 */       Float.compare(rangeY.getMin(), rangeY.getMax()) == 0)
/*     */     {
/*  80 */       return Collections.emptyList();
/*     */     }
/*  82 */     int numPerRow = latticeTriangleShadingType.getVerticesPerRow();
/*  83 */     PDRange[] colRange = new PDRange[this.numberOfColorComponents];
/*  84 */     for (int i = 0; i < this.numberOfColorComponents; i++)
/*     */     {
/*  86 */       colRange[i] = latticeTriangleShadingType.getDecodeForParameter(2 + i);
/*     */     }
/*  88 */     List<Vertex> vlist = new ArrayList<Vertex>();
/*  89 */     long maxSrcCoord = (long)Math.pow(2.0D, this.bitsPerCoordinate) - 1L;
/*  90 */     long maxSrcColor = (long)Math.pow(2.0D, this.bitsPerColorComponent) - 1L;
/*  91 */     COSStream cosStream = (COSStream)dict;
/*     */     
/*  93 */     ImageInputStream mciis = new MemoryCacheImageInputStream((InputStream)cosStream.createInputStream());
/*     */     
/*     */     try {
/*  96 */       boolean eof = false;
/*  97 */       while (!eof) {
/*     */         
/*     */         try
/*     */         {
/*     */           
/* 102 */           Vertex p = readVertex(mciis, maxSrcCoord, maxSrcColor, rangeX, rangeY, colRange, matrix, xform);
/* 103 */           vlist.add(p);
/*     */         }
/* 105 */         catch (EOFException ex)
/*     */         {
/* 107 */           eof = true;
/*     */         }
/*     */       
/*     */       } 
/*     */     } finally {
/*     */       
/* 113 */       mciis.close();
/*     */     } 
/* 115 */     int rowNum = vlist.size() / numPerRow;
/* 116 */     Vertex[][] latticeArray = new Vertex[rowNum][numPerRow];
/* 117 */     List<ShadedTriangle> list = new ArrayList<ShadedTriangle>();
/* 118 */     if (rowNum < 2)
/*     */     {
/*     */       
/* 121 */       return list; } 
/*     */     int j;
/* 123 */     for (j = 0; j < rowNum; j++) {
/*     */       
/* 125 */       for (int k = 0; k < numPerRow; k++)
/*     */       {
/* 127 */         latticeArray[j][k] = vlist.get(j * numPerRow + k);
/*     */       }
/*     */     } 
/*     */     
/* 131 */     for (j = 0; j < rowNum - 1; j++) {
/*     */       
/* 133 */       for (int k = 0; k < numPerRow - 1; k++) {
/*     */         
/* 135 */         Point2D[] ps = { (latticeArray[j][k]).point, (latticeArray[j][k + 1]).point, (latticeArray[j + 1][k]).point };
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 140 */         float[][] cs = { (latticeArray[j][k]).color, (latticeArray[j][k + 1]).color, (latticeArray[j + 1][k]).color };
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 145 */         list.add(new ShadedTriangle(ps, cs));
/*     */         
/* 147 */         ps = new Point2D[] { (latticeArray[j][k + 1]).point, (latticeArray[j + 1][k]).point, (latticeArray[j + 1][k + 1]).point };
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 152 */         cs = new float[][] { (latticeArray[j][k + 1]).color, (latticeArray[j + 1][k]).color, (latticeArray[j + 1][k + 1]).color };
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 157 */         list.add(new ShadedTriangle(ps, cs));
/*     */       } 
/*     */     } 
/* 160 */     return list;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/Type5ShadingContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */