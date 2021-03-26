/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Factory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConstantFactory
/*    */   implements Serializable, Factory
/*    */ {
/*    */   static final long serialVersionUID = -3520677225766901240L;
/* 40 */   public static final Factory NULL_INSTANCE = new ConstantFactory(null);
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
/*    */   public static Factory getInstance(Object constantToReturn) {
/* 52 */     if (constantToReturn == null) {
/* 53 */       return NULL_INSTANCE;
/*    */     }
/* 55 */     return new ConstantFactory(constantToReturn);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ConstantFactory(Object constantToReturn) {
/* 66 */     this.iConstant = constantToReturn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object create() {
/* 75 */     return this.iConstant;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getConstant() {
/* 85 */     return this.iConstant;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/ConstantFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */