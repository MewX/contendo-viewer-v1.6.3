/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStyleSheetNode;
/*     */ import org.apache.batik.css.engine.StyleSheet;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.stylesheets.LinkStyle;
/*     */ import org.w3c.dom.stylesheets.StyleSheet;
/*     */ import org.w3c.dom.svg.SVGStyleElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMStyleElement
/*     */   extends SVGOMElement
/*     */   implements CSSStyleSheetNode, LinkStyle, SVGStyleElement
/*     */ {
/*  71 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(1); static {
/*  72 */     attributeInitializer.addAttribute("http://www.w3.org/XML/1998/namespace", "xml", "space", "preserve");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected transient StyleSheet sheet;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected transient StyleSheet styleSheet;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   protected transient EventListener domCharacterDataModifiedListener = new DOMCharacterDataModifiedListener();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMStyleElement(String prefix, AbstractDocument owner) {
/* 104 */     super(prefix, owner);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 111 */     return "style";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleSheet getCSSStyleSheet() {
/* 118 */     if (this.styleSheet == null && 
/* 119 */       getType().equals("text/css")) {
/* 120 */       SVGOMDocument doc = (SVGOMDocument)getOwnerDocument();
/* 121 */       CSSEngine e = doc.getCSSEngine();
/* 122 */       String text = "";
/* 123 */       Node n = getFirstChild();
/* 124 */       if (n != null) {
/* 125 */         StringBuffer sb = new StringBuffer();
/* 126 */         while (n != null) {
/* 127 */           if (n.getNodeType() == 4 || n.getNodeType() == 3)
/*     */           {
/* 129 */             sb.append(n.getNodeValue()); } 
/* 130 */           n = n.getNextSibling();
/*     */         } 
/* 132 */         text = sb.toString();
/*     */       } 
/* 134 */       ParsedURL burl = null;
/* 135 */       String bu = getBaseURI();
/* 136 */       if (bu != null) {
/* 137 */         burl = new ParsedURL(bu);
/*     */       }
/* 139 */       String media = getAttributeNS(null, "media");
/* 140 */       this.styleSheet = e.parseStyleSheet(text, burl, media);
/* 141 */       addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", this.domCharacterDataModifiedListener, false, null);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     return this.styleSheet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleSheet getSheet() {
/* 156 */     throw new UnsupportedOperationException("LinkStyle.getSheet() is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLspace() {
/* 164 */     return XMLSupport.getXMLSpace((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLspace(String space) throws DOMException {
/* 171 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 178 */     if (hasAttributeNS(null, "type")) {
/* 179 */       return getAttributeNS(null, "type");
/*     */     }
/* 181 */     return "text/css";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String type) throws DOMException {
/* 188 */     setAttributeNS(null, "type", type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMedia() {
/* 195 */     return getAttribute("media");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMedia(String media) throws DOMException {
/* 202 */     setAttribute("media", media);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 209 */     return getAttribute("title");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(String title) throws DOMException {
/* 216 */     setAttribute("title", title);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 224 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 231 */     return (Node)new SVGOMStyleElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMStyleElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class DOMCharacterDataModifiedListener
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 247 */       SVGOMStyleElement.this.styleSheet = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMStyleElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */