/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.IFGE;
/*     */ import org.apache.bcel.generic.IFGT;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
/*     */ import org.apache.xml.utils.XMLChar;
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
/*     */ final class Key
/*     */   extends TopLevelElement
/*     */ {
/*     */   private QName _name;
/*     */   private Pattern _match;
/*     */   private Expression _use;
/*     */   private Type _useType;
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  80 */     String name = getAttribute("name");
/*  81 */     if (!XMLChar.isValidQName(name)) {
/*  82 */       ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", name, this);
/*  83 */       parser.reportError(3, err);
/*     */     } 
/*  85 */     this._name = parser.getQNameIgnoreDefaultNs(name);
/*  86 */     this._match = parser.parsePattern(this, "match", null);
/*  87 */     this._use = parser.parseExpression(this, "use", null);
/*     */ 
/*     */     
/*  90 */     if (this._name == null) {
/*  91 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "name");
/*     */       return;
/*     */     } 
/*  94 */     if (this._match.isDummy()) {
/*  95 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "match");
/*     */       return;
/*     */     } 
/*  98 */     if (this._use.isDummy()) {
/*  99 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "use");
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 109 */     return this._name.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 114 */     this._match.typeCheck(stable);
/*     */ 
/*     */     
/* 117 */     this._useType = this._use.typeCheck(stable);
/* 118 */     if (!(this._useType instanceof org.apache.xalan.xsltc.compiler.util.StringType) && !(this._useType instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType))
/*     */     {
/*     */       
/* 121 */       this._use = new CastExpr(this._use, Type.String);
/*     */     }
/*     */     
/* 124 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void traverseNodeSet(ClassGenerator classGen, MethodGenerator methodGen, int buildKeyIndex) {
/* 135 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 136 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 139 */     int getNodeValue = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getStringValueX", "(I)Ljava/lang/String;");
/*     */ 
/*     */ 
/*     */     
/* 143 */     int getNodeIdent = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeIdent", "(I)I");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     int keyDom = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "setKeyIndexDom", "(Ljava/lang/String;Lorg/apache/xalan/xsltc/DOM;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     LocalVariableGen parentNode = methodGen.addLocalVariable("parentNode", Util.getJCRefType("I"), il.getEnd(), null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     il.append((Instruction)new ISTORE(parentNode.getIndex()));
/* 163 */     il.append(methodGen.loadDOM());
/* 164 */     il.append((Instruction)new ILOAD(parentNode.getIndex()));
/* 165 */     il.append((Instruction)new INVOKEINTERFACE(getNodeIdent, 2));
/* 166 */     il.append((Instruction)new ISTORE(parentNode.getIndex()));
/*     */ 
/*     */     
/* 169 */     il.append(methodGen.loadCurrentNode());
/* 170 */     il.append(methodGen.loadIterator());
/*     */ 
/*     */     
/* 173 */     this._use.translate(classGen, methodGen);
/* 174 */     this._use.startIterator(classGen, methodGen);
/* 175 */     il.append(methodGen.storeIterator());
/*     */     
/* 177 */     BranchHandle nextNode = il.append((BranchInstruction)new GOTO(null));
/* 178 */     InstructionHandle loop = il.append(InstructionConstants.NOP);
/*     */ 
/*     */     
/* 181 */     il.append(classGen.loadTranslet());
/* 182 */     il.append((CompoundInstruction)new PUSH(cpg, this._name.toString()));
/* 183 */     il.append((Instruction)new ILOAD(parentNode.getIndex()));
/*     */ 
/*     */     
/* 186 */     il.append(methodGen.loadDOM());
/* 187 */     il.append(methodGen.loadCurrentNode());
/* 188 */     il.append((Instruction)new INVOKEINTERFACE(getNodeValue, 2));
/*     */ 
/*     */     
/* 191 */     il.append((Instruction)new INVOKEVIRTUAL(buildKeyIndex));
/*     */     
/* 193 */     il.append(classGen.loadTranslet());
/* 194 */     il.append((CompoundInstruction)new PUSH(cpg, getName()));
/* 195 */     il.append(methodGen.loadDOM());
/* 196 */     il.append((Instruction)new INVOKEVIRTUAL(keyDom));
/*     */     
/* 198 */     nextNode.setTarget(il.append(methodGen.loadIterator()));
/* 199 */     il.append(methodGen.nextNode());
/*     */     
/* 201 */     il.append((Instruction)InstructionConstants.DUP);
/* 202 */     il.append(methodGen.storeCurrentNode());
/* 203 */     il.append((BranchInstruction)new IFGE(loop));
/*     */ 
/*     */     
/* 206 */     il.append(methodGen.storeIterator());
/* 207 */     il.append(methodGen.storeCurrentNode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 216 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 217 */     InstructionList il = methodGen.getInstructionList();
/* 218 */     int current = methodGen.getLocalIndex("current");
/*     */ 
/*     */     
/* 221 */     int key = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "buildKeyIndex", "(Ljava/lang/String;ILjava/lang/Object;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 226 */     int keyDom = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "setKeyIndexDom", "(Ljava/lang/String;Lorg/apache/xalan/xsltc/DOM;)V");
/*     */ 
/*     */ 
/*     */     
/* 230 */     int getNodeIdent = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeIdent", "(I)I");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 235 */     int git = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getAxisIterator", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */ 
/*     */     
/* 239 */     il.append(methodGen.loadCurrentNode());
/* 240 */     il.append(methodGen.loadIterator());
/*     */ 
/*     */     
/* 243 */     il.append(methodGen.loadDOM());
/* 244 */     il.append((CompoundInstruction)new PUSH(cpg, 4));
/* 245 */     il.append((Instruction)new INVOKEINTERFACE(git, 2));
/*     */ 
/*     */     
/* 248 */     il.append(methodGen.loadCurrentNode());
/* 249 */     il.append(methodGen.setStartNode());
/* 250 */     il.append(methodGen.storeIterator());
/*     */ 
/*     */     
/* 253 */     BranchHandle nextNode = il.append((BranchInstruction)new GOTO(null));
/* 254 */     InstructionHandle loop = il.append(InstructionConstants.NOP);
/*     */ 
/*     */     
/* 257 */     il.append(methodGen.loadCurrentNode());
/* 258 */     this._match.translate(classGen, methodGen);
/* 259 */     this._match.synthesize(classGen, methodGen);
/* 260 */     BranchHandle skipNode = il.append((BranchInstruction)new IFEQ(null));
/*     */ 
/*     */     
/* 263 */     if (this._useType instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType) {
/*     */       
/* 265 */       il.append(methodGen.loadCurrentNode());
/* 266 */       traverseNodeSet(classGen, methodGen, key);
/*     */     } else {
/*     */       
/* 269 */       il.append(classGen.loadTranslet());
/* 270 */       il.append((Instruction)InstructionConstants.DUP);
/* 271 */       il.append((CompoundInstruction)new PUSH(cpg, this._name.toString()));
/* 272 */       il.append((Instruction)InstructionConstants.DUP_X1);
/* 273 */       il.append(methodGen.loadCurrentNode());
/* 274 */       this._use.translate(classGen, methodGen);
/* 275 */       il.append((Instruction)InstructionConstants.SWAP);
/* 276 */       il.append(methodGen.loadDOM());
/* 277 */       il.append((Instruction)InstructionConstants.SWAP);
/* 278 */       il.append((Instruction)new INVOKEINTERFACE(getNodeIdent, 2));
/* 279 */       il.append((Instruction)InstructionConstants.SWAP);
/* 280 */       il.append((Instruction)new INVOKEVIRTUAL(key));
/*     */       
/* 282 */       il.append(methodGen.loadDOM());
/* 283 */       il.append((Instruction)new INVOKEVIRTUAL(keyDom));
/*     */     } 
/*     */ 
/*     */     
/* 287 */     InstructionHandle skip = il.append(InstructionConstants.NOP);
/*     */     
/* 289 */     il.append(methodGen.loadIterator());
/* 290 */     il.append(methodGen.nextNode());
/* 291 */     il.append((Instruction)InstructionConstants.DUP);
/* 292 */     il.append(methodGen.storeCurrentNode());
/* 293 */     il.append((BranchInstruction)new IFGT(loop));
/*     */ 
/*     */     
/* 296 */     il.append(methodGen.storeIterator());
/* 297 */     il.append(methodGen.storeCurrentNode());
/*     */     
/* 299 */     nextNode.setTarget(skip);
/* 300 */     skipNode.setTarget(skip);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Key.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */