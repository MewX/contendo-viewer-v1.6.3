/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.NEW;
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
/*     */ final class Message
/*     */   extends Instruction
/*     */ {
/*     */   private boolean _terminate = false;
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  42 */     String termstr = getAttribute("terminate");
/*  43 */     if (termstr != null) {
/*  44 */       this._terminate = termstr.equals("yes");
/*     */     }
/*  46 */     parseChildren(parser);
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  50 */     typeCheckContents(stable);
/*  51 */     return Type.Void;
/*     */   }
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*     */     SyntaxTreeNode child;
/*  55 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  56 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/*  59 */     il.append(classGen.loadTranslet());
/*     */     
/*  61 */     switch (elementCount()) {
/*     */       case 0:
/*  63 */         il.append((CompoundInstruction)new PUSH(cpg, ""));
/*     */         break;
/*     */       case 1:
/*  66 */         child = (SyntaxTreeNode)elementAt(0);
/*  67 */         if (child instanceof Text) {
/*  68 */           il.append((CompoundInstruction)new PUSH(cpg, ((Text)child).getText()));
/*     */           break;
/*     */         } 
/*     */ 
/*     */       
/*     */       default:
/*  74 */         il.append(methodGen.loadHandler());
/*     */ 
/*     */         
/*  77 */         il.append((Instruction)new NEW(cpg.addClass("org.apache.xml.serializer.ToXMLStream")));
/*  78 */         il.append(methodGen.storeHandler());
/*     */ 
/*     */         
/*  81 */         il.append((Instruction)new NEW(cpg.addClass("java.io.StringWriter")));
/*  82 */         il.append((Instruction)InstructionConstants.DUP);
/*  83 */         il.append((Instruction)InstructionConstants.DUP);
/*  84 */         il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref("java.io.StringWriter", "<init>", "()V")));
/*     */ 
/*     */ 
/*     */         
/*  88 */         il.append(methodGen.loadHandler());
/*  89 */         il.append((Instruction)new INVOKESPECIAL(cpg.addMethodref("org.apache.xml.serializer.ToXMLStream", "<init>", "()V")));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  94 */         il.append(methodGen.loadHandler());
/*  95 */         il.append((Instruction)InstructionConstants.SWAP);
/*  96 */         il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xml.serializer.SerializerBase", "setWriter", "(Ljava/io/Writer;)V")));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 101 */         il.append(methodGen.loadHandler());
/* 102 */         il.append((CompoundInstruction)new PUSH(cpg, "UTF-8"));
/* 103 */         il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xml.serializer.SerializerBase", "setEncoding", "(Ljava/lang/String;)V")));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 108 */         il.append(methodGen.loadHandler());
/* 109 */         il.append(InstructionConstants.ICONST_1);
/* 110 */         il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xml.serializer.SerializerBase", "setOmitXMLDeclaration", "(Z)V")));
/*     */ 
/*     */ 
/*     */         
/* 114 */         il.append(methodGen.loadHandler());
/* 115 */         il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xml.serializer.SerializerBase", "startDocument", "()V")));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 120 */         translateContents(classGen, methodGen);
/*     */         
/* 122 */         il.append(methodGen.loadHandler());
/* 123 */         il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xml.serializer.SerializerBase", "endDocument", "()V")));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 128 */         il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("java.io.StringWriter", "toString", "()Ljava/lang/String;")));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 133 */         il.append((Instruction)InstructionConstants.SWAP);
/* 134 */         il.append(methodGen.storeHandler());
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 139 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "displayMessage", "(Ljava/lang/String;)V")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     if (this._terminate == true) {
/*     */       
/* 147 */       int einit = cpg.addMethodref("java.lang.RuntimeException", "<init>", "(Ljava/lang/String;)V");
/*     */ 
/*     */       
/* 150 */       il.append((Instruction)new NEW(cpg.addClass("java.lang.RuntimeException")));
/* 151 */       il.append((Instruction)InstructionConstants.DUP);
/* 152 */       il.append((CompoundInstruction)new PUSH(cpg, "Termination forced by an xsl:message instruction"));
/*     */       
/* 154 */       il.append((Instruction)new INVOKESPECIAL(einit));
/* 155 */       il.append(InstructionConstants.ATHROW);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Message.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */