/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import org.apache.bcel.classfile.LocalVariable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalVariableGen
/*     */   implements Cloneable, InstructionTargeter, NamedAndTyped
/*     */ {
/*     */   private int index;
/*     */   private String name;
/*     */   private Type type;
/*     */   private InstructionHandle start;
/*     */   private InstructionHandle end;
/*     */   
/*     */   public LocalVariableGen(int index, String name, Type type, InstructionHandle start, InstructionHandle end) {
/*  89 */     if (index < 0 || index > 65535) {
/*  90 */       throw new ClassGenException("Invalid index index: " + index);
/*     */     }
/*  92 */     this.name = name;
/*  93 */     this.type = type;
/*  94 */     this.index = index;
/*  95 */     setStart(start);
/*  96 */     setEnd(end);
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
/*     */ 
/*     */   
/*     */   public LocalVariable getLocalVariable(ConstantPoolGen cp) {
/* 115 */     int start_pc = this.start.getPosition();
/* 116 */     int length = this.end.getPosition() - start_pc;
/*     */     
/* 118 */     if (length > 0) {
/* 119 */       length += this.end.getInstruction().getLength();
/*     */     }
/* 121 */     int name_index = cp.addUtf8(this.name);
/* 122 */     int signature_index = cp.addUtf8(this.type.getSignature());
/*     */     
/* 124 */     return new LocalVariable(start_pc, length, name_index, signature_index, this.index, cp.getConstantPool());
/*     */   }
/*     */   
/*     */   public void setIndex(int index) {
/* 128 */     this.index = index;
/* 129 */   } public int getIndex() { return this.index; }
/* 130 */   public void setName(String name) { this.name = name; }
/* 131 */   public String getName() { return this.name; }
/* 132 */   public void setType(Type type) { this.type = type; } public Type getType() {
/* 133 */     return this.type;
/*     */   }
/* 135 */   public InstructionHandle getStart() { return this.start; } public InstructionHandle getEnd() {
/* 136 */     return this.end;
/*     */   }
/*     */   public void setStart(InstructionHandle start) {
/* 139 */     BranchInstruction.notifyTarget(this.start, start, this);
/* 140 */     this.start = start;
/*     */   }
/*     */   
/*     */   public void setEnd(InstructionHandle end) {
/* 144 */     BranchInstruction.notifyTarget(this.end, end, this);
/* 145 */     this.end = end;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
/* 153 */     boolean targeted = false;
/*     */     
/* 155 */     if (this.start == old_ih) {
/* 156 */       targeted = true;
/* 157 */       setStart(new_ih);
/*     */     } 
/*     */     
/* 160 */     if (this.end == old_ih) {
/* 161 */       targeted = true;
/* 162 */       setEnd(new_ih);
/*     */     } 
/*     */     
/* 165 */     if (!targeted) {
/* 166 */       throw new ClassGenException("Not targeting " + old_ih + ", but {" + this.start + ", " + this.end + "}");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsTarget(InstructionHandle ih) {
/* 174 */     return (this.start == ih || this.end == ih);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 182 */     if (!(o instanceof LocalVariableGen)) {
/* 183 */       return false;
/*     */     }
/* 185 */     LocalVariableGen l = (LocalVariableGen)o;
/* 186 */     return (l.index == this.index && l.start == this.start && l.end == this.end);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 190 */     return "LocalVariableGen(" + this.name + ", " + this.type + ", " + this.start + ", " + this.end + ")";
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 195 */       return super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 197 */       System.err.println(e);
/* 198 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/LocalVariableGen.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */