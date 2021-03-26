/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.BranchHandle;
/*    */ import org.apache.bcel.generic.BranchInstruction;
/*    */ import org.apache.bcel.generic.GOTO;
/*    */ import org.apache.bcel.generic.Instruction;
/*    */ import org.apache.bcel.generic.InstructionConstants;
/*    */ import org.apache.bcel.generic.InstructionHandle;
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class NotCall
/*    */   extends FunctionCall
/*    */ {
/*    */   public NotCall(QName fname, Vector arguments) {
/* 36 */     super(fname, arguments);
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 40 */     InstructionList il = methodGen.getInstructionList();
/* 41 */     argument().translate(classGen, methodGen);
/* 42 */     il.append(InstructionConstants.ICONST_1);
/* 43 */     il.append((Instruction)InstructionConstants.IXOR);
/*    */   }
/*    */ 
/*    */   
/*    */   public void translateDesynthesized(ClassGenerator classGen, MethodGenerator methodGen) {
/* 48 */     InstructionList il = methodGen.getInstructionList();
/* 49 */     Expression exp = argument();
/* 50 */     exp.translateDesynthesized(classGen, methodGen);
/* 51 */     BranchHandle gotoh = il.append((BranchInstruction)new GOTO(null));
/* 52 */     this._trueList = exp._falseList;
/* 53 */     this._falseList = exp._trueList;
/* 54 */     this._falseList.add((InstructionHandle)gotoh);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/NotCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */