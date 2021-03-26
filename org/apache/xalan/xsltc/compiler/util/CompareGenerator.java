/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.ACONST_NULL;
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.ISTORE;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
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
/*     */ 
/*     */ public final class CompareGenerator
/*     */   extends MethodGenerator
/*     */ {
/*  40 */   private static int DOM_INDEX = 1;
/*  41 */   private static int CURRENT_INDEX = 2;
/*  42 */   private static int LEVEL_INDEX = 3;
/*  43 */   private static int TRANSLET_INDEX = 4;
/*  44 */   private static int LAST_INDEX = 5;
/*  45 */   private int ITERATOR_INDEX = 6;
/*     */   
/*     */   private final Instruction _iloadCurrent;
/*     */   
/*     */   private final Instruction _istoreCurrent;
/*     */   
/*     */   private final Instruction _aloadDom;
/*     */   
/*     */   private final Instruction _iloadLast;
/*     */   private final Instruction _aloadIterator;
/*     */   private final Instruction _astoreIterator;
/*     */   
/*     */   public CompareGenerator(int access_flags, Type return_type, Type[] arg_types, String[] arg_names, String method_name, String class_name, InstructionList il, ConstantPoolGen cp) {
/*  58 */     super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cp);
/*     */ 
/*     */     
/*  61 */     this._iloadCurrent = (Instruction)new ILOAD(CURRENT_INDEX);
/*  62 */     this._istoreCurrent = (Instruction)new ISTORE(CURRENT_INDEX);
/*  63 */     this._aloadDom = (Instruction)new ALOAD(DOM_INDEX);
/*  64 */     this._iloadLast = (Instruction)new ILOAD(LAST_INDEX);
/*     */     
/*  66 */     LocalVariableGen iterator = addLocalVariable("iterator", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), null, null);
/*     */ 
/*     */ 
/*     */     
/*  70 */     this.ITERATOR_INDEX = iterator.getIndex();
/*  71 */     this._aloadIterator = (Instruction)new ALOAD(this.ITERATOR_INDEX);
/*  72 */     this._astoreIterator = (Instruction)new ASTORE(this.ITERATOR_INDEX);
/*  73 */     il.append((Instruction)new ACONST_NULL());
/*  74 */     il.append(storeIterator());
/*     */   }
/*     */   
/*     */   public Instruction loadLastNode() {
/*  78 */     return this._iloadLast;
/*     */   }
/*     */   
/*     */   public Instruction loadCurrentNode() {
/*  82 */     return this._iloadCurrent;
/*     */   }
/*     */   
/*     */   public Instruction storeCurrentNode() {
/*  86 */     return this._istoreCurrent;
/*     */   }
/*     */   
/*     */   public Instruction loadDOM() {
/*  90 */     return this._aloadDom;
/*     */   }
/*     */   
/*     */   public int getHandlerIndex() {
/*  94 */     return -1;
/*     */   }
/*     */   
/*     */   public int getIteratorIndex() {
/*  98 */     return -1;
/*     */   }
/*     */   
/*     */   public Instruction storeIterator() {
/* 102 */     return this._astoreIterator;
/*     */   }
/*     */   
/*     */   public Instruction loadIterator() {
/* 106 */     return this._aloadIterator;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLocalIndex(String name) {
/* 111 */     if (name.equals("current")) {
/* 112 */       return CURRENT_INDEX;
/*     */     }
/* 114 */     return super.getLocalIndex(name);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/CompareGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */