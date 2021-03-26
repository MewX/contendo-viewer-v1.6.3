/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.awt.image.ConvolveOp;
/*     */ import java.awt.image.Kernel;
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
/*     */ public class SVGConvolveOp
/*     */   extends AbstractSVGFilterConverter
/*     */ {
/*     */   public SVGConvolveOp(SVGGeneratorContext generatorContext) {
/*  42 */     super(generatorContext);
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
/*  59 */     if (filter instanceof ConvolveOp) {
/*  60 */       return toSVG((ConvolveOp)filter);
/*     */     }
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGFilterDescriptor toSVG(ConvolveOp convolveOp) {
/*  73 */     SVGFilterDescriptor filterDesc = (SVGFilterDescriptor)this.descMap.get(convolveOp);
/*     */     
/*  75 */     Document domFactory = this.generatorContext.domFactory;
/*     */     
/*  77 */     if (filterDesc == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  82 */       Kernel kernel = convolveOp.getKernel();
/*  83 */       Element filterDef = domFactory.createElementNS("http://www.w3.org/2000/svg", "filter");
/*     */       
/*  85 */       Element feConvolveMatrixDef = domFactory.createElementNS("http://www.w3.org/2000/svg", "feConvolveMatrix");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  90 */       feConvolveMatrixDef.setAttributeNS((String)null, "order", kernel.getWidth() + " " + kernel.getHeight());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  95 */       float[] data = kernel.getKernelData(null);
/*  96 */       StringBuffer kernelMatrixBuf = new StringBuffer(data.length * 8);
/*  97 */       for (float aData : data) {
/*  98 */         kernelMatrixBuf.append(doubleString(aData));
/*  99 */         kernelMatrixBuf.append(" ");
/*     */       } 
/*     */       
/* 102 */       feConvolveMatrixDef.setAttributeNS((String)null, "kernelMatrix", kernelMatrixBuf.toString().trim());
/*     */ 
/*     */ 
/*     */       
/* 106 */       filterDef.appendChild(feConvolveMatrixDef);
/*     */       
/* 108 */       filterDef.setAttributeNS((String)null, "id", this.generatorContext.idGenerator.generateID("convolve"));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 113 */       if (convolveOp.getEdgeCondition() == 1) {
/* 114 */         feConvolveMatrixDef.setAttributeNS((String)null, "edgeMode", "duplicate");
/*     */       } else {
/*     */         
/* 117 */         feConvolveMatrixDef.setAttributeNS((String)null, "edgeMode", "none");
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 125 */       StringBuffer filterAttrBuf = new StringBuffer("url(");
/* 126 */       filterAttrBuf.append("#");
/* 127 */       filterAttrBuf.append(filterDef.getAttributeNS((String)null, "id"));
/* 128 */       filterAttrBuf.append(")");
/*     */       
/* 130 */       filterDesc = new SVGFilterDescriptor(filterAttrBuf.toString(), filterDef);
/*     */ 
/*     */       
/* 133 */       this.defSet.add(filterDef);
/* 134 */       this.descMap.put(convolveOp, filterDesc);
/*     */     } 
/*     */     
/* 137 */     return filterDesc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGConvolveOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */