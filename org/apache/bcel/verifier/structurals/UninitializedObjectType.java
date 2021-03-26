/*    */ package org.apache.bcel.verifier.structurals;
/*    */ 
/*    */ import org.apache.bcel.Constants;
/*    */ import org.apache.bcel.generic.ObjectType;
/*    */ import org.apache.bcel.generic.ReferenceType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UninitializedObjectType
/*    */   extends ReferenceType
/*    */   implements Constants
/*    */ {
/*    */   private ObjectType initialized;
/*    */   
/*    */   public UninitializedObjectType(ObjectType t) {
/* 75 */     super((byte)15, "<UNINITIALIZED OBJECT OF TYPE '" + t.getClassName() + "'>");
/* 76 */     this.initialized = t;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ObjectType getInitialized() {
/* 84 */     return this.initialized;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 94 */     if (!(o instanceof UninitializedObjectType)) return false; 
/* 95 */     return this.initialized.equals(((UninitializedObjectType)o).initialized);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/UninitializedObjectType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */