/*     */ package org.apache.batik.dom.events;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.dom.xbl.OriginalEvent;
/*     */ import org.w3c.dom.events.Event;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractEvent
/*     */   implements Cloneable, OriginalEvent, Event
/*     */ {
/*     */   protected String type;
/*     */   protected boolean isBubbling;
/*     */   protected boolean cancelable;
/*     */   protected EventTarget currentTarget;
/*     */   protected EventTarget target;
/*     */   protected short eventPhase;
/*  72 */   protected long timeStamp = System.currentTimeMillis();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean stopPropagation = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean stopImmediatePropagation = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean preventDefault = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String namespaceURI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Event originalEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List defaultActions;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   protected int bubbleLimit = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 120 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventTarget getCurrentTarget() {
/* 129 */     return this.currentTarget;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventTarget getTarget() {
/* 138 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getEventPhase() {
/* 146 */     return this.eventPhase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBubbles() {
/* 155 */     return this.isBubbling;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCancelable() {
/* 165 */     return this.cancelable;
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
/*     */   public long getTimeStamp() {
/* 177 */     return this.timeStamp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/* 184 */     return this.namespaceURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Event getOriginalEvent() {
/* 191 */     return this.originalEvent;
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
/*     */   public void stopPropagation() {
/* 204 */     this.stopPropagation = true;
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
/*     */   public void preventDefault() {
/* 221 */     this.preventDefault = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDefaultPrevented() {
/* 229 */     return this.preventDefault;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getDefaultActions() {
/* 235 */     return this.defaultActions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDefaultAction(Runnable rable) {
/* 241 */     if (this.defaultActions == null) this.defaultActions = new ArrayList(); 
/* 242 */     this.defaultActions.add(rable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopImmediatePropagation() {
/* 250 */     this.stopImmediatePropagation = true;
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
/*     */   public void initEvent(String eventTypeArg, boolean canBubbleArg, boolean cancelableArg) {
/* 275 */     this.type = eventTypeArg;
/* 276 */     this.isBubbling = canBubbleArg;
/* 277 */     this.cancelable = cancelableArg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEventNS(String namespaceURIArg, String eventTypeArg, boolean canBubbleArg, boolean cancelableArg) {
/* 288 */     if (this.namespaceURI != null && this.namespaceURI.length() == 0) {
/* 289 */       this.namespaceURI = null;
/*     */     }
/* 291 */     this.namespaceURI = namespaceURIArg;
/* 292 */     this.type = eventTypeArg;
/* 293 */     this.isBubbling = canBubbleArg;
/* 294 */     this.cancelable = cancelableArg;
/*     */   }
/*     */   
/*     */   boolean getStopPropagation() {
/* 298 */     return this.stopPropagation;
/*     */   }
/*     */   
/*     */   boolean getStopImmediatePropagation() {
/* 302 */     return this.stopImmediatePropagation;
/*     */   }
/*     */   
/*     */   void setEventPhase(short eventPhase) {
/* 306 */     this.eventPhase = eventPhase;
/*     */   }
/*     */   
/*     */   void stopPropagation(boolean state) {
/* 310 */     this.stopPropagation = state;
/*     */   }
/*     */   
/*     */   void stopImmediatePropagation(boolean state) {
/* 314 */     this.stopImmediatePropagation = state;
/*     */   }
/*     */   
/*     */   void preventDefault(boolean state) {
/* 318 */     this.preventDefault = state;
/*     */   }
/*     */   
/*     */   void setCurrentTarget(EventTarget currentTarget) {
/* 322 */     this.currentTarget = currentTarget;
/*     */   }
/*     */   
/*     */   void setTarget(EventTarget target) {
/* 326 */     this.target = target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 333 */     AbstractEvent newEvent = (AbstractEvent)super.clone();
/* 334 */     newEvent.timeStamp = System.currentTimeMillis();
/* 335 */     return newEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEvent cloneEvent() {
/*     */     try {
/* 344 */       AbstractEvent newEvent = (AbstractEvent)clone();
/* 345 */       newEvent.originalEvent = this;
/* 346 */       return newEvent;
/* 347 */     } catch (CloneNotSupportedException e) {
/* 348 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBubbleLimit() {
/* 356 */     return this.bubbleLimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBubbleLimit(int n) {
/* 363 */     this.bubbleLimit = n;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/AbstractEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */