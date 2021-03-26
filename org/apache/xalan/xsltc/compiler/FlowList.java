/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FlowList
/*     */ {
/*     */   private Vector _elements;
/*     */   
/*     */   public FlowList() {
/*  37 */     this._elements = null;
/*     */   }
/*     */   
/*     */   public FlowList(InstructionHandle bh) {
/*  41 */     this._elements = new Vector();
/*  42 */     this._elements.addElement(bh);
/*     */   }
/*     */   
/*     */   public FlowList(FlowList list) {
/*  46 */     this._elements = list._elements;
/*     */   }
/*     */   
/*     */   public FlowList add(InstructionHandle bh) {
/*  50 */     if (this._elements == null) {
/*  51 */       this._elements = new Vector();
/*     */     }
/*  53 */     this._elements.addElement(bh);
/*  54 */     return this;
/*     */   }
/*     */   
/*     */   public FlowList append(FlowList right) {
/*  58 */     if (this._elements == null) {
/*  59 */       this._elements = right._elements;
/*     */     } else {
/*     */       
/*  62 */       Vector temp = right._elements;
/*  63 */       if (temp != null) {
/*  64 */         int n = temp.size();
/*  65 */         for (int i = 0; i < n; i++) {
/*  66 */           this._elements.addElement(temp.elementAt(i));
/*     */         }
/*     */       } 
/*     */     } 
/*  70 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void backPatch(InstructionHandle target) {
/*  77 */     if (this._elements != null) {
/*  78 */       int n = this._elements.size();
/*  79 */       for (int i = 0; i < n; i++) {
/*  80 */         BranchHandle bh = this._elements.elementAt(i);
/*  81 */         bh.setTarget(target);
/*     */       } 
/*  83 */       this._elements.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlowList copyAndRedirect(InstructionList oldList, InstructionList newList) {
/*  94 */     FlowList result = new FlowList();
/*  95 */     if (this._elements == null) {
/*  96 */       return result;
/*     */     }
/*     */     
/*  99 */     int n = this._elements.size();
/* 100 */     Iterator oldIter = oldList.iterator();
/* 101 */     Iterator newIter = newList.iterator();
/*     */     
/* 103 */     while (oldIter.hasNext()) {
/* 104 */       InstructionHandle oldIh = oldIter.next();
/* 105 */       InstructionHandle newIh = newIter.next();
/*     */       
/* 107 */       for (int i = 0; i < n; i++) {
/* 108 */         if (this._elements.elementAt(i) == oldIh) {
/* 109 */           result.add(newIh);
/*     */         }
/*     */       } 
/*     */     } 
/* 113 */     return result;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/FlowList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */