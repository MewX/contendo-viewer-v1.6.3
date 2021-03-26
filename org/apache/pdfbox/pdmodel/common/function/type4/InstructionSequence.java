/*     */ package org.apache.pdfbox.pdmodel.common.function.type4;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Stack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InstructionSequence
/*     */ {
/*  29 */   private final List<Object> instructions = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addName(String name) {
/*  37 */     this.instructions.add(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInteger(int value) {
/*  46 */     this.instructions.add(Integer.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReal(float value) {
/*  55 */     this.instructions.add(Float.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addBoolean(boolean value) {
/*  64 */     this.instructions.add(Boolean.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addProc(InstructionSequence child) {
/*  73 */     this.instructions.add(child);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(ExecutionContext context) {
/*  82 */     Stack<Object> stack = context.getStack();
/*  83 */     for (Object o : this.instructions) {
/*     */       
/*  85 */       if (o instanceof String) {
/*     */         
/*  87 */         String name = (String)o;
/*  88 */         Operator cmd = context.getOperators().getOperator(name);
/*  89 */         if (cmd != null) {
/*     */           
/*  91 */           cmd.execute(context);
/*     */           
/*     */           continue;
/*     */         } 
/*  95 */         throw new UnsupportedOperationException("Unknown operator or name: " + name);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 100 */       stack.push(o);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 105 */     while (!stack.isEmpty() && stack.peek() instanceof InstructionSequence) {
/*     */       
/* 107 */       InstructionSequence nested = (InstructionSequence)stack.pop();
/* 108 */       nested.execute(context);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/type4/InstructionSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */