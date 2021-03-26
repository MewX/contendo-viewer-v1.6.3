/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.INVOKEINTERFACE;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ final class LocalNameCall
/*    */   extends NameBase
/*    */ {
/*    */   public LocalNameCall(QName fname) {
/* 40 */     super(fname);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LocalNameCall(QName fname, Vector arguments) {
/* 47 */     super(fname, arguments);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 56 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 57 */     InstructionList il = methodGen.getInstructionList();
/*    */ 
/*    */     
/* 60 */     int getNodeName = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeName", "(I)Ljava/lang/String;");
/*    */ 
/*    */ 
/*    */     
/* 64 */     int getLocalName = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "getLocalName", "(Ljava/lang/String;)Ljava/lang/String;");
/*    */ 
/*    */ 
/*    */     
/* 68 */     super.translate(classGen, methodGen);
/* 69 */     il.append((Instruction)new INVOKEINTERFACE(getNodeName, 2));
/* 70 */     il.append((Instruction)new INVOKESTATIC(getLocalName));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/LocalNameCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */