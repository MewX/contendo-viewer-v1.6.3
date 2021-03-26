/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ClassGen;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.xalan.xsltc.compiler.Parser;
/*     */ import org.apache.xalan.xsltc.compiler.Stylesheet;
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
/*     */ public class ClassGenerator
/*     */   extends ClassGen
/*     */ {
/*  46 */   protected static int TRANSLET_INDEX = 0;
/*  47 */   protected static int INVALID_INDEX = -1;
/*     */   
/*     */   private Stylesheet _stylesheet;
/*     */   
/*     */   private final Parser _parser;
/*     */   
/*     */   private final Instruction _aloadTranslet;
/*     */   
/*     */   private final String _domClass;
/*     */   
/*     */   private final String _domClassSig;
/*     */   private final String _applyTemplatesSig;
/*     */   
/*     */   public ClassGenerator(String class_name, String super_class_name, String file_name, int access_flags, String[] interfaces, Stylesheet stylesheet) {
/*  61 */     super(class_name, super_class_name, file_name, access_flags, interfaces);
/*     */     
/*  63 */     this._stylesheet = stylesheet;
/*  64 */     this._parser = stylesheet.getParser();
/*  65 */     this._aloadTranslet = (Instruction)new ALOAD(TRANSLET_INDEX);
/*     */     
/*  67 */     if (stylesheet.isMultiDocument()) {
/*  68 */       this._domClass = "org.apache.xalan.xsltc.dom.MultiDOM";
/*  69 */       this._domClassSig = "Lorg/apache/xalan/xsltc/dom/MultiDOM;";
/*     */     } else {
/*     */       
/*  72 */       this._domClass = "org.apache.xalan.xsltc.dom.DOMAdapter";
/*  73 */       this._domClassSig = "Lorg/apache/xalan/xsltc/dom/DOMAdapter;";
/*     */     } 
/*  75 */     this._applyTemplatesSig = "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Parser getParser() {
/*  83 */     return this._parser;
/*     */   }
/*     */   
/*     */   public final Stylesheet getStylesheet() {
/*  87 */     return this._stylesheet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getClassName() {
/*  95 */     return this._stylesheet.getClassName();
/*     */   }
/*     */   
/*     */   public Instruction loadTranslet() {
/*  99 */     return this._aloadTranslet;
/*     */   }
/*     */   
/*     */   public final String getDOMClass() {
/* 103 */     return this._domClass;
/*     */   }
/*     */   
/*     */   public final String getDOMClassSig() {
/* 107 */     return this._domClassSig;
/*     */   }
/*     */   
/*     */   public final String getApplyTemplatesSig() {
/* 111 */     return this._applyTemplatesSig;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExternal() {
/* 119 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/ClassGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */