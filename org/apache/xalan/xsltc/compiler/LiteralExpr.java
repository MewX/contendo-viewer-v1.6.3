/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import org.apache.bcel.generic.CompoundInstruction;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.bcel.generic.PUSH;
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
/*    */ 
/*    */ 
/*    */ final class LiteralExpr
/*    */   extends Expression
/*    */ {
/*    */   private final String _value;
/*    */   private final String _namespace;
/*    */   
/*    */   public LiteralExpr(String value) {
/* 44 */     this._value = value;
/* 45 */     this._namespace = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LiteralExpr(String value, String namespace) {
/* 54 */     this._value = value;
/* 55 */     this._namespace = namespace.equals("") ? null : namespace;
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 59 */     return this._type = Type.String;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 63 */     return "literal-expr(" + this._value + ')';
/*    */   }
/*    */   
/*    */   protected boolean contextDependent() {
/* 67 */     return false;
/*    */   }
/*    */   
/*    */   protected String getValue() {
/* 71 */     return this._value;
/*    */   }
/*    */   
/*    */   protected String getNamespace() {
/* 75 */     return this._namespace;
/*    */   }
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 79 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 80 */     InstructionList il = methodGen.getInstructionList();
/* 81 */     il.append((CompoundInstruction)new PUSH(cpg, this._value));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/LiteralExpr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */