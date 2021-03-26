/*    */ package org.apache.batik.svggen;
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
/*    */ 
/*    */ 
/*    */ public abstract class SVGGraphicObjectConverter
/*    */   implements SVGSyntax
/*    */ {
/*    */   protected SVGGeneratorContext generatorContext;
/*    */   
/*    */   public SVGGraphicObjectConverter(SVGGeneratorContext generatorContext) {
/* 39 */     if (generatorContext == null)
/* 40 */       throw new SVGGraphics2DRuntimeException("generatorContext should not be null"); 
/* 41 */     this.generatorContext = generatorContext;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final String doubleString(double value) {
/* 48 */     return this.generatorContext.doubleString(value);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGGraphicObjectConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */