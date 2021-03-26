/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
/*     */ import org.apache.bcel.generic.NEW;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
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
/*     */ class VariableBase
/*     */   extends TopLevelElement
/*     */ {
/*     */   protected QName _name;
/*     */   protected String _escapedName;
/*     */   protected Type _type;
/*     */   protected boolean _isLocal;
/*     */   protected LocalVariableGen _local;
/*     */   protected Instruction _loadInstruction;
/*     */   protected Instruction _storeInstruction;
/*     */   protected Expression _select;
/*     */   protected String select;
/*  59 */   protected Vector _refs = new Vector(2);
/*     */ 
/*     */   
/*  62 */   protected Vector _dependencies = null;
/*     */ 
/*     */   
/*     */   protected boolean _ignore = false;
/*     */ 
/*     */   
/*  68 */   protected int _weight = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disable() {
/*  74 */     this._ignore = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReference(VariableRefBase vref) {
/*  82 */     this._refs.addElement(vref);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeReference(VariableRefBase vref) {
/*  90 */     this._refs.remove(vref);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDependency(VariableBase other) {
/*  97 */     if (this._dependencies == null) {
/*  98 */       this._dependencies = new Vector();
/*     */     }
/* 100 */     if (!this._dependencies.contains(other)) {
/* 101 */       this._dependencies.addElement(other);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getDependencies() {
/* 109 */     return this._dependencies;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mapRegister(MethodGenerator methodGen) {
/* 116 */     if (this._local == null) {
/* 117 */       InstructionList il = methodGen.getInstructionList();
/* 118 */       String name = getEscapedName();
/* 119 */       Type varType = this._type.toJCType();
/* 120 */       this._local = methodGen.addLocalVariable2(name, varType, il.getEnd());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unmapRegister(MethodGenerator methodGen) {
/* 129 */     if (this._refs.isEmpty() && this._local != null) {
/* 130 */       this._local.setEnd(methodGen.getInstructionList().getEnd());
/* 131 */       methodGen.removeLocalVariable(this._local);
/* 132 */       this._refs = null;
/* 133 */       this._local = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction loadInstruction() {
/* 142 */     Instruction instr = this._loadInstruction;
/* 143 */     if (this._loadInstruction == null) {
/* 144 */       this._loadInstruction = this._type.LOAD(this._local.getIndex());
/*     */     }
/* 146 */     return this._loadInstruction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction storeInstruction() {
/* 154 */     Instruction instr = this._storeInstruction;
/* 155 */     if (this._storeInstruction == null) {
/* 156 */       this._storeInstruction = this._type.STORE(this._local.getIndex());
/*     */     }
/* 158 */     return this._storeInstruction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getExpression() {
/* 165 */     return this._select;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 172 */     return "variable(" + this._name + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void display(int indent) {
/* 179 */     indent(indent);
/* 180 */     System.out.println("Variable " + this._name);
/* 181 */     if (this._select != null) {
/* 182 */       indent(indent + 4);
/* 183 */       System.out.println("select " + this._select.toString());
/*     */     } 
/* 185 */     displayContents(indent + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType() {
/* 192 */     return this._type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QName getName() {
/* 200 */     return this._name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEscapedName() {
/* 207 */     return this._escapedName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(QName name) {
/* 214 */     this._name = name;
/* 215 */     this._escapedName = Util.escape(name.getStringRep());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLocal() {
/* 222 */     return this._isLocal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/* 230 */     String name = getAttribute("name");
/*     */     
/* 232 */     if (name.length() > 0) {
/* 233 */       if (!XMLChar.isValidQName(name)) {
/* 234 */         ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", name, this);
/* 235 */         parser.reportError(3, err);
/*     */       } 
/* 237 */       setName(parser.getQNameIgnoreDefaultNs(name));
/*     */     } else {
/*     */       
/* 240 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "name");
/*     */     } 
/*     */     
/* 243 */     VariableBase other = parser.lookupVariable(this._name);
/* 244 */     if (other != null && other.getParent() == getParent()) {
/* 245 */       reportError(this, parser, "VARIABLE_REDEF_ERR", name);
/*     */     }
/*     */     
/* 248 */     this.select = getAttribute("select");
/* 249 */     if (this.select.length() > 0) {
/* 250 */       this._select = getParser().parseExpression(this, "select", null);
/* 251 */       if (this._select.isDummy()) {
/* 252 */         reportError(this, parser, "REQUIRED_ATTR_ERR", "select");
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 258 */     parseChildren(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateValue(ClassGenerator classGen, MethodGenerator methodGen) {
/* 268 */     if (this._select != null) {
/* 269 */       this._select.translate(classGen, methodGen);
/*     */ 
/*     */       
/* 272 */       if (this._select.getType() instanceof org.apache.xalan.xsltc.compiler.util.NodeSetType) {
/* 273 */         ConstantPoolGen cpg = classGen.getConstantPool();
/* 274 */         InstructionList il = methodGen.getInstructionList();
/*     */         
/* 276 */         int initCNI = cpg.addMethodref("org.apache.xalan.xsltc.dom.CachedNodeListIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 281 */         il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.CachedNodeListIterator")));
/* 282 */         il.append((Instruction)InstructionConstants.DUP_X1);
/* 283 */         il.append((Instruction)InstructionConstants.SWAP);
/*     */         
/* 285 */         il.append((Instruction)new INVOKESPECIAL(initCNI));
/*     */       } 
/* 287 */       this._select.startIterator(classGen, methodGen);
/*     */     
/*     */     }
/* 290 */     else if (hasContents()) {
/* 291 */       compileResultTree(classGen, methodGen);
/*     */     }
/*     */     else {
/*     */       
/* 295 */       ConstantPoolGen cpg = classGen.getConstantPool();
/* 296 */       InstructionList il = methodGen.getInstructionList();
/* 297 */       il.append((CompoundInstruction)new PUSH(cpg, ""));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/VariableBase.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */