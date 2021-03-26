/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Choose
/*     */   extends Instruction
/*     */ {
/*     */   public void display(int indent) {
/*  48 */     indent(indent);
/*  49 */     Util.println("Choose");
/*  50 */     indent(indent + 4);
/*  51 */     displayContents(indent + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  59 */     Vector whenElements = new Vector();
/*  60 */     Otherwise otherwise = null;
/*  61 */     Enumeration elements = elements();
/*     */ 
/*     */     
/*  64 */     ErrorMsg error = null;
/*  65 */     int line = getLineNumber();
/*     */ 
/*     */     
/*  68 */     while (elements.hasMoreElements()) {
/*  69 */       Object element = elements.nextElement();
/*     */       
/*  71 */       if (element instanceof When) {
/*  72 */         whenElements.addElement(element);
/*     */         continue;
/*     */       } 
/*  75 */       if (element instanceof Otherwise) {
/*  76 */         if (otherwise == null) {
/*  77 */           otherwise = (Otherwise)element;
/*     */           continue;
/*     */         } 
/*  80 */         error = new ErrorMsg("MULTIPLE_OTHERWISE_ERR", this);
/*  81 */         getParser().reportError(3, error);
/*     */         continue;
/*     */       } 
/*  84 */       if (element instanceof Text) {
/*  85 */         ((Text)element).ignore();
/*     */         
/*     */         continue;
/*     */       } 
/*  89 */       error = new ErrorMsg("WHEN_ELEMENT_ERR", this);
/*  90 */       getParser().reportError(3, error);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  95 */     if (whenElements.size() == 0) {
/*  96 */       error = new ErrorMsg("MISSING_WHEN_ERR", this);
/*  97 */       getParser().reportError(3, error);
/*     */       
/*     */       return;
/*     */     } 
/* 101 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */ 
/*     */     
/* 105 */     BranchHandle nextElement = null;
/* 106 */     Vector exitHandles = new Vector();
/* 107 */     InstructionHandle exit = null;
/*     */     
/* 109 */     Enumeration whens = whenElements.elements();
/* 110 */     while (whens.hasMoreElements()) {
/* 111 */       When when = whens.nextElement();
/* 112 */       Expression test = when.getTest();
/*     */       
/* 114 */       InstructionHandle truec = il.getEnd();
/*     */       
/* 116 */       if (nextElement != null)
/* 117 */         nextElement.setTarget(il.append(InstructionConstants.NOP)); 
/* 118 */       test.translateDesynthesized(classGen, methodGen);
/*     */       
/* 120 */       if (test instanceof FunctionCall) {
/* 121 */         FunctionCall call = (FunctionCall)test;
/*     */         
/* 123 */         try { Type type = call.typeCheck(getParser().getSymbolTable());
/* 124 */           if (type != Type.Boolean)
/* 125 */             test._falseList.add((InstructionHandle)il.append((BranchInstruction)new IFEQ(null)));  } catch (TypeCheckError typeCheckError) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       truec = il.getEnd();
/*     */ 
/*     */ 
/*     */       
/* 137 */       if (!when.ignore()) when.translateContents(classGen, methodGen);
/*     */ 
/*     */       
/* 140 */       exitHandles.addElement(il.append((BranchInstruction)new GOTO(null)));
/* 141 */       if (whens.hasMoreElements() || otherwise != null) {
/* 142 */         nextElement = il.append((BranchInstruction)new GOTO(null));
/* 143 */         test.backPatchFalseList((InstructionHandle)nextElement);
/*     */       } else {
/*     */         
/* 146 */         test.backPatchFalseList(exit = il.append(InstructionConstants.NOP));
/* 147 */       }  test.backPatchTrueList(truec.getNext());
/*     */     } 
/*     */ 
/*     */     
/* 151 */     if (otherwise != null) {
/* 152 */       nextElement.setTarget(il.append(InstructionConstants.NOP));
/* 153 */       otherwise.translateContents(classGen, methodGen);
/* 154 */       exit = il.append(InstructionConstants.NOP);
/*     */     } 
/*     */ 
/*     */     
/* 158 */     Enumeration exitGotos = exitHandles.elements();
/* 159 */     while (exitGotos.hasMoreElements()) {
/* 160 */       BranchHandle gotoExit = exitGotos.nextElement();
/* 161 */       gotoExit.setTarget(exit);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Choose.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */