/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.ICONST;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
/*     */ import org.apache.bcel.generic.MethodGen;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.xalan.xsltc.compiler.Constants;
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
/*     */ public class MethodGenerator
/*     */   extends MethodGen
/*     */   implements Constants
/*     */ {
/*     */   protected static final int INVALID_INDEX = -1;
/*     */   private static final String START_ELEMENT_SIG = "(Ljava/lang/String;)V";
/*     */   private static final String END_ELEMENT_SIG = "(Ljava/lang/String;)V";
/*     */   private InstructionList _mapTypeSub;
/*     */   private static final int DOM_INDEX = 1;
/*     */   private static final int ITERATOR_INDEX = 2;
/*     */   private static final int HANDLER_INDEX = 3;
/*     */   private Instruction _iloadCurrent;
/*     */   private Instruction _istoreCurrent;
/*     */   private final Instruction _astoreHandler;
/*     */   private final Instruction _aloadHandler;
/*     */   private final Instruction _astoreIterator;
/*     */   private final Instruction _aloadIterator;
/*     */   private final Instruction _aloadDom;
/*     */   private final Instruction _astoreDom;
/*     */   private final Instruction _startElement;
/*     */   private final Instruction _endElement;
/*     */   private final Instruction _startDocument;
/*     */   private final Instruction _endDocument;
/*     */   private final Instruction _attribute;
/*     */   private final Instruction _uniqueAttribute;
/*     */   private final Instruction _namespace;
/*     */   private final Instruction _setStartNode;
/*     */   private final Instruction _reset;
/*     */   private final Instruction _nextNode;
/*     */   private SlotAllocator _slotAllocator;
/*     */   private boolean _allocatorInit = false;
/*     */   
/*     */   public MethodGenerator(int access_flags, Type return_type, Type[] arg_types, String[] arg_names, String method_name, String class_name, InstructionList il, ConstantPoolGen cpg) {
/*  83 */     super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cpg);
/*     */ 
/*     */     
/*  86 */     this._astoreHandler = (Instruction)new ASTORE(3);
/*  87 */     this._aloadHandler = (Instruction)new ALOAD(3);
/*  88 */     this._astoreIterator = (Instruction)new ASTORE(2);
/*  89 */     this._aloadIterator = (Instruction)new ALOAD(2);
/*  90 */     this._aloadDom = (Instruction)new ALOAD(1);
/*  91 */     this._astoreDom = (Instruction)new ASTORE(1);
/*     */     
/*  93 */     int startElement = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "startElement", "(Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */     
/*  97 */     this._startElement = (Instruction)new INVOKEINTERFACE(startElement, 2);
/*     */     
/*  99 */     int endElement = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "endElement", "(Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */     
/* 103 */     this._endElement = (Instruction)new INVOKEINTERFACE(endElement, 2);
/*     */     
/* 105 */     int attribute = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "addAttribute", "(Ljava/lang/String;Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     this._attribute = (Instruction)new INVOKEINTERFACE(attribute, 3);
/*     */     
/* 114 */     int uniqueAttribute = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "addUniqueAttribute", "(Ljava/lang/String;Ljava/lang/String;I)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     this._uniqueAttribute = (Instruction)new INVOKEINTERFACE(uniqueAttribute, 4);
/*     */     
/* 123 */     int namespace = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "namespaceAfterStartElement", "(Ljava/lang/String;Ljava/lang/String;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     this._namespace = (Instruction)new INVOKEINTERFACE(namespace, 3);
/*     */     
/* 132 */     int index = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "startDocument", "()V");
/*     */ 
/*     */     
/* 135 */     this._startDocument = (Instruction)new INVOKEINTERFACE(index, 1);
/*     */     
/* 137 */     index = cpg.addInterfaceMethodref("org.apache.xml.serializer.SerializationHandler", "endDocument", "()V");
/*     */ 
/*     */     
/* 140 */     this._endDocument = (Instruction)new INVOKEINTERFACE(index, 1);
/*     */ 
/*     */     
/* 143 */     index = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "setStartNode", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */     
/* 146 */     this._setStartNode = (Instruction)new INVOKEINTERFACE(index, 2);
/*     */     
/* 148 */     index = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "reset", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */     
/* 150 */     this._reset = (Instruction)new INVOKEINTERFACE(index, 1);
/*     */     
/* 152 */     index = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "next", "()I");
/* 153 */     this._nextNode = (Instruction)new INVOKEINTERFACE(index, 1);
/*     */     
/* 155 */     this._slotAllocator = new SlotAllocator();
/* 156 */     this._slotAllocator.initialize(getLocalVariables());
/* 157 */     this._allocatorInit = true;
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
/*     */   public LocalVariableGen addLocalVariable(String name, Type type, InstructionHandle start, InstructionHandle end) {
/* 170 */     return this._allocatorInit ? addLocalVariable2(name, type, start) : super.addLocalVariable(name, type, start, end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalVariableGen addLocalVariable2(String name, Type type, InstructionHandle start) {
/* 177 */     return addLocalVariable(name, type, this._slotAllocator.allocateSlot(type), start, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeLocalVariable(LocalVariableGen lvg) {
/* 183 */     this._slotAllocator.releaseSlot(lvg);
/* 184 */     super.removeLocalVariable(lvg);
/*     */   }
/*     */   
/*     */   public Instruction loadDOM() {
/* 188 */     return this._aloadDom;
/*     */   }
/*     */   
/*     */   public Instruction storeDOM() {
/* 192 */     return this._astoreDom;
/*     */   }
/*     */   
/*     */   public Instruction storeHandler() {
/* 196 */     return this._astoreHandler;
/*     */   }
/*     */   
/*     */   public Instruction loadHandler() {
/* 200 */     return this._aloadHandler;
/*     */   }
/*     */   
/*     */   public Instruction storeIterator() {
/* 204 */     return this._astoreIterator;
/*     */   }
/*     */   
/*     */   public Instruction loadIterator() {
/* 208 */     return this._aloadIterator;
/*     */   }
/*     */   
/*     */   public final Instruction setStartNode() {
/* 212 */     return this._setStartNode;
/*     */   }
/*     */   
/*     */   public final Instruction reset() {
/* 216 */     return this._reset;
/*     */   }
/*     */   
/*     */   public final Instruction nextNode() {
/* 220 */     return this._nextNode;
/*     */   }
/*     */   
/*     */   public final Instruction startElement() {
/* 224 */     return this._startElement;
/*     */   }
/*     */   
/*     */   public final Instruction endElement() {
/* 228 */     return this._endElement;
/*     */   }
/*     */   
/*     */   public final Instruction startDocument() {
/* 232 */     return this._startDocument;
/*     */   }
/*     */   
/*     */   public final Instruction endDocument() {
/* 236 */     return this._endDocument;
/*     */   }
/*     */   
/*     */   public final Instruction attribute() {
/* 240 */     return this._attribute;
/*     */   }
/*     */   
/*     */   public final Instruction uniqueAttribute() {
/* 244 */     return this._uniqueAttribute;
/*     */   }
/*     */   
/*     */   public final Instruction namespace() {
/* 248 */     return this._namespace;
/*     */   }
/*     */   
/*     */   public Instruction loadCurrentNode() {
/* 252 */     if (this._iloadCurrent == null) {
/* 253 */       int idx = getLocalIndex("current");
/* 254 */       if (idx > 0) {
/* 255 */         this._iloadCurrent = (Instruction)new ILOAD(idx);
/*     */       } else {
/* 257 */         this._iloadCurrent = (Instruction)new ICONST(0);
/*     */       } 
/* 259 */     }  return this._iloadCurrent;
/*     */   }
/*     */   
/*     */   public Instruction storeCurrentNode() {
/* 263 */     return (this._istoreCurrent != null) ? this._istoreCurrent : (this._istoreCurrent = (Instruction)new ISTORE(getLocalIndex("current")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction loadContextNode() {
/* 270 */     return loadCurrentNode();
/*     */   }
/*     */   
/*     */   public Instruction storeContextNode() {
/* 274 */     return storeCurrentNode();
/*     */   }
/*     */   
/*     */   public int getLocalIndex(String name) {
/* 278 */     return getLocalVariable(name).getIndex();
/*     */   }
/*     */   
/*     */   public LocalVariableGen getLocalVariable(String name) {
/* 282 */     LocalVariableGen[] vars = getLocalVariables();
/* 283 */     for (int i = 0; i < vars.length; i++) {
/* 284 */       if (vars[i].getName().equals(name))
/* 285 */         return vars[i]; 
/* 286 */     }  return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxLocals() {
/* 292 */     int maxLocals = getMaxLocals();
/* 293 */     int prevLocals = maxLocals;
/*     */ 
/*     */     
/* 296 */     LocalVariableGen[] localVars = getLocalVariables();
/* 297 */     if (localVars != null && 
/* 298 */       localVars.length > maxLocals) {
/* 299 */       maxLocals = localVars.length;
/*     */     }
/*     */ 
/*     */     
/* 303 */     if (maxLocals < 5) maxLocals = 5;
/*     */     
/* 305 */     setMaxLocals(maxLocals);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/MethodGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */