/*     */ package org.apache.batik.gvt.flow;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.batik.ext.awt.geom.Segment;
/*     */ import org.apache.batik.ext.awt.geom.SegmentList;
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
/*     */ public class FlowRegions
/*     */ {
/*     */   Shape flowShape;
/*     */   SegmentList sl;
/*     */   SegmentList.SplitResults sr;
/*     */   List validRanges;
/*     */   int currentRange;
/*     */   double currentY;
/*     */   double lineHeight;
/*     */   
/*     */   public FlowRegions(Shape s) {
/*  46 */     this(s, s.getBounds2D().getY());
/*     */   }
/*     */   
/*     */   public FlowRegions(Shape s, double startY) {
/*  50 */     this.flowShape = s;
/*  51 */     this.sl = new SegmentList(s);
/*  52 */     this.currentY = startY - 1.0D;
/*  53 */     gotoY(startY);
/*     */   }
/*     */   
/*  56 */   public double getCurrentY() { return this.currentY; } public double getLineHeight() {
/*  57 */     return this.lineHeight;
/*     */   }
/*     */   public boolean gotoY(double y) {
/*  60 */     if (y < this.currentY) {
/*  61 */       throw new IllegalArgumentException("New Y can not be lower than old Y\nOld Y: " + this.currentY + " New Y: " + y);
/*     */     }
/*     */     
/*  64 */     if (y == this.currentY) return false; 
/*  65 */     this.sr = this.sl.split(y);
/*  66 */     this.sl = this.sr.getBelow();
/*  67 */     this.sr = null;
/*  68 */     this.currentY = y;
/*  69 */     if (this.sl == null) return true;
/*     */     
/*  71 */     newLineHeight(this.lineHeight);
/*  72 */     return false;
/*     */   }
/*     */   
/*     */   public void newLineHeight(double lineHeight) {
/*  76 */     this.lineHeight = lineHeight;
/*  77 */     this.sr = this.sl.split(this.currentY + lineHeight);
/*     */     
/*  79 */     if (this.sr.getAbove() != null) {
/*  80 */       sortRow(this.sr.getAbove());
/*     */     }
/*  82 */     this.currentRange = 0;
/*     */   }
/*     */   
/*     */   public int getNumRangeOnLine() {
/*  86 */     if (this.validRanges == null) return 0; 
/*  87 */     return this.validRanges.size();
/*     */   }
/*     */   public void resetRange() {
/*  90 */     this.currentRange = 0;
/*     */   }
/*     */   
/*     */   public double[] nextRange() {
/*  94 */     if (this.currentRange >= this.validRanges.size())
/*  95 */       return null; 
/*  96 */     return this.validRanges.get(this.currentRange++);
/*     */   }
/*     */   public void endLine() {
/*  99 */     this.sl = this.sr.getBelow();
/* 100 */     this.sr = null;
/* 101 */     this.currentY += this.lineHeight;
/*     */   }
/*     */   
/*     */   public boolean newLine() {
/* 105 */     return newLine(this.lineHeight);
/*     */   }
/*     */   
/*     */   public boolean newLine(double lineHeight) {
/* 109 */     if (this.sr != null) {
/* 110 */       this.sl = this.sr.getBelow();
/*     */     }
/* 112 */     this.sr = null;
/* 113 */     if (this.sl == null) return false; 
/* 114 */     this.currentY += this.lineHeight;
/* 115 */     newLineHeight(lineHeight);
/* 116 */     return true;
/*     */   }
/*     */   
/*     */   public boolean newLineAt(double y, double lineHeight) {
/* 120 */     if (this.sr != null) {
/* 121 */       this.sl = this.sr.getBelow();
/*     */     }
/* 123 */     this.sr = null;
/* 124 */     if (this.sl == null) return false; 
/* 125 */     this.currentY = y;
/* 126 */     newLineHeight(lineHeight);
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean done() {
/* 132 */     return (this.sl == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sortRow(SegmentList sl) {
/* 137 */     Transition[] segs = new Transition[sl.size() * 2];
/* 138 */     Iterator<Segment> iter = sl.iterator();
/* 139 */     int i = 0;
/* 140 */     while (iter.hasNext()) {
/* 141 */       Segment seg = iter.next();
/* 142 */       segs[i++] = new Transition(seg.minX(), true);
/* 143 */       segs[i++] = new Transition(seg.maxX(), false);
/*     */     } 
/*     */ 
/*     */     
/* 147 */     Arrays.sort(segs, TransitionComp.COMP);
/* 148 */     this.validRanges = new ArrayList();
/* 149 */     int count = 1;
/* 150 */     double openStart = 0.0D;
/*     */     
/* 152 */     for (i = 1; i < segs.length; i++) {
/* 153 */       Transition t = segs[i];
/* 154 */       if (t.up) {
/* 155 */         if (count == 0) {
/* 156 */           double cx = (openStart + t.loc) / 2.0D;
/* 157 */           double cy = this.currentY + this.lineHeight / 2.0D;
/*     */           
/* 159 */           if (this.flowShape.contains(cx, cy)) {
/* 160 */             this.validRanges.add(new double[] { openStart, t.loc });
/*     */           }
/*     */         } 
/* 163 */         count++;
/*     */       } else {
/* 165 */         count--;
/* 166 */         if (count == 0)
/* 167 */           openStart = t.loc; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static class Transition { public double loc;
/*     */     public boolean up;
/*     */     
/*     */     public Transition(double loc, boolean up) {
/* 176 */       this.loc = loc;
/* 177 */       this.up = up;
/*     */     } }
/*     */ 
/*     */   
/*     */   static class TransitionComp implements Comparator {
/* 182 */     public static Comparator COMP = new TransitionComp();
/*     */     
/*     */     public int compare(Object o1, Object o2) {
/* 185 */       FlowRegions.Transition t1 = (FlowRegions.Transition)o1;
/* 186 */       FlowRegions.Transition t2 = (FlowRegions.Transition)o2;
/* 187 */       if (t1.loc < t2.loc) return -1; 
/* 188 */       if (t1.loc > t2.loc) return 1;
/*     */       
/* 190 */       if (t1.up) {
/* 191 */         if (t2.up) return 0; 
/* 192 */         return -1;
/*     */       } 
/* 194 */       if (t2.up) return 1; 
/* 195 */       return 0;
/*     */     }
/*     */     public boolean equals(Object comp) {
/* 198 */       return (this == comp);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/flow/FlowRegions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */