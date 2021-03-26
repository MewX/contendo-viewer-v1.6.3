/*     */ package org.apache.batik.anim.timing;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SyncbaseTimingSpecifier
/*     */   extends OffsetTimingSpecifier
/*     */ {
/*     */   protected String syncbaseID;
/*     */   protected TimedElement syncbaseElement;
/*     */   protected boolean syncBegin;
/*  52 */   protected HashMap instances = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SyncbaseTimingSpecifier(TimedElement owner, boolean isBegin, float offset, String syncbaseID, boolean syncBegin) {
/*  60 */     super(owner, isBegin, offset);
/*     */     
/*  62 */     this.syncbaseID = syncbaseID;
/*  63 */     this.syncBegin = syncBegin;
/*  64 */     this.syncbaseElement = owner.getTimedElementById(syncbaseID);
/*  65 */     this.syncbaseElement.addDependent(this, syncBegin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  73 */     return this.syncbaseID + "." + (this.syncBegin ? "begin" : "end") + ((this.offset != 0.0F) ? super.toString() : "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEventCondition() {
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float newInterval(Interval interval) {
/*  97 */     if (this.owner.hasPropagated) {
/*  98 */       return Float.POSITIVE_INFINITY;
/*     */     }
/* 100 */     InstanceTime instance = new InstanceTime(this, (this.syncBegin ? interval.getBegin() : interval.getEnd()) + this.offset, true);
/*     */ 
/*     */ 
/*     */     
/* 104 */     this.instances.put(interval, instance);
/* 105 */     interval.addDependent(instance, this.syncBegin);
/* 106 */     return this.owner.addInstanceTime(instance, this.isBegin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float removeInterval(Interval interval) {
/* 115 */     if (this.owner.hasPropagated) {
/* 116 */       return Float.POSITIVE_INFINITY;
/*     */     }
/* 118 */     InstanceTime instance = (InstanceTime)this.instances.get(interval);
/* 119 */     interval.removeDependent(instance, this.syncBegin);
/* 120 */     return this.owner.removeInstanceTime(instance, this.isBegin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float handleTimebaseUpdate(InstanceTime instanceTime, float newTime) {
/* 130 */     if (this.owner.hasPropagated) {
/* 131 */       return Float.POSITIVE_INFINITY;
/*     */     }
/* 133 */     return this.owner.instanceTimeChanged(instanceTime, this.isBegin);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/SyncbaseTimingSpecifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */