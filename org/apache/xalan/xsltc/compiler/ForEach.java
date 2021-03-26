/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFGT;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class ForEach
/*     */   extends Instruction
/*     */ {
/*     */   private Expression _select;
/*     */   private Type _type;
/*     */   
/*     */   public void display(int indent) {
/*  53 */     indent(indent);
/*  54 */     Util.println("ForEach");
/*  55 */     indent(indent + 4);
/*  56 */     Util.println("select " + this._select.toString());
/*  57 */     displayContents(indent + 4);
/*     */   }
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  61 */     this._select = parser.parseExpression(this, "select", null);
/*     */     
/*  63 */     parseChildren(parser);
/*     */ 
/*     */     
/*  66 */     if (this._select.isDummy()) {
/*  67 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "select");
/*     */     }
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  72 */     this._type = this._select.typeCheck(stable);
/*     */     
/*  74 */     if (this._type instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType || this._type instanceof org.apache.xalan.xsltc.compiler.util.NodeType) {
/*  75 */       this._select = new CastExpr(this._select, Type.NodeSet);
/*  76 */       typeCheckContents(stable);
/*  77 */       return Type.Void;
/*     */     } 
/*  79 */     if (this._type instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType || this._type instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/*  80 */       typeCheckContents(stable);
/*  81 */       return Type.Void;
/*     */     } 
/*  83 */     throw new TypeCheckError(this);
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  87 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  88 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/*  91 */     il.append(methodGen.loadCurrentNode());
/*  92 */     il.append(methodGen.loadIterator());
/*     */ 
/*     */     
/*  95 */     Vector sortObjects = new Vector();
/*  96 */     Enumeration children = elements();
/*  97 */     while (children.hasMoreElements()) {
/*  98 */       Object child = children.nextElement();
/*  99 */       if (child instanceof Sort) {
/* 100 */         sortObjects.addElement(child);
/*     */       }
/*     */     } 
/*     */     
/* 104 */     if (this._type != null && this._type instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/*     */       
/* 106 */       il.append(methodGen.loadDOM());
/*     */ 
/*     */       
/* 109 */       if (sortObjects.size() > 0) {
/* 110 */         ErrorMsg msg = new ErrorMsg("RESULT_TREE_SORT_ERR", this);
/* 111 */         getParser().reportError(4, msg);
/*     */       } 
/*     */ 
/*     */       
/* 115 */       this._select.translate(classGen, methodGen);
/*     */       
/* 117 */       this._type.translateTo(classGen, methodGen, Type.NodeSet);
/*     */       
/* 119 */       il.append((Instruction)InstructionConstants.SWAP);
/* 120 */       il.append(methodGen.storeDOM());
/*     */     }
/*     */     else {
/*     */       
/* 124 */       if (sortObjects.size() > 0) {
/* 125 */         Sort.translateSortIterator(classGen, methodGen, this._select, sortObjects);
/*     */       }
/*     */       else {
/*     */         
/* 129 */         this._select.translate(classGen, methodGen);
/*     */       } 
/*     */       
/* 132 */       if (!(this._type instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType)) {
/* 133 */         il.append(methodGen.loadContextNode());
/* 134 */         il.append(methodGen.setStartNode());
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 140 */     il.append(methodGen.storeIterator());
/*     */ 
/*     */     
/* 143 */     initializeVariables(classGen, methodGen);
/*     */     
/* 145 */     BranchHandle nextNode = il.append((BranchInstruction)new GOTO(null));
/* 146 */     InstructionHandle loop = il.append(InstructionConstants.NOP);
/*     */     
/* 148 */     translateContents(classGen, methodGen);
/*     */     
/* 150 */     nextNode.setTarget(il.append(methodGen.loadIterator()));
/* 151 */     il.append(methodGen.nextNode());
/* 152 */     il.append((Instruction)InstructionConstants.DUP);
/* 153 */     il.append(methodGen.storeCurrentNode());
/* 154 */     il.append((BranchInstruction)new IFGT(loop));
/*     */ 
/*     */     
/* 157 */     if (this._type != null && this._type instanceof org.apache.xalan.xsltc.compiler.util.ResultTreeType) {
/* 158 */       il.append(methodGen.storeDOM());
/*     */     }
/*     */ 
/*     */     
/* 162 */     il.append(methodGen.storeIterator());
/* 163 */     il.append(methodGen.storeCurrentNode());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeVariables(ClassGenerator classGen, MethodGenerator methodGen) {
/* 184 */     int n = elementCount();
/* 185 */     for (int i = 0; i < n; i++) {
/* 186 */       Object child = getContents().elementAt(i);
/* 187 */       if (child instanceof Variable) {
/* 188 */         Variable var = (Variable)child;
/* 189 */         var.initialize(classGen, methodGen);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ForEach.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */