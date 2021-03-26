/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.PathIterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGPath
/*     */   extends SVGGraphicObjectConverter
/*     */ {
/*     */   public SVGPath(SVGGeneratorContext generatorContext) {
/*  52 */     super(generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element toSVG(Shape path) {
/*  63 */     String dAttr = toSVGPathData(path, this.generatorContext);
/*  64 */     if (dAttr == null || dAttr.length() == 0)
/*     */     {
/*     */       
/*  67 */       return null;
/*     */     }
/*     */     
/*  70 */     Element svgPath = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "path");
/*     */     
/*  72 */     svgPath.setAttributeNS(null, "d", dAttr);
/*     */ 
/*     */     
/*  75 */     if (path.getPathIterator(null).getWindingRule() == 0) {
/*  76 */       svgPath.setAttributeNS(null, "fill-rule", "evenodd");
/*     */     }
/*  78 */     return svgPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toSVGPathData(Shape path, SVGGeneratorContext gc) {
/*  86 */     StringBuffer d = new StringBuffer(40);
/*  87 */     PathIterator pi = path.getPathIterator(null);
/*  88 */     float[] seg = new float[6];
/*  89 */     int segType = 0;
/*  90 */     while (!pi.isDone()) {
/*  91 */       segType = pi.currentSegment(seg);
/*  92 */       switch (segType) {
/*     */         case 0:
/*  94 */           d.append("M");
/*  95 */           appendPoint(d, seg[0], seg[1], gc);
/*     */           break;
/*     */         case 1:
/*  98 */           d.append("L");
/*  99 */           appendPoint(d, seg[0], seg[1], gc);
/*     */           break;
/*     */         case 4:
/* 102 */           d.append("Z");
/*     */           break;
/*     */         case 2:
/* 105 */           d.append("Q");
/* 106 */           appendPoint(d, seg[0], seg[1], gc);
/* 107 */           appendPoint(d, seg[2], seg[3], gc);
/*     */           break;
/*     */         case 3:
/* 110 */           d.append("C");
/* 111 */           appendPoint(d, seg[0], seg[1], gc);
/* 112 */           appendPoint(d, seg[2], seg[3], gc);
/* 113 */           appendPoint(d, seg[4], seg[5], gc);
/*     */           break;
/*     */         default:
/* 116 */           throw new RuntimeException("invalid segmentType:" + segType);
/*     */       } 
/* 118 */       pi.next();
/*     */     } 
/*     */     
/* 121 */     if (d.length() > 0) {
/* 122 */       return d.toString().trim();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void appendPoint(StringBuffer d, float x, float y, SVGGeneratorContext gc) {
/* 140 */     d.append(gc.doubleString(x));
/* 141 */     d.append(" ");
/* 142 */     d.append(gc.doubleString(y));
/* 143 */     d.append(" ");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */