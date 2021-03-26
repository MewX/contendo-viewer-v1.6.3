/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ import org.apache.bcel.util.ByteSequence;
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
/*     */ public abstract class Instruction
/*     */   implements Serializable, Cloneable
/*     */ {
/*  70 */   protected short length = 1;
/*  71 */   protected short opcode = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   Instruction() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction(short opcode, short length) {
/*  80 */     this.length = length;
/*  81 */     this.opcode = opcode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  89 */     out.writeByte(this.opcode);
/*     */   }
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
/*     */   public String toString(boolean verbose) {
/* 102 */     if (verbose) {
/* 103 */       return Constants.OPCODE_NAMES[this.opcode] + "[" + this.opcode + "](" + this.length + ")";
/*     */     }
/* 105 */     return Constants.OPCODE_NAMES[this.opcode];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 112 */     return toString(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(ConstantPool cp) {
/* 119 */     return toString(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction copy() {
/* 131 */     Instruction i = null;
/*     */ 
/*     */     
/* 134 */     if (InstructionConstants.INSTRUCTIONS[getOpcode()] != null) {
/* 135 */       i = this;
/*     */     } else {
/*     */       try {
/* 138 */         i = (Instruction)clone();
/*     */       } catch (CloneNotSupportedException e) {
/* 140 */         System.err.println(e);
/*     */       } 
/*     */     } 
/*     */     
/* 144 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final Instruction readInstruction(ByteSequence bytes) throws IOException {
/*     */     Class clazz;
/* 167 */     boolean wide = false;
/* 168 */     short opcode = (short)bytes.readUnsignedByte();
/* 169 */     Instruction obj = null;
/*     */     
/* 171 */     if (opcode == 196) {
/* 172 */       wide = true;
/* 173 */       opcode = (short)bytes.readUnsignedByte();
/*     */     } 
/*     */     
/* 176 */     if (InstructionConstants.INSTRUCTIONS[opcode] != null) {
/* 177 */       return InstructionConstants.INSTRUCTIONS[opcode];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 184 */       clazz = Class.forName(className(opcode));
/*     */     
/*     */     }
/*     */     catch (ClassNotFoundException cnfe) {
/*     */       
/* 189 */       throw new ClassGenException("Illegal opcode detected.");
/*     */     } 
/*     */     try {
/* 192 */       obj = (Instruction)clazz.newInstance();
/*     */       
/* 194 */       if (wide && !(obj instanceof LocalVariableInstruction) && !(obj instanceof IINC) && !(obj instanceof RET))
/*     */       {
/* 196 */         throw new Exception("Illegal opcode after wide: " + opcode);
/*     */       }
/* 198 */       obj.setOpcode(opcode);
/* 199 */       obj.initFromFile(bytes, wide);
/*     */     } catch (Exception e) {
/* 201 */       throw new ClassGenException(e.toString());
/*     */     } 
/* 203 */     return obj;
/*     */   }
/*     */   
/*     */   private static final String className(short opcode) {
/* 207 */     String name = Constants.OPCODE_NAMES[opcode].toUpperCase();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     try { int len = name.length();
/* 214 */       char ch1 = name.charAt(len - 2), ch2 = name.charAt(len - 1);
/*     */       
/* 216 */       if (ch1 == '_' && ch2 >= '0' && ch2 <= '5') {
/* 217 */         name = name.substring(0, len - 2);
/*     */       }
/* 219 */       if (name.equals("ICONST_M1"))
/* 220 */         name = "ICONST";  }
/* 221 */     catch (StringIndexOutOfBoundsException e) { System.err.println(e); }
/*     */     
/* 223 */     return "org.apache.bcel.generic." + name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int consumeStack(ConstantPoolGen cpg) {
/* 234 */     return Constants.CONSUME_STACK[this.opcode];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int produceStack(ConstantPoolGen cpg) {
/* 245 */     return Constants.PRODUCE_STACK[this.opcode];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public short getOpcode() {
/* 251 */     return this.opcode;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 256 */     return this.length;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setOpcode(short opcode) {
/* 261 */     this.opcode = opcode;
/*     */   }
/*     */   
/*     */   void dispose() {}
/*     */   
/*     */   public abstract void accept(Visitor paramVisitor);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/Instruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */