/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.PUSH;
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
/*     */ final class FunctionAvailableCall
/*     */   extends FunctionCall
/*     */ {
/*     */   private Expression _arg;
/*  42 */   private String _nameOfFunct = null;
/*  43 */   private String _namespaceOfFunct = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean _isFunctionAvailable = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FunctionAvailableCall(QName fname, Vector arguments) {
/*  53 */     super(fname, arguments);
/*  54 */     this._arg = arguments.elementAt(0);
/*  55 */     this._type = null;
/*     */     
/*  57 */     if (this._arg instanceof LiteralExpr) {
/*  58 */       LiteralExpr arg = (LiteralExpr)this._arg;
/*  59 */       this._namespaceOfFunct = arg.getNamespace();
/*  60 */       this._nameOfFunct = arg.getValue();
/*     */       
/*  62 */       if (!isInternalNamespace()) {
/*  63 */         this._isFunctionAvailable = hasMethods();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  73 */     if (this._type != null) {
/*  74 */       return this._type;
/*     */     }
/*  76 */     if (this._arg instanceof LiteralExpr) {
/*  77 */       return this._type = Type.Boolean;
/*     */     }
/*  79 */     ErrorMsg err = new ErrorMsg("NEED_LITERAL_ERR", "function-available", this);
/*     */     
/*  81 */     throw new TypeCheckError(err);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object evaluateAtCompileTime() {
/*  90 */     return getResult() ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasMethods() {
/*  98 */     LiteralExpr arg = (LiteralExpr)this._arg;
/*     */ 
/*     */     
/* 101 */     String className = getClassNameFromUri(this._namespaceOfFunct);
/*     */ 
/*     */     
/* 104 */     String methodName = null;
/* 105 */     int colonIndex = this._nameOfFunct.indexOf(":");
/* 106 */     if (colonIndex > 0) {
/* 107 */       String functionName = this._nameOfFunct.substring(colonIndex + 1);
/* 108 */       int lastDotIndex = functionName.lastIndexOf('.');
/* 109 */       if (lastDotIndex > 0) {
/* 110 */         methodName = functionName.substring(lastDotIndex + 1);
/* 111 */         if (className != null && !className.equals("")) {
/* 112 */           className = className + "." + functionName.substring(0, lastDotIndex);
/*     */         } else {
/* 114 */           className = functionName.substring(0, lastDotIndex);
/*     */         } 
/*     */       } else {
/* 117 */         methodName = functionName;
/*     */       } 
/*     */     } else {
/* 120 */       methodName = this._nameOfFunct;
/*     */     } 
/* 122 */     if (className == null || methodName == null) {
/* 123 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 127 */     if (methodName.indexOf('-') > 0) {
/* 128 */       methodName = FunctionCall.replaceDash(methodName);
/*     */     }
/*     */     try {
/* 131 */       Class clazz = ObjectFactory.findProviderClass(className, ObjectFactory.findClassLoader(), true);
/*     */ 
/*     */       
/* 134 */       if (clazz == null) {
/* 135 */         return false;
/*     */       }
/*     */       
/* 138 */       Method[] methods = clazz.getMethods();
/*     */       
/* 140 */       for (int i = 0; i < methods.length; i++) {
/* 141 */         int mods = methods[i].getModifiers();
/*     */         
/* 143 */         if (Modifier.isPublic(mods) && Modifier.isStatic(mods) && methods[i].getName().equals(methodName))
/*     */         {
/*     */           
/* 146 */           return true;
/*     */         }
/*     */       } 
/*     */     } catch (ClassNotFoundException e) {
/*     */       
/* 151 */       return false;
/*     */     } 
/* 153 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getResult() {
/* 161 */     if (this._nameOfFunct == null) {
/* 162 */       return false;
/*     */     }
/*     */     
/* 165 */     if (isInternalNamespace()) {
/* 166 */       Parser parser = getParser();
/* 167 */       this._isFunctionAvailable = parser.functionSupported(Util.getLocalName(this._nameOfFunct));
/*     */     } 
/*     */     
/* 170 */     return this._isFunctionAvailable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isInternalNamespace() {
/* 177 */     return (this._namespaceOfFunct == null || this._namespaceOfFunct.equals("") || this._namespaceOfFunct.equals("http://xml.apache.org/xalan/xsltc"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 188 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 189 */     methodGen.getInstructionList().append((CompoundInstruction)new PUSH(cpg, getResult()));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/FunctionAvailableCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */