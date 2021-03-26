/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.bcel.generic.Type;
/*     */ import org.apache.xml.utils.XMLChar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Util
/*     */ {
/*     */   private static char filesep;
/*     */   
/*     */   static {
/*  36 */     String temp = System.getProperty("file.separator", "/");
/*  37 */     filesep = temp.charAt(0);
/*     */   }
/*     */   
/*     */   public static String noExtName(String name) {
/*  41 */     int index = name.lastIndexOf('.');
/*  42 */     return name.substring(0, (index >= 0) ? index : name.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String baseName(String name) {
/*  50 */     int index = name.lastIndexOf('\\');
/*  51 */     if (index < 0) {
/*  52 */       index = name.lastIndexOf('/');
/*     */     }
/*     */     
/*  55 */     if (index >= 0) {
/*  56 */       return name.substring(index + 1);
/*     */     }
/*  58 */     int lastColonIndex = name.lastIndexOf(':');
/*  59 */     if (lastColonIndex > 0) {
/*  60 */       return name.substring(lastColonIndex + 1);
/*     */     }
/*  62 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String pathName(String name) {
/*  71 */     int index = name.lastIndexOf('/');
/*  72 */     if (index < 0) {
/*  73 */       index = name.lastIndexOf('\\');
/*     */     }
/*  75 */     return name.substring(0, index + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toJavaName(String name) {
/*  82 */     if (name.length() > 0) {
/*  83 */       StringBuffer result = new StringBuffer();
/*     */       
/*  85 */       char ch = name.charAt(0);
/*  86 */       result.append(Character.isJavaIdentifierStart(ch) ? ch : 95);
/*     */       
/*  88 */       int n = name.length();
/*  89 */       for (int i = 1; i < n; i++) {
/*  90 */         ch = name.charAt(i);
/*  91 */         result.append(Character.isJavaIdentifierPart(ch) ? ch : 95);
/*     */       } 
/*  93 */       return result.toString();
/*     */     } 
/*  95 */     return name;
/*     */   }
/*     */   
/*     */   public static Type getJCRefType(String signature) {
/*  99 */     return Type.getType(signature);
/*     */   }
/*     */   
/*     */   public static String internalName(String cname) {
/* 103 */     return cname.replace('.', filesep);
/*     */   }
/*     */   
/*     */   public static void println(String s) {
/* 107 */     System.out.println(s);
/*     */   }
/*     */   
/*     */   public static void println(char ch) {
/* 111 */     System.out.println(ch);
/*     */   }
/*     */   
/*     */   public static void TRACE1() {
/* 115 */     System.out.println("TRACE1");
/*     */   }
/*     */   
/*     */   public static void TRACE2() {
/* 119 */     System.out.println("TRACE2");
/*     */   }
/*     */   
/*     */   public static void TRACE3() {
/* 123 */     System.out.println("TRACE3");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String replace(String base, char ch, String str) {
/* 130 */     return (base.indexOf(ch) < 0) ? base : replace(base, String.valueOf(ch), new String[] { str });
/*     */   }
/*     */ 
/*     */   
/*     */   public static String replace(String base, String delim, String[] str) {
/* 135 */     int len = base.length();
/* 136 */     StringBuffer result = new StringBuffer();
/*     */     
/* 138 */     for (int i = 0; i < len; i++) {
/* 139 */       char ch = base.charAt(i);
/* 140 */       int k = delim.indexOf(ch);
/*     */       
/* 142 */       if (k >= 0) {
/* 143 */         result.append(str[k]);
/*     */       } else {
/*     */         
/* 146 */         result.append(ch);
/*     */       } 
/*     */     } 
/* 149 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String escape(String input) {
/* 156 */     return replace(input, ".-/:", new String[] { "$dot$", "$dash$", "$slash$", "$colon$" });
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getLocalName(String qname) {
/* 161 */     int index = qname.lastIndexOf(":");
/* 162 */     return (index > 0) ? qname.substring(index + 1) : qname;
/*     */   }
/*     */   
/*     */   public static String getPrefix(String qname) {
/* 166 */     int index = qname.lastIndexOf(":");
/* 167 */     return (index > 0) ? qname.substring(0, index) : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isLiteral(String str) {
/* 175 */     int length = str.length();
/* 176 */     for (int i = 0; i < length - 1; i++) {
/* 177 */       if (str.charAt(i) == '{' && str.charAt(i + 1) != '{') {
/* 178 */         return false;
/*     */       }
/*     */     } 
/* 181 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isValidQNames(String str) {
/* 188 */     if (str != null && !str.equals("")) {
/* 189 */       StringTokenizer tokens = new StringTokenizer(str);
/* 190 */       while (tokens.hasMoreTokens()) {
/* 191 */         if (!XMLChar.isValidQName(tokens.nextToken())) {
/* 192 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 196 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/Util.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */