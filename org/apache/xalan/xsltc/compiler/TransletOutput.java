/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class TransletOutput
/*     */   extends Instruction
/*     */ {
/*     */   private Expression _filename;
/*     */   private boolean _append;
/*     */   
/*     */   public void display(int indent) {
/*  46 */     indent(indent);
/*  47 */     Util.println("TransletOutput: " + this._filename);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  56 */     String filename = getAttribute("file");
/*     */ 
/*     */ 
/*     */     
/*  60 */     String append = getAttribute("append");
/*     */ 
/*     */     
/*  63 */     if (filename == null || filename.equals("")) {
/*  64 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "file");
/*     */     }
/*     */ 
/*     */     
/*  68 */     this._filename = AttributeValue.create(this, filename, parser);
/*     */     
/*  70 */     if (append != null && (append.toLowerCase().equals("yes") || append.toLowerCase().equals("true"))) {
/*     */       
/*  72 */       this._append = true;
/*     */     } else {
/*     */       
/*  75 */       this._append = false;
/*     */     } 
/*  77 */     parseChildren(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  84 */     Type type = this._filename.typeCheck(stable);
/*  85 */     if (!(type instanceof org.apache.xalan.xsltc.compiler.util.StringType)) {
/*  86 */       this._filename = new CastExpr(this._filename, Type.String);
/*     */     }
/*  88 */     typeCheckContents(stable);
/*  89 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  97 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  98 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 101 */     il.append(methodGen.loadHandler());
/*     */     
/* 103 */     int open = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "openOutputHandler", "(Ljava/lang/String;Z)Lorg/apache/xml/serializer/SerializationHandler;");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     int close = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "closeOutputHandler", "(Lorg/apache/xml/serializer/SerializationHandler;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     il.append(classGen.loadTranslet());
/* 114 */     this._filename.translate(classGen, methodGen);
/* 115 */     il.append((CompoundInstruction)new PUSH(cpg, this._append));
/* 116 */     il.append((Instruction)new INVOKEVIRTUAL(open));
/*     */ 
/*     */     
/* 119 */     il.append(methodGen.storeHandler());
/*     */ 
/*     */     
/* 122 */     translateContents(classGen, methodGen);
/*     */ 
/*     */     
/* 125 */     il.append(classGen.loadTranslet());
/* 126 */     il.append(methodGen.loadHandler());
/* 127 */     il.append((Instruction)new INVOKEVIRTUAL(close));
/*     */ 
/*     */     
/* 130 */     il.append(methodGen.storeHandler());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/TransletOutput.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */