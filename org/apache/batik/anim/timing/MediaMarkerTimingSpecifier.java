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
/*    */ public class MediaMarkerTimingSpecifier
/*    */   extends TimingSpecifier
/*    */ {
/*    */   protected String syncbaseID;
/*    */   protected TimedElement mediaElement;
/*    */   protected String markerName;
/*    */   protected InstanceTime instance;
/*    */   
/*    */   public MediaMarkerTimingSpecifier(TimedElement owner, boolean isBegin, String syncbaseID, String markerName) {
/* 55 */     super(owner, isBegin);
/* 56 */     this.syncbaseID = syncbaseID;
/* 57 */     this.markerName = markerName;
/* 58 */     this.mediaElement = owner.getTimedElementById(syncbaseID);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 65 */     return this.syncbaseID + ".marker(" + this.markerName + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEventCondition() {
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/timing/MediaMarkerTimingSpecifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */