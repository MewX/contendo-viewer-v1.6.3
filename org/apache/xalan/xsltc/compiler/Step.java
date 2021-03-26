/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.ICONST;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.NEW;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xml.dtm.Axis;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Step
/*     */   extends RelativeLocationPath
/*     */ {
/*     */   private int _axis;
/*     */   private Vector _predicates;
/*     */   private boolean _hadPredicates = false;
/*     */   private int _nodeType;
/*     */   
/*     */   public Step(int axis, int nodeType, Vector predicates) {
/*  70 */     this._axis = axis;
/*  71 */     this._nodeType = nodeType;
/*  72 */     this._predicates = predicates;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParser(Parser parser) {
/*  79 */     super.setParser(parser);
/*  80 */     if (this._predicates != null) {
/*  81 */       int n = this._predicates.size();
/*  82 */       for (int i = 0; i < n; i++) {
/*  83 */         Predicate exp = this._predicates.elementAt(i);
/*  84 */         exp.setParser(parser);
/*  85 */         exp.setParent(this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAxis() {
/*  94 */     return this._axis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAxis(int axis) {
/* 101 */     this._axis = axis;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNodeType() {
/* 108 */     return this._nodeType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getPredicates() {
/* 115 */     return this._predicates;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPredicates(Vector predicates) {
/* 122 */     if (this._predicates == null) {
/* 123 */       this._predicates = predicates;
/*     */     } else {
/*     */       
/* 126 */       this._predicates.addAll(predicates);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasParentPattern() {
/* 136 */     SyntaxTreeNode parent = getParent();
/* 137 */     return (parent instanceof ParentPattern || parent instanceof ParentLocationPath || parent instanceof UnionPathExpr || parent instanceof FilterParentPath);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasPredicates() {
/* 147 */     return (this._predicates != null && this._predicates.size() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isPredicate() {
/* 154 */     SyntaxTreeNode parent = this;
/* 155 */     while (parent != null) {
/* 156 */       parent = parent.getParent();
/* 157 */       if (parent instanceof Predicate) return true; 
/*     */     } 
/* 159 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAbbreviatedDot() {
/* 166 */     return (this._nodeType == -1 && this._axis == 13);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAbbreviatedDDot() {
/* 174 */     return (this._nodeType == -1 && this._axis == 10);
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
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 186 */     this._hadPredicates = hasPredicates();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     if (isAbbreviatedDot()) {
/* 192 */       this._type = (hasParentPattern() || hasPredicates()) ? Type.NodeSet : Type.Node;
/*     */     }
/*     */     else {
/*     */       
/* 196 */       this._type = Type.NodeSet;
/*     */     } 
/*     */ 
/*     */     
/* 200 */     if (this._predicates != null) {
/* 201 */       int n = this._predicates.size();
/* 202 */       for (int i = 0; i < n; i++) {
/* 203 */         Expression pred = this._predicates.elementAt(i);
/* 204 */         pred.typeCheck(stable);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 209 */     return this._type;
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
/* 220 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 221 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 223 */     if (hasPredicates()) {
/* 224 */       translatePredicates(classGen, methodGen);
/*     */     } else {
/* 226 */       int git, star = 0;
/* 227 */       String name = null;
/* 228 */       XSLTC xsltc = getParser().getXSLTC();
/*     */       
/* 230 */       if (this._nodeType >= 14) {
/* 231 */         Vector ni = xsltc.getNamesIndex();
/*     */         
/* 233 */         name = ni.elementAt(this._nodeType - 14);
/* 234 */         star = name.lastIndexOf('*');
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 239 */       if (this._axis == 2 && this._nodeType != 2 && this._nodeType != -1 && !hasParentPattern() && star == 0) {
/*     */ 
/*     */ 
/*     */         
/* 243 */         int iter = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getTypedAxisIterator", "(II)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */         
/* 246 */         il.append(methodGen.loadDOM());
/* 247 */         il.append((CompoundInstruction)new PUSH(cpg, 2));
/* 248 */         il.append((CompoundInstruction)new PUSH(cpg, this._nodeType));
/* 249 */         il.append((Instruction)new INVOKEINTERFACE(iter, 3));
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 254 */       if (isAbbreviatedDot()) {
/* 255 */         if (this._type == Type.Node) {
/*     */           
/* 257 */           il.append(methodGen.loadContextNode());
/*     */         }
/*     */         else {
/*     */           
/* 261 */           int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.SingletonIterator", "<init>", "(I)V");
/*     */           
/* 263 */           il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.SingletonIterator")));
/* 264 */           il.append((Instruction)InstructionConstants.DUP);
/* 265 */           il.append(methodGen.loadContextNode());
/* 266 */           il.append((Instruction)new INVOKESPECIAL(init));
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 272 */       SyntaxTreeNode parent = getParent();
/* 273 */       if (parent instanceof ParentLocationPath && parent.getParent() instanceof ParentLocationPath)
/*     */       {
/* 275 */         if (this._nodeType == 1 && !this._hadPredicates) {
/* 276 */           this._nodeType = -1;
/*     */         }
/*     */       }
/*     */ 
/*     */       
/* 281 */       switch (this._nodeType) {
/*     */         case 2:
/* 283 */           this._axis = 2;
/*     */         
/*     */         case -1:
/* 286 */           git = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getAxisIterator", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */           
/* 289 */           il.append(methodGen.loadDOM());
/* 290 */           il.append((CompoundInstruction)new PUSH(cpg, this._axis));
/* 291 */           il.append((Instruction)new INVOKEINTERFACE(git, 2));
/*     */           return;
/*     */         default:
/* 294 */           if (star > 1) {
/*     */             String str;
/* 296 */             if (this._axis == 2) {
/* 297 */               str = name.substring(0, star - 2);
/*     */             } else {
/* 299 */               str = name.substring(0, star - 1);
/*     */             } 
/* 301 */             int nsType = xsltc.registerNamespace(str);
/* 302 */             int ns = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNamespaceAxisIterator", "(II)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */             
/* 305 */             il.append(methodGen.loadDOM());
/* 306 */             il.append((CompoundInstruction)new PUSH(cpg, this._axis));
/* 307 */             il.append((CompoundInstruction)new PUSH(cpg, nsType));
/* 308 */             il.append((Instruction)new INVOKEINTERFACE(ns, 3)); return;
/*     */           }  break;
/*     */         case 1:
/*     */           break;
/*     */       } 
/* 313 */       int ty = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getTypedAxisIterator", "(II)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */ 
/*     */       
/* 317 */       il.append(methodGen.loadDOM());
/* 318 */       il.append((CompoundInstruction)new PUSH(cpg, this._axis));
/* 319 */       il.append((CompoundInstruction)new PUSH(cpg, this._nodeType));
/* 320 */       il.append((Instruction)new INVOKEINTERFACE(ty, 3));
/*     */     } 
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
/*     */ 
/*     */   
/*     */   public void translatePredicates(ClassGenerator classGen, MethodGenerator methodGen) {
/* 336 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 337 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 339 */     int idx = 0;
/*     */     
/* 341 */     if (this._predicates.size() == 0) {
/* 342 */       translate(classGen, methodGen);
/*     */     } else {
/*     */       
/* 345 */       Predicate predicate = this._predicates.lastElement();
/* 346 */       this._predicates.remove(predicate);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 355 */       if (predicate.isNodeValueTest()) {
/* 356 */         Step step = predicate.getStep();
/*     */         
/* 358 */         il.append(methodGen.loadDOM());
/*     */ 
/*     */         
/* 361 */         if (step.isAbbreviatedDot()) {
/* 362 */           translate(classGen, methodGen);
/* 363 */           il.append((Instruction)new ICONST(0));
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 368 */           ParentLocationPath path = new ParentLocationPath(this, step);
/*     */           
/* 370 */           try { path.typeCheck(getParser().getSymbolTable()); } catch (TypeCheckError typeCheckError) {}
/*     */ 
/*     */           
/* 373 */           path.translate(classGen, methodGen);
/* 374 */           il.append((Instruction)new ICONST(1));
/*     */         } 
/* 376 */         predicate.translate(classGen, methodGen);
/* 377 */         idx = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeValueIterator", "(Lorg/apache/xml/dtm/DTMAxisIterator;ILjava/lang/String;Z)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */         
/* 380 */         il.append((Instruction)new INVOKEINTERFACE(idx, 5));
/*     */       
/*     */       }
/* 383 */       else if (predicate.isNthDescendant()) {
/* 384 */         il.append(methodGen.loadDOM());
/*     */         
/* 386 */         il.append((Instruction)new ICONST(predicate.getPosType()));
/* 387 */         predicate.translate(classGen, methodGen);
/* 388 */         il.append((Instruction)new ICONST(0));
/* 389 */         idx = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNthDescendant", "(IIZ)Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */ 
/*     */         
/* 392 */         il.append((Instruction)new INVOKEINTERFACE(idx, 4));
/*     */       
/*     */       }
/* 395 */       else if (predicate.isNthPositionFilter()) {
/* 396 */         idx = cpg.addMethodref("org.apache.xalan.xsltc.dom.NthIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;I)V");
/*     */ 
/*     */         
/* 399 */         il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.NthIterator")));
/* 400 */         il.append((Instruction)InstructionConstants.DUP);
/* 401 */         translatePredicates(classGen, methodGen);
/* 402 */         predicate.translate(classGen, methodGen);
/* 403 */         il.append((Instruction)new INVOKESPECIAL(idx));
/*     */       } else {
/*     */         
/* 406 */         idx = cpg.addMethodref("org.apache.xalan.xsltc.dom.CurrentNodeListIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xalan/xsltc/dom/CurrentNodeListFilter;ILorg/apache/xalan/xsltc/runtime/AbstractTranslet;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 415 */         il.append((Instruction)new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.CurrentNodeListIterator")));
/* 416 */         il.append((Instruction)InstructionConstants.DUP);
/* 417 */         translatePredicates(classGen, methodGen);
/* 418 */         predicate.translateFilter(classGen, methodGen);
/*     */         
/* 420 */         il.append(methodGen.loadCurrentNode());
/* 421 */         il.append(classGen.loadTranslet());
/* 422 */         if (classGen.isExternal()) {
/* 423 */           String className = classGen.getClassName();
/* 424 */           il.append((Instruction)new CHECKCAST(cpg.addClass(className)));
/*     */         } 
/* 426 */         il.append((Instruction)new INVOKESPECIAL(idx));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 435 */     StringBuffer buffer = new StringBuffer("step(\"");
/* 436 */     buffer.append(Axis.names[this._axis]).append("\", ").append(this._nodeType);
/* 437 */     if (this._predicates != null) {
/* 438 */       int n = this._predicates.size();
/* 439 */       for (int i = 0; i < n; i++) {
/* 440 */         Predicate pred = this._predicates.elementAt(i);
/* 441 */         buffer.append(", ").append(pred.toString());
/*     */       } 
/*     */     } 
/* 444 */     return buffer.append(')').toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Step.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */