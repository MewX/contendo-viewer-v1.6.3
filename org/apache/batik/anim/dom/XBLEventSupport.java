/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.events.AbstractEvent;
/*     */ import org.apache.batik.dom.events.EventListenerList;
/*     */ import org.apache.batik.dom.events.EventSupport;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.dom.xbl.NodeXBL;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XBLEventSupport
/*     */   extends EventSupport
/*     */ {
/*     */   protected HashMap<String, EventListenerList> capturingImplementationListeners;
/*     */   protected HashMap<String, EventListenerList> bubblingImplementationListeners;
/*  63 */   protected static HashMap<String, String> eventTypeAliases = new HashMap<String, String>();
/*     */   static {
/*  65 */     eventTypeAliases.put("SVGLoad", "load");
/*  66 */     eventTypeAliases.put("SVGUnoad", "unload");
/*  67 */     eventTypeAliases.put("SVGAbort", "abort");
/*  68 */     eventTypeAliases.put("SVGError", "error");
/*  69 */     eventTypeAliases.put("SVGResize", "resize");
/*  70 */     eventTypeAliases.put("SVGScroll", "scroll");
/*  71 */     eventTypeAliases.put("SVGZoom", "zoom");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XBLEventSupport(AbstractNode n) {
/*  78 */     super(n);
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
/*     */   public void addEventListenerNS(String namespaceURI, String type, EventListener listener, boolean useCapture, Object group) {
/*  90 */     super.addEventListenerNS(namespaceURI, type, listener, useCapture, group);
/*     */     
/*  92 */     if (namespaceURI == null || namespaceURI.equals("http://www.w3.org/2001/xml-events")) {
/*     */       
/*  94 */       String alias = eventTypeAliases.get(type);
/*  95 */       if (alias != null) {
/*  96 */         super.addEventListenerNS(namespaceURI, alias, listener, useCapture, group);
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
/*     */   public void removeEventListenerNS(String namespaceURI, String type, EventListener listener, boolean useCapture) {
/* 109 */     super.removeEventListenerNS(namespaceURI, type, listener, useCapture);
/* 110 */     if (namespaceURI == null || namespaceURI.equals("http://www.w3.org/2001/xml-events")) {
/*     */       
/* 112 */       String alias = eventTypeAliases.get(type);
/* 113 */       if (alias != null) {
/* 114 */         super.removeEventListenerNS(namespaceURI, alias, listener, useCapture);
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
/*     */   public void addImplementationEventListenerNS(String namespaceURI, String type, EventListener listener, boolean useCapture) {
/*     */     HashMap<String, EventListenerList> listeners;
/* 129 */     if (useCapture) {
/* 130 */       if (this.capturingImplementationListeners == null) {
/* 131 */         this.capturingImplementationListeners = new HashMap<String, EventListenerList>();
/*     */       }
/* 133 */       listeners = this.capturingImplementationListeners;
/*     */     } else {
/* 135 */       if (this.bubblingImplementationListeners == null) {
/* 136 */         this.bubblingImplementationListeners = new HashMap<String, EventListenerList>();
/*     */       }
/* 138 */       listeners = this.bubblingImplementationListeners;
/*     */     } 
/* 140 */     EventListenerList list = listeners.get(type);
/* 141 */     if (list == null) {
/* 142 */       list = new EventListenerList();
/* 143 */       listeners.put(type, list);
/*     */     } 
/* 145 */     list.addListener(namespaceURI, null, listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeImplementationEventListenerNS(String namespaceURI, String type, EventListener listener, boolean useCapture) {
/* 155 */     HashMap<String, EventListenerList> listeners = useCapture ? this.capturingImplementationListeners : this.bubblingImplementationListeners;
/*     */     
/* 157 */     if (listeners == null) {
/*     */       return;
/*     */     }
/* 160 */     EventListenerList list = listeners.get(type);
/* 161 */     if (list == null) {
/*     */       return;
/*     */     }
/* 164 */     list.removeListener(namespaceURI, listener);
/* 165 */     if (list.size() == 0) {
/* 166 */       listeners.remove(type);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveEventListeners(EventSupport other) {
/* 177 */     super.moveEventListeners(other);
/* 178 */     XBLEventSupport es = (XBLEventSupport)other;
/* 179 */     es.capturingImplementationListeners = this.capturingImplementationListeners;
/* 180 */     es.bubblingImplementationListeners = this.bubblingImplementationListeners;
/* 181 */     this.capturingImplementationListeners = null;
/* 182 */     this.bubblingImplementationListeners = null;
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
/*     */   public boolean dispatchEvent(NodeEventTarget target, Event evt) throws EventException {
/* 214 */     if (evt == null) {
/* 215 */       return false;
/*     */     }
/* 217 */     if (!(evt instanceof AbstractEvent)) {
/* 218 */       throw createEventException((short)9, "unsupported.event", new Object[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 223 */     AbstractEvent e = (AbstractEvent)evt;
/* 224 */     String type = e.getType();
/* 225 */     if (type == null || type.length() == 0) {
/* 226 */       throw createEventException((short)0, "unspecified.event", new Object[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 232 */     setTarget(e, target);
/* 233 */     stopPropagation(e, false);
/* 234 */     stopImmediatePropagation(e, false);
/* 235 */     preventDefault(e, false);
/*     */     
/* 237 */     NodeEventTarget[] ancestors = getAncestors(target);
/* 238 */     int bubbleLimit = e.getBubbleLimit();
/* 239 */     int minAncestor = 0;
/* 240 */     if (isSingleScopeEvent((Event)e)) {
/*     */ 
/*     */       
/* 243 */       AbstractNode targetNode = (AbstractNode)target;
/* 244 */       Node boundElement = targetNode.getXblBoundElement();
/* 245 */       if (boundElement != null) {
/* 246 */         minAncestor = ancestors.length;
/* 247 */         while (minAncestor > 0) {
/* 248 */           AbstractNode ancestorNode = (AbstractNode)ancestors[minAncestor - 1];
/*     */           
/* 250 */           if (ancestorNode.getXblBoundElement() != boundElement) {
/*     */             break;
/*     */           }
/* 253 */           minAncestor--;
/*     */         } 
/*     */       } 
/* 256 */     } else if (bubbleLimit != 0) {
/*     */       
/* 258 */       minAncestor = ancestors.length - bubbleLimit + 1;
/* 259 */       if (minAncestor < 0) {
/* 260 */         minAncestor = 0;
/*     */       }
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
/* 272 */     AbstractEvent[] es = getRetargettedEvents(target, ancestors, e);
/* 273 */     boolean preventDefault = false;
/*     */     
/* 275 */     HashSet stoppedGroups = new HashSet();
/* 276 */     HashSet toBeStoppedGroups = new HashSet(); int i;
/* 277 */     for (i = 0; i < minAncestor; i++) {
/* 278 */       NodeEventTarget node = ancestors[i];
/*     */       
/* 280 */       setCurrentTarget(es[i], node);
/* 281 */       setEventPhase(es[i], (short)1);
/* 282 */       fireImplementationEventListeners(node, es[i], true);
/*     */     } 
/* 284 */     for (i = minAncestor; i < ancestors.length; i++) {
/* 285 */       NodeEventTarget node = ancestors[i];
/*     */       
/* 287 */       setCurrentTarget(es[i], node);
/* 288 */       setEventPhase(es[i], (short)1);
/* 289 */       fireImplementationEventListeners(node, es[i], true);
/* 290 */       fireEventListeners(node, es[i], true, stoppedGroups, toBeStoppedGroups);
/*     */       
/* 292 */       fireHandlerGroupEventListeners(node, es[i], true, stoppedGroups, toBeStoppedGroups);
/*     */       
/* 294 */       preventDefault = (preventDefault || es[i].getDefaultPrevented());
/* 295 */       stoppedGroups.addAll(toBeStoppedGroups);
/* 296 */       toBeStoppedGroups.clear();
/*     */     } 
/*     */ 
/*     */     
/* 300 */     setEventPhase(e, (short)2);
/* 301 */     setCurrentTarget(e, target);
/* 302 */     fireImplementationEventListeners(target, e, false);
/* 303 */     fireEventListeners(target, e, false, stoppedGroups, toBeStoppedGroups);
/*     */     
/* 305 */     fireHandlerGroupEventListeners((NodeEventTarget)this.node, e, false, stoppedGroups, toBeStoppedGroups);
/*     */     
/* 307 */     stoppedGroups.addAll(toBeStoppedGroups);
/* 308 */     toBeStoppedGroups.clear();
/* 309 */     preventDefault = (preventDefault || e.getDefaultPrevented());
/*     */     
/* 311 */     if (e.getBubbles()) {
/* 312 */       for (i = ancestors.length - 1; i >= minAncestor; i--) {
/* 313 */         NodeEventTarget node = ancestors[i];
/*     */         
/* 315 */         setCurrentTarget(es[i], node);
/* 316 */         setEventPhase(es[i], (short)3);
/* 317 */         fireImplementationEventListeners(node, es[i], false);
/* 318 */         fireEventListeners(node, es[i], false, stoppedGroups, toBeStoppedGroups);
/*     */         
/* 320 */         fireHandlerGroupEventListeners(node, es[i], false, stoppedGroups, toBeStoppedGroups);
/*     */         
/* 322 */         preventDefault = (preventDefault || es[i].getDefaultPrevented());
/*     */         
/* 324 */         stoppedGroups.addAll(toBeStoppedGroups);
/* 325 */         toBeStoppedGroups.clear();
/*     */       } 
/* 327 */       for (i = minAncestor - 1; i >= 0; i--) {
/* 328 */         NodeEventTarget node = ancestors[i];
/*     */         
/* 330 */         setCurrentTarget(es[i], node);
/* 331 */         setEventPhase(es[i], (short)3);
/* 332 */         fireImplementationEventListeners(node, es[i], false);
/* 333 */         preventDefault = (preventDefault || es[i].getDefaultPrevented());
/*     */       } 
/*     */     } 
/*     */     
/* 337 */     if (!preventDefault) {
/* 338 */       runDefaultActions(e);
/*     */     }
/* 340 */     return preventDefault;
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
/*     */   protected void fireHandlerGroupEventListeners(NodeEventTarget node, AbstractEvent e, boolean useCapture, HashSet stoppedGroups, HashSet toBeStoppedGroups) {
/* 352 */     NodeList defs = ((NodeXBL)node).getXblDefinitions();
/* 353 */     for (int j = 0; j < defs.getLength(); j++) {
/*     */       
/* 355 */       Node n = defs.item(j).getFirstChild();
/* 356 */       while (n != null && !(n instanceof XBLOMHandlerGroupElement))
/*     */       {
/* 358 */         n = n.getNextSibling();
/*     */       }
/* 360 */       if (n != null) {
/*     */ 
/*     */         
/* 363 */         node = (NodeEventTarget)n;
/* 364 */         String type = e.getType();
/* 365 */         EventSupport support = node.getEventSupport();
/*     */         
/* 367 */         if (support != null) {
/*     */ 
/*     */           
/* 370 */           EventListenerList list = support.getEventListeners(type, useCapture);
/*     */           
/* 372 */           if (list == null) {
/*     */             return;
/*     */           }
/*     */           
/* 376 */           EventListenerList.Entry[] listeners = list.getEventListeners();
/* 377 */           fireEventListeners(node, e, listeners, stoppedGroups, toBeStoppedGroups);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSingleScopeEvent(Event evt) {
/* 387 */     return (evt instanceof org.w3c.dom.events.MutationEvent || evt instanceof org.apache.batik.dom.xbl.ShadowTreeEvent);
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
/*     */   protected AbstractEvent[] getRetargettedEvents(NodeEventTarget target, NodeEventTarget[] ancestors, AbstractEvent e) {
/* 399 */     boolean singleScope = isSingleScopeEvent((Event)e);
/* 400 */     AbstractNode targetNode = (AbstractNode)target;
/* 401 */     AbstractEvent[] es = new AbstractEvent[ancestors.length];
/* 402 */     if (ancestors.length > 0) {
/* 403 */       int index = ancestors.length - 1;
/* 404 */       Node boundElement = targetNode.getXblBoundElement();
/* 405 */       AbstractNode ancestorNode = (AbstractNode)ancestors[index];
/* 406 */       if (!singleScope && ancestorNode.getXblBoundElement() != boundElement) {
/*     */         
/* 408 */         es[index] = retargetEvent(e, ancestors[index]);
/*     */       } else {
/* 410 */         es[index] = e;
/*     */       } 
/* 412 */       while (--index >= 0) {
/* 413 */         ancestorNode = (AbstractNode)ancestors[index + 1];
/* 414 */         boundElement = ancestorNode.getXblBoundElement();
/* 415 */         AbstractNode nextAncestorNode = (AbstractNode)ancestors[index];
/* 416 */         Node nextBoundElement = nextAncestorNode.getXblBoundElement();
/* 417 */         if (!singleScope && nextBoundElement != boundElement) {
/* 418 */           es[index] = retargetEvent(es[index + 1], ancestors[index]); continue;
/*     */         } 
/* 420 */         es[index] = es[index + 1];
/*     */       } 
/*     */     } 
/*     */     
/* 424 */     return es;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractEvent retargetEvent(AbstractEvent e, NodeEventTarget target) {
/* 432 */     AbstractEvent clonedEvent = e.cloneEvent();
/* 433 */     setTarget(clonedEvent, target);
/* 434 */     return clonedEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventListenerList getImplementationEventListeners(String type, boolean useCapture) {
/* 442 */     HashMap<String, EventListenerList> listeners = useCapture ? this.capturingImplementationListeners : this.bubblingImplementationListeners;
/*     */     
/* 444 */     return (listeners != null) ? listeners.get(type) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireImplementationEventListeners(NodeEventTarget node, AbstractEvent e, boolean useCapture) {
/* 454 */     String type = e.getType();
/* 455 */     XBLEventSupport support = (XBLEventSupport)node.getEventSupport();
/*     */     
/* 457 */     if (support == null) {
/*     */       return;
/*     */     }
/* 460 */     EventListenerList list = support.getImplementationEventListeners(type, useCapture);
/*     */ 
/*     */     
/* 463 */     if (list == null) {
/*     */       return;
/*     */     }
/*     */     
/* 467 */     EventListenerList.Entry[] listeners = list.getEventListeners();
/* 468 */     fireEventListeners(node, e, listeners, null, null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/XBLEventSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */