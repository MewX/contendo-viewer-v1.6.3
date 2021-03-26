/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import org.apache.batik.dom.events.DOMUIEvent;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.events.DocumentEvent;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ import org.w3c.dom.events.MouseEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FocusManager
/*     */ {
/*     */   protected EventTarget lastFocusEventTarget;
/*     */   protected Document document;
/*     */   protected EventListener mouseclickListener;
/*     */   protected EventListener domFocusInListener;
/*     */   protected EventListener domFocusOutListener;
/*     */   protected EventListener mouseoverListener;
/*     */   protected EventListener mouseoutListener;
/*     */   
/*     */   public FocusManager(Document doc) {
/*  82 */     this.document = doc;
/*  83 */     addEventListeners(doc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addEventListeners(Document doc) {
/*  90 */     NodeEventTarget target = (NodeEventTarget)doc;
/*     */     
/*  92 */     this.mouseclickListener = new MouseClickTracker();
/*  93 */     target.addEventListenerNS("http://www.w3.org/2001/xml-events", "click", this.mouseclickListener, true, null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     this.mouseoverListener = new MouseOverTracker();
/*  99 */     target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.mouseoverListener, true, null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     this.mouseoutListener = new MouseOutTracker();
/* 105 */     target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.mouseoutListener, true, null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     this.domFocusInListener = new DOMFocusInTracker();
/* 111 */     target.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusIn", this.domFocusInListener, true, null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     this.domFocusOutListener = new DOMFocusOutTracker();
/* 117 */     target.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusOut", this.domFocusOutListener, true, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeEventListeners(Document doc) {
/* 127 */     NodeEventTarget target = (NodeEventTarget)doc;
/*     */     
/* 129 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "click", this.mouseclickListener, true);
/*     */ 
/*     */     
/* 132 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", this.mouseoverListener, true);
/*     */ 
/*     */     
/* 135 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", this.mouseoutListener, true);
/*     */ 
/*     */     
/* 138 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusIn", this.domFocusInListener, true);
/*     */ 
/*     */     
/* 141 */     target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMFocusOut", this.domFocusOutListener, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventTarget getCurrentEventTarget() {
/* 150 */     return this.lastFocusEventTarget;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 157 */     if (this.document == null)
/* 158 */       return;  removeEventListeners(this.document);
/* 159 */     this.lastFocusEventTarget = null;
/* 160 */     this.document = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class MouseClickTracker
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 169 */       MouseEvent mevt = (MouseEvent)evt;
/* 170 */       FocusManager.this.fireDOMActivateEvent(evt.getTarget(), mevt.getDetail());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class DOMFocusInTracker
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 180 */       EventTarget newTarget = evt.getTarget();
/* 181 */       if (FocusManager.this.lastFocusEventTarget != null && FocusManager.this.lastFocusEventTarget != newTarget)
/*     */       {
/* 183 */         FocusManager.this.fireDOMFocusOutEvent(FocusManager.this.lastFocusEventTarget, newTarget);
/*     */       }
/* 185 */       FocusManager.this.lastFocusEventTarget = evt.getTarget();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class DOMFocusOutTracker
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 198 */       FocusManager.this.lastFocusEventTarget = null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class MouseOverTracker
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 209 */       MouseEvent me = (MouseEvent)evt;
/* 210 */       EventTarget target = evt.getTarget();
/* 211 */       EventTarget relatedTarget = me.getRelatedTarget();
/* 212 */       FocusManager.this.fireDOMFocusInEvent(target, relatedTarget);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class MouseOutTracker
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 223 */       MouseEvent me = (MouseEvent)evt;
/* 224 */       EventTarget target = evt.getTarget();
/* 225 */       EventTarget relatedTarget = me.getRelatedTarget();
/* 226 */       FocusManager.this.fireDOMFocusOutEvent(target, relatedTarget);
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
/* 238 */     DocumentEvent docEvt = (DocumentEvent)((Element)target).getOwnerDocument();
/*     */     
/* 240 */     DOMUIEvent uiEvt = (DOMUIEvent)docEvt.createEvent("UIEvents");
/* 241 */     uiEvt.initUIEventNS("http://www.w3.org/2001/xml-events", "DOMFocusIn", true, false, null, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 247 */     target.dispatchEvent((Event)uiEvt);
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
/* 258 */     DocumentEvent docEvt = (DocumentEvent)((Element)target).getOwnerDocument();
/*     */     
/* 260 */     DOMUIEvent uiEvt = (DOMUIEvent)docEvt.createEvent("UIEvents");
/* 261 */     uiEvt.initUIEventNS("http://www.w3.org/2001/xml-events", "DOMFocusOut", true, false, null, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     target.dispatchEvent((Event)uiEvt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireDOMActivateEvent(EventTarget target, int detailArg) {
/* 277 */     DocumentEvent docEvt = (DocumentEvent)((Element)target).getOwnerDocument();
/*     */     
/* 279 */     DOMUIEvent uiEvt = (DOMUIEvent)docEvt.createEvent("UIEvents");
/* 280 */     uiEvt.initUIEventNS("http://www.w3.org/2001/xml-events", "DOMActivate", true, true, null, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 286 */     target.dispatchEvent((Event)uiEvt);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/FocusManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */