/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import org.apache.bcel.generic.ALOAD;
/*     */ import org.apache.bcel.generic.BranchHandle;
/*     */ import org.apache.bcel.generic.BranchInstruction;
/*     */ import org.apache.bcel.generic.CompoundInstruction;
/*     */ import org.apache.bcel.generic.ConstantPoolGen;
/*     */ import org.apache.bcel.generic.IF_ICMPEQ;
/*     */ import org.apache.bcel.generic.ILOAD;
/*     */ import org.apache.bcel.generic.INVOKEINTERFACE;
/*     */ import org.apache.bcel.generic.INVOKEVIRTUAL;
/*     */ import org.apache.bcel.generic.Instruction;
/*     */ import org.apache.bcel.generic.InstructionConstants;
/*     */ import org.apache.bcel.generic.InstructionHandle;
/*     */ import org.apache.bcel.generic.InstructionList;
/*     */ import org.apache.bcel.generic.PUSH;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
/*     */ import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Whitespace
/*     */   extends TopLevelElement
/*     */ {
/*     */   public static final int USE_PREDICATE = 0;
/*     */   public static final int STRIP_SPACE = 1;
/*     */   public static final int PRESERVE_SPACE = 2;
/*     */   public static final int RULE_NONE = 0;
/*     */   public static final int RULE_ELEMENT = 1;
/*     */   public static final int RULE_NAMESPACE = 2;
/*     */   public static final int RULE_ALL = 3;
/*     */   private String _elementList;
/*     */   private int _action;
/*     */   private int _importPrecedence;
/*     */   
/*     */   private static final class WhitespaceRule
/*     */   {
/*     */     private final int _action;
/*     */     private String _namespace;
/*     */     private String _element;
/*     */     private int _type;
/*     */     private int _priority;
/*     */     
/*     */     public WhitespaceRule(int action, String element, int precedence) {
/*  76 */       this._action = action;
/*     */ 
/*     */       
/*  79 */       int colon = element.indexOf(':');
/*  80 */       if (colon >= 0) {
/*  81 */         this._namespace = element.substring(0, colon);
/*  82 */         this._element = element.substring(colon + 1, element.length());
/*     */       } else {
/*     */         
/*  85 */         this._namespace = "";
/*  86 */         this._element = element;
/*     */       } 
/*     */ 
/*     */       
/*  90 */       this._priority = precedence << 2;
/*     */ 
/*     */       
/*  93 */       if (this._element.equals("*")) {
/*  94 */         if (this._namespace == "") {
/*  95 */           this._type = 3;
/*  96 */           this._priority += 2;
/*     */         } else {
/*     */           
/*  99 */           this._type = 2;
/* 100 */           this._priority++;
/*     */         } 
/*     */       } else {
/*     */         
/* 104 */         this._type = 1;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int compareTo(WhitespaceRule other) {
/* 112 */       return (this._priority < other._priority) ? -1 : ((this._priority > other._priority) ? 1 : 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getAction() {
/* 117 */       return this._action; }
/* 118 */     public int getStrength() { return this._type; }
/* 119 */     public int getPriority() { return this._priority; }
/* 120 */     public String getElement() { return this._element; } public String getNamespace() {
/* 121 */       return this._namespace;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseContents(Parser parser) {
/* 130 */     this._action = this._qname.getLocalPart().endsWith("strip-space") ? 1 : 2;
/*     */ 
/*     */ 
/*     */     
/* 134 */     this._importPrecedence = parser.getCurrentImportPrecedence();
/*     */ 
/*     */     
/* 137 */     this._elementList = getAttribute("elements");
/* 138 */     if (this._elementList == null || this._elementList.length() == 0) {
/* 139 */       reportError(this, parser, "REQUIRED_ATTR_ERR", "elements");
/*     */       
/*     */       return;
/*     */     } 
/* 143 */     SymbolTable stable = parser.getSymbolTable();
/* 144 */     StringTokenizer list = new StringTokenizer(this._elementList);
/* 145 */     StringBuffer elements = new StringBuffer("");
/*     */     
/* 147 */     while (list.hasMoreElements()) {
/* 148 */       String str1, token = list.nextToken();
/*     */ 
/*     */       
/*     */       int col;
/*     */       
/* 153 */       if ((col = token.indexOf(':')) != -1) {
/* 154 */         str1 = token.substring(0, col);
/*     */       } else {
/*     */         
/* 157 */         str1 = "";
/*     */       } 
/*     */       
/* 160 */       String namespace = lookupNamespace(str1);
/* 161 */       if (namespace != null) {
/* 162 */         elements.append(namespace + ":" + token.substring(col + 1, token.length()));
/*     */       } else {
/*     */         
/* 165 */         elements.append(token);
/*     */       } 
/* 167 */       if (list.hasMoreElements())
/* 168 */         elements.append(" "); 
/*     */     } 
/* 170 */     this._elementList = elements.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getRules() {
/* 179 */     Vector rules = new Vector();
/*     */     
/* 181 */     StringTokenizer list = new StringTokenizer(this._elementList);
/* 182 */     while (list.hasMoreElements()) {
/* 183 */       rules.add(new WhitespaceRule(this._action, list.nextToken(), this._importPrecedence));
/*     */     }
/*     */ 
/*     */     
/* 187 */     return rules;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static WhitespaceRule findContradictingRule(Vector rules, WhitespaceRule rule) {
/* 197 */     for (int i = 0; i < rules.size(); i++) {
/*     */       
/* 199 */       WhitespaceRule currentRule = rules.elementAt(i);
/*     */       
/* 201 */       if (currentRule == rule) {
/* 202 */         return null;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 210 */       switch (currentRule.getStrength()) {
/*     */         case 3:
/* 212 */           return currentRule;
/*     */         
/*     */         case 1:
/* 215 */           if (!rule.getElement().equals(currentRule.getElement())) {
/*     */             break;
/*     */           }
/*     */         
/*     */         case 2:
/* 220 */           if (rule.getNamespace().equals(currentRule.getNamespace())) {
/* 221 */             return currentRule;
/*     */           }
/*     */           break;
/*     */       } 
/*     */     } 
/* 226 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int prioritizeRules(Vector rules) {
/* 236 */     int defaultAction = 2;
/*     */ 
/*     */     
/* 239 */     quicksort(rules, 0, rules.size() - 1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     boolean strip = false;
/* 245 */     for (int i = 0; i < rules.size(); i++) {
/* 246 */       WhitespaceRule currentRule = rules.elementAt(i);
/* 247 */       if (currentRule.getAction() == 1) {
/* 248 */         strip = true;
/*     */       }
/*     */     } 
/*     */     
/* 252 */     if (!strip) {
/* 253 */       rules.removeAllElements();
/* 254 */       return 2;
/*     */     } 
/*     */ 
/*     */     
/* 258 */     for (int idx = 0; idx < rules.size(); ) {
/* 259 */       WhitespaceRule whitespaceRule = rules.elementAt(idx);
/*     */ 
/*     */       
/* 262 */       if (findContradictingRule(rules, whitespaceRule) != null) {
/* 263 */         rules.remove(idx);
/*     */         
/*     */         continue;
/*     */       } 
/* 267 */       if (whitespaceRule.getStrength() == 3) {
/* 268 */         defaultAction = whitespaceRule.getAction();
/* 269 */         for (int j = idx; j < rules.size(); j++) {
/* 270 */           rules.removeElementAt(j);
/*     */         }
/*     */       } 
/*     */       
/* 274 */       idx++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 279 */     if (rules.size() == 0) {
/* 280 */       return defaultAction;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/* 286 */       WhitespaceRule whitespaceRule = rules.lastElement();
/* 287 */       if (whitespaceRule.getAction() == defaultAction) {
/* 288 */         rules.removeElementAt(rules.size() - 1);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 293 */         if (rules.size() <= 0)
/*     */           break;  continue;
/*     */       }  break;
/* 296 */     }  return defaultAction;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void compileStripSpace(BranchHandle[] strip, int sCount, InstructionList il) {
/* 302 */     InstructionHandle target = il.append(InstructionConstants.ICONST_1);
/* 303 */     il.append((Instruction)InstructionConstants.IRETURN);
/* 304 */     for (int i = 0; i < sCount; i++) {
/* 305 */       strip[i].setTarget(target);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void compilePreserveSpace(BranchHandle[] preserve, int pCount, InstructionList il) {
/* 312 */     InstructionHandle target = il.append(InstructionConstants.ICONST_0);
/* 313 */     il.append((Instruction)InstructionConstants.IRETURN);
/* 314 */     for (int i = 0; i < pCount; i++) {
/* 315 */       preserve[i].setTarget(target);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void compilePredicate(Vector rules, int defaultAction, ClassGenerator classGen) {
/* 337 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 338 */     InstructionList il = new InstructionList();
/* 339 */     XSLTC xsltc = classGen.getParser().getXSLTC();
/*     */ 
/*     */     
/* 342 */     MethodGenerator stripSpace = new MethodGenerator(17, (Type)Type.BOOLEAN, new Type[] { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), (Type)Type.INT, (Type)Type.INT }, new String[] { "dom", "node", "type" }, "stripSpace", classGen.getClassName(), il, cpg);
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
/* 353 */     classGen.addInterface("org/apache/xalan/xsltc/StripFilter");
/*     */     
/* 355 */     int paramDom = stripSpace.getLocalIndex("dom");
/* 356 */     int paramCurrent = stripSpace.getLocalIndex("node");
/* 357 */     int paramType = stripSpace.getLocalIndex("type");
/*     */     
/* 359 */     BranchHandle[] strip = new BranchHandle[rules.size()];
/* 360 */     BranchHandle[] preserve = new BranchHandle[rules.size()];
/* 361 */     int sCount = 0;
/* 362 */     int pCount = 0;
/*     */ 
/*     */     
/* 365 */     for (int i = 0; i < rules.size(); i++) {
/*     */       
/* 367 */       WhitespaceRule rule = rules.elementAt(i);
/*     */ 
/*     */       
/* 370 */       int gns = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNamespaceName", "(I)Ljava/lang/String;");
/*     */ 
/*     */ 
/*     */       
/* 374 */       int strcmp = cpg.addMethodref("java/lang/String", "compareTo", "(Ljava/lang/String;)I");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 379 */       if (rule.getStrength() == 2) {
/* 380 */         il.append((Instruction)new ALOAD(paramDom));
/* 381 */         il.append((Instruction)new ILOAD(paramCurrent));
/* 382 */         il.append((Instruction)new INVOKEINTERFACE(gns, 2));
/* 383 */         il.append((CompoundInstruction)new PUSH(cpg, rule.getNamespace()));
/* 384 */         il.append((Instruction)new INVOKEVIRTUAL(strcmp));
/* 385 */         il.append(InstructionConstants.ICONST_0);
/*     */         
/* 387 */         if (rule.getAction() == 1) {
/* 388 */           strip[sCount++] = il.append((BranchInstruction)new IF_ICMPEQ(null));
/*     */         } else {
/*     */           
/* 391 */           preserve[pCount++] = il.append((BranchInstruction)new IF_ICMPEQ(null));
/*     */         }
/*     */       
/*     */       }
/* 395 */       else if (rule.getStrength() == 1) {
/*     */         QName qName;
/* 397 */         Parser parser = classGen.getParser();
/*     */         
/* 399 */         if (rule.getNamespace() != "") {
/* 400 */           qName = parser.getQName(rule.getNamespace(), (String)null, rule.getElement());
/*     */         } else {
/*     */           
/* 403 */           qName = parser.getQName(rule.getElement());
/*     */         } 
/*     */         
/* 406 */         int elementType = xsltc.registerElement(qName);
/* 407 */         il.append((Instruction)new ILOAD(paramType));
/* 408 */         il.append((CompoundInstruction)new PUSH(cpg, elementType));
/*     */ 
/*     */         
/* 411 */         if (rule.getAction() == 1) {
/* 412 */           strip[sCount++] = il.append((BranchInstruction)new IF_ICMPEQ(null));
/*     */         } else {
/* 414 */           preserve[pCount++] = il.append((BranchInstruction)new IF_ICMPEQ(null));
/*     */         } 
/*     */       } 
/*     */     } 
/* 418 */     if (defaultAction == 1) {
/* 419 */       compileStripSpace(strip, sCount, il);
/* 420 */       compilePreserveSpace(preserve, pCount, il);
/*     */     } else {
/*     */       
/* 423 */       compilePreserveSpace(preserve, pCount, il);
/* 424 */       compileStripSpace(strip, sCount, il);
/*     */     } 
/*     */     
/* 427 */     stripSpace.stripAttributes(true);
/* 428 */     stripSpace.setMaxLocals();
/* 429 */     stripSpace.setMaxStack();
/* 430 */     stripSpace.removeNOPs();
/*     */     
/* 432 */     classGen.addMethod(stripSpace.getMethod());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void compileDefault(int defaultAction, ClassGenerator classGen) {
/* 440 */     ConstantPoolGen cpg = classGen.getConstantPool();
/* 441 */     InstructionList il = new InstructionList();
/* 442 */     XSLTC xsltc = classGen.getParser().getXSLTC();
/*     */ 
/*     */     
/* 445 */     MethodGenerator stripSpace = new MethodGenerator(17, (Type)Type.BOOLEAN, new Type[] { Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), (Type)Type.INT, (Type)Type.INT }, new String[] { "dom", "node", "type" }, "stripSpace", classGen.getClassName(), il, cpg);
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
/* 456 */     classGen.addInterface("org/apache/xalan/xsltc/StripFilter");
/*     */     
/* 458 */     if (defaultAction == 1) {
/* 459 */       il.append(InstructionConstants.ICONST_1);
/*     */     } else {
/* 461 */       il.append(InstructionConstants.ICONST_0);
/* 462 */     }  il.append((Instruction)InstructionConstants.IRETURN);
/*     */     
/* 464 */     stripSpace.stripAttributes(true);
/* 465 */     stripSpace.setMaxLocals();
/* 466 */     stripSpace.setMaxStack();
/* 467 */     stripSpace.removeNOPs();
/*     */     
/* 469 */     classGen.addMethod(stripSpace.getMethod());
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
/*     */   public static int translateRules(Vector rules, ClassGenerator classGen) {
/* 484 */     int defaultAction = prioritizeRules(rules);
/*     */     
/* 486 */     if (rules.size() == 0) {
/* 487 */       compileDefault(defaultAction, classGen);
/* 488 */       return defaultAction;
/*     */     } 
/*     */     
/* 491 */     compilePredicate(rules, defaultAction, classGen);
/*     */     
/* 493 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void quicksort(Vector rules, int p, int r) {
/* 500 */     while (p < r) {
/* 501 */       int q = partition(rules, p, r);
/* 502 */       quicksort(rules, p, q);
/* 503 */       p = q + 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int partition(Vector rules, int p, int r) {
/* 511 */     WhitespaceRule x = rules.elementAt(p + r >>> 1);
/* 512 */     int i = p - 1, j = r + 1;
/*     */     while (true) {
/* 514 */       if (x.compareTo(rules.elementAt(--j)) >= 0) { do {
/*     */         
/* 516 */         } while (x.compareTo(rules.elementAt(++i)) > 0);
/*     */         
/* 518 */         if (i < j) {
/* 519 */           WhitespaceRule tmp = rules.elementAt(i);
/* 520 */           rules.setElementAt(rules.elementAt(j), i);
/* 521 */           rules.setElementAt(tmp, j); continue;
/*     */         }  break; }
/*     */     
/* 524 */     }  return j;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type typeCheck(SymbolTable stable) throws TypeCheckError {
/* 533 */     return Type.Void;
/*     */   }
/*     */   
/*     */   public void translate(ClassGenerator classGen, MethodGenerator methodGen) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Whitespace.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */