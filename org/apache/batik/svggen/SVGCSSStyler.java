/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.w3c.dom.Attr;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.NamedNodeMap;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.NodeList;
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
/*    */ public class SVGCSSStyler
/*    */   implements SVGSyntax
/*    */ {
/*    */   private static final char CSS_PROPERTY_VALUE_SEPARATOR = ':';
/*    */   private static final char CSS_RULE_SEPARATOR = ';';
/*    */   private static final char SPACE = ' ';
/*    */   
/*    */   public static void style(Node node) {
/* 52 */     NamedNodeMap attributes = node.getAttributes();
/* 53 */     if (attributes != null) {
/*    */ 
/*    */       
/* 56 */       Element element = (Element)node;
/* 57 */       StringBuffer styleAttrBuffer = new StringBuffer();
/* 58 */       int nAttr = attributes.getLength();
/* 59 */       List<String> toBeRemoved = new ArrayList();
/* 60 */       for (int j = 0; j < nAttr; j++) {
/* 61 */         Attr attr = (Attr)attributes.item(j);
/* 62 */         String attrName = attr.getName();
/* 63 */         if (SVGStylingAttributes.set.contains(attrName)) {
/*    */           
/* 65 */           styleAttrBuffer.append(attrName);
/* 66 */           styleAttrBuffer.append(':');
/* 67 */           styleAttrBuffer.append(attr.getValue());
/* 68 */           styleAttrBuffer.append(';');
/* 69 */           styleAttrBuffer.append(' ');
/* 70 */           toBeRemoved.add(attrName);
/*    */         } 
/*    */       } 
/*    */       
/* 74 */       if (styleAttrBuffer.length() > 0) {
/*    */ 
/*    */         
/* 77 */         element.setAttributeNS(null, "style", styleAttrBuffer.toString().trim());
/*    */ 
/*    */ 
/*    */         
/* 81 */         int n = toBeRemoved.size();
/* 82 */         for (String aToBeRemoved : toBeRemoved) {
/* 83 */           element.removeAttribute(aToBeRemoved);
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 91 */     NodeList children = node.getChildNodes();
/* 92 */     int nChildren = children.getLength();
/* 93 */     for (int i = 0; i < nChildren; i++) {
/* 94 */       Node child = children.item(i);
/* 95 */       style(child);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGCSSStyler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */