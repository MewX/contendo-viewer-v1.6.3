/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.apache.batik.util.SVGConstants;
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
/*    */ 
/*    */ 
/*    */ public class DefaultStyleHandler
/*    */   implements StyleHandler, SVGConstants
/*    */ {
/* 46 */   static Map ignoreAttributes = new HashMap<Object, Object>();
/*    */ 
/*    */   
/*    */   static {
/* 50 */     Set<String> textAttributes = new HashSet();
/* 51 */     textAttributes.add("font-size");
/* 52 */     textAttributes.add("font-family");
/* 53 */     textAttributes.add("font-style");
/* 54 */     textAttributes.add("font-weight");
/*    */     
/* 56 */     ignoreAttributes.put("rect", textAttributes);
/* 57 */     ignoreAttributes.put("circle", textAttributes);
/* 58 */     ignoreAttributes.put("ellipse", textAttributes);
/* 59 */     ignoreAttributes.put("polygon", textAttributes);
/* 60 */     ignoreAttributes.put("polygon", textAttributes);
/* 61 */     ignoreAttributes.put("line", textAttributes);
/* 62 */     ignoreAttributes.put("path", textAttributes);
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
/*    */   public void setStyle(Element element, Map styleMap, SVGGeneratorContext generatorContext) {
/* 75 */     String tagName = element.getTagName();
/* 76 */     for (Object o : styleMap.keySet()) {
/* 77 */       String styleName = (String)o;
/* 78 */       if (element.getAttributeNS(null, styleName).length() == 0 && 
/* 79 */         appliesTo(styleName, tagName)) {
/* 80 */         element.setAttributeNS(null, styleName, (String)styleMap.get(styleName));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean appliesTo(String styleName, String tagName) {
/* 92 */     Set s = (Set)ignoreAttributes.get(tagName);
/* 93 */     if (s == null) {
/* 94 */       return true;
/*    */     }
/* 96 */     return !s.contains(styleName);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/DefaultStyleHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */