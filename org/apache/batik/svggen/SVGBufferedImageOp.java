/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ public class SVGBufferedImageOp
/*     */   extends AbstractSVGFilterConverter
/*     */ {
/*     */   private SVGLookupOp svgLookupOp;
/*     */   private SVGRescaleOp svgRescaleOp;
/*     */   private SVGConvolveOp svgConvolveOp;
/*     */   private SVGCustomBufferedImageOp svgCustomBufferedImageOp;
/*     */   
/*     */   public SVGBufferedImageOp(SVGGeneratorContext generatorContext) {
/*  68 */     super(generatorContext);
/*  69 */     this.svgLookupOp = new SVGLookupOp(generatorContext);
/*  70 */     this.svgRescaleOp = new SVGRescaleOp(generatorContext);
/*  71 */     this.svgConvolveOp = new SVGConvolveOp(generatorContext);
/*  72 */     this.svgCustomBufferedImageOp = new SVGCustomBufferedImageOp(generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getDefinitionSet() {
/*  81 */     List filterSet = new LinkedList(this.svgLookupOp.getDefinitionSet());
/*  82 */     filterSet.addAll(this.svgRescaleOp.getDefinitionSet());
/*  83 */     filterSet.addAll(this.svgConvolveOp.getDefinitionSet());
/*  84 */     filterSet.addAll(this.svgCustomBufferedImageOp.getDefinitionSet());
/*  85 */     return filterSet;
/*     */   }
/*     */   
/*     */   public SVGLookupOp getLookupOpConverter() {
/*  89 */     return this.svgLookupOp;
/*     */   }
/*     */   
/*     */   public SVGRescaleOp getRescaleOpConverter() {
/*  93 */     return this.svgRescaleOp;
/*     */   }
/*     */   
/*     */   public SVGConvolveOp getConvolveOpConverter() {
/*  97 */     return this.svgConvolveOp;
/*     */   }
/*     */   
/*     */   public SVGCustomBufferedImageOp getCustomBufferedImageOpConverter() {
/* 101 */     return this.svgCustomBufferedImageOp;
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
/*     */   public SVGFilterDescriptor toSVG(BufferedImageOp op, Rectangle filterRect) {
/* 114 */     SVGFilterDescriptor filterDesc = this.svgCustomBufferedImageOp.toSVG(op, filterRect);
/*     */ 
/*     */     
/* 117 */     if (filterDesc == null) {
/* 118 */       if (op instanceof java.awt.image.LookupOp) {
/* 119 */         filterDesc = this.svgLookupOp.toSVG(op, filterRect);
/* 120 */       } else if (op instanceof java.awt.image.RescaleOp) {
/* 121 */         filterDesc = this.svgRescaleOp.toSVG(op, filterRect);
/* 122 */       } else if (op instanceof java.awt.image.ConvolveOp) {
/* 123 */         filterDesc = this.svgConvolveOp.toSVG(op, filterRect);
/*     */       } 
/*     */     }
/* 126 */     return filterDesc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGBufferedImageOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */