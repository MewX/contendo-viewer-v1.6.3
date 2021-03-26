/*    */ package org.apache.bcel.generic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ConversionInstruction
/*    */   extends Instruction
/*    */   implements StackConsumer, StackProducer, TypedInstruction
/*    */ {
/*    */   ConversionInstruction() {}
/*    */   
/*    */   protected ConversionInstruction(short opcode) {
/* 75 */     super(opcode, (short)1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Type getType(ConstantPoolGen cp) {
/* 81 */     switch (this.opcode) { case 136: case 139:
/*    */       case 142:
/* 83 */         return Type.INT;
/*    */       case 134: case 137: case 144:
/* 85 */         return Type.FLOAT;
/*    */       case 133: case 140: case 143:
/* 87 */         return Type.LONG;
/*    */       case 135: case 138: case 141:
/* 89 */         return Type.DOUBLE;
/*    */       case 145:
/* 91 */         return Type.BYTE;
/*    */       case 146:
/* 93 */         return Type.CHAR;
/*    */       case 147:
/* 95 */         return Type.SHORT; }
/*    */ 
/*    */     
/* 98 */     throw new ClassGenException("Unknown type " + this.opcode);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ConversionInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */