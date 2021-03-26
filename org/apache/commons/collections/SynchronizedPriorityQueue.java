/*    */ package org.apache.commons.collections;
/*    */ 
/*    */ import java.util.NoSuchElementException;
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
/*    */ public final class SynchronizedPriorityQueue
/*    */   implements PriorityQueue
/*    */ {
/*    */   protected final PriorityQueue m_priorityQueue;
/*    */   
/*    */   public SynchronizedPriorityQueue(PriorityQueue priorityQueue) {
/* 45 */     this.m_priorityQueue = priorityQueue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized void clear() {
/* 52 */     this.m_priorityQueue.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized boolean isEmpty() {
/* 61 */     return this.m_priorityQueue.isEmpty();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized void insert(Object element) {
/* 70 */     this.m_priorityQueue.insert(element);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized Object peek() throws NoSuchElementException {
/* 80 */     return this.m_priorityQueue.peek();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized Object pop() throws NoSuchElementException {
/* 90 */     return this.m_priorityQueue.pop();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized String toString() {
/* 99 */     return this.m_priorityQueue.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/SynchronizedPriorityQueue.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */