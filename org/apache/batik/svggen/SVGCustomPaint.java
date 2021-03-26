/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.awt.Paint;
/*    */ import org.apache.batik.ext.awt.g2d.GraphicContext;
/*    */ import org.w3c.dom.Element;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SVGCustomPaint
/*    */   extends AbstractSVGConverter
/*    */ {
/*    */   public SVGCustomPaint(SVGGeneratorContext generatorContext) {
/* 39 */     super(generatorContext);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGDescriptor toSVG(GraphicContext gc) {
/* 53 */     return toSVG(gc.getPaint());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGPaintDescriptor toSVG(Paint paint) {
/* 63 */     SVGPaintDescriptor paintDesc = (SVGPaintDescriptor)this.descMap.get(paint);
/*    */     
/* 65 */     if (paintDesc == null) {
/*    */ 
/*    */       
/* 68 */       paintDesc = this.generatorContext.extensionHandler.handlePaint(paint, this.generatorContext);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 73 */       if (paintDesc != null) {
/* 74 */         Element def = paintDesc.getDef();
/* 75 */         if (def != null) this.defSet.add(def); 
/* 76 */         this.descMap.put(paint, paintDesc);
/*    */       } 
/*    */     } 
/*    */     
/* 80 */     return paintDesc;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGCustomPaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */