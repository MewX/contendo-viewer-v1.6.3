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
/*    */ 
/*    */ public class SVGFontDescriptor
/*    */   implements SVGDescriptor, SVGSyntax
/*    */ {
/*    */   private Element def;
/*    */   private String fontSize;
/*    */   private String fontWeight;
/*    */   private String fontStyle;
/*    */   private String fontFamily;
/*    */   
/*    */   public SVGFontDescriptor(String fontSize, String fontWeight, String fontStyle, String fontFamily, Element def) {
/* 50 */     if (fontSize == null || fontWeight == null || fontStyle == null || fontFamily == null)
/*    */     {
/*    */ 
/*    */       
/* 54 */       throw new SVGGraphics2DRuntimeException("none of the font description parameters should be null");
/*    */     }
/* 56 */     this.fontSize = fontSize;
/* 57 */     this.fontWeight = fontWeight;
/* 58 */     this.fontStyle = fontStyle;
/* 59 */     this.fontFamily = fontFamily;
/* 60 */     this.def = def;
/*    */   }
/*    */   
/*    */   public Map getAttributeMap(Map<Object, Object> attrMap) {
/* 64 */     if (attrMap == null) {
/* 65 */       attrMap = new HashMap<Object, Object>();
/*    */     }
/* 67 */     attrMap.put("font-size", this.fontSize);
/* 68 */     attrMap.put("font-weight", this.fontWeight);
/* 69 */     attrMap.put("font-style", this.fontStyle);
/* 70 */     attrMap.put("font-family", this.fontFamily);
/*    */     
/* 72 */     return attrMap;
/*    */   }
/*    */   
/*    */   public Element getDef() {
/* 76 */     return this.def;
/*    */   }
/*    */   
/*    */   public List getDefinitionSet(List<Element> defSet) {
/* 80 */     if (defSet == null) {
/* 81 */       defSet = new LinkedList();
/*    */     }
/* 83 */     if (this.def != null && !defSet.contains(this.def)) {
/* 84 */       defSet.add(this.def);
/*    */     }
/* 86 */     return defSet;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGFontDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */