/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.OrderedIterator;
/*    */ import org.apache.commons.collections.ResettableIterator;
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
/*    */ public class EmptyOrderedIterator
/*    */   extends AbstractEmptyIterator
/*    */   implements OrderedIterator, ResettableIterator
/*    */ {
/* 35 */   public static final OrderedIterator INSTANCE = new EmptyOrderedIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/EmptyOrderedIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */