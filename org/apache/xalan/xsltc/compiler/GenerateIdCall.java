/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.INVOKESTATIC;
/*    */ import org.apache.bcel.generic.Instruction;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ final class GenerateIdCall
/*    */   extends FunctionCall
/*    */ {
/*    */   public GenerateIdCall(QName fname, Vector arguments) {
/* 36 */     super(fname, arguments);
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 40 */     InstructionList il = methodGen.getInstructionList();
/* 41 */     if (argumentCount() == 0) {
/* 42 */       il.append(methodGen.loadContextNode());
/*    */     } else {
/*    */       
/* 45 */       argument().translate(classGen, methodGen);
/*    */     } 
/* 47 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 48 */     il.append((Instruction)new INVOKESTATIC(cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "generate_idF", "(I)Ljava/lang/String;")));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/GenerateIdCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */