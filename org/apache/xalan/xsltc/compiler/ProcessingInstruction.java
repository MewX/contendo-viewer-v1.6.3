/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETFIELD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
/*     */ import org.apache.xml.utils.XMLChar;
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
/*     */ final class ProcessingInstruction
/*     */   extends Instruction
/*     */ {
/*     */   private AttributeValue _name;
/*     */   private boolean _isLiteral = false;
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  49 */     String name = getAttribute("name");
/*     */     
/*  51 */     if (name.length() > 0) {
/*  52 */       this._isLiteral = Util.isLiteral(name);
/*  53 */       if (this._isLiteral && 
/*  54 */         !XMLChar.isValidNCName(name)) {
/*  55 */         ErrorMsg err = new ErrorMsg("INVALID_NCNAME_ERR", name, this);
/*  56 */         parser.reportError(3, err);
/*     */       } 
/*     */       
/*  59 */       this._name = AttributeValue.create(this, name, parser);
/*     */     } else {
/*     */       
/*  62 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "name");
/*     */     } 
/*  64 */     if (name.equals("xml")) {
/*  65 */       reportError(this, parser, "ILLEGAL_PI_ERR", "xml");
/*     */     }
/*  67 */     parseChildren(parser);
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  71 */     this._name.typeCheck(stable);
/*  72 */     typeCheckContents(stable);
/*  73 */     return Type.Void;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  77 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  78 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/*  80 */     if (!this._isLiteral) {
/*     */       
/*  82 */       LocalVariableGen nameValue = methodGen.addLocalVariable2("nameValue", Util.getJCRefType("Ljava/lang/String;"), il.getEnd());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  87 */       this._name.translate(classGen, methodGen);
/*  88 */       il.append((Instruction)new ASTORE(nameValue.getIndex()));
/*  89 */       il.append((Instruction)new ALOAD(nameValue.getIndex()));
/*     */ 
/*     */       
/*  92 */       int check = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "checkNCName", "(Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */       
/*  96 */       il.append((Instruction)new INVOKESTATIC(check));
/*     */ 
/*     */       
/*  99 */       il.append(methodGen.loadHandler());
/* 100 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/* 103 */       il.append((Instruction)new ALOAD(nameValue.getIndex()));
/*     */     } else {
/*     */       
/* 106 */       il.append(methodGen.loadHandler());
/* 107 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/* 110 */       this._name.translate(classGen, methodGen);
/*     */     } 
/*     */ 
/*     */     
/* 114 */     il.append(classGen.loadTranslet());
/* 115 */     il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "stringValueHandler", "Lorg/apache/xalan/xsltc/runtime/StringValueHandler;")));
/*     */ 
/*     */     
/* 118 */     il.append((Instruction)InstructionConstants.DUP);
/* 119 */     il.append(methodGen.storeHandler());
/*     */ 
/*     */     
/* 122 */     translateContents(classGen, methodGen);
/*     */ 
/*     */     
/* 125 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.StringValueHandler", "getValueOfPI", "()Ljava/lang/String;")));
/*     */ 
/*     */ 
/*     */     
/* 129 */     int processingInstruction = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "processingInstruction", "(Ljava/lang/String;Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */     
/* 133 */     il.append((Instruction)new INVOKEINTERFACE(processingInstruction, 3));
/*     */     
/* 135 */     il.append(methodGen.storeHandler());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ProcessingInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */