/*     */ package org.apache.bcel.util;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.classfile.Attribute;
/*     */ import org.apache.bcel.classfile.Code;
/*     */ import org.apache.bcel.classfile.ConstantValue;
/*     */ import org.apache.bcel.classfile.ExceptionTable;
/*     */ import org.apache.bcel.classfile.Field;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ final class MethodHTML
/*     */   implements Constants
/*     */ {
/*     */   private String class_name;
/*     */   private PrintWriter file;
/*     */   private ConstantHTML constant_html;
/*     */   private AttributeHTML attribute_html;
/*     */   
/*     */   MethodHTML(String dir, String class_name, Method[] methods, Field[] fields, ConstantHTML constant_html, AttributeHTML attribute_html) throws IOException {
/*  77 */     this.class_name = class_name;
/*  78 */     this.attribute_html = attribute_html;
/*  79 */     this.constant_html = constant_html;
/*     */     
/*  81 */     this.file = new PrintWriter(new FileOutputStream(dir + class_name + "_methods.html"));
/*     */     
/*  83 */     this.file.println("<HTML><BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
/*  84 */     this.file.println("<TR><TH ALIGN=LEFT>Access&nbsp;flags</TH><TH ALIGN=LEFT>Type</TH><TH ALIGN=LEFT>Field&nbsp;name</TH></TR>");
/*     */     
/*  86 */     for (int i = 0; i < fields.length; i++)
/*  87 */       writeField(fields[i]); 
/*  88 */     this.file.println("</TABLE>");
/*     */     
/*  90 */     this.file.println("<TABLE BORDER=0><TR><TH ALIGN=LEFT>Access&nbsp;flags</TH><TH ALIGN=LEFT>Return&nbsp;type</TH><TH ALIGN=LEFT>Method&nbsp;name</TH><TH ALIGN=LEFT>Arguments</TH></TR>");
/*     */ 
/*     */     
/*  93 */     for (int j = 0; j < methods.length; j++) {
/*  94 */       writeMethod(methods[j], j);
/*     */     }
/*  96 */     this.file.println("</TABLE></BODY></HTML>");
/*  97 */     this.file.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeField(Field field) throws IOException {
/* 107 */     String type = Utility.signatureToString(field.getSignature());
/* 108 */     String name = field.getName();
/* 109 */     String access = Utility.accessToString(field.getAccessFlags());
/*     */ 
/*     */     
/* 112 */     access = Utility.replace(access, " ", "&nbsp;");
/*     */     
/* 114 */     this.file.print("<TR><TD><FONT COLOR=\"#FF0000\">" + access + "</FONT></TD>\n<TD>" + Class2HTML.referenceType(type) + "</TD><TD><A NAME=\"field" + name + "\">" + name + "</A></TD>");
/*     */ 
/*     */ 
/*     */     
/* 118 */     Attribute[] attributes = field.getAttributes();
/*     */ 
/*     */     
/* 121 */     for (int i = 0; i < attributes.length; i++) {
/* 122 */       this.attribute_html.writeAttribute(attributes[i], name + "@" + i);
/*     */     }
/* 124 */     for (int j = 0; j < attributes.length; j++) {
/* 125 */       if (attributes[j].getTag() == 1) {
/* 126 */         String str = ((ConstantValue)attributes[j]).toString();
/*     */ 
/*     */         
/* 129 */         this.file.print("<TD>= <A HREF=\"" + this.class_name + "_attributes.html#" + name + "@" + j + "\" TARGET=\"Attributes\">" + str + "</TD>\n");
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     this.file.println("</TR>");
/*     */   }
/*     */ 
/*     */   
/*     */   private final void writeMethod(Method method, int method_number) throws IOException {
/* 140 */     String signature = method.getSignature();
/*     */     
/* 142 */     String[] args = Utility.methodSignatureArgumentTypes(signature, false);
/*     */     
/* 144 */     String type = Utility.methodSignatureReturnType(signature, false);
/*     */     
/* 146 */     String name = method.getName();
/*     */     
/* 148 */     String access = Utility.accessToString(method.getAccessFlags());
/*     */     
/* 150 */     Attribute[] attributes = method.getAttributes();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     access = Utility.replace(access, " ", "&nbsp;");
/* 156 */     String html_name = Class2HTML.toHTML(name);
/*     */     
/* 158 */     this.file.print("<TR VALIGN=TOP><TD><FONT COLOR=\"#FF0000\"><A NAME=method" + method_number + ">" + access + "</A></FONT></TD>");
/*     */ 
/*     */     
/* 161 */     this.file.print("<TD>" + Class2HTML.referenceType(type) + "</TD><TD>" + "<A HREF=" + this.class_name + "_code.html#method" + method_number + " TARGET=Code>" + html_name + "</A></TD>\n<TD>(");
/*     */ 
/*     */ 
/*     */     
/* 165 */     for (int i = 0; i < args.length; i++) {
/* 166 */       this.file.print(Class2HTML.referenceType(args[i]));
/* 167 */       if (i < args.length - 1) {
/* 168 */         this.file.print(", ");
/*     */       }
/*     */     } 
/* 171 */     this.file.print(")</TD></TR>");
/*     */ 
/*     */     
/* 174 */     for (int j = 0; j < attributes.length; j++) {
/* 175 */       this.attribute_html.writeAttribute(attributes[j], "method" + method_number + "@" + j, method_number);
/*     */ 
/*     */       
/* 178 */       byte tag = attributes[j].getTag();
/* 179 */       if (tag == 3) {
/* 180 */         this.file.print("<TR VALIGN=TOP><TD COLSPAN=2></TD><TH ALIGN=LEFT>throws</TH><TD>");
/* 181 */         int[] exceptions = ((ExceptionTable)attributes[j]).getExceptionIndexTable();
/*     */         
/* 183 */         for (int k = 0; k < exceptions.length; k++) {
/* 184 */           this.file.print(this.constant_html.referenceConstant(exceptions[k]));
/*     */           
/* 186 */           if (k < exceptions.length - 1)
/* 187 */             this.file.print(", "); 
/*     */         } 
/* 189 */         this.file.println("</TD></TR>");
/* 190 */       } else if (tag == 2) {
/* 191 */         Attribute[] c_a = ((Code)attributes[j]).getAttributes();
/*     */         
/* 193 */         for (int k = 0; k < c_a.length; k++)
/* 194 */           this.attribute_html.writeAttribute(c_a[k], "method" + method_number + "@" + j + "@" + k, method_number); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/MethodHTML.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */