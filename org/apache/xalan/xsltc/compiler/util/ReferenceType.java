/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ReferenceType
/*     */   extends Type
/*     */ {
/*     */   public String toString() {
/*  47 */     return "reference";
/*     */   }
/*     */   
/*     */   public boolean identicalTo(Type other) {
/*  51 */     return (this == other);
/*     */   }
/*     */   
/*     */   public String toSignature() {
/*  55 */     return "Ljava/lang/Object;";
/*     */   }
/*     */   
/*     */   public Type toJCType() {
/*  59 */     return (Type)Type.OBJECT;
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
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Type type) {
/*  71 */     if (type == Type.String) {
/*  72 */       translateTo(classGen, methodGen, (StringType)type);
/*     */     }
/*  74 */     else if (type == Type.Real) {
/*  75 */       translateTo(classGen, methodGen, (RealType)type);
/*     */     }
/*  77 */     else if (type == Type.Boolean) {
/*  78 */       translateTo(classGen, methodGen, (BooleanType)type);
/*     */     }
/*  80 */     else if (type == Type.NodeSet) {
/*  81 */       translateTo(classGen, methodGen, (NodeSetType)type);
/*     */     }
/*  83 */     else if (type == Type.Node) {
/*  84 */       translateTo(classGen, methodGen, (NodeType)type);
/*     */     }
/*  86 */     else if (type == Type.ResultTree) {
/*  87 */       translateTo(classGen, methodGen, (ResultTreeType)type);
/*     */     }
/*  89 */     else if (type == Type.Object) {
/*  90 */       translateTo(classGen, methodGen, (ObjectType)type);
/*     */     }
/*  92 */     else if (type != Type.Reference) {
/*     */ 
/*     */       
/*  95 */       ErrorMsg err = new ErrorMsg("INTERNAL_ERR", type.toString());
/*  96 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, StringType type) {
/* 107 */     int current = methodGen.getLocalIndex("current");
/* 108 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 109 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 112 */     if (current < 0) {
/* 113 */       il.append((CompoundInstruction)new PUSH(cpg, 0));
/*     */     } else {
/*     */       
/* 116 */       il.append((Instruction)new ILOAD(current));
/*     */     } 
/* 118 */     il.append(methodGen.loadDOM());
/* 119 */     int stringF = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "stringF", "(Ljava/lang/Object;ILorg/apache/xalan/xsltc/DOM;)Ljava/lang/String;");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     il.append((Instruction)new INVOKESTATIC(stringF));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, RealType type) {
/* 136 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 137 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 139 */     il.append(methodGen.loadDOM());
/* 140 */     int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "numberF", "(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)D");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     il.append((Instruction)new INVOKESTATIC(index));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/* 155 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 156 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 158 */     int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "booleanF", "(Ljava/lang/Object;)Z");
/*     */ 
/*     */ 
/*     */     
/* 162 */     il.append((Instruction)new INVOKESTATIC(index));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, NodeSetType type) {
/* 172 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 173 */     InstructionList il = methodGen.getInstructionList();
/* 174 */     int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToNodeSet", "(Ljava/lang/Object;)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 179 */     il.append((Instruction)new INVOKESTATIC(index));
/*     */ 
/*     */     
/* 182 */     index = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "reset", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
/* 183 */     il.append((Instruction)new INVOKEINTERFACE(index, 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, NodeType type) {
/* 193 */     translateTo(classGen, methodGen, Type.NodeSet);
/* 194 */     Type.NodeSet.translateTo(classGen, methodGen, type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, ResultTreeType type) {
/* 204 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 205 */     InstructionList il = methodGen.getInstructionList();
/* 206 */     int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToResultTree", "(Ljava/lang/Object;)Lorg/apache/xalan/xsltc/DOM;");
/*     */     
/* 208 */     il.append((Instruction)new INVOKESTATIC(index));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, ObjectType type) {
/* 218 */     methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 226 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 227 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 229 */     int referenceToLong = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToLong", "(Ljava/lang/Object;)J");
/*     */ 
/*     */     
/* 232 */     int referenceToDouble = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToDouble", "(Ljava/lang/Object;)D");
/*     */ 
/*     */     
/* 235 */     int referenceToBoolean = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToBoolean", "(Ljava/lang/Object;)Z");
/*     */ 
/*     */ 
/*     */     
/* 239 */     if (clazz.getName().equals("java.lang.Object")) {
/* 240 */       il.append(InstructionConstants.NOP);
/*     */     }
/* 242 */     else if (clazz == double.class) {
/* 243 */       il.append((Instruction)new INVOKESTATIC(referenceToDouble));
/*     */     }
/* 245 */     else if (clazz.getName().equals("java.lang.Double")) {
/* 246 */       il.append((Instruction)new INVOKESTATIC(referenceToDouble));
/* 247 */       Type.Real.translateTo(classGen, methodGen, Type.Reference);
/*     */     }
/* 249 */     else if (clazz == float.class) {
/* 250 */       il.append((Instruction)new INVOKESTATIC(referenceToDouble));
/* 251 */       il.append((Instruction)InstructionConstants.D2F);
/*     */     }
/* 253 */     else if (clazz.getName().equals("java.lang.String")) {
/* 254 */       int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToString", "(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)Ljava/lang/String;");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 260 */       il.append(methodGen.loadDOM());
/* 261 */       il.append((Instruction)new INVOKESTATIC(index));
/*     */     }
/* 263 */     else if (clazz.getName().equals("org.w3c.dom.Node")) {
/* 264 */       int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToNode", "(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)Lorg/w3c/dom/Node;");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 270 */       il.append(methodGen.loadDOM());
/* 271 */       il.append((Instruction)new INVOKESTATIC(index));
/*     */     }
/* 273 */     else if (clazz.getName().equals("org.w3c.dom.NodeList")) {
/* 274 */       int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToNodeList", "(Ljava/lang/Object;Lorg/apache/xalan/xsltc/DOM;)Lorg/w3c/dom/NodeList;");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 280 */       il.append(methodGen.loadDOM());
/* 281 */       il.append((Instruction)new INVOKESTATIC(index));
/*     */     }
/* 283 */     else if (clazz.getName().equals("org.apache.xalan.xsltc.DOM")) {
/* 284 */       translateTo(classGen, methodGen, Type.ResultTree);
/*     */     }
/* 286 */     else if (clazz == long.class) {
/* 287 */       il.append((Instruction)new INVOKESTATIC(referenceToLong));
/*     */     }
/* 289 */     else if (clazz == int.class) {
/* 290 */       il.append((Instruction)new INVOKESTATIC(referenceToLong));
/* 291 */       il.append((Instruction)InstructionConstants.L2I);
/*     */     }
/* 293 */     else if (clazz == short.class) {
/* 294 */       il.append((Instruction)new INVOKESTATIC(referenceToLong));
/* 295 */       il.append((Instruction)InstructionConstants.L2I);
/* 296 */       il.append((Instruction)InstructionConstants.I2S);
/*     */     }
/* 298 */     else if (clazz == byte.class) {
/* 299 */       il.append((Instruction)new INVOKESTATIC(referenceToLong));
/* 300 */       il.append((Instruction)InstructionConstants.L2I);
/* 301 */       il.append((Instruction)InstructionConstants.I2B);
/*     */     }
/* 303 */     else if (clazz == char.class) {
/* 304 */       il.append((Instruction)new INVOKESTATIC(referenceToLong));
/* 305 */       il.append((Instruction)InstructionConstants.L2I);
/* 306 */       il.append((Instruction)InstructionConstants.I2C);
/*     */     }
/* 308 */     else if (clazz == boolean.class) {
/* 309 */       il.append((Instruction)new INVOKESTATIC(referenceToBoolean));
/*     */     }
/* 311 */     else if (clazz.getName().equals("java.lang.Boolean")) {
/* 312 */       il.append((Instruction)new INVOKESTATIC(referenceToBoolean));
/* 313 */       Type.Boolean.translateTo(classGen, methodGen, Type.Reference);
/*     */     } else {
/*     */       
/* 316 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getName());
/*     */       
/* 318 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateFrom(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 328 */     if (clazz.getName().equals("java.lang.Object")) {
/* 329 */       methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */     } else {
/*     */       
/* 332 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), clazz.getName());
/*     */       
/* 334 */       classGen.getParser().reportError(2, err);
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
/*     */   public FlowList translateToDesynthesized(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/* 348 */     InstructionList il = methodGen.getInstructionList();
/* 349 */     translateTo(classGen, methodGen, type);
/* 350 */     return new FlowList((InstructionHandle)il.append((BranchInstruction)new IFEQ(null)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateBox(ClassGenerator classGen, MethodGenerator methodGen) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateUnBox(ClassGenerator classGen, MethodGenerator methodGen) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction LOAD(int slot) {
/* 369 */     return (Instruction)new ALOAD(slot);
/*     */   }
/*     */   
/*     */   public Instruction STORE(int slot) {
/* 373 */     return (Instruction)new ASTORE(slot);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/ReferenceType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */