/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.g2d.GraphicContext;
/*     */ import org.apache.batik.ext.awt.g2d.TransformStackElement;
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
/*     */ public class SVGGraphicContextConverter
/*     */ {
/*     */   private static final int GRAPHIC_CONTEXT_CONVERTER_COUNT = 6;
/*     */   private SVGTransform transformConverter;
/*     */   private SVGPaint paintConverter;
/*     */   private SVGBasicStroke strokeConverter;
/*     */   private SVGComposite compositeConverter;
/*     */   private SVGClip clipConverter;
/*     */   private SVGRenderingHints hintsConverter;
/*     */   private SVGFont fontConverter;
/*  48 */   private SVGConverter[] converters = new SVGConverter[6];
/*     */   
/*     */   public SVGTransform getTransformConverter() {
/*  51 */     return this.transformConverter;
/*  52 */   } public SVGPaint getPaintConverter() { return this.paintConverter; }
/*  53 */   public SVGBasicStroke getStrokeConverter() { return this.strokeConverter; }
/*  54 */   public SVGComposite getCompositeConverter() { return this.compositeConverter; }
/*  55 */   public SVGClip getClipConverter() { return this.clipConverter; }
/*  56 */   public SVGRenderingHints getHintsConverter() { return this.hintsConverter; } public SVGFont getFontConverter() {
/*  57 */     return this.fontConverter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGGraphicContextConverter(SVGGeneratorContext generatorContext) {
/*  64 */     if (generatorContext == null) {
/*  65 */       throw new SVGGraphics2DRuntimeException("generatorContext should not be null");
/*     */     }
/*  67 */     this.transformConverter = new SVGTransform(generatorContext);
/*  68 */     this.paintConverter = new SVGPaint(generatorContext);
/*  69 */     this.strokeConverter = new SVGBasicStroke(generatorContext);
/*  70 */     this.compositeConverter = new SVGComposite(generatorContext);
/*  71 */     this.clipConverter = new SVGClip(generatorContext);
/*  72 */     this.hintsConverter = new SVGRenderingHints(generatorContext);
/*  73 */     this.fontConverter = new SVGFont(generatorContext);
/*     */     
/*  75 */     int i = 0;
/*  76 */     this.converters[i++] = this.paintConverter;
/*  77 */     this.converters[i++] = this.strokeConverter;
/*  78 */     this.converters[i++] = this.compositeConverter;
/*  79 */     this.converters[i++] = this.clipConverter;
/*  80 */     this.converters[i++] = this.hintsConverter;
/*  81 */     this.converters[i++] = this.fontConverter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toSVG(TransformStackElement[] transformStack) {
/*  89 */     return this.transformConverter.toSVGTransform(transformStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGGraphicContext toSVG(GraphicContext gc) {
/*  98 */     Map<Object, Object> groupAttrMap = new HashMap<Object, Object>();
/*     */     
/* 100 */     for (SVGConverter converter : this.converters) {
/* 101 */       SVGDescriptor desc = converter.toSVG(gc);
/* 102 */       if (desc != null) {
/* 103 */         desc.getAttributeMap(groupAttrMap);
/*     */       }
/*     */     } 
/*     */     
/* 107 */     return new SVGGraphicContext(groupAttrMap, gc.getTransformStack());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getDefinitionSet() {
/* 116 */     List defSet = new LinkedList();
/* 117 */     for (SVGConverter converter : this.converters) defSet.addAll(converter.getDefinitionSet());
/*     */     
/* 119 */     return defSet;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGGraphicContextConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */