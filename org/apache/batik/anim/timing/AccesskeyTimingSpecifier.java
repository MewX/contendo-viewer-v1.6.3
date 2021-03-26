/*     */ package org.apache.batik.anim.timing;
/*     */ 
/*     */ import org.apache.batik.dom.events.DOMKeyEvent;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.w3c.dom.events.KeyboardEvent;
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
/*     */ public class AccesskeyTimingSpecifier
/*     */   extends EventLikeTimingSpecifier
/*     */   implements EventListener
/*     */ {
/*     */   protected char accesskey;
/*     */   protected boolean isSVG12AccessKey;
/*     */   protected String keyName;
/*     */   
/*     */   public AccesskeyTimingSpecifier(TimedElement owner, boolean isBegin, float offset, char accesskey) {
/*  61 */     super(owner, isBegin, offset);
/*  62 */     this.accesskey = accesskey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AccesskeyTimingSpecifier(TimedElement owner, boolean isBegin, float offset, String keyName) {
/*  70 */     super(owner, isBegin, offset);
/*  71 */     this.isSVG12AccessKey = true;
/*  72 */     this.keyName = keyName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  79 */     if (this.isSVG12AccessKey) {
/*  80 */       return "accessKey(" + this.keyName + ")" + ((this.offset != 0.0F) ? super.toString() : "");
/*     */     }
/*     */     
/*  83 */     return "accesskey(" + this.accesskey + ")" + ((this.offset != 0.0F) ? super.toString() : "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  92 */     if (this.isSVG12AccessKey) {
/*  93 */       NodeEventTarget eventTarget = (NodeEventTarget)this.owner.getRootEventTarget();
/*     */       
/*  95 */       eventTarget.addEventListenerNS("http://www.w3.org/2001/xml-events", "keydown", this, false, null);
/*     */     }
/*     */     else {
/*     */       
/*  99 */       EventTarget eventTarget = this.owner.getRootEventTarget();
/* 100 */       eventTarget.addEventListener("keypress", this, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deinitialize() {
/* 108 */     if (this.isSVG12AccessKey) {
/* 109 */       NodeEventTarget eventTarget = (NodeEventTarget)this.owner.getRootEventTarget();
/*     */       
/* 111 */       eventTarget.removeEventListenerNS("http://www.w3.org/2001/xml-events", "keydown", this, false);
/*     */     }
/*     */     else {
/*     */       
/* 115 */       EventTarget eventTarget = this.owner.getRootEventTarget();
/* 116 */       eventTarget.removeEventListener("keypress", this, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleEvent(Event e) {
/*     */     boolean matched;
/* 127 */     if (e.getType().charAt(3) == 'p') {
/*     */       
/* 129 */       DOMKeyEvent evt = (DOMKeyEvent)e;
/* 130 */       matched = (evt.getCharCode() == this.accesskey);
/*     */     } else {
/*     */       
/* 133 */       KeyboardEvent evt = (KeyboardEvent)e;
/* 134 */       matched = evt.getKeyIdentifier().equals(this.keyName);
/*     */     } 
/* 136 */     if (matched) {
/* 137 */       this.owner.eventOccurred(this, e);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resolve(Event e) {
/* 145 */     float time = this.owner.getRoot().convertEpochTime(e.getTimeStamp());
/* 146 */     InstanceTime instance = new InstanceTime(this, time + this.offset, true);
/* 147 */     this.owner.addInstanceTime(instance, this.isBegin);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/AccesskeyTimingSpecifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */