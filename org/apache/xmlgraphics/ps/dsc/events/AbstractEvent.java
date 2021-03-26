/*    */ package org.apache.xmlgraphics.ps.dsc.events;
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
/*    */ public abstract class AbstractEvent
/*    */   implements DSCEvent
/*    */ {
/*    */   public boolean isComment() {
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isDSCComment() {
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isHeaderComment() {
/* 45 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isLine() {
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DSCComment asDSCComment() {
/* 59 */     throw new ClassCastException(getClass().getName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PostScriptLine asLine() {
/* 66 */     throw new ClassCastException(getClass().getName());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/AbstractEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */