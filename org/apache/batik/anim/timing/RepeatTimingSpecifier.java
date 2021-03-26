/*    */ package org.apache.batik.anim.timing;
/*    */ 
/*    */ import org.w3c.dom.events.Event;
/*    */ import org.w3c.dom.smil.TimeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RepeatTimingSpecifier
/*    */   extends EventbaseTimingSpecifier
/*    */ {
/*    */   protected int repeatIteration;
/*    */   protected boolean repeatIterationSpecified;
/*    */   
/*    */   public RepeatTimingSpecifier(TimedElement owner, boolean isBegin, float offset, String syncbaseID) {
/* 47 */     super(owner, isBegin, offset, syncbaseID, owner.getRoot().getRepeatEventName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RepeatTimingSpecifier(TimedElement owner, boolean isBegin, float offset, String syncbaseID, int repeatIteration) {
/* 57 */     super(owner, isBegin, offset, syncbaseID, owner.getRoot().getRepeatEventName());
/*    */     
/* 59 */     this.repeatIteration = repeatIteration;
/* 60 */     this.repeatIterationSpecified = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     return ((this.eventbaseID == null) ? "" : (this.eventbaseID + ".")) + "repeat" + (this.repeatIterationSpecified ? ("(" + this.repeatIteration + ")") : "") + ((this.offset != 0.0F) ? super.toString() : "");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleEvent(Event e) {
/* 78 */     TimeEvent evt = (TimeEvent)e;
/* 79 */     if (!this.repeatIterationSpecified || evt.getDetail() == this.repeatIteration)
/* 80 */       super.handleEvent(e); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/RepeatTimingSpecifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */