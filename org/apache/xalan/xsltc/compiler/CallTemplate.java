/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class CallTemplate
/*     */   extends Instruction
/*     */ {
/*     */   private QName _name;
/*  55 */   private Object[] _parameters = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private Template _calleeTemplate = null;
/*     */   
/*     */   public void display(int indent) {
/*  63 */     indent(indent);
/*  64 */     System.out.print("CallTemplate");
/*  65 */     Util.println(" name " + this._name);
/*  66 */     displayContents(indent + 4);
/*     */   }
/*     */   
/*     */   public boolean hasWithParams() {
/*  70 */     return (elementCount() > 0);
/*     */   }
/*     */   
/*     */   public void parseContents(Parser parser) {
/*  74 */     String name = getAttribute("name");
/*  75 */     if (name.length() > 0) {
/*  76 */       if (!XMLChar.isValidQName(name)) {
/*  77 */         ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", name, this);
/*  78 */         parser.reportError(3, err);
/*     */       } 
/*  80 */       this._name = parser.getQNameIgnoreDefaultNs(name);
/*     */     } else {
/*     */       
/*  83 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "name");
/*     */     } 
/*  85 */     parseChildren(parser);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  92 */     Template template = stable.lookupTemplate(this._name);
/*  93 */     if (template != null) {
/*  94 */       typeCheckContents(stable);
/*     */     } else {
/*     */       
/*  97 */       ErrorMsg err = new ErrorMsg("TEMPLATE_UNDEF_ERR", this._name, this);
/*  98 */       throw new TypeCheckError(err);
/*     */     } 
/* 100 */     return Type.Void;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 104 */     Stylesheet stylesheet = classGen.getStylesheet();
/* 105 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 106 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 109 */     if (stylesheet.hasLocalParams() || hasContents()) {
/* 110 */       this._calleeTemplate = getCalleeTemplate();
/*     */ 
/*     */       
/* 113 */       if (this._calleeTemplate != null) {
/* 114 */         buildParameterList();
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 120 */         int push = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "pushParamFrame", "()V");
/*     */ 
/*     */         
/* 123 */         il.append(classGen.loadTranslet());
/* 124 */         il.append((Instruction)new INVOKEVIRTUAL(push));
/* 125 */         translateContents(classGen, methodGen);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 130 */     String className = stylesheet.getClassName();
/* 131 */     String methodName = Util.escape(this._name.toString());
/*     */ 
/*     */     
/* 134 */     il.append(classGen.loadTranslet());
/* 135 */     il.append(methodGen.loadDOM());
/* 136 */     il.append(methodGen.loadIterator());
/* 137 */     il.append(methodGen.loadHandler());
/* 138 */     il.append(methodGen.loadCurrentNode());
/*     */ 
/*     */     
/* 141 */     StringBuffer methodSig = new StringBuffer("(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;I");
/*     */ 
/*     */ 
/*     */     
/* 145 */     if (this._calleeTemplate != null) {
/* 146 */       Vector calleeParams = this._calleeTemplate.getParameters();
/* 147 */       int numParams = this._parameters.length;
/*     */       
/* 149 */       for (int i = 0; i < numParams; i++) {
/* 150 */         SyntaxTreeNode node = (SyntaxTreeNode)this._parameters[i];
/* 151 */         methodSig.append("Ljava/lang/Object;");
/*     */ 
/*     */         
/* 154 */         if (node instanceof Param) {
/* 155 */           il.append(InstructionConstants.ACONST_NULL);
/*     */         } else {
/*     */           
/* 158 */           node.translate(classGen, methodGen);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 164 */     methodSig.append(")V");
/* 165 */     il.append((Instruction)new INVOKEVIRTUAL(cpg.addMethodref(className, methodName, methodSig.toString())));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     if (this._calleeTemplate == null && (stylesheet.hasLocalParams() || hasContents())) {
/*     */       
/* 173 */       int pop = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "popParamFrame", "()V");
/*     */ 
/*     */       
/* 176 */       il.append(classGen.loadTranslet());
/* 177 */       il.append((Instruction)new INVOKEVIRTUAL(pop));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Template getCalleeTemplate() {
/* 187 */     Stylesheet stylesheet = getXSLTC().getStylesheet();
/* 188 */     Vector templates = stylesheet.getAllValidTemplates();
/*     */     
/* 190 */     int size = templates.size();
/* 191 */     for (int i = 0; i < size; i++) {
/* 192 */       Template t = templates.elementAt(i);
/* 193 */       if (t.getName() == this._name && t.isSimpleNamedTemplate()) {
/* 194 */         return t;
/*     */       }
/*     */     } 
/* 197 */     return null;
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
/*     */   private void buildParameterList() {
/* 209 */     Vector defaultParams = this._calleeTemplate.getParameters();
/* 210 */     int numParams = defaultParams.size();
/* 211 */     this._parameters = new Object[numParams];
/* 212 */     for (int i = 0; i < numParams; i++) {
/* 213 */       this._parameters[i] = defaultParams.elementAt(i);
/*     */     }
/*     */ 
/*     */     
/* 217 */     int count = elementCount();
/* 218 */     for (int j = 0; j < count; j++) {
/* 219 */       Object node = elementAt(j);
/*     */ 
/*     */       
/* 222 */       if (node instanceof WithParam) {
/* 223 */         WithParam withParam = (WithParam)node;
/* 224 */         QName name = withParam.getName();
/*     */ 
/*     */         
/* 227 */         for (int k = 0; k < numParams; k++) {
/* 228 */           Object object = this._parameters[k];
/* 229 */           if (object instanceof Param && ((Param)object).getName() == name) {
/*     */             
/* 231 */             withParam.setDoParameterOptimization(true);
/* 232 */             this._parameters[k] = withParam;
/*     */             break;
/*     */           } 
/* 235 */           if (object instanceof WithParam && ((WithParam)object).getName() == name) {
/*     */             
/* 237 */             withParam.setDoParameterOptimization(true);
/* 238 */             this._parameters[k] = withParam;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/CallTemplate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */