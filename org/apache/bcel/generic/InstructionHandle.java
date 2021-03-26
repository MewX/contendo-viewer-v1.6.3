/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import org.apache.bcel.classfile.Utility;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InstructionHandle
/*     */   implements Serializable
/*     */ {
/*     */   InstructionHandle next;
/*     */   InstructionHandle prev;
/*     */   Instruction instruction;
/*  83 */   protected int i_position = -1;
/*     */   private HashSet targeters;
/*     */   private HashMap attributes;
/*     */   
/*  87 */   public final InstructionHandle getNext() { return this.next; }
/*  88 */   public final InstructionHandle getPrev() { return this.prev; } public final Instruction getInstruction() {
/*  89 */     return this.instruction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInstruction(Instruction i) {
/*  96 */     if (i == null) {
/*  97 */       throw new ClassGenException("Assigning null to handle");
/*     */     }
/*  99 */     if (getClass() != BranchHandle.class && i instanceof BranchInstruction) {
/* 100 */       throw new ClassGenException("Assigning branch instruction " + i + " to plain handle");
/*     */     }
/* 102 */     if (this.instruction != null) {
/* 103 */       this.instruction.dispose();
/*     */     }
/* 105 */     this.instruction = i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction swapInstruction(Instruction i) {
/* 114 */     Instruction oldInstruction = this.instruction;
/* 115 */     this.instruction = i;
/* 116 */     return oldInstruction;
/*     */   }
/*     */   
/*     */   protected InstructionHandle(Instruction i) {
/* 120 */     setInstruction(i);
/*     */   }
/*     */   
/* 123 */   private static InstructionHandle ih_list = null;
/*     */ 
/*     */ 
/*     */   
/*     */   static final InstructionHandle getInstructionHandle(Instruction i) {
/* 128 */     if (ih_list == null) {
/* 129 */       return new InstructionHandle(i);
/*     */     }
/* 131 */     InstructionHandle ih = ih_list;
/* 132 */     ih_list = ih.next;
/*     */     
/* 134 */     ih.setInstruction(i);
/*     */     
/* 136 */     return ih;
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
/*     */   protected int updatePosition(int offset, int max_offset) {
/* 151 */     this.i_position += offset;
/* 152 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosition() {
/* 159 */     return this.i_position;
/*     */   }
/*     */ 
/*     */   
/*     */   void setPosition(int pos) {
/* 164 */     this.i_position = pos;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addHandle() {
/* 169 */     this.next = ih_list;
/* 170 */     ih_list = this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void dispose() {
/* 177 */     this.next = this.prev = null;
/* 178 */     this.instruction.dispose();
/* 179 */     this.instruction = null;
/* 180 */     this.i_position = -1;
/* 181 */     this.attributes = null;
/* 182 */     removeAllTargeters();
/* 183 */     addHandle();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllTargeters() {
/* 189 */     if (this.targeters != null) {
/* 190 */       this.targeters.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTargeter(InstructionTargeter t) {
/* 197 */     this.targeters.remove(t);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTargeter(InstructionTargeter t) {
/* 204 */     if (this.targeters == null) {
/* 205 */       this.targeters = new HashSet();
/*     */     }
/*     */     
/* 208 */     this.targeters.add(t);
/*     */   }
/*     */   
/*     */   public boolean hasTargeters() {
/* 212 */     return (this.targeters != null && this.targeters.size() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionTargeter[] getTargeters() {
/* 219 */     if (!hasTargeters()) {
/* 220 */       return null;
/*     */     }
/* 222 */     InstructionTargeter[] t = new InstructionTargeter[this.targeters.size()];
/* 223 */     this.targeters.toArray((Object[])t);
/* 224 */     return t;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(boolean verbose) {
/* 230 */     return Utility.format(this.i_position, 4, false, ' ') + ": " + this.instruction.toString(verbose);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 236 */     return toString(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(Object key, Object attr) {
/* 245 */     if (this.attributes == null) {
/* 246 */       this.attributes = new HashMap(3);
/*     */     }
/* 248 */     this.attributes.put(key, attr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAttribute(Object key) {
/* 256 */     if (this.attributes != null) {
/* 257 */       this.attributes.remove(key);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getAttribute(Object key) {
/* 265 */     if (this.attributes != null) {
/* 266 */       return this.attributes.get(key);
/*     */     }
/* 268 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getAttributes() {
/* 274 */     return this.attributes.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(Visitor v) {
/* 282 */     this.instruction.accept(v);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/InstructionHandle.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */