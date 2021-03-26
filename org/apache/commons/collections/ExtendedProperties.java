/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.LineNumberReader;
/*      */ import java.io.OutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Reader;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Properties;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ExtendedProperties
/*      */   extends Hashtable
/*      */ {
/*      */   private ExtendedProperties defaults;
/*      */   protected String file;
/*      */   protected String basePath;
/*  165 */   protected String fileSeparator = System.getProperty("file.separator");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isInitialized = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  176 */   protected static String include = "include";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  184 */   protected ArrayList keysAsListed = new ArrayList();
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String START_TOKEN = "${";
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String END_TOKEN = "}";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String interpolate(String base) {
/*  198 */     return interpolateHelper(base, null);
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
/*      */   protected String interpolateHelper(String base, List priorVariables) {
/*  217 */     if (base == null) {
/*  218 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  223 */     if (priorVariables == null) {
/*  224 */       priorVariables = new ArrayList();
/*  225 */       priorVariables.add(base);
/*      */     } 
/*      */     
/*  228 */     int begin = -1;
/*  229 */     int end = -1;
/*  230 */     int prec = 0 - "}".length();
/*  231 */     String variable = null;
/*  232 */     StringBuffer result = new StringBuffer();
/*      */ 
/*      */ 
/*      */     
/*  236 */     while ((begin = base.indexOf("${", prec + "}".length())) > -1 && (end = base.indexOf("}", begin)) > -1) {
/*  237 */       result.append(base.substring(prec + "}".length(), begin));
/*  238 */       variable = base.substring(begin + "${".length(), end);
/*      */ 
/*      */       
/*  241 */       if (priorVariables.contains(variable)) {
/*  242 */         String initialBase = priorVariables.remove(0).toString();
/*  243 */         priorVariables.add(variable);
/*  244 */         StringBuffer priorVariableSb = new StringBuffer();
/*      */ 
/*      */ 
/*      */         
/*  248 */         for (Iterator it = priorVariables.iterator(); it.hasNext(); ) {
/*  249 */           priorVariableSb.append(it.next());
/*  250 */           if (it.hasNext()) {
/*  251 */             priorVariableSb.append("->");
/*      */           }
/*      */         } 
/*      */         
/*  255 */         throw new IllegalStateException("infinite loop in property interpolation of " + initialBase + ": " + priorVariableSb.toString());
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  260 */       priorVariables.add(variable);
/*      */ 
/*      */ 
/*      */       
/*  264 */       Object value = getProperty(variable);
/*  265 */       if (value != null) {
/*  266 */         result.append(interpolateHelper(value.toString(), priorVariables));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  272 */         priorVariables.remove(priorVariables.size() - 1);
/*  273 */       } else if (this.defaults != null && this.defaults.getString(variable, null) != null) {
/*  274 */         result.append(this.defaults.getString(variable));
/*      */       } else {
/*      */         
/*  277 */         result.append("${").append(variable).append("}");
/*      */       } 
/*  279 */       prec = end;
/*      */     } 
/*  281 */     result.append(base.substring(prec + "}".length(), base.length()));
/*      */     
/*  283 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String escape(String s) {
/*  290 */     StringBuffer buf = new StringBuffer(s);
/*  291 */     for (int i = 0; i < buf.length(); i++) {
/*  292 */       char c = buf.charAt(i);
/*  293 */       if (c == ',' || c == '\\') {
/*  294 */         buf.insert(i, '\\');
/*  295 */         i++;
/*      */       } 
/*      */     } 
/*  298 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String unescape(String s) {
/*  305 */     StringBuffer buf = new StringBuffer(s);
/*  306 */     for (int i = 0; i < buf.length() - 1; i++) {
/*  307 */       char c1 = buf.charAt(i);
/*  308 */       char c2 = buf.charAt(i + 1);
/*  309 */       if (c1 == '\\' && c2 == '\\') {
/*  310 */         buf.deleteCharAt(i);
/*      */       }
/*      */     } 
/*  313 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int countPreceding(String line, int index, char ch) {
/*      */     int i;
/*  322 */     for (i = index - 1; i >= 0 && 
/*  323 */       line.charAt(i) == ch; i--);
/*      */ 
/*      */ 
/*      */     
/*  327 */     return index - 1 - i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean endsWithSlash(String line) {
/*  334 */     if (!line.endsWith("\\")) {
/*  335 */       return false;
/*      */     }
/*  337 */     return (countPreceding(line, line.length() - 1, '\\') % 2 == 0);
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
/*      */   static class PropertiesReader
/*      */     extends LineNumberReader
/*      */   {
/*      */     public PropertiesReader(Reader reader) {
/*  353 */       super(reader);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String readProperty() throws IOException {
/*  363 */       StringBuffer buffer = new StringBuffer();
/*      */       try {
/*      */         String line;
/*      */         while (true) {
/*  367 */           line = readLine().trim();
/*  368 */           if (line.length() != 0 && line.charAt(0) != '#') {
/*  369 */             if (ExtendedProperties.endsWithSlash(line))
/*  370 */             { line = line.substring(0, line.length() - 1);
/*  371 */               buffer.append(line); continue; }  break;
/*      */           } 
/*  373 */         }  buffer.append(line);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  378 */       catch (NullPointerException ex) {
/*  379 */         return null;
/*      */       } 
/*      */       
/*  382 */       return buffer.toString();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class PropertiesTokenizer
/*      */     extends StringTokenizer
/*      */   {
/*      */     static final String DELIMITER = ",";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public PropertiesTokenizer(String string) {
/*  403 */       super(string, ",");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasMoreTokens() {
/*  412 */       return super.hasMoreTokens();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String nextToken() {
/*  421 */       StringBuffer buffer = new StringBuffer();
/*      */       
/*  423 */       while (hasMoreTokens()) {
/*  424 */         String token = super.nextToken();
/*  425 */         if (ExtendedProperties.endsWithSlash(token)) {
/*  426 */           buffer.append(token.substring(0, token.length() - 1));
/*  427 */           buffer.append(","); continue;
/*      */         } 
/*  429 */         buffer.append(token);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  434 */       return buffer.toString().trim();
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ExtendedProperties(String file) throws IOException {
/*  452 */     this(file, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ExtendedProperties(String file, String defaultFile) throws IOException {
/*  463 */     this.file = file;
/*      */     
/*  465 */     this.basePath = (new File(file)).getAbsolutePath();
/*  466 */     this.basePath = this.basePath.substring(0, this.basePath.lastIndexOf(this.fileSeparator) + 1);
/*      */     
/*  468 */     FileInputStream in = null;
/*      */     try {
/*  470 */       in = new FileInputStream(file);
/*  471 */       load(in);
/*      */     } finally {
/*      */       try {
/*  474 */         if (in != null) {
/*  475 */           in.close();
/*      */         }
/*  477 */       } catch (IOException iOException) {}
/*      */     } 
/*      */     
/*  480 */     if (defaultFile != null) {
/*  481 */       this.defaults = new ExtendedProperties(defaultFile);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInitialized() {
/*  490 */     return this.isInitialized;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getInclude() {
/*  500 */     return include;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInclude(String inc) {
/*  510 */     include = inc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void load(InputStream input) throws IOException {
/*  520 */     load(input, null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void load(InputStream input, String enc) throws IOException {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore_3
/*      */     //   2: aload_2
/*      */     //   3: ifnull -> 28
/*      */     //   6: new org/apache/commons/collections/ExtendedProperties$PropertiesReader
/*      */     //   9: dup
/*      */     //   10: new java/io/InputStreamReader
/*      */     //   13: dup
/*      */     //   14: aload_1
/*      */     //   15: aload_2
/*      */     //   16: invokespecial <init> : (Ljava/io/InputStream;Ljava/lang/String;)V
/*      */     //   19: invokespecial <init> : (Ljava/io/Reader;)V
/*      */     //   22: astore_3
/*      */     //   23: goto -> 28
/*      */     //   26: astore #4
/*      */     //   28: aload_3
/*      */     //   29: ifnonnull -> 71
/*      */     //   32: new org/apache/commons/collections/ExtendedProperties$PropertiesReader
/*      */     //   35: dup
/*      */     //   36: new java/io/InputStreamReader
/*      */     //   39: dup
/*      */     //   40: aload_1
/*      */     //   41: ldc '8859_1'
/*      */     //   43: invokespecial <init> : (Ljava/io/InputStream;Ljava/lang/String;)V
/*      */     //   46: invokespecial <init> : (Ljava/io/Reader;)V
/*      */     //   49: astore_3
/*      */     //   50: goto -> 71
/*      */     //   53: astore #4
/*      */     //   55: new org/apache/commons/collections/ExtendedProperties$PropertiesReader
/*      */     //   58: dup
/*      */     //   59: new java/io/InputStreamReader
/*      */     //   62: dup
/*      */     //   63: aload_1
/*      */     //   64: invokespecial <init> : (Ljava/io/InputStream;)V
/*      */     //   67: invokespecial <init> : (Ljava/io/Reader;)V
/*      */     //   70: astore_3
/*      */     //   71: aload_3
/*      */     //   72: invokevirtual readProperty : ()Ljava/lang/String;
/*      */     //   75: astore #4
/*      */     //   77: aload #4
/*      */     //   79: bipush #61
/*      */     //   81: invokevirtual indexOf : (I)I
/*      */     //   84: istore #5
/*      */     //   86: iload #5
/*      */     //   88: ifle -> 293
/*      */     //   91: aload #4
/*      */     //   93: iconst_0
/*      */     //   94: iload #5
/*      */     //   96: invokevirtual substring : (II)Ljava/lang/String;
/*      */     //   99: invokevirtual trim : ()Ljava/lang/String;
/*      */     //   102: astore #6
/*      */     //   104: aload #4
/*      */     //   106: iload #5
/*      */     //   108: iconst_1
/*      */     //   109: iadd
/*      */     //   110: invokevirtual substring : (I)Ljava/lang/String;
/*      */     //   113: invokevirtual trim : ()Ljava/lang/String;
/*      */     //   116: astore #7
/*      */     //   118: ldc ''
/*      */     //   120: aload #7
/*      */     //   122: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */     //   125: ifeq -> 131
/*      */     //   128: goto -> 71
/*      */     //   131: aload_0
/*      */     //   132: invokevirtual getInclude : ()Ljava/lang/String;
/*      */     //   135: ifnull -> 285
/*      */     //   138: aload #6
/*      */     //   140: aload_0
/*      */     //   141: invokevirtual getInclude : ()Ljava/lang/String;
/*      */     //   144: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
/*      */     //   147: ifeq -> 285
/*      */     //   150: aconst_null
/*      */     //   151: astore #8
/*      */     //   153: aload #7
/*      */     //   155: aload_0
/*      */     //   156: getfield fileSeparator : Ljava/lang/String;
/*      */     //   159: invokevirtual startsWith : (Ljava/lang/String;)Z
/*      */     //   162: ifeq -> 179
/*      */     //   165: new java/io/File
/*      */     //   168: dup
/*      */     //   169: aload #7
/*      */     //   171: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   174: astore #8
/*      */     //   176: goto -> 248
/*      */     //   179: aload #7
/*      */     //   181: new java/lang/StringBuffer
/*      */     //   184: dup
/*      */     //   185: invokespecial <init> : ()V
/*      */     //   188: ldc '.'
/*      */     //   190: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   193: aload_0
/*      */     //   194: getfield fileSeparator : Ljava/lang/String;
/*      */     //   197: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   200: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   203: invokevirtual startsWith : (Ljava/lang/String;)Z
/*      */     //   206: ifeq -> 217
/*      */     //   209: aload #7
/*      */     //   211: iconst_2
/*      */     //   212: invokevirtual substring : (I)Ljava/lang/String;
/*      */     //   215: astore #7
/*      */     //   217: new java/io/File
/*      */     //   220: dup
/*      */     //   221: new java/lang/StringBuffer
/*      */     //   224: dup
/*      */     //   225: invokespecial <init> : ()V
/*      */     //   228: aload_0
/*      */     //   229: getfield basePath : Ljava/lang/String;
/*      */     //   232: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   235: aload #7
/*      */     //   237: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   240: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   243: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   246: astore #8
/*      */     //   248: aload #8
/*      */     //   250: ifnull -> 282
/*      */     //   253: aload #8
/*      */     //   255: invokevirtual exists : ()Z
/*      */     //   258: ifeq -> 282
/*      */     //   261: aload #8
/*      */     //   263: invokevirtual canRead : ()Z
/*      */     //   266: ifeq -> 282
/*      */     //   269: aload_0
/*      */     //   270: new java/io/FileInputStream
/*      */     //   273: dup
/*      */     //   274: aload #8
/*      */     //   276: invokespecial <init> : (Ljava/io/File;)V
/*      */     //   279: invokevirtual load : (Ljava/io/InputStream;)V
/*      */     //   282: goto -> 293
/*      */     //   285: aload_0
/*      */     //   286: aload #6
/*      */     //   288: aload #7
/*      */     //   290: invokevirtual addProperty : (Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   293: goto -> 71
/*      */     //   296: astore #4
/*      */     //   298: aload_0
/*      */     //   299: iconst_1
/*      */     //   300: putfield isInitialized : Z
/*      */     //   303: return
/*      */     //   304: astore #9
/*      */     //   306: aload_0
/*      */     //   307: iconst_1
/*      */     //   308: putfield isInitialized : Z
/*      */     //   311: aload #9
/*      */     //   313: athrow
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #532	-> 0
/*      */     //   #533	-> 2
/*      */     //   #535	-> 6
/*      */     //   #539	-> 23
/*      */     //   #537	-> 26
/*      */     //   #542	-> 28
/*      */     //   #544	-> 32
/*      */     //   #550	-> 50
/*      */     //   #546	-> 53
/*      */     //   #549	-> 55
/*      */     //   #555	-> 71
/*      */     //   #556	-> 77
/*      */     //   #558	-> 86
/*      */     //   #559	-> 91
/*      */     //   #560	-> 104
/*      */     //   #563	-> 118
/*      */     //   #564	-> 128
/*      */     //   #567	-> 131
/*      */     //   #569	-> 150
/*      */     //   #571	-> 153
/*      */     //   #573	-> 165
/*      */     //   #579	-> 179
/*      */     //   #580	-> 209
/*      */     //   #583	-> 217
/*      */     //   #586	-> 248
/*      */     //   #587	-> 269
/*      */     //   #590	-> 285
/*      */     //   #594	-> 296
/*      */     //   #599	-> 298
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	314	0	this	Lorg/apache/commons/collections/ExtendedProperties;
/*      */     //   0	314	1	input	Ljava/io/InputStream;
/*      */     //   0	314	2	enc	Ljava/lang/String;
/*      */     //   2	312	3	reader	Lorg/apache/commons/collections/ExtendedProperties$PropertiesReader;
/*      */     //   55	16	4	ex	Ljava/io/UnsupportedEncodingException;
/*      */     //   77	216	4	line	Ljava/lang/String;
/*      */     //   86	207	5	equalSign	I
/*      */     //   104	189	6	key	Ljava/lang/String;
/*      */     //   118	175	7	value	Ljava/lang/String;
/*      */     //   153	129	8	file	Ljava/io/File;
/*      */     //   298	6	4	ex	Ljava/lang/NullPointerException;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   6	23	26	java/io/UnsupportedEncodingException
/*      */     //   32	50	53	java/io/UnsupportedEncodingException
/*      */     //   71	296	296	java/lang/NullPointerException
/*      */     //   71	298	304	finally
/*      */     //   304	306	304	finally
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getProperty(String key) {
/*  612 */     Object obj = get(key);
/*      */     
/*  614 */     if (obj == null)
/*      */     {
/*      */       
/*  617 */       if (this.defaults != null) {
/*  618 */         obj = this.defaults.get(key);
/*      */       }
/*      */     }
/*      */     
/*  622 */     return obj;
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
/*      */   public void addProperty(String key, Object token) {
/*  645 */     Object obj = get(key);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  660 */     if (obj instanceof String) {
/*  661 */       Vector v = new Vector(2);
/*  662 */       v.addElement(obj);
/*  663 */       v.addElement(token);
/*  664 */       put((K)key, (V)v);
/*      */     }
/*  666 */     else if (obj instanceof Vector) {
/*  667 */       ((Vector)obj).addElement(token);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  684 */     else if (token instanceof String && ((String)token).indexOf(",") > 0) {
/*      */ 
/*      */       
/*  687 */       PropertiesTokenizer tokenizer = new PropertiesTokenizer((String)token);
/*      */       
/*  689 */       while (tokenizer.hasMoreTokens()) {
/*  690 */         String value = tokenizer.nextToken();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  697 */         addStringProperty(key, unescape(value));
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  709 */       if (token instanceof String) {
/*  710 */         token = unescape((String)token);
/*      */       }
/*  712 */       addPropertyDirect(key, token);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  717 */     this.isInitialized = true;
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
/*      */   private void addPropertyDirect(String key, Object obj) {
/*  729 */     if (!containsKey(key)) {
/*  730 */       this.keysAsListed.add(key);
/*      */     }
/*  732 */     put((K)key, (V)obj);
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
/*      */   private void addStringProperty(String key, String token) {
/*  745 */     Object obj = get(key);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  763 */     if (obj instanceof String) {
/*  764 */       Vector v = new Vector(2);
/*  765 */       v.addElement(obj);
/*  766 */       v.addElement(token);
/*  767 */       put((K)key, (V)v);
/*      */     }
/*  769 */     else if (obj instanceof Vector) {
/*  770 */       ((Vector)obj).addElement(token);
/*      */     } else {
/*      */       
/*  773 */       addPropertyDirect(key, token);
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
/*      */   public void setProperty(String key, Object value) {
/*  786 */     clearProperty(key);
/*  787 */     addProperty(key, value);
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
/*      */   public synchronized void save(OutputStream output, String header) throws IOException {
/*  800 */     if (output == null) {
/*      */       return;
/*      */     }
/*  803 */     PrintWriter theWrtr = new PrintWriter(output);
/*  804 */     if (header != null) {
/*  805 */       theWrtr.println(header);
/*      */     }
/*      */     
/*  808 */     Enumeration theKeys = keys();
/*  809 */     while (theKeys.hasMoreElements()) {
/*  810 */       String key = (String)theKeys.nextElement();
/*  811 */       Object value = get(key);
/*  812 */       if (value != null) {
/*  813 */         if (value instanceof String) {
/*  814 */           StringBuffer currentOutput = new StringBuffer();
/*  815 */           currentOutput.append(key);
/*  816 */           currentOutput.append("=");
/*  817 */           currentOutput.append(escape((String)value));
/*  818 */           theWrtr.println(currentOutput.toString());
/*      */         }
/*  820 */         else if (value instanceof Vector) {
/*  821 */           Vector values = (Vector)value;
/*  822 */           Enumeration valuesEnum = values.elements();
/*  823 */           while (valuesEnum.hasMoreElements()) {
/*  824 */             String currentElement = valuesEnum.nextElement();
/*  825 */             StringBuffer currentOutput = new StringBuffer();
/*  826 */             currentOutput.append(key);
/*  827 */             currentOutput.append("=");
/*  828 */             currentOutput.append(escape(currentElement));
/*  829 */             theWrtr.println(currentOutput.toString());
/*      */           } 
/*      */         } 
/*      */       }
/*  833 */       theWrtr.println();
/*  834 */       theWrtr.flush();
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
/*      */   public void combine(ExtendedProperties props) {
/*  846 */     for (Iterator it = props.getKeys(); it.hasNext(); ) {
/*  847 */       String key = it.next();
/*  848 */       setProperty(key, props.get(key));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearProperty(String key) {
/*  858 */     if (containsKey(key)) {
/*      */ 
/*      */       
/*  861 */       for (int i = 0; i < this.keysAsListed.size(); i++) {
/*  862 */         if (this.keysAsListed.get(i).equals(key)) {
/*  863 */           this.keysAsListed.remove(i);
/*      */           break;
/*      */         } 
/*      */       } 
/*  867 */       remove(key);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterator getKeys() {
/*  878 */     return this.keysAsListed.iterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterator getKeys(String prefix) {
/*  889 */     Iterator keys = getKeys();
/*  890 */     ArrayList matchingKeys = new ArrayList();
/*      */     
/*  892 */     while (keys.hasNext()) {
/*  893 */       Object key = keys.next();
/*      */       
/*  895 */       if (key instanceof String && ((String)key).startsWith(prefix)) {
/*  896 */         matchingKeys.add(key);
/*      */       }
/*      */     } 
/*  899 */     return matchingKeys.iterator();
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
/*      */   public ExtendedProperties subset(String prefix) {
/*  911 */     ExtendedProperties c = new ExtendedProperties();
/*  912 */     Iterator keys = getKeys();
/*  913 */     boolean validSubset = false;
/*      */     
/*  915 */     while (keys.hasNext()) {
/*  916 */       Object key = keys.next();
/*      */       
/*  918 */       if (key instanceof String && ((String)key).startsWith(prefix)) {
/*  919 */         if (!validSubset) {
/*  920 */           validSubset = true;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  929 */         String newKey = null;
/*  930 */         if (((String)key).length() == prefix.length()) {
/*  931 */           newKey = prefix;
/*      */         } else {
/*  933 */           newKey = ((String)key).substring(prefix.length() + 1);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  941 */         c.addPropertyDirect(newKey, get(key));
/*      */       } 
/*      */     } 
/*      */     
/*  945 */     if (validSubset) {
/*  946 */       return c;
/*      */     }
/*  948 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void display() {
/*  956 */     Iterator i = getKeys();
/*      */     
/*  958 */     while (i.hasNext()) {
/*  959 */       String key = i.next();
/*  960 */       Object value = get(key);
/*  961 */       System.out.println(key + " => " + value);
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
/*      */   public String getString(String key) {
/*  974 */     return getString(key, null);
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
/*      */   public String getString(String key, String defaultValue) {
/*  988 */     Object value = get(key);
/*      */     
/*  990 */     if (value instanceof String) {
/*  991 */       return interpolate((String)value);
/*      */     }
/*  993 */     if (value == null) {
/*  994 */       if (this.defaults != null) {
/*  995 */         return interpolate(this.defaults.getString(key, defaultValue));
/*      */       }
/*  997 */       return interpolate(defaultValue);
/*      */     } 
/*  999 */     if (value instanceof Vector) {
/* 1000 */       return interpolate(((Vector)value).get(0));
/*      */     }
/* 1002 */     throw new ClassCastException('\'' + key + "' doesn't map to a String object");
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
/*      */   public Properties getProperties(String key) {
/* 1018 */     return getProperties(key, new Properties());
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
/*      */   public Properties getProperties(String key, Properties defaults) {
/* 1036 */     String[] tokens = getStringArray(key);
/*      */ 
/*      */     
/* 1039 */     Properties props = new Properties(defaults);
/* 1040 */     for (int i = 0; i < tokens.length; i++) {
/* 1041 */       String token = tokens[i];
/* 1042 */       int equalSign = token.indexOf('=');
/* 1043 */       if (equalSign > 0) {
/* 1044 */         String pkey = token.substring(0, equalSign).trim();
/* 1045 */         String pvalue = token.substring(equalSign + 1).trim();
/* 1046 */         props.put(pkey, pvalue);
/*      */       } else {
/* 1048 */         throw new IllegalArgumentException('\'' + token + "' does not contain " + "an equals sign");
/*      */       } 
/*      */     } 
/* 1051 */     return props;
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
/*      */   public String[] getStringArray(String key) {
/*      */     Vector vector;
/* 1064 */     Object value = get(key);
/*      */ 
/*      */ 
/*      */     
/* 1068 */     if (value instanceof String) {
/* 1069 */       vector = new Vector(1);
/* 1070 */       vector.addElement(value);
/*      */     }
/* 1072 */     else if (value instanceof Vector) {
/* 1073 */       vector = (Vector)value;
/*      */     } else {
/* 1075 */       if (value == null) {
/* 1076 */         if (this.defaults != null) {
/* 1077 */           return this.defaults.getStringArray(key);
/*      */         }
/* 1079 */         return new String[0];
/*      */       } 
/*      */       
/* 1082 */       throw new ClassCastException('\'' + key + "' doesn't map to a String/Vector object");
/*      */     } 
/*      */     
/* 1085 */     String[] tokens = new String[vector.size()];
/* 1086 */     for (int i = 0; i < tokens.length; i++) {
/* 1087 */       tokens[i] = vector.elementAt(i);
/*      */     }
/*      */     
/* 1090 */     return tokens;
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
/*      */   public Vector getVector(String key) {
/* 1103 */     return getVector(key, null);
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
/*      */   public Vector getVector(String key, Vector defaultValue) {
/* 1117 */     Object value = get(key);
/*      */     
/* 1119 */     if (value instanceof Vector) {
/* 1120 */       return (Vector)value;
/*      */     }
/* 1122 */     if (value instanceof String) {
/* 1123 */       Vector v = new Vector(1);
/* 1124 */       v.addElement(value);
/* 1125 */       put((K)key, (V)v);
/* 1126 */       return v;
/*      */     } 
/* 1128 */     if (value == null) {
/* 1129 */       if (this.defaults != null) {
/* 1130 */         return this.defaults.getVector(key, defaultValue);
/*      */       }
/* 1132 */       return (defaultValue == null) ? new Vector() : defaultValue;
/*      */     } 
/*      */     
/* 1135 */     throw new ClassCastException('\'' + key + "' doesn't map to a Vector object");
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
/*      */   public boolean getBoolean(String key) {
/* 1150 */     Boolean b = getBoolean(key, (Boolean)null);
/* 1151 */     if (b != null) {
/* 1152 */       return b.booleanValue();
/*      */     }
/* 1154 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
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
/*      */   public boolean getBoolean(String key, boolean defaultValue) {
/* 1168 */     return getBoolean(key, new Boolean(defaultValue)).booleanValue();
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
/*      */   public Boolean getBoolean(String key, Boolean defaultValue) {
/* 1183 */     Object value = get(key);
/*      */     
/* 1185 */     if (value instanceof Boolean) {
/* 1186 */       return (Boolean)value;
/*      */     }
/* 1188 */     if (value instanceof String) {
/* 1189 */       String s = testBoolean((String)value);
/* 1190 */       Boolean b = new Boolean(s);
/* 1191 */       put((K)key, (V)b);
/* 1192 */       return b;
/*      */     } 
/* 1194 */     if (value == null) {
/* 1195 */       if (this.defaults != null) {
/* 1196 */         return this.defaults.getBoolean(key, defaultValue);
/*      */       }
/* 1198 */       return defaultValue;
/*      */     } 
/*      */     
/* 1201 */     throw new ClassCastException('\'' + key + "' doesn't map to a Boolean object");
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
/*      */   public String testBoolean(String value) {
/* 1218 */     String s = value.toLowerCase();
/*      */     
/* 1220 */     if (s.equals("true") || s.equals("on") || s.equals("yes"))
/* 1221 */       return "true"; 
/* 1222 */     if (s.equals("false") || s.equals("off") || s.equals("no")) {
/* 1223 */       return "false";
/*      */     }
/* 1225 */     return null;
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
/*      */   public byte getByte(String key) {
/* 1242 */     Byte b = getByte(key, (Byte)null);
/* 1243 */     if (b != null) {
/* 1244 */       return b.byteValue();
/*      */     }
/* 1246 */     throw new NoSuchElementException('\'' + key + " doesn't map to an existing object");
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
/*      */   public byte getByte(String key, byte defaultValue) {
/* 1262 */     return getByte(key, new Byte(defaultValue)).byteValue();
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
/*      */   public Byte getByte(String key, Byte defaultValue) {
/* 1278 */     Object value = get(key);
/*      */     
/* 1280 */     if (value instanceof Byte) {
/* 1281 */       return (Byte)value;
/*      */     }
/* 1283 */     if (value instanceof String) {
/* 1284 */       Byte b = new Byte((String)value);
/* 1285 */       put((K)key, (V)b);
/* 1286 */       return b;
/*      */     } 
/* 1288 */     if (value == null) {
/* 1289 */       if (this.defaults != null) {
/* 1290 */         return this.defaults.getByte(key, defaultValue);
/*      */       }
/* 1292 */       return defaultValue;
/*      */     } 
/*      */     
/* 1295 */     throw new ClassCastException('\'' + key + "' doesn't map to a Byte object");
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
/*      */   public short getShort(String key) {
/* 1312 */     Short s = getShort(key, (Short)null);
/* 1313 */     if (s != null) {
/* 1314 */       return s.shortValue();
/*      */     }
/* 1316 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
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
/*      */   public short getShort(String key, short defaultValue) {
/* 1332 */     return getShort(key, new Short(defaultValue)).shortValue();
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
/*      */   public Short getShort(String key, Short defaultValue) {
/* 1348 */     Object value = get(key);
/*      */     
/* 1350 */     if (value instanceof Short) {
/* 1351 */       return (Short)value;
/*      */     }
/* 1353 */     if (value instanceof String) {
/* 1354 */       Short s = new Short((String)value);
/* 1355 */       put((K)key, (V)s);
/* 1356 */       return s;
/*      */     } 
/* 1358 */     if (value == null) {
/* 1359 */       if (this.defaults != null) {
/* 1360 */         return this.defaults.getShort(key, defaultValue);
/*      */       }
/* 1362 */       return defaultValue;
/*      */     } 
/*      */     
/* 1365 */     throw new ClassCastException('\'' + key + "' doesn't map to a Short object");
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
/*      */   public int getInt(String name) {
/* 1377 */     return getInteger(name);
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
/*      */   public int getInt(String name, int def) {
/* 1389 */     return getInteger(name, def);
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
/*      */   public int getInteger(String key) {
/* 1405 */     Integer i = getInteger(key, (Integer)null);
/* 1406 */     if (i != null) {
/* 1407 */       return i.intValue();
/*      */     }
/* 1409 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
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
/*      */   public int getInteger(String key, int defaultValue) {
/* 1425 */     Integer i = getInteger(key, (Integer)null);
/*      */     
/* 1427 */     if (i == null) {
/* 1428 */       return defaultValue;
/*      */     }
/* 1430 */     return i.intValue();
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
/*      */   public Integer getInteger(String key, Integer defaultValue) {
/* 1446 */     Object value = get(key);
/*      */     
/* 1448 */     if (value instanceof Integer) {
/* 1449 */       return (Integer)value;
/*      */     }
/* 1451 */     if (value instanceof String) {
/* 1452 */       Integer i = new Integer((String)value);
/* 1453 */       put((K)key, (V)i);
/* 1454 */       return i;
/*      */     } 
/* 1456 */     if (value == null) {
/* 1457 */       if (this.defaults != null) {
/* 1458 */         return this.defaults.getInteger(key, defaultValue);
/*      */       }
/* 1460 */       return defaultValue;
/*      */     } 
/*      */     
/* 1463 */     throw new ClassCastException('\'' + key + "' doesn't map to a Integer object");
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
/*      */   public long getLong(String key) {
/* 1480 */     Long l = getLong(key, (Long)null);
/* 1481 */     if (l != null) {
/* 1482 */       return l.longValue();
/*      */     }
/* 1484 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
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
/*      */   public long getLong(String key, long defaultValue) {
/* 1500 */     return getLong(key, new Long(defaultValue)).longValue();
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
/*      */   public Long getLong(String key, Long defaultValue) {
/* 1516 */     Object value = get(key);
/*      */     
/* 1518 */     if (value instanceof Long) {
/* 1519 */       return (Long)value;
/*      */     }
/* 1521 */     if (value instanceof String) {
/* 1522 */       Long l = new Long((String)value);
/* 1523 */       put((K)key, (V)l);
/* 1524 */       return l;
/*      */     } 
/* 1526 */     if (value == null) {
/* 1527 */       if (this.defaults != null) {
/* 1528 */         return this.defaults.getLong(key, defaultValue);
/*      */       }
/* 1530 */       return defaultValue;
/*      */     } 
/*      */     
/* 1533 */     throw new ClassCastException('\'' + key + "' doesn't map to a Long object");
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
/*      */   public float getFloat(String key) {
/* 1550 */     Float f = getFloat(key, (Float)null);
/* 1551 */     if (f != null) {
/* 1552 */       return f.floatValue();
/*      */     }
/* 1554 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
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
/*      */   public float getFloat(String key, float defaultValue) {
/* 1570 */     return getFloat(key, new Float(defaultValue)).floatValue();
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
/*      */   public Float getFloat(String key, Float defaultValue) {
/* 1586 */     Object value = get(key);
/*      */     
/* 1588 */     if (value instanceof Float) {
/* 1589 */       return (Float)value;
/*      */     }
/* 1591 */     if (value instanceof String) {
/* 1592 */       Float f = new Float((String)value);
/* 1593 */       put((K)key, (V)f);
/* 1594 */       return f;
/*      */     } 
/* 1596 */     if (value == null) {
/* 1597 */       if (this.defaults != null) {
/* 1598 */         return this.defaults.getFloat(key, defaultValue);
/*      */       }
/* 1600 */       return defaultValue;
/*      */     } 
/*      */     
/* 1603 */     throw new ClassCastException('\'' + key + "' doesn't map to a Float object");
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
/*      */   public double getDouble(String key) {
/* 1620 */     Double d = getDouble(key, (Double)null);
/* 1621 */     if (d != null) {
/* 1622 */       return d.doubleValue();
/*      */     }
/* 1624 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
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
/*      */   public double getDouble(String key, double defaultValue) {
/* 1640 */     return getDouble(key, new Double(defaultValue)).doubleValue();
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
/*      */   public Double getDouble(String key, Double defaultValue) {
/* 1656 */     Object value = get(key);
/*      */     
/* 1658 */     if (value instanceof Double) {
/* 1659 */       return (Double)value;
/*      */     }
/* 1661 */     if (value instanceof String) {
/* 1662 */       Double d = new Double((String)value);
/* 1663 */       put((K)key, (V)d);
/* 1664 */       return d;
/*      */     } 
/* 1666 */     if (value == null) {
/* 1667 */       if (this.defaults != null) {
/* 1668 */         return this.defaults.getDouble(key, defaultValue);
/*      */       }
/* 1670 */       return defaultValue;
/*      */     } 
/*      */     
/* 1673 */     throw new ClassCastException('\'' + key + "' doesn't map to a Double object");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ExtendedProperties convertProperties(Properties props) {
/* 1684 */     ExtendedProperties c = new ExtendedProperties();
/*      */     
/* 1686 */     for (Enumeration e = props.keys(); e.hasMoreElements(); ) {
/* 1687 */       String s = e.nextElement();
/* 1688 */       c.setProperty(s, props.getProperty(s));
/*      */     } 
/*      */     
/* 1691 */     return c;
/*      */   }
/*      */   
/*      */   public ExtendedProperties() {}
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/ExtendedProperties.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */