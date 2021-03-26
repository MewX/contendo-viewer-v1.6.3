/*    */ package org.apache.commons.collections.set;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ import org.apache.commons.collections.collection.PredicatedCollection;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PredicatedSet
/*    */   extends PredicatedCollection
/*    */   implements Set
/*    */ {
/*    */   private static final long serialVersionUID = -684521469108685117L;
/*    */   
/*    */   public static Set decorate(Set set, Predicate predicate) {
/* 59 */     return new PredicatedSet(set, predicate);
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
/*    */   
/*    */   protected PredicatedSet(Set set, Predicate predicate) {
/* 75 */     super(set, predicate);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Set getSet() {
/* 84 */     return (Set)getCollection();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/set/PredicatedSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */