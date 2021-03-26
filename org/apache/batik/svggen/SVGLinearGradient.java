/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Point2D;
/*     */ import org.apache.batik.ext.awt.g2d.GraphicContext;
/*     */ import org.w3c.dom.Document;
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
/*     */ public class SVGLinearGradient
/*     */   extends AbstractSVGConverter
/*     */ {
/*     */   public SVGLinearGradient(SVGGeneratorContext generatorContext) {
/*  41 */     super(generatorContext);
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
/*     */ 
/*     */   
/*     */   public SVGDescriptor toSVG(GraphicContext gc) {
/*  55 */     Paint paint = gc.getPaint();
/*  56 */     return toSVG((GradientPaint)paint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPaintDescriptor toSVG(GradientPaint gradient) {
/*  67 */     SVGPaintDescriptor gradientDesc = (SVGPaintDescriptor)this.descMap.get(gradient);
/*     */ 
/*     */     
/*  70 */     Document domFactory = this.generatorContext.domFactory;
/*     */     
/*  72 */     if (gradientDesc == null) {
/*  73 */       Element gradientDef = domFactory.createElementNS("http://www.w3.org/2000/svg", "linearGradient");
/*     */ 
/*     */       
/*  76 */       gradientDef.setAttributeNS((String)null, "gradientUnits", "userSpaceOnUse");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  82 */       Point2D p1 = gradient.getPoint1();
/*  83 */       Point2D p2 = gradient.getPoint2();
/*  84 */       gradientDef.setAttributeNS((String)null, "x1", doubleString(p1.getX()));
/*     */       
/*  86 */       gradientDef.setAttributeNS((String)null, "y1", doubleString(p1.getY()));
/*     */       
/*  88 */       gradientDef.setAttributeNS((String)null, "x2", doubleString(p2.getX()));
/*     */       
/*  90 */       gradientDef.setAttributeNS((String)null, "y2", doubleString(p2.getY()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  96 */       String spreadMethod = "pad";
/*  97 */       if (gradient.isCyclic())
/*  98 */         spreadMethod = "reflect"; 
/*  99 */       gradientDef.setAttributeNS((String)null, "spreadMethod", spreadMethod);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 105 */       Element gradientStop = domFactory.createElementNS("http://www.w3.org/2000/svg", "stop");
/*     */       
/* 107 */       gradientStop.setAttributeNS((String)null, "offset", "0%");
/*     */ 
/*     */       
/* 110 */       SVGPaintDescriptor colorDesc = SVGColor.toSVG(gradient.getColor1(), this.generatorContext);
/* 111 */       gradientStop.setAttributeNS((String)null, "stop-color", colorDesc.getPaintValue());
/*     */       
/* 113 */       gradientStop.setAttributeNS((String)null, "stop-opacity", colorDesc.getOpacityValue());
/*     */ 
/*     */       
/* 116 */       gradientDef.appendChild(gradientStop);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 121 */       gradientStop = domFactory.createElementNS("http://www.w3.org/2000/svg", "stop");
/*     */       
/* 123 */       gradientStop.setAttributeNS((String)null, "offset", "100%");
/*     */ 
/*     */       
/* 126 */       colorDesc = SVGColor.toSVG(gradient.getColor2(), this.generatorContext);
/* 127 */       gradientStop.setAttributeNS((String)null, "stop-color", colorDesc.getPaintValue());
/*     */       
/* 129 */       gradientStop.setAttributeNS((String)null, "stop-opacity", colorDesc.getOpacityValue());
/*     */ 
/*     */       
/* 132 */       gradientDef.appendChild(gradientStop);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       gradientDef.setAttributeNS((String)null, "id", this.generatorContext.idGenerator.generateID("linearGradient"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       StringBuffer paintAttrBuf = new StringBuffer("url(");
/* 146 */       paintAttrBuf.append("#");
/* 147 */       paintAttrBuf.append(gradientDef.getAttributeNS((String)null, "id"));
/* 148 */       paintAttrBuf.append(")");
/*     */       
/* 150 */       gradientDesc = new SVGPaintDescriptor(paintAttrBuf.toString(), "1", gradientDef);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 157 */       this.descMap.put(gradient, gradientDesc);
/* 158 */       this.defSet.add(gradientDef);
/*     */     } 
/*     */     
/* 161 */     return gradientDesc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGLinearGradient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */