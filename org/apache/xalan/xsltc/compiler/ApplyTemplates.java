/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class ApplyTemplates
/*     */   extends Instruction
/*     */ {
/*     */   private Expression _select;
/*  47 */   private Type _type = null;
/*     */   private QName _modeName;
/*     */   private String _functionName;
/*     */   
/*     */   public void display(int indent) {
/*  52 */     indent(indent);
/*  53 */     Util.println("ApplyTemplates");
/*  54 */     indent(indent + 4);
/*  55 */     Util.println("select " + this._select.toString());
/*  56 */     if (this._modeName != null) {
/*  57 */       indent(indent + 4);
/*  58 */       Util.println("mode " + this._modeName);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasWithParams() {
/*  63 */     return hasContents();
/*     */   }
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  67 */     String select = getAttribute("select");
/*  68 */     String mode = getAttribute("mode");
/*     */     
/*  70 */     if (select.length() > 0) {
/*  71 */       this._select = parser.parseExpression(this, "select", null);
/*     */     }
/*     */ 
/*     */     
/*  75 */     if (mode.length() > 0) {
/*  76 */       if (!XMLChar.isValidQName(mode)) {
/*  77 */         ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", mode, this);
/*  78 */         parser.reportError(3, err);
/*     */       } 
/*  80 */       this._modeName = parser.getQNameIgnoreDefaultNs(mode);
/*     */     } 
/*     */ 
/*     */     
/*  84 */     this._functionName = parser.getTopLevelStylesheet().getMode(this._modeName).functionName();
/*     */     
/*  86 */     parseChildren(parser);
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  90 */     if (this._select != null) {
/*  91 */       this._type = this._select.typeCheck(stable);
/*  92 */       if (this._type instanceof org.apache.xalan.xsltc.compiler.util.NodeType || this._type instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType) {
/*  93 */         this._select = new CastExpr(this._select, Type.NodeSet);
/*  94 */         this._type = Type.NodeSet;
/*     */       } 
/*  96 */       if (this._type instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType || this._type instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/*  97 */         typeCheckContents(stable);
/*  98 */         return Type.Void;
/*     */       } 
/* 100 */       throw new TypeCheckError(this);
/*     */     } 
/*     */     
/* 103 */     typeCheckContents(stable);
/* 104 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 113 */     boolean setStartNodeCalled = false;
/* 114 */     Stylesheet stylesheet = classGen.getStylesheet();
/* 115 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 116 */     InstructionList il = methodGen.getInstructionList();
/* 117 */     int current = methodGen.getLocalIndex("current");
/*     */ 
/*     */     
/* 120 */     Vector sortObjects = new Vector();
/* 121 */     Enumeration children = elements();
/* 122 */     while (children.hasMoreElements()) {
/* 123 */       Object child = children.nextElement();
/* 124 */       if (child instanceof Sort) {
/* 125 */         sortObjects.addElement(child);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 130 */     if (stylesheet.hasLocalParams() || hasContents()) {
/* 131 */       il.append(classGen.loadTranslet());
/* 132 */       int pushFrame = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "pushParamFrame", "()V");
/*     */ 
/*     */       
/* 135 */       il.append((Instruction)new INVOKEVIRTUAL(pushFrame));
/*     */       
/* 137 */       translateContents(classGen, methodGen);
/*     */     } 
/*     */ 
/*     */     
/* 141 */     il.append(classGen.loadTranslet());
/*     */ 
/*     */     
/* 144 */     if (this._type != null && this._type instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/*     */       
/* 146 */       if (sortObjects.size() > 0) {
/* 147 */         ErrorMsg err = new ErrorMsg("RESULT_TREE_SORT_ERR", this);
/* 148 */         getParser().reportError(4, err);
/*     */       } 
/*     */       
/* 151 */       this._select.translate(classGen, methodGen);
/*     */       
/* 153 */       this._type.translateTo(classGen, methodGen, Type.NodeSet);
/*     */     } else {
/*     */       
/* 156 */       il.append(methodGen.loadDOM());
/*     */ 
/*     */       
/* 159 */       if (sortObjects.size() > 0) {
/* 160 */         Sort.translateSortIterator(classGen, methodGen, this._select, sortObjects);
/*     */         
/* 162 */         int setStartNode = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "setStartNode", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */ 
/*     */         
/* 166 */         il.append(methodGen.loadCurrentNode());
/* 167 */         il.append((Instruction)new INVOKEINTERFACE(setStartNode, 2));
/* 168 */         setStartNodeCalled = true;
/*     */       
/*     */       }
/* 171 */       else if (this._select == null) {
/* 172 */         Mode.compileGetChildren(classGen, methodGen, current);
/*     */       } else {
/* 174 */         this._select.translate(classGen, methodGen);
/*     */       } 
/*     */     } 
/*     */     
/* 178 */     if (this._select != null && !setStartNodeCalled) {
/* 179 */       this._select.startIterator(classGen, methodGen);
/*     */     }
/*     */ 
/*     */     
/* 183 */     String className = classGen.getStylesheet().getClassName();
/* 184 */     il.append(methodGen.loadHandler());
/* 185 */     String applyTemplatesSig = classGen.getApplyTemplatesSig();
/* 186 */     int applyTemplates = cpg.addMethodref(className, this._functionName, applyTemplatesSig);
/*     */ 
/*     */     
/* 189 */     il.append((Instruction)new INVOKEVIRTUAL(applyTemplates));
/*     */ 
/*     */     
/* 192 */     if (stylesheet.hasLocalParams() || hasContents()) {
/* 193 */       il.append(classGen.loadTranslet());
/* 194 */       int popFrame = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "popParamFrame", "()V");
/*     */ 
/*     */       
/* 197 */       il.append((Instruction)new INVOKEVIRTUAL(popFrame));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ApplyTemplates.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */