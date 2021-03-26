/*      */ package org.apache.batik.transcoder.svg2svg;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Reader;
/*      */ import java.io.Writer;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import org.apache.batik.transcoder.ErrorHandler;
/*      */ import org.apache.batik.transcoder.TranscoderException;
/*      */ import org.apache.batik.xml.XMLException;
/*      */ import org.apache.batik.xml.XMLScanner;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PrettyPrinter
/*      */ {
/*      */   public static final int DOCTYPE_CHANGE = 0;
/*      */   public static final int DOCTYPE_REMOVE = 1;
/*      */   public static final int DOCTYPE_KEEP_UNCHANGED = 2;
/*      */   protected XMLScanner scanner;
/*      */   protected OutputManager output;
/*      */   protected Writer writer;
/*   65 */   protected ErrorHandler errorHandler = SVGTranscoder.DEFAULT_ERROR_HANDLER;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   70 */   protected String newline = "\n";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean format = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   80 */   protected int tabulationWidth = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   85 */   protected int documentWidth = 80;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   90 */   protected int doctypeOption = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String publicId;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String systemId;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String xmlDeclaration;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int type;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setXMLDeclaration(String s) {
/*  116 */     this.xmlDeclaration = s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoctypeOption(int i) {
/*  123 */     this.doctypeOption = i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPublicId(String s) {
/*  130 */     this.publicId = s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSystemId(String s) {
/*  137 */     this.systemId = s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNewline(String s) {
/*  144 */     this.newline = s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNewline() {
/*  151 */     return this.newline;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFormat(boolean b) {
/*  158 */     this.format = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getFormat() {
/*  165 */     return this.format;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTabulationWidth(int i) {
/*  172 */     this.tabulationWidth = Math.max(i, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTabulationWidth() {
/*  179 */     return this.tabulationWidth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentWidth(int i) {
/*  186 */     this.documentWidth = Math.max(i, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDocumentWidth() {
/*  193 */     return this.documentWidth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void print(Reader r, Writer w) throws TranscoderException, IOException {
/*      */     try {
/*  202 */       this.scanner = new XMLScanner(r);
/*  203 */       this.output = new OutputManager(this, w);
/*  204 */       this.writer = w;
/*  205 */       this.type = this.scanner.next();
/*      */       
/*  207 */       printXMLDecl();
/*      */       
/*      */       while (true) {
/*  210 */         switch (this.type) {
/*      */           case 1:
/*  212 */             this.output.printTopSpaces(getCurrentValue());
/*  213 */             this.scanner.clearBuffer();
/*  214 */             this.type = this.scanner.next();
/*      */             continue;
/*      */           case 4:
/*  217 */             this.output.printComment(getCurrentValue());
/*  218 */             this.scanner.clearBuffer();
/*  219 */             this.type = this.scanner.next();
/*      */             continue;
/*      */           case 5:
/*  222 */             printPI();
/*      */             continue;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/*  229 */       printDoctype();
/*      */       
/*      */       while (true) {
/*  232 */         this.scanner.clearBuffer();
/*  233 */         switch (this.type) {
/*      */           case 1:
/*  235 */             this.output.printTopSpaces(getCurrentValue());
/*  236 */             this.scanner.clearBuffer();
/*  237 */             this.type = this.scanner.next();
/*      */             continue;
/*      */           case 4:
/*  240 */             this.output.printComment(getCurrentValue());
/*  241 */             this.scanner.clearBuffer();
/*  242 */             this.type = this.scanner.next();
/*      */             continue;
/*      */           case 5:
/*  245 */             printPI();
/*      */             continue;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/*  252 */       if (this.type != 9) {
/*  253 */         throw fatalError("element", null);
/*      */       }
/*      */       
/*  256 */       printElement();
/*      */       
/*      */       while (true) {
/*  259 */         switch (this.type) {
/*      */           case 1:
/*  261 */             this.output.printTopSpaces(getCurrentValue());
/*  262 */             this.scanner.clearBuffer();
/*  263 */             this.type = this.scanner.next();
/*      */             continue;
/*      */           case 4:
/*  266 */             this.output.printComment(getCurrentValue());
/*  267 */             this.scanner.clearBuffer();
/*  268 */             this.type = this.scanner.next();
/*      */             continue;
/*      */           case 5:
/*  271 */             printPI();
/*      */             continue;
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/*  277 */     } catch (XMLException e) {
/*  278 */       this.errorHandler.fatalError(new TranscoderException(e.getMessage()));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void printXMLDecl() throws TranscoderException, XMLException, IOException {
/*  289 */     if (this.xmlDeclaration == null) {
/*  290 */       if (this.type == 2) {
/*  291 */         if (this.scanner.next() != 1) {
/*  292 */           throw fatalError("space", null);
/*      */         }
/*  294 */         char[] space1 = getCurrentValue();
/*      */         
/*  296 */         if (this.scanner.next() != 22) {
/*  297 */           throw fatalError("token", new Object[] { "version" });
/*      */         }
/*  299 */         this.type = this.scanner.next();
/*      */         
/*  301 */         char[] space2 = null;
/*  302 */         if (this.type == 1) {
/*  303 */           space2 = getCurrentValue();
/*  304 */           this.type = this.scanner.next();
/*      */         } 
/*  306 */         if (this.type != 15) {
/*  307 */           throw fatalError("token", new Object[] { "=" });
/*      */         }
/*  309 */         this.type = this.scanner.next();
/*      */         
/*  311 */         char[] space3 = null;
/*  312 */         if (this.type == 1) {
/*  313 */           space3 = getCurrentValue();
/*  314 */           this.type = this.scanner.next();
/*      */         } 
/*      */         
/*  317 */         if (this.type != 25) {
/*  318 */           throw fatalError("string", null);
/*      */         }
/*      */         
/*  321 */         char[] version = getCurrentValue();
/*  322 */         char versionDelim = this.scanner.getStringDelimiter();
/*      */         
/*  324 */         char[] space4 = null;
/*  325 */         char[] space5 = null;
/*  326 */         char[] space6 = null;
/*  327 */         char[] encoding = null;
/*  328 */         char encodingDelim = Character.MIN_VALUE;
/*  329 */         char[] space7 = null;
/*  330 */         char[] space8 = null;
/*  331 */         char[] space9 = null;
/*  332 */         char[] standalone = null;
/*  333 */         char standaloneDelim = Character.MIN_VALUE;
/*  334 */         char[] space10 = null;
/*      */         
/*  336 */         this.type = this.scanner.next();
/*  337 */         if (this.type == 1) {
/*  338 */           space4 = getCurrentValue();
/*  339 */           this.type = this.scanner.next();
/*      */           
/*  341 */           if (this.type == 23) {
/*  342 */             this.type = this.scanner.next();
/*  343 */             if (this.type == 1) {
/*  344 */               space5 = getCurrentValue();
/*  345 */               this.type = this.scanner.next();
/*      */             } 
/*  347 */             if (this.type != 15) {
/*  348 */               throw fatalError("token", new Object[] { "=" });
/*      */             }
/*  350 */             this.type = this.scanner.next();
/*  351 */             if (this.type == 1) {
/*  352 */               space6 = getCurrentValue();
/*  353 */               this.type = this.scanner.next();
/*      */             } 
/*  355 */             if (this.type != 25) {
/*  356 */               throw fatalError("string", null);
/*      */             }
/*      */             
/*  359 */             encoding = getCurrentValue();
/*  360 */             encodingDelim = this.scanner.getStringDelimiter();
/*      */             
/*  362 */             this.type = this.scanner.next();
/*  363 */             if (this.type == 1) {
/*  364 */               space7 = getCurrentValue();
/*  365 */               this.type = this.scanner.next();
/*      */             } 
/*      */           } 
/*      */           
/*  369 */           if (this.type == 24) {
/*  370 */             this.type = this.scanner.next();
/*  371 */             if (this.type == 1) {
/*  372 */               space8 = getCurrentValue();
/*  373 */               this.type = this.scanner.next();
/*      */             } 
/*  375 */             if (this.type != 15) {
/*  376 */               throw fatalError("token", new Object[] { "=" });
/*      */             }
/*  378 */             this.type = this.scanner.next();
/*  379 */             if (this.type == 1) {
/*  380 */               space9 = getCurrentValue();
/*  381 */               this.type = this.scanner.next();
/*      */             } 
/*  383 */             if (this.type != 25) {
/*  384 */               throw fatalError("string", null);
/*      */             }
/*      */             
/*  387 */             standalone = getCurrentValue();
/*  388 */             standaloneDelim = this.scanner.getStringDelimiter();
/*      */             
/*  390 */             this.type = this.scanner.next();
/*  391 */             if (this.type == 1) {
/*  392 */               space10 = getCurrentValue();
/*  393 */               this.type = this.scanner.next();
/*      */             } 
/*      */           } 
/*      */         } 
/*  397 */         if (this.type != 7) {
/*  398 */           throw fatalError("pi.end", null);
/*      */         }
/*      */         
/*  401 */         this.output.printXMLDecl(space1, space2, space3, version, versionDelim, space4, space5, space6, encoding, encodingDelim, space7, space8, space9, standalone, standaloneDelim, space10);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  409 */         this.type = this.scanner.next();
/*      */       } 
/*      */     } else {
/*  412 */       this.output.printString(this.xmlDeclaration);
/*  413 */       this.output.printNewline();
/*      */       
/*  415 */       if (this.type == 2) {
/*      */         
/*  417 */         if (this.scanner.next() != 1) {
/*  418 */           throw fatalError("space", null);
/*      */         }
/*      */         
/*  421 */         if (this.scanner.next() != 22) {
/*  422 */           throw fatalError("token", new Object[] { "version" });
/*      */         }
/*  424 */         this.type = this.scanner.next();
/*      */         
/*  426 */         if (this.type == 1) {
/*  427 */           this.type = this.scanner.next();
/*      */         }
/*  429 */         if (this.type != 15) {
/*  430 */           throw fatalError("token", new Object[] { "=" });
/*      */         }
/*  432 */         this.type = this.scanner.next();
/*      */         
/*  434 */         if (this.type == 1) {
/*  435 */           this.type = this.scanner.next();
/*      */         }
/*      */         
/*  438 */         if (this.type != 25) {
/*  439 */           throw fatalError("string", null);
/*      */         }
/*      */         
/*  442 */         this.type = this.scanner.next();
/*  443 */         if (this.type == 1) {
/*  444 */           this.type = this.scanner.next();
/*      */           
/*  446 */           if (this.type == 23) {
/*  447 */             this.type = this.scanner.next();
/*  448 */             if (this.type == 1) {
/*  449 */               this.type = this.scanner.next();
/*      */             }
/*  451 */             if (this.type != 15) {
/*  452 */               throw fatalError("token", new Object[] { "=" });
/*      */             }
/*  454 */             this.type = this.scanner.next();
/*  455 */             if (this.type == 1) {
/*  456 */               this.type = this.scanner.next();
/*      */             }
/*  458 */             if (this.type != 25) {
/*  459 */               throw fatalError("string", null);
/*      */             }
/*      */             
/*  462 */             this.type = this.scanner.next();
/*  463 */             if (this.type == 1) {
/*  464 */               this.type = this.scanner.next();
/*      */             }
/*      */           } 
/*      */           
/*  468 */           if (this.type == 24) {
/*  469 */             this.type = this.scanner.next();
/*  470 */             if (this.type == 1) {
/*  471 */               this.type = this.scanner.next();
/*      */             }
/*  473 */             if (this.type != 15) {
/*  474 */               throw fatalError("token", new Object[] { "=" });
/*      */             }
/*  476 */             this.type = this.scanner.next();
/*  477 */             if (this.type == 1) {
/*  478 */               this.type = this.scanner.next();
/*      */             }
/*  480 */             if (this.type != 25) {
/*  481 */               throw fatalError("string", null);
/*      */             }
/*      */             
/*  484 */             this.type = this.scanner.next();
/*  485 */             if (this.type == 1) {
/*  486 */               this.type = this.scanner.next();
/*      */             }
/*      */           } 
/*      */         } 
/*  490 */         if (this.type != 7) {
/*  491 */           throw fatalError("pi.end", null);
/*      */         }
/*      */         
/*  494 */         this.type = this.scanner.next();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void printPI() throws TranscoderException, XMLException, IOException {
/*  506 */     char[] target = getCurrentValue();
/*      */     
/*  508 */     this.type = this.scanner.next();
/*  509 */     char[] space = new char[0];
/*  510 */     if (this.type == 1) {
/*  511 */       space = getCurrentValue();
/*  512 */       this.type = this.scanner.next();
/*      */     } 
/*  514 */     if (this.type != 6) {
/*  515 */       throw fatalError("pi.data", null);
/*      */     }
/*  517 */     char[] data = getCurrentValue();
/*      */     
/*  519 */     this.type = this.scanner.next();
/*  520 */     if (this.type != 7) {
/*  521 */       throw fatalError("pi.end", null);
/*      */     }
/*      */     
/*  524 */     this.output.printPI(target, space, data);
/*      */     
/*  526 */     this.type = this.scanner.next();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void printDoctype() throws TranscoderException, XMLException, IOException {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield doctypeOption : I
/*      */     //   4: lookupswitch default -> 24, 1 -> 1205
/*      */     //   24: aload_0
/*      */     //   25: getfield type : I
/*      */     //   28: iconst_3
/*      */     //   29: if_icmpne -> 1054
/*      */     //   32: aload_0
/*      */     //   33: aload_0
/*      */     //   34: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   37: invokevirtual next : ()I
/*      */     //   40: putfield type : I
/*      */     //   43: aload_0
/*      */     //   44: getfield type : I
/*      */     //   47: iconst_1
/*      */     //   48: if_icmpeq -> 59
/*      */     //   51: aload_0
/*      */     //   52: ldc 'space'
/*      */     //   54: aconst_null
/*      */     //   55: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   58: athrow
/*      */     //   59: aload_0
/*      */     //   60: invokevirtual getCurrentValue : ()[C
/*      */     //   63: astore_1
/*      */     //   64: aload_0
/*      */     //   65: aload_0
/*      */     //   66: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   69: invokevirtual next : ()I
/*      */     //   72: putfield type : I
/*      */     //   75: aload_0
/*      */     //   76: getfield type : I
/*      */     //   79: bipush #14
/*      */     //   81: if_icmpeq -> 92
/*      */     //   84: aload_0
/*      */     //   85: ldc 'name'
/*      */     //   87: aconst_null
/*      */     //   88: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   91: athrow
/*      */     //   92: aload_0
/*      */     //   93: invokevirtual getCurrentValue : ()[C
/*      */     //   96: astore_2
/*      */     //   97: aconst_null
/*      */     //   98: astore_3
/*      */     //   99: aconst_null
/*      */     //   100: astore #4
/*      */     //   102: aconst_null
/*      */     //   103: astore #5
/*      */     //   105: aconst_null
/*      */     //   106: astore #6
/*      */     //   108: iconst_0
/*      */     //   109: istore #7
/*      */     //   111: aconst_null
/*      */     //   112: astore #8
/*      */     //   114: aconst_null
/*      */     //   115: astore #9
/*      */     //   117: iconst_0
/*      */     //   118: istore #10
/*      */     //   120: aconst_null
/*      */     //   121: astore #11
/*      */     //   123: aload_0
/*      */     //   124: aload_0
/*      */     //   125: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   128: invokevirtual next : ()I
/*      */     //   131: putfield type : I
/*      */     //   134: aload_0
/*      */     //   135: getfield type : I
/*      */     //   138: iconst_1
/*      */     //   139: if_icmpne -> 499
/*      */     //   142: aload_0
/*      */     //   143: invokevirtual getCurrentValue : ()[C
/*      */     //   146: astore_3
/*      */     //   147: aload_0
/*      */     //   148: aload_0
/*      */     //   149: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   152: invokevirtual next : ()I
/*      */     //   155: putfield type : I
/*      */     //   158: aload_0
/*      */     //   159: getfield type : I
/*      */     //   162: lookupswitch default -> 499, 26 -> 383, 27 -> 188
/*      */     //   188: ldc 'PUBLIC'
/*      */     //   190: astore #4
/*      */     //   192: aload_0
/*      */     //   193: aload_0
/*      */     //   194: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   197: invokevirtual next : ()I
/*      */     //   200: putfield type : I
/*      */     //   203: aload_0
/*      */     //   204: getfield type : I
/*      */     //   207: iconst_1
/*      */     //   208: if_icmpeq -> 219
/*      */     //   211: aload_0
/*      */     //   212: ldc 'space'
/*      */     //   214: aconst_null
/*      */     //   215: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   218: athrow
/*      */     //   219: aload_0
/*      */     //   220: invokevirtual getCurrentValue : ()[C
/*      */     //   223: astore #5
/*      */     //   225: aload_0
/*      */     //   226: aload_0
/*      */     //   227: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   230: invokevirtual next : ()I
/*      */     //   233: putfield type : I
/*      */     //   236: aload_0
/*      */     //   237: getfield type : I
/*      */     //   240: bipush #25
/*      */     //   242: if_icmpeq -> 253
/*      */     //   245: aload_0
/*      */     //   246: ldc 'string'
/*      */     //   248: aconst_null
/*      */     //   249: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   252: athrow
/*      */     //   253: aload_0
/*      */     //   254: invokevirtual getCurrentValue : ()[C
/*      */     //   257: astore #6
/*      */     //   259: aload_0
/*      */     //   260: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   263: invokevirtual getStringDelimiter : ()C
/*      */     //   266: istore #7
/*      */     //   268: aload_0
/*      */     //   269: aload_0
/*      */     //   270: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   273: invokevirtual next : ()I
/*      */     //   276: putfield type : I
/*      */     //   279: aload_0
/*      */     //   280: getfield type : I
/*      */     //   283: iconst_1
/*      */     //   284: if_icmpeq -> 295
/*      */     //   287: aload_0
/*      */     //   288: ldc 'space'
/*      */     //   290: aconst_null
/*      */     //   291: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   294: athrow
/*      */     //   295: aload_0
/*      */     //   296: invokevirtual getCurrentValue : ()[C
/*      */     //   299: astore #8
/*      */     //   301: aload_0
/*      */     //   302: aload_0
/*      */     //   303: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   306: invokevirtual next : ()I
/*      */     //   309: putfield type : I
/*      */     //   312: aload_0
/*      */     //   313: getfield type : I
/*      */     //   316: bipush #25
/*      */     //   318: if_icmpeq -> 329
/*      */     //   321: aload_0
/*      */     //   322: ldc 'string'
/*      */     //   324: aconst_null
/*      */     //   325: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   328: athrow
/*      */     //   329: aload_0
/*      */     //   330: invokevirtual getCurrentValue : ()[C
/*      */     //   333: astore #9
/*      */     //   335: aload_0
/*      */     //   336: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   339: invokevirtual getStringDelimiter : ()C
/*      */     //   342: istore #10
/*      */     //   344: aload_0
/*      */     //   345: aload_0
/*      */     //   346: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   349: invokevirtual next : ()I
/*      */     //   352: putfield type : I
/*      */     //   355: aload_0
/*      */     //   356: getfield type : I
/*      */     //   359: iconst_1
/*      */     //   360: if_icmpne -> 499
/*      */     //   363: aload_0
/*      */     //   364: invokevirtual getCurrentValue : ()[C
/*      */     //   367: astore #11
/*      */     //   369: aload_0
/*      */     //   370: aload_0
/*      */     //   371: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   374: invokevirtual next : ()I
/*      */     //   377: putfield type : I
/*      */     //   380: goto -> 499
/*      */     //   383: ldc 'SYSTEM'
/*      */     //   385: astore #4
/*      */     //   387: aload_0
/*      */     //   388: aload_0
/*      */     //   389: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   392: invokevirtual next : ()I
/*      */     //   395: putfield type : I
/*      */     //   398: aload_0
/*      */     //   399: getfield type : I
/*      */     //   402: iconst_1
/*      */     //   403: if_icmpeq -> 414
/*      */     //   406: aload_0
/*      */     //   407: ldc 'space'
/*      */     //   409: aconst_null
/*      */     //   410: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   413: athrow
/*      */     //   414: aload_0
/*      */     //   415: invokevirtual getCurrentValue : ()[C
/*      */     //   418: astore #5
/*      */     //   420: aload_0
/*      */     //   421: aload_0
/*      */     //   422: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   425: invokevirtual next : ()I
/*      */     //   428: putfield type : I
/*      */     //   431: aload_0
/*      */     //   432: getfield type : I
/*      */     //   435: bipush #25
/*      */     //   437: if_icmpeq -> 448
/*      */     //   440: aload_0
/*      */     //   441: ldc 'string'
/*      */     //   443: aconst_null
/*      */     //   444: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   447: athrow
/*      */     //   448: aload_0
/*      */     //   449: invokevirtual getCurrentValue : ()[C
/*      */     //   452: astore #6
/*      */     //   454: aload_0
/*      */     //   455: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   458: invokevirtual getStringDelimiter : ()C
/*      */     //   461: istore #7
/*      */     //   463: aload_0
/*      */     //   464: aload_0
/*      */     //   465: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   468: invokevirtual next : ()I
/*      */     //   471: putfield type : I
/*      */     //   474: aload_0
/*      */     //   475: getfield type : I
/*      */     //   478: iconst_1
/*      */     //   479: if_icmpne -> 499
/*      */     //   482: aload_0
/*      */     //   483: invokevirtual getCurrentValue : ()[C
/*      */     //   486: astore #8
/*      */     //   488: aload_0
/*      */     //   489: aload_0
/*      */     //   490: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   493: invokevirtual next : ()I
/*      */     //   496: putfield type : I
/*      */     //   499: aload_0
/*      */     //   500: getfield doctypeOption : I
/*      */     //   503: ifne -> 580
/*      */     //   506: aload_0
/*      */     //   507: getfield publicId : Ljava/lang/String;
/*      */     //   510: ifnull -> 553
/*      */     //   513: ldc 'PUBLIC'
/*      */     //   515: astore #4
/*      */     //   517: aload_0
/*      */     //   518: getfield publicId : Ljava/lang/String;
/*      */     //   521: invokevirtual toCharArray : ()[C
/*      */     //   524: astore #6
/*      */     //   526: bipush #34
/*      */     //   528: istore #7
/*      */     //   530: aload_0
/*      */     //   531: getfield systemId : Ljava/lang/String;
/*      */     //   534: ifnull -> 580
/*      */     //   537: aload_0
/*      */     //   538: getfield systemId : Ljava/lang/String;
/*      */     //   541: invokevirtual toCharArray : ()[C
/*      */     //   544: astore #9
/*      */     //   546: bipush #34
/*      */     //   548: istore #10
/*      */     //   550: goto -> 580
/*      */     //   553: aload_0
/*      */     //   554: getfield systemId : Ljava/lang/String;
/*      */     //   557: ifnull -> 580
/*      */     //   560: ldc 'SYSTEM'
/*      */     //   562: astore #4
/*      */     //   564: aload_0
/*      */     //   565: getfield systemId : Ljava/lang/String;
/*      */     //   568: invokevirtual toCharArray : ()[C
/*      */     //   571: astore #6
/*      */     //   573: bipush #34
/*      */     //   575: istore #7
/*      */     //   577: aconst_null
/*      */     //   578: astore #9
/*      */     //   580: aload_0
/*      */     //   581: getfield output : Lorg/apache/batik/transcoder/svg2svg/OutputManager;
/*      */     //   584: aload_1
/*      */     //   585: aload_2
/*      */     //   586: aload_3
/*      */     //   587: aload #4
/*      */     //   589: aload #5
/*      */     //   591: aload #6
/*      */     //   593: iload #7
/*      */     //   595: aload #8
/*      */     //   597: aload #9
/*      */     //   599: iload #10
/*      */     //   601: aload #11
/*      */     //   603: invokevirtual printDoctypeStart : ([C[C[CLjava/lang/String;[C[CC[C[CC[C)V
/*      */     //   606: aload_0
/*      */     //   607: getfield type : I
/*      */     //   610: bipush #28
/*      */     //   612: if_icmpne -> 986
/*      */     //   615: aload_0
/*      */     //   616: getfield output : Lorg/apache/batik/transcoder/svg2svg/OutputManager;
/*      */     //   619: bipush #91
/*      */     //   621: invokevirtual printCharacter : (C)V
/*      */     //   624: aload_0
/*      */     //   625: aload_0
/*      */     //   626: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   629: invokevirtual next : ()I
/*      */     //   632: putfield type : I
/*      */     //   635: aload_0
/*      */     //   636: getfield type : I
/*      */     //   639: tableswitch default -> 978, 1 -> 788, 2 -> 978, 3 -> 978, 4 -> 821, 5 -> 853, 6 -> 978, 7 -> 978, 8 -> 978, 9 -> 978, 10 -> 978, 11 -> 978, 12 -> 978, 13 -> 978, 14 -> 978, 15 -> 978, 16 -> 978, 17 -> 978, 18 -> 978, 19 -> 978, 20 -> 978, 21 -> 978, 22 -> 978, 23 -> 978, 24 -> 978, 25 -> 978, 26 -> 978, 27 -> 978, 28 -> 978, 29 -> 948, 30 -> 892, 31 -> 906, 32 -> 934, 33 -> 920, 34 -> 860
/*      */     //   788: aload_0
/*      */     //   789: getfield output : Lorg/apache/batik/transcoder/svg2svg/OutputManager;
/*      */     //   792: aload_0
/*      */     //   793: invokevirtual getCurrentValue : ()[C
/*      */     //   796: iconst_1
/*      */     //   797: invokevirtual printSpaces : ([CZ)V
/*      */     //   800: aload_0
/*      */     //   801: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   804: invokevirtual clearBuffer : ()V
/*      */     //   807: aload_0
/*      */     //   808: aload_0
/*      */     //   809: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   812: invokevirtual next : ()I
/*      */     //   815: putfield type : I
/*      */     //   818: goto -> 635
/*      */     //   821: aload_0
/*      */     //   822: getfield output : Lorg/apache/batik/transcoder/svg2svg/OutputManager;
/*      */     //   825: aload_0
/*      */     //   826: invokevirtual getCurrentValue : ()[C
/*      */     //   829: invokevirtual printComment : ([C)V
/*      */     //   832: aload_0
/*      */     //   833: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   836: invokevirtual clearBuffer : ()V
/*      */     //   839: aload_0
/*      */     //   840: aload_0
/*      */     //   841: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   844: invokevirtual next : ()I
/*      */     //   847: putfield type : I
/*      */     //   850: goto -> 635
/*      */     //   853: aload_0
/*      */     //   854: invokevirtual printPI : ()V
/*      */     //   857: goto -> 635
/*      */     //   860: aload_0
/*      */     //   861: getfield output : Lorg/apache/batik/transcoder/svg2svg/OutputManager;
/*      */     //   864: aload_0
/*      */     //   865: invokevirtual getCurrentValue : ()[C
/*      */     //   868: invokevirtual printParameterEntityReference : ([C)V
/*      */     //   871: aload_0
/*      */     //   872: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   875: invokevirtual clearBuffer : ()V
/*      */     //   878: aload_0
/*      */     //   879: aload_0
/*      */     //   880: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   883: invokevirtual next : ()I
/*      */     //   886: putfield type : I
/*      */     //   889: goto -> 635
/*      */     //   892: aload_0
/*      */     //   893: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   896: invokevirtual clearBuffer : ()V
/*      */     //   899: aload_0
/*      */     //   900: invokevirtual printElementDeclaration : ()V
/*      */     //   903: goto -> 635
/*      */     //   906: aload_0
/*      */     //   907: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   910: invokevirtual clearBuffer : ()V
/*      */     //   913: aload_0
/*      */     //   914: invokevirtual printAttlist : ()V
/*      */     //   917: goto -> 635
/*      */     //   920: aload_0
/*      */     //   921: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   924: invokevirtual clearBuffer : ()V
/*      */     //   927: aload_0
/*      */     //   928: invokevirtual printNotation : ()V
/*      */     //   931: goto -> 635
/*      */     //   934: aload_0
/*      */     //   935: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   938: invokevirtual clearBuffer : ()V
/*      */     //   941: aload_0
/*      */     //   942: invokevirtual printEntityDeclaration : ()V
/*      */     //   945: goto -> 635
/*      */     //   948: aload_0
/*      */     //   949: getfield output : Lorg/apache/batik/transcoder/svg2svg/OutputManager;
/*      */     //   952: bipush #93
/*      */     //   954: invokevirtual printCharacter : (C)V
/*      */     //   957: aload_0
/*      */     //   958: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   961: invokevirtual clearBuffer : ()V
/*      */     //   964: aload_0
/*      */     //   965: aload_0
/*      */     //   966: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   969: invokevirtual next : ()I
/*      */     //   972: putfield type : I
/*      */     //   975: goto -> 986
/*      */     //   978: aload_0
/*      */     //   979: ldc 'xml'
/*      */     //   981: aconst_null
/*      */     //   982: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   985: athrow
/*      */     //   986: aconst_null
/*      */     //   987: astore #12
/*      */     //   989: aload_0
/*      */     //   990: getfield type : I
/*      */     //   993: iconst_1
/*      */     //   994: if_icmpne -> 1014
/*      */     //   997: aload_0
/*      */     //   998: invokevirtual getCurrentValue : ()[C
/*      */     //   1001: astore #12
/*      */     //   1003: aload_0
/*      */     //   1004: aload_0
/*      */     //   1005: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1008: invokevirtual next : ()I
/*      */     //   1011: putfield type : I
/*      */     //   1014: aload_0
/*      */     //   1015: getfield type : I
/*      */     //   1018: bipush #20
/*      */     //   1020: if_icmpeq -> 1031
/*      */     //   1023: aload_0
/*      */     //   1024: ldc 'end'
/*      */     //   1026: aconst_null
/*      */     //   1027: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   1030: athrow
/*      */     //   1031: aload_0
/*      */     //   1032: aload_0
/*      */     //   1033: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1036: invokevirtual next : ()I
/*      */     //   1039: putfield type : I
/*      */     //   1042: aload_0
/*      */     //   1043: getfield output : Lorg/apache/batik/transcoder/svg2svg/OutputManager;
/*      */     //   1046: aload #12
/*      */     //   1048: invokevirtual printDoctypeEnd : ([C)V
/*      */     //   1051: goto -> 1632
/*      */     //   1054: aload_0
/*      */     //   1055: getfield doctypeOption : I
/*      */     //   1058: ifne -> 1632
/*      */     //   1061: ldc 'PUBLIC'
/*      */     //   1063: astore_1
/*      */     //   1064: ldc '-//W3C//DTD SVG 1.0//EN'
/*      */     //   1066: invokevirtual toCharArray : ()[C
/*      */     //   1069: astore_2
/*      */     //   1070: ldc 'http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd'
/*      */     //   1072: invokevirtual toCharArray : ()[C
/*      */     //   1075: astore_3
/*      */     //   1076: aload_0
/*      */     //   1077: getfield publicId : Ljava/lang/String;
/*      */     //   1080: ifnull -> 1109
/*      */     //   1083: aload_0
/*      */     //   1084: getfield publicId : Ljava/lang/String;
/*      */     //   1087: invokevirtual toCharArray : ()[C
/*      */     //   1090: astore_2
/*      */     //   1091: aload_0
/*      */     //   1092: getfield systemId : Ljava/lang/String;
/*      */     //   1095: ifnull -> 1129
/*      */     //   1098: aload_0
/*      */     //   1099: getfield systemId : Ljava/lang/String;
/*      */     //   1102: invokevirtual toCharArray : ()[C
/*      */     //   1105: astore_3
/*      */     //   1106: goto -> 1129
/*      */     //   1109: aload_0
/*      */     //   1110: getfield systemId : Ljava/lang/String;
/*      */     //   1113: ifnull -> 1129
/*      */     //   1116: ldc 'SYSTEM'
/*      */     //   1118: astore_1
/*      */     //   1119: aload_0
/*      */     //   1120: getfield systemId : Ljava/lang/String;
/*      */     //   1123: invokevirtual toCharArray : ()[C
/*      */     //   1126: astore_2
/*      */     //   1127: aconst_null
/*      */     //   1128: astore_3
/*      */     //   1129: aload_0
/*      */     //   1130: getfield output : Lorg/apache/batik/transcoder/svg2svg/OutputManager;
/*      */     //   1133: iconst_1
/*      */     //   1134: newarray char
/*      */     //   1136: dup
/*      */     //   1137: iconst_0
/*      */     //   1138: bipush #32
/*      */     //   1140: castore
/*      */     //   1141: iconst_3
/*      */     //   1142: newarray char
/*      */     //   1144: dup
/*      */     //   1145: iconst_0
/*      */     //   1146: bipush #115
/*      */     //   1148: castore
/*      */     //   1149: dup
/*      */     //   1150: iconst_1
/*      */     //   1151: bipush #118
/*      */     //   1153: castore
/*      */     //   1154: dup
/*      */     //   1155: iconst_2
/*      */     //   1156: bipush #103
/*      */     //   1158: castore
/*      */     //   1159: iconst_1
/*      */     //   1160: newarray char
/*      */     //   1162: dup
/*      */     //   1163: iconst_0
/*      */     //   1164: bipush #32
/*      */     //   1166: castore
/*      */     //   1167: aload_1
/*      */     //   1168: iconst_1
/*      */     //   1169: newarray char
/*      */     //   1171: dup
/*      */     //   1172: iconst_0
/*      */     //   1173: bipush #32
/*      */     //   1175: castore
/*      */     //   1176: aload_2
/*      */     //   1177: bipush #34
/*      */     //   1179: iconst_1
/*      */     //   1180: newarray char
/*      */     //   1182: dup
/*      */     //   1183: iconst_0
/*      */     //   1184: bipush #32
/*      */     //   1186: castore
/*      */     //   1187: aload_3
/*      */     //   1188: bipush #34
/*      */     //   1190: aconst_null
/*      */     //   1191: invokevirtual printDoctypeStart : ([C[C[CLjava/lang/String;[C[CC[C[CC[C)V
/*      */     //   1194: aload_0
/*      */     //   1195: getfield output : Lorg/apache/batik/transcoder/svg2svg/OutputManager;
/*      */     //   1198: aconst_null
/*      */     //   1199: invokevirtual printDoctypeEnd : ([C)V
/*      */     //   1202: goto -> 1632
/*      */     //   1205: aload_0
/*      */     //   1206: getfield type : I
/*      */     //   1209: iconst_3
/*      */     //   1210: if_icmpne -> 1621
/*      */     //   1213: aload_0
/*      */     //   1214: aload_0
/*      */     //   1215: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1218: invokevirtual next : ()I
/*      */     //   1221: putfield type : I
/*      */     //   1224: aload_0
/*      */     //   1225: getfield type : I
/*      */     //   1228: iconst_1
/*      */     //   1229: if_icmpeq -> 1240
/*      */     //   1232: aload_0
/*      */     //   1233: ldc 'space'
/*      */     //   1235: aconst_null
/*      */     //   1236: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   1239: athrow
/*      */     //   1240: aload_0
/*      */     //   1241: aload_0
/*      */     //   1242: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1245: invokevirtual next : ()I
/*      */     //   1248: putfield type : I
/*      */     //   1251: aload_0
/*      */     //   1252: getfield type : I
/*      */     //   1255: bipush #14
/*      */     //   1257: if_icmpeq -> 1268
/*      */     //   1260: aload_0
/*      */     //   1261: ldc 'name'
/*      */     //   1263: aconst_null
/*      */     //   1264: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   1267: athrow
/*      */     //   1268: aload_0
/*      */     //   1269: aload_0
/*      */     //   1270: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1273: invokevirtual next : ()I
/*      */     //   1276: putfield type : I
/*      */     //   1279: aload_0
/*      */     //   1280: getfield type : I
/*      */     //   1283: iconst_1
/*      */     //   1284: if_icmpne -> 1556
/*      */     //   1287: aload_0
/*      */     //   1288: aload_0
/*      */     //   1289: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1292: invokevirtual next : ()I
/*      */     //   1295: putfield type : I
/*      */     //   1298: aload_0
/*      */     //   1299: getfield type : I
/*      */     //   1302: lookupswitch default -> 1556, 26 -> 1471, 27 -> 1328
/*      */     //   1328: aload_0
/*      */     //   1329: aload_0
/*      */     //   1330: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1333: invokevirtual next : ()I
/*      */     //   1336: putfield type : I
/*      */     //   1339: aload_0
/*      */     //   1340: getfield type : I
/*      */     //   1343: iconst_1
/*      */     //   1344: if_icmpeq -> 1355
/*      */     //   1347: aload_0
/*      */     //   1348: ldc 'space'
/*      */     //   1350: aconst_null
/*      */     //   1351: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   1354: athrow
/*      */     //   1355: aload_0
/*      */     //   1356: aload_0
/*      */     //   1357: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1360: invokevirtual next : ()I
/*      */     //   1363: putfield type : I
/*      */     //   1366: aload_0
/*      */     //   1367: getfield type : I
/*      */     //   1370: bipush #25
/*      */     //   1372: if_icmpeq -> 1383
/*      */     //   1375: aload_0
/*      */     //   1376: ldc 'string'
/*      */     //   1378: aconst_null
/*      */     //   1379: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   1382: athrow
/*      */     //   1383: aload_0
/*      */     //   1384: aload_0
/*      */     //   1385: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1388: invokevirtual next : ()I
/*      */     //   1391: putfield type : I
/*      */     //   1394: aload_0
/*      */     //   1395: getfield type : I
/*      */     //   1398: iconst_1
/*      */     //   1399: if_icmpeq -> 1410
/*      */     //   1402: aload_0
/*      */     //   1403: ldc 'space'
/*      */     //   1405: aconst_null
/*      */     //   1406: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   1409: athrow
/*      */     //   1410: aload_0
/*      */     //   1411: aload_0
/*      */     //   1412: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1415: invokevirtual next : ()I
/*      */     //   1418: putfield type : I
/*      */     //   1421: aload_0
/*      */     //   1422: getfield type : I
/*      */     //   1425: bipush #25
/*      */     //   1427: if_icmpeq -> 1438
/*      */     //   1430: aload_0
/*      */     //   1431: ldc 'string'
/*      */     //   1433: aconst_null
/*      */     //   1434: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   1437: athrow
/*      */     //   1438: aload_0
/*      */     //   1439: aload_0
/*      */     //   1440: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1443: invokevirtual next : ()I
/*      */     //   1446: putfield type : I
/*      */     //   1449: aload_0
/*      */     //   1450: getfield type : I
/*      */     //   1453: iconst_1
/*      */     //   1454: if_icmpne -> 1556
/*      */     //   1457: aload_0
/*      */     //   1458: aload_0
/*      */     //   1459: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1462: invokevirtual next : ()I
/*      */     //   1465: putfield type : I
/*      */     //   1468: goto -> 1556
/*      */     //   1471: aload_0
/*      */     //   1472: aload_0
/*      */     //   1473: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1476: invokevirtual next : ()I
/*      */     //   1479: putfield type : I
/*      */     //   1482: aload_0
/*      */     //   1483: getfield type : I
/*      */     //   1486: iconst_1
/*      */     //   1487: if_icmpeq -> 1498
/*      */     //   1490: aload_0
/*      */     //   1491: ldc 'space'
/*      */     //   1493: aconst_null
/*      */     //   1494: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   1497: athrow
/*      */     //   1498: aload_0
/*      */     //   1499: aload_0
/*      */     //   1500: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1503: invokevirtual next : ()I
/*      */     //   1506: putfield type : I
/*      */     //   1509: aload_0
/*      */     //   1510: getfield type : I
/*      */     //   1513: bipush #25
/*      */     //   1515: if_icmpeq -> 1526
/*      */     //   1518: aload_0
/*      */     //   1519: ldc 'string'
/*      */     //   1521: aconst_null
/*      */     //   1522: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   1525: athrow
/*      */     //   1526: aload_0
/*      */     //   1527: aload_0
/*      */     //   1528: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1531: invokevirtual next : ()I
/*      */     //   1534: putfield type : I
/*      */     //   1537: aload_0
/*      */     //   1538: getfield type : I
/*      */     //   1541: iconst_1
/*      */     //   1542: if_icmpne -> 1556
/*      */     //   1545: aload_0
/*      */     //   1546: aload_0
/*      */     //   1547: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1550: invokevirtual next : ()I
/*      */     //   1553: putfield type : I
/*      */     //   1556: aload_0
/*      */     //   1557: getfield type : I
/*      */     //   1560: bipush #28
/*      */     //   1562: if_icmpne -> 1585
/*      */     //   1565: aload_0
/*      */     //   1566: aload_0
/*      */     //   1567: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1570: invokevirtual next : ()I
/*      */     //   1573: putfield type : I
/*      */     //   1576: aload_0
/*      */     //   1577: getfield type : I
/*      */     //   1580: bipush #29
/*      */     //   1582: if_icmpne -> 1565
/*      */     //   1585: aload_0
/*      */     //   1586: getfield type : I
/*      */     //   1589: iconst_1
/*      */     //   1590: if_icmpne -> 1604
/*      */     //   1593: aload_0
/*      */     //   1594: aload_0
/*      */     //   1595: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1598: invokevirtual next : ()I
/*      */     //   1601: putfield type : I
/*      */     //   1604: aload_0
/*      */     //   1605: getfield type : I
/*      */     //   1608: bipush #20
/*      */     //   1610: if_icmpeq -> 1621
/*      */     //   1613: aload_0
/*      */     //   1614: ldc 'end'
/*      */     //   1616: aconst_null
/*      */     //   1617: invokevirtual fatalError : (Ljava/lang/String;[Ljava/lang/Object;)Lorg/apache/batik/transcoder/TranscoderException;
/*      */     //   1620: athrow
/*      */     //   1621: aload_0
/*      */     //   1622: aload_0
/*      */     //   1623: getfield scanner : Lorg/apache/batik/xml/XMLScanner;
/*      */     //   1626: invokevirtual next : ()I
/*      */     //   1629: putfield type : I
/*      */     //   1632: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #536	-> 0
/*      */     //   #538	-> 24
/*      */     //   #539	-> 32
/*      */     //   #541	-> 43
/*      */     //   #542	-> 51
/*      */     //   #544	-> 59
/*      */     //   #545	-> 64
/*      */     //   #547	-> 75
/*      */     //   #548	-> 84
/*      */     //   #551	-> 92
/*      */     //   #552	-> 97
/*      */     //   #553	-> 99
/*      */     //   #554	-> 102
/*      */     //   #555	-> 105
/*      */     //   #556	-> 108
/*      */     //   #557	-> 111
/*      */     //   #558	-> 114
/*      */     //   #559	-> 117
/*      */     //   #560	-> 120
/*      */     //   #562	-> 123
/*      */     //   #563	-> 134
/*      */     //   #564	-> 142
/*      */     //   #565	-> 147
/*      */     //   #567	-> 158
/*      */     //   #569	-> 188
/*      */     //   #571	-> 192
/*      */     //   #572	-> 203
/*      */     //   #573	-> 211
/*      */     //   #575	-> 219
/*      */     //   #576	-> 225
/*      */     //   #578	-> 236
/*      */     //   #579	-> 245
/*      */     //   #582	-> 253
/*      */     //   #583	-> 259
/*      */     //   #585	-> 268
/*      */     //   #586	-> 279
/*      */     //   #587	-> 287
/*      */     //   #589	-> 295
/*      */     //   #590	-> 301
/*      */     //   #592	-> 312
/*      */     //   #593	-> 321
/*      */     //   #596	-> 329
/*      */     //   #597	-> 335
/*      */     //   #599	-> 344
/*      */     //   #600	-> 355
/*      */     //   #601	-> 363
/*      */     //   #602	-> 369
/*      */     //   #606	-> 383
/*      */     //   #608	-> 387
/*      */     //   #609	-> 398
/*      */     //   #610	-> 406
/*      */     //   #612	-> 414
/*      */     //   #613	-> 420
/*      */     //   #615	-> 431
/*      */     //   #616	-> 440
/*      */     //   #619	-> 448
/*      */     //   #620	-> 454
/*      */     //   #622	-> 463
/*      */     //   #623	-> 474
/*      */     //   #624	-> 482
/*      */     //   #625	-> 488
/*      */     //   #630	-> 499
/*      */     //   #631	-> 506
/*      */     //   #632	-> 513
/*      */     //   #633	-> 517
/*      */     //   #634	-> 526
/*      */     //   #635	-> 530
/*      */     //   #636	-> 537
/*      */     //   #637	-> 546
/*      */     //   #639	-> 553
/*      */     //   #640	-> 560
/*      */     //   #641	-> 564
/*      */     //   #642	-> 573
/*      */     //   #643	-> 577
/*      */     //   #646	-> 580
/*      */     //   #653	-> 606
/*      */     //   #654	-> 615
/*      */     //   #655	-> 624
/*      */     //   #658	-> 635
/*      */     //   #660	-> 788
/*      */     //   #661	-> 800
/*      */     //   #662	-> 807
/*      */     //   #663	-> 818
/*      */     //   #665	-> 821
/*      */     //   #666	-> 832
/*      */     //   #667	-> 839
/*      */     //   #668	-> 850
/*      */     //   #670	-> 853
/*      */     //   #671	-> 857
/*      */     //   #673	-> 860
/*      */     //   #674	-> 871
/*      */     //   #675	-> 878
/*      */     //   #676	-> 889
/*      */     //   #678	-> 892
/*      */     //   #679	-> 899
/*      */     //   #680	-> 903
/*      */     //   #682	-> 906
/*      */     //   #683	-> 913
/*      */     //   #684	-> 917
/*      */     //   #686	-> 920
/*      */     //   #687	-> 927
/*      */     //   #688	-> 931
/*      */     //   #690	-> 934
/*      */     //   #691	-> 941
/*      */     //   #692	-> 945
/*      */     //   #694	-> 948
/*      */     //   #695	-> 957
/*      */     //   #696	-> 964
/*      */     //   #697	-> 975
/*      */     //   #699	-> 978
/*      */     //   #703	-> 986
/*      */     //   #704	-> 989
/*      */     //   #705	-> 997
/*      */     //   #706	-> 1003
/*      */     //   #709	-> 1014
/*      */     //   #710	-> 1023
/*      */     //   #712	-> 1031
/*      */     //   #713	-> 1042
/*      */     //   #714	-> 1051
/*      */     //   #715	-> 1054
/*      */     //   #716	-> 1061
/*      */     //   #717	-> 1064
/*      */     //   #718	-> 1070
/*      */     //   #719	-> 1076
/*      */     //   #720	-> 1083
/*      */     //   #721	-> 1091
/*      */     //   #722	-> 1098
/*      */     //   #724	-> 1109
/*      */     //   #725	-> 1116
/*      */     //   #726	-> 1119
/*      */     //   #727	-> 1127
/*      */     //   #729	-> 1129
/*      */     //   #738	-> 1194
/*      */     //   #739	-> 1202
/*      */     //   #745	-> 1205
/*      */     //   #746	-> 1213
/*      */     //   #748	-> 1224
/*      */     //   #749	-> 1232
/*      */     //   #751	-> 1240
/*      */     //   #753	-> 1251
/*      */     //   #754	-> 1260
/*      */     //   #757	-> 1268
/*      */     //   #758	-> 1279
/*      */     //   #759	-> 1287
/*      */     //   #761	-> 1298
/*      */     //   #764	-> 1328
/*      */     //   #765	-> 1339
/*      */     //   #766	-> 1347
/*      */     //   #768	-> 1355
/*      */     //   #770	-> 1366
/*      */     //   #771	-> 1375
/*      */     //   #774	-> 1383
/*      */     //   #775	-> 1394
/*      */     //   #776	-> 1402
/*      */     //   #778	-> 1410
/*      */     //   #780	-> 1421
/*      */     //   #781	-> 1430
/*      */     //   #784	-> 1438
/*      */     //   #785	-> 1449
/*      */     //   #786	-> 1457
/*      */     //   #791	-> 1471
/*      */     //   #792	-> 1482
/*      */     //   #793	-> 1490
/*      */     //   #795	-> 1498
/*      */     //   #797	-> 1509
/*      */     //   #798	-> 1518
/*      */     //   #801	-> 1526
/*      */     //   #802	-> 1537
/*      */     //   #803	-> 1545
/*      */     //   #808	-> 1556
/*      */     //   #810	-> 1565
/*      */     //   #811	-> 1576
/*      */     //   #813	-> 1585
/*      */     //   #814	-> 1593
/*      */     //   #817	-> 1604
/*      */     //   #818	-> 1613
/*      */     //   #821	-> 1621
/*      */     //   #823	-> 1632
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	1633	0	this	Lorg/apache/batik/transcoder/svg2svg/PrettyPrinter;
/*      */     //   64	987	1	space1	[C
/*      */     //   97	954	2	root	[C
/*      */     //   99	952	3	space2	[C
/*      */     //   102	949	4	externalId	Ljava/lang/String;
/*      */     //   105	946	5	space3	[C
/*      */     //   108	943	6	string1	[C
/*      */     //   111	940	7	string1Delim	C
/*      */     //   114	937	8	space4	[C
/*      */     //   117	934	9	string2	[C
/*      */     //   120	931	10	string2Delim	C
/*      */     //   123	928	11	space5	[C
/*      */     //   989	62	12	endSpace	[C
/*      */     //   1064	138	1	externalId	Ljava/lang/String;
/*      */     //   1070	132	2	string1	[C
/*      */     //   1076	126	3	string2	[C
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String printElement() throws TranscoderException, XMLException, IOException {
/*  832 */     char[] name = getCurrentValue();
/*  833 */     String nameStr = new String(name);
/*  834 */     List<OutputManager.AttributeInfo> attributes = new LinkedList();
/*  835 */     char[] space = null;
/*      */     
/*  837 */     this.type = this.scanner.next();
/*  838 */     while (this.type == 1) {
/*  839 */       space = getCurrentValue();
/*      */       
/*  841 */       this.type = this.scanner.next();
/*  842 */       if (this.type == 14) {
/*  843 */         char[] attName = getCurrentValue();
/*  844 */         char[] space1 = null;
/*      */         
/*  846 */         this.type = this.scanner.next();
/*  847 */         if (this.type == 1) {
/*  848 */           space1 = getCurrentValue();
/*  849 */           this.type = this.scanner.next();
/*      */         } 
/*  851 */         if (this.type != 15) {
/*  852 */           throw fatalError("token", new Object[] { "=" });
/*      */         }
/*  854 */         this.type = this.scanner.next();
/*      */         
/*  856 */         char[] space2 = null;
/*  857 */         if (this.type == 1) {
/*  858 */           space2 = getCurrentValue();
/*  859 */           this.type = this.scanner.next();
/*      */         } 
/*  861 */         if (this.type != 25 && this.type != 16)
/*      */         {
/*  863 */           throw fatalError("string", null);
/*      */         }
/*      */         
/*  866 */         char valueDelim = this.scanner.getStringDelimiter();
/*  867 */         boolean hasEntityRef = false;
/*      */         
/*  869 */         StringBuffer sb = new StringBuffer();
/*  870 */         sb.append(getCurrentValue());
/*      */         while (true) {
/*  872 */           this.scanner.clearBuffer();
/*  873 */           this.type = this.scanner.next();
/*  874 */           switch (this.type) {
/*      */             case 16:
/*      */             case 17:
/*      */             case 18:
/*      */             case 25:
/*  879 */               sb.append(getCurrentValue());
/*      */               continue;
/*      */             case 12:
/*  882 */               hasEntityRef = true;
/*  883 */               sb.append("&#");
/*  884 */               sb.append(getCurrentValue());
/*  885 */               sb.append(";");
/*      */               continue;
/*      */             case 13:
/*  888 */               hasEntityRef = true;
/*  889 */               sb.append("&");
/*  890 */               sb.append(getCurrentValue());
/*  891 */               sb.append(";");
/*      */               continue;
/*      */           } 
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*  898 */         attributes.add(new OutputManager.AttributeInfo(space, attName, space1, space2, new String(sb), valueDelim, hasEntityRef));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  904 */         space = null;
/*      */       } 
/*      */     } 
/*  907 */     this.output.printElementStart(name, attributes, space);
/*      */     
/*  909 */     switch (this.type)
/*      */     { default:
/*  911 */         throw fatalError("xml", null);
/*      */       case 19:
/*  913 */         this.output.printElementEnd(null, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  938 */         this.type = this.scanner.next();
/*  939 */         return nameStr;case 20: break; }  this.output.printCharacter('>'); this.type = this.scanner.next(); printContent(allowSpaceAtStart(nameStr)); if (this.type != 10) throw fatalError("end.tag", null);  name = getCurrentValue(); this.type = this.scanner.next(); space = null; if (this.type == 1) { space = getCurrentValue(); this.type = this.scanner.next(); }  this.output.printElementEnd(name, space); if (this.type != 20) throw fatalError("end", null);  this.type = this.scanner.next(); return nameStr;
/*      */   }
/*      */   
/*      */   boolean allowSpaceAtStart(String tagName) {
/*  943 */     return true;
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
/*      */   protected void printContent(boolean spaceAtStart) throws TranscoderException, XMLException, IOException {
/*  960 */     boolean preceedingSpace = false; while (true) {
/*      */       String name;
/*  962 */       switch (this.type) {
/*      */         case 4:
/*  964 */           this.output.printComment(getCurrentValue());
/*  965 */           this.scanner.clearBuffer();
/*  966 */           this.type = this.scanner.next();
/*  967 */           preceedingSpace = false;
/*      */           continue;
/*      */         case 5:
/*  970 */           printPI();
/*  971 */           preceedingSpace = false;
/*      */           continue;
/*      */         case 8:
/*  974 */           preceedingSpace = this.output.printCharacterData(getCurrentValue(), spaceAtStart, preceedingSpace);
/*      */           
/*  976 */           this.scanner.clearBuffer();
/*  977 */           this.type = this.scanner.next();
/*  978 */           spaceAtStart = false;
/*      */           continue;
/*      */         case 11:
/*  981 */           this.type = this.scanner.next();
/*  982 */           if (this.type != 8) {
/*  983 */             throw fatalError("character.data", null);
/*      */           }
/*  985 */           this.output.printCDATASection(getCurrentValue());
/*  986 */           if (this.scanner.next() != 21) {
/*  987 */             throw fatalError("section.end", null);
/*      */           }
/*  989 */           this.scanner.clearBuffer();
/*  990 */           this.type = this.scanner.next();
/*  991 */           preceedingSpace = false;
/*  992 */           spaceAtStart = false;
/*      */           continue;
/*      */         case 9:
/*  995 */           name = printElement();
/*  996 */           spaceAtStart = allowSpaceAtStart(name);
/*      */           continue;
/*      */         case 12:
/*  999 */           this.output.printCharacterEntityReference(getCurrentValue(), spaceAtStart, preceedingSpace);
/*      */ 
/*      */           
/* 1002 */           this.scanner.clearBuffer();
/* 1003 */           this.type = this.scanner.next();
/* 1004 */           spaceAtStart = false;
/* 1005 */           preceedingSpace = false;
/*      */           continue;
/*      */         case 13:
/* 1008 */           this.output.printEntityReference(getCurrentValue(), spaceAtStart);
/* 1009 */           this.scanner.clearBuffer();
/* 1010 */           this.type = this.scanner.next();
/* 1011 */           spaceAtStart = false;
/* 1012 */           preceedingSpace = false;
/*      */           continue;
/*      */       } 
/*      */       break;
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
/*      */   protected void printNotation() throws TranscoderException, XMLException, IOException {
/* 1027 */     int t = this.scanner.next();
/* 1028 */     if (t != 1) {
/* 1029 */       throw fatalError("space", null);
/*      */     }
/* 1031 */     char[] space1 = getCurrentValue();
/* 1032 */     t = this.scanner.next();
/*      */     
/* 1034 */     if (t != 14) {
/* 1035 */       throw fatalError("name", null);
/*      */     }
/* 1037 */     char[] name = getCurrentValue();
/* 1038 */     t = this.scanner.next();
/*      */     
/* 1040 */     if (t != 1) {
/* 1041 */       throw fatalError("space", null);
/*      */     }
/* 1043 */     char[] space2 = getCurrentValue();
/* 1044 */     t = this.scanner.next();
/*      */     
/* 1046 */     String externalId = null;
/* 1047 */     char[] space3 = null;
/* 1048 */     char[] string1 = null;
/* 1049 */     char string1Delim = Character.MIN_VALUE;
/* 1050 */     char[] space4 = null;
/* 1051 */     char[] string2 = null;
/* 1052 */     char string2Delim = Character.MIN_VALUE;
/*      */     
/* 1054 */     switch (t) {
/*      */       default:
/* 1056 */         throw fatalError("notation.definition", null);
/*      */       case 27:
/* 1058 */         externalId = "PUBLIC";
/*      */         
/* 1060 */         t = this.scanner.next();
/* 1061 */         if (t != 1) {
/* 1062 */           throw fatalError("space", null);
/*      */         }
/* 1064 */         space3 = getCurrentValue();
/* 1065 */         t = this.scanner.next();
/*      */         
/* 1067 */         if (t != 25) {
/* 1068 */           throw fatalError("string", null);
/*      */         }
/* 1070 */         string1 = getCurrentValue();
/* 1071 */         string1Delim = this.scanner.getStringDelimiter();
/* 1072 */         t = this.scanner.next();
/*      */         
/* 1074 */         if (t == 1) {
/* 1075 */           space4 = getCurrentValue();
/* 1076 */           t = this.scanner.next();
/*      */           
/* 1078 */           if (t == 25) {
/* 1079 */             string2 = getCurrentValue();
/* 1080 */             string2Delim = this.scanner.getStringDelimiter();
/* 1081 */             t = this.scanner.next();
/*      */           } 
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 26:
/* 1087 */         externalId = "SYSTEM";
/*      */         
/* 1089 */         t = this.scanner.next();
/* 1090 */         if (t != 1) {
/* 1091 */           throw fatalError("space", null);
/*      */         }
/* 1093 */         space3 = getCurrentValue();
/* 1094 */         t = this.scanner.next();
/*      */         
/* 1096 */         if (t != 25) {
/* 1097 */           throw fatalError("string", null);
/*      */         }
/* 1099 */         string1 = getCurrentValue();
/* 1100 */         string1Delim = this.scanner.getStringDelimiter();
/* 1101 */         t = this.scanner.next();
/*      */         break;
/*      */     } 
/* 1104 */     char[] space5 = null;
/* 1105 */     if (t == 1) {
/* 1106 */       space5 = getCurrentValue();
/* 1107 */       t = this.scanner.next();
/*      */     } 
/* 1109 */     if (t != 20) {
/* 1110 */       throw fatalError("end", null);
/*      */     }
/* 1112 */     this.output.printNotation(space1, name, space2, externalId, space3, string1, string1Delim, space4, string2, string2Delim, space5);
/*      */ 
/*      */ 
/*      */     
/* 1116 */     this.scanner.next();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void printAttlist() throws TranscoderException, XMLException, IOException {
/* 1126 */     this.type = this.scanner.next();
/* 1127 */     if (this.type != 1) {
/* 1128 */       throw fatalError("space", null);
/*      */     }
/* 1130 */     char[] space = getCurrentValue();
/* 1131 */     this.type = this.scanner.next();
/*      */     
/* 1133 */     if (this.type != 14) {
/* 1134 */       throw fatalError("name", null);
/*      */     }
/* 1136 */     char[] name = getCurrentValue();
/* 1137 */     this.type = this.scanner.next();
/*      */     
/* 1139 */     this.output.printAttlistStart(space, name);
/*      */     
/* 1141 */     while (this.type == 1) {
/* 1142 */       List<OutputManager.NameInfo> names; space = getCurrentValue();
/* 1143 */       this.type = this.scanner.next();
/*      */       
/* 1145 */       if (this.type != 14) {
/*      */         break;
/*      */       }
/* 1148 */       name = getCurrentValue();
/* 1149 */       this.type = this.scanner.next();
/*      */       
/* 1151 */       if (this.type != 1) {
/* 1152 */         throw fatalError("space", null);
/*      */       }
/* 1154 */       char[] space2 = getCurrentValue();
/* 1155 */       this.type = this.scanner.next();
/*      */       
/* 1157 */       this.output.printAttName(space, name, space2);
/*      */       
/* 1159 */       switch (this.type) {
/*      */         case 45:
/*      */         case 46:
/*      */         case 47:
/*      */         case 48:
/*      */         case 49:
/*      */         case 50:
/*      */         case 51:
/*      */         case 52:
/* 1168 */           this.output.printCharacters(getCurrentValue());
/* 1169 */           this.type = this.scanner.next();
/*      */           break;
/*      */         case 57:
/* 1172 */           this.output.printCharacters(getCurrentValue());
/* 1173 */           this.type = this.scanner.next();
/*      */           
/* 1175 */           if (this.type != 1) {
/* 1176 */             throw fatalError("space", null);
/*      */           }
/* 1178 */           this.output.printSpaces(getCurrentValue(), false);
/* 1179 */           this.type = this.scanner.next();
/*      */           
/* 1181 */           if (this.type != 40) {
/* 1182 */             throw fatalError("left.brace", null);
/*      */           }
/* 1184 */           this.type = this.scanner.next();
/*      */           
/* 1186 */           names = new LinkedList();
/* 1187 */           space = null;
/*      */           
/* 1189 */           if (this.type == 1) {
/* 1190 */             space = getCurrentValue();
/* 1191 */             this.type = this.scanner.next();
/*      */           } 
/*      */           
/* 1194 */           if (this.type != 14) {
/* 1195 */             throw fatalError("name", null);
/*      */           }
/* 1197 */           name = getCurrentValue();
/* 1198 */           this.type = this.scanner.next();
/*      */           
/* 1200 */           space2 = null;
/* 1201 */           if (this.type == 1) {
/* 1202 */             space2 = getCurrentValue();
/* 1203 */             this.type = this.scanner.next();
/*      */           } 
/*      */           
/* 1206 */           names.add(new OutputManager.NameInfo(space, name, space2));
/*      */           
/*      */           while (true) {
/* 1209 */             switch (this.type) { default:
/*      */                 break;
/*      */               case 42:
/*      */                 break; }
/* 1213 */              this.type = this.scanner.next();
/*      */             
/* 1215 */             space = null;
/* 1216 */             if (this.type == 1) {
/* 1217 */               space = getCurrentValue();
/* 1218 */               this.type = this.scanner.next();
/*      */             } 
/*      */             
/* 1221 */             if (this.type != 14) {
/* 1222 */               throw fatalError("name", null);
/*      */             }
/* 1224 */             name = getCurrentValue();
/* 1225 */             this.type = this.scanner.next();
/*      */             
/* 1227 */             space2 = null;
/* 1228 */             if (this.type == 1) {
/* 1229 */               space2 = getCurrentValue();
/* 1230 */               this.type = this.scanner.next();
/*      */             } 
/*      */             
/* 1233 */             names.add(new OutputManager.NameInfo(space, name, space2));
/*      */           } 
/*      */           
/* 1236 */           if (this.type != 41) {
/* 1237 */             throw fatalError("right.brace", null);
/*      */           }
/*      */           
/* 1240 */           this.output.printEnumeration(names);
/* 1241 */           this.type = this.scanner.next();
/*      */           break;
/*      */         case 40:
/* 1244 */           this.type = this.scanner.next();
/*      */           
/* 1246 */           names = new LinkedList<OutputManager.NameInfo>();
/* 1247 */           space = null;
/*      */           
/* 1249 */           if (this.type == 1) {
/* 1250 */             space = getCurrentValue();
/* 1251 */             this.type = this.scanner.next();
/*      */           } 
/*      */           
/* 1254 */           if (this.type != 56) {
/* 1255 */             throw fatalError("nmtoken", null);
/*      */           }
/* 1257 */           name = getCurrentValue();
/* 1258 */           this.type = this.scanner.next();
/*      */           
/* 1260 */           space2 = null;
/* 1261 */           if (this.type == 1) {
/* 1262 */             space2 = getCurrentValue();
/* 1263 */             this.type = this.scanner.next();
/*      */           } 
/*      */           
/* 1266 */           names.add(new OutputManager.NameInfo(space, name, space2));
/*      */           
/*      */           while (true) {
/* 1269 */             switch (this.type) { default:
/*      */                 break;
/*      */               case 42:
/*      */                 break; }
/* 1273 */              this.type = this.scanner.next();
/*      */             
/* 1275 */             space = null;
/* 1276 */             if (this.type == 1) {
/* 1277 */               space = getCurrentValue();
/* 1278 */               this.type = this.scanner.next();
/*      */             } 
/*      */             
/* 1281 */             if (this.type != 56) {
/* 1282 */               throw fatalError("nmtoken", null);
/*      */             }
/* 1284 */             name = getCurrentValue();
/* 1285 */             this.type = this.scanner.next();
/*      */             
/* 1287 */             space2 = null;
/* 1288 */             if (this.type == 1) {
/* 1289 */               space2 = getCurrentValue();
/* 1290 */               this.type = this.scanner.next();
/*      */             } 
/*      */             
/* 1293 */             names.add(new OutputManager.NameInfo(space, name, space2));
/*      */           } 
/*      */           
/* 1296 */           if (this.type != 41) {
/* 1297 */             throw fatalError("right.brace", null);
/*      */           }
/*      */           
/* 1300 */           this.output.printEnumeration(names);
/* 1301 */           this.type = this.scanner.next();
/*      */           break;
/*      */       } 
/*      */       
/* 1305 */       if (this.type == 1) {
/* 1306 */         this.output.printSpaces(getCurrentValue(), true);
/* 1307 */         this.type = this.scanner.next();
/*      */       } 
/*      */       
/* 1310 */       switch (this.type) {
/*      */         default:
/* 1312 */           throw fatalError("default.decl", null);
/*      */         case 53:
/*      */         case 54:
/* 1315 */           this.output.printCharacters(getCurrentValue());
/* 1316 */           this.type = this.scanner.next();
/*      */           break;
/*      */         case 55:
/* 1319 */           this.output.printCharacters(getCurrentValue());
/* 1320 */           this.type = this.scanner.next();
/*      */           
/* 1322 */           if (this.type != 1) {
/* 1323 */             throw fatalError("space", null);
/*      */           }
/* 1325 */           this.output.printSpaces(getCurrentValue(), false);
/* 1326 */           this.type = this.scanner.next();
/*      */           
/* 1328 */           if (this.type != 25 && this.type != 16)
/*      */           {
/* 1330 */             throw fatalError("space", null);
/*      */           }
/*      */         case 16:
/*      */         case 25:
/* 1334 */           this.output.printCharacter(this.scanner.getStringDelimiter());
/* 1335 */           this.output.printCharacters(getCurrentValue());
/*      */           while (true) {
/* 1337 */             this.type = this.scanner.next();
/* 1338 */             switch (this.type) {
/*      */               case 16:
/*      */               case 17:
/*      */               case 18:
/*      */               case 25:
/* 1343 */                 this.output.printCharacters(getCurrentValue());
/*      */                 continue;
/*      */               case 12:
/* 1346 */                 this.output.printString("&#");
/* 1347 */                 this.output.printCharacters(getCurrentValue());
/* 1348 */                 this.output.printCharacter(';');
/*      */                 continue;
/*      */               case 13:
/* 1351 */                 this.output.printCharacter('&');
/* 1352 */                 this.output.printCharacters(getCurrentValue());
/* 1353 */                 this.output.printCharacter(';');
/*      */                 continue;
/*      */             } 
/*      */             
/*      */             break;
/*      */           } 
/* 1359 */           this.output.printCharacter(this.scanner.getStringDelimiter()); break;
/*      */       } 
/* 1361 */       space = null;
/*      */     } 
/*      */     
/* 1364 */     if (this.type != 20) {
/* 1365 */       throw fatalError("end", null);
/*      */     }
/* 1367 */     this.output.printAttlistEnd(space);
/* 1368 */     this.type = this.scanner.next();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void printEntityDeclaration() throws TranscoderException, XMLException, IOException {
/*      */     char sd;
/* 1378 */     this.writer.write("<!ENTITY");
/*      */     
/* 1380 */     this.type = this.scanner.next();
/* 1381 */     if (this.type != 1) {
/* 1382 */       throw fatalError("space", null);
/*      */     }
/* 1384 */     this.writer.write(getCurrentValue());
/* 1385 */     this.type = this.scanner.next();
/*      */     
/* 1387 */     boolean pe = false;
/*      */     
/* 1389 */     switch (this.type) {
/*      */       default:
/* 1391 */         throw fatalError("xml", null);
/*      */       case 14:
/* 1393 */         this.writer.write(getCurrentValue());
/* 1394 */         this.type = this.scanner.next();
/*      */         break;
/*      */       case 58:
/* 1397 */         pe = true;
/* 1398 */         this.writer.write(37);
/* 1399 */         this.type = this.scanner.next();
/*      */         
/* 1401 */         if (this.type != 1) {
/* 1402 */           throw fatalError("space", null);
/*      */         }
/* 1404 */         this.writer.write(getCurrentValue());
/* 1405 */         this.type = this.scanner.next();
/*      */         
/* 1407 */         if (this.type != 14) {
/* 1408 */           throw fatalError("name", null);
/*      */         }
/* 1410 */         this.writer.write(getCurrentValue());
/* 1411 */         this.type = this.scanner.next();
/*      */         break;
/*      */     } 
/* 1414 */     if (this.type != 1) {
/* 1415 */       throw fatalError("space", null);
/*      */     }
/* 1417 */     this.writer.write(getCurrentValue());
/* 1418 */     this.type = this.scanner.next();
/*      */     
/* 1420 */     switch (this.type) {
/*      */       case 16:
/*      */       case 25:
/* 1423 */         sd = this.scanner.getStringDelimiter();
/* 1424 */         this.writer.write(sd);
/*      */         while (true) {
/* 1426 */           switch (this.type) {
/*      */             case 16:
/*      */             case 17:
/*      */             case 18:
/*      */             case 25:
/* 1431 */               this.writer.write(getCurrentValue());
/*      */               break;
/*      */             case 13:
/* 1434 */               this.writer.write(38);
/* 1435 */               this.writer.write(getCurrentValue());
/* 1436 */               this.writer.write(59);
/*      */               break;
/*      */             case 34:
/* 1439 */               this.writer.write(38);
/* 1440 */               this.writer.write(getCurrentValue());
/* 1441 */               this.writer.write(59);
/*      */               break;
/*      */             default:
/*      */               break;
/*      */           } 
/* 1446 */           this.type = this.scanner.next();
/*      */         } 
/* 1448 */         this.writer.write(sd);
/*      */         
/* 1450 */         if (this.type == 1) {
/* 1451 */           this.writer.write(getCurrentValue());
/* 1452 */           this.type = this.scanner.next();
/*      */         } 
/*      */         
/* 1455 */         if (this.type != 20) {
/* 1456 */           throw fatalError("end", null);
/*      */         }
/* 1458 */         this.writer.write(">");
/* 1459 */         this.type = this.scanner.next();
/*      */         return;
/*      */       case 27:
/* 1462 */         this.writer.write("PUBLIC");
/* 1463 */         this.type = this.scanner.next();
/* 1464 */         if (this.type != 1) {
/* 1465 */           throw fatalError("space", null);
/*      */         }
/* 1467 */         this.type = this.scanner.next();
/* 1468 */         if (this.type != 25) {
/* 1469 */           throw fatalError("string", null);
/*      */         }
/*      */         
/* 1472 */         this.writer.write(" \"");
/* 1473 */         this.writer.write(getCurrentValue());
/* 1474 */         this.writer.write("\" \"");
/*      */         
/* 1476 */         this.type = this.scanner.next();
/* 1477 */         if (this.type != 1) {
/* 1478 */           throw fatalError("space", null);
/*      */         }
/* 1480 */         this.type = this.scanner.next();
/* 1481 */         if (this.type != 25) {
/* 1482 */           throw fatalError("string", null);
/*      */         }
/*      */         
/* 1485 */         this.writer.write(getCurrentValue());
/* 1486 */         this.writer.write(34);
/*      */         break;
/*      */       
/*      */       case 26:
/* 1490 */         this.writer.write("SYSTEM");
/* 1491 */         this.type = this.scanner.next();
/* 1492 */         if (this.type != 1) {
/* 1493 */           throw fatalError("space", null);
/*      */         }
/* 1495 */         this.type = this.scanner.next();
/* 1496 */         if (this.type != 25) {
/* 1497 */           throw fatalError("string", null);
/*      */         }
/* 1499 */         this.writer.write(" \"");
/* 1500 */         this.writer.write(getCurrentValue());
/* 1501 */         this.writer.write(34);
/*      */         break;
/*      */     } 
/* 1504 */     this.type = this.scanner.next();
/* 1505 */     if (this.type == 1) {
/* 1506 */       this.writer.write(getCurrentValue());
/* 1507 */       this.type = this.scanner.next();
/* 1508 */       if (!pe && this.type == 59) {
/* 1509 */         this.writer.write("NDATA");
/* 1510 */         this.type = this.scanner.next();
/* 1511 */         if (this.type != 1) {
/* 1512 */           throw fatalError("space", null);
/*      */         }
/* 1514 */         this.writer.write(getCurrentValue());
/* 1515 */         this.type = this.scanner.next();
/* 1516 */         if (this.type != 14) {
/* 1517 */           throw fatalError("name", null);
/*      */         }
/* 1519 */         this.writer.write(getCurrentValue());
/* 1520 */         this.type = this.scanner.next();
/*      */       } 
/* 1522 */       if (this.type == 1) {
/* 1523 */         this.writer.write(getCurrentValue());
/* 1524 */         this.type = this.scanner.next();
/*      */       } 
/*      */     } 
/*      */     
/* 1528 */     if (this.type != 20) {
/* 1529 */       throw fatalError("end", null);
/*      */     }
/* 1531 */     this.writer.write(62);
/* 1532 */     this.type = this.scanner.next();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void printElementDeclaration() throws TranscoderException, XMLException, IOException {
/* 1542 */     this.writer.write("<!ELEMENT");
/*      */     
/* 1544 */     this.type = this.scanner.next();
/* 1545 */     if (this.type != 1) {
/* 1546 */       throw fatalError("space", null);
/*      */     }
/* 1548 */     this.writer.write(getCurrentValue());
/* 1549 */     this.type = this.scanner.next();
/* 1550 */     switch (this.type)
/*      */     { default:
/* 1552 */         throw fatalError("name", null);
/*      */       case 14:
/* 1554 */         break; }  this.writer.write(getCurrentValue());
/*      */ 
/*      */     
/* 1557 */     this.type = this.scanner.next();
/* 1558 */     if (this.type != 1) {
/* 1559 */       throw fatalError("space", null);
/*      */     }
/* 1561 */     this.writer.write(getCurrentValue());
/*      */     
/* 1563 */     switch (this.type = this.scanner.next()) {
/*      */       case 35:
/* 1565 */         this.writer.write("EMPTY");
/* 1566 */         this.type = this.scanner.next();
/*      */         break;
/*      */       case 36:
/* 1569 */         this.writer.write("ANY");
/* 1570 */         this.type = this.scanner.next();
/*      */         break;
/*      */       case 40:
/* 1573 */         this.writer.write(40);
/* 1574 */         this.type = this.scanner.next();
/* 1575 */         if (this.type == 1) {
/* 1576 */           this.writer.write(getCurrentValue());
/* 1577 */           this.type = this.scanner.next();
/*      */         } 
/* 1579 */         switch (this.type) {
/*      */           case 44:
/* 1581 */             this.writer.write("#PCDATA");
/* 1582 */             this.type = this.scanner.next();
/*      */             
/*      */             while (true) {
/* 1585 */               switch (this.type)
/*      */               { case 1:
/* 1587 */                   this.writer.write(getCurrentValue());
/* 1588 */                   this.type = this.scanner.next();
/*      */                 
/*      */                 case 42:
/* 1591 */                   this.writer.write(124);
/* 1592 */                   this.type = this.scanner.next();
/* 1593 */                   if (this.type == 1) {
/* 1594 */                     this.writer.write(getCurrentValue());
/* 1595 */                     this.type = this.scanner.next();
/*      */                   } 
/* 1597 */                   if (this.type != 14) {
/* 1598 */                     throw fatalError("name", null);
/*      */                   }
/* 1600 */                   this.writer.write(getCurrentValue());
/* 1601 */                   this.type = this.scanner.next();
/*      */                 case 41:
/*      */                   break; } 
/* 1604 */             }  this.writer.write(41);
/* 1605 */             this.type = this.scanner.next();
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 14:
/*      */           case 40:
/* 1612 */             printChildren();
/* 1613 */             if (this.type != 41) {
/* 1614 */               throw fatalError("right.brace", null);
/*      */             }
/* 1616 */             this.writer.write(41);
/* 1617 */             this.type = this.scanner.next();
/* 1618 */             if (this.type == 1) {
/* 1619 */               this.writer.write(getCurrentValue());
/* 1620 */               this.type = this.scanner.next();
/*      */             } 
/* 1622 */             switch (this.type) {
/*      */               case 37:
/* 1624 */                 this.writer.write(63);
/* 1625 */                 this.type = this.scanner.next();
/*      */                 break;
/*      */               case 39:
/* 1628 */                 this.writer.write(42);
/* 1629 */                 this.type = this.scanner.next();
/*      */                 break;
/*      */               case 38:
/* 1632 */                 this.writer.write(43);
/* 1633 */                 this.type = this.scanner.next(); break;
/*      */             }  break;
/*      */         } 
/*      */         break;
/*      */     } 
/* 1638 */     if (this.type == 1) {
/* 1639 */       this.writer.write(getCurrentValue());
/* 1640 */       this.type = this.scanner.next();
/*      */     } 
/*      */     
/* 1643 */     if (this.type != 20) {
/* 1644 */       throw fatalError("end", null);
/*      */     }
/* 1646 */     this.writer.write(62);
/* 1647 */     this.scanner.next();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void printChildren() throws TranscoderException, XMLException, IOException {
/* 1657 */     int op = 0;
/*      */     while (true) {
/* 1659 */       switch (this.type) {
/*      */         default:
/* 1661 */           throw new RuntimeException("Invalid XML");
/*      */         case 14:
/* 1663 */           this.writer.write(getCurrentValue());
/* 1664 */           this.type = this.scanner.next();
/*      */           break;
/*      */         case 40:
/* 1667 */           this.writer.write(40);
/* 1668 */           this.type = this.scanner.next();
/* 1669 */           if (this.type == 1) {
/* 1670 */             this.writer.write(getCurrentValue());
/* 1671 */             this.type = this.scanner.next();
/*      */           } 
/* 1673 */           printChildren();
/* 1674 */           if (this.type != 41) {
/* 1675 */             throw fatalError("right.brace", null);
/*      */           }
/* 1677 */           this.writer.write(41);
/* 1678 */           this.type = this.scanner.next();
/*      */           break;
/*      */       } 
/* 1681 */       if (this.type == 1) {
/* 1682 */         this.writer.write(getCurrentValue());
/* 1683 */         this.type = this.scanner.next();
/*      */       } 
/*      */       
/* 1686 */       switch (this.type) {
/*      */         case 41:
/*      */           break;
/*      */         case 39:
/* 1690 */           this.writer.write(42);
/* 1691 */           this.type = this.scanner.next();
/*      */           break;
/*      */         case 37:
/* 1694 */           this.writer.write(63);
/* 1695 */           this.type = this.scanner.next();
/*      */           break;
/*      */         case 38:
/* 1698 */           this.writer.write(43);
/* 1699 */           this.type = this.scanner.next();
/*      */           break;
/*      */       } 
/*      */       
/* 1703 */       if (this.type == 1) {
/* 1704 */         this.writer.write(getCurrentValue());
/* 1705 */         this.type = this.scanner.next();
/*      */       } 
/*      */       
/* 1708 */       switch (this.type) {
/*      */         case 42:
/* 1710 */           if (op != 0 && op != this.type) {
/* 1711 */             throw new RuntimeException("Invalid XML");
/*      */           }
/* 1713 */           this.writer.write(124);
/* 1714 */           op = this.type;
/* 1715 */           this.type = this.scanner.next();
/*      */           break;
/*      */         case 43:
/* 1718 */           if (op != 0 && op != this.type) {
/* 1719 */             throw new RuntimeException("Invalid XML");
/*      */           }
/* 1721 */           this.writer.write(44);
/* 1722 */           op = this.type;
/* 1723 */           this.type = this.scanner.next();
/*      */           break;
/*      */       } 
/* 1726 */       if (this.type == 1) {
/* 1727 */         this.writer.write(getCurrentValue());
/* 1728 */         this.type = this.scanner.next();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected char[] getCurrentValue() {
/* 1737 */     int off = this.scanner.getStart() + this.scanner.getStartOffset();
/* 1738 */     int len = this.scanner.getEnd() + this.scanner.getEndOffset() - off;
/* 1739 */     char[] result = new char[len];
/* 1740 */     char[] buffer = this.scanner.getBuffer();
/* 1741 */     System.arraycopy(buffer, off, result, 0, len);
/* 1742 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected TranscoderException fatalError(String key, Object[] params) throws TranscoderException {
/* 1750 */     TranscoderException result = new TranscoderException(key);
/* 1751 */     this.errorHandler.fatalError(result);
/* 1752 */     return result;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/svg2svg/PrettyPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */