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
/*     */ public class JSR
/*     */   extends JsrInstruction
/*     */   implements VariableLengthInstruction
/*     */ {
/*     */   JSR() {}
/*     */   
/*     */   public JSR(InstructionHandle target) {
/*  72 */     super((short)168, target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  80 */     this.index = getTargetOffset();
/*  81 */     if (this.opcode == 168) {
/*  82 */       super.dump(out);
/*     */     } else {
/*  84 */       this.index = getTargetOffset();
/*  85 */       out.writeByte(this.opcode);
/*  86 */       out.writeInt(this.index);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int updatePosition(int offset, int max_offset) {
/*  91 */     int i = getTargetOffset();
/*     */     
/*  93 */     this.position += offset;
/*     */     
/*  95 */     if (Math.abs(i) >= 32767 - max_offset) {
/*  96 */       this.opcode = 201;
/*  97 */       this.length = 5;
/*  98 */       return 2;
/*     */     } 
/*     */     
/* 101 */     return 0;
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
/* 113 */     v.visitStackProducer(this);
/* 114 */     v.visitVariableLengthInstruction(this);
/* 115 */     v.visitBranchInstruction(this);
/* 116 */     v.visitJsrInstruction(this);
/* 117 */     v.visitJSR(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/JSR.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */