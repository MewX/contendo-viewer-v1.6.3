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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TABLESWITCH
/*     */   extends Select
/*     */ {
/*     */   TABLESWITCH() {}
/*     */   
/*     */   public TABLESWITCH(int[] match, InstructionHandle[] targets, InstructionHandle target) {
/*  81 */     super((short)170, match, targets, target);
/*     */     
/*  83 */     this.length = (short)(13 + this.match_length * 4);
/*     */     
/*  85 */     this.fixed_length = this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  93 */     super.dump(out);
/*     */     
/*  95 */     int low = (this.match_length > 0) ? this.match[0] : 0;
/*  96 */     out.writeInt(low);
/*     */     
/*  98 */     int high = (this.match_length > 0) ? this.match[this.match_length - 1] : 0;
/*  99 */     out.writeInt(high);
/*     */     
/* 101 */     for (int i = 0; i < this.match_length; i++) {
/* 102 */       out.writeInt(this.indices[i] = getTargetOffset(this.targets[i]));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 110 */     super.initFromFile(bytes, wide);
/*     */     
/* 112 */     int low = bytes.readInt();
/* 113 */     int high = bytes.readInt();
/*     */     
/* 115 */     this.match_length = high - low + 1;
/* 116 */     this.fixed_length = (short)(13 + this.match_length * 4);
/* 117 */     this.length = (short)(this.fixed_length + this.padding);
/*     */     
/* 119 */     this.match = new int[this.match_length];
/* 120 */     this.indices = new int[this.match_length];
/* 121 */     this.targets = new InstructionHandle[this.match_length];
/*     */     
/* 123 */     for (int i = low; i <= high; i++) {
/* 124 */       this.match[i - low] = i;
/*     */     }
/* 126 */     for (int j = 0; j < this.match_length; j++) {
/* 127 */       this.indices[j] = bytes.readInt();
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
/* 141 */     v.visitVariableLengthInstruction(this);
/* 142 */     v.visitStackProducer(this);
/* 143 */     v.visitBranchInstruction(this);
/* 144 */     v.visitSelect(this);
/* 145 */     v.visitTABLESWITCH(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/TABLESWITCH.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */