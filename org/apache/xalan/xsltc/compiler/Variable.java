/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.classfile.Field;
/*     */ import org.apache.bcel.generic.ACONST_NULL;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.DCONST;
/*     */ import org.apache.bcel.generic.ICONST;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUTFIELD;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
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
/*     */ final class Variable
/*     */   extends VariableBase
/*     */ {
/*     */   public int getIndex() {
/*  49 */     return (this._local != null) ? this._local.getIndex() : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  57 */     super.parseContents(parser);
/*     */ 
/*     */     
/*  60 */     SyntaxTreeNode parent = getParent();
/*  61 */     if (parent instanceof Stylesheet) {
/*     */       
/*  63 */       this._isLocal = false;
/*     */       
/*  65 */       Variable var = parser.getSymbolTable().lookupVariable(this._name);
/*     */       
/*  67 */       if (var != null) {
/*  68 */         int us = getImportPrecedence();
/*  69 */         int them = var.getImportPrecedence();
/*     */         
/*  71 */         if (us == them) {
/*  72 */           String name = this._name.toString();
/*  73 */           reportError(this, parser, "VARIABLE_REDEF_ERR", name);
/*     */         } else {
/*     */           
/*  76 */           if (them > us) {
/*  77 */             this._ignore = true;
/*     */             
/*     */             return;
/*     */           } 
/*  81 */           var.disable();
/*     */         } 
/*     */       } 
/*     */       
/*  85 */       ((Stylesheet)parent).addVariable(this);
/*  86 */       parser.getSymbolTable().addVariable(this);
/*     */     } else {
/*     */       
/*  89 */       this._isLocal = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 100 */     if (this._select != null) {
/* 101 */       this._type = this._select.typeCheck(stable);
/*     */     
/*     */     }
/* 104 */     else if (hasContents()) {
/* 105 */       typeCheckContents(stable);
/* 106 */       this._type = Type.ResultTree;
/*     */     } else {
/*     */       
/* 109 */       this._type = Type.Reference;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 114 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize(ClassGenerator classGen, MethodGenerator methodGen) {
/* 123 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 124 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 127 */     if (isLocal() && !this._refs.isEmpty()) {
/*     */       
/* 129 */       if (this._local == null) {
/* 130 */         this._local = methodGen.addLocalVariable2(getEscapedName(), this._type.toJCType(), il.getEnd());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 135 */       if (this._type instanceof org.apache.xalan.xsltc.compiler.util.IntType || this._type instanceof org.apache.xalan.xsltc.compiler.util.NodeType || this._type instanceof org.apache.xalan.xsltc.compiler.util.BooleanType) {
/*     */ 
/*     */         
/* 138 */         il.append((Instruction)new ICONST(0));
/* 139 */       } else if (this._type instanceof org.apache.xalan.xsltc.compiler.util.RealType) {
/* 140 */         il.append((Instruction)new DCONST(0.0D));
/*     */       } else {
/* 142 */         il.append((Instruction)new ACONST_NULL());
/* 143 */       }  il.append(this._type.STORE(this._local.getIndex()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 148 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 149 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 151 */     String name = getEscapedName();
/*     */ 
/*     */     
/* 154 */     if (this._ignore)
/* 155 */       return;  this._ignore = true;
/*     */     
/* 157 */     if (isLocal()) {
/*     */       
/* 159 */       translateValue(classGen, methodGen);
/*     */ 
/*     */       
/* 162 */       if (this._refs.isEmpty()) {
/* 163 */         il.append(this._type.POP());
/* 164 */         this._local = null;
/*     */       } else {
/*     */         
/* 167 */         if (this._local == null) mapRegister(methodGen); 
/* 168 */         il.append(this._type.STORE(this._local.getIndex()));
/*     */       } 
/*     */     } else {
/*     */       
/* 172 */       String signature = this._type.toSignature();
/*     */ 
/*     */       
/* 175 */       if (classGen.containsField(name) == null) {
/* 176 */         classGen.addField(new Field(1, cpg.addUtf8(name), cpg.addUtf8(signature), null, cpg.getConstantPool()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 182 */         il.append(classGen.loadTranslet());
/*     */         
/* 184 */         translateValue(classGen, methodGen);
/*     */         
/* 186 */         il.append((Instruction)new PUTFIELD(cpg.addFieldref(classGen.getClassName(), name, signature)));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Variable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */