/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathParser
/*     */   extends NumberParser
/*     */ {
/*  41 */   protected PathHandler pathHandler = DefaultPathHandler.INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPathHandler(PathHandler handler) {
/*  56 */     this.pathHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathHandler getPathHandler() {
/*  63 */     return this.pathHandler;
/*     */   }
/*     */   
/*     */   protected void doParse() throws ParseException, IOException {
/*  67 */     this.pathHandler.startPath();
/*     */     
/*  69 */     this.current = this.reader.read();
/*     */     while (true) {
/*     */       try {
/*  72 */         switch (this.current) {
/*     */           case 9:
/*     */           case 10:
/*     */           case 13:
/*     */           case 32:
/*  77 */             this.current = this.reader.read();
/*     */             continue;
/*     */           case 90:
/*     */           case 122:
/*  81 */             this.current = this.reader.read();
/*  82 */             this.pathHandler.closePath(); continue;
/*     */           case 109:
/*  84 */             parsem(); continue;
/*  85 */           case 77: parseM(); continue;
/*  86 */           case 108: parsel(); continue;
/*  87 */           case 76: parseL(); continue;
/*  88 */           case 104: parseh(); continue;
/*  89 */           case 72: parseH(); continue;
/*  90 */           case 118: parsev(); continue;
/*  91 */           case 86: parseV(); continue;
/*  92 */           case 99: parsec(); continue;
/*  93 */           case 67: parseC(); continue;
/*  94 */           case 113: parseq(); continue;
/*  95 */           case 81: parseQ(); continue;
/*  96 */           case 115: parses(); continue;
/*  97 */           case 83: parseS(); continue;
/*  98 */           case 116: parset(); continue;
/*  99 */           case 84: parseT(); continue;
/* 100 */           case 97: parsea(); continue;
/* 101 */           case 65: parseA(); continue;
/*     */           case -1:
/*     */             break;
/* 104 */         }  reportUnexpected(this.current);
/*     */       
/*     */       }
/* 107 */       catch (ParseException e) {
/* 108 */         this.errorHandler.error(e);
/* 109 */         skipSubPath();
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     skipSpaces();
/* 114 */     if (this.current != -1) {
/* 115 */       reportError("end.of.stream.expected", new Object[] { Integer.valueOf(this.current) });
/*     */     }
/*     */ 
/*     */     
/* 119 */     this.pathHandler.endPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parsem() throws ParseException, IOException {
/* 126 */     this.current = this.reader.read();
/* 127 */     skipSpaces();
/*     */     
/* 129 */     float x = parseFloat();
/* 130 */     skipCommaSpaces();
/* 131 */     float y = parseFloat();
/* 132 */     this.pathHandler.movetoRel(x, y);
/*     */     
/* 134 */     boolean expectNumber = skipCommaSpaces2();
/* 135 */     _parsel(expectNumber);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseM() throws ParseException, IOException {
/* 142 */     this.current = this.reader.read();
/* 143 */     skipSpaces();
/*     */     
/* 145 */     float x = parseFloat();
/* 146 */     skipCommaSpaces();
/* 147 */     float y = parseFloat();
/* 148 */     this.pathHandler.movetoAbs(x, y);
/*     */     
/* 150 */     boolean expectNumber = skipCommaSpaces2();
/* 151 */     _parseL(expectNumber);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parsel() throws ParseException, IOException {
/* 158 */     this.current = this.reader.read();
/* 159 */     skipSpaces();
/* 160 */     _parsel(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void _parsel(boolean expectNumber) throws ParseException, IOException {
/*     */     while (true) {
/* 166 */       switch (this.current)
/*     */       { default:
/* 168 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50: case 51: case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 175 */           break; }  float x = parseFloat();
/* 176 */       skipCommaSpaces();
/* 177 */       float y = parseFloat();
/*     */       
/* 179 */       this.pathHandler.linetoRel(x, y);
/* 180 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseL() throws ParseException, IOException {
/* 188 */     this.current = this.reader.read();
/* 189 */     skipSpaces();
/* 190 */     _parseL(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void _parseL(boolean expectNumber) throws ParseException, IOException {
/*     */     while (true) {
/* 196 */       switch (this.current)
/*     */       { default:
/* 198 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50: case 51: case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 205 */           break; }  float x = parseFloat();
/* 206 */       skipCommaSpaces();
/* 207 */       float y = parseFloat();
/*     */       
/* 209 */       this.pathHandler.linetoAbs(x, y);
/* 210 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseh() throws ParseException, IOException {
/* 218 */     this.current = this.reader.read();
/* 219 */     skipSpaces();
/* 220 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 223 */       switch (this.current)
/*     */       { default:
/* 225 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50: case 51: case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 232 */           break; }  float x = parseFloat();
/* 233 */       this.pathHandler.linetoHorizontalRel(x);
/* 234 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseH() throws ParseException, IOException {
/* 242 */     this.current = this.reader.read();
/* 243 */     skipSpaces();
/* 244 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 247 */       switch (this.current)
/*     */       { default:
/* 249 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50: case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 257 */           break; }  float x = parseFloat();
/* 258 */       this.pathHandler.linetoHorizontalAbs(x);
/* 259 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parsev() throws ParseException, IOException {
/* 267 */     this.current = this.reader.read();
/* 268 */     skipSpaces();
/* 269 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 272 */       switch (this.current)
/*     */       { default:
/* 274 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50: case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 282 */           break; }  float x = parseFloat();
/*     */       
/* 284 */       this.pathHandler.linetoVerticalRel(x);
/* 285 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseV() throws ParseException, IOException {
/* 293 */     this.current = this.reader.read();
/* 294 */     skipSpaces();
/* 295 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 298 */       switch (this.current)
/*     */       { default:
/* 300 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50: case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 308 */           break; }  float x = parseFloat();
/*     */       
/* 310 */       this.pathHandler.linetoVerticalAbs(x);
/* 311 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parsec() throws ParseException, IOException {
/* 319 */     this.current = this.reader.read();
/* 320 */     skipSpaces();
/* 321 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 324 */       switch (this.current)
/*     */       { default:
/* 326 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 335 */           break; }  float x1 = parseFloat();
/* 336 */       skipCommaSpaces();
/* 337 */       float y1 = parseFloat();
/* 338 */       skipCommaSpaces();
/* 339 */       float x2 = parseFloat();
/* 340 */       skipCommaSpaces();
/* 341 */       float y2 = parseFloat();
/* 342 */       skipCommaSpaces();
/* 343 */       float x = parseFloat();
/* 344 */       skipCommaSpaces();
/* 345 */       float y = parseFloat();
/*     */       
/* 347 */       this.pathHandler.curvetoCubicRel(x1, y1, x2, y2, x, y);
/* 348 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseC() throws ParseException, IOException {
/* 356 */     this.current = this.reader.read();
/* 357 */     skipSpaces();
/* 358 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 361 */       switch (this.current)
/*     */       { default:
/* 363 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 372 */           break; }  float x1 = parseFloat();
/* 373 */       skipCommaSpaces();
/* 374 */       float y1 = parseFloat();
/* 375 */       skipCommaSpaces();
/* 376 */       float x2 = parseFloat();
/* 377 */       skipCommaSpaces();
/* 378 */       float y2 = parseFloat();
/* 379 */       skipCommaSpaces();
/* 380 */       float x = parseFloat();
/* 381 */       skipCommaSpaces();
/* 382 */       float y = parseFloat();
/*     */       
/* 384 */       this.pathHandler.curvetoCubicAbs(x1, y1, x2, y2, x, y);
/* 385 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseq() throws ParseException, IOException {
/* 393 */     this.current = this.reader.read();
/* 394 */     skipSpaces();
/* 395 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 398 */       switch (this.current)
/*     */       { default:
/* 400 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 409 */           break; }  float x1 = parseFloat();
/* 410 */       skipCommaSpaces();
/* 411 */       float y1 = parseFloat();
/* 412 */       skipCommaSpaces();
/* 413 */       float x = parseFloat();
/* 414 */       skipCommaSpaces();
/* 415 */       float y = parseFloat();
/*     */       
/* 417 */       this.pathHandler.curvetoQuadraticRel(x1, y1, x, y);
/* 418 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseQ() throws ParseException, IOException {
/* 426 */     this.current = this.reader.read();
/* 427 */     skipSpaces();
/* 428 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 431 */       switch (this.current)
/*     */       { default:
/* 433 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 442 */           break; }  float x1 = parseFloat();
/* 443 */       skipCommaSpaces();
/* 444 */       float y1 = parseFloat();
/* 445 */       skipCommaSpaces();
/* 446 */       float x = parseFloat();
/* 447 */       skipCommaSpaces();
/* 448 */       float y = parseFloat();
/*     */       
/* 450 */       this.pathHandler.curvetoQuadraticAbs(x1, y1, x, y);
/* 451 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parses() throws ParseException, IOException {
/* 459 */     this.current = this.reader.read();
/* 460 */     skipSpaces();
/* 461 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 464 */       switch (this.current)
/*     */       { default:
/* 466 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 475 */           break; }  float x2 = parseFloat();
/* 476 */       skipCommaSpaces();
/* 477 */       float y2 = parseFloat();
/* 478 */       skipCommaSpaces();
/* 479 */       float x = parseFloat();
/* 480 */       skipCommaSpaces();
/* 481 */       float y = parseFloat();
/*     */       
/* 483 */       this.pathHandler.curvetoCubicSmoothRel(x2, y2, x, y);
/* 484 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseS() throws ParseException, IOException {
/* 492 */     this.current = this.reader.read();
/* 493 */     skipSpaces();
/* 494 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 497 */       switch (this.current)
/*     */       { default:
/* 499 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 508 */           break; }  float x2 = parseFloat();
/* 509 */       skipCommaSpaces();
/* 510 */       float y2 = parseFloat();
/* 511 */       skipCommaSpaces();
/* 512 */       float x = parseFloat();
/* 513 */       skipCommaSpaces();
/* 514 */       float y = parseFloat();
/*     */       
/* 516 */       this.pathHandler.curvetoCubicSmoothAbs(x2, y2, x, y);
/* 517 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parset() throws ParseException, IOException {
/* 525 */     this.current = this.reader.read();
/* 526 */     skipSpaces();
/* 527 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 530 */       switch (this.current)
/*     */       { default:
/* 532 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 541 */           break; }  float x = parseFloat();
/* 542 */       skipCommaSpaces();
/* 543 */       float y = parseFloat();
/*     */       
/* 545 */       this.pathHandler.curvetoQuadraticSmoothRel(x, y);
/* 546 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseT() throws ParseException, IOException {
/* 554 */     this.current = this.reader.read();
/* 555 */     skipSpaces();
/* 556 */     boolean expectNumber = true;
/*     */     
/*     */     while (true) {
/* 559 */       switch (this.current)
/*     */       { default:
/* 561 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 570 */           break; }  float x = parseFloat();
/* 571 */       skipCommaSpaces();
/* 572 */       float y = parseFloat();
/*     */       
/* 574 */       this.pathHandler.curvetoQuadraticSmoothAbs(x, y);
/* 575 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parsea() throws ParseException, IOException {
/* 583 */     this.current = this.reader.read();
/* 584 */     skipSpaces();
/* 585 */     boolean expectNumber = true;
/*     */     while (true) {
/*     */       boolean laf, sf;
/* 588 */       switch (this.current)
/*     */       { default:
/* 590 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 599 */           break; }  float rx = parseFloat();
/* 600 */       skipCommaSpaces();
/* 601 */       float ry = parseFloat();
/* 602 */       skipCommaSpaces();
/* 603 */       float ax = parseFloat();
/* 604 */       skipCommaSpaces();
/*     */ 
/*     */       
/* 607 */       switch (this.current) { default:
/* 608 */           reportUnexpected(this.current); return;
/* 609 */         case 48: laf = false; break;
/* 610 */         case 49: laf = true;
/*     */           break; }
/*     */       
/* 613 */       this.current = this.reader.read();
/* 614 */       skipCommaSpaces();
/*     */ 
/*     */       
/* 617 */       switch (this.current) { default:
/* 618 */           reportUnexpected(this.current); return;
/* 619 */         case 48: sf = false; break;
/* 620 */         case 49: sf = true;
/*     */           break; }
/*     */       
/* 623 */       this.current = this.reader.read();
/* 624 */       skipCommaSpaces();
/*     */       
/* 626 */       float x = parseFloat();
/* 627 */       skipCommaSpaces();
/* 628 */       float y = parseFloat();
/*     */       
/* 630 */       this.pathHandler.arcRel(rx, ry, ax, laf, sf, x, y);
/* 631 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseA() throws ParseException, IOException {
/* 639 */     this.current = this.reader.read();
/* 640 */     skipSpaces();
/* 641 */     boolean expectNumber = true;
/*     */     while (true) {
/*     */       boolean laf, sf;
/* 644 */       switch (this.current)
/*     */       { default:
/* 646 */           if (expectNumber) reportUnexpected(this.current);  return;
/*     */         case 43: case 45: case 46: case 48: case 49: case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 655 */           break; }  float rx = parseFloat();
/* 656 */       skipCommaSpaces();
/* 657 */       float ry = parseFloat();
/* 658 */       skipCommaSpaces();
/* 659 */       float ax = parseFloat();
/* 660 */       skipCommaSpaces();
/*     */ 
/*     */       
/* 663 */       switch (this.current) { default:
/* 664 */           reportUnexpected(this.current); return;
/* 665 */         case 48: laf = false; break;
/* 666 */         case 49: laf = true;
/*     */           break; }
/*     */       
/* 669 */       this.current = this.reader.read();
/* 670 */       skipCommaSpaces();
/*     */ 
/*     */       
/* 673 */       switch (this.current) { default:
/* 674 */           reportUnexpected(this.current); return;
/* 675 */         case 48: sf = false; break;
/* 676 */         case 49: sf = true;
/*     */           break; }
/*     */       
/* 679 */       this.current = this.reader.read();
/* 680 */       skipCommaSpaces();
/* 681 */       float x = parseFloat();
/* 682 */       skipCommaSpaces();
/* 683 */       float y = parseFloat();
/*     */       
/* 685 */       this.pathHandler.arcAbs(rx, ry, ax, laf, sf, x, y);
/* 686 */       expectNumber = skipCommaSpaces2();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void skipSubPath() throws ParseException, IOException {
/*     */     while (true) {
/* 695 */       switch (this.current) { case -1:
/*     */         case 77:
/*     */         case 109:
/*     */           return; }
/* 699 */        this.current = this.reader.read();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void reportUnexpected(int ch) throws ParseException, IOException {
/* 705 */     reportUnexpectedCharacterError(this.current);
/* 706 */     skipSubPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean skipCommaSpaces2() throws IOException {
/*     */     while (true) {
/* 715 */       switch (this.current) { default: break;
/*     */         case 9: case 10:
/*     */         case 13:
/*     */         case 32:
/* 719 */           break; }  this.current = this.reader.read();
/*     */     } 
/*     */     
/* 722 */     if (this.current != 44) {
/* 723 */       return false;
/*     */     }
/*     */     while (true) {
/* 726 */       switch (this.current = this.reader.read()) { case 9:
/*     */         case 10:
/*     */         case 13:
/*     */         case 32:
/*     */           break; } 
/* 731 */     }  return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/PathParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */