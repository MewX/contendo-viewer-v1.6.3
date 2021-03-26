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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SVGClipDescriptor
/*    */   implements SVGDescriptor, SVGSyntax
/*    */ {
/*    */   private String clipPathValue;
/*    */   private Element clipPathDef;
/*    */   
/*    */   public SVGClipDescriptor(String clipPathValue, Element clipPathDef) {
/* 46 */     if (clipPathValue == null) {
/* 47 */       throw new SVGGraphics2DRuntimeException("clipPathValue should not be null");
/*    */     }
/* 49 */     this.clipPathValue = clipPathValue;
/* 50 */     this.clipPathDef = clipPathDef;
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
/*    */   public Map getAttributeMap(Map<Object, Object> attrMap) {
/* 62 */     if (attrMap == null) {
/* 63 */       attrMap = new HashMap<Object, Object>();
/*    */     }
/* 65 */     attrMap.put("clip-path", this.clipPathValue);
/*    */     
/* 67 */     return attrMap;
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
/*    */   public List getDefinitionSet(List<Element> defSet) {
/* 81 */     if (defSet == null) {
/* 82 */       defSet = new LinkedList();
/*    */     }
/* 84 */     if (this.clipPathDef != null) {
/* 85 */       defSet.add(this.clipPathDef);
/*    */     }
/* 87 */     return defSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGClipDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */