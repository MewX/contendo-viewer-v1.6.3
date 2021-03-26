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
/*    */ public class SVGStrokeDescriptor
/*    */   implements SVGDescriptor, SVGSyntax
/*    */ {
/*    */   private String strokeWidth;
/*    */   private String capStyle;
/*    */   private String joinStyle;
/*    */   private String miterLimit;
/*    */   private String dashArray;
/*    */   private String dashOffset;
/*    */   
/*    */   public SVGStrokeDescriptor(String strokeWidth, String capStyle, String joinStyle, String miterLimit, String dashArray, String dashOffset) {
/* 45 */     if (strokeWidth == null || capStyle == null || joinStyle == null || miterLimit == null || dashArray == null || dashOffset == null)
/*    */     {
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 51 */       throw new SVGGraphics2DRuntimeException("none of the stroke description parameters should be null");
/*    */     }
/* 53 */     this.strokeWidth = strokeWidth;
/* 54 */     this.capStyle = capStyle;
/* 55 */     this.joinStyle = joinStyle;
/* 56 */     this.miterLimit = miterLimit;
/* 57 */     this.dashArray = dashArray;
/* 58 */     this.dashOffset = dashOffset;
/*    */   }
/*    */   
/* 61 */   String getStrokeWidth() { return this.strokeWidth; }
/* 62 */   String getCapStyle() { return this.capStyle; }
/* 63 */   String getJoinStyle() { return this.joinStyle; }
/* 64 */   String getMiterLimit() { return this.miterLimit; }
/* 65 */   String getDashArray() { return this.dashArray; } String getDashOffset() {
/* 66 */     return this.dashOffset;
/*    */   }
/*    */   public Map getAttributeMap(Map<Object, Object> attrMap) {
/* 69 */     if (attrMap == null) {
/* 70 */       attrMap = new HashMap<Object, Object>();
/*    */     }
/* 72 */     attrMap.put("stroke-width", this.strokeWidth);
/* 73 */     attrMap.put("stroke-linecap", this.capStyle);
/* 74 */     attrMap.put("stroke-linejoin", this.joinStyle);
/* 75 */     attrMap.put("stroke-miterlimit", this.miterLimit);
/* 76 */     attrMap.put("stroke-dasharray", this.dashArray);
/* 77 */     attrMap.put("stroke-dashoffset", this.dashOffset);
/*    */     
/* 79 */     return attrMap;
/*    */   }
/*    */   
/*    */   public List getDefinitionSet(List defSet) {
/* 83 */     if (defSet == null) {
/* 84 */       defSet = new LinkedList();
/*    */     }
/* 86 */     return defSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGStrokeDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */