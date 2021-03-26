/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETFIELD;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
/*     */ import org.apache.bcel.generic.NEW;
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
/*     */ public final class ResultTreeType
/*     */   extends Type
/*     */ {
/*     */   private final String _methodName;
/*     */   
/*     */   protected ResultTreeType() {
/*  48 */     this._methodName = null;
/*     */   }
/*     */   
/*     */   public ResultTreeType(String methodName) {
/*  52 */     this._methodName = methodName;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  56 */     return "result-tree";
/*     */   }
/*     */   
/*     */   public boolean identicalTo(Type other) {
/*  60 */     return other instanceof ResultTreeType;
/*     */   }
/*     */   
/*     */   public String toSignature() {
/*  64 */     return "Lorg/apache/xalan/xsltc/DOM;";
/*     */   }
/*     */   
/*     */   public Type toJCType() {
/*  68 */     return Util.getJCRefType(toSignature());
/*     */   }
/*     */   
/*     */   public String getMethodName() {
/*  72 */     return this._methodName;
/*     */   }
/*     */   
/*     */   public boolean implementedAsMethod() {
/*  76 */     return (this._methodName != null);
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
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Type type) {
/*  91 */     if (type == Type.String) {
/*  92 */       translateTo(classGen, methodGen, (StringType)type);
/*     */     }
/*  94 */     else if (type == Type.Boolean) {
/*  95 */       translateTo(classGen, methodGen, (BooleanType)type);
/*     */     }
/*  97 */     else if (type == Type.Real) {
/*  98 */       translateTo(classGen, methodGen, (RealType)type);
/*     */     }
/* 100 */     else if (type == Type.NodeSet) {
/* 101 */       translateTo(classGen, methodGen, (NodeSetType)type);
/*     */     }
/* 103 */     else if (type == Type.Reference) {
/* 104 */       translateTo(classGen, methodGen, (ReferenceType)type);
/*     */     }
/* 106 */     else if (type == Type.Object) {
/* 107 */       translateTo(classGen, methodGen, (ObjectType)type);
/*     */     } else {
/*     */       
/* 110 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*     */       
/* 112 */       classGen.getParser().reportError(2, err);
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
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/* 129 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 130 */     InstructionList il = methodGen.getInstructionList();
/* 131 */     il.append((Instruction)InstructionConstants.POP);
/* 132 */     il.append(InstructionConstants.ICONST_1);
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
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, StringType type) {
/* 145 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 146 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 148 */     if (this._methodName == null) {
/* 149 */       int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getStringValue", "()Ljava/lang/String;");
/*     */ 
/*     */       
/* 152 */       il.append((Instruction)new INVOKEINTERFACE(index, 1));
/*     */     } else {
/*     */       
/* 155 */       String className = classGen.getClassName();
/* 156 */       int current = methodGen.getLocalIndex("current");
/*     */ 
/*     */       
/* 159 */       il.append(classGen.loadTranslet());
/* 160 */       if (classGen.isExternal()) {
/* 161 */         il.append((Instruction)new CHECKCAST(cpg.addClass(className)));
/*     */       }
/* 163 */       il.append((Instruction)InstructionConstants.DUP);
/* 164 */       il.append((Instruction)new GETFIELD(cpg.addFieldref(className, "_dom", "Lorg/apache/xalan/xsltc/DOM;")));
/*     */ 
/*     */ 
/*     */       
/* 168 */       int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.StringValueHandler", "<init>", "()V");
/* 169 */       il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.runtime.StringValueHandler")));
/* 170 */       il.append((Instruction)InstructionConstants.DUP);
/* 171 */       il.append((Instruction)InstructionConstants.DUP);
/* 172 */       il.append((Instruction)new INVOKESPECIAL(index));
/*     */ 
/*     */       
/* 175 */       LocalVariableGen handler = methodGen.addLocalVariable("rt_to_string_handler", Util.getJCRefType("Lorg/apache/xalan/xsltc/runtime/StringValueHandler;"), null, null);
/*     */ 
/*     */ 
/*     */       
/* 179 */       il.append((Instruction)new ASTORE(handler.getIndex()));
/*     */ 
/*     */       
/* 182 */       index = cpg.addMethodref(className, this._methodName, "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/serializer/SerializationHandler;)V");
/*     */       
/* 184 */       il.append((Instruction)new INVOKEVIRTUAL(index));
/*     */ 
/*     */       
/* 187 */       il.append((Instruction)new ALOAD(handler.getIndex()));
/* 188 */       index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.StringValueHandler", "getValue", "()Ljava/lang/String;");
/*     */ 
/*     */       
/* 191 */       il.append((Instruction)new INVOKEVIRTUAL(index));
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
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, RealType type) {
/* 206 */     translateTo(classGen, methodGen, Type.String);
/* 207 */     Type.String.translateTo(classGen, methodGen, Type.Real);
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
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, ReferenceType type) {
/* 221 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 222 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 224 */     if (this._methodName == null) {
/* 225 */       il.append(InstructionConstants.NOP);
/*     */     }
/*     */     else {
/*     */       
/* 229 */       String className = classGen.getClassName();
/* 230 */       int current = methodGen.getLocalIndex("current");
/*     */ 
/*     */       
/* 233 */       il.append(classGen.loadTranslet());
/* 234 */       if (classGen.isExternal()) {
/* 235 */         il.append((Instruction)new CHECKCAST(cpg.addClass(className)));
/*     */       }
/* 237 */       il.append(methodGen.loadDOM());
/*     */ 
/*     */       
/* 240 */       il.append(methodGen.loadDOM());
/* 241 */       int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getResultTreeFrag", "(IZ)Lorg/apache/xalan/xsltc/DOM;");
/*     */ 
/*     */       
/* 244 */       il.append((CompoundInstruction)new PUSH(cpg, 32));
/* 245 */       il.append((CompoundInstruction)new PUSH(cpg, false));
/* 246 */       il.append((Instruction)new INVOKEINTERFACE(index, 3));
/* 247 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/* 250 */       LocalVariableGen newDom = methodGen.addLocalVariable("rt_to_reference_dom", Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), null, null);
/*     */ 
/*     */       
/* 253 */       il.append((Instruction)new CHECKCAST(cpg.addClass("Lorg/apache/xalan/xsltc/DOM;")));
/* 254 */       il.append((Instruction)new ASTORE(newDom.getIndex()));
/*     */ 
/*     */       
/* 257 */       index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getOutputDomBuilder", "()Lorg/apache/xml/serializer/SerializationHandler;");
/*     */ 
/*     */ 
/*     */       
/* 261 */       il.append((Instruction)new INVOKEINTERFACE(index, 1));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 266 */       il.append((Instruction)InstructionConstants.DUP);
/* 267 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/* 270 */       LocalVariableGen domBuilder = methodGen.addLocalVariable("rt_to_reference_handler", Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;"), null, null);
/*     */ 
/*     */ 
/*     */       
/* 274 */       il.append((Instruction)new ASTORE(domBuilder.getIndex()));
/*     */ 
/*     */       
/* 277 */       index = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "startDocument", "()V");
/*     */       
/* 279 */       il.append((Instruction)new INVOKEINTERFACE(index, 1));
/*     */ 
/*     */       
/* 282 */       index = cpg.addMethodref(className, this._methodName, "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/serializer/SerializationHandler;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 288 */       il.append((Instruction)new INVOKEVIRTUAL(index));
/*     */ 
/*     */       
/* 291 */       il.append((Instruction)new ALOAD(domBuilder.getIndex()));
/* 292 */       index = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "endDocument", "()V");
/*     */       
/* 294 */       il.append((Instruction)new INVOKEINTERFACE(index, 1));
/*     */ 
/*     */       
/* 297 */       il.append((Instruction)new ALOAD(newDom.getIndex()));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, NodeSetType type) {
/* 316 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 317 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 320 */     il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 325 */     il.append(classGen.loadTranslet());
/* 326 */     il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "namesArray", "[Ljava/lang/String;")));
/*     */ 
/*     */     
/* 329 */     il.append(classGen.loadTranslet());
/* 330 */     il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "urisArray", "[Ljava/lang/String;")));
/*     */ 
/*     */     
/* 333 */     il.append(classGen.loadTranslet());
/* 334 */     il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "typesArray", "[I")));
/*     */ 
/*     */     
/* 337 */     il.append(classGen.loadTranslet());
/* 338 */     il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "namespaceArray", "[Ljava/lang/String;")));
/*     */ 
/*     */ 
/*     */     
/* 342 */     int mapping = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "setupMapping", "([Ljava/lang/String;[Ljava/lang/String;[I[Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 348 */     il.append((Instruction)new INVOKEINTERFACE(mapping, 5));
/* 349 */     il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */     
/* 352 */     int iter = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getIterator", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */     
/* 355 */     il.append((Instruction)new INVOKEINTERFACE(iter, 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, ObjectType type) {
/* 365 */     methodGen.getInstructionList().append(InstructionConstants.NOP);
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
/*     */ 
/*     */   
/*     */   public FlowList translateToDesynthesized(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/* 381 */     InstructionList il = methodGen.getInstructionList();
/* 382 */     translateTo(classGen, methodGen, Type.Boolean);
/* 383 */     return new FlowList((InstructionHandle)il.append((BranchInstruction)new IFEQ(null)));
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 400 */     String className = clazz.getName();
/* 401 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 402 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 404 */     if (className.equals("org.w3c.dom.Node")) {
/* 405 */       translateTo(classGen, methodGen, Type.NodeSet);
/* 406 */       int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "makeNode", "(Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/w3c/dom/Node;");
/*     */ 
/*     */       
/* 409 */       il.append((Instruction)new INVOKEINTERFACE(index, 2));
/*     */     }
/* 411 */     else if (className.equals("org.w3c.dom.NodeList")) {
/* 412 */       translateTo(classGen, methodGen, Type.NodeSet);
/* 413 */       int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "makeNodeList", "(Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/w3c/dom/NodeList;");
/*     */ 
/*     */       
/* 416 */       il.append((Instruction)new INVOKEINTERFACE(index, 2));
/*     */     }
/* 418 */     else if (className.equals("java.lang.Object")) {
/* 419 */       il.append(InstructionConstants.NOP);
/*     */     }
/* 421 */     else if (className.equals("java.lang.String")) {
/* 422 */       translateTo(classGen, methodGen, Type.String);
/*     */     } else {
/*     */       
/* 425 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), className);
/*     */       
/* 427 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 436 */     translateTo(classGen, methodGen, Type.Reference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateUnBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 444 */     methodGen.getInstructionList().append(InstructionConstants.NOP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClassName() {
/* 451 */     return "org.apache.xalan.xsltc.DOM";
/*     */   }
/*     */   
/*     */   public Instruction LOAD(int slot) {
/* 455 */     return (Instruction)new ALOAD(slot);
/*     */   }
/*     */   
/*     */   public Instruction STORE(int slot) {
/* 459 */     return (Instruction)new ASTORE(slot);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/ResultTreeType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */