/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import org.apache.batik.anim.dom.AnimatedAttributeListener;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.anim.dom.XBLEventSupport;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.BridgeUpdateHandler;
/*     */ import org.apache.batik.bridge.DocumentLoader;
/*     */ import org.apache.batik.bridge.ScriptingEnvironment;
/*     */ import org.apache.batik.bridge.URIResolver;
/*     */ import org.apache.batik.bridge.UserAgent;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSEngineListener;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.events.EventSupport;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.dom.xbl.NodeXBL;
/*     */ import org.apache.batik.dom.xbl.XBLManager;
/*     */ import org.apache.batik.script.Interpreter;
/*     */ import org.apache.batik.script.InterpreterPool;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ import org.w3c.dom.svg.SVGDocument;
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
/*     */ public class SVG12BridgeContext
/*     */   extends BridgeContext
/*     */ {
/*     */   protected XBLBindingListener bindingListener;
/*     */   protected XBLContentListener contentListener;
/*     */   protected EventTarget mouseCaptureTarget;
/*     */   protected boolean mouseCaptureSendAll;
/*     */   protected boolean mouseCaptureAutoRelease;
/*     */   
/*     */   public SVG12BridgeContext(UserAgent userAgent) {
/*  90 */     super(userAgent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVG12BridgeContext(UserAgent userAgent, DocumentLoader loader) {
/* 100 */     super(userAgent, loader);
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
/*     */   public SVG12BridgeContext(UserAgent userAgent, InterpreterPool interpreterPool, DocumentLoader documentLoader) {
/* 112 */     super(userAgent, interpreterPool, documentLoader);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URIResolver createURIResolver(SVGDocument doc, DocumentLoader dl) {
/* 119 */     return new SVG12URIResolver(doc, dl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGVTListener(Document doc) {
/* 126 */     SVG12BridgeEventSupport.addGVTListener(this, doc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 133 */     clearChildContexts();
/*     */     
/* 135 */     synchronized (this.eventListenerSet) {
/*     */       
/* 137 */       for (Object anEventListenerSet : this.eventListenerSet) {
/* 138 */         BridgeContext.EventListenerMememto m = (BridgeContext.EventListenerMememto)anEventListenerSet;
/* 139 */         NodeEventTarget et = m.getTarget();
/* 140 */         EventListener el = m.getListener();
/* 141 */         boolean uc = m.getUseCapture();
/* 142 */         String t = m.getEventType();
/* 143 */         boolean in = m.getNamespaced();
/* 144 */         if (et == null || el == null || t == null) {
/*     */           continue;
/*     */         }
/* 147 */         if (m instanceof ImplementationEventListenerMememto) {
/* 148 */           String ns = m.getNamespaceURI();
/* 149 */           Node nde = (Node)et;
/* 150 */           AbstractNode n = (AbstractNode)nde.getOwnerDocument();
/* 151 */           if (n != null) {
/*     */             
/* 153 */             XBLEventSupport es = (XBLEventSupport)n.initializeEventSupport();
/* 154 */             es.removeImplementationEventListenerNS(ns, t, el, uc);
/*     */           }  continue;
/* 156 */         }  if (in) {
/* 157 */           String ns = m.getNamespaceURI();
/* 158 */           et.removeEventListenerNS(ns, t, el, uc); continue;
/*     */         } 
/* 160 */         et.removeEventListener(t, el, uc);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 165 */     if (this.document != null) {
/* 166 */       removeDOMListeners();
/* 167 */       removeBindingListener();
/*     */     } 
/*     */     
/* 170 */     if (this.animationEngine != null) {
/* 171 */       this.animationEngine.dispose();
/* 172 */       this.animationEngine = null;
/*     */     } 
/*     */     
/* 175 */     for (Object o : this.interpreterMap.values()) {
/* 176 */       Interpreter interpreter = (Interpreter)o;
/* 177 */       if (interpreter != null)
/* 178 */         interpreter.dispose(); 
/*     */     } 
/* 180 */     this.interpreterMap.clear();
/*     */     
/* 182 */     if (this.focusManager != null) {
/* 183 */       this.focusManager.dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addBindingListener() {
/* 192 */     AbstractDocument doc = (AbstractDocument)this.document;
/* 193 */     DefaultXBLManager xm = (DefaultXBLManager)doc.getXBLManager();
/* 194 */     if (xm != null) {
/* 195 */       this.bindingListener = new XBLBindingListener();
/* 196 */       xm.addBindingListener(this.bindingListener);
/* 197 */       this.contentListener = new XBLContentListener();
/* 198 */       xm.addContentSelectionChangedListener(this.contentListener);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeBindingListener() {
/* 206 */     AbstractDocument doc = (AbstractDocument)this.document;
/* 207 */     XBLManager xm = doc.getXBLManager();
/* 208 */     if (xm instanceof DefaultXBLManager) {
/* 209 */       DefaultXBLManager dxm = (DefaultXBLManager)xm;
/* 210 */       dxm.removeBindingListener(this.bindingListener);
/* 211 */       dxm.removeContentSelectionChangedListener(this.contentListener);
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
/*     */   public void addDOMListeners() {
/* 223 */     SVGOMDocument doc = (SVGOMDocument)this.document;
/* 224 */     XBLEventSupport evtSupport = (XBLEventSupport)doc.initializeEventSupport();
/*     */ 
/*     */     
/* 227 */     this.domAttrModifiedEventListener = new EventListenerWrapper((EventListener)new BridgeContext.DOMAttrModifiedEventListener(this));
/*     */     
/* 229 */     evtSupport.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.domAttrModifiedEventListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     this.domNodeInsertedEventListener = new EventListenerWrapper((EventListener)new BridgeContext.DOMNodeInsertedEventListener(this));
/*     */     
/* 236 */     evtSupport.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.domNodeInsertedEventListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 241 */     this.domNodeRemovedEventListener = new EventListenerWrapper((EventListener)new BridgeContext.DOMNodeRemovedEventListener(this));
/*     */     
/* 243 */     evtSupport.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.domNodeRemovedEventListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 248 */     this.domCharacterDataModifiedEventListener = new EventListenerWrapper((EventListener)new BridgeContext.DOMCharacterDataModifiedEventListener(this));
/*     */     
/* 250 */     evtSupport.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", this.domCharacterDataModifiedEventListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 255 */     this.animatedAttributeListener = (AnimatedAttributeListener)new BridgeContext.AnimatedAttrListener(this);
/* 256 */     doc.addAnimatedAttributeListener(this.animatedAttributeListener);
/*     */     
/* 258 */     this.focusManager = new SVG12FocusManager(this.document);
/*     */     
/* 260 */     CSSEngine cssEngine = doc.getCSSEngine();
/* 261 */     this.cssPropertiesChangedListener = (CSSEngineListener)new BridgeContext.CSSPropertiesChangedListener(this);
/* 262 */     cssEngine.addCSSEngineListener(this.cssPropertiesChangedListener);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addUIEventListeners(Document doc) {
/* 278 */     EventTarget evtTarget = (EventTarget)doc.getDocumentElement();
/* 279 */     AbstractNode n = (AbstractNode)evtTarget;
/* 280 */     XBLEventSupport evtSupport = (XBLEventSupport)n.initializeEventSupport();
/*     */ 
/*     */     
/* 283 */     EventListener domMouseOverListener = new EventListenerWrapper((EventListener)new BridgeContext.DOMMouseOverEventListener(this));
/*     */     
/* 285 */     evtSupport.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", domMouseOverListener, true);
/*     */ 
/*     */ 
/*     */     
/* 289 */     storeImplementationEventListenerNS(evtTarget, "http://www.w3.org/2001/xml-events", "mouseover", domMouseOverListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 295 */     EventListener domMouseOutListener = new EventListenerWrapper((EventListener)new BridgeContext.DOMMouseOutEventListener(this));
/*     */     
/* 297 */     evtSupport.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "mouseout", domMouseOutListener, true);
/*     */ 
/*     */ 
/*     */     
/* 301 */     storeImplementationEventListenerNS(evtTarget, "http://www.w3.org/2001/xml-events", "mouseout", domMouseOutListener, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeUIEventListeners(Document doc) {
/* 309 */     EventTarget evtTarget = (EventTarget)doc.getDocumentElement();
/* 310 */     AbstractNode n = (AbstractNode)evtTarget;
/* 311 */     XBLEventSupport es = (XBLEventSupport)n.initializeEventSupport();
/*     */     
/* 313 */     synchronized (this.eventListenerSet) {
/* 314 */       for (Object anEventListenerSet : this.eventListenerSet) {
/* 315 */         BridgeContext.EventListenerMememto elm = (BridgeContext.EventListenerMememto)anEventListenerSet;
/* 316 */         NodeEventTarget et = elm.getTarget();
/* 317 */         if (et == evtTarget) {
/* 318 */           EventListener el = elm.getListener();
/* 319 */           boolean uc = elm.getUseCapture();
/* 320 */           String t = elm.getEventType();
/* 321 */           boolean in = elm.getNamespaced();
/* 322 */           if (et == null || el == null || t == null) {
/*     */             continue;
/*     */           }
/* 325 */           if (elm instanceof ImplementationEventListenerMememto) {
/* 326 */             String ns = elm.getNamespaceURI();
/* 327 */             es.removeImplementationEventListenerNS(ns, t, el, uc); continue;
/* 328 */           }  if (in) {
/* 329 */             String ns = elm.getNamespaceURI();
/* 330 */             et.removeEventListenerNS(ns, t, el, uc); continue;
/*     */           } 
/* 332 */           et.removeEventListener(t, el, uc);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeDOMListeners() {
/* 343 */     SVGOMDocument doc = (SVGOMDocument)this.document;
/*     */     
/* 345 */     doc.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.domAttrModifiedEventListener, true);
/*     */ 
/*     */ 
/*     */     
/* 349 */     doc.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.domNodeInsertedEventListener, true);
/*     */ 
/*     */ 
/*     */     
/* 353 */     doc.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.domNodeRemovedEventListener, true);
/*     */ 
/*     */ 
/*     */     
/* 357 */     doc.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", this.domCharacterDataModifiedEventListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 362 */     doc.removeAnimatedAttributeListener(this.animatedAttributeListener);
/*     */     
/* 364 */     CSSEngine cssEngine = doc.getCSSEngine();
/* 365 */     if (cssEngine != null) {
/* 366 */       cssEngine.removeCSSEngineListener(this.cssPropertiesChangedListener);
/*     */       
/* 368 */       cssEngine.dispose();
/* 369 */       doc.setCSSEngine(null);
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
/*     */   
/*     */   protected void storeImplementationEventListenerNS(EventTarget t, String ns, String s, EventListener l, boolean b) {
/* 382 */     synchronized (this.eventListenerSet) {
/* 383 */       ImplementationEventListenerMememto m = new ImplementationEventListenerMememto(t, ns, s, l, b, this);
/*     */       
/* 385 */       this.eventListenerSet.add(m);
/*     */     } 
/*     */   }
/*     */   
/*     */   public BridgeContext createSubBridgeContext(SVGOMDocument newDoc) {
/* 390 */     CSSEngine eng = newDoc.getCSSEngine();
/* 391 */     if (eng != null) {
/* 392 */       return (BridgeContext)newDoc.getCSSEngine().getCSSContext();
/*     */     }
/*     */     
/* 395 */     BridgeContext subCtx = super.createSubBridgeContext(newDoc);
/* 396 */     if (isDynamic() && subCtx.isDynamic()) {
/* 397 */       setUpdateManager(subCtx, this.updateManager);
/* 398 */       if (this.updateManager != null) {
/*     */         ScriptingEnvironment se;
/* 400 */         if (newDoc.isSVG12()) {
/* 401 */           se = new SVG12ScriptingEnvironment(subCtx);
/*     */         } else {
/* 403 */           se = new ScriptingEnvironment(subCtx);
/*     */         } 
/* 405 */         se.loadScripts();
/* 406 */         se.dispatchSVGLoadEvent();
/* 407 */         if (newDoc.isSVG12()) {
/* 408 */           DefaultXBLManager xm = new DefaultXBLManager((Document)newDoc, subCtx);
/*     */           
/* 410 */           setXBLManager(subCtx, xm);
/* 411 */           newDoc.setXBLManager(xm);
/* 412 */           xm.startProcessing();
/*     */         } 
/*     */       } 
/*     */     } 
/* 416 */     return subCtx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startMouseCapture(EventTarget target, boolean sendAll, boolean autoRelease) {
/* 424 */     this.mouseCaptureTarget = target;
/* 425 */     this.mouseCaptureSendAll = sendAll;
/* 426 */     this.mouseCaptureAutoRelease = autoRelease;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopMouseCapture() {
/* 433 */     this.mouseCaptureTarget = null;
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
/*     */ 
/*     */   
/*     */   protected static class ImplementationEventListenerMememto
/*     */     extends BridgeContext.EventListenerMememto
/*     */   {
/*     */     public ImplementationEventListenerMememto(EventTarget t, String s, EventListener l, boolean b, BridgeContext c) {
/* 450 */       super(t, s, l, b, c);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ImplementationEventListenerMememto(EventTarget t, String n, String s, EventListener l, boolean b, BridgeContext c) {
/* 462 */       super(t, n, s, l, b, c);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class EventListenerWrapper
/*     */     implements EventListener
/*     */   {
/*     */     protected EventListener listener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public EventListenerWrapper(EventListener l) {
/* 481 */       this.listener = l;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 488 */       this.listener.handleEvent(EventSupport.getUltimateOriginalEvent(evt));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 495 */       return super.toString() + " [wrapping " + this.listener.toString() + "]";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class XBLBindingListener
/*     */     implements BindingListener
/*     */   {
/*     */     public void bindingChanged(Element bindableElement, Element shadowTree) {
/* 509 */       BridgeUpdateHandler h = SVG12BridgeContext.getBridgeUpdateHandler(bindableElement);
/* 510 */       if (h instanceof SVG12BridgeUpdateHandler) {
/* 511 */         SVG12BridgeUpdateHandler h12 = (SVG12BridgeUpdateHandler)h;
/*     */         try {
/* 513 */           h12.handleBindingEvent(bindableElement, shadowTree);
/* 514 */         } catch (Exception e) {
/* 515 */           SVG12BridgeContext.this.userAgent.displayError(e);
/*     */         } 
/*     */       } 
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
/*     */   
/*     */   protected class XBLContentListener
/*     */     implements ContentSelectionChangedListener
/*     */   {
/*     */     public void contentSelectionChanged(ContentSelectionChangedEvent csce) {
/* 533 */       Element e = (Element)csce.getContentElement().getParentNode();
/* 534 */       if (e instanceof org.apache.batik.anim.dom.XBLOMShadowTreeElement) {
/* 535 */         e = ((NodeXBL)e).getXblBoundElement();
/*     */       }
/* 537 */       BridgeUpdateHandler h = SVG12BridgeContext.getBridgeUpdateHandler(e);
/* 538 */       if (h instanceof SVG12BridgeUpdateHandler) {
/* 539 */         SVG12BridgeUpdateHandler h12 = (SVG12BridgeUpdateHandler)h;
/*     */         try {
/* 541 */           h12.handleContentSelectionChangedEvent(csce);
/* 542 */         } catch (Exception ex) {
/* 543 */           SVG12BridgeContext.this.userAgent.displayError(ex);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/SVG12BridgeContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */