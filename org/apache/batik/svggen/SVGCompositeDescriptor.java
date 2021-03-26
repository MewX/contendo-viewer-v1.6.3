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
/*    */ public class SVGCompositeDescriptor
/*    */   implements SVGDescriptor, SVGSyntax
/*    */ {
/*    */   private Element def;
/*    */   private String opacityValue;
/*    */   private String filterValue;
/*    */   
/*    */   public SVGCompositeDescriptor(String opacityValue, String filterValue) {
/* 42 */     this.opacityValue = opacityValue;
/* 43 */     this.filterValue = filterValue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGCompositeDescriptor(String opacityValue, String filterValue, Element def) {
/* 49 */     this(opacityValue, filterValue);
/* 50 */     this.def = def;
/*    */   }
/*    */   
/*    */   public String getOpacityValue() {
/* 54 */     return this.opacityValue;
/*    */   }
/*    */   
/*    */   public String getFilterValue() {
/* 58 */     return this.filterValue;
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
/* 69 */     attrMap.put("opacity", this.opacityValue);
/* 70 */     attrMap.put("filter", this.filterValue);
/*    */     
/* 72 */     return attrMap;
/*    */   }
/*    */   
/*    */   public List getDefinitionSet(List<Element> defSet) {
/* 76 */     if (defSet == null) {
/* 77 */       defSet = new LinkedList();
/*    */     }
/* 79 */     if (this.def != null) {
/* 80 */       defSet.add(this.def);
/*    */     }
/* 82 */     return defSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGCompositeDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */