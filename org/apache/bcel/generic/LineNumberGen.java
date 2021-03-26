/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import org.apache.bcel.classfile.LineNumber;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LineNumberGen
/*     */   implements Cloneable, InstructionTargeter
/*     */ {
/*     */   private InstructionHandle ih;
/*     */   private int src_line;
/*     */   
/*     */   public LineNumberGen(InstructionHandle ih, int src_line) {
/*  80 */     setInstruction(ih);
/*  81 */     setSourceLine(src_line);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsTarget(InstructionHandle ih) {
/*  88 */     return (this.ih == ih);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
/*  96 */     if (old_ih != this.ih) {
/*  97 */       throw new ClassGenException("Not targeting " + old_ih + ", but " + this.ih + "}");
/*     */     }
/*  99 */     setInstruction(new_ih);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LineNumber getLineNumber() {
/* 109 */     return new LineNumber(this.ih.getPosition(), this.src_line);
/*     */   }
/*     */   
/*     */   public void setInstruction(InstructionHandle ih) {
/* 113 */     BranchInstruction.notifyTarget(this.ih, ih, this);
/*     */     
/* 115 */     this.ih = ih;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 120 */       return super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 122 */       System.err.println(e);
/* 123 */       return null;
/*     */     } 
/*     */   }
/*     */   
/* 127 */   public InstructionHandle getInstruction() { return this.ih; }
/* 128 */   public void setSourceLine(int src_line) { this.src_line = src_line; } public int getSourceLine() {
/* 129 */     return this.src_line;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/LineNumberGen.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */