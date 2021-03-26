/*      */ package org.apache.xalan.xsltc.compiler;
/*      */ 
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ import org.apache.bcel.generic.ALOAD;
/*      */ import org.apache.bcel.generic.ANEWARRAY;
/*      */ import org.apache.bcel.generic.ASTORE;
/*      */ import org.apache.bcel.generic.BranchInstruction;
/*      */ import org.apache.bcel.generic.CompoundInstruction;
/*      */ import org.apache.bcel.generic.ConstantPoolGen;
/*      */ import org.apache.bcel.generic.IFEQ;
/*      */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*      */ import org.apache.bcel.generic.INVOKESPECIAL;
/*      */ import org.apache.bcel.generic.INVOKESTATIC;
/*      */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*      */ import org.apache.bcel.generic.Instruction;
/*      */ import org.apache.bcel.generic.InstructionConstants;
/*      */ import org.apache.bcel.generic.InstructionHandle;
/*      */ import org.apache.bcel.generic.InstructionList;
/*      */ import org.apache.bcel.generic.LocalVariableGen;
/*      */ import org.apache.bcel.generic.NEW;
/*      */ import org.apache.bcel.generic.PUSH;
/*      */ import org.apache.bcel.generic.Type;
/*      */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*      */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*      */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*      */ import org.apache.xalan.xsltc.compiler.util.MethodType;
/*      */ import org.apache.xalan.xsltc.compiler.util.MultiHashtable;
/*      */ import org.apache.xalan.xsltc.compiler.util.ObjectType;
/*      */ import org.apache.xalan.xsltc.compiler.util.Type;
/*      */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class FunctionCall
/*      */   extends Expression
/*      */ {
/*      */   private QName _fname;
/*      */   private final Vector _arguments;
/*   73 */   private static final Vector EMPTY_ARG_LIST = new Vector(0);
/*      */ 
/*      */   
/*      */   protected static final String EXT_XSLTC = "http://xml.apache.org/xalan/xsltc";
/*      */ 
/*      */   
/*      */   protected static final String JAVA_EXT_XSLTC = "http://xml.apache.org/xalan/xsltc/java";
/*      */ 
/*      */   
/*      */   protected static final String EXT_XALAN = "http://xml.apache.org/xalan";
/*      */ 
/*      */   
/*      */   protected static final String JAVA_EXT_XALAN = "http://xml.apache.org/xalan/java";
/*      */ 
/*      */   
/*      */   protected static final String JAVA_EXT_XALAN_OLD = "http://xml.apache.org/xslt/java";
/*      */ 
/*      */   
/*      */   protected static final String EXSLT_COMMON = "http://exslt.org/common";
/*      */ 
/*      */   
/*      */   protected static final String EXSLT_MATH = "http://exslt.org/math";
/*      */ 
/*      */   
/*      */   protected static final String EXSLT_SETS = "http://exslt.org/sets";
/*      */ 
/*      */   
/*      */   protected static final String EXSLT_DATETIME = "http://exslt.org/dates-and-times";
/*      */ 
/*      */   
/*      */   protected static final String EXSLT_STRINGS = "http://exslt.org/strings";
/*      */   
/*      */   protected static final int NAMESPACE_FORMAT_JAVA = 0;
/*      */   
/*      */   protected static final int NAMESPACE_FORMAT_CLASS = 1;
/*      */   
/*      */   protected static final int NAMESPACE_FORMAT_PACKAGE = 2;
/*      */   
/*      */   protected static final int NAMESPACE_FORMAT_CLASS_OR_PACKAGE = 3;
/*      */   
/*  113 */   private int _namespace_format = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  118 */   Expression _thisArgument = null;
/*      */ 
/*      */   
/*      */   private String _className;
/*      */ 
/*      */   
/*      */   private Class _clazz;
/*      */   
/*      */   private Method _chosenMethod;
/*      */   
/*      */   private Constructor _chosenConstructor;
/*      */   
/*      */   private MethodType _chosenMethodType;
/*      */   
/*      */   private boolean unresolvedExternal;
/*      */   
/*      */   private boolean _isExtConstructor = false;
/*      */   
/*      */   private boolean _isStatic = false;
/*      */   
/*      */   private boolean resolveDynamic = false;
/*      */   
/*  140 */   private static final MultiHashtable _internal2Java = new MultiHashtable();
/*      */ 
/*      */   
/*  143 */   private static final Hashtable _java2Internal = new Hashtable();
/*      */ 
/*      */   
/*  146 */   private static final Hashtable _extensionNamespaceTable = new Hashtable();
/*      */ 
/*      */   
/*  149 */   private static final Hashtable _extensionFunctionTable = new Hashtable();
/*      */ 
/*      */   
/*      */   static class JavaType
/*      */   {
/*      */     public Class type;
/*      */     
/*      */     public int distance;
/*      */ 
/*      */     
/*      */     public JavaType(Class type, int distance) {
/*  160 */       this.type = type;
/*  161 */       this.distance = distance;
/*      */     }
/*      */     public boolean equals(Object query) {
/*  164 */       return query.equals(this.type);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*      */     
/*  176 */     try { Class nodeClass = Class.forName("org.w3c.dom.Node");
/*  177 */       Class nodeListClass = Class.forName("org.w3c.dom.NodeList");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  182 */       _internal2Java.put(Type.Boolean, new JavaType(boolean.class, 0));
/*  183 */       _internal2Java.put(Type.Boolean, new JavaType(Boolean.class, 1));
/*  184 */       _internal2Java.put(Type.Boolean, new JavaType(Object.class, 2));
/*      */ 
/*      */ 
/*      */       
/*  188 */       _internal2Java.put(Type.Real, new JavaType(double.class, 0));
/*  189 */       _internal2Java.put(Type.Real, new JavaType(Double.class, 1));
/*  190 */       _internal2Java.put(Type.Real, new JavaType(float.class, 2));
/*  191 */       _internal2Java.put(Type.Real, new JavaType(long.class, 3));
/*  192 */       _internal2Java.put(Type.Real, new JavaType(int.class, 4));
/*  193 */       _internal2Java.put(Type.Real, new JavaType(short.class, 5));
/*  194 */       _internal2Java.put(Type.Real, new JavaType(byte.class, 6));
/*  195 */       _internal2Java.put(Type.Real, new JavaType(char.class, 7));
/*  196 */       _internal2Java.put(Type.Real, new JavaType(Object.class, 8));
/*      */ 
/*      */       
/*  199 */       _internal2Java.put(Type.Int, new JavaType(double.class, 0));
/*  200 */       _internal2Java.put(Type.Int, new JavaType(Double.class, 1));
/*  201 */       _internal2Java.put(Type.Int, new JavaType(float.class, 2));
/*  202 */       _internal2Java.put(Type.Int, new JavaType(long.class, 3));
/*  203 */       _internal2Java.put(Type.Int, new JavaType(int.class, 4));
/*  204 */       _internal2Java.put(Type.Int, new JavaType(short.class, 5));
/*  205 */       _internal2Java.put(Type.Int, new JavaType(byte.class, 6));
/*  206 */       _internal2Java.put(Type.Int, new JavaType(char.class, 7));
/*  207 */       _internal2Java.put(Type.Int, new JavaType(Object.class, 8));
/*      */ 
/*      */       
/*  210 */       _internal2Java.put(Type.String, new JavaType(String.class, 0));
/*  211 */       _internal2Java.put(Type.String, new JavaType(Object.class, 1));
/*      */ 
/*      */       
/*  214 */       _internal2Java.put(Type.NodeSet, new JavaType(nodeListClass, 0));
/*  215 */       _internal2Java.put(Type.NodeSet, new JavaType(nodeClass, 1));
/*  216 */       _internal2Java.put(Type.NodeSet, new JavaType(Object.class, 2));
/*  217 */       _internal2Java.put(Type.NodeSet, new JavaType(String.class, 3));
/*      */ 
/*      */       
/*  220 */       _internal2Java.put(Type.Node, new JavaType(nodeListClass, 0));
/*  221 */       _internal2Java.put(Type.Node, new JavaType(nodeClass, 1));
/*  222 */       _internal2Java.put(Type.Node, new JavaType(Object.class, 2));
/*  223 */       _internal2Java.put(Type.Node, new JavaType(String.class, 3));
/*      */ 
/*      */       
/*  226 */       _internal2Java.put(Type.ResultTree, new JavaType(nodeListClass, 0));
/*  227 */       _internal2Java.put(Type.ResultTree, new JavaType(nodeClass, 1));
/*  228 */       _internal2Java.put(Type.ResultTree, new JavaType(Object.class, 2));
/*  229 */       _internal2Java.put(Type.ResultTree, new JavaType(String.class, 3));
/*      */       
/*  231 */       _internal2Java.put(Type.Reference, new JavaType(Object.class, 0));
/*      */ 
/*      */       
/*  234 */       _java2Internal.put(boolean.class, Type.Boolean);
/*  235 */       _java2Internal.put(void.class, Type.Void);
/*  236 */       _java2Internal.put(char.class, Type.Real);
/*  237 */       _java2Internal.put(byte.class, Type.Real);
/*  238 */       _java2Internal.put(short.class, Type.Real);
/*  239 */       _java2Internal.put(int.class, Type.Real);
/*  240 */       _java2Internal.put(long.class, Type.Real);
/*  241 */       _java2Internal.put(float.class, Type.Real);
/*  242 */       _java2Internal.put(double.class, Type.Real);
/*      */       
/*  244 */       _java2Internal.put(String.class, Type.String);
/*      */       
/*  246 */       _java2Internal.put(Object.class, Type.Reference);
/*      */ 
/*      */       
/*  249 */       _java2Internal.put(nodeListClass, Type.NodeSet);
/*  250 */       _java2Internal.put(nodeClass, Type.NodeSet);
/*      */ 
/*      */       
/*  253 */       _extensionNamespaceTable.put("http://xml.apache.org/xalan", "org.apache.xalan.lib.Extensions");
/*  254 */       _extensionNamespaceTable.put("http://exslt.org/common", "org.apache.xalan.lib.ExsltCommon");
/*  255 */       _extensionNamespaceTable.put("http://exslt.org/math", "org.apache.xalan.lib.ExsltMath");
/*  256 */       _extensionNamespaceTable.put("http://exslt.org/sets", "org.apache.xalan.lib.ExsltSets");
/*  257 */       _extensionNamespaceTable.put("http://exslt.org/dates-and-times", "org.apache.xalan.lib.ExsltDatetime");
/*  258 */       _extensionNamespaceTable.put("http://exslt.org/strings", "org.apache.xalan.lib.ExsltStrings");
/*      */ 
/*      */       
/*  261 */       _extensionFunctionTable.put("http://exslt.org/common:nodeSet", "nodeset");
/*  262 */       _extensionFunctionTable.put("http://exslt.org/common:objectType", "objectType");
/*  263 */       _extensionFunctionTable.put("http://xml.apache.org/xalan:nodeset", "nodeset"); } catch (ClassNotFoundException e)
/*      */     
/*      */     { 
/*  266 */       System.err.println(e); }
/*      */   
/*      */   }
/*      */   
/*      */   public FunctionCall(QName fname, Vector arguments) {
/*  271 */     this._fname = fname;
/*  272 */     this._arguments = arguments;
/*  273 */     this._type = null;
/*      */   }
/*      */   
/*      */   public FunctionCall(QName fname) {
/*  277 */     this(fname, EMPTY_ARG_LIST);
/*      */   }
/*      */   
/*      */   public String getName() {
/*  281 */     return this._fname.toString();
/*      */   }
/*      */   
/*      */   public void setParser(Parser parser) {
/*  285 */     super.setParser(parser);
/*  286 */     if (this._arguments != null) {
/*  287 */       int n = this._arguments.size();
/*  288 */       for (int i = 0; i < n; i++) {
/*  289 */         Expression exp = this._arguments.elementAt(i);
/*  290 */         exp.setParser(parser);
/*  291 */         exp.setParent(this);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getClassNameFromUri(String uri) {
/*  298 */     String className = (String)_extensionNamespaceTable.get(uri);
/*      */     
/*  300 */     if (className != null) {
/*  301 */       return className;
/*      */     }
/*  303 */     if (uri.startsWith("http://xml.apache.org/xalan/xsltc/java")) {
/*  304 */       int length = "http://xml.apache.org/xalan/xsltc/java".length() + 1;
/*  305 */       return (uri.length() > length) ? uri.substring(length) : "";
/*      */     } 
/*  307 */     if (uri.startsWith("http://xml.apache.org/xalan/java")) {
/*  308 */       int length = "http://xml.apache.org/xalan/java".length() + 1;
/*  309 */       return (uri.length() > length) ? uri.substring(length) : "";
/*      */     } 
/*  311 */     if (uri.startsWith("http://xml.apache.org/xslt/java")) {
/*  312 */       int length = "http://xml.apache.org/xslt/java".length() + 1;
/*  313 */       return (uri.length() > length) ? uri.substring(length) : "";
/*      */     } 
/*      */     
/*  316 */     int index = uri.lastIndexOf('/');
/*  317 */     return (index > 0) ? uri.substring(index + 1) : uri;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  329 */     if (this._type != null) return this._type;
/*      */     
/*  331 */     String namespace = this._fname.getNamespace();
/*  332 */     String local = this._fname.getLocalPart();
/*      */     
/*  334 */     if (isExtension()) {
/*  335 */       this._fname = new QName(null, null, local);
/*  336 */       return typeCheckStandard(stable);
/*      */     } 
/*  338 */     if (isStandard()) {
/*  339 */       return typeCheckStandard(stable);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  344 */     try { this._className = getClassNameFromUri(namespace);
/*      */       
/*  346 */       int pos = local.lastIndexOf('.');
/*  347 */       if (pos > 0) {
/*  348 */         this._isStatic = true;
/*  349 */         if (this._className != null && this._className.length() > 0) {
/*  350 */           this._namespace_format = 2;
/*  351 */           this._className += "." + local.substring(0, pos);
/*      */         } else {
/*      */           
/*  354 */           this._namespace_format = 0;
/*  355 */           this._className = local.substring(0, pos);
/*      */         } 
/*      */         
/*  358 */         this._fname = new QName(namespace, null, local.substring(pos + 1));
/*      */       } else {
/*      */         
/*  361 */         if (this._className != null && this._className.length() > 0) {
/*      */           
/*  363 */           try { this._clazz = ObjectFactory.findProviderClass(this._className, ObjectFactory.findClassLoader(), true);
/*      */             
/*  365 */             this._namespace_format = 1; } catch (ClassNotFoundException e)
/*      */           
/*      */           { 
/*  368 */             this._namespace_format = 2; }
/*      */         
/*      */         } else {
/*      */           
/*  372 */           this._namespace_format = 0;
/*      */         } 
/*  374 */         if (local.indexOf('-') > 0) {
/*  375 */           local = replaceDash(local);
/*      */         }
/*      */         
/*  378 */         String extFunction = (String)_extensionFunctionTable.get(namespace + ":" + local);
/*  379 */         if (extFunction != null) {
/*  380 */           this._fname = new QName(null, null, extFunction);
/*  381 */           return typeCheckStandard(stable);
/*      */         } 
/*      */         
/*  384 */         this._fname = new QName(namespace, null, local);
/*      */       } 
/*      */       
/*  387 */       return typeCheckExternal(stable); } catch (TypeCheckError e)
/*      */     
/*      */     { 
/*  390 */       ErrorMsg errorMsg = e.getErrorMsg();
/*  391 */       if (errorMsg == null) {
/*  392 */         String name = this._fname.getLocalPart();
/*  393 */         errorMsg = new ErrorMsg("METHOD_NOT_FOUND_ERR", name);
/*      */       } 
/*  395 */       getParser().reportError(3, errorMsg);
/*  396 */       return this._type = Type.Void; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Type typeCheckStandard(SymbolTable stable) throws TypeCheckError {
/*  407 */     this._fname.clearNamespace();
/*      */     
/*  409 */     int n = this._arguments.size();
/*  410 */     Vector argsType = typeCheckArgs(stable);
/*  411 */     MethodType args = new MethodType(Type.Void, argsType);
/*  412 */     MethodType ptype = lookupPrimop(stable, this._fname.getLocalPart(), args);
/*      */ 
/*      */     
/*  415 */     if (ptype != null) {
/*  416 */       for (int i = 0; i < n; i++) {
/*  417 */         Type argType = ptype.argsType().elementAt(i);
/*  418 */         Expression exp = this._arguments.elementAt(i);
/*  419 */         if (!argType.identicalTo(exp.getType())) {
/*      */           
/*  421 */           try { this._arguments.setElementAt(new CastExpr(exp, argType), i); } catch (TypeCheckError e)
/*      */           
/*      */           { 
/*  424 */             throw new TypeCheckError(this); }
/*      */         
/*      */         }
/*      */       } 
/*  428 */       this._chosenMethodType = ptype;
/*  429 */       return this._type = ptype.resultType();
/*      */     } 
/*  431 */     throw new TypeCheckError(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Type typeCheckConstructor(SymbolTable stable) throws TypeCheckError {
/*  437 */     Vector constructors = findConstructors();
/*  438 */     if (constructors == null)
/*      */     {
/*  440 */       throw new TypeCheckError("CONSTRUCTOR_NOT_FOUND", this._className);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  445 */     int nConstructors = constructors.size();
/*  446 */     int nArgs = this._arguments.size();
/*  447 */     Vector argsType = typeCheckArgs(stable);
/*      */ 
/*      */     
/*  450 */     int bestConstrDistance = Integer.MAX_VALUE;
/*  451 */     this._type = null;
/*  452 */     for (int i = 0; i < nConstructors; i++) {
/*      */       
/*  454 */       Constructor constructor = constructors.elementAt(i);
/*      */       
/*  456 */       Class[] paramTypes = constructor.getParameterTypes();
/*      */       
/*  458 */       Class extType = null;
/*  459 */       int currConstrDistance = 0; int j;
/*  460 */       for (j = 0; j < nArgs; j++) {
/*      */         
/*  462 */         extType = paramTypes[j];
/*  463 */         Type intType = argsType.elementAt(j);
/*  464 */         Object match = _internal2Java.maps(intType, extType);
/*  465 */         if (match != null) {
/*  466 */           currConstrDistance += ((JavaType)match).distance;
/*      */         }
/*  468 */         else if (intType instanceof ObjectType) {
/*  469 */           ObjectType objectType = (ObjectType)intType;
/*  470 */           if (objectType.getJavaClass() != extType)
/*      */           {
/*  472 */             if (extType.isAssignableFrom(objectType.getJavaClass())) {
/*  473 */               currConstrDistance++;
/*      */             } else {
/*  475 */               currConstrDistance = Integer.MAX_VALUE;
/*      */               
/*      */               break;
/*      */             } 
/*      */           }
/*      */         } else {
/*  481 */           currConstrDistance = Integer.MAX_VALUE;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  486 */       if (j == nArgs && currConstrDistance < bestConstrDistance) {
/*  487 */         this._chosenConstructor = constructor;
/*  488 */         this._isExtConstructor = true;
/*  489 */         bestConstrDistance = currConstrDistance;
/*      */         
/*  491 */         this._type = (this._clazz != null) ? Type.newObjectType(this._clazz) : Type.newObjectType(this._className);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  496 */     if (this._type != null) {
/*  497 */       return this._type;
/*      */     }
/*      */     
/*  500 */     this.resolveDynamic = true;
/*      */ 
/*      */     
/*  503 */     return this._type = (this._clazz != null) ? Type.newObjectType(this._clazz) : Type.newObjectType(this._className);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Type typeCheckExternal(SymbolTable stable) throws TypeCheckError {
/*  516 */     int nArgs = this._arguments.size();
/*  517 */     String name = this._fname.getLocalPart();
/*      */ 
/*      */     
/*  520 */     if (this._fname.getLocalPart().equals("new")) {
/*  521 */       return typeCheckConstructor(stable);
/*      */     }
/*      */ 
/*      */     
/*  525 */     boolean hasThisArgument = false;
/*      */     
/*  527 */     if (nArgs == 0) {
/*  528 */       this._isStatic = true;
/*      */     }
/*  530 */     if (!this._isStatic) {
/*  531 */       if (this._namespace_format == 0 || this._namespace_format == 2)
/*      */       {
/*  533 */         hasThisArgument = true;
/*      */       }
/*  535 */       Expression firstArg = this._arguments.elementAt(0);
/*  536 */       Type firstArgType = firstArg.typeCheck(stable);
/*      */       
/*  538 */       if (this._namespace_format == 1 && firstArgType instanceof ObjectType && this._clazz != null && this._clazz.isAssignableFrom(((ObjectType)firstArgType).getJavaClass())) {
/*      */ 
/*      */ 
/*      */         
/*  542 */         hasThisArgument = true;
/*  543 */       } else if (firstArgType instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType) {
/*  544 */         this.resolveDynamic = true;
/*  545 */         typeCheckArgs(stable);
/*  546 */         return Type.String;
/*      */       } 
/*      */       
/*  549 */       if (hasThisArgument) {
/*  550 */         this._thisArgument = this._arguments.elementAt(0);
/*  551 */         this._arguments.remove(0); nArgs--;
/*  552 */         if (firstArgType instanceof ObjectType) {
/*  553 */           this._className = ((ObjectType)firstArgType).getJavaClassName();
/*      */         } else {
/*      */           
/*  556 */           throw new TypeCheckError("NO_JAVA_FUNCT_THIS_REF", name);
/*      */         } 
/*      */       } 
/*  559 */     } else if (this._className.length() == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  566 */       Parser parser = getParser();
/*  567 */       if (parser != null) {
/*  568 */         reportWarning(this, parser, "FUNCTION_RESOLVE_ERR", this._fname.toString());
/*      */       }
/*      */       
/*  571 */       this.unresolvedExternal = true;
/*  572 */       return this._type = Type.Int;
/*      */     } 
/*      */ 
/*      */     
/*  576 */     Vector methods = findMethods();
/*      */     
/*  578 */     if (methods == null)
/*      */     {
/*  580 */       throw new TypeCheckError("METHOD_NOT_FOUND_ERR", this._className + "." + name);
/*      */     }
/*      */     
/*  583 */     Class extType = null;
/*  584 */     int nMethods = methods.size();
/*  585 */     Vector argsType = typeCheckArgs(stable);
/*      */ 
/*      */     
/*  588 */     int bestMethodDistance = Integer.MAX_VALUE;
/*  589 */     this._type = null;
/*  590 */     for (int i = 0; i < nMethods; i++) {
/*      */       
/*  592 */       Method method = methods.elementAt(i);
/*  593 */       Class[] paramTypes = method.getParameterTypes();
/*      */       
/*  595 */       int currMethodDistance = 0; int j;
/*  596 */       for (j = 0; j < nArgs; j++) {
/*      */         
/*  598 */         extType = paramTypes[j];
/*  599 */         Type intType = argsType.elementAt(j);
/*  600 */         Object match = _internal2Java.maps(intType, extType);
/*  601 */         if (match != null) {
/*  602 */           currMethodDistance += ((JavaType)match).distance;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  609 */         else if (intType instanceof org.apache.xalan.xsltc.compiler.util.ReferenceType) {
/*  610 */           currMethodDistance++;
/*      */         }
/*  612 */         else if (intType instanceof ObjectType) {
/*  613 */           ObjectType object = (ObjectType)intType;
/*  614 */           if (extType.getName().equals(object.getJavaClassName())) {
/*  615 */             currMethodDistance += 0;
/*  616 */           } else if (extType.isAssignableFrom(object.getJavaClass())) {
/*  617 */             currMethodDistance++;
/*      */           } else {
/*  619 */             currMethodDistance = Integer.MAX_VALUE;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } else {
/*  624 */           currMethodDistance = Integer.MAX_VALUE;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/*  630 */       if (j == nArgs) {
/*      */         
/*  632 */         extType = method.getReturnType();
/*      */         
/*  634 */         this._type = (Type)_java2Internal.get(extType);
/*  635 */         if (this._type == null) {
/*  636 */           this._type = Type.newObjectType(extType);
/*      */         }
/*      */ 
/*      */         
/*  640 */         if (this._type != null && currMethodDistance < bestMethodDistance) {
/*  641 */           this._chosenMethod = method;
/*  642 */           bestMethodDistance = currMethodDistance;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  649 */     if (this._chosenMethod != null && this._thisArgument == null && !Modifier.isStatic(this._chosenMethod.getModifiers()))
/*      */     {
/*  651 */       throw new TypeCheckError("NO_JAVA_FUNCT_THIS_REF", getMethodSignature(argsType));
/*      */     }
/*      */     
/*  654 */     if (this._type != null) {
/*  655 */       if (this._type == Type.NodeSet) {
/*  656 */         getXSLTC().setMultiDocument(true);
/*      */       }
/*  658 */       return this._type;
/*      */     } 
/*      */     
/*  661 */     throw new TypeCheckError("ARGUMENT_CONVERSION_ERR", getMethodSignature(argsType));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vector typeCheckArgs(SymbolTable stable) throws TypeCheckError {
/*  668 */     Vector result = new Vector();
/*  669 */     Enumeration e = this._arguments.elements();
/*  670 */     while (e.hasMoreElements()) {
/*  671 */       Expression exp = e.nextElement();
/*  672 */       result.addElement(exp.typeCheck(stable));
/*      */     } 
/*  674 */     return result;
/*      */   }
/*      */   
/*      */   protected final Expression argument(int i) {
/*  678 */     return this._arguments.elementAt(i);
/*      */   }
/*      */   
/*      */   protected final Expression argument() {
/*  682 */     return argument(0);
/*      */   }
/*      */   
/*      */   protected final int argumentCount() {
/*  686 */     return this._arguments.size();
/*      */   }
/*      */   
/*      */   protected final void setArgument(int i, Expression exp) {
/*  690 */     this._arguments.setElementAt(exp, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void translateDesynthesized(ClassGenerator classGen, MethodGenerator methodGen) {
/*  700 */     Type type = Type.Boolean;
/*  701 */     if (this._chosenMethodType != null) {
/*  702 */       type = this._chosenMethodType.resultType();
/*      */     }
/*  704 */     InstructionList il = methodGen.getInstructionList();
/*  705 */     translate(classGen, methodGen);
/*      */     
/*  707 */     if (type instanceof org.apache.xalan.xsltc.compiler.util.BooleanType || type instanceof org.apache.xalan.xsltc.compiler.util.IntType) {
/*  708 */       this._falseList.add((InstructionHandle)il.append((BranchInstruction)new IFEQ(null)));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  718 */     int n = argumentCount();
/*  719 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  720 */     InstructionList il = methodGen.getInstructionList();
/*      */ 
/*      */ 
/*      */     
/*  724 */     if (isStandard() || isExtension()) {
/*  725 */       for (int i = 0; i < n; i++) {
/*  726 */         Expression exp = argument(i);
/*  727 */         exp.translate(classGen, methodGen);
/*  728 */         exp.startIterator(classGen, methodGen);
/*      */       } 
/*      */ 
/*      */       
/*  732 */       String name = this._fname.toString().replace('-', '_') + "F";
/*  733 */       String args = "";
/*      */ 
/*      */       
/*  736 */       if (name.equals("sumF")) {
/*  737 */         args = "Lorg/apache/xalan/xsltc/DOM;";
/*  738 */         il.append(methodGen.loadDOM());
/*      */       }
/*  740 */       else if (name.equals("normalize_spaceF") && 
/*  741 */         this._chosenMethodType.toSignature(args).equals("()Ljava/lang/String;")) {
/*      */         
/*  743 */         args = "ILorg/apache/xalan/xsltc/DOM;";
/*  744 */         il.append(methodGen.loadContextNode());
/*  745 */         il.append(methodGen.loadDOM());
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  750 */       int index = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", name, this._chosenMethodType.toSignature(args));
/*      */       
/*  752 */       il.append((Instruction)new INVOKESTATIC(index));
/*      */ 
/*      */     
/*      */     }
/*  756 */     else if (this.unresolvedExternal) {
/*  757 */       int i = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "unresolved_externalF", "(Ljava/lang/String;)V");
/*      */ 
/*      */       
/*  760 */       il.append((CompoundInstruction)new PUSH(cpg, this._fname.toString()));
/*  761 */       il.append((Instruction)new INVOKESTATIC(i));
/*      */     }
/*  763 */     else if (this._isExtConstructor) {
/*  764 */       String clazz = this._chosenConstructor.getDeclaringClass().getName();
/*      */       
/*  766 */       Class[] paramTypes = this._chosenConstructor.getParameterTypes();
/*      */       
/*  768 */       il.append((Instruction)new NEW(cpg.addClass(this._className)));
/*  769 */       il.append((Instruction)InstructionConstants.DUP);
/*      */       
/*  771 */       for (int i = 0; i < n; i++) {
/*  772 */         Expression exp = argument(i);
/*  773 */         exp.translate(classGen, methodGen);
/*      */         
/*  775 */         exp.startIterator(classGen, methodGen);
/*  776 */         exp.getType().translateTo(classGen, methodGen, paramTypes[i]);
/*      */       } 
/*      */       
/*  779 */       StringBuffer buffer = new StringBuffer();
/*  780 */       buffer.append('(');
/*  781 */       for (int k = 0; k < paramTypes.length; k++) {
/*  782 */         buffer.append(getSignature(paramTypes[k]));
/*      */       }
/*  784 */       buffer.append(')');
/*  785 */       buffer.append("V");
/*      */       
/*  787 */       int j = cpg.addMethodref(clazz, "<init>", buffer.toString());
/*      */ 
/*      */       
/*  790 */       il.append((Instruction)new INVOKESPECIAL(j));
/*      */ 
/*      */       
/*  793 */       Type.Object.translateFrom(classGen, methodGen, this._chosenConstructor.getDeclaringClass());
/*      */ 
/*      */     
/*      */     }
/*  797 */     else if (this.resolveDynamic) {
/*      */       
/*  799 */       LocalVariableGen _local = methodGen.addLocalVariable2("objects", (Type)Type.OBJECT, il.getEnd());
/*      */ 
/*      */ 
/*      */       
/*  803 */       il.append((CompoundInstruction)new PUSH(cpg, n + 1));
/*  804 */       il.append((Instruction)new ANEWARRAY(cpg.addClass("java.lang.Object")));
/*      */ 
/*      */       
/*  807 */       il.append((Instruction)InstructionConstants.DUP);
/*  808 */       if (this._thisArgument != null) {
/*  809 */         il.append((CompoundInstruction)new PUSH(cpg, 0));
/*  810 */         this._thisArgument.translate(classGen, methodGen);
/*      */       }
/*      */       else {
/*      */         
/*  814 */         il.append((CompoundInstruction)new PUSH(cpg, 0));
/*  815 */         il.append(InstructionConstants.ACONST_NULL);
/*      */       } 
/*  817 */       il.append((Instruction)InstructionConstants.AASTORE);
/*      */ 
/*      */       
/*  820 */       for (int i = 0; i < n; i++) {
/*  821 */         il.append((Instruction)InstructionConstants.DUP);
/*  822 */         il.append((CompoundInstruction)new PUSH(cpg, i + 1));
/*  823 */         Expression exp = argument(i);
/*  824 */         exp.translate(classGen, methodGen);
/*  825 */         exp.startIterator(classGen, methodGen);
/*  826 */         exp.getType().translateTo(classGen, methodGen, Type.Reference);
/*  827 */         il.append((Instruction)InstructionConstants.AASTORE);
/*      */       } 
/*  829 */       il.append((Instruction)new ASTORE(_local.getIndex()));
/*      */ 
/*      */       
/*  832 */       String methodName = null;
/*  833 */       if (this._chosenMethod != null) {
/*  834 */         methodName = this._chosenMethod.getName();
/*      */       } else {
/*  836 */         methodName = this._fname.getLocalPart();
/*      */       } 
/*  838 */       il.append((CompoundInstruction)new PUSH(cpg, this._className));
/*  839 */       il.append((CompoundInstruction)new PUSH(cpg, methodName));
/*  840 */       il.append((Instruction)new ALOAD(_local.getIndex()));
/*      */       
/*  842 */       int j = cpg.addMethodref("org.apache.xalan.xsltc.runtime.CallFunction", "invokeMethod", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;");
/*      */       
/*  844 */       il.append((Instruction)new INVOKESTATIC(j));
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  849 */       String clazz = this._chosenMethod.getDeclaringClass().getName();
/*  850 */       Class[] paramTypes = this._chosenMethod.getParameterTypes();
/*      */ 
/*      */       
/*  853 */       if (this._thisArgument != null) {
/*  854 */         this._thisArgument.translate(classGen, methodGen);
/*      */       }
/*      */       
/*  857 */       for (int i = 0; i < n; i++) {
/*  858 */         Expression exp = argument(i);
/*  859 */         exp.translate(classGen, methodGen);
/*      */         
/*  861 */         exp.startIterator(classGen, methodGen);
/*  862 */         exp.getType().translateTo(classGen, methodGen, paramTypes[i]);
/*      */       } 
/*      */       
/*  865 */       StringBuffer buffer = new StringBuffer();
/*  866 */       buffer.append('(');
/*  867 */       for (int j = 0; j < paramTypes.length; j++) {
/*  868 */         buffer.append(getSignature(paramTypes[j]));
/*      */       }
/*  870 */       buffer.append(')');
/*  871 */       buffer.append(getSignature(this._chosenMethod.getReturnType()));
/*      */       
/*  873 */       if (this._thisArgument != null && this._clazz.isInterface()) {
/*  874 */         int k = cpg.addInterfaceMethodref(clazz, this._fname.getLocalPart(), buffer.toString());
/*      */ 
/*      */         
/*  877 */         il.append((Instruction)new INVOKEINTERFACE(k, n + 1));
/*      */       } else {
/*      */         
/*  880 */         int k = cpg.addMethodref(clazz, this._fname.getLocalPart(), buffer.toString());
/*      */ 
/*      */         
/*  883 */         il.append((this._thisArgument != null) ? (Instruction)new INVOKEVIRTUAL(k) : (Instruction)new INVOKESTATIC(k));
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  888 */       this._type.translateFrom(classGen, methodGen, this._chosenMethod.getReturnType());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/*  894 */     return "funcall(" + this._fname + ", " + this._arguments + ')';
/*      */   }
/*      */   
/*      */   public boolean isStandard() {
/*  898 */     String namespace = this._fname.getNamespace();
/*  899 */     return (namespace == null || namespace.equals(""));
/*      */   }
/*      */   
/*      */   public boolean isExtension() {
/*  903 */     String namespace = this._fname.getNamespace();
/*  904 */     return (namespace != null && namespace.equals("http://xml.apache.org/xalan/xsltc"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Vector findMethods() {
/*  914 */     Vector result = null;
/*  915 */     String namespace = this._fname.getNamespace();
/*      */     
/*  917 */     if (this._className != null && this._className.length() > 0) {
/*  918 */       int nArgs = this._arguments.size();
/*      */       try {
/*  920 */         if (this._clazz == null) {
/*  921 */           this._clazz = ObjectFactory.findProviderClass(this._className, ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */           
/*  924 */           if (this._clazz == null) {
/*  925 */             ErrorMsg msg = new ErrorMsg("CLASS_NOT_FOUND_ERR", this._className);
/*      */             
/*  927 */             getParser().reportError(3, msg);
/*      */           } 
/*      */         } 
/*      */         
/*  931 */         String methodName = this._fname.getLocalPart();
/*  932 */         Method[] methods = this._clazz.getMethods();
/*      */         
/*  934 */         for (int i = 0; i < methods.length; i++) {
/*  935 */           int mods = methods[i].getModifiers();
/*      */           
/*  937 */           if (Modifier.isPublic(mods) && methods[i].getName().equals(methodName) && (methods[i].getParameterTypes()).length == nArgs) {
/*      */ 
/*      */ 
/*      */             
/*  941 */             if (result == null) {
/*  942 */               result = new Vector();
/*      */             }
/*  944 */             result.addElement(methods[i]);
/*      */           } 
/*      */         } 
/*      */       } catch (ClassNotFoundException e) {
/*      */         
/*  949 */         ErrorMsg msg = new ErrorMsg("CLASS_NOT_FOUND_ERR", this._className);
/*  950 */         getParser().reportError(3, msg);
/*      */       } 
/*      */     } 
/*  953 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Vector findConstructors() {
/*  962 */     Vector result = null;
/*  963 */     String namespace = this._fname.getNamespace();
/*      */     
/*  965 */     int nArgs = this._arguments.size();
/*      */     try {
/*  967 */       if (this._clazz == null) {
/*  968 */         this._clazz = ObjectFactory.findProviderClass(this._className, ObjectFactory.findClassLoader(), true);
/*      */ 
/*      */         
/*  971 */         if (this._clazz == null) {
/*  972 */           ErrorMsg msg = new ErrorMsg("CLASS_NOT_FOUND_ERR", this._className);
/*  973 */           getParser().reportError(3, msg);
/*      */         } 
/*      */       } 
/*      */       
/*  977 */       Constructor[] constructors = (Constructor[])this._clazz.getConstructors();
/*      */       
/*  979 */       for (int i = 0; i < constructors.length; i++) {
/*  980 */         int mods = constructors[i].getModifiers();
/*      */         
/*  982 */         if (Modifier.isPublic(mods) && (constructors[i].getParameterTypes()).length == nArgs) {
/*      */ 
/*      */           
/*  985 */           if (result == null) {
/*  986 */             result = new Vector();
/*      */           }
/*  988 */           result.addElement(constructors[i]);
/*      */         } 
/*      */       } 
/*      */     } catch (ClassNotFoundException e) {
/*      */       
/*  993 */       ErrorMsg msg = new ErrorMsg("CLASS_NOT_FOUND_ERR", this._className);
/*  994 */       getParser().reportError(3, msg);
/*      */     } 
/*      */     
/*  997 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String getSignature(Class clazz) {
/* 1005 */     if (clazz.isArray()) {
/* 1006 */       StringBuffer sb = new StringBuffer();
/* 1007 */       Class cl = clazz;
/* 1008 */       while (cl.isArray()) {
/* 1009 */         sb.append("[");
/* 1010 */         cl = cl.getComponentType();
/*      */       } 
/* 1012 */       sb.append(getSignature(cl));
/* 1013 */       return sb.toString();
/*      */     } 
/* 1015 */     if (clazz.isPrimitive()) {
/* 1016 */       if (clazz == int.class) {
/* 1017 */         return "I";
/*      */       }
/* 1019 */       if (clazz == byte.class) {
/* 1020 */         return "B";
/*      */       }
/* 1022 */       if (clazz == long.class) {
/* 1023 */         return "J";
/*      */       }
/* 1025 */       if (clazz == float.class) {
/* 1026 */         return "F";
/*      */       }
/* 1028 */       if (clazz == double.class) {
/* 1029 */         return "D";
/*      */       }
/* 1031 */       if (clazz == short.class) {
/* 1032 */         return "S";
/*      */       }
/* 1034 */       if (clazz == char.class) {
/* 1035 */         return "C";
/*      */       }
/* 1037 */       if (clazz == boolean.class) {
/* 1038 */         return "Z";
/*      */       }
/* 1040 */       if (clazz == void.class) {
/* 1041 */         return "V";
/*      */       }
/*      */       
/* 1044 */       String name = clazz.toString();
/* 1045 */       ErrorMsg err = new ErrorMsg("UNKNOWN_SIG_TYPE_ERR", name);
/* 1046 */       throw new Error(err.toString());
/*      */     } 
/*      */ 
/*      */     
/* 1050 */     return "L" + clazz.getName().replace('.', '/') + ';';
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String getSignature(Method meth) {
/* 1058 */     StringBuffer sb = new StringBuffer();
/* 1059 */     sb.append('(');
/* 1060 */     Class[] params = meth.getParameterTypes();
/* 1061 */     for (int j = 0; j < params.length; j++) {
/* 1062 */       sb.append(getSignature(params[j]));
/*      */     }
/* 1064 */     return sb.append(')').append(getSignature(meth.getReturnType())).toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String getSignature(Constructor cons) {
/* 1072 */     StringBuffer sb = new StringBuffer();
/* 1073 */     sb.append('(');
/* 1074 */     Class[] params = cons.getParameterTypes();
/* 1075 */     for (int j = 0; j < params.length; j++) {
/* 1076 */       sb.append(getSignature(params[j]));
/*      */     }
/* 1078 */     return sb.append(")V").toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getMethodSignature(Vector argsType) {
/* 1085 */     StringBuffer buf = new StringBuffer(this._className);
/* 1086 */     buf.append('.').append(this._fname.getLocalPart()).append('(');
/*      */     
/* 1088 */     int nArgs = argsType.size();
/* 1089 */     for (int i = 0; i < nArgs; i++) {
/* 1090 */       Type intType = argsType.elementAt(i);
/* 1091 */       buf.append(intType.toString());
/* 1092 */       if (i < nArgs - 1) buf.append(", ");
/*      */     
/*      */     } 
/* 1095 */     buf.append(')');
/* 1096 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static String replaceDash(String name) {
/* 1106 */     char dash = '-';
/* 1107 */     StringBuffer buff = new StringBuffer("");
/* 1108 */     for (int i = 0; i < name.length(); i++) {
/* 1109 */       if (i > 0 && name.charAt(i - 1) == dash) {
/* 1110 */         buff.append(Character.toUpperCase(name.charAt(i)));
/* 1111 */       } else if (name.charAt(i) != dash) {
/* 1112 */         buff.append(name.charAt(i));
/*      */       } 
/* 1114 */     }  return buff.toString();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/FunctionCall.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */