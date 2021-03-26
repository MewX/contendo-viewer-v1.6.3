/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import org.apache.bcel.Repository;
/*     */ import org.apache.bcel.classfile.JavaClass;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ObjectType
/*     */   extends ReferenceType
/*     */ {
/*     */   private String class_name;
/*     */   
/*     */   public ObjectType(String class_name) {
/*  73 */     super((byte)14, "L" + class_name.replace('.', '/') + ";");
/*  74 */     this.class_name = class_name.replace('/', '.');
/*     */   }
/*     */ 
/*     */   
/*     */   public String getClassName() {
/*  79 */     return this.class_name;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  83 */     return this.class_name.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object type) {
/*  88 */     return (type instanceof ObjectType) ? ((ObjectType)type).class_name.equals(this.class_name) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean referencesClass() {
/*  97 */     JavaClass jc = Repository.lookupClass(this.class_name);
/*  98 */     if (jc == null) {
/*  99 */       return false;
/*     */     }
/* 101 */     return jc.isClass();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean referencesInterface() {
/* 109 */     JavaClass jc = Repository.lookupClass(this.class_name);
/* 110 */     if (jc == null) {
/* 111 */       return false;
/*     */     }
/* 113 */     return !jc.isClass();
/*     */   }
/*     */   
/*     */   public boolean subclassOf(ObjectType superclass) {
/* 117 */     if (referencesInterface() || superclass.referencesInterface()) {
/* 118 */       return false;
/*     */     }
/* 120 */     return Repository.instanceOf(this.class_name, superclass.class_name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean accessibleTo(ObjectType accessor) {
/* 127 */     JavaClass jc = Repository.lookupClass(this.class_name);
/*     */     
/* 129 */     if (jc.isPublic()) {
/* 130 */       return true;
/*     */     }
/* 132 */     JavaClass acc = Repository.lookupClass(accessor.class_name);
/* 133 */     return acc.getPackageName().equals(jc.getPackageName());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ObjectType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */