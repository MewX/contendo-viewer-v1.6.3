/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.bcel.generic.BranchHandle;
/*    */ import org.apache.bcel.generic.BranchInstruction;
/*    */ import org.apache.bcel.generic.GOTO;
/*    */ import org.apache.bcel.generic.InstructionHandle;
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.Type;
/*    */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class AlternativePattern
/*    */   extends Pattern
/*    */ {
/*    */   private final Pattern _left;
/*    */   private final Pattern _right;
/*    */   
/*    */   public AlternativePattern(Pattern left, Pattern right) {
/* 43 */     this._left = left;
/* 44 */     this._right = right;
/*    */   }
/*    */   
/*    */   public void setParser(Parser parser) {
/* 48 */     super.setParser(parser);
/* 49 */     this._left.setParser(parser);
/* 50 */     this._right.setParser(parser);
/*    */   }
/*    */   
/*    */   public Pattern getLeft() {
/* 54 */     return this._left;
/*    */   }
/*    */   
/*    */   public Pattern getRight() {
/* 58 */     return this._right;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 65 */     this._left.typeCheck(stable);
/* 66 */     this._right.typeCheck(stable);
/* 67 */     return null;
/*    */   }
/*    */   
/*    */   public double getPriority() {
/* 71 */     double left = this._left.getPriority();
/* 72 */     double right = this._right.getPriority();
/*    */     
/* 74 */     if (left < right) {
/* 75 */       return left;
/*    */     }
/* 77 */     return right;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 81 */     return "alternative(" + this._left + ", " + this._right + ')';
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 85 */     InstructionList il = methodGen.getInstructionList();
/*    */     
/* 87 */     this._left.translate(classGen, methodGen);
/* 88 */     BranchHandle branchHandle = il.append((BranchInstruction)new GOTO(null));
/* 89 */     il.append(methodGen.loadContextNode());
/* 90 */     this._right.translate(classGen, methodGen);
/*    */     
/* 92 */     this._left._trueList.backPatch((InstructionHandle)branchHandle);
/* 93 */     this._left._falseList.backPatch(branchHandle.getNext());
/*    */     
/* 95 */     this._trueList.append(this._right._trueList.add((InstructionHandle)branchHandle));
/* 96 */     this._falseList.append(this._right._falseList);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/AlternativePattern.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */