/*      */ package org.apache.bcel.verifier.structurals;
/*      */ import org.apache.bcel.Repository;
/*      */ import org.apache.bcel.classfile.Constant;
/*      */ import org.apache.bcel.classfile.Field;
/*      */ import org.apache.bcel.classfile.JavaClass;
/*      */ import org.apache.bcel.generic.AALOAD;
/*      */ import org.apache.bcel.generic.AASTORE;
/*      */ import org.apache.bcel.generic.ASTORE;
/*      */ import org.apache.bcel.generic.ArrayType;
/*      */ import org.apache.bcel.generic.BALOAD;
/*      */ import org.apache.bcel.generic.BASTORE;
/*      */ import org.apache.bcel.generic.BasicType;
/*      */ import org.apache.bcel.generic.CASTORE;
/*      */ import org.apache.bcel.generic.CHECKCAST;
/*      */ import org.apache.bcel.generic.CPInstruction;
/*      */ import org.apache.bcel.generic.ConstantPoolGen;
/*      */ import org.apache.bcel.generic.DADD;
/*      */ import org.apache.bcel.generic.DALOAD;
/*      */ import org.apache.bcel.generic.DASTORE;
/*      */ import org.apache.bcel.generic.DCMPL;
/*      */ import org.apache.bcel.generic.DDIV;
/*      */ import org.apache.bcel.generic.DMUL;
/*      */ import org.apache.bcel.generic.DREM;
/*      */ import org.apache.bcel.generic.DUP2_X1;
/*      */ import org.apache.bcel.generic.DUP_X1;
/*      */ import org.apache.bcel.generic.DUP_X2;
/*      */ import org.apache.bcel.generic.FADD;
/*      */ import org.apache.bcel.generic.FALOAD;
/*      */ import org.apache.bcel.generic.FASTORE;
/*      */ import org.apache.bcel.generic.FMUL;
/*      */ import org.apache.bcel.generic.FSUB;
/*      */ import org.apache.bcel.generic.FieldInstruction;
/*      */ import org.apache.bcel.generic.GETFIELD;
/*      */ import org.apache.bcel.generic.IADD;
/*      */ import org.apache.bcel.generic.IALOAD;
/*      */ import org.apache.bcel.generic.IAND;
/*      */ import org.apache.bcel.generic.IASTORE;
/*      */ import org.apache.bcel.generic.IDIV;
/*      */ import org.apache.bcel.generic.IFNONNULL;
/*      */ import org.apache.bcel.generic.IF_ACMPEQ;
/*      */ import org.apache.bcel.generic.IF_ACMPNE;
/*      */ import org.apache.bcel.generic.IF_ICMPGE;
/*      */ import org.apache.bcel.generic.IF_ICMPGT;
/*      */ import org.apache.bcel.generic.IF_ICMPLE;
/*      */ import org.apache.bcel.generic.IF_ICMPLT;
/*      */ import org.apache.bcel.generic.IINC;
/*      */ import org.apache.bcel.generic.IMUL;
/*      */ import org.apache.bcel.generic.INSTANCEOF;
/*      */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*      */ import org.apache.bcel.generic.INVOKESPECIAL;
/*      */ import org.apache.bcel.generic.INVOKESTATIC;
/*      */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*      */ import org.apache.bcel.generic.IOR;
/*      */ import org.apache.bcel.generic.IREM;
/*      */ import org.apache.bcel.generic.ISHL;
/*      */ import org.apache.bcel.generic.ISHR;
/*      */ import org.apache.bcel.generic.IUSHR;
/*      */ import org.apache.bcel.generic.Instruction;
/*      */ import org.apache.bcel.generic.LADD;
/*      */ import org.apache.bcel.generic.LALOAD;
/*      */ import org.apache.bcel.generic.LAND;
/*      */ import org.apache.bcel.generic.LASTORE;
/*      */ import org.apache.bcel.generic.LDC;
/*      */ import org.apache.bcel.generic.LDC2_W;
/*      */ import org.apache.bcel.generic.LDIV;
/*      */ import org.apache.bcel.generic.LMUL;
/*      */ import org.apache.bcel.generic.LOR;
/*      */ import org.apache.bcel.generic.LREM;
/*      */ import org.apache.bcel.generic.LSHL;
/*      */ import org.apache.bcel.generic.LSHR;
/*      */ import org.apache.bcel.generic.LoadClass;
/*      */ import org.apache.bcel.generic.LoadInstruction;
/*      */ import org.apache.bcel.generic.LocalVariableInstruction;
/*      */ import org.apache.bcel.generic.MONITORENTER;
/*      */ import org.apache.bcel.generic.MULTIANEWARRAY;
/*      */ import org.apache.bcel.generic.NEW;
/*      */ import org.apache.bcel.generic.ObjectType;
/*      */ import org.apache.bcel.generic.PUTFIELD;
/*      */ import org.apache.bcel.generic.PUTSTATIC;
/*      */ import org.apache.bcel.generic.RET;
/*      */ import org.apache.bcel.generic.ReferenceType;
/*      */ import org.apache.bcel.generic.ReturnInstruction;
/*      */ import org.apache.bcel.generic.SALOAD;
/*      */ import org.apache.bcel.generic.SASTORE;
/*      */ import org.apache.bcel.generic.SWAP;
/*      */ import org.apache.bcel.generic.StoreInstruction;
/*      */ import org.apache.bcel.generic.Type;
/*      */ import org.apache.bcel.verifier.VerificationResult;
/*      */ import org.apache.bcel.verifier.Verifier;
/*      */ import org.apache.bcel.verifier.VerifierFactory;
/*      */ import org.apache.bcel.verifier.exc.AssertionViolatedException;
/*      */ 
/*      */ public class InstConstraintVisitor extends EmptyVisitor implements Visitor {
/*   94 */   private static ObjectType GENERIC_ARRAY = new ObjectType("org.apache.bcel.verifier.structurals.GenericArray");
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
/*  108 */   private Frame frame = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  115 */   private ConstantPoolGen cpg = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  122 */   private MethodGen mg = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private OperandStack stack() {
/*  130 */     return this.frame.getStack();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LocalVariables locals() {
/*  139 */     return this.frame.getLocals();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void constraintViolated(Instruction violator, String description) {
/*  149 */     String fq_classname = violator.getClass().getName();
/*  150 */     throw new StructuralCodeConstraintException("Instruction " + fq_classname.substring(fq_classname.lastIndexOf('.') + 1) + " constraint violated: " + description);
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
/*      */   public void setFrame(Frame f) {
/*  163 */     this.frame = f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConstantPoolGen(ConstantPoolGen cpg) {
/*  172 */     this.cpg = cpg;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMethodGen(MethodGen mg) {
/*  180 */     this.mg = mg;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void indexOfInt(Instruction o, Type index) {
/*  188 */     if (!index.equals(Type.INT)) {
/*  189 */       constraintViolated(o, "The 'index' is not of type int but of type " + index + ".");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void referenceTypeIsInitialized(Instruction o, ReferenceType r) {
/*  199 */     if (r instanceof UninitializedObjectType) {
/*  200 */       constraintViolated(o, "Working on an uninitialized object '" + r + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void valueOfInt(Instruction o, Type value) {
/*  206 */     if (!value.equals(Type.INT)) {
/*  207 */       constraintViolated(o, "The 'value' is not of type int but of type " + value + ".");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean arrayrefOfArrayType(Instruction o, Type arrayref) {
/*  216 */     if (!(arrayref instanceof ArrayType) && !arrayref.equals(Type.NULL))
/*  217 */       constraintViolated(o, "The 'arrayref' does not refer to an array but is of type " + arrayref + "."); 
/*  218 */     return arrayref instanceof ArrayType;
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
/*      */   private void _visitStackAccessor(Instruction o) {
/*  240 */     int consume = o.consumeStack(this.cpg);
/*  241 */     if (consume > stack().slotsUsed()) {
/*  242 */       constraintViolated(o, "Cannot consume " + consume + " stack slots: only " + stack().slotsUsed() + " slot(s) left on stack!\nStack:\n" + stack());
/*      */     }
/*      */     
/*  245 */     int produce = o.produceStack(this.cpg) - o.consumeStack(this.cpg);
/*  246 */     if (produce + stack().slotsUsed() > stack().maxStack()) {
/*  247 */       constraintViolated(o, "Cannot produce " + produce + " stack slots: only " + (stack().maxStack() - stack().slotsUsed()) + " free stack slot(s) left.\nStack:\n" + stack());
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
/*      */   public void visitLoadClass(LoadClass o) {
/*  262 */     ObjectType t = o.getLoadClassType(this.cpg);
/*  263 */     if (t != null) {
/*  264 */       Verifier v = VerifierFactory.getVerifier(t.getClassName());
/*  265 */       VerificationResult vr = v.doPass2();
/*  266 */       if (vr.getStatus() != 1) {
/*  267 */         constraintViolated((Instruction)o, "Class '" + o.getLoadClassType(this.cpg).getClassName() + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitStackConsumer(StackConsumer o) {
/*  276 */     _visitStackAccessor((Instruction)o);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitStackProducer(StackProducer o) {
/*  283 */     _visitStackAccessor((Instruction)o);
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
/*      */   public void visitCPInstruction(CPInstruction o) {
/*  296 */     int idx = o.getIndex();
/*  297 */     if (idx < 0 || idx >= this.cpg.getSize()) {
/*  298 */       throw new AssertionViolatedException("Huh?! Constant pool index of instruction '" + o + "' illegal? Pass 3a should have checked this!");
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
/*      */   public void visitFieldInstruction(FieldInstruction o) {
/*  310 */     Constant c = this.cpg.getConstant(o.getIndex());
/*  311 */     if (!(c instanceof org.apache.bcel.classfile.ConstantFieldref)) {
/*  312 */       constraintViolated((Instruction)o, "Index '" + o.getIndex() + "' should refer to a CONSTANT_Fieldref_info structure, but refers to '" + c + "'.");
/*      */     }
/*      */     
/*  315 */     Type t = o.getType(this.cpg);
/*  316 */     if (t instanceof ObjectType) {
/*  317 */       String name = ((ObjectType)t).getClassName();
/*  318 */       Verifier v = VerifierFactory.getVerifier(name);
/*  319 */       VerificationResult vr = v.doPass2();
/*  320 */       if (vr.getStatus() != 1) {
/*  321 */         constraintViolated((Instruction)o, "Class '" + name + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
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
/*      */   public void visitInvokeInstruction(InvokeInstruction o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitStackInstruction(StackInstruction o) {
/*  340 */     _visitStackAccessor((Instruction)o);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLocalVariableInstruction(LocalVariableInstruction o) {
/*  348 */     if (locals().maxLocals() <= ((o.getType(this.cpg).getSize() == 1) ? o.getIndex() : (o.getIndex() + 1))) {
/*  349 */       constraintViolated((Instruction)o, "The 'index' is not a valid index into the local variable array.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLoadInstruction(LoadInstruction o) {
/*  360 */     if (locals().get(o.getIndex()) == Type.UNKNOWN) {
/*  361 */       constraintViolated((Instruction)o, "Read-Access on local variable " + o.getIndex() + " with unknown content.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  367 */     if (o.getType(this.cpg).getSize() == 2 && 
/*  368 */       locals().get(o.getIndex() + 1) != Type.UNKNOWN) {
/*  369 */       constraintViolated((Instruction)o, "Reading a two-locals value from local variables " + o.getIndex() + " and " + (o.getIndex() + 1) + " where the latter one is destroyed.");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  374 */     if (!(o instanceof ALOAD)) {
/*  375 */       if (locals().get(o.getIndex()) != o.getType(this.cpg)) {
/*  376 */         constraintViolated((Instruction)o, "Local Variable type and LOADing Instruction type mismatch: Local Variable: '" + locals().get(o.getIndex()) + "'; Instruction type: '" + o.getType(this.cpg) + "'.");
/*      */       
/*      */       }
/*      */     }
/*  380 */     else if (!(locals().get(o.getIndex()) instanceof ReferenceType)) {
/*  381 */       constraintViolated((Instruction)o, "Local Variable type and LOADing Instruction type mismatch: Local Variable: '" + locals().get(o.getIndex()) + "'; Instruction expects a ReferenceType.");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  388 */     if (stack().maxStack() - stack().slotsUsed() < o.getType(this.cpg).getSize()) {
/*  389 */       constraintViolated((Instruction)o, "Not enough free stack slots to load a '" + o.getType(this.cpg) + "' onto the OperandStack.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitStoreInstruction(StoreInstruction o) {
/*  399 */     if (stack().isEmpty()) {
/*  400 */       constraintViolated((Instruction)o, "Cannot STORE: Stack to read from is empty.");
/*      */     }
/*      */     
/*  403 */     if (!(o instanceof ASTORE)) {
/*  404 */       if (stack().peek() != o.getType(this.cpg)) {
/*  405 */         constraintViolated((Instruction)o, "Stack top type and STOREing Instruction type mismatch: Stack top: '" + stack().peek() + "'; Instruction type: '" + o.getType(this.cpg) + "'.");
/*      */       }
/*      */     } else {
/*      */       
/*  409 */       Type stacktop = stack().peek();
/*  410 */       if (!(stacktop instanceof ReferenceType) && !(stacktop instanceof ReturnaddressType)) {
/*  411 */         constraintViolated((Instruction)o, "Stack top type and STOREing Instruction type mismatch: Stack top: '" + stack().peek() + "'; Instruction expects a ReferenceType or a ReturnadressType.");
/*      */       }
/*  413 */       if (stacktop instanceof ReferenceType) {
/*  414 */         referenceTypeIsInitialized((Instruction)o, (ReferenceType)stacktop);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitReturnInstruction(ReturnInstruction o) {
/*  423 */     if (o instanceof RETURN) {
/*      */       return;
/*      */     }
/*  426 */     if (o instanceof ARETURN) {
/*  427 */       if (stack().peek() == Type.NULL) {
/*      */         return;
/*      */       }
/*      */       
/*  431 */       if (!(stack().peek() instanceof ReferenceType)) {
/*  432 */         constraintViolated((Instruction)o, "Reference type expected on top of stack, but is: '" + stack().peek() + "'.");
/*      */       }
/*  434 */       referenceTypeIsInitialized((Instruction)o, (ReferenceType)stack().peek());
/*  435 */       ReferenceType referenceType = (ReferenceType)stack().peek();
/*      */     } else {
/*      */       BasicType basicType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  444 */       Type method_type = this.mg.getType();
/*  445 */       if (method_type == Type.BOOLEAN || method_type == Type.BYTE || method_type == Type.SHORT || method_type == Type.CHAR)
/*      */       {
/*      */ 
/*      */         
/*  449 */         basicType = Type.INT;
/*      */       }
/*  451 */       if (!basicType.equals(stack().peek())) {
/*  452 */         constraintViolated((Instruction)o, "Current method has return type of '" + this.mg.getType() + "' expecting a '" + basicType + "' on top of the stack. But stack top is a '" + stack().peek() + "'.");
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
/*      */   public void visitAALOAD(AALOAD o) {
/*  465 */     Type arrayref = stack().peek(1);
/*  466 */     Type index = stack().peek(0);
/*      */     
/*  468 */     indexOfInt((Instruction)o, index);
/*  469 */     if (arrayrefOfArrayType((Instruction)o, arrayref)) {
/*  470 */       if (!(((ArrayType)arrayref).getElementType() instanceof ReferenceType)) {
/*  471 */         constraintViolated((Instruction)o, "The 'arrayref' does not refer to an array with elements of a ReferenceType but to an array of " + ((ArrayType)arrayref).getElementType() + ".");
/*      */       }
/*  473 */       referenceTypeIsInitialized((Instruction)o, (ReferenceType)((ArrayType)arrayref).getElementType());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitAASTORE(AASTORE o) {
/*  481 */     Type arrayref = stack().peek(2);
/*  482 */     Type index = stack().peek(1);
/*  483 */     Type value = stack().peek(0);
/*      */     
/*  485 */     indexOfInt((Instruction)o, index);
/*  486 */     if (!(value instanceof ReferenceType)) {
/*  487 */       constraintViolated((Instruction)o, "The 'value' is not of a ReferenceType but of type " + value + ".");
/*      */     } else {
/*  489 */       referenceTypeIsInitialized((Instruction)o, (ReferenceType)value);
/*      */     } 
/*      */ 
/*      */     
/*  493 */     if (arrayrefOfArrayType((Instruction)o, arrayref)) {
/*  494 */       if (!(((ArrayType)arrayref).getElementType() instanceof ReferenceType)) {
/*  495 */         constraintViolated((Instruction)o, "The 'arrayref' does not refer to an array with elements of a ReferenceType but to an array of " + ((ArrayType)arrayref).getElementType() + ".");
/*      */       }
/*  497 */       if (!((ReferenceType)value).isAssignmentCompatibleWith(((ArrayType)arrayref).getElementType())) {
/*  498 */         constraintViolated((Instruction)o, "The type of 'value' ('" + value + "') is not assignment compatible to the components of the array 'arrayref' refers to. ('" + ((ArrayType)arrayref).getElementType() + "')");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitACONST_NULL(ACONST_NULL o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitALOAD(ALOAD o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitANEWARRAY(ANEWARRAY o) {
/*  523 */     if (!stack().peek().equals(Type.INT)) {
/*  524 */       constraintViolated((Instruction)o, "The 'count' at the stack top is not of type '" + Type.INT + "' but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitARETURN(ARETURN o) {
/*  533 */     if (!(stack().peek() instanceof ReferenceType)) {
/*  534 */       constraintViolated((Instruction)o, "The 'objectref' at the stack top is not of a ReferenceType but of type '" + stack().peek() + "'.");
/*      */     }
/*  536 */     ReferenceType objectref = (ReferenceType)stack().peek();
/*  537 */     referenceTypeIsInitialized((Instruction)o, objectref);
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
/*      */   public void visitARRAYLENGTH(ARRAYLENGTH o) {
/*  551 */     Type arrayref = stack().peek(0);
/*  552 */     arrayrefOfArrayType((Instruction)o, arrayref);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitASTORE(ASTORE o) {
/*  559 */     if (!(stack().peek() instanceof ReferenceType) && !(stack().peek() instanceof ReturnaddressType)) {
/*  560 */       constraintViolated((Instruction)o, "The 'objectref' is not of a ReferenceType or of ReturnaddressType but of " + stack().peek() + ".");
/*      */     }
/*  562 */     if (stack().peek() instanceof ReferenceType) {
/*  563 */       referenceTypeIsInitialized((Instruction)o, (ReferenceType)stack().peek());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitATHROW(ATHROW o) {
/*  573 */     if (!(stack().peek() instanceof ObjectType) && !stack().peek().equals(Type.NULL)) {
/*  574 */       constraintViolated((Instruction)o, "The 'objectref' is not of an (initialized) ObjectType but of type " + stack().peek() + ".");
/*      */     }
/*      */ 
/*      */     
/*  578 */     if (stack().peek().equals(Type.NULL))
/*      */       return; 
/*  580 */     ObjectType exc = (ObjectType)stack().peek();
/*  581 */     ObjectType throwable = (ObjectType)Type.getType("Ljava/lang/Throwable;");
/*  582 */     if (!exc.subclassOf(throwable) && !exc.equals(throwable)) {
/*  583 */       constraintViolated((Instruction)o, "The 'objectref' is not of class Throwable or of a subclass of Throwable, but of '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitBALOAD(BALOAD o) {
/*  591 */     Type arrayref = stack().peek(1);
/*  592 */     Type index = stack().peek(0);
/*  593 */     indexOfInt((Instruction)o, index);
/*  594 */     if (arrayrefOfArrayType((Instruction)o, arrayref) && 
/*  595 */       !((ArrayType)arrayref).getElementType().equals(Type.BOOLEAN) && !((ArrayType)arrayref).getElementType().equals(Type.BYTE))
/*      */     {
/*  597 */       constraintViolated((Instruction)o, "The 'arrayref' does not refer to an array with elements of a Type.BYTE or Type.BOOLEAN but to an array of '" + ((ArrayType)arrayref).getElementType() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitBASTORE(BASTORE o) {
/*  606 */     Type arrayref = stack().peek(2);
/*  607 */     Type index = stack().peek(1);
/*  608 */     Type value = stack().peek(0);
/*      */     
/*  610 */     indexOfInt((Instruction)o, index);
/*  611 */     valueOfInt((Instruction)o, index);
/*  612 */     if (arrayrefOfArrayType((Instruction)o, arrayref) && 
/*  613 */       !((ArrayType)arrayref).getElementType().equals(Type.BOOLEAN) && !((ArrayType)arrayref).getElementType().equals(Type.BYTE))
/*      */     {
/*  615 */       constraintViolated((Instruction)o, "The 'arrayref' does not refer to an array with elements of a Type.BYTE or Type.BOOLEAN but to an array of '" + ((ArrayType)arrayref).getElementType() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitBIPUSH(BIPUSH o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitBREAKPOINT(BREAKPOINT o) {
/*  630 */     throw new AssertionViolatedException("In this JustIce verification pass there should not occur an illegal instruction such as BREAKPOINT.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitCALOAD(CALOAD o) {
/*  637 */     Type arrayref = stack().peek(1);
/*  638 */     Type index = stack().peek(0);
/*      */     
/*  640 */     indexOfInt((Instruction)o, index);
/*  641 */     arrayrefOfArrayType((Instruction)o, arrayref);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitCASTORE(CASTORE o) {
/*  648 */     Type arrayref = stack().peek(2);
/*  649 */     Type index = stack().peek(1);
/*  650 */     Type value = stack().peek(0);
/*      */     
/*  652 */     indexOfInt((Instruction)o, index);
/*  653 */     valueOfInt((Instruction)o, index);
/*  654 */     if (arrayrefOfArrayType((Instruction)o, arrayref) && 
/*  655 */       !((ArrayType)arrayref).getElementType().equals(Type.CHAR)) {
/*  656 */       constraintViolated((Instruction)o, "The 'arrayref' does not refer to an array with elements of type char but to an array of type " + ((ArrayType)arrayref).getElementType() + ".");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitCHECKCAST(CHECKCAST o) {
/*  666 */     Type objectref = stack().peek(0);
/*  667 */     if (!(objectref instanceof ReferenceType)) {
/*  668 */       constraintViolated((Instruction)o, "The 'objectref' is not of a ReferenceType but of type " + objectref + ".");
/*      */     } else {
/*      */       
/*  671 */       referenceTypeIsInitialized((Instruction)o, (ReferenceType)objectref);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  676 */     Constant c = this.cpg.getConstant(o.getIndex());
/*  677 */     if (!(c instanceof org.apache.bcel.classfile.ConstantClass)) {
/*  678 */       constraintViolated((Instruction)o, "The Constant at 'index' is not a ConstantClass, but '" + c + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitD2F(D2F o) {
/*  686 */     if (stack().peek() != Type.DOUBLE) {
/*  687 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitD2I(D2I o) {
/*  695 */     if (stack().peek() != Type.DOUBLE) {
/*  696 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitD2L(D2L o) {
/*  704 */     if (stack().peek() != Type.DOUBLE) {
/*  705 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDADD(DADD o) {
/*  713 */     if (stack().peek() != Type.DOUBLE) {
/*  714 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*  716 */     if (stack().peek(1) != Type.DOUBLE) {
/*  717 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'double', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDALOAD(DALOAD o) {
/*  725 */     indexOfInt((Instruction)o, stack().peek());
/*  726 */     if (stack().peek(1) == Type.NULL) {
/*      */       return;
/*      */     }
/*  729 */     if (!(stack().peek(1) instanceof ArrayType)) {
/*  730 */       constraintViolated((Instruction)o, "Stack next-to-top must be of type double[] but is '" + stack().peek(1) + "'.");
/*      */     }
/*  732 */     Type t = ((ArrayType)stack().peek(1)).getBasicType();
/*  733 */     if (t != Type.DOUBLE) {
/*  734 */       constraintViolated((Instruction)o, "Stack next-to-top must be of type double[] but is '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDASTORE(DASTORE o) {
/*  742 */     if (stack().peek() != Type.DOUBLE) {
/*  743 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*  745 */     indexOfInt((Instruction)o, stack().peek(1));
/*  746 */     if (stack().peek(2) == Type.NULL) {
/*      */       return;
/*      */     }
/*  749 */     if (!(stack().peek(2) instanceof ArrayType)) {
/*  750 */       constraintViolated((Instruction)o, "Stack next-to-next-to-top must be of type double[] but is '" + stack().peek(2) + "'.");
/*      */     }
/*  752 */     Type t = ((ArrayType)stack().peek(2)).getBasicType();
/*  753 */     if (t != Type.DOUBLE) {
/*  754 */       constraintViolated((Instruction)o, "Stack next-to-next-to-top must be of type double[] but is '" + stack().peek(2) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDCMPG(DCMPG o) {
/*  762 */     if (stack().peek() != Type.DOUBLE) {
/*  763 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*  765 */     if (stack().peek(1) != Type.DOUBLE) {
/*  766 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'double', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDCMPL(DCMPL o) {
/*  774 */     if (stack().peek() != Type.DOUBLE) {
/*  775 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*  777 */     if (stack().peek(1) != Type.DOUBLE) {
/*  778 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'double', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDCONST(DCONST o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDDIV(DDIV o) {
/*  793 */     if (stack().peek() != Type.DOUBLE) {
/*  794 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*  796 */     if (stack().peek(1) != Type.DOUBLE) {
/*  797 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'double', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDLOAD(DLOAD o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDMUL(DMUL o) {
/*  814 */     if (stack().peek() != Type.DOUBLE) {
/*  815 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*  817 */     if (stack().peek(1) != Type.DOUBLE) {
/*  818 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'double', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDNEG(DNEG o) {
/*  826 */     if (stack().peek() != Type.DOUBLE) {
/*  827 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDREM(DREM o) {
/*  835 */     if (stack().peek() != Type.DOUBLE) {
/*  836 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*  838 */     if (stack().peek(1) != Type.DOUBLE) {
/*  839 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'double', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDRETURN(DRETURN o) {
/*  847 */     if (stack().peek() != Type.DOUBLE) {
/*  848 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDSTORE(DSTORE o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDSUB(DSUB o) {
/*  865 */     if (stack().peek() != Type.DOUBLE) {
/*  866 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'double', but of type '" + stack().peek() + "'.");
/*      */     }
/*  868 */     if (stack().peek(1) != Type.DOUBLE) {
/*  869 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'double', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDUP(DUP o) {
/*  877 */     if (stack().peek().getSize() != 1) {
/*  878 */       constraintViolated((Instruction)o, "Won't DUP type on stack top '" + stack().peek() + "' because it must occupy exactly one slot, not '" + stack().peek().getSize() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDUP_X1(DUP_X1 o) {
/*  886 */     if (stack().peek().getSize() != 1) {
/*  887 */       constraintViolated((Instruction)o, "Type on stack top '" + stack().peek() + "' should occupy exactly one slot, not '" + stack().peek().getSize() + "'.");
/*      */     }
/*  889 */     if (stack().peek(1).getSize() != 1) {
/*  890 */       constraintViolated((Instruction)o, "Type on stack next-to-top '" + stack().peek(1) + "' should occupy exactly one slot, not '" + stack().peek(1).getSize() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDUP_X2(DUP_X2 o) {
/*  898 */     if (stack().peek().getSize() != 1) {
/*  899 */       constraintViolated((Instruction)o, "Stack top type must be of size 1, but is '" + stack().peek() + "' of size '" + stack().peek().getSize() + "'.");
/*      */     }
/*  901 */     if (stack().peek(1).getSize() == 2) {
/*      */       return;
/*      */     }
/*      */     
/*  905 */     if (stack().peek(2).getSize() != 1) {
/*  906 */       constraintViolated((Instruction)o, "If stack top's size is 1 and stack next-to-top's size is 1, stack next-to-next-to-top's size must also be 1, but is: '" + stack().peek(2) + "' of size '" + stack().peek(2).getSize() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDUP2(DUP2 o) {
/*  915 */     if (stack().peek().getSize() == 2) {
/*      */       return;
/*      */     }
/*      */     
/*  919 */     if (stack().peek(1).getSize() != 1) {
/*  920 */       constraintViolated((Instruction)o, "If stack top's size is 1, then stack next-to-top's size must also be 1. But it is '" + stack().peek(1) + "' of size '" + stack().peek(1).getSize() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDUP2_X1(DUP2_X1 o) {
/*  929 */     if (stack().peek().getSize() == 2) {
/*  930 */       if (stack().peek(1).getSize() != 1) {
/*  931 */         constraintViolated((Instruction)o, "If stack top's size is 2, then stack next-to-top's size must be 1. But it is '" + stack().peek(1) + "' of size '" + stack().peek(1).getSize() + "'.");
/*      */       } else {
/*      */         
/*      */         return;
/*      */       } 
/*      */     } else {
/*      */       
/*  938 */       if (stack().peek(1).getSize() != 1) {
/*  939 */         constraintViolated((Instruction)o, "If stack top's size is 1, then stack next-to-top's size must also be 1. But it is '" + stack().peek(1) + "' of size '" + stack().peek(1).getSize() + "'.");
/*      */       }
/*  941 */       if (stack().peek(2).getSize() != 1) {
/*  942 */         constraintViolated((Instruction)o, "If stack top's size is 1, then stack next-to-next-to-top's size must also be 1. But it is '" + stack().peek(2) + "' of size '" + stack().peek(2).getSize() + "'.");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitDUP2_X2(DUP2_X2 o) {
/*  952 */     if (stack().peek(0).getSize() == 2) {
/*  953 */       if (stack().peek(1).getSize() == 2) {
/*      */         return;
/*      */       }
/*      */       
/*  957 */       if (stack().peek(2).getSize() != 1) {
/*  958 */         constraintViolated((Instruction)o, "If stack top's size is 2 and stack-next-to-top's size is 1, then stack next-to-next-to-top's size must also be 1. But it is '" + stack().peek(2) + "' of size '" + stack().peek(2).getSize() + "'.");
/*      */       }
/*      */       else {
/*      */         
/*      */         return;
/*      */       }
/*      */     
/*      */     }
/*  966 */     else if (stack().peek(1).getSize() == 1) {
/*  967 */       if (stack().peek(2).getSize() == 2) {
/*      */         return;
/*      */       }
/*      */       
/*  971 */       if (stack().peek(3).getSize() == 1) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  977 */     constraintViolated((Instruction)o, "The operand sizes on the stack do not match any of the four forms of usage of this instruction.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitF2D(F2D o) {
/*  984 */     if (stack().peek() != Type.FLOAT) {
/*  985 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitF2I(F2I o) {
/*  993 */     if (stack().peek() != Type.FLOAT) {
/*  994 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitF2L(F2L o) {
/* 1002 */     if (stack().peek() != Type.FLOAT) {
/* 1003 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFADD(FADD o) {
/* 1011 */     if (stack().peek() != Type.FLOAT) {
/* 1012 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1014 */     if (stack().peek(1) != Type.FLOAT) {
/* 1015 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'float', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFALOAD(FALOAD o) {
/* 1023 */     indexOfInt((Instruction)o, stack().peek());
/* 1024 */     if (stack().peek(1) == Type.NULL) {
/*      */       return;
/*      */     }
/* 1027 */     if (!(stack().peek(1) instanceof ArrayType)) {
/* 1028 */       constraintViolated((Instruction)o, "Stack next-to-top must be of type float[] but is '" + stack().peek(1) + "'.");
/*      */     }
/* 1030 */     Type t = ((ArrayType)stack().peek(1)).getBasicType();
/* 1031 */     if (t != Type.FLOAT) {
/* 1032 */       constraintViolated((Instruction)o, "Stack next-to-top must be of type float[] but is '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFASTORE(FASTORE o) {
/* 1040 */     if (stack().peek() != Type.FLOAT) {
/* 1041 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1043 */     indexOfInt((Instruction)o, stack().peek(1));
/* 1044 */     if (stack().peek(2) == Type.NULL) {
/*      */       return;
/*      */     }
/* 1047 */     if (!(stack().peek(2) instanceof ArrayType)) {
/* 1048 */       constraintViolated((Instruction)o, "Stack next-to-next-to-top must be of type float[] but is '" + stack().peek(2) + "'.");
/*      */     }
/* 1050 */     Type t = ((ArrayType)stack().peek(2)).getBasicType();
/* 1051 */     if (t != Type.FLOAT) {
/* 1052 */       constraintViolated((Instruction)o, "Stack next-to-next-to-top must be of type float[] but is '" + stack().peek(2) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFCMPG(FCMPG o) {
/* 1060 */     if (stack().peek() != Type.FLOAT) {
/* 1061 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1063 */     if (stack().peek(1) != Type.FLOAT) {
/* 1064 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'float', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFCMPL(FCMPL o) {
/* 1072 */     if (stack().peek() != Type.FLOAT) {
/* 1073 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1075 */     if (stack().peek(1) != Type.FLOAT) {
/* 1076 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'float', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFCONST(FCONST o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFDIV(FDIV o) {
/* 1091 */     if (stack().peek() != Type.FLOAT) {
/* 1092 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1094 */     if (stack().peek(1) != Type.FLOAT) {
/* 1095 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'float', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFLOAD(FLOAD o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFMUL(FMUL o) {
/* 1112 */     if (stack().peek() != Type.FLOAT) {
/* 1113 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1115 */     if (stack().peek(1) != Type.FLOAT) {
/* 1116 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'float', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFNEG(FNEG o) {
/* 1124 */     if (stack().peek() != Type.FLOAT) {
/* 1125 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFREM(FREM o) {
/* 1133 */     if (stack().peek() != Type.FLOAT) {
/* 1134 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1136 */     if (stack().peek(1) != Type.FLOAT) {
/* 1137 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'float', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFRETURN(FRETURN o) {
/* 1145 */     if (stack().peek() != Type.FLOAT) {
/* 1146 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFSTORE(FSTORE o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitFSUB(FSUB o) {
/* 1163 */     if (stack().peek() != Type.FLOAT) {
/* 1164 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'float', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1166 */     if (stack().peek(1) != Type.FLOAT) {
/* 1167 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'float', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitGETFIELD(GETFIELD o) {
/* 1175 */     Type objectref = stack().peek();
/* 1176 */     if (!(objectref instanceof ObjectType) && objectref != Type.NULL) {
/* 1177 */       constraintViolated((Instruction)o, "Stack top should be an object reference that's not an array reference, but is '" + objectref + "'.");
/*      */     }
/*      */     
/* 1180 */     String field_name = o.getFieldName(this.cpg);
/*      */     
/* 1182 */     JavaClass jc = Repository.lookupClass(o.getClassType(this.cpg).getClassName());
/* 1183 */     Field[] fields = jc.getFields();
/* 1184 */     Field f = null;
/* 1185 */     for (int i = 0; i < fields.length; i++) {
/* 1186 */       if (fields[i].getName().equals(field_name)) {
/* 1187 */         f = fields[i];
/*      */         break;
/*      */       } 
/*      */     } 
/* 1191 */     if (f == null) {
/* 1192 */       throw new AssertionViolatedException("Field not found?!?");
/*      */     }
/*      */     
/* 1195 */     if (f.isProtected()) {
/* 1196 */       ObjectType classtype = o.getClassType(this.cpg);
/* 1197 */       ObjectType curr = new ObjectType(this.mg.getClassName());
/*      */       
/* 1199 */       if (classtype.equals(curr) || curr.subclassOf(classtype)) {
/*      */         
/* 1201 */         Type t = stack().peek();
/* 1202 */         if (t == Type.NULL) {
/*      */           return;
/*      */         }
/* 1205 */         if (!(t instanceof ObjectType)) {
/* 1206 */           constraintViolated((Instruction)o, "The 'objectref' must refer to an object that's not an array. Found instead: '" + t + "'.");
/*      */         }
/* 1208 */         ObjectType objreftype = (ObjectType)t;
/* 1209 */         if (objreftype.equals(curr) || !objreftype.subclassOf(curr));
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
/* 1220 */     if (f.isStatic()) {
/* 1221 */       constraintViolated((Instruction)o, "Referenced field '" + f + "' is static which it shouldn't be.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitGETSTATIC(GETSTATIC o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitGOTO(GOTO o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitGOTO_W(GOTO_W o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitI2B(I2B o) {
/* 1250 */     if (stack().peek() != Type.INT) {
/* 1251 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitI2C(I2C o) {
/* 1259 */     if (stack().peek() != Type.INT) {
/* 1260 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitI2D(I2D o) {
/* 1268 */     if (stack().peek() != Type.INT) {
/* 1269 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitI2F(I2F o) {
/* 1277 */     if (stack().peek() != Type.INT) {
/* 1278 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitI2L(I2L o) {
/* 1286 */     if (stack().peek() != Type.INT) {
/* 1287 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitI2S(I2S o) {
/* 1295 */     if (stack().peek() != Type.INT) {
/* 1296 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIADD(IADD o) {
/* 1304 */     if (stack().peek() != Type.INT) {
/* 1305 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1307 */     if (stack().peek(1) != Type.INT) {
/* 1308 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIALOAD(IALOAD o) {
/* 1316 */     indexOfInt((Instruction)o, stack().peek());
/* 1317 */     if (stack().peek(1) == Type.NULL) {
/*      */       return;
/*      */     }
/* 1320 */     if (!(stack().peek(1) instanceof ArrayType)) {
/* 1321 */       constraintViolated((Instruction)o, "Stack next-to-top must be of type int[] but is '" + stack().peek(1) + "'.");
/*      */     }
/* 1323 */     Type t = ((ArrayType)stack().peek(1)).getBasicType();
/* 1324 */     if (t != Type.INT) {
/* 1325 */       constraintViolated((Instruction)o, "Stack next-to-top must be of type int[] but is '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIAND(IAND o) {
/* 1333 */     if (stack().peek() != Type.INT) {
/* 1334 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1336 */     if (stack().peek(1) != Type.INT) {
/* 1337 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIASTORE(IASTORE o) {
/* 1345 */     if (stack().peek() != Type.INT) {
/* 1346 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1348 */     indexOfInt((Instruction)o, stack().peek(1));
/* 1349 */     if (stack().peek(2) == Type.NULL) {
/*      */       return;
/*      */     }
/* 1352 */     if (!(stack().peek(2) instanceof ArrayType)) {
/* 1353 */       constraintViolated((Instruction)o, "Stack next-to-next-to-top must be of type int[] but is '" + stack().peek(2) + "'.");
/*      */     }
/* 1355 */     Type t = ((ArrayType)stack().peek(2)).getBasicType();
/* 1356 */     if (t != Type.INT) {
/* 1357 */       constraintViolated((Instruction)o, "Stack next-to-next-to-top must be of type int[] but is '" + stack().peek(2) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitICONST(ICONST o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIDIV(IDIV o) {
/* 1372 */     if (stack().peek() != Type.INT) {
/* 1373 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1375 */     if (stack().peek(1) != Type.INT) {
/* 1376 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIF_ACMPEQ(IF_ACMPEQ o) {
/* 1384 */     if (!(stack().peek() instanceof ReferenceType)) {
/* 1385 */       constraintViolated((Instruction)o, "The value at the stack top is not of a ReferenceType, but of type '" + stack().peek() + "'.");
/*      */     }
/* 1387 */     referenceTypeIsInitialized((Instruction)o, (ReferenceType)stack().peek());
/*      */     
/* 1389 */     if (!(stack().peek(1) instanceof ReferenceType)) {
/* 1390 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of a ReferenceType, but of type '" + stack().peek(1) + "'.");
/*      */     }
/* 1392 */     referenceTypeIsInitialized((Instruction)o, (ReferenceType)stack().peek(1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIF_ACMPNE(IF_ACMPNE o) {
/* 1400 */     if (!(stack().peek() instanceof ReferenceType)) {
/* 1401 */       constraintViolated((Instruction)o, "The value at the stack top is not of a ReferenceType, but of type '" + stack().peek() + "'.");
/* 1402 */       referenceTypeIsInitialized((Instruction)o, (ReferenceType)stack().peek());
/*      */     } 
/* 1404 */     if (!(stack().peek(1) instanceof ReferenceType)) {
/* 1405 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of a ReferenceType, but of type '" + stack().peek(1) + "'.");
/* 1406 */       referenceTypeIsInitialized((Instruction)o, (ReferenceType)stack().peek(1));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIF_ICMPEQ(IF_ICMPEQ o) {
/* 1414 */     if (stack().peek() != Type.INT) {
/* 1415 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1417 */     if (stack().peek(1) != Type.INT) {
/* 1418 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIF_ICMPGE(IF_ICMPGE o) {
/* 1426 */     if (stack().peek() != Type.INT) {
/* 1427 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1429 */     if (stack().peek(1) != Type.INT) {
/* 1430 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIF_ICMPGT(IF_ICMPGT o) {
/* 1438 */     if (stack().peek() != Type.INT) {
/* 1439 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1441 */     if (stack().peek(1) != Type.INT) {
/* 1442 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIF_ICMPLE(IF_ICMPLE o) {
/* 1450 */     if (stack().peek() != Type.INT) {
/* 1451 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1453 */     if (stack().peek(1) != Type.INT) {
/* 1454 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIF_ICMPLT(IF_ICMPLT o) {
/* 1462 */     if (stack().peek() != Type.INT) {
/* 1463 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1465 */     if (stack().peek(1) != Type.INT) {
/* 1466 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIF_ICMPNE(IF_ICMPNE o) {
/* 1474 */     if (stack().peek() != Type.INT) {
/* 1475 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1477 */     if (stack().peek(1) != Type.INT) {
/* 1478 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIFEQ(IFEQ o) {
/* 1486 */     if (stack().peek() != Type.INT) {
/* 1487 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIFGE(IFGE o) {
/* 1495 */     if (stack().peek() != Type.INT) {
/* 1496 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIFGT(IFGT o) {
/* 1504 */     if (stack().peek() != Type.INT) {
/* 1505 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIFLE(IFLE o) {
/* 1513 */     if (stack().peek() != Type.INT) {
/* 1514 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIFLT(IFLT o) {
/* 1522 */     if (stack().peek() != Type.INT) {
/* 1523 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIFNE(IFNE o) {
/* 1531 */     if (stack().peek() != Type.INT) {
/* 1532 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIFNONNULL(IFNONNULL o) {
/* 1540 */     if (!(stack().peek() instanceof ReferenceType)) {
/* 1541 */       constraintViolated((Instruction)o, "The value at the stack top is not of a ReferenceType, but of type '" + stack().peek() + "'.");
/*      */     }
/* 1543 */     referenceTypeIsInitialized((Instruction)o, (ReferenceType)stack().peek());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIFNULL(IFNULL o) {
/* 1550 */     if (!(stack().peek() instanceof ReferenceType)) {
/* 1551 */       constraintViolated((Instruction)o, "The value at the stack top is not of a ReferenceType, but of type '" + stack().peek() + "'.");
/*      */     }
/* 1553 */     referenceTypeIsInitialized((Instruction)o, (ReferenceType)stack().peek());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIINC(IINC o) {
/* 1561 */     if (locals().maxLocals() <= ((o.getType(this.cpg).getSize() == 1) ? o.getIndex() : (o.getIndex() + 1))) {
/* 1562 */       constraintViolated((Instruction)o, "The 'index' is not a valid index into the local variable array.");
/*      */     }
/*      */     
/* 1565 */     indexOfInt((Instruction)o, locals().get(o.getIndex()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitILOAD(ILOAD o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIMPDEP1(IMPDEP1 o) {
/* 1579 */     throw new AssertionViolatedException("In this JustIce verification pass there should not occur an illegal instruction such as IMPDEP1.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIMPDEP2(IMPDEP2 o) {
/* 1586 */     throw new AssertionViolatedException("In this JustIce verification pass there should not occur an illegal instruction such as IMPDEP2.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIMUL(IMUL o) {
/* 1593 */     if (stack().peek() != Type.INT) {
/* 1594 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1596 */     if (stack().peek(1) != Type.INT) {
/* 1597 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitINEG(INEG o) {
/* 1605 */     if (stack().peek() != Type.INT) {
/* 1606 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitINSTANCEOF(INSTANCEOF o) {
/* 1615 */     Type objectref = stack().peek(0);
/* 1616 */     if (!(objectref instanceof ReferenceType)) {
/* 1617 */       constraintViolated((Instruction)o, "The 'objectref' is not of a ReferenceType but of type " + objectref + ".");
/*      */     } else {
/*      */       
/* 1620 */       referenceTypeIsInitialized((Instruction)o, (ReferenceType)objectref);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1625 */     Constant c = this.cpg.getConstant(o.getIndex());
/* 1626 */     if (!(c instanceof org.apache.bcel.classfile.ConstantClass)) {
/* 1627 */       constraintViolated((Instruction)o, "The Constant at 'index' is not a ConstantClass, but '" + c + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitINVOKEINTERFACE(INVOKEINTERFACE o) {
/*      */     ObjectType objectType;
/* 1637 */     int count = o.getCount();
/* 1638 */     if (count == 0) {
/* 1639 */       constraintViolated((Instruction)o, "The 'count' argument must not be 0.");
/*      */     }
/*      */     
/* 1642 */     ConstantInterfaceMethodref cimr = (ConstantInterfaceMethodref)this.cpg.getConstant(o.getIndex());
/*      */ 
/*      */ 
/*      */     
/* 1646 */     Type t = o.getType(this.cpg);
/* 1647 */     if (t instanceof ObjectType) {
/* 1648 */       String name = ((ObjectType)t).getClassName();
/* 1649 */       Verifier v = VerifierFactory.getVerifier(name);
/* 1650 */       VerificationResult vr = v.doPass2();
/* 1651 */       if (vr.getStatus() != 1) {
/* 1652 */         constraintViolated((Instruction)o, "Class '" + name + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1657 */     Type[] argtypes = o.getArgumentTypes(this.cpg);
/* 1658 */     int nargs = argtypes.length;
/*      */     
/* 1660 */     for (int i = nargs - 1; i >= 0; i--) {
/* 1661 */       BasicType basicType; Type fromStack = stack().peek(nargs - 1 - i);
/* 1662 */       Type fromDesc = argtypes[i];
/* 1663 */       if (fromDesc == Type.BOOLEAN || fromDesc == Type.BYTE || fromDesc == Type.CHAR || fromDesc == Type.SHORT)
/*      */       {
/*      */ 
/*      */         
/* 1667 */         basicType = Type.INT;
/*      */       }
/* 1669 */       if (!fromStack.equals(basicType)) {
/* 1670 */         if (fromStack instanceof ReferenceType && basicType instanceof ReferenceType) {
/* 1671 */           ReferenceType rFromStack = (ReferenceType)fromStack;
/* 1672 */           ReferenceType referenceType1 = (ReferenceType)basicType;
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */           
/* 1680 */           constraintViolated((Instruction)o, "Expecting a '" + basicType + "' but found a '" + fromStack + "' on the stack.");
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1685 */     Type objref = stack().peek(nargs);
/* 1686 */     if (objref == Type.NULL) {
/*      */       return;
/*      */     }
/* 1689 */     if (!(objref instanceof ReferenceType)) {
/* 1690 */       constraintViolated((Instruction)o, "Expecting a reference type as 'objectref' on the stack, not a '" + objref + "'.");
/*      */     }
/* 1692 */     referenceTypeIsInitialized((Instruction)o, (ReferenceType)objref);
/* 1693 */     if (!(objref instanceof ObjectType)) {
/* 1694 */       if (!(objref instanceof ArrayType)) {
/* 1695 */         constraintViolated((Instruction)o, "Expecting an ObjectType as 'objectref' on the stack, not a '" + objref + "'.");
/*      */       } else {
/*      */         
/* 1698 */         objectType = GENERIC_ARRAY;
/*      */       } 
/*      */     }
/*      */     
/* 1702 */     String objref_classname = objectType.getClassName();
/*      */     
/* 1704 */     String theInterface = o.getClassName(this.cpg);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1712 */     int counted_count = 1;
/* 1713 */     for (int j = 0; j < nargs; j++) {
/* 1714 */       counted_count += argtypes[j].getSize();
/*      */     }
/* 1716 */     if (count != counted_count) {
/* 1717 */       constraintViolated((Instruction)o, "The 'count' argument should probably read '" + counted_count + "' but is '" + count + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitINVOKESPECIAL(INVOKESPECIAL o) {
/*      */     ObjectType objectType;
/* 1726 */     if (o.getMethodName(this.cpg).equals("<init>") && !(stack().peek((o.getArgumentTypes(this.cpg)).length) instanceof UninitializedObjectType)) {
/* 1727 */       constraintViolated((Instruction)o, "Possibly initializing object twice. A valid instruction sequence must not have an uninitialized object on the operand stack or in a local variable during a backwards branch, or in a local variable in code protected by an exception handler. Please see The Java Virtual Machine Specification, Second Edition, 4.9.4 (pages 147 and 148) for details.");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1732 */     Type t = o.getType(this.cpg);
/* 1733 */     if (t instanceof ObjectType) {
/* 1734 */       String name = ((ObjectType)t).getClassName();
/* 1735 */       Verifier v = VerifierFactory.getVerifier(name);
/* 1736 */       VerificationResult vr = v.doPass2();
/* 1737 */       if (vr.getStatus() != 1) {
/* 1738 */         constraintViolated((Instruction)o, "Class '" + name + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1743 */     Type[] argtypes = o.getArgumentTypes(this.cpg);
/* 1744 */     int nargs = argtypes.length;
/*      */     
/* 1746 */     for (int i = nargs - 1; i >= 0; i--) {
/* 1747 */       BasicType basicType; Type fromStack = stack().peek(nargs - 1 - i);
/* 1748 */       Type fromDesc = argtypes[i];
/* 1749 */       if (fromDesc == Type.BOOLEAN || fromDesc == Type.BYTE || fromDesc == Type.CHAR || fromDesc == Type.SHORT)
/*      */       {
/*      */ 
/*      */         
/* 1753 */         basicType = Type.INT;
/*      */       }
/* 1755 */       if (!fromStack.equals(basicType)) {
/* 1756 */         if (fromStack instanceof ReferenceType && basicType instanceof ReferenceType) {
/* 1757 */           ReferenceType rFromStack = (ReferenceType)fromStack;
/* 1758 */           ReferenceType referenceType1 = (ReferenceType)basicType;
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */           
/* 1766 */           constraintViolated((Instruction)o, "Expecting a '" + basicType + "' but found a '" + fromStack + "' on the stack.");
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1771 */     Type objref = stack().peek(nargs);
/* 1772 */     if (objref == Type.NULL) {
/*      */       return;
/*      */     }
/* 1775 */     if (!(objref instanceof ReferenceType)) {
/* 1776 */       constraintViolated((Instruction)o, "Expecting a reference type as 'objectref' on the stack, not a '" + objref + "'.");
/*      */     }
/* 1778 */     String objref_classname = null;
/* 1779 */     if (!o.getMethodName(this.cpg).equals("<init>")) {
/* 1780 */       referenceTypeIsInitialized((Instruction)o, (ReferenceType)objref);
/* 1781 */       if (!(objref instanceof ObjectType)) {
/* 1782 */         if (!(objref instanceof ArrayType)) {
/* 1783 */           constraintViolated((Instruction)o, "Expecting an ObjectType as 'objectref' on the stack, not a '" + objref + "'.");
/*      */         } else {
/*      */           
/* 1786 */           objectType = GENERIC_ARRAY;
/*      */         } 
/*      */       }
/*      */       
/* 1790 */       objref_classname = objectType.getClassName();
/*      */     } else {
/*      */       
/* 1793 */       if (!(objectType instanceof UninitializedObjectType)) {
/* 1794 */         constraintViolated((Instruction)o, "Expecting an UninitializedObjectType as 'objectref' on the stack, not a '" + objectType + "'. Otherwise, you couldn't invoke a method since an array has no methods (not to speak of a return address).");
/*      */       }
/* 1796 */       objref_classname = ((UninitializedObjectType)objectType).getInitialized().getClassName();
/*      */     } 
/*      */ 
/*      */     
/* 1800 */     String theClass = o.getClassName(this.cpg);
/* 1801 */     if (!Repository.instanceOf(objref_classname, theClass)) {
/* 1802 */       constraintViolated((Instruction)o, "The 'objref' item '" + objectType + "' does not implement '" + theClass + "' as expected.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitINVOKESTATIC(INVOKESTATIC o) {
/* 1813 */     Type t = o.getType(this.cpg);
/* 1814 */     if (t instanceof ObjectType) {
/* 1815 */       String name = ((ObjectType)t).getClassName();
/* 1816 */       Verifier v = VerifierFactory.getVerifier(name);
/* 1817 */       VerificationResult vr = v.doPass2();
/* 1818 */       if (vr.getStatus() != 1) {
/* 1819 */         constraintViolated((Instruction)o, "Class '" + name + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
/*      */       }
/*      */     } 
/*      */     
/* 1823 */     Type[] argtypes = o.getArgumentTypes(this.cpg);
/* 1824 */     int nargs = argtypes.length;
/*      */     
/* 1826 */     for (int i = nargs - 1; i >= 0; i--) {
/* 1827 */       BasicType basicType; Type fromStack = stack().peek(nargs - 1 - i);
/* 1828 */       Type fromDesc = argtypes[i];
/* 1829 */       if (fromDesc == Type.BOOLEAN || fromDesc == Type.BYTE || fromDesc == Type.CHAR || fromDesc == Type.SHORT)
/*      */       {
/*      */ 
/*      */         
/* 1833 */         basicType = Type.INT;
/*      */       }
/* 1835 */       if (!fromStack.equals(basicType)) {
/* 1836 */         if (fromStack instanceof ReferenceType && basicType instanceof ReferenceType) {
/* 1837 */           ReferenceType rFromStack = (ReferenceType)fromStack;
/* 1838 */           ReferenceType referenceType1 = (ReferenceType)basicType;
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */           
/* 1846 */           constraintViolated((Instruction)o, "Expecting a '" + basicType + "' but found a '" + fromStack + "' on the stack.");
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitINVOKEVIRTUAL(INVOKEVIRTUAL o) {
/*      */     ObjectType objectType;
/* 1858 */     Type t = o.getType(this.cpg);
/* 1859 */     if (t instanceof ObjectType) {
/* 1860 */       String name = ((ObjectType)t).getClassName();
/* 1861 */       Verifier v = VerifierFactory.getVerifier(name);
/* 1862 */       VerificationResult vr = v.doPass2();
/* 1863 */       if (vr.getStatus() != 1) {
/* 1864 */         constraintViolated((Instruction)o, "Class '" + name + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1869 */     Type[] argtypes = o.getArgumentTypes(this.cpg);
/* 1870 */     int nargs = argtypes.length;
/*      */     
/* 1872 */     for (int i = nargs - 1; i >= 0; i--) {
/* 1873 */       BasicType basicType; Type fromStack = stack().peek(nargs - 1 - i);
/* 1874 */       Type fromDesc = argtypes[i];
/* 1875 */       if (fromDesc == Type.BOOLEAN || fromDesc == Type.BYTE || fromDesc == Type.CHAR || fromDesc == Type.SHORT)
/*      */       {
/*      */ 
/*      */         
/* 1879 */         basicType = Type.INT;
/*      */       }
/* 1881 */       if (!fromStack.equals(basicType)) {
/* 1882 */         if (fromStack instanceof ReferenceType && basicType instanceof ReferenceType) {
/* 1883 */           ReferenceType rFromStack = (ReferenceType)fromStack;
/* 1884 */           ReferenceType referenceType1 = (ReferenceType)basicType;
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */           
/* 1892 */           constraintViolated((Instruction)o, "Expecting a '" + basicType + "' but found a '" + fromStack + "' on the stack.");
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1897 */     Type objref = stack().peek(nargs);
/* 1898 */     if (objref == Type.NULL) {
/*      */       return;
/*      */     }
/* 1901 */     if (!(objref instanceof ReferenceType)) {
/* 1902 */       constraintViolated((Instruction)o, "Expecting a reference type as 'objectref' on the stack, not a '" + objref + "'.");
/*      */     }
/* 1904 */     referenceTypeIsInitialized((Instruction)o, (ReferenceType)objref);
/* 1905 */     if (!(objref instanceof ObjectType)) {
/* 1906 */       if (!(objref instanceof ArrayType)) {
/* 1907 */         constraintViolated((Instruction)o, "Expecting an ObjectType as 'objectref' on the stack, not a '" + objref + "'.");
/*      */       } else {
/*      */         
/* 1910 */         objectType = GENERIC_ARRAY;
/*      */       } 
/*      */     }
/*      */     
/* 1914 */     String objref_classname = objectType.getClassName();
/*      */     
/* 1916 */     String theClass = o.getClassName(this.cpg);
/*      */     
/* 1918 */     if (!Repository.instanceOf(objref_classname, theClass)) {
/* 1919 */       constraintViolated((Instruction)o, "The 'objref' item '" + objectType + "' does not implement '" + theClass + "' as expected.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIOR(IOR o) {
/* 1927 */     if (stack().peek() != Type.INT) {
/* 1928 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1930 */     if (stack().peek(1) != Type.INT) {
/* 1931 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIREM(IREM o) {
/* 1939 */     if (stack().peek() != Type.INT) {
/* 1940 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1942 */     if (stack().peek(1) != Type.INT) {
/* 1943 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIRETURN(IRETURN o) {
/* 1951 */     if (stack().peek() != Type.INT) {
/* 1952 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitISHL(ISHL o) {
/* 1960 */     if (stack().peek() != Type.INT) {
/* 1961 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1963 */     if (stack().peek(1) != Type.INT) {
/* 1964 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitISHR(ISHR o) {
/* 1972 */     if (stack().peek() != Type.INT) {
/* 1973 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1975 */     if (stack().peek(1) != Type.INT) {
/* 1976 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitISTORE(ISTORE o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitISUB(ISUB o) {
/* 1993 */     if (stack().peek() != Type.INT) {
/* 1994 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 1996 */     if (stack().peek(1) != Type.INT) {
/* 1997 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIUSHR(IUSHR o) {
/* 2005 */     if (stack().peek() != Type.INT) {
/* 2006 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2008 */     if (stack().peek(1) != Type.INT) {
/* 2009 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitIXOR(IXOR o) {
/* 2017 */     if (stack().peek() != Type.INT) {
/* 2018 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2020 */     if (stack().peek(1) != Type.INT) {
/* 2021 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'int', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitJSR(JSR o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitJSR_W(JSR_W o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitL2D(L2D o) {
/* 2043 */     if (stack().peek() != Type.LONG) {
/* 2044 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitL2F(L2F o) {
/* 2052 */     if (stack().peek() != Type.LONG) {
/* 2053 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitL2I(L2I o) {
/* 2061 */     if (stack().peek() != Type.LONG) {
/* 2062 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLADD(LADD o) {
/* 2070 */     if (stack().peek() != Type.LONG) {
/* 2071 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2073 */     if (stack().peek(1) != Type.LONG) {
/* 2074 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLALOAD(LALOAD o) {
/* 2082 */     indexOfInt((Instruction)o, stack().peek());
/* 2083 */     if (stack().peek(1) == Type.NULL) {
/*      */       return;
/*      */     }
/* 2086 */     if (!(stack().peek(1) instanceof ArrayType)) {
/* 2087 */       constraintViolated((Instruction)o, "Stack next-to-top must be of type long[] but is '" + stack().peek(1) + "'.");
/*      */     }
/* 2089 */     Type t = ((ArrayType)stack().peek(1)).getBasicType();
/* 2090 */     if (t != Type.LONG) {
/* 2091 */       constraintViolated((Instruction)o, "Stack next-to-top must be of type long[] but is '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLAND(LAND o) {
/* 2099 */     if (stack().peek() != Type.LONG) {
/* 2100 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2102 */     if (stack().peek(1) != Type.LONG) {
/* 2103 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLASTORE(LASTORE o) {
/* 2111 */     if (stack().peek() != Type.LONG) {
/* 2112 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2114 */     indexOfInt((Instruction)o, stack().peek(1));
/* 2115 */     if (stack().peek(2) == Type.NULL) {
/*      */       return;
/*      */     }
/* 2118 */     if (!(stack().peek(2) instanceof ArrayType)) {
/* 2119 */       constraintViolated((Instruction)o, "Stack next-to-next-to-top must be of type long[] but is '" + stack().peek(2) + "'.");
/*      */     }
/* 2121 */     Type t = ((ArrayType)stack().peek(2)).getBasicType();
/* 2122 */     if (t != Type.LONG) {
/* 2123 */       constraintViolated((Instruction)o, "Stack next-to-next-to-top must be of type long[] but is '" + stack().peek(2) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLCMP(LCMP o) {
/* 2131 */     if (stack().peek() != Type.LONG) {
/* 2132 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2134 */     if (stack().peek(1) != Type.LONG) {
/* 2135 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLCONST(LCONST o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLDC(LDC o) {
/* 2152 */     Constant c = this.cpg.getConstant(o.getIndex());
/* 2153 */     if (!(c instanceof org.apache.bcel.classfile.ConstantInteger) && !(c instanceof org.apache.bcel.classfile.ConstantFloat) && !(c instanceof org.apache.bcel.classfile.ConstantString))
/*      */     {
/*      */       
/* 2156 */       constraintViolated((Instruction)o, "Referenced constant should be a CONSTANT_Integer, a CONSTANT_Float or a CONSTANT_String, but is '" + c + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLDC_W(LDC_W o) {
/* 2166 */     Constant c = this.cpg.getConstant(o.getIndex());
/* 2167 */     if (!(c instanceof org.apache.bcel.classfile.ConstantInteger) && !(c instanceof org.apache.bcel.classfile.ConstantFloat) && !(c instanceof org.apache.bcel.classfile.ConstantString))
/*      */     {
/*      */       
/* 2170 */       constraintViolated((Instruction)o, "Referenced constant should be a CONSTANT_Integer, a CONSTANT_Float or a CONSTANT_String, but is '" + c + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLDC2_W(LDC2_W o) {
/* 2180 */     Constant c = this.cpg.getConstant(o.getIndex());
/* 2181 */     if (!(c instanceof org.apache.bcel.classfile.ConstantLong) && !(c instanceof org.apache.bcel.classfile.ConstantDouble))
/*      */     {
/* 2183 */       constraintViolated((Instruction)o, "Referenced constant should be a CONSTANT_Integer, a CONSTANT_Float or a CONSTANT_String, but is '" + c + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLDIV(LDIV o) {
/* 2191 */     if (stack().peek() != Type.LONG) {
/* 2192 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2194 */     if (stack().peek(1) != Type.LONG) {
/* 2195 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLLOAD(LLOAD o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLMUL(LMUL o) {
/* 2212 */     if (stack().peek() != Type.LONG) {
/* 2213 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2215 */     if (stack().peek(1) != Type.LONG) {
/* 2216 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLNEG(LNEG o) {
/* 2224 */     if (stack().peek() != Type.LONG) {
/* 2225 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLOOKUPSWITCH(LOOKUPSWITCH o) {
/* 2233 */     if (stack().peek() != Type.INT) {
/* 2234 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLOR(LOR o) {
/* 2243 */     if (stack().peek() != Type.LONG) {
/* 2244 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2246 */     if (stack().peek(1) != Type.LONG) {
/* 2247 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLREM(LREM o) {
/* 2255 */     if (stack().peek() != Type.LONG) {
/* 2256 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2258 */     if (stack().peek(1) != Type.LONG) {
/* 2259 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLRETURN(LRETURN o) {
/* 2267 */     if (stack().peek() != Type.LONG) {
/* 2268 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLSHL(LSHL o) {
/* 2276 */     if (stack().peek() != Type.INT) {
/* 2277 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2279 */     if (stack().peek(1) != Type.LONG) {
/* 2280 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLSHR(LSHR o) {
/* 2288 */     if (stack().peek() != Type.INT) {
/* 2289 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2291 */     if (stack().peek(1) != Type.LONG) {
/* 2292 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLSTORE(LSTORE o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLSUB(LSUB o) {
/* 2309 */     if (stack().peek() != Type.LONG) {
/* 2310 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2312 */     if (stack().peek(1) != Type.LONG) {
/* 2313 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLUSHR(LUSHR o) {
/* 2321 */     if (stack().peek() != Type.INT) {
/* 2322 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2324 */     if (stack().peek(1) != Type.LONG) {
/* 2325 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitLXOR(LXOR o) {
/* 2333 */     if (stack().peek() != Type.LONG) {
/* 2334 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'long', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2336 */     if (stack().peek(1) != Type.LONG) {
/* 2337 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of type 'long', but of type '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitMONITORENTER(MONITORENTER o) {
/* 2345 */     if (!(stack().peek() instanceof ReferenceType)) {
/* 2346 */       constraintViolated((Instruction)o, "The stack top should be of a ReferenceType, but is '" + stack().peek() + "'.");
/*      */     }
/* 2348 */     referenceTypeIsInitialized((Instruction)o, (ReferenceType)stack().peek());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitMONITOREXIT(MONITOREXIT o) {
/* 2355 */     if (!(stack().peek() instanceof ReferenceType)) {
/* 2356 */       constraintViolated((Instruction)o, "The stack top should be of a ReferenceType, but is '" + stack().peek() + "'.");
/*      */     }
/* 2358 */     referenceTypeIsInitialized((Instruction)o, (ReferenceType)stack().peek());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitMULTIANEWARRAY(MULTIANEWARRAY o) {
/* 2365 */     int dimensions = o.getDimensions();
/*      */     
/* 2367 */     for (int i = 0; i < dimensions; i++) {
/* 2368 */       if (stack().peek(i) != Type.INT) {
/* 2369 */         constraintViolated((Instruction)o, "The '" + dimensions + "' upper stack types should be 'int' but aren't.");
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
/*      */   public void visitNEW(NEW o) {
/* 2383 */     Type t = o.getType(this.cpg);
/* 2384 */     if (!(t instanceof ReferenceType)) {
/* 2385 */       throw new AssertionViolatedException("NEW.getType() returning a non-reference type?!");
/*      */     }
/* 2387 */     if (!(t instanceof ObjectType)) {
/* 2388 */       constraintViolated((Instruction)o, "Expecting a class type (ObjectType) to work on. Found: '" + t + "'.");
/*      */     }
/* 2390 */     ObjectType obj = (ObjectType)t;
/*      */ 
/*      */     
/* 2393 */     if (!obj.referencesClass()) {
/* 2394 */       constraintViolated((Instruction)o, "Expecting a class type (ObjectType) to work on. Found: '" + obj + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitNEWARRAY(NEWARRAY o) {
/* 2402 */     if (stack().peek() != Type.INT) {
/* 2403 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitNOP(NOP o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitPOP(POP o) {
/* 2418 */     if (stack().peek().getSize() != 1) {
/* 2419 */       constraintViolated((Instruction)o, "Stack top size should be 1 but stack top is '" + stack().peek() + "' of size '" + stack().peek().getSize() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitPOP2(POP2 o) {
/* 2427 */     if (stack().peek().getSize() != 2) {
/* 2428 */       constraintViolated((Instruction)o, "Stack top size should be 2 but stack top is '" + stack().peek() + "' of size '" + stack().peek().getSize() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitPUTFIELD(PUTFIELD o) {
/*      */     BasicType basicType;
/* 2437 */     Type objectref = stack().peek(1);
/* 2438 */     if (!(objectref instanceof ObjectType) && objectref != Type.NULL) {
/* 2439 */       constraintViolated((Instruction)o, "Stack next-to-top should be an object reference that's not an array reference, but is '" + objectref + "'.");
/*      */     }
/*      */     
/* 2442 */     String field_name = o.getFieldName(this.cpg);
/*      */     
/* 2444 */     JavaClass jc = Repository.lookupClass(o.getClassType(this.cpg).getClassName());
/* 2445 */     Field[] fields = jc.getFields();
/* 2446 */     Field f = null;
/* 2447 */     for (int i = 0; i < fields.length; i++) {
/* 2448 */       if (fields[i].getName().equals(field_name)) {
/* 2449 */         f = fields[i];
/*      */         break;
/*      */       } 
/*      */     } 
/* 2453 */     if (f == null) {
/* 2454 */       throw new AssertionViolatedException("Field not found?!?");
/*      */     }
/*      */     
/* 2457 */     Type value = stack().peek();
/* 2458 */     Type t = Type.getType(f.getSignature());
/* 2459 */     Type shouldbe = t;
/* 2460 */     if (shouldbe == Type.BOOLEAN || shouldbe == Type.BYTE || shouldbe == Type.CHAR || shouldbe == Type.SHORT)
/*      */     {
/*      */ 
/*      */       
/* 2464 */       basicType = Type.INT;
/*      */     }
/* 2466 */     if (t instanceof ReferenceType) {
/* 2467 */       ReferenceType rvalue = null;
/* 2468 */       if (value instanceof ReferenceType) {
/* 2469 */         rvalue = (ReferenceType)value;
/* 2470 */         referenceTypeIsInitialized((Instruction)o, rvalue);
/*      */       } else {
/*      */         
/* 2473 */         constraintViolated((Instruction)o, "The stack top type '" + value + "' is not of a reference type as expected.");
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2482 */     else if (basicType != value) {
/* 2483 */       constraintViolated((Instruction)o, "The stack top type '" + value + "' is not of type '" + basicType + "' as expected.");
/*      */     } 
/*      */ 
/*      */     
/* 2487 */     if (f.isProtected()) {
/* 2488 */       ObjectType classtype = o.getClassType(this.cpg);
/* 2489 */       ObjectType curr = new ObjectType(this.mg.getClassName());
/*      */       
/* 2491 */       if (classtype.equals(curr) || curr.subclassOf(classtype)) {
/*      */         
/* 2493 */         Type tp = stack().peek(1);
/* 2494 */         if (tp == Type.NULL) {
/*      */           return;
/*      */         }
/* 2497 */         if (!(tp instanceof ObjectType)) {
/* 2498 */           constraintViolated((Instruction)o, "The 'objectref' must refer to an object that's not an array. Found instead: '" + tp + "'.");
/*      */         }
/* 2500 */         ObjectType objreftype = (ObjectType)tp;
/* 2501 */         if (!objreftype.equals(curr) && !objreftype.subclassOf(curr))
/*      */         {
/* 2503 */           constraintViolated((Instruction)o, "The referenced field has the ACC_PROTECTED modifier, and it's a member of the current class or a superclass of the current class. However, the referenced object type '" + stack().peek() + "' is not the current class or a subclass of the current class.");
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2509 */     if (f.isStatic()) {
/* 2510 */       constraintViolated((Instruction)o, "Referenced field '" + f + "' is static which it shouldn't be.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitPUTSTATIC(PUTSTATIC o) {
/*      */     BasicType basicType;
/* 2518 */     String field_name = o.getFieldName(this.cpg);
/* 2519 */     JavaClass jc = Repository.lookupClass(o.getClassType(this.cpg).getClassName());
/* 2520 */     Field[] fields = jc.getFields();
/* 2521 */     Field f = null;
/* 2522 */     for (int i = 0; i < fields.length; i++) {
/* 2523 */       if (fields[i].getName().equals(field_name)) {
/* 2524 */         f = fields[i];
/*      */         break;
/*      */       } 
/*      */     } 
/* 2528 */     if (f == null) {
/* 2529 */       throw new AssertionViolatedException("Field not found?!?");
/*      */     }
/* 2531 */     Type value = stack().peek();
/* 2532 */     Type t = Type.getType(f.getSignature());
/* 2533 */     Type shouldbe = t;
/* 2534 */     if (shouldbe == Type.BOOLEAN || shouldbe == Type.BYTE || shouldbe == Type.CHAR || shouldbe == Type.SHORT)
/*      */     {
/*      */ 
/*      */       
/* 2538 */       basicType = Type.INT;
/*      */     }
/* 2540 */     if (t instanceof ReferenceType) {
/* 2541 */       ReferenceType rvalue = null;
/* 2542 */       if (value instanceof ReferenceType) {
/* 2543 */         rvalue = (ReferenceType)value;
/* 2544 */         referenceTypeIsInitialized((Instruction)o, rvalue);
/*      */       } else {
/*      */         
/* 2547 */         constraintViolated((Instruction)o, "The stack top type '" + value + "' is not of a reference type as expected.");
/*      */       } 
/* 2549 */       if (!rvalue.isAssignmentCompatibleWith((Type)basicType)) {
/* 2550 */         constraintViolated((Instruction)o, "The stack top type '" + value + "' is not assignment compatible with '" + basicType + "'.");
/*      */       
/*      */       }
/*      */     }
/* 2554 */     else if (basicType != value) {
/* 2555 */       constraintViolated((Instruction)o, "The stack top type '" + value + "' is not of type '" + basicType + "' as expected.");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitRET(RET o) {
/* 2566 */     if (!(locals().get(o.getIndex()) instanceof ReturnaddressType)) {
/* 2567 */       constraintViolated((Instruction)o, "Expecting a ReturnaddressType in local variable " + o.getIndex() + ".");
/*      */     }
/* 2569 */     if (locals().get(o.getIndex()) == ReturnaddressType.NO_TARGET) {
/* 2570 */       throw new AssertionViolatedException("Oops: RET expecting a target!");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitRETURN(RETURN o) {
/* 2580 */     if (this.mg.getName().equals("<init>")) {
/* 2581 */       this; if (Frame._this != null && !this.mg.getClassName().equals(Type.OBJECT.getClassName())) {
/* 2582 */         constraintViolated((Instruction)o, "Leaving a constructor that itself did not call a constructor.");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitSALOAD(SALOAD o) {
/* 2591 */     indexOfInt((Instruction)o, stack().peek());
/* 2592 */     if (stack().peek(1) == Type.NULL) {
/*      */       return;
/*      */     }
/* 2595 */     if (!(stack().peek(1) instanceof ArrayType)) {
/* 2596 */       constraintViolated((Instruction)o, "Stack next-to-top must be of type short[] but is '" + stack().peek(1) + "'.");
/*      */     }
/* 2598 */     Type t = ((ArrayType)stack().peek(1)).getBasicType();
/* 2599 */     if (t != Type.SHORT) {
/* 2600 */       constraintViolated((Instruction)o, "Stack next-to-top must be of type short[] but is '" + stack().peek(1) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitSASTORE(SASTORE o) {
/* 2608 */     if (stack().peek() != Type.INT) {
/* 2609 */       constraintViolated((Instruction)o, "The value at the stack top is not of type 'int', but of type '" + stack().peek() + "'.");
/*      */     }
/* 2611 */     indexOfInt((Instruction)o, stack().peek(1));
/* 2612 */     if (stack().peek(2) == Type.NULL) {
/*      */       return;
/*      */     }
/* 2615 */     if (!(stack().peek(2) instanceof ArrayType)) {
/* 2616 */       constraintViolated((Instruction)o, "Stack next-to-next-to-top must be of type short[] but is '" + stack().peek(2) + "'.");
/*      */     }
/* 2618 */     Type t = ((ArrayType)stack().peek(2)).getBasicType();
/* 2619 */     if (t != Type.SHORT) {
/* 2620 */       constraintViolated((Instruction)o, "Stack next-to-next-to-top must be of type short[] but is '" + stack().peek(2) + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitSIPUSH(SIPUSH o) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitSWAP(SWAP o) {
/* 2635 */     if (stack().peek().getSize() != 1) {
/* 2636 */       constraintViolated((Instruction)o, "The value at the stack top is not of size '1', but of size '" + stack().peek().getSize() + "'.");
/*      */     }
/* 2638 */     if (stack().peek(1).getSize() != 1) {
/* 2639 */       constraintViolated((Instruction)o, "The value at the stack next-to-top is not of size '1', but of size '" + stack().peek(1).getSize() + "'.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitTABLESWITCH(TABLESWITCH o) {
/* 2647 */     indexOfInt((Instruction)o, stack().peek());
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/InstConstraintVisitor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */