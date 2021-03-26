/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.FunctorException;
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
/*    */ public final class TransformerPredicate
/*    */   implements Serializable, Predicate
/*    */ {
/*    */   static final long serialVersionUID = -2407966402920578741L;
/*    */   private final Transformer iTransformer;
/*    */   
/*    */   public static Predicate getInstance(Transformer transformer) {
/* 48 */     if (transformer == null) {
/* 49 */       throw new IllegalArgumentException("The transformer to call must not be null");
/*    */     }
/* 51 */     return new TransformerPredicate(transformer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TransformerPredicate(Transformer transformer) {
/* 62 */     this.iTransformer = transformer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean evaluate(Object object) {
/* 73 */     Object result = this.iTransformer.transform(object);
/* 74 */     if (!(result instanceof Boolean)) {
/* 75 */       throw new FunctorException("Transformer must return an instanceof Boolean, it was a " + ((result == null) ? "null object" : result.getClass().getName()));
/*    */     }
/*    */ 
/*    */     
/* 79 */     return ((Boolean)result).booleanValue();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Transformer getTransformer() {
/* 89 */     return this.iTransformer;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/TransformerPredicate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */