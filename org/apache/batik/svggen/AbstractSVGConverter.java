/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSVGConverter
/*    */   implements ErrorConstants, SVGConverter
/*    */ {
/*    */   protected SVGGeneratorContext generatorContext;
/* 48 */   protected Map descMap = new HashMap<Object, Object>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   protected List defSet = new LinkedList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractSVGConverter(SVGGeneratorContext generatorContext) {
/* 61 */     if (generatorContext == null) {
/* 62 */       throw new SVGGraphics2DRuntimeException("generatorContext should not be null");
/*    */     }
/* 64 */     this.generatorContext = generatorContext;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getDefinitionSet() {
/* 75 */     return this.defSet;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final String doubleString(double value) {
/* 82 */     return this.generatorContext.doubleString(value);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/AbstractSVGConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */