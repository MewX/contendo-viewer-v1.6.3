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
/*    */ public class SVGTransformDescriptor
/*    */   implements SVGDescriptor, SVGSyntax
/*    */ {
/*    */   private String transform;
/*    */   
/*    */   public SVGTransformDescriptor(String transform) {
/* 37 */     this.transform = transform;
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
/* 49 */     if (attrMap == null) {
/* 50 */       attrMap = new HashMap<Object, Object>();
/*    */     }
/* 52 */     attrMap.put("transform", this.transform);
/*    */     
/* 54 */     return attrMap;
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
/*    */   public List getDefinitionSet(List defSet) {
/* 67 */     if (defSet == null) {
/* 68 */       defSet = new LinkedList();
/*    */     }
/* 70 */     return defSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGTransformDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */