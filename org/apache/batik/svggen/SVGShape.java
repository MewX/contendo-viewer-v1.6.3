/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.awt.Polygon;
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.Arc2D;
/*    */ import java.awt.geom.Ellipse2D;
/*    */ import java.awt.geom.Line2D;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.geom.RoundRectangle2D;
/*    */ import org.w3c.dom.Element;
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
/*    */ public class SVGShape
/*    */   extends SVGGraphicObjectConverter
/*    */ {
/*    */   private SVGArc svgArc;
/*    */   private SVGEllipse svgEllipse;
/*    */   private SVGLine svgLine;
/*    */   private SVGPath svgPath;
/*    */   private SVGPolygon svgPolygon;
/*    */   private SVGRectangle svgRectangle;
/*    */   
/*    */   public SVGShape(SVGGeneratorContext generatorContext) {
/* 61 */     super(generatorContext);
/* 62 */     this.svgArc = new SVGArc(generatorContext);
/* 63 */     this.svgEllipse = new SVGEllipse(generatorContext);
/* 64 */     this.svgLine = new SVGLine(generatorContext);
/* 65 */     this.svgPath = new SVGPath(generatorContext);
/* 66 */     this.svgPolygon = new SVGPolygon(generatorContext);
/* 67 */     this.svgRectangle = new SVGRectangle(generatorContext);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Element toSVG(Shape shape) {
/* 74 */     if (shape instanceof Polygon)
/* 75 */       return this.svgPolygon.toSVG((Polygon)shape); 
/* 76 */     if (shape instanceof Rectangle2D)
/* 77 */       return this.svgRectangle.toSVG((Rectangle2D)shape); 
/* 78 */     if (shape instanceof RoundRectangle2D)
/* 79 */       return this.svgRectangle.toSVG((RoundRectangle2D)shape); 
/* 80 */     if (shape instanceof Ellipse2D)
/* 81 */       return this.svgEllipse.toSVG((Ellipse2D)shape); 
/* 82 */     if (shape instanceof Line2D)
/* 83 */       return this.svgLine.toSVG((Line2D)shape); 
/* 84 */     if (shape instanceof Arc2D) {
/* 85 */       return this.svgArc.toSVG((Arc2D)shape);
/*    */     }
/* 87 */     return this.svgPath.toSVG(shape);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGShape.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */