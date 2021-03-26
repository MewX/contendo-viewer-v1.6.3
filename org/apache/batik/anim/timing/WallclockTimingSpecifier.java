/*    */ package org.apache.batik.anim.timing;
/*    */ 
/*    */ import java.util.Calendar;
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
/*    */ public class WallclockTimingSpecifier
/*    */   extends TimingSpecifier
/*    */ {
/*    */   protected Calendar time;
/*    */   protected InstanceTime instance;
/*    */   
/*    */   public WallclockTimingSpecifier(TimedElement owner, boolean isBegin, Calendar time) {
/* 46 */     super(owner, isBegin);
/* 47 */     this.time = time;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return "wallclock(" + this.time.toString() + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialize() {
/* 62 */     float t = this.owner.getRoot().convertWallclockTime(this.time);
/* 63 */     this.instance = new InstanceTime(this, t, false);
/* 64 */     this.owner.addInstanceTime(this.instance, this.isBegin);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEventCondition() {
/* 72 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/WallclockTimingSpecifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */