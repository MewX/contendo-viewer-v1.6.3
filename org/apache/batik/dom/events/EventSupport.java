/*     */ package org.apache.batik.dom.events;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.AbstractNode;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EventSupport
/*     */ {
/*     */   protected HashMap<String, EventListenerList> capturingListeners;
/*     */   protected HashMap<String, EventListenerList> bubblingListeners;
/*     */   protected AbstractNode node;
/*     */   
/*     */   public EventSupport(AbstractNode n) {
/*  64 */     this.node = n;
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
/*     */   public void addEventListener(String type, EventListener listener, boolean useCapture) {
/*  96 */     addEventListenerNS(null, type, listener, useCapture, null);
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
/*     */     HashMap<String, EventListenerList> listeners;
/* 109 */     if (useCapture) {
/* 110 */       if (this.capturingListeners == null) {
/* 111 */         this.capturingListeners = new HashMap<String, EventListenerList>();
/*     */       }
/* 113 */       listeners = this.capturingListeners;
/*     */     } else {
/* 115 */       if (this.bubblingListeners == null) {
/* 116 */         this.bubblingListeners = new HashMap<String, EventListenerList>();
/*     */       }
/* 118 */       listeners = this.bubblingListeners;
/*     */     } 
/* 120 */     EventListenerList list = listeners.get(type);
/* 121 */     if (list == null) {
/* 122 */       list = new EventListenerList();
/* 123 */       listeners.put(type, list);
/*     */     } 
/* 125 */     list.addListener(namespaceURI, group, listener);
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
/*     */   public void removeEventListener(String type, EventListener listener, boolean useCapture) {
/* 157 */     removeEventListenerNS(null, type, listener, useCapture);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeEventListenerNS(String namespaceURI, String type, EventListener listener, boolean useCapture) {
/*     */     HashMap<String, EventListenerList> listeners;
/* 168 */     if (useCapture) {
/* 169 */       listeners = this.capturingListeners;
/*     */     } else {
/* 171 */       listeners = this.bubblingListeners;
/*     */     } 
/* 173 */     if (listeners == null) {
/*     */       return;
/*     */     }
/* 176 */     EventListenerList list = listeners.get(type);
/* 177 */     if (list != null) {
/* 178 */       list.removeListener(namespaceURI, listener);
/* 179 */       if (list.size() == 0) {
/* 180 */         listeners.remove(type);
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
/*     */   public void moveEventListeners(EventSupport other) {
/* 192 */     other.capturingListeners = this.capturingListeners;
/* 193 */     other.bubblingListeners = this.bubblingListeners;
/* 194 */     this.capturingListeners = null;
/* 195 */     this.bubblingListeners = null;
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
/*     */   public boolean dispatchEvent(NodeEventTarget target, Event evt) throws EventException {
/* 226 */     if (evt == null) {
/* 227 */       return false;
/*     */     }
/* 229 */     if (!(evt instanceof AbstractEvent)) {
/* 230 */       throw createEventException((short)9, "unsupported.event", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/* 234 */     AbstractEvent e = (AbstractEvent)evt;
/* 235 */     String type = e.getType();
/* 236 */     if (type == null || type.length() == 0) {
/* 237 */       throw createEventException((short)0, "unspecified.event", new Object[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 243 */     e.setTarget(target);
/* 244 */     e.stopPropagation(false);
/* 245 */     e.stopImmediatePropagation(false);
/* 246 */     e.preventDefault(false);
/*     */     
/* 248 */     NodeEventTarget[] ancestors = getAncestors(target);
/*     */     
/* 250 */     e.setEventPhase((short)1);
/* 251 */     HashSet stoppedGroups = new HashSet();
/* 252 */     HashSet toBeStoppedGroups = new HashSet();
/* 253 */     for (NodeEventTarget node : ancestors) {
/* 254 */       e.setCurrentTarget(node);
/* 255 */       fireEventListeners(node, e, true, stoppedGroups, toBeStoppedGroups);
/*     */       
/* 257 */       stoppedGroups.addAll(toBeStoppedGroups);
/* 258 */       toBeStoppedGroups.clear();
/*     */     } 
/*     */     
/* 261 */     e.setEventPhase((short)2);
/* 262 */     e.setCurrentTarget(target);
/* 263 */     fireEventListeners(target, e, false, stoppedGroups, toBeStoppedGroups);
/*     */     
/* 265 */     stoppedGroups.addAll(toBeStoppedGroups);
/* 266 */     toBeStoppedGroups.clear();
/*     */     
/* 268 */     if (e.getBubbles()) {
/* 269 */       e.setEventPhase((short)3);
/* 270 */       for (int i = ancestors.length - 1; i >= 0; i--) {
/* 271 */         NodeEventTarget node = ancestors[i];
/* 272 */         e.setCurrentTarget(node);
/* 273 */         fireEventListeners(node, e, false, stoppedGroups, toBeStoppedGroups);
/*     */         
/* 275 */         stoppedGroups.addAll(toBeStoppedGroups);
/* 276 */         toBeStoppedGroups.clear();
/*     */       } 
/*     */     } 
/* 279 */     if (!e.getDefaultPrevented()) {
/* 280 */       runDefaultActions(e);
/*     */     }
/* 282 */     return e.getDefaultPrevented();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void runDefaultActions(AbstractEvent e) {
/* 289 */     List runables = e.getDefaultActions();
/* 290 */     if (runables != null) {
/* 291 */       for (Object runable : runables) {
/* 292 */         Runnable r = (Runnable)runable;
/* 293 */         r.run();
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
/*     */   protected void fireEventListeners(NodeEventTarget node, AbstractEvent e, EventListenerList.Entry[] listeners, HashSet<Object> stoppedGroups, HashSet<Object> toBeStoppedGroups) {
/* 306 */     if (listeners == null) {
/*     */       return;
/*     */     }
/*     */     
/* 310 */     String eventNS = e.getNamespaceURI();
/* 311 */     for (EventListenerList.Entry listener : listeners) {
/*     */       try {
/* 313 */         String listenerNS = listener.getNamespaceURI();
/* 314 */         if (listenerNS == null || eventNS == null || listenerNS.equals(eventNS)) {
/*     */ 
/*     */ 
/*     */           
/* 318 */           Object group = listener.getGroup();
/* 319 */           if (stoppedGroups == null || !stoppedGroups.contains(group)) {
/* 320 */             listener.getListener().handleEvent(e);
/* 321 */             if (e.getStopImmediatePropagation())
/* 322 */             { if (stoppedGroups != null) {
/* 323 */                 stoppedGroups.add(group);
/*     */               }
/* 325 */               e.stopImmediatePropagation(false); }
/* 326 */             else if (e.getStopPropagation())
/* 327 */             { if (toBeStoppedGroups != null) {
/* 328 */                 toBeStoppedGroups.add(group);
/*     */               }
/* 330 */               e.stopPropagation(false); } 
/*     */           } 
/*     */         } 
/* 333 */       } catch (ThreadDeath td) {
/* 334 */         throw td;
/* 335 */       } catch (Throwable th) {
/* 336 */         th.printStackTrace();
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
/*     */   protected void fireEventListeners(NodeEventTarget node, AbstractEvent e, boolean useCapture, HashSet stoppedGroups, HashSet toBeStoppedGroups) {
/* 349 */     String type = e.getType();
/* 350 */     EventSupport support = node.getEventSupport();
/*     */     
/* 352 */     if (support == null) {
/*     */       return;
/*     */     }
/* 355 */     EventListenerList list = support.getEventListeners(type, useCapture);
/*     */     
/* 357 */     if (list == null) {
/*     */       return;
/*     */     }
/*     */     
/* 361 */     EventListenerList.Entry[] listeners = list.getEventListeners();
/* 362 */     fireEventListeners(node, e, listeners, stoppedGroups, toBeStoppedGroups);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected NodeEventTarget[] getAncestors(NodeEventTarget node) {
/* 370 */     node = node.getParentNodeEventTarget();
/* 371 */     int nancestors = 0;
/* 372 */     NodeEventTarget n = node;
/* 373 */     while (n != null) {
/* 374 */       n = n.getParentNodeEventTarget(); nancestors++;
/*     */     } 
/* 376 */     NodeEventTarget[] ancestors = new NodeEventTarget[nancestors];
/* 377 */     int i = nancestors - 1;
/* 378 */     for (; i >= 0; 
/* 379 */       i--, node = node.getParentNodeEventTarget()) {
/* 380 */       ancestors[i] = node;
/*     */     }
/* 382 */     return ancestors;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasEventListenerNS(String namespaceURI, String type) {
/* 390 */     if (this.capturingListeners != null) {
/* 391 */       EventListenerList ell = this.capturingListeners.get(type);
/* 392 */       if (ell != null && 
/* 393 */         ell.hasEventListener(namespaceURI)) {
/* 394 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 398 */     if (this.bubblingListeners != null) {
/* 399 */       EventListenerList ell = this.capturingListeners.get(type);
/* 400 */       if (ell != null) {
/* 401 */         return ell.hasEventListener(namespaceURI);
/*     */       }
/*     */     } 
/* 404 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventListenerList getEventListeners(String type, boolean useCapture) {
/* 415 */     HashMap<String, EventListenerList> listeners = useCapture ? this.capturingListeners : this.bubblingListeners;
/*     */     
/* 417 */     if (listeners == null) {
/* 418 */       return null;
/*     */     }
/* 420 */     return listeners.get(type);
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
/*     */   protected EventException createEventException(short code, String key, Object[] args) {
/*     */     try {
/* 434 */       AbstractDocument doc = (AbstractDocument)this.node.getOwnerDocument();
/* 435 */       return new EventException(code, doc.formatMessage(key, args));
/* 436 */     } catch (Exception e) {
/* 437 */       return new EventException(code, key);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTarget(AbstractEvent e, NodeEventTarget target) {
/* 445 */     e.setTarget(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void stopPropagation(AbstractEvent e, boolean b) {
/* 452 */     e.stopPropagation(b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void stopImmediatePropagation(AbstractEvent e, boolean b) {
/* 459 */     e.stopImmediatePropagation(b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preventDefault(AbstractEvent e, boolean b) {
/* 466 */     e.preventDefault(b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setCurrentTarget(AbstractEvent e, NodeEventTarget target) {
/* 473 */     e.setCurrentTarget(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setEventPhase(AbstractEvent e, short phase) {
/* 480 */     e.setEventPhase(phase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Event getUltimateOriginalEvent(Event evt) {
/* 487 */     AbstractEvent e = (AbstractEvent)evt;
/*     */     while (true) {
/* 489 */       AbstractEvent origEvt = (AbstractEvent)e.getOriginalEvent();
/* 490 */       if (origEvt == null) {
/*     */         break;
/*     */       }
/* 493 */       e = origEvt;
/*     */     } 
/* 495 */     return e;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/EventSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */