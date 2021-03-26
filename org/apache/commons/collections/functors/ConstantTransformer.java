/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class ConstantTransformer
/*    */   implements Serializable, Transformer
/*    */ {
/*    */   static final long serialVersionUID = 6374440726369055124L;
/* 40 */   public static final Transformer NULL_INSTANCE = new ConstantTransformer(null);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final Object iConstant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Transformer getInstance(Object constantToReturn) {
/* 52 */     if (constantToReturn == null) {
/* 53 */       return NULL_INSTANCE;
/*    */     }
/* 55 */     return new ConstantTransformer(constantToReturn);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ConstantTransformer(Object constantToReturn) {
/* 66 */     this.iConstant = constantToReturn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object transform(Object input) {
/* 76 */     return this.iConstant;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getConstant() {
/* 86 */     return this.iConstant;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/ConstantTransformer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */