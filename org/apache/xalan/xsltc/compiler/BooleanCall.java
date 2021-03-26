/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
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
/*    */ final class BooleanCall
/*    */   extends FunctionCall
/*    */ {
/* 35 */   private Expression _arg = null;
/*    */   
/*    */   public BooleanCall(QName fname, Vector arguments) {
/* 38 */     super(fname, arguments);
/* 39 */     this._arg = argument(0);
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 43 */     this._arg.typeCheck(stable);
/* 44 */     return this._type = Type.Boolean;
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 48 */     this._arg.translate(classGen, methodGen);
/* 49 */     Type targ = this._arg.getType();
/* 50 */     if (!targ.identicalTo(Type.Boolean)) {
/* 51 */       this._arg.startIterator(classGen, methodGen);
/* 52 */       targ.translateTo(classGen, methodGen, Type.Boolean);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/BooleanCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */