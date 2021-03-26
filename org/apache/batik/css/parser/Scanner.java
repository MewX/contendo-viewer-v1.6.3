/*      */ package org.apache.batik.css.parser;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import org.apache.batik.util.io.NormalizingReader;
/*      */ import org.apache.batik.util.io.StreamNormalizingReader;
/*      */ import org.apache.batik.util.io.StringNormalizingReader;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*   51 */   protected char[] buffer = new char[128];
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
/*      */   protected int type;
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
/*      */   public Scanner(Reader r) throws ParseException {
/*      */     try {
/*   85 */       this.reader = (NormalizingReader)new StreamNormalizingReader(r);
/*   86 */       this.current = nextChar();
/*   87 */     } catch (IOException e) {
/*   88 */       throw new ParseException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Scanner(InputStream is, String enc) throws ParseException {
/*      */     try {
/*   99 */       this.reader = (NormalizingReader)new StreamNormalizingReader(is, enc);
/*  100 */       this.current = nextChar();
/*  101 */     } catch (IOException e) {
/*  102 */       throw new ParseException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Scanner(String s) throws ParseException {
/*      */     try {
/*  112 */       this.reader = (NormalizingReader)new StringNormalizingReader(s);
/*  113 */       this.current = nextChar();
/*  114 */     } catch (IOException e) {
/*  115 */       throw new ParseException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLine() {
/*  123 */     return this.reader.getLine();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getColumn() {
/*  130 */     return this.reader.getColumn();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char[] getBuffer() {
/*  137 */     return this.buffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStart() {
/*  144 */     return this.start;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEnd() {
/*  151 */     return this.end;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearBuffer() {
/*  158 */     if (this.position <= 0) {
/*  159 */       this.position = 0;
/*      */     } else {
/*  161 */       this.buffer[0] = this.buffer[this.position - 1];
/*  162 */       this.position = 1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getType() {
/*  170 */     return this.type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringValue() {
/*  177 */     return new String(this.buffer, this.start, this.end - this.start);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void scanAtRule() throws ParseException {
/*      */     try {
/*      */       while (true) {
/*      */         int brackets;
/*  188 */         switch (this.current) {
/*      */           case 123:
/*  190 */             brackets = 1;
/*      */             while (true) {
/*  192 */               nextChar();
/*  193 */               switch (this.current) {
/*      */                 case 125:
/*  195 */                   if (--brackets > 0)
/*      */                     continue; 
/*      */                   break;
/*      */                 case -1:
/*      */                   break;
/*      */                 case 123:
/*  201 */                   brackets++;
/*      */               } 
/*      */             }  break;
/*      */           case -1:
/*      */           case 59:
/*      */             break;
/*      */         } 
/*  208 */         nextChar();
/*      */       } 
/*  210 */       this.end = this.position;
/*  211 */     } catch (IOException e) {
/*  212 */       throw new ParseException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int next() throws ParseException {
/*  220 */     this.blankCharacters = 0;
/*  221 */     this.start = this.position - 1;
/*  222 */     nextToken();
/*  223 */     this.end = this.position - endGap();
/*  224 */     return this.type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {
/*      */     try {
/*  232 */       this.reader.close();
/*  233 */     } catch (IOException e) {
/*  234 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int endGap() {
/*  242 */     int result = (this.current == -1) ? 0 : 1;
/*  243 */     switch (this.type) {
/*      */       case 19:
/*      */       case 42:
/*      */       case 43:
/*      */       case 52:
/*  248 */         result++;
/*      */         break;
/*      */       case 18:
/*      */       case 35:
/*      */       case 36:
/*      */       case 37:
/*      */       case 38:
/*      */       case 39:
/*      */       case 40:
/*      */       case 41:
/*      */       case 44:
/*      */       case 45:
/*      */       case 46:
/*  261 */         result += 2;
/*      */         break;
/*      */       case 47:
/*      */       case 48:
/*      */       case 50:
/*  266 */         result += 3;
/*      */         break;
/*      */       case 49:
/*  269 */         result += 4; break;
/*      */     } 
/*  271 */     return result + this.blankCharacters;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void nextToken() throws ParseException {
/*      */     try {
/*      */       boolean range;
/*      */       int i;
/*  279 */       switch (this.current) {
/*      */         case -1:
/*  281 */           this.type = 0;
/*      */           return;
/*      */         case 123:
/*  284 */           nextChar();
/*  285 */           this.type = 1;
/*      */           return;
/*      */         case 125:
/*  288 */           nextChar();
/*  289 */           this.type = 2;
/*      */           return;
/*      */         case 61:
/*  292 */           nextChar();
/*  293 */           this.type = 3;
/*      */           return;
/*      */         case 43:
/*  296 */           nextChar();
/*  297 */           this.type = 4;
/*      */           return;
/*      */         case 44:
/*  300 */           nextChar();
/*  301 */           this.type = 6;
/*      */           return;
/*      */         case 59:
/*  304 */           nextChar();
/*  305 */           this.type = 8;
/*      */           return;
/*      */         case 62:
/*  308 */           nextChar();
/*  309 */           this.type = 9;
/*      */           return;
/*      */         case 91:
/*  312 */           nextChar();
/*  313 */           this.type = 11;
/*      */           return;
/*      */         case 93:
/*  316 */           nextChar();
/*  317 */           this.type = 12;
/*      */           return;
/*      */         case 42:
/*  320 */           nextChar();
/*  321 */           this.type = 13;
/*      */           return;
/*      */         case 40:
/*  324 */           nextChar();
/*  325 */           this.type = 14;
/*      */           return;
/*      */         case 41:
/*  328 */           nextChar();
/*  329 */           this.type = 15;
/*      */           return;
/*      */         case 58:
/*  332 */           nextChar();
/*  333 */           this.type = 16;
/*      */           return;
/*      */         case 9:
/*      */         case 10:
/*      */         case 12:
/*      */         case 13:
/*      */         case 32:
/*      */           while (true) {
/*  341 */             nextChar();
/*  342 */             if (!ScannerUtilities.isCSSSpace((char)this.current))
/*  343 */             { this.type = 17; return; } 
/*      */           } 
/*      */         case 47:
/*  346 */           nextChar();
/*  347 */           if (this.current != 42) {
/*  348 */             this.type = 10;
/*      */             
/*      */             return;
/*      */           } 
/*  352 */           nextChar();
/*  353 */           this.start = this.position - 1;
/*      */           while (true)
/*  355 */           { if (this.current != -1 && this.current != 42) {
/*  356 */               nextChar(); continue;
/*      */             } 
/*      */             do {
/*  359 */               nextChar();
/*  360 */             } while (this.current != -1 && this.current == 42);
/*  361 */             if (this.current == -1 || this.current == 47)
/*  362 */               break;  }  if (this.current == -1) {
/*  363 */             throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */           }
/*      */ 
/*      */           
/*  367 */           nextChar();
/*  368 */           this.type = 18;
/*      */           return;
/*      */         case 39:
/*  371 */           this.type = string1();
/*      */           return;
/*      */         case 34:
/*  374 */           this.type = string2();
/*      */           return;
/*      */         case 60:
/*  377 */           nextChar();
/*  378 */           if (this.current != 33) {
/*  379 */             throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */           }
/*      */ 
/*      */           
/*  383 */           nextChar();
/*  384 */           if (this.current == 45) {
/*  385 */             nextChar();
/*  386 */             if (this.current == 45) {
/*  387 */               nextChar();
/*  388 */               this.type = 21;
/*      */               return;
/*      */             } 
/*      */           } 
/*  392 */           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */ 
/*      */         
/*      */         case 45:
/*  396 */           nextChar();
/*  397 */           if (this.current != 45) {
/*  398 */             this.type = 5;
/*      */             return;
/*      */           } 
/*  401 */           nextChar();
/*  402 */           if (this.current == 62) {
/*  403 */             nextChar();
/*  404 */             this.type = 22;
/*      */             return;
/*      */           } 
/*  407 */           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */ 
/*      */         
/*      */         case 124:
/*  411 */           nextChar();
/*  412 */           if (this.current == 61) {
/*  413 */             nextChar();
/*  414 */             this.type = 25;
/*      */             return;
/*      */           } 
/*  417 */           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */ 
/*      */         
/*      */         case 126:
/*  421 */           nextChar();
/*  422 */           if (this.current == 61) {
/*  423 */             nextChar();
/*  424 */             this.type = 26;
/*      */             return;
/*      */           } 
/*  427 */           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */ 
/*      */         
/*      */         case 35:
/*  431 */           nextChar();
/*  432 */           if (ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*  433 */             this.start = this.position - 1;
/*      */             do {
/*  435 */               nextChar();
/*  436 */               while (this.current == 92) {
/*  437 */                 nextChar();
/*  438 */                 escape();
/*      */               } 
/*  440 */             } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */             
/*  443 */             this.type = 27;
/*      */             return;
/*      */           } 
/*  446 */           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */ 
/*      */         
/*      */         case 64:
/*  450 */           nextChar();
/*  451 */           switch (this.current) {
/*      */             case 67:
/*      */             case 99:
/*  454 */               this.start = this.position - 1;
/*  455 */               if (isEqualIgnoreCase(nextChar(), 'h') && isEqualIgnoreCase(nextChar(), 'a') && isEqualIgnoreCase(nextChar(), 'r') && isEqualIgnoreCase(nextChar(), 's') && isEqualIgnoreCase(nextChar(), 'e') && isEqualIgnoreCase(nextChar(), 't')) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  461 */                 nextChar();
/*  462 */                 this.type = 30;
/*      */                 return;
/*      */               } 
/*      */               break;
/*      */             case 70:
/*      */             case 102:
/*  468 */               this.start = this.position - 1;
/*  469 */               if (isEqualIgnoreCase(nextChar(), 'o') && isEqualIgnoreCase(nextChar(), 'n') && isEqualIgnoreCase(nextChar(), 't') && isEqualIgnoreCase(nextChar(), '-') && isEqualIgnoreCase(nextChar(), 'f') && isEqualIgnoreCase(nextChar(), 'a') && isEqualIgnoreCase(nextChar(), 'c') && isEqualIgnoreCase(nextChar(), 'e')) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  477 */                 nextChar();
/*  478 */                 this.type = 31;
/*      */                 return;
/*      */               } 
/*      */               break;
/*      */             case 73:
/*      */             case 105:
/*  484 */               this.start = this.position - 1;
/*  485 */               if (isEqualIgnoreCase(nextChar(), 'm') && isEqualIgnoreCase(nextChar(), 'p') && isEqualIgnoreCase(nextChar(), 'o') && isEqualIgnoreCase(nextChar(), 'r') && isEqualIgnoreCase(nextChar(), 't')) {
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  490 */                 nextChar();
/*  491 */                 this.type = 28;
/*      */                 return;
/*      */               } 
/*      */               break;
/*      */             case 77:
/*      */             case 109:
/*  497 */               this.start = this.position - 1;
/*  498 */               if (isEqualIgnoreCase(nextChar(), 'e') && isEqualIgnoreCase(nextChar(), 'd') && isEqualIgnoreCase(nextChar(), 'i') && isEqualIgnoreCase(nextChar(), 'a')) {
/*      */ 
/*      */ 
/*      */                 
/*  502 */                 nextChar();
/*  503 */                 this.type = 32;
/*      */                 return;
/*      */               } 
/*      */               break;
/*      */             case 80:
/*      */             case 112:
/*  509 */               this.start = this.position - 1;
/*  510 */               if (isEqualIgnoreCase(nextChar(), 'a') && isEqualIgnoreCase(nextChar(), 'g') && isEqualIgnoreCase(nextChar(), 'e')) {
/*      */ 
/*      */                 
/*  513 */                 nextChar();
/*  514 */                 this.type = 33;
/*      */                 return;
/*      */               } 
/*      */               break;
/*      */             default:
/*  519 */               if (!ScannerUtilities.isCSSIdentifierStartCharacter((char)this.current))
/*      */               {
/*  521 */                 throw new ParseException("identifier.character", this.reader.getLine(), this.reader.getColumn());
/*      */               }
/*      */ 
/*      */               
/*  525 */               this.start = this.position - 1; break;
/*      */           } 
/*      */           do {
/*  528 */             nextChar();
/*  529 */             while (this.current == 92) {
/*  530 */               nextChar();
/*  531 */               escape();
/*      */             } 
/*  533 */           } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */           
/*  535 */           this.type = 29;
/*      */           return;
/*      */         case 33:
/*      */           do {
/*  539 */             nextChar();
/*  540 */           } while (this.current != -1 && ScannerUtilities.isCSSSpace((char)this.current));
/*      */           
/*  542 */           if (isEqualIgnoreCase(this.current, 'i') && isEqualIgnoreCase(nextChar(), 'm') && isEqualIgnoreCase(nextChar(), 'p') && isEqualIgnoreCase(nextChar(), 'o') && isEqualIgnoreCase(nextChar(), 'r') && isEqualIgnoreCase(nextChar(), 't') && isEqualIgnoreCase(nextChar(), 'a') && isEqualIgnoreCase(nextChar(), 'n') && isEqualIgnoreCase(nextChar(), 't')) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  551 */             nextChar();
/*  552 */             this.type = 23;
/*      */             return;
/*      */           } 
/*  555 */           if (this.current == -1) {
/*  556 */             throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */           }
/*      */ 
/*      */           
/*  560 */           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */         case 48: case 49: case 50: case 51: case 52: case 53:
/*      */         case 54:
/*      */         case 55:
/*      */         case 56:
/*      */         case 57:
/*  566 */           this.type = number();
/*      */           return;
/*      */         case 46:
/*  569 */           switch (nextChar()) { case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55:
/*      */             case 56:
/*      */             case 57:
/*  572 */               this.type = dotNumber();
/*      */               return; }
/*      */           
/*  575 */           this.type = 7;
/*      */           return;
/*      */         
/*      */         case 85:
/*      */         case 117:
/*  580 */           nextChar();
/*  581 */           switch (this.current) {
/*      */             case 43:
/*  583 */               range = false;
/*  584 */               for (i = 0; i < 6; i++) {
/*  585 */                 nextChar();
/*  586 */                 switch (this.current) {
/*      */                   case 63:
/*  588 */                     range = true;
/*      */                     break;
/*      */                   default:
/*  591 */                     if (range && !ScannerUtilities.isCSSHexadecimalCharacter((char)this.current))
/*      */                     {
/*      */                       
/*  594 */                       throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */                     }
/*      */                     break;
/*      */                 } 
/*      */               
/*      */               } 
/*  600 */               nextChar();
/*  601 */               if (range) {
/*  602 */                 this.type = 53;
/*      */                 return;
/*      */               } 
/*  605 */               if (this.current == 45) {
/*  606 */                 nextChar();
/*  607 */                 if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current))
/*      */                 {
/*  609 */                   throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */                 }
/*      */ 
/*      */                 
/*  613 */                 nextChar();
/*  614 */                 if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/*      */                   
/*  616 */                   this.type = 53;
/*      */                   return;
/*      */                 } 
/*  619 */                 nextChar();
/*  620 */                 if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/*      */                   
/*  622 */                   this.type = 53;
/*      */                   return;
/*      */                 } 
/*  625 */                 nextChar();
/*  626 */                 if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/*      */                   
/*  628 */                   this.type = 53;
/*      */                   return;
/*      */                 } 
/*  631 */                 nextChar();
/*  632 */                 if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/*      */                   
/*  634 */                   this.type = 53;
/*      */                   return;
/*      */                 } 
/*  637 */                 nextChar();
/*  638 */                 if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/*      */                   
/*  640 */                   this.type = 53;
/*      */                   return;
/*      */                 } 
/*  643 */                 nextChar();
/*  644 */                 this.type = 53;
/*      */                 return;
/*      */               } 
/*      */             case 82:
/*      */             case 114:
/*  649 */               nextChar();
/*  650 */               switch (this.current) {
/*      */                 case 76:
/*      */                 case 108:
/*  653 */                   nextChar();
/*  654 */                   switch (this.current) {
/*      */                     case 40:
/*      */                       do {
/*  657 */                         nextChar();
/*  658 */                       } while (this.current != -1 && ScannerUtilities.isCSSSpace((char)this.current));
/*      */ 
/*      */                       
/*  661 */                       switch (this.current) {
/*      */                         case 39:
/*  663 */                           string1();
/*  664 */                           this.blankCharacters += 2;
/*  665 */                           while (this.current != -1 && ScannerUtilities.isCSSSpace((char)this.current)) {
/*      */ 
/*      */                             
/*  668 */                             this.blankCharacters++;
/*  669 */                             nextChar();
/*      */                           } 
/*  671 */                           if (this.current == -1) {
/*  672 */                             throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */                           }
/*      */ 
/*      */ 
/*      */                           
/*  677 */                           if (this.current != 41) {
/*  678 */                             throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */                           }
/*      */ 
/*      */ 
/*      */                           
/*  683 */                           nextChar();
/*  684 */                           this.type = 51;
/*      */                           return;
/*      */                         case 34:
/*  687 */                           string2();
/*  688 */                           this.blankCharacters += 2;
/*  689 */                           while (this.current != -1 && ScannerUtilities.isCSSSpace((char)this.current)) {
/*      */ 
/*      */                             
/*  692 */                             this.blankCharacters++;
/*  693 */                             nextChar();
/*      */                           } 
/*  695 */                           if (this.current == -1) {
/*  696 */                             throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */                           }
/*      */ 
/*      */ 
/*      */                           
/*  701 */                           if (this.current != 41) {
/*  702 */                             throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */                           }
/*      */ 
/*      */ 
/*      */                           
/*  707 */                           nextChar();
/*  708 */                           this.type = 51;
/*      */                           return;
/*      */                         case 41:
/*  711 */                           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */                       } 
/*      */ 
/*      */                       
/*  715 */                       if (!ScannerUtilities.isCSSURICharacter((char)this.current))
/*      */                       {
/*  717 */                         throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */                       }
/*      */ 
/*      */ 
/*      */                       
/*  722 */                       this.start = this.position - 1;
/*      */                       do {
/*  724 */                         nextChar();
/*  725 */                       } while (this.current != -1 && ScannerUtilities.isCSSURICharacter((char)this.current));
/*      */ 
/*      */                       
/*  728 */                       this.blankCharacters++;
/*  729 */                       while (this.current != -1 && ScannerUtilities.isCSSSpace((char)this.current)) {
/*      */ 
/*      */                         
/*  732 */                         this.blankCharacters++;
/*  733 */                         nextChar();
/*      */                       } 
/*  735 */                       if (this.current == -1) {
/*  736 */                         throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */                       }
/*      */ 
/*      */ 
/*      */                       
/*  741 */                       if (this.current != 41) {
/*  742 */                         throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */                       }
/*      */ 
/*      */ 
/*      */                       
/*  747 */                       nextChar();
/*  748 */                       this.type = 51; return;
/*      */                   } 
/*      */                   break;
/*      */               } 
/*      */               break;
/*      */           } 
/*  754 */           while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current))
/*      */           {
/*  756 */             nextChar();
/*      */           }
/*  758 */           if (this.current == 40) {
/*  759 */             nextChar();
/*  760 */             this.type = 52;
/*      */             return;
/*      */           } 
/*  763 */           this.type = 20;
/*      */           return;
/*      */       } 
/*  766 */       if (this.current == 92) {
/*      */         do {
/*  768 */           nextChar();
/*  769 */           escape();
/*  770 */         } while (this.current == 92);
/*  771 */       } else if (!ScannerUtilities.isCSSIdentifierStartCharacter((char)this.current)) {
/*      */         
/*  773 */         nextChar();
/*  774 */         throw new ParseException("identifier.character", this.reader.getLine(), this.reader.getColumn());
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  779 */       while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */         
/*  781 */         nextChar();
/*  782 */         while (this.current == 92) {
/*  783 */           nextChar();
/*  784 */           escape();
/*      */         } 
/*      */       } 
/*  787 */       if (this.current == 40) {
/*  788 */         nextChar();
/*  789 */         this.type = 52;
/*      */         return;
/*      */       } 
/*  792 */       this.type = 20;
/*      */       
/*      */       return;
/*  795 */     } catch (IOException e) {
/*  796 */       throw new ParseException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int string1() throws IOException {
/*  804 */     this.start = this.position;
/*      */     while (true) {
/*  806 */       switch (nextChar()) {
/*      */         case -1:
/*  808 */           throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */         
/*      */         case 39:
/*      */           break;
/*      */         
/*      */         case 34:
/*      */           continue;
/*      */         case 92:
/*  816 */           switch (nextChar()) {
/*      */             case 10:
/*      */             case 12:
/*      */               continue;
/*      */           } 
/*  821 */           escape();
/*      */           continue;
/*      */       } 
/*      */       
/*  825 */       if (!ScannerUtilities.isCSSStringCharacter((char)this.current)) {
/*  826 */         throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  832 */     nextChar();
/*  833 */     return 19;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int string2() throws IOException {
/*  840 */     this.start = this.position;
/*      */     while (true) {
/*  842 */       switch (nextChar()) {
/*      */         case -1:
/*  844 */           throw new ParseException("eof", this.reader.getLine(), this.reader.getColumn());
/*      */         
/*      */         case 39:
/*      */           continue;
/*      */         
/*      */         case 34:
/*      */           break;
/*      */         case 92:
/*  852 */           switch (nextChar()) {
/*      */             case 10:
/*      */             case 12:
/*      */               continue;
/*      */           } 
/*  857 */           escape();
/*      */           continue;
/*      */       } 
/*      */       
/*  861 */       if (!ScannerUtilities.isCSSStringCharacter((char)this.current)) {
/*  862 */         throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  868 */     nextChar();
/*  869 */     return 19;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int number() throws IOException {
/*      */     while (true)
/*  877 */     { switch (nextChar())
/*      */       { case 46:
/*  879 */           switch (nextChar()) { case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55:
/*      */             case 56:
/*      */             case 57:
/*  882 */               return dotNumber(); }
/*      */           
/*  884 */           throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */         case 48: case 49: case 50:
/*      */         case 51:
/*      */         case 52:
/*      */         case 53:
/*      */         case 54:
/*      */         case 55:
/*      */         case 56:
/*      */         case 57:
/*  893 */           break; }  }  return numberUnit(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int dotNumber() throws IOException {
/*      */     while (true)
/*  901 */     { switch (nextChar()) { case 48: case 49: case 50: case 51:
/*      */         case 52:
/*      */         case 53:
/*      */         case 54:
/*      */         case 55:
/*      */         case 56:
/*      */         case 57:
/*  908 */           break; }  }  return numberUnit(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int numberUnit(boolean integer) throws IOException {
/*  915 */     switch (this.current) {
/*      */       case 37:
/*  917 */         nextChar();
/*  918 */         return 42;
/*      */       case 67:
/*      */       case 99:
/*  921 */         switch (nextChar()) {
/*      */           case 77:
/*      */           case 109:
/*  924 */             nextChar();
/*  925 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               
/*      */               do {
/*  928 */                 nextChar();
/*  929 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */               
/*  932 */               return 34;
/*      */             } 
/*  934 */             return 37;
/*      */         } 
/*  936 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current))
/*      */         {
/*  938 */           nextChar();
/*      */         }
/*  940 */         return 34;
/*      */       
/*      */       case 68:
/*      */       case 100:
/*  944 */         switch (nextChar()) {
/*      */           case 69:
/*      */           case 101:
/*  947 */             switch (nextChar()) {
/*      */               case 71:
/*      */               case 103:
/*  950 */                 nextChar();
/*  951 */                 if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */                   
/*      */                   do {
/*  954 */                     nextChar();
/*  955 */                   } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */                   
/*  958 */                   return 34;
/*      */                 } 
/*  960 */                 return 47;
/*      */             }  break;
/*      */         } 
/*  963 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current))
/*      */         {
/*  965 */           nextChar();
/*      */         }
/*  967 */         return 34;
/*      */       
/*      */       case 69:
/*      */       case 101:
/*  971 */         switch (nextChar()) {
/*      */           case 77:
/*      */           case 109:
/*  974 */             nextChar();
/*  975 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               
/*      */               do {
/*  978 */                 nextChar();
/*  979 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */               
/*  982 */               return 34;
/*      */             } 
/*  984 */             return 36;
/*      */           case 88:
/*      */           case 120:
/*  987 */             nextChar();
/*  988 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               
/*      */               do {
/*  991 */                 nextChar();
/*  992 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */               
/*  995 */               return 34;
/*      */             } 
/*  997 */             return 35;
/*      */         } 
/*  999 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current))
/*      */         {
/* 1001 */           nextChar();
/*      */         }
/* 1003 */         return 34;
/*      */       
/*      */       case 71:
/*      */       case 103:
/* 1007 */         switch (nextChar()) {
/*      */           case 82:
/*      */           case 114:
/* 1010 */             switch (nextChar()) {
/*      */               case 65:
/*      */               case 97:
/* 1013 */                 switch (nextChar()) {
/*      */                   case 68:
/*      */                   case 100:
/* 1016 */                     nextChar();
/* 1017 */                     if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */ 
/*      */                       
/*      */                       do {
/* 1021 */                         nextChar();
/* 1022 */                       } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */                       
/* 1025 */                       return 34;
/*      */                     } 
/* 1027 */                     return 49;
/*      */                 }  break;
/*      */             }  break;
/*      */         } 
/* 1031 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current))
/*      */         {
/* 1033 */           nextChar();
/*      */         }
/* 1035 */         return 34;
/*      */       
/*      */       case 72:
/*      */       case 104:
/* 1039 */         nextChar();
/* 1040 */         switch (this.current) {
/*      */           case 90:
/*      */           case 122:
/* 1043 */             nextChar();
/* 1044 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               
/*      */               do {
/* 1047 */                 nextChar();
/* 1048 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */               
/* 1051 */               return 34;
/*      */             } 
/* 1053 */             return 41;
/*      */         } 
/* 1055 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current))
/*      */         {
/* 1057 */           nextChar();
/*      */         }
/* 1059 */         return 34;
/*      */       
/*      */       case 73:
/*      */       case 105:
/* 1063 */         switch (nextChar()) {
/*      */           case 78:
/*      */           case 110:
/* 1066 */             nextChar();
/* 1067 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               
/*      */               do {
/* 1070 */                 nextChar();
/* 1071 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */               
/* 1074 */               return 34;
/*      */             } 
/* 1076 */             return 39;
/*      */         } 
/* 1078 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current))
/*      */         {
/* 1080 */           nextChar();
/*      */         }
/* 1082 */         return 34;
/*      */       
/*      */       case 75:
/*      */       case 107:
/* 1086 */         switch (nextChar()) {
/*      */           case 72:
/*      */           case 104:
/* 1089 */             switch (nextChar()) {
/*      */               case 90:
/*      */               case 122:
/* 1092 */                 nextChar();
/* 1093 */                 if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */                   
/*      */                   do {
/* 1096 */                     nextChar();
/* 1097 */                   } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */                   
/* 1100 */                   return 34;
/*      */                 } 
/* 1102 */                 return 50;
/*      */             }  break;
/*      */         } 
/* 1105 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current))
/*      */         {
/* 1107 */           nextChar();
/*      */         }
/* 1109 */         return 34;
/*      */       
/*      */       case 77:
/*      */       case 109:
/* 1113 */         switch (nextChar()) {
/*      */           case 77:
/*      */           case 109:
/* 1116 */             nextChar();
/* 1117 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               
/*      */               do {
/* 1120 */                 nextChar();
/* 1121 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */               
/* 1124 */               return 34;
/*      */             } 
/* 1126 */             return 38;
/*      */           case 83:
/*      */           case 115:
/* 1129 */             nextChar();
/* 1130 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               
/*      */               do {
/* 1133 */                 nextChar();
/* 1134 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */               
/* 1137 */               return 34;
/*      */             } 
/* 1139 */             return 40;
/*      */         } 
/* 1141 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current))
/*      */         {
/* 1143 */           nextChar();
/*      */         }
/* 1145 */         return 34;
/*      */       
/*      */       case 80:
/*      */       case 112:
/* 1149 */         switch (nextChar()) {
/*      */           case 67:
/*      */           case 99:
/* 1152 */             nextChar();
/* 1153 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               
/*      */               do {
/* 1156 */                 nextChar();
/* 1157 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */               
/* 1160 */               return 34;
/*      */             } 
/* 1162 */             return 44;
/*      */           case 84:
/*      */           case 116:
/* 1165 */             nextChar();
/* 1166 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               
/*      */               do {
/* 1169 */                 nextChar();
/* 1170 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */               
/* 1173 */               return 34;
/*      */             } 
/* 1175 */             return 45;
/*      */           case 88:
/*      */           case 120:
/* 1178 */             nextChar();
/* 1179 */             if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */               
/*      */               do {
/* 1182 */                 nextChar();
/* 1183 */               } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */               
/* 1186 */               return 34;
/*      */             } 
/* 1188 */             return 46;
/*      */         } 
/* 1190 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current))
/*      */         {
/* 1192 */           nextChar();
/*      */         }
/* 1194 */         return 34;
/*      */       
/*      */       case 82:
/*      */       case 114:
/* 1198 */         switch (nextChar()) {
/*      */           case 65:
/*      */           case 97:
/* 1201 */             switch (nextChar()) {
/*      */               case 68:
/*      */               case 100:
/* 1204 */                 nextChar();
/* 1205 */                 if (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current)) {
/*      */                   
/*      */                   do {
/* 1208 */                     nextChar();
/* 1209 */                   } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */ 
/*      */                   
/* 1212 */                   return 34;
/*      */                 } 
/* 1214 */                 return 48;
/*      */             }  break;
/*      */         } 
/* 1217 */         while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current))
/*      */         {
/* 1219 */           nextChar();
/*      */         }
/* 1221 */         return 34;
/*      */       
/*      */       case 83:
/*      */       case 115:
/* 1225 */         nextChar();
/* 1226 */         return 43;
/*      */     } 
/* 1228 */     if (this.current != -1 && ScannerUtilities.isCSSIdentifierStartCharacter((char)this.current)) {
/*      */ 
/*      */       
/*      */       do {
/* 1232 */         nextChar();
/* 1233 */       } while (this.current != -1 && ScannerUtilities.isCSSNameCharacter((char)this.current));
/*      */       
/* 1235 */       return 34;
/*      */     } 
/* 1237 */     return integer ? 24 : 54;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void escape() throws IOException {
/* 1245 */     if (ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/* 1246 */       nextChar();
/* 1247 */       if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/* 1248 */         if (ScannerUtilities.isCSSSpace((char)this.current)) {
/* 1249 */           nextChar();
/*      */         }
/*      */         return;
/*      */       } 
/* 1253 */       nextChar();
/* 1254 */       if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/* 1255 */         if (ScannerUtilities.isCSSSpace((char)this.current)) {
/* 1256 */           nextChar();
/*      */         }
/*      */         return;
/*      */       } 
/* 1260 */       nextChar();
/* 1261 */       if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/* 1262 */         if (ScannerUtilities.isCSSSpace((char)this.current)) {
/* 1263 */           nextChar();
/*      */         }
/*      */         return;
/*      */       } 
/* 1267 */       nextChar();
/* 1268 */       if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/* 1269 */         if (ScannerUtilities.isCSSSpace((char)this.current)) {
/* 1270 */           nextChar();
/*      */         }
/*      */         return;
/*      */       } 
/* 1274 */       nextChar();
/* 1275 */       if (!ScannerUtilities.isCSSHexadecimalCharacter((char)this.current)) {
/* 1276 */         if (ScannerUtilities.isCSSSpace((char)this.current)) {
/* 1277 */           nextChar();
/*      */         }
/*      */         return;
/*      */       } 
/*      */     } 
/* 1282 */     if ((this.current >= 32 && this.current <= 126) || this.current >= 128) {
/* 1283 */       nextChar();
/*      */       return;
/*      */     } 
/* 1286 */     throw new ParseException("character", this.reader.getLine(), this.reader.getColumn());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean isEqualIgnoreCase(int i, char c) {
/* 1295 */     return (i == -1) ? false : ((Character.toLowerCase((char)i) == c));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextChar() throws IOException {
/* 1303 */     this.current = this.reader.read();
/*      */     
/* 1305 */     if (this.current == -1) {
/* 1306 */       return this.current;
/*      */     }
/*      */     
/* 1309 */     if (this.position == this.buffer.length) {
/*      */       
/* 1311 */       char[] t = new char[1 + this.position + this.position / 2];
/* 1312 */       System.arraycopy(this.buffer, 0, t, 0, this.position);
/* 1313 */       this.buffer = t;
/*      */     } 
/*      */     
/* 1316 */     this.buffer[this.position++] = (char)this.current; return (char)this.current;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/Scanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */