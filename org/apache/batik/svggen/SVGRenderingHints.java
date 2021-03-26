/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.RenderingHints;
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
/*     */ public class SVGRenderingHints
/*     */   extends AbstractSVGConverter
/*     */ {
/*     */   public SVGRenderingHints(SVGGeneratorContext generatorContext) {
/*  49 */     super(generatorContext);
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
/*  63 */     return toSVG(gc.getRenderingHints());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SVGHintsDescriptor toSVG(RenderingHints hints) {
/*  73 */     String colorInterpolation = "auto";
/*  74 */     String colorRendering = "auto";
/*  75 */     String textRendering = "auto";
/*  76 */     String shapeRendering = "auto";
/*  77 */     String imageRendering = "auto";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (hints != null) {
/*  83 */       Object rendering = hints.get(RenderingHints.KEY_RENDERING);
/*  84 */       if (rendering == RenderingHints.VALUE_RENDER_DEFAULT) {
/*  85 */         colorInterpolation = "auto";
/*  86 */         colorRendering = "auto";
/*  87 */         textRendering = "auto";
/*  88 */         shapeRendering = "auto";
/*  89 */         imageRendering = "auto";
/*     */       }
/*  91 */       else if (rendering == RenderingHints.VALUE_RENDER_SPEED) {
/*  92 */         colorInterpolation = "sRGB";
/*  93 */         colorRendering = "optimizeSpeed";
/*  94 */         textRendering = "optimizeSpeed";
/*  95 */         shapeRendering = "geometricPrecision";
/*  96 */         imageRendering = "optimizeSpeed";
/*     */       }
/*  98 */       else if (rendering == RenderingHints.VALUE_RENDER_QUALITY) {
/*  99 */         colorInterpolation = "linearRGB";
/* 100 */         colorRendering = "optimizeQuality";
/* 101 */         textRendering = "optimizeQuality";
/* 102 */         shapeRendering = "geometricPrecision";
/* 103 */         imageRendering = "optimizeQuality";
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 109 */       Object fractionalMetrics = hints.get(RenderingHints.KEY_FRACTIONALMETRICS);
/* 110 */       if (fractionalMetrics == RenderingHints.VALUE_FRACTIONALMETRICS_ON) {
/* 111 */         textRendering = "optimizeQuality";
/* 112 */         shapeRendering = "geometricPrecision";
/*     */       }
/* 114 */       else if (fractionalMetrics == RenderingHints.VALUE_FRACTIONALMETRICS_OFF) {
/* 115 */         textRendering = "optimizeSpeed";
/* 116 */         shapeRendering = "optimizeSpeed";
/*     */       }
/* 118 */       else if (fractionalMetrics == RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT) {
/* 119 */         textRendering = "auto";
/* 120 */         shapeRendering = "auto";
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 126 */       Object antialiasing = hints.get(RenderingHints.KEY_ANTIALIASING);
/* 127 */       if (antialiasing == RenderingHints.VALUE_ANTIALIAS_ON) {
/* 128 */         textRendering = "optimizeLegibility";
/* 129 */         shapeRendering = "auto";
/*     */       }
/* 131 */       else if (antialiasing == RenderingHints.VALUE_ANTIALIAS_OFF) {
/* 132 */         textRendering = "geometricPrecision";
/* 133 */         shapeRendering = "crispEdges";
/*     */       }
/* 135 */       else if (antialiasing == RenderingHints.VALUE_ANTIALIAS_DEFAULT) {
/* 136 */         textRendering = "auto";
/* 137 */         shapeRendering = "auto";
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       Object textAntialiasing = hints.get(RenderingHints.KEY_TEXT_ANTIALIASING);
/* 144 */       if (textAntialiasing == RenderingHints.VALUE_TEXT_ANTIALIAS_ON) {
/* 145 */         textRendering = "geometricPrecision";
/* 146 */       } else if (textAntialiasing == RenderingHints.VALUE_TEXT_ANTIALIAS_OFF) {
/* 147 */         textRendering = "optimizeSpeed";
/* 148 */       } else if (textAntialiasing == RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT) {
/* 149 */         textRendering = "auto";
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 154 */       Object colorRenderingHint = hints.get(RenderingHints.KEY_COLOR_RENDERING);
/* 155 */       if (colorRenderingHint == RenderingHints.VALUE_COLOR_RENDER_DEFAULT) {
/* 156 */         colorRendering = "auto";
/* 157 */       } else if (colorRenderingHint == RenderingHints.VALUE_COLOR_RENDER_QUALITY) {
/* 158 */         colorRendering = "optimizeQuality";
/* 159 */       } else if (colorRenderingHint == RenderingHints.VALUE_COLOR_RENDER_SPEED) {
/* 160 */         colorRendering = "optimizeSpeed";
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 165 */       Object interpolation = hints.get(RenderingHints.KEY_INTERPOLATION);
/* 166 */       if (interpolation == RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR) {
/* 167 */         imageRendering = "optimizeSpeed";
/* 168 */       } else if (interpolation == RenderingHints.VALUE_INTERPOLATION_BICUBIC || interpolation == RenderingHints.VALUE_INTERPOLATION_BILINEAR) {
/*     */ 
/*     */         
/* 171 */         imageRendering = "optimizeQuality";
/*     */       } 
/*     */     } 
/* 174 */     return new SVGHintsDescriptor(colorInterpolation, colorRendering, textRendering, shapeRendering, imageRendering);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGRenderingHints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */