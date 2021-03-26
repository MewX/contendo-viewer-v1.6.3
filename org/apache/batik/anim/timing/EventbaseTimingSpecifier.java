/*     */ package org.apache.batik.anim.timing;
/*     */ 
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EventbaseTimingSpecifier
/*     */   extends EventLikeTimingSpecifier
/*     */   implements EventListener
/*     */ {
/*     */   protected String eventbaseID;
/*     */   protected TimedElement eventbase;
/*     */   protected EventTarget eventTarget;
/*     */   protected String eventNamespaceURI;
/*     */   protected String eventType;
/*     */   protected String eventName;
/*     */   
/*     */   public EventbaseTimingSpecifier(TimedElement owner, boolean isBegin, float offset, String eventbaseID, String eventName) {
/*  73 */     super(owner, isBegin, offset);
/*  74 */     this.eventbaseID = eventbaseID;
/*  75 */     this.eventName = eventName;
/*  76 */     TimedDocumentRoot root = owner.getRoot();
/*  77 */     this.eventNamespaceURI = root.getEventNamespaceURI(eventName);
/*  78 */     this.eventType = root.getEventType(eventName);
/*  79 */     if (eventbaseID == null) {
/*  80 */       this.eventTarget = owner.getAnimationEventTarget();
/*     */     } else {
/*  82 */       this.eventTarget = owner.getEventTargetById(eventbaseID);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  90 */     return ((this.eventbaseID == null) ? "" : (this.eventbaseID + ".")) + this.eventName + ((this.offset != 0.0F) ? super.toString() : "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  99 */     ((NodeEventTarget)this.eventTarget).addEventListenerNS(this.eventNamespaceURI, this.eventType, this, false, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deinitialize() {
/* 107 */     ((NodeEventTarget)this.eventTarget).removeEventListenerNS(this.eventNamespaceURI, this.eventType, this, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleEvent(Event e) {
/* 117 */     this.owner.eventOccurred(this, e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resolve(Event e) {
/* 124 */     float time = this.owner.getRoot().convertEpochTime(e.getTimeStamp());
/* 125 */     InstanceTime instance = new InstanceTime(this, time + this.offset, true);
/* 126 */     this.owner.addInstanceTime(instance, this.isBegin);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/EventbaseTimingSpecifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */