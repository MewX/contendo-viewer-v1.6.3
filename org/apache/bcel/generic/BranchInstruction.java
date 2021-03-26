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
/*     */ public abstract class BranchInstruction
/*     */   extends Instruction
/*     */   implements InstructionTargeter
/*     */ {
/*     */   protected int index;
/*     */   protected InstructionHandle target;
/*     */   protected int position;
/*     */   
/*     */   BranchInstruction() {}
/*     */   
/*     */   protected BranchInstruction(short opcode, InstructionHandle target) {
/*  85 */     super(opcode, (short)3);
/*  86 */     setTarget(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  94 */     out.writeByte(this.opcode);
/*     */     
/*  96 */     this.index = getTargetOffset();
/*     */     
/*  98 */     if (Math.abs(this.index) >= 32767) {
/*  99 */       throw new ClassGenException("Branch target offset too large for short");
/*     */     }
/* 101 */     out.writeShort(this.index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getTargetOffset(InstructionHandle target) {
/* 109 */     if (target == null) {
/* 110 */       throw new ClassGenException("Target of " + super.toString(true) + " is invalid null handle");
/*     */     }
/*     */     
/* 113 */     int t = target.getPosition();
/*     */     
/* 115 */     if (t < 0) {
/* 116 */       throw new ClassGenException("Invalid branch target position offset for " + super.toString(true) + ":" + t + ":" + target);
/*     */     }
/*     */     
/* 119 */     return t - this.position;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getTargetOffset() {
/* 125 */     return getTargetOffset(this.target);
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
/*     */   protected int updatePosition(int offset, int max_offset) {
/* 138 */     this.position += offset;
/* 139 */     return 0;
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
/*     */ 
/*     */   
/*     */   public String toString(boolean verbose) {
/* 154 */     String s = super.toString(verbose);
/* 155 */     String t = "null";
/*     */     
/* 157 */     if (verbose) {
/* 158 */       if (this.target != null) {
/* 159 */         if (this.target.getInstruction() == this) {
/* 160 */           t = "<points to itself>";
/* 161 */         } else if (this.target.getInstruction() == null) {
/* 162 */           t = "<null instruction!!!?>";
/*     */         } else {
/* 164 */           t = this.target.getInstruction().toString(false);
/*     */         } 
/*     */       }
/* 167 */     } else if (this.target != null) {
/* 168 */       this.index = getTargetOffset();
/* 169 */       t = "" + (this.index + this.position);
/*     */     } 
/*     */ 
/*     */     
/* 173 */     return s + " -> " + t;
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
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 186 */     this.length = 3;
/* 187 */     this.index = bytes.readShort();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getIndex() {
/* 193 */     return this.index;
/*     */   }
/*     */ 
/*     */   
/*     */   public InstructionHandle getTarget() {
/* 198 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(InstructionHandle target) {
/* 205 */     notifyTarget(this.target, target, this);
/* 206 */     this.target = target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final void notifyTarget(InstructionHandle old_ih, InstructionHandle new_ih, InstructionTargeter t) {
/* 214 */     if (old_ih != null)
/* 215 */       old_ih.removeTargeter(t); 
/* 216 */     if (new_ih != null) {
/* 217 */       new_ih.addTargeter(t);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
/* 225 */     if (this.target == old_ih) {
/* 226 */       setTarget(new_ih);
/*     */     } else {
/* 228 */       throw new ClassGenException("Not targeting " + old_ih + ", but " + this.target);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsTarget(InstructionHandle ih) {
/* 235 */     return (this.target == ih);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void dispose() {
/* 242 */     setTarget(null);
/* 243 */     this.index = -1;
/* 244 */     this.position = -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/BranchInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */