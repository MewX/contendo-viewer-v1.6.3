/*    */ package org.apache.batik.anim.timing;
/*    */ 
/*    */ import org.w3c.dom.events.Event;
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
/*    */ public abstract class EventLikeTimingSpecifier
/*    */   extends OffsetTimingSpecifier
/*    */ {
/*    */   public EventLikeTimingSpecifier(TimedElement owner, boolean isBegin, float offset) {
/* 36 */     super(owner, isBegin, offset);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEventCondition() {
/* 44 */     return true;
/*    */   }
/*    */   
/*    */   public abstract void resolve(Event paramEvent);
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/EventLikeTimingSpecifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */