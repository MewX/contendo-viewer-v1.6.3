/*      */ package org.apache.bcel.verifier.statics;
/*      */ 
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import org.apache.bcel.Constants;
/*      */ import org.apache.bcel.Repository;
/*      */ import org.apache.bcel.classfile.Attribute;
/*      */ import org.apache.bcel.classfile.Code;
/*      */ import org.apache.bcel.classfile.CodeException;
/*      */ import org.apache.bcel.classfile.Constant;
/*      */ import org.apache.bcel.classfile.ConstantClass;
/*      */ import org.apache.bcel.classfile.ConstantDouble;
/*      */ import org.apache.bcel.classfile.ConstantFieldref;
/*      */ import org.apache.bcel.classfile.ConstantFloat;
/*      */ import org.apache.bcel.classfile.ConstantInteger;
/*      */ import org.apache.bcel.classfile.ConstantInterfaceMethodref;
/*      */ import org.apache.bcel.classfile.ConstantLong;
/*      */ import org.apache.bcel.classfile.ConstantMethodref;
/*      */ import org.apache.bcel.classfile.ConstantNameAndType;
/*      */ import org.apache.bcel.classfile.ConstantPool;
/*      */ import org.apache.bcel.classfile.ConstantString;
/*      */ import org.apache.bcel.classfile.ConstantUtf8;
/*      */ import org.apache.bcel.classfile.ConstantValue;
/*      */ import org.apache.bcel.classfile.Deprecated;
/*      */ import org.apache.bcel.classfile.DescendingVisitor;
/*      */ import org.apache.bcel.classfile.EmptyVisitor;
/*      */ import org.apache.bcel.classfile.ExceptionTable;
/*      */ import org.apache.bcel.classfile.Field;
/*      */ import org.apache.bcel.classfile.InnerClass;
/*      */ import org.apache.bcel.classfile.InnerClasses;
/*      */ import org.apache.bcel.classfile.JavaClass;
/*      */ import org.apache.bcel.classfile.LineNumber;
/*      */ import org.apache.bcel.classfile.LineNumberTable;
/*      */ import org.apache.bcel.classfile.LocalVariable;
/*      */ import org.apache.bcel.classfile.LocalVariableTable;
/*      */ import org.apache.bcel.classfile.Method;
/*      */ import org.apache.bcel.classfile.Node;
/*      */ import org.apache.bcel.classfile.SourceFile;
/*      */ import org.apache.bcel.classfile.Synthetic;
/*      */ import org.apache.bcel.classfile.Unknown;
/*      */ import org.apache.bcel.classfile.Visitor;
/*      */ import org.apache.bcel.generic.ArrayType;
/*      */ import org.apache.bcel.generic.ConstantPoolGen;
/*      */ import org.apache.bcel.generic.ObjectType;
/*      */ import org.apache.bcel.generic.Type;
/*      */ import org.apache.bcel.verifier.PassVerifier;
/*      */ import org.apache.bcel.verifier.VerificationResult;
/*      */ import org.apache.bcel.verifier.Verifier;
/*      */ import org.apache.bcel.verifier.VerifierFactory;
/*      */ import org.apache.bcel.verifier.exc.AssertionViolatedException;
/*      */ import org.apache.bcel.verifier.exc.ClassConstraintException;
/*      */ import org.apache.bcel.verifier.exc.LocalVariableInfoInconsistentException;
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
/*      */ public final class Pass2Verifier
/*      */   extends PassVerifier
/*      */   implements Constants
/*      */ {
/*      */   private LocalVariablesInfo[] localVariablesInfos;
/*      */   private Verifier myOwner;
/*      */   
/*      */   public Pass2Verifier(Verifier owner) {
/*   99 */     this.myOwner = owner;
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
/*      */   public LocalVariablesInfo getLocalVariablesInfo(int method_nr) {
/*  112 */     if (verify() != VerificationResult.VR_OK) return null; 
/*  113 */     if (method_nr < 0 || method_nr >= this.localVariablesInfos.length) {
/*  114 */       throw new AssertionViolatedException("Method number out of range.");
/*      */     }
/*  116 */     return this.localVariablesInfos[method_nr];
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
/*      */ 
/*      */ 
/*      */   
/*      */   public VerificationResult do_verify() {
/*  142 */     VerificationResult vr1 = this.myOwner.doPass1();
/*  143 */     if (vr1.equals(VerificationResult.VR_OK)) {
/*      */ 
/*      */ 
/*      */       
/*  147 */       this.localVariablesInfos = new LocalVariablesInfo[(Repository.lookupClass(this.myOwner.getClassName()).getMethods()).length];
/*      */       
/*  149 */       VerificationResult vr = VerificationResult.VR_OK;
/*      */       try {
/*  151 */         constant_pool_entries_satisfy_static_constraints();
/*  152 */         field_and_method_refs_are_valid();
/*  153 */         every_class_has_an_accessible_superclass();
/*  154 */         final_methods_are_not_overridden();
/*      */       } catch (ClassConstraintException cce) {
/*      */         
/*  157 */         vr = new VerificationResult(2, cce.getMessage());
/*      */       } 
/*  159 */       return vr;
/*      */     } 
/*      */     
/*  162 */     return VerificationResult.VR_NOTYET;
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
/*      */   private void every_class_has_an_accessible_superclass() {
/*  179 */     HashSet hs = new HashSet();
/*  180 */     JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
/*  181 */     int supidx = -1;
/*      */     
/*  183 */     while (supidx != 0) {
/*  184 */       supidx = jc.getSuperclassNameIndex();
/*      */       
/*  186 */       if (supidx == 0) {
/*  187 */         if (jc != Repository.lookupClass(Type.OBJECT.getClassName())) {
/*  188 */           throw new ClassConstraintException("Superclass of '" + jc.getClassName() + "' missing but not " + Type.OBJECT.getClassName() + " itself!");
/*      */         }
/*      */         continue;
/*      */       } 
/*  192 */       String supername = jc.getSuperclassName();
/*  193 */       if (!hs.add(supername)) {
/*  194 */         throw new ClassConstraintException("Circular superclass hierarchy detected.");
/*      */       }
/*  196 */       Verifier v = VerifierFactory.getVerifier(supername);
/*  197 */       VerificationResult vr = v.doPass1();
/*      */       
/*  199 */       if (vr != VerificationResult.VR_OK) {
/*  200 */         throw new ClassConstraintException("Could not load in ancestor class '" + supername + "'.");
/*      */       }
/*  202 */       jc = Repository.lookupClass(supername);
/*      */       
/*  204 */       if (jc.isFinal()) {
/*  205 */         throw new ClassConstraintException("Ancestor class '" + supername + "' has the FINAL access modifier and must therefore not be subclassed.");
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
/*      */   private void final_methods_are_not_overridden() {
/*  223 */     HashMap hashmap = new HashMap();
/*  224 */     JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
/*      */     
/*  226 */     int supidx = -1;
/*  227 */     while (supidx != 0) {
/*  228 */       supidx = jc.getSuperclassNameIndex();
/*      */       
/*  230 */       ConstantPoolGen cpg = new ConstantPoolGen(jc.getConstantPool());
/*  231 */       Method[] methods = jc.getMethods();
/*  232 */       for (int i = 0; i < methods.length; i++) {
/*  233 */         String name_and_sig = methods[i].getName() + methods[i].getSignature();
/*      */         
/*  235 */         if (hashmap.containsKey(name_and_sig)) {
/*  236 */           if (methods[i].isFinal()) {
/*  237 */             throw new ClassConstraintException("Method '" + name_and_sig + "' in class '" + hashmap.get(name_and_sig) + "' overrides the final (not-overridable) definition in class '" + jc.getClassName() + "'.");
/*      */           }
/*      */           
/*  240 */           if (!methods[i].isStatic()) {
/*  241 */             hashmap.put(name_and_sig, jc.getClassName());
/*      */           
/*      */           }
/*      */         
/*      */         }
/*  246 */         else if (!methods[i].isStatic()) {
/*  247 */           hashmap.put(name_and_sig, jc.getClassName());
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  252 */       jc = Repository.lookupClass(jc.getSuperclassName());
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
/*      */   private void constant_pool_entries_satisfy_static_constraints() {
/*  267 */     JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
/*  268 */     new CPESSC_Visitor(jc);
/*      */   }
/*      */ 
/*      */   
/*      */   private class CPESSC_Visitor
/*      */     extends EmptyVisitor
/*      */     implements Visitor
/*      */   {
/*      */     private Class CONST_Class;
/*      */     
/*      */     private Class CONST_Fieldref;
/*      */     
/*      */     private Class CONST_Methodref;
/*      */     
/*      */     private Class CONST_InterfaceMethodref;
/*      */     private Class CONST_String;
/*      */     private Class CONST_Integer;
/*      */     private Class CONST_Float;
/*      */     private Class CONST_Long;
/*      */     private Class CONST_Double;
/*      */     private Class CONST_NameAndType;
/*      */     private Class CONST_Utf8;
/*      */     private final JavaClass jc;
/*      */     private final ConstantPool cp;
/*      */     private final int cplen;
/*      */     private DescendingVisitor carrier;
/*      */     private HashSet field_names;
/*      */     private HashSet field_names_and_desc;
/*      */     private HashSet method_names_and_desc;
/*      */     private final Pass2Verifier this$0;
/*      */     
/*      */     private CPESSC_Visitor(Pass2Verifier this$0, JavaClass _jc) {
/*  300 */       Pass2Verifier.this = Pass2Verifier.this; this.field_names = new HashSet(); this.field_names_and_desc = new HashSet(); this.method_names_and_desc = new HashSet();
/*  301 */       this.jc = _jc;
/*  302 */       this.cp = _jc.getConstantPool();
/*  303 */       this.cplen = this.cp.getLength();
/*      */       
/*  305 */       this.CONST_Class = ConstantClass.class;
/*  306 */       this.CONST_Fieldref = ConstantFieldref.class;
/*  307 */       this.CONST_Methodref = ConstantMethodref.class;
/*  308 */       this.CONST_InterfaceMethodref = ConstantInterfaceMethodref.class;
/*  309 */       this.CONST_String = ConstantString.class;
/*  310 */       this.CONST_Integer = ConstantInteger.class;
/*  311 */       this.CONST_Float = ConstantFloat.class;
/*  312 */       this.CONST_Long = ConstantLong.class;
/*  313 */       this.CONST_Double = ConstantDouble.class;
/*  314 */       this.CONST_NameAndType = ConstantNameAndType.class;
/*  315 */       this.CONST_Utf8 = ConstantUtf8.class;
/*      */       
/*  317 */       this.carrier = new DescendingVisitor(_jc, this);
/*  318 */       this.carrier.visit();
/*      */     }
/*      */     
/*      */     private void checkIndex(Node referrer, int index, Class shouldbe) {
/*  322 */       if (index < 0 || index >= this.cplen) {
/*  323 */         throw new ClassConstraintException("Invalid index '" + index + "' used by '" + Pass2Verifier.tostring(referrer) + "'.");
/*      */       }
/*  325 */       Constant c = this.cp.getConstant(index);
/*  326 */       if (!shouldbe.isInstance(c)) {
/*  327 */         String isnot = shouldbe.toString().substring(shouldbe.toString().lastIndexOf(".") + 1);
/*  328 */         throw new ClassCastException("Illegal constant '" + Pass2Verifier.tostring(c) + "' at index '" + index + "'. '" + Pass2Verifier.tostring(referrer) + "' expects a '" + shouldbe + "'.");
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitJavaClass(JavaClass obj) {
/*  335 */       Attribute[] atts = obj.getAttributes();
/*  336 */       boolean foundSourceFile = false;
/*  337 */       boolean foundInnerClasses = false;
/*      */ 
/*      */ 
/*      */       
/*  341 */       boolean hasInnerClass = (new Pass2Verifier.InnerClassDetector(Pass2Verifier.this, this.jc)).innerClassReferenced();
/*      */       
/*  343 */       for (int i = 0; i < atts.length; i++) {
/*  344 */         if (!(atts[i] instanceof SourceFile) && !(atts[i] instanceof Deprecated) && !(atts[i] instanceof InnerClasses) && !(atts[i] instanceof Synthetic))
/*      */         {
/*      */ 
/*      */           
/*  348 */           Pass2Verifier.this.addMessage("Attribute '" + Pass2Verifier.tostring((Node)atts[i]) + "' as an attribute of the ClassFile structure '" + Pass2Verifier.tostring((Node)obj) + "' is unknown and will therefore be ignored.");
/*      */         }
/*      */         
/*  351 */         if (atts[i] instanceof SourceFile) {
/*  352 */           if (!foundSourceFile) { foundSourceFile = true; }
/*  353 */           else { throw new ClassConstraintException("A ClassFile structure (like '" + Pass2Verifier.tostring(obj) + "') may have no more than one SourceFile attribute."); }
/*      */         
/*      */         }
/*  356 */         if (atts[i] instanceof InnerClasses) {
/*  357 */           if (!foundInnerClasses) { foundInnerClasses = true; }
/*      */           
/*  359 */           else if (hasInnerClass)
/*  360 */           { throw new ClassConstraintException("A Classfile structure (like '" + Pass2Verifier.tostring(obj) + "') must have exactly one InnerClasses attribute if at least one Inner Class is referenced (which is the case). More than one InnerClasses attribute was found."); }
/*      */ 
/*      */           
/*  363 */           if (!hasInnerClass) {
/*  364 */             Pass2Verifier.this.addMessage("No referenced Inner Class found, but InnerClasses attribute '" + Pass2Verifier.tostring((Node)atts[i]) + "' found. Strongly suggest removal of that attribute.");
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  369 */       if (hasInnerClass && !foundInnerClasses)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/*  374 */         Pass2Verifier.this.addMessage("A Classfile structure (like '" + Pass2Verifier.tostring((Node)obj) + "') must have exactly one InnerClasses attribute if at least one Inner Class is referenced (which is the case). No InnerClasses attribute was found.");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitConstantClass(ConstantClass obj) {
/*  381 */       if (obj.getTag() != 7) {
/*  382 */         throw new ClassConstraintException("Wrong constant tag in '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*  384 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */     }
/*      */     
/*      */     public void visitConstantFieldref(ConstantFieldref obj) {
/*  388 */       if (obj.getTag() != 9) {
/*  389 */         throw new ClassConstraintException("Wrong constant tag in '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*  391 */       checkIndex((Node)obj, obj.getClassIndex(), this.CONST_Class);
/*  392 */       checkIndex((Node)obj, obj.getNameAndTypeIndex(), this.CONST_NameAndType);
/*      */     }
/*      */     public void visitConstantMethodref(ConstantMethodref obj) {
/*  395 */       if (obj.getTag() != 10) {
/*  396 */         throw new ClassConstraintException("Wrong constant tag in '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*  398 */       checkIndex((Node)obj, obj.getClassIndex(), this.CONST_Class);
/*  399 */       checkIndex((Node)obj, obj.getNameAndTypeIndex(), this.CONST_NameAndType);
/*      */     }
/*      */     public void visitConstantInterfaceMethodref(ConstantInterfaceMethodref obj) {
/*  402 */       if (obj.getTag() != 11) {
/*  403 */         throw new ClassConstraintException("Wrong constant tag in '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*  405 */       checkIndex((Node)obj, obj.getClassIndex(), this.CONST_Class);
/*  406 */       checkIndex((Node)obj, obj.getNameAndTypeIndex(), this.CONST_NameAndType);
/*      */     }
/*      */     public void visitConstantString(ConstantString obj) {
/*  409 */       if (obj.getTag() != 8) {
/*  410 */         throw new ClassConstraintException("Wrong constant tag in '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*  412 */       checkIndex((Node)obj, obj.getStringIndex(), this.CONST_Utf8);
/*      */     }
/*      */     public void visitConstantInteger(ConstantInteger obj) {
/*  415 */       if (obj.getTag() != 3) {
/*  416 */         throw new ClassConstraintException("Wrong constant tag in '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*      */     }
/*      */     
/*      */     public void visitConstantFloat(ConstantFloat obj) {
/*  421 */       if (obj.getTag() != 4) {
/*  422 */         throw new ClassConstraintException("Wrong constant tag in '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*      */     }
/*      */     
/*      */     public void visitConstantLong(ConstantLong obj) {
/*  427 */       if (obj.getTag() != 5) {
/*  428 */         throw new ClassConstraintException("Wrong constant tag in '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*      */     }
/*      */     
/*      */     public void visitConstantDouble(ConstantDouble obj) {
/*  433 */       if (obj.getTag() != 6) {
/*  434 */         throw new ClassConstraintException("Wrong constant tag in '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*      */     }
/*      */     
/*      */     public void visitConstantNameAndType(ConstantNameAndType obj) {
/*  439 */       if (obj.getTag() != 12) {
/*  440 */         throw new ClassConstraintException("Wrong constant tag in '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*  442 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */       
/*  444 */       checkIndex((Node)obj, obj.getSignatureIndex(), this.CONST_Utf8);
/*      */     }
/*      */     public void visitConstantUtf8(ConstantUtf8 obj) {
/*  447 */       if (obj.getTag() != 1) {
/*  448 */         throw new ClassConstraintException("Wrong constant tag in '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitField(Field obj) {
/*  457 */       if (this.jc.isClass()) {
/*  458 */         int maxone = 0;
/*  459 */         if (obj.isPrivate()) maxone++; 
/*  460 */         if (obj.isProtected()) maxone++; 
/*  461 */         if (obj.isPublic()) maxone++; 
/*  462 */         if (maxone > 1) {
/*  463 */           throw new ClassConstraintException("Field '" + Pass2Verifier.tostring(obj) + "' must only have at most one of its ACC_PRIVATE, ACC_PROTECTED, ACC_PUBLIC modifiers set.");
/*      */         }
/*      */         
/*  466 */         if (obj.isFinal() && obj.isVolatile()) {
/*  467 */           throw new ClassConstraintException("Field '" + Pass2Verifier.tostring(obj) + "' must only have at most one of its ACC_FINAL, ACC_VOLATILE modifiers set.");
/*      */         }
/*      */       } else {
/*      */         
/*  471 */         if (!obj.isPublic()) {
/*  472 */           throw new ClassConstraintException("Interface field '" + Pass2Verifier.tostring(obj) + "' must have the ACC_PUBLIC modifier set but hasn't!");
/*      */         }
/*  474 */         if (!obj.isStatic()) {
/*  475 */           throw new ClassConstraintException("Interface field '" + Pass2Verifier.tostring(obj) + "' must have the ACC_STATIC modifier set but hasn't!");
/*      */         }
/*  477 */         if (!obj.isFinal()) {
/*  478 */           throw new ClassConstraintException("Interface field '" + Pass2Verifier.tostring(obj) + "' must have the ACC_FINAL modifier set but hasn't!");
/*      */         }
/*      */       } 
/*      */       
/*  482 */       if ((obj.getAccessFlags() & 0xFFFFFF20) > 0) {
/*  483 */         Pass2Verifier.this.addMessage("Field '" + Pass2Verifier.tostring((Node)obj) + "' has access flag(s) other than ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_VOLATILE, ACC_TRANSIENT set (ignored).");
/*      */       }
/*      */       
/*  486 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */       
/*  488 */       String name = obj.getName();
/*  489 */       if (!Pass2Verifier.validFieldName(name)) {
/*  490 */         throw new ClassConstraintException("Field '" + Pass2Verifier.tostring(obj) + "' has illegal name '" + obj.getName() + "'.");
/*      */       }
/*      */ 
/*      */       
/*  494 */       checkIndex((Node)obj, obj.getSignatureIndex(), this.CONST_Utf8);
/*      */       
/*  496 */       String sig = ((ConstantUtf8)this.cp.getConstant(obj.getSignatureIndex())).getBytes();
/*      */       
/*      */       try {
/*  499 */         Type type = Type.getType(sig);
/*      */       } catch (ClassFormatError cfe) {
/*      */         
/*  502 */         throw new ClassConstraintException("Illegal descriptor (==signature) '" + sig + "' used by '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       } 
/*      */       
/*  505 */       String nameanddesc = name + sig;
/*  506 */       if (this.field_names_and_desc.contains(nameanddesc)) {
/*  507 */         throw new ClassConstraintException("No two fields (like '" + Pass2Verifier.tostring(obj) + "') are allowed have same names and descriptors!");
/*      */       }
/*  509 */       if (this.field_names.contains(name)) {
/*  510 */         Pass2Verifier.this.addMessage("More than one field of name '" + name + "' detected (but with different type descriptors). This is very unusual.");
/*      */       }
/*  512 */       this.field_names_and_desc.add(nameanddesc);
/*  513 */       this.field_names.add(name);
/*      */       
/*  515 */       Attribute[] atts = obj.getAttributes();
/*  516 */       for (int i = 0; i < atts.length; i++) {
/*  517 */         if (!(atts[i] instanceof ConstantValue) && !(atts[i] instanceof Synthetic) && !(atts[i] instanceof Deprecated))
/*      */         {
/*      */           
/*  520 */           Pass2Verifier.this.addMessage("Attribute '" + Pass2Verifier.tostring((Node)atts[i]) + "' as an attribute of Field '" + Pass2Verifier.tostring((Node)obj) + "' is unknown and will therefore be ignored.");
/*      */         }
/*  522 */         if (!(atts[i] instanceof ConstantValue)) {
/*  523 */           Pass2Verifier.this.addMessage("Attribute '" + Pass2Verifier.tostring((Node)atts[i]) + "' as an attribute of Field '" + Pass2Verifier.tostring((Node)obj) + "' is not a ConstantValue and is therefore only of use for debuggers and such.");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitMethod(Method obj) {
/*      */       Type t, ts[];
/*  532 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */       
/*  534 */       String name = obj.getName();
/*  535 */       if (!Pass2Verifier.validMethodName(name, true)) {
/*  536 */         throw new ClassConstraintException("Method '" + Pass2Verifier.tostring(obj) + "' has illegal name '" + name + "'.");
/*      */       }
/*      */ 
/*      */       
/*  540 */       checkIndex((Node)obj, obj.getSignatureIndex(), this.CONST_Utf8);
/*      */       
/*  542 */       String sig = ((ConstantUtf8)this.cp.getConstant(obj.getSignatureIndex())).getBytes();
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  547 */         t = Type.getReturnType(sig);
/*  548 */         ts = Type.getArgumentTypes(sig);
/*      */       }
/*      */       catch (ClassFormatError cfe) {
/*      */         
/*  552 */         throw new ClassConstraintException("Illegal descriptor (==signature) '" + sig + "' used by Method '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       } 
/*      */ 
/*      */       
/*  556 */       Type act = t;
/*  557 */       if (act instanceof ArrayType) act = ((ArrayType)act).getBasicType(); 
/*  558 */       if (act instanceof ObjectType) {
/*  559 */         Verifier v = VerifierFactory.getVerifier(((ObjectType)act).getClassName());
/*  560 */         VerificationResult vr = v.doPass1();
/*  561 */         if (vr != VerificationResult.VR_OK) {
/*  562 */           throw new ClassConstraintException("Method '" + Pass2Verifier.tostring(obj) + "' has a return type that does not pass verification pass 1: '" + vr + "'.");
/*      */         }
/*      */       } 
/*      */       
/*  566 */       for (int i = 0; i < ts.length; i++) {
/*  567 */         act = ts[i];
/*  568 */         if (act instanceof ArrayType) act = ((ArrayType)act).getBasicType(); 
/*  569 */         if (act instanceof ObjectType) {
/*  570 */           Verifier v = VerifierFactory.getVerifier(((ObjectType)act).getClassName());
/*  571 */           VerificationResult vr = v.doPass1();
/*  572 */           if (vr != VerificationResult.VR_OK) {
/*  573 */             throw new ClassConstraintException("Method '" + Pass2Verifier.tostring(obj) + "' has an argument type that does not pass verification pass 1: '" + vr + "'.");
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  579 */       if (name.equals("<clinit>") && ts.length != 0) {
/*  580 */         throw new ClassConstraintException("Method '" + Pass2Verifier.tostring(obj) + "' has illegal name '" + name + "'. It's name resembles the class or interface initialization method which it isn't because of its arguments (==descriptor).");
/*      */       }
/*      */       
/*  583 */       if (this.jc.isClass()) {
/*  584 */         int maxone = 0;
/*  585 */         if (obj.isPrivate()) maxone++; 
/*  586 */         if (obj.isProtected()) maxone++; 
/*  587 */         if (obj.isPublic()) maxone++; 
/*  588 */         if (maxone > 1) {
/*  589 */           throw new ClassConstraintException("Method '" + Pass2Verifier.tostring(obj) + "' must only have at most one of its ACC_PRIVATE, ACC_PROTECTED, ACC_PUBLIC modifiers set.");
/*      */         }
/*      */         
/*  592 */         if (obj.isAbstract()) {
/*  593 */           if (obj.isFinal()) throw new ClassConstraintException("Abstract method '" + Pass2Verifier.tostring(obj) + "' must not have the ACC_FINAL modifier set."); 
/*  594 */           if (obj.isNative()) throw new ClassConstraintException("Abstract method '" + Pass2Verifier.tostring(obj) + "' must not have the ACC_NATIVE modifier set."); 
/*  595 */           if (obj.isPrivate()) throw new ClassConstraintException("Abstract method '" + Pass2Verifier.tostring(obj) + "' must not have the ACC_PRIVATE modifier set."); 
/*  596 */           if (obj.isStatic()) throw new ClassConstraintException("Abstract method '" + Pass2Verifier.tostring(obj) + "' must not have the ACC_STATIC modifier set."); 
/*  597 */           if (obj.isStrictfp()) throw new ClassConstraintException("Abstract method '" + Pass2Verifier.tostring(obj) + "' must not have the ACC_STRICT modifier set."); 
/*  598 */           if (obj.isSynchronized()) throw new ClassConstraintException("Abstract method '" + Pass2Verifier.tostring(obj) + "' must not have the ACC_SYNCHRONIZED modifier set.");
/*      */         
/*      */         }
/*      */       
/*  602 */       } else if (!name.equals("<clinit>")) {
/*  603 */         if (!obj.isPublic()) {
/*  604 */           throw new ClassConstraintException("Interface method '" + Pass2Verifier.tostring(obj) + "' must have the ACC_PUBLIC modifier set but hasn't!");
/*      */         }
/*  606 */         if (!obj.isAbstract()) {
/*  607 */           throw new ClassConstraintException("Interface method '" + Pass2Verifier.tostring(obj) + "' must have the ACC_STATIC modifier set but hasn't!");
/*      */         }
/*  609 */         if (obj.isPrivate() || obj.isProtected() || obj.isStatic() || obj.isFinal() || obj.isSynchronized() || obj.isNative() || obj.isStrictfp())
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  616 */           throw new ClassConstraintException("Interface method '" + Pass2Verifier.tostring(obj) + "' must not have any of the ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_SYNCHRONIZED, ACC_NATIVE, ACC_ABSTRACT, ACC_STRICT modifiers set.");
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  622 */       if (name.equals("<init>"))
/*      */       {
/*      */         
/*  625 */         if (obj.isStatic() || obj.isFinal() || obj.isSynchronized() || obj.isNative() || obj.isAbstract())
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*  630 */           throw new ClassConstraintException("Instance initialization method '" + Pass2Verifier.tostring(obj) + "' must not have any of the ACC_STATIC, ACC_FINAL, ACC_SYNCHRONIZED, ACC_NATIVE, ACC_ABSTRACT modifiers set.");
/*      */         }
/*      */       }
/*      */ 
/*      */       
/*  635 */       if (name.equals("<clinit>")) {
/*  636 */         if ((obj.getAccessFlags() & 0xFFFFF7FF) > 0) {
/*  637 */           Pass2Verifier.this.addMessage("Class or interface initialization method '" + Pass2Verifier.tostring((Node)obj) + "' has superfluous access modifier(s) set: everything but ACC_STRICT is ignored.");
/*      */         }
/*  639 */         if (obj.isAbstract()) {
/*  640 */           throw new ClassConstraintException("Class or interface initialization method '" + Pass2Verifier.tostring(obj) + "' must not be abstract. This contradicts the Java Language Specification, Second Edition (which omits this constraint) but is common practice of existing verifiers.");
/*      */         }
/*      */       } 
/*      */       
/*  644 */       if ((obj.getAccessFlags() & 0xFFFFF2C0) > 0) {
/*  645 */         Pass2Verifier.this.addMessage("Method '" + Pass2Verifier.tostring((Node)obj) + "' has access flag(s) other than ACC_PUBLIC, ACC_PRIVATE, ACC_PROTECTED, ACC_STATIC, ACC_FINAL, ACC_SYNCHRONIZED, ACC_NATIVE, ACC_ABSTRACT, ACC_STRICT set (ignored).");
/*      */       }
/*      */       
/*  648 */       String nameanddesc = name + sig;
/*  649 */       if (this.method_names_and_desc.contains(nameanddesc)) {
/*  650 */         throw new ClassConstraintException("No two methods (like '" + Pass2Verifier.tostring(obj) + "') are allowed have same names and desciptors!");
/*      */       }
/*  652 */       this.method_names_and_desc.add(nameanddesc);
/*      */       
/*  654 */       Attribute[] atts = obj.getAttributes();
/*  655 */       int num_code_atts = 0;
/*  656 */       for (int j = 0; j < atts.length; j++) {
/*  657 */         if (!(atts[j] instanceof Code) && !(atts[j] instanceof ExceptionTable) && !(atts[j] instanceof Synthetic) && !(atts[j] instanceof Deprecated))
/*      */         {
/*      */ 
/*      */           
/*  661 */           Pass2Verifier.this.addMessage("Attribute '" + Pass2Verifier.tostring((Node)atts[j]) + "' as an attribute of Method '" + Pass2Verifier.tostring((Node)obj) + "' is unknown and will therefore be ignored.");
/*      */         }
/*  663 */         if (!(atts[j] instanceof Code) && !(atts[j] instanceof ExceptionTable))
/*      */         {
/*  665 */           Pass2Verifier.this.addMessage("Attribute '" + Pass2Verifier.tostring((Node)atts[j]) + "' as an attribute of Method '" + Pass2Verifier.tostring((Node)obj) + "' is neither Code nor Exceptions and is therefore only of use for debuggers and such.");
/*      */         }
/*  667 */         if (atts[j] instanceof Code && (obj.isNative() || obj.isAbstract())) {
/*  668 */           throw new ClassConstraintException("Native or abstract methods like '" + Pass2Verifier.tostring(obj) + "' must not have a Code attribute like '" + Pass2Verifier.tostring(atts[j]) + "'.");
/*      */         }
/*  670 */         if (atts[j] instanceof Code) num_code_atts++; 
/*      */       } 
/*  672 */       if (!obj.isNative() && !obj.isAbstract() && num_code_atts != 1) {
/*  673 */         throw new ClassConstraintException("Non-native, non-abstract methods like '" + Pass2Verifier.tostring(obj) + "' must have exactly one Code attribute (found: " + num_code_atts + ").");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitSourceFile(SourceFile obj) {
/*  683 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */       
/*  685 */       String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
/*  686 */       if (!name.equals("SourceFile")) {
/*  687 */         throw new ClassConstraintException("The SourceFile attribute '" + Pass2Verifier.tostring(obj) + "' is not correctly named 'SourceFile' but '" + name + "'.");
/*      */       }
/*      */       
/*  690 */       checkIndex((Node)obj, obj.getSourceFileIndex(), this.CONST_Utf8);
/*      */       
/*  692 */       String sourcefilename = ((ConstantUtf8)this.cp.getConstant(obj.getSourceFileIndex())).getBytes();
/*  693 */       String sourcefilenamelc = sourcefilename.toLowerCase();
/*      */       
/*  695 */       if (sourcefilename.indexOf('/') != -1 || sourcefilename.indexOf('\\') != -1 || sourcefilename.indexOf(':') != -1 || sourcefilenamelc.lastIndexOf(".java") == -1)
/*      */       {
/*      */ 
/*      */         
/*  699 */         Pass2Verifier.this.addMessage("SourceFile attribute '" + Pass2Verifier.tostring((Node)obj) + "' has a funny name: remember not to confuse certain parsers working on javap's output. Also, this name ('" + sourcefilename + "') is considered an unqualified (simple) file name only."); } 
/*      */     }
/*      */     
/*      */     public void visitDeprecated(Deprecated obj) {
/*  703 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */       
/*  705 */       String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
/*  706 */       if (!name.equals("Deprecated"))
/*  707 */         throw new ClassConstraintException("The Deprecated attribute '" + Pass2Verifier.tostring(obj) + "' is not correctly named 'Deprecated' but '" + name + "'."); 
/*      */     }
/*      */     
/*      */     public void visitSynthetic(Synthetic obj) {
/*  711 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*  712 */       String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
/*  713 */       if (!name.equals("Synthetic")) {
/*  714 */         throw new ClassConstraintException("The Synthetic attribute '" + Pass2Verifier.tostring(obj) + "' is not correctly named 'Synthetic' but '" + name + "'.");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitInnerClasses(InnerClasses obj) {
/*  721 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */       
/*  723 */       String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
/*  724 */       if (!name.equals("InnerClasses")) {
/*  725 */         throw new ClassConstraintException("The InnerClasses attribute '" + Pass2Verifier.tostring(obj) + "' is not correctly named 'InnerClasses' but '" + name + "'.");
/*      */       }
/*      */       
/*  728 */       InnerClass[] ics = obj.getInnerClasses();
/*      */       
/*  730 */       for (int i = 0; i < ics.length; i++) {
/*  731 */         checkIndex((Node)obj, ics[i].getInnerClassIndex(), this.CONST_Class);
/*  732 */         int outer_idx = ics[i].getOuterClassIndex();
/*  733 */         if (outer_idx != 0) {
/*  734 */           checkIndex((Node)obj, outer_idx, this.CONST_Class);
/*      */         }
/*  736 */         int innername_idx = ics[i].getInnerNameIndex();
/*  737 */         if (innername_idx != 0) {
/*  738 */           checkIndex((Node)obj, innername_idx, this.CONST_Utf8);
/*      */         }
/*  740 */         int acc = ics[i].getInnerAccessFlags();
/*  741 */         acc &= 0xFFFFF9E0;
/*  742 */         if (acc != 0) {
/*  743 */           Pass2Verifier.this.addMessage("Unknown access flag for inner class '" + Pass2Verifier.tostring((Node)ics[i]) + "' set (InnerClasses attribute '" + Pass2Verifier.tostring((Node)obj) + "').");
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
/*      */     public void visitConstantValue(ConstantValue obj) {
/*  755 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */       
/*  757 */       String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
/*  758 */       if (!name.equals("ConstantValue")) {
/*  759 */         throw new ClassConstraintException("The ConstantValue attribute '" + Pass2Verifier.tostring(obj) + "' is not correctly named 'ConstantValue' but '" + name + "'.");
/*      */       }
/*      */       
/*  762 */       Object pred = this.carrier.predecessor();
/*  763 */       if (pred instanceof Field) {
/*  764 */         Field f = (Field)pred;
/*      */         
/*  766 */         Type field_type = Type.getType(((ConstantUtf8)this.cp.getConstant(f.getSignatureIndex())).getBytes());
/*      */         
/*  768 */         int index = obj.getConstantValueIndex();
/*  769 */         if (index < 0 || index >= this.cplen) {
/*  770 */           throw new ClassConstraintException("Invalid index '" + index + "' used by '" + Pass2Verifier.tostring(obj) + "'.");
/*      */         }
/*  772 */         Constant c = this.cp.getConstant(index);
/*      */         
/*  774 */         if (this.CONST_Long.isInstance(c) && field_type.equals(Type.LONG)) {
/*      */           return;
/*      */         }
/*  777 */         if (this.CONST_Float.isInstance(c) && field_type.equals(Type.FLOAT)) {
/*      */           return;
/*      */         }
/*  780 */         if (this.CONST_Double.isInstance(c) && field_type.equals(Type.DOUBLE)) {
/*      */           return;
/*      */         }
/*  783 */         if (this.CONST_Integer.isInstance(c) && (field_type.equals(Type.INT) || field_type.equals(Type.SHORT) || field_type.equals(Type.CHAR) || field_type.equals(Type.BYTE) || field_type.equals(Type.BOOLEAN))) {
/*      */           return;
/*      */         }
/*  786 */         if (this.CONST_String.isInstance(c) && field_type.equals(Type.STRING)) {
/*      */           return;
/*      */         }
/*      */         
/*  790 */         throw new ClassConstraintException("Illegal type of ConstantValue '" + obj + "' embedding Constant '" + c + "'. It is referenced by field '" + Pass2Verifier.tostring(f) + "' expecting a different type: '" + field_type + "'.");
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
/*      */     public void visitCode(Code obj) {
/*  802 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */       
/*  804 */       String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
/*  805 */       if (!name.equals("Code")) {
/*  806 */         throw new ClassConstraintException("The Code attribute '" + Pass2Verifier.tostring(obj) + "' is not correctly named 'Code' but '" + name + "'.");
/*      */       }
/*      */       
/*  809 */       Method m = null;
/*  810 */       if (!(this.carrier.predecessor() instanceof Method)) {
/*  811 */         Pass2Verifier.this.addMessage("Code attribute '" + Pass2Verifier.tostring((Node)obj) + "' is not declared in a method_info structure but in '" + this.carrier.predecessor() + "'. Ignored.");
/*      */         
/*      */         return;
/*      */       } 
/*  815 */       m = (Method)this.carrier.predecessor();
/*      */ 
/*      */ 
/*      */       
/*  819 */       if ((obj.getCode()).length == 0) {
/*  820 */         throw new ClassConstraintException("Code array of Code attribute '" + Pass2Verifier.tostring(obj) + "' (method '" + m + "') must not be empty.");
/*      */       }
/*      */ 
/*      */       
/*  824 */       CodeException[] exc_table = obj.getExceptionTable();
/*  825 */       for (int i = 0; i < exc_table.length; i++) {
/*  826 */         int exc_index = exc_table[i].getCatchType();
/*  827 */         if (exc_index != 0) {
/*  828 */           checkIndex((Node)obj, exc_index, this.CONST_Class);
/*  829 */           ConstantClass cc = (ConstantClass)this.cp.getConstant(exc_index);
/*  830 */           checkIndex((Node)cc, cc.getNameIndex(), this.CONST_Utf8);
/*  831 */           String cname = ((ConstantUtf8)this.cp.getConstant(cc.getNameIndex())).getBytes().replace('/', '.');
/*      */           
/*  833 */           Verifier v = VerifierFactory.getVerifier(cname);
/*  834 */           VerificationResult vr = v.doPass1();
/*      */           
/*  836 */           if (vr != VerificationResult.VR_OK) {
/*  837 */             throw new ClassConstraintException("Code attribute '" + Pass2Verifier.tostring(obj) + "' (method '" + m + "') has an exception_table entry '" + Pass2Verifier.tostring(exc_table[i]) + "' that references '" + cname + "' as an Exception but it does not pass verification pass 1: " + vr);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  842 */           JavaClass e = Repository.lookupClass(cname);
/*  843 */           JavaClass t = Repository.lookupClass(Type.THROWABLE.getClassName());
/*  844 */           JavaClass o = Repository.lookupClass(Type.OBJECT.getClassName());
/*  845 */           while (e != o && 
/*  846 */             e != t) {
/*      */             
/*  848 */             v = VerifierFactory.getVerifier(e.getSuperclassName());
/*  849 */             vr = v.doPass1();
/*  850 */             if (vr != VerificationResult.VR_OK) {
/*  851 */               throw new ClassConstraintException("Code attribute '" + Pass2Verifier.tostring(obj) + "' (method '" + m + "') has an exception_table entry '" + Pass2Verifier.tostring(exc_table[i]) + "' that references '" + cname + "' as an Exception but '" + e.getSuperclassName() + "' in the ancestor hierachy does not pass verification pass 1: " + vr);
/*      */             }
/*      */             
/*  854 */             e = Repository.lookupClass(e.getSuperclassName());
/*      */           } 
/*      */           
/*  857 */           if (e != t) throw new ClassConstraintException("Code attribute '" + Pass2Verifier.tostring(obj) + "' (method '" + m + "') has an exception_table entry '" + Pass2Verifier.tostring(exc_table[i]) + "' that references '" + cname + "' as an Exception but it is not a subclass of '" + t.getClassName() + "'.");
/*      */         
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  865 */       int method_number = -1;
/*  866 */       Method[] ms = Repository.lookupClass(Pass2Verifier.this.myOwner.getClassName()).getMethods();
/*  867 */       for (int mn = 0; mn < ms.length; mn++) {
/*  868 */         if (m == ms[mn]) {
/*  869 */           method_number = mn;
/*      */           break;
/*      */         } 
/*      */       } 
/*  873 */       if (method_number < 0) {
/*  874 */         throw new AssertionViolatedException("Could not find a known BCEL Method object in the corresponding BCEL JavaClass object.");
/*      */       }
/*  876 */       Pass2Verifier.this.localVariablesInfos[method_number] = new LocalVariablesInfo(obj.getMaxLocals());
/*      */       
/*  878 */       int num_of_lvt_attribs = 0;
/*      */       
/*  880 */       Attribute[] atts = obj.getAttributes();
/*  881 */       for (int a = 0; a < atts.length; a++) {
/*  882 */         if (!(atts[a] instanceof LineNumberTable) && !(atts[a] instanceof LocalVariableTable)) {
/*      */           
/*  884 */           Pass2Verifier.this.addMessage("Attribute '" + Pass2Verifier.tostring((Node)atts[a]) + "' as an attribute of Code attribute '" + Pass2Verifier.tostring((Node)obj) + "' (method '" + m + "') is unknown and will therefore be ignored.");
/*      */         } else {
/*      */           
/*  887 */           Pass2Verifier.this.addMessage("Attribute '" + Pass2Verifier.tostring((Node)atts[a]) + "' as an attribute of Code attribute '" + Pass2Verifier.tostring((Node)obj) + "' (method '" + m + "') will effectively be ignored and is only useful for debuggers and such.");
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  894 */         if (atts[a] instanceof LocalVariableTable) {
/*      */           
/*  896 */           LocalVariableTable lvt = (LocalVariableTable)atts[a];
/*      */           
/*  898 */           checkIndex((Node)lvt, lvt.getNameIndex(), this.CONST_Utf8);
/*      */           
/*  900 */           String lvtname = ((ConstantUtf8)this.cp.getConstant(lvt.getNameIndex())).getBytes();
/*  901 */           if (!lvtname.equals("LocalVariableTable")) {
/*  902 */             throw new ClassConstraintException("The LocalVariableTable attribute '" + Pass2Verifier.tostring(lvt) + "' is not correctly named 'LocalVariableTable' but '" + lvtname + "'.");
/*      */           }
/*      */           
/*  905 */           Code code = obj;
/*  906 */           int max_locals = code.getMaxLocals();
/*      */ 
/*      */           
/*  909 */           LocalVariable[] localvariables = lvt.getLocalVariableTable();
/*      */           
/*  911 */           for (int j = 0; j < localvariables.length; j++) {
/*  912 */             Type t; checkIndex((Node)lvt, localvariables[j].getNameIndex(), this.CONST_Utf8);
/*  913 */             String localname = ((ConstantUtf8)this.cp.getConstant(localvariables[j].getNameIndex())).getBytes();
/*  914 */             if (!Pass2Verifier.validJavaIdentifier(localname)) {
/*  915 */               throw new ClassConstraintException("LocalVariableTable '" + Pass2Verifier.tostring(lvt) + "' references a local variable by the name '" + localname + "' which is not a legal Java simple name.");
/*      */             }
/*      */             
/*  918 */             checkIndex((Node)lvt, localvariables[j].getSignatureIndex(), this.CONST_Utf8);
/*  919 */             String localsig = ((ConstantUtf8)this.cp.getConstant(localvariables[j].getSignatureIndex())).getBytes();
/*      */             
/*      */             try {
/*  922 */               t = Type.getType(localsig);
/*      */             } catch (ClassFormatError cfe) {
/*      */               
/*  925 */               throw new ClassConstraintException("Illegal descriptor (==signature) '" + localsig + "' used by LocalVariable '" + Pass2Verifier.tostring(localvariables[j]) + "' referenced by '" + Pass2Verifier.tostring(lvt) + "'.");
/*      */             } 
/*  927 */             int localindex = localvariables[j].getIndex();
/*  928 */             if (((t == Type.LONG || t == Type.DOUBLE) ? (localindex + 1) : localindex) >= code.getMaxLocals()) {
/*  929 */               throw new ClassConstraintException("LocalVariableTable attribute '" + Pass2Verifier.tostring(lvt) + "' references a LocalVariable '" + Pass2Verifier.tostring(localvariables[j]) + "' with an index that exceeds the surrounding Code attribute's max_locals value of '" + code.getMaxLocals() + "'.");
/*      */             }
/*      */             
/*      */             try {
/*  933 */               Pass2Verifier.this.localVariablesInfos[method_number].add(localindex, localname, localvariables[j].getStartPC(), localvariables[j].getLength(), t);
/*      */             } catch (LocalVariableInfoInconsistentException lviie) {
/*      */               
/*  936 */               throw new ClassConstraintException("Conflicting information in LocalVariableTable '" + Pass2Verifier.tostring(lvt) + "' found in Code attribute '" + Pass2Verifier.tostring(obj) + "' (method '" + Pass2Verifier.tostring(m) + "'). " + lviie.getMessage());
/*      */             } 
/*      */           } 
/*      */           
/*  940 */           num_of_lvt_attribs++;
/*  941 */           if (num_of_lvt_attribs > obj.getMaxLocals()) {
/*  942 */             throw new ClassConstraintException("Number of LocalVariableTable attributes of Code attribute '" + Pass2Verifier.tostring(obj) + "' (method '" + Pass2Verifier.tostring(m) + "') exceeds number of local variable slots '" + obj.getMaxLocals() + "' ('There may be no more than one LocalVariableTable attribute per local variable in the Code attribute.').");
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void visitExceptionTable(ExceptionTable obj) {
/*  950 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */       
/*  952 */       String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
/*  953 */       if (!name.equals("Exceptions")) {
/*  954 */         throw new ClassConstraintException("The Exceptions attribute '" + Pass2Verifier.tostring(obj) + "' is not correctly named 'Exceptions' but '" + name + "'.");
/*      */       }
/*      */       
/*  957 */       int[] exc_indices = obj.getExceptionIndexTable();
/*      */       
/*  959 */       for (int i = 0; i < exc_indices.length; i++) {
/*  960 */         checkIndex((Node)obj, exc_indices[i], this.CONST_Class);
/*      */         
/*  962 */         ConstantClass cc = (ConstantClass)this.cp.getConstant(exc_indices[i]);
/*  963 */         checkIndex((Node)cc, cc.getNameIndex(), this.CONST_Utf8);
/*  964 */         String cname = ((ConstantUtf8)this.cp.getConstant(cc.getNameIndex())).getBytes().replace('/', '.');
/*      */         
/*  966 */         Verifier v = VerifierFactory.getVerifier(cname);
/*  967 */         VerificationResult vr = v.doPass1();
/*      */         
/*  969 */         if (vr != VerificationResult.VR_OK) {
/*  970 */           throw new ClassConstraintException("Exceptions attribute '" + Pass2Verifier.tostring(obj) + "' references '" + cname + "' as an Exception but it does not pass verification pass 1: " + vr);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  975 */         JavaClass e = Repository.lookupClass(cname);
/*  976 */         JavaClass t = Repository.lookupClass(Type.THROWABLE.getClassName());
/*  977 */         JavaClass o = Repository.lookupClass(Type.OBJECT.getClassName());
/*  978 */         while (e != o && 
/*  979 */           e != t) {
/*      */           
/*  981 */           v = VerifierFactory.getVerifier(e.getSuperclassName());
/*  982 */           vr = v.doPass1();
/*  983 */           if (vr != VerificationResult.VR_OK) {
/*  984 */             throw new ClassConstraintException("Exceptions attribute '" + Pass2Verifier.tostring(obj) + "' references '" + cname + "' as an Exception but '" + e.getSuperclassName() + "' in the ancestor hierachy does not pass verification pass 1: " + vr);
/*      */           }
/*      */           
/*  987 */           e = Repository.lookupClass(e.getSuperclassName());
/*      */         } 
/*      */         
/*  990 */         if (e != t) throw new ClassConstraintException("Exceptions attribute '" + Pass2Verifier.tostring(obj) + "' references '" + cname + "' as an Exception but it is not a subclass of '" + t.getClassName() + "'.");
/*      */       
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitLineNumberTable(LineNumberTable obj) {
/* 1000 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */       
/* 1002 */       String name = ((ConstantUtf8)this.cp.getConstant(obj.getNameIndex())).getBytes();
/* 1003 */       if (!name.equals("LineNumberTable")) {
/* 1004 */         throw new ClassConstraintException("The LineNumberTable attribute '" + Pass2Verifier.tostring(obj) + "' is not correctly named 'LineNumberTable' but '" + name + "'.");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitLocalVariableTable(LocalVariableTable obj) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitUnknown(Unknown obj) {
/* 1021 */       checkIndex((Node)obj, obj.getNameIndex(), this.CONST_Utf8);
/*      */ 
/*      */       
/* 1024 */       Pass2Verifier.this.addMessage("Unknown attribute '" + Pass2Verifier.tostring((Node)obj) + "'. This attribute is not known in any context!");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitLocalVariable(LocalVariable obj) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitCodeException(CodeException obj) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitConstantPool(ConstantPool obj) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitInnerClass(InnerClass obj) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void visitLineNumber(LineNumber obj) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void field_and_method_refs_are_valid() {
/* 1074 */     JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
/* 1075 */     DescendingVisitor v = new DescendingVisitor(jc, new FAMRAV_Visitor(jc));
/* 1076 */     v.visit();
/*      */   }
/*      */ 
/*      */   
/*      */   private class FAMRAV_Visitor
/*      */     extends EmptyVisitor
/*      */     implements Visitor
/*      */   {
/*      */     private final JavaClass jc;
/*      */     
/*      */     private final ConstantPool cp;
/*      */     
/*      */     private final Pass2Verifier this$0;
/*      */     
/*      */     private FAMRAV_Visitor(Pass2Verifier this$0, JavaClass _jc) {
/* 1091 */       Pass2Verifier.this = Pass2Verifier.this;
/* 1092 */       this.jc = _jc;
/* 1093 */       this.cp = _jc.getConstantPool();
/*      */     }
/*      */     
/*      */     public void visitConstantFieldref(ConstantFieldref obj) {
/* 1097 */       if (obj.getTag() != 9) {
/* 1098 */         throw new ClassConstraintException("ConstantFieldref '" + Pass2Verifier.tostring(obj) + "' has wrong tag!");
/*      */       }
/* 1100 */       int name_and_type_index = obj.getNameAndTypeIndex();
/* 1101 */       ConstantNameAndType cnat = (ConstantNameAndType)this.cp.getConstant(name_and_type_index);
/* 1102 */       String name = ((ConstantUtf8)this.cp.getConstant(cnat.getNameIndex())).getBytes();
/* 1103 */       if (!Pass2Verifier.validFieldName(name)) {
/* 1104 */         throw new ClassConstraintException("Invalid field name '" + name + "' referenced by '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*      */       
/* 1107 */       int class_index = obj.getClassIndex();
/* 1108 */       ConstantClass cc = (ConstantClass)this.cp.getConstant(class_index);
/* 1109 */       String className = ((ConstantUtf8)this.cp.getConstant(cc.getNameIndex())).getBytes();
/* 1110 */       if (!Pass2Verifier.validClassName(className)) {
/* 1111 */         throw new ClassConstraintException("Illegal class name '" + className + "' used by '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*      */       
/* 1114 */       String sig = ((ConstantUtf8)this.cp.getConstant(cnat.getSignatureIndex())).getBytes();
/*      */       
/*      */       try {
/* 1117 */         Type type = Type.getType(sig);
/*      */       }
/*      */       catch (ClassFormatError cfe) {
/*      */         
/* 1121 */         throw new ClassConstraintException("Illegal descriptor (==signature) '" + sig + "' used by '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       } 
/*      */     }
/*      */     
/*      */     public void visitConstantMethodref(ConstantMethodref obj) {
/* 1126 */       if (obj.getTag() != 10) {
/* 1127 */         throw new ClassConstraintException("ConstantMethodref '" + Pass2Verifier.tostring(obj) + "' has wrong tag!");
/*      */       }
/* 1129 */       int name_and_type_index = obj.getNameAndTypeIndex();
/* 1130 */       ConstantNameAndType cnat = (ConstantNameAndType)this.cp.getConstant(name_and_type_index);
/* 1131 */       String name = ((ConstantUtf8)this.cp.getConstant(cnat.getNameIndex())).getBytes();
/* 1132 */       if (!Pass2Verifier.validClassMethodName(name)) {
/* 1133 */         throw new ClassConstraintException("Invalid (non-interface) method name '" + name + "' referenced by '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*      */       
/* 1136 */       int class_index = obj.getClassIndex();
/* 1137 */       ConstantClass cc = (ConstantClass)this.cp.getConstant(class_index);
/* 1138 */       String className = ((ConstantUtf8)this.cp.getConstant(cc.getNameIndex())).getBytes();
/* 1139 */       if (!Pass2Verifier.validClassName(className)) {
/* 1140 */         throw new ClassConstraintException("Illegal class name '" + className + "' used by '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*      */       
/* 1143 */       String sig = ((ConstantUtf8)this.cp.getConstant(cnat.getSignatureIndex())).getBytes();
/*      */       
/*      */       try {
/* 1146 */         Type t = Type.getReturnType(sig);
/* 1147 */         Type[] ts = Type.getArgumentTypes(sig);
/* 1148 */         if (name.equals("<init>") && t != Type.VOID) {
/* 1149 */           throw new ClassConstraintException("Instance initialization method must have VOID return type.");
/*      */         }
/*      */       }
/*      */       catch (ClassFormatError cfe) {
/*      */         
/* 1154 */         throw new ClassConstraintException("Illegal descriptor (==signature) '" + sig + "' used by '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       } 
/*      */     }
/*      */     
/*      */     public void visitConstantInterfaceMethodref(ConstantInterfaceMethodref obj) {
/* 1159 */       if (obj.getTag() != 11) {
/* 1160 */         throw new ClassConstraintException("ConstantInterfaceMethodref '" + Pass2Verifier.tostring(obj) + "' has wrong tag!");
/*      */       }
/* 1162 */       int name_and_type_index = obj.getNameAndTypeIndex();
/* 1163 */       ConstantNameAndType cnat = (ConstantNameAndType)this.cp.getConstant(name_and_type_index);
/* 1164 */       String name = ((ConstantUtf8)this.cp.getConstant(cnat.getNameIndex())).getBytes();
/* 1165 */       if (!Pass2Verifier.validInterfaceMethodName(name)) {
/* 1166 */         throw new ClassConstraintException("Invalid (interface) method name '" + name + "' referenced by '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*      */       
/* 1169 */       int class_index = obj.getClassIndex();
/* 1170 */       ConstantClass cc = (ConstantClass)this.cp.getConstant(class_index);
/* 1171 */       String className = ((ConstantUtf8)this.cp.getConstant(cc.getNameIndex())).getBytes();
/* 1172 */       if (!Pass2Verifier.validClassName(className)) {
/* 1173 */         throw new ClassConstraintException("Illegal class name '" + className + "' used by '" + Pass2Verifier.tostring(obj) + "'.");
/*      */       }
/*      */       
/* 1176 */       String sig = ((ConstantUtf8)this.cp.getConstant(cnat.getSignatureIndex())).getBytes();
/*      */       
/*      */       try {
/* 1179 */         Type t = Type.getReturnType(sig);
/* 1180 */         Type[] ts = Type.getArgumentTypes(sig);
/* 1181 */         if (name.equals("<clinit>") && t != Type.VOID) {
/* 1182 */           Pass2Verifier.this.addMessage("Class or interface initialization method '<clinit>' usually has VOID return type instead of '" + t + "'. Note this is really not a requirement of The Java Virtual Machine Specification, Second Edition.");
/*      */         }
/*      */       }
/*      */       catch (ClassFormatError cfe) {
/*      */         
/* 1187 */         throw new ClassConstraintException("Illegal descriptor (==signature) '" + sig + "' used by '" + Pass2Verifier.tostring(obj) + "'.");
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
/*      */   private static final boolean validClassName(String name) {
/* 1200 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean validMethodName(String name, boolean allowStaticInit) {
/* 1211 */     if (validJavaLangMethodName(name)) return true;
/*      */     
/* 1213 */     if (allowStaticInit) {
/* 1214 */       return (name.equals("<init>") || name.equals("<clinit>"));
/*      */     }
/*      */     
/* 1217 */     return name.equals("<init>");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean validClassMethodName(String name) {
/* 1227 */     return validMethodName(name, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean validJavaLangMethodName(String name) {
/* 1237 */     if (!Character.isJavaIdentifierStart(name.charAt(0))) return false;
/*      */     
/* 1239 */     for (int i = 1; i < name.length(); i++) {
/* 1240 */       if (!Character.isJavaIdentifierPart(name.charAt(i))) return false; 
/*      */     } 
/* 1242 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean validInterfaceMethodName(String name) {
/* 1252 */     if (name.startsWith("<")) return false; 
/* 1253 */     return validJavaLangMethodName(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean validJavaIdentifier(String name) {
/* 1262 */     if (!Character.isJavaIdentifierStart(name.charAt(0))) return false;
/*      */     
/* 1264 */     for (int i = 1; i < name.length(); i++) {
/* 1265 */       if (!Character.isJavaIdentifierPart(name.charAt(i))) return false; 
/*      */     } 
/* 1267 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean validFieldName(String name) {
/* 1276 */     return validJavaIdentifier(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class InnerClassDetector
/*      */     extends EmptyVisitor
/*      */   {
/*      */     private boolean hasInnerClass;
/*      */ 
/*      */ 
/*      */     
/*      */     private JavaClass jc;
/*      */ 
/*      */ 
/*      */     
/*      */     private ConstantPool cp;
/*      */ 
/*      */ 
/*      */     
/*      */     private final Pass2Verifier this$0;
/*      */ 
/*      */ 
/*      */     
/*      */     private InnerClassDetector(Pass2Verifier this$0) {
/* 1302 */       this.this$0 = this$0;
/*      */       this.hasInnerClass = false; } public InnerClassDetector(Pass2Verifier this$0, JavaClass _jc) {
/* 1304 */       this.this$0 = this$0; this.hasInnerClass = false;
/* 1305 */       this.jc = _jc;
/* 1306 */       this.cp = this.jc.getConstantPool();
/* 1307 */       (new DescendingVisitor(this.jc, (Visitor)this)).visit();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean innerClassReferenced() {
/* 1314 */       return this.hasInnerClass;
/*      */     }
/*      */     
/*      */     public void visitConstantClass(ConstantClass obj) {
/* 1318 */       Constant c = this.cp.getConstant(obj.getNameIndex());
/*      */       
/* 1320 */       String classname = ((ConstantUtf8)c).getBytes();
/* 1321 */       if (c instanceof ConstantUtf8 && classname.startsWith(this.jc.getClassName().replace('.', '/') + "$")) {
/* 1322 */         this.hasInnerClass = true;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String tostring(Node n) {
/* 1332 */     return (new StringRepresentation(n)).toString();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/statics/Pass2Verifier.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */