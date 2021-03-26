/*     */ package org.apache.batik.ext.awt.geom;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
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
/*     */ public class SegmentList
/*     */ {
/*  35 */   List segments = new LinkedList();
/*     */ 
/*     */   
/*     */   public SegmentList() {}
/*     */   
/*     */   public SegmentList(Shape s) {
/*  41 */     PathIterator pi = s.getPathIterator(null);
/*  42 */     float[] pts = new float[6];
/*     */     
/*  44 */     Point2D.Double loc = null;
/*  45 */     Point2D.Double openLoc = null;
/*  46 */     while (!pi.isDone()) {
/*  47 */       Point2D.Double p0, p1, p2; int type = pi.currentSegment(pts);
/*  48 */       switch (type) {
/*     */         case 0:
/*  50 */           openLoc = loc = new Point2D.Double(pts[0], pts[1]);
/*     */           break;
/*     */         case 1:
/*  53 */           p0 = new Point2D.Double(pts[0], pts[1]);
/*  54 */           this.segments.add(new Linear(loc, p0));
/*  55 */           loc = p0;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/*  60 */           p0 = new Point2D.Double(pts[0], pts[1]);
/*  61 */           p1 = new Point2D.Double(pts[2], pts[3]);
/*  62 */           this.segments.add(new Quadradic(loc, p0, p1));
/*  63 */           loc = p1;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/*  68 */           p0 = new Point2D.Double(pts[0], pts[1]);
/*  69 */           p1 = new Point2D.Double(pts[2], pts[3]);
/*  70 */           p2 = new Point2D.Double(pts[4], pts[5]);
/*  71 */           this.segments.add(new Cubic(loc, p0, p1, p2));
/*  72 */           loc = p2;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 4:
/*  77 */           this.segments.add(new Linear(loc, openLoc));
/*  78 */           loc = openLoc;
/*     */           break;
/*     */       } 
/*  81 */       pi.next();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/*  86 */     Iterator<Segment> iter = iterator();
/*  87 */     if (!iter.hasNext()) return null;
/*     */ 
/*     */     
/*  90 */     Rectangle2D ret = (Rectangle2D)((Segment)iter.next()).getBounds2D().clone();
/*  91 */     while (iter.hasNext()) {
/*  92 */       Segment seg = iter.next();
/*  93 */       Rectangle2D segB = seg.getBounds2D();
/*  94 */       Rectangle2D.union(segB, ret, ret);
/*     */     } 
/*  96 */     return ret;
/*     */   }
/*     */   
/*     */   public void add(Segment s) {
/* 100 */     this.segments.add(s);
/*     */   }
/*     */   public Iterator iterator() {
/* 103 */     return this.segments.iterator();
/*     */   } public int size() {
/* 105 */     return this.segments.size();
/*     */   }
/*     */   public SplitResults split(double y) {
/* 108 */     Iterator<Segment> iter = this.segments.iterator();
/* 109 */     SegmentList above = new SegmentList();
/* 110 */     SegmentList below = new SegmentList();
/* 111 */     while (iter.hasNext()) {
/* 112 */       Segment seg = iter.next();
/* 113 */       Segment.SplitResults results = seg.split(y);
/* 114 */       if (results == null) {
/* 115 */         Rectangle2D bounds = seg.getBounds2D();
/* 116 */         if (bounds.getY() > y) {
/* 117 */           below.add(seg); continue;
/* 118 */         }  if (bounds.getY() == y) {
/* 119 */           if (bounds.getHeight() != 0.0D)
/* 120 */             below.add(seg); 
/*     */           continue;
/*     */         } 
/* 123 */         above.add(seg);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 128 */       Segment[] resAbove = results.getAbove();
/* 129 */       for (Segment aResAbove : resAbove) {
/* 130 */         above.add(aResAbove);
/*     */       }
/*     */       
/* 133 */       Segment[] resBelow = results.getBelow();
/* 134 */       for (Segment aResBelow : resBelow) {
/* 135 */         below.add(aResBelow);
/*     */       }
/*     */     } 
/* 138 */     return new SplitResults(above, below);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SplitResults
/*     */   {
/*     */     final SegmentList above;
/*     */ 
/*     */ 
/*     */     
/*     */     final SegmentList below;
/*     */ 
/*     */ 
/*     */     
/*     */     public SplitResults(SegmentList above, SegmentList below) {
/* 155 */       if (above != null && above.size() > 0) {
/* 156 */         this.above = above;
/*     */       } else {
/* 158 */         this.above = null;
/*     */       } 
/* 160 */       if (below != null && below.size() > 0) {
/* 161 */         this.below = below;
/*     */       } else {
/* 163 */         this.below = null;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public SegmentList getAbove() {
/* 170 */       return this.above;
/*     */     }
/*     */ 
/*     */     
/*     */     public SegmentList getBelow() {
/* 175 */       return this.below;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/SegmentList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */