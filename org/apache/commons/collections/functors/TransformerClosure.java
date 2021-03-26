/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Closure;
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
/*    */ 
/*    */ public class TransformerClosure
/*    */   implements Serializable, Closure
/*    */ {
/*    */   static final long serialVersionUID = -5194992589193388969L;
/*    */   private final Transformer iTransformer;
/*    */   
/*    */   public static Closure getInstance(Transformer transformer) {
/* 49 */     if (transformer == null) {
/* 50 */       return NOPClosure.INSTANCE;
/*    */     }
/* 52 */     return new TransformerClosure(transformer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TransformerClosure(Transformer transformer) {
/* 63 */     this.iTransformer = transformer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(Object input) {
/* 72 */     this.iTransformer.transform(input);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Transformer getTransformer() {
/* 82 */     return this.iTransformer;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/TransformerClosure.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */