/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Line2D;
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
/*     */ public class SVGEllipse
/*     */   extends SVGGraphicObjectConverter
/*     */ {
/*     */   private SVGLine svgLine;
/*     */   
/*     */   public SVGEllipse(SVGGeneratorContext generatorContext) {
/*  43 */     super(generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element toSVG(Ellipse2D ellipse) {
/*  50 */     if (ellipse.getWidth() < 0.0D || ellipse.getHeight() < 0.0D) {
/*  51 */       return null;
/*     */     }
/*     */     
/*  54 */     if (ellipse.getWidth() == ellipse.getHeight()) {
/*  55 */       return toSVGCircle(ellipse);
/*     */     }
/*  57 */     return toSVGEllipse(ellipse);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Element toSVGCircle(Ellipse2D ellipse) {
/*  64 */     Element svgCircle = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "circle");
/*     */ 
/*     */     
/*  67 */     svgCircle.setAttributeNS(null, "cx", doubleString(ellipse.getX() + ellipse.getWidth() / 2.0D));
/*     */ 
/*     */     
/*  70 */     svgCircle.setAttributeNS(null, "cy", doubleString(ellipse.getY() + ellipse.getHeight() / 2.0D));
/*     */ 
/*     */     
/*  73 */     svgCircle.setAttributeNS(null, "r", doubleString(ellipse.getWidth() / 2.0D));
/*     */     
/*  75 */     return svgCircle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Element toSVGEllipse(Ellipse2D ellipse) {
/*  82 */     if (ellipse.getWidth() > 0.0D && ellipse.getHeight() > 0.0D) {
/*  83 */       Element svgCircle = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "ellipse");
/*     */ 
/*     */       
/*  86 */       svgCircle.setAttributeNS(null, "cx", doubleString(ellipse.getX() + ellipse.getWidth() / 2.0D));
/*     */ 
/*     */       
/*  89 */       svgCircle.setAttributeNS(null, "cy", doubleString(ellipse.getY() + ellipse.getHeight() / 2.0D));
/*     */ 
/*     */       
/*  92 */       svgCircle.setAttributeNS(null, "rx", doubleString(ellipse.getWidth() / 2.0D));
/*     */       
/*  94 */       svgCircle.setAttributeNS(null, "ry", doubleString(ellipse.getHeight() / 2.0D));
/*     */       
/*  96 */       return svgCircle;
/*     */     } 
/*  98 */     if (ellipse.getWidth() == 0.0D && ellipse.getHeight() > 0.0D) {
/*     */       
/* 100 */       Line2D line = new Line2D.Double(ellipse.getX(), ellipse.getY(), ellipse.getX(), ellipse.getY() + ellipse.getHeight());
/*     */       
/* 102 */       if (this.svgLine == null)
/* 103 */         this.svgLine = new SVGLine(this.generatorContext); 
/* 104 */       return this.svgLine.toSVG(line);
/*     */     } 
/* 106 */     if (ellipse.getWidth() > 0.0D && ellipse.getHeight() == 0.0D) {
/*     */       
/* 108 */       Line2D line = new Line2D.Double(ellipse.getX(), ellipse.getY(), ellipse.getX() + ellipse.getWidth(), ellipse.getY());
/*     */ 
/*     */       
/* 111 */       if (this.svgLine == null)
/* 112 */         this.svgLine = new SVGLine(this.generatorContext); 
/* 113 */       return this.svgLine.toSVG(line);
/*     */     } 
/* 115 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGEllipse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */