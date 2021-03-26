/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
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
/*     */ final class UnsupportedElement
/*     */   extends SyntaxTreeNode
/*     */ {
/*  41 */   private Vector _fallbacks = null;
/*  42 */   private ErrorMsg _message = null;
/*     */ 
/*     */   
/*     */   private boolean _isExtension = false;
/*     */ 
/*     */   
/*     */   public UnsupportedElement(String uri, String prefix, String local, boolean isExtension) {
/*  49 */     super(uri, prefix, local);
/*  50 */     this._isExtension = isExtension;
/*     */   }
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
/*     */   public void setErrorMessage(ErrorMsg message) {
/*  63 */     this._message = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void display(int indent) {
/*  70 */     indent(indent);
/*  71 */     Util.println("Unsupported element = " + this._qname.getNamespace() + ":" + this._qname.getLocalPart());
/*     */     
/*  73 */     displayContents(indent + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processFallbacks(Parser parser) {
/*  82 */     Vector children = getContents();
/*  83 */     if (children != null) {
/*  84 */       int count = children.size();
/*  85 */       for (int i = 0; i < count; i++) {
/*  86 */         SyntaxTreeNode child = children.elementAt(i);
/*  87 */         if (child instanceof Fallback) {
/*  88 */           Fallback fallback = (Fallback)child;
/*  89 */           fallback.activate();
/*  90 */           fallback.parseContents(parser);
/*  91 */           if (this._fallbacks == null) {
/*  92 */             this._fallbacks = new Vector();
/*     */           }
/*  94 */           this._fallbacks.addElement(child);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/* 104 */     processFallbacks(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 111 */     if (this._fallbacks != null) {
/* 112 */       int count = this._fallbacks.size();
/* 113 */       for (int i = 0; i < count; i++) {
/* 114 */         Fallback fallback = this._fallbacks.elementAt(i);
/* 115 */         fallback.typeCheck(stable);
/*     */       } 
/*     */     } 
/* 118 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 125 */     if (this._fallbacks != null) {
/* 126 */       int count = this._fallbacks.size();
/* 127 */       for (int i = 0; i < count; i++) {
/* 128 */         Fallback fallback = this._fallbacks.elementAt(i);
/* 129 */         fallback.translate(classGen, methodGen);
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 138 */       ConstantPoolGen cpg = classGen.getConstantPool();
/* 139 */       InstructionList il = methodGen.getInstructionList();
/*     */       
/* 141 */       int unsupportedElem = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "unsupported_ElementF", "(Ljava/lang/String;Z)V");
/*     */       
/* 143 */       il.append((CompoundInstruction)new PUSH(cpg, getQName().toString()));
/* 144 */       il.append((CompoundInstruction)new PUSH(cpg, this._isExtension));
/* 145 */       il.append((Instruction)new INVOKESTATIC(unsupportedElem));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/UnsupportedElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */