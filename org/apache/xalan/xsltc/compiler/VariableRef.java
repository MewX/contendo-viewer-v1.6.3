/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.bcel.generic.CHECKCAST;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.GETFIELD;
/*    */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*    */ import org.apache.bcel.generic.Instruction;
/*    */ import org.apache.bcel.generic.InstructionConstants;
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
/*    */ final class VariableRef
/*    */   extends VariableRefBase
/*    */ {
/*    */   public VariableRef(Variable variable) {
/* 40 */     super(variable);
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 44 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 45 */     InstructionList il = methodGen.getInstructionList();
/*    */ 
/*    */     
/* 48 */     if (this._type.implementedAsMethod())
/*    */       return; 
/* 50 */     String name = this._variable.getEscapedName();
/* 51 */     String signature = this._type.toSignature();
/*    */     
/* 53 */     if (this._variable.isLocal()) {
/* 54 */       if (classGen.isExternal()) {
/* 55 */         Closure variableClosure = this._closure;
/* 56 */         while (variableClosure != null && 
/* 57 */           !variableClosure.inInnerClass()) {
/* 58 */           variableClosure = variableClosure.getParentClosure();
/*    */         }
/*    */         
/* 61 */         if (variableClosure != null) {
/* 62 */           il.append((Instruction)InstructionConstants.ALOAD_0);
/* 63 */           il.append((Instruction)new GETFIELD(cpg.addFieldref(variableClosure.getInnerClassName(), name, signature)));
/*    */         
/*    */         }
/*    */         else {
/*    */           
/* 68 */           il.append(this._variable.loadInstruction());
/* 69 */           this._variable.removeReference(this);
/*    */         } 
/*    */       } else {
/*    */         
/* 73 */         il.append(this._variable.loadInstruction());
/* 74 */         this._variable.removeReference(this);
/*    */       } 
/*    */     } else {
/*    */       
/* 78 */       String className = classGen.getClassName();
/* 79 */       il.append(classGen.loadTranslet());
/* 80 */       if (classGen.isExternal()) {
/* 81 */         il.append((Instruction)new CHECKCAST(cpg.addClass(className)));
/*    */       }
/* 83 */       il.append((Instruction)new GETFIELD(cpg.addFieldref(className, name, signature)));
/*    */     } 
/*    */     
/* 86 */     if (this._variable.getType() instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType) {
/*    */       
/* 88 */       int clone = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "cloneIterator", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
/*    */ 
/*    */ 
/*    */       
/* 92 */       il.append((Instruction)new INVOKEINTERFACE(clone, 1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/VariableRef.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */