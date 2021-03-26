/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*    */ import org.apache.bcel.generic.Instruction;
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.Type;
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
/*    */ final class StringLengthCall
/*    */   extends FunctionCall
/*    */ {
/*    */   public StringLengthCall(QName fname, Vector arguments) {
/* 37 */     super(fname, arguments);
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 41 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 42 */     InstructionList il = methodGen.getInstructionList();
/* 43 */     if (argumentCount() > 0) {
/* 44 */       argument().translate(classGen, methodGen);
/*    */     } else {
/*    */       
/* 47 */       il.append(methodGen.loadContextNode());
/* 48 */       Type.Node.translateTo(classGen, methodGen, Type.String);
/*    */     } 
/* 50 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("java.lang.String", "length", "()I")));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/StringLengthCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */