/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETFIELD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.runtime.BasisLibrary;
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
/*     */ 
/*     */ final class ParameterRef
/*     */   extends VariableRefBase
/*     */ {
/*  43 */   QName _name = null;
/*     */   
/*     */   public ParameterRef(Param param) {
/*  46 */     super(param);
/*  47 */     this._name = param._name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  52 */     return "parameter-ref(" + this._variable.getName() + '/' + this._variable.getType() + ')';
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  56 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  57 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     String name = BasisLibrary.mapQNameToJavaName(this._name.toString());
/*  65 */     String signature = this._type.toSignature();
/*     */     
/*  67 */     if (this._variable.isLocal()) {
/*  68 */       if (classGen.isExternal()) {
/*  69 */         Closure variableClosure = this._closure;
/*  70 */         while (variableClosure != null && 
/*  71 */           !variableClosure.inInnerClass()) {
/*  72 */           variableClosure = variableClosure.getParentClosure();
/*     */         }
/*     */         
/*  75 */         if (variableClosure != null) {
/*  76 */           il.append((Instruction)InstructionConstants.ALOAD_0);
/*  77 */           il.append((Instruction)new GETFIELD(cpg.addFieldref(variableClosure.getInnerClassName(), name, signature)));
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  82 */           il.append(this._variable.loadInstruction());
/*  83 */           this._variable.removeReference(this);
/*     */         } 
/*     */       } else {
/*     */         
/*  87 */         il.append(this._variable.loadInstruction());
/*  88 */         this._variable.removeReference(this);
/*     */       } 
/*     */     } else {
/*     */       
/*  92 */       String className = classGen.getClassName();
/*  93 */       il.append(classGen.loadTranslet());
/*  94 */       if (classGen.isExternal()) {
/*  95 */         il.append((Instruction)new CHECKCAST(cpg.addClass(className)));
/*     */       }
/*  97 */       il.append((Instruction)new GETFIELD(cpg.addFieldref(className, name, signature)));
/*     */     } 
/*     */     
/* 100 */     if (this._variable.getType() instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType) {
/*     */       
/* 102 */       int clone = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "cloneIterator", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */ 
/*     */       
/* 106 */       il.append((Instruction)new INVOKEINTERFACE(clone, 1));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ParameterRef.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */