/*    */ package org.apache.xml.dtm.ref;
/*    */ 
/*    */ import org.apache.xml.dtm.DTMAxisIterator;
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
/*    */ public final class EmptyIterator
/*    */   implements DTMAxisIterator
/*    */ {
/* 31 */   private static final EmptyIterator INSTANCE = new EmptyIterator();
/*    */   public static DTMAxisIterator getInstance() {
/* 33 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public final int next() {
/* 37 */     return -1;
/*    */   } public final DTMAxisIterator reset() {
/* 39 */     return this;
/*    */   } public final int getLast() {
/* 41 */     return 0;
/*    */   } public final int getPosition() {
/* 43 */     return 1;
/*    */   }
/*    */   public final void setMark() {}
/*    */   public final void gotoMark() {}
/*    */   
/*    */   public final DTMAxisIterator setStartNode(int node) {
/* 49 */     return this;
/*    */   } public final int getStartNode() {
/* 51 */     return -1;
/*    */   } public final boolean isReverse() {
/* 53 */     return false;
/*    */   } public final DTMAxisIterator cloneIterator() {
/* 55 */     return this;
/*    */   }
/*    */   public final void setRestartable(boolean isRestartable) {}
/*    */   public final int getNodeByPosition(int position) {
/* 59 */     return -1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/EmptyIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */