/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import org.apache.batik.ext.awt.g2d.GraphicContext;
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
/*     */ public class SVGBasicStroke
/*     */   extends AbstractSVGConverter
/*     */ {
/*     */   public SVGBasicStroke(SVGGeneratorContext generatorContext) {
/*  38 */     super(generatorContext);
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
/*  52 */     if (gc.getStroke() instanceof BasicStroke) {
/*  53 */       return toSVG((BasicStroke)gc.getStroke());
/*     */     }
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final SVGStrokeDescriptor toSVG(BasicStroke stroke) {
/*  66 */     String strokeWidth = doubleString(stroke.getLineWidth());
/*     */ 
/*     */     
/*  69 */     String capStyle = endCapToSVG(stroke.getEndCap());
/*     */ 
/*     */     
/*  72 */     String joinStyle = joinToSVG(stroke.getLineJoin());
/*     */ 
/*     */     
/*  75 */     String miterLimit = doubleString(stroke.getMiterLimit());
/*     */ 
/*     */     
/*  78 */     float[] array = stroke.getDashArray();
/*  79 */     String dashArray = null;
/*  80 */     if (array != null) {
/*  81 */       dashArray = dashArrayToSVG(array);
/*     */     } else {
/*  83 */       dashArray = "none";
/*     */     } 
/*     */     
/*  86 */     String dashOffset = doubleString(stroke.getDashPhase());
/*     */     
/*  88 */     return new SVGStrokeDescriptor(strokeWidth, capStyle, joinStyle, miterLimit, dashArray, dashOffset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String dashArrayToSVG(float[] dashArray) {
/*  97 */     StringBuffer dashArrayBuf = new StringBuffer(dashArray.length * 8);
/*  98 */     if (dashArray.length > 0) {
/*  99 */       dashArrayBuf.append(doubleString(dashArray[0]));
/*     */     }
/* 101 */     for (int i = 1; i < dashArray.length; i++) {
/* 102 */       dashArrayBuf.append(",");
/* 103 */       dashArrayBuf.append(doubleString(dashArray[i]));
/*     */     } 
/*     */     
/* 106 */     return dashArrayBuf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String joinToSVG(int lineJoin) {
/* 113 */     switch (lineJoin) {
/*     */       case 2:
/* 115 */         return "bevel";
/*     */       case 1:
/* 117 */         return "round";
/*     */     } 
/*     */     
/* 120 */     return "miter";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String endCapToSVG(int endCap) {
/* 128 */     switch (endCap) {
/*     */       case 0:
/* 130 */         return "butt";
/*     */       case 1:
/* 132 */         return "round";
/*     */     } 
/*     */     
/* 135 */     return "square";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGBasicStroke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */