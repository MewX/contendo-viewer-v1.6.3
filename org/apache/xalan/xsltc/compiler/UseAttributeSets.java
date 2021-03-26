/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
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
/*     */ final class UseAttributeSets
/*     */   extends Instruction
/*     */ {
/*     */   private static final String ATTR_SET_NOT_FOUND = "";
/*  46 */   private final Vector _sets = new Vector(2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UseAttributeSets(String setNames, Parser parser) {
/*  52 */     setParser(parser);
/*  53 */     addAttributeSets(setNames);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttributeSets(String setNames) {
/*  62 */     if (setNames != null && !setNames.equals("")) {
/*  63 */       StringTokenizer tokens = new StringTokenizer(setNames);
/*  64 */       while (tokens.hasMoreTokens()) {
/*  65 */         QName qname = getParser().getQNameIgnoreDefaultNs(tokens.nextToken());
/*     */         
/*  67 */         this._sets.add(qname);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  76 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/*  84 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*  85 */     InstructionList il = methodGen.getInstructionList();
/*  86 */     SymbolTable symbolTable = getParser().getSymbolTable();
/*     */ 
/*     */     
/*  89 */     for (int i = 0; i < this._sets.size(); i++) {
/*     */       
/*  91 */       QName name = this._sets.elementAt(i);
/*     */       
/*  93 */       AttributeSet attrs = symbolTable.lookupAttributeSet(name);
/*     */       
/*  95 */       if (attrs != null) {
/*  96 */         String methodName = attrs.getMethodName();
/*  97 */         il.append(classGen.loadTranslet());
/*  98 */         il.append(methodGen.loadDOM());
/*  99 */         il.append(methodGen.loadIterator());
/* 100 */         il.append(methodGen.loadHandler());
/* 101 */         int method = cpg.addMethodref(classGen.getClassName(), methodName, "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V");
/*     */         
/* 103 */         il.append((Instruction)new INVOKESPECIAL(method));
/*     */       }
/*     */       else {
/*     */         
/* 107 */         Parser parser = getParser();
/* 108 */         String atrs = name.toString();
/* 109 */         reportError(this, parser, "ATTRIBSET_UNDEF_ERR", atrs);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/UseAttributeSets.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */