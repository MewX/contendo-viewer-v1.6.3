/*    */ package org.apache.xalan.xsltc.compiler;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.bcel.generic.CompoundInstruction;
/*    */ import org.apache.bcel.generic.ConstantPoolGen;
/*    */ import org.apache.bcel.generic.INVOKESPECIAL;
/*    */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*    */ import org.apache.bcel.generic.Instruction;
/*    */ import org.apache.bcel.generic.InstructionConstants;
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.bcel.generic.NEW;
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
/*    */ final class ConcatCall
/*    */   extends FunctionCall
/*    */ {
/*    */   public ConcatCall(QName fname, Vector arguments) {
/* 42 */     super(fname, arguments);
/*    */   }
/*    */   
/*    */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 46 */     for (int i = 0; i < argumentCount(); i++) {
/* 47 */       Expression exp = argument(i);
/* 48 */       if (!exp.typeCheck(stable).identicalTo(Type.String)) {
/* 49 */         setArgument(i, new CastExpr(exp, Type.String));
/*    */       }
/*    */     } 
/* 52 */     return this._type = Type.String;
/*    */   }
/*    */ 
/*    */   
/*    */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 57 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 58 */     InstructionList il = methodGen.getInstructionList();
/* 59 */     int nArgs = argumentCount();
/*    */     
/* 61 */     switch (nArgs) {
/*    */       case 0:
/* 63 */         il.append((CompoundInstruction)new PUSH(cpg, ""));
/*    */         return;
/*    */       
/*    */       case 1:
/* 67 */         argument().translate(classGen, methodGen);
/*    */         return;
/*    */     } 
/*    */     
/* 71 */     int initBuffer = cpg.addMethodref("java.lang.StringBuffer", "<init>", "()V");
/*    */     
/* 73 */     INVOKEVIRTUAL iNVOKEVIRTUAL = new INVOKEVIRTUAL(cpg.addMethodref("java.lang.StringBuffer", "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;"));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 79 */     int toString = cpg.addMethodref("java.lang.StringBuffer", "toString", "()Ljava/lang/String;");
/*    */ 
/*    */ 
/*    */     
/* 83 */     il.append((Instruction)new NEW(cpg.addClass("java.lang.StringBuffer")));
/* 84 */     il.append((Instruction)InstructionConstants.DUP);
/* 85 */     il.append((Instruction)new INVOKESPECIAL(initBuffer));
/* 86 */     for (int i = 0; i < nArgs; i++) {
/* 87 */       argument(i).translate(classGen, methodGen);
/* 88 */       il.append((Instruction)iNVOKEVIRTUAL);
/*    */     } 
/* 90 */     il.append((Instruction)new INVOKEVIRTUAL(toString));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ConcatCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */