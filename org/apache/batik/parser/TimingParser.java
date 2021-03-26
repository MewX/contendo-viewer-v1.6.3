/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import org.apache.batik.xml.XMLUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TimingParser
/*     */   extends AbstractParser
/*     */ {
/*     */   protected static final int TIME_OFFSET = 0;
/*     */   protected static final int TIME_SYNCBASE = 1;
/*     */   protected static final int TIME_EVENTBASE = 2;
/*     */   protected static final int TIME_REPEAT = 3;
/*     */   protected static final int TIME_ACCESSKEY = 4;
/*     */   protected static final int TIME_ACCESSKEY_SVG12 = 5;
/*     */   protected static final int TIME_MEDIA_MARKER = 6;
/*     */   protected static final int TIME_WALLCLOCK = 7;
/*     */   protected static final int TIME_INDEFINITE = 8;
/*     */   protected boolean useSVG11AccessKeys;
/*     */   protected boolean useSVG12AccessKeys;
/*     */   
/*     */   public TimingParser(boolean useSVG11AccessKeys, boolean useSVG12AccessKeys) {
/*  67 */     this.useSVG11AccessKeys = useSVG11AccessKeys;
/*  68 */     this.useSVG12AccessKeys = useSVG12AccessKeys;
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
/*     */   protected Object[] parseTimingSpecifier() throws ParseException, IOException {
/*  87 */     skipSpaces();
/*  88 */     boolean escaped = false;
/*  89 */     if (this.current == 92) {
/*  90 */       escaped = true;
/*  91 */       this.current = this.reader.read();
/*     */     } 
/*  93 */     Object[] ret = null;
/*  94 */     if (this.current == 43 || (this.current == 45 && !escaped) || (this.current >= 48 && this.current <= 57)) {
/*     */       
/*  96 */       float offset = parseOffset();
/*  97 */       ret = new Object[] { Integer.valueOf(0), Float.valueOf(offset) };
/*  98 */     } else if (XMLUtilities.isXMLNameFirstCharacter((char)this.current)) {
/*  99 */       ret = parseIDValue(escaped);
/*     */     } else {
/* 101 */       reportUnexpectedCharacterError(this.current);
/*     */     } 
/* 103 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String parseName() throws ParseException, IOException {
/* 110 */     StringBuffer sb = new StringBuffer();
/* 111 */     boolean midEscaped = false;
/*     */     do {
/* 113 */       sb.append((char)this.current);
/* 114 */       this.current = this.reader.read();
/* 115 */       midEscaped = false;
/* 116 */       if (this.current != 92)
/* 117 */         continue;  midEscaped = true;
/* 118 */       this.current = this.reader.read();
/*     */     
/*     */     }
/* 121 */     while (XMLUtilities.isXMLNameCharacter((char)this.current) && (midEscaped || (this.current != 45 && this.current != 46)));
/* 122 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object[] parseIDValue(boolean escaped) throws ParseException, IOException {
/* 131 */     String id = parseName();
/* 132 */     if (((id.equals("accessKey") && this.useSVG11AccessKeys) || id.equals("accesskey")) && !escaped) {
/*     */ 
/*     */       
/* 135 */       if (this.current != 40) {
/* 136 */         reportUnexpectedCharacterError(this.current);
/*     */       }
/* 138 */       this.current = this.reader.read();
/* 139 */       if (this.current == -1) {
/* 140 */         reportError("end.of.stream", new Object[0]);
/*     */       }
/* 142 */       char key = (char)this.current;
/* 143 */       this.current = this.reader.read();
/* 144 */       if (this.current != 41) {
/* 145 */         reportUnexpectedCharacterError(this.current);
/*     */       }
/* 147 */       this.current = this.reader.read();
/* 148 */       skipSpaces();
/* 149 */       float f = 0.0F;
/* 150 */       if (this.current == 43 || this.current == 45) {
/* 151 */         f = parseOffset();
/*     */       }
/* 153 */       return new Object[] { Integer.valueOf(4), Float.valueOf(f), Character.valueOf(key) };
/*     */     } 
/*     */     
/* 156 */     if (id.equals("accessKey") && this.useSVG12AccessKeys && !escaped) {
/* 157 */       if (this.current != 40) {
/* 158 */         reportUnexpectedCharacterError(this.current);
/*     */       }
/* 160 */       this.current = this.reader.read();
/* 161 */       StringBuffer keyName = new StringBuffer();
/*     */ 
/*     */ 
/*     */       
/* 165 */       while ((this.current >= 65 && this.current <= 90) || (this.current >= 97 && this.current <= 122) || (this.current >= 48 && this.current <= 57) || this.current == 43) {
/* 166 */         keyName.append((char)this.current);
/* 167 */         this.current = this.reader.read();
/*     */       } 
/* 169 */       if (this.current != 41) {
/* 170 */         reportUnexpectedCharacterError(this.current);
/*     */       }
/* 172 */       this.current = this.reader.read();
/* 173 */       skipSpaces();
/* 174 */       float f = 0.0F;
/* 175 */       if (this.current == 43 || this.current == 45) {
/* 176 */         f = parseOffset();
/*     */       }
/* 178 */       return new Object[] { Integer.valueOf(5), Float.valueOf(f), keyName.toString() };
/*     */     } 
/*     */     
/* 181 */     if (id.equals("wallclock") && !escaped) {
/* 182 */       if (this.current != 40) {
/* 183 */         reportUnexpectedCharacterError(this.current);
/*     */       }
/* 185 */       this.current = this.reader.read();
/* 186 */       skipSpaces();
/* 187 */       Calendar wallclockValue = parseWallclockValue();
/* 188 */       skipSpaces();
/* 189 */       if (this.current != 41) {
/* 190 */         reportError("character.unexpected", new Object[] { Integer.valueOf(this.current) });
/*     */       }
/*     */       
/* 193 */       this.current = this.reader.read();
/* 194 */       return new Object[] { Integer.valueOf(7), wallclockValue };
/* 195 */     }  if (id.equals("indefinite") && !escaped) {
/* 196 */       return new Object[] { Integer.valueOf(8) };
/*     */     }
/* 198 */     if (this.current == 46) {
/* 199 */       this.current = this.reader.read();
/* 200 */       if (this.current == 92) {
/* 201 */         escaped = true;
/* 202 */         this.current = this.reader.read();
/*     */       } 
/* 204 */       if (!XMLUtilities.isXMLNameFirstCharacter((char)this.current)) {
/* 205 */         reportUnexpectedCharacterError(this.current);
/*     */       }
/* 207 */       String id2 = parseName();
/* 208 */       if ((id2.equals("begin") || id2.equals("end")) && !escaped) {
/* 209 */         skipSpaces();
/* 210 */         float f1 = 0.0F;
/* 211 */         if (this.current == 43 || this.current == 45) {
/* 212 */           f1 = parseOffset();
/*     */         }
/* 214 */         return new Object[] { Integer.valueOf(1), Float.valueOf(f1), id, id2 };
/*     */       } 
/*     */ 
/*     */       
/* 218 */       if (id2.equals("repeat") && !escaped) {
/* 219 */         Integer repeatIteration = null;
/* 220 */         if (this.current == 40) {
/* 221 */           this.current = this.reader.read();
/* 222 */           repeatIteration = Integer.valueOf(parseDigits());
/* 223 */           if (this.current != 41) {
/* 224 */             reportUnexpectedCharacterError(this.current);
/*     */           }
/* 226 */           this.current = this.reader.read();
/*     */         } 
/* 228 */         skipSpaces();
/* 229 */         float f1 = 0.0F;
/* 230 */         if (this.current == 43 || this.current == 45) {
/* 231 */           f1 = parseOffset();
/*     */         }
/* 233 */         return new Object[] { Integer.valueOf(3), Float.valueOf(f1), id, repeatIteration };
/*     */       } 
/*     */ 
/*     */       
/* 237 */       if (id2.equals("marker") && !escaped) {
/* 238 */         if (this.current != 40) {
/* 239 */           reportUnexpectedCharacterError(this.current);
/*     */         }
/* 241 */         String markerName = parseName();
/* 242 */         if (this.current != 41) {
/* 243 */           reportUnexpectedCharacterError(this.current);
/*     */         }
/* 245 */         this.current = this.reader.read();
/* 246 */         return new Object[] { Integer.valueOf(6), id, markerName };
/*     */       } 
/*     */ 
/*     */       
/* 250 */       skipSpaces();
/* 251 */       float f = 0.0F;
/* 252 */       if (this.current == 43 || this.current == 45) {
/* 253 */         f = parseOffset();
/*     */       }
/* 255 */       return new Object[] { Integer.valueOf(2), Float.valueOf(f), id, id2 };
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     skipSpaces();
/* 262 */     float offset = 0.0F;
/* 263 */     if (this.current == 43 || this.current == 45) {
/* 264 */       offset = parseOffset();
/*     */     }
/* 266 */     return new Object[] { Integer.valueOf(2), Float.valueOf(offset), null, id };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float parseClockValue() throws ParseException, IOException {
/*     */     float offset;
/* 278 */     int d1 = parseDigits();
/*     */     
/* 280 */     if (this.current == 58) {
/* 281 */       this.current = this.reader.read();
/* 282 */       int d2 = parseDigits();
/* 283 */       if (this.current == 58) {
/* 284 */         this.current = this.reader.read();
/* 285 */         int d3 = parseDigits();
/* 286 */         offset = (d1 * 3600 + d2 * 60 + d3);
/*     */       } else {
/* 288 */         offset = (d1 * 60 + d2);
/*     */       } 
/* 290 */       if (this.current == 46) {
/* 291 */         this.current = this.reader.read();
/* 292 */         offset += parseFraction();
/*     */       } 
/* 294 */     } else if (this.current == 46) {
/* 295 */       this.current = this.reader.read();
/* 296 */       offset = (parseFraction() + d1) * parseUnit();
/*     */     } else {
/* 298 */       offset = d1 * parseUnit();
/*     */     } 
/* 300 */     return offset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float parseOffset() throws ParseException, IOException {
/* 307 */     boolean offsetNegative = false;
/* 308 */     if (this.current == 45) {
/* 309 */       offsetNegative = true;
/* 310 */       this.current = this.reader.read();
/* 311 */       skipSpaces();
/* 312 */     } else if (this.current == 43) {
/* 313 */       this.current = this.reader.read();
/* 314 */       skipSpaces();
/*     */     } 
/* 316 */     if (offsetNegative) {
/* 317 */       return -parseClockValue();
/*     */     }
/* 319 */     return parseClockValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int parseDigits() throws ParseException, IOException {
/* 326 */     int value = 0;
/* 327 */     if (this.current < 48 || this.current > 57) {
/* 328 */       reportUnexpectedCharacterError(this.current);
/*     */     }
/*     */     do {
/* 331 */       value = value * 10 + this.current - 48;
/* 332 */       this.current = this.reader.read();
/* 333 */     } while (this.current >= 48 && this.current <= 57);
/* 334 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float parseFraction() throws ParseException, IOException {
/* 341 */     float value = 0.0F;
/* 342 */     if (this.current < 48 || this.current > 57) {
/* 343 */       reportUnexpectedCharacterError(this.current);
/*     */     }
/* 345 */     float weight = 0.1F;
/*     */     do {
/* 347 */       value += weight * (this.current - 48);
/* 348 */       weight *= 0.1F;
/* 349 */       this.current = this.reader.read();
/* 350 */     } while (this.current >= 48 && this.current <= 57);
/* 351 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float parseUnit() throws ParseException, IOException {
/* 358 */     if (this.current == 104) {
/* 359 */       this.current = this.reader.read();
/* 360 */       return 3600.0F;
/* 361 */     }  if (this.current == 109) {
/* 362 */       this.current = this.reader.read();
/* 363 */       if (this.current == 105) {
/* 364 */         this.current = this.reader.read();
/* 365 */         if (this.current != 110) {
/* 366 */           reportUnexpectedCharacterError(this.current);
/*     */         }
/* 368 */         this.current = this.reader.read();
/* 369 */         return 60.0F;
/* 370 */       }  if (this.current == 115) {
/* 371 */         this.current = this.reader.read();
/* 372 */         return 0.001F;
/*     */       } 
/* 374 */       reportUnexpectedCharacterError(this.current);
/*     */     }
/* 376 */     else if (this.current == 115) {
/* 377 */       this.current = this.reader.read();
/*     */     } 
/* 379 */     return 1.0F;
/*     */   }
/*     */   
/*     */   protected Calendar parseWallclockValue() throws ParseException, IOException {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore_1
/*     */     //   2: iconst_0
/*     */     //   3: istore_2
/*     */     //   4: iconst_0
/*     */     //   5: istore_3
/*     */     //   6: iconst_0
/*     */     //   7: istore #4
/*     */     //   9: iconst_0
/*     */     //   10: istore #5
/*     */     //   12: iconst_0
/*     */     //   13: istore #6
/*     */     //   15: iconst_0
/*     */     //   16: istore #7
/*     */     //   18: iconst_0
/*     */     //   19: istore #8
/*     */     //   21: fconst_0
/*     */     //   22: fstore #9
/*     */     //   24: iconst_0
/*     */     //   25: istore #10
/*     */     //   27: iconst_0
/*     */     //   28: istore #11
/*     */     //   30: iconst_0
/*     */     //   31: istore #12
/*     */     //   33: iconst_0
/*     */     //   34: istore #13
/*     */     //   36: aconst_null
/*     */     //   37: astore #14
/*     */     //   39: aload_0
/*     */     //   40: invokevirtual parseDigits : ()I
/*     */     //   43: istore #15
/*     */     //   45: aload_0
/*     */     //   46: getfield current : I
/*     */     //   49: bipush #45
/*     */     //   51: if_icmpne -> 155
/*     */     //   54: iconst_1
/*     */     //   55: istore #10
/*     */     //   57: iload #15
/*     */     //   59: istore_1
/*     */     //   60: aload_0
/*     */     //   61: aload_0
/*     */     //   62: getfield reader : Lorg/apache/batik/util/io/NormalizingReader;
/*     */     //   65: invokevirtual read : ()I
/*     */     //   68: putfield current : I
/*     */     //   71: aload_0
/*     */     //   72: invokevirtual parseDigits : ()I
/*     */     //   75: istore_2
/*     */     //   76: aload_0
/*     */     //   77: getfield current : I
/*     */     //   80: bipush #45
/*     */     //   82: if_icmpeq -> 93
/*     */     //   85: aload_0
/*     */     //   86: aload_0
/*     */     //   87: getfield current : I
/*     */     //   90: invokevirtual reportUnexpectedCharacterError : (I)V
/*     */     //   93: aload_0
/*     */     //   94: aload_0
/*     */     //   95: getfield reader : Lorg/apache/batik/util/io/NormalizingReader;
/*     */     //   98: invokevirtual read : ()I
/*     */     //   101: putfield current : I
/*     */     //   104: aload_0
/*     */     //   105: invokevirtual parseDigits : ()I
/*     */     //   108: istore_3
/*     */     //   109: aload_0
/*     */     //   110: getfield current : I
/*     */     //   113: bipush #84
/*     */     //   115: if_icmpeq -> 121
/*     */     //   118: goto -> 443
/*     */     //   121: aload_0
/*     */     //   122: aload_0
/*     */     //   123: getfield reader : Lorg/apache/batik/util/io/NormalizingReader;
/*     */     //   126: invokevirtual read : ()I
/*     */     //   129: putfield current : I
/*     */     //   132: aload_0
/*     */     //   133: invokevirtual parseDigits : ()I
/*     */     //   136: istore #15
/*     */     //   138: aload_0
/*     */     //   139: getfield current : I
/*     */     //   142: bipush #58
/*     */     //   144: if_icmpeq -> 155
/*     */     //   147: aload_0
/*     */     //   148: aload_0
/*     */     //   149: getfield current : I
/*     */     //   152: invokevirtual reportUnexpectedCharacterError : (I)V
/*     */     //   155: aload_0
/*     */     //   156: getfield current : I
/*     */     //   159: bipush #58
/*     */     //   161: if_icmpne -> 443
/*     */     //   164: iconst_1
/*     */     //   165: istore #11
/*     */     //   167: iload #15
/*     */     //   169: istore #4
/*     */     //   171: aload_0
/*     */     //   172: aload_0
/*     */     //   173: getfield reader : Lorg/apache/batik/util/io/NormalizingReader;
/*     */     //   176: invokevirtual read : ()I
/*     */     //   179: putfield current : I
/*     */     //   182: aload_0
/*     */     //   183: invokevirtual parseDigits : ()I
/*     */     //   186: istore #5
/*     */     //   188: aload_0
/*     */     //   189: getfield current : I
/*     */     //   192: bipush #58
/*     */     //   194: if_icmpne -> 240
/*     */     //   197: aload_0
/*     */     //   198: aload_0
/*     */     //   199: getfield reader : Lorg/apache/batik/util/io/NormalizingReader;
/*     */     //   202: invokevirtual read : ()I
/*     */     //   205: putfield current : I
/*     */     //   208: aload_0
/*     */     //   209: invokevirtual parseDigits : ()I
/*     */     //   212: istore #6
/*     */     //   214: aload_0
/*     */     //   215: getfield current : I
/*     */     //   218: bipush #46
/*     */     //   220: if_icmpne -> 240
/*     */     //   223: aload_0
/*     */     //   224: aload_0
/*     */     //   225: getfield reader : Lorg/apache/batik/util/io/NormalizingReader;
/*     */     //   228: invokevirtual read : ()I
/*     */     //   231: putfield current : I
/*     */     //   234: aload_0
/*     */     //   235: invokevirtual parseFraction : ()F
/*     */     //   238: fstore #9
/*     */     //   240: aload_0
/*     */     //   241: getfield current : I
/*     */     //   244: bipush #90
/*     */     //   246: if_icmpne -> 270
/*     */     //   249: iconst_1
/*     */     //   250: istore #12
/*     */     //   252: ldc 'UTC'
/*     */     //   254: astore #14
/*     */     //   256: aload_0
/*     */     //   257: aload_0
/*     */     //   258: getfield reader : Lorg/apache/batik/util/io/NormalizingReader;
/*     */     //   261: invokevirtual read : ()I
/*     */     //   264: putfield current : I
/*     */     //   267: goto -> 443
/*     */     //   270: aload_0
/*     */     //   271: getfield current : I
/*     */     //   274: bipush #43
/*     */     //   276: if_icmpeq -> 288
/*     */     //   279: aload_0
/*     */     //   280: getfield current : I
/*     */     //   283: bipush #45
/*     */     //   285: if_icmpne -> 443
/*     */     //   288: new java/lang/StringBuffer
/*     */     //   291: dup
/*     */     //   292: invokespecial <init> : ()V
/*     */     //   295: astore #16
/*     */     //   297: iconst_1
/*     */     //   298: istore #12
/*     */     //   300: aload_0
/*     */     //   301: getfield current : I
/*     */     //   304: bipush #45
/*     */     //   306: if_icmpne -> 323
/*     */     //   309: iconst_1
/*     */     //   310: istore #13
/*     */     //   312: aload #16
/*     */     //   314: bipush #45
/*     */     //   316: invokevirtual append : (C)Ljava/lang/StringBuffer;
/*     */     //   319: pop
/*     */     //   320: goto -> 331
/*     */     //   323: aload #16
/*     */     //   325: bipush #43
/*     */     //   327: invokevirtual append : (C)Ljava/lang/StringBuffer;
/*     */     //   330: pop
/*     */     //   331: aload_0
/*     */     //   332: aload_0
/*     */     //   333: getfield reader : Lorg/apache/batik/util/io/NormalizingReader;
/*     */     //   336: invokevirtual read : ()I
/*     */     //   339: putfield current : I
/*     */     //   342: aload_0
/*     */     //   343: invokevirtual parseDigits : ()I
/*     */     //   346: istore #7
/*     */     //   348: iload #7
/*     */     //   350: bipush #10
/*     */     //   352: if_icmpge -> 363
/*     */     //   355: aload #16
/*     */     //   357: bipush #48
/*     */     //   359: invokevirtual append : (C)Ljava/lang/StringBuffer;
/*     */     //   362: pop
/*     */     //   363: aload #16
/*     */     //   365: iload #7
/*     */     //   367: invokevirtual append : (I)Ljava/lang/StringBuffer;
/*     */     //   370: pop
/*     */     //   371: aload_0
/*     */     //   372: getfield current : I
/*     */     //   375: bipush #58
/*     */     //   377: if_icmpeq -> 388
/*     */     //   380: aload_0
/*     */     //   381: aload_0
/*     */     //   382: getfield current : I
/*     */     //   385: invokevirtual reportUnexpectedCharacterError : (I)V
/*     */     //   388: aload #16
/*     */     //   390: bipush #58
/*     */     //   392: invokevirtual append : (C)Ljava/lang/StringBuffer;
/*     */     //   395: pop
/*     */     //   396: aload_0
/*     */     //   397: aload_0
/*     */     //   398: getfield reader : Lorg/apache/batik/util/io/NormalizingReader;
/*     */     //   401: invokevirtual read : ()I
/*     */     //   404: putfield current : I
/*     */     //   407: aload_0
/*     */     //   408: invokevirtual parseDigits : ()I
/*     */     //   411: istore #8
/*     */     //   413: iload #8
/*     */     //   415: bipush #10
/*     */     //   417: if_icmpge -> 428
/*     */     //   420: aload #16
/*     */     //   422: bipush #48
/*     */     //   424: invokevirtual append : (C)Ljava/lang/StringBuffer;
/*     */     //   427: pop
/*     */     //   428: aload #16
/*     */     //   430: iload #8
/*     */     //   432: invokevirtual append : (I)Ljava/lang/StringBuffer;
/*     */     //   435: pop
/*     */     //   436: aload #16
/*     */     //   438: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   441: astore #14
/*     */     //   443: iload #10
/*     */     //   445: ifne -> 461
/*     */     //   448: iload #11
/*     */     //   450: ifne -> 461
/*     */     //   453: aload_0
/*     */     //   454: aload_0
/*     */     //   455: getfield current : I
/*     */     //   458: invokevirtual reportUnexpectedCharacterError : (I)V
/*     */     //   461: iload #12
/*     */     //   463: ifeq -> 509
/*     */     //   466: iload #13
/*     */     //   468: ifeq -> 475
/*     */     //   471: iconst_m1
/*     */     //   472: goto -> 476
/*     */     //   475: iconst_1
/*     */     //   476: iload #7
/*     */     //   478: ldc 3600000
/*     */     //   480: imul
/*     */     //   481: iload #8
/*     */     //   483: ldc 60000
/*     */     //   485: imul
/*     */     //   486: iadd
/*     */     //   487: imul
/*     */     //   488: istore #17
/*     */     //   490: new java/util/SimpleTimeZone
/*     */     //   493: dup
/*     */     //   494: iload #17
/*     */     //   496: aload #14
/*     */     //   498: invokespecial <init> : (ILjava/lang/String;)V
/*     */     //   501: invokestatic getInstance : (Ljava/util/TimeZone;)Ljava/util/Calendar;
/*     */     //   504: astore #16
/*     */     //   506: goto -> 514
/*     */     //   509: invokestatic getInstance : ()Ljava/util/Calendar;
/*     */     //   512: astore #16
/*     */     //   514: iload #10
/*     */     //   516: ifeq -> 541
/*     */     //   519: iload #11
/*     */     //   521: ifeq -> 541
/*     */     //   524: aload #16
/*     */     //   526: iload_1
/*     */     //   527: iload_2
/*     */     //   528: iload_3
/*     */     //   529: iload #4
/*     */     //   531: iload #5
/*     */     //   533: iload #6
/*     */     //   535: invokevirtual set : (IIIIII)V
/*     */     //   538: goto -> 587
/*     */     //   541: iload #10
/*     */     //   543: ifeq -> 560
/*     */     //   546: aload #16
/*     */     //   548: iload_1
/*     */     //   549: iload_2
/*     */     //   550: iload_3
/*     */     //   551: iconst_0
/*     */     //   552: iconst_0
/*     */     //   553: iconst_0
/*     */     //   554: invokevirtual set : (IIIIII)V
/*     */     //   557: goto -> 587
/*     */     //   560: aload #16
/*     */     //   562: bipush #10
/*     */     //   564: iload #4
/*     */     //   566: invokevirtual set : (II)V
/*     */     //   569: aload #16
/*     */     //   571: bipush #12
/*     */     //   573: iload #5
/*     */     //   575: invokevirtual set : (II)V
/*     */     //   578: aload #16
/*     */     //   580: bipush #13
/*     */     //   582: iload #6
/*     */     //   584: invokevirtual set : (II)V
/*     */     //   587: fload #9
/*     */     //   589: fconst_0
/*     */     //   590: fcmpl
/*     */     //   591: ifne -> 610
/*     */     //   594: aload #16
/*     */     //   596: bipush #14
/*     */     //   598: fload #9
/*     */     //   600: ldc 1000.0
/*     */     //   602: fmul
/*     */     //   603: f2i
/*     */     //   604: invokevirtual set : (II)V
/*     */     //   607: goto -> 618
/*     */     //   610: aload #16
/*     */     //   612: bipush #14
/*     */     //   614: iconst_0
/*     */     //   615: invokevirtual set : (II)V
/*     */     //   618: aload #16
/*     */     //   620: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #387	-> 0
/*     */     //   #388	-> 21
/*     */     //   #389	-> 24
/*     */     //   #390	-> 27
/*     */     //   #391	-> 30
/*     */     //   #392	-> 33
/*     */     //   #393	-> 36
/*     */     //   #395	-> 39
/*     */     //   #397	-> 45
/*     */     //   #398	-> 54
/*     */     //   #399	-> 57
/*     */     //   #400	-> 60
/*     */     //   #401	-> 71
/*     */     //   #402	-> 76
/*     */     //   #403	-> 85
/*     */     //   #405	-> 93
/*     */     //   #406	-> 104
/*     */     //   #407	-> 109
/*     */     //   #408	-> 118
/*     */     //   #410	-> 121
/*     */     //   #411	-> 132
/*     */     //   #412	-> 138
/*     */     //   #413	-> 147
/*     */     //   #416	-> 155
/*     */     //   #417	-> 164
/*     */     //   #418	-> 167
/*     */     //   #419	-> 171
/*     */     //   #420	-> 182
/*     */     //   #421	-> 188
/*     */     //   #422	-> 197
/*     */     //   #423	-> 208
/*     */     //   #424	-> 214
/*     */     //   #425	-> 223
/*     */     //   #426	-> 234
/*     */     //   #429	-> 240
/*     */     //   #430	-> 249
/*     */     //   #431	-> 252
/*     */     //   #432	-> 256
/*     */     //   #433	-> 270
/*     */     //   #434	-> 288
/*     */     //   #435	-> 297
/*     */     //   #436	-> 300
/*     */     //   #437	-> 309
/*     */     //   #438	-> 312
/*     */     //   #440	-> 323
/*     */     //   #442	-> 331
/*     */     //   #443	-> 342
/*     */     //   #444	-> 348
/*     */     //   #445	-> 355
/*     */     //   #447	-> 363
/*     */     //   #448	-> 371
/*     */     //   #449	-> 380
/*     */     //   #451	-> 388
/*     */     //   #452	-> 396
/*     */     //   #453	-> 407
/*     */     //   #454	-> 413
/*     */     //   #455	-> 420
/*     */     //   #457	-> 428
/*     */     //   #458	-> 436
/*     */     //   #462	-> 443
/*     */     //   #463	-> 453
/*     */     //   #466	-> 461
/*     */     //   #467	-> 466
/*     */     //   #469	-> 490
/*     */     //   #470	-> 506
/*     */     //   #471	-> 509
/*     */     //   #473	-> 514
/*     */     //   #474	-> 524
/*     */     //   #475	-> 541
/*     */     //   #476	-> 546
/*     */     //   #478	-> 560
/*     */     //   #479	-> 569
/*     */     //   #480	-> 578
/*     */     //   #482	-> 587
/*     */     //   #483	-> 594
/*     */     //   #485	-> 610
/*     */     //   #487	-> 618
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	621	0	this	Lorg/apache/batik/parser/TimingParser;
/*     */     //   2	619	1	y	I
/*     */     //   4	617	2	M	I
/*     */     //   6	615	3	d	I
/*     */     //   9	612	4	h	I
/*     */     //   12	609	5	m	I
/*     */     //   15	606	6	s	I
/*     */     //   18	603	7	tzh	I
/*     */     //   21	600	8	tzm	I
/*     */     //   24	597	9	frac	F
/*     */     //   27	594	10	dateSpecified	Z
/*     */     //   30	591	11	timeSpecified	Z
/*     */     //   33	588	12	tzSpecified	Z
/*     */     //   36	585	13	tzNegative	Z
/*     */     //   39	582	14	tzn	Ljava/lang/String;
/*     */     //   45	576	15	digits1	I
/*     */     //   297	146	16	tznb	Ljava/lang/StringBuffer;
/*     */     //   490	16	17	offset	I
/*     */     //   506	3	16	wallclockTime	Ljava/util/Calendar;
/*     */     //   514	107	16	wallclockTime	Ljava/util/Calendar;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/TimingParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */