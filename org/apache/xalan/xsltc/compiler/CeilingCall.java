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
/*    */ final class CeilingCall
/*    */   extends FunctionCall
/*    */ {
/*    */   public CeilingCall(QName fname, Vector arguments) {
/* 36 */     super(fname, arguments);
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 40 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 41 */     InstructionList il = methodGen.getInstructionList();
/* 42 */     argument(0).translate(classGen, methodGen);
/* 43 */     il.append((Instruction)new INVOKESTATIC(cpg.addMethodref("java.lang.Math", "ceil", "(D)D")));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/CeilingCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */