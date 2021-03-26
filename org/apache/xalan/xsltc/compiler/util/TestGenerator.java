/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.Type;
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
/*     */ public final class TestGenerator
/*     */   extends MethodGenerator
/*     */ {
/*  37 */   private static int CONTEXT_NODE_INDEX = 1;
/*  38 */   private static int CURRENT_NODE_INDEX = 4;
/*  39 */   private static int ITERATOR_INDEX = 6;
/*     */   
/*     */   private Instruction _aloadDom;
/*     */   
/*     */   private final Instruction _iloadCurrent;
/*     */   
/*     */   private final Instruction _iloadContext;
/*     */   
/*     */   private final Instruction _istoreCurrent;
/*     */   private final Instruction _istoreContext;
/*     */   private final Instruction _astoreIterator;
/*     */   private final Instruction _aloadIterator;
/*     */   
/*     */   public TestGenerator(int access_flags, Type return_type, Type[] arg_types, String[] arg_names, String method_name, String class_name, InstructionList il, ConstantPoolGen cp) {
/*  53 */     super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cp);
/*     */ 
/*     */     
/*  56 */     this._iloadCurrent = (Instruction)new ILOAD(CURRENT_NODE_INDEX);
/*  57 */     this._istoreCurrent = (Instruction)new ISTORE(CURRENT_NODE_INDEX);
/*  58 */     this._iloadContext = (Instruction)new ILOAD(CONTEXT_NODE_INDEX);
/*  59 */     this._istoreContext = (Instruction)new ILOAD(CONTEXT_NODE_INDEX);
/*  60 */     this._astoreIterator = (Instruction)new ASTORE(ITERATOR_INDEX);
/*  61 */     this._aloadIterator = (Instruction)new ALOAD(ITERATOR_INDEX);
/*     */   }
/*     */   
/*     */   public int getHandlerIndex() {
/*  65 */     return -1;
/*     */   }
/*     */   
/*     */   public int getIteratorIndex() {
/*  69 */     return ITERATOR_INDEX;
/*     */   }
/*     */   
/*     */   public void setDomIndex(int domIndex) {
/*  73 */     this._aloadDom = (Instruction)new ALOAD(domIndex);
/*     */   }
/*     */   
/*     */   public Instruction loadDOM() {
/*  77 */     return this._aloadDom;
/*     */   }
/*     */   
/*     */   public Instruction loadCurrentNode() {
/*  81 */     return this._iloadCurrent;
/*     */   }
/*     */ 
/*     */   
/*     */   public Instruction loadContextNode() {
/*  86 */     return this._iloadContext;
/*     */   }
/*     */   
/*     */   public Instruction storeContextNode() {
/*  90 */     return this._istoreContext;
/*     */   }
/*     */   
/*     */   public Instruction storeCurrentNode() {
/*  94 */     return this._istoreCurrent;
/*     */   }
/*     */   
/*     */   public Instruction storeIterator() {
/*  98 */     return this._astoreIterator;
/*     */   }
/*     */   
/*     */   public Instruction loadIterator() {
/* 102 */     return this._aloadIterator;
/*     */   }
/*     */   
/*     */   public int getLocalIndex(String name) {
/* 106 */     if (name.equals("current")) {
/* 107 */       return CURRENT_NODE_INDEX;
/*     */     }
/*     */     
/* 110 */     return super.getLocalIndex(name);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/TestGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */