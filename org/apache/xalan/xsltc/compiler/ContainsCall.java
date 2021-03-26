/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.IFLT;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
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
/*     */ final class ContainsCall
/*     */   extends FunctionCall
/*     */ {
/*  41 */   private Expression _base = null;
/*  42 */   private Expression _token = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContainsCall(QName fname, Vector arguments) {
/*  48 */     super(fname, arguments);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBoolean() {
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  64 */     if (argumentCount() != 2) {
/*  65 */       throw new TypeCheckError("ILLEGAL_ARG_ERR", getName(), this);
/*     */     }
/*     */ 
/*     */     
/*  69 */     this._base = argument(0);
/*  70 */     Type baseType = this._base.typeCheck(stable);
/*  71 */     if (baseType != Type.String) {
/*  72 */       this._base = new CastExpr(this._base, Type.String);
/*     */     }
/*     */     
/*  75 */     this._token = argument(1);
/*  76 */     Type tokenType = this._token.typeCheck(stable);
/*  77 */     if (tokenType != Type.String) {
/*  78 */       this._token = new CastExpr(this._token, Type.String);
/*     */     }
/*  80 */     return this._type = Type.Boolean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  87 */     translateDesynthesized(classGen, methodGen);
/*  88 */     synthesize(classGen, methodGen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateDesynthesized(ClassGenerator classGen, MethodGenerator methodGen) {
/*  96 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  97 */     InstructionList il = methodGen.getInstructionList();
/*  98 */     this._base.translate(classGen, methodGen);
/*  99 */     this._token.translate(classGen, methodGen);
/* 100 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("java.lang.String", "indexOf", "(Ljava/lang/String;)I")));
/*     */ 
/*     */     
/* 103 */     this._falseList.add((InstructionHandle)il.append((BranchInstruction)new IFLT(null)));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ContainsCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */