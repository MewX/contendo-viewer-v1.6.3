/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
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
/*     */ import org.apache.xalan.xsltc.dom.Axis;
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
/*     */ final class UnionPathExpr
/*     */   extends Expression
/*     */ {
/*     */   private final Expression _pathExpr;
/*     */   private final Expression _rest;
/*     */   private boolean _reverse = false;
/*     */   private Expression[] _components;
/*     */   
/*     */   public UnionPathExpr(Expression pathExpr, Expression rest) {
/*  51 */     this._pathExpr = pathExpr;
/*  52 */     this._rest = rest;
/*     */   }
/*     */   
/*     */   public void setParser(Parser parser) {
/*  56 */     super.setParser(parser);
/*     */     
/*  58 */     Vector components = new Vector();
/*  59 */     flatten(components);
/*  60 */     int size = components.size();
/*  61 */     this._components = (Expression[])components.toArray((Object[])new Expression[size]);
/*  62 */     for (int i = 0; i < size; i++) {
/*  63 */       this._components[i].setParser(parser);
/*  64 */       this._components[i].setParent(this);
/*  65 */       if (this._components[i] instanceof Step) {
/*  66 */         Step step = (Step)this._components[i];
/*  67 */         int axis = step.getAxis();
/*  68 */         int type = step.getNodeType();
/*     */         
/*  70 */         if (axis == 2 || type == 2) {
/*  71 */           this._components[i] = this._components[0];
/*  72 */           this._components[0] = step;
/*     */         } 
/*     */         
/*  75 */         if (Axis.isReverse[axis]) this._reverse = true;
/*     */       
/*     */       } 
/*     */     } 
/*  79 */     if (getParent() instanceof Expression) this._reverse = false; 
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/*  83 */     int length = this._components.length;
/*  84 */     for (int i = 0; i < length; i++) {
/*  85 */       if (this._components[i].typeCheck(stable) != Type.NodeSet) {
/*  86 */         this._components[i] = new CastExpr(this._components[i], Type.NodeSet);
/*     */       }
/*     */     } 
/*  89 */     return this._type = Type.NodeSet;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  93 */     return "union(" + this._pathExpr + ", " + this._rest + ')';
/*     */   }
/*     */   
/*     */   private void flatten(Vector components) {
/*  97 */     components.addElement(this._pathExpr);
/*  98 */     if (this._rest != null) {
/*  99 */       if (this._rest instanceof UnionPathExpr) {
/* 100 */         ((UnionPathExpr)this._rest).flatten(components);
/*     */       } else {
/*     */         
/* 103 */         components.addElement(this._rest);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 109 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 110 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 112 */     int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.UnionIterator", "<init>", "(Lorg/apache/xalan/xsltc/DOM;)V");
/*     */ 
/*     */     
/* 115 */     int iter = cpg.addMethodref("org.apache.xalan.xsltc.dom.UnionIterator", "addIterator", "(Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/apache/xalan/xsltc/dom/UnionIterator;");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.UnionIterator")));
/* 121 */     il.append((Instruction)InstructionConstants.DUP);
/* 122 */     il.append(methodGen.loadDOM());
/* 123 */     il.append((Instruction)new INVOKESPECIAL(init));
/*     */ 
/*     */     
/* 126 */     int length = this._components.length;
/* 127 */     for (int i = 0; i < length; i++) {
/* 128 */       this._components[i].translate(classGen, methodGen);
/* 129 */       il.append((Instruction)new INVOKEVIRTUAL(iter));
/*     */     } 
/*     */ 
/*     */     
/* 133 */     if (this._reverse) {
/* 134 */       int order = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "orderNodes", "(Lorg/apache/xml/dtm/DTMAxisIterator;I)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */       
/* 137 */       il.append(methodGen.loadDOM());
/* 138 */       il.append((Instruction)InstructionConstants.SWAP);
/* 139 */       il.append(methodGen.loadContextNode());
/* 140 */       il.append((Instruction)new INVOKEINTERFACE(order, 3));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/UnionPathExpr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */