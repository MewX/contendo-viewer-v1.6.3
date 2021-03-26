/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*    */ import org.apache.bcel.generic.Instruction;
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*    */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*    */ import org.apache.xalan.xsltc.compiler.util.Type;
/*    */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class StartsWithCall
/*    */   extends FunctionCall
/*    */ {
/* 40 */   private Expression _base = null;
/* 41 */   private Expression _token = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StartsWithCall(QName fname, Vector arguments) {
/* 47 */     super(fname, arguments);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 56 */     if (argumentCount() != 2) {
/* 57 */       ErrorMsg err = new ErrorMsg("ILLEGAL_ARG_ERR", getName(), this);
/*    */       
/* 59 */       throw new TypeCheckError(err);
/*    */     } 
/*    */ 
/*    */     
/* 63 */     this._base = argument(0);
/* 64 */     Type baseType = this._base.typeCheck(stable);
/* 65 */     if (baseType != Type.String) {
/* 66 */       this._base = new CastExpr(this._base, Type.String);
/*    */     }
/*    */     
/* 69 */     this._token = argument(1);
/* 70 */     Type tokenType = this._token.typeCheck(stable);
/* 71 */     if (tokenType != Type.String) {
/* 72 */       this._token = new CastExpr(this._token, Type.String);
/*    */     }
/* 74 */     return this._type = Type.Boolean;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 81 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 82 */     InstructionList il = methodGen.getInstructionList();
/* 83 */     this._base.translate(classGen, methodGen);
/* 84 */     this._token.translate(classGen, methodGen);
/* 85 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("java.lang.String", "startsWith", "(Ljava/lang/String;)Z")));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/StartsWithCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */