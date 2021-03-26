/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFLT;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.ObjectType;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.xalan.xsltc.compiler.FlowList;
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
/*     */ public final class NodeSetType
/*     */   extends Type
/*     */ {
/*     */   public String toString() {
/*  44 */     return "node-set";
/*     */   }
/*     */   
/*     */   public boolean identicalTo(Type other) {
/*  48 */     return (this == other);
/*     */   }
/*     */   
/*     */   public String toSignature() {
/*  52 */     return "Lorg/apache/xml/dtm/DTMAxisIterator;";
/*     */   }
/*     */   
/*     */   public Type toJCType() {
/*  56 */     return (Type)new ObjectType("org.apache.xml.dtm.DTMAxisIterator");
/*     */   }
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
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Type type) {
/*  69 */     if (type == Type.String) {
/*  70 */       translateTo(classGen, methodGen, (StringType)type);
/*     */     }
/*  72 */     else if (type == Type.Boolean) {
/*  73 */       translateTo(classGen, methodGen, (BooleanType)type);
/*     */     }
/*  75 */     else if (type == Type.Real) {
/*  76 */       translateTo(classGen, methodGen, (RealType)type);
/*     */     }
/*  78 */     else if (type == Type.Node) {
/*  79 */       translateTo(classGen, methodGen, (NodeType)type);
/*     */     }
/*  81 */     else if (type == Type.Reference) {
/*  82 */       translateTo(classGen, methodGen, (ReferenceType)type);
/*     */     }
/*  84 */     else if (type == Type.Object) {
/*  85 */       translateTo(classGen, methodGen, (ObjectType)type);
/*     */     } else {
/*     */       
/*  88 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*     */       
/*  90 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateFrom(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 102 */     InstructionList il = methodGen.getInstructionList();
/* 103 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 104 */     if (clazz.getName().equals("org.w3c.dom.NodeList")) {
/*     */ 
/*     */ 
/*     */       
/* 108 */       il.append(classGen.loadTranslet());
/* 109 */       il.append(methodGen.loadDOM());
/* 110 */       int convert = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "nodeList2Iterator", "(Lorg/w3c/dom/NodeList;Lorg/apache/xalan/xsltc/Translet;Lorg/apache/xalan/xsltc/DOM;)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       il.append((Instruction)new INVOKESTATIC(convert));
/*     */     }
/* 119 */     else if (clazz.getName().equals("org.w3c.dom.Node")) {
/*     */ 
/*     */ 
/*     */       
/* 123 */       il.append(classGen.loadTranslet());
/* 124 */       il.append(methodGen.loadDOM());
/* 125 */       int convert = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "node2Iterator", "(Lorg/w3c/dom/Node;Lorg/apache/xalan/xsltc/Translet;Lorg/apache/xalan/xsltc/DOM;)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 132 */       il.append((Instruction)new INVOKESTATIC(convert));
/*     */     } else {
/*     */       
/* 135 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getName());
/*     */       
/* 137 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
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
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/* 152 */     InstructionList il = methodGen.getInstructionList();
/* 153 */     FlowList falsel = translateToDesynthesized(classGen, methodGen, type);
/* 154 */     il.append(InstructionConstants.ICONST_1);
/* 155 */     BranchHandle truec = il.append((BranchInstruction)new GOTO(null));
/* 156 */     falsel.backPatch(il.append(InstructionConstants.ICONST_0));
/* 157 */     truec.setTarget(il.append(InstructionConstants.NOP));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, StringType type) {
/* 168 */     InstructionList il = methodGen.getInstructionList();
/* 169 */     getFirstNode(classGen, methodGen);
/* 170 */     il.append((Instruction)InstructionConstants.DUP);
/* 171 */     BranchHandle falsec = il.append((BranchInstruction)new IFLT(null));
/* 172 */     Type.Node.translateTo(classGen, methodGen, type);
/* 173 */     BranchHandle truec = il.append((BranchInstruction)new GOTO(null));
/* 174 */     falsec.setTarget(il.append((Instruction)InstructionConstants.POP));
/* 175 */     il.append((CompoundInstruction)new PUSH(classGen.getConstantPool(), ""));
/* 176 */     truec.setTarget(il.append(InstructionConstants.NOP));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, RealType type) {
/* 187 */     translateTo(classGen, methodGen, Type.String);
/* 188 */     Type.String.translateTo(classGen, methodGen, Type.Real);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, NodeType type) {
/* 198 */     getFirstNode(classGen, methodGen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, ObjectType type) {
/* 208 */     methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */   }
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
/*     */   public FlowList translateToDesynthesized(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/* 221 */     InstructionList il = methodGen.getInstructionList();
/* 222 */     getFirstNode(classGen, methodGen);
/* 223 */     return new FlowList((InstructionHandle)il.append((BranchInstruction)new IFLT(null)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, ReferenceType type) {
/* 234 */     methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 244 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 245 */     InstructionList il = methodGen.getInstructionList();
/* 246 */     String className = clazz.getName();
/*     */     
/* 248 */     il.append(methodGen.loadDOM());
/* 249 */     il.append((Instruction)InstructionConstants.SWAP);
/*     */     
/* 251 */     if (className.equals("org.w3c.dom.Node")) {
/* 252 */       int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "makeNode", "(Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/w3c/dom/Node;");
/*     */ 
/*     */       
/* 255 */       il.append((Instruction)new INVOKEINTERFACE(index, 2));
/*     */     }
/* 257 */     else if (className.equals("org.w3c.dom.NodeList") || className.equals("java.lang.Object")) {
/*     */       
/* 259 */       int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "makeNodeList", "(Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/w3c/dom/NodeList;");
/*     */ 
/*     */       
/* 262 */       il.append((Instruction)new INVOKEINTERFACE(index, 2));
/*     */     }
/* 264 */     else if (className.equals("java.lang.String")) {
/* 265 */       int next = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "next", "()I");
/*     */       
/* 267 */       int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getStringValueX", "(I)Ljava/lang/String;");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 272 */       il.append((Instruction)new INVOKEINTERFACE(next, 1));
/*     */       
/* 274 */       il.append((Instruction)new INVOKEINTERFACE(index, 2));
/*     */     }
/*     */     else {
/*     */       
/* 278 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), className);
/*     */       
/* 280 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void getFirstNode(ClassGenerator classGen, MethodGenerator methodGen) {
/* 289 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 290 */     InstructionList il = methodGen.getInstructionList();
/* 291 */     il.append((Instruction)new INVOKEINTERFACE(cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "next", "()I"), 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 301 */     translateTo(classGen, methodGen, Type.Reference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateUnBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 309 */     methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClassName() {
/* 316 */     return "org.apache.xml.dtm.DTMAxisIterator";
/*     */   }
/*     */ 
/*     */   
/*     */   public Instruction LOAD(int slot) {
/* 321 */     return (Instruction)new ALOAD(slot);
/*     */   }
/*     */   
/*     */   public Instruction STORE(int slot) {
/* 325 */     return (Instruction)new ASTORE(slot);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/NodeSetType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */