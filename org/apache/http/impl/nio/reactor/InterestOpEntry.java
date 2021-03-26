/*    */ package org.apache.http.impl.nio.reactor;
/*    */ 
/*    */ import java.nio.channels.SelectionKey;
/*    */ import org.apache.http.util.Args;
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
/*    */ class InterestOpEntry
/*    */ {
/*    */   private final SelectionKey key;
/*    */   private final int eventMask;
/*    */   
/*    */   public InterestOpEntry(SelectionKey key, int eventMask) {
/* 47 */     Args.notNull(key, "Selection key");
/* 48 */     this.key = key;
/* 49 */     this.eventMask = eventMask;
/*    */   }
/*    */   
/*    */   public SelectionKey getSelectionKey() {
/* 53 */     return this.key;
/*    */   }
/*    */   
/*    */   public int getEventMask() {
/* 57 */     return this.eventMask;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 62 */     if (this == obj) {
/* 63 */       return true;
/*    */     }
/* 65 */     return (obj instanceof InterestOpEntry) ? this.key.equals(((InterestOpEntry)obj).key) : false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 70 */     return this.key.hashCode();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/InterestOpEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */