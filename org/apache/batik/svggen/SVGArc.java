/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import org.w3c.dom.Element;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGArc
/*     */   extends SVGGraphicObjectConverter
/*     */ {
/*     */   private SVGLine svgLine;
/*     */   private SVGEllipse svgEllipse;
/*     */   
/*     */   public SVGArc(SVGGeneratorContext generatorContext) {
/*  52 */     super(generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element toSVG(Arc2D arc) {
/*  59 */     double ext = arc.getAngleExtent();
/*  60 */     double width = arc.getWidth();
/*  61 */     double height = arc.getHeight();
/*     */     
/*  63 */     if (width == 0.0D || height == 0.0D) {
/*  64 */       Line2D line = new Line2D.Double(arc.getX(), arc.getY(), arc.getX() + width, arc.getY() + height);
/*     */ 
/*     */ 
/*     */       
/*  68 */       if (this.svgLine == null) {
/*  69 */         this.svgLine = new SVGLine(this.generatorContext);
/*     */       }
/*  71 */       return this.svgLine.toSVG(line);
/*     */     } 
/*     */     
/*  74 */     if (ext >= 360.0D || ext <= -360.0D) {
/*  75 */       Ellipse2D ellipse = new Ellipse2D.Double(arc.getX(), arc.getY(), width, height);
/*     */       
/*  77 */       if (this.svgEllipse == null) {
/*  78 */         this.svgEllipse = new SVGEllipse(this.generatorContext);
/*     */       }
/*  80 */       return this.svgEllipse.toSVG(ellipse);
/*     */     } 
/*     */     
/*  83 */     Element svgPath = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "path");
/*     */     
/*  85 */     StringBuffer d = new StringBuffer(64);
/*     */     
/*  87 */     Point2D startPt = arc.getStartPoint();
/*  88 */     Point2D endPt = arc.getEndPoint();
/*  89 */     int type = arc.getArcType();
/*     */     
/*  91 */     d.append("M");
/*  92 */     d.append(doubleString(startPt.getX()));
/*  93 */     d.append(" ");
/*  94 */     d.append(doubleString(startPt.getY()));
/*  95 */     d.append(" ");
/*     */     
/*  97 */     d.append("A");
/*  98 */     d.append(doubleString(width / 2.0D));
/*  99 */     d.append(" ");
/* 100 */     d.append(doubleString(height / 2.0D));
/* 101 */     d.append(" ");
/* 102 */     d.append('0');
/* 103 */     d.append(" ");
/* 104 */     if (ext > 0.0D) {
/*     */       
/* 106 */       if (ext > 180.0D) { d.append('1'); }
/* 107 */       else { d.append('0'); }
/* 108 */        d.append(" ");
/* 109 */       d.append('0');
/*     */     } else {
/*     */       
/* 112 */       if (ext < -180.0D) { d.append('1'); }
/* 113 */       else { d.append('0'); }
/* 114 */        d.append(" ");
/* 115 */       d.append('1');
/*     */     } 
/*     */     
/* 118 */     d.append(" ");
/* 119 */     d.append(doubleString(endPt.getX()));
/* 120 */     d.append(" ");
/* 121 */     d.append(doubleString(endPt.getY()));
/*     */     
/* 123 */     if (type == 1) {
/* 124 */       d.append("Z");
/* 125 */     } else if (type == 2) {
/* 126 */       double cx = arc.getX() + width / 2.0D;
/* 127 */       double cy = arc.getY() + height / 2.0D;
/* 128 */       d.append("L");
/* 129 */       d.append(" ");
/* 130 */       d.append(doubleString(cx));
/* 131 */       d.append(" ");
/* 132 */       d.append(doubleString(cy));
/* 133 */       d.append(" ");
/* 134 */       d.append("Z");
/*     */     } 
/* 136 */     svgPath.setAttributeNS(null, "d", d.toString());
/* 137 */     return svgPath;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGArc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */