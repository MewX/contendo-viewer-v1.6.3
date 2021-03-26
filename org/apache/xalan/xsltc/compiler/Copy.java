/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.IFNULL;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
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
/*     */ final class Copy
/*     */   extends Instruction
/*     */ {
/*     */   private UseAttributeSets _useSets;
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  50 */     String useSets = getAttribute("use-attribute-sets");
/*  51 */     if (useSets.length() > 0) {
/*  52 */       if (!Util.isValidQNames(useSets)) {
/*  53 */         ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", useSets, this);
/*  54 */         parser.reportError(3, err);
/*     */       } 
/*  56 */       this._useSets = new UseAttributeSets(useSets, parser);
/*     */     } 
/*  58 */     parseChildren(parser);
/*     */   }
/*     */   
/*     */   public void display(int indent) {
/*  62 */     indent(indent);
/*  63 */     Util.println("Copy");
/*  64 */     indent(indent + 4);
/*  65 */     displayContents(indent + 4);
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  69 */     if (this._useSets != null) {
/*  70 */       this._useSets.typeCheck(stable);
/*     */     }
/*  72 */     typeCheckContents(stable);
/*  73 */     return Type.Void;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  77 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  78 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/*  80 */     LocalVariableGen name = methodGen.addLocalVariable2("name", Util.getJCRefType("Ljava/lang/String;"), il.getEnd());
/*     */ 
/*     */ 
/*     */     
/*  84 */     LocalVariableGen length = methodGen.addLocalVariable2("length", Util.getJCRefType("I"), il.getEnd());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     il.append(methodGen.loadDOM());
/*  91 */     il.append(methodGen.loadCurrentNode());
/*  92 */     il.append(methodGen.loadHandler());
/*  93 */     int cpy = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "shallowCopy", "(ILorg/apache/xml/serializer/SerializationHandler;)Ljava/lang/String;");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     il.append((Instruction)new INVOKEINTERFACE(cpy, 3));
/* 100 */     il.append((Instruction)InstructionConstants.DUP);
/* 101 */     il.append((Instruction)new ASTORE(name.getIndex()));
/* 102 */     BranchHandle ifBlock1 = il.append((BranchInstruction)new IFNULL(null));
/*     */ 
/*     */     
/* 105 */     il.append((Instruction)new ALOAD(name.getIndex()));
/* 106 */     int lengthMethod = cpg.addMethodref("java.lang.String", "length", "()I");
/* 107 */     il.append((Instruction)new INVOKEVIRTUAL(lengthMethod));
/* 108 */     il.append((Instruction)new ISTORE(length.getIndex()));
/*     */ 
/*     */     
/* 111 */     if (this._useSets != null) {
/*     */ 
/*     */       
/* 114 */       SyntaxTreeNode parent = getParent();
/* 115 */       if (parent instanceof LiteralElement || parent instanceof LiteralElement) {
/*     */         
/* 117 */         this._useSets.translate(classGen, methodGen);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 123 */         il.append((Instruction)new ILOAD(length.getIndex()));
/* 124 */         BranchHandle ifBlock2 = il.append((BranchInstruction)new IFEQ(null));
/*     */         
/* 126 */         this._useSets.translate(classGen, methodGen);
/*     */         
/* 128 */         ifBlock2.setTarget(il.append(InstructionConstants.NOP));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 133 */     translateContents(classGen, methodGen);
/*     */ 
/*     */ 
/*     */     
/* 137 */     il.append((Instruction)new ILOAD(length.getIndex()));
/* 138 */     BranchHandle ifBlock3 = il.append((BranchInstruction)new IFEQ(null));
/* 139 */     il.append(methodGen.loadHandler());
/* 140 */     il.append((Instruction)new ALOAD(name.getIndex()));
/* 141 */     il.append(methodGen.endElement());
/*     */     
/* 143 */     InstructionHandle end = il.append(InstructionConstants.NOP);
/* 144 */     ifBlock1.setTarget(end);
/* 145 */     ifBlock3.setTarget(end);
/* 146 */     methodGen.removeLocalVariable(name);
/* 147 */     methodGen.removeLocalVariable(length);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Copy.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */