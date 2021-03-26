/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.awt.Polygon;
/*    */ import java.awt.geom.PathIterator;
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
/*    */ public class SVGPolygon
/*    */   extends SVGGraphicObjectConverter
/*    */ {
/*    */   public SVGPolygon(SVGGeneratorContext generatorContext) {
/* 38 */     super(generatorContext);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Element toSVG(Polygon polygon) {
/* 45 */     Element svgPolygon = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "polygon");
/*    */ 
/*    */     
/* 48 */     StringBuffer points = new StringBuffer(" ");
/* 49 */     PathIterator pi = polygon.getPathIterator(null);
/* 50 */     float[] seg = new float[6];
/* 51 */     while (!pi.isDone()) {
/* 52 */       int segType = pi.currentSegment(seg);
/* 53 */       switch (segType) {
/*    */         case 0:
/* 55 */           appendPoint(points, seg[0], seg[1]);
/*    */           break;
/*    */         case 1:
/* 58 */           appendPoint(points, seg[0], seg[1]);
/*    */           break;
/*    */         
/*    */         case 4:
/*    */           break;
/*    */         
/*    */         default:
/* 65 */           throw new RuntimeException("invalid segmentType:" + segType);
/*    */       } 
/* 67 */       pi.next();
/*    */     } 
/*    */     
/* 70 */     svgPolygon.setAttributeNS(null, "points", points.substring(0, points.length() - 1));
/*    */ 
/*    */ 
/*    */     
/* 74 */     return svgPolygon;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void appendPoint(StringBuffer points, float x, float y) {
/* 81 */     points.append(doubleString(x));
/* 82 */     points.append(" ");
/* 83 */     points.append(doubleString(y));
/* 84 */     points.append(" ");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGPolygon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */