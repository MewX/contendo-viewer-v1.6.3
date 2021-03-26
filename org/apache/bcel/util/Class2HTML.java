/*     */ package org.apache.bcel.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.classfile.Attribute;
/*     */ import org.apache.bcel.classfile.ClassParser;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ import org.apache.bcel.classfile.JavaClass;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Class2HTML
/*     */   implements Constants
/*     */ {
/*     */   private JavaClass java_class;
/*     */   private String dir;
/*     */   private static String class_package;
/*     */   private static String class_name;
/*     */   private static ConstantPool constant_pool;
/*     */   
/*     */   public Class2HTML(JavaClass java_class, String dir) throws IOException {
/* 100 */     Method[] methods = java_class.getMethods();
/*     */     
/* 102 */     this.java_class = java_class;
/* 103 */     this.dir = dir;
/* 104 */     class_name = java_class.getClassName();
/* 105 */     constant_pool = java_class.getConstantPool();
/*     */ 
/*     */     
/* 108 */     int index = class_name.lastIndexOf('.');
/* 109 */     if (index > -1) {
/* 110 */       class_package = class_name.substring(0, index);
/*     */     } else {
/* 112 */       class_package = "";
/*     */     } 
/* 114 */     ConstantHTML constant_html = new ConstantHTML(dir, class_name, class_package, methods, constant_pool);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     AttributeHTML attribute_html = new AttributeHTML(dir, class_name, constant_pool, constant_html);
/*     */     
/* 122 */     MethodHTML method_html = new MethodHTML(dir, class_name, methods, java_class.getFields(), constant_html, attribute_html);
/*     */ 
/*     */     
/* 125 */     writeMainHTML(attribute_html);
/* 126 */     new CodeHTML(dir, class_name, methods, constant_pool, constant_html);
/* 127 */     attribute_html.close();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] argv) {
/* 132 */     String[] file_name = new String[argv.length];
/* 133 */     int files = 0;
/* 134 */     ClassParser parser = null;
/* 135 */     JavaClass java_class = null;
/* 136 */     String zip_file = null;
/* 137 */     char sep = System.getProperty("file.separator").toCharArray()[0];
/* 138 */     String dir = "." + sep;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 143 */       for (int i = 0; i < argv.length; i++) {
/* 144 */         if (argv[i].charAt(0) == '-') {
/* 145 */           if (argv[i].equals("-d")) {
/* 146 */             dir = argv[++i];
/*     */             
/* 148 */             if (!dir.endsWith("" + sep)) {
/* 149 */               dir = dir + sep;
/*     */             }
/* 151 */             (new File(dir)).mkdirs();
/*     */           }
/* 153 */           else if (argv[i].equals("-zip")) {
/* 154 */             zip_file = argv[++i];
/*     */           } else {
/* 156 */             System.out.println("Unknown option " + argv[i]);
/*     */           } 
/*     */         } else {
/* 159 */           file_name[files++] = argv[i];
/*     */         } 
/*     */       } 
/* 162 */       if (files == 0) {
/* 163 */         System.err.println("Class2HTML: No input files specified.");
/*     */       } else {
/* 165 */         for (int j = 0; j < files; j++) {
/* 166 */           System.out.print("Processing " + file_name[j] + "...");
/* 167 */           if (zip_file == null) {
/* 168 */             parser = new ClassParser(file_name[j]);
/*     */           } else {
/* 170 */             parser = new ClassParser(zip_file, file_name[j]);
/*     */           } 
/* 172 */           java_class = parser.parse();
/* 173 */           new Class2HTML(java_class, dir);
/* 174 */           System.out.println("Done.");
/*     */         } 
/*     */       } 
/*     */     } catch (Exception e) {
/* 178 */       System.out.println(e);
/* 179 */       e.printStackTrace(System.out);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String referenceClass(int index) {
/* 188 */     String str = constant_pool.getConstantString(index, (byte)7);
/* 189 */     str = Utility.compactClassName(str);
/* 190 */     str = Utility.compactClassName(str, class_package + ".", true);
/*     */     
/* 192 */     return "<A HREF=\"" + class_name + "_cp.html#cp" + index + "\" TARGET=ConstantPool>" + str + "</A>";
/*     */   }
/*     */ 
/*     */   
/*     */   static final String referenceType(String type) {
/* 197 */     String short_type = Utility.compactClassName(type);
/* 198 */     short_type = Utility.compactClassName(short_type, class_package + ".", true);
/*     */     
/* 200 */     int index = type.indexOf('[');
/* 201 */     if (index > -1) {
/* 202 */       type = type.substring(0, index);
/*     */     }
/*     */     
/* 205 */     if (type.equals("int") || type.equals("short") || type.equals("boolean") || type.equals("void") || type.equals("char") || type.equals("byte") || type.equals("long") || type.equals("double") || type.equals("float"))
/*     */     {
/*     */       
/* 208 */       return "<FONT COLOR=\"#00FF00\">" + type + "</FONT>";
/*     */     }
/* 210 */     return "<A HREF=\"" + type + ".html\" TARGET=_top>" + short_type + "</A>";
/*     */   }
/*     */   
/*     */   static String toHTML(String str) {
/* 214 */     StringBuffer buf = new StringBuffer();
/*     */     
/*     */     try {
/* 217 */       for (int i = 0; i < str.length(); i++) {
/*     */         char ch;
/*     */         
/* 220 */         switch (ch = str.charAt(i)) { case '<':
/* 221 */             buf.append("&lt;"); break;
/* 222 */           case '>': buf.append("&gt;"); break;
/* 223 */           case '\n': buf.append("\\n"); break;
/* 224 */           case '\r': buf.append("\\r"); break;
/* 225 */           default: buf.append(ch); break; }
/*     */       
/*     */       } 
/* 228 */     } catch (StringIndexOutOfBoundsException e) {}
/*     */     
/* 230 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private void writeMainHTML(AttributeHTML attribute_html) throws IOException {
/* 234 */     PrintWriter file = new PrintWriter(new FileOutputStream(this.dir + class_name + ".html"));
/* 235 */     Attribute[] attributes = this.java_class.getAttributes();
/*     */     
/* 237 */     file.println("<HTML>\n<HEAD><TITLE>Documentation for " + class_name + "</TITLE>" + "</HEAD>\n" + "<FRAMESET BORDER=1 cols=\"30%,*\">\n" + "<FRAMESET BORDER=1 rows=\"80%,*\">\n" + "<FRAME NAME=\"ConstantPool\" SRC=\"" + class_name + "_cp.html" + "\"\n MARGINWIDTH=\"0\" " + "MARGINHEIGHT=\"0\" FRAMEBORDER=\"1\" SCROLLING=\"AUTO\">\n" + "<FRAME NAME=\"Attributes\" SRC=\"" + class_name + "_attributes.html" + "\"\n MARGINWIDTH=\"0\" " + "MARGINHEIGHT=\"0\" FRAMEBORDER=\"1\" SCROLLING=\"AUTO\">\n" + "</FRAMESET>\n" + "<FRAMESET BORDER=1 rows=\"80%,*\">\n" + "<FRAME NAME=\"Code\" SRC=\"" + class_name + "_code.html\"\n MARGINWIDTH=0 " + "MARGINHEIGHT=0 FRAMEBORDER=1 SCROLLING=\"AUTO\">\n" + "<FRAME NAME=\"Methods\" SRC=\"" + class_name + "_methods.html\"\n MARGINWIDTH=0 " + "MARGINHEIGHT=0 FRAMEBORDER=1 SCROLLING=\"AUTO\">\n" + "</FRAMESET></FRAMESET></HTML>");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 257 */     file.close();
/*     */     
/* 259 */     for (int i = 0; i < attributes.length; i++)
/* 260 */       attribute_html.writeAttribute(attributes[i], "class" + i); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/util/Class2HTML.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */