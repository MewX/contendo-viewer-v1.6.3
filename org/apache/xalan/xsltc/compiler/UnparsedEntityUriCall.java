/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*    */ import org.apache.bcel.generic.Instruction;
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
/*    */ final class UnparsedEntityUriCall
/*    */   extends FunctionCall
/*    */ {
/*    */   private Expression _entity;
/*    */   
/*    */   public UnparsedEntityUriCall(QName fname, Vector arguments) {
/* 42 */     super(fname, arguments);
/* 43 */     this._entity = argument();
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 47 */     Type entity = this._entity.typeCheck(stable);
/* 48 */     if (!(entity instanceof org.apache.xalan.xsltc.compiler.util.StringType)) {
/* 49 */       this._entity = new CastExpr(this._entity, Type.String);
/*    */     }
/* 51 */     return this._type = Type.String;
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 55 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 56 */     InstructionList il = methodGen.getInstructionList();
/*    */     
/* 58 */     il.append(methodGen.loadDOM());
/*    */     
/* 60 */     this._entity.translate(classGen, methodGen);
/*    */     
/* 62 */     il.append((Instruction)new INVOKEINTERFACE(cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getUnparsedEntityURI", "(Ljava/lang/String;)Ljava/lang/String;"), 2));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/UnparsedEntityUriCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */