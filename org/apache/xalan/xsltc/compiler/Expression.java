/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO_W;
/*     */ import org.apache.bcel.generic.IFEQ;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodType;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class Expression
/*     */   extends SyntaxTreeNode
/*     */ {
/*     */   protected Type _type;
/*  55 */   protected FlowList _trueList = new FlowList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected FlowList _falseList = new FlowList();
/*     */   
/*     */   public Type getType() {
/*  63 */     return this._type;
/*     */   }
/*     */   
/*     */   public abstract String toString();
/*     */   
/*     */   public boolean hasPositionCall() {
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasLastCall() {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object evaluateAtCompileTime() {
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  89 */     return typeCheckContents(stable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  96 */     ErrorMsg msg = new ErrorMsg("NOT_IMPLEMENTED_ERR", getClass(), this);
/*     */     
/*  98 */     getParser().reportError(2, msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final InstructionList compile(ClassGenerator classGen, MethodGenerator methodGen) {
/* 107 */     InstructionList save = methodGen.getInstructionList(); InstructionList result;
/* 108 */     methodGen.setInstructionList(result = new InstructionList());
/* 109 */     translate(classGen, methodGen);
/* 110 */     methodGen.setInstructionList(save);
/* 111 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateDesynthesized(ClassGenerator classGen, MethodGenerator methodGen) {
/* 119 */     translate(classGen, methodGen);
/* 120 */     if (this._type instanceof org.apache.xalan.xsltc.compiler.util.BooleanType) {
/* 121 */       desynthesize(classGen, methodGen);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startIterator(ClassGenerator classGen, MethodGenerator methodGen) {
/* 132 */     if (!(this._type instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 137 */     Expression expr = this;
/* 138 */     if (expr instanceof CastExpr) {
/* 139 */       expr = ((CastExpr)expr).getExpr();
/*     */     }
/* 141 */     if (!(expr instanceof VariableRefBase)) {
/* 142 */       InstructionList il = methodGen.getInstructionList();
/* 143 */       il.append(methodGen.loadContextNode());
/* 144 */       il.append(methodGen.setStartNode());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void synthesize(ClassGenerator classGen, MethodGenerator methodGen) {
/* 154 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 155 */     InstructionList il = methodGen.getInstructionList();
/* 156 */     this._trueList.backPatch(il.append(InstructionConstants.ICONST_1));
/* 157 */     BranchHandle truec = il.append((BranchInstruction)new GOTO_W(null));
/* 158 */     this._falseList.backPatch(il.append(InstructionConstants.ICONST_0));
/* 159 */     truec.setTarget(il.append(InstructionConstants.NOP));
/*     */   }
/*     */ 
/*     */   
/*     */   public void desynthesize(ClassGenerator classGen, MethodGenerator methodGen) {
/* 164 */     InstructionList il = methodGen.getInstructionList();
/* 165 */     this._falseList.add((InstructionHandle)il.append((BranchInstruction)new IFEQ(null)));
/*     */   }
/*     */   
/*     */   public FlowList getFalseList() {
/* 169 */     return this._falseList;
/*     */   }
/*     */   
/*     */   public FlowList getTrueList() {
/* 173 */     return this._trueList;
/*     */   }
/*     */   
/*     */   public void backPatchFalseList(InstructionHandle ih) {
/* 177 */     this._falseList.backPatch(ih);
/*     */   }
/*     */   
/*     */   public void backPatchTrueList(InstructionHandle ih) {
/* 181 */     this._trueList.backPatch(ih);
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
/*     */   public MethodType lookupPrimop(SymbolTable stable, String op, MethodType ctype) {
/* 193 */     MethodType result = null;
/* 194 */     Vector primop = stable.lookupPrimop(op);
/* 195 */     if (primop != null) {
/* 196 */       int n = primop.size();
/* 197 */       int minDistance = Integer.MAX_VALUE;
/* 198 */       for (int i = 0; i < n; i++) {
/* 199 */         MethodType ptype = primop.elementAt(i);
/*     */         
/* 201 */         if (ptype.argsCount() == ctype.argsCount()) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 206 */           if (result == null) {
/* 207 */             result = ptype;
/*     */           }
/*     */ 
/*     */           
/* 211 */           int distance = ctype.distanceTo((Type)ptype);
/* 212 */           if (distance < minDistance) {
/* 213 */             minDistance = distance;
/* 214 */             result = ptype;
/*     */           } 
/*     */         } 
/*     */       } 
/* 218 */     }  return result;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Expression.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */