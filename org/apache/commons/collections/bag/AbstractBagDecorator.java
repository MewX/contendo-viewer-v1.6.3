/*    */ package org.apache.commons.collections.bag;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections.Bag;
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
/*    */ public abstract class AbstractBagDecorator
/*    */   extends AbstractCollectionDecorator
/*    */   implements Bag
/*    */ {
/*    */   protected AbstractBagDecorator() {}
/*    */   
/*    */   protected AbstractBagDecorator(Bag bag) {
/* 51 */     super((Collection)bag);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Bag getBag() {
/* 60 */     return (Bag)getCollection();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCount(Object object) {
/* 65 */     return getBag().getCount(object);
/*    */   }
/*    */   
/*    */   public boolean add(Object object, int count) {
/* 69 */     return getBag().add(object, count);
/*    */   }
/*    */   
/*    */   public boolean remove(Object object, int count) {
/* 73 */     return getBag().remove(object, count);
/*    */   }
/*    */   
/*    */   public Set uniqueSet() {
/* 77 */     return getBag().uniqueSet();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/AbstractBagDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */