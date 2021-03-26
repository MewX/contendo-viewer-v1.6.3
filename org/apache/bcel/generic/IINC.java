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
/*     */ 
/*     */ public class IINC
/*     */   extends LocalVariableInstruction
/*     */ {
/*     */   private boolean wide;
/*     */   private int c;
/*     */   
/*     */   IINC() {}
/*     */   
/*     */   public IINC(int n, int c) {
/*  78 */     this.opcode = 132;
/*  79 */     this.length = 3;
/*     */     
/*  81 */     setIndex(n);
/*  82 */     setIncrement(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  90 */     if (this.wide) {
/*  91 */       out.writeByte(196);
/*     */     }
/*  93 */     out.writeByte(this.opcode);
/*     */     
/*  95 */     if (this.wide) {
/*  96 */       out.writeShort(this.n);
/*  97 */       out.writeShort(this.c);
/*     */     } else {
/*  99 */       out.writeByte(this.n);
/* 100 */       out.writeByte(this.c);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void setWide() {
/* 105 */     if (this.wide = (this.n > 65535 || Math.abs(this.c) > 127)) {
/*     */       
/* 107 */       this.length = 6;
/*     */     } else {
/* 109 */       this.length = 3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 117 */     this.wide = wide;
/*     */     
/* 119 */     if (wide) {
/* 120 */       this.length = 6;
/* 121 */       this.n = bytes.readUnsignedShort();
/* 122 */       this.c = bytes.readShort();
/*     */     } else {
/* 124 */       this.length = 3;
/* 125 */       this.n = bytes.readUnsignedByte();
/* 126 */       this.c = bytes.readByte();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(boolean verbose) {
/* 134 */     return super.toString(verbose) + " " + this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setIndex(int n) {
/* 141 */     if (n < 0) {
/* 142 */       throw new ClassGenException("Negative index value: " + n);
/*     */     }
/* 144 */     this.n = n;
/* 145 */     setWide();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getIncrement() {
/* 151 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setIncrement(int c) {
/* 157 */     this.c = c;
/* 158 */     setWide();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cp) {
/* 164 */     return Type.INT;
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
/* 176 */     v.visitLocalVariableInstruction(this);
/* 177 */     v.visitIINC(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/IINC.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */