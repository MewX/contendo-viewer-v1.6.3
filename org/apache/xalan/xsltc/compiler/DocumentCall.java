/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETFIELD;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class DocumentCall
/*     */   extends FunctionCall
/*     */ {
/*  42 */   private Expression _arg1 = null;
/*  43 */   private Expression _arg2 = null;
/*     */ 
/*     */   
/*     */   private Type _arg1Type;
/*     */ 
/*     */   
/*     */   public DocumentCall(QName fname, Vector arguments) {
/*  50 */     super(fname, arguments);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  60 */     int ac = argumentCount();
/*  61 */     if (ac < 1 || ac > 2) {
/*  62 */       ErrorMsg msg = new ErrorMsg("ILLEGAL_ARG_ERR", this);
/*  63 */       throw new TypeCheckError(msg);
/*     */     } 
/*  65 */     if (getStylesheet() == null) {
/*  66 */       ErrorMsg msg = new ErrorMsg("ILLEGAL_ARG_ERR", this);
/*  67 */       throw new TypeCheckError(msg);
/*     */     } 
/*     */ 
/*     */     
/*  71 */     this._arg1 = argument(0);
/*     */     
/*  73 */     if (this._arg1 == null) {
/*  74 */       ErrorMsg msg = new ErrorMsg("DOCUMENT_ARG_ERR", this);
/*  75 */       throw new TypeCheckError(msg);
/*     */     } 
/*     */     
/*  78 */     this._arg1Type = this._arg1.typeCheck(stable);
/*  79 */     if (this._arg1Type != Type.NodeSet && this._arg1Type != Type.String) {
/*  80 */       this._arg1 = new CastExpr(this._arg1, Type.String);
/*     */     }
/*     */ 
/*     */     
/*  84 */     if (ac == 2) {
/*  85 */       this._arg2 = argument(1);
/*     */       
/*  87 */       if (this._arg2 == null) {
/*  88 */         ErrorMsg msg = new ErrorMsg("DOCUMENT_ARG_ERR", this);
/*  89 */         throw new TypeCheckError(msg);
/*     */       } 
/*     */       
/*  92 */       Type arg2Type = this._arg2.typeCheck(stable);
/*     */       
/*  94 */       if (arg2Type.identicalTo(Type.Node)) {
/*  95 */         this._arg2 = new CastExpr(this._arg2, Type.NodeSet);
/*  96 */       } else if (!arg2Type.identicalTo(Type.NodeSet)) {
/*     */ 
/*     */         
/*  99 */         ErrorMsg msg = new ErrorMsg("DOCUMENT_ARG_ERR", this);
/* 100 */         throw new TypeCheckError(msg);
/*     */       } 
/*     */     } 
/*     */     
/* 104 */     return this._type = Type.NodeSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 112 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 113 */     InstructionList il = methodGen.getInstructionList();
/* 114 */     int ac = argumentCount();
/*     */     
/* 116 */     int domField = cpg.addFieldref(classGen.getClassName(), "_dom", "Lorg/apache/xalan/xsltc/DOM;");
/*     */ 
/*     */ 
/*     */     
/* 120 */     String docParamList = null;
/* 121 */     if (ac == 1) {
/*     */       
/* 123 */       docParamList = "(Ljava/lang/Object;Ljava/lang/String;Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;Lorg/apache/xalan/xsltc/DOM;)Lorg/apache/xml/dtm/DTMAxisIterator;";
/*     */     }
/*     */     else {
/*     */       
/* 127 */       docParamList = "(Ljava/lang/Object;Lorg/apache/xml/dtm/DTMAxisIterator;Ljava/lang/String;Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;Lorg/apache/xalan/xsltc/DOM;)Lorg/apache/xml/dtm/DTMAxisIterator;";
/*     */     } 
/*     */     
/* 130 */     int docIdx = cpg.addMethodref("org.apache.xalan.xsltc.dom.LoadDocument", "documentF", docParamList);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     this._arg1.translate(classGen, methodGen);
/* 136 */     if (this._arg1Type == Type.NodeSet) {
/* 137 */       this._arg1.startIterator(classGen, methodGen);
/*     */     }
/*     */     
/* 140 */     if (ac == 2) {
/*     */       
/* 142 */       this._arg2.translate(classGen, methodGen);
/* 143 */       this._arg2.startIterator(classGen, methodGen);
/*     */     } 
/*     */ 
/*     */     
/* 147 */     il.append((CompoundInstruction)new PUSH(cpg, getStylesheet().getSystemId()));
/* 148 */     il.append(classGen.loadTranslet());
/* 149 */     il.append((Instruction)InstructionConstants.DUP);
/* 150 */     il.append((Instruction)new GETFIELD(domField));
/* 151 */     il.append((Instruction)new INVOKESTATIC(docIdx));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/DocumentCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */