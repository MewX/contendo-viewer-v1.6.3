/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
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
/*     */ 
/*     */ class NameBase
/*     */   extends FunctionCall
/*     */ {
/*  38 */   private Expression _param = null;
/*  39 */   private Type _paramType = Type.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NameBase(QName fname) {
/*  45 */     super(fname);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NameBase(QName fname, Vector arguments) {
/*  52 */     super(fname, arguments);
/*  53 */     this._param = argument(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  64 */     switch (argumentCount()) {
/*     */       case 0:
/*  66 */         this._paramType = Type.Node;
/*     */         break;
/*     */       case 1:
/*  69 */         this._paramType = this._param.typeCheck(stable);
/*     */         break;
/*     */       default:
/*  72 */         throw new TypeCheckError(this);
/*     */     } 
/*     */ 
/*     */     
/*  76 */     if (this._paramType != Type.NodeSet && this._paramType != Type.Node && this._paramType != Type.Reference)
/*     */     {
/*     */       
/*  79 */       throw new TypeCheckError(this);
/*     */     }
/*     */     
/*  82 */     return this._type = Type.String;
/*     */   }
/*     */   
/*     */   public Type getType() {
/*  86 */     return this._type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  95 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  96 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/*  98 */     il.append(methodGen.loadDOM());
/*     */ 
/*     */     
/* 101 */     if (argumentCount() == 0) {
/* 102 */       il.append(methodGen.loadContextNode());
/*     */     
/*     */     }
/* 105 */     else if (this._paramType == Type.Node) {
/* 106 */       this._param.translate(classGen, methodGen);
/*     */     }
/* 108 */     else if (this._paramType == Type.Reference) {
/* 109 */       this._param.translate(classGen, methodGen);
/* 110 */       il.append((Instruction)new INVOKESTATIC(cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "referenceToNodeSet", "(Ljava/lang/Object;)Lorg/apache/xml/dtm/DTMAxisIterator;")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       il.append(methodGen.nextNode());
/*     */     }
/*     */     else {
/*     */       
/* 121 */       this._param.translate(classGen, methodGen);
/* 122 */       this._param.startIterator(classGen, methodGen);
/* 123 */       il.append(methodGen.nextNode());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/NameBase.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */