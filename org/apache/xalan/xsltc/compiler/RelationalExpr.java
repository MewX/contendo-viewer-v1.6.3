/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodType;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.runtime.Operators;
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
/*     */ final class RelationalExpr
/*     */   extends Expression
/*     */   implements Operators
/*     */ {
/*     */   private int _op;
/*     */   private Expression _left;
/*     */   private Expression _right;
/*     */   
/*     */   public RelationalExpr(int op, Expression left, Expression right) {
/*  51 */     this._op = op;
/*  52 */     (this._left = left).setParent(this);
/*  53 */     (this._right = right).setParent(this);
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  57 */     super.setParser(parser);
/*  58 */     this._left.setParser(parser);
/*  59 */     this._right.setParser(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPositionCall() {
/*  67 */     if (this._left.hasPositionCall()) return true; 
/*  68 */     if (this._right.hasPositionCall()) return true; 
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasLastCall() {
/*  76 */     return (this._left.hasLastCall() || this._right.hasLastCall());
/*     */   }
/*     */   
/*     */   public boolean hasReferenceArgs() {
/*  80 */     return (this._left.getType() instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType || this._right.getType() instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasNodeArgs() {
/*  85 */     return (this._left.getType() instanceof org.apache.xalan.xsltc.compiler.util.NodeType || this._right.getType() instanceof org.apache.xalan.xsltc.compiler.util.NodeType);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasNodeSetArgs() {
/*  90 */     return (this._left.getType() instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType || this._right.getType() instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType);
/*     */   }
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  95 */     Type tleft = this._left.typeCheck(stable);
/*  96 */     Type tright = this._right.typeCheck(stable);
/*     */ 
/*     */     
/*  99 */     if (tleft instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType && tright instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/*     */ 
/*     */       
/* 102 */       this._right = new CastExpr(this._right, Type.Real);
/* 103 */       this._left = new CastExpr(this._left, Type.Real);
/* 104 */       return this._type = Type.Boolean;
/*     */     } 
/*     */ 
/*     */     
/* 108 */     if (hasReferenceArgs()) {
/* 109 */       Type type = null;
/* 110 */       Type typeL = null;
/* 111 */       Type typeR = null;
/* 112 */       if (tleft instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType && 
/* 113 */         this._left instanceof VariableRefBase) {
/* 114 */         VariableRefBase ref = (VariableRefBase)this._left;
/* 115 */         VariableBase var = ref.getVariable();
/* 116 */         typeL = var.getType();
/*     */       } 
/*     */       
/* 119 */       if (tright instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType && 
/* 120 */         this._right instanceof VariableRefBase) {
/* 121 */         VariableRefBase ref = (VariableRefBase)this._right;
/* 122 */         VariableBase var = ref.getVariable();
/* 123 */         typeR = var.getType();
/*     */       } 
/*     */ 
/*     */       
/* 127 */       if (typeL == null) {
/* 128 */         type = typeR;
/* 129 */       } else if (typeR == null) {
/* 130 */         type = typeL;
/*     */       } else {
/* 132 */         type = Type.Real;
/*     */       } 
/* 134 */       if (type == null) type = Type.Real;
/*     */       
/* 136 */       this._right = new CastExpr(this._right, type);
/* 137 */       this._left = new CastExpr(this._left, type);
/* 138 */       return this._type = Type.Boolean;
/*     */     } 
/*     */     
/* 141 */     if (hasNodeSetArgs()) {
/*     */       
/* 143 */       if (tright instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType) {
/* 144 */         Expression temp = this._right; this._right = this._left; this._left = temp;
/* 145 */         this._op = (this._op == 2) ? 3 : ((this._op == 3) ? 2 : ((this._op == 4) ? 5 : 4));
/*     */ 
/*     */         
/* 148 */         tright = this._right.getType();
/*     */       } 
/*     */ 
/*     */       
/* 152 */       if (tright instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/* 153 */         this._right = new CastExpr(this._right, Type.NodeSet);
/*     */       }
/*     */       
/* 156 */       if (tright instanceof org.apache.xalan.xsltc.compiler.util.IntType) {
/* 157 */         this._right = new CastExpr(this._right, Type.Real);
/*     */       }
/*     */       
/* 160 */       if (tright instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/* 161 */         this._right = new CastExpr(this._right, Type.String);
/*     */       }
/* 163 */       return this._type = Type.Boolean;
/*     */     } 
/*     */ 
/*     */     
/* 167 */     if (hasNodeArgs()) {
/* 168 */       if (tleft instanceof org.apache.xalan.xsltc.compiler.util.BooleanType) {
/* 169 */         this._right = new CastExpr(this._right, Type.Boolean);
/* 170 */         tright = Type.Boolean;
/*     */       } 
/* 172 */       if (tright instanceof org.apache.xalan.xsltc.compiler.util.BooleanType) {
/* 173 */         this._left = new CastExpr(this._left, Type.Boolean);
/* 174 */         tleft = Type.Boolean;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 179 */     MethodType ptype = lookupPrimop(stable, Operators.names[this._op], new MethodType(Type.Void, tleft, tright));
/*     */ 
/*     */ 
/*     */     
/* 183 */     if (ptype != null) {
/* 184 */       Type arg1 = ptype.argsType().elementAt(0);
/* 185 */       if (!arg1.identicalTo(tleft)) {
/* 186 */         this._left = new CastExpr(this._left, arg1);
/*     */       }
/* 188 */       Type arg2 = ptype.argsType().elementAt(1);
/* 189 */       if (!arg2.identicalTo(tright)) {
/* 190 */         this._right = new CastExpr(this._right, arg1);
/*     */       }
/* 192 */       return this._type = ptype.resultType();
/*     */     } 
/* 194 */     throw new TypeCheckError(this);
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 198 */     if (hasNodeSetArgs() || hasReferenceArgs()) {
/* 199 */       ConstantPoolGen cpg = classGen.getConstantPool();
/* 200 */       InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */       
/* 203 */       this._left.translate(classGen, methodGen);
/* 204 */       this._left.startIterator(classGen, methodGen);
/* 205 */       this._right.translate(classGen, methodGen);
/* 206 */       this._right.startIterator(classGen, methodGen);
/*     */       
/* 208 */       il.append((CompoundInstruction)new PUSH(cpg, this._op));
/* 209 */       il.append(methodGen.loadDOM());
/*     */       
/* 211 */       int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "compare", "(" + this._left.getType().toSignature() + this._right.getType().toSignature() + "I" + "Lorg/apache/xalan/xsltc/DOM;" + ")Z");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 218 */       il.append((Instruction)new INVOKESTATIC(index));
/*     */     } else {
/*     */       
/* 221 */       translateDesynthesized(classGen, methodGen);
/* 222 */       synthesize(classGen, methodGen);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void translateDesynthesized(ClassGenerator classGen, MethodGenerator methodGen) {
/* 228 */     if (hasNodeSetArgs() || hasReferenceArgs()) {
/* 229 */       translate(classGen, methodGen);
/* 230 */       desynthesize(classGen, methodGen);
/*     */     } else {
/*     */       ErrorMsg msg;
/* 233 */       BranchInstruction bi = null;
/* 234 */       InstructionList il = methodGen.getInstructionList();
/*     */       
/* 236 */       this._left.translate(classGen, methodGen);
/* 237 */       this._right.translate(classGen, methodGen);
/*     */ 
/*     */ 
/*     */       
/* 241 */       boolean tozero = false;
/* 242 */       Type tleft = this._left.getType();
/*     */       
/* 244 */       if (tleft instanceof org.apache.xalan.xsltc.compiler.util.RealType) {
/* 245 */         il.append(tleft.CMP((this._op == 3 || this._op == 5)));
/* 246 */         tleft = Type.Int;
/* 247 */         tozero = true;
/*     */       } 
/*     */       
/* 250 */       switch (this._op) {
/*     */         case 3:
/* 252 */           bi = tleft.GE(tozero);
/*     */           break;
/*     */         
/*     */         case 2:
/* 256 */           bi = tleft.LE(tozero);
/*     */           break;
/*     */         
/*     */         case 5:
/* 260 */           bi = tleft.GT(tozero);
/*     */           break;
/*     */         
/*     */         case 4:
/* 264 */           bi = tleft.LT(tozero);
/*     */           break;
/*     */         
/*     */         default:
/* 268 */           msg = new ErrorMsg("ILLEGAL_RELAT_OP_ERR", this);
/* 269 */           getParser().reportError(2, msg);
/*     */           break;
/*     */       } 
/* 272 */       this._falseList.add((InstructionHandle)il.append(bi));
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 277 */     return Operators.names[this._op] + '(' + this._left + ", " + this._right + ')';
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/RelationalExpr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */