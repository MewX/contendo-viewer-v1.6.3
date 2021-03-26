/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ArrayType
/*     */   extends ReferenceType
/*     */ {
/*     */   private int dimensions;
/*     */   private Type basic_type;
/*     */   
/*     */   public ArrayType(byte type, int dimensions) {
/*  74 */     this(BasicType.getType(type), dimensions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayType(String class_name, int dimensions) {
/*  83 */     this(new ObjectType(class_name), dimensions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayType(Type type, int dimensions) {
/*  92 */     super((byte)13, "<dummy>");
/*     */     ArrayType array;
/*  94 */     if (dimensions < 1 || dimensions > 255) {
/*  95 */       throw new ClassGenException("Invalid number of dimensions: " + dimensions);
/*     */     }
/*  97 */     switch (type.getType()) {
/*     */       case 13:
/*  99 */         array = (ArrayType)type;
/* 100 */         this.dimensions = dimensions + array.dimensions;
/* 101 */         this.basic_type = array.basic_type;
/*     */         break;
/*     */       
/*     */       case 12:
/* 105 */         throw new ClassGenException("Invalid type: void[]");
/*     */       
/*     */       default:
/* 108 */         this.dimensions = dimensions;
/* 109 */         this.basic_type = type;
/*     */         break;
/*     */     } 
/*     */     
/* 113 */     StringBuffer buf = new StringBuffer();
/* 114 */     for (int i = 0; i < this.dimensions; i++) {
/* 115 */       buf.append('[');
/*     */     }
/* 117 */     buf.append(this.basic_type.getSignature());
/*     */     
/* 119 */     this.signature = buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getBasicType() {
/* 126 */     return this.basic_type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getElementType() {
/* 133 */     if (this.dimensions == 1) {
/* 134 */       return this.basic_type;
/*     */     }
/* 136 */     return new ArrayType(this.basic_type, this.dimensions - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDimensions() {
/* 141 */     return this.dimensions;
/*     */   }
/*     */   
/*     */   public int hashcode() {
/* 145 */     return this.basic_type.hashCode() ^ this.dimensions;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object type) {
/* 150 */     if (type instanceof ArrayType) {
/* 151 */       ArrayType array = (ArrayType)type;
/* 152 */       return (array.dimensions == this.dimensions && array.basic_type.equals(this.basic_type));
/*     */     } 
/* 154 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ArrayType.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */