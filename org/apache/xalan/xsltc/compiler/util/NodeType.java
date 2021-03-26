/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETFIELD;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
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
/*     */ public final class NodeType
/*     */   extends Type
/*     */ {
/*     */   private final int _type;
/*     */   
/*     */   protected NodeType() {
/*  48 */     this(-1);
/*     */   }
/*     */   
/*     */   protected NodeType(int type) {
/*  52 */     this._type = type;
/*     */   }
/*     */   
/*     */   public int getType() {
/*  56 */     return this._type;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  60 */     return "node-type";
/*     */   }
/*     */   
/*     */   public boolean identicalTo(Type other) {
/*  64 */     return other instanceof NodeType;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  68 */     return this._type;
/*     */   }
/*     */   
/*     */   public String toSignature() {
/*  72 */     return "I";
/*     */   }
/*     */   
/*     */   public Type toJCType() {
/*  76 */     return (Type)Type.INT;
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
/*  88 */     if (type == Type.String) {
/*  89 */       translateTo(classGen, methodGen, (StringType)type);
/*     */     }
/*  91 */     else if (type == Type.Boolean) {
/*  92 */       translateTo(classGen, methodGen, (BooleanType)type);
/*     */     }
/*  94 */     else if (type == Type.Real) {
/*  95 */       translateTo(classGen, methodGen, (RealType)type);
/*     */     }
/*  97 */     else if (type == Type.NodeSet) {
/*  98 */       translateTo(classGen, methodGen, (NodeSetType)type);
/*     */     }
/* 100 */     else if (type == Type.Reference) {
/* 101 */       translateTo(classGen, methodGen, (ReferenceType)type);
/*     */     }
/* 103 */     else if (type == Type.Object) {
/* 104 */       translateTo(classGen, methodGen, (ObjectType)type);
/*     */     } else {
/*     */       
/* 107 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*     */       
/* 109 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, StringType type) {
/*     */     int index;
/* 120 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 121 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 123 */     switch (this._type) {
/*     */       case 1:
/*     */       case 9:
/* 126 */         il.append(methodGen.loadDOM());
/* 127 */         il.append((Instruction)InstructionConstants.SWAP);
/* 128 */         index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getElementValue", "(I)Ljava/lang/String;");
/*     */ 
/*     */         
/* 131 */         il.append((Instruction)new INVOKEINTERFACE(index, 2));
/*     */         return;
/*     */       
/*     */       case -1:
/*     */       case 2:
/*     */       case 7:
/*     */       case 8:
/* 138 */         il.append(methodGen.loadDOM());
/* 139 */         il.append((Instruction)InstructionConstants.SWAP);
/* 140 */         index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getStringValueX", "(I)Ljava/lang/String;");
/*     */ 
/*     */         
/* 143 */         il.append((Instruction)new INVOKEINTERFACE(index, 2));
/*     */         return;
/*     */     } 
/*     */     
/* 147 */     ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString());
/*     */     
/* 149 */     classGen.getParser().reportError(2, err);
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
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, BooleanType type) {
/* 164 */     InstructionList il = methodGen.getInstructionList();
/* 165 */     FlowList falsel = translateToDesynthesized(classGen, methodGen, type);
/* 166 */     il.append(InstructionConstants.ICONST_1);
/* 167 */     BranchHandle truec = il.append((BranchInstruction)new GOTO(null));
/* 168 */     falsel.backPatch(il.append(InstructionConstants.ICONST_0));
/* 169 */     truec.setTarget(il.append(InstructionConstants.NOP));
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
/* 180 */     translateTo(classGen, methodGen, Type.String);
/* 181 */     Type.String.translateTo(classGen, methodGen, Type.Real);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, NodeSetType type) {
/* 192 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 193 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 196 */     il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.SingletonIterator")));
/* 197 */     il.append((Instruction)InstructionConstants.DUP_X1);
/* 198 */     il.append((Instruction)InstructionConstants.SWAP);
/* 199 */     int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.SingletonIterator", "<init>", "(I)V");
/*     */     
/* 201 */     il.append((Instruction)new INVOKESPECIAL(init));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, ObjectType type) {
/* 211 */     methodGen.getInstructionList().append(InstructionConstants.NOP);
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
/* 224 */     InstructionList il = methodGen.getInstructionList();
/* 225 */     return new FlowList((InstructionHandle)il.append((BranchInstruction)new IFEQ(null)));
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
/* 236 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 237 */     InstructionList il = methodGen.getInstructionList();
/* 238 */     il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.runtime.Node")));
/* 239 */     il.append((Instruction)InstructionConstants.DUP_X1);
/* 240 */     il.append((Instruction)InstructionConstants.SWAP);
/* 241 */     il.append((CompoundInstruction)new PUSH(cpg, this._type));
/* 242 */     il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.Node", "<init>", "(II)V")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateTo(ClassGenerator classGen, MethodGenerator methodGen, Class clazz) {
/* 253 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 254 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 256 */     String className = clazz.getName();
/* 257 */     if (className.equals("java.lang.String")) {
/* 258 */       translateTo(classGen, methodGen, Type.String);
/*     */       
/*     */       return;
/*     */     } 
/* 262 */     il.append(methodGen.loadDOM());
/* 263 */     il.append((Instruction)InstructionConstants.SWAP);
/*     */     
/* 265 */     if (className.equals("org.w3c.dom.Node") || className.equals("java.lang.Object")) {
/*     */       
/* 267 */       int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "makeNode", "(I)Lorg/w3c/dom/Node;");
/*     */ 
/*     */       
/* 270 */       il.append((Instruction)new INVOKEINTERFACE(index, 2));
/*     */     }
/* 272 */     else if (className.equals("org.w3c.dom.NodeList")) {
/* 273 */       int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "makeNodeList", "(I)Lorg/w3c/dom/NodeList;");
/*     */ 
/*     */       
/* 276 */       il.append((Instruction)new INVOKEINTERFACE(index, 2));
/*     */     } else {
/*     */       
/* 279 */       ErrorMsg err = new ErrorMsg("DATA_CONVERSION_ERR", toString(), className);
/*     */       
/* 281 */       classGen.getParser().reportError(2, err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 290 */     translateTo(classGen, methodGen, Type.Reference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateUnBox(ClassGenerator classGen, MethodGenerator methodGen) {
/* 298 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 299 */     InstructionList il = methodGen.getInstructionList();
/* 300 */     il.append((Instruction)new CHECKCAST(cpg.addClass("org.apache.xalan.xsltc.runtime.Node")));
/* 301 */     il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.Node", "node", "I")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClassName() {
/* 310 */     return "org.apache.xalan.xsltc.runtime.Node";
/*     */   }
/*     */   
/*     */   public Instruction LOAD(int slot) {
/* 314 */     return (Instruction)new ILOAD(slot);
/*     */   }
/*     */   
/*     */   public Instruction STORE(int slot) {
/* 318 */     return (Instruction)new ISTORE(slot);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/NodeType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */