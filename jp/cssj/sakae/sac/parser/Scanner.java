/*      */ package jp.cssj.sakae.sac.parser;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Reader;
/*      */ import jp.cssj.sakae.sac.util.io.NormalizingReader;
/*      */ import jp.cssj.sakae.sac.util.io.StreamNormalizingReader;
/*      */ import jp.cssj.sakae.sac.util.io.StringNormalizingReader;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Scanner
/*      */ {
/*      */   protected NormalizingReader reader;
/*      */   protected int current;
/*   82 */   protected char[] buffer = new char[128];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int position;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int type;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int start;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int end;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int blankCharacters;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Scanner(Reader r) throws IOException {
/*  117 */     this.reader = (NormalizingReader)new StreamNormalizingReader(r);
/*  118 */     this.current = nextChar();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Scanner(String s) throws IOException {
/*  128 */     this.reader = (NormalizingReader)new StringNormalizingReader(s);
/*  129 */     this.current = nextChar();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLine() {
/*  136 */     return this.reader.getLine();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getColumn() {
/*  143 */     return this.reader.getColumn();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char[] getBuffer() {
/*  150 */     return this.buffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStart() {
/*  157 */     return this.start;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEnd() {
/*  164 */     return this.end;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearBuffer() {
/*  171 */     if (this.position <= 0) {
/*  172 */       this.position = 0;
/*      */     } else {
/*  174 */       this.buffer[0] = this.buffer[this.position - 1];
/*  175 */       this.position = 1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getType() {
/*  183 */     return this.type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringValue() {
/*  190 */     return new String(this.buffer, this.start, this.end - this.start);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRawStringValue() {
/*  197 */     return new String(this.buffer, this.start, this.end - this.start + endUnitGap());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void scanAtRule() throws IOException {
/*      */     while (true) {
/*      */       int brackets;
/*  209 */       switch (this.current) {
/*      */         case 123:
/*  211 */           brackets = 1;
/*      */           while (true) {
/*  213 */             nextChar();
/*  214 */             switch (this.current) {
/*      */               case 125:
/*  216 */                 if (--brackets > 0)
/*      */                   continue; 
/*      */                 break;
/*      */               case -1:
/*      */                 break;
/*      */               case 123:
/*  222 */                 brackets++;
/*      */             } 
/*      */           }  break;
/*      */         case -1:
/*      */         case 59:
/*      */           break;
/*      */       } 
/*  229 */       nextChar();
/*      */     } 
/*  231 */     this.end = this.position;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int next() throws IOException, ParseException {
/*  238 */     this.blankCharacters = 0;
/*  239 */     this.start = this.position - 1;
/*  240 */     nextToken();
/*  241 */     this.end = this.position - endGap();
/*  242 */     return this.type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int endGap() {
/*  249 */     int result = (this.current == -1) ? 0 : 1;
/*  250 */     result += endUnitGap();
/*  251 */     return result + this.blankCharacters;
/*      */   }
/*      */   
/*      */   protected int endUnitGap() {
/*  255 */     int result = 0;
/*  256 */     switch (this.type) {
/*      */       case 20:
/*      */       case 44:
/*      */       case 45:
/*      */       case 54:
/*  261 */         result++;
/*      */         break;
/*      */       case 19:
/*      */       case 37:
/*      */       case 38:
/*      */       case 39:
/*      */       case 40:
/*      */       case 41:
/*      */       case 42:
/*      */       case 43:
/*      */       case 46:
/*      */       case 47:
/*      */       case 48:
/*      */       case 59:
/*  275 */         result += 2;
/*      */         break;
/*      */       case 49:
/*      */       case 50:
/*      */       case 52:
/*      */       case 58:
/*  281 */         result += 3;
/*      */         break;
/*      */       case 51:
/*  284 */         result += 4; break;
/*      */     } 
/*  286 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void nextToken() throws IOException, ParseException {
/*  293 */     switch (this.current) {
/*      */       case -1:
/*  295 */         this.type = 0;
/*      */         return;
/*      */       case 123:
/*  298 */         nextChar();
/*  299 */         this.type = 1;
/*      */         return;
/*      */       case 125:
/*  302 */         nextChar();
/*  303 */         this.type = 2;
/*      */         return;
/*      */       case 61:
/*  306 */         nextChar();
/*  307 */         this.type = 3;
/*      */         return;
/*      */       case 43:
/*  310 */         nextChar();
/*  311 */         this.type = 4;
/*      */         return;
/*      */       case 44:
/*  314 */         nextChar();
/*  315 */         this.type = 6;
/*      */         return;
/*      */       case 59:
/*  318 */         nextChar();
/*  319 */         this.type = 8;
/*      */         return;
/*      */       case 62:
/*  322 */         nextChar();
/*  323 */         this.type = 9;
/*      */         return;
/*      */       case 91:
/*  326 */         nextChar();
/*  327 */         this.type = 11;
/*      */         return;
/*      */       case 93:
/*  330 */         nextChar();
/*  331 */         this.type = 12;
/*      */         return;
/*      */       case 42:
/*  334 */         nextChar();
/*  335 */         this.type = 13;
/*      */         return;
/*      */       case 40:
/*  338 */         nextChar();
/*  339 */         this.type = 14;
/*      */         return;
/*      */       case 41:
/*  342 */         nextChar();
/*  343 */         this.type = 15;
/*      */         return;
/*      */       case 58:
/*  346 */         nextChar();
/*  347 */         this.type = 16;
/*      */         return;
/*      */       case 9:
/*      */       case 10:
/*      */       case 12:
/*      */       case 13:
/*      */       case 32:
/*      */       case 12288:
/*      */       case 65279:
/*      */         while (true) {
/*  357 */           nextChar();
/*  358 */           if (!ScannerUtilities.isCSSSpace((char)this.current))
/*  359 */           { this.type = 18; return; } 
/*      */         } 
/*      */       case 47:
/*  362 */         nextChar();
/*  363 */         if (this.current != 42) {
/*  364 */           this.type = 10;
/*      */           
/*      */           return;
/*      */         } 
/*  368 */         nextChar();
/*  369 */         this.start = this.position - 1;
/*      */         while (true)
/*  371 */         { if (this.current != -1 && this.current != 42) {
/*  372 */             nextChar(); continue;
/*      */           } 
/*      */           do {
/*  375 */             nextChar();
/*  376 */           } while (this.current != -1 && this.current == 42);
/*  377 */           if (this.current == -1 || this.current == 47)
/*  378 */             break;  }  if (this.current == -1) {
/*  379 */           throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */         }
/*  381 */         nextChar();
/*  382 */         this.type = 19;
/*      */         return;
/*      */       case 39:
/*  385 */         this.type = string1();
/*      */         return;
/*      */       case 34:
/*  388 */         this.type = string2();
/*      */         return;
/*      */       case 60:
/*  391 */         nextChar();
/*  392 */         if (this.current != 33) {
/*  393 */           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */         }
/*  395 */         nextChar();
/*  396 */         if (this.current == 45) {
/*  397 */           nextChar();
/*  398 */           if (this.current == 45) {
/*  399 */             nextChar();
/*  400 */             this.type = 22;
/*      */             return;
/*      */           } 
/*      */         } 
/*  404 */         throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */       case 124:
/*  406 */         nextChar();
/*  407 */         if (this.current == 61) {
/*  408 */           nextChar();
/*  409 */           this.type = 26;
/*      */           return;
/*      */         } 
/*  412 */         this.type = 17;
/*      */         return;
/*      */       case 126:
/*  415 */         nextChar();
/*  416 */         if (this.current == 61) {
/*  417 */           nextChar();
/*  418 */           this.type = 27;
/*      */           return;
/*      */         } 
/*  421 */         throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */       case 35:
/*  423 */         nextChar();
/*  424 */         if (ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*  425 */           this.start = this.position - 1;
/*      */           do {
/*  427 */             nextChar();
/*  428 */             while (this.current == 92) {
/*  429 */               nextChar();
/*  430 */               escape();
/*      */             } 
/*  432 */           } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*  433 */           this.type = 28;
/*      */           return;
/*      */         } 
/*  436 */         throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */       case 64:
/*  438 */         nextChar();
/*  439 */         switch (this.current) {
/*      */           case 67:
/*      */           case 99:
/*  442 */             this.start = this.position - 1;
/*  443 */             if (isEqualIgnoreCase(nextChar(), 'h') && isEqualIgnoreCase(nextChar(), 'a') && 
/*  444 */               isEqualIgnoreCase(nextChar(), 'r') && isEqualIgnoreCase(nextChar(), 's') && 
/*  445 */               isEqualIgnoreCase(nextChar(), 'e') && isEqualIgnoreCase(nextChar(), 't')) {
/*  446 */               nextChar();
/*  447 */               this.type = 31;
/*      */               return;
/*      */             } 
/*      */             break;
/*      */           case 70:
/*      */           case 102:
/*  453 */             this.start = this.position - 1;
/*  454 */             if (isEqualIgnoreCase(nextChar(), 'o') && isEqualIgnoreCase(nextChar(), 'n') && 
/*  455 */               isEqualIgnoreCase(nextChar(), 't') && isEqualIgnoreCase(nextChar(), '-') && 
/*  456 */               isEqualIgnoreCase(nextChar(), 'f') && isEqualIgnoreCase(nextChar(), 'a') && 
/*  457 */               isEqualIgnoreCase(nextChar(), 'c') && isEqualIgnoreCase(nextChar(), 'e')) {
/*  458 */               nextChar();
/*  459 */               this.type = 32;
/*      */               return;
/*      */             } 
/*      */             break;
/*      */           case 73:
/*      */           case 105:
/*  465 */             this.start = this.position - 1;
/*  466 */             if (isEqualIgnoreCase(nextChar(), 'm') && isEqualIgnoreCase(nextChar(), 'p') && 
/*  467 */               isEqualIgnoreCase(nextChar(), 'o') && isEqualIgnoreCase(nextChar(), 'r') && 
/*  468 */               isEqualIgnoreCase(nextChar(), 't')) {
/*  469 */               nextChar();
/*  470 */               this.type = 29;
/*      */               return;
/*      */             } 
/*      */             break;
/*      */           case 77:
/*      */           case 109:
/*  476 */             this.start = this.position - 1;
/*  477 */             if (isEqualIgnoreCase(nextChar(), 'e') && isEqualIgnoreCase(nextChar(), 'd') && 
/*  478 */               isEqualIgnoreCase(nextChar(), 'i') && isEqualIgnoreCase(nextChar(), 'a')) {
/*  479 */               nextChar();
/*  480 */               this.type = 33;
/*      */               return;
/*      */             } 
/*      */             break;
/*      */           case 78:
/*      */           case 110:
/*  486 */             this.start = this.position - 1;
/*  487 */             if (isEqualIgnoreCase(nextChar(), 'a') && isEqualIgnoreCase(nextChar(), 'm') && 
/*  488 */               isEqualIgnoreCase(nextChar(), 'e') && isEqualIgnoreCase(nextChar(), 's') && 
/*  489 */               isEqualIgnoreCase(nextChar(), 'p') && isEqualIgnoreCase(nextChar(), 'a') && 
/*  490 */               isEqualIgnoreCase(nextChar(), 'c') && isEqualIgnoreCase(nextChar(), 'e')) {
/*  491 */               nextChar();
/*  492 */               this.type = 35;
/*      */               return;
/*      */             } 
/*      */             break;
/*      */           case 80:
/*      */           case 112:
/*  498 */             this.start = this.position - 1;
/*  499 */             if (isEqualIgnoreCase(nextChar(), 'a') && isEqualIgnoreCase(nextChar(), 'g') && 
/*  500 */               isEqualIgnoreCase(nextChar(), 'e')) {
/*  501 */               nextChar();
/*  502 */               this.type = 34;
/*      */               return;
/*      */             } 
/*      */             break;
/*      */           case 45:
/*  507 */             this.start = this.position - 1;
/*  508 */             if (isEqualIgnoreCase(nextChar(), 'c') && isEqualIgnoreCase(nextChar(), 's') && 
/*  509 */               isEqualIgnoreCase(nextChar(), 's') && isEqualIgnoreCase(nextChar(), 'j') && 
/*  510 */               isEqualIgnoreCase(nextChar(), '-') && isEqualIgnoreCase(nextChar(), 'p') && 
/*  511 */               isEqualIgnoreCase(nextChar(), 'a') && isEqualIgnoreCase(nextChar(), 'g') && 
/*  512 */               isEqualIgnoreCase(nextChar(), 'e') && isEqualIgnoreCase(nextChar(), '-') && 
/*  513 */               isEqualIgnoreCase(nextChar(), 'c') && isEqualIgnoreCase(nextChar(), 'o') && 
/*  514 */               isEqualIgnoreCase(nextChar(), 'n') && isEqualIgnoreCase(nextChar(), 't') && 
/*  515 */               isEqualIgnoreCase(nextChar(), 'e') && isEqualIgnoreCase(nextChar(), 'n') && 
/*  516 */               isEqualIgnoreCase(nextChar(), 't')) {
/*  517 */               nextChar();
/*  518 */               this.type = 57;
/*      */               return;
/*      */             } 
/*      */             break;
/*      */           default:
/*  523 */             if (!ScannerUtilities.isCSSIdentifierStartCharacter((char)this.current)) {
/*  524 */               throw new ParseException("identifier.character", this.reader.getLine(), this.reader.getColumn());
/*      */             }
/*  526 */             this.start = this.position - 1; break;
/*      */         } 
/*      */         do {
/*  529 */           nextChar();
/*  530 */           while (this.current == 92) {
/*  531 */             nextChar();
/*  532 */             escape();
/*      */           } 
/*  534 */         } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*  535 */         this.type = 30;
/*      */         return;
/*      */       case 33:
/*      */         do {
/*  539 */           nextChar();
/*  540 */         } while (this.current != -1 && ScannerUtilities.isCSSSpace((char)this.current));
/*  541 */         if (isEqualIgnoreCase(this.current, 'i') && isEqualIgnoreCase(nextChar(), 'm') && 
/*  542 */           isEqualIgnoreCase(nextChar(), 'p') && isEqualIgnoreCase(nextChar(), 'o') && 
/*  543 */           isEqualIgnoreCase(nextChar(), 'r') && isEqualIgnoreCase(nextChar(), 't') && 
/*  544 */           isEqualIgnoreCase(nextChar(), 'a') && isEqualIgnoreCase(nextChar(), 'n') && 
/*  545 */           isEqualIgnoreCase(nextChar(), 't')) {
/*  546 */           nextChar();
/*  547 */           this.type = 24;
/*      */           return;
/*      */         } 
/*  550 */         if (this.current == -1) {
/*  551 */           throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */         }
/*  553 */         throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */       
/*      */       case 48:
/*      */       case 49:
/*      */       case 50:
/*      */       case 51:
/*      */       case 52:
/*      */       case 53:
/*      */       case 54:
/*      */       case 55:
/*      */       case 56:
/*      */       case 57:
/*  565 */         this.type = number();
/*      */         return;
/*      */       case 46:
/*  568 */         switch (nextChar()) {
/*      */           case 48:
/*      */           case 49:
/*      */           case 50:
/*      */           case 51:
/*      */           case 52:
/*      */           case 53:
/*      */           case 54:
/*      */           case 55:
/*      */           case 56:
/*      */           case 57:
/*  579 */             this.type = dotNumber();
/*      */             return;
/*      */         } 
/*  582 */         this.type = 7;
/*      */         return;
/*      */       
/*      */       case 85:
/*      */       case 117:
/*  587 */         nextChar();
/*  588 */         switch (this.current) {
/*      */           case 43:
/*  590 */             nextChar();
/*  591 */             while (this.current != -1 && (ScannerUtilities.isCSSHexadecimalCharacter((char)this.current) || this.current == 63 || this.current == 45))
/*      */             {
/*  593 */               nextChar();
/*      */             }
/*  595 */             this.type = 55;
/*      */             return;
/*      */           case 82:
/*      */           case 114:
/*  599 */             nextChar();
/*  600 */             switch (this.current) {
/*      */               case 76:
/*      */               case 108:
/*  603 */                 nextChar();
/*  604 */                 switch (this.current) {
/*      */                   case 40:
/*      */                     do {
/*  607 */                       nextChar();
/*  608 */                     } while (this.current != -1 && ScannerUtilities.isCSSSpace((char)this.current));
/*  609 */                     switch (this.current) {
/*      */                       case 39:
/*  611 */                         string1();
/*  612 */                         this.blankCharacters += 2;
/*  613 */                         while (this.current != -1 && ScannerUtilities.isCSSSpace((char)this.current)) {
/*  614 */                           this.blankCharacters++;
/*  615 */                           nextChar();
/*      */                         } 
/*  617 */                         if (this.current == -1) {
/*  618 */                           throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */                         }
/*  620 */                         if (this.current != 41) {
/*  621 */                           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */                         }
/*  623 */                         nextChar();
/*  624 */                         this.type = 53;
/*      */                         return;
/*      */                       case 34:
/*  627 */                         string2();
/*  628 */                         this.blankCharacters += 2;
/*  629 */                         while (this.current != -1 && ScannerUtilities.isCSSSpace((char)this.current)) {
/*  630 */                           this.blankCharacters++;
/*  631 */                           nextChar();
/*      */                         } 
/*  633 */                         if (this.current == -1) {
/*  634 */                           throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */                         }
/*  636 */                         if (this.current != 41) {
/*  637 */                           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */                         }
/*  639 */                         nextChar();
/*  640 */                         this.type = 53;
/*      */                         return;
/*      */                       case 41:
/*  643 */                         this.start = this.position;
/*  644 */                         nextChar();
/*  645 */                         this.type = 53;
/*      */                         return;
/*      */                     } 
/*  648 */                     if (!ScannerUtilities.isCSSURICharacter((char)this.current)) {
/*  649 */                       throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */                     }
/*  651 */                     this.start = this.position - 1;
/*      */                     do {
/*  653 */                       nextChar();
/*  654 */                     } while (this.current != -1 && ScannerUtilities.isCSSURICharacter((char)this.current));
/*  655 */                     this.blankCharacters++;
/*  656 */                     while (this.current != -1 && ScannerUtilities.isCSSSpace((char)this.current)) {
/*  657 */                       this.blankCharacters++;
/*  658 */                       nextChar();
/*      */                     } 
/*  660 */                     if (this.current == -1) {
/*  661 */                       throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */                     }
/*  663 */                     if (this.current != 41) {
/*  664 */                       throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */                     }
/*  666 */                     nextChar();
/*  667 */                     this.type = 53; return;
/*      */                 } 
/*      */                 break;
/*      */             } 
/*      */             break;
/*      */         } 
/*  673 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*  674 */           nextChar();
/*      */         }
/*  676 */         if (this.current == 40) {
/*  677 */           nextChar();
/*  678 */           this.type = 54;
/*      */           return;
/*      */         } 
/*  681 */         this.type = 21;
/*      */         return;
/*      */       case 45:
/*  684 */         nextChar();
/*  685 */         switch (this.current) {
/*      */           case 45:
/*  687 */             nextChar();
/*  688 */             if (this.current == 62) {
/*  689 */               nextChar();
/*  690 */               this.type = 23;
/*      */               return;
/*      */             } 
/*  693 */             throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */           case 46:
/*      */           case 48:
/*      */           case 49:
/*      */           case 50:
/*      */           case 51:
/*      */           case 52:
/*      */           case 53:
/*      */           case 54:
/*      */           case 55:
/*      */           case 56:
/*      */           case 57:
/*  705 */             this.type = 5; return;
/*      */         } 
/*      */         break;
/*      */     } 
/*  709 */     if (this.current == 92 || ScannerUtilities.isCSSIdentifierStartCharacter((char)this.current)) {
/*  710 */       while (this.current == 92) {
/*  711 */         nextChar();
/*  712 */         escape();
/*      */       } 
/*      */ 
/*      */       
/*  716 */       while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*  717 */         nextChar();
/*  718 */         while (this.current == 92) {
/*  719 */           nextChar();
/*  720 */           escape();
/*      */         } 
/*      */       } 
/*      */       
/*  724 */       if (this.current == 40) {
/*  725 */         nextChar();
/*  726 */         this.type = 54;
/*      */         return;
/*      */       } 
/*  729 */       this.type = 21;
/*      */       return;
/*      */     } 
/*  732 */     nextChar();
/*  733 */     throw new ParseException("identifier.character", this.reader.getLine(), this.reader.getColumn());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int string1() throws IOException, ParseException {
/*  741 */     nextChar();
/*  742 */     this.start = this.position - 1;
/*      */     while (true) {
/*  744 */       switch (this.current) {
/*      */         case -1:
/*  746 */           throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */         case 39:
/*      */           break;
/*      */         case 34:
/*      */           break;
/*      */         case 92:
/*  752 */           switch (nextChar()) {
/*      */             case 10:
/*      */             case 12:
/*  755 */               prevChar();
/*  756 */               prevChar();
/*      */               break;
/*      */           } 
/*  759 */           escape();
/*      */           continue;
/*      */ 
/*      */         
/*      */         default:
/*  764 */           if (!ScannerUtilities.isCSSStringCharacter((char)this.current))
/*  765 */             throw new ParseException("character", this.reader.getLine(), this.reader.getColumn()); 
/*      */           break;
/*      */       } 
/*  768 */       nextChar();
/*      */     } 
/*  770 */     nextChar();
/*  771 */     return 20;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int string2() throws IOException, ParseException {
/*  778 */     nextChar();
/*  779 */     this.start = this.position - 1;
/*      */     while (true) {
/*  781 */       switch (this.current) {
/*      */         case -1:
/*  783 */           throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */         case 39:
/*      */           break;
/*      */         case 34:
/*      */           break;
/*      */         case 92:
/*  789 */           switch (nextChar()) {
/*      */             case 10:
/*      */             case 12:
/*  792 */               prevChar();
/*  793 */               prevChar();
/*      */               break;
/*      */           } 
/*  796 */           escape();
/*      */           continue;
/*      */ 
/*      */         
/*      */         default:
/*  801 */           if (!ScannerUtilities.isCSSStringCharacter((char)this.current))
/*  802 */             throw new ParseException("character", this.reader.getLine(), this.reader.getColumn()); 
/*      */           break;
/*      */       } 
/*  805 */       nextChar();
/*      */     } 
/*  807 */     nextChar();
/*  808 */     return 20;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int number() throws IOException, ParseException {
/*      */     while (true) {
/*  816 */       switch (nextChar()) {
/*      */         case 46:
/*  818 */           switch (nextChar()) {
/*      */             case 48:
/*      */             case 49:
/*      */             case 50:
/*      */             case 51:
/*      */             case 52:
/*      */             case 53:
/*      */             case 54:
/*      */             case 55:
/*      */             case 56:
/*      */             case 57:
/*  829 */               return dotNumber();
/*      */           } 
/*  831 */           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */         
/*      */         case 48:
/*      */         case 49:
/*      */         case 50:
/*      */         case 51:
/*      */         case 52:
/*      */         case 53:
/*      */         case 54:
/*      */         case 55:
/*      */         case 56:
/*      */         case 57:
/*      */           break;
/*      */       } 
/*      */     } 
/*  846 */     return numberUnit(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int dotNumber() throws IOException {
/*      */     while (true) {
/*  854 */       switch (nextChar()) {
/*      */         case 48:
/*      */         case 49:
/*      */         case 50:
/*      */         case 51:
/*      */         case 52:
/*      */         case 53:
/*      */         case 54:
/*      */         case 55:
/*      */         case 56:
/*      */         case 57:
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/*  869 */     return numberUnit(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int numberUnit(boolean integer) throws IOException {
/*  876 */     switch (this.current) {
/*      */       case 37:
/*  878 */         nextChar();
/*  879 */         return 44;
/*      */       case 67:
/*      */       case 99:
/*  882 */         switch (nextChar()) {
/*      */           case 72:
/*      */           case 104:
/*  885 */             nextChar();
/*  886 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               do {
/*  888 */                 nextChar();
/*  889 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*  890 */               return 36;
/*      */             } 
/*  892 */             return 59;
/*      */           case 77:
/*      */           case 109:
/*  895 */             nextChar();
/*  896 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               do {
/*  898 */                 nextChar();
/*  899 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*  900 */               return 36;
/*      */             } 
/*  902 */             return 39;
/*      */         } 
/*  904 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*  905 */           nextChar();
/*      */         }
/*  907 */         return 36;
/*      */       
/*      */       case 68:
/*      */       case 100:
/*  911 */         switch (nextChar()) {
/*      */           case 69:
/*      */           case 101:
/*  914 */             switch (nextChar()) {
/*      */               case 71:
/*      */               case 103:
/*  917 */                 nextChar();
/*  918 */                 if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */                   do {
/*  920 */                     nextChar();
/*  921 */                   } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*  922 */                   return 36;
/*      */                 } 
/*  924 */                 return 49;
/*      */             }  break;
/*      */         } 
/*  927 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*  928 */           nextChar();
/*      */         }
/*  930 */         return 36;
/*      */       
/*      */       case 69:
/*      */       case 101:
/*  934 */         switch (nextChar()) {
/*      */           case 77:
/*      */           case 109:
/*  937 */             nextChar();
/*  938 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               do {
/*  940 */                 nextChar();
/*  941 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*  942 */               return 36;
/*      */             } 
/*  944 */             return 38;
/*      */           case 88:
/*      */           case 120:
/*  947 */             nextChar();
/*  948 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               do {
/*  950 */                 nextChar();
/*  951 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*  952 */               return 36;
/*      */             } 
/*  954 */             return 37;
/*      */         } 
/*  956 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*  957 */           nextChar();
/*      */         }
/*  959 */         return 36;
/*      */       
/*      */       case 71:
/*      */       case 103:
/*  963 */         switch (nextChar()) {
/*      */           case 82:
/*      */           case 114:
/*  966 */             switch (nextChar()) {
/*      */               case 65:
/*      */               case 97:
/*  969 */                 switch (nextChar()) {
/*      */                   case 68:
/*      */                   case 100:
/*  972 */                     nextChar();
/*  973 */                     if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */                       do {
/*  975 */                         nextChar();
/*  976 */                       } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*  977 */                       return 36;
/*      */                     } 
/*  979 */                     return 51;
/*      */                 }  break;
/*      */             }  break;
/*      */         } 
/*  983 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*  984 */           nextChar();
/*      */         }
/*  986 */         return 36;
/*      */       
/*      */       case 72:
/*      */       case 104:
/*  990 */         nextChar();
/*  991 */         switch (this.current) {
/*      */           case 90:
/*      */           case 122:
/*  994 */             nextChar();
/*  995 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               do {
/*  997 */                 nextChar();
/*  998 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*  999 */               return 36;
/*      */             } 
/* 1001 */             return 43;
/*      */         } 
/* 1003 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/* 1004 */           nextChar();
/*      */         }
/* 1006 */         return 36;
/*      */       
/*      */       case 73:
/*      */       case 105:
/* 1010 */         switch (nextChar()) {
/*      */           case 78:
/*      */           case 110:
/* 1013 */             nextChar();
/* 1014 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               do {
/* 1016 */                 nextChar();
/* 1017 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/* 1018 */               return 36;
/*      */             } 
/* 1020 */             return 41;
/*      */         } 
/* 1022 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/* 1023 */           nextChar();
/*      */         }
/* 1025 */         return 36;
/*      */       
/*      */       case 75:
/*      */       case 107:
/* 1029 */         switch (nextChar()) {
/*      */           case 72:
/*      */           case 104:
/* 1032 */             switch (nextChar()) {
/*      */               case 90:
/*      */               case 122:
/* 1035 */                 nextChar();
/* 1036 */                 if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */                   do {
/* 1038 */                     nextChar();
/* 1039 */                   } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/* 1040 */                   return 36;
/*      */                 } 
/* 1042 */                 return 52;
/*      */             }  break;
/*      */         } 
/* 1045 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/* 1046 */           nextChar();
/*      */         }
/* 1048 */         return 36;
/*      */       
/*      */       case 77:
/*      */       case 109:
/* 1052 */         switch (nextChar()) {
/*      */           case 77:
/*      */           case 109:
/* 1055 */             nextChar();
/* 1056 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               do {
/* 1058 */                 nextChar();
/* 1059 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/* 1060 */               return 36;
/*      */             } 
/* 1062 */             return 40;
/*      */           case 83:
/*      */           case 115:
/* 1065 */             nextChar();
/* 1066 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               do {
/* 1068 */                 nextChar();
/* 1069 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/* 1070 */               return 36;
/*      */             } 
/* 1072 */             return 42;
/*      */         } 
/* 1074 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/* 1075 */           nextChar();
/*      */         }
/* 1077 */         return 36;
/*      */       
/*      */       case 80:
/*      */       case 112:
/* 1081 */         switch (nextChar()) {
/*      */           case 67:
/*      */           case 99:
/* 1084 */             nextChar();
/* 1085 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               do {
/* 1087 */                 nextChar();
/* 1088 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/* 1089 */               return 36;
/*      */             } 
/* 1091 */             return 46;
/*      */           case 84:
/*      */           case 116:
/* 1094 */             nextChar();
/* 1095 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               do {
/* 1097 */                 nextChar();
/* 1098 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/* 1099 */               return 36;
/*      */             } 
/* 1101 */             return 47;
/*      */           case 88:
/*      */           case 120:
/* 1104 */             nextChar();
/* 1105 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               do {
/* 1107 */                 nextChar();
/* 1108 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/* 1109 */               return 36;
/*      */             } 
/* 1111 */             return 48;
/*      */         } 
/* 1113 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/* 1114 */           nextChar();
/*      */         }
/* 1116 */         return 36;
/*      */       
/*      */       case 82:
/*      */       case 114:
/* 1120 */         switch (nextChar()) {
/*      */           case 65:
/*      */           case 97:
/* 1123 */             switch (nextChar()) {
/*      */               case 68:
/*      */               case 100:
/* 1126 */                 nextChar();
/* 1127 */                 if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */                   do {
/* 1129 */                     nextChar();
/* 1130 */                   } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/* 1131 */                   return 36;
/*      */                 } 
/* 1133 */                 return 50;
/*      */             } 
/*      */           case 69:
/*      */           case 101:
/* 1137 */             switch (nextChar()) {
/*      */               case 77:
/*      */               case 109:
/* 1140 */                 nextChar();
/* 1141 */                 if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */                   do {
/* 1143 */                     nextChar();
/* 1144 */                   } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/* 1145 */                   return 36;
/*      */                 } 
/* 1147 */                 return 58;
/*      */             }  break;
/*      */         } 
/* 1150 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/* 1151 */           nextChar();
/*      */         }
/* 1153 */         return 36;
/*      */       
/*      */       case 83:
/*      */       case 115:
/* 1157 */         nextChar();
/* 1158 */         return 45;
/*      */     } 
/* 1160 */     if (this.current != -1 && ScannerUtilities.isCSSIdentifierStartCharacter((char)this.current)) {
/*      */       do {
/* 1162 */         nextChar();
/* 1163 */       } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/* 1164 */       return 36;
/*      */     } 
/* 1166 */     return integer ? 25 : 56;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void escape() throws IOException, ParseException {
/* 1174 */     int escapeStart = this.position - 1;
/* 1175 */     if (ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/* 1176 */       for (int i = 0; i < 4; i++) {
/* 1177 */         nextChar();
/* 1178 */         if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/*      */           break;
/*      */         }
/*      */       } 
/* 1182 */       String hex = new String(this.buffer, escapeStart, this.position - escapeStart - 1);
/* 1183 */       char ch = (char)this.current;
/* 1184 */       for (int j = hex.length() + 1; j >= 0; j--) {
/* 1185 */         prevChar();
/*      */       }
/*      */       try {
/* 1188 */         addChar((char)Integer.parseInt(hex, 16));
/* 1189 */         if (!ScannerUtilities.isCSSSpace(ch)) {
/* 1190 */           addChar(ch);
/*      */         } else {
/* 1192 */           nextChar();
/*      */         } 
/*      */         return;
/* 1195 */       } catch (NumberFormatException numberFormatException) {}
/*      */ 
/*      */     
/*      */     }
/* 1199 */     else if ((this.current >= 32 && this.current <= 126) || this.current >= 128) {
/* 1200 */       char ch = (char)this.current;
/* 1201 */       prevChar();
/* 1202 */       prevChar();
/* 1203 */       addChar(ch);
/* 1204 */       nextChar();
/*      */       
/*      */       return;
/*      */     } 
/* 1208 */     throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean isEqualIgnoreCase(int i, char c) {
/* 1215 */     return (i == -1) ? false : ((Character.toLowerCase((char)i) == c));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextChar() throws IOException {
/* 1223 */     this.current = this.reader.read();
/*      */     
/* 1225 */     if (this.current == -1) {
/* 1226 */       return this.current;
/*      */     }
/*      */     
/* 1229 */     if (this.position == this.buffer.length) {
/* 1230 */       char[] t = new char[this.position * 3 / 2];
/* 1231 */       for (int i = 0; i < this.position; i++) {
/* 1232 */         t[i] = this.buffer[i];
/*      */       }
/* 1234 */       this.buffer = t;
/*      */     } 
/*      */     
/* 1237 */     this.buffer[this.position++] = (char)this.current; return (char)this.current;
/*      */   }
/*      */   
/*      */   protected void prevChar() {
/* 1241 */     this.current = this.buffer[--this.position];
/*      */   }
/*      */   
/*      */   protected void addChar(char ch) {
/* 1245 */     this.current = ch;
/* 1246 */     this.buffer[this.position++] = (char)this.current;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/Scanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */