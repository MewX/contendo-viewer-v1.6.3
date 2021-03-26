/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.RectangularShape;
/*     */ import java.awt.geom.RoundRectangle2D;
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
/*     */ public class SVGRectangle
/*     */   extends SVGGraphicObjectConverter
/*     */ {
/*     */   private SVGLine svgLine;
/*     */   
/*     */   public SVGRectangle(SVGGeneratorContext generatorContext) {
/*  45 */     super(generatorContext);
/*  46 */     this.svgLine = new SVGLine(generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element toSVG(Rectangle2D rect) {
/*  53 */     return toSVG(rect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element toSVG(RoundRectangle2D rect) {
/*  64 */     Element svgRect = toSVG(rect);
/*  65 */     if (svgRect != null && svgRect.getTagName() == "rect") {
/*  66 */       svgRect.setAttributeNS(null, "rx", doubleString(Math.abs(rect.getArcWidth() / 2.0D)));
/*     */       
/*  68 */       svgRect.setAttributeNS(null, "ry", doubleString(Math.abs(rect.getArcHeight() / 2.0D)));
/*     */     } 
/*     */ 
/*     */     
/*  72 */     return svgRect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Element toSVG(RectangularShape rect) {
/*  80 */     if (rect.getWidth() > 0.0D && rect.getHeight() > 0.0D) {
/*  81 */       Element svgRect = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "rect");
/*     */ 
/*     */       
/*  84 */       svgRect.setAttributeNS(null, "x", doubleString(rect.getX()));
/*  85 */       svgRect.setAttributeNS(null, "y", doubleString(rect.getY()));
/*  86 */       svgRect.setAttributeNS(null, "width", doubleString(rect.getWidth()));
/*     */       
/*  88 */       svgRect.setAttributeNS(null, "height", doubleString(rect.getHeight()));
/*     */ 
/*     */       
/*  91 */       return svgRect;
/*     */     } 
/*     */ 
/*     */     
/*  95 */     if (rect.getWidth() == 0.0D && rect.getHeight() > 0.0D) {
/*     */       
/*  97 */       Line2D line = new Line2D.Double(rect.getX(), rect.getY(), rect.getX(), rect.getY() + rect.getHeight());
/*     */       
/*  99 */       return this.svgLine.toSVG(line);
/*     */     } 
/* 101 */     if (rect.getWidth() > 0.0D && rect.getHeight() == 0.0D) {
/*     */       
/* 103 */       Line2D line = new Line2D.Double(rect.getX(), rect.getY(), rect.getX() + rect.getWidth(), rect.getY());
/*     */ 
/*     */       
/* 106 */       return this.svgLine.toSVG(line);
/*     */     } 
/* 108 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGRectangle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */