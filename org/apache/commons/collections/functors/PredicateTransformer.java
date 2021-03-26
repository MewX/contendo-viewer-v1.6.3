/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ import org.apache.commons.collections.Transformer;
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
/*    */ public class PredicateTransformer
/*    */   implements Serializable, Transformer
/*    */ {
/*    */   static final long serialVersionUID = 5278818408044349346L;
/*    */   private final Predicate iPredicate;
/*    */   
/*    */   public static Transformer getInstance(Predicate predicate) {
/* 48 */     if (predicate == null) {
/* 49 */       throw new IllegalArgumentException("Predicate must not be null");
/*    */     }
/* 51 */     return new PredicateTransformer(predicate);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PredicateTransformer(Predicate predicate) {
/* 62 */     this.iPredicate = predicate;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object transform(Object input) {
/* 72 */     return this.iPredicate.evaluate(input) ? Boolean.TRUE : Boolean.FALSE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Predicate getPredicate() {
/* 82 */     return this.iPredicate;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/PredicateTransformer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */