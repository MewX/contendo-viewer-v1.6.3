/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
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
/*     */ 
/*     */ 
/*     */ final class CopyOf
/*     */   extends Instruction
/*     */ {
/*     */   private Expression _select;
/*     */   
/*     */   public void display(int indent) {
/*  46 */     indent(indent);
/*  47 */     Util.println("CopyOf");
/*  48 */     indent(indent + 4);
/*  49 */     Util.println("select " + this._select.toString());
/*     */   }
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  53 */     this._select = parser.parseExpression(this, "select", null);
/*     */     
/*  55 */     if (this._select.isDummy()) {
/*  56 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "select");
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  62 */     Type tselect = this._select.typeCheck(stable);
/*  63 */     if (!(tselect instanceof org.apache.xalan.xsltc.compiler.util.NodeType) && !(tselect instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType) && !(tselect instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType) && !(tselect instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  70 */       this._select = new CastExpr(this._select, Type.String);
/*     */     }
/*  72 */     return Type.Void;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  76 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  77 */     InstructionList il = methodGen.getInstructionList();
/*  78 */     Type tselect = this._select.getType();
/*     */     
/*  80 */     String CPY1_SIG = "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V";
/*  81 */     int cpy1 = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "copy", "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V");
/*     */     
/*  83 */     String CPY2_SIG = "(ILorg/apache/xml/serializer/SerializationHandler;)V";
/*  84 */     int cpy2 = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "copy", "(ILorg/apache/xml/serializer/SerializationHandler;)V");
/*     */     
/*  86 */     String getDoc_SIG = "()I";
/*  87 */     int getDoc = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getDocument", "()I");
/*     */ 
/*     */     
/*  90 */     if (tselect instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType) {
/*  91 */       il.append(methodGen.loadDOM());
/*     */ 
/*     */       
/*  94 */       this._select.translate(classGen, methodGen);
/*  95 */       this._select.startIterator(classGen, methodGen);
/*     */ 
/*     */       
/*  98 */       il.append(methodGen.loadHandler());
/*  99 */       il.append((Instruction)new INVOKEINTERFACE(cpy1, 3));
/*     */     }
/* 101 */     else if (tselect instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/* 102 */       il.append(methodGen.loadDOM());
/* 103 */       this._select.translate(classGen, methodGen);
/* 104 */       il.append(methodGen.loadHandler());
/* 105 */       il.append((Instruction)new INVOKEINTERFACE(cpy2, 3));
/*     */     }
/* 107 */     else if (tselect instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/* 108 */       this._select.translate(classGen, methodGen);
/*     */       
/* 110 */       il.append((Instruction)InstructionConstants.DUP);
/* 111 */       il.append((Instruction)new INVOKEINTERFACE(getDoc, 1));
/* 112 */       il.append(methodGen.loadHandler());
/* 113 */       il.append((Instruction)new INVOKEINTERFACE(cpy2, 3));
/*     */     }
/* 115 */     else if (tselect instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType) {
/* 116 */       this._select.translate(classGen, methodGen);
/* 117 */       il.append(methodGen.loadHandler());
/* 118 */       il.append(methodGen.loadCurrentNode());
/* 119 */       il.append(methodGen.loadDOM());
/* 120 */       int copy = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "copy", "(Ljava/lang/Object;Lorg/apache/xml/serializer/SerializationHandler;ILorg/apache/xalan/xsltc/DOM;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 127 */       il.append((Instruction)new INVOKESTATIC(copy));
/*     */     } else {
/*     */       
/* 130 */       il.append(classGen.loadTranslet());
/* 131 */       this._select.translate(classGen, methodGen);
/* 132 */       il.append(methodGen.loadHandler());
/* 133 */       il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "characters", "(Ljava/lang/String;Lorg/apache/xml/serializer/SerializationHandler;)V")));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/CopyOf.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */