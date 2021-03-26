/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.classfile.Field;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.IFNONNULL;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.PUTFIELD;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Param
/*     */   extends VariableBase
/*     */ {
/*     */   private boolean _isInSimpleNamedTemplate = false;
/*     */   
/*     */   public String toString() {
/*  61 */     return "param(" + this._name + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction setLoadInstruction(Instruction instruction) {
/*  69 */     Instruction tmp = this._loadInstruction;
/*  70 */     this._loadInstruction = instruction;
/*  71 */     return tmp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction setStoreInstruction(Instruction instruction) {
/*  79 */     Instruction tmp = this._storeInstruction;
/*  80 */     this._storeInstruction = instruction;
/*  81 */     return tmp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void display(int indent) {
/*  88 */     indent(indent);
/*  89 */     System.out.println("param " + this._name);
/*  90 */     if (this._select != null) {
/*  91 */       indent(indent + 4);
/*  92 */       System.out.println("select " + this._select.toString());
/*     */     } 
/*  94 */     displayContents(indent + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/* 104 */     super.parseContents(parser);
/*     */ 
/*     */     
/* 107 */     SyntaxTreeNode parent = getParent();
/* 108 */     if (parent instanceof Stylesheet) {
/*     */       
/* 110 */       this._isLocal = false;
/*     */       
/* 112 */       Param param = parser.getSymbolTable().lookupParam(this._name);
/*     */       
/* 114 */       if (param != null) {
/* 115 */         int us = getImportPrecedence();
/* 116 */         int them = param.getImportPrecedence();
/*     */         
/* 118 */         if (us == them) {
/* 119 */           String name = this._name.toString();
/* 120 */           reportError(this, parser, "VARIABLE_REDEF_ERR", name);
/*     */         } else {
/*     */           
/* 123 */           if (them > us) {
/* 124 */             this._ignore = true;
/*     */             
/*     */             return;
/*     */           } 
/* 128 */           param.disable();
/*     */         } 
/*     */       } 
/*     */       
/* 132 */       ((Stylesheet)parent).addParam(this);
/* 133 */       parser.getSymbolTable().addParam(this);
/*     */     }
/* 135 */     else if (parent instanceof Template) {
/* 136 */       Template template = (Template)parent;
/* 137 */       this._isLocal = true;
/* 138 */       template.addParameter(this);
/* 139 */       if (template.isSimpleNamedTemplate()) {
/* 140 */         this._isInSimpleNamedTemplate = true;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 151 */     if (this._select != null) {
/* 152 */       this._type = this._select.typeCheck(stable);
/* 153 */       if (!(this._type instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType) && !(this._type instanceof org.apache.xalan.xsltc.compiler.util.ObjectType)) {
/* 154 */         this._select = new CastExpr(this._select, Type.Reference);
/*     */       }
/*     */     }
/* 157 */     else if (hasContents()) {
/* 158 */       typeCheckContents(stable);
/*     */     } 
/* 160 */     this._type = Type.Reference;
/*     */ 
/*     */ 
/*     */     
/* 164 */     return Type.Void;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 168 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 169 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 171 */     if (this._ignore)
/* 172 */       return;  this._ignore = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 179 */     String name = BasisLibrary.mapQNameToJavaName(this._name.toString());
/* 180 */     String signature = this._type.toSignature();
/* 181 */     String className = this._type.getClassName();
/*     */     
/* 183 */     if (isLocal()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 189 */       if (this._isInSimpleNamedTemplate) {
/* 190 */         il.append(loadInstruction());
/* 191 */         BranchHandle ifBlock = il.append((BranchInstruction)new IFNONNULL(null));
/* 192 */         translateValue(classGen, methodGen);
/* 193 */         il.append(storeInstruction());
/* 194 */         ifBlock.setTarget(il.append(InstructionConstants.NOP));
/*     */         
/*     */         return;
/*     */       } 
/* 198 */       il.append(classGen.loadTranslet());
/* 199 */       il.append((CompoundInstruction)new PUSH(cpg, name));
/* 200 */       translateValue(classGen, methodGen);
/* 201 */       il.append((CompoundInstruction)new PUSH(cpg, true));
/*     */ 
/*     */       
/* 204 */       il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addParameter", "(Ljava/lang/String;Ljava/lang/Object;Z)Ljava/lang/Object;")));
/*     */ 
/*     */       
/* 207 */       if (className != "") {
/* 208 */         il.append((Instruction)new CHECKCAST(cpg.addClass(className)));
/*     */       }
/*     */       
/* 211 */       this._type.translateUnBox(classGen, methodGen);
/*     */       
/* 213 */       if (this._refs.isEmpty()) {
/* 214 */         il.append(this._type.POP());
/* 215 */         this._local = null;
/*     */       } else {
/*     */         
/* 218 */         this._local = methodGen.addLocalVariable2(name, this._type.toJCType(), il.getEnd());
/*     */ 
/*     */ 
/*     */         
/* 222 */         il.append(this._type.STORE(this._local.getIndex()));
/*     */       }
/*     */     
/*     */     }
/* 226 */     else if (classGen.containsField(name) == null) {
/* 227 */       classGen.addField(new Field(1, cpg.addUtf8(name), cpg.addUtf8(signature), null, cpg.getConstantPool()));
/*     */ 
/*     */       
/* 230 */       il.append(classGen.loadTranslet());
/* 231 */       il.append((Instruction)InstructionConstants.DUP);
/* 232 */       il.append((CompoundInstruction)new PUSH(cpg, name));
/* 233 */       translateValue(classGen, methodGen);
/* 234 */       il.append((CompoundInstruction)new PUSH(cpg, true));
/*     */ 
/*     */       
/* 237 */       il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addParameter", "(Ljava/lang/String;Ljava/lang/Object;Z)Ljava/lang/Object;")));
/*     */ 
/*     */ 
/*     */       
/* 241 */       this._type.translateUnBox(classGen, methodGen);
/*     */ 
/*     */       
/* 244 */       if (className != "") {
/* 245 */         il.append((Instruction)new CHECKCAST(cpg.addClass(className)));
/*     */       }
/* 247 */       il.append((Instruction)new PUTFIELD(cpg.addFieldref(classGen.getClassName(), name, signature)));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Param.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */