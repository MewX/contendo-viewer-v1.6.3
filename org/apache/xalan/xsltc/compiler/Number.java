/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.apache.bcel.classfile.Field;
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.ASTORE;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CHECKCAST;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.GETFIELD;
/*     */ import org.apache.bcel.generic.GOTO;
/*     */ import org.apache.bcel.generic.IFNONNULL;
/*     */ import org.apache.bcel.generic.INVOKESPECIAL;
/*     */ import org.apache.bcel.generic.INVOKESTATIC;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.L2I;
/*     */ import org.apache.bcel.generic.LocalVariableGen;
/*     */ import org.apache.bcel.generic.NEW;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.PUTFIELD;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MatchGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.NodeCounterGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Number
/*     */   extends Instruction
/*     */   implements Closure
/*     */ {
/*     */   private static final int LEVEL_SINGLE = 0;
/*     */   private static final int LEVEL_MULTIPLE = 1;
/*     */   private static final int LEVEL_ANY = 2;
/*  60 */   private static final String[] ClassNames = new String[] { "org.apache.xalan.xsltc.dom.SingleNodeCounter", "org.apache.xalan.xsltc.dom.MultipleNodeCounter", "org.apache.xalan.xsltc.dom.AnyNodeCounter" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private static final String[] FieldNames = new String[] { "___single_node_counter", "___multiple_node_counter", "___any_node_counter" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   private Pattern _from = null;
/*  73 */   private Pattern _count = null;
/*  74 */   private Expression _value = null;
/*     */   
/*  76 */   private AttributeValueTemplate _lang = null;
/*  77 */   private AttributeValueTemplate _format = null;
/*  78 */   private AttributeValueTemplate _letterValue = null;
/*  79 */   private AttributeValueTemplate _groupingSeparator = null;
/*  80 */   private AttributeValueTemplate _groupingSize = null;
/*     */   
/*  82 */   private int _level = 0;
/*     */   
/*     */   private boolean _formatNeeded = false;
/*  85 */   private String _className = null;
/*  86 */   private ArrayList _closureVars = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inInnerClass() {
/*  95 */     return (this._className != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Closure getParentClosure() {
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInnerClassName() {
/* 110 */     return this._className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVariable(VariableRefBase variableRef) {
/* 117 */     if (this._closureVars == null) {
/* 118 */       this._closureVars = new ArrayList();
/*     */     }
/*     */ 
/*     */     
/* 122 */     if (!this._closureVars.contains(variableRef)) {
/* 123 */       this._closureVars.add(variableRef);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/* 130 */     int count = this._attributes.getLength();
/*     */     
/* 132 */     for (int i = 0; i < count; i++) {
/* 133 */       String name = this._attributes.getQName(i);
/* 134 */       String value = this._attributes.getValue(i);
/*     */       
/* 136 */       if (name.equals("value")) {
/* 137 */         this._value = parser.parseExpression(this, name, null);
/*     */       }
/* 139 */       else if (name.equals("count")) {
/* 140 */         this._count = parser.parsePattern(this, name, null);
/*     */       }
/* 142 */       else if (name.equals("from")) {
/* 143 */         this._from = parser.parsePattern(this, name, null);
/*     */       }
/* 145 */       else if (name.equals("level")) {
/* 146 */         if (value.equals("single")) {
/* 147 */           this._level = 0;
/*     */         }
/* 149 */         else if (value.equals("multiple")) {
/* 150 */           this._level = 1;
/*     */         }
/* 152 */         else if (value.equals("any")) {
/* 153 */           this._level = 2;
/*     */         }
/*     */       
/* 156 */       } else if (name.equals("format")) {
/* 157 */         this._format = new AttributeValueTemplate(value, parser, this);
/* 158 */         this._formatNeeded = true;
/*     */       }
/* 160 */       else if (name.equals("lang")) {
/* 161 */         this._lang = new AttributeValueTemplate(value, parser, this);
/* 162 */         this._formatNeeded = true;
/*     */       }
/* 164 */       else if (name.equals("letter-value")) {
/* 165 */         this._letterValue = new AttributeValueTemplate(value, parser, this);
/* 166 */         this._formatNeeded = true;
/*     */       }
/* 168 */       else if (name.equals("grouping-separator")) {
/* 169 */         this._groupingSeparator = new AttributeValueTemplate(value, parser, this);
/* 170 */         this._formatNeeded = true;
/*     */       }
/* 172 */       else if (name.equals("grouping-size")) {
/* 173 */         this._groupingSize = new AttributeValueTemplate(value, parser, this);
/* 174 */         this._formatNeeded = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 180 */     if (this._value != null) {
/* 181 */       Type tvalue = this._value.typeCheck(stable);
/* 182 */       if (!(tvalue instanceof org.apache.xalan.xsltc.compiler.util.RealType)) {
/* 183 */         this._value = new CastExpr(this._value, Type.Real);
/*     */       }
/*     */     } 
/* 186 */     if (this._count != null) {
/* 187 */       this._count.typeCheck(stable);
/*     */     }
/* 189 */     if (this._from != null) {
/* 190 */       this._from.typeCheck(stable);
/*     */     }
/* 192 */     if (this._format != null) {
/* 193 */       this._format.typeCheck(stable);
/*     */     }
/* 195 */     if (this._lang != null) {
/* 196 */       this._lang.typeCheck(stable);
/*     */     }
/* 198 */     if (this._letterValue != null) {
/* 199 */       this._letterValue.typeCheck(stable);
/*     */     }
/* 201 */     if (this._groupingSeparator != null) {
/* 202 */       this._groupingSeparator.typeCheck(stable);
/*     */     }
/* 204 */     if (this._groupingSize != null) {
/* 205 */       this._groupingSize.typeCheck(stable);
/*     */     }
/* 207 */     return Type.Void;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasValue() {
/* 214 */     return (this._value != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDefault() {
/* 222 */     return (this._from == null && this._count == null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void compileDefault(ClassGenerator classGen, MethodGenerator methodGen) {
/* 228 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 229 */     InstructionList il = methodGen.getInstructionList();
/*     */     
/* 231 */     int[] fieldIndexes = getXSLTC().getNumberFieldIndexes();
/*     */     
/* 233 */     if (fieldIndexes[this._level] == -1) {
/* 234 */       Field defaultNode = new Field(2, cpg.addUtf8(FieldNames[this._level]), cpg.addUtf8("Lorg/apache/xalan/xsltc/dom/NodeCounter;"), null, cpg.getConstantPool());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 241 */       classGen.addField(defaultNode);
/*     */ 
/*     */       
/* 244 */       fieldIndexes[this._level] = cpg.addFieldref(classGen.getClassName(), FieldNames[this._level], "Lorg/apache/xalan/xsltc/dom/NodeCounter;");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     il.append(classGen.loadTranslet());
/* 251 */     il.append((Instruction)new GETFIELD(fieldIndexes[this._level]));
/* 252 */     BranchHandle ifBlock1 = il.append((BranchInstruction)new IFNONNULL(null));
/*     */ 
/*     */     
/* 255 */     int index = cpg.addMethodref(ClassNames[this._level], "getDefaultNodeCounter", "(Lorg/apache/xalan/xsltc/Translet;Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/apache/xalan/xsltc/dom/NodeCounter;");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     il.append(classGen.loadTranslet());
/* 262 */     il.append(methodGen.loadDOM());
/* 263 */     il.append(methodGen.loadIterator());
/* 264 */     il.append((Instruction)new INVOKESTATIC(index));
/* 265 */     il.append((Instruction)InstructionConstants.DUP);
/*     */ 
/*     */     
/* 268 */     il.append(classGen.loadTranslet());
/* 269 */     il.append((Instruction)InstructionConstants.SWAP);
/* 270 */     il.append((Instruction)new PUTFIELD(fieldIndexes[this._level]));
/* 271 */     BranchHandle ifBlock2 = il.append((BranchInstruction)new GOTO(null));
/*     */ 
/*     */     
/* 274 */     ifBlock1.setTarget(il.append(classGen.loadTranslet()));
/* 275 */     il.append((Instruction)new GETFIELD(fieldIndexes[this._level]));
/*     */     
/* 277 */     ifBlock2.setTarget(il.append(InstructionConstants.NOP));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void compileConstructor(ClassGenerator classGen) {
/* 287 */     InstructionList il = new InstructionList();
/* 288 */     ConstantPoolGen cpg = classGen.getConstantPool();
/*     */     
/* 290 */     MethodGenerator cons = new MethodGenerator(1, (Type)Type.VOID, new Type[] { Util.getJCRefType("Lorg/apache/xalan/xsltc/Translet;"), Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;") }, new String[] { "dom", "translet", "iterator" }, "<init>", this._className, il, cpg);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 304 */     il.append((Instruction)InstructionConstants.ALOAD_0);
/* 305 */     il.append((Instruction)InstructionConstants.ALOAD_1);
/* 306 */     il.append((Instruction)InstructionConstants.ALOAD_2);
/* 307 */     il.append((Instruction)new ALOAD(3));
/*     */     
/* 309 */     int index = cpg.addMethodref(ClassNames[this._level], "<init>", "(Lorg/apache/xalan/xsltc/Translet;Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 315 */     il.append((Instruction)new INVOKESPECIAL(index));
/* 316 */     il.append((Instruction)InstructionConstants.RETURN);
/*     */     
/* 318 */     cons.stripAttributes(true);
/* 319 */     cons.setMaxLocals();
/* 320 */     cons.setMaxStack();
/* 321 */     classGen.addMethod(cons.getMethod());
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
/*     */   private void compileLocals(NodeCounterGenerator nodeCounterGen, MatchGenerator matchGen, InstructionList il) {
/* 334 */     ConstantPoolGen cpg = nodeCounterGen.getConstantPool();
/*     */ 
/*     */     
/* 337 */     LocalVariableGen local = matchGen.addLocalVariable("iterator", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), null, null);
/*     */ 
/*     */     
/* 340 */     int field = cpg.addFieldref("org.apache.xalan.xsltc.dom.NodeCounter", "_iterator", "Lorg/apache/xml/dtm/DTMAxisIterator;");
/*     */     
/* 342 */     il.append((Instruction)InstructionConstants.ALOAD_0);
/* 343 */     il.append((Instruction)new GETFIELD(field));
/* 344 */     il.append((Instruction)new ASTORE(local.getIndex()));
/* 345 */     matchGen.setIteratorIndex(local.getIndex());
/*     */ 
/*     */     
/* 348 */     local = matchGen.addLocalVariable("translet", Util.getJCRefType("Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;"), null, null);
/*     */ 
/*     */     
/* 351 */     field = cpg.addFieldref("org.apache.xalan.xsltc.dom.NodeCounter", "_translet", "Lorg/apache/xalan/xsltc/Translet;");
/*     */     
/* 353 */     il.append((Instruction)InstructionConstants.ALOAD_0);
/* 354 */     il.append((Instruction)new GETFIELD(field));
/* 355 */     il.append((Instruction)new CHECKCAST(cpg.addClass("org.apache.xalan.xsltc.runtime.AbstractTranslet")));
/* 356 */     il.append((Instruction)new ASTORE(local.getIndex()));
/* 357 */     nodeCounterGen.setTransletIndex(local.getIndex());
/*     */ 
/*     */     
/* 360 */     local = matchGen.addLocalVariable("document", Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), null, null);
/*     */ 
/*     */     
/* 363 */     field = cpg.addFieldref(this._className, "_document", "Lorg/apache/xalan/xsltc/DOM;");
/* 364 */     il.append((Instruction)InstructionConstants.ALOAD_0);
/* 365 */     il.append((Instruction)new GETFIELD(field));
/*     */     
/* 367 */     il.append((Instruction)new ASTORE(local.getIndex()));
/* 368 */     matchGen.setDomIndex(local.getIndex());
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
/*     */   private void compilePatterns(ClassGenerator classGen, MethodGenerator methodGen) {
/* 380 */     this._className = getXSLTC().getHelperClassName();
/* 381 */     NodeCounterGenerator nodeCounterGen = new NodeCounterGenerator(this._className, ClassNames[this._level], toString(), 33, null, classGen.getStylesheet());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 387 */     InstructionList il = null;
/* 388 */     ConstantPoolGen cpg = nodeCounterGen.getConstantPool();
/*     */ 
/*     */     
/* 391 */     int closureLen = (this._closureVars == null) ? 0 : this._closureVars.size();
/*     */ 
/*     */     
/* 394 */     for (int i = 0; i < closureLen; i++) {
/* 395 */       VariableBase var = ((VariableRefBase)this._closureVars.get(i)).getVariable();
/*     */ 
/*     */       
/* 398 */       nodeCounterGen.addField(new Field(1, cpg.addUtf8(var.getEscapedName()), cpg.addUtf8(var.getType().toSignature()), null, cpg.getConstantPool()));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 405 */     compileConstructor((ClassGenerator)nodeCounterGen);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 410 */     if (this._from != null) {
/* 411 */       il = new InstructionList();
/* 412 */       MatchGenerator matchGen = new MatchGenerator(17, (Type)Type.BOOLEAN, new Type[] { (Type)Type.INT }, new String[] { "node" }, "matchesFrom", this._className, il, cpg);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 423 */       compileLocals(nodeCounterGen, matchGen, il);
/*     */ 
/*     */       
/* 426 */       il.append(matchGen.loadContextNode());
/* 427 */       this._from.translate((ClassGenerator)nodeCounterGen, (MethodGenerator)matchGen);
/* 428 */       this._from.synthesize((ClassGenerator)nodeCounterGen, (MethodGenerator)matchGen);
/* 429 */       il.append((Instruction)InstructionConstants.IRETURN);
/*     */       
/* 431 */       matchGen.stripAttributes(true);
/* 432 */       matchGen.setMaxLocals();
/* 433 */       matchGen.setMaxStack();
/* 434 */       matchGen.removeNOPs();
/* 435 */       nodeCounterGen.addMethod(matchGen.getMethod());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 441 */     if (this._count != null) {
/* 442 */       il = new InstructionList();
/* 443 */       MatchGenerator matchGenerator = new MatchGenerator(17, (Type)Type.BOOLEAN, new Type[] { (Type)Type.INT }, new String[] { "node" }, "matchesCount", this._className, il, cpg);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 453 */       compileLocals(nodeCounterGen, matchGenerator, il);
/*     */ 
/*     */       
/* 456 */       il.append(matchGenerator.loadContextNode());
/* 457 */       this._count.translate((ClassGenerator)nodeCounterGen, (MethodGenerator)matchGenerator);
/* 458 */       this._count.synthesize((ClassGenerator)nodeCounterGen, (MethodGenerator)matchGenerator);
/*     */       
/* 460 */       il.append((Instruction)InstructionConstants.IRETURN);
/*     */       
/* 462 */       matchGenerator.stripAttributes(true);
/* 463 */       matchGenerator.setMaxLocals();
/* 464 */       matchGenerator.setMaxStack();
/* 465 */       matchGenerator.removeNOPs();
/* 466 */       nodeCounterGen.addMethod(matchGenerator.getMethod());
/*     */     } 
/*     */     
/* 469 */     getXSLTC().dumpClass(nodeCounterGen.getJavaClass());
/*     */ 
/*     */     
/* 472 */     cpg = classGen.getConstantPool();
/* 473 */     il = methodGen.getInstructionList();
/*     */     
/* 475 */     int index = cpg.addMethodref(this._className, "<init>", "(Lorg/apache/xalan/xsltc/Translet;Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;)V");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 480 */     il.append((Instruction)new NEW(cpg.addClass(this._className)));
/* 481 */     il.append((Instruction)InstructionConstants.DUP);
/* 482 */     il.append(classGen.loadTranslet());
/* 483 */     il.append(methodGen.loadDOM());
/* 484 */     il.append(methodGen.loadIterator());
/* 485 */     il.append((Instruction)new INVOKESPECIAL(index));
/*     */ 
/*     */     
/* 488 */     for (int j = 0; j < closureLen; j++) {
/* 489 */       VariableRefBase varRef = this._closureVars.get(j);
/* 490 */       VariableBase var = varRef.getVariable();
/* 491 */       Type varType = var.getType();
/*     */ 
/*     */       
/* 494 */       il.append((Instruction)InstructionConstants.DUP);
/* 495 */       il.append(var.loadInstruction());
/* 496 */       il.append((Instruction)new PUTFIELD(cpg.addFieldref(this._className, var.getEscapedName(), varType.toSignature())));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
/* 504 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 505 */     InstructionList il = methodGen.getInstructionList();
/*     */ 
/*     */     
/* 508 */     il.append(classGen.loadTranslet());
/*     */     
/* 510 */     if (hasValue()) {
/* 511 */       compileDefault(classGen, methodGen);
/* 512 */       this._value.translate(classGen, methodGen);
/*     */ 
/*     */       
/* 515 */       int index = cpg.addMethodref("java.lang.Math", "round", "(D)J");
/* 516 */       il.append((Instruction)new INVOKESTATIC(index));
/* 517 */       il.append((Instruction)new L2I());
/*     */ 
/*     */       
/* 520 */       index = cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeCounter", "setValue", "(I)Lorg/apache/xalan/xsltc/dom/NodeCounter;");
/*     */ 
/*     */       
/* 523 */       il.append((Instruction)new INVOKEVIRTUAL(index));
/*     */     }
/* 525 */     else if (isDefault()) {
/* 526 */       compileDefault(classGen, methodGen);
/*     */     } else {
/*     */       
/* 529 */       compilePatterns(classGen, methodGen);
/*     */     } 
/*     */ 
/*     */     
/* 533 */     if (!hasValue()) {
/* 534 */       il.append(methodGen.loadContextNode());
/* 535 */       int j = cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeCounter", "setStartNode", "(I)Lorg/apache/xalan/xsltc/dom/NodeCounter;");
/*     */ 
/*     */       
/* 538 */       il.append((Instruction)new INVOKEVIRTUAL(j));
/*     */     } 
/*     */ 
/*     */     
/* 542 */     if (this._formatNeeded) {
/* 543 */       if (this._format != null) {
/* 544 */         this._format.translate(classGen, methodGen);
/*     */       } else {
/*     */         
/* 547 */         il.append((CompoundInstruction)new PUSH(cpg, "1"));
/*     */       } 
/*     */       
/* 550 */       if (this._lang != null) {
/* 551 */         this._lang.translate(classGen, methodGen);
/*     */       } else {
/*     */         
/* 554 */         il.append((CompoundInstruction)new PUSH(cpg, "en"));
/*     */       } 
/*     */       
/* 557 */       if (this._letterValue != null) {
/* 558 */         this._letterValue.translate(classGen, methodGen);
/*     */       } else {
/*     */         
/* 561 */         il.append((CompoundInstruction)new PUSH(cpg, ""));
/*     */       } 
/*     */       
/* 564 */       if (this._groupingSeparator != null) {
/* 565 */         this._groupingSeparator.translate(classGen, methodGen);
/*     */       } else {
/*     */         
/* 568 */         il.append((CompoundInstruction)new PUSH(cpg, ""));
/*     */       } 
/*     */       
/* 571 */       if (this._groupingSize != null) {
/* 572 */         this._groupingSize.translate(classGen, methodGen);
/*     */       } else {
/*     */         
/* 575 */         il.append((CompoundInstruction)new PUSH(cpg, "0"));
/*     */       } 
/*     */       
/* 578 */       int j = cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeCounter", "getCounter", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
/*     */ 
/*     */ 
/*     */       
/* 582 */       il.append((Instruction)new INVOKEVIRTUAL(j));
/*     */     } else {
/*     */       
/* 585 */       int j = cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeCounter", "setDefaultFormatting", "()Lorg/apache/xalan/xsltc/dom/NodeCounter;");
/*     */       
/* 587 */       il.append((Instruction)new INVOKEVIRTUAL(j));
/*     */       
/* 589 */       j = cpg.addMethodref("org.apache.xalan.xsltc.dom.NodeCounter", "getCounter", "()Ljava/lang/String;");
/*     */       
/* 591 */       il.append((Instruction)new INVOKEVIRTUAL(j));
/*     */     } 
/*     */ 
/*     */     
/* 595 */     il.append(methodGen.loadHandler());
/* 596 */     int i = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "characters", "(Ljava/lang/String;Lorg/apache/xml/serializer/SerializationHandler;)V");
/*     */ 
/*     */     
/* 599 */     il.append((Instruction)new INVOKEVIRTUAL(i));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Number.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */