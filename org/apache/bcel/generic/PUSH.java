/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PUSH
/*     */   implements CompoundInstruction, InstructionConstants, VariableLengthInstruction
/*     */ {
/*     */   private Instruction instruction;
/*     */   
/*     */   public PUSH(ConstantPoolGen cp, int value) {
/*  78 */     if (value >= -1 && value <= 5) {
/*  79 */       this.instruction = InstructionConstants.INSTRUCTIONS[3 + value];
/*  80 */     } else if (value >= -128 && value <= 127) {
/*  81 */       this.instruction = new BIPUSH((byte)value);
/*  82 */     } else if (value >= -32768 && value <= 32767) {
/*  83 */       this.instruction = new SIPUSH((short)value);
/*     */     } else {
/*  85 */       this.instruction = new LDC(cp.addInteger(value));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PUSH(ConstantPoolGen cp, boolean value) {
/*  93 */     this.instruction = InstructionConstants.INSTRUCTIONS[3 + (value ? 1 : 0)];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PUSH(ConstantPoolGen cp, float value) {
/* 101 */     if (value == 0.0D) {
/* 102 */       this.instruction = InstructionConstants.FCONST_0;
/* 103 */     } else if (value == 1.0D) {
/* 104 */       this.instruction = InstructionConstants.FCONST_1;
/* 105 */     } else if (value == 2.0D) {
/* 106 */       this.instruction = InstructionConstants.FCONST_2;
/*     */     } else {
/* 108 */       this.instruction = new LDC(cp.addFloat(value));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PUSH(ConstantPoolGen cp, long value) {
/* 116 */     if (value == 0L) {
/* 117 */       this.instruction = InstructionConstants.LCONST_0;
/* 118 */     } else if (value == 1L) {
/* 119 */       this.instruction = InstructionConstants.LCONST_1;
/*     */     } else {
/* 121 */       this.instruction = new LDC2_W(cp.addLong(value));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PUSH(ConstantPoolGen cp, double value) {
/* 129 */     if (value == 0.0D) {
/* 130 */       this.instruction = InstructionConstants.DCONST_0;
/* 131 */     } else if (value == 1.0D) {
/* 132 */       this.instruction = InstructionConstants.DCONST_1;
/*     */     } else {
/* 134 */       this.instruction = new LDC2_W(cp.addDouble(value));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PUSH(ConstantPoolGen cp, String value) {
/* 142 */     if (value == null) {
/* 143 */       this.instruction = InstructionConstants.ACONST_NULL;
/*     */     } else {
/* 145 */       this.instruction = new LDC(cp.addString(value));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PUSH(ConstantPoolGen cp, Number value) {
/* 153 */     if (value instanceof Integer || value instanceof Short || value instanceof Byte) {
/* 154 */       this.instruction = (new PUSH(cp, value.intValue())).instruction;
/* 155 */     } else if (value instanceof Double) {
/* 156 */       this.instruction = (new PUSH(cp, value.doubleValue())).instruction;
/* 157 */     } else if (value instanceof Float) {
/* 158 */       this.instruction = (new PUSH(cp, value.floatValue())).instruction;
/* 159 */     } else if (value instanceof Long) {
/* 160 */       this.instruction = (new PUSH(cp, value.longValue())).instruction;
/*     */     } else {
/* 162 */       throw new ClassGenException("What's this: " + value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PUSH(ConstantPoolGen cp, Character value) {
/* 170 */     this(cp, value.charValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PUSH(ConstantPoolGen cp, Boolean value) {
/* 178 */     this(cp, value.booleanValue());
/*     */   }
/*     */   
/*     */   public final InstructionList getInstructionList() {
/* 182 */     return new InstructionList(this.instruction);
/*     */   }
/*     */   
/*     */   public final Instruction getInstruction() {
/* 186 */     return this.instruction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 193 */     return this.instruction.toString() + " (PUSH)";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/PUSH.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */