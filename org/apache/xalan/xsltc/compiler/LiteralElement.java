/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
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
/*     */ import org.apache.xml.serializer.ElemDesc;
/*     */ import org.apache.xml.serializer.ToHTMLStream;
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
/*     */ final class LiteralElement
/*     */   extends Instruction
/*     */ {
/*     */   private String _name;
/*     */   private LiteralElement _literalElemParent;
/*  48 */   private Vector _attributeElements = null;
/*  49 */   private Hashtable _accessedPrefixes = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean _allAttributesUnique = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String XMLNS_STRING = "xmlns";
/*     */ 
/*     */ 
/*     */   
/*     */   public QName getName() {
/*  62 */     return this._qname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void display(int indent) {
/*  69 */     indent(indent);
/*  70 */     Util.println("LiteralElement name = " + this._name);
/*  71 */     displayContents(indent + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String accessedNamespace(String prefix) {
/*  78 */     if (this._accessedPrefixes == null) {
/*  79 */       return null;
/*     */     }
/*  81 */     return (String)this._accessedPrefixes.get(prefix);
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
/*     */   public void registerNamespace(String prefix, String uri, SymbolTable stable, boolean declared) {
/*  93 */     if (this._literalElemParent != null) {
/*  94 */       String parentUri = this._literalElemParent.accessedNamespace(prefix);
/*  95 */       if (parentUri == null) {
/*  96 */         this._literalElemParent.registerNamespace(prefix, uri, stable, declared);
/*     */         return;
/*     */       } 
/*  99 */       if (parentUri.equals(uri)) {
/*     */         return;
/*     */       }
/*     */     } 
/* 103 */     if (this._accessedPrefixes == null) {
/* 104 */       this._accessedPrefixes = new Hashtable();
/*     */     
/*     */     }
/* 107 */     else if (!declared) {
/*     */       
/* 109 */       String old = (String)this._accessedPrefixes.get(prefix);
/* 110 */       if (old != null) {
/* 111 */         if (old.equals(uri)) {
/*     */           return;
/*     */         }
/* 114 */         prefix = stable.generateNamespacePrefix();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 119 */     if (!prefix.equals("xml")) {
/* 120 */       this._accessedPrefixes.put(prefix, uri);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String translateQName(QName qname, SymbolTable stable) {
/* 131 */     String localname = qname.getLocalPart();
/* 132 */     String prefix = qname.getPrefix();
/*     */ 
/*     */     
/* 135 */     if (prefix == null) {
/* 136 */       prefix = "";
/* 137 */     } else if (prefix.equals("xmlns")) {
/* 138 */       return "xmlns";
/*     */     } 
/*     */     
/* 141 */     String alternative = stable.lookupPrefixAlias(prefix);
/* 142 */     if (alternative != null) {
/* 143 */       stable.excludeNamespaces(prefix);
/* 144 */       prefix = alternative;
/*     */     } 
/*     */ 
/*     */     
/* 148 */     String uri = lookupNamespace(prefix);
/* 149 */     if (uri == null) return localname;
/*     */ 
/*     */     
/* 152 */     registerNamespace(prefix, uri, stable, false);
/*     */ 
/*     */     
/* 155 */     if (prefix != "") {
/* 156 */       return prefix + ":" + localname;
/*     */     }
/* 158 */     return localname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(SyntaxTreeNode attribute) {
/* 165 */     if (this._attributeElements == null) {
/* 166 */       this._attributeElements = new Vector(2);
/*     */     }
/* 168 */     this._attributeElements.add(attribute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstAttribute(SyntaxTreeNode attribute) {
/* 175 */     if (this._attributeElements == null) {
/* 176 */       this._attributeElements = new Vector(2);
/*     */     }
/* 178 */     this._attributeElements.insertElementAt(attribute, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 187 */     if (this._attributeElements != null) {
/* 188 */       int count = this._attributeElements.size();
/* 189 */       for (int i = 0; i < count; i++) {
/* 190 */         SyntaxTreeNode node = this._attributeElements.elementAt(i);
/*     */         
/* 192 */         node.typeCheck(stable);
/*     */       } 
/*     */     } 
/* 195 */     typeCheckContents(stable);
/* 196 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration getNamespaceScope(SyntaxTreeNode node) {
/* 205 */     Hashtable all = new Hashtable();
/*     */     
/* 207 */     while (node != null) {
/* 208 */       Hashtable mapping = node.getPrefixMapping();
/* 209 */       if (mapping != null) {
/* 210 */         Enumeration prefixes = mapping.keys();
/* 211 */         while (prefixes.hasMoreElements()) {
/* 212 */           String prefix = prefixes.nextElement();
/* 213 */           if (!all.containsKey(prefix)) {
/* 214 */             all.put(prefix, mapping.get(prefix));
/*     */           }
/*     */         } 
/*     */       } 
/* 218 */       node = node.getParent();
/*     */     } 
/* 220 */     return all.keys();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/* 228 */     SymbolTable stable = parser.getSymbolTable();
/* 229 */     stable.setCurrentNode(this);
/*     */ 
/*     */     
/* 232 */     SyntaxTreeNode _literalElemParent = getParent();
/* 233 */     while (_literalElemParent != null && !(_literalElemParent instanceof LiteralElement)) {
/* 234 */       _literalElemParent = _literalElemParent.getParent();
/*     */     }
/*     */     
/* 237 */     if (!(_literalElemParent instanceof LiteralElement)) {
/* 238 */       _literalElemParent = null;
/*     */     }
/*     */     
/* 241 */     this._name = translateQName(this._qname, stable);
/*     */ 
/*     */     
/* 244 */     int count = this._attributes.getLength();
/* 245 */     for (int i = 0; i < count; i++) {
/* 246 */       QName qname = parser.getQName(this._attributes.getQName(i));
/* 247 */       String uri = qname.getNamespace();
/* 248 */       String val = this._attributes.getValue(i);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 253 */       if (qname == parser.getUseAttributeSets()) {
/* 254 */         if (!Util.isValidQNames(val)) {
/* 255 */           ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", val, this);
/* 256 */           parser.reportError(3, err);
/*     */         } 
/* 258 */         setFirstAttribute(new UseAttributeSets(val, parser));
/*     */       
/*     */       }
/* 261 */       else if (qname == parser.getExtensionElementPrefixes()) {
/* 262 */         stable.excludeNamespaces(val);
/*     */       
/*     */       }
/* 265 */       else if (qname == parser.getExcludeResultPrefixes()) {
/* 266 */         stable.excludeNamespaces(val);
/*     */       }
/*     */       else {
/*     */         
/* 270 */         String prefix = qname.getPrefix();
/* 271 */         if ((prefix == null || !prefix.equals("xmlns")) && (prefix != null || !qname.getLocalPart().equals("xmlns")) && (uri == null || !uri.equals("http://www.w3.org/1999/XSL/Transform"))) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 279 */           String name = translateQName(qname, stable);
/* 280 */           LiteralAttribute attr = new LiteralAttribute(name, val, parser);
/* 281 */           addAttribute(attr);
/* 282 */           attr.setParent(this);
/* 283 */           attr.parseContents(parser);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 289 */     Enumeration include = getNamespaceScope(this);
/* 290 */     while (include.hasMoreElements()) {
/* 291 */       String prefix = include.nextElement();
/* 292 */       if (!prefix.equals("xml")) {
/* 293 */         String uri = lookupNamespace(prefix);
/* 294 */         if (uri != null && !stable.isExcludedNamespace(uri)) {
/* 295 */           registerNamespace(prefix, uri, stable, true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 300 */     parseChildren(parser);
/*     */ 
/*     */     
/* 303 */     for (int j = 0; j < count; j++) {
/* 304 */       QName qname = parser.getQName(this._attributes.getQName(j));
/* 305 */       String val = this._attributes.getValue(j);
/*     */ 
/*     */       
/* 308 */       if (qname == parser.getExtensionElementPrefixes()) {
/* 309 */         stable.unExcludeNamespaces(val);
/*     */       
/*     */       }
/* 312 */       else if (qname == parser.getExcludeResultPrefixes()) {
/* 313 */         stable.unExcludeNamespaces(val);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean contextDependent() {
/* 319 */     return dependentContents();
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
/* 331 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 332 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 335 */     this._allAttributesUnique = checkAttributesUnique();
/*     */ 
/*     */     
/* 338 */     il.append(methodGen.loadHandler());
/*     */     
/* 340 */     il.append((CompoundInstruction)new PUSH(cpg, this._name));
/* 341 */     il.append((Instruction)InstructionConstants.DUP2);
/* 342 */     il.append(methodGen.startElement());
/*     */ 
/*     */     
/* 345 */     int j = 0;
/* 346 */     while (j < elementCount()) {
/*     */       
/* 348 */       SyntaxTreeNode item = (SyntaxTreeNode)elementAt(j);
/* 349 */       if (item instanceof Variable) {
/* 350 */         item.translate(classGen, methodGen);
/* 351 */         removeElement(item);
/*     */ 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 360 */       j++;
/*     */     } 
/*     */ 
/*     */     
/* 364 */     if (this._accessedPrefixes != null) {
/* 365 */       boolean declaresDefaultNS = false;
/* 366 */       Enumeration e = this._accessedPrefixes.keys();
/*     */       
/* 368 */       while (e.hasMoreElements()) {
/* 369 */         String prefix = e.nextElement();
/* 370 */         String uri = (String)this._accessedPrefixes.get(prefix);
/*     */         
/* 372 */         if (uri != "" || prefix != "") {
/*     */ 
/*     */           
/* 375 */           if (prefix == "") {
/* 376 */             declaresDefaultNS = true;
/*     */           }
/* 378 */           il.append(methodGen.loadHandler());
/* 379 */           il.append((CompoundInstruction)new PUSH(cpg, prefix));
/* 380 */           il.append((CompoundInstruction)new PUSH(cpg, uri));
/* 381 */           il.append(methodGen.namespace());
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 389 */       if (!declaresDefaultNS && this._parent instanceof XslElement && ((XslElement)this._parent).declaresDefaultNS()) {
/*     */ 
/*     */         
/* 392 */         il.append(methodGen.loadHandler());
/* 393 */         il.append((CompoundInstruction)new PUSH(cpg, ""));
/* 394 */         il.append((CompoundInstruction)new PUSH(cpg, ""));
/* 395 */         il.append(methodGen.namespace());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 400 */     if (this._attributeElements != null) {
/* 401 */       int count = this._attributeElements.size();
/* 402 */       for (int i = 0; i < count; i++) {
/* 403 */         SyntaxTreeNode node = this._attributeElements.elementAt(i);
/*     */         
/* 405 */         if (!(node instanceof XslAttribute)) {
/* 406 */           node.translate(classGen, methodGen);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 412 */     translateContents(classGen, methodGen);
/*     */ 
/*     */     
/* 415 */     il.append(methodGen.endElement());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isHTMLOutput() {
/* 422 */     return (getStylesheet().getOutputMethod() == 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElemDesc getElemDesc() {
/* 431 */     if (isHTMLOutput()) {
/* 432 */       return ToHTMLStream.getElemDesc(this._name);
/*     */     }
/*     */     
/* 435 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean allAttributesUnique() {
/* 442 */     return this._allAttributesUnique;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean checkAttributesUnique() {
/* 449 */     boolean hasHiddenXslAttribute = canProduceAttributeNodes(this, true);
/* 450 */     if (hasHiddenXslAttribute) {
/* 451 */       return false;
/*     */     }
/* 453 */     if (this._attributeElements != null) {
/* 454 */       int numAttrs = this._attributeElements.size();
/* 455 */       Hashtable attrsTable = null;
/* 456 */       for (int i = 0; i < numAttrs; i++) {
/* 457 */         SyntaxTreeNode node = this._attributeElements.elementAt(i);
/*     */         
/* 459 */         if (node instanceof UseAttributeSets) {
/* 460 */           return false;
/*     */         }
/* 462 */         if (node instanceof XslAttribute) {
/* 463 */           if (attrsTable == null) {
/* 464 */             attrsTable = new Hashtable();
/* 465 */             for (int k = 0; k < i; k++) {
/* 466 */               SyntaxTreeNode n = this._attributeElements.elementAt(k);
/* 467 */               if (n instanceof LiteralAttribute) {
/* 468 */                 LiteralAttribute literalAttr = (LiteralAttribute)n;
/* 469 */                 attrsTable.put(literalAttr.getName(), literalAttr);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 474 */           XslAttribute xslAttr = (XslAttribute)node;
/* 475 */           AttributeValue attrName = xslAttr.getName();
/* 476 */           if (attrName instanceof AttributeValueTemplate) {
/* 477 */             return false;
/*     */           }
/* 479 */           if (attrName instanceof SimpleAttributeValue) {
/* 480 */             SimpleAttributeValue simpleAttr = (SimpleAttributeValue)attrName;
/* 481 */             String name = simpleAttr.toString();
/* 482 */             if (name != null && attrsTable.get(name) != null)
/* 483 */               return false; 
/* 484 */             if (name != null) {
/* 485 */               attrsTable.put(name, xslAttr);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 491 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canProduceAttributeNodes(SyntaxTreeNode node, boolean ignoreXslAttribute) {
/* 501 */     Vector contents = node.getContents();
/* 502 */     int size = contents.size();
/* 503 */     for (int i = 0; i < size; i++) {
/* 504 */       SyntaxTreeNode child = contents.elementAt(i);
/* 505 */       if (child instanceof Text) {
/* 506 */         Text text = (Text)child;
/* 507 */         if (!text.isIgnore())
/*     */         {
/*     */           
/* 510 */           return false;
/*     */         }
/*     */       } else {
/*     */         
/* 514 */         if (child instanceof LiteralElement || child instanceof ValueOf || child instanceof XslElement || child instanceof Comment || child instanceof Number || child instanceof ProcessingInstruction)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 520 */           return false; } 
/* 521 */         if (child instanceof XslAttribute) {
/* 522 */           if (!ignoreXslAttribute)
/*     */           {
/*     */             
/* 525 */             return true;
/*     */           
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 531 */           if (child instanceof CallTemplate || child instanceof ApplyTemplates || child instanceof Copy || child instanceof CopyOf)
/*     */           {
/*     */ 
/*     */             
/* 535 */             return true; } 
/* 536 */           if ((child instanceof If || child instanceof ForEach) && canProduceAttributeNodes(child, false))
/*     */           {
/*     */             
/* 539 */             return true;
/*     */           }
/* 541 */           if (child instanceof Choose) {
/* 542 */             Vector chooseContents = child.getContents();
/* 543 */             int num = chooseContents.size();
/* 544 */             for (int k = 0; k < num; k++) {
/* 545 */               SyntaxTreeNode chooseChild = chooseContents.elementAt(k);
/* 546 */               if ((chooseChild instanceof When || chooseChild instanceof Otherwise) && 
/* 547 */                 canProduceAttributeNodes(chooseChild, false))
/* 548 */                 return true; 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 553 */     }  return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/LiteralElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */