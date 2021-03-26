/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.ClassGen;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.xalan.xsltc.compiler.util.AttributeSetMethodGenerator;
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
/*     */ final class AttributeSet
/*     */   extends TopLevelElement
/*     */ {
/*     */   private static final String AttributeSetPrefix = "$as$";
/*     */   private QName _name;
/*     */   private UseAttributeSets _useSets;
/*     */   private AttributeSet _mergeSet;
/*     */   private String _method;
/*     */   private boolean _ignore = false;
/*     */   
/*     */   public QName getName() {
/*  58 */     return this._name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMethodName() {
/*  66 */     return this._method;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignore() {
/*  76 */     this._ignore = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  86 */     String name = getAttribute("name");
/*     */     
/*  88 */     if (!XMLChar.isValidQName(name)) {
/*  89 */       ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", name, this);
/*  90 */       parser.reportError(3, err);
/*     */     } 
/*  92 */     this._name = parser.getQNameIgnoreDefaultNs(name);
/*  93 */     if (this._name == null || this._name.equals("")) {
/*  94 */       ErrorMsg msg = new ErrorMsg("UNNAMED_ATTRIBSET_ERR", this);
/*  95 */       parser.reportError(3, msg);
/*     */     } 
/*     */ 
/*     */     
/*  99 */     String useSets = getAttribute("use-attribute-sets");
/* 100 */     if (useSets.length() > 0) {
/* 101 */       if (!Util.isValidQNames(useSets)) {
/* 102 */         ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", useSets, this);
/* 103 */         parser.reportError(3, err);
/*     */       } 
/* 105 */       this._useSets = new UseAttributeSets(useSets, parser);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 110 */     Vector contents = getContents();
/* 111 */     int count = contents.size();
/* 112 */     for (int i = 0; i < count; i++) {
/* 113 */       SyntaxTreeNode child = contents.elementAt(i);
/* 114 */       if (child instanceof XslAttribute) {
/* 115 */         parser.getSymbolTable().setCurrentNode(child);
/* 116 */         child.parseContents(parser);
/*     */       }
/* 118 */       else if (!(child instanceof Text)) {
/*     */ 
/*     */ 
/*     */         
/* 122 */         ErrorMsg msg = new ErrorMsg("ILLEGAL_CHILD_ERR", this);
/* 123 */         parser.reportError(3, msg);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 128 */     parser.getSymbolTable().setCurrentNode(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 136 */     if (this._ignore) return Type.Void;
/*     */ 
/*     */     
/* 139 */     this._mergeSet = stable.addAttributeSet(this);
/*     */     
/* 141 */     this._method = "$as$" + getXSLTC().nextAttributeSetSerial();
/*     */     
/* 143 */     if (this._useSets != null) this._useSets.typeCheck(stable); 
/* 144 */     typeCheckContents(stable);
/* 145 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 153 */     if (this._ignore) {
/*     */       return;
/*     */     }
/* 156 */     AttributeSetMethodGenerator attributeSetMethodGenerator = new AttributeSetMethodGenerator(this._method, (ClassGen)classGen);
/*     */ 
/*     */ 
/*     */     
/* 160 */     if (this._mergeSet != null) {
/* 161 */       ConstantPoolGen cpg = classGen.getConstantPool();
/* 162 */       InstructionList instructionList = attributeSetMethodGenerator.getInstructionList();
/* 163 */       String methodName = this._mergeSet.getMethodName();
/*     */       
/* 165 */       instructionList.append(classGen.loadTranslet());
/* 166 */       instructionList.append(attributeSetMethodGenerator.loadDOM());
/* 167 */       instructionList.append(attributeSetMethodGenerator.loadIterator());
/* 168 */       instructionList.append(attributeSetMethodGenerator.loadHandler());
/* 169 */       int method = cpg.addMethodref(classGen.getClassName(), methodName, "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V");
/*     */       
/* 171 */       instructionList.append((Instruction)new INVOKESPECIAL(method));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 176 */     if (this._useSets != null) this._useSets.translate(classGen, (MethodGenerator)attributeSetMethodGenerator);
/*     */ 
/*     */     
/* 179 */     Enumeration attributes = elements();
/* 180 */     while (attributes.hasMoreElements()) {
/* 181 */       SyntaxTreeNode element = attributes.nextElement();
/* 182 */       if (element instanceof XslAttribute) {
/* 183 */         XslAttribute attribute = (XslAttribute)element;
/* 184 */         attribute.translate(classGen, (MethodGenerator)attributeSetMethodGenerator);
/*     */       } 
/*     */     } 
/* 187 */     InstructionList il = attributeSetMethodGenerator.getInstructionList();
/* 188 */     il.append((Instruction)InstructionConstants.RETURN);
/*     */     
/* 190 */     attributeSetMethodGenerator.stripAttributes(true);
/* 191 */     attributeSetMethodGenerator.setMaxLocals();
/* 192 */     attributeSetMethodGenerator.setMaxStack();
/* 193 */     attributeSetMethodGenerator.removeNOPs();
/* 194 */     classGen.addMethod(attributeSetMethodGenerator.getMethod());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 198 */     StringBuffer buf = new StringBuffer("attribute-set: ");
/*     */     
/* 200 */     Enumeration attributes = elements();
/* 201 */     while (attributes.hasMoreElements()) {
/* 202 */       XslAttribute attribute = attributes.nextElement();
/*     */       
/* 204 */       buf.append(attribute);
/*     */     } 
/* 206 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/AttributeSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */