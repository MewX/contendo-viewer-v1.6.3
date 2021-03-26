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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RET
/*     */   extends Instruction
/*     */   implements IndexedInstruction, TypedInstruction
/*     */ {
/*     */   private boolean wide;
/*     */   private int index;
/*     */   
/*     */   RET() {}
/*     */   
/*     */   public RET(int index) {
/*  78 */     super((short)169, (short)2);
/*  79 */     setIndex(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  87 */     if (this.wide) {
/*  88 */       out.writeByte(196);
/*     */     }
/*  90 */     out.writeByte(this.opcode);
/*     */     
/*  92 */     if (this.wide) {
/*  93 */       out.writeShort(this.index);
/*     */     } else {
/*  95 */       out.writeByte(this.index);
/*     */     } 
/*     */   }
/*     */   private final void setWide() {
/*  99 */     if (this.wide = (this.index > 255)) {
/* 100 */       this.length = 4;
/*     */     } else {
/* 102 */       this.length = 2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 110 */     this.wide = wide;
/*     */     
/* 112 */     if (wide) {
/* 113 */       this.index = bytes.readUnsignedShort();
/* 114 */       this.length = 4;
/*     */     } else {
/* 116 */       this.index = bytes.readUnsignedByte();
/* 117 */       this.length = 2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getIndex() {
/* 124 */     return this.index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setIndex(int n) {
/* 130 */     if (n < 0) {
/* 131 */       throw new ClassGenException("Negative index value: " + n);
/*     */     }
/* 133 */     this.index = n;
/* 134 */     setWide();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(boolean verbose) {
/* 141 */     return super.toString(verbose) + " " + this.index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cp) {
/* 147 */     return ReturnaddressType.NO_TARGET;
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
/*     */   public void accept(Visitor v) {
/* 159 */     v.visitRET(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/RET.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */