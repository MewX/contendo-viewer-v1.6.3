/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.IFNE;
/*     */ import org.apache.bcel.generic.IF_ICMPEQ;
/*     */ import org.apache.bcel.generic.IF_ICMPNE;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class EqualityExpr
/*     */   extends Expression
/*     */   implements Operators
/*     */ {
/*     */   private final int _op;
/*     */   private Expression _left;
/*     */   private Expression _right;
/*     */   
/*     */   public EqualityExpr(int op, Expression left, Expression right) {
/*  61 */     this._op = op;
/*  62 */     (this._left = left).setParent(this);
/*  63 */     (this._right = right).setParent(this);
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  67 */     super.setParser(parser);
/*  68 */     this._left.setParser(parser);
/*  69 */     this._right.setParser(parser);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  73 */     return Operators.names[this._op] + '(' + this._left + ", " + this._right + ')';
/*     */   }
/*     */   
/*     */   public Expression getLeft() {
/*  77 */     return this._left;
/*     */   }
/*     */   
/*     */   public Expression getRight() {
/*  81 */     return this._right;
/*     */   }
/*     */   
/*     */   public boolean getOp() {
/*  85 */     return (this._op != 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPositionCall() {
/*  93 */     if (this._left.hasPositionCall()) return true; 
/*  94 */     if (this._right.hasPositionCall()) return true; 
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasLastCall() {
/*  99 */     if (this._left.hasLastCall()) return true; 
/* 100 */     if (this._right.hasLastCall()) return true; 
/* 101 */     return false;
/*     */   }
/*     */   
/*     */   private void swapArguments() {
/* 105 */     Expression temp = this._left;
/* 106 */     this._left = this._right;
/* 107 */     this._right = temp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 114 */     Type tleft = this._left.typeCheck(stable);
/* 115 */     Type tright = this._right.typeCheck(stable);
/*     */     
/* 117 */     if (tleft.isSimple() && tright.isSimple()) {
/* 118 */       if (tleft != tright) {
/* 119 */         if (tleft instanceof org.apache.xalan.xsltc.compiler.util.BooleanType) {
/* 120 */           this._right = new CastExpr(this._right, Type.Boolean);
/*     */         }
/* 122 */         else if (tright instanceof org.apache.xalan.xsltc.compiler.util.BooleanType) {
/* 123 */           this._left = new CastExpr(this._left, Type.Boolean);
/*     */         }
/* 125 */         else if (tleft instanceof org.apache.xalan.xsltc.compiler.util.NumberType || tright instanceof org.apache.xalan.xsltc.compiler.util.NumberType) {
/*     */           
/* 127 */           this._left = new CastExpr(this._left, Type.Real);
/* 128 */           this._right = new CastExpr(this._right, Type.Real);
/*     */         } else {
/*     */           
/* 131 */           this._left = new CastExpr(this._left, Type.String);
/* 132 */           this._right = new CastExpr(this._right, Type.String);
/*     */         }
/*     */       
/*     */       }
/* 136 */     } else if (tleft instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType) {
/* 137 */       this._right = new CastExpr(this._right, Type.Reference);
/*     */     }
/* 139 */     else if (tright instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType) {
/* 140 */       this._left = new CastExpr(this._left, Type.Reference);
/*     */     
/*     */     }
/* 143 */     else if (tleft instanceof org.apache.xalan.xsltc.compiler.util.NodeType && tright == Type.String) {
/* 144 */       this._left = new CastExpr(this._left, Type.String);
/*     */     }
/* 146 */     else if (tleft == Type.String && tright instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/* 147 */       this._right = new CastExpr(this._right, Type.String);
/*     */     
/*     */     }
/* 150 */     else if (tleft instanceof org.apache.xalan.xsltc.compiler.util.NodeType && tright instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/* 151 */       this._left = new CastExpr(this._left, Type.String);
/* 152 */       this._right = new CastExpr(this._right, Type.String);
/*     */     }
/* 154 */     else if (!(tleft instanceof org.apache.xalan.xsltc.compiler.util.NodeType) || !(tright instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType)) {
/*     */ 
/*     */       
/* 157 */       if (tleft instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType && tright instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/* 158 */         swapArguments();
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 164 */         if (tleft instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/* 165 */           this._left = new CastExpr(this._left, Type.NodeSet);
/*     */         }
/* 167 */         if (tright instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/* 168 */           this._right = new CastExpr(this._right, Type.NodeSet);
/*     */         }
/*     */ 
/*     */         
/* 172 */         if (tleft.isSimple() || (tleft instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType && tright instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType))
/*     */         {
/*     */           
/* 175 */           swapArguments();
/*     */         }
/*     */ 
/*     */         
/* 179 */         if (this._right.getType() instanceof org.apache.xalan.xsltc.compiler.util.IntType)
/* 180 */           this._right = new CastExpr(this._right, Type.Real); 
/*     */       } 
/*     */     } 
/* 183 */     return this._type = Type.Boolean;
/*     */   }
/*     */ 
/*     */   
/*     */   public void translateDesynthesized(ClassGenerator classGen, MethodGenerator methodGen) {
/* 188 */     Type tleft = this._left.getType();
/* 189 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 191 */     if (tleft instanceof org.apache.xalan.xsltc.compiler.util.BooleanType) {
/* 192 */       this._left.translate(classGen, methodGen);
/* 193 */       this._right.translate(classGen, methodGen);
/* 194 */       this._falseList.add((InstructionHandle)il.append((this._op == 0) ? (BranchInstruction)new IF_ICMPNE(null) : (BranchInstruction)new IF_ICMPEQ(null)));
/*     */ 
/*     */     
/*     */     }
/* 198 */     else if (tleft instanceof org.apache.xalan.xsltc.compiler.util.NumberType) {
/* 199 */       this._left.translate(classGen, methodGen);
/* 200 */       this._right.translate(classGen, methodGen);
/*     */       
/* 202 */       if (tleft instanceof org.apache.xalan.xsltc.compiler.util.RealType) {
/* 203 */         il.append(InstructionConstants.DCMPG);
/* 204 */         this._falseList.add((InstructionHandle)il.append((this._op == 0) ? (BranchInstruction)new IFNE(null) : (BranchInstruction)new IFEQ(null)));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 209 */         this._falseList.add((InstructionHandle)il.append((this._op == 0) ? (BranchInstruction)new IF_ICMPNE(null) : (BranchInstruction)new IF_ICMPEQ(null)));
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 215 */       translate(classGen, methodGen);
/* 216 */       desynthesize(classGen, methodGen);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 221 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 222 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 224 */     Type tleft = this._left.getType();
/* 225 */     Type tright = this._right.getType();
/*     */     
/* 227 */     if (tleft instanceof org.apache.xalan.xsltc.compiler.util.BooleanType || tleft instanceof org.apache.xalan.xsltc.compiler.util.NumberType) {
/* 228 */       translateDesynthesized(classGen, methodGen);
/* 229 */       synthesize(classGen, methodGen);
/*     */       
/*     */       return;
/*     */     } 
/* 233 */     if (tleft instanceof org.apache.xalan.xsltc.compiler.util.StringType) {
/* 234 */       int equals = cpg.addMethodref("java.lang.String", "equals", "(Ljava/lang/Object;)Z");
/*     */ 
/*     */       
/* 237 */       this._left.translate(classGen, methodGen);
/* 238 */       this._right.translate(classGen, methodGen);
/* 239 */       il.append((Instruction)new INVOKEVIRTUAL(equals));
/*     */       
/* 241 */       if (this._op == 1) {
/* 242 */         il.append(InstructionConstants.ICONST_1);
/* 243 */         il.append((Instruction)InstructionConstants.IXOR);
/*     */       } 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 250 */     if (tleft instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/* 251 */       if (tright instanceof org.apache.xalan.xsltc.compiler.util.BooleanType) {
/* 252 */         this._right.translate(classGen, methodGen);
/* 253 */         if (this._op == 1) {
/* 254 */           il.append(InstructionConstants.ICONST_1);
/* 255 */           il.append((Instruction)InstructionConstants.IXOR);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/* 260 */       if (tright instanceof org.apache.xalan.xsltc.compiler.util.RealType) {
/* 261 */         this._left.translate(classGen, methodGen);
/* 262 */         tleft.translateTo(classGen, methodGen, Type.Real);
/* 263 */         this._right.translate(classGen, methodGen);
/*     */         
/* 265 */         il.append(InstructionConstants.DCMPG);
/* 266 */         BranchHandle falsec = il.append((this._op == 0) ? (BranchInstruction)new IFNE(null) : (BranchInstruction)new IFEQ(null));
/*     */ 
/*     */         
/* 269 */         il.append(InstructionConstants.ICONST_1);
/* 270 */         BranchHandle truec = il.append((BranchInstruction)new GOTO(null));
/* 271 */         falsec.setTarget(il.append(InstructionConstants.ICONST_0));
/* 272 */         truec.setTarget(il.append(InstructionConstants.NOP));
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 278 */       this._left.translate(classGen, methodGen);
/* 279 */       tleft.translateTo(classGen, methodGen, Type.String);
/* 280 */       this._right.translate(classGen, methodGen);
/*     */       
/* 282 */       if (tright instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/* 283 */         tright.translateTo(classGen, methodGen, Type.String);
/*     */       }
/*     */       
/* 286 */       int equals = cpg.addMethodref("java.lang.String", "equals", "(Ljava/lang/Object;)Z");
/*     */ 
/*     */       
/* 289 */       il.append((Instruction)new INVOKEVIRTUAL(equals));
/*     */       
/* 291 */       if (this._op == 1) {
/* 292 */         il.append(InstructionConstants.ICONST_1);
/* 293 */         il.append((Instruction)InstructionConstants.IXOR);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 298 */     if (tleft instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType && tright instanceof org.apache.xalan.xsltc.compiler.util.BooleanType) {
/* 299 */       this._left.translate(classGen, methodGen);
/* 300 */       this._left.startIterator(classGen, methodGen);
/* 301 */       Type.NodeSet.translateTo(classGen, methodGen, Type.Boolean);
/* 302 */       this._right.translate(classGen, methodGen);
/*     */       
/* 304 */       il.append((Instruction)InstructionConstants.IXOR);
/* 305 */       if (this._op == 0) {
/* 306 */         il.append(InstructionConstants.ICONST_1);
/* 307 */         il.append((Instruction)InstructionConstants.IXOR);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 312 */     if (tleft instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType && tright instanceof org.apache.xalan.xsltc.compiler.util.StringType) {
/* 313 */       this._left.translate(classGen, methodGen);
/* 314 */       this._left.startIterator(classGen, methodGen);
/* 315 */       this._right.translate(classGen, methodGen);
/* 316 */       il.append((CompoundInstruction)new PUSH(cpg, this._op));
/* 317 */       il.append(methodGen.loadDOM());
/* 318 */       int cmp = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "compare", "(" + tleft.toSignature() + tright.toSignature() + "I" + "Lorg/apache/xalan/xsltc/DOM;" + ")Z");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 326 */       il.append((Instruction)new INVOKESTATIC(cmp));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 331 */     this._left.translate(classGen, methodGen);
/* 332 */     this._left.startIterator(classGen, methodGen);
/* 333 */     this._right.translate(classGen, methodGen);
/* 334 */     this._right.startIterator(classGen, methodGen);
/*     */ 
/*     */     
/* 337 */     if (tright instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/* 338 */       tright.translateTo(classGen, methodGen, Type.String);
/* 339 */       tright = Type.String;
/*     */     } 
/*     */ 
/*     */     
/* 343 */     il.append((CompoundInstruction)new PUSH(cpg, this._op));
/* 344 */     il.append(methodGen.loadDOM());
/*     */     
/* 346 */     int compare = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "compare", "(" + tleft.toSignature() + tright.toSignature() + "I" + "Lorg/apache/xalan/xsltc/DOM;" + ")Z");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     il.append((Instruction)new INVOKESTATIC(compare));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/EqualityExpr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */