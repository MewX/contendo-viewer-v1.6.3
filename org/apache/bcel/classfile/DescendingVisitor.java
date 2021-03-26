/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.util.Stack;
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
/*     */ public class DescendingVisitor
/*     */   implements Visitor
/*     */ {
/*     */   private JavaClass clazz;
/*     */   private Visitor visitor;
/*  70 */   private Stack stack = new Stack();
/*     */ 
/*     */ 
/*     */   
/*     */   public Object predecessor() {
/*  75 */     return predecessor(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object predecessor(int level) {
/*  83 */     int size = this.stack.size();
/*     */     
/*  85 */     if (size < 2 || level < 0) {
/*  86 */       return null;
/*     */     }
/*  88 */     return this.stack.elementAt(size - level + 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object current() {
/*  94 */     return this.stack.peek();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DescendingVisitor(JavaClass clazz, Visitor visitor) {
/* 102 */     this.clazz = clazz;
/* 103 */     this.visitor = visitor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visit() {
/* 109 */     this.clazz.accept(this);
/*     */   }
/*     */   public void visitJavaClass(JavaClass clazz) {
/* 112 */     this.stack.push(clazz);
/* 113 */     clazz.accept(this.visitor);
/*     */     
/* 115 */     Field[] fields = clazz.getFields();
/* 116 */     for (int i = 0; i < fields.length; i++) {
/* 117 */       fields[i].accept(this);
/*     */     }
/* 119 */     Method[] methods = clazz.getMethods();
/* 120 */     for (int j = 0; j < methods.length; j++) {
/* 121 */       methods[j].accept(this);
/*     */     }
/* 123 */     Attribute[] attributes = clazz.getAttributes();
/* 124 */     for (int k = 0; k < attributes.length; k++) {
/* 125 */       attributes[k].accept(this);
/*     */     }
/* 127 */     clazz.getConstantPool().accept(this);
/* 128 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitField(Field field) {
/* 132 */     this.stack.push(field);
/* 133 */     field.accept(this.visitor);
/*     */     
/* 135 */     Attribute[] attributes = field.getAttributes();
/* 136 */     for (int i = 0; i < attributes.length; i++)
/* 137 */       attributes[i].accept(this); 
/* 138 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantValue(ConstantValue cv) {
/* 142 */     this.stack.push(cv);
/* 143 */     cv.accept(this.visitor);
/* 144 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitMethod(Method method) {
/* 148 */     this.stack.push(method);
/* 149 */     method.accept(this.visitor);
/*     */     
/* 151 */     Attribute[] attributes = method.getAttributes();
/* 152 */     for (int i = 0; i < attributes.length; i++) {
/* 153 */       attributes[i].accept(this);
/*     */     }
/* 155 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitExceptionTable(ExceptionTable table) {
/* 159 */     this.stack.push(table);
/* 160 */     table.accept(this.visitor);
/* 161 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitCode(Code code) {
/* 165 */     this.stack.push(code);
/* 166 */     code.accept(this.visitor);
/*     */     
/* 168 */     CodeException[] table = code.getExceptionTable();
/* 169 */     for (int i = 0; i < table.length; i++) {
/* 170 */       table[i].accept(this);
/*     */     }
/* 172 */     Attribute[] attributes = code.getAttributes();
/* 173 */     for (int j = 0; j < attributes.length; j++)
/* 174 */       attributes[j].accept(this); 
/* 175 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitCodeException(CodeException ce) {
/* 179 */     this.stack.push(ce);
/* 180 */     ce.accept(this.visitor);
/* 181 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitLineNumberTable(LineNumberTable table) {
/* 185 */     this.stack.push(table);
/* 186 */     table.accept(this.visitor);
/*     */     
/* 188 */     LineNumber[] numbers = table.getLineNumberTable();
/* 189 */     for (int i = 0; i < numbers.length; i++)
/* 190 */       numbers[i].accept(this); 
/* 191 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitLineNumber(LineNumber number) {
/* 195 */     this.stack.push(number);
/* 196 */     number.accept(this.visitor);
/* 197 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitLocalVariableTable(LocalVariableTable table) {
/* 201 */     this.stack.push(table);
/* 202 */     table.accept(this.visitor);
/*     */     
/* 204 */     LocalVariable[] vars = table.getLocalVariableTable();
/* 205 */     for (int i = 0; i < vars.length; i++)
/* 206 */       vars[i].accept(this); 
/* 207 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitStackMap(StackMap table) {
/* 211 */     this.stack.push(table);
/* 212 */     table.accept(this.visitor);
/*     */     
/* 214 */     StackMapEntry[] vars = table.getStackMap();
/*     */     
/* 216 */     for (int i = 0; i < vars.length; i++)
/* 217 */       vars[i].accept(this); 
/* 218 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitStackMapEntry(StackMapEntry var) {
/* 222 */     this.stack.push(var);
/* 223 */     var.accept(this.visitor);
/* 224 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitLocalVariable(LocalVariable var) {
/* 228 */     this.stack.push(var);
/* 229 */     var.accept(this.visitor);
/* 230 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantPool(ConstantPool cp) {
/* 234 */     this.stack.push(cp);
/* 235 */     cp.accept(this.visitor);
/*     */     
/* 237 */     Constant[] constants = cp.getConstantPool();
/* 238 */     for (int i = 1; i < constants.length; i++) {
/* 239 */       if (constants[i] != null) {
/* 240 */         constants[i].accept(this);
/*     */       }
/*     */     } 
/* 243 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantClass(ConstantClass constant) {
/* 247 */     this.stack.push(constant);
/* 248 */     constant.accept(this.visitor);
/* 249 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantDouble(ConstantDouble constant) {
/* 253 */     this.stack.push(constant);
/* 254 */     constant.accept(this.visitor);
/* 255 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantFieldref(ConstantFieldref constant) {
/* 259 */     this.stack.push(constant);
/* 260 */     constant.accept(this.visitor);
/* 261 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantFloat(ConstantFloat constant) {
/* 265 */     this.stack.push(constant);
/* 266 */     constant.accept(this.visitor);
/* 267 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantInteger(ConstantInteger constant) {
/* 271 */     this.stack.push(constant);
/* 272 */     constant.accept(this.visitor);
/* 273 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantInterfaceMethodref(ConstantInterfaceMethodref constant) {
/* 277 */     this.stack.push(constant);
/* 278 */     constant.accept(this.visitor);
/* 279 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantLong(ConstantLong constant) {
/* 283 */     this.stack.push(constant);
/* 284 */     constant.accept(this.visitor);
/* 285 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantMethodref(ConstantMethodref constant) {
/* 289 */     this.stack.push(constant);
/* 290 */     constant.accept(this.visitor);
/* 291 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantNameAndType(ConstantNameAndType constant) {
/* 295 */     this.stack.push(constant);
/* 296 */     constant.accept(this.visitor);
/* 297 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantString(ConstantString constant) {
/* 301 */     this.stack.push(constant);
/* 302 */     constant.accept(this.visitor);
/* 303 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitConstantUtf8(ConstantUtf8 constant) {
/* 307 */     this.stack.push(constant);
/* 308 */     constant.accept(this.visitor);
/* 309 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitInnerClasses(InnerClasses ic) {
/* 313 */     this.stack.push(ic);
/* 314 */     ic.accept(this.visitor);
/*     */     
/* 316 */     InnerClass[] ics = ic.getInnerClasses();
/* 317 */     for (int i = 0; i < ics.length; i++)
/* 318 */       ics[i].accept(this); 
/* 319 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitInnerClass(InnerClass inner) {
/* 323 */     this.stack.push(inner);
/* 324 */     inner.accept(this.visitor);
/* 325 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitDeprecated(Deprecated attribute) {
/* 329 */     this.stack.push(attribute);
/* 330 */     attribute.accept(this.visitor);
/* 331 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitSourceFile(SourceFile attribute) {
/* 335 */     this.stack.push(attribute);
/* 336 */     attribute.accept(this.visitor);
/* 337 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitSynthetic(Synthetic attribute) {
/* 341 */     this.stack.push(attribute);
/* 342 */     attribute.accept(this.visitor);
/* 343 */     this.stack.pop();
/*     */   }
/*     */   
/*     */   public void visitUnknown(Unknown attribute) {
/* 347 */     this.stack.push(attribute);
/* 348 */     attribute.accept(this.visitor);
/* 349 */     this.stack.pop();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/DescendingVisitor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */