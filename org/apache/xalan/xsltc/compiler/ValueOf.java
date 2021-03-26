/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
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
/*     */ final class ValueOf
/*     */   extends Instruction
/*     */ {
/*     */   private Expression _select;
/*     */   private boolean _escaping = true;
/*     */   private boolean _isString = false;
/*     */   
/*     */   public void display(int indent) {
/*  45 */     indent(indent);
/*  46 */     Util.println("ValueOf");
/*  47 */     indent(indent + 4);
/*  48 */     Util.println("select " + this._select.toString());
/*     */   }
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  52 */     this._select = parser.parseExpression(this, "select", null);
/*     */ 
/*     */     
/*  55 */     if (this._select.isDummy()) {
/*  56 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "select");
/*     */       return;
/*     */     } 
/*  59 */     String str = getAttribute("disable-output-escaping");
/*  60 */     if (str != null && str.equals("yes")) this._escaping = false; 
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  64 */     Type type = this._select.typeCheck(stable);
/*     */ 
/*     */     
/*  67 */     if (type != null && !type.identicalTo(Type.Node))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  77 */       if (type.identicalTo(Type.NodeSet)) {
/*  78 */         this._select = new CastExpr(this._select, Type.Node);
/*     */       } else {
/*  80 */         this._isString = true;
/*  81 */         if (!type.identicalTo(Type.String)) {
/*  82 */           this._select = new CastExpr(this._select, Type.String);
/*     */         }
/*  84 */         this._isString = true;
/*     */       } 
/*     */     }
/*  87 */     return Type.Void;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  91 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  92 */     InstructionList il = methodGen.getInstructionList();
/*  93 */     int setEscaping = cpg.addInterfaceMethodref("org/apache/xml/serializer/SerializationHandler", "setEscaping", "(Z)Z");
/*     */ 
/*     */ 
/*     */     
/*  97 */     if (!this._escaping) {
/*  98 */       il.append(methodGen.loadHandler());
/*  99 */       il.append((CompoundInstruction)new PUSH(cpg, false));
/* 100 */       il.append((Instruction)new INVOKEINTERFACE(setEscaping, 2));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     if (this._isString) {
/* 109 */       int characters = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "characters", "(Ljava/lang/String;Lorg/apache/xml/serializer/SerializationHandler;)V");
/*     */ 
/*     */ 
/*     */       
/* 113 */       il.append(classGen.loadTranslet());
/* 114 */       this._select.translate(classGen, methodGen);
/* 115 */       il.append(methodGen.loadHandler());
/* 116 */       il.append((Instruction)new INVOKEVIRTUAL(characters));
/*     */     } else {
/* 118 */       int characters = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "characters", "(ILorg/apache/xml/serializer/SerializationHandler;)V");
/*     */ 
/*     */ 
/*     */       
/* 122 */       il.append(methodGen.loadDOM());
/* 123 */       this._select.translate(classGen, methodGen);
/* 124 */       il.append(methodGen.loadHandler());
/* 125 */       il.append((Instruction)new INVOKEINTERFACE(characters, 3));
/*     */     } 
/*     */ 
/*     */     
/* 129 */     if (!this._escaping) {
/* 130 */       il.append(methodGen.loadHandler());
/* 131 */       il.append((Instruction)InstructionConstants.SWAP);
/* 132 */       il.append((Instruction)new INVOKEINTERFACE(setEscaping, 2));
/* 133 */       il.append((Instruction)InstructionConstants.POP);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ValueOf.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */