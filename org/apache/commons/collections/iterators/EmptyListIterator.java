/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import org.apache.commons.collections.ResettableListIterator;
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
/*    */ public class EmptyListIterator
/*    */   extends AbstractEmptyIterator
/*    */   implements ResettableListIterator
/*    */ {
/* 40 */   public static final ResettableListIterator RESETTABLE_INSTANCE = new EmptyListIterator();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public static final ListIterator INSTANCE = (ListIterator)RESETTABLE_INSTANCE;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/EmptyListIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */