/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import org.apache.batik.ext.awt.g2d.GraphicContext;
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
/*     */ public class SVGClip
/*     */   extends AbstractSVGConverter
/*     */ {
/*  38 */   public static final Shape ORIGIN = new Line2D.Float(0.0F, 0.0F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public static final SVGClipDescriptor NO_CLIP = new SVGClipDescriptor("none", null);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SVGShape shapeConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGClip(SVGGeneratorContext generatorContext) {
/*  55 */     super(generatorContext);
/*  56 */     this.shapeConverter = new SVGShape(generatorContext);
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
/*     */   public SVGDescriptor toSVG(GraphicContext gc) {
/*  69 */     Shape clip = gc.getClip();
/*     */     
/*  71 */     SVGClipDescriptor clipDesc = null;
/*     */     
/*  73 */     if (clip != null) {
/*  74 */       StringBuffer clipPathAttrBuf = new StringBuffer("url(");
/*     */ 
/*     */       
/*  77 */       GeneralPath clipPath = new GeneralPath(clip);
/*     */ 
/*     */       
/*  80 */       ClipKey clipKey = new ClipKey(clipPath, this.generatorContext);
/*  81 */       clipDesc = (SVGClipDescriptor)this.descMap.get(clipKey);
/*     */       
/*  83 */       if (clipDesc == null) {
/*  84 */         Element clipDef = clipToSVG(clip);
/*  85 */         if (clipDef == null) {
/*  86 */           clipDesc = NO_CLIP;
/*     */         } else {
/*  88 */           clipPathAttrBuf.append("#");
/*  89 */           clipPathAttrBuf.append(clipDef.getAttributeNS((String)null, "id"));
/*  90 */           clipPathAttrBuf.append(")");
/*     */           
/*  92 */           clipDesc = new SVGClipDescriptor(clipPathAttrBuf.toString(), clipDef);
/*     */ 
/*     */           
/*  95 */           this.descMap.put(clipKey, clipDesc);
/*  96 */           this.defSet.add(clipDef);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 100 */       clipDesc = NO_CLIP;
/*     */     } 
/* 102 */     return clipDesc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Element clipToSVG(Shape clip) {
/* 113 */     Element clipDef = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "clipPath");
/*     */ 
/*     */     
/* 116 */     clipDef.setAttributeNS((String)null, "clipPathUnits", "userSpaceOnUse");
/*     */ 
/*     */     
/* 119 */     clipDef.setAttributeNS((String)null, "id", this.generatorContext.idGenerator.generateID("clipPath"));
/*     */ 
/*     */ 
/*     */     
/* 123 */     Element clipPath = this.shapeConverter.toSVG(clip);
/*     */ 
/*     */     
/* 126 */     if (clipPath != null) {
/* 127 */       clipDef.appendChild(clipPath);
/* 128 */       return clipDef;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     clipDef.appendChild(this.shapeConverter.toSVG(ORIGIN));
/* 135 */     return clipDef;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGClip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */