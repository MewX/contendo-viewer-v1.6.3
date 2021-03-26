/*    */ package org.apache.commons.collections.bag;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.apache.commons.collections.Bag;
/*    */ import org.apache.commons.collections.SortedBag;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
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
/*    */ public class TransformedSortedBag
/*    */   extends TransformedBag
/*    */   implements SortedBag
/*    */ {
/*    */   private static final long serialVersionUID = -251737742649401930L;
/*    */   
/*    */   public static SortedBag decorate(SortedBag bag, Transformer transformer) {
/* 56 */     return new TransformedSortedBag(bag, transformer);
/*    */   }
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
/*    */   protected TransformedSortedBag(SortedBag bag, Transformer transformer) {
/* 71 */     super((Bag)bag, transformer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SortedBag getSortedBag() {
/* 80 */     return (SortedBag)((AbstractCollectionDecorator)this).collection;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object first() {
/* 85 */     return getSortedBag().first();
/*    */   }
/*    */   
/*    */   public Object last() {
/* 89 */     return getSortedBag().last();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 93 */     return getSortedBag().comparator();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/TransformedSortedBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */