/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Stack;
/*     */ import org.apache.bcel.classfile.Attribute;
/*     */ import org.apache.bcel.classfile.Code;
/*     */ import org.apache.bcel.classfile.CodeException;
/*     */ import org.apache.bcel.classfile.ExceptionTable;
/*     */ import org.apache.bcel.classfile.LineNumber;
/*     */ import org.apache.bcel.classfile.LineNumberTable;
/*     */ import org.apache.bcel.classfile.LocalVariable;
/*     */ import org.apache.bcel.classfile.LocalVariableTable;
/*     */ import org.apache.bcel.classfile.Method;
/*     */ import org.apache.bcel.classfile.Utility;
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
/*     */ public class MethodGen
/*     */   extends FieldGenOrMethodGen
/*     */ {
/*     */   private String class_name;
/*     */   private Type[] arg_types;
/*     */   private String[] arg_names;
/*     */   private int max_locals;
/*     */   private int max_stack;
/*     */   private InstructionList il;
/*     */   private boolean strip_attributes;
/*  86 */   private ArrayList variable_vec = new ArrayList();
/*  87 */   private ArrayList line_number_vec = new ArrayList();
/*  88 */   private ArrayList exception_vec = new ArrayList();
/*  89 */   private ArrayList throws_vec = new ArrayList();
/*  90 */   private ArrayList code_attrs_vec = new ArrayList();
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
/*     */   private ArrayList observers;
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
/*     */   public MethodGen(int access_flags, Type return_type, Type[] arg_types, String[] arg_names, String method_name, String class_name, InstructionList il, ConstantPoolGen cp) {
/* 117 */     setAccessFlags(access_flags);
/* 118 */     setType(return_type);
/* 119 */     setArgumentTypes(arg_types);
/* 120 */     setArgumentNames(arg_names);
/* 121 */     setName(method_name);
/* 122 */     setClassName(class_name);
/* 123 */     setInstructionList(il);
/* 124 */     setConstantPool(cp);
/*     */     
/* 126 */     if ((access_flags & 0x500) == 0) {
/* 127 */       InstructionHandle start = il.getStart();
/* 128 */       InstructionHandle end = il.getEnd();
/*     */ 
/*     */ 
/*     */       
/* 132 */       if (!isStatic() && class_name != null) {
/* 133 */         addLocalVariable("this", new ObjectType(class_name), start, end);
/*     */       }
/* 135 */       if (arg_types != null) {
/* 136 */         int size = arg_types.length;
/*     */         
/* 138 */         if (arg_names != null) {
/* 139 */           if (size != arg_names.length) {
/* 140 */             throw new ClassGenException("Mismatch in argument array lengths: " + size + " vs. " + arg_names.length);
/*     */           }
/*     */         } else {
/* 143 */           arg_names = new String[size];
/*     */           
/* 145 */           for (int j = 0; j < size; j++) {
/* 146 */             arg_names[j] = "arg" + j;
/*     */           }
/* 148 */           setArgumentNames(arg_names);
/*     */         } 
/*     */         
/* 151 */         for (int i = 0; i < size; i++) {
/* 152 */           addLocalVariable(arg_names[i], arg_types[i], start, end);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodGen(Method m, String class_name, ConstantPoolGen cp) {
/* 165 */     this(m.getAccessFlags(), Type.getReturnType(m.getSignature()), Type.getArgumentTypes(m.getSignature()), null, m.getName(), class_name, ((m.getAccessFlags() & 0x500) == 0) ? new InstructionList(m.getCode().getCode()) : null, cp);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     Attribute[] attributes = m.getAttributes();
/* 173 */     for (int i = 0; i < attributes.length; i++) {
/* 174 */       Attribute a = attributes[i];
/*     */       
/* 176 */       if (a instanceof Code) {
/* 177 */         Code c = (Code)a;
/* 178 */         setMaxStack(c.getMaxStack());
/* 179 */         setMaxLocals(c.getMaxLocals());
/*     */         
/* 181 */         CodeException[] ces = c.getExceptionTable();
/*     */         
/* 183 */         if (ces != null) {
/* 184 */           for (int k = 0; k < ces.length; k++) {
/* 185 */             InstructionHandle instructionHandle; CodeException ce = ces[k];
/* 186 */             int type = ce.getCatchType();
/* 187 */             ObjectType c_type = null;
/*     */             
/* 189 */             if (type > 0) {
/* 190 */               String cen = m.getConstantPool().getConstantString(type, (byte)7);
/* 191 */               c_type = new ObjectType(cen);
/*     */             } 
/*     */             
/* 194 */             int end_pc = ce.getEndPC();
/* 195 */             int length = (m.getCode().getCode()).length;
/*     */ 
/*     */ 
/*     */             
/* 199 */             if (length == end_pc) {
/* 200 */               instructionHandle = this.il.getEnd();
/*     */             } else {
/* 202 */               instructionHandle = this.il.findHandle(end_pc);
/* 203 */               instructionHandle = instructionHandle.getPrev();
/*     */             } 
/*     */             
/* 206 */             addExceptionHandler(this.il.findHandle(ce.getStartPC()), instructionHandle, this.il.findHandle(ce.getHandlerPC()), c_type);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 211 */         Attribute[] c_attributes = c.getAttributes();
/* 212 */         for (int j = 0; j < c_attributes.length; j++) {
/* 213 */           a = c_attributes[j];
/*     */           
/* 215 */           if (a instanceof LineNumberTable)
/* 216 */           { LineNumber[] ln = ((LineNumberTable)a).getLineNumberTable();
/* 217 */             for (int k = 0; k < ln.length; k++) {
/* 218 */               LineNumber l = ln[k];
/* 219 */               addLineNumber(this.il.findHandle(l.getStartPC()), l.getLineNumber());
/*     */             }  }
/* 221 */           else if (a instanceof LocalVariableTable)
/* 222 */           { LocalVariable[] lv = ((LocalVariableTable)a).getLocalVariableTable();
/* 223 */             for (int k = 0; k < lv.length; k++) {
/* 224 */               LocalVariable l = lv[k];
/* 225 */               InstructionHandle start = this.il.findHandle(l.getStartPC());
/* 226 */               InstructionHandle end = this.il.findHandle(l.getStartPC() + l.getLength());
/*     */ 
/*     */               
/* 229 */               if (start == null)
/* 230 */                 start = this.il.getStart(); 
/* 231 */               if (end == null) {
/* 232 */                 end = this.il.getEnd();
/*     */               }
/* 234 */               addLocalVariable(l.getName(), Type.getType(l.getSignature()), l.getIndex(), start, end);
/*     */             }  }
/*     */           else
/*     */           
/* 238 */           { addCodeAttribute(a); } 
/*     */         } 
/* 240 */       } else if (a instanceof ExceptionTable) {
/* 241 */         String[] names = ((ExceptionTable)a).getExceptionNames();
/* 242 */         for (int j = 0; j < names.length; j++)
/* 243 */           addException(names[j]); 
/*     */       } else {
/* 245 */         addAttribute(a);
/*     */       } 
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
/*     */ 
/*     */   
/*     */   public LocalVariableGen addLocalVariable(String name, Type type, int slot, InstructionHandle start, InstructionHandle end) {
/* 264 */     byte t = type.getType();
/* 265 */     int add = type.getSize();
/*     */     
/* 267 */     if (slot + add > this.max_locals) {
/* 268 */       this.max_locals = slot + add;
/*     */     }
/* 270 */     LocalVariableGen l = new LocalVariableGen(slot, name, type, start, end);
/*     */     
/*     */     int i;
/* 273 */     if ((i = this.variable_vec.indexOf(l)) >= 0) {
/* 274 */       this.variable_vec.set(i, l);
/*     */     } else {
/* 276 */       this.variable_vec.add(l);
/* 277 */     }  return l;
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
/*     */ 
/*     */   
/*     */   public LocalVariableGen addLocalVariable(String name, Type type, InstructionHandle start, InstructionHandle end) {
/* 295 */     return addLocalVariable(name, type, this.max_locals, start, end);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeLocalVariable(LocalVariableGen l) {
/* 303 */     this.variable_vec.remove(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeLocalVariables() {
/* 310 */     this.variable_vec.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final void sort(LocalVariableGen[] vars, int l, int r) {
/* 317 */     int i = l, j = r;
/* 318 */     int m = vars[(l + r) / 2].getIndex();
/*     */     
/*     */     while (true) {
/*     */       while (true) {
/* 322 */         if (vars[i].getIndex() >= m)
/* 323 */         { for (; m < vars[j].getIndex(); j--);
/*     */           
/* 325 */           if (i <= j) {
/* 326 */             LocalVariableGen h = vars[i]; vars[i] = vars[j]; vars[j] = h;
/* 327 */             i++; j--;
/*     */           } 
/* 329 */           if (i > j)
/*     */             break;  continue; }  i++;
/* 331 */       }  if (l < j) sort(vars, l, j); 
/* 332 */       if (i < r) sort(vars, i, r);
/*     */       
/*     */       return;
/*     */     } 
/*     */     i++;
/*     */     continue;
/*     */   }
/*     */ 
/*     */   
/*     */   public LocalVariableGen[] getLocalVariables() {
/* 342 */     int size = this.variable_vec.size();
/* 343 */     LocalVariableGen[] lg = new LocalVariableGen[size];
/* 344 */     this.variable_vec.toArray((Object[])lg);
/*     */     
/* 346 */     for (int i = 0; i < size; i++) {
/* 347 */       if (lg[i].getStart() == null) {
/* 348 */         lg[i].setStart(this.il.getStart());
/*     */       }
/* 350 */       if (lg[i].getEnd() == null) {
/* 351 */         lg[i].setEnd(this.il.getEnd());
/*     */       }
/*     */     } 
/* 354 */     if (size > 1) {
/* 355 */       sort(lg, 0, size - 1);
/*     */     }
/* 357 */     return lg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalVariableTable getLocalVariableTable(ConstantPoolGen cp) {
/* 364 */     LocalVariableGen[] lg = getLocalVariables();
/* 365 */     int size = lg.length;
/* 366 */     LocalVariable[] lv = new LocalVariable[size];
/*     */     
/* 368 */     for (int i = 0; i < size; i++) {
/* 369 */       lv[i] = lg[i].getLocalVariable(cp);
/*     */     }
/* 371 */     return new LocalVariableTable(cp.addUtf8("LocalVariableTable"), 2 + lv.length * 10, lv, cp.getConstantPool());
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
/*     */   public LineNumberGen addLineNumber(InstructionHandle ih, int src_line) {
/* 383 */     LineNumberGen l = new LineNumberGen(ih, src_line);
/* 384 */     this.line_number_vec.add(l);
/* 385 */     return l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeLineNumber(LineNumberGen l) {
/* 392 */     this.line_number_vec.remove(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeLineNumbers() {
/* 399 */     this.line_number_vec.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LineNumberGen[] getLineNumbers() {
/* 406 */     LineNumberGen[] lg = new LineNumberGen[this.line_number_vec.size()];
/* 407 */     this.line_number_vec.toArray((Object[])lg);
/* 408 */     return lg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LineNumberTable getLineNumberTable(ConstantPoolGen cp) {
/* 415 */     int size = this.line_number_vec.size();
/* 416 */     LineNumber[] ln = new LineNumber[size];
/*     */     
/*     */     try {
/* 419 */       for (int i = 0; i < size; i++)
/* 420 */         ln[i] = ((LineNumberGen)this.line_number_vec.get(i)).getLineNumber(); 
/* 421 */     } catch (ArrayIndexOutOfBoundsException e) {}
/*     */     
/* 423 */     return new LineNumberTable(cp.addUtf8("LineNumberTable"), 2 + ln.length * 4, ln, cp.getConstantPool());
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
/*     */ 
/*     */ 
/*     */   
/*     */   public CodeExceptionGen addExceptionHandler(InstructionHandle start_pc, InstructionHandle end_pc, InstructionHandle handler_pc, ObjectType catch_type) {
/* 442 */     if (start_pc == null || end_pc == null || handler_pc == null) {
/* 443 */       throw new ClassGenException("Exception handler target is null instruction");
/*     */     }
/* 445 */     CodeExceptionGen c = new CodeExceptionGen(start_pc, end_pc, handler_pc, catch_type);
/*     */     
/* 447 */     this.exception_vec.add(c);
/* 448 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeExceptionHandler(CodeExceptionGen c) {
/* 455 */     this.exception_vec.remove(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeExceptionHandlers() {
/* 462 */     this.exception_vec.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CodeExceptionGen[] getExceptionHandlers() {
/* 469 */     CodeExceptionGen[] cg = new CodeExceptionGen[this.exception_vec.size()];
/* 470 */     this.exception_vec.toArray((Object[])cg);
/* 471 */     return cg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CodeException[] getCodeExceptions() {
/* 478 */     int size = this.exception_vec.size();
/* 479 */     CodeException[] c_exc = new CodeException[size];
/*     */     
/*     */     try {
/* 482 */       for (int i = 0; i < size; i++) {
/* 483 */         CodeExceptionGen c = this.exception_vec.get(i);
/* 484 */         c_exc[i] = c.getCodeException(this.cp);
/*     */       } 
/* 486 */     } catch (ArrayIndexOutOfBoundsException e) {}
/*     */     
/* 488 */     return c_exc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addException(String class_name) {
/* 497 */     this.throws_vec.add(class_name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeException(String c) {
/* 504 */     this.throws_vec.remove(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeExceptions() {
/* 511 */     this.throws_vec.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getExceptions() {
/* 518 */     String[] e = new String[this.throws_vec.size()];
/* 519 */     this.throws_vec.toArray((Object[])e);
/* 520 */     return e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ExceptionTable getExceptionTable(ConstantPoolGen cp) {
/* 527 */     int size = this.throws_vec.size();
/* 528 */     int[] ex = new int[size];
/*     */     
/*     */     try {
/* 531 */       for (int i = 0; i < size; i++)
/* 532 */         ex[i] = cp.addClass((String)this.throws_vec.get(i)); 
/* 533 */     } catch (ArrayIndexOutOfBoundsException e) {}
/*     */     
/* 535 */     return new ExceptionTable(cp.addUtf8("Exceptions"), 2 + 2 * size, ex, cp.getConstantPool());
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
/*     */   public void addCodeAttribute(Attribute a) {
/* 548 */     this.code_attrs_vec.add(a);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeCodeAttribute(Attribute a) {
/* 553 */     this.code_attrs_vec.remove(a);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeCodeAttributes() {
/* 559 */     this.code_attrs_vec.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute[] getCodeAttributes() {
/* 566 */     Attribute[] attributes = new Attribute[this.code_attrs_vec.size()];
/* 567 */     this.code_attrs_vec.toArray((Object[])attributes);
/* 568 */     return attributes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Method getMethod() {
/* 578 */     String signature = getSignature();
/* 579 */     int name_index = this.cp.addUtf8(this.name);
/* 580 */     int signature_index = this.cp.addUtf8(signature);
/*     */ 
/*     */ 
/*     */     
/* 584 */     byte[] byte_code = null;
/*     */     
/* 586 */     if (this.il != null) {
/* 587 */       byte_code = this.il.getByteCode();
/*     */     }
/*     */     
/* 590 */     LineNumberTable lnt = null;
/* 591 */     LocalVariableTable lvt = null;
/*     */ 
/*     */ 
/*     */     
/* 595 */     if (this.variable_vec.size() > 0 && !this.strip_attributes) {
/* 596 */       addCodeAttribute((Attribute)(lvt = getLocalVariableTable(this.cp)));
/*     */     }
/* 598 */     if (this.line_number_vec.size() > 0 && !this.strip_attributes) {
/* 599 */       addCodeAttribute((Attribute)(lnt = getLineNumberTable(this.cp)));
/*     */     }
/* 601 */     Attribute[] code_attrs = getCodeAttributes();
/*     */ 
/*     */ 
/*     */     
/* 605 */     int attrs_len = 0;
/* 606 */     for (int i = 0; i < code_attrs.length; i++) {
/* 607 */       attrs_len += code_attrs[i].getLength() + 6;
/*     */     }
/* 609 */     CodeException[] c_exc = getCodeExceptions();
/* 610 */     int exc_len = c_exc.length * 8;
/*     */     
/* 612 */     Code code = null;
/* 613 */     if (this.il != null && !isAbstract()) {
/* 614 */       code = new Code(this.cp.addUtf8("Code"), 8 + byte_code.length + 2 + exc_len + 2 + attrs_len, this.max_stack, this.max_locals, byte_code, c_exc, code_attrs, this.cp.getConstantPool());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 623 */       addAttribute((Attribute)code);
/*     */     } 
/*     */     
/* 626 */     ExceptionTable et = null;
/*     */     
/* 628 */     if (this.throws_vec.size() > 0) {
/* 629 */       addAttribute((Attribute)(et = getExceptionTable(this.cp)));
/*     */     }
/* 631 */     Method m = new Method(this.access_flags, name_index, signature_index, getAttributes(), this.cp.getConstantPool());
/*     */ 
/*     */ 
/*     */     
/* 635 */     if (lvt != null) removeCodeAttribute((Attribute)lvt); 
/* 636 */     if (lnt != null) removeCodeAttribute((Attribute)lnt); 
/* 637 */     if (code != null) removeAttribute((Attribute)code); 
/* 638 */     if (et != null) removeAttribute((Attribute)et);
/*     */     
/* 640 */     return m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeNOPs() {
/* 649 */     if (this.il != null)
/*     */     {
/*     */ 
/*     */       
/* 653 */       for (InstructionHandle ih = this.il.getStart(); ih != null; ih = next) {
/* 654 */         InstructionHandle next = ih.next;
/*     */         
/* 656 */         if (next != null && ih.getInstruction() instanceof NOP) {
/*     */           try {
/* 658 */             this.il.delete(ih);
/*     */           } catch (TargetLostException e) {
/* 660 */             InstructionHandle[] targets = e.getTargets();
/*     */             
/* 662 */             for (int i = 0; i < targets.length; i++) {
/* 663 */               InstructionTargeter[] targeters = targets[i].getTargeters();
/*     */               
/* 665 */               for (int j = 0; j < targeters.length; j++) {
/* 666 */                 targeters[j].updateTarget(targets[i], next);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxLocals(int m) {
/* 677 */     this.max_locals = m; } public int getMaxLocals() {
/* 678 */     return this.max_locals;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxStack(int m) {
/* 683 */     this.max_stack = m; } public int getMaxStack() {
/* 684 */     return this.max_stack;
/*     */   }
/*     */   
/*     */   public String getClassName() {
/* 688 */     return this.class_name; } public void setClassName(String class_name) {
/* 689 */     this.class_name = class_name;
/*     */   }
/* 691 */   public void setReturnType(Type return_type) { setType(return_type); } public Type getReturnType() {
/* 692 */     return getType();
/*     */   }
/* 694 */   public void setArgumentTypes(Type[] arg_types) { this.arg_types = arg_types; }
/* 695 */   public Type[] getArgumentTypes() { return (Type[])this.arg_types.clone(); }
/* 696 */   public void setArgumentType(int i, Type type) { this.arg_types[i] = type; } public Type getArgumentType(int i) {
/* 697 */     return this.arg_types[i];
/*     */   }
/* 699 */   public void setArgumentNames(String[] arg_names) { this.arg_names = arg_names; }
/* 700 */   public String[] getArgumentNames() { return (String[])this.arg_names.clone(); }
/* 701 */   public void setArgumentName(int i, String name) { this.arg_names[i] = name; } public String getArgumentName(int i) {
/* 702 */     return this.arg_names[i];
/*     */   }
/* 704 */   public InstructionList getInstructionList() { return this.il; } public void setInstructionList(InstructionList il) {
/* 705 */     this.il = il;
/*     */   }
/*     */   public String getSignature() {
/* 708 */     return Type.getMethodSignature(this.type, this.arg_types);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxStack() {
/* 716 */     if (this.il != null) {
/* 717 */       this.max_stack = getMaxStack(this.cp, this.il, getExceptionHandlers());
/*     */     } else {
/* 719 */       this.max_stack = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxLocals() {
/* 726 */     if (this.il != null) {
/* 727 */       int max = isStatic() ? 0 : 1;
/*     */       
/* 729 */       if (this.arg_types != null)
/* 730 */         for (int i = 0; i < this.arg_types.length; i++) {
/* 731 */           max += this.arg_types[i].getSize();
/*     */         } 
/* 733 */       for (InstructionHandle ih = this.il.getStart(); ih != null; ih = ih.getNext()) {
/* 734 */         Instruction ins = ih.getInstruction();
/*     */         
/* 736 */         if (ins instanceof LocalVariableInstruction || ins instanceof RET || ins instanceof IINC) {
/*     */ 
/*     */           
/* 739 */           int index = ((IndexedInstruction)ins).getIndex() + ((TypedInstruction)ins).getType(this.cp).getSize();
/*     */ 
/*     */           
/* 742 */           if (index > max) {
/* 743 */             max = index;
/*     */           }
/*     */         } 
/*     */       } 
/* 747 */       this.max_locals = max;
/*     */     } else {
/* 749 */       this.max_locals = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void stripAttributes(boolean flag) {
/* 755 */     this.strip_attributes = flag;
/*     */   }
/*     */   
/*     */   static final class BranchTarget { InstructionHandle target;
/*     */     int stackDepth;
/*     */     
/*     */     BranchTarget(InstructionHandle target, int stackDepth) {
/* 762 */       this.target = target;
/* 763 */       this.stackDepth = stackDepth;
/*     */     } }
/*     */ 
/*     */   
/*     */   static final class BranchStack {
/* 768 */     Stack branchTargets = new Stack();
/* 769 */     Hashtable visitedTargets = new Hashtable();
/*     */     
/*     */     public void push(InstructionHandle target, int stackDepth) {
/* 772 */       if (visited(target)) {
/*     */         return;
/*     */       }
/* 775 */       this.branchTargets.push(visit(target, stackDepth));
/*     */     }
/*     */     
/*     */     public MethodGen.BranchTarget pop() {
/* 779 */       if (!this.branchTargets.empty()) {
/* 780 */         MethodGen.BranchTarget bt = this.branchTargets.pop();
/* 781 */         return bt;
/*     */       } 
/*     */       
/* 784 */       return null;
/*     */     }
/*     */     
/*     */     private final MethodGen.BranchTarget visit(InstructionHandle target, int stackDepth) {
/* 788 */       MethodGen.BranchTarget bt = new MethodGen.BranchTarget(target, stackDepth);
/* 789 */       this.visitedTargets.put(target, bt);
/*     */       
/* 791 */       return bt;
/*     */     }
/*     */     
/*     */     private final boolean visited(InstructionHandle target) {
/* 795 */       return (this.visitedTargets.get(target) != null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getMaxStack(ConstantPoolGen cp, InstructionList il, CodeExceptionGen[] et) {
/* 805 */     BranchStack branchTargets = new BranchStack();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 812 */     for (int i = 0; i < et.length; i++) {
/* 813 */       InstructionHandle handler_pc = et[i].getHandlerPC();
/* 814 */       if (handler_pc != null) {
/* 815 */         branchTargets.push(handler_pc, 1);
/*     */       }
/*     */     } 
/* 818 */     int stackDepth = 0, maxStackDepth = 0;
/* 819 */     InstructionHandle ih = il.getStart();
/*     */     
/* 821 */     while (ih != null) {
/* 822 */       Instruction instruction = ih.getInstruction();
/* 823 */       short opcode = instruction.getOpcode();
/* 824 */       int delta = instruction.produceStack(cp) - instruction.consumeStack(cp);
/*     */       
/* 826 */       stackDepth += delta;
/* 827 */       if (stackDepth > maxStackDepth) {
/* 828 */         maxStackDepth = stackDepth;
/*     */       }
/*     */       
/* 831 */       if (instruction instanceof BranchInstruction) {
/* 832 */         BranchInstruction branch = (BranchInstruction)instruction;
/* 833 */         if (instruction instanceof Select) {
/*     */           
/* 835 */           Select select = (Select)branch;
/* 836 */           InstructionHandle[] targets = select.getTargets();
/* 837 */           for (int j = 0; j < targets.length; j++) {
/* 838 */             branchTargets.push(targets[j], stackDepth);
/*     */           }
/* 840 */           ih = null;
/* 841 */         } else if (!(branch instanceof IfInstruction)) {
/*     */ 
/*     */           
/* 844 */           if (opcode == 168 || opcode == 201)
/* 845 */             branchTargets.push(ih.getNext(), stackDepth - 1); 
/* 846 */           ih = null;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 851 */         branchTargets.push(branch.getTarget(), stackDepth);
/*     */       
/*     */       }
/* 854 */       else if (opcode == 191 || opcode == 169 || (opcode >= 172 && opcode <= 177)) {
/*     */         
/* 856 */         ih = null;
/*     */       } 
/*     */       
/* 859 */       if (ih != null) {
/* 860 */         ih = ih.getNext();
/*     */       }
/* 862 */       if (ih == null) {
/* 863 */         BranchTarget bt = branchTargets.pop();
/* 864 */         if (bt != null) {
/* 865 */           ih = bt.target;
/* 866 */           stackDepth = bt.stackDepth;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 871 */     return maxStackDepth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addObserver(MethodObserver o) {
/* 879 */     if (this.observers == null) {
/* 880 */       this.observers = new ArrayList();
/*     */     }
/* 882 */     this.observers.add(o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeObserver(MethodObserver o) {
/* 888 */     if (this.observers != null) {
/* 889 */       this.observers.remove(o);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 897 */     if (this.observers != null) {
/* 898 */       for (Iterator e = this.observers.iterator(); e.hasNext();) {
/* 899 */         ((MethodObserver)e.next()).notify(this);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 909 */     String access = Utility.accessToString(this.access_flags);
/* 910 */     String signature = Type.getMethodSignature(this.type, this.arg_types);
/*     */     
/* 912 */     signature = Utility.methodSignatureToString(signature, this.name, access, true, getLocalVariableTable(this.cp));
/*     */ 
/*     */     
/* 915 */     StringBuffer buf = new StringBuffer(signature);
/*     */     
/* 917 */     if (this.throws_vec.size() > 0) {
/* 918 */       for (Iterator e = this.throws_vec.iterator(); e.hasNext();) {
/* 919 */         buf.append("\n\t\tthrows " + e.next());
/*     */       }
/*     */     }
/* 922 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodGen copy(String class_name, ConstantPoolGen cp) {
/* 928 */     Method m = ((MethodGen)clone()).getMethod();
/* 929 */     MethodGen mg = new MethodGen(m, class_name, this.cp);
/*     */     
/* 931 */     if (this.cp != cp) {
/* 932 */       mg.setConstantPool(cp);
/* 933 */       mg.getInstructionList().replaceConstantPool(this.cp, cp);
/*     */     } 
/*     */     
/* 936 */     return mg;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/MethodGen.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */