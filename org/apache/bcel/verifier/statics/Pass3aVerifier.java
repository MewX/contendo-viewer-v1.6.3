/*      */ package org.apache.bcel.verifier.statics;
/*      */ 
/*      */ import org.apache.bcel.Repository;
/*      */ import org.apache.bcel.classfile.Attribute;
/*      */ import org.apache.bcel.classfile.Code;
/*      */ import org.apache.bcel.classfile.CodeException;
/*      */ import org.apache.bcel.classfile.Constant;
/*      */ import org.apache.bcel.classfile.ConstantClass;
/*      */ import org.apache.bcel.classfile.ConstantInterfaceMethodref;
/*      */ import org.apache.bcel.classfile.ConstantMethodref;
/*      */ import org.apache.bcel.classfile.ConstantNameAndType;
/*      */ import org.apache.bcel.classfile.ConstantUtf8;
/*      */ import org.apache.bcel.classfile.Field;
/*      */ import org.apache.bcel.classfile.JavaClass;
/*      */ import org.apache.bcel.classfile.LineNumber;
/*      */ import org.apache.bcel.classfile.LineNumberTable;
/*      */ import org.apache.bcel.classfile.LocalVariable;
/*      */ import org.apache.bcel.classfile.LocalVariableTable;
/*      */ import org.apache.bcel.classfile.Method;
/*      */ import org.apache.bcel.generic.ALOAD;
/*      */ import org.apache.bcel.generic.ANEWARRAY;
/*      */ import org.apache.bcel.generic.ASTORE;
/*      */ import org.apache.bcel.generic.ArrayType;
/*      */ import org.apache.bcel.generic.CHECKCAST;
/*      */ import org.apache.bcel.generic.ConstantPoolGen;
/*      */ import org.apache.bcel.generic.DLOAD;
/*      */ import org.apache.bcel.generic.DSTORE;
/*      */ import org.apache.bcel.generic.EmptyVisitor;
/*      */ import org.apache.bcel.generic.FLOAD;
/*      */ import org.apache.bcel.generic.FSTORE;
/*      */ import org.apache.bcel.generic.FieldInstruction;
/*      */ import org.apache.bcel.generic.GETSTATIC;
/*      */ import org.apache.bcel.generic.IINC;
/*      */ import org.apache.bcel.generic.ILOAD;
/*      */ import org.apache.bcel.generic.INSTANCEOF;
/*      */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*      */ import org.apache.bcel.generic.INVOKESPECIAL;
/*      */ import org.apache.bcel.generic.INVOKESTATIC;
/*      */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*      */ import org.apache.bcel.generic.ISTORE;
/*      */ import org.apache.bcel.generic.Instruction;
/*      */ import org.apache.bcel.generic.InstructionHandle;
/*      */ import org.apache.bcel.generic.InstructionList;
/*      */ import org.apache.bcel.generic.InvokeInstruction;
/*      */ import org.apache.bcel.generic.JsrInstruction;
/*      */ import org.apache.bcel.generic.LDC;
/*      */ import org.apache.bcel.generic.LDC2_W;
/*      */ import org.apache.bcel.generic.LLOAD;
/*      */ import org.apache.bcel.generic.LOOKUPSWITCH;
/*      */ import org.apache.bcel.generic.LSTORE;
/*      */ import org.apache.bcel.generic.LoadClass;
/*      */ import org.apache.bcel.generic.MULTIANEWARRAY;
/*      */ import org.apache.bcel.generic.NEW;
/*      */ import org.apache.bcel.generic.NEWARRAY;
/*      */ import org.apache.bcel.generic.ObjectType;
/*      */ import org.apache.bcel.generic.PUTSTATIC;
/*      */ import org.apache.bcel.generic.RET;
/*      */ import org.apache.bcel.generic.TABLESWITCH;
/*      */ import org.apache.bcel.generic.Type;
/*      */ import org.apache.bcel.generic.Visitor;
/*      */ import org.apache.bcel.verifier.PassVerifier;
/*      */ import org.apache.bcel.verifier.VerificationResult;
/*      */ import org.apache.bcel.verifier.Verifier;
/*      */ import org.apache.bcel.verifier.VerifierFactory;
/*      */ import org.apache.bcel.verifier.exc.AssertionViolatedException;
/*      */ import org.apache.bcel.verifier.exc.ClassConstraintException;
/*      */ import org.apache.bcel.verifier.exc.InvalidMethodException;
/*      */ import org.apache.bcel.verifier.exc.StaticCodeConstraintException;
/*      */ import org.apache.bcel.verifier.exc.StaticCodeInstructionConstraintException;
/*      */ import org.apache.bcel.verifier.exc.StaticCodeInstructionOperandConstraintException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Pass3aVerifier
/*      */   extends PassVerifier
/*      */ {
/*      */   private Verifier myOwner;
/*      */   private int method_no;
/*      */   InstructionList instructionList;
/*      */   Code code;
/*      */   
/*      */   public Pass3aVerifier(Verifier owner, int method_no) {
/*   95 */     this.myOwner = owner;
/*   96 */     this.method_no = method_no;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VerificationResult do_verify() {
/*  117 */     if (this.myOwner.doPass2().equals(VerificationResult.VR_OK)) {
/*      */ 
/*      */       
/*  120 */       JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
/*  121 */       Method[] methods = jc.getMethods();
/*  122 */       if (this.method_no >= methods.length) {
/*  123 */         throw new InvalidMethodException("METHOD DOES NOT EXIST!");
/*      */       }
/*  125 */       Method method = methods[this.method_no];
/*  126 */       this.code = method.getCode();
/*      */ 
/*      */       
/*  129 */       if (method.isAbstract() || method.isNative()) {
/*  130 */         return VerificationResult.VR_OK;
/*      */       }
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
/*      */       try {
/*  143 */         this.instructionList = new InstructionList(method.getCode().getCode());
/*      */       } catch (RuntimeException re) {
/*      */         
/*  146 */         return new VerificationResult(2, "Bad bytecode in the code array of the Code attribute of method '" + method + "'.");
/*      */       } 
/*      */       
/*  149 */       this.instructionList.setPositions(true);
/*      */ 
/*      */       
/*  152 */       VerificationResult vr = VerificationResult.VR_OK;
/*      */       try {
/*  154 */         delayedPass2Checks();
/*      */       } catch (ClassConstraintException cce) {
/*      */         
/*  157 */         vr = new VerificationResult(2, cce.getMessage());
/*  158 */         return vr;
/*      */       } 
/*      */       try {
/*  161 */         pass3StaticInstructionChecks();
/*  162 */         pass3StaticInstructionOperandsChecks();
/*      */       } catch (StaticCodeConstraintException scce) {
/*      */         
/*  165 */         vr = new VerificationResult(2, scce.getMessage());
/*      */       } 
/*  167 */       return vr;
/*      */     } 
/*      */     
/*  170 */     return VerificationResult.VR_NOTYET;
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
/*      */   private void delayedPass2Checks() {
/*  185 */     int[] instructionPositions = this.instructionList.getInstructionPositions();
/*  186 */     int codeLength = (this.code.getCode()).length;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  191 */     LineNumberTable lnt = this.code.getLineNumberTable();
/*  192 */     if (lnt != null) {
/*  193 */       LineNumber[] lineNumbers = lnt.getLineNumberTable();
/*  194 */       IntList offsets = new IntList();
/*  195 */       for (int j = 0; j < lineNumbers.length; j++) {
/*  196 */         int k = 0; while (true) { if (k >= instructionPositions.length)
/*      */           {
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
/*  209 */             throw new ClassConstraintException("Code attribute '" + this.code + "' has a LineNumberTable attribute '" + this.code.getLineNumberTable() + "' referring to a code offset ('" + lineNumbers[j].getStartPC() + "') that does not exist."); }  int offset = lineNumbers[j].getStartPC(); if (instructionPositions[k] == offset) {
/*      */             if (offsets.contains(offset)) {
/*      */               addMessage("LineNumberTable attribute '" + this.code.getLineNumberTable() + "' refers to the same code offset ('" + offset + "') more than once which is violating the semantics [but is sometimes produced by IBM's 'jikes' compiler]."); break;
/*      */             }  offsets.add(offset); break;
/*      */           } 
/*      */           k++; }
/*      */       
/*      */       } 
/*      */     } 
/*  218 */     Attribute[] atts = this.code.getAttributes();
/*  219 */     for (int a = 0; a < atts.length; a++) {
/*  220 */       if (atts[a] instanceof LocalVariableTable) {
/*  221 */         LocalVariableTable lvt = (LocalVariableTable)atts[a];
/*  222 */         if (lvt != null) {
/*  223 */           LocalVariable[] localVariables = lvt.getLocalVariableTable();
/*  224 */           for (int j = 0; j < localVariables.length; j++) {
/*  225 */             int startpc = localVariables[j].getStartPC();
/*  226 */             int length = localVariables[j].getLength();
/*      */             
/*  228 */             if (!contains(instructionPositions, startpc)) {
/*  229 */               throw new ClassConstraintException("Code attribute '" + this.code + "' has a LocalVariableTable attribute '" + this.code.getLocalVariableTable() + "' referring to a code offset ('" + startpc + "') that does not exist.");
/*      */             }
/*  231 */             if (!contains(instructionPositions, startpc + length) && startpc + length != codeLength) {
/*  232 */               throw new ClassConstraintException("Code attribute '" + this.code + "' has a LocalVariableTable attribute '" + this.code.getLocalVariableTable() + "' referring to a code offset start_pc+length ('" + (startpc + length) + "') that does not exist.");
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  245 */     CodeException[] exceptionTable = this.code.getExceptionTable();
/*  246 */     for (int i = 0; i < exceptionTable.length; i++) {
/*  247 */       int startpc = exceptionTable[i].getStartPC();
/*  248 */       int endpc = exceptionTable[i].getEndPC();
/*  249 */       int handlerpc = exceptionTable[i].getHandlerPC();
/*  250 */       if (startpc >= endpc) {
/*  251 */         throw new ClassConstraintException("Code attribute '" + this.code + "' has an exception_table entry '" + exceptionTable[i] + "' that has its start_pc ('" + startpc + "') not smaller than its end_pc ('" + endpc + "').");
/*      */       }
/*  253 */       if (!contains(instructionPositions, startpc)) {
/*  254 */         throw new ClassConstraintException("Code attribute '" + this.code + "' has an exception_table entry '" + exceptionTable[i] + "' that has a non-existant bytecode offset as its start_pc ('" + startpc + "').");
/*      */       }
/*  256 */       if (!contains(instructionPositions, endpc) && endpc != codeLength) {
/*  257 */         throw new ClassConstraintException("Code attribute '" + this.code + "' has an exception_table entry '" + exceptionTable[i] + "' that has a non-existant bytecode offset as its end_pc ('" + startpc + "') [that is also not equal to code_length ('" + codeLength + "')].");
/*      */       }
/*  259 */       if (!contains(instructionPositions, handlerpc)) {
/*  260 */         throw new ClassConstraintException("Code attribute '" + this.code + "' has an exception_table entry '" + exceptionTable[i] + "' that has a non-existant bytecode offset as its handler_pc ('" + handlerpc + "').");
/*      */       }
/*      */     } 
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
/*      */ 
/*      */   
/*      */   private void pass3StaticInstructionChecks() {
/*  279 */     if ((this.code.getCode()).length >= 65536) {
/*  280 */       throw new StaticCodeInstructionConstraintException("Code array in code attribute '" + this.code + "' too big: must be smaller than 65536 bytes.");
/*      */     }
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  298 */     InstructionHandle ih = this.instructionList.getStart();
/*  299 */     while (ih != null) {
/*  300 */       Instruction i = ih.getInstruction();
/*  301 */       if (i instanceof org.apache.bcel.generic.IMPDEP1) {
/*  302 */         throw new StaticCodeInstructionConstraintException("IMPDEP1 must not be in the code, it is an illegal instruction for _internal_ JVM use!");
/*      */       }
/*  304 */       if (i instanceof org.apache.bcel.generic.IMPDEP2) {
/*  305 */         throw new StaticCodeInstructionConstraintException("IMPDEP2 must not be in the code, it is an illegal instruction for _internal_ JVM use!");
/*      */       }
/*  307 */       if (i instanceof org.apache.bcel.generic.BREAKPOINT) {
/*  308 */         throw new StaticCodeInstructionConstraintException("BREAKPOINT must not be in the code, it is an illegal instruction for _internal_ JVM use!");
/*      */       }
/*  310 */       ih = ih.getNext();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  317 */     Instruction last = this.instructionList.getEnd().getInstruction();
/*  318 */     if (!(last instanceof org.apache.bcel.generic.ReturnInstruction) && !(last instanceof RET) && !(last instanceof org.apache.bcel.generic.GotoInstruction) && !(last instanceof org.apache.bcel.generic.ATHROW))
/*      */     {
/*      */ 
/*      */       
/*  322 */       throw new StaticCodeInstructionConstraintException("Execution must not fall off the bottom of the code array. This constraint is enforced statically as some existing verifiers do - so it may be a false alarm if the last instruction is not reachable.");
/*      */     }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void pass3StaticInstructionOperandsChecks() {
/*  346 */     ConstantPoolGen cpg = new ConstantPoolGen(Repository.lookupClass(this.myOwner.getClassName()).getConstantPool());
/*  347 */     InstOperandConstraintVisitor v = new InstOperandConstraintVisitor(this, cpg);
/*      */ 
/*      */     
/*  350 */     InstructionHandle ih = this.instructionList.getStart();
/*  351 */     while (ih != null) {
/*  352 */       Instruction i = ih.getInstruction();
/*      */ 
/*      */       
/*  355 */       if (i instanceof JsrInstruction) {
/*  356 */         InstructionHandle target = ((JsrInstruction)i).getTarget();
/*  357 */         if (target == this.instructionList.getStart()) {
/*  358 */           throw new StaticCodeInstructionOperandConstraintException("Due to JustIce's clear definition of subroutines, no JSR or JSR_W may have a top-level instruction (such as the very first instruction, which is targeted by instruction '" + ih + "' as its target.");
/*      */         }
/*  360 */         if (!(target.getInstruction() instanceof ASTORE)) {
/*  361 */           throw new StaticCodeInstructionOperandConstraintException("Due to JustIce's clear definition of subroutines, no JSR or JSR_W may target anything else than an ASTORE instruction. Instruction '" + ih + "' targets '" + target + "'.");
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  366 */       ih.accept((Visitor)v);
/*      */       
/*  368 */       ih = ih.getNext();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean contains(int[] ints, int i) {
/*  375 */     for (int j = 0; j < ints.length; j++) {
/*  376 */       if (ints[j] == i) return true; 
/*      */     } 
/*  378 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMethodNo() {
/*  383 */     return this.method_no;
/*      */   }
/*      */ 
/*      */   
/*      */   private class InstOperandConstraintVisitor
/*      */     extends EmptyVisitor
/*      */   {
/*      */     private ConstantPoolGen cpg;
/*      */     
/*      */     private final Pass3aVerifier this$0;
/*      */     
/*      */     InstOperandConstraintVisitor(Pass3aVerifier this$0, ConstantPoolGen cpg) {
/*  395 */       this.this$0 = this$0;
/*  396 */       this.cpg = cpg;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int max_locals() {
/*  404 */       return Repository.lookupClass(this.this$0.myOwner.getClassName()).getMethods()[this.this$0.method_no].getCode().getMaxLocals();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void constraintViolated(Instruction i, String message) {
/*  411 */       throw new StaticCodeInstructionOperandConstraintException("Instruction " + i + " constraint violated: " + message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void indexValid(Instruction i, int idx) {
/*  419 */       if (idx < 0 || idx >= this.cpg.getSize()) {
/*  420 */         constraintViolated(i, "Illegal constant pool index '" + idx + "'.");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitLoadClass(LoadClass o) {
/*  432 */       ObjectType t = o.getLoadClassType(this.cpg);
/*  433 */       if (t != null) {
/*  434 */         Verifier v = VerifierFactory.getVerifier(t.getClassName());
/*  435 */         VerificationResult vr = v.doPass1();
/*  436 */         if (vr.getStatus() != 1) {
/*  437 */           constraintViolated((Instruction)o, "Class '" + o.getLoadClassType(this.cpg).getClassName() + "' is referenced, but cannot be loaded: '" + vr + "'.");
/*      */         }
/*      */       } 
/*      */     }
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
/*      */     public void visitLDC(LDC o) {
/*  452 */       indexValid((Instruction)o, o.getIndex());
/*  453 */       Constant c = this.cpg.getConstant(o.getIndex());
/*  454 */       if (!(c instanceof org.apache.bcel.classfile.ConstantInteger) && !(c instanceof org.apache.bcel.classfile.ConstantFloat) && !(c instanceof org.apache.bcel.classfile.ConstantString))
/*      */       {
/*      */         
/*  457 */         constraintViolated((Instruction)o, "Operand of LDC or LDC_W must be one of CONSTANT_Integer, CONSTANT_Float or CONSTANT_String, but is '" + c + "'.");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitLDC2_W(LDC2_W o) {
/*  464 */       indexValid((Instruction)o, o.getIndex());
/*  465 */       Constant c = this.cpg.getConstant(o.getIndex());
/*  466 */       if (!(c instanceof org.apache.bcel.classfile.ConstantLong) && !(c instanceof org.apache.bcel.classfile.ConstantDouble))
/*      */       {
/*  468 */         constraintViolated((Instruction)o, "Operand of LDC2_W must be CONSTANT_Long or CONSTANT_Double, but is '" + c + "'.");
/*      */       }
/*      */       try {
/*  471 */         indexValid((Instruction)o, o.getIndex() + 1);
/*      */       } catch (StaticCodeInstructionOperandConstraintException e) {
/*      */         
/*  474 */         throw new AssertionViolatedException("OOPS: Does not BCEL handle that? LDC2_W operand has a problem.");
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitFieldInstruction(FieldInstruction o) {
/*  481 */       indexValid((Instruction)o, o.getIndex());
/*  482 */       Constant c = this.cpg.getConstant(o.getIndex());
/*  483 */       if (!(c instanceof org.apache.bcel.classfile.ConstantFieldref)) {
/*  484 */         constraintViolated((Instruction)o, "Indexing a constant that's not a CONSTANT_Fieldref but a '" + c + "'.");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitInvokeInstruction(InvokeInstruction o) {
/*  490 */       indexValid((Instruction)o, o.getIndex());
/*  491 */       if (o instanceof INVOKEVIRTUAL || o instanceof INVOKESPECIAL || o instanceof INVOKESTATIC) {
/*      */ 
/*      */         
/*  494 */         Constant c = this.cpg.getConstant(o.getIndex());
/*  495 */         if (!(c instanceof ConstantMethodref)) {
/*  496 */           constraintViolated((Instruction)o, "Indexing a constant that's not a CONSTANT_Methodref but a '" + c + "'.");
/*      */         }
/*      */         else {
/*      */           
/*  500 */           ConstantNameAndType cnat = (ConstantNameAndType)this.cpg.getConstant(((ConstantMethodref)c).getNameAndTypeIndex());
/*  501 */           ConstantUtf8 cutf8 = (ConstantUtf8)this.cpg.getConstant(cnat.getNameIndex());
/*  502 */           if (cutf8.getBytes().equals("<init>") && !(o instanceof INVOKESPECIAL)) {
/*  503 */             constraintViolated((Instruction)o, "Only INVOKESPECIAL is allowed to invoke instance initialization methods.");
/*      */           }
/*  505 */           if (!cutf8.getBytes().equals("<init>") && cutf8.getBytes().startsWith("<")) {
/*  506 */             constraintViolated((Instruction)o, "No method with a name beginning with '<' other than the instance initialization methods may be called by the method invocation instructions.");
/*      */           }
/*      */         } 
/*      */       } else {
/*      */         
/*  511 */         Constant c = this.cpg.getConstant(o.getIndex());
/*  512 */         if (!(c instanceof ConstantInterfaceMethodref)) {
/*  513 */           constraintViolated((Instruction)o, "Indexing a constant that's not a CONSTANT_InterfaceMethodref but a '" + c + "'.");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  521 */         ConstantNameAndType cnat = (ConstantNameAndType)this.cpg.getConstant(((ConstantInterfaceMethodref)c).getNameAndTypeIndex());
/*  522 */         String name = ((ConstantUtf8)this.cpg.getConstant(cnat.getNameIndex())).getBytes();
/*  523 */         if (name.equals("<init>")) {
/*  524 */           constraintViolated((Instruction)o, "Method to invoke must not be '<init>'.");
/*      */         }
/*  526 */         if (name.equals("<clinit>")) {
/*  527 */           constraintViolated((Instruction)o, "Method to invoke must not be '<clinit>'.");
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  533 */       Type t = o.getReturnType(this.cpg);
/*  534 */       if (t instanceof ArrayType) {
/*  535 */         t = ((ArrayType)t).getBasicType();
/*      */       }
/*  537 */       if (t instanceof ObjectType) {
/*  538 */         Verifier v = VerifierFactory.getVerifier(((ObjectType)t).getClassName());
/*  539 */         VerificationResult vr = v.doPass2();
/*  540 */         if (vr.getStatus() != 1) {
/*  541 */           constraintViolated((Instruction)o, "Return type class/interface could not be verified successfully: '" + vr.getMessage() + "'.");
/*      */         }
/*      */       } 
/*      */       
/*  545 */       Type[] ts = o.getArgumentTypes(this.cpg);
/*  546 */       for (int i = 0; i < ts.length; i++) {
/*  547 */         t = ts[i];
/*  548 */         if (t instanceof ArrayType) {
/*  549 */           t = ((ArrayType)t).getBasicType();
/*      */         }
/*  551 */         if (t instanceof ObjectType) {
/*  552 */           Verifier v = VerifierFactory.getVerifier(((ObjectType)t).getClassName());
/*  553 */           VerificationResult vr = v.doPass2();
/*  554 */           if (vr.getStatus() != 1) {
/*  555 */             constraintViolated((Instruction)o, "Argument type class/interface could not be verified successfully: '" + vr.getMessage() + "'.");
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitINSTANCEOF(INSTANCEOF o) {
/*  564 */       indexValid((Instruction)o, o.getIndex());
/*  565 */       Constant c = this.cpg.getConstant(o.getIndex());
/*  566 */       if (!(c instanceof ConstantClass)) {
/*  567 */         constraintViolated((Instruction)o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitCHECKCAST(CHECKCAST o) {
/*  573 */       indexValid((Instruction)o, o.getIndex());
/*  574 */       Constant c = this.cpg.getConstant(o.getIndex());
/*  575 */       if (!(c instanceof ConstantClass)) {
/*  576 */         constraintViolated((Instruction)o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitNEW(NEW o) {
/*  582 */       indexValid((Instruction)o, o.getIndex());
/*  583 */       Constant c = this.cpg.getConstant(o.getIndex());
/*  584 */       if (!(c instanceof ConstantClass)) {
/*  585 */         constraintViolated((Instruction)o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
/*      */       } else {
/*      */         
/*  588 */         ConstantUtf8 cutf8 = (ConstantUtf8)this.cpg.getConstant(((ConstantClass)c).getNameIndex());
/*  589 */         Type t = Type.getType("L" + cutf8.getBytes() + ";");
/*  590 */         if (t instanceof ArrayType) {
/*  591 */           constraintViolated((Instruction)o, "NEW must not be used to create an array.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitMULTIANEWARRAY(MULTIANEWARRAY o) {
/*  599 */       indexValid((Instruction)o, o.getIndex());
/*  600 */       Constant c = this.cpg.getConstant(o.getIndex());
/*  601 */       if (!(c instanceof ConstantClass)) {
/*  602 */         constraintViolated((Instruction)o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
/*      */       }
/*  604 */       int dimensions2create = o.getDimensions();
/*  605 */       if (dimensions2create < 1) {
/*  606 */         constraintViolated((Instruction)o, "Number of dimensions to create must be greater than zero.");
/*      */       }
/*  608 */       Type t = o.getType(this.cpg);
/*  609 */       if (t instanceof ArrayType) {
/*  610 */         int dimensions = ((ArrayType)t).getDimensions();
/*  611 */         if (dimensions < dimensions2create) {
/*  612 */           constraintViolated((Instruction)o, "Not allowed to create array with more dimensions ('+dimensions2create+') than the one referenced by the CONSTANT_Class '" + t + "'.");
/*      */         }
/*      */       } else {
/*      */         
/*  616 */         constraintViolated((Instruction)o, "Expecting a CONSTANT_Class referencing an array type. [Constraint not found in The Java Virtual Machine Specification, Second Edition, 4.8.1]");
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitANEWARRAY(ANEWARRAY o) {
/*  622 */       indexValid((Instruction)o, o.getIndex());
/*  623 */       Constant c = this.cpg.getConstant(o.getIndex());
/*  624 */       if (!(c instanceof ConstantClass)) {
/*  625 */         constraintViolated((Instruction)o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
/*      */       }
/*  627 */       Type t = o.getType(this.cpg);
/*  628 */       if (t instanceof ArrayType) {
/*  629 */         int dimensions = ((ArrayType)t).getDimensions();
/*  630 */         if (dimensions >= 255) {
/*  631 */           constraintViolated((Instruction)o, "Not allowed to create an array with more than 255 dimensions.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitNEWARRAY(NEWARRAY o) {
/*  638 */       byte t = o.getTypecode();
/*  639 */       if (t != 4 && t != 5 && t != 6 && t != 7 && t != 8 && t != 9 && t != 10 && t != 11)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  647 */         constraintViolated((Instruction)o, "Illegal type code '+t+' for 'atype' operand.");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitILOAD(ILOAD o) {
/*  653 */       int idx = o.getIndex();
/*  654 */       if (idx < 0) {
/*  655 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative.");
/*      */       } else {
/*      */         
/*  658 */         int maxminus1 = max_locals() - 1;
/*  659 */         if (idx > maxminus1) {
/*  660 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitFLOAD(FLOAD o) {
/*  667 */       int idx = o.getIndex();
/*  668 */       if (idx < 0) {
/*  669 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative.");
/*      */       } else {
/*      */         
/*  672 */         int maxminus1 = max_locals() - 1;
/*  673 */         if (idx > maxminus1) {
/*  674 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitALOAD(ALOAD o) {
/*  681 */       int idx = o.getIndex();
/*  682 */       if (idx < 0) {
/*  683 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative.");
/*      */       } else {
/*      */         
/*  686 */         int maxminus1 = max_locals() - 1;
/*  687 */         if (idx > maxminus1) {
/*  688 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitISTORE(ISTORE o) {
/*  695 */       int idx = o.getIndex();
/*  696 */       if (idx < 0) {
/*  697 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative.");
/*      */       } else {
/*      */         
/*  700 */         int maxminus1 = max_locals() - 1;
/*  701 */         if (idx > maxminus1) {
/*  702 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitFSTORE(FSTORE o) {
/*  709 */       int idx = o.getIndex();
/*  710 */       if (idx < 0) {
/*  711 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative.");
/*      */       } else {
/*      */         
/*  714 */         int maxminus1 = max_locals() - 1;
/*  715 */         if (idx > maxminus1) {
/*  716 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitASTORE(ASTORE o) {
/*  723 */       int idx = o.getIndex();
/*  724 */       if (idx < 0) {
/*  725 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative.");
/*      */       } else {
/*      */         
/*  728 */         int maxminus1 = max_locals() - 1;
/*  729 */         if (idx > maxminus1) {
/*  730 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitIINC(IINC o) {
/*  737 */       int idx = o.getIndex();
/*  738 */       if (idx < 0) {
/*  739 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative.");
/*      */       } else {
/*      */         
/*  742 */         int maxminus1 = max_locals() - 1;
/*  743 */         if (idx > maxminus1) {
/*  744 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitRET(RET o) {
/*  751 */       int idx = o.getIndex();
/*  752 */       if (idx < 0) {
/*  753 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative.");
/*      */       } else {
/*      */         
/*  756 */         int maxminus1 = max_locals() - 1;
/*  757 */         if (idx > maxminus1) {
/*  758 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitLLOAD(LLOAD o) {
/*  765 */       int idx = o.getIndex();
/*  766 */       if (idx < 0) {
/*  767 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative. [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
/*      */       } else {
/*      */         
/*  770 */         int maxminus2 = max_locals() - 2;
/*  771 */         if (idx > maxminus2) {
/*  772 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitDLOAD(DLOAD o) {
/*  779 */       int idx = o.getIndex();
/*  780 */       if (idx < 0) {
/*  781 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative. [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
/*      */       } else {
/*      */         
/*  784 */         int maxminus2 = max_locals() - 2;
/*  785 */         if (idx > maxminus2) {
/*  786 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitLSTORE(LSTORE o) {
/*  793 */       int idx = o.getIndex();
/*  794 */       if (idx < 0) {
/*  795 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative. [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
/*      */       } else {
/*      */         
/*  798 */         int maxminus2 = max_locals() - 2;
/*  799 */         if (idx > maxminus2) {
/*  800 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitDSTORE(DSTORE o) {
/*  807 */       int idx = o.getIndex();
/*  808 */       if (idx < 0) {
/*  809 */         constraintViolated((Instruction)o, "Index '" + idx + "' must be non-negative. [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
/*      */       } else {
/*      */         
/*  812 */         int maxminus2 = max_locals() - 2;
/*  813 */         if (idx > maxminus2) {
/*  814 */           constraintViolated((Instruction)o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitLOOKUPSWITCH(LOOKUPSWITCH o) {
/*  821 */       int[] matchs = o.getMatchs();
/*  822 */       int max = Integer.MIN_VALUE;
/*  823 */       for (int i = 0; i < matchs.length; i++) {
/*  824 */         if (matchs[i] == max && i != 0) {
/*  825 */           constraintViolated((Instruction)o, "Match '" + matchs[i] + "' occurs more than once.");
/*      */         }
/*  827 */         if (matchs[i] < max) {
/*  828 */           constraintViolated((Instruction)o, "Lookup table must be sorted but isn't.");
/*      */         } else {
/*      */           
/*  831 */           max = matchs[i];
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitTABLESWITCH(TABLESWITCH o) {}
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitPUTSTATIC(PUTSTATIC o) {
/*  844 */       String field_name = o.getFieldName(this.cpg);
/*  845 */       JavaClass jc = Repository.lookupClass(o.getClassType(this.cpg).getClassName());
/*  846 */       Field[] fields = jc.getFields();
/*  847 */       Field f = null;
/*  848 */       for (int i = 0; i < fields.length; i++) {
/*  849 */         if (fields[i].getName().equals(field_name)) {
/*  850 */           f = fields[i];
/*      */           break;
/*      */         } 
/*      */       } 
/*  854 */       if (f == null) {
/*  855 */         throw new AssertionViolatedException("Field not found?!?");
/*      */       }
/*      */       
/*  858 */       if (f.isFinal() && 
/*  859 */         !this.this$0.myOwner.getClassName().equals(o.getClassType(this.cpg).getClassName())) {
/*  860 */         constraintViolated((Instruction)o, "Referenced field '" + f + "' is final and must therefore be declared in the current class '" + this.this$0.myOwner.getClassName() + "' which is not the case: it is declared in '" + o.getClassType(this.cpg).getClassName() + "'.");
/*      */       }
/*      */ 
/*      */       
/*  864 */       if (!f.isStatic()) {
/*  865 */         constraintViolated((Instruction)o, "Referenced field '" + f + "' is not static which it should be.");
/*      */       }
/*      */       
/*  868 */       String meth_name = Repository.lookupClass(this.this$0.myOwner.getClassName()).getMethods()[this.this$0.method_no].getName();
/*      */ 
/*      */       
/*  871 */       if (!jc.isClass() && !meth_name.equals("<clinit>")) {
/*  872 */         constraintViolated((Instruction)o, "Interface field '" + f + "' must be set in a '" + "<clinit>" + "' method.");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitGETSTATIC(GETSTATIC o) {
/*  878 */       String field_name = o.getFieldName(this.cpg);
/*  879 */       JavaClass jc = Repository.lookupClass(o.getClassType(this.cpg).getClassName());
/*  880 */       Field[] fields = jc.getFields();
/*  881 */       Field f = null;
/*  882 */       for (int i = 0; i < fields.length; i++) {
/*  883 */         if (fields[i].getName().equals(field_name)) {
/*  884 */           f = fields[i];
/*      */           break;
/*      */         } 
/*      */       } 
/*  888 */       if (f == null) {
/*  889 */         throw new AssertionViolatedException("Field not found?!?");
/*      */       }
/*      */       
/*  892 */       if (!f.isStatic()) {
/*  893 */         constraintViolated((Instruction)o, "Referenced field '" + f + "' is not static which it should be.");
/*      */       }
/*      */     }
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitINVOKEINTERFACE(INVOKEINTERFACE o) {
/*  913 */       String classname = o.getClassName(this.cpg);
/*  914 */       JavaClass jc = Repository.lookupClass(classname);
/*  915 */       Method[] ms = jc.getMethods();
/*  916 */       Method m = null;
/*  917 */       for (int i = 0; i < ms.length; i++) {
/*  918 */         if (ms[i].getName().equals(o.getMethodName(this.cpg)) && Type.getReturnType(ms[i].getSignature()).equals(o.getReturnType(this.cpg)) && objarrayequals((Object[])Type.getArgumentTypes(ms[i].getSignature()), (Object[])o.getArgumentTypes(this.cpg))) {
/*      */ 
/*      */           
/*  921 */           m = ms[i];
/*      */           break;
/*      */         } 
/*      */       } 
/*  925 */       if (m == null) {
/*  926 */         constraintViolated((Instruction)o, "Referenced method '" + o.getMethodName(this.cpg) + "' with expected signature not found in class '" + jc.getClassName() + "'. The native verfier does allow the method to be declared in some superinterface, which the Java Virtual Machine Specification, Second Edition does not.");
/*      */       }
/*  928 */       if (jc.isClass()) {
/*  929 */         constraintViolated((Instruction)o, "Referenced class '" + jc.getClassName() + "' is a class, but not an interface as expected.");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitINVOKESPECIAL(INVOKESPECIAL o) {
/*  939 */       String classname = o.getClassName(this.cpg);
/*  940 */       JavaClass jc = Repository.lookupClass(classname);
/*  941 */       Method[] ms = jc.getMethods();
/*  942 */       Method m = null;
/*  943 */       for (int i = 0; i < ms.length; i++) {
/*  944 */         if (ms[i].getName().equals(o.getMethodName(this.cpg)) && Type.getReturnType(ms[i].getSignature()).equals(o.getReturnType(this.cpg)) && objarrayequals((Object[])Type.getArgumentTypes(ms[i].getSignature()), (Object[])o.getArgumentTypes(this.cpg))) {
/*      */ 
/*      */           
/*  947 */           m = ms[i];
/*      */           break;
/*      */         } 
/*      */       } 
/*  951 */       if (m == null) {
/*  952 */         constraintViolated((Instruction)o, "Referenced method '" + o.getMethodName(this.cpg) + "' with expected signature not found in class '" + jc.getClassName() + "'. The native verfier does allow the method to be declared in some superclass or implemented interface, which the Java Virtual Machine Specification, Second Edition does not.");
/*      */       }
/*      */       
/*  955 */       JavaClass current = Repository.lookupClass(this.this$0.myOwner.getClassName());
/*  956 */       if (current.isSuper())
/*      */       {
/*  958 */         if (Repository.instanceOf(current, jc) && !current.equals(jc))
/*      */         {
/*  960 */           if (!o.getMethodName(this.cpg).equals("<init>")) {
/*      */ 
/*      */             
/*  963 */             int supidx = -1;
/*      */             
/*  965 */             Method meth = null;
/*  966 */             while (supidx != 0) {
/*  967 */               supidx = current.getSuperclassNameIndex();
/*  968 */               current = Repository.lookupClass(current.getSuperclassName());
/*      */               
/*  970 */               Method[] meths = current.getMethods();
/*  971 */               for (int j = 0; j < meths.length; j++) {
/*  972 */                 if (meths[j].getName().equals(o.getMethodName(this.cpg)) && Type.getReturnType(meths[j].getSignature()).equals(o.getReturnType(this.cpg)) && objarrayequals((Object[])Type.getArgumentTypes(meths[j].getSignature()), (Object[])o.getArgumentTypes(this.cpg))) {
/*      */ 
/*      */                   
/*  975 */                   meth = meths[j];
/*      */                   break;
/*      */                 } 
/*      */               } 
/*  979 */               if (meth != null)
/*      */                 break; 
/*  981 */             }  if (meth == null) {
/*  982 */               constraintViolated((Instruction)o, "ACC_SUPER special lookup procedure not successful: method '" + o.getMethodName(this.cpg) + "' with proper signature not declared in superclass hierarchy.");
/*      */             }
/*      */           } 
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitINVOKESTATIC(INVOKESTATIC o) {
/*  997 */       String classname = o.getClassName(this.cpg);
/*  998 */       JavaClass jc = Repository.lookupClass(classname);
/*  999 */       Method[] ms = jc.getMethods();
/* 1000 */       Method m = null;
/* 1001 */       for (int i = 0; i < ms.length; i++) {
/* 1002 */         if (ms[i].getName().equals(o.getMethodName(this.cpg)) && Type.getReturnType(ms[i].getSignature()).equals(o.getReturnType(this.cpg)) && objarrayequals((Object[])Type.getArgumentTypes(ms[i].getSignature()), (Object[])o.getArgumentTypes(this.cpg))) {
/*      */ 
/*      */           
/* 1005 */           m = ms[i];
/*      */           break;
/*      */         } 
/*      */       } 
/* 1009 */       if (m == null) {
/* 1010 */         constraintViolated((Instruction)o, "Referenced method '" + o.getMethodName(this.cpg) + "' with expected signature not found in class '" + jc.getClassName() + "'. The native verifier possibly allows the method to be declared in some superclass or implemented interface, which the Java Virtual Machine Specification, Second Edition does not.");
/*      */       }
/*      */       
/* 1013 */       if (!m.isStatic()) {
/* 1014 */         constraintViolated((Instruction)o, "Referenced method '" + o.getMethodName(this.cpg) + "' has ACC_STATIC unset.");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitINVOKEVIRTUAL(INVOKEVIRTUAL o) {
/* 1026 */       String classname = o.getClassName(this.cpg);
/* 1027 */       JavaClass jc = Repository.lookupClass(classname);
/* 1028 */       Method[] ms = jc.getMethods();
/* 1029 */       Method m = null;
/* 1030 */       for (int i = 0; i < ms.length; i++) {
/* 1031 */         if (ms[i].getName().equals(o.getMethodName(this.cpg)) && Type.getReturnType(ms[i].getSignature()).equals(o.getReturnType(this.cpg)) && objarrayequals((Object[])Type.getArgumentTypes(ms[i].getSignature()), (Object[])o.getArgumentTypes(this.cpg))) {
/*      */ 
/*      */           
/* 1034 */           m = ms[i];
/*      */           break;
/*      */         } 
/*      */       } 
/* 1038 */       if (m == null) {
/* 1039 */         constraintViolated((Instruction)o, "Referenced method '" + o.getMethodName(this.cpg) + "' with expected signature not found in class '" + jc.getClassName() + "'. The native verfier does allow the method to be declared in some superclass or implemented interface, which the Java Virtual Machine Specification, Second Edition does not.");
/*      */       }
/* 1041 */       if (!jc.isClass()) {
/* 1042 */         constraintViolated((Instruction)o, "Referenced class '" + jc.getClassName() + "' is an interface, but not a class as expected.");
/*      */       }
/*      */     }
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
/*      */     private boolean objarrayequals(Object[] o, Object[] p) {
/* 1056 */       if (o.length != p.length) {
/* 1057 */         return false;
/*      */       }
/*      */       
/* 1060 */       for (int i = 0; i < o.length; i++) {
/* 1061 */         if (!o[i].equals(p[i])) {
/* 1062 */           return false;
/*      */         }
/*      */       } 
/*      */       
/* 1066 */       return true;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/statics/Pass3aVerifier.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */