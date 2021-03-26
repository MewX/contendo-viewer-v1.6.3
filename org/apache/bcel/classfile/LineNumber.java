/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.io.DataInputStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LineNumber
/*     */   implements Cloneable, Node
/*     */ {
/*     */   private int start_pc;
/*     */   private int line_number;
/*     */   
/*     */   public LineNumber(LineNumber c) {
/*  77 */     this(c.getStartPC(), c.getLineNumber());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LineNumber(DataInputStream file) throws IOException {
/*  87 */     this(file.readUnsignedShort(), file.readUnsignedShort());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LineNumber(int start_pc, int line_number) {
/*  96 */     this.start_pc = start_pc;
/*  97 */     this.line_number = line_number;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(Visitor v) {
/* 108 */     v.visitLineNumber(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 119 */     file.writeShort(this.start_pc);
/* 120 */     file.writeShort(this.line_number);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getLineNumber() {
/* 126 */     return this.line_number;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getStartPC() {
/* 131 */     return this.start_pc;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setLineNumber(int line_number) {
/* 137 */     this.line_number = line_number;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setStartPC(int start_pc) {
/* 144 */     this.start_pc = start_pc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 151 */     return "LineNumber(" + this.start_pc + ", " + this.line_number + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LineNumber copy() {
/*     */     try {
/* 159 */       return (LineNumber)clone();
/* 160 */     } catch (CloneNotSupportedException e) {
/*     */       
/* 162 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/LineNumber.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */