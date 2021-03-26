/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETFIELD;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
/*     */ import org.apache.xml.serializer.ElemDesc;
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
/*     */ final class XslAttribute
/*     */   extends Instruction
/*     */ {
/*     */   private String _prefix;
/*     */   private AttributeValue _name;
/*  55 */   private AttributeValueTemplate _namespace = null;
/*     */   
/*     */   private boolean _ignore = false;
/*     */   
/*     */   private boolean _isLiteral = false;
/*     */ 
/*     */   
/*     */   public AttributeValue getName() {
/*  63 */     return this._name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void display(int indent) {
/*  70 */     indent(indent);
/*  71 */     Util.println("Attribute " + this._name);
/*  72 */     displayContents(indent + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  79 */     boolean generated = false;
/*  80 */     SymbolTable stable = parser.getSymbolTable();
/*     */     
/*  82 */     String name = getAttribute("name");
/*  83 */     String namespace = getAttribute("namespace");
/*  84 */     QName qname = parser.getQName(name, false);
/*  85 */     String prefix = qname.getPrefix();
/*     */     
/*  87 */     if ((prefix != null && prefix.equals("xmlns")) || name.equals("xmlns")) {
/*  88 */       reportError(this, parser, "ILLEGAL_ATTR_NAME_ERR", name);
/*     */       
/*     */       return;
/*     */     } 
/*  92 */     this._isLiteral = Util.isLiteral(name);
/*  93 */     if (this._isLiteral && 
/*  94 */       !XMLChar.isValidQName(name)) {
/*  95 */       reportError(this, parser, "ILLEGAL_ATTR_NAME_ERR", name);
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 101 */     SyntaxTreeNode parent = getParent();
/* 102 */     Vector siblings = parent.getContents();
/* 103 */     for (int i = 0; i < parent.elementCount(); i++) {
/* 104 */       SyntaxTreeNode item = siblings.elementAt(i);
/* 105 */       if (item == this) {
/*     */         break;
/*     */       }
/* 108 */       if (!(item instanceof XslAttribute) && 
/* 109 */         !(item instanceof UseAttributeSets) && 
/* 110 */         !(item instanceof LiteralAttribute) && 
/* 111 */         !(item instanceof Text))
/*     */       {
/*     */ 
/*     */         
/* 115 */         if (!(item instanceof If) && 
/* 116 */           !(item instanceof Choose) && 
/* 117 */           !(item instanceof CopyOf) && 
/* 118 */           !(item instanceof VariableBase))
/*     */         {
/*     */           
/* 121 */           reportWarning(this, parser, "STRAY_ATTRIBUTE_ERR", name);
/*     */         }
/*     */       }
/*     */     } 
/* 125 */     if (namespace != null && namespace != "") {
/* 126 */       this._prefix = lookupPrefix(namespace);
/* 127 */       this._namespace = new AttributeValueTemplate(namespace, parser, this);
/*     */     
/*     */     }
/* 130 */     else if (prefix != null && prefix != "") {
/* 131 */       this._prefix = prefix;
/* 132 */       namespace = lookupNamespace(prefix);
/* 133 */       if (namespace != null) {
/* 134 */         this._namespace = new AttributeValueTemplate(namespace, parser, this);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 139 */     if (this._namespace != null) {
/*     */       
/* 141 */       if (this._prefix == null || this._prefix == "") {
/* 142 */         if (prefix != null) {
/* 143 */           this._prefix = prefix;
/*     */         } else {
/*     */           
/* 146 */           this._prefix = stable.generateNamespacePrefix();
/* 147 */           generated = true;
/*     */         }
/*     */       
/* 150 */       } else if (prefix != null && !prefix.equals(this._prefix)) {
/* 151 */         this._prefix = prefix;
/*     */       } 
/*     */       
/* 154 */       name = this._prefix + ":" + qname.getLocalPart();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 161 */       if (parent instanceof LiteralElement && !generated) {
/* 162 */         ((LiteralElement)parent).registerNamespace(this._prefix, namespace, stable, false);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 168 */     if (parent instanceof LiteralElement) {
/* 169 */       ((LiteralElement)parent).addAttribute(this);
/*     */     }
/*     */     
/* 172 */     this._name = AttributeValue.create(this, name, parser);
/* 173 */     parseChildren(parser);
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 177 */     if (!this._ignore) {
/* 178 */       this._name.typeCheck(stable);
/* 179 */       if (this._namespace != null) {
/* 180 */         this._namespace.typeCheck(stable);
/*     */       }
/* 182 */       typeCheckContents(stable);
/*     */     } 
/* 184 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 191 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 192 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 194 */     if (this._ignore)
/* 195 */       return;  this._ignore = true;
/*     */ 
/*     */     
/* 198 */     if (this._namespace != null) {
/*     */       
/* 200 */       il.append(methodGen.loadHandler());
/* 201 */       il.append((CompoundInstruction)new PUSH(cpg, this._prefix));
/* 202 */       this._namespace.translate(classGen, methodGen);
/* 203 */       il.append(methodGen.namespace());
/*     */     } 
/*     */     
/* 206 */     if (!this._isLiteral) {
/*     */       
/* 208 */       LocalVariableGen nameValue = methodGen.addLocalVariable2("nameValue", Util.getJCRefType("Ljava/lang/String;"), il.getEnd());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 213 */       this._name.translate(classGen, methodGen);
/* 214 */       il.append((Instruction)new ASTORE(nameValue.getIndex()));
/* 215 */       il.append((Instruction)new ALOAD(nameValue.getIndex()));
/*     */ 
/*     */       
/* 218 */       int check = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "checkAttribQName", "(Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */       
/* 222 */       il.append((Instruction)new INVOKESTATIC(check));
/*     */ 
/*     */       
/* 225 */       il.append(methodGen.loadHandler());
/* 226 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/* 229 */       il.append((Instruction)new ALOAD(nameValue.getIndex()));
/*     */     } else {
/*     */       
/* 232 */       il.append(methodGen.loadHandler());
/* 233 */       il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */       
/* 236 */       this._name.translate(classGen, methodGen);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 241 */     if (elementCount() == 1 && elementAt(0) instanceof Text) {
/* 242 */       il.append((CompoundInstruction)new PUSH(cpg, ((Text)elementAt(0)).getText()));
/*     */     } else {
/*     */       
/* 245 */       il.append(classGen.loadTranslet());
/* 246 */       il.append((Instruction)new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "stringValueHandler", "Lorg/apache/xalan/xsltc/runtime/StringValueHandler;")));
/*     */ 
/*     */       
/* 249 */       il.append((Instruction)InstructionConstants.DUP);
/* 250 */       il.append(methodGen.storeHandler());
/*     */       
/* 252 */       translateContents(classGen, methodGen);
/*     */       
/* 254 */       il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.StringValueHandler", "getValue", "()Ljava/lang/String;")));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 259 */     SyntaxTreeNode parent = getParent();
/* 260 */     if (parent instanceof LiteralElement && ((LiteralElement)parent).allAttributesUnique()) {
/*     */       
/* 262 */       int flags = 0;
/* 263 */       ElemDesc elemDesc = ((LiteralElement)parent).getElemDesc();
/*     */ 
/*     */       
/* 266 */       if (elemDesc != null && this._name instanceof SimpleAttributeValue) {
/* 267 */         String attrName = ((SimpleAttributeValue)this._name).toString();
/* 268 */         if (elemDesc.isAttrFlagSet(attrName, 4)) {
/* 269 */           flags |= 0x2;
/*     */         }
/* 271 */         else if (elemDesc.isAttrFlagSet(attrName, 2)) {
/* 272 */           flags |= 0x4;
/*     */         } 
/*     */       } 
/* 275 */       il.append((CompoundInstruction)new PUSH(cpg, flags));
/* 276 */       il.append(methodGen.uniqueAttribute());
/*     */     }
/*     */     else {
/*     */       
/* 280 */       il.append(methodGen.attribute());
/*     */     } 
/*     */ 
/*     */     
/* 284 */     il.append(methodGen.storeHandler());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/XslAttribute.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */