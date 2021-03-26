/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Composite;
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
/*     */ public class SVGComposite
/*     */   implements SVGConverter
/*     */ {
/*     */   private SVGAlphaComposite svgAlphaComposite;
/*     */   private SVGCustomComposite svgCustomComposite;
/*     */   
/*     */   public SVGComposite(SVGGeneratorContext generatorContext) {
/*  61 */     this.svgAlphaComposite = new SVGAlphaComposite(generatorContext);
/*  62 */     this.svgCustomComposite = new SVGCustomComposite(generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getDefinitionSet() {
/*  70 */     List compositeDefs = new LinkedList(this.svgAlphaComposite.getDefinitionSet());
/*  71 */     compositeDefs.addAll(this.svgCustomComposite.getDefinitionSet());
/*  72 */     return compositeDefs;
/*     */   }
/*     */   
/*     */   public SVGAlphaComposite getAlphaCompositeConverter() {
/*  76 */     return this.svgAlphaComposite;
/*     */   }
/*     */   
/*     */   public SVGCustomComposite getCustomCompositeConverter() {
/*  80 */     return this.svgCustomComposite;
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
/*  94 */     return toSVG(gc.getComposite());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGCompositeDescriptor toSVG(Composite composite) {
/* 103 */     if (composite instanceof AlphaComposite) {
/* 104 */       return this.svgAlphaComposite.toSVG((AlphaComposite)composite);
/*     */     }
/* 106 */     return this.svgCustomComposite.toSVG(composite);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGComposite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */