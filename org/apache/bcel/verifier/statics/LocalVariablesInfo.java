/*     */ package org.apache.bcel.verifier.statics;
/*     */ 
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.bcel.verifier.exc.AssertionViolatedException;
/*     */ import org.apache.bcel.verifier.exc.LocalVariableInfoInconsistentException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalVariablesInfo
/*     */ {
/*     */   private LocalVariableInfo[] localVariableInfos;
/*  78 */   private IntList instruction_offsets = new IntList();
/*     */ 
/*     */   
/*     */   LocalVariablesInfo(int max_locals) {
/*  82 */     this.localVariableInfos = new LocalVariableInfo[max_locals];
/*  83 */     for (int i = 0; i < max_locals; i++) {
/*  84 */       this.localVariableInfos[i] = new LocalVariableInfo();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public LocalVariableInfo getLocalVariableInfo(int slot) {
/*  90 */     if (slot < 0 || slot >= this.localVariableInfos.length) {
/*  91 */       throw new AssertionViolatedException("Slot number for local variable information out of range.");
/*     */     }
/*  93 */     return this.localVariableInfos[slot];
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
/*     */   public void add(int slot, String name, int startpc, int length, Type t) throws LocalVariableInfoInconsistentException {
/* 105 */     if (slot < 0 || slot >= this.localVariableInfos.length) {
/* 106 */       throw new AssertionViolatedException("Slot number for local variable information out of range.");
/*     */     }
/*     */     
/* 109 */     this.localVariableInfos[slot].add(name, startpc, length, t);
/* 110 */     if (t == Type.LONG) this.localVariableInfos[slot + 1].add(name, startpc, length, LONG_Upper.theInstance()); 
/* 111 */     if (t == Type.DOUBLE) this.localVariableInfos[slot + 1].add(name, startpc, length, DOUBLE_Upper.theInstance()); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/statics/LocalVariablesInfo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */