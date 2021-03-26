/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.awt.image.RescaleOp;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGRescaleOp
/*     */   extends AbstractSVGFilterConverter
/*     */ {
/*     */   public SVGRescaleOp(SVGGeneratorContext generatorContext) {
/*  46 */     super(generatorContext);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGFilterDescriptor toSVG(BufferedImageOp filter, Rectangle filterRect) {
/*  63 */     if (filter instanceof RescaleOp) {
/*  64 */       return toSVG((RescaleOp)filter);
/*     */     }
/*  66 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGFilterDescriptor toSVG(RescaleOp rescaleOp) {
/*  77 */     SVGFilterDescriptor filterDesc = (SVGFilterDescriptor)this.descMap.get(rescaleOp);
/*     */ 
/*     */     
/*  80 */     Document domFactory = this.generatorContext.domFactory;
/*     */     
/*  82 */     if (filterDesc == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  87 */       Element filterDef = domFactory.createElementNS("http://www.w3.org/2000/svg", "filter");
/*     */       
/*  89 */       Element feComponentTransferDef = domFactory.createElementNS("http://www.w3.org/2000/svg", "feComponentTransfer");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 103 */       float[] offsets = rescaleOp.getOffsets(null);
/* 104 */       float[] scaleFactors = rescaleOp.getScaleFactors(null);
/* 105 */       if (offsets.length != scaleFactors.length) {
/* 106 */         throw new SVGGraphics2DRuntimeException("RescapeOp offsets and scaleFactor array length do not match");
/*     */       }
/* 108 */       if (offsets.length != 1 && offsets.length != 3 && offsets.length != 4)
/*     */       {
/*     */         
/* 111 */         throw new SVGGraphics2DRuntimeException("BufferedImage RescaleOp should have 1, 3 or 4 scale factors");
/*     */       }
/* 113 */       Element feFuncR = domFactory.createElementNS("http://www.w3.org/2000/svg", "feFuncR");
/*     */       
/* 115 */       Element feFuncG = domFactory.createElementNS("http://www.w3.org/2000/svg", "feFuncG");
/*     */       
/* 117 */       Element feFuncB = domFactory.createElementNS("http://www.w3.org/2000/svg", "feFuncB");
/*     */       
/* 119 */       Element feFuncA = null;
/* 120 */       String type = "linear";
/*     */       
/* 122 */       if (offsets.length == 1) {
/* 123 */         String slope = doubleString(scaleFactors[0]);
/* 124 */         String intercept = doubleString(offsets[0]);
/* 125 */         feFuncR.setAttributeNS((String)null, "type", type);
/* 126 */         feFuncG.setAttributeNS((String)null, "type", type);
/* 127 */         feFuncB.setAttributeNS((String)null, "type", type);
/* 128 */         feFuncR.setAttributeNS((String)null, "slope", slope);
/* 129 */         feFuncG.setAttributeNS((String)null, "slope", slope);
/* 130 */         feFuncB.setAttributeNS((String)null, "slope", slope);
/* 131 */         feFuncR.setAttributeNS((String)null, "intercept", intercept);
/* 132 */         feFuncG.setAttributeNS((String)null, "intercept", intercept);
/* 133 */         feFuncB.setAttributeNS((String)null, "intercept", intercept);
/*     */       }
/* 135 */       else if (offsets.length >= 3) {
/* 136 */         feFuncR.setAttributeNS((String)null, "type", type);
/* 137 */         feFuncG.setAttributeNS((String)null, "type", type);
/* 138 */         feFuncB.setAttributeNS((String)null, "type", type);
/* 139 */         feFuncR.setAttributeNS((String)null, "slope", doubleString(scaleFactors[0]));
/*     */         
/* 141 */         feFuncG.setAttributeNS((String)null, "slope", doubleString(scaleFactors[1]));
/*     */         
/* 143 */         feFuncB.setAttributeNS((String)null, "slope", doubleString(scaleFactors[2]));
/*     */         
/* 145 */         feFuncR.setAttributeNS((String)null, "intercept", doubleString(offsets[0]));
/*     */         
/* 147 */         feFuncG.setAttributeNS((String)null, "intercept", doubleString(offsets[1]));
/*     */         
/* 149 */         feFuncB.setAttributeNS((String)null, "intercept", doubleString(offsets[2]));
/*     */ 
/*     */         
/* 152 */         if (offsets.length == 4) {
/* 153 */           feFuncA = domFactory.createElementNS("http://www.w3.org/2000/svg", "feFuncA");
/*     */           
/* 155 */           feFuncA.setAttributeNS((String)null, "type", type);
/* 156 */           feFuncA.setAttributeNS((String)null, "slope", doubleString(scaleFactors[3]));
/*     */           
/* 158 */           feFuncA.setAttributeNS((String)null, "intercept", doubleString(offsets[3]));
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 163 */       feComponentTransferDef.appendChild(feFuncR);
/* 164 */       feComponentTransferDef.appendChild(feFuncG);
/* 165 */       feComponentTransferDef.appendChild(feFuncB);
/* 166 */       if (feFuncA != null) {
/* 167 */         feComponentTransferDef.appendChild(feFuncA);
/*     */       }
/* 169 */       filterDef.appendChild(feComponentTransferDef);
/*     */       
/* 171 */       filterDef.setAttributeNS((String)null, "id", this.generatorContext.idGenerator.generateID("componentTransfer"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 186 */       String filterAttrBuf = "url(#" + filterDef.getAttributeNS((String)null, "id") + ")";
/*     */       
/* 188 */       filterDesc = new SVGFilterDescriptor(filterAttrBuf, filterDef);
/*     */       
/* 190 */       this.defSet.add(filterDef);
/* 191 */       this.descMap.put(rescaleOp, filterDesc);
/*     */     } 
/*     */     
/* 194 */     return filterDesc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGRescaleOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */