/*     */ package org.apache.bcel.verifier.structurals;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.apache.bcel.generic.ObjectType;
/*     */ import org.apache.bcel.generic.ReferenceType;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.bcel.verifier.exc.AssertionViolatedException;
/*     */ import org.apache.bcel.verifier.exc.StructuralCodeConstraintException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OperandStack
/*     */ {
/*  72 */   private ArrayList stack = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   private int maxStack;
/*     */ 
/*     */ 
/*     */   
/*     */   public OperandStack(int maxStack) {
/*  81 */     this.maxStack = maxStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OperandStack(int maxStack, ObjectType obj) {
/*  89 */     this.maxStack = maxStack;
/*  90 */     push((Type)obj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() {
/*  98 */     OperandStack newstack = new OperandStack(this.maxStack);
/*  99 */     newstack.stack = (ArrayList)this.stack.clone();
/* 100 */     return newstack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 107 */     this.stack = new ArrayList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 116 */     if (!(o instanceof OperandStack)) return false; 
/* 117 */     OperandStack s = (OperandStack)o;
/* 118 */     return this.stack.equals(s.stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OperandStack getClone() {
/* 127 */     return (OperandStack)clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 134 */     return this.stack.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int maxStack() {
/* 141 */     return this.maxStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type peek() {
/* 148 */     return peek(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type peek(int i) {
/* 156 */     return this.stack.get(size() - i - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type pop() {
/* 163 */     Type e = this.stack.remove(size() - 1);
/* 164 */     return e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type pop(int i) {
/* 171 */     for (int j = 0; j < i; j++) {
/* 172 */       pop();
/*     */     }
/* 174 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void push(Type type) {
/* 181 */     if (type == null) throw new AssertionViolatedException("Cannot push NULL onto OperandStack."); 
/* 182 */     if (type == Type.BOOLEAN || type == Type.CHAR || type == Type.BYTE || type == Type.SHORT) {
/* 183 */       throw new AssertionViolatedException("The OperandStack does not know about '" + type + "'; use Type.INT instead.");
/*     */     }
/* 185 */     if (slotsUsed() >= this.maxStack) {
/* 186 */       throw new AssertionViolatedException("OperandStack too small, should have thrown proper Exception elsewhere. Stack: " + this);
/*     */     }
/* 188 */     this.stack.add(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int size() {
/* 195 */     return this.stack.size();
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
/*     */   public int slotsUsed() {
/* 207 */     int slots = 0;
/* 208 */     for (int i = 0; i < this.stack.size(); i++) {
/* 209 */       slots += peek(i).getSize();
/*     */     }
/* 211 */     return slots;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 218 */     String s = "Slots used: " + slotsUsed() + " MaxStack: " + this.maxStack + ".\n";
/* 219 */     for (int i = 0; i < size(); i++) {
/* 220 */       s = s + peek(i) + " (Size: " + peek(i).getSize() + ")\n";
/*     */     }
/* 222 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void merge(OperandStack s) {
/* 231 */     if (slotsUsed() != s.slotsUsed() || size() != s.size()) {
/* 232 */       throw new StructuralCodeConstraintException("Cannot merge stacks of different size:\nOperandStack A:\n" + this + "\nOperandStack B:\n" + s);
/*     */     }
/* 234 */     for (int i = 0; i < size(); i++) {
/*     */ 
/*     */       
/* 237 */       if (!(this.stack.get(i) instanceof UninitializedObjectType) && s.stack.get(i) instanceof UninitializedObjectType) {
/* 238 */         throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object on the stack detected.");
/*     */       }
/*     */ 
/*     */       
/* 242 */       if (!this.stack.get(i).equals(s.stack.get(i)) && this.stack.get(i) instanceof UninitializedObjectType && !(s.stack.get(i) instanceof UninitializedObjectType)) {
/* 243 */         throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object on the stack detected.");
/*     */       }
/*     */       
/* 246 */       if (this.stack.get(i) instanceof UninitializedObjectType && 
/* 247 */         !(s.stack.get(i) instanceof UninitializedObjectType)) {
/* 248 */         this.stack.set(i, ((UninitializedObjectType)this.stack.get(i)).getInitialized());
/*     */       }
/*     */       
/* 251 */       if (!this.stack.get(i).equals(s.stack.get(i))) {
/* 252 */         if (this.stack.get(i) instanceof ReferenceType && s.stack.get(i) instanceof ReferenceType) {
/*     */           
/* 254 */           this.stack.set(i, ((ReferenceType)this.stack.get(i)).firstCommonSuperclass(s.stack.get(i)));
/*     */         } else {
/*     */           
/* 257 */           throw new StructuralCodeConstraintException("Cannot merge stacks of different types:\nStack A:\n" + this + "\nStack B:\n" + s);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeObject(UninitializedObjectType u) {
/* 268 */     for (int i = 0; i < this.stack.size(); i++) {
/* 269 */       if (this.stack.get(i) == u)
/* 270 */         this.stack.set(i, u.getInitialized()); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/OperandStack.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */