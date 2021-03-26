/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.NEW;
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
/*     */ final class ParentLocationPath
/*     */   extends RelativeLocationPath
/*     */ {
/*     */   private Expression _step;
/*     */   private final RelativeLocationPath _path;
/*     */   private Type stype;
/*     */   private boolean _orderNodes = false;
/*     */   private boolean _axisMismatch = false;
/*     */   
/*     */   public ParentLocationPath(RelativeLocationPath path, Expression step) {
/*  47 */     this._path = path;
/*  48 */     this._step = step;
/*  49 */     this._path.setParent(this);
/*  50 */     this._step.setParent(this);
/*     */     
/*  52 */     if (this._step instanceof Step) {
/*  53 */       this._axisMismatch = checkAxisMismatch();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setAxis(int axis) {
/*  58 */     this._path.setAxis(axis);
/*     */   }
/*     */   
/*     */   public int getAxis() {
/*  62 */     return this._path.getAxis();
/*     */   }
/*     */   
/*     */   public RelativeLocationPath getPath() {
/*  66 */     return this._path;
/*     */   }
/*     */   
/*     */   public Expression getStep() {
/*  70 */     return this._step;
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  74 */     super.setParser(parser);
/*  75 */     this._step.setParser(parser);
/*  76 */     this._path.setParser(parser);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  80 */     return "ParentLocationPath(" + this._path + ", " + this._step + ')';
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  84 */     this.stype = this._step.typeCheck(stable);
/*  85 */     this._path.typeCheck(stable);
/*     */     
/*  87 */     if (this._axisMismatch) enableNodeOrdering();
/*     */     
/*  89 */     return this._type = Type.NodeSet;
/*     */   }
/*     */   
/*     */   public void enableNodeOrdering() {
/*  93 */     SyntaxTreeNode parent = getParent();
/*  94 */     if (parent instanceof ParentLocationPath) {
/*  95 */       ((ParentLocationPath)parent).enableNodeOrdering();
/*     */     } else {
/*  97 */       this._orderNodes = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkAxisMismatch() {
/* 108 */     int left = this._path.getAxis();
/* 109 */     int right = ((Step)this._step).getAxis();
/*     */     
/* 111 */     if ((left == 0 || left == 1) && (right == 3 || right == 4 || right == 5 || right == 10 || right == 11 || right == 12))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 118 */       return true;
/*     */     }
/* 120 */     if ((left == 3 && right == 0) || right == 1 || right == 10 || right == 11)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 125 */       return true;
/*     */     }
/* 127 */     if (left == 4 || left == 5) {
/* 128 */       return true;
/*     */     }
/* 130 */     if ((left == 6 || left == 7) && (right == 6 || right == 10 || right == 11 || right == 12))
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 135 */       return true;
/*     */     }
/* 137 */     if ((left == 11 || left == 12) && (right == 4 || right == 5 || right == 6 || right == 7 || right == 10 || right == 11 || right == 12))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       return true;
/*     */     }
/* 147 */     if (right == 6 && left == 3)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 152 */       if (this._path instanceof Step) {
/* 153 */         int type = ((Step)this._path).getNodeType();
/* 154 */         if (type == 2) return true;
/*     */       
/*     */       } 
/*     */     }
/* 158 */     return false;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 162 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 163 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 166 */     int initSI = cpg.addMethodref("org.apache.xalan.xsltc.dom.StepIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/dtm/DTMAxisIterator;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.StepIterator")));
/* 173 */     il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */     
/* 176 */     this._path.translate(classGen, methodGen);
/* 177 */     this._step.translate(classGen, methodGen);
/*     */ 
/*     */     
/* 180 */     il.append((Instruction)new INVOKESPECIAL(initSI));
/*     */ 
/*     */     
/* 183 */     Expression stp = this._step;
/* 184 */     if (stp instanceof ParentLocationPath) {
/* 185 */       stp = ((ParentLocationPath)stp).getStep();
/*     */     }
/* 187 */     if (this._path instanceof Step && stp instanceof Step) {
/* 188 */       int path = ((Step)this._path).getAxis();
/* 189 */       int step = ((Step)stp).getAxis();
/* 190 */       if ((path == 5 && step == 3) || (path == 11 && step == 10)) {
/*     */         
/* 192 */         int incl = cpg.addMethodref("org.apache.xml.dtm.ref.DTMAxisIteratorBase", "includeSelf", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */         
/* 195 */         il.append((Instruction)new INVOKEVIRTUAL(incl));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     if (this._orderNodes) {
/* 206 */       int order = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "orderNodes", "(Lorg/apache/xml/dtm/DTMAxisIterator;I)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */       
/* 209 */       il.append(methodGen.loadDOM());
/* 210 */       il.append((Instruction)InstructionConstants.SWAP);
/* 211 */       il.append(methodGen.loadContextNode());
/* 212 */       il.append((Instruction)new INVOKEINTERFACE(order, 3));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/ParentLocationPath.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */