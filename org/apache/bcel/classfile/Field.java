/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Field
/*     */   extends FieldOrMethod
/*     */ {
/*     */   public Field(Field c) {
/*  72 */     super(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Field(DataInputStream file, ConstantPool constant_pool) throws IOException, ClassFormatError {
/*  82 */     super(file, constant_pool);
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
/*     */   public Field(int access_flags, int name_index, int signature_index, Attribute[] attributes, ConstantPool constant_pool) {
/*  95 */     super(access_flags, name_index, signature_index, attributes, constant_pool);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(Visitor v) {
/* 106 */     v.visitField(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final ConstantValue getConstantValue() {
/* 113 */     for (int i = 0; i < this.attributes_count; i++) {
/* 114 */       if (this.attributes[i].getTag() == 1)
/* 115 */         return (ConstantValue)this.attributes[i]; 
/*     */     } 
/* 117 */     return null;
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
/*     */   public final String toString() {
/* 130 */     String access = Utility.accessToString(this.access_flags);
/* 131 */     access = access.equals("") ? "" : (access + " ");
/* 132 */     String signature = Utility.signatureToString(getSignature());
/* 133 */     String name = getName();
/*     */     
/* 135 */     StringBuffer buf = new StringBuffer(access + signature + " " + name);
/* 136 */     ConstantValue cv = getConstantValue();
/*     */     
/* 138 */     if (cv != null) {
/* 139 */       buf.append(" = " + cv);
/*     */     }
/* 141 */     for (int i = 0; i < this.attributes_count; i++) {
/* 142 */       Attribute a = this.attributes[i];
/*     */       
/* 144 */       if (!(a instanceof ConstantValue)) {
/* 145 */         buf.append(" [" + a.toString() + "]");
/*     */       }
/*     */     } 
/* 148 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Field copy(ConstantPool constant_pool) {
/* 155 */     return (Field)copy_(constant_pool);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/Field.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */