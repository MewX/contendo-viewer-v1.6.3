/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.CompoundInstruction;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.PUSH;
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
/*    */ final class ElementAvailableCall
/*    */   extends FunctionCall
/*    */ {
/*    */   public ElementAvailableCall(QName fname, Vector arguments) {
/* 39 */     super(fname, arguments);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 46 */     if (argument() instanceof LiteralExpr) {
/* 47 */       return this._type = Type.Boolean;
/*    */     }
/* 49 */     ErrorMsg err = new ErrorMsg("NEED_LITERAL_ERR", "element-available", this);
/*    */     
/* 51 */     throw new TypeCheckError(err);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object evaluateAtCompileTime() {
/* 60 */     return getResult() ? Boolean.TRUE : Boolean.FALSE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getResult() {
/*    */     
/* 68 */     try { LiteralExpr arg = (LiteralExpr)argument();
/* 69 */       String qname = arg.getValue();
/* 70 */       int index = qname.indexOf(':');
/* 71 */       String localName = (index > 0) ? qname.substring(index + 1) : qname;
/*    */       
/* 73 */       return getParser().elementSupported(arg.getNamespace(), localName); } catch (ClassCastException e)
/*    */     
/*    */     { 
/*    */       
/* 77 */       return false; }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 87 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 88 */     boolean result = getResult();
/* 89 */     methodGen.getInstructionList().append((CompoundInstruction)new PUSH(cpg, result));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ElementAvailableCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */