/*     */ package org.apache.bcel.util;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.classfile.Attribute;
/*     */ import org.apache.bcel.classfile.Code;
/*     */ import org.apache.bcel.classfile.CodeException;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ import org.apache.bcel.classfile.ConstantUtf8;
/*     */ import org.apache.bcel.classfile.ConstantValue;
/*     */ import org.apache.bcel.classfile.ExceptionTable;
/*     */ import org.apache.bcel.classfile.InnerClass;
/*     */ import org.apache.bcel.classfile.InnerClasses;
/*     */ import org.apache.bcel.classfile.LineNumber;
/*     */ import org.apache.bcel.classfile.LineNumberTable;
/*     */ import org.apache.bcel.classfile.LocalVariable;
/*     */ import org.apache.bcel.classfile.LocalVariableTable;
/*     */ import org.apache.bcel.classfile.SourceFile;
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
/*     */ final class AttributeHTML
/*     */   implements Constants
/*     */ {
/*     */   private String class_name;
/*     */   private PrintWriter file;
/*  70 */   private int attr_count = 0;
/*     */   
/*     */   private ConstantHTML constant_html;
/*     */   
/*     */   private ConstantPool constant_pool;
/*     */   
/*     */   AttributeHTML(String dir, String class_name, ConstantPool constant_pool, ConstantHTML constant_html) throws IOException {
/*  77 */     this.class_name = class_name;
/*  78 */     this.constant_pool = constant_pool;
/*  79 */     this.constant_html = constant_html;
/*     */     
/*  81 */     this.file = new PrintWriter(new FileOutputStream(dir + class_name + "_attributes.html"));
/*  82 */     this.file.println("<HTML><BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
/*     */   }
/*     */   
/*     */   private final String codeLink(int link, int method_number) {
/*  86 */     return "<A HREF=\"" + this.class_name + "_code.html#code" + method_number + "@" + link + "\" TARGET=Code>" + link + "</A>";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   final void close() {
/*  92 */     this.file.println("</TABLE></BODY></HTML>");
/*  93 */     this.file.close();
/*     */   }
/*     */   
/*     */   final void writeAttribute(Attribute attribute, String anchor) throws IOException {
/*  97 */     writeAttribute(attribute, anchor, 0); } final void writeAttribute(Attribute attribute, String anchor, int method_number) throws IOException { int index; Code c; Attribute[] attributes; CodeException[] ce; int len, indices[], i; LineNumber[] line_numbers; int j; LocalVariable[] vars;
/*     */     int k;
/*     */     InnerClass[] classes;
/*     */     int m;
/* 101 */     byte tag = attribute.getTag();
/*     */ 
/*     */     
/* 104 */     if (tag == -1) {
/*     */       return;
/*     */     }
/* 107 */     this.attr_count++;
/*     */     
/* 109 */     if (this.attr_count % 2 == 0) {
/* 110 */       this.file.print("<TR BGCOLOR=\"#C0C0C0\"><TD>");
/*     */     } else {
/* 112 */       this.file.print("<TR BGCOLOR=\"#A0A0A0\"><TD>");
/*     */     } 
/* 114 */     this.file.println("<H4><A NAME=\"" + anchor + "\">" + this.attr_count + " " + Constants.ATTRIBUTE_NAMES[tag] + "</A></H4>");
/*     */ 
/*     */ 
/*     */     
/* 118 */     switch (tag) {
/*     */       case 2:
/* 120 */         c = (Code)attribute;
/* 121 */         attributes = c.getAttributes();
/*     */ 
/*     */         
/* 124 */         this.file.print("<UL><LI>Maximum stack size = " + c.getMaxStack() + "</LI>\n<LI>Number of local variables = " + c.getMaxLocals() + "</LI>\n<LI><A HREF=\"" + this.class_name + "_code.html#method" + method_number + "\" TARGET=Code>Byte code</A></LI></UL>\n");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 130 */         ce = c.getExceptionTable();
/* 131 */         len = ce.length;
/*     */         
/* 133 */         if (len > 0) {
/* 134 */           this.file.print("<P><B>Exceptions handled</B><UL>");
/*     */           
/* 136 */           for (int n = 0; n < len; n++) {
/* 137 */             int catch_type = ce[n].getCatchType();
/*     */             
/* 139 */             this.file.print("<LI>");
/*     */             
/* 141 */             if (catch_type != 0) {
/* 142 */               this.file.print(this.constant_html.referenceConstant(catch_type));
/*     */             } else {
/* 144 */               this.file.print("Any Exception");
/*     */             } 
/* 146 */             this.file.print("<BR>(Ranging from lines " + codeLink(ce[n].getStartPC(), method_number) + " to " + codeLink(ce[n].getEndPC(), method_number) + ", handled at line " + codeLink(ce[n].getHandlerPC(), method_number) + ")</LI>");
/*     */           } 
/*     */ 
/*     */           
/* 150 */           this.file.print("</UL>");
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 1:
/* 155 */         index = ((ConstantValue)attribute).getConstantValueIndex();
/*     */ 
/*     */         
/* 158 */         this.file.print("<UL><LI><A HREF=\"" + this.class_name + "_cp.html#cp" + index + "\" TARGET=\"ConstantPool\">Constant value index(" + index + ")</A></UL>\n");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 0:
/* 163 */         index = ((SourceFile)attribute).getSourceFileIndex();
/*     */ 
/*     */         
/* 166 */         this.file.print("<UL><LI><A HREF=\"" + this.class_name + "_cp.html#cp" + index + "\" TARGET=\"ConstantPool\">Source file index(" + index + ")</A></UL>\n");
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 172 */         indices = ((ExceptionTable)attribute).getExceptionIndexTable();
/*     */         
/* 174 */         this.file.print("<UL>");
/*     */         
/* 176 */         for (i = 0; i < indices.length; i++) {
/* 177 */           this.file.print("<LI><A HREF=\"" + this.class_name + "_cp.html#cp" + indices[i] + "\" TARGET=\"ConstantPool\">Exception class index(" + indices[i] + ")</A>\n");
/*     */         }
/*     */         
/* 180 */         this.file.print("</UL>\n");
/*     */         break;
/*     */       
/*     */       case 4:
/* 184 */         line_numbers = ((LineNumberTable)attribute).getLineNumberTable();
/*     */ 
/*     */         
/* 187 */         this.file.print("<P>");
/*     */         
/* 189 */         for (j = 0; j < line_numbers.length; j++) {
/* 190 */           this.file.print("(" + line_numbers[j].getStartPC() + ",&nbsp;" + line_numbers[j].getLineNumber() + ")");
/*     */           
/* 192 */           if (j < line_numbers.length - 1) {
/* 193 */             this.file.print(", ");
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 198 */         vars = ((LocalVariableTable)attribute).getLocalVariableTable();
/*     */ 
/*     */         
/* 201 */         this.file.print("<UL>");
/*     */         
/* 203 */         for (k = 0; k < vars.length; k++) {
/* 204 */           index = vars[k].getSignatureIndex();
/* 205 */           String signature = ((ConstantUtf8)this.constant_pool.getConstant(index, (byte)1)).getBytes();
/* 206 */           signature = Utility.signatureToString(signature, false);
/* 207 */           int start = vars[k].getStartPC();
/* 208 */           int end = start + vars[k].getLength();
/*     */           
/* 210 */           this.file.println("<LI>" + Class2HTML.referenceType(signature) + "&nbsp;<B>" + vars[k].getName() + "</B> in slot %" + vars[k].getIndex() + "<BR>Valid from lines " + "<A HREF=\"" + this.class_name + "_code.html#code" + method_number + "@" + start + "\" TARGET=Code>" + start + "</A> to " + "<A HREF=\"" + this.class_name + "_code.html#code" + method_number + "@" + end + "\" TARGET=Code>" + end + "</A></LI>");
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 218 */         this.file.print("</UL>\n");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/* 223 */         classes = ((InnerClasses)attribute).getInnerClasses();
/*     */ 
/*     */         
/* 226 */         this.file.print("<UL>");
/*     */         
/* 228 */         for (m = 0; m < classes.length; m++) {
/*     */           String str1;
/*     */           
/* 231 */           index = classes[m].getInnerNameIndex();
/* 232 */           if (index > 0) {
/* 233 */             str1 = ((ConstantUtf8)this.constant_pool.getConstant(index, (byte)1)).getBytes();
/*     */           } else {
/* 235 */             str1 = "&lt;anonymous&gt;";
/*     */           } 
/* 237 */           String access = Utility.accessToString(classes[m].getInnerAccessFlags());
/*     */           
/* 239 */           this.file.print("<LI><FONT COLOR=\"#FF0000\">" + access + "</FONT> " + this.constant_html.referenceConstant(classes[m].getInnerClassIndex()) + " in&nbsp;class " + this.constant_html.referenceConstant(classes[m].getOuterClassIndex()) + " named " + str1 + "</LI>\n");
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 246 */         this.file.print("</UL>\n");
/*     */         break;
/*     */       
/*     */       default:
/* 250 */         this.file.print("<P>" + attribute.toString());
/*     */         break;
/*     */     } 
/* 253 */     this.file.println("</TD></TR>");
/* 254 */     this.file.flush(); }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/AttributeHTML.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */