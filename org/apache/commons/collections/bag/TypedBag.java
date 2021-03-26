/*    */ package org.apache.commons.collections.bag;
/*    */ 
/*    */ import org.apache.commons.collections.Bag;
/*    */ import org.apache.commons.collections.functors.InstanceofPredicate;
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
/*    */ public class TypedBag
/*    */ {
/*    */   public static Bag decorate(Bag bag, Class type) {
/* 50 */     return new PredicatedBag(bag, InstanceofPredicate.getInstance(type));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/TypedBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */