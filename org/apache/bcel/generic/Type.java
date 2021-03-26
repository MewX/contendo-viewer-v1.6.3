/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.apache.bcel.classfile.Utility;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Type
/*     */ {
/*     */   protected byte type;
/*     */   protected String signature;
/*  75 */   public static final BasicType VOID = new BasicType((byte)12);
/*  76 */   public static final BasicType BOOLEAN = new BasicType((byte)4);
/*  77 */   public static final BasicType INT = new BasicType((byte)10);
/*  78 */   public static final BasicType SHORT = new BasicType((byte)9);
/*  79 */   public static final BasicType BYTE = new BasicType((byte)8);
/*  80 */   public static final BasicType LONG = new BasicType((byte)11);
/*  81 */   public static final BasicType DOUBLE = new BasicType((byte)7);
/*  82 */   public static final BasicType FLOAT = new BasicType((byte)6);
/*  83 */   public static final BasicType CHAR = new BasicType((byte)5);
/*  84 */   public static final ObjectType OBJECT = new ObjectType("java.lang.Object");
/*  85 */   public static final ObjectType STRING = new ObjectType("java.lang.String");
/*  86 */   public static final ObjectType STRINGBUFFER = new ObjectType("java.lang.StringBuffer");
/*  87 */   public static final ObjectType THROWABLE = new ObjectType("java.lang.Throwable");
/*  88 */   public static final Type[] NO_ARGS = new Type[0];
/*  89 */   public static final ReferenceType NULL = new ReferenceType();
/*  90 */   public static final Type UNKNOWN = new Type((byte)15, "<unknown object>") {  }
/*     */   ;
/*     */   
/*     */   protected Type(byte t, String s) {
/*  94 */     this.type = t;
/*  95 */     this.signature = s;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSignature() {
/* 101 */     return this.signature;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getType() {
/* 106 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 112 */     switch (this.type) { case 7:
/*     */       case 11:
/* 114 */         return 2;
/* 115 */       case 12: return 0; }
/* 116 */      return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 124 */     return (equals(NULL) || this.type >= 15) ? this.signature : Utility.signatureToString(this.signature, false);
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
/*     */   public static String getMethodSignature(Type return_type, Type[] arg_types) {
/* 137 */     StringBuffer buf = new StringBuffer("(");
/* 138 */     int length = (arg_types == null) ? 0 : arg_types.length;
/*     */     
/* 140 */     for (int i = 0; i < length; i++) {
/* 141 */       buf.append(arg_types[i].getSignature());
/*     */     }
/* 143 */     buf.append(')');
/* 144 */     buf.append(return_type.getSignature());
/*     */     
/* 146 */     return buf.toString();
/*     */   }
/*     */   
/* 149 */   private static int consumed_chars = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final Type getType(String signature) throws StringIndexOutOfBoundsException {
/* 159 */     byte type = Utility.typeOfSignature(signature);
/*     */     
/* 161 */     if (type <= 12) {
/* 162 */       consumed_chars = 1;
/* 163 */       return BasicType.getType(type);
/* 164 */     }  if (type == 13) {
/* 165 */       int dim = 0;
/*     */       do {
/* 167 */         dim++;
/* 168 */       } while (signature.charAt(dim) == '[');
/*     */ 
/*     */       
/* 171 */       Type t = getType(signature.substring(dim));
/*     */       
/* 173 */       consumed_chars += dim;
/*     */       
/* 175 */       return new ArrayType(t, dim);
/*     */     } 
/* 177 */     int index = signature.indexOf(';');
/*     */     
/* 179 */     if (index < 0) {
/* 180 */       throw new ClassFormatError("Invalid signature: " + signature);
/*     */     }
/* 182 */     consumed_chars = index + 1;
/*     */     
/* 184 */     return new ObjectType(signature.substring(1, index).replace('/', '.'));
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
/*     */   public static Type getReturnType(String signature) {
/*     */     try {
/* 197 */       int index = signature.lastIndexOf(')') + 1;
/* 198 */       return getType(signature.substring(index));
/*     */     } catch (StringIndexOutOfBoundsException e) {
/* 200 */       throw new ClassFormatError("Invalid method signature: " + signature);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Type[] getArgumentTypes(String signature) {
/* 210 */     ArrayList vec = new ArrayList();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 215 */       if (signature.charAt(0) != '(') {
/* 216 */         throw new ClassFormatError("Invalid method signature: " + signature);
/*     */       }
/* 218 */       int index = 1;
/*     */       
/* 220 */       while (signature.charAt(index) != ')') {
/* 221 */         vec.add(getType(signature.substring(index)));
/* 222 */         index += consumed_chars;
/*     */       } 
/*     */     } catch (StringIndexOutOfBoundsException e) {
/* 225 */       throw new ClassFormatError("Invalid method signature: " + signature);
/*     */     } 
/*     */     
/* 228 */     Type[] types = new Type[vec.size()];
/* 229 */     vec.toArray(types);
/* 230 */     return types;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/Type.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */