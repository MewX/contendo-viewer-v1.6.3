/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ public class SVGPaintDescriptor
/*    */   implements SVGDescriptor, SVGSyntax
/*    */ {
/*    */   private Element def;
/*    */   private String paintValue;
/*    */   private String opacityValue;
/*    */   
/*    */   public SVGPaintDescriptor(String paintValue, String opacityValue) {
/* 42 */     this.paintValue = paintValue;
/* 43 */     this.opacityValue = opacityValue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGPaintDescriptor(String paintValue, String opacityValue, Element def) {
/* 49 */     this(paintValue, opacityValue);
/* 50 */     this.def = def;
/*    */   }
/*    */   
/*    */   public String getPaintValue() {
/* 54 */     return this.paintValue;
/*    */   }
/*    */   
/*    */   public String getOpacityValue() {
/* 58 */     return this.opacityValue;
/*    */   }
/*    */   
/*    */   public Element getDef() {
/* 62 */     return this.def;
/*    */   }
/*    */   
/*    */   public Map getAttributeMap(Map<Object, Object> attrMap) {
/* 66 */     if (attrMap == null) {
/* 67 */       attrMap = new HashMap<Object, Object>();
/*    */     }
/* 69 */     attrMap.put("fill", this.paintValue);
/* 70 */     attrMap.put("stroke", this.paintValue);
/* 71 */     attrMap.put("fill-opacity", this.opacityValue);
/* 72 */     attrMap.put("stroke-opacity", this.opacityValue);
/*    */     
/* 74 */     return attrMap;
/*    */   }
/*    */   
/*    */   public List getDefinitionSet(List<Element> defSet) {
/* 78 */     if (defSet == null) {
/* 79 */       defSet = new LinkedList();
/*    */     }
/* 81 */     if (this.def != null) {
/* 82 */       defSet.add(this.def);
/*    */     }
/* 84 */     return defSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGPaintDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */