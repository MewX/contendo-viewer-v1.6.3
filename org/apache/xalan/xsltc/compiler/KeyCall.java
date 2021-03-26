/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFGT;
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
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
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
/*     */ final class KeyCall
/*     */   extends FunctionCall
/*     */ {
/*     */   private Expression _name;
/*     */   private Expression _value;
/*     */   private Type _valueType;
/*  69 */   private QName _resolvedQName = null;
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
/*     */   public KeyCall(QName fname, Vector arguments) {
/*  84 */     super(fname, arguments);
/*  85 */     switch (argumentCount()) {
/*     */       case 1:
/*  87 */         this._name = null;
/*  88 */         this._value = argument(0);
/*     */         return;
/*     */       case 2:
/*  91 */         this._name = argument(0);
/*  92 */         this._value = argument(1);
/*     */         return;
/*     */     } 
/*  95 */     this._name = this._value = null;
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
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 108 */     Type returnType = super.typeCheck(stable);
/*     */ 
/*     */ 
/*     */     
/* 112 */     if (this._name != null) {
/* 113 */       Type nameType = this._name.typeCheck(stable);
/*     */       
/* 115 */       if (this._name instanceof LiteralExpr) {
/* 116 */         LiteralExpr literal = (LiteralExpr)this._name;
/* 117 */         this._resolvedQName = getParser().getQNameIgnoreDefaultNs(literal.getValue());
/*     */       
/*     */       }
/* 120 */       else if (!(nameType instanceof org.apache.xalan.xsltc.compiler.util.StringType)) {
/* 121 */         this._name = new CastExpr(this._name, Type.String);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     this._valueType = this._value.typeCheck(stable);
/*     */     
/* 134 */     if (this._valueType != Type.NodeSet && this._valueType != Type.String)
/*     */     {
/* 136 */       this._value = new CastExpr(this._value, Type.String);
/*     */     }
/*     */     
/* 139 */     return returnType;
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
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 155 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 156 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 158 */     int getNodeHandle = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeHandle", "(I)I");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     int dupInit = cpg.addMethodref("org.apache.xalan.xsltc.dom.DupFilterIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;)V");
/*     */ 
/*     */ 
/*     */     
/* 168 */     il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.DupFilterIterator")));
/* 169 */     il.append((Instruction)InstructionConstants.DUP);
/* 170 */     translateCall(classGen, methodGen);
/* 171 */     il.append((Instruction)new INVOKESPECIAL(dupInit));
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
/*     */   private void translateCall(ClassGenerator classGen, MethodGenerator methodGen) {
/* 183 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 184 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 187 */     int getNodeValue = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getStringValueX", "(I)Ljava/lang/String;");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     int getKeyIndex = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "getKeyIndex", "(Ljava/lang/String;)Lorg/apache/xalan/xsltc/dom/KeyIndex;");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     int lookupId = cpg.addMethodref("org/apache/xalan/xsltc/dom/KeyIndex", "lookupId", "(Ljava/lang/Object;)V");
/*     */ 
/*     */     
/* 201 */     int lookupKey = cpg.addMethodref("org/apache/xalan/xsltc/dom/KeyIndex", "lookupKey", "(Ljava/lang/Object;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     int merge = cpg.addMethodref("org/apache/xalan/xsltc/dom/KeyIndex", "merge", "(Lorg/apache/xalan/xsltc/dom/KeyIndex;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 211 */     int indexConstructor = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "createKeyIndex", "()Lorg/apache/xalan/xsltc/dom/KeyIndex;");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     int keyDom = cpg.addMethodref("org.apache.xalan.xsltc.dom.KeyIndex", "setDom", "(Lorg/apache/xalan/xsltc/DOM;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 222 */     LocalVariableGen returnIndex = methodGen.addLocalVariable("returnIndex", Util.getJCRefType("Lorg/apache/xalan/xsltc/dom/KeyIndex;"), il.getEnd(), null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 228 */     LocalVariableGen searchIndex = methodGen.addLocalVariable("searchIndex", Util.getJCRefType("Lorg/apache/xalan/xsltc/dom/KeyIndex;"), il.getEnd(), null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 236 */     if (this._valueType == Type.NodeSet) {
/*     */       
/* 238 */       il.append(methodGen.loadCurrentNode());
/* 239 */       il.append(methodGen.loadIterator());
/*     */ 
/*     */       
/* 242 */       this._value.translate(classGen, methodGen);
/* 243 */       this._value.startIterator(classGen, methodGen);
/* 244 */       il.append(methodGen.storeIterator());
/*     */ 
/*     */       
/* 247 */       il.append(classGen.loadTranslet());
/* 248 */       il.append((Instruction)new INVOKEVIRTUAL(indexConstructor));
/* 249 */       il.append((Instruction)InstructionConstants.DUP);
/* 250 */       il.append(methodGen.loadDOM());
/* 251 */       il.append((Instruction)new INVOKEVIRTUAL(keyDom));
/* 252 */       il.append((Instruction)new ASTORE(returnIndex.getIndex()));
/*     */ 
/*     */       
/* 255 */       il.append(classGen.loadTranslet());
/* 256 */       if (this._name == null) {
/* 257 */         il.append((CompoundInstruction)new PUSH(cpg, "##id"));
/*     */       }
/* 259 */       else if (this._resolvedQName != null) {
/* 260 */         il.append((CompoundInstruction)new PUSH(cpg, this._resolvedQName.toString()));
/*     */       } else {
/*     */         
/* 263 */         this._name.translate(classGen, methodGen);
/*     */       } 
/*     */       
/* 266 */       il.append((Instruction)new INVOKEVIRTUAL(getKeyIndex));
/* 267 */       il.append((Instruction)new ASTORE(searchIndex.getIndex()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 273 */       BranchHandle nextNode = il.append((BranchInstruction)new GOTO(null));
/* 274 */       InstructionHandle loop = il.append(InstructionConstants.NOP);
/*     */ 
/*     */       
/* 277 */       il.append((Instruction)new ALOAD(returnIndex.getIndex()));
/*     */ 
/*     */       
/* 280 */       il.append((Instruction)new ALOAD(searchIndex.getIndex()));
/* 281 */       il.append((Instruction)InstructionConstants.DUP);
/* 282 */       il.append(methodGen.loadDOM());
/* 283 */       il.append(methodGen.loadCurrentNode());
/* 284 */       il.append((Instruction)new INVOKEINTERFACE(getNodeValue, 2));
/* 285 */       if (this._name == null) {
/* 286 */         il.append((Instruction)new INVOKEVIRTUAL(lookupId));
/*     */       } else {
/*     */         
/* 289 */         il.append((Instruction)new INVOKEVIRTUAL(lookupKey));
/*     */       } 
/*     */ 
/*     */       
/* 293 */       il.append((Instruction)new INVOKEVIRTUAL(merge));
/*     */ 
/*     */       
/* 296 */       nextNode.setTarget(il.append(methodGen.loadIterator()));
/* 297 */       il.append(methodGen.nextNode());
/* 298 */       il.append((Instruction)InstructionConstants.DUP);
/* 299 */       il.append(methodGen.storeCurrentNode());
/* 300 */       il.append((BranchInstruction)new IFGT(loop));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 305 */       il.append(methodGen.storeIterator());
/* 306 */       il.append(methodGen.storeCurrentNode());
/*     */ 
/*     */       
/* 309 */       il.append((Instruction)new ALOAD(returnIndex.getIndex()));
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 316 */       il.append(classGen.loadTranslet());
/* 317 */       if (this._name == null) {
/* 318 */         il.append((CompoundInstruction)new PUSH(cpg, "##id"));
/*     */       }
/* 320 */       else if (this._resolvedQName != null) {
/* 321 */         il.append((CompoundInstruction)new PUSH(cpg, this._resolvedQName.toString()));
/*     */       } else {
/*     */         
/* 324 */         this._name.translate(classGen, methodGen);
/*     */       } 
/* 326 */       il.append((Instruction)new INVOKEVIRTUAL(getKeyIndex));
/*     */ 
/*     */ 
/*     */       
/* 330 */       il.append((Instruction)InstructionConstants.DUP);
/*     */       
/* 332 */       this._value.translate(classGen, methodGen);
/*     */       
/* 334 */       if (this._name == null) {
/* 335 */         il.append((Instruction)new INVOKEVIRTUAL(lookupId));
/*     */       } else {
/*     */         
/* 338 */         il.append((Instruction)new INVOKEVIRTUAL(lookupKey));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/KeyCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */