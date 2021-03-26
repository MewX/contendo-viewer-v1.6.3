/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.OrderedMapIterator;
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
/*    */ public class EmptyOrderedMapIterator
/*    */   extends AbstractEmptyIterator
/*    */   implements OrderedMapIterator, ResettableIterator
/*    */ {
/* 35 */   public static final OrderedMapIterator INSTANCE = new EmptyOrderedMapIterator();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/EmptyOrderedMapIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */