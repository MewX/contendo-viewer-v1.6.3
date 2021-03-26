/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
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
/*     */ final class WithParam
/*     */   extends Instruction
/*     */ {
/*     */   private QName _name;
/*     */   protected String _escapedName;
/*     */   private Expression _select;
/*     */   private boolean _doParameterOptimization = false;
/*     */   
/*     */   public void display(int indent) {
/*  70 */     indent(indent);
/*  71 */     Util.println("with-param " + this._name);
/*  72 */     if (this._select != null) {
/*  73 */       indent(indent + 4);
/*  74 */       Util.println("select " + this._select.toString());
/*     */     } 
/*  76 */     displayContents(indent + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEscapedName() {
/*  83 */     return this._escapedName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QName getName() {
/*  90 */     return this._name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(QName name) {
/*  97 */     this._name = name;
/*  98 */     this._escapedName = Util.escape(name.getStringRep());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoParameterOptimization(boolean flag) {
/* 105 */     this._doParameterOptimization = flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/* 113 */     String name = getAttribute("name");
/* 114 */     if (name.length() > 0) {
/* 115 */       if (!XMLChar.isValidQName(name)) {
/* 116 */         ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", name, this);
/*     */         
/* 118 */         parser.reportError(3, err);
/*     */       } 
/* 120 */       setName(parser.getQNameIgnoreDefaultNs(name));
/*     */     } else {
/*     */       
/* 123 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "name");
/*     */     } 
/*     */     
/* 126 */     String select = getAttribute("select");
/* 127 */     if (select.length() > 0) {
/* 128 */       this._select = parser.parseExpression(this, "select", null);
/*     */     }
/*     */     
/* 131 */     parseChildren(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 139 */     if (this._select != null) {
/* 140 */       Type tselect = this._select.typeCheck(stable);
/* 141 */       if (!(tselect instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType)) {
/* 142 */         this._select = new CastExpr(this._select, Type.Reference);
/*     */       }
/*     */     } else {
/*     */       
/* 146 */       typeCheckContents(stable);
/*     */     } 
/* 148 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateValue(ClassGenerator classGen, MethodGenerator methodGen) {
/* 158 */     if (this._select != null) {
/* 159 */       this._select.translate(classGen, methodGen);
/* 160 */       this._select.startIterator(classGen, methodGen);
/*     */     
/*     */     }
/* 163 */     else if (hasContents()) {
/* 164 */       compileResultTree(classGen, methodGen);
/*     */     }
/*     */     else {
/*     */       
/* 168 */       ConstantPoolGen cpg = classGen.getConstantPool();
/* 169 */       InstructionList il = methodGen.getInstructionList();
/* 170 */       il.append((CompoundInstruction)new PUSH(cpg, ""));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 180 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 181 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 184 */     if (this._doParameterOptimization) {
/* 185 */       translateValue(classGen, methodGen);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 190 */     String name = Util.escape(getEscapedName());
/*     */ 
/*     */     
/* 193 */     il.append(classGen.loadTranslet());
/*     */ 
/*     */     
/* 196 */     il.append((CompoundInstruction)new PUSH(cpg, name));
/*     */     
/* 198 */     translateValue(classGen, methodGen);
/*     */     
/* 200 */     il.append((CompoundInstruction)new PUSH(cpg, false));
/*     */     
/* 202 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addParameter", "(Ljava/lang/String;Ljava/lang/Object;Z)Ljava/lang/Object;")));
/*     */ 
/*     */     
/* 205 */     il.append((Instruction)InstructionConstants.POP);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/WithParam.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */