/*      */ package org.apache.bcel.verifier.structurals;
/*      */ import org.apache.bcel.classfile.Constant;
/*      */ import org.apache.bcel.generic.AALOAD;
/*      */ import org.apache.bcel.generic.AASTORE;
/*      */ import org.apache.bcel.generic.ALOAD;
/*      */ import org.apache.bcel.generic.ANEWARRAY;
/*      */ import org.apache.bcel.generic.ARRAYLENGTH;
/*      */ import org.apache.bcel.generic.ASTORE;
/*      */ import org.apache.bcel.generic.ArrayType;
/*      */ import org.apache.bcel.generic.BALOAD;
/*      */ import org.apache.bcel.generic.BASTORE;
/*      */ import org.apache.bcel.generic.BasicType;
/*      */ import org.apache.bcel.generic.CASTORE;
/*      */ import org.apache.bcel.generic.CHECKCAST;
/*      */ import org.apache.bcel.generic.ConstantPoolGen;
/*      */ import org.apache.bcel.generic.DADD;
/*      */ import org.apache.bcel.generic.DDIV;
/*      */ import org.apache.bcel.generic.DMUL;
/*      */ import org.apache.bcel.generic.DNEG;
/*      */ import org.apache.bcel.generic.DREM;
/*      */ import org.apache.bcel.generic.DRETURN;
/*      */ import org.apache.bcel.generic.DSTORE;
/*      */ import org.apache.bcel.generic.DUP;
/*      */ import org.apache.bcel.generic.DUP2;
/*      */ import org.apache.bcel.generic.DUP_X1;
/*      */ import org.apache.bcel.generic.DUP_X2;
/*      */ import org.apache.bcel.generic.EmptyVisitor;
/*      */ import org.apache.bcel.generic.F2D;
/*      */ import org.apache.bcel.generic.F2I;
/*      */ import org.apache.bcel.generic.F2L;
/*      */ import org.apache.bcel.generic.FADD;
/*      */ import org.apache.bcel.generic.FALOAD;
/*      */ import org.apache.bcel.generic.FCONST;
/*      */ import org.apache.bcel.generic.FMUL;
/*      */ import org.apache.bcel.generic.FRETURN;
/*      */ import org.apache.bcel.generic.FSTORE;
/*      */ import org.apache.bcel.generic.FSUB;
/*      */ import org.apache.bcel.generic.GETFIELD;
/*      */ import org.apache.bcel.generic.GETSTATIC;
/*      */ import org.apache.bcel.generic.GOTO_W;
/*      */ import org.apache.bcel.generic.IADD;
/*      */ import org.apache.bcel.generic.IALOAD;
/*      */ import org.apache.bcel.generic.IAND;
/*      */ import org.apache.bcel.generic.ICONST;
/*      */ import org.apache.bcel.generic.IDIV;
/*      */ import org.apache.bcel.generic.IFEQ;
/*      */ import org.apache.bcel.generic.IFGE;
/*      */ import org.apache.bcel.generic.IFGT;
/*      */ import org.apache.bcel.generic.IFNE;
/*      */ import org.apache.bcel.generic.IFNONNULL;
/*      */ import org.apache.bcel.generic.IF_ACMPEQ;
/*      */ import org.apache.bcel.generic.IF_ACMPNE;
/*      */ import org.apache.bcel.generic.IF_ICMPGE;
/*      */ import org.apache.bcel.generic.IF_ICMPLE;
/*      */ import org.apache.bcel.generic.IF_ICMPLT;
/*      */ import org.apache.bcel.generic.IINC;
/*      */ import org.apache.bcel.generic.IMUL;
/*      */ import org.apache.bcel.generic.INEG;
/*      */ import org.apache.bcel.generic.INSTANCEOF;
/*      */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*      */ import org.apache.bcel.generic.INVOKESPECIAL;
/*      */ import org.apache.bcel.generic.INVOKESTATIC;
/*      */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*      */ import org.apache.bcel.generic.IOR;
/*      */ import org.apache.bcel.generic.IREM;
/*      */ import org.apache.bcel.generic.ISHL;
/*      */ import org.apache.bcel.generic.ISHR;
/*      */ import org.apache.bcel.generic.ISTORE;
/*      */ import org.apache.bcel.generic.IUSHR;
/*      */ import org.apache.bcel.generic.JSR;
/*      */ import org.apache.bcel.generic.JSR_W;
/*      */ import org.apache.bcel.generic.LADD;
/*      */ import org.apache.bcel.generic.LAND;
/*      */ import org.apache.bcel.generic.LASTORE;
/*      */ import org.apache.bcel.generic.LCONST;
/*      */ import org.apache.bcel.generic.LDC;
/*      */ import org.apache.bcel.generic.LDC2_W;
/*      */ import org.apache.bcel.generic.LDC_W;
/*      */ import org.apache.bcel.generic.LDIV;
/*      */ import org.apache.bcel.generic.LLOAD;
/*      */ import org.apache.bcel.generic.LMUL;
/*      */ import org.apache.bcel.generic.LNEG;
/*      */ import org.apache.bcel.generic.LOOKUPSWITCH;
/*      */ import org.apache.bcel.generic.LOR;
/*      */ import org.apache.bcel.generic.LREM;
/*      */ import org.apache.bcel.generic.LRETURN;
/*      */ import org.apache.bcel.generic.LSHL;
/*      */ import org.apache.bcel.generic.LSHR;
/*      */ import org.apache.bcel.generic.LSTORE;
/*      */ import org.apache.bcel.generic.MONITORENTER;
/*      */ import org.apache.bcel.generic.MULTIANEWARRAY;
/*      */ import org.apache.bcel.generic.NEW;
/*      */ import org.apache.bcel.generic.NEWARRAY;
/*      */ import org.apache.bcel.generic.NOP;
/*      */ import org.apache.bcel.generic.POP;
/*      */ import org.apache.bcel.generic.POP2;
/*      */ import org.apache.bcel.generic.RETURN;
/*      */ import org.apache.bcel.generic.ReturnaddressType;
/*      */ import org.apache.bcel.generic.SASTORE;
/*      */ import org.apache.bcel.generic.SIPUSH;
/*      */ import org.apache.bcel.generic.SWAP;
/*      */ import org.apache.bcel.generic.Type;
/*      */ 
/*      */ public class ExecutionVisitor extends EmptyVisitor implements Visitor {
/*  105 */   private Frame frame = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  111 */   private ConstantPoolGen cpg = null;
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
/*      */   private OperandStack stack() {
/*  123 */     return this.frame.getStack();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LocalVariables locals() {
/*  131 */     return this.frame.getLocals();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConstantPoolGen(ConstantPoolGen cpg) {
/*  138 */     this.cpg = cpg;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFrame(Frame f) {
/*  148 */     this.frame = f;
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
/*      */ 
/*      */   
/*      */   public void visitAALOAD(AALOAD o) {
/*  163 */     stack().pop();
/*      */     
/*  165 */     Type t = stack().pop();
/*  166 */     if (t == Type.NULL) {
/*  167 */       stack().push((Type)Type.NULL);
/*      */     } else {
/*      */       
/*  170 */       ArrayType at = (ArrayType)t;
/*  171 */       stack().push(at.getElementType());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitAASTORE(AASTORE o) {
/*  176 */     stack().pop();
/*  177 */     stack().pop();
/*  178 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitACONST_NULL(ACONST_NULL o) {
/*  182 */     stack().push((Type)Type.NULL);
/*      */   }
/*      */   
/*      */   public void visitALOAD(ALOAD o) {
/*  186 */     stack().push(locals().get(o.getIndex()));
/*      */   }
/*      */   
/*      */   public void visitANEWARRAY(ANEWARRAY o) {
/*  190 */     stack().pop();
/*  191 */     stack().push((Type)new ArrayType(o.getType(this.cpg), 1));
/*      */   }
/*      */   
/*      */   public void visitARETURN(ARETURN o) {
/*  195 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitARRAYLENGTH(ARRAYLENGTH o) {
/*  199 */     stack().pop();
/*  200 */     stack().push((Type)Type.INT);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitASTORE(ASTORE o) {
/*  205 */     locals().set(o.getIndex(), stack().pop());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitATHROW(ATHROW o) {
/*  211 */     Type t = stack().pop();
/*  212 */     stack().clear();
/*  213 */     if (t.equals(Type.NULL)) {
/*  214 */       stack().push(Type.getType("Ljava/lang/NullPointerException;"));
/*      */     } else {
/*  216 */       stack().push(t);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitBALOAD(BALOAD o) {
/*  221 */     stack().pop();
/*  222 */     stack().pop();
/*  223 */     stack().push((Type)Type.INT);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitBASTORE(BASTORE o) {
/*  228 */     stack().pop();
/*  229 */     stack().pop();
/*  230 */     stack().pop();
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitBIPUSH(BIPUSH o) {
/*  235 */     stack().push((Type)Type.INT);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitCALOAD(CALOAD o) {
/*  240 */     stack().pop();
/*  241 */     stack().pop();
/*  242 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitCASTORE(CASTORE o) {
/*  246 */     stack().pop();
/*  247 */     stack().pop();
/*  248 */     stack().pop();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitCHECKCAST(CHECKCAST o) {
/*  257 */     stack().pop();
/*  258 */     stack().push(o.getType(this.cpg));
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitD2F(D2F o) {
/*  263 */     stack().pop();
/*  264 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   
/*      */   public void visitD2I(D2I o) {
/*  268 */     stack().pop();
/*  269 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitD2L(D2L o) {
/*  273 */     stack().pop();
/*  274 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitDADD(DADD o) {
/*  278 */     stack().pop();
/*  279 */     stack().pop();
/*  280 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitDALOAD(DALOAD o) {
/*  284 */     stack().pop();
/*  285 */     stack().pop();
/*  286 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitDASTORE(DASTORE o) {
/*  290 */     stack().pop();
/*  291 */     stack().pop();
/*  292 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitDCMPG(DCMPG o) {
/*  296 */     stack().pop();
/*  297 */     stack().pop();
/*  298 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitDCMPL(DCMPL o) {
/*  302 */     stack().pop();
/*  303 */     stack().pop();
/*  304 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitDCONST(DCONST o) {
/*  308 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitDDIV(DDIV o) {
/*  312 */     stack().pop();
/*  313 */     stack().pop();
/*  314 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitDLOAD(DLOAD o) {
/*  318 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitDMUL(DMUL o) {
/*  322 */     stack().pop();
/*  323 */     stack().pop();
/*  324 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitDNEG(DNEG o) {
/*  328 */     stack().pop();
/*  329 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitDREM(DREM o) {
/*  333 */     stack().pop();
/*  334 */     stack().pop();
/*  335 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitDRETURN(DRETURN o) {
/*  339 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitDSTORE(DSTORE o) {
/*  343 */     locals().set(o.getIndex(), stack().pop());
/*  344 */     locals().set(o.getIndex() + 1, Type.UNKNOWN);
/*      */   }
/*      */   
/*      */   public void visitDSUB(DSUB o) {
/*  348 */     stack().pop();
/*  349 */     stack().pop();
/*  350 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitDUP(DUP o) {
/*  354 */     Type t = stack().pop();
/*  355 */     stack().push(t);
/*  356 */     stack().push(t);
/*      */   }
/*      */   
/*      */   public void visitDUP_X1(DUP_X1 o) {
/*  360 */     Type w1 = stack().pop();
/*  361 */     Type w2 = stack().pop();
/*  362 */     stack().push(w1);
/*  363 */     stack().push(w2);
/*  364 */     stack().push(w1);
/*      */   }
/*      */   
/*      */   public void visitDUP_X2(DUP_X2 o) {
/*  368 */     Type w1 = stack().pop();
/*  369 */     Type w2 = stack().pop();
/*  370 */     if (w2.getSize() == 2) {
/*  371 */       stack().push(w1);
/*  372 */       stack().push(w2);
/*  373 */       stack().push(w1);
/*      */     } else {
/*      */       
/*  376 */       Type w3 = stack().pop();
/*  377 */       stack().push(w1);
/*  378 */       stack().push(w3);
/*  379 */       stack().push(w2);
/*  380 */       stack().push(w1);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitDUP2(DUP2 o) {
/*  385 */     Type t = stack().pop();
/*  386 */     if (t.getSize() == 2) {
/*  387 */       stack().push(t);
/*  388 */       stack().push(t);
/*      */     } else {
/*      */       
/*  391 */       Type u = stack().pop();
/*  392 */       stack().push(u);
/*  393 */       stack().push(t);
/*  394 */       stack().push(u);
/*  395 */       stack().push(t);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitDUP2_X1(DUP2_X1 o) {
/*  400 */     Type t = stack().pop();
/*  401 */     if (t.getSize() == 2) {
/*  402 */       Type u = stack().pop();
/*  403 */       stack().push(t);
/*  404 */       stack().push(u);
/*  405 */       stack().push(t);
/*      */     } else {
/*      */       
/*  408 */       Type u = stack().pop();
/*  409 */       Type v = stack().pop();
/*  410 */       stack().push(u);
/*  411 */       stack().push(t);
/*  412 */       stack().push(v);
/*  413 */       stack().push(u);
/*  414 */       stack().push(t);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitDUP2_X2(DUP2_X2 o) {
/*  419 */     Type t = stack().pop();
/*  420 */     if (t.getSize() == 2) {
/*  421 */       Type u = stack().pop();
/*  422 */       if (u.getSize() == 2) {
/*  423 */         stack().push(t);
/*  424 */         stack().push(u);
/*  425 */         stack().push(t);
/*      */       } else {
/*  427 */         Type v = stack().pop();
/*  428 */         stack().push(t);
/*  429 */         stack().push(v);
/*  430 */         stack().push(u);
/*  431 */         stack().push(t);
/*      */       } 
/*      */     } else {
/*      */       
/*  435 */       Type u = stack().pop();
/*  436 */       Type v = stack().pop();
/*  437 */       if (v.getSize() == 2) {
/*  438 */         stack().push(u);
/*  439 */         stack().push(t);
/*  440 */         stack().push(v);
/*  441 */         stack().push(u);
/*  442 */         stack().push(t);
/*      */       } else {
/*  444 */         Type w = stack().pop();
/*  445 */         stack().push(u);
/*  446 */         stack().push(t);
/*  447 */         stack().push(w);
/*  448 */         stack().push(v);
/*  449 */         stack().push(u);
/*  450 */         stack().push(t);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitF2D(F2D o) {
/*  456 */     stack().pop();
/*  457 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitF2I(F2I o) {
/*  461 */     stack().pop();
/*  462 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitF2L(F2L o) {
/*  466 */     stack().pop();
/*  467 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitFADD(FADD o) {
/*  471 */     stack().pop();
/*  472 */     stack().pop();
/*  473 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   
/*      */   public void visitFALOAD(FALOAD o) {
/*  477 */     stack().pop();
/*  478 */     stack().pop();
/*  479 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   
/*      */   public void visitFASTORE(FASTORE o) {
/*  483 */     stack().pop();
/*  484 */     stack().pop();
/*  485 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitFCMPG(FCMPG o) {
/*  489 */     stack().pop();
/*  490 */     stack().pop();
/*  491 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitFCMPL(FCMPL o) {
/*  495 */     stack().pop();
/*  496 */     stack().pop();
/*  497 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitFCONST(FCONST o) {
/*  501 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   
/*      */   public void visitFDIV(FDIV o) {
/*  505 */     stack().pop();
/*  506 */     stack().pop();
/*  507 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   
/*      */   public void visitFLOAD(FLOAD o) {
/*  511 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   
/*      */   public void visitFMUL(FMUL o) {
/*  515 */     stack().pop();
/*  516 */     stack().pop();
/*  517 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   
/*      */   public void visitFNEG(FNEG o) {
/*  521 */     stack().pop();
/*  522 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   
/*      */   public void visitFREM(FREM o) {
/*  526 */     stack().pop();
/*  527 */     stack().pop();
/*  528 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   
/*      */   public void visitFRETURN(FRETURN o) {
/*  532 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitFSTORE(FSTORE o) {
/*  536 */     locals().set(o.getIndex(), stack().pop());
/*      */   }
/*      */   
/*      */   public void visitFSUB(FSUB o) {
/*  540 */     stack().pop();
/*  541 */     stack().pop();
/*  542 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   public void visitGETFIELD(GETFIELD o) {
/*      */     BasicType basicType;
/*  546 */     stack().pop();
/*  547 */     Type t = o.getFieldType(this.cpg);
/*  548 */     if (t.equals(Type.BOOLEAN) || t.equals(Type.CHAR) || t.equals(Type.BYTE) || t.equals(Type.SHORT))
/*      */     {
/*      */ 
/*      */       
/*  552 */       basicType = Type.INT; } 
/*  553 */     stack().push((Type)basicType);
/*      */   }
/*      */   public void visitGETSTATIC(GETSTATIC o) {
/*      */     BasicType basicType;
/*  557 */     Type t = o.getFieldType(this.cpg);
/*  558 */     if (t.equals(Type.BOOLEAN) || t.equals(Type.CHAR) || t.equals(Type.BYTE) || t.equals(Type.SHORT))
/*      */     {
/*      */ 
/*      */       
/*  562 */       basicType = Type.INT; } 
/*  563 */     stack().push((Type)basicType);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitGOTO(GOTO o) {}
/*      */ 
/*      */   
/*      */   public void visitGOTO_W(GOTO_W o) {}
/*      */ 
/*      */   
/*      */   public void visitI2B(I2B o) {
/*  575 */     stack().pop();
/*  576 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitI2C(I2C o) {
/*  580 */     stack().pop();
/*  581 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitI2D(I2D o) {
/*  585 */     stack().pop();
/*  586 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitI2F(I2F o) {
/*  590 */     stack().pop();
/*  591 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   
/*      */   public void visitI2L(I2L o) {
/*  595 */     stack().pop();
/*  596 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitI2S(I2S o) {
/*  600 */     stack().pop();
/*  601 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitIADD(IADD o) {
/*  605 */     stack().pop();
/*  606 */     stack().pop();
/*  607 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitIALOAD(IALOAD o) {
/*  611 */     stack().pop();
/*  612 */     stack().pop();
/*  613 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitIAND(IAND o) {
/*  617 */     stack().pop();
/*  618 */     stack().pop();
/*  619 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitIASTORE(IASTORE o) {
/*  623 */     stack().pop();
/*  624 */     stack().pop();
/*  625 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitICONST(ICONST o) {
/*  629 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitIDIV(IDIV o) {
/*  633 */     stack().pop();
/*  634 */     stack().pop();
/*  635 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitIF_ACMPEQ(IF_ACMPEQ o) {
/*  639 */     stack().pop();
/*  640 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIF_ACMPNE(IF_ACMPNE o) {
/*  644 */     stack().pop();
/*  645 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIF_ICMPEQ(IF_ICMPEQ o) {
/*  649 */     stack().pop();
/*  650 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIF_ICMPGE(IF_ICMPGE o) {
/*  654 */     stack().pop();
/*  655 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIF_ICMPGT(IF_ICMPGT o) {
/*  659 */     stack().pop();
/*  660 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIF_ICMPLE(IF_ICMPLE o) {
/*  664 */     stack().pop();
/*  665 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIF_ICMPLT(IF_ICMPLT o) {
/*  669 */     stack().pop();
/*  670 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIF_ICMPNE(IF_ICMPNE o) {
/*  674 */     stack().pop();
/*  675 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIFEQ(IFEQ o) {
/*  679 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIFGE(IFGE o) {
/*  683 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIFGT(IFGT o) {
/*  687 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIFLE(IFLE o) {
/*  691 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIFLT(IFLT o) {
/*  695 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIFNE(IFNE o) {
/*  699 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIFNONNULL(IFNONNULL o) {
/*  703 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitIFNULL(IFNULL o) {
/*  707 */     stack().pop();
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitIINC(IINC o) {}
/*      */ 
/*      */   
/*      */   public void visitILOAD(ILOAD o) {
/*  715 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitIMUL(IMUL o) {
/*  719 */     stack().pop();
/*  720 */     stack().pop();
/*  721 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitINEG(INEG o) {
/*  725 */     stack().pop();
/*  726 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitINSTANCEOF(INSTANCEOF o) {
/*  730 */     stack().pop();
/*  731 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitINVOKEINTERFACE(INVOKEINTERFACE o) {
/*  735 */     stack().pop();
/*  736 */     for (int i = 0; i < (o.getArgumentTypes(this.cpg)).length; i++) {
/*  737 */       stack().pop();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  743 */     if (o.getReturnType(this.cpg) != Type.VOID) {
/*  744 */       BasicType basicType; Type t = o.getReturnType(this.cpg);
/*  745 */       if (t.equals(Type.BOOLEAN) || t.equals(Type.CHAR) || t.equals(Type.BYTE) || t.equals(Type.SHORT))
/*      */       {
/*      */ 
/*      */         
/*  749 */         basicType = Type.INT; } 
/*  750 */       stack().push((Type)basicType);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitINVOKESPECIAL(INVOKESPECIAL o) {
/*  755 */     if (o.getMethodName(this.cpg).equals("<init>")) {
/*  756 */       UninitializedObjectType t = (UninitializedObjectType)stack().peek((o.getArgumentTypes(this.cpg)).length);
/*  757 */       this; if (t == Frame._this) {
/*  758 */         this; Frame._this = null;
/*      */       } 
/*  760 */       stack().initializeObject(t);
/*  761 */       locals().initializeObject(t);
/*      */     } 
/*  763 */     stack().pop();
/*  764 */     for (int i = 0; i < (o.getArgumentTypes(this.cpg)).length; i++) {
/*  765 */       stack().pop();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  771 */     if (o.getReturnType(this.cpg) != Type.VOID) {
/*  772 */       BasicType basicType; Type t = o.getReturnType(this.cpg);
/*  773 */       if (t.equals(Type.BOOLEAN) || t.equals(Type.CHAR) || t.equals(Type.BYTE) || t.equals(Type.SHORT))
/*      */       {
/*      */ 
/*      */         
/*  777 */         basicType = Type.INT; } 
/*  778 */       stack().push((Type)basicType);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitINVOKESTATIC(INVOKESTATIC o) {
/*  783 */     for (int i = 0; i < (o.getArgumentTypes(this.cpg)).length; i++) {
/*  784 */       stack().pop();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  790 */     if (o.getReturnType(this.cpg) != Type.VOID) {
/*  791 */       BasicType basicType; Type t = o.getReturnType(this.cpg);
/*  792 */       if (t.equals(Type.BOOLEAN) || t.equals(Type.CHAR) || t.equals(Type.BYTE) || t.equals(Type.SHORT))
/*      */       {
/*      */ 
/*      */         
/*  796 */         basicType = Type.INT; } 
/*  797 */       stack().push((Type)basicType);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitINVOKEVIRTUAL(INVOKEVIRTUAL o) {
/*  802 */     stack().pop();
/*  803 */     for (int i = 0; i < (o.getArgumentTypes(this.cpg)).length; i++) {
/*  804 */       stack().pop();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  810 */     if (o.getReturnType(this.cpg) != Type.VOID) {
/*  811 */       BasicType basicType; Type t = o.getReturnType(this.cpg);
/*  812 */       if (t.equals(Type.BOOLEAN) || t.equals(Type.CHAR) || t.equals(Type.BYTE) || t.equals(Type.SHORT))
/*      */       {
/*      */ 
/*      */         
/*  816 */         basicType = Type.INT; } 
/*  817 */       stack().push((Type)basicType);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitIOR(IOR o) {
/*  822 */     stack().pop();
/*  823 */     stack().pop();
/*  824 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitIREM(IREM o) {
/*  828 */     stack().pop();
/*  829 */     stack().pop();
/*  830 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitIRETURN(IRETURN o) {
/*  834 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitISHL(ISHL o) {
/*  838 */     stack().pop();
/*  839 */     stack().pop();
/*  840 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitISHR(ISHR o) {
/*  844 */     stack().pop();
/*  845 */     stack().pop();
/*  846 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitISTORE(ISTORE o) {
/*  850 */     locals().set(o.getIndex(), stack().pop());
/*      */   }
/*      */   
/*      */   public void visitISUB(ISUB o) {
/*  854 */     stack().pop();
/*  855 */     stack().pop();
/*  856 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitIUSHR(IUSHR o) {
/*  860 */     stack().pop();
/*  861 */     stack().pop();
/*  862 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitIXOR(IXOR o) {
/*  866 */     stack().pop();
/*  867 */     stack().pop();
/*  868 */     stack().push((Type)Type.INT);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitJSR(JSR o) {
/*  873 */     stack().push((Type)new ReturnaddressType(o.physicalSuccessor()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitJSR_W(JSR_W o) {
/*  879 */     stack().push((Type)new ReturnaddressType(o.physicalSuccessor()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitL2D(L2D o) {
/*  884 */     stack().pop();
/*  885 */     stack().push((Type)Type.DOUBLE);
/*      */   }
/*      */   
/*      */   public void visitL2F(L2F o) {
/*  889 */     stack().pop();
/*  890 */     stack().push((Type)Type.FLOAT);
/*      */   }
/*      */   
/*      */   public void visitL2I(L2I o) {
/*  894 */     stack().pop();
/*  895 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitLADD(LADD o) {
/*  899 */     stack().pop();
/*  900 */     stack().pop();
/*  901 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLALOAD(LALOAD o) {
/*  905 */     stack().pop();
/*  906 */     stack().pop();
/*  907 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLAND(LAND o) {
/*  911 */     stack().pop();
/*  912 */     stack().pop();
/*  913 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLASTORE(LASTORE o) {
/*  917 */     stack().pop();
/*  918 */     stack().pop();
/*  919 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitLCMP(LCMP o) {
/*  923 */     stack().pop();
/*  924 */     stack().pop();
/*  925 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitLCONST(LCONST o) {
/*  929 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLDC(LDC o) {
/*  933 */     Constant c = this.cpg.getConstant(o.getIndex());
/*  934 */     if (c instanceof org.apache.bcel.classfile.ConstantInteger) {
/*  935 */       stack().push((Type)Type.INT);
/*      */     }
/*  937 */     if (c instanceof org.apache.bcel.classfile.ConstantFloat) {
/*  938 */       stack().push((Type)Type.FLOAT);
/*      */     }
/*  940 */     if (c instanceof org.apache.bcel.classfile.ConstantString) {
/*  941 */       stack().push((Type)Type.STRING);
/*      */     }
/*      */   }
/*      */   
/*      */   public void visitLDC_W(LDC_W o) {
/*  946 */     Constant c = this.cpg.getConstant(o.getIndex());
/*  947 */     if (c instanceof org.apache.bcel.classfile.ConstantInteger) {
/*  948 */       stack().push((Type)Type.INT);
/*      */     }
/*  950 */     if (c instanceof org.apache.bcel.classfile.ConstantFloat) {
/*  951 */       stack().push((Type)Type.FLOAT);
/*      */     }
/*  953 */     if (c instanceof org.apache.bcel.classfile.ConstantString) {
/*  954 */       stack().push((Type)Type.STRING);
/*      */     }
/*      */   }
/*      */   
/*      */   public void visitLDC2_W(LDC2_W o) {
/*  959 */     Constant c = this.cpg.getConstant(o.getIndex());
/*  960 */     if (c instanceof org.apache.bcel.classfile.ConstantLong) {
/*  961 */       stack().push((Type)Type.LONG);
/*      */     }
/*  963 */     if (c instanceof org.apache.bcel.classfile.ConstantDouble) {
/*  964 */       stack().push((Type)Type.DOUBLE);
/*      */     }
/*      */   }
/*      */   
/*      */   public void visitLDIV(LDIV o) {
/*  969 */     stack().pop();
/*  970 */     stack().pop();
/*  971 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLLOAD(LLOAD o) {
/*  975 */     stack().push(locals().get(o.getIndex()));
/*      */   }
/*      */   
/*      */   public void visitLMUL(LMUL o) {
/*  979 */     stack().pop();
/*  980 */     stack().pop();
/*  981 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLNEG(LNEG o) {
/*  985 */     stack().pop();
/*  986 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLOOKUPSWITCH(LOOKUPSWITCH o) {
/*  990 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitLOR(LOR o) {
/*  994 */     stack().pop();
/*  995 */     stack().pop();
/*  996 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLREM(LREM o) {
/* 1000 */     stack().pop();
/* 1001 */     stack().pop();
/* 1002 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLRETURN(LRETURN o) {
/* 1006 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitLSHL(LSHL o) {
/* 1010 */     stack().pop();
/* 1011 */     stack().pop();
/* 1012 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLSHR(LSHR o) {
/* 1016 */     stack().pop();
/* 1017 */     stack().pop();
/* 1018 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLSTORE(LSTORE o) {
/* 1022 */     locals().set(o.getIndex(), stack().pop());
/* 1023 */     locals().set(o.getIndex() + 1, Type.UNKNOWN);
/*      */   }
/*      */   
/*      */   public void visitLSUB(LSUB o) {
/* 1027 */     stack().pop();
/* 1028 */     stack().pop();
/* 1029 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLUSHR(LUSHR o) {
/* 1033 */     stack().pop();
/* 1034 */     stack().pop();
/* 1035 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitLXOR(LXOR o) {
/* 1039 */     stack().pop();
/* 1040 */     stack().pop();
/* 1041 */     stack().push((Type)Type.LONG);
/*      */   }
/*      */   
/*      */   public void visitMONITORENTER(MONITORENTER o) {
/* 1045 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitMONITOREXIT(MONITOREXIT o) {
/* 1049 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitMULTIANEWARRAY(MULTIANEWARRAY o) {
/* 1053 */     for (int i = 0; i < o.getDimensions(); i++) {
/* 1054 */       stack().pop();
/*      */     }
/* 1056 */     stack().push(o.getType(this.cpg));
/*      */   }
/*      */   
/*      */   public void visitNEW(NEW o) {
/* 1060 */     stack().push((Type)new UninitializedObjectType((ObjectType)o.getType(this.cpg)));
/*      */   }
/*      */   
/*      */   public void visitNEWARRAY(NEWARRAY o) {
/* 1064 */     stack().pop();
/* 1065 */     stack().push(o.getType());
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitNOP(NOP o) {}
/*      */   
/*      */   public void visitPOP(POP o) {
/* 1072 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitPOP2(POP2 o) {
/* 1076 */     Type t = stack().pop();
/* 1077 */     if (t.getSize() == 1) {
/* 1078 */       stack().pop();
/*      */     }
/*      */   }
/*      */   
/*      */   public void visitPUTFIELD(PUTFIELD o) {
/* 1083 */     stack().pop();
/* 1084 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitPUTSTATIC(PUTSTATIC o) {
/* 1088 */     stack().pop();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitRET(RET o) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitRETURN(RETURN o) {}
/*      */ 
/*      */   
/*      */   public void visitSALOAD(SALOAD o) {
/* 1101 */     stack().pop();
/* 1102 */     stack().pop();
/* 1103 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitSASTORE(SASTORE o) {
/* 1107 */     stack().pop();
/* 1108 */     stack().pop();
/* 1109 */     stack().pop();
/*      */   }
/*      */   
/*      */   public void visitSIPUSH(SIPUSH o) {
/* 1113 */     stack().push((Type)Type.INT);
/*      */   }
/*      */   
/*      */   public void visitSWAP(SWAP o) {
/* 1117 */     Type t = stack().pop();
/* 1118 */     Type u = stack().pop();
/* 1119 */     stack().push(t);
/* 1120 */     stack().push(u);
/*      */   }
/*      */   
/*      */   public void visitTABLESWITCH(TABLESWITCH o) {
/* 1124 */     stack().pop();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/ExecutionVisitor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */