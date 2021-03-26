/*      */ package org.apache.bcel.classfile;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.CharArrayReader;
/*      */ import java.io.CharArrayWriter;
/*      */ import java.io.FilterReader;
/*      */ import java.io.FilterWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Reader;
/*      */ import java.io.Writer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.zip.GZIPInputStream;
/*      */ import java.util.zip.GZIPOutputStream;
/*      */ import org.apache.bcel.Constants;
/*      */ import org.apache.bcel.util.ByteSequence;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class Utility
/*      */ {
/*      */   private static int consumed_chars;
/*      */   private static boolean wide = false;
/*      */   private static final int FREE_CHARS = 48;
/*      */   
/*      */   public static final String accessToString(int access_flags) {
/*   91 */     return accessToString(access_flags, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String accessToString(int access_flags, boolean for_class) {
/*  109 */     StringBuffer buf = new StringBuffer();
/*      */     
/*  111 */     int p = 0;
/*  112 */     for (int i = 0; p < 1024; i++) {
/*  113 */       p = pow2(i);
/*      */       
/*  115 */       if ((access_flags & p) != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  121 */         if (!for_class || (p != 32 && p != 512))
/*      */         {
/*      */           
/*  124 */           buf.append(Constants.ACCESS_NAMES[i] + " ");
/*      */         }
/*      */       }
/*      */     } 
/*  128 */     return buf.toString().trim();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String classOrInterface(int access_flags) {
/*  135 */     return ((access_flags & 0x200) != 0) ? "interface" : "class";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String codeToString(byte[] code, ConstantPool constant_pool, int index, int length, boolean verbose) {
/*  156 */     StringBuffer buf = new StringBuffer(code.length * 20);
/*  157 */     ByteSequence stream = new ByteSequence(code);
/*      */     
/*      */     try {
/*  160 */       for (int i = 0; i < index; i++) {
/*  161 */         codeToString(stream, constant_pool, verbose);
/*      */       }
/*  163 */       for (int j = 0; stream.available() > 0; j++) {
/*  164 */         if (length < 0 || j < length) {
/*  165 */           String indices = fillup(stream.getIndex() + ":", 6, true, ' ');
/*  166 */           buf.append(indices + codeToString(stream, constant_pool, verbose) + '\n');
/*      */         } 
/*      */       } 
/*      */     } catch (IOException e) {
/*  170 */       System.out.println(buf.toString());
/*  171 */       e.printStackTrace();
/*  172 */       throw new ClassFormatError("Byte code error: " + e);
/*      */     } 
/*      */     
/*  175 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String codeToString(byte[] code, ConstantPool constant_pool, int index, int length) {
/*  181 */     return codeToString(code, constant_pool, index, length, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String codeToString(ByteSequence bytes, ConstantPool constant_pool, boolean verbose) throws IOException {
/*      */     int low, high, npairs, index, j, k, match[], jump_table[], offset, i, m, nargs, dimensions;
/*  197 */     short opcode = (short)bytes.readUnsignedByte();
/*  198 */     int default_offset = 0;
/*      */ 
/*      */     
/*  201 */     int no_pad_bytes = 0;
/*  202 */     StringBuffer buf = new StringBuffer(Constants.OPCODE_NAMES[opcode]);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  207 */     if (opcode == 170 || opcode == 171) {
/*  208 */       int remainder = bytes.getIndex() % 4;
/*  209 */       no_pad_bytes = (remainder == 0) ? 0 : (4 - remainder);
/*      */       
/*  211 */       for (int n = 0; n < no_pad_bytes; n++) {
/*      */         byte b;
/*      */         
/*  214 */         if ((b = bytes.readByte()) != 0) {
/*  215 */           System.err.println("Warning: Padding byte != 0 in " + Constants.OPCODE_NAMES[opcode] + ":" + b);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  220 */       default_offset = bytes.readInt();
/*      */     } 
/*      */     
/*  223 */     switch (opcode)
/*      */     
/*      */     { 
/*      */       case 170:
/*  227 */         low = bytes.readInt();
/*  228 */         high = bytes.readInt();
/*      */         
/*  230 */         offset = bytes.getIndex() - 12 - no_pad_bytes - 1;
/*  231 */         default_offset += offset;
/*      */         
/*  233 */         buf.append("\tdefault = " + default_offset + ", low = " + low + ", high = " + high + "(");
/*      */ 
/*      */         
/*  236 */         jump_table = new int[high - low + 1];
/*  237 */         for (i = 0; i < jump_table.length; i++) {
/*  238 */           jump_table[i] = offset + bytes.readInt();
/*  239 */           buf.append(jump_table[i]);
/*      */           
/*  241 */           if (i < jump_table.length - 1)
/*  242 */             buf.append(", "); 
/*      */         } 
/*  244 */         buf.append(")");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  437 */         return buf.toString();case 171: npairs = bytes.readInt(); offset = bytes.getIndex() - 8 - no_pad_bytes - 1; match = new int[npairs]; jump_table = new int[npairs]; default_offset += offset; buf.append("\tdefault = " + default_offset + ", npairs = " + npairs + " ("); for (m = 0; m < npairs; m++) { match[m] = bytes.readInt(); jump_table[m] = offset + bytes.readInt(); buf.append("(" + match[m] + ", " + jump_table[m] + ")"); if (m < npairs - 1) buf.append(", ");  }  buf.append(")"); return buf.toString();case 153: case 154: case 155: case 156: case 157: case 158: case 159: case 160: case 161: case 162: case 163: case 164: case 165: case 166: case 167: case 168: case 198: case 199: buf.append("\t\t#" + (bytes.getIndex() - 1 + bytes.readShort())); return buf.toString();case 200: case 201: buf.append("\t\t#" + (bytes.getIndex() - 1 + bytes.readInt())); return buf.toString();case 21: case 22: case 23: case 24: case 25: case 54: case 55: case 56: case 57: case 58: case 169: if (wide) { j = bytes.readUnsignedShort(); wide = false; } else { j = bytes.readUnsignedByte(); }  buf.append("\t\t%" + j); return buf.toString();case 196: wide = true; buf.append("\t(wide)"); return buf.toString();case 188: buf.append("\t\t<" + Constants.TYPE_NAMES[bytes.readByte()] + ">"); return buf.toString();case 178: case 179: case 180: case 181: index = bytes.readUnsignedShort(); buf.append("\t\t" + constant_pool.constantToString(index, (byte)9) + (verbose ? (" (" + index + ")") : "")); return buf.toString();case 187: case 192: buf.append("\t");case 193: index = bytes.readUnsignedShort(); buf.append("\t<" + constant_pool.constantToString(index, (byte)7) + ">" + (verbose ? (" (" + index + ")") : "")); return buf.toString();case 182: case 183: case 184: index = bytes.readUnsignedShort(); buf.append("\t" + constant_pool.constantToString(index, (byte)10) + (verbose ? (" (" + index + ")") : "")); return buf.toString();case 185: index = bytes.readUnsignedShort(); nargs = bytes.readUnsignedByte(); buf.append("\t" + constant_pool.constantToString(index, (byte)11) + (verbose ? (" (" + index + ")\t") : "") + nargs + "\t" + bytes.readUnsignedByte()); return buf.toString();case 19: case 20: index = bytes.readUnsignedShort(); buf.append("\t\t" + constant_pool.constantToString(index, constant_pool.getConstant(index).getTag()) + (verbose ? (" (" + index + ")") : "")); return buf.toString();case 18: index = bytes.readUnsignedByte(); buf.append("\t\t" + constant_pool.constantToString(index, constant_pool.getConstant(index).getTag()) + (verbose ? (" (" + index + ")") : "")); return buf.toString();case 189: index = bytes.readUnsignedShort(); buf.append("\t\t<" + compactClassName(constant_pool.getConstantString(index, (byte)7), false) + ">" + (verbose ? (" (" + index + ")") : "")); return buf.toString();case 197: index = bytes.readUnsignedShort(); dimensions = bytes.readUnsignedByte(); buf.append("\t<" + compactClassName(constant_pool.getConstantString(index, (byte)7), false) + ">\t" + dimensions + (verbose ? (" (" + index + ")") : "")); return buf.toString();case 132: if (wide) { j = bytes.readUnsignedShort(); k = bytes.readShort(); wide = false; } else { j = bytes.readUnsignedByte(); k = bytes.readByte(); }  buf.append("\t\t%" + j + "\t" + k); return buf.toString(); }  if (Constants.NO_OF_OPERANDS[opcode] > 0) for (int n = 0; n < (Constants.TYPE_OF_OPERANDS[opcode]).length; n++) { buf.append("\t\t"); switch (Constants.TYPE_OF_OPERANDS[opcode][n]) { case 8: buf.append(bytes.readByte()); break;case 9: buf.append(bytes.readShort()); break;case 10: buf.append(bytes.readInt()); break;default: System.err.println("Unreachable default case reached!"); System.exit(-1); break; }  }   return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String codeToString(ByteSequence bytes, ConstantPool constant_pool) throws IOException {
/*  443 */     return codeToString(bytes, constant_pool, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String compactClassName(String str) {
/*  454 */     return compactClassName(str, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String compactClassName(String str, String prefix, boolean chopit) {
/*  472 */     int len = prefix.length();
/*      */     
/*  474 */     str = str.replace('/', '.');
/*      */     
/*  476 */     if (chopit)
/*      */     {
/*  478 */       if (str.startsWith(prefix) && str.substring(len).indexOf('.') == -1)
/*      */       {
/*  480 */         str = str.substring(len);
/*      */       }
/*      */     }
/*  483 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String compactClassName(String str, boolean chopit) {
/*  497 */     return compactClassName(str, "java.lang.", chopit);
/*      */   }
/*      */   
/*      */   private static final boolean is_digit(char ch) {
/*  501 */     return (ch >= '0' && ch <= '9');
/*      */   }
/*      */   
/*      */   private static final boolean is_space(char ch) {
/*  505 */     return (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int setBit(int flag, int i) {
/*  512 */     return flag | pow2(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int clearBit(int flag, int i) {
/*  519 */     int bit = pow2(i);
/*  520 */     return ((flag & bit) == 0) ? flag : (flag ^ bit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean isSet(int flag, int i) {
/*  527 */     return ((flag & pow2(i)) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String methodTypeToSignature(String ret, String[] argv) throws ClassFormatError {
/*  541 */     StringBuffer buf = new StringBuffer("(");
/*      */ 
/*      */     
/*  544 */     if (argv != null) {
/*  545 */       for (int i = 0; i < argv.length; i++) {
/*  546 */         String str1 = getSignature(argv[i]);
/*      */         
/*  548 */         if (str1.endsWith("V")) {
/*  549 */           throw new ClassFormatError("Invalid type: " + argv[i]);
/*      */         }
/*  551 */         buf.append(str1);
/*      */       } 
/*      */     }
/*  554 */     String str = getSignature(ret);
/*      */     
/*  556 */     buf.append(")" + str);
/*      */     
/*  558 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String[] methodSignatureArgumentTypes(String signature) throws ClassFormatError {
/*  569 */     return methodSignatureArgumentTypes(signature, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String[] methodSignatureArgumentTypes(String signature, boolean chopit) throws ClassFormatError {
/*  582 */     ArrayList vec = new ArrayList();
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  587 */       if (signature.charAt(0) != '(') {
/*  588 */         throw new ClassFormatError("Invalid method signature: " + signature);
/*      */       }
/*  590 */       int index = 1;
/*      */       
/*  592 */       while (signature.charAt(index) != ')') {
/*  593 */         vec.add(signatureToString(signature.substring(index), chopit));
/*  594 */         index += consumed_chars;
/*      */       } 
/*      */     } catch (StringIndexOutOfBoundsException e) {
/*  597 */       throw new ClassFormatError("Invalid method signature: " + signature);
/*      */     } 
/*      */     
/*  600 */     String[] types = new String[vec.size()];
/*  601 */     vec.toArray(types);
/*  602 */     return types;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String methodSignatureReturnType(String signature) throws ClassFormatError {
/*  612 */     return methodSignatureReturnType(signature, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String methodSignatureReturnType(String signature, boolean chopit) throws ClassFormatError {
/*      */     String type;
/*      */     try {
/*  629 */       int index = signature.lastIndexOf(')') + 1;
/*  630 */       type = signatureToString(signature.substring(index), chopit);
/*      */     } catch (StringIndexOutOfBoundsException e) {
/*  632 */       throw new ClassFormatError("Invalid method signature: " + signature);
/*      */     } 
/*      */     
/*  635 */     return type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String methodSignatureToString(String signature, String name, String access) {
/*  649 */     return methodSignatureToString(signature, name, access, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String methodSignatureToString(String signature, String name, String access, boolean chopit) {
/*  656 */     return methodSignatureToString(signature, name, access, chopit, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String methodSignatureToString(String signature, String name, String access, boolean chopit, LocalVariableTable vars) throws ClassFormatError {
/*      */     String type;
/*  693 */     StringBuffer buf = new StringBuffer("(");
/*      */ 
/*      */     
/*  696 */     int var_index = (access.indexOf("static") >= 0) ? 0 : 1;
/*      */     
/*      */     try {
/*  699 */       if (signature.charAt(0) != '(') {
/*  700 */         throw new ClassFormatError("Invalid method signature: " + signature);
/*      */       }
/*  702 */       int index = 1;
/*      */       
/*  704 */       while (signature.charAt(index) != ')') {
/*  705 */         buf.append(signatureToString(signature.substring(index), chopit));
/*      */         
/*  707 */         if (vars != null) {
/*  708 */           LocalVariable l = vars.getLocalVariable(var_index);
/*      */           
/*  710 */           if (l != null)
/*  711 */             buf.append(" " + l.getName()); 
/*      */         } else {
/*  713 */           buf.append(" arg" + var_index);
/*      */         } 
/*  715 */         var_index++;
/*  716 */         buf.append(", ");
/*  717 */         index += consumed_chars;
/*      */       } 
/*      */       
/*  720 */       index++;
/*      */ 
/*      */       
/*  723 */       type = signatureToString(signature.substring(index), chopit);
/*      */     } catch (StringIndexOutOfBoundsException e) {
/*      */       
/*  726 */       throw new ClassFormatError("Invalid method signature: " + signature);
/*      */     } 
/*      */     
/*  729 */     if (buf.length() > 1) {
/*  730 */       buf.setLength(buf.length() - 2);
/*      */     }
/*  732 */     buf.append(")");
/*      */     
/*  734 */     return access + ((access.length() > 0) ? " " : "") + type + " " + name + buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int pow2(int n) {
/*  740 */     return 1 << n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String replace(String str, String old, String new_) {
/*  753 */     StringBuffer buf = new StringBuffer();
/*      */     try {
/*      */       int index;
/*  756 */       if ((index = str.indexOf(old)) != -1) {
/*  757 */         int old_index = 0;
/*      */ 
/*      */         
/*  760 */         while ((index = str.indexOf(old, old_index)) != -1) {
/*  761 */           buf.append(str.substring(old_index, index));
/*  762 */           buf.append(new_);
/*      */           
/*  764 */           old_index = index + old.length();
/*      */         } 
/*      */         
/*  767 */         buf.append(str.substring(old_index));
/*  768 */         str = buf.toString();
/*      */       } 
/*      */     } catch (StringIndexOutOfBoundsException e) {
/*  771 */       System.err.println(e);
/*      */     } 
/*      */     
/*  774 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String signatureToString(String signature) {
/*  784 */     return signatureToString(signature, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String signatureToString(String signature, boolean chopit) {
/*  824 */     Utility.consumed_chars = 1; try {
/*      */       int index; int n; StringBuffer brackets; String type;
/*      */       int consumed_chars;
/*  827 */       switch (signature.charAt(0)) { case 'B':
/*  828 */           return "byte";
/*  829 */         case 'C': return "char";
/*  830 */         case 'D': return "double";
/*  831 */         case 'F': return "float";
/*  832 */         case 'I': return "int";
/*  833 */         case 'J': return "long";
/*      */         
/*      */         case 'L':
/*  836 */           index = signature.indexOf(';');
/*      */           
/*  838 */           if (index < 0) {
/*  839 */             throw new ClassFormatError("Invalid signature: " + signature);
/*      */           }
/*  841 */           Utility.consumed_chars = index + 1;
/*      */           
/*  843 */           return compactClassName(signature.substring(1, index), chopit);
/*      */         
/*      */         case 'S':
/*  846 */           return "short";
/*  847 */         case 'Z': return "boolean";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case '[':
/*  856 */           brackets = new StringBuffer();
/*      */ 
/*      */           
/*  859 */           for (n = 0; signature.charAt(n) == '['; n++) {
/*  860 */             brackets.append("[]");
/*      */           }
/*  862 */           consumed_chars = n;
/*      */ 
/*      */           
/*  865 */           type = signatureToString(signature.substring(n), chopit);
/*      */           
/*  867 */           Utility.consumed_chars += consumed_chars;
/*  868 */           return type + brackets.toString();
/*      */         
/*      */         case 'V':
/*  871 */           return "void"; }
/*      */       
/*  873 */       throw new ClassFormatError("Invalid signature: `" + signature + "'");
/*      */     }
/*      */     catch (StringIndexOutOfBoundsException e) {
/*      */       
/*  877 */       throw new ClassFormatError("Invalid signature: " + e + ":" + signature);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getSignature(String type) {
/*  888 */     StringBuffer buf = new StringBuffer();
/*  889 */     char[] chars = type.toCharArray();
/*  890 */     boolean char_found = false, delim = false;
/*  891 */     int index = -1;
/*      */ 
/*      */     
/*  894 */     for (int i = 0; i < chars.length; i++) {
/*  895 */       switch (chars[i]) { case '\t': case '\n': case '\f': case '\r':
/*      */         case ' ':
/*  897 */           if (char_found) {
/*  898 */             delim = true;
/*      */           }
/*      */           break;
/*      */         case '[':
/*  902 */           if (!char_found) {
/*  903 */             throw new RuntimeException("Illegal type: " + type);
/*      */           }
/*  905 */           index = i;
/*      */           break;
/*      */         
/*      */         default:
/*  909 */           char_found = true;
/*  910 */           if (!delim)
/*  911 */             buf.append(chars[i]); 
/*      */           break; }
/*      */     
/*      */     } 
/*  915 */     int brackets = 0;
/*      */     
/*  917 */     if (index > 0) {
/*  918 */       brackets = countBrackets(type.substring(index));
/*      */     }
/*  920 */     type = buf.toString();
/*  921 */     buf.setLength(0);
/*      */     
/*  923 */     for (int j = 0; j < brackets; j++) {
/*  924 */       buf.append('[');
/*      */     }
/*  926 */     boolean found = false;
/*      */     
/*  928 */     for (int k = 4; k <= 12 && !found; k++) {
/*  929 */       if (Constants.TYPE_NAMES[k].equals(type)) {
/*  930 */         found = true;
/*  931 */         buf.append(Constants.SHORT_TYPE_NAMES[k]);
/*      */       } 
/*      */     } 
/*      */     
/*  935 */     if (!found) {
/*  936 */       buf.append('L' + type.replace('.', '/') + ';');
/*      */     }
/*  938 */     return buf.toString();
/*      */   }
/*      */   
/*      */   private static int countBrackets(String brackets) {
/*  942 */     char[] chars = brackets.toCharArray();
/*  943 */     int count = 0;
/*  944 */     boolean open = false;
/*      */     
/*  946 */     for (int i = 0; i < chars.length; i++) {
/*  947 */       switch (chars[i]) {
/*      */         case '[':
/*  949 */           if (open)
/*  950 */             throw new RuntimeException("Illegally nested brackets:" + brackets); 
/*  951 */           open = true;
/*      */           break;
/*      */         
/*      */         case ']':
/*  955 */           if (!open)
/*  956 */             throw new RuntimeException("Illegally nested brackets:" + brackets); 
/*  957 */           open = false;
/*  958 */           count++;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/*  966 */     if (open) {
/*  967 */       throw new RuntimeException("Illegally nested brackets:" + brackets);
/*      */     }
/*  969 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte typeOfMethodSignature(String signature) throws ClassFormatError {
/*      */     try {
/*  985 */       if (signature.charAt(0) != '(') {
/*  986 */         throw new ClassFormatError("Invalid method signature: " + signature);
/*      */       }
/*  988 */       int index = signature.lastIndexOf(')') + 1;
/*  989 */       return typeOfSignature(signature.substring(index));
/*      */     } catch (StringIndexOutOfBoundsException e) {
/*  991 */       throw new ClassFormatError("Invalid method signature: " + signature);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte typeOfSignature(String signature) throws ClassFormatError {
/*      */     try {
/* 1006 */       switch (signature.charAt(0)) { case 'B':
/* 1007 */           return 8;
/* 1008 */         case 'C': return 5;
/* 1009 */         case 'D': return 7;
/* 1010 */         case 'F': return 6;
/* 1011 */         case 'I': return 10;
/* 1012 */         case 'J': return 11;
/* 1013 */         case 'L': return 14;
/* 1014 */         case '[': return 13;
/* 1015 */         case 'V': return 12;
/* 1016 */         case 'Z': return 4;
/* 1017 */         case 'S': return 9; }
/*      */       
/* 1019 */       throw new ClassFormatError("Invalid method signature: " + signature);
/*      */     } catch (StringIndexOutOfBoundsException e) {
/*      */       
/* 1022 */       throw new ClassFormatError("Invalid method signature: " + signature);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static short searchOpcode(String name) {
/* 1029 */     name = name.toLowerCase();
/*      */     
/* 1031 */     for (short i = 0; i < Constants.OPCODE_NAMES.length; i = (short)(i + 1)) {
/* 1032 */       if (Constants.OPCODE_NAMES[i].equals(name))
/* 1033 */         return i; 
/*      */     } 
/* 1035 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final short byteToShort(byte b) {
/* 1043 */     return (b < 0) ? (short)(256 + b) : (short)b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String toHexString(byte[] bytes) {
/* 1051 */     StringBuffer buf = new StringBuffer();
/*      */     
/* 1053 */     for (int i = 0; i < bytes.length; i++) {
/* 1054 */       short b = byteToShort(bytes[i]);
/* 1055 */       String hex = Integer.toString(b, 16);
/*      */       
/* 1057 */       if (b < 16) {
/* 1058 */         buf.append('0');
/*      */       }
/* 1060 */       buf.append(hex);
/*      */       
/* 1062 */       if (i < bytes.length - 1) {
/* 1063 */         buf.append(' ');
/*      */       }
/*      */     } 
/* 1066 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String format(int i, int length, boolean left_justify, char fill) {
/* 1080 */     return fillup(Integer.toString(i), length, left_justify, fill);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String fillup(String str, int length, boolean left_justify, char fill) {
/* 1093 */     int len = length - str.length();
/* 1094 */     char[] buf = new char[(len < 0) ? 0 : len];
/*      */     
/* 1096 */     for (int j = 0; j < buf.length; j++) {
/* 1097 */       buf[j] = fill;
/*      */     }
/* 1099 */     if (left_justify) {
/* 1100 */       return str + new String(buf);
/*      */     }
/* 1102 */     return new String(buf) + str;
/*      */   }
/*      */ 
/*      */   
/*      */   static final boolean equals(byte[] a, byte[] b) {
/*      */     int size;
/* 1108 */     if ((size = a.length) != b.length) {
/* 1109 */       return false;
/*      */     }
/* 1111 */     for (int i = 0; i < size; i++) {
/* 1112 */       if (a[i] != b[i])
/* 1113 */         return false; 
/*      */     } 
/* 1115 */     return true;
/*      */   }
/*      */   
/*      */   public static final void printArray(PrintStream out, Object[] obj) {
/* 1119 */     out.println(printArray(obj, true));
/*      */   }
/*      */   
/*      */   public static final void printArray(PrintWriter out, Object[] obj) {
/* 1123 */     out.println(printArray(obj, true));
/*      */   }
/*      */   
/*      */   public static final String printArray(Object[] obj) {
/* 1127 */     return printArray(obj, true);
/*      */   }
/*      */   
/*      */   public static final String printArray(Object[] obj, boolean braces) {
/* 1131 */     if (obj == null) {
/* 1132 */       return null;
/*      */     }
/* 1134 */     StringBuffer buf = new StringBuffer();
/* 1135 */     if (braces) {
/* 1136 */       buf.append('{');
/*      */     }
/* 1138 */     for (int i = 0; i < obj.length; i++) {
/* 1139 */       if (obj[i] != null) {
/* 1140 */         buf.append(obj[i].toString());
/*      */       } else {
/* 1142 */         buf.append("null");
/*      */       } 
/* 1144 */       if (i < obj.length - 1) {
/* 1145 */         buf.append(", ");
/*      */       }
/*      */     } 
/* 1148 */     if (braces) {
/* 1149 */       buf.append('}');
/*      */     }
/* 1151 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isJavaIdentifierPart(char ch) {
/* 1157 */     return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9') || ch == '_');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String encode(byte[] bytes, boolean compress) throws IOException {
/* 1180 */     if (compress) {
/* 1181 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 1182 */       GZIPOutputStream gos = new GZIPOutputStream(baos);
/*      */       
/* 1184 */       gos.write(bytes, 0, bytes.length);
/* 1185 */       gos.close();
/* 1186 */       baos.close();
/*      */       
/* 1188 */       bytes = baos.toByteArray();
/*      */     } 
/*      */     
/* 1191 */     CharArrayWriter caw = new CharArrayWriter();
/* 1192 */     JavaWriter jw = new JavaWriter(caw);
/*      */     
/* 1194 */     for (int i = 0; i < bytes.length; i++) {
/* 1195 */       int in = bytes[i] & 0xFF;
/* 1196 */       jw.write(in);
/*      */     } 
/*      */     
/* 1199 */     return caw.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] decode(String s, boolean uncompress) throws IOException {
/* 1208 */     char[] chars = s.toCharArray();
/*      */     
/* 1210 */     CharArrayReader car = new CharArrayReader(chars);
/* 1211 */     JavaReader jr = new JavaReader(car);
/*      */     
/* 1213 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*      */     
/*      */     int ch;
/*      */     
/* 1217 */     while ((ch = jr.read()) >= 0) {
/* 1218 */       bos.write(ch);
/*      */     }
/*      */     
/* 1221 */     bos.close();
/* 1222 */     car.close();
/* 1223 */     jr.close();
/*      */     
/* 1225 */     byte[] bytes = bos.toByteArray();
/*      */     
/* 1227 */     if (uncompress) {
/* 1228 */       GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
/*      */       
/* 1230 */       byte[] tmp = new byte[bytes.length * 3];
/* 1231 */       int count = 0;
/*      */       
/*      */       int b;
/* 1234 */       while ((b = gis.read()) >= 0) {
/* 1235 */         tmp[count++] = (byte)b;
/*      */       }
/* 1237 */       bytes = new byte[count];
/* 1238 */       System.arraycopy(tmp, 0, bytes, 0, count);
/*      */     } 
/*      */     
/* 1241 */     return bytes;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1246 */   private static int[] CHAR_MAP = new int[48];
/* 1247 */   private static int[] MAP_CHAR = new int[256];
/*      */   private static final char ESCAPE_CHAR = '$';
/*      */   
/*      */   static {
/* 1251 */     int j = 0, k = 0;
/* 1252 */     for (int i = 65; i <= 90; i++) {
/* 1253 */       CHAR_MAP[j] = i;
/* 1254 */       MAP_CHAR[i] = j;
/* 1255 */       j++;
/*      */     } 
/*      */     
/* 1258 */     for (int m = 103; m <= 122; m++) {
/* 1259 */       CHAR_MAP[j] = m;
/* 1260 */       MAP_CHAR[m] = j;
/* 1261 */       j++;
/*      */     } 
/*      */     
/* 1264 */     CHAR_MAP[j] = 36;
/* 1265 */     MAP_CHAR[36] = j;
/* 1266 */     j++;
/*      */     
/* 1268 */     CHAR_MAP[j] = 95;
/* 1269 */     MAP_CHAR[95] = j;
/*      */   }
/*      */ 
/*      */   
/*      */   private static class JavaReader
/*      */     extends FilterReader
/*      */   {
/*      */     public JavaReader(Reader in) {
/* 1277 */       super(in);
/*      */     }
/*      */     
/*      */     public int read() throws IOException {
/* 1281 */       int b = this.in.read();
/*      */       
/* 1283 */       if (b != 36) {
/* 1284 */         return b;
/*      */       }
/* 1286 */       int i = this.in.read();
/*      */       
/* 1288 */       if (i < 0) {
/* 1289 */         return -1;
/*      */       }
/* 1291 */       if ((i >= 48 && i <= 57) || (i >= 97 && i <= 102)) {
/* 1292 */         int j = this.in.read();
/*      */         
/* 1294 */         if (j < 0) {
/* 1295 */           return -1;
/*      */         }
/* 1297 */         char[] tmp = { (char)i, (char)j };
/* 1298 */         int s = Integer.parseInt(new String(tmp), 16);
/*      */         
/* 1300 */         return s;
/*      */       } 
/* 1302 */       return Utility.MAP_CHAR[i];
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int read(char[] cbuf, int off, int len) throws IOException {
/* 1308 */       for (int i = 0; i < len; i++) {
/* 1309 */         cbuf[off + i] = (char)read();
/*      */       }
/* 1311 */       return len;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static class JavaWriter
/*      */     extends FilterWriter
/*      */   {
/*      */     public JavaWriter(Writer out) {
/* 1320 */       super(out);
/*      */     }
/*      */     
/*      */     public void write(int b) throws IOException {
/* 1324 */       if (Utility.isJavaIdentifierPart((char)b) && b != 36) {
/* 1325 */         this.out.write(b);
/*      */       } else {
/* 1327 */         this.out.write(36);
/*      */ 
/*      */         
/* 1330 */         if (b >= 0 && b < 48) {
/* 1331 */           this.out.write(Utility.CHAR_MAP[b]);
/*      */         } else {
/* 1333 */           char[] tmp = Integer.toHexString(b).toCharArray();
/*      */           
/* 1335 */           if (tmp.length == 1) {
/* 1336 */             this.out.write(48);
/* 1337 */             this.out.write(tmp[0]);
/*      */           } else {
/* 1339 */             this.out.write(tmp[0]);
/* 1340 */             this.out.write(tmp[1]);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public void write(char[] cbuf, int off, int len) throws IOException {
/* 1347 */       for (int i = 0; i < len; i++)
/* 1348 */         write(cbuf[off + i]); 
/*      */     }
/*      */     
/*      */     public void write(String str, int off, int len) throws IOException {
/* 1352 */       write(str.toCharArray(), off, len);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/Utility.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */