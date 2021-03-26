/*     */ package org.apache.bcel.util;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.BitSet;
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.classfile.Attribute;
/*     */ import org.apache.bcel.classfile.Code;
/*     */ import org.apache.bcel.classfile.CodeException;
/*     */ import org.apache.bcel.classfile.Constant;
/*     */ import org.apache.bcel.classfile.ConstantFieldref;
/*     */ import org.apache.bcel.classfile.ConstantInterfaceMethodref;
/*     */ import org.apache.bcel.classfile.ConstantMethodref;
/*     */ import org.apache.bcel.classfile.ConstantNameAndType;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ import org.apache.bcel.classfile.LocalVariable;
/*     */ import org.apache.bcel.classfile.LocalVariableTable;
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
/*     */ final class CodeHTML
/*     */   implements Constants
/*     */ {
/*     */   private String class_name;
/*     */   private Method[] methods;
/*     */   private PrintWriter file;
/*     */   private BitSet goto_set;
/*     */   private ConstantPool constant_pool;
/*     */   private ConstantHTML constant_html;
/*     */   private static boolean wide = false;
/*     */   
/*     */   CodeHTML(String dir, String class_name, Method[] methods, ConstantPool constant_pool, ConstantHTML constant_html) throws IOException {
/*  81 */     this.class_name = class_name;
/*  82 */     this.methods = methods;
/*  83 */     this.constant_pool = constant_pool;
/*  84 */     this.constant_html = constant_html;
/*     */     
/*  86 */     this.file = new PrintWriter(new FileOutputStream(dir + class_name + "_code.html"));
/*  87 */     this.file.println("<HTML><BODY BGCOLOR=\"#C0C0C0\">");
/*     */     
/*  89 */     for (int i = 0; i < methods.length; i++) {
/*  90 */       writeMethod(methods[i], i);
/*     */     }
/*  92 */     this.file.println("</BODY></HTML>");
/*  93 */     this.file.close();
/*     */   }
/*     */   
/*     */   private final String codeToHTML(ByteSequence bytes, int method_number) throws IOException {
/*     */     String name, signature;
/*     */     int low, high, index, class_index, j, k, jump_table[], offset, i, m, npairs, n, i1, windex;
/*     */     ConstantFieldref c1;
/*     */     String field_name;
/*     */     int m_index;
/*     */     String str1;
/*     */     ConstantNameAndType c2;
/*     */     String args[], type;
/*     */     int i2, dimensions;
/* 106 */     short opcode = (short)bytes.readUnsignedByte();
/*     */ 
/*     */     
/* 109 */     int default_offset = 0;
/*     */ 
/*     */     
/* 112 */     int no_pad_bytes = 0;
/*     */     
/* 114 */     StringBuffer buf = new StringBuffer("<TT>" + Constants.OPCODE_NAMES[opcode] + "</TT></TD><TD>");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     if (opcode == 170 || opcode == 171) {
/* 120 */       int remainder = bytes.getIndex() % 4;
/* 121 */       no_pad_bytes = (remainder == 0) ? 0 : (4 - remainder);
/*     */       
/* 123 */       for (int i3 = 0; i3 < no_pad_bytes; i3++) {
/* 124 */         bytes.readByte();
/*     */       }
/*     */       
/* 127 */       default_offset = bytes.readInt();
/*     */     } 
/*     */     
/* 130 */     switch (opcode)
/*     */     { case 170:
/* 132 */         low = bytes.readInt();
/* 133 */         high = bytes.readInt();
/*     */         
/* 135 */         offset = bytes.getIndex() - 12 - no_pad_bytes - 1;
/* 136 */         default_offset += offset;
/*     */         
/* 138 */         buf.append("<TABLE BORDER=1><TR>");
/*     */ 
/*     */         
/* 141 */         jump_table = new int[high - low + 1];
/* 142 */         for (i = 0; i < jump_table.length; i++) {
/* 143 */           jump_table[i] = offset + bytes.readInt();
/*     */           
/* 145 */           buf.append("<TH>" + (low + i) + "</TH>");
/*     */         } 
/* 147 */         buf.append("<TH>default</TH></TR>\n<TR>");
/*     */ 
/*     */         
/* 150 */         for (m = 0; m < jump_table.length; m++) {
/* 151 */           buf.append("<TD><A HREF=\"#code" + method_number + "@" + jump_table[m] + "\">" + jump_table[m] + "</A></TD>");
/*     */         }
/* 153 */         buf.append("<TD><A HREF=\"#code" + method_number + "@" + default_offset + "\">" + default_offset + "</A></TD></TR>\n</TABLE>\n");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 396 */         buf.append("</TD>");
/* 397 */         return buf.toString();case 171: npairs = bytes.readInt(); offset = bytes.getIndex() - 8 - no_pad_bytes - 1; jump_table = new int[npairs]; default_offset += offset; buf.append("<TABLE BORDER=1><TR>"); for (n = 0; n < npairs; n++) { int match = bytes.readInt(); jump_table[n] = offset + bytes.readInt(); buf.append("<TH>" + match + "</TH>"); }  buf.append("<TH>default</TH></TR>\n<TR>"); for (i1 = 0; i1 < npairs; i1++) buf.append("<TD><A HREF=\"#code" + method_number + "@" + jump_table[i1] + "\">" + jump_table[i1] + "</A></TD>");  buf.append("<TD><A HREF=\"#code" + method_number + "@" + default_offset + "\">" + default_offset + "</A></TD></TR>\n</TABLE>\n"); buf.append("</TD>"); return buf.toString();case 153: case 154: case 155: case 156: case 157: case 158: case 159: case 160: case 161: case 162: case 163: case 164: case 165: case 166: case 167: case 168: case 198: case 199: index = bytes.getIndex() + bytes.readShort() - 1; buf.append("<A HREF=\"#code" + method_number + "@" + index + "\">" + index + "</A>"); buf.append("</TD>"); return buf.toString();case 200: case 201: windex = bytes.getIndex() + bytes.readInt() - 1; buf.append("<A HREF=\"#code" + method_number + "@" + windex + "\">" + windex + "</A>"); buf.append("</TD>"); return buf.toString();case 21: case 22: case 23: case 24: case 25: case 54: case 55: case 56: case 57: case 58: case 169: if (wide) { j = bytes.readShort(); wide = false; } else { j = bytes.readUnsignedByte(); }  buf.append("%" + j); buf.append("</TD>"); return buf.toString();case 196: wide = true; buf.append("(wide)"); buf.append("</TD>"); return buf.toString();case 188: buf.append("<FONT COLOR=\"#00FF00\">" + Constants.TYPE_NAMES[bytes.readByte()] + "</FONT>"); buf.append("</TD>"); return buf.toString();case 178: case 179: case 180: case 181: index = bytes.readShort(); c1 = (ConstantFieldref)this.constant_pool.getConstant(index, (byte)9); class_index = c1.getClassIndex(); name = this.constant_pool.getConstantString(class_index, (byte)7); name = Utility.compactClassName(name, false); index = c1.getNameAndTypeIndex(); field_name = this.constant_pool.constantToString(index, (byte)12); if (name.equals(this.class_name)) { buf.append("<A HREF=\"" + this.class_name + "_methods.html#field" + field_name + "\" TARGET=Methods>" + field_name + "</A>\n"); } else { buf.append(this.constant_html.referenceConstant(class_index) + "." + field_name); }  buf.append("</TD>"); return buf.toString();case 187: case 192: case 193: index = bytes.readShort(); buf.append(this.constant_html.referenceConstant(index)); buf.append("</TD>"); return buf.toString();case 182: case 183: case 184: case 185: m_index = bytes.readShort(); if (opcode == 185) { int nargs = bytes.readUnsignedByte(); int reserved = bytes.readUnsignedByte(); ConstantInterfaceMethodref c = (ConstantInterfaceMethodref)this.constant_pool.getConstant(m_index, (byte)11); class_index = c.getClassIndex(); String str = this.constant_pool.constantToString((Constant)c); index = c.getNameAndTypeIndex(); } else { ConstantMethodref c = (ConstantMethodref)this.constant_pool.getConstant(m_index, (byte)10); class_index = c.getClassIndex(); String str = this.constant_pool.constantToString((Constant)c); index = c.getNameAndTypeIndex(); }  name = Class2HTML.referenceClass(class_index); str1 = Class2HTML.toHTML(this.constant_pool.constantToString(this.constant_pool.getConstant(index, (byte)12))); c2 = (ConstantNameAndType)this.constant_pool.getConstant(index, (byte)12); signature = this.constant_pool.constantToString(c2.getSignatureIndex(), (byte)1); args = Utility.methodSignatureArgumentTypes(signature, false); type = Utility.methodSignatureReturnType(signature, false); buf.append(name + ".<A HREF=\"" + this.class_name + "_cp.html#cp" + m_index + "\" TARGET=ConstantPool>" + str1 + "</A>" + "("); for (i2 = 0; i2 < args.length; i2++) { buf.append(Class2HTML.referenceType(args[i2])); if (i2 < args.length - 1) buf.append(", ");  }  buf.append("):" + Class2HTML.referenceType(type)); buf.append("</TD>"); return buf.toString();case 19: case 20: index = bytes.readShort(); buf.append("<A HREF=\"" + this.class_name + "_cp.html#cp" + index + "\" TARGET=\"ConstantPool\">" + Class2HTML.toHTML(this.constant_pool.constantToString(index, this.constant_pool.getConstant(index).getTag())) + "</a>"); buf.append("</TD>"); return buf.toString();case 18: index = bytes.readUnsignedByte(); buf.append("<A HREF=\"" + this.class_name + "_cp.html#cp" + index + "\" TARGET=\"ConstantPool\">" + Class2HTML.toHTML(this.constant_pool.constantToString(index, this.constant_pool.getConstant(index).getTag())) + "</a>"); buf.append("</TD>"); return buf.toString();case 189: index = bytes.readShort(); buf.append(this.constant_html.referenceConstant(index)); buf.append("</TD>"); return buf.toString();case 197: index = bytes.readShort(); dimensions = bytes.readByte(); buf.append(this.constant_html.referenceConstant(index) + ":" + dimensions + "-dimensional"); buf.append("</TD>"); return buf.toString();case 132: if (wide) { j = bytes.readShort(); k = bytes.readShort(); wide = false; } else { j = bytes.readUnsignedByte(); k = bytes.readByte(); }  buf.append("%" + j + " " + k); buf.append("</TD>"); return buf.toString(); }  if (Constants.NO_OF_OPERANDS[opcode] > 0) for (int i3 = 0; i3 < (Constants.TYPE_OF_OPERANDS[opcode]).length; i3++) { switch (Constants.TYPE_OF_OPERANDS[opcode][i3]) { case 8: buf.append(bytes.readUnsignedByte()); break;case 9: buf.append(bytes.readShort()); break;case 10: buf.append(bytes.readInt()); break;default: System.err.println("Unreachable default case reached!"); System.exit(-1); break; }  buf.append("&nbsp;"); }   buf.append("</TD>"); return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void findGotos(ByteSequence bytes, Method method, Code code) throws IOException {
/* 408 */     this.goto_set = new BitSet(bytes.available());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 415 */     if (code != null) {
/* 416 */       CodeException[] ce = code.getExceptionTable();
/* 417 */       int len = ce.length;
/*     */       
/* 419 */       for (int j = 0; j < len; j++) {
/* 420 */         this.goto_set.set(ce[j].getStartPC());
/* 421 */         this.goto_set.set(ce[j].getEndPC());
/* 422 */         this.goto_set.set(ce[j].getHandlerPC());
/*     */       } 
/*     */ 
/*     */       
/* 426 */       Attribute[] attributes = code.getAttributes();
/* 427 */       for (int k = 0; k < attributes.length; k++) {
/* 428 */         if (attributes[k].getTag() == 5) {
/* 429 */           LocalVariable[] vars = ((LocalVariableTable)attributes[k]).getLocalVariableTable();
/*     */           
/* 431 */           for (int m = 0; m < vars.length; m++) {
/* 432 */             int start = vars[m].getStartPC();
/* 433 */             int end = start + vars[m].getLength();
/* 434 */             this.goto_set.set(start);
/* 435 */             this.goto_set.set(end);
/*     */           } 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 443 */     for (int i = 0; bytes.available() > 0; i++) {
/* 444 */       int k, remainder, no_pad_bytes, default_offset, m, j, npairs, n, opcode = bytes.readUnsignedByte();
/*     */       
/* 446 */       switch (opcode) {
/*     */         
/*     */         case 170:
/*     */         case 171:
/* 450 */           remainder = bytes.getIndex() % 4;
/* 451 */           no_pad_bytes = (remainder == 0) ? 0 : (4 - remainder);
/*     */ 
/*     */           
/* 454 */           for (j = 0; j < no_pad_bytes; j++) {
/* 455 */             bytes.readByte();
/*     */           }
/*     */           
/* 458 */           default_offset = bytes.readInt();
/*     */           
/* 460 */           if (opcode == 170) {
/* 461 */             int low = bytes.readInt();
/* 462 */             int high = bytes.readInt();
/*     */             
/* 464 */             int offset = bytes.getIndex() - 12 - no_pad_bytes - 1;
/* 465 */             default_offset += offset;
/* 466 */             this.goto_set.set(default_offset);
/*     */             
/* 468 */             for (int i1 = 0; i1 < high - low + 1; i1++) {
/* 469 */               int index = offset + bytes.readInt();
/* 470 */               this.goto_set.set(index);
/*     */             } 
/*     */             break;
/*     */           } 
/* 474 */           npairs = bytes.readInt();
/*     */           
/* 476 */           m = bytes.getIndex() - 8 - no_pad_bytes - 1;
/* 477 */           default_offset += m;
/* 478 */           this.goto_set.set(default_offset);
/*     */           
/* 480 */           for (n = 0; n < npairs; n++)
/* 481 */           { int match = bytes.readInt();
/*     */             
/* 483 */             int i1 = m + bytes.readInt();
/* 484 */             this.goto_set.set(i1); }  break;
/*     */         case 153: case 154: case 155: case 156: case 157: case 158: case 159: case 160: case 161:
/*     */         case 162:
/*     */         case 163:
/*     */         case 164:
/*     */         case 165:
/*     */         case 166:
/*     */         case 167:
/*     */         case 168:
/*     */         case 198:
/*     */         case 199:
/* 495 */           k = bytes.getIndex() + bytes.readShort() - 1;
/*     */           
/* 497 */           this.goto_set.set(k);
/*     */           break;
/*     */         
/*     */         case 200:
/*     */         case 201:
/* 502 */           k = bytes.getIndex() + bytes.readInt() - 1;
/* 503 */           this.goto_set.set(k);
/*     */           break;
/*     */         
/*     */         default:
/* 507 */           bytes.unreadByte();
/* 508 */           codeToHTML(bytes, 0);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeMethod(Method method, int method_number) throws IOException {
/* 520 */     String signature = method.getSignature();
/*     */     
/* 522 */     String[] args = Utility.methodSignatureArgumentTypes(signature, false);
/*     */     
/* 524 */     String type = Utility.methodSignatureReturnType(signature, false);
/*     */     
/* 526 */     String name = method.getName();
/* 527 */     String html_name = Class2HTML.toHTML(name);
/*     */     
/* 529 */     String access = Utility.accessToString(method.getAccessFlags());
/* 530 */     access = Utility.replace(access, " ", "&nbsp;");
/*     */     
/* 532 */     Attribute[] attributes = method.getAttributes();
/*     */     
/* 534 */     this.file.print("<P><B><FONT COLOR=\"#FF0000\">" + access + "</FONT>&nbsp;" + "<A NAME=method" + method_number + ">" + Class2HTML.referenceType(type) + "</A>&nbsp<A HREF=\"" + this.class_name + "_methods.html#method" + method_number + "\" TARGET=Methods>" + html_name + "</A>(");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 539 */     for (int i = 0; i < args.length; i++) {
/* 540 */       this.file.print(Class2HTML.referenceType(args[i]));
/* 541 */       if (i < args.length - 1) {
/* 542 */         this.file.print(",&nbsp;");
/*     */       }
/*     */     } 
/* 545 */     this.file.println(")</B></P>");
/*     */     
/* 547 */     Code c = null;
/* 548 */     byte[] code = null;
/*     */     
/* 550 */     if (attributes.length > 0) {
/* 551 */       this.file.print("<H4>Attributes</H4><UL>\n");
/* 552 */       for (int j = 0; j < attributes.length; j++) {
/* 553 */         byte tag = attributes[j].getTag();
/*     */         
/* 555 */         if (tag != -1) {
/* 556 */           this.file.print("<LI><A HREF=\"" + this.class_name + "_attributes.html#method" + method_number + "@" + j + "\" TARGET=Attributes>" + Constants.ATTRIBUTE_NAMES[tag] + "</A></LI>\n");
/*     */         } else {
/*     */           
/* 559 */           this.file.print("<LI>" + attributes[j] + "</LI>");
/*     */         } 
/* 561 */         if (tag == 2) {
/* 562 */           c = (Code)attributes[j];
/* 563 */           Attribute[] attributes2 = c.getAttributes();
/* 564 */           code = c.getCode();
/*     */           
/* 566 */           this.file.print("<UL>");
/* 567 */           for (int k = 0; k < attributes2.length; k++) {
/* 568 */             tag = attributes2[k].getTag();
/* 569 */             this.file.print("<LI><A HREF=\"" + this.class_name + "_attributes.html#" + "method" + method_number + "@" + j + "@" + k + "\" TARGET=Attributes>" + Constants.ATTRIBUTE_NAMES[tag] + "</A></LI>\n");
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 574 */           this.file.print("</UL>");
/*     */         } 
/*     */       } 
/* 577 */       this.file.println("</UL>");
/*     */     } 
/*     */     
/* 580 */     if (code != null) {
/*     */ 
/*     */ 
/*     */       
/* 584 */       ByteSequence stream = new ByteSequence(code);
/* 585 */       stream.mark(stream.available());
/* 586 */       findGotos(stream, method, c);
/* 587 */       stream.reset();
/*     */       
/* 589 */       this.file.println("<TABLE BORDER=0><TR><TH ALIGN=LEFT>Byte<BR>offset</TH><TH ALIGN=LEFT>Instruction</TH><TH ALIGN=LEFT>Argument</TH>");
/*     */ 
/*     */       
/* 592 */       for (int j = 0; stream.available() > 0; j++) {
/* 593 */         String str1; int offset = stream.getIndex();
/* 594 */         String str = codeToHTML(stream, method_number);
/* 595 */         String anchor = "";
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 600 */         if (this.goto_set.get(offset)) {
/* 601 */           anchor = "<A NAME=code" + method_number + "@" + offset + "></A>";
/*     */         }
/*     */         
/* 604 */         if (stream.getIndex() == code.length) {
/* 605 */           str1 = "<A NAME=code" + method_number + "@" + code.length + ">" + offset + "</A>";
/*     */         } else {
/* 607 */           str1 = "" + offset;
/*     */         } 
/* 609 */         this.file.println("<TR VALIGN=TOP><TD>" + str1 + "</TD><TD>" + anchor + str + "</TR>");
/*     */       } 
/*     */ 
/*     */       
/* 613 */       this.file.println("<TR><TD> </A></TD></TR>");
/* 614 */       this.file.println("</TABLE>");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/CodeHTML.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */