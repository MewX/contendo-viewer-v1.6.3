/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ public abstract class LocalVariableInstruction
/*     */   extends Instruction
/*     */   implements IndexedInstruction, TypedInstruction
/*     */ {
/*  69 */   protected int n = -1;
/*  70 */   private short c_tag = -1;
/*  71 */   private short canon_tag = -1;
/*     */   private final boolean wide() {
/*  73 */     return (this.n > 255);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LocalVariableInstruction(short canon_tag, short c_tag) {
/*  82 */     this.canon_tag = canon_tag;
/*  83 */     this.c_tag = c_tag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LocalVariableInstruction() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LocalVariableInstruction(short opcode, short c_tag, int n) {
/*  99 */     super(opcode, (short)2);
/*     */     
/* 101 */     this.c_tag = c_tag;
/* 102 */     this.canon_tag = opcode;
/*     */     
/* 104 */     setIndex(n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/* 112 */     if (wide()) {
/* 113 */       out.writeByte(196);
/*     */     }
/* 115 */     out.writeByte(this.opcode);
/*     */     
/* 117 */     if (this.length > 1) {
/* 118 */       if (wide()) {
/* 119 */         out.writeShort(this.n);
/*     */       } else {
/* 121 */         out.writeByte(this.n);
/*     */       } 
/*     */     }
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
/*     */   public String toString(boolean verbose) {
/* 135 */     if ((this.opcode >= 26 && this.opcode <= 45) || (this.opcode >= 59 && this.opcode <= 78))
/*     */     {
/*     */ 
/*     */       
/* 139 */       return super.toString(verbose);
/*     */     }
/* 141 */     return super.toString(verbose) + " " + this.n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 151 */     if (wide) {
/* 152 */       this.n = bytes.readUnsignedShort();
/* 153 */       this.length = 4;
/* 154 */     } else if ((this.opcode >= 21 && this.opcode <= 25) || (this.opcode >= 54 && this.opcode <= 58)) {
/*     */ 
/*     */ 
/*     */       
/* 158 */       this.n = bytes.readUnsignedByte();
/* 159 */       this.length = 2;
/* 160 */     } else if (this.opcode <= 45) {
/* 161 */       this.n = (this.opcode - 26) % 4;
/* 162 */       this.length = 1;
/*     */     } else {
/* 164 */       this.n = (this.opcode - 59) % 4;
/* 165 */       this.length = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getIndex() {
/* 172 */     return this.n;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIndex(int n) {
/* 178 */     if (n < 0 || n > 65535) {
/* 179 */       throw new ClassGenException("Illegal value: " + n);
/*     */     }
/* 181 */     this.n = n;
/*     */     
/* 183 */     if (n >= 0 && n <= 3) {
/* 184 */       this.opcode = (short)(this.c_tag + n);
/* 185 */       this.length = 1;
/*     */     } else {
/* 187 */       this.opcode = this.canon_tag;
/*     */       
/* 189 */       if (wide()) {
/* 190 */         this.length = 4;
/*     */       } else {
/* 192 */         this.length = 2;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public short getCanonicalTag() {
/* 199 */     return this.canon_tag;
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
/*     */   public Type getType(ConstantPoolGen cp) {
/* 211 */     switch (this.canon_tag) { case 21:
/*     */       case 54:
/* 213 */         return Type.INT;
/*     */       case 22: case 55:
/* 215 */         return Type.LONG;
/*     */       case 24: case 57:
/* 217 */         return Type.DOUBLE;
/*     */       case 23: case 56:
/* 219 */         return Type.FLOAT;
/*     */       case 25: case 58:
/* 221 */         return Type.OBJECT; }
/*     */     
/* 223 */     throw new ClassGenException("Oops: unknown case in switch" + this.canon_tag);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/LocalVariableInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */