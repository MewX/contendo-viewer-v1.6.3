/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.apache.batik.anim.dom.XBLEventSupport;
/*     */ import org.apache.batik.anim.dom.XBLOMContentElement;
/*     */ import org.apache.batik.anim.dom.XBLOMShadowTreeElement;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.dom.xbl.XBLManager;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ import org.w3c.dom.events.MutationEvent;
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
/*     */ public class ContentManager
/*     */ {
/*     */   protected XBLOMShadowTreeElement shadowTree;
/*     */   protected Element boundElement;
/*     */   protected DefaultXBLManager xblManager;
/*  73 */   protected HashMap selectors = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   protected HashMap selectedNodes = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   protected LinkedList contentElementList = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node removedNode;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   protected HashMap listeners = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ContentElementDOMAttrModifiedEventListener contentElementDomAttrModifiedEventListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DOMAttrModifiedEventListener domAttrModifiedEventListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DOMNodeInsertedEventListener domNodeInsertedEventListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DOMNodeRemovedEventListener domNodeRemovedEventListener;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DOMSubtreeModifiedEventListener domSubtreeModifiedEventListener;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ShadowTreeNodeInsertedListener shadowTreeNodeInsertedListener;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ShadowTreeNodeRemovedListener shadowTreeNodeRemovedListener;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ShadowTreeSubtreeModifiedListener shadowTreeSubtreeModifiedListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContentManager(XBLOMShadowTreeElement s, XBLManager xm) {
/* 148 */     this.shadowTree = s;
/* 149 */     this.xblManager = (DefaultXBLManager)xm;
/*     */     
/* 151 */     this.xblManager.setContentManager((Element)s, this);
/* 152 */     this.boundElement = this.xblManager.getXblBoundElement((Node)s);
/*     */     
/* 154 */     this.contentElementDomAttrModifiedEventListener = new ContentElementDOMAttrModifiedEventListener();
/*     */ 
/*     */     
/* 157 */     XBLEventSupport es = (XBLEventSupport)this.shadowTree.initializeEventSupport();
/*     */     
/* 159 */     this.shadowTreeNodeInsertedListener = new ShadowTreeNodeInsertedListener();
/* 160 */     this.shadowTreeNodeRemovedListener = new ShadowTreeNodeRemovedListener();
/* 161 */     this.shadowTreeSubtreeModifiedListener = new ShadowTreeSubtreeModifiedListener();
/*     */     
/* 163 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.shadowTreeNodeInsertedListener, true);
/*     */ 
/*     */ 
/*     */     
/* 167 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.shadowTreeNodeRemovedListener, true);
/*     */ 
/*     */ 
/*     */     
/* 171 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", this.shadowTreeSubtreeModifiedListener, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 176 */     es = (XBLEventSupport)((AbstractNode)this.boundElement).initializeEventSupport();
/*     */     
/* 178 */     this.domAttrModifiedEventListener = new DOMAttrModifiedEventListener();
/* 179 */     this.domNodeInsertedEventListener = new DOMNodeInsertedEventListener();
/* 180 */     this.domNodeRemovedEventListener = new DOMNodeRemovedEventListener();
/* 181 */     this.domSubtreeModifiedEventListener = new DOMSubtreeModifiedEventListener();
/* 182 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.domAttrModifiedEventListener, true);
/*     */ 
/*     */ 
/*     */     
/* 186 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.domNodeInsertedEventListener, true);
/*     */ 
/*     */ 
/*     */     
/* 190 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.domNodeRemovedEventListener, true);
/*     */ 
/*     */ 
/*     */     
/* 194 */     es.addImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", this.domSubtreeModifiedEventListener, false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     update(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 206 */     this.xblManager.setContentManager((Element)this.shadowTree, null);
/*     */     
/* 208 */     Iterator<Map.Entry> i = this.selectedNodes.entrySet().iterator();
/* 209 */     while (i.hasNext()) {
/* 210 */       Map.Entry e = i.next();
/* 211 */       NodeList nl = (NodeList)e.getValue();
/* 212 */       for (int j = 0; j < nl.getLength(); j++) {
/* 213 */         Node n = nl.item(j);
/* 214 */         (this.xblManager.getRecord(n)).contentElement = null;
/*     */       } 
/*     */     } 
/*     */     
/* 218 */     i = this.contentElementList.iterator();
/* 219 */     while (i.hasNext()) {
/* 220 */       NodeEventTarget n = (NodeEventTarget)i.next();
/* 221 */       n.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.contentElementDomAttrModifiedEventListener, false);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 226 */     this.contentElementList.clear();
/* 227 */     this.selectedNodes.clear();
/*     */     
/* 229 */     XBLEventSupport es = (XBLEventSupport)((AbstractNode)this.boundElement).getEventSupport();
/*     */     
/* 231 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.domAttrModifiedEventListener, true);
/*     */ 
/*     */ 
/*     */     
/* 235 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.domNodeInsertedEventListener, true);
/*     */ 
/*     */ 
/*     */     
/* 239 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.domNodeRemovedEventListener, true);
/*     */ 
/*     */ 
/*     */     
/* 243 */     es.removeImplementationEventListenerNS("http://www.w3.org/2001/xml-events", "DOMSubtreeModified", this.domSubtreeModifiedEventListener, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeList getSelectedContent(XBLOMContentElement e) {
/* 254 */     return (NodeList)this.selectedNodes.get(e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected XBLOMContentElement getContentElement(Node n) {
/* 261 */     return this.xblManager.getXblContentElement(n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addContentSelectionChangedListener(XBLOMContentElement e, ContentSelectionChangedListener l) {
/* 270 */     EventListenerList ll = (EventListenerList)this.listeners.get(e);
/* 271 */     if (ll == null) {
/* 272 */       ll = new EventListenerList();
/* 273 */       this.listeners.put(e, ll);
/*     */     } 
/* 275 */     ll.add(ContentSelectionChangedListener.class, l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeContentSelectionChangedListener(XBLOMContentElement e, ContentSelectionChangedListener l) {
/* 284 */     EventListenerList ll = (EventListenerList)this.listeners.get(e);
/* 285 */     if (ll != null) {
/* 286 */       ll.remove(ContentSelectionChangedListener.class, l);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dispatchContentSelectionChangedEvent(XBLOMContentElement e) {
/* 295 */     this.xblManager.invalidateChildNodes(e.getXblParentNode());
/*     */     
/* 297 */     ContentSelectionChangedEvent evt = new ContentSelectionChangedEvent(e);
/*     */ 
/*     */     
/* 300 */     EventListenerList ll = (EventListenerList)this.listeners.get(e);
/* 301 */     if (ll != null) {
/* 302 */       Object[] arrayOfObject = ll.getListenerList();
/* 303 */       for (int j = arrayOfObject.length - 2; j >= 0; j -= 2) {
/* 304 */         ContentSelectionChangedListener l = (ContentSelectionChangedListener)arrayOfObject[j + 1];
/*     */         
/* 306 */         l.contentSelectionChanged(evt);
/*     */       } 
/*     */     } 
/*     */     
/* 310 */     Object[] ls = this.xblManager.getContentSelectionChangedListeners();
/* 311 */     for (int i = ls.length - 2; i >= 0; i -= 2) {
/* 312 */       ContentSelectionChangedListener l = (ContentSelectionChangedListener)ls[i + 1];
/*     */       
/* 314 */       l.contentSelectionChanged(evt);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void update(boolean first) {
/* 323 */     HashSet<Node> previouslySelectedNodes = new HashSet();
/* 324 */     Iterator<Map.Entry> i = this.selectedNodes.entrySet().iterator();
/* 325 */     while (i.hasNext()) {
/* 326 */       Map.Entry e = i.next();
/* 327 */       NodeList nl = (NodeList)e.getValue();
/* 328 */       for (int j = 0; j < nl.getLength(); j++) {
/* 329 */         Node node = nl.item(j);
/* 330 */         (this.xblManager.getRecord(node)).contentElement = null;
/* 331 */         previouslySelectedNodes.add(node);
/*     */       } 
/*     */     } 
/*     */     
/* 335 */     i = this.contentElementList.iterator();
/* 336 */     while (i.hasNext()) {
/* 337 */       NodeEventTarget nodeEventTarget = (NodeEventTarget)i.next();
/* 338 */       nodeEventTarget.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.contentElementDomAttrModifiedEventListener, false);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 343 */     this.contentElementList.clear();
/* 344 */     this.selectedNodes.clear();
/*     */     
/* 346 */     boolean updated = false;
/* 347 */     Node n = this.shadowTree.getFirstChild();
/* 348 */     for (; n != null; 
/* 349 */       n = n.getNextSibling()) {
/* 350 */       if (update(first, n)) {
/* 351 */         updated = true;
/*     */       }
/*     */     } 
/*     */     
/* 355 */     if (updated) {
/* 356 */       HashSet<Node> newlySelectedNodes = new HashSet();
/* 357 */       i = this.selectedNodes.entrySet().iterator();
/* 358 */       while (i.hasNext()) {
/* 359 */         Map.Entry e = i.next();
/* 360 */         NodeList nl = (NodeList)e.getValue();
/* 361 */         for (int j = 0; j < nl.getLength(); j++) {
/* 362 */           Node node = nl.item(j);
/* 363 */           newlySelectedNodes.add(node);
/*     */         } 
/*     */       } 
/*     */       
/* 367 */       HashSet<Node> removed = new HashSet();
/* 368 */       removed.addAll(previouslySelectedNodes);
/* 369 */       removed.removeAll(newlySelectedNodes);
/*     */       
/* 371 */       HashSet<Node> added = new HashSet();
/* 372 */       added.addAll(newlySelectedNodes);
/* 373 */       added.removeAll(previouslySelectedNodes);
/*     */       
/* 375 */       if (!first) {
/* 376 */         this.xblManager.shadowTreeSelectedContentChanged(removed, added);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean update(boolean first, Node n) {
/* 382 */     boolean updated = false;
/* 383 */     for (Node m = n.getFirstChild(); m != null; m = m.getNextSibling()) {
/* 384 */       if (update(first, m)) {
/* 385 */         updated = true;
/*     */       }
/*     */     } 
/* 388 */     if (n instanceof XBLOMContentElement) {
/* 389 */       boolean changed; this.contentElementList.add(n);
/* 390 */       XBLOMContentElement e = (XBLOMContentElement)n;
/* 391 */       e.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.contentElementDomAttrModifiedEventListener, false, null);
/*     */ 
/*     */       
/* 394 */       AbstractContentSelector s = (AbstractContentSelector)this.selectors.get(n);
/*     */ 
/*     */       
/* 397 */       if (s == null) {
/* 398 */         if (e.hasAttributeNS(null, "includes")) {
/*     */           
/* 400 */           String lang = getContentSelectorLanguage((Element)e);
/* 401 */           String selector = e.getAttributeNS(null, "includes");
/*     */           
/* 403 */           s = AbstractContentSelector.createSelector(lang, this, e, this.boundElement, selector);
/*     */         } else {
/*     */           
/* 406 */           s = new DefaultContentSelector(this, e, this.boundElement);
/*     */         } 
/* 408 */         this.selectors.put(n, s);
/* 409 */         changed = true;
/*     */       } else {
/* 411 */         changed = s.update();
/*     */       } 
/* 413 */       NodeList selectedContent = s.getSelectedContent();
/* 414 */       this.selectedNodes.put(n, selectedContent);
/* 415 */       for (int i = 0; i < selectedContent.getLength(); i++) {
/* 416 */         Node node = selectedContent.item(i);
/* 417 */         (this.xblManager.getRecord(node)).contentElement = e;
/*     */       } 
/* 419 */       if (changed) {
/* 420 */         updated = true;
/* 421 */         dispatchContentSelectionChangedEvent(e);
/*     */       } 
/*     */     } 
/* 424 */     return updated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getContentSelectorLanguage(Element e) {
/* 434 */     String lang = e.getAttributeNS("http://xml.apache.org/batik/ext", "selectorLanguage");
/*     */     
/* 436 */     if (lang.length() != 0) {
/* 437 */       return lang;
/*     */     }
/* 439 */     lang = e.getOwnerDocument().getDocumentElement().getAttributeNS("http://xml.apache.org/batik/ext", "selectorLanguage");
/*     */     
/* 441 */     if (lang.length() != 0) {
/* 442 */       return lang;
/*     */     }
/* 444 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ContentElementDOMAttrModifiedEventListener
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 454 */       MutationEvent me = (MutationEvent)evt;
/* 455 */       Attr a = (Attr)me.getRelatedNode();
/* 456 */       Element e = (Element)evt.getTarget();
/* 457 */       if (e instanceof XBLOMContentElement) {
/* 458 */         String ans = a.getNamespaceURI();
/* 459 */         String aln = a.getLocalName();
/* 460 */         if (aln == null) {
/* 461 */           aln = a.getNodeName();
/*     */         }
/* 463 */         if ((ans == null && "includes".equals(aln)) || ("http://xml.apache.org/batik/ext".equals(ans) && "selectorLanguage".equals(aln))) {
/*     */ 
/*     */           
/* 466 */           ContentManager.this.selectors.remove(e);
/* 467 */           ContentManager.this.update(false);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class DOMAttrModifiedEventListener
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 478 */       if (evt.getTarget() != ContentManager.this.boundElement) {
/* 479 */         ContentManager.this.update(false);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class DOMNodeInsertedEventListener
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 489 */       ContentManager.this.update(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class DOMNodeRemovedEventListener
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 498 */       ContentManager.this.removedNode = (Node)evt.getTarget();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class DOMSubtreeModifiedEventListener
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 507 */       if (ContentManager.this.removedNode != null) {
/* 508 */         ContentManager.this.removedNode = null;
/* 509 */         ContentManager.this.update(false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ShadowTreeNodeInsertedListener
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 521 */       if (evt.getTarget() instanceof XBLOMContentElement) {
/* 522 */         ContentManager.this.update(false);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ShadowTreeNodeRemovedListener
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 533 */       EventTarget target = evt.getTarget();
/* 534 */       if (target instanceof XBLOMContentElement) {
/* 535 */         ContentManager.this.removedNode = (Node)evt.getTarget();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ShadowTreeSubtreeModifiedListener
/*     */     implements EventListener
/*     */   {
/*     */     public void handleEvent(Event evt) {
/* 546 */       if (ContentManager.this.removedNode != null) {
/* 547 */         ContentManager.this.removedNode = null;
/* 548 */         ContentManager.this.update(false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/ContentManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */