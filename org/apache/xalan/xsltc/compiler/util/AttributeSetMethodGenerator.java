/*    */ package org.apache.xalan.xsltc.compiler.util;
/*    */ 
/*    */ import org.apache.bcel.generic.ALOAD;
/*    */ import org.apache.bcel.generic.ASTORE;
/*    */ import org.apache.bcel.generic.ClassGen;
/*    */ import org.apache.bcel.generic.Instruction;
/*    */ import org.apache.bcel.generic.InstructionList;
/*    */ import org.apache.bcel.generic.Type;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class AttributeSetMethodGenerator
/*    */   extends MethodGenerator
/*    */ {
/*    */   private static final int DOM_INDEX = 1;
/*    */   private static final int ITERATOR_INDEX = 2;
/*    */   private static final int HANDLER_INDEX = 3;
/* 37 */   private static final Type[] argTypes = new Type[3];
/*    */   
/* 39 */   private static final String[] argNames = new String[3];
/*    */   
/*    */   static {
/* 42 */     argTypes[0] = Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;");
/* 43 */     argNames[0] = "dom";
/* 44 */     argTypes[1] = Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;");
/* 45 */     argNames[1] = "iterator";
/* 46 */     argTypes[2] = Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;");
/* 47 */     argNames[2] = "handler";
/*    */   }
/*    */ 
/*    */   
/*    */   private final Instruction _aloadDom;
/*    */   private final Instruction _astoreDom;
/*    */   private final Instruction _astoreIterator;
/*    */   private final Instruction _aloadIterator;
/*    */   private final Instruction _astoreHandler;
/*    */   private final Instruction _aloadHandler;
/*    */   
/*    */   public AttributeSetMethodGenerator(String methodName, ClassGen classGen) {
/* 59 */     super(2, (Type)Type.VOID, argTypes, argNames, methodName, classGen.getClassName(), new InstructionList(), classGen.getConstantPool());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 66 */     this._aloadDom = (Instruction)new ALOAD(1);
/* 67 */     this._astoreDom = (Instruction)new ASTORE(1);
/* 68 */     this._astoreIterator = (Instruction)new ASTORE(2);
/* 69 */     this._aloadIterator = (Instruction)new ALOAD(2);
/* 70 */     this._astoreHandler = (Instruction)new ASTORE(3);
/* 71 */     this._aloadHandler = (Instruction)new ALOAD(3);
/*    */   }
/*    */   
/*    */   public Instruction storeIterator() {
/* 75 */     return this._astoreIterator;
/*    */   }
/*    */   
/*    */   public Instruction loadIterator() {
/* 79 */     return this._aloadIterator;
/*    */   }
/*    */   
/*    */   public int getIteratorIndex() {
/* 83 */     return 2;
/*    */   }
/*    */   
/*    */   public Instruction storeHandler() {
/* 87 */     return this._astoreHandler;
/*    */   }
/*    */   
/*    */   public Instruction loadHandler() {
/* 91 */     return this._aloadHandler;
/*    */   }
/*    */   
/*    */   public int getLocalIndex(String name) {
/* 95 */     return -1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/AttributeSetMethodGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */