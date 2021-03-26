/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Closure;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NOPClosure
/*    */   implements Serializable, Closure
/*    */ {
/*    */   static final long serialVersionUID = 3518477308466486130L;
/* 36 */   public static final Closure INSTANCE = new NOPClosure();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Closure getInstance() {
/* 45 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public void execute(Object input) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/functors/NOPClosure.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */