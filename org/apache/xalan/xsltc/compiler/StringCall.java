/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
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
/*    */ final class StringCall
/*    */   extends FunctionCall
/*    */ {
/*    */   public StringCall(QName fname, Vector arguments) {
/* 37 */     super(fname, arguments);
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 41 */     int argc = argumentCount();
/* 42 */     if (argc > 1) {
/* 43 */       ErrorMsg err = new ErrorMsg("ILLEGAL_ARG_ERR", this);
/* 44 */       throw new TypeCheckError(err);
/*    */     } 
/*    */     
/* 47 */     if (argc > 0) {
/* 48 */       argument().typeCheck(stable);
/*    */     }
/* 50 */     return this._type = Type.String;
/*    */   }
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*    */     Type type;
/* 54 */     InstructionList il = methodGen.getInstructionList();
/*    */ 
/*    */     
/* 57 */     if (argumentCount() == 0) {
/* 58 */       il.append(methodGen.loadContextNode());
/* 59 */       type = Type.Node;
/*    */     } else {
/*    */       
/* 62 */       Expression arg = argument();
/* 63 */       arg.translate(classGen, methodGen);
/* 64 */       arg.startIterator(classGen, methodGen);
/* 65 */       type = arg.getType();
/*    */     } 
/*    */     
/* 68 */     if (!type.identicalTo(Type.String))
/* 69 */       type.translateTo(classGen, methodGen, Type.String); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/StringCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */