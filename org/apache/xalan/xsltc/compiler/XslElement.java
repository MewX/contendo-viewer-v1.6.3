/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
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
/*     */ final class XslElement
/*     */   extends Instruction
/*     */ {
/*     */   private String _prefix;
/*     */   private boolean _ignore = false;
/*     */   private boolean _isLiteralName = true;
/*     */   private AttributeValueTemplate _name;
/*     */   private AttributeValueTemplate _namespace;
/*     */   
/*     */   public void display(int indent) {
/*  55 */     indent(indent);
/*  56 */     Util.println("Element " + this._name);
/*  57 */     displayContents(indent + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean declaresDefaultNS() {
/*  65 */     return false;
/*     */   }
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  69 */     SymbolTable stable = parser.getSymbolTable();
/*     */ 
/*     */     
/*  72 */     String name = getAttribute("name");
/*  73 */     if (name == "") {
/*  74 */       ErrorMsg msg = new ErrorMsg("ILLEGAL_ELEM_NAME_ERR", name, this);
/*     */       
/*  76 */       parser.reportError(4, msg);
/*  77 */       parseChildren(parser);
/*  78 */       this._ignore = true;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  83 */     String namespace = getAttribute("namespace");
/*     */ 
/*     */     
/*  86 */     this._isLiteralName = Util.isLiteral(name);
/*  87 */     if (this._isLiteralName) {
/*  88 */       if (!XMLChar.isValidQName(name)) {
/*  89 */         ErrorMsg msg = new ErrorMsg("ILLEGAL_ELEM_NAME_ERR", name, this);
/*     */         
/*  91 */         parser.reportError(4, msg);
/*  92 */         parseChildren(parser);
/*  93 */         this._ignore = true;
/*     */         
/*     */         return;
/*     */       } 
/*  97 */       QName qname = parser.getQNameSafe(name);
/*  98 */       String prefix = qname.getPrefix();
/*  99 */       String local = qname.getLocalPart();
/*     */       
/* 101 */       if (prefix == null) {
/* 102 */         prefix = "";
/*     */       }
/*     */       
/* 105 */       if (!hasAttribute("namespace")) {
/* 106 */         namespace = lookupNamespace(prefix);
/* 107 */         if (namespace == null) {
/* 108 */           ErrorMsg err = new ErrorMsg("NAMESPACE_UNDEF_ERR", prefix, this);
/*     */           
/* 110 */           parser.reportError(4, err);
/* 111 */           parseChildren(parser);
/* 112 */           this._ignore = true;
/*     */           return;
/*     */         } 
/* 115 */         this._prefix = prefix;
/* 116 */         this._namespace = new AttributeValueTemplate(namespace, parser, this);
/*     */       } else {
/*     */         
/* 119 */         if (prefix == "") {
/* 120 */           if (Util.isLiteral(namespace)) {
/* 121 */             prefix = lookupPrefix(namespace);
/* 122 */             if (prefix == null) {
/* 123 */               prefix = stable.generateNamespacePrefix();
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 128 */           StringBuffer newName = new StringBuffer(prefix);
/* 129 */           if (prefix != "") {
/* 130 */             newName.append(':');
/*     */           }
/* 132 */           name = newName.append(local).toString();
/*     */         } 
/* 134 */         this._prefix = prefix;
/* 135 */         this._namespace = new AttributeValueTemplate(namespace, parser, this);
/*     */       } 
/*     */     } else {
/*     */       
/* 139 */       this._namespace = (namespace == "") ? null : new AttributeValueTemplate(namespace, parser, this);
/*     */     } 
/*     */ 
/*     */     
/* 143 */     this._name = new AttributeValueTemplate(name, parser, this);
/*     */     
/* 145 */     String useSets = getAttribute("use-attribute-sets");
/* 146 */     if (useSets.length() > 0) {
/* 147 */       if (!Util.isValidQNames(useSets)) {
/* 148 */         ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", useSets, this);
/* 149 */         parser.reportError(3, err);
/*     */       } 
/* 151 */       setFirstElement(new UseAttributeSets(useSets, parser));
/*     */     } 
/*     */     
/* 154 */     parseChildren(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 161 */     if (!this._ignore) {
/* 162 */       this._name.typeCheck(stable);
/* 163 */       if (this._namespace != null) {
/* 164 */         this._namespace.typeCheck(stable);
/*     */       }
/*     */     } 
/* 167 */     typeCheckContents(stable);
/* 168 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateLiteral(ClassGenerator classGen, MethodGenerator methodGen) {
/* 177 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 178 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 180 */     if (!this._ignore) {
/* 181 */       il.append(methodGen.loadHandler());
/* 182 */       this._name.translate(classGen, methodGen);
/* 183 */       il.append((Instruction)InstructionConstants.DUP2);
/* 184 */       il.append(methodGen.startElement());
/*     */       
/* 186 */       if (this._namespace != null) {
/* 187 */         il.append(methodGen.loadHandler());
/* 188 */         il.append((CompoundInstruction)new PUSH(cpg, this._prefix));
/* 189 */         this._namespace.translate(classGen, methodGen);
/* 190 */         il.append(methodGen.namespace());
/*     */       } 
/*     */     } 
/*     */     
/* 194 */     translateContents(classGen, methodGen);
/*     */     
/* 196 */     if (!this._ignore) {
/* 197 */       il.append(methodGen.endElement());
/*     */     }
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
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 210 */     LocalVariableGen local = null;
/* 211 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 212 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 215 */     if (this._isLiteralName) {
/* 216 */       translateLiteral(classGen, methodGen);
/*     */       
/*     */       return;
/*     */     } 
/* 220 */     if (!this._ignore) {
/*     */ 
/*     */       
/* 223 */       LocalVariableGen nameValue = methodGen.addLocalVariable2("nameValue", Util.getJCRefType("Ljava/lang/String;"), il.getEnd());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       this._name.translate(classGen, methodGen);
/* 229 */       il.append((Instruction)new ASTORE(nameValue.getIndex()));
/* 230 */       il.append((Instruction)new ALOAD(nameValue.getIndex()));
/*     */ 
/*     */       
/* 233 */       int check = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "checkQName", "(Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */       
/* 237 */       il.append((Instruction)new INVOKESTATIC(check));
/*     */ 
/*     */       
/* 240 */       il.append(methodGen.loadHandler());
/*     */ 
/*     */       
/* 243 */       il.append((Instruction)new ALOAD(nameValue.getIndex()));
/*     */       
/* 245 */       if (this._namespace != null) {
/* 246 */         this._namespace.translate(classGen, methodGen);
/*     */       } else {
/*     */         
/* 249 */         il.append(InstructionConstants.ACONST_NULL);
/*     */       } 
/*     */ 
/*     */       
/* 253 */       il.append(methodGen.loadHandler());
/* 254 */       il.append(methodGen.loadDOM());
/* 255 */       il.append(methodGen.loadCurrentNode());
/*     */ 
/*     */       
/* 258 */       il.append((Instruction)new INVOKESTATIC(cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "startXslElement", "(Ljava/lang/String;Ljava/lang/String;Lorg/apache/xml/serializer/SerializationHandler;Lorg/apache/xalan/xsltc/DOM;I)Ljava/lang/String;")));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 268 */     translateContents(classGen, methodGen);
/*     */     
/* 270 */     if (!this._ignore) {
/* 271 */       il.append(methodGen.endElement());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateContents(ClassGenerator classGen, MethodGenerator methodGen) {
/* 281 */     int n = elementCount();
/* 282 */     for (int i = 0; i < n; i++) {
/* 283 */       SyntaxTreeNode item = getContents().elementAt(i);
/*     */       
/* 285 */       if (!this._ignore || !(item instanceof XslAttribute))
/* 286 */         item.translate(classGen, methodGen); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/XslElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */