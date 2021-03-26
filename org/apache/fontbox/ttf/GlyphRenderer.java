/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GlyphRenderer
/*     */ {
/*  42 */   private static final Log LOG = LogFactory.getLog(GlyphRenderer.class);
/*     */   
/*     */   private GlyphDescription glyphDescription;
/*     */ 
/*     */   
/*     */   GlyphRenderer(GlyphDescription glyphDescription) {
/*  48 */     this.glyphDescription = glyphDescription;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath() {
/*  57 */     Point[] points = describe(this.glyphDescription);
/*  58 */     return calculatePath(points);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Point[] describe(GlyphDescription gd) {
/*  66 */     int endPtIndex = 0;
/*  67 */     int endPtOfContourIndex = -1;
/*  68 */     Point[] points = new Point[gd.getPointCount()];
/*  69 */     for (int i = 0; i < gd.getPointCount(); i++) {
/*     */       
/*  71 */       if (endPtOfContourIndex == -1)
/*     */       {
/*  73 */         endPtOfContourIndex = gd.getEndPtOfContours(endPtIndex);
/*     */       }
/*  75 */       boolean endPt = (endPtOfContourIndex == i);
/*  76 */       if (endPt) {
/*     */         
/*  78 */         endPtIndex++;
/*  79 */         endPtOfContourIndex = -1;
/*     */       } 
/*  81 */       points[i] = new Point(gd.getXCoordinate(i), gd.getYCoordinate(i), 
/*  82 */           ((gd.getFlags(i) & 0x1) != 0), endPt);
/*     */     } 
/*  84 */     return points;
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
/*     */   private GeneralPath calculatePath(Point[] points) {
/*  96 */     GeneralPath path = new GeneralPath();
/*  97 */     int start = 0;
/*  98 */     for (int p = 0, len = points.length; p < len; p++) {
/*     */       
/* 100 */       if ((points[p]).endOfContour) {
/*     */         
/* 102 */         Point firstPoint = points[start];
/* 103 */         Point lastPoint = points[p];
/* 104 */         List<Point> contour = new ArrayList<Point>();
/* 105 */         for (int q = start; q <= p; q++)
/*     */         {
/* 107 */           contour.add(points[q]);
/*     */         }
/* 109 */         if ((points[start]).onCurve) {
/*     */ 
/*     */           
/* 112 */           contour.add(firstPoint);
/*     */         }
/* 114 */         else if ((points[p]).onCurve) {
/*     */ 
/*     */           
/* 117 */           contour.add(0, lastPoint);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 122 */           Point pmid = midValue(firstPoint, lastPoint);
/* 123 */           contour.add(0, pmid);
/* 124 */           contour.add(pmid);
/*     */         } 
/* 126 */         moveTo(path, contour.get(0));
/* 127 */         for (int j = 1, clen = contour.size(); j < clen; j++) {
/*     */           
/* 129 */           Point pnow = contour.get(j);
/* 130 */           if (pnow.onCurve) {
/*     */             
/* 132 */             lineTo(path, pnow);
/*     */           }
/* 134 */           else if ((contour.get(j + 1)).onCurve) {
/*     */             
/* 136 */             quadTo(path, pnow, contour.get(j + 1));
/* 137 */             j++;
/*     */           }
/*     */           else {
/*     */             
/* 141 */             quadTo(path, pnow, midValue(pnow, contour.get(j + 1)));
/*     */           } 
/*     */         } 
/* 144 */         path.closePath();
/* 145 */         start = p + 1;
/*     */       } 
/*     */     } 
/* 148 */     return path;
/*     */   }
/*     */ 
/*     */   
/*     */   private void moveTo(GeneralPath path, Point point) {
/* 153 */     path.moveTo(point.x, point.y);
/* 154 */     if (LOG.isDebugEnabled())
/*     */     {
/* 156 */       LOG.trace("moveTo: " + String.format(Locale.US, "%d,%d", new Object[] { Integer.valueOf(Point.access$200(point)), Integer.valueOf(Point.access$300(point)) }));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void lineTo(GeneralPath path, Point point) {
/* 162 */     path.lineTo(point.x, point.y);
/* 163 */     if (LOG.isDebugEnabled())
/*     */     {
/* 165 */       LOG.trace("lineTo: " + String.format(Locale.US, "%d,%d", new Object[] { Integer.valueOf(Point.access$200(point)), Integer.valueOf(Point.access$300(point)) }));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void quadTo(GeneralPath path, Point ctrlPoint, Point point) {
/* 171 */     path.quadTo(ctrlPoint.x, ctrlPoint.y, point.x, point.y);
/* 172 */     if (LOG.isDebugEnabled())
/*     */     {
/* 174 */       LOG.trace("quadTo: " + String.format(Locale.US, "%d,%d %d,%d", new Object[] { Integer.valueOf(Point.access$200(ctrlPoint)), Integer.valueOf(Point.access$300(ctrlPoint)), 
/* 175 */               Integer.valueOf(Point.access$200(point)), Integer.valueOf(Point.access$300(point)) }));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int midValue(int a, int b) {
/* 181 */     return a + (b - a) / 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Point midValue(Point point1, Point point2) {
/* 187 */     return new Point(midValue(point1.x, point2.x), midValue(point1.y, point2.y));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class Point
/*     */   {
/* 195 */     private int x = 0;
/* 196 */     private int y = 0;
/*     */     
/*     */     private boolean onCurve = true;
/*     */     private boolean endOfContour = false;
/*     */     
/*     */     Point(int xValue, int yValue, boolean onCurveValue, boolean endOfContourValue) {
/* 202 */       this.x = xValue;
/* 203 */       this.y = yValue;
/* 204 */       this.onCurve = onCurveValue;
/* 205 */       this.endOfContour = endOfContourValue;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     Point(int xValue, int yValue) {
/* 211 */       this(xValue, yValue, true, false);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 217 */       return String.format(Locale.US, "Point(%d,%d,%s,%s)", new Object[] { Integer.valueOf(this.x), Integer.valueOf(this.y), this.onCurve ? "onCurve" : "", this.endOfContour ? "endOfContour" : "" });
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/GlyphRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */