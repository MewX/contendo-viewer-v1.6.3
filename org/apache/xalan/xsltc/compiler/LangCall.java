/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.ILOAD;
/*    */ import org.apache.bcel.generic.INVOKESTATIC;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ final class LangCall
/*    */   extends FunctionCall
/*    */ {
/*    */   private Expression _lang;
/*    */   private Type _langType;
/*    */   
/*    */   public LangCall(QName fname, Vector arguments) {
/* 47 */     super(fname, arguments);
/* 48 */     this._lang = argument(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 55 */     this._langType = this._lang.typeCheck(stable);
/* 56 */     if (!(this._langType instanceof org.apache.xalan.xsltc.compiler.util.StringType)) {
/* 57 */       this._lang = new CastExpr(this._lang, Type.String);
/*    */     }
/* 59 */     return Type.Boolean;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Type getType() {
/* 66 */     return Type.Boolean;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 75 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 76 */     InstructionList il = methodGen.getInstructionList();
/*    */     
/* 78 */     int tst = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "testLanguage", "(Ljava/lang/String;Lorg/apache/xalan/xsltc/DOM;I)Z");
/*    */ 
/*    */     
/* 81 */     this._lang.translate(classGen, methodGen);
/* 82 */     il.append(methodGen.loadDOM());
/* 83 */     if (classGen instanceof org.apache.xalan.xsltc.compiler.util.FilterGenerator) {
/* 84 */       il.append((Instruction)new ILOAD(1));
/*    */     } else {
/* 86 */       il.append(methodGen.loadContextNode());
/* 87 */     }  il.append((Instruction)new INVOKESTATIC(tst));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/LangCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */