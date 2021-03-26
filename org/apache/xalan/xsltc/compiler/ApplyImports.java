/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.NEW;
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
/*     */ final class ApplyImports
/*     */   extends Instruction
/*     */ {
/*     */   private QName _modeName;
/*     */   private String _functionName;
/*     */   private int _precedence;
/*     */   
/*     */   public void display(int indent) {
/*  45 */     indent(indent);
/*  46 */     Util.println("ApplyTemplates");
/*  47 */     indent(indent + 4);
/*  48 */     if (this._modeName != null) {
/*  49 */       indent(indent + 4);
/*  50 */       Util.println("mode " + this._modeName);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasWithParams() {
/*  58 */     return hasContents();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getMinPrecedence(int max) {
/*  69 */     Stylesheet stylesheet = getStylesheet();
/*  70 */     Stylesheet root = getParser().getTopLevelStylesheet();
/*     */     
/*  72 */     int min = max;
/*     */     
/*  74 */     Enumeration templates = root.getContents().elements();
/*  75 */     while (templates.hasMoreElements()) {
/*  76 */       SyntaxTreeNode child = templates.nextElement();
/*  77 */       if (child instanceof Template) {
/*  78 */         Stylesheet curr = child.getStylesheet();
/*  79 */         while (curr != null && curr != stylesheet) {
/*  80 */           if (curr._importedFrom != null) {
/*  81 */             curr = curr._importedFrom; continue;
/*  82 */           }  if (curr._includedFrom != null) {
/*  83 */             curr = curr._includedFrom; continue;
/*     */           } 
/*  85 */           curr = null;
/*     */         } 
/*  87 */         if (curr == stylesheet) {
/*  88 */           int prec = child.getStylesheet().getImportPrecedence();
/*  89 */           if (prec < min) min = prec; 
/*     */         } 
/*     */       } 
/*     */     } 
/*  93 */     return min;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/* 102 */     Stylesheet stylesheet = getStylesheet();
/* 103 */     stylesheet.setTemplateInlining(false);
/*     */ 
/*     */     
/* 106 */     Template template = getTemplate();
/* 107 */     this._modeName = template.getModeName();
/* 108 */     this._precedence = template.getImportPrecedence();
/*     */ 
/*     */     
/* 111 */     stylesheet = parser.getTopLevelStylesheet();
/*     */ 
/*     */ 
/*     */     
/* 115 */     int maxPrecedence = this._precedence;
/* 116 */     int minPrecedence = getMinPrecedence(maxPrecedence);
/* 117 */     Mode mode = stylesheet.getMode(this._modeName);
/* 118 */     this._functionName = mode.functionName(minPrecedence, maxPrecedence);
/*     */     
/* 120 */     parseChildren(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 127 */     typeCheckContents(stable);
/* 128 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 136 */     Stylesheet stylesheet = classGen.getStylesheet();
/* 137 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 138 */     InstructionList il = methodGen.getInstructionList();
/* 139 */     int current = methodGen.getLocalIndex("current");
/*     */ 
/*     */     
/* 142 */     il.append(classGen.loadTranslet());
/* 143 */     il.append(methodGen.loadDOM());
/*     */     
/* 145 */     int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.SingletonIterator", "<init>", "(I)V");
/*     */     
/* 147 */     il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.SingletonIterator")));
/* 148 */     il.append((Instruction)InstructionConstants.DUP);
/* 149 */     il.append(methodGen.loadCurrentNode());
/* 150 */     il.append((Instruction)new INVOKESPECIAL(init));
/*     */     
/* 152 */     il.append(methodGen.loadHandler());
/*     */ 
/*     */     
/* 155 */     String className = classGen.getStylesheet().getClassName();
/* 156 */     String signature = classGen.getApplyTemplatesSig();
/* 157 */     int applyTemplates = cpg.addMethodref(className, this._functionName, signature);
/*     */ 
/*     */     
/* 160 */     il.append((Instruction)new INVOKEVIRTUAL(applyTemplates));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ApplyImports.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */