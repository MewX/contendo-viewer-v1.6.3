/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGAlphaComposite
/*     */   extends AbstractSVGConverter
/*     */ {
/*  52 */   private Map compositeDefsMap = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean backgroundAccessRequired = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAlphaComposite(SVGGeneratorContext generatorContext) {
/*  63 */     super(generatorContext);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     this.compositeDefsMap.put(AlphaComposite.Src, compositeToSVG(AlphaComposite.Src));
/*     */     
/*  70 */     this.compositeDefsMap.put(AlphaComposite.SrcIn, compositeToSVG(AlphaComposite.SrcIn));
/*     */     
/*  72 */     this.compositeDefsMap.put(AlphaComposite.SrcOut, compositeToSVG(AlphaComposite.SrcOut));
/*     */     
/*  74 */     this.compositeDefsMap.put(AlphaComposite.DstIn, compositeToSVG(AlphaComposite.DstIn));
/*     */     
/*  76 */     this.compositeDefsMap.put(AlphaComposite.DstOut, compositeToSVG(AlphaComposite.DstOut));
/*     */     
/*  78 */     this.compositeDefsMap.put(AlphaComposite.DstOver, compositeToSVG(AlphaComposite.DstOver));
/*     */     
/*  80 */     this.compositeDefsMap.put(AlphaComposite.Clear, compositeToSVG(AlphaComposite.Clear));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAlphaCompositeFilterSet() {
/*  88 */     return new LinkedList(this.compositeDefsMap.values());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean requiresBackgroundAccess() {
/*  96 */     return this.backgroundAccessRequired;
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
/* 110 */     return toSVG((AlphaComposite)gc.getComposite());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGCompositeDescriptor toSVG(AlphaComposite composite) {
/* 119 */     SVGCompositeDescriptor compositeDesc = (SVGCompositeDescriptor)this.descMap.get(composite);
/*     */ 
/*     */     
/* 122 */     if (compositeDesc == null) {
/*     */       
/* 124 */       String opacityValue = doubleString(composite.getAlpha());
/*     */ 
/*     */ 
/*     */       
/* 128 */       String filterValue = null;
/* 129 */       Element filterDef = null;
/* 130 */       if (composite.getRule() != 3) {
/*     */ 
/*     */ 
/*     */         
/* 134 */         AlphaComposite majorComposite = AlphaComposite.getInstance(composite.getRule());
/*     */         
/* 136 */         filterDef = (Element)this.compositeDefsMap.get(majorComposite);
/* 137 */         this.defSet.add(filterDef);
/*     */ 
/*     */         
/* 140 */         StringBuffer filterAttrBuf = new StringBuffer("url(");
/* 141 */         filterAttrBuf.append("#");
/* 142 */         filterAttrBuf.append(filterDef.getAttributeNS((String)null, "id"));
/* 143 */         filterAttrBuf.append(")");
/*     */         
/* 145 */         filterValue = filterAttrBuf.toString();
/*     */       } else {
/* 147 */         filterValue = "none";
/*     */       } 
/* 149 */       compositeDesc = new SVGCompositeDescriptor(opacityValue, filterValue, filterDef);
/*     */ 
/*     */       
/* 152 */       this.descMap.put(composite, compositeDesc);
/*     */     } 
/*     */     
/* 155 */     if (composite.getRule() != 3) {
/* 156 */       this.backgroundAccessRequired = true;
/*     */     }
/* 158 */     return compositeDesc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Element compositeToSVG(AlphaComposite composite) {
/*     */     Element compositeFilter, feComposite, feFlood, feMerge, feMergeNodeFlood, feMergeNodeComposite;
/* 168 */     String operator = null;
/*     */ 
/*     */     
/* 171 */     String input1 = null;
/*     */ 
/*     */     
/* 174 */     String input2 = null;
/*     */ 
/*     */ 
/*     */     
/* 178 */     String k2 = "0";
/*     */ 
/*     */     
/* 181 */     String id = null;
/*     */     
/* 183 */     switch (composite.getRule()) {
/*     */       case 1:
/* 185 */         operator = "arithmetic";
/* 186 */         input1 = "SourceGraphic";
/* 187 */         input2 = "BackgroundImage";
/* 188 */         id = "alphaCompositeClear";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 231 */         compositeFilter = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "filter");
/*     */ 
/*     */         
/* 234 */         compositeFilter.setAttributeNS((String)null, "id", id);
/* 235 */         compositeFilter.setAttributeNS((String)null, "filterUnits", "objectBoundingBox");
/*     */         
/* 237 */         compositeFilter.setAttributeNS((String)null, "x", "0%");
/* 238 */         compositeFilter.setAttributeNS((String)null, "y", "0%");
/* 239 */         compositeFilter.setAttributeNS((String)null, "width", "100%");
/*     */         
/* 241 */         compositeFilter.setAttributeNS((String)null, "height", "100%");
/*     */ 
/*     */         
/* 244 */         feComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feComposite");
/*     */ 
/*     */         
/* 247 */         feComposite.setAttributeNS((String)null, "operator", operator);
/* 248 */         feComposite.setAttributeNS((String)null, "in", input1);
/* 249 */         feComposite.setAttributeNS((String)null, "in2", input2);
/* 250 */         feComposite.setAttributeNS((String)null, "k2", k2);
/* 251 */         feComposite.setAttributeNS((String)null, "result", "composite");
/*     */         
/* 253 */         feFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feFlood");
/*     */ 
/*     */         
/* 256 */         feFlood.setAttributeNS((String)null, "flood-color", "white");
/* 257 */         feFlood.setAttributeNS((String)null, "flood-opacity", "1");
/* 258 */         feFlood.setAttributeNS((String)null, "result", "flood");
/*     */ 
/*     */         
/* 261 */         feMerge = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMerge");
/*     */ 
/*     */         
/* 264 */         feMergeNodeFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode");
/*     */ 
/*     */         
/* 267 */         feMergeNodeFlood.setAttributeNS((String)null, "in", "flood");
/* 268 */         feMergeNodeComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode");
/*     */ 
/*     */         
/* 271 */         feMergeNodeComposite.setAttributeNS((String)null, "in", "composite");
/*     */ 
/*     */         
/* 274 */         feMerge.appendChild(feMergeNodeFlood);
/* 275 */         feMerge.appendChild(feMergeNodeComposite);
/*     */         
/* 277 */         compositeFilter.appendChild(feFlood);
/* 278 */         compositeFilter.appendChild(feComposite);
/* 279 */         compositeFilter.appendChild(feMerge);
/*     */         
/* 281 */         return compositeFilter;case 2: operator = "arithmetic"; input1 = "SourceGraphic"; input2 = "BackgroundImage"; id = "alphaCompositeSrc"; k2 = "1"; compositeFilter = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "filter"); compositeFilter.setAttributeNS((String)null, "id", id); compositeFilter.setAttributeNS((String)null, "filterUnits", "objectBoundingBox"); compositeFilter.setAttributeNS((String)null, "x", "0%"); compositeFilter.setAttributeNS((String)null, "y", "0%"); compositeFilter.setAttributeNS((String)null, "width", "100%"); compositeFilter.setAttributeNS((String)null, "height", "100%"); feComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feComposite"); feComposite.setAttributeNS((String)null, "operator", operator); feComposite.setAttributeNS((String)null, "in", input1); feComposite.setAttributeNS((String)null, "in2", input2); feComposite.setAttributeNS((String)null, "k2", k2); feComposite.setAttributeNS((String)null, "result", "composite"); feFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feFlood"); feFlood.setAttributeNS((String)null, "flood-color", "white"); feFlood.setAttributeNS((String)null, "flood-opacity", "1"); feFlood.setAttributeNS((String)null, "result", "flood"); feMerge = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMerge"); feMergeNodeFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeFlood.setAttributeNS((String)null, "in", "flood"); feMergeNodeComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeComposite.setAttributeNS((String)null, "in", "composite"); feMerge.appendChild(feMergeNodeFlood); feMerge.appendChild(feMergeNodeComposite); compositeFilter.appendChild(feFlood); compositeFilter.appendChild(feComposite); compositeFilter.appendChild(feMerge); return compositeFilter;case 5: operator = "in"; input1 = "SourceGraphic"; input2 = "BackgroundImage"; id = "alphaCompositeSrcIn"; compositeFilter = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "filter"); compositeFilter.setAttributeNS((String)null, "id", id); compositeFilter.setAttributeNS((String)null, "filterUnits", "objectBoundingBox"); compositeFilter.setAttributeNS((String)null, "x", "0%"); compositeFilter.setAttributeNS((String)null, "y", "0%"); compositeFilter.setAttributeNS((String)null, "width", "100%"); compositeFilter.setAttributeNS((String)null, "height", "100%"); feComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feComposite"); feComposite.setAttributeNS((String)null, "operator", operator); feComposite.setAttributeNS((String)null, "in", input1); feComposite.setAttributeNS((String)null, "in2", input2); feComposite.setAttributeNS((String)null, "k2", k2); feComposite.setAttributeNS((String)null, "result", "composite"); feFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feFlood"); feFlood.setAttributeNS((String)null, "flood-color", "white"); feFlood.setAttributeNS((String)null, "flood-opacity", "1"); feFlood.setAttributeNS((String)null, "result", "flood"); feMerge = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMerge"); feMergeNodeFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeFlood.setAttributeNS((String)null, "in", "flood"); feMergeNodeComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeComposite.setAttributeNS((String)null, "in", "composite"); feMerge.appendChild(feMergeNodeFlood); feMerge.appendChild(feMergeNodeComposite); compositeFilter.appendChild(feFlood); compositeFilter.appendChild(feComposite); compositeFilter.appendChild(feMerge); return compositeFilter;case 7: operator = "out"; input1 = "SourceGraphic"; input2 = "BackgroundImage"; id = "alphaCompositeSrcOut"; compositeFilter = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "filter"); compositeFilter.setAttributeNS((String)null, "id", id); compositeFilter.setAttributeNS((String)null, "filterUnits", "objectBoundingBox"); compositeFilter.setAttributeNS((String)null, "x", "0%"); compositeFilter.setAttributeNS((String)null, "y", "0%"); compositeFilter.setAttributeNS((String)null, "width", "100%"); compositeFilter.setAttributeNS((String)null, "height", "100%"); feComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feComposite"); feComposite.setAttributeNS((String)null, "operator", operator); feComposite.setAttributeNS((String)null, "in", input1); feComposite.setAttributeNS((String)null, "in2", input2); feComposite.setAttributeNS((String)null, "k2", k2); feComposite.setAttributeNS((String)null, "result", "composite"); feFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feFlood"); feFlood.setAttributeNS((String)null, "flood-color", "white"); feFlood.setAttributeNS((String)null, "flood-opacity", "1"); feFlood.setAttributeNS((String)null, "result", "flood"); feMerge = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMerge"); feMergeNodeFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeFlood.setAttributeNS((String)null, "in", "flood"); feMergeNodeComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeComposite.setAttributeNS((String)null, "in", "composite"); feMerge.appendChild(feMergeNodeFlood); feMerge.appendChild(feMergeNodeComposite); compositeFilter.appendChild(feFlood); compositeFilter.appendChild(feComposite); compositeFilter.appendChild(feMerge); return compositeFilter;case 6: operator = "in"; input2 = "SourceGraphic"; input1 = "BackgroundImage"; id = "alphaCompositeDstIn"; compositeFilter = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "filter"); compositeFilter.setAttributeNS((String)null, "id", id); compositeFilter.setAttributeNS((String)null, "filterUnits", "objectBoundingBox"); compositeFilter.setAttributeNS((String)null, "x", "0%"); compositeFilter.setAttributeNS((String)null, "y", "0%"); compositeFilter.setAttributeNS((String)null, "width", "100%"); compositeFilter.setAttributeNS((String)null, "height", "100%"); feComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feComposite"); feComposite.setAttributeNS((String)null, "operator", operator); feComposite.setAttributeNS((String)null, "in", input1); feComposite.setAttributeNS((String)null, "in2", input2); feComposite.setAttributeNS((String)null, "k2", k2); feComposite.setAttributeNS((String)null, "result", "composite"); feFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feFlood"); feFlood.setAttributeNS((String)null, "flood-color", "white"); feFlood.setAttributeNS((String)null, "flood-opacity", "1"); feFlood.setAttributeNS((String)null, "result", "flood"); feMerge = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMerge"); feMergeNodeFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeFlood.setAttributeNS((String)null, "in", "flood"); feMergeNodeComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeComposite.setAttributeNS((String)null, "in", "composite"); feMerge.appendChild(feMergeNodeFlood); feMerge.appendChild(feMergeNodeComposite); compositeFilter.appendChild(feFlood); compositeFilter.appendChild(feComposite); compositeFilter.appendChild(feMerge); return compositeFilter;case 8: operator = "out"; input2 = "SourceGraphic"; input1 = "BackgroundImage"; id = "alphaCompositeDstOut"; compositeFilter = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "filter"); compositeFilter.setAttributeNS((String)null, "id", id); compositeFilter.setAttributeNS((String)null, "filterUnits", "objectBoundingBox"); compositeFilter.setAttributeNS((String)null, "x", "0%"); compositeFilter.setAttributeNS((String)null, "y", "0%"); compositeFilter.setAttributeNS((String)null, "width", "100%"); compositeFilter.setAttributeNS((String)null, "height", "100%"); feComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feComposite"); feComposite.setAttributeNS((String)null, "operator", operator); feComposite.setAttributeNS((String)null, "in", input1); feComposite.setAttributeNS((String)null, "in2", input2); feComposite.setAttributeNS((String)null, "k2", k2); feComposite.setAttributeNS((String)null, "result", "composite"); feFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feFlood"); feFlood.setAttributeNS((String)null, "flood-color", "white"); feFlood.setAttributeNS((String)null, "flood-opacity", "1"); feFlood.setAttributeNS((String)null, "result", "flood"); feMerge = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMerge"); feMergeNodeFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeFlood.setAttributeNS((String)null, "in", "flood"); feMergeNodeComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeComposite.setAttributeNS((String)null, "in", "composite"); feMerge.appendChild(feMergeNodeFlood); feMerge.appendChild(feMergeNodeComposite); compositeFilter.appendChild(feFlood); compositeFilter.appendChild(feComposite); compositeFilter.appendChild(feMerge); return compositeFilter;case 4: operator = "over"; input2 = "SourceGraphic"; input1 = "BackgroundImage"; id = "alphaCompositeDstOver"; compositeFilter = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "filter"); compositeFilter.setAttributeNS((String)null, "id", id); compositeFilter.setAttributeNS((String)null, "filterUnits", "objectBoundingBox"); compositeFilter.setAttributeNS((String)null, "x", "0%"); compositeFilter.setAttributeNS((String)null, "y", "0%"); compositeFilter.setAttributeNS((String)null, "width", "100%"); compositeFilter.setAttributeNS((String)null, "height", "100%"); feComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feComposite"); feComposite.setAttributeNS((String)null, "operator", operator); feComposite.setAttributeNS((String)null, "in", input1); feComposite.setAttributeNS((String)null, "in2", input2); feComposite.setAttributeNS((String)null, "k2", k2); feComposite.setAttributeNS((String)null, "result", "composite"); feFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feFlood"); feFlood.setAttributeNS((String)null, "flood-color", "white"); feFlood.setAttributeNS((String)null, "flood-opacity", "1"); feFlood.setAttributeNS((String)null, "result", "flood"); feMerge = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMerge"); feMergeNodeFlood = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeFlood.setAttributeNS((String)null, "in", "flood"); feMergeNodeComposite = this.generatorContext.domFactory.createElementNS("http://www.w3.org/2000/svg", "feMergeNode"); feMergeNodeComposite.setAttributeNS((String)null, "in", "composite"); feMerge.appendChild(feMergeNodeFlood); feMerge.appendChild(feMergeNodeComposite); compositeFilter.appendChild(feFlood); compositeFilter.appendChild(feComposite); compositeFilter.appendChild(feMerge); return compositeFilter;
/*     */     } 
/*     */     throw new RuntimeException("invalid rule:" + composite.getRule());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGAlphaComposite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */