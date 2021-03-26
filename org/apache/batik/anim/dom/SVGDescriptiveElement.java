/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.apache.batik.dom.util.XMLSupport;
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
/*    */ public abstract class SVGDescriptiveElement
/*    */   extends SVGStylableElement
/*    */ {
/*    */   protected SVGDescriptiveElement() {}
/*    */   
/*    */   protected SVGDescriptiveElement(String prefix, AbstractDocument owner) {
/* 45 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getXMLlang() {
/* 54 */     return XMLSupport.getXMLLang((Element)this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setXMLlang(String lang) {
/* 61 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:lang", lang);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getXMLspace() {
/* 68 */     return XMLSupport.getXMLSpace((Element)this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setXMLspace(String space) {
/* 75 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", space);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGDescriptiveElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */