/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.InstructionList;
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
/*    */ final class NumberCall
/*    */   extends FunctionCall
/*    */ {
/*    */   public NumberCall(QName fname, Vector arguments) {
/* 37 */     super(fname, arguments);
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 41 */     if (argumentCount() > 0) {
/* 42 */       argument().typeCheck(stable);
/*    */     }
/* 44 */     return this._type = Type.Real;
/*    */   }
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*    */     Type type;
/* 48 */     InstructionList il = methodGen.getInstructionList();
/*    */ 
/*    */     
/* 51 */     if (argumentCount() == 0) {
/* 52 */       il.append(methodGen.loadContextNode());
/* 53 */       type = Type.Node;
/*    */     } else {
/*    */       
/* 56 */       Expression arg = argument();
/* 57 */       arg.translate(classGen, methodGen);
/* 58 */       arg.startIterator(classGen, methodGen);
/* 59 */       type = arg.getType();
/*    */     } 
/*    */     
/* 62 */     if (!type.identicalTo(Type.Real))
/* 63 */       type.translateTo(classGen, methodGen, Type.Real); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/NumberCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */