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
/*    */ public class SVGHintsDescriptor
/*    */   implements SVGDescriptor, SVGSyntax
/*    */ {
/*    */   private String colorInterpolation;
/*    */   private String colorRendering;
/*    */   private String textRendering;
/*    */   private String shapeRendering;
/*    */   private String imageRendering;
/*    */   
/*    */   public SVGHintsDescriptor(String colorInterpolation, String colorRendering, String textRendering, String shapeRendering, String imageRendering) {
/* 48 */     if (colorInterpolation == null || colorRendering == null || textRendering == null || shapeRendering == null || imageRendering == null)
/*    */     {
/*    */ 
/*    */ 
/*    */       
/* 53 */       throw new SVGGraphics2DRuntimeException("none of the hints description parameters should be null");
/*    */     }
/* 55 */     this.colorInterpolation = colorInterpolation;
/* 56 */     this.colorRendering = colorRendering;
/* 57 */     this.textRendering = textRendering;
/* 58 */     this.shapeRendering = shapeRendering;
/* 59 */     this.imageRendering = imageRendering;
/*    */   }
/*    */   
/*    */   public Map getAttributeMap(Map<Object, Object> attrMap) {
/* 63 */     if (attrMap == null) {
/* 64 */       attrMap = new HashMap<Object, Object>();
/*    */     }
/* 66 */     attrMap.put("color-interpolation", this.colorInterpolation);
/* 67 */     attrMap.put("color-rendering", this.colorRendering);
/* 68 */     attrMap.put("text-rendering", this.textRendering);
/* 69 */     attrMap.put("shape-rendering", this.shapeRendering);
/* 70 */     attrMap.put("image-rendering", this.imageRendering);
/*    */     
/* 72 */     return attrMap;
/*    */   }
/*    */   
/*    */   public List getDefinitionSet(List defSet) {
/* 76 */     if (defSet == null) {
/* 77 */       defSet = new LinkedList();
/*    */     }
/* 79 */     return defSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGHintsDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */