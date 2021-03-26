/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GOTO
/*     */   extends GotoInstruction
/*     */   implements VariableLengthInstruction
/*     */ {
/*     */   GOTO() {}
/*     */   
/*     */   public GOTO(InstructionHandle target) {
/*  72 */     super((short)167, target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  80 */     this.index = getTargetOffset();
/*  81 */     if (this.opcode == 167) {
/*  82 */       super.dump(out);
/*     */     } else {
/*  84 */       this.index = getTargetOffset();
/*  85 */       out.writeByte(this.opcode);
/*  86 */       out.writeInt(this.index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int updatePosition(int offset, int max_offset) {
/*  94 */     int i = getTargetOffset();
/*     */     
/*  96 */     this.position += offset;
/*     */     
/*  98 */     if (Math.abs(i) >= 32767 - max_offset) {
/*  99 */       this.opcode = 200;
/* 100 */       this.length = 5;
/* 101 */       return 2;
/*     */     } 
/*     */     
/* 104 */     return 0;
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
/* 116 */     v.visitVariableLengthInstruction(this);
/* 117 */     v.visitUnconditionalBranch(this);
/* 118 */     v.visitBranchInstruction(this);
/* 119 */     v.visitGotoInstruction(this);
/* 120 */     v.visitGOTO(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/GOTO.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */