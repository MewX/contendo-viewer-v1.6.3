/*     */ package org.apache.bcel.util;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.classfile.Constant;
/*     */ import org.apache.bcel.classfile.ConstantClass;
/*     */ import org.apache.bcel.classfile.ConstantFieldref;
/*     */ import org.apache.bcel.classfile.ConstantInterfaceMethodref;
/*     */ import org.apache.bcel.classfile.ConstantMethodref;
/*     */ import org.apache.bcel.classfile.ConstantNameAndType;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ import org.apache.bcel.classfile.ConstantString;
/*     */ import org.apache.bcel.classfile.Method;
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
/*     */ final class ConstantHTML
/*     */   implements Constants
/*     */ {
/*     */   private String class_name;
/*     */   private String class_package;
/*     */   private ConstantPool constant_pool;
/*     */   private PrintWriter file;
/*     */   private String[] constant_ref;
/*     */   private Constant[] constants;
/*     */   private Method[] methods;
/*     */   
/*     */   ConstantHTML(String dir, String class_name, String class_package, Method[] methods, ConstantPool constant_pool) throws IOException {
/*  80 */     this.class_name = class_name;
/*  81 */     this.class_package = class_package;
/*  82 */     this.constant_pool = constant_pool;
/*  83 */     this.methods = methods;
/*  84 */     this.constants = constant_pool.getConstantPool();
/*  85 */     this.file = new PrintWriter(new FileOutputStream(dir + class_name + "_cp.html"));
/*  86 */     this.constant_ref = new String[this.constants.length];
/*  87 */     this.constant_ref[0] = "&lt;unknown&gt;";
/*     */     
/*  89 */     this.file.println("<HTML><BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
/*     */ 
/*     */     
/*  92 */     for (int i = 1; i < this.constants.length; i++) {
/*  93 */       if (i % 2 == 0) {
/*  94 */         this.file.print("<TR BGCOLOR=\"#C0C0C0\"><TD>");
/*     */       } else {
/*  96 */         this.file.print("<TR BGCOLOR=\"#A0A0A0\"><TD>");
/*     */       } 
/*  98 */       if (this.constants[i] != null) {
/*  99 */         writeConstant(i);
/*     */       }
/* 101 */       this.file.print("</TD></TR>\n");
/*     */     } 
/*     */     
/* 104 */     this.file.println("</TABLE></BODY></HTML>");
/* 105 */     this.file.close();
/*     */   }
/*     */   
/*     */   String referenceConstant(int index) {
/* 109 */     return this.constant_ref[index]; } private void writeConstant(int index) { int j, k; String str1, method_name, html_method_name, method_class, short_method_class; ConstantNameAndType c2; String signature, args[], type, ret_type; StringBuffer buf; int i; String arg_types; ConstantFieldref c3; String field_class, short_field_class, field_name; ConstantClass c4; String class_name2, short_class_name; ConstantString c5;
/*     */     String str;
/*     */     ConstantNameAndType c6;
/*     */     int signature_index;
/* 113 */     byte tag = this.constants[index].getTag();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     this.file.println("<H4> <A NAME=cp" + index + ">" + index + "</A> " + Constants.CONSTANT_NAMES[tag] + "</H4>");
/*     */ 
/*     */ 
/*     */     
/* 122 */     switch (tag) {
/*     */       
/*     */       case 10:
/*     */       case 11:
/* 126 */         if (tag == 10) {
/* 127 */           ConstantMethodref c = (ConstantMethodref)this.constant_pool.getConstant(index, (byte)10);
/* 128 */           j = c.getClassIndex();
/* 129 */           k = c.getNameAndTypeIndex();
/*     */         } else {
/*     */           
/* 132 */           ConstantInterfaceMethodref c1 = (ConstantInterfaceMethodref)this.constant_pool.getConstant(index, (byte)11);
/* 133 */           j = c1.getClassIndex();
/* 134 */           k = c1.getNameAndTypeIndex();
/*     */         } 
/*     */ 
/*     */         
/* 138 */         method_name = this.constant_pool.constantToString(k, (byte)12);
/* 139 */         html_method_name = Class2HTML.toHTML(method_name);
/*     */ 
/*     */         
/* 142 */         method_class = this.constant_pool.constantToString(j, (byte)7);
/* 143 */         short_method_class = Utility.compactClassName(method_class);
/* 144 */         short_method_class = Utility.compactClassName(method_class);
/* 145 */         short_method_class = Utility.compactClassName(short_method_class, this.class_package + ".", true);
/*     */ 
/*     */         
/* 148 */         c2 = (ConstantNameAndType)this.constant_pool.getConstant(k, (byte)12);
/* 149 */         signature = this.constant_pool.constantToString(c2.getSignatureIndex(), (byte)1);
/*     */         
/* 151 */         args = Utility.methodSignatureArgumentTypes(signature, false);
/*     */ 
/*     */         
/* 154 */         type = Utility.methodSignatureReturnType(signature, false);
/* 155 */         ret_type = Class2HTML.referenceType(type);
/*     */         
/* 157 */         buf = new StringBuffer("(");
/* 158 */         for (i = 0; i < args.length; i++) {
/* 159 */           buf.append(Class2HTML.referenceType(args[i]));
/* 160 */           if (i < args.length - 1)
/* 161 */             buf.append(",&nbsp;"); 
/*     */         } 
/* 163 */         buf.append(")");
/*     */         
/* 165 */         arg_types = buf.toString();
/*     */         
/* 167 */         if (method_class.equals(this.class_name)) {
/* 168 */           str1 = "<A HREF=\"" + this.class_name + "_code.html#method" + getMethodNumber(method_name + signature) + "\" TARGET=Code>" + html_method_name + "</A>";
/*     */         } else {
/*     */           
/* 171 */           str1 = "<A HREF=\"" + method_class + ".html" + "\" TARGET=_top>" + short_method_class + "</A>." + html_method_name;
/*     */         } 
/*     */         
/* 174 */         this.constant_ref[index] = ret_type + "&nbsp;<A HREF=\"" + this.class_name + "_cp.html#cp" + j + "\" TARGET=Constants>" + short_method_class + "</A>.<A HREF=\"" + this.class_name + "_cp.html#cp" + index + "\" TARGET=ConstantPool>" + html_method_name + "</A>&nbsp;" + arg_types;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 179 */         this.file.println("<P><TT>" + ret_type + "&nbsp;" + str1 + arg_types + "&nbsp;</TT>\n<UL>" + "<LI><A HREF=\"#cp" + j + "\">Class index(" + j + ")</A>\n" + "<LI><A HREF=\"#cp" + k + "\">NameAndType index(" + k + ")</A></UL>");
/*     */         return;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 9:
/* 186 */         c3 = (ConstantFieldref)this.constant_pool.getConstant(index, (byte)9);
/* 187 */         j = c3.getClassIndex();
/* 188 */         k = c3.getNameAndTypeIndex();
/*     */ 
/*     */         
/* 191 */         field_class = this.constant_pool.constantToString(j, (byte)7);
/* 192 */         short_field_class = Utility.compactClassName(field_class);
/* 193 */         short_field_class = Utility.compactClassName(short_field_class, this.class_package + ".", true);
/*     */         
/* 195 */         field_name = this.constant_pool.constantToString(k, (byte)12);
/*     */         
/* 197 */         if (field_class.equals(this.class_name)) {
/* 198 */           str1 = "<A HREF=\"" + field_class + "_methods.html#field" + field_name + "\" TARGET=Methods>" + field_name + "</A>";
/*     */         } else {
/*     */           
/* 201 */           str1 = "<A HREF=\"" + field_class + ".html\" TARGET=_top>" + short_field_class + "</A>." + field_name + "\n";
/*     */         } 
/*     */         
/* 204 */         this.constant_ref[index] = "<A HREF=\"" + this.class_name + "_cp.html#cp" + j + "\" TARGET=Constants>" + short_field_class + "</A>.<A HREF=\"" + this.class_name + "_cp.html#cp" + index + "\" TARGET=ConstantPool>" + field_name + "</A>";
/*     */ 
/*     */ 
/*     */         
/* 208 */         this.file.println("<P><TT>" + str1 + "</TT><BR>\n" + "<UL>" + "<LI><A HREF=\"#cp" + j + "\">Class(" + j + ")</A><BR>\n" + "<LI><A HREF=\"#cp" + k + "\">NameAndType(" + k + ")</A></UL>");
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 7:
/* 214 */         c4 = (ConstantClass)this.constant_pool.getConstant(index, (byte)7);
/* 215 */         k = c4.getNameIndex();
/* 216 */         class_name2 = this.constant_pool.constantToString(index, tag);
/* 217 */         short_class_name = Utility.compactClassName(class_name2);
/* 218 */         short_class_name = Utility.compactClassName(short_class_name, this.class_package + ".", true);
/*     */         
/* 220 */         str1 = "<A HREF=\"" + class_name2 + ".html\" TARGET=_top>" + short_class_name + "</A>";
/* 221 */         this.constant_ref[index] = "<A HREF=\"" + this.class_name + "_cp.html#cp" + index + "\" TARGET=ConstantPool>" + short_class_name + "</A>";
/*     */ 
/*     */         
/* 224 */         this.file.println("<P><TT>" + str1 + "</TT><UL>" + "<LI><A HREF=\"#cp" + k + "\">Name index(" + k + ")</A></UL>\n");
/*     */         return;
/*     */ 
/*     */       
/*     */       case 8:
/* 229 */         c5 = (ConstantString)this.constant_pool.getConstant(index, (byte)8);
/* 230 */         k = c5.getStringIndex();
/*     */         
/* 232 */         str = Class2HTML.toHTML(this.constant_pool.constantToString(index, tag));
/*     */         
/* 234 */         this.file.println("<P><TT>" + str + "</TT><UL>" + "<LI><A HREF=\"#cp" + k + "\">Name index(" + k + ")</A></UL>\n");
/*     */         return;
/*     */ 
/*     */       
/*     */       case 12:
/* 239 */         c6 = (ConstantNameAndType)this.constant_pool.getConstant(index, (byte)12);
/* 240 */         k = c6.getNameIndex();
/* 241 */         signature_index = c6.getSignatureIndex();
/*     */         
/* 243 */         this.file.println("<P><TT>" + Class2HTML.toHTML(this.constant_pool.constantToString(index, tag)) + "</TT><UL>" + "<LI><A HREF=\"#cp" + k + "\">Name index(" + k + ")</A>\n" + "<LI><A HREF=\"#cp" + signature_index + "\">Signature index(" + signature_index + ")</A></UL>\n");
/*     */         return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     this.file.println("<P><TT>" + Class2HTML.toHTML(this.constant_pool.constantToString(index, tag)) + "</TT>\n"); }
/*     */ 
/*     */ 
/*     */   
/*     */   private final int getMethodNumber(String str) {
/* 255 */     for (int i = 0; i < this.methods.length; i++) {
/* 256 */       String cmp = this.methods[i].getName() + this.methods[i].getSignature();
/* 257 */       if (cmp.equals(str))
/* 258 */         return i; 
/*     */     } 
/* 260 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/ConstantHTML.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */