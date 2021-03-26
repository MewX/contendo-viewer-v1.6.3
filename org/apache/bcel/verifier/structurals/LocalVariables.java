/*     */ package org.apache.bcel.verifier.structurals;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalVariables
/*     */ {
/*     */   private Type[] locals;
/*     */   
/*     */   public LocalVariables(int maxLocals) {
/*  76 */     this.locals = new Type[maxLocals];
/*  77 */     for (int i = 0; i < maxLocals; i++) {
/*  78 */       this.locals[i] = Type.UNKNOWN;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() {
/*  88 */     LocalVariables lvs = new LocalVariables(this.locals.length);
/*  89 */     for (int i = 0; i < this.locals.length; i++) {
/*  90 */       lvs.locals[i] = this.locals[i];
/*     */     }
/*  92 */     return lvs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type get(int i) {
/*  99 */     return this.locals[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalVariables getClone() {
/* 107 */     return (LocalVariables)clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int maxLocals() {
/* 115 */     return this.locals.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int i, Type type) {
/* 122 */     if (type == Type.BYTE || type == Type.SHORT || type == Type.BOOLEAN || type == Type.CHAR) {
/* 123 */       throw new AssertionViolatedException("LocalVariables do not know about '" + type + "'. Use Type.INT instead.");
/*     */     }
/* 125 */     this.locals[i] = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 132 */     if (!(o instanceof LocalVariables)) return false; 
/* 133 */     LocalVariables lv = (LocalVariables)o;
/* 134 */     if (this.locals.length != lv.locals.length) return false; 
/* 135 */     for (int i = 0; i < this.locals.length; i++) {
/* 136 */       if (!this.locals[i].equals(lv.locals[i]))
/*     */       {
/* 138 */         return false;
/*     */       }
/*     */     } 
/* 141 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void merge(LocalVariables lv) {
/* 150 */     if (this.locals.length != lv.locals.length) {
/* 151 */       throw new AssertionViolatedException("Merging LocalVariables of different size?!? From different methods or what?!?");
/*     */     }
/*     */     
/* 154 */     for (int i = 0; i < this.locals.length; i++) {
/* 155 */       merge(lv, i);
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
/*     */   private void merge(LocalVariables lv, int i) {
/* 168 */     if (!(this.locals[i] instanceof UninitializedObjectType) && lv.locals[i] instanceof UninitializedObjectType) {
/* 169 */       throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object in the local variables detected.");
/*     */     }
/*     */     
/* 172 */     if (!this.locals[i].equals(lv.locals[i]) && this.locals[i] instanceof UninitializedObjectType && lv.locals[i] instanceof UninitializedObjectType) {
/* 173 */       throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object in the local variables detected.");
/*     */     }
/*     */     
/* 176 */     if (this.locals[i] instanceof UninitializedObjectType && 
/* 177 */       !(lv.locals[i] instanceof UninitializedObjectType)) {
/* 178 */       this.locals[i] = (Type)((UninitializedObjectType)this.locals[i]).getInitialized();
/*     */     }
/*     */     
/* 181 */     if (this.locals[i] instanceof ReferenceType && lv.locals[i] instanceof ReferenceType) {
/* 182 */       if (!this.locals[i].equals(lv.locals[i])) {
/* 183 */         ReferenceType referenceType = ((ReferenceType)this.locals[i]).firstCommonSuperclass((ReferenceType)lv.locals[i]);
/*     */         
/* 185 */         if (referenceType != null) {
/* 186 */           this.locals[i] = (Type)referenceType;
/*     */         }
/*     */         else {
/*     */           
/* 190 */           throw new AssertionViolatedException("Could not load all the super classes of '" + this.locals[i] + "' and '" + lv.locals[i] + "'.");
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 195 */     } else if (!this.locals[i].equals(lv.locals[i])) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 202 */       this.locals[i] = Type.UNKNOWN;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 211 */     String s = new String();
/* 212 */     for (int i = 0; i < this.locals.length; i++) {
/* 213 */       s = s + Integer.toString(i) + ": " + this.locals[i] + "\n";
/*     */     }
/* 215 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeObject(UninitializedObjectType u) {
/* 223 */     for (int i = 0; i < this.locals.length; i++) {
/* 224 */       if (this.locals[i] == u)
/* 225 */         this.locals[i] = (Type)u.getInitialized(); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/LocalVariables.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */