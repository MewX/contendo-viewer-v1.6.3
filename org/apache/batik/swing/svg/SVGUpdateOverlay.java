/*    */ package org.apache.batik.swing.svg;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import org.apache.batik.swing.gvt.Overlay;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SVGUpdateOverlay
/*    */   implements Overlay
/*    */ {
/* 40 */   List rects = new LinkedList();
/*    */   int size;
/*    */   
/*    */   public SVGUpdateOverlay(int size, int numUpdates) {
/* 44 */     this.size = size;
/* 45 */     this.counts = new int[numUpdates];
/*    */   }
/*    */   int updateCount; int[] counts;
/*    */   public void addRect(Rectangle r) {
/* 49 */     this.rects.add(r);
/* 50 */     if (this.rects.size() > this.size)
/* 51 */       this.rects.remove(0); 
/* 52 */     this.updateCount++;
/*    */   }
/*    */   
/*    */   public void endUpdate() {
/* 56 */     int i = 0;
/* 57 */     for (; i < this.counts.length - 1; i++) {
/* 58 */       this.counts[i] = this.counts[i + 1];
/*    */     }
/* 60 */     this.counts[i] = this.updateCount;
/* 61 */     this.updateCount = 0;
/*    */     
/* 63 */     int num = this.rects.size();
/* 64 */     for (i = this.counts.length - 1; i >= 0; i--) {
/* 65 */       if (this.counts[i] > num) {
/* 66 */         this.counts[i] = num;
/*    */       }
/* 68 */       num -= this.counts[i];
/*    */     } 
/* 70 */     this.counts[0] = this.counts[0] + num;
/*    */   }
/*    */   
/*    */   public void paint(Graphics g) {
/* 74 */     Iterator<Rectangle> i = this.rects.iterator();
/* 75 */     int count = 0;
/* 76 */     int idx = 0;
/* 77 */     int group = 0;
/* 78 */     while (group < this.counts.length - 1 && idx == this.counts[group])
/*    */     {
/* 80 */       group++; } 
/* 81 */     int cmax = this.counts.length - 1;
/* 82 */     while (i.hasNext()) {
/* 83 */       Rectangle r = i.next();
/*    */       
/* 85 */       Color c = new Color(1.0F, (cmax - group) / cmax, 0.0F, (count + 1.0F) / this.rects.size());
/*    */       
/* 87 */       g.setColor(c);
/* 88 */       g.drawRect(r.x, r.y, r.width, r.height);
/* 89 */       count++; idx++;
/* 90 */       while (group < this.counts.length - 1 && idx == this.counts[group]) {
/*    */         
/* 92 */         group++;
/* 93 */         idx = 0;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/svg/SVGUpdateOverlay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */