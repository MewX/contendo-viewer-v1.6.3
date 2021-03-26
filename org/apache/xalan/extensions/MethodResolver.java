/*     */ package org.apache.xalan.extensions;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.templates.ElemExtensionCall;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.dtm.ref.DTMNodeIterator;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.objects.XRTreeFrag;
/*     */ import org.apache.xpath.objects.XString;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.traversal.NodeIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MethodResolver
/*     */ {
/*     */   public static final int STATIC_ONLY = 1;
/*     */   public static final int INSTANCE_ONLY = 2;
/*     */   public static final int STATIC_AND_INSTANCE = 3;
/*     */   public static final int DYNAMIC = 4;
/*     */   private static final int SCOREBASE = 1;
/*     */   
/*     */   public static Constructor getConstructor(Class classObj, Object[] argsIn, Object[][] argsOut, ExpressionContext exprContext) throws NoSuchMethodException, SecurityException, TransformerException {
/*  89 */     Constructor bestConstructor = null;
/*  90 */     Class[] bestParamTypes = null;
/*  91 */     Constructor[] constructors = (Constructor[])classObj.getConstructors();
/*  92 */     int nMethods = constructors.length;
/*  93 */     int bestScore = Integer.MAX_VALUE;
/*  94 */     int bestScoreCount = 0;
/*  95 */     for (int i = 0; i < nMethods; i++) {
/*     */       char c;
/*  97 */       Constructor ctor = constructors[i];
/*  98 */       Class[] paramTypes = ctor.getParameterTypes();
/*  99 */       int numberMethodParams = paramTypes.length;
/* 100 */       int paramStart = 0;
/* 101 */       boolean isFirstExpressionContext = false;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 106 */       if (numberMethodParams == argsIn.length + 1) {
/*     */         
/* 108 */         Class javaClass = paramTypes[0];
/*     */         
/* 110 */         if (ExpressionContext.class.isAssignableFrom(javaClass)) {
/*     */           
/* 112 */           isFirstExpressionContext = true;
/* 113 */           c = Character.MIN_VALUE;
/* 114 */           paramStart++;
/*     */         } else {
/*     */           
/*     */           continue;
/*     */         } 
/*     */       } else {
/*     */         
/* 121 */         c = 'Ϩ';
/*     */       } 
/* 123 */       if (argsIn.length == numberMethodParams - paramStart) {
/*     */ 
/*     */         
/* 126 */         int score = scoreMatch(paramTypes, paramStart, argsIn, c);
/*     */         
/* 128 */         if (-1 != score)
/*     */         {
/* 130 */           if (score < bestScore) {
/*     */ 
/*     */             
/* 133 */             bestConstructor = ctor;
/* 134 */             bestParamTypes = paramTypes;
/* 135 */             bestScore = score;
/* 136 */             bestScoreCount = 1;
/*     */           }
/* 138 */           else if (score == bestScore) {
/* 139 */             bestScoreCount++;
/*     */           }  } 
/*     */       }  continue;
/*     */     } 
/* 143 */     if (null == bestConstructor)
/*     */     {
/* 145 */       throw new NoSuchMethodException(errString("function", "constructor", classObj, "", 0, argsIn));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     convertParams(argsIn, argsOut, bestParamTypes, exprContext);
/*     */     
/* 156 */     return bestConstructor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Method getMethod(Class classObj, String name, Object[] argsIn, Object[][] argsOut, ExpressionContext exprContext, int searchMethod) throws NoSuchMethodException, SecurityException, TransformerException {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ldc '-'
/*     */     //   3: invokevirtual indexOf : (Ljava/lang/String;)I
/*     */     //   6: ifle -> 14
/*     */     //   9: aload_1
/*     */     //   10: invokestatic replaceDash : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   13: astore_1
/*     */     //   14: aconst_null
/*     */     //   15: astore #6
/*     */     //   17: aconst_null
/*     */     //   18: astore #7
/*     */     //   20: aload_0
/*     */     //   21: invokevirtual getMethods : ()[Ljava/lang/reflect/Method;
/*     */     //   24: astore #8
/*     */     //   26: aload #8
/*     */     //   28: arraylength
/*     */     //   29: istore #9
/*     */     //   31: ldc 2147483647
/*     */     //   33: istore #10
/*     */     //   35: iconst_0
/*     */     //   36: istore #11
/*     */     //   38: iconst_0
/*     */     //   39: istore #13
/*     */     //   41: goto -> 302
/*     */     //   44: aload #8
/*     */     //   46: iload #13
/*     */     //   48: aaload
/*     */     //   49: astore #14
/*     */     //   51: iconst_0
/*     */     //   52: istore #15
/*     */     //   54: aload #14
/*     */     //   56: invokevirtual getName : ()Ljava/lang/String;
/*     */     //   59: aload_1
/*     */     //   60: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   63: ifeq -> 299
/*     */     //   66: aload #14
/*     */     //   68: invokevirtual getModifiers : ()I
/*     */     //   71: invokestatic isStatic : (I)Z
/*     */     //   74: istore #12
/*     */     //   76: iload #5
/*     */     //   78: tableswitch default -> 135, 1 -> 108, 2 -> 116, 3 -> 124, 4 -> 127
/*     */     //   108: iload #12
/*     */     //   110: ifne -> 135
/*     */     //   113: goto -> 299
/*     */     //   116: iload #12
/*     */     //   118: ifeq -> 135
/*     */     //   121: goto -> 299
/*     */     //   124: goto -> 135
/*     */     //   127: iload #12
/*     */     //   129: ifne -> 135
/*     */     //   132: iconst_1
/*     */     //   133: istore #15
/*     */     //   135: iconst_0
/*     */     //   136: istore #16
/*     */     //   138: aload #14
/*     */     //   140: invokevirtual getParameterTypes : ()[Ljava/lang/Class;
/*     */     //   143: astore #17
/*     */     //   145: aload #17
/*     */     //   147: arraylength
/*     */     //   148: istore #18
/*     */     //   150: iconst_0
/*     */     //   151: istore #19
/*     */     //   153: aconst_null
/*     */     //   154: aload_2
/*     */     //   155: if_acmpeq -> 163
/*     */     //   158: aload_2
/*     */     //   159: arraylength
/*     */     //   160: goto -> 164
/*     */     //   163: iconst_0
/*     */     //   164: istore #21
/*     */     //   166: iload #18
/*     */     //   168: iload #21
/*     */     //   170: iload #15
/*     */     //   172: isub
/*     */     //   173: iconst_1
/*     */     //   174: iadd
/*     */     //   175: if_icmpne -> 225
/*     */     //   178: aload #17
/*     */     //   180: iconst_0
/*     */     //   181: aaload
/*     */     //   182: astore #22
/*     */     //   184: getstatic org/apache/xalan/extensions/MethodResolver.class$org$apache$xalan$extensions$ExpressionContext : Ljava/lang/Class;
/*     */     //   187: ifnonnull -> 202
/*     */     //   190: ldc 'org.apache.xalan.extensions.ExpressionContext'
/*     */     //   192: invokestatic class$ : (Ljava/lang/String;)Ljava/lang/Class;
/*     */     //   195: dup
/*     */     //   196: putstatic org/apache/xalan/extensions/MethodResolver.class$org$apache$xalan$extensions$ExpressionContext : Ljava/lang/Class;
/*     */     //   199: goto -> 205
/*     */     //   202: getstatic org/apache/xalan/extensions/MethodResolver.class$org$apache$xalan$extensions$ExpressionContext : Ljava/lang/Class;
/*     */     //   205: aload #22
/*     */     //   207: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
/*     */     //   210: ifeq -> 299
/*     */     //   213: iconst_1
/*     */     //   214: istore #19
/*     */     //   216: iconst_0
/*     */     //   217: istore #20
/*     */     //   219: iinc #16, 1
/*     */     //   222: goto -> 230
/*     */     //   225: sipush #1000
/*     */     //   228: istore #20
/*     */     //   230: iload #21
/*     */     //   232: iload #15
/*     */     //   234: isub
/*     */     //   235: iload #18
/*     */     //   237: iload #16
/*     */     //   239: isub
/*     */     //   240: if_icmpne -> 299
/*     */     //   243: aload #17
/*     */     //   245: iload #16
/*     */     //   247: aload_2
/*     */     //   248: iload #20
/*     */     //   250: invokestatic scoreMatch : ([Ljava/lang/Class;I[Ljava/lang/Object;I)I
/*     */     //   253: istore #22
/*     */     //   255: iconst_m1
/*     */     //   256: iload #22
/*     */     //   258: if_icmpne -> 264
/*     */     //   261: goto -> 299
/*     */     //   264: iload #22
/*     */     //   266: iload #10
/*     */     //   268: if_icmpge -> 289
/*     */     //   271: aload #14
/*     */     //   273: astore #6
/*     */     //   275: aload #17
/*     */     //   277: astore #7
/*     */     //   279: iload #22
/*     */     //   281: istore #10
/*     */     //   283: iconst_1
/*     */     //   284: istore #11
/*     */     //   286: goto -> 299
/*     */     //   289: iload #22
/*     */     //   291: iload #10
/*     */     //   293: if_icmpne -> 299
/*     */     //   296: iinc #11, 1
/*     */     //   299: iinc #13, 1
/*     */     //   302: iload #13
/*     */     //   304: iload #9
/*     */     //   306: if_icmplt -> 44
/*     */     //   309: aconst_null
/*     */     //   310: aload #6
/*     */     //   312: if_acmpne -> 335
/*     */     //   315: new java/lang/NoSuchMethodException
/*     */     //   318: dup
/*     */     //   319: ldc 'function'
/*     */     //   321: ldc 'method'
/*     */     //   323: aload_0
/*     */     //   324: aload_1
/*     */     //   325: iload #5
/*     */     //   327: aload_2
/*     */     //   328: invokestatic errString : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;
/*     */     //   331: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   334: athrow
/*     */     //   335: aload_2
/*     */     //   336: aload_3
/*     */     //   337: aload #7
/*     */     //   339: aload #4
/*     */     //   341: invokestatic convertParams : ([Ljava/lang/Object;[[Ljava/lang/Object;[Ljava/lang/Class;Lorg/apache/xalan/extensions/ExpressionContext;)V
/*     */     //   344: aload #6
/*     */     //   346: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #185	-> 0
/*     */     //   #186	-> 9
/*     */     //   #187	-> 14
/*     */     //   #188	-> 17
/*     */     //   #189	-> 20
/*     */     //   #190	-> 26
/*     */     //   #191	-> 31
/*     */     //   #192	-> 35
/*     */     //   #194	-> 38
/*     */     //   #196	-> 44
/*     */     //   #198	-> 51
/*     */     //   #199	-> 54
/*     */     //   #201	-> 66
/*     */     //   #202	-> 76
/*     */     //   #205	-> 108
/*     */     //   #207	-> 113
/*     */     //   #212	-> 116
/*     */     //   #214	-> 121
/*     */     //   #219	-> 124
/*     */     //   #222	-> 127
/*     */     //   #223	-> 132
/*     */     //   #225	-> 135
/*     */     //   #226	-> 138
/*     */     //   #227	-> 145
/*     */     //   #228	-> 150
/*     */     //   #233	-> 153
/*     */     //   #234	-> 166
/*     */     //   #236	-> 178
/*     */     //   #237	-> 184
/*     */     //   #239	-> 213
/*     */     //   #240	-> 216
/*     */     //   #241	-> 219
/*     */     //   #245	-> 222
/*     */     //   #249	-> 225
/*     */     //   #251	-> 230
/*     */     //   #254	-> 243
/*     */     //   #256	-> 255
/*     */     //   #257	-> 261
/*     */     //   #258	-> 264
/*     */     //   #261	-> 271
/*     */     //   #262	-> 275
/*     */     //   #263	-> 279
/*     */     //   #264	-> 283
/*     */     //   #266	-> 289
/*     */     //   #267	-> 296
/*     */     //   #194	-> 299
/*     */     //   #272	-> 309
/*     */     //   #274	-> 315
/*     */     //   #282	-> 335
/*     */     //   #284	-> 344
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	347	0	classObj	Ljava/lang/Class;
/*     */     //   0	347	1	name	Ljava/lang/String;
/*     */     //   0	347	2	argsIn	[Ljava/lang/Object;
/*     */     //   0	347	3	argsOut	[[Ljava/lang/Object;
/*     */     //   0	347	4	exprContext	Lorg/apache/xalan/extensions/ExpressionContext;
/*     */     //   0	347	5	searchMethod	I
/*     */     //   17	330	6	bestMethod	Ljava/lang/reflect/Method;
/*     */     //   20	327	7	bestParamTypes	[Ljava/lang/Class;
/*     */     //   26	321	8	methods	[Ljava/lang/reflect/Method;
/*     */     //   31	316	9	nMethods	I
/*     */     //   35	312	10	bestScore	I
/*     */     //   38	309	11	bestScoreCount	I
/*     */     //   41	306	13	i	I
/*     */     //   51	248	14	method	Ljava/lang/reflect/Method;
/*     */     //   54	245	15	xsltParamStart	I
/*     */     //   76	271	12	isStatic	Z
/*     */     //   138	161	16	javaParamStart	I
/*     */     //   145	154	17	paramTypes	[Ljava/lang/Class;
/*     */     //   150	149	18	numberMethodParams	I
/*     */     //   153	146	19	isFirstExpressionContext	Z
/*     */     //   166	133	21	argsLen	I
/*     */     //   184	38	22	javaClass	Ljava/lang/Class;
/*     */     //   219	80	20	scoreStart	I
/*     */     //   255	44	22	score	I
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String replaceDash(String name) {
/* 294 */     char dash = '-';
/* 295 */     StringBuffer buff = new StringBuffer("");
/* 296 */     for (int i = 0; i < name.length(); i++) {
/*     */       
/* 298 */       if (name.charAt(i) != dash)
/*     */       {
/* 300 */         if (i > 0 && name.charAt(i - 1) == dash) {
/* 301 */           buff.append(Character.toUpperCase(name.charAt(i)));
/*     */         } else {
/* 303 */           buff.append(name.charAt(i));
/*     */         }  } 
/* 305 */     }  return buff.toString();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Method getElementMethod(Class classObj, String name) throws NoSuchMethodException, SecurityException, TransformerException {
/* 325 */     Method bestMethod = null;
/* 326 */     Method[] methods = classObj.getMethods();
/* 327 */     int nMethods = methods.length;
/* 328 */     int bestScoreCount = 0;
/* 329 */     for (int i = 0; i < nMethods; i++) {
/*     */       
/* 331 */       Method method = methods[i];
/*     */       
/* 333 */       if (method.getName().equals(name)) {
/*     */         
/* 335 */         Class[] paramTypes = method.getParameterTypes();
/* 336 */         if (paramTypes.length == 2 && paramTypes[1].isAssignableFrom(ElemExtensionCall.class) && paramTypes[0].isAssignableFrom(XSLProcessorContext.class))
/*     */         {
/*     */ 
/*     */           
/* 340 */           if (++bestScoreCount == 1) {
/* 341 */             bestMethod = method;
/*     */           } else {
/*     */             break;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 348 */     if (null == bestMethod)
/*     */     {
/* 350 */       throw new NoSuchMethodException(errString("element", "method", classObj, name, 0, null));
/*     */     }
/*     */     
/* 353 */     if (bestScoreCount > 1) {
/* 354 */       throw new TransformerException(XSLMessages.createMessage("ER_MORE_MATCH_ELEMENT", new Object[] { name }));
/*     */     }
/* 356 */     return bestMethod;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void convertParams(Object[] argsIn, Object[][] argsOut, Class[] paramTypes, ExpressionContext exprContext) throws TransformerException {
/* 376 */     if (paramTypes == null) {
/* 377 */       argsOut[0] = null;
/*     */     } else {
/*     */       
/* 380 */       int nParams = paramTypes.length;
/* 381 */       argsOut[0] = new Object[nParams];
/* 382 */       int paramIndex = 0;
/* 383 */       if (nParams > 0 && ExpressionContext.class.isAssignableFrom(paramTypes[0])) {
/*     */ 
/*     */         
/* 386 */         argsOut[0][0] = exprContext;
/*     */         
/* 388 */         paramIndex++;
/*     */       } 
/*     */       
/* 391 */       if (argsIn != null)
/*     */       {
/* 393 */         for (int i = argsIn.length - nParams + paramIndex; paramIndex < nParams; i++, paramIndex++)
/*     */         {
/*     */           
/* 396 */           argsOut[0][paramIndex] = convert(argsIn[i], paramTypes[paramIndex]);
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static class ConversionInfo
/*     */   {
/*     */     Class m_class;
/*     */     
/*     */     int m_score;
/*     */     
/*     */     ConversionInfo(Class cl, int score) {
/* 410 */       this.m_class = cl;
/* 411 */       this.m_score = score;
/*     */     }
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
/* 424 */   static ConversionInfo[] m_javaObjConversions = new ConversionInfo[] { new ConversionInfo(double.class, 11), new ConversionInfo(float.class, 12), new ConversionInfo(long.class, 13), new ConversionInfo(int.class, 14), new ConversionInfo(short.class, 15), new ConversionInfo(char.class, 16), new ConversionInfo(byte.class, 17), new ConversionInfo(String.class, 18) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 439 */   static ConversionInfo[] m_booleanConversions = new ConversionInfo[] { new ConversionInfo(boolean.class, 0), new ConversionInfo(Boolean.class, 1), new ConversionInfo(Object.class, 2), new ConversionInfo(String.class, 3) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 450 */   static ConversionInfo[] m_numberConversions = new ConversionInfo[] { new ConversionInfo(double.class, 0), new ConversionInfo(Double.class, 1), new ConversionInfo(float.class, 3), new ConversionInfo(long.class, 4), new ConversionInfo(int.class, 5), new ConversionInfo(short.class, 6), new ConversionInfo(char.class, 7), new ConversionInfo(byte.class, 8), new ConversionInfo(boolean.class, 9), new ConversionInfo(String.class, 10), new ConversionInfo(Object.class, 11) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 468 */   static ConversionInfo[] m_stringConversions = new ConversionInfo[] { new ConversionInfo(String.class, 0), new ConversionInfo(Object.class, 1), new ConversionInfo(char.class, 2), new ConversionInfo(double.class, 3), new ConversionInfo(float.class, 3), new ConversionInfo(long.class, 3), new ConversionInfo(int.class, 3), new ConversionInfo(short.class, 3), new ConversionInfo(byte.class, 3), new ConversionInfo(boolean.class, 4) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 485 */   static ConversionInfo[] m_rtfConversions = new ConversionInfo[] { new ConversionInfo(NodeIterator.class, 0), new ConversionInfo(NodeList.class, 1), new ConversionInfo(Node.class, 2), new ConversionInfo(String.class, 3), new ConversionInfo(Object.class, 5), new ConversionInfo(char.class, 6), new ConversionInfo(double.class, 7), new ConversionInfo(float.class, 7), new ConversionInfo(long.class, 7), new ConversionInfo(int.class, 7), new ConversionInfo(short.class, 7), new ConversionInfo(byte.class, 7), new ConversionInfo(boolean.class, 8) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 505 */   static ConversionInfo[] m_nodesetConversions = new ConversionInfo[] { new ConversionInfo(NodeIterator.class, 0), new ConversionInfo(NodeList.class, 1), new ConversionInfo(Node.class, 2), new ConversionInfo(String.class, 3), new ConversionInfo(Object.class, 5), new ConversionInfo(char.class, 6), new ConversionInfo(double.class, 7), new ConversionInfo(float.class, 7), new ConversionInfo(long.class, 7), new ConversionInfo(int.class, 7), new ConversionInfo(short.class, 7), new ConversionInfo(byte.class, 7), new ConversionInfo(boolean.class, 8) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 525 */   static ConversionInfo[][] m_conversions = new ConversionInfo[][] { m_javaObjConversions, m_booleanConversions, m_numberConversions, m_stringConversions, m_nodesetConversions, m_rtfConversions };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int scoreMatch(Class[] javaParamTypes, int javaParamsStart, Object[] xsltArgs, int score) {
/* 552 */     if (xsltArgs == null || javaParamTypes == null)
/* 553 */       return score; 
/* 554 */     int nParams = xsltArgs.length;
/* 555 */     int i = nParams - javaParamTypes.length + javaParamsStart, javaParamTypesIndex = javaParamsStart;
/* 556 */     for (; i < nParams; 
/* 557 */       i++, javaParamTypesIndex++) {
/*     */       
/* 559 */       Object xsltObj = xsltArgs[i];
/* 560 */       int xsltClassType = (xsltObj instanceof XObject) ? ((XObject)xsltObj).getType() : 0;
/*     */ 
/*     */       
/* 563 */       Class javaClass = javaParamTypes[javaParamTypesIndex];
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 568 */       if (xsltClassType == -1) {
/*     */ 
/*     */ 
/*     */         
/* 572 */         if (!javaClass.isPrimitive()) {
/*     */ 
/*     */           
/* 575 */           score += 10;
/*     */         }
/*     */         else {
/*     */           
/* 579 */           return -1;
/*     */         } 
/*     */       } else {
/* 582 */         ConversionInfo[] convInfo = m_conversions[xsltClassType];
/* 583 */         int nConversions = convInfo.length;
/*     */         int k;
/* 585 */         for (k = 0; k < nConversions; k++) {
/*     */           
/* 587 */           ConversionInfo cinfo = convInfo[k];
/* 588 */           if (javaClass.isAssignableFrom(cinfo.m_class)) {
/*     */             
/* 590 */             score += cinfo.m_score;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 595 */         if (k == nConversions)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 623 */           if (0 == xsltClassType) {
/*     */             
/* 625 */             Class realClass = null;
/*     */             
/* 627 */             if (xsltObj instanceof XObject) {
/*     */               
/* 629 */               Object realObj = ((XObject)xsltObj).object();
/* 630 */               if (null != realObj) {
/*     */                 
/* 632 */                 realClass = realObj.getClass();
/*     */               
/*     */               }
/*     */               else {
/*     */                 
/* 637 */                 score += 10;
/*     */                 
/*     */                 i++;
/*     */                 javaParamTypesIndex++;
/*     */               } 
/*     */             } else {
/* 643 */               realClass = xsltObj.getClass();
/*     */             } 
/*     */             
/* 646 */             if (javaClass.isAssignableFrom(realClass)) {
/*     */               
/* 648 */               score += 0;
/*     */             } else {
/*     */               
/* 651 */               return -1;
/*     */             } 
/*     */           } else {
/* 654 */             return -1;
/*     */           }  } 
/*     */       } 
/* 657 */     }  return score;
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
/*     */ 
/*     */   
/*     */   static Object convert(Object xsltObj, Class javaClass) throws TransformerException {
/* 673 */     if (xsltObj instanceof XObject) {
/*     */       DTMIterator iter; int rootHandle, childHandle; DTM dtm; Node child;
/* 675 */       XObject xobj = (XObject)xsltObj;
/* 676 */       int xsltClassType = xobj.getType();
/*     */       
/* 678 */       switch (xsltClassType) {
/*     */         
/*     */         case -1:
/* 681 */           return null;
/*     */ 
/*     */         
/*     */         case 1:
/* 685 */           if (javaClass == String.class) {
/* 686 */             return xobj.str();
/*     */           }
/* 688 */           return new Boolean(xobj.bool());
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 693 */           if (javaClass == String.class)
/* 694 */             return xobj.str(); 
/* 695 */           if (javaClass == boolean.class) {
/* 696 */             return new Boolean(xobj.bool());
/*     */           }
/*     */           
/* 699 */           return convertDoubleToNumber(xobj.num(), javaClass);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 706 */           if (javaClass == String.class || javaClass == Object.class)
/*     */           {
/* 708 */             return xobj.str(); } 
/* 709 */           if (javaClass == char.class) {
/*     */             
/* 711 */             String str = xobj.str();
/* 712 */             if (str.length() > 0) {
/* 713 */               return new Character(str.charAt(0));
/*     */             }
/* 715 */             return null;
/*     */           } 
/* 717 */           if (javaClass == boolean.class) {
/* 718 */             return new Boolean(xobj.bool());
/*     */           }
/*     */           
/* 721 */           return convertDoubleToNumber(xobj.num(), javaClass);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 5:
/* 734 */           if (javaClass == NodeIterator.class || javaClass == Object.class) {
/*     */ 
/*     */             
/* 737 */             DTMIterator dtmIter = ((XRTreeFrag)xobj).asNodeIterator();
/* 738 */             return new DTMNodeIterator(dtmIter);
/*     */           } 
/* 740 */           if (javaClass == NodeList.class)
/*     */           {
/* 742 */             return ((XRTreeFrag)xobj).convertToNodeset();
/*     */           }
/*     */ 
/*     */           
/* 746 */           if (javaClass == Node.class) {
/*     */             
/* 748 */             DTMIterator dTMIterator = ((XRTreeFrag)xobj).asNodeIterator();
/* 749 */             int i = dTMIterator.nextNode();
/* 750 */             DTM dTM = dTMIterator.getDTM(i);
/* 751 */             return dTM.getNode(dTM.getFirstChild(i));
/*     */           } 
/* 753 */           if (javaClass == String.class)
/*     */           {
/* 755 */             return xobj.str();
/*     */           }
/* 757 */           if (javaClass == boolean.class)
/*     */           {
/* 759 */             return new Boolean(xobj.bool());
/*     */           }
/* 761 */           if (javaClass.isPrimitive())
/*     */           {
/* 763 */             return convertDoubleToNumber(xobj.num(), javaClass);
/*     */           }
/*     */ 
/*     */           
/* 767 */           iter = ((XRTreeFrag)xobj).asNodeIterator();
/* 768 */           rootHandle = iter.nextNode();
/* 769 */           dtm = iter.getDTM(rootHandle);
/* 770 */           child = dtm.getNode(dtm.getFirstChild(rootHandle));
/*     */           
/* 772 */           if (javaClass.isAssignableFrom(child.getClass())) {
/* 773 */             return child;
/*     */           }
/* 775 */           return null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 4:
/* 788 */           if (javaClass == NodeIterator.class || javaClass == Object.class)
/*     */           {
/*     */             
/* 791 */             return xobj.nodeset();
/*     */           }
/*     */ 
/*     */           
/* 795 */           if (javaClass == NodeList.class)
/*     */           {
/* 797 */             return xobj.nodelist();
/*     */           }
/*     */ 
/*     */           
/* 801 */           if (javaClass == Node.class) {
/*     */ 
/*     */ 
/*     */             
/* 805 */             DTMIterator ni = xobj.iter();
/* 806 */             int handle = ni.nextNode();
/* 807 */             if (handle != -1) {
/* 808 */               return ni.getDTM(handle).getNode(handle);
/*     */             }
/* 810 */             return null;
/*     */           } 
/* 812 */           if (javaClass == String.class)
/*     */           {
/* 814 */             return xobj.str();
/*     */           }
/* 816 */           if (javaClass == boolean.class)
/*     */           {
/* 818 */             return new Boolean(xobj.bool());
/*     */           }
/* 820 */           if (javaClass.isPrimitive())
/*     */           {
/* 822 */             return convertDoubleToNumber(xobj.num(), javaClass);
/*     */           }
/*     */ 
/*     */           
/* 826 */           iter = xobj.iter();
/* 827 */           childHandle = iter.nextNode();
/* 828 */           dtm = iter.getDTM(childHandle);
/* 829 */           child = dtm.getNode(childHandle);
/* 830 */           if (javaClass.isAssignableFrom(child.getClass())) {
/* 831 */             return child;
/*     */           }
/* 833 */           return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 840 */       xsltObj = xobj.object();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 845 */     if (null != xsltObj) {
/*     */       
/* 847 */       if (javaClass == String.class)
/*     */       {
/* 849 */         return xsltObj.toString();
/*     */       }
/* 851 */       if (javaClass.isPrimitive()) {
/*     */ 
/*     */         
/* 854 */         XString xstr = new XString(xsltObj.toString());
/* 855 */         double num = xstr.num();
/* 856 */         return convertDoubleToNumber(num, javaClass);
/*     */       } 
/* 858 */       if (javaClass == Class.class)
/*     */       {
/* 860 */         return xsltObj.getClass();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 865 */       return xsltObj;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 871 */     return xsltObj;
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
/*     */   
/*     */   static Object convertDoubleToNumber(double num, Class javaClass) {
/* 886 */     if (javaClass == double.class || javaClass == Double.class)
/*     */     {
/* 888 */       return new Double(num); } 
/* 889 */     if (javaClass == float.class)
/* 890 */       return new Float(num); 
/* 891 */     if (javaClass == long.class)
/*     */     {
/*     */ 
/*     */       
/* 895 */       return new Long((long)num);
/*     */     }
/* 897 */     if (javaClass == int.class)
/*     */     {
/*     */ 
/*     */       
/* 901 */       return new Integer((int)num);
/*     */     }
/* 903 */     if (javaClass == short.class)
/*     */     {
/*     */ 
/*     */       
/* 907 */       return new Short((short)(int)num);
/*     */     }
/* 909 */     if (javaClass == char.class)
/*     */     {
/*     */ 
/*     */       
/* 913 */       return new Character((char)(int)num);
/*     */     }
/* 915 */     if (javaClass == byte.class)
/*     */     {
/*     */ 
/*     */       
/* 919 */       return new Byte((byte)(int)num);
/*     */     }
/*     */ 
/*     */     
/* 923 */     return new Double(num);
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
/*     */ 
/*     */   
/*     */   private static String errString(String callType, String searchType, Class classObj, String funcName, int searchMethod, Object[] xsltArgs) {
/* 939 */     String resultString = "For extension " + callType + ", could not find " + searchType + " ";
/*     */     
/* 941 */     switch (searchMethod) {
/*     */       
/*     */       case 1:
/* 944 */         return resultString + "static " + classObj.getName() + "." + funcName + "([ExpressionContext,] " + errArgs(xsltArgs, 0) + ").";
/*     */ 
/*     */       
/*     */       case 2:
/* 948 */         return resultString + classObj.getName() + "." + funcName + "([ExpressionContext,] " + errArgs(xsltArgs, 0) + ").";
/*     */ 
/*     */       
/*     */       case 3:
/* 952 */         return resultString + classObj.getName() + "." + funcName + "([ExpressionContext,] " + errArgs(xsltArgs, 0) + ").\n" + "Checked both static and instance methods.";
/*     */ 
/*     */       
/*     */       case 4:
/* 956 */         return resultString + "static " + classObj.getName() + "." + funcName + "([ExpressionContext, ]" + errArgs(xsltArgs, 0) + ") nor\n" + classObj + "." + funcName + "([ExpressionContext,] " + errArgs(xsltArgs, 1) + ").";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 961 */     if (callType.equals("function"))
/*     */     {
/* 963 */       return resultString + classObj.getName() + "([ExpressionContext,] " + errArgs(xsltArgs, 0) + ").";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 968 */     return resultString + classObj.getName() + "." + funcName + "(org.apache.xalan.extensions.XSLProcessorContext, " + "org.apache.xalan.templates.ElemExtensionCall).";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String errArgs(Object[] xsltArgs, int startingArg) {
/* 979 */     StringBuffer returnArgs = new StringBuffer();
/* 980 */     for (int i = startingArg; i < xsltArgs.length; i++) {
/*     */       
/* 982 */       if (i != startingArg)
/* 983 */         returnArgs.append(", "); 
/* 984 */       if (xsltArgs[i] instanceof XObject) {
/* 985 */         returnArgs.append(((XObject)xsltArgs[i]).getTypeString());
/*     */       } else {
/* 987 */         returnArgs.append(xsltArgs[i].getClass().getName());
/*     */       } 
/* 989 */     }  return returnArgs.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/MethodResolver.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */