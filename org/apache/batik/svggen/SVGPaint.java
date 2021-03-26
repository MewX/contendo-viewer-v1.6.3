/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Paint;
/*     */ import java.awt.TexturePaint;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
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
/*     */ public class SVGPaint
/*     */   implements SVGConverter
/*     */ {
/*     */   private SVGLinearGradient svgLinearGradient;
/*     */   private SVGTexturePaint svgTexturePaint;
/*     */   private SVGColor svgColor;
/*     */   private SVGCustomPaint svgCustomPaint;
/*     */   private SVGGeneratorContext generatorContext;
/*     */   
/*     */   public SVGPaint(SVGGeneratorContext generatorContext) {
/*  70 */     this.svgLinearGradient = new SVGLinearGradient(generatorContext);
/*  71 */     this.svgTexturePaint = new SVGTexturePaint(generatorContext);
/*  72 */     this.svgCustomPaint = new SVGCustomPaint(generatorContext);
/*  73 */     this.svgColor = new SVGColor(generatorContext);
/*  74 */     this.generatorContext = generatorContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getDefinitionSet() {
/*  82 */     List paintDefs = new LinkedList(this.svgLinearGradient.getDefinitionSet());
/*  83 */     paintDefs.addAll(this.svgTexturePaint.getDefinitionSet());
/*  84 */     paintDefs.addAll(this.svgCustomPaint.getDefinitionSet());
/*  85 */     paintDefs.addAll(this.svgColor.getDefinitionSet());
/*  86 */     return paintDefs;
/*     */   }
/*     */   
/*     */   public SVGTexturePaint getTexturePaintConverter() {
/*  90 */     return this.svgTexturePaint;
/*     */   }
/*     */   
/*     */   public SVGLinearGradient getGradientPaintConverter() {
/*  94 */     return this.svgLinearGradient;
/*     */   }
/*     */   
/*     */   public SVGCustomPaint getCustomPaintConverter() {
/*  98 */     return this.svgCustomPaint;
/*     */   }
/*     */   
/*     */   public SVGColor getColorConverter() {
/* 102 */     return this.svgColor;
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
/* 116 */     return toSVG(gc.getPaint());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPaintDescriptor toSVG(Paint paint) {
/* 126 */     SVGPaintDescriptor paintDesc = this.svgCustomPaint.toSVG(paint);
/*     */     
/* 128 */     if (paintDesc == null) {
/* 129 */       if (paint instanceof Color) {
/* 130 */         paintDesc = SVGColor.toSVG((Color)paint, this.generatorContext);
/* 131 */       } else if (paint instanceof GradientPaint) {
/* 132 */         paintDesc = this.svgLinearGradient.toSVG((GradientPaint)paint);
/* 133 */       } else if (paint instanceof TexturePaint) {
/* 134 */         paintDesc = this.svgTexturePaint.toSVG((TexturePaint)paint);
/*     */       } 
/*     */     }
/* 137 */     return paintDesc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGPaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */