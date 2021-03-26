/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import org.apache.batik.anim.dom.XBLEventSupport;
/*     */ import org.apache.batik.bridge.FocusManager;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.events.DOMUIEvent;
/*     */ import org.apache.batik.dom.events.EventSupport;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.DocumentEvent;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
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
/*     */ public class SVG12FocusManager
/*     */   extends FocusManager
/*     */ {
/*     */   public SVG12FocusManager(Document doc) {
/*  51 */     super(doc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addEventListeners(Document doc) {
/*  58 */     AbstractNode n = (AbstractNode)doc;
/*  59 */     XBLEventSupport es = (XBLEventSupport)n.initializeEventSupport();
/*     */     
/*  61 */     this.mouseclickListener = (EventListener)new MouseClickTracker();
/*  62 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "click", this.mouseclickListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     this.mouseoverListener = (EventListener)new MouseOverTracker();
/*  68 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.mouseoverListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     this.mouseoutListener = (EventListener)new MouseOutTracker();
/*  74 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.mouseoutListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     this.domFocusInListener = (EventListener)new DOMFocusInTracker();
/*  80 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusIn", this.domFocusInListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     this.domFocusOutListener = (EventListener)new FocusManager.DOMFocusOutTracker(this);
/*  86 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusOut", this.domFocusOutListener, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeEventListeners(Document doc) {
/*  96 */     AbstractNode n = (AbstractNode)doc;
/*  97 */     XBLEventSupport es = (XBLEventSupport)n.getEventSupport();
/*     */     
/*  99 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "click", this.mouseclickListener, true);
/*     */ 
/*     */ 
/*     */     
/* 103 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.mouseoverListener, true);
/*     */ 
/*     */ 
/*     */     
/* 107 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.mouseoutListener, true);
/*     */ 
/*     */ 
/*     */     
/* 111 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusIn", this.domFocusInListener, true);
/*     */ 
/*     */ 
/*     */     
/* 115 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusOut", this.domFocusOutListener, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class MouseClickTracker
/*     */     extends FocusManager.MouseClickTracker
/*     */   {
/*     */     protected MouseClickTracker() {
/* 124 */       super(SVG12FocusManager.this);
/*     */     } public void handleEvent(Event evt) {
/* 126 */       super.handleEvent(EventSupport.getUltimateOriginalEvent(evt));
/*     */     }
/*     */   }
/*     */   
/*     */   protected class DOMFocusInTracker
/*     */     extends FocusManager.DOMFocusInTracker {
/*     */     protected DOMFocusInTracker() {
/* 133 */       super(SVG12FocusManager.this);
/*     */     } public void handleEvent(Event evt) {
/* 135 */       super.handleEvent(EventSupport.getUltimateOriginalEvent(evt));
/*     */     }
/*     */   }
/*     */   
/*     */   protected class MouseOverTracker
/*     */     extends FocusManager.MouseOverTracker {
/*     */     protected MouseOverTracker() {
/* 142 */       super(SVG12FocusManager.this);
/*     */     } public void handleEvent(Event evt) {
/* 144 */       super.handleEvent(EventSupport.getUltimateOriginalEvent(evt));
/*     */     }
/*     */   }
/*     */   
/*     */   protected class MouseOutTracker
/*     */     extends FocusManager.MouseOutTracker {
/*     */     protected MouseOutTracker() {
/* 151 */       super(SVG12FocusManager.this);
/*     */     } public void handleEvent(Event evt) {
/* 153 */       super.handleEvent(EventSupport.getUltimateOriginalEvent(evt));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireDOMFocusInEvent(EventTarget target, EventTarget relatedTarget) {
/* 165 */     DocumentEvent docEvt = (DocumentEvent)((Element)target).getOwnerDocument();
/*     */     
/* 167 */     DOMUIEvent uiEvt = (DOMUIEvent)docEvt.createEvent("UIEvents");
/* 168 */     uiEvt.initUIEventNS("http://www.w3.org/2001/xml-events", "DOMFocusIn", true, false, null, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     int limit = DefaultXBLManager.computeBubbleLimit((Node)relatedTarget, (Node)target);
/*     */     
/* 176 */     uiEvt.setBubbleLimit(limit);
/* 177 */     target.dispatchEvent((Event)uiEvt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireDOMFocusOutEvent(EventTarget target, EventTarget relatedTarget) {
/* 188 */     DocumentEvent docEvt = (DocumentEvent)((Element)target).getOwnerDocument();
/*     */     
/* 190 */     DOMUIEvent uiEvt = (DOMUIEvent)docEvt.createEvent("UIEvents");
/* 191 */     uiEvt.initUIEventNS("http://www.w3.org/2001/xml-events", "DOMFocusOut", true, false, null, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     int limit = DefaultXBLManager.computeBubbleLimit((Node)target, (Node)relatedTarget);
/*     */     
/* 199 */     uiEvt.setBubbleLimit(limit);
/* 200 */     target.dispatchEvent((Event)uiEvt);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/SVG12FocusManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */