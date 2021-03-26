/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.awt.geom.Line2D;
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
/*    */ public class SVGLine
/*    */   extends SVGGraphicObjectConverter
/*    */ {
/*    */   public SVGLine(SVGGeneratorContext generatorContext) {
/* 37 */     super(generatorContext);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Element toSVG(Line2D line) {
/* 44 */     Element svgLine = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "line");
/*    */ 
/*    */     
/* 47 */     svgLine.setAttributeNS(null, "x1", doubleString(line.getX1()));
/*    */     
/* 49 */     svgLine.setAttributeNS(null, "y1", doubleString(line.getY1()));
/*    */     
/* 51 */     svgLine.setAttributeNS(null, "x2", doubleString(line.getX2()));
/*    */     
/* 53 */     svgLine.setAttributeNS(null, "y2", doubleString(line.getY2()));
/*    */     
/* 55 */     return svgLine;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */