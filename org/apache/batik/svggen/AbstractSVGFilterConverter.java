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
/*    */ public abstract class AbstractSVGFilterConverter
/*    */   implements ErrorConstants, SVGFilterConverter
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
/*    */   public AbstractSVGFilterConverter(SVGGeneratorContext generatorContext) {
/* 61 */     if (generatorContext == null)
/* 62 */       throw new SVGGraphics2DRuntimeException("generatorContext should not be null"); 
/* 63 */     this.generatorContext = generatorContext;
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
/* 74 */     return this.defSet;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final String doubleString(double value) {
/* 81 */     return this.generatorContext.doubleString(value);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/AbstractSVGFilterConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */