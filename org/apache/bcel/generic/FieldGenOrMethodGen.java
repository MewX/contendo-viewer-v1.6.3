/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.apache.bcel.classfile.AccessFlags;
/*     */ import org.apache.bcel.classfile.Attribute;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FieldGenOrMethodGen
/*     */   extends AccessFlags
/*     */   implements Cloneable, NamedAndTyped
/*     */ {
/*     */   protected String name;
/*     */   protected Type type;
/*     */   protected ConstantPoolGen cp;
/*  73 */   private ArrayList attribute_vec = new ArrayList();
/*     */ 
/*     */   
/*     */   public void setType(Type type) {
/*  77 */     this.type = type; } public Type getType() {
/*  78 */     return this.type;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  82 */     return this.name; } public void setName(String name) {
/*  83 */     this.name = name;
/*     */   }
/*  85 */   public ConstantPoolGen getConstantPool() { return this.cp; } public void setConstantPool(ConstantPoolGen cp) {
/*  86 */     this.cp = cp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAttribute(Attribute a) {
/*  96 */     this.attribute_vec.add(a);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAttribute(Attribute a) {
/* 101 */     this.attribute_vec.remove(a);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAttributes() {
/* 106 */     this.attribute_vec.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute[] getAttributes() {
/* 112 */     Attribute[] attributes = new Attribute[this.attribute_vec.size()];
/* 113 */     this.attribute_vec.toArray((Object[])attributes);
/* 114 */     return attributes;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract String getSignature();
/*     */ 
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 123 */       return super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 125 */       System.err.println(e);
/* 126 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/FieldGenOrMethodGen.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */