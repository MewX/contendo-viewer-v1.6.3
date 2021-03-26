/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETFIELD;
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
/*     */ final class Comment
/*     */   extends Instruction
/*     */ {
/*     */   public void parseContents(Parser parser) {
/*  41 */     parseChildren(parser);
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  45 */     typeCheckContents(stable);
/*  46 */     return Type.String;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  50 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  51 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/*  54 */     Text rawText = null;
/*  55 */     if (elementCount() == 1) {
/*  56 */       Object content = elementAt(0);
/*  57 */       if (content instanceof Text) {
/*  58 */         rawText = (Text)content;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     if (rawText != null) {
/*  67 */       il.append(methodGen.loadHandler());
/*     */       
/*  69 */       if (rawText.canLoadAsArrayOffsetLength()) {
/*  70 */         rawText.loadAsArrayOffsetLength(classGen, methodGen);
/*  71 */         int comment = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "comment", "([CII)V");
/*     */ 
/*     */ 
/*     */         
/*  75 */         il.append((Instruction)new INVOKEINTERFACE(comment, 4));
/*     */       } else {
/*  77 */         il.append((CompoundInstruction)new PUSH(cpg, rawText.getText()));
/*  78 */         int comment = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "comment", "(Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */         
/*  82 */         il.append((Instruction)new INVOKEINTERFACE(comment, 2));
/*     */       } 
/*     */     } else {
/*     */       
/*  86 */       il.append(methodGen.loadHandler());
/*  87 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/*  90 */       il.append(classGen.loadTranslet());
/*  91 */       il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "stringValueHandler", "Lorg/apache/xalan/xsltc/runtime/StringValueHandler;")));
/*     */ 
/*     */       
/*  94 */       il.append((Instruction)InstructionConstants.DUP);
/*  95 */       il.append(methodGen.storeHandler());
/*     */ 
/*     */       
/*  98 */       translateContents(classGen, methodGen);
/*     */ 
/*     */       
/* 101 */       il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.StringValueHandler", "getValue", "()Ljava/lang/String;")));
/*     */ 
/*     */ 
/*     */       
/* 105 */       int comment = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "comment", "(Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */       
/* 109 */       il.append((Instruction)new INVOKEINTERFACE(comment, 2));
/*     */       
/* 111 */       il.append(methodGen.storeHandler());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Comment.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */