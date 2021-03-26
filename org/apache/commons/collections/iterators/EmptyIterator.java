/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.Iterator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EmptyIterator
/*    */   extends AbstractEmptyIterator
/*    */   implements ResettableIterator
/*    */ {
/* 40 */   public static final ResettableIterator RESETTABLE_INSTANCE = new EmptyIterator();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public static final Iterator INSTANCE = (Iterator)RESETTABLE_INSTANCE;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/EmptyIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */