/*    */ package org.apache.batik.anim.timing;
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
/*    */ public class OffsetTimingSpecifier
/*    */   extends TimingSpecifier
/*    */ {
/*    */   protected float offset;
/*    */   
/*    */   public OffsetTimingSpecifier(TimedElement owner, boolean isBegin, float offset) {
/* 39 */     super(owner, isBegin);
/* 40 */     this.offset = offset;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     return ((this.offset >= 0.0F) ? "+" : "") + this.offset;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialize() {
/* 55 */     InstanceTime instance = new InstanceTime(this, this.offset, false);
/* 56 */     this.owner.addInstanceTime(instance, this.isBegin);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEventCondition() {
/* 64 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/OffsetTimingSpecifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */