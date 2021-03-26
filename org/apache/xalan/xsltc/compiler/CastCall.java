/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.CHECKCAST;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
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
/*    */ final class CastCall
/*    */   extends FunctionCall
/*    */ {
/*    */   private String _className;
/*    */   private Expression _right;
/*    */   
/*    */   public CastCall(QName fname, Vector arguments) {
/* 54 */     super(fname, arguments);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 62 */     if (argumentCount() != 2) {
/* 63 */       throw new TypeCheckError(new ErrorMsg("ILLEGAL_ARG_ERR", getName(), this));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 68 */     Expression exp = argument(0);
/* 69 */     if (exp instanceof LiteralExpr) {
/* 70 */       this._className = ((LiteralExpr)exp).getValue();
/* 71 */       this._type = Type.newObjectType(this._className);
/*    */     } else {
/*    */       
/* 74 */       throw new TypeCheckError(new ErrorMsg("NEED_LITERAL_ERR", getName(), this));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 79 */     this._right = argument(1);
/* 80 */     Type tright = this._right.typeCheck(stable);
/* 81 */     if (tright != Type.Reference && !(tright instanceof org.apache.xalan.xsltc.compiler.util.ObjectType))
/*    */     {
/*    */       
/* 84 */       throw new TypeCheckError(new ErrorMsg("DATA_CONVERSION_ERR", tright, this._type, this));
/*    */     }
/*    */ 
/*    */     
/* 88 */     return this._type;
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 92 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 93 */     InstructionList il = methodGen.getInstructionList();
/*    */     
/* 95 */     this._right.translate(classGen, methodGen);
/* 96 */     il.append((Instruction)new CHECKCAST(cpg.addClass(this._className)));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/CastCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */