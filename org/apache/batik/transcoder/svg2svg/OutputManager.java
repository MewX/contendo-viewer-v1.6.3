/*     */ package org.apache.batik.transcoder.svg2svg;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
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
/*     */ public class OutputManager
/*     */ {
/*     */   protected PrettyPrinter prettyPrinter;
/*     */   protected Writer writer;
/*     */   protected int level;
/*  55 */   protected StringBuffer margin = new StringBuffer();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected int line = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int column;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   protected List xmlSpace = new LinkedList(); protected boolean canIndent; protected List startingLines; protected boolean lineAttributes;
/*     */   public OutputManager(PrettyPrinter pp, Writer w) {
/*  72 */     this.xmlSpace.add(Boolean.FALSE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     this.canIndent = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     this.startingLines = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     this.lineAttributes = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     this.prettyPrinter = pp;
/*  97 */     this.writer = w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printCharacter(char c) throws IOException {
/* 104 */     if (c == '\n') {
/* 105 */       printNewline();
/*     */     } else {
/* 107 */       this.column++;
/* 108 */       this.writer.write(c);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printNewline() throws IOException {
/* 116 */     String nl = this.prettyPrinter.getNewline();
/* 117 */     for (int i = 0; i < nl.length(); i++) {
/* 118 */       this.writer.write(nl.charAt(i));
/*     */     }
/* 120 */     this.column = 0;
/* 121 */     this.line++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printString(String s) throws IOException {
/* 128 */     for (int i = 0; i < s.length(); i++) {
/* 129 */       printCharacter(s.charAt(i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printCharacters(char[] ca) throws IOException {
/* 137 */     for (char aCa : ca) {
/* 138 */       printCharacter(aCa);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printSpaces(char[] text, boolean opt) throws IOException {
/* 148 */     if (this.prettyPrinter.getFormat()) {
/* 149 */       if (!opt) {
/* 150 */         printCharacter(' ');
/*     */       }
/*     */     } else {
/* 153 */       printCharacters(text);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printTopSpaces(char[] text) throws IOException {
/* 162 */     if (this.prettyPrinter.getFormat()) {
/* 163 */       int nl = newlines(text);
/* 164 */       for (int i = 0; i < nl; i++) {
/* 165 */         printNewline();
/*     */       }
/*     */     } else {
/* 168 */       printCharacters(text);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printComment(char[] text) throws IOException {
/* 177 */     if (this.prettyPrinter.getFormat()) {
/* 178 */       if (this.canIndent) {
/* 179 */         printNewline();
/* 180 */         printString(this.margin.toString());
/*     */       } 
/* 182 */       printString("<!--");
/* 183 */       if (this.column + text.length + 3 < this.prettyPrinter.getDocumentWidth()) {
/* 184 */         printCharacters(text);
/*     */       } else {
/* 186 */         formatText(text, this.margin.toString(), false);
/* 187 */         printCharacter(' ');
/*     */       } 
/* 189 */       if (this.column + 3 > this.prettyPrinter.getDocumentWidth()) {
/* 190 */         printNewline();
/* 191 */         printString(this.margin.toString());
/*     */       } 
/* 193 */       printString("-->");
/*     */     } else {
/* 195 */       printString("<!--");
/* 196 */       printCharacters(text);
/* 197 */       printString("-->");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printXMLDecl(char[] space1, char[] space2, char[] space3, char[] version, char versionDelim, char[] space4, char[] space5, char[] space6, char[] encoding, char encodingDelim, char[] space7, char[] space8, char[] space9, char[] standalone, char standaloneDelim, char[] space10) throws IOException {
/* 218 */     printString("<?xml");
/*     */     
/* 220 */     printSpaces(space1, false);
/*     */     
/* 222 */     printString("version");
/*     */     
/* 224 */     if (space2 != null) {
/* 225 */       printSpaces(space2, true);
/*     */     }
/*     */     
/* 228 */     printCharacter('=');
/*     */     
/* 230 */     if (space3 != null) {
/* 231 */       printSpaces(space3, true);
/*     */     }
/*     */     
/* 234 */     printCharacter(versionDelim);
/* 235 */     printCharacters(version);
/* 236 */     printCharacter(versionDelim);
/*     */     
/* 238 */     if (space4 != null) {
/* 239 */       printSpaces(space4, false);
/*     */       
/* 241 */       if (encoding != null) {
/* 242 */         printString("encoding");
/*     */         
/* 244 */         if (space5 != null) {
/* 245 */           printSpaces(space5, true);
/*     */         }
/*     */         
/* 248 */         printCharacter('=');
/*     */         
/* 250 */         if (space6 != null) {
/* 251 */           printSpaces(space6, true);
/*     */         }
/*     */         
/* 254 */         printCharacter(encodingDelim);
/* 255 */         printCharacters(encoding);
/* 256 */         printCharacter(encodingDelim);
/*     */         
/* 258 */         if (space7 != null) {
/* 259 */           printSpaces(space7, (standalone == null));
/*     */         }
/*     */       } 
/*     */       
/* 263 */       if (standalone != null) {
/* 264 */         printString("standalone");
/*     */         
/* 266 */         if (space8 != null) {
/* 267 */           printSpaces(space8, true);
/*     */         }
/*     */         
/* 270 */         printCharacter('=');
/*     */         
/* 272 */         if (space9 != null) {
/* 273 */           printSpaces(space9, true);
/*     */         }
/*     */         
/* 276 */         printCharacter(standaloneDelim);
/* 277 */         printCharacters(standalone);
/* 278 */         printCharacter(standaloneDelim);
/*     */         
/* 280 */         if (space10 != null) {
/* 281 */           printSpaces(space10, true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 286 */     printString("?>");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printPI(char[] target, char[] space, char[] data) throws IOException {
/* 293 */     if (this.prettyPrinter.getFormat() && 
/* 294 */       this.canIndent) {
/* 295 */       printNewline();
/* 296 */       printString(this.margin.toString());
/*     */     } 
/*     */     
/* 299 */     printString("<?");
/* 300 */     printCharacters(target);
/* 301 */     printSpaces(space, false);
/* 302 */     printCharacters(data);
/* 303 */     printString("?>");
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
/*     */   public void printDoctypeStart(char[] space1, char[] root, char[] space2, String externalId, char[] space3, char[] string1, char string1Delim, char[] space4, char[] string2, char string2Delim, char[] space5) throws IOException {
/* 318 */     if (this.prettyPrinter.getFormat()) {
/* 319 */       printString("<!DOCTYPE");
/*     */       
/* 321 */       printCharacter(' ');
/* 322 */       printCharacters(root);
/*     */       
/* 324 */       if (space2 != null) {
/* 325 */         printCharacter(' ');
/* 326 */         printString(externalId);
/* 327 */         printCharacter(' ');
/*     */         
/* 329 */         printCharacter(string1Delim);
/* 330 */         printCharacters(string1);
/* 331 */         printCharacter(string1Delim);
/*     */         
/* 333 */         if (space4 != null && 
/* 334 */           string2 != null) {
/* 335 */           if (this.column + string2.length + 3 > this.prettyPrinter.getDocumentWidth()) {
/*     */             
/* 337 */             printNewline();
/* 338 */             int i = 0;
/* 339 */             for (; i < this.prettyPrinter.getTabulationWidth(); 
/* 340 */               i++) {
/* 341 */               printCharacter(' ');
/*     */             }
/*     */           } else {
/* 344 */             printCharacter(' ');
/*     */           } 
/* 346 */           printCharacter(string2Delim);
/* 347 */           printCharacters(string2);
/* 348 */           printCharacter(string2Delim);
/* 349 */           printCharacter(' ');
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 354 */       printString("<!DOCTYPE");
/*     */       
/* 356 */       printSpaces(space1, false);
/* 357 */       printCharacters(root);
/*     */       
/* 359 */       if (space2 != null) {
/* 360 */         printSpaces(space2, false);
/* 361 */         printString(externalId);
/* 362 */         printSpaces(space3, false);
/*     */         
/* 364 */         printCharacter(string1Delim);
/* 365 */         printCharacters(string1);
/* 366 */         printCharacter(string1Delim);
/*     */         
/* 368 */         if (space4 != null) {
/* 369 */           printSpaces(space4, (string2 == null));
/*     */           
/* 371 */           if (string2 != null) {
/* 372 */             printCharacter(string2Delim);
/* 373 */             printCharacters(string2);
/* 374 */             printCharacter(string2Delim);
/*     */             
/* 376 */             if (space5 != null) {
/* 377 */               printSpaces(space5, true);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printDoctypeEnd(char[] space) throws IOException {
/* 389 */     if (space != null) {
/* 390 */       printSpaces(space, true);
/*     */     }
/* 392 */     printCharacter('>');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printParameterEntityReference(char[] name) throws IOException {
/* 399 */     printCharacter('%');
/* 400 */     printCharacters(name);
/* 401 */     printCharacter(';');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printEntityReference(char[] name, boolean first) throws IOException {
/* 409 */     if (this.prettyPrinter.getFormat() && this.xmlSpace.get(0) != Boolean.TRUE && first) {
/*     */ 
/*     */       
/* 412 */       printNewline();
/* 413 */       printString(this.margin.toString());
/*     */     } 
/* 415 */     printCharacter('&');
/* 416 */     printCharacters(name);
/* 417 */     printCharacter(';');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printCharacterEntityReference(char[] code, boolean first, boolean preceedingSpace) throws IOException {
/* 426 */     if (this.prettyPrinter.getFormat() && this.xmlSpace.get(0) != Boolean.TRUE)
/*     */     {
/*     */       
/* 429 */       if (first) {
/* 430 */         printNewline();
/* 431 */         printString(this.margin.toString());
/* 432 */       } else if (preceedingSpace) {
/* 433 */         int endCol = this.column + code.length + 3;
/* 434 */         if (endCol > this.prettyPrinter.getDocumentWidth()) {
/* 435 */           printNewline();
/* 436 */           printString(this.margin.toString());
/*     */         } else {
/* 438 */           printCharacter(' ');
/*     */         } 
/*     */       } 
/*     */     }
/* 442 */     printString("&#");
/* 443 */     printCharacters(code);
/* 444 */     printCharacter(';');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printElementStart(char[] name, List attributes, char[] space) throws IOException {
/* 452 */     this.xmlSpace.add(0, this.xmlSpace.get(0));
/*     */     
/* 454 */     this.startingLines.add(0, Integer.valueOf(this.line));
/*     */     
/* 456 */     if (this.prettyPrinter.getFormat() && 
/* 457 */       this.canIndent) {
/* 458 */       printNewline();
/* 459 */       printString(this.margin.toString());
/*     */     } 
/*     */     
/* 462 */     printCharacter('<');
/* 463 */     printCharacters(name);
/*     */     
/* 465 */     if (this.prettyPrinter.getFormat()) {
/* 466 */       Iterator<AttributeInfo> it = attributes.iterator();
/* 467 */       if (it.hasNext()) {
/* 468 */         AttributeInfo ai = it.next();
/*     */         
/* 470 */         if (ai.isAttribute("xml:space")) {
/* 471 */           this.xmlSpace.set(0, ai.value.equals("preserve") ? Boolean.TRUE : Boolean.FALSE);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 476 */         printCharacter(' ');
/* 477 */         printCharacters(ai.name);
/* 478 */         printCharacter('=');
/* 479 */         printCharacter(ai.delimiter);
/* 480 */         printString(ai.value);
/* 481 */         printCharacter(ai.delimiter);
/*     */       } 
/* 483 */       while (it.hasNext()) {
/* 484 */         AttributeInfo ai = it.next();
/*     */         
/* 486 */         if (ai.isAttribute("xml:space")) {
/* 487 */           this.xmlSpace.set(0, ai.value.equals("preserve") ? Boolean.TRUE : Boolean.FALSE);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 492 */         int len = ai.name.length + ai.value.length() + 4;
/* 493 */         if (this.lineAttributes || len + this.column > this.prettyPrinter.getDocumentWidth()) {
/*     */           
/* 495 */           printNewline();
/* 496 */           printString(this.margin.toString());
/* 497 */           for (int j = 0; j < name.length + 2; j++) {
/* 498 */             printCharacter(' ');
/*     */           }
/*     */         } else {
/* 501 */           printCharacter(' ');
/*     */         } 
/* 503 */         printCharacters(ai.name);
/* 504 */         printCharacter('=');
/* 505 */         printCharacter(ai.delimiter);
/* 506 */         printString(ai.value);
/* 507 */         printCharacter(ai.delimiter);
/*     */       } 
/*     */     } else {
/* 510 */       for (Object attribute : attributes) {
/* 511 */         AttributeInfo ai = (AttributeInfo)attribute;
/*     */         
/* 513 */         if (ai.isAttribute("xml:space")) {
/* 514 */           this.xmlSpace.set(0, ai.value.equals("preserve") ? Boolean.TRUE : Boolean.FALSE);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 519 */         printSpaces(ai.space, false);
/* 520 */         printCharacters(ai.name);
/*     */         
/* 522 */         if (ai.space1 != null) {
/* 523 */           printSpaces(ai.space1, true);
/*     */         }
/* 525 */         printCharacter('=');
/* 526 */         if (ai.space2 != null) {
/* 527 */           printSpaces(ai.space2, true);
/*     */         }
/*     */         
/* 530 */         printCharacter(ai.delimiter);
/* 531 */         printString(ai.value);
/* 532 */         printCharacter(ai.delimiter);
/*     */       } 
/*     */     } 
/*     */     
/* 536 */     if (space != null) {
/* 537 */       printSpaces(space, true);
/*     */     }
/* 539 */     this.level++;
/* 540 */     for (int i = 0; i < this.prettyPrinter.getTabulationWidth(); i++) {
/* 541 */       this.margin.append(' ');
/*     */     }
/* 543 */     this.canIndent = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printElementEnd(char[] name, char[] space) throws IOException {
/* 550 */     for (int i = 0; i < this.prettyPrinter.getTabulationWidth(); i++) {
/* 551 */       this.margin.deleteCharAt(0);
/*     */     }
/* 553 */     this.level--;
/* 554 */     if (name != null) {
/* 555 */       if (this.prettyPrinter.getFormat() && 
/* 556 */         this.xmlSpace.get(0) != Boolean.TRUE && (this.line != ((Integer)this.startingLines.get(0)).intValue() || this.column + name.length + 3 >= this.prettyPrinter.getDocumentWidth())) {
/*     */ 
/*     */         
/* 559 */         printNewline();
/* 560 */         printString(this.margin.toString());
/*     */       } 
/*     */       
/* 563 */       printString("</");
/* 564 */       printCharacters(name);
/* 565 */       if (space != null) {
/* 566 */         printSpaces(space, true);
/*     */       }
/* 568 */       printCharacter('>');
/*     */     } else {
/* 570 */       printString("/>");
/*     */     } 
/* 572 */     this.startingLines.remove(0);
/* 573 */     this.xmlSpace.remove(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean printCharacterData(char[] data, boolean first, boolean preceedingSpace) throws IOException {
/* 583 */     if (!this.prettyPrinter.getFormat()) {
/* 584 */       printCharacters(data);
/* 585 */       return false;
/*     */     } 
/*     */     
/* 588 */     this.canIndent = true;
/* 589 */     if (isWhiteSpace(data)) {
/* 590 */       int nl = newlines(data);
/* 591 */       for (int i = 0; i < nl - 1; i++) {
/* 592 */         printNewline();
/*     */       }
/* 594 */       return true;
/*     */     } 
/*     */     
/* 597 */     if (this.xmlSpace.get(0) == Boolean.TRUE) {
/* 598 */       printCharacters(data);
/* 599 */       this.canIndent = false;
/* 600 */       return false;
/*     */     } 
/*     */     
/* 603 */     if (first) {
/* 604 */       printNewline();
/* 605 */       printString(this.margin.toString());
/*     */     } 
/* 607 */     return formatText(data, this.margin.toString(), preceedingSpace);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printCDATASection(char[] data) throws IOException {
/* 614 */     printString("<![CDATA[");
/* 615 */     printCharacters(data);
/* 616 */     printString("]]>");
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
/*     */   public void printNotation(char[] space1, char[] name, char[] space2, String externalId, char[] space3, char[] string1, char string1Delim, char[] space4, char[] string2, char string2Delim, char[] space5) throws IOException {
/* 632 */     this.writer.write("<!NOTATION");
/* 633 */     printSpaces(space1, false);
/* 634 */     this.writer.write(name);
/* 635 */     printSpaces(space2, false);
/* 636 */     this.writer.write(externalId);
/* 637 */     printSpaces(space3, false);
/*     */     
/* 639 */     this.writer.write(string1Delim);
/* 640 */     this.writer.write(string1);
/* 641 */     this.writer.write(string1Delim);
/*     */     
/* 643 */     if (space4 != null) {
/* 644 */       printSpaces(space4, false);
/*     */       
/* 646 */       if (string2 != null) {
/* 647 */         this.writer.write(string2Delim);
/* 648 */         this.writer.write(string2);
/* 649 */         this.writer.write(string2Delim);
/*     */       } 
/*     */     } 
/* 652 */     if (space5 != null) {
/* 653 */       printSpaces(space5, true);
/*     */     }
/* 655 */     this.writer.write(62);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printAttlistStart(char[] space, char[] name) throws IOException {
/* 662 */     this.writer.write("<!ATTLIST");
/* 663 */     printSpaces(space, false);
/* 664 */     this.writer.write(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printAttlistEnd(char[] space) throws IOException {
/* 671 */     if (space != null) {
/* 672 */       printSpaces(space, false);
/*     */     }
/* 674 */     this.writer.write(62);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printAttName(char[] space1, char[] name, char[] space2) throws IOException {
/* 682 */     printSpaces(space1, false);
/* 683 */     this.writer.write(name);
/* 684 */     printSpaces(space2, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printEnumeration(List names) throws IOException {
/* 691 */     this.writer.write(40);
/*     */     
/* 693 */     Iterator<NameInfo> it = names.iterator();
/* 694 */     NameInfo ni = it.next();
/* 695 */     if (ni.space1 != null) {
/* 696 */       printSpaces(ni.space1, true);
/*     */     }
/*     */     
/* 699 */     this.writer.write(ni.name);
/*     */     
/* 701 */     if (ni.space2 != null) {
/* 702 */       printSpaces(ni.space2, true);
/*     */     }
/* 704 */     while (it.hasNext()) {
/* 705 */       this.writer.write(124);
/*     */       
/* 707 */       ni = it.next();
/* 708 */       if (ni.space1 != null) {
/* 709 */         printSpaces(ni.space1, true);
/*     */       }
/*     */       
/* 712 */       this.writer.write(ni.name);
/*     */       
/* 714 */       if (ni.space2 != null) {
/* 715 */         printSpaces(ni.space2, true);
/*     */       }
/*     */     } 
/*     */     
/* 719 */     this.writer.write(41);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int newlines(char[] text) {
/* 726 */     int result = 0;
/* 727 */     for (char aText : text) {
/* 728 */       if (aText == '\n') {
/* 729 */         result++;
/*     */       }
/*     */     } 
/* 732 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isWhiteSpace(char[] text) {
/* 739 */     for (char aText : text) {
/* 740 */       if (!XMLUtilities.isXMLSpace(aText)) {
/* 741 */         return false;
/*     */       }
/*     */     } 
/* 744 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean formatText(char[] text, String margin, boolean preceedingSpace) throws IOException {
/* 752 */     int i = 0;
/* 753 */     boolean startsWithSpace = preceedingSpace;
/* 754 */     label34: while (i < text.length) {
/*     */       
/* 756 */       while (i < text.length) {
/*     */ 
/*     */         
/* 759 */         if (!XMLUtilities.isXMLSpace(text[i]))
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */           
/* 765 */           StringBuffer sb = new StringBuffer();
/*     */           
/* 767 */           while (i < text.length && !XMLUtilities.isXMLSpace(text[i]))
/*     */           {
/*     */             
/* 770 */             sb.append(text[i++]);
/*     */           }
/* 772 */           if (sb.length() == 0) {
/* 773 */             return startsWithSpace;
/*     */           }
/* 775 */           if (startsWithSpace)
/*     */           
/* 777 */           { int endCol = this.column + sb.length();
/* 778 */             if (endCol >= this.prettyPrinter.getDocumentWidth() - 1) { if (margin.length() + sb.length() < this.prettyPrinter.getDocumentWidth() - 1 || margin.length() < this.column) {
/*     */ 
/*     */ 
/*     */                 
/* 782 */                 printNewline();
/* 783 */                 printString(margin); continue label34;
/* 784 */               }  if (this.column > margin.length())
/*     */               {
/* 786 */                 printCharacter(' ');
/*     */               }
/*     */               
/* 789 */               printString(sb.toString());
/* 790 */               startsWithSpace = false; continue; }  continue label34; }  continue label34; }  startsWithSpace = true; i++;
/*     */       } 
/* 792 */     }  return startsWithSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class NameInfo
/*     */   {
/*     */     public char[] space1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public char[] name;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public char[] space2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public NameInfo(char[] sp1, char[] nm, char[] sp2) {
/* 819 */       this.space1 = sp1;
/* 820 */       this.name = nm;
/* 821 */       this.space2 = sp2;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class AttributeInfo
/*     */   {
/*     */     public char[] space;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public char[] name;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public char[] space1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public char[] space2;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String value;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public char delimiter;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean entityReferences;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AttributeInfo(char[] sp, char[] n, char[] sp1, char[] sp2, String val, char delim, boolean entity) {
/* 870 */       this.space = sp;
/* 871 */       this.name = n;
/* 872 */       this.space1 = sp1;
/* 873 */       this.space2 = sp2;
/* 874 */       this.value = val;
/* 875 */       this.delimiter = delim;
/* 876 */       this.entityReferences = entity;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isAttribute(String s) {
/* 884 */       if (this.name.length == s.length()) {
/* 885 */         for (int i = 0; i < this.name.length; i++) {
/* 886 */           if (this.name[i] != s.charAt(i)) {
/* 887 */             return false;
/*     */           }
/*     */         } 
/* 890 */         return true;
/*     */       } 
/* 892 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/svg2svg/OutputManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */