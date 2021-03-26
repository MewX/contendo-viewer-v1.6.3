/*    */ package org.apache.bcel.generic;
/*    */ 
/*    */ import org.apache.bcel.Constants;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BasicType
/*    */   extends Type
/*    */ {
/*    */   BasicType(byte type) {
/* 72 */     super(type, Constants.SHORT_TYPE_NAMES[type]);
/*    */     
/* 74 */     if (type < 4 || type > 12)
/* 75 */       throw new ClassGenException("Invalid type: " + type); 
/*    */   }
/*    */   
/*    */   public static final BasicType getType(byte type) {
/* 79 */     switch (type) { case 12:
/* 80 */         return Type.VOID;
/* 81 */       case 4: return Type.BOOLEAN;
/* 82 */       case 8: return Type.BYTE;
/* 83 */       case 9: return Type.SHORT;
/* 84 */       case 5: return Type.CHAR;
/* 85 */       case 10: return Type.INT;
/* 86 */       case 11: return Type.LONG;
/* 87 */       case 7: return Type.DOUBLE;
/* 88 */       case 6: return Type.FLOAT; }
/*    */ 
/*    */     
/* 91 */     throw new ClassGenException("Invalid type: " + type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object type) {
/* 98 */     return (type instanceof BasicType) ? ((((BasicType)type).type == this.type)) : false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/BasicType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */