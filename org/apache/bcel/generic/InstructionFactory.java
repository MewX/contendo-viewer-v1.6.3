/*     */ package org.apache.bcel.generic;
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
/*     */ public class InstructionFactory
/*     */   implements InstructionConstants
/*     */ {
/*     */   protected ClassGen cg;
/*     */   protected ConstantPoolGen cp;
/*     */   
/*     */   public InstructionFactory(ClassGen cg, ConstantPoolGen cp) {
/*  73 */     this.cg = cg;
/*  74 */     this.cp = cp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionFactory(ClassGen cg) {
/*  80 */     this(cg, cg.getConstantPool());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionFactory(ConstantPoolGen cp) {
/*  86 */     this(null, cp);
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
/*     */   
/*     */   public InvokeInstruction createInvoke(String class_name, String name, Type ret_type, Type[] arg_types, short kind) {
/* 102 */     int j, nargs = 0;
/* 103 */     String signature = Type.getMethodSignature(ret_type, arg_types);
/*     */     
/* 105 */     for (int i = 0; i < arg_types.length; i++) {
/* 106 */       nargs += arg_types[i].getSize();
/*     */     }
/* 108 */     if (kind == 185) {
/* 109 */       j = this.cp.addInterfaceMethodref(class_name, name, signature);
/*     */     } else {
/* 111 */       j = this.cp.addMethodref(class_name, name, signature);
/*     */     } 
/* 113 */     switch (kind) { case 183:
/* 114 */         return new INVOKESPECIAL(j);
/* 115 */       case 182: return new INVOKEVIRTUAL(j);
/* 116 */       case 184: return new INVOKESTATIC(j);
/* 117 */       case 185: return new INVOKEINTERFACE(j, nargs + 1); }
/*     */     
/* 119 */     throw new RuntimeException("Oops: Unknown invoke kind:" + kind);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionList createPrintln(String s) {
/* 128 */     InstructionList il = new InstructionList();
/* 129 */     int out = this.cp.addFieldref("java.lang.System", "out", "Ljava/io/PrintStream;");
/*     */     
/* 131 */     int println = this.cp.addMethodref("java.io.PrintStream", "println", "(Ljava/lang/String;)V");
/*     */ 
/*     */     
/* 134 */     il.append(new GETSTATIC(out));
/* 135 */     il.append(new PUSH(this.cp, s));
/* 136 */     il.append(new INVOKEVIRTUAL(println));
/*     */     
/* 138 */     return il;
/*     */   }
/*     */   
/*     */   private static class MethodObject {
/*     */     Type[] arg_types;
/*     */     Type result_type;
/*     */     String[] arg_names;
/*     */     String class_name;
/*     */     String name;
/*     */     int access;
/*     */     
/*     */     MethodObject(String c, String n, Type r, Type[] a, int acc) {
/* 150 */       this.class_name = c;
/* 151 */       this.name = n;
/* 152 */       this.result_type = r;
/* 153 */       this.arg_types = a;
/* 154 */       this.access = acc;
/*     */     }
/*     */   }
/*     */   
/*     */   private InvokeInstruction createInvoke(MethodObject m, short kind) {
/* 159 */     return createInvoke(m.class_name, m.name, m.result_type, m.arg_types, kind);
/*     */   }
/*     */   
/* 162 */   private static MethodObject[] append_mos = new MethodObject[] { new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.STRING }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.OBJECT }, 1), null, null, new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.BOOLEAN }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.CHAR }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.FLOAT }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.DOUBLE }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.INT }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.INT }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.INT }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.LONG }, 1) };
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
/*     */   private static final boolean isString(Type type) {
/* 187 */     return (type instanceof ObjectType && ((ObjectType)type).getClassName().equals("java.lang.String"));
/*     */   }
/*     */ 
/*     */   
/*     */   public Instruction createAppend(Type type) {
/* 192 */     byte t = type.getType();
/*     */     
/* 194 */     if (isString(type)) {
/* 195 */       return createInvoke(append_mos[0], (short)182);
/*     */     }
/* 197 */     switch (t) {
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 11:
/* 206 */         return createInvoke(append_mos[t], (short)182);
/*     */       case 13:
/*     */       case 14:
/* 209 */         return createInvoke(append_mos[1], (short)182);
/*     */     } 
/* 211 */     throw new RuntimeException("Oops: No append for this type? " + type);
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
/*     */   public FieldInstruction createFieldAccess(String class_name, String name, Type type, short kind) {
/* 225 */     String signature = type.getSignature();
/*     */     
/* 227 */     int index = this.cp.addFieldref(class_name, name, signature);
/*     */     
/* 229 */     switch (kind) { case 180:
/* 230 */         return new GETFIELD(index);
/* 231 */       case 181: return new PUTFIELD(index);
/* 232 */       case 178: return new GETSTATIC(index);
/* 233 */       case 179: return new PUTSTATIC(index); }
/*     */ 
/*     */     
/* 236 */     throw new RuntimeException("Oops: Unknown getfield kind:" + kind);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Instruction createThis() {
/* 243 */     return new ALOAD(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ReturnInstruction createReturn(Type type) {
/* 249 */     switch (type.getType()) { case 13:
/*     */       case 14:
/* 251 */         return InstructionConstants.ARETURN;
/*     */       case 4: case 5:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/* 256 */         return InstructionConstants.IRETURN;
/* 257 */       case 6: return InstructionConstants.FRETURN;
/* 258 */       case 7: return InstructionConstants.DRETURN;
/* 259 */       case 11: return InstructionConstants.LRETURN;
/* 260 */       case 12: return InstructionConstants.RETURN; }
/*     */ 
/*     */     
/* 263 */     throw new RuntimeException("Invalid type: " + type);
/*     */   }
/*     */ 
/*     */   
/*     */   private static final ArithmeticInstruction createBinaryIntOp(char first, String op) {
/* 268 */     switch (first) { case '-':
/* 269 */         return InstructionConstants.ISUB;
/* 270 */       case '+': return InstructionConstants.IADD;
/* 271 */       case '%': return InstructionConstants.IREM;
/* 272 */       case '*': return InstructionConstants.IMUL;
/* 273 */       case '/': return InstructionConstants.IDIV;
/* 274 */       case '&': return InstructionConstants.IAND;
/* 275 */       case '|': return InstructionConstants.IOR;
/* 276 */       case '^': return InstructionConstants.IXOR;
/* 277 */       case '<': return InstructionConstants.ISHL;
/* 278 */       case '>': return op.equals(">>>") ? InstructionConstants.IUSHR : InstructionConstants.ISHR; }
/*     */     
/* 280 */     throw new RuntimeException("Invalid operand " + op);
/*     */   }
/*     */ 
/*     */   
/*     */   private static final ArithmeticInstruction createBinaryLongOp(char first, String op) {
/* 285 */     switch (first) { case '-':
/* 286 */         return InstructionConstants.LSUB;
/* 287 */       case '+': return InstructionConstants.LADD;
/* 288 */       case '%': return InstructionConstants.LREM;
/* 289 */       case '*': return InstructionConstants.LMUL;
/* 290 */       case '/': return InstructionConstants.LDIV;
/* 291 */       case '&': return InstructionConstants.LAND;
/* 292 */       case '|': return InstructionConstants.LOR;
/* 293 */       case '^': return InstructionConstants.LXOR;
/* 294 */       case '<': return InstructionConstants.LSHL;
/* 295 */       case '>': return op.equals(">>>") ? InstructionConstants.LUSHR : InstructionConstants.LSHR; }
/*     */     
/* 297 */     throw new RuntimeException("Invalid operand " + op);
/*     */   }
/*     */ 
/*     */   
/*     */   private static final ArithmeticInstruction createBinaryFloatOp(char op) {
/* 302 */     switch (op) { case '-':
/* 303 */         return InstructionConstants.FSUB;
/* 304 */       case '+': return InstructionConstants.FADD;
/* 305 */       case '*': return InstructionConstants.FMUL;
/* 306 */       case '/': return InstructionConstants.FDIV; }
/* 307 */      throw new RuntimeException("Invalid operand " + op);
/*     */   }
/*     */ 
/*     */   
/*     */   private static final ArithmeticInstruction createBinaryDoubleOp(char op) {
/* 312 */     switch (op) { case '-':
/* 313 */         return InstructionConstants.DSUB;
/* 314 */       case '+': return InstructionConstants.DADD;
/* 315 */       case '*': return InstructionConstants.DMUL;
/* 316 */       case '/': return InstructionConstants.DDIV; }
/* 317 */      throw new RuntimeException("Invalid operand " + op);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArithmeticInstruction createBinaryOperation(String op, Type type) {
/* 327 */     char first = op.toCharArray()[0];
/*     */     
/* 329 */     switch (type.getType()) { case 5:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/* 333 */         return createBinaryIntOp(first, op);
/* 334 */       case 11: return createBinaryLongOp(first, op);
/* 335 */       case 6: return createBinaryFloatOp(first);
/* 336 */       case 7: return createBinaryDoubleOp(first); }
/* 337 */      throw new RuntimeException("Invalid type " + type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static StackInstruction createPop(int size) {
/* 345 */     return (size == 2) ? InstructionConstants.POP2 : InstructionConstants.POP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static StackInstruction createDup(int size) {
/* 353 */     return (size == 2) ? InstructionConstants.DUP2 : InstructionConstants.DUP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static StackInstruction createDup_2(int size) {
/* 361 */     return (size == 2) ? InstructionConstants.DUP2_X2 : InstructionConstants.DUP_X2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static StackInstruction createDup_1(int size) {
/* 369 */     return (size == 2) ? InstructionConstants.DUP2_X1 : InstructionConstants.DUP_X1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LocalVariableInstruction createStore(Type type, int index) {
/* 377 */     switch (type.getType()) { case 4:
/*     */       case 5:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/* 382 */         return new ISTORE(index);
/* 383 */       case 6: return new FSTORE(index);
/* 384 */       case 7: return new DSTORE(index);
/* 385 */       case 11: return new LSTORE(index);
/*     */       case 13: case 14:
/* 387 */         return new ASTORE(index); }
/* 388 */      throw new RuntimeException("Invalid type " + type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LocalVariableInstruction createLoad(Type type, int index) {
/* 396 */     switch (type.getType()) { case 4:
/*     */       case 5:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/* 401 */         return new ILOAD(index);
/* 402 */       case 6: return new FLOAD(index);
/* 403 */       case 7: return new DLOAD(index);
/* 404 */       case 11: return new LLOAD(index);
/*     */       case 13: case 14:
/* 406 */         return new ALOAD(index); }
/* 407 */      throw new RuntimeException("Invalid type " + type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayInstruction createArrayLoad(Type type) {
/* 415 */     switch (type.getType()) { case 4:
/*     */       case 8:
/* 417 */         return InstructionConstants.BALOAD;
/* 418 */       case 5: return InstructionConstants.CALOAD;
/* 419 */       case 9: return InstructionConstants.SALOAD;
/* 420 */       case 10: return InstructionConstants.IALOAD;
/* 421 */       case 6: return InstructionConstants.FALOAD;
/* 422 */       case 7: return InstructionConstants.DALOAD;
/* 423 */       case 11: return InstructionConstants.LALOAD;
/*     */       case 13: case 14:
/* 425 */         return InstructionConstants.AALOAD; }
/* 426 */      throw new RuntimeException("Invalid type " + type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayInstruction createArrayStore(Type type) {
/* 434 */     switch (type.getType()) { case 4:
/*     */       case 8:
/* 436 */         return InstructionConstants.BASTORE;
/* 437 */       case 5: return InstructionConstants.CASTORE;
/* 438 */       case 9: return InstructionConstants.SASTORE;
/* 439 */       case 10: return InstructionConstants.IASTORE;
/* 440 */       case 6: return InstructionConstants.FASTORE;
/* 441 */       case 7: return InstructionConstants.DASTORE;
/* 442 */       case 11: return InstructionConstants.LASTORE;
/*     */       case 13: case 14:
/* 444 */         return InstructionConstants.AASTORE; }
/* 445 */      throw new RuntimeException("Invalid type " + type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instruction createCast(Type src_type, Type dest_type) {
/* 454 */     if (src_type instanceof BasicType && dest_type instanceof BasicType) {
/* 455 */       byte dest = dest_type.getType();
/* 456 */       byte src = src_type.getType();
/*     */       
/* 458 */       if (dest == 11 && (src == 5 || src == 8 || src == 9))
/*     */       {
/* 460 */         src = 10;
/*     */       }
/* 462 */       String[] short_names = { "C", "F", "D", "B", "S", "I", "L" };
/*     */       
/* 464 */       String name = "org.apache.bcel.generic." + short_names[src - 5] + "2" + short_names[dest - 5];
/*     */ 
/*     */       
/* 467 */       Instruction i = null;
/*     */       try {
/* 469 */         i = (Instruction)Class.forName(name).newInstance();
/*     */       } catch (Exception e) {
/* 471 */         throw new RuntimeException("Could not find instruction: " + name);
/*     */       } 
/*     */       
/* 474 */       return i;
/* 475 */     }  if (src_type instanceof ReferenceType && dest_type instanceof ReferenceType) {
/* 476 */       if (dest_type instanceof ArrayType) {
/* 477 */         return new CHECKCAST(this.cp.addArrayClass((ArrayType)dest_type));
/*     */       }
/* 479 */       return new CHECKCAST(this.cp.addClass(((ObjectType)dest_type).getClassName()));
/*     */     } 
/*     */     
/* 482 */     throw new RuntimeException("Can not cast " + src_type + " to " + dest_type);
/*     */   }
/*     */   
/*     */   public GETFIELD createGetField(String class_name, String name, Type t) {
/* 486 */     return new GETFIELD(this.cp.addFieldref(class_name, name, t.getSignature()));
/*     */   }
/*     */   
/*     */   public GETSTATIC createGetStatic(String class_name, String name, Type t) {
/* 490 */     return new GETSTATIC(this.cp.addFieldref(class_name, name, t.getSignature()));
/*     */   }
/*     */   
/*     */   public PUTFIELD createPutField(String class_name, String name, Type t) {
/* 494 */     return new PUTFIELD(this.cp.addFieldref(class_name, name, t.getSignature()));
/*     */   }
/*     */   
/*     */   public PUTSTATIC createPutStatic(String class_name, String name, Type t) {
/* 498 */     return new PUTSTATIC(this.cp.addFieldref(class_name, name, t.getSignature()));
/*     */   }
/*     */   
/*     */   public CHECKCAST createCheckCast(ReferenceType t) {
/* 502 */     if (t instanceof ArrayType) {
/* 503 */       return new CHECKCAST(this.cp.addArrayClass((ArrayType)t));
/*     */     }
/* 505 */     return new CHECKCAST(this.cp.addClass((ObjectType)t));
/*     */   }
/*     */   
/*     */   public NEW createNew(ObjectType t) {
/* 509 */     return new NEW(this.cp.addClass(t));
/*     */   }
/*     */   
/*     */   public NEW createNew(String s) {
/* 513 */     return createNew(new ObjectType(s));
/*     */   }
/*     */ 
/*     */   
/*     */   public AllocationInstruction createNewArray(Type t, short dim) {
/*     */     ArrayType arrayType;
/* 519 */     if (dim == 1) {
/* 520 */       if (t instanceof ObjectType)
/* 521 */         return new ANEWARRAY(this.cp.addClass((ObjectType)t)); 
/* 522 */       if (t instanceof ArrayType) {
/* 523 */         return new ANEWARRAY(this.cp.addArrayClass((ArrayType)t));
/*     */       }
/* 525 */       return new NEWARRAY(((BasicType)t).getType());
/*     */     } 
/*     */ 
/*     */     
/* 529 */     if (t instanceof ArrayType) {
/* 530 */       arrayType = (ArrayType)t;
/*     */     } else {
/* 532 */       arrayType = new ArrayType(t, dim);
/*     */     } 
/* 534 */     return new MULTIANEWARRAY(this.cp.addArrayClass(arrayType), dim);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Instruction createNull(Type type) {
/* 541 */     switch (type.getType()) { case 13:
/*     */       case 14:
/* 543 */         return InstructionConstants.ACONST_NULL;
/*     */       case 4: case 5:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/* 548 */         return InstructionConstants.ICONST_0;
/* 549 */       case 6: return InstructionConstants.FCONST_0;
/* 550 */       case 7: return InstructionConstants.DCONST_0;
/* 551 */       case 11: return InstructionConstants.LCONST_0;
/* 552 */       case 12: return InstructionConstants.NOP; }
/*     */ 
/*     */     
/* 555 */     throw new RuntimeException("Invalid type: " + type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BranchInstruction createBranchInstruction(short opcode, InstructionHandle target) {
/* 563 */     switch (opcode) { case 153:
/* 564 */         return new IFEQ(target);
/* 565 */       case 154: return new IFNE(target);
/* 566 */       case 155: return new IFLT(target);
/* 567 */       case 156: return new IFGE(target);
/* 568 */       case 157: return new IFGT(target);
/* 569 */       case 158: return new IFLE(target);
/* 570 */       case 159: return new IF_ICMPEQ(target);
/* 571 */       case 160: return new IF_ICMPNE(target);
/* 572 */       case 161: return new IF_ICMPLT(target);
/* 573 */       case 162: return new IF_ICMPGE(target);
/* 574 */       case 163: return new IF_ICMPGT(target);
/* 575 */       case 164: return new IF_ICMPLE(target);
/* 576 */       case 165: return new IF_ACMPEQ(target);
/* 577 */       case 166: return new IF_ACMPNE(target);
/* 578 */       case 167: return new GOTO(target);
/* 579 */       case 168: return new JSR(target);
/* 580 */       case 198: return new IFNULL(target);
/* 581 */       case 199: return new IFNONNULL(target);
/* 582 */       case 200: return new GOTO_W(target);
/* 583 */       case 201: return new JSR_W(target); }
/*     */     
/* 585 */     throw new RuntimeException("Invalid opcode: " + opcode);
/*     */   }
/*     */   
/*     */   public void setClassGen(ClassGen c) {
/* 589 */     this.cg = c;
/* 590 */   } public ClassGen getClassGen() { return this.cg; }
/* 591 */   public void setConstantPool(ConstantPoolGen c) { this.cp = c; } public ConstantPoolGen getConstantPool() {
/* 592 */     return this.cp;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/InstructionFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */