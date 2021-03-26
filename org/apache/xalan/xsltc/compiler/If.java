/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
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
/*     */ final class If
/*     */   extends Instruction
/*     */ {
/*     */   private Expression _test;
/*     */   private boolean _ignore = false;
/*     */   
/*     */   public void display(int indent) {
/*  46 */     indent(indent);
/*  47 */     Util.println("If");
/*  48 */     indent(indent + 4);
/*  49 */     System.out.print("test ");
/*  50 */     Util.println(this._test.toString());
/*  51 */     displayContents(indent + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  59 */     this._test = parser.parseExpression(this, "test", null);
/*     */ 
/*     */     
/*  62 */     if (this._test.isDummy()) {
/*  63 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "test");
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  69 */     Object result = this._test.evaluateAtCompileTime();
/*  70 */     if (result != null && result instanceof Boolean) {
/*  71 */       this._ignore = !((Boolean)result).booleanValue();
/*     */     }
/*     */     
/*  74 */     parseChildren(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  83 */     if (!(this._test.typeCheck(stable) instanceof org.apache.xalan.xsltc.compiler.util.BooleanType)) {
/*  84 */       this._test = new CastExpr(this._test, Type.Boolean);
/*     */     }
/*     */     
/*  87 */     if (!this._ignore) {
/*  88 */       typeCheckContents(stable);
/*     */     }
/*  90 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  98 */     InstructionList il = methodGen.getInstructionList();
/*  99 */     this._test.translateDesynthesized(classGen, methodGen);
/*     */     
/* 101 */     InstructionHandle truec = il.getEnd();
/* 102 */     if (!this._ignore) {
/* 103 */       translateContents(classGen, methodGen);
/*     */     }
/* 105 */     this._test.backPatchFalseList(il.append(InstructionConstants.NOP));
/* 106 */     this._test.backPatchTrueList(truec.getNext());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/If.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */