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
/*     */ public abstract class Select
/*     */   extends BranchInstruction
/*     */   implements StackProducer, VariableLengthInstruction
/*     */ {
/*     */   protected int[] match;
/*     */   protected int[] indices;
/*     */   protected InstructionHandle[] targets;
/*     */   protected int fixed_length;
/*     */   protected int match_length;
/*  76 */   protected int padding = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Select() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Select(short opcode, int[] match, InstructionHandle[] targets, InstructionHandle target) {
/*  94 */     super(opcode, target);
/*     */     
/*  96 */     this.targets = targets;
/*  97 */     for (int i = 0; i < targets.length; i++) {
/*  98 */       BranchInstruction.notifyTarget(null, targets[i], this);
/*     */     }
/* 100 */     this.match = match;
/*     */     
/* 102 */     if ((this.match_length = match.length) != targets.length) {
/* 103 */       throw new ClassGenException("Match and target array have not the same length");
/*     */     }
/* 105 */     this.indices = new int[this.match_length];
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
/*     */ 
/*     */   
/*     */   protected int updatePosition(int offset, int max_offset) {
/* 122 */     this.position += offset;
/*     */     
/* 124 */     short old_length = this.length;
/*     */ 
/*     */ 
/*     */     
/* 128 */     this.padding = (4 - (this.position + 1) % 4) % 4;
/* 129 */     this.length = (short)(this.fixed_length + this.padding);
/*     */     
/* 131 */     return this.length - old_length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/* 139 */     out.writeByte(this.opcode);
/*     */     
/* 141 */     for (int i = 0; i < this.padding; i++) {
/* 142 */       out.writeByte(0);
/*     */     }
/* 144 */     this.index = getTargetOffset();
/* 145 */     out.writeInt(this.index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 153 */     this.padding = (4 - bytes.getIndex() % 4) % 4;
/*     */     
/* 155 */     for (int i = 0; i < this.padding; i++) {
/*     */       byte b;
/* 157 */       if ((b = bytes.readByte()) != 0) {
/* 158 */         throw new ClassGenException("Padding byte != 0: " + b);
/*     */       }
/*     */     } 
/*     */     
/* 162 */     this.index = bytes.readInt();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(boolean verbose) {
/* 169 */     StringBuffer buf = new StringBuffer(super.toString(verbose));
/*     */     
/* 171 */     if (verbose) {
/* 172 */       for (int i = 0; i < this.match_length; i++) {
/* 173 */         String s = "null";
/*     */         
/* 175 */         if (this.targets[i] != null) {
/* 176 */           s = this.targets[i].getInstruction().toString();
/*     */         }
/* 178 */         buf.append("(" + this.match[i] + ", " + s + " = {" + this.indices[i] + "})");
/*     */       } 
/*     */     } else {
/*     */       
/* 182 */       buf.append(" ...");
/*     */     } 
/* 184 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(int i, InstructionHandle target) {
/* 191 */     BranchInstruction.notifyTarget(this.targets[i], target, this);
/* 192 */     this.targets[i] = target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
/* 200 */     boolean targeted = false;
/*     */     
/* 202 */     if (this.target == old_ih) {
/* 203 */       targeted = true;
/* 204 */       setTarget(new_ih);
/*     */     } 
/*     */     
/* 207 */     for (int i = 0; i < this.targets.length; i++) {
/* 208 */       if (this.targets[i] == old_ih) {
/* 209 */         targeted = true;
/* 210 */         setTarget(i, new_ih);
/*     */       } 
/*     */     } 
/*     */     
/* 214 */     if (!targeted) {
/* 215 */       throw new ClassGenException("Not targeting " + old_ih);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsTarget(InstructionHandle ih) {
/* 222 */     if (this.target == ih) {
/* 223 */       return true;
/*     */     }
/* 225 */     for (int i = 0; i < this.targets.length; i++) {
/* 226 */       if (this.targets[i] == ih)
/* 227 */         return true; 
/*     */     } 
/* 229 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void dispose() {
/* 236 */     super.dispose();
/*     */     
/* 238 */     for (int i = 0; i < this.targets.length; i++) {
/* 239 */       this.targets[i].removeTargeter(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getMatchs() {
/* 245 */     return this.match;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getIndices() {
/* 250 */     return this.indices;
/*     */   }
/*     */ 
/*     */   
/*     */   public InstructionHandle[] getTargets() {
/* 255 */     return this.targets;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/Select.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */