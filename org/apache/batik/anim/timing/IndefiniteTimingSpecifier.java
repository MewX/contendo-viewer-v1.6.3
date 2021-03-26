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
/*    */ public class IndefiniteTimingSpecifier
/*    */   extends TimingSpecifier
/*    */ {
/*    */   public IndefiniteTimingSpecifier(TimedElement owner, boolean isBegin) {
/* 33 */     super(owner, isBegin);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     return "indefinite";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialize() {
/* 48 */     if (!this.isBegin) {
/*    */ 
/*    */       
/* 51 */       InstanceTime instance = new InstanceTime(this, Float.POSITIVE_INFINITY, false);
/*    */       
/* 53 */       this.owner.addInstanceTime(instance, this.isBegin);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEventCondition() {
/* 62 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/IndefiniteTimingSpecifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */