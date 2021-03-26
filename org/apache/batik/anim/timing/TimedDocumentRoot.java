/*     */ package org.apache.batik.anim.timing;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.LinkedList;
/*     */ import org.apache.batik.util.DoublyIndexedSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TimedDocumentRoot
/*     */   extends TimeContainer
/*     */ {
/*     */   protected Calendar documentBeginTime;
/*     */   protected boolean useSVG11AccessKeys;
/*     */   protected boolean useSVG12AccessKeys;
/*  56 */   protected DoublyIndexedSet propagationFlags = new DoublyIndexedSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   protected LinkedList listeners = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSampling;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isHyperlinking;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimedDocumentRoot(boolean useSVG11AccessKeys, boolean useSVG12AccessKeys) {
/*  83 */     this.root = this;
/*  84 */     this.useSVG11AccessKeys = useSVG11AccessKeys;
/*  85 */     this.useSVG12AccessKeys = useSVG12AccessKeys;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getImplicitDur() {
/*  93 */     return Float.POSITIVE_INFINITY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDefaultBegin(TimedElement child) {
/* 103 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCurrentTime() {
/* 110 */     return this.lastSampleTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSampling() {
/* 117 */     return this.isSampling;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHyperlinking() {
/* 124 */     return this.isHyperlinking;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float seekTo(float time, boolean hyperlinking) {
/* 132 */     this.isSampling = true;
/* 133 */     this.lastSampleTime = time;
/* 134 */     this.isHyperlinking = hyperlinking;
/* 135 */     this.propagationFlags.clear();
/*     */ 
/*     */     
/* 138 */     float mint = Float.POSITIVE_INFINITY;
/* 139 */     TimedElement[] es = getChildren();
/* 140 */     for (TimedElement e1 : es) {
/* 141 */       float t = e1.sampleAt(time, hyperlinking);
/* 142 */       if (t < mint) {
/* 143 */         mint = t;
/*     */       }
/*     */     } 
/*     */     
/*     */     while (true) {
/* 148 */       boolean needsUpdates = false;
/* 149 */       for (TimedElement e : es) {
/* 150 */         if (e.shouldUpdateCurrentInterval) {
/* 151 */           needsUpdates = true;
/*     */           
/* 153 */           float t = e.sampleAt(time, hyperlinking);
/* 154 */           if (t < mint) {
/* 155 */             mint = t;
/*     */           }
/*     */         } 
/*     */       } 
/* 159 */       if (!needsUpdates) {
/* 160 */         this.isSampling = false;
/* 161 */         if (hyperlinking) {
/* 162 */           this.root.currentIntervalWillUpdate();
/*     */         }
/* 164 */         return mint;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetDocument(Calendar documentBeginTime) {
/* 172 */     if (documentBeginTime == null) {
/* 173 */       this.documentBeginTime = Calendar.getInstance();
/*     */     } else {
/* 175 */       this.documentBeginTime = documentBeginTime;
/*     */     } 
/* 177 */     reset(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getDocumentBeginTime() {
/* 184 */     return this.documentBeginTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float convertEpochTime(long t) {
/* 191 */     long begin = this.documentBeginTime.getTime().getTime();
/* 192 */     return (float)(t - begin) / 1000.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float convertWallclockTime(Calendar time) {
/* 199 */     long begin = this.documentBeginTime.getTime().getTime();
/* 200 */     long t = time.getTime().getTime();
/* 201 */     return (float)(t - begin) / 1000.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTimegraphListener(TimegraphListener l) {
/* 208 */     this.listeners.add(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTimegraphListener(TimegraphListener l) {
/* 215 */     this.listeners.remove(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void fireElementAdded(TimedElement e) {
/* 223 */     for (Object listener : this.listeners) {
/* 224 */       ((TimegraphListener)listener).elementAdded(e);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void fireElementRemoved(TimedElement e) {
/* 233 */     for (Object listener : this.listeners) {
/* 234 */       ((TimegraphListener)listener).elementRemoved(e);
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
/*     */ 
/*     */ 
/*     */   
/*     */   boolean shouldPropagate(Interval i, TimingSpecifier ts, boolean isBegin) {
/* 250 */     InstanceTime it = isBegin ? i.getBeginInstanceTime() : i.getEndInstanceTime();
/*     */     
/* 252 */     if (this.propagationFlags.contains(it, ts)) {
/* 253 */       return false;
/*     */     }
/* 255 */     this.propagationFlags.add(it, ts);
/* 256 */     return true;
/*     */   }
/*     */   
/*     */   protected void currentIntervalWillUpdate() {}
/*     */   
/*     */   protected abstract String getEventNamespaceURI(String paramString);
/*     */   
/*     */   protected abstract String getEventType(String paramString);
/*     */   
/*     */   protected abstract String getRepeatEventName();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/TimedDocumentRoot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */