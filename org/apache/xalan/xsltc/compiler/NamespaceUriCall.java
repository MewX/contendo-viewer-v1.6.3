/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.INVOKEINTERFACE;
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
/*    */ final class NamespaceUriCall
/*    */   extends NameBase
/*    */ {
/*    */   public NamespaceUriCall(QName fname) {
/* 39 */     super(fname);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NamespaceUriCall(QName fname, Vector arguments) {
/* 46 */     super(fname, arguments);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 55 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 56 */     InstructionList il = methodGen.getInstructionList();
/*    */ 
/*    */     
/* 59 */     int getNamespace = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNamespaceName", "(I)Ljava/lang/String;");
/*    */ 
/*    */     
/* 62 */     super.translate(classGen, methodGen);
/* 63 */     il.append((Instruction)new INVOKEINTERFACE(getNamespace, 2));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/NamespaceUriCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */