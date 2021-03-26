/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSNavigableDocumentListener;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.EventListener;
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
/*     */ public class SVG12OMDocument
/*     */   extends SVGOMDocument
/*     */ {
/*     */   protected SVG12OMDocument() {}
/*     */   
/*     */   public SVG12OMDocument(DocumentType dt, DOMImplementation impl) {
/*  49 */     super(dt, impl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  58 */     return (Node)new SVG12OMDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCSSNavigableDocumentListener(CSSNavigableDocumentListener l) {
/*  69 */     if (this.cssNavigableDocumentListeners.containsKey(l)) {
/*     */       return;
/*     */     }
/*     */     
/*  73 */     SVGOMDocument.DOMNodeInsertedListenerWrapper nodeInserted = new SVGOMDocument.DOMNodeInsertedListenerWrapper(this, l);
/*     */     
/*  75 */     SVGOMDocument.DOMNodeRemovedListenerWrapper nodeRemoved = new SVGOMDocument.DOMNodeRemovedListenerWrapper(this, l);
/*     */     
/*  77 */     SVGOMDocument.DOMSubtreeModifiedListenerWrapper subtreeModified = new SVGOMDocument.DOMSubtreeModifiedListenerWrapper(this, l);
/*     */     
/*  79 */     SVGOMDocument.DOMCharacterDataModifiedListenerWrapper cdataModified = new SVGOMDocument.DOMCharacterDataModifiedListenerWrapper(this, l);
/*     */     
/*  81 */     SVGOMDocument.DOMAttrModifiedListenerWrapper attrModified = new SVGOMDocument.DOMAttrModifiedListenerWrapper(this, l);
/*     */ 
/*     */     
/*  84 */     this.cssNavigableDocumentListeners.put(l, new EventListener[] { nodeInserted, nodeRemoved, subtreeModified, cdataModified, attrModified });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     XBLEventSupport es = (XBLEventSupport)initializeEventSupport();
/*     */     
/*  93 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", nodeInserted, false);
/*     */ 
/*     */ 
/*     */     
/*  97 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", nodeRemoved, false);
/*     */ 
/*     */ 
/*     */     
/* 101 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", subtreeModified, false);
/*     */ 
/*     */ 
/*     */     
/* 105 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", cdataModified, false);
/*     */ 
/*     */ 
/*     */     
/* 109 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", attrModified, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeCSSNavigableDocumentListener(CSSNavigableDocumentListener l) {
/* 121 */     EventListener[] listeners = (EventListener[])this.cssNavigableDocumentListeners.get(l);
/*     */     
/* 123 */     if (listeners == null) {
/*     */       return;
/*     */     }
/*     */     
/* 127 */     XBLEventSupport es = (XBLEventSupport)initializeEventSupport();
/*     */     
/* 129 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", listeners[0], false);
/*     */ 
/*     */ 
/*     */     
/* 133 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", listeners[1], false);
/*     */ 
/*     */ 
/*     */     
/* 137 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", listeners[2], false);
/*     */ 
/*     */ 
/*     */     
/* 141 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", listeners[3], false);
/*     */ 
/*     */ 
/*     */     
/* 145 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", listeners[4], false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     this.cssNavigableDocumentListeners.remove(l);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVG12OMDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */