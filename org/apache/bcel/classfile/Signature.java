/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
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
/*     */ public final class Signature
/*     */   extends Attribute
/*     */ {
/*     */   private int signature_index;
/*     */   
/*     */   public Signature(Signature c) {
/*  76 */     this(c.getNameIndex(), c.getLength(), c.getSignatureIndex(), c.getConstantPool());
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
/*     */   Signature(int name_index, int length, DataInputStream file, ConstantPool constant_pool) throws IOException {
/*  90 */     this(name_index, length, file.readUnsignedShort(), constant_pool);
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
/*     */   public Signature(int name_index, int length, int signature_index, ConstantPool constant_pool) {
/* 102 */     super((byte)10, name_index, length, constant_pool);
/* 103 */     this.signature_index = signature_index;
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
/* 114 */     System.err.println("Visiting non-standard Signature object");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void dump(DataOutputStream file) throws IOException {
/* 125 */     super.dump(file);
/* 126 */     file.writeShort(this.signature_index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getSignatureIndex() {
/* 132 */     return this.signature_index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setSignatureIndex(int signature_index) {
/* 138 */     this.signature_index = signature_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getSignature() {
/* 145 */     ConstantUtf8 c = (ConstantUtf8)this.constant_pool.getConstant(this.signature_index, (byte)1);
/*     */     
/* 147 */     return c.getBytes();
/*     */   }
/*     */   
/*     */   private static final class MyByteArrayInputStream
/*     */     extends ByteArrayInputStream
/*     */   {
/*     */     MyByteArrayInputStream(String data) {
/* 154 */       super(data.getBytes());
/* 155 */     } final int mark() { return this.pos; }
/* 156 */     final String getData() { return new String(this.buf); }
/* 157 */     final void reset(int p) { this.pos = p; } final void unread() {
/* 158 */       if (this.pos > 0) this.pos--; 
/*     */     } }
/*     */   
/*     */   private static boolean identStart(int ch) {
/* 162 */     return (ch == 84 || ch == 76);
/*     */   }
/*     */   
/*     */   private static boolean identPart(int ch) {
/* 166 */     return (ch == 47 || ch == 59);
/*     */   }
/*     */ 
/*     */   
/*     */   private static final void matchIdent(MyByteArrayInputStream in, StringBuffer buf) {
/*     */     int ch;
/* 172 */     if ((ch = in.read()) == -1) {
/* 173 */       throw new RuntimeException("Illegal signature: " + in.getData() + " no ident, reaching EOF");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 178 */     if (!identStart(ch)) {
/* 179 */       StringBuffer stringBuffer = new StringBuffer();
/*     */       
/* 181 */       int count = 1;
/* 182 */       while (Character.isJavaIdentifierPart((char)ch)) {
/* 183 */         stringBuffer.append((char)ch);
/* 184 */         count++;
/* 185 */         ch = in.read();
/*     */       } 
/*     */       
/* 188 */       if (ch == 58) {
/* 189 */         in.skip("Ljava/lang/Object".length());
/* 190 */         buf.append(stringBuffer);
/*     */         
/* 192 */         ch = in.read();
/* 193 */         in.unread();
/*     */       } else {
/*     */         
/* 196 */         for (int i = 0; i < count; i++) {
/* 197 */           in.unread();
/*     */         }
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 203 */     StringBuffer buf2 = new StringBuffer();
/* 204 */     ch = in.read();
/*     */     
/*     */     do {
/* 207 */       buf2.append((char)ch);
/* 208 */       ch = in.read();
/*     */     
/*     */     }
/* 211 */     while (ch != -1 && (Character.isJavaIdentifierPart((char)ch) || ch == 47));
/*     */     
/* 213 */     buf.append(buf2.toString().replace('/', '.'));
/*     */ 
/*     */ 
/*     */     
/* 217 */     if (ch != -1) {
/* 218 */       in.unread();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final void matchGJIdent(MyByteArrayInputStream in, StringBuffer buf) {
/* 226 */     matchIdent(in, buf);
/*     */     
/* 228 */     int ch = in.read();
/* 229 */     if (ch == 60 || ch == 40) {
/*     */       
/* 231 */       buf.append((char)ch);
/* 232 */       matchGJIdent(in, buf);
/*     */       
/* 234 */       while ((ch = in.read()) != 62 && ch != 41) {
/* 235 */         if (ch == -1) {
/* 236 */           throw new RuntimeException("Illegal signature: " + in.getData() + " reaching EOF");
/*     */         }
/*     */ 
/*     */         
/* 240 */         buf.append(", ");
/* 241 */         in.unread();
/* 242 */         matchGJIdent(in, buf);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 247 */       buf.append((char)ch);
/*     */     } else {
/* 249 */       in.unread();
/*     */     } 
/* 251 */     ch = in.read();
/* 252 */     if (identStart(ch))
/* 253 */     { in.unread();
/* 254 */       matchGJIdent(in, buf); }
/* 255 */     else { if (ch == 41) {
/* 256 */         in.unread(); return;
/*     */       } 
/* 258 */       if (ch != 59) {
/* 259 */         throw new RuntimeException("Illegal signature: " + in.getData() + " read " + (char)ch);
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   public static String translate(String s) {
/* 265 */     StringBuffer buf = new StringBuffer();
/*     */     
/* 267 */     matchGJIdent(new MyByteArrayInputStream(s), buf);
/*     */     
/* 269 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public static final boolean isFormalParameterList(String s) {
/* 273 */     return (s.startsWith("<") && s.indexOf(':') > 0);
/*     */   }
/*     */   
/*     */   public static final boolean isActualParameterList(String s) {
/* 277 */     return (s.startsWith("L") && s.endsWith(">;"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String toString() {
/* 284 */     String s = getSignature();
/*     */     
/* 286 */     return "Signature(" + s + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute copy(ConstantPool constant_pool) {
/* 293 */     return (Signature)clone();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/Signature.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */