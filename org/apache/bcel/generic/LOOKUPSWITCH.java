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
/*     */ public class LOOKUPSWITCH
/*     */   extends Select
/*     */ {
/*     */   LOOKUPSWITCH() {}
/*     */   
/*     */   public LOOKUPSWITCH(int[] match, InstructionHandle[] targets, InstructionHandle target) {
/*  75 */     super((short)171, match, targets, target);
/*     */     
/*  77 */     this.length = (short)(9 + this.match_length * 8);
/*     */     
/*  79 */     this.fixed_length = this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  87 */     super.dump(out);
/*  88 */     out.writeInt(this.match_length);
/*     */     
/*  90 */     for (int i = 0; i < this.match_length; i++) {
/*  91 */       out.writeInt(this.match[i]);
/*  92 */       out.writeInt(this.indices[i] = getTargetOffset(this.targets[i]));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 101 */     super.initFromFile(bytes, wide);
/*     */     
/* 103 */     this.match_length = bytes.readInt();
/* 104 */     this.fixed_length = (short)(9 + this.match_length * 8);
/* 105 */     this.length = (short)(this.fixed_length + this.padding);
/*     */     
/* 107 */     this.match = new int[this.match_length];
/* 108 */     this.indices = new int[this.match_length];
/* 109 */     this.targets = new InstructionHandle[this.match_length];
/*     */     
/* 111 */     for (int i = 0; i < this.match_length; i++) {
/* 112 */       this.match[i] = bytes.readInt();
/* 113 */       this.indices[i] = bytes.readInt();
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
/*     */   
/*     */   public void accept(Visitor v) {
/* 127 */     v.visitVariableLengthInstruction(this);
/* 128 */     v.visitStackProducer(this);
/* 129 */     v.visitBranchInstruction(this);
/* 130 */     v.visitSelect(this);
/* 131 */     v.visitLOOKUPSWITCH(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/LOOKUPSWITCH.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */