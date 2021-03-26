/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.bcel.Constants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CodeException
/*     */   implements Cloneable, Constants, Node
/*     */ {
/*     */   private int start_pc;
/*     */   private int end_pc;
/*     */   private int handler_pc;
/*     */   private int catch_type;
/*     */   
/*     */   public CodeException(CodeException c) {
/*  83 */     this(c.getStartPC(), c.getEndPC(), c.getHandlerPC(), c.getCatchType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   CodeException(DataInputStream file) throws IOException {
/*  93 */     this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
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
/*     */   public CodeException(int start_pc, int end_pc, int handler_pc, int catch_type) {
/* 110 */     this.start_pc = start_pc;
/* 111 */     this.end_pc = end_pc;
/* 112 */     this.handler_pc = handler_pc;
/* 113 */     this.catch_type = catch_type;
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
/* 124 */     v.visitCodeException(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 134 */     file.writeShort(this.start_pc);
/* 135 */     file.writeShort(this.end_pc);
/* 136 */     file.writeShort(this.handler_pc);
/* 137 */     file.writeShort(this.catch_type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getCatchType() {
/* 144 */     return this.catch_type;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getEndPC() {
/* 149 */     return this.end_pc;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getHandlerPC() {
/* 154 */     return this.handler_pc;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getStartPC() {
/* 159 */     return this.start_pc;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setCatchType(int catch_type) {
/* 165 */     this.catch_type = catch_type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setEndPC(int end_pc) {
/* 172 */     this.end_pc = end_pc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setHandlerPC(int handler_pc) {
/* 179 */     this.handler_pc = handler_pc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setStartPC(int start_pc) {
/* 186 */     this.start_pc = start_pc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 193 */     return "CodeException(start_pc = " + this.start_pc + ", end_pc = " + this.end_pc + ", handler_pc = " + this.handler_pc + ", catch_type = " + this.catch_type + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString(ConstantPool cp, boolean verbose) {
/*     */     String str;
/* 204 */     if (this.catch_type == 0) {
/* 205 */       str = "<Any exception>(0)";
/*     */     } else {
/* 207 */       str = Utility.compactClassName(cp.getConstantString(this.catch_type, (byte)7), false) + (verbose ? ("(" + this.catch_type + ")") : "");
/*     */     } 
/*     */     
/* 210 */     return this.start_pc + "\t" + this.end_pc + "\t" + this.handler_pc + "\t" + str;
/*     */   }
/*     */   
/*     */   public final String toString(ConstantPool cp) {
/* 214 */     return toString(cp, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CodeException copy() {
/*     */     try {
/* 222 */       return (CodeException)clone();
/* 223 */     } catch (CloneNotSupportedException e) {
/*     */       
/* 225 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/CodeException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */