/*      */ package org.apache.batik.parser;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import org.apache.batik.xml.XMLUtilities;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FragmentIdentifierParser
/*      */   extends NumberParser
/*      */ {
/*   37 */   protected char[] buffer = new char[16];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int bufferSize;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected FragmentIdentifierHandler fragmentIdentifierHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FragmentIdentifierParser() {
/*   53 */     this.fragmentIdentifierHandler = DefaultFragmentIdentifierHandler.INSTANCE;
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
/*      */   public void setFragmentIdentifierHandler(FragmentIdentifierHandler handler) {
/*   70 */     this.fragmentIdentifierHandler = handler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FragmentIdentifierHandler getFragmentIdentifierHandler() {
/*   77 */     return this.fragmentIdentifierHandler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void doParse() throws ParseException, IOException {
/*   84 */     this.bufferSize = 0;
/*      */     
/*   86 */     this.current = this.reader.read();
/*      */     
/*   88 */     this.fragmentIdentifierHandler.startFragmentIdentifier();
/*      */ 
/*      */     
/*   91 */     String id = null;
/*      */     
/*   93 */     switch (this.current) {
/*      */       case 120:
/*   95 */         bufferize();
/*   96 */         this.current = this.reader.read();
/*   97 */         if (this.current != 112) {
/*   98 */           parseIdentifier();
/*      */         } else {
/*      */           
/*  101 */           bufferize();
/*  102 */           this.current = this.reader.read();
/*  103 */           if (this.current != 111) {
/*  104 */             parseIdentifier();
/*      */           } else {
/*      */             
/*  107 */             bufferize();
/*  108 */             this.current = this.reader.read();
/*  109 */             if (this.current != 105) {
/*  110 */               parseIdentifier();
/*      */             } else {
/*      */               
/*  113 */               bufferize();
/*  114 */               this.current = this.reader.read();
/*  115 */               if (this.current != 110) {
/*  116 */                 parseIdentifier();
/*      */               } else {
/*      */                 
/*  119 */                 bufferize();
/*  120 */                 this.current = this.reader.read();
/*  121 */                 if (this.current != 116) {
/*  122 */                   parseIdentifier();
/*      */                 } else {
/*      */                   
/*  125 */                   bufferize();
/*  126 */                   this.current = this.reader.read();
/*  127 */                   if (this.current != 101) {
/*  128 */                     parseIdentifier();
/*      */                   } else {
/*      */                     
/*  131 */                     bufferize();
/*  132 */                     this.current = this.reader.read();
/*  133 */                     if (this.current != 114) {
/*  134 */                       parseIdentifier();
/*      */                     } else {
/*      */                       
/*  137 */                       bufferize();
/*  138 */                       this.current = this.reader.read();
/*  139 */                       if (this.current != 40) {
/*  140 */                         parseIdentifier();
/*      */                       } else {
/*      */                         
/*  143 */                         this.bufferSize = 0;
/*  144 */                         this.current = this.reader.read();
/*  145 */                         if (this.current != 105) {
/*  146 */                           reportCharacterExpectedError('i', this.current);
/*      */                           break;
/*      */                         } 
/*  149 */                         this.current = this.reader.read();
/*  150 */                         if (this.current != 100) {
/*  151 */                           reportCharacterExpectedError('d', this.current);
/*      */                           break;
/*      */                         } 
/*  154 */                         this.current = this.reader.read();
/*  155 */                         if (this.current != 40) {
/*  156 */                           reportCharacterExpectedError('(', this.current);
/*      */                           break;
/*      */                         } 
/*  159 */                         this.current = this.reader.read();
/*  160 */                         if (this.current != 34 && this.current != 39) {
/*  161 */                           reportCharacterExpectedError('\'', this.current);
/*      */                           break;
/*      */                         } 
/*  164 */                         char q = (char)this.current;
/*  165 */                         this.current = this.reader.read();
/*  166 */                         parseIdentifier();
/*      */                         
/*  168 */                         id = getBufferContent();
/*  169 */                         this.bufferSize = 0;
/*  170 */                         this.fragmentIdentifierHandler.idReference(id);
/*      */                         
/*  172 */                         if (this.current != q) {
/*  173 */                           reportCharacterExpectedError(q, this.current);
/*      */                           break;
/*      */                         } 
/*  176 */                         this.current = this.reader.read();
/*  177 */                         if (this.current != 41) {
/*  178 */                           reportCharacterExpectedError(')', this.current);
/*      */                           break;
/*      */                         } 
/*  181 */                         this.current = this.reader.read();
/*  182 */                         if (this.current != 41) {
/*  183 */                           reportCharacterExpectedError(')', this.current);
/*      */                         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                         
/*      */                         break;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  248 */         id = getBufferContent();
/*  249 */         this.fragmentIdentifierHandler.idReference(id); break;case 115: bufferize(); this.current = this.reader.read(); if (this.current != 118) { parseIdentifier(); } else { bufferize(); this.current = this.reader.read(); if (this.current != 103) { parseIdentifier(); } else { bufferize(); this.current = this.reader.read(); if (this.current != 86) { parseIdentifier(); } else { bufferize(); this.current = this.reader.read(); if (this.current != 105) { parseIdentifier(); } else { bufferize(); this.current = this.reader.read(); if (this.current != 101) { parseIdentifier(); } else { bufferize(); this.current = this.reader.read(); if (this.current != 119) { parseIdentifier(); } else { bufferize(); this.current = this.reader.read(); if (this.current != 40) { parseIdentifier(); } else { this.bufferSize = 0; this.current = this.reader.read(); parseViewAttributes(); if (this.current != 41) reportCharacterExpectedError(')', this.current);  break; }  }  }  }  }  }  }  id = getBufferContent(); this.fragmentIdentifierHandler.idReference(id); break;default: if (this.current == -1 || !XMLUtilities.isXMLNameFirstCharacter((char)this.current)) break;  bufferize(); this.current = this.reader.read(); parseIdentifier(); id = getBufferContent(); this.fragmentIdentifierHandler.idReference(id);
/*      */         break;
/*      */     } 
/*  252 */     this.fragmentIdentifierHandler.endFragmentIdentifier();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseViewAttributes() throws ParseException, IOException {
/*  259 */     boolean first = true; while (true) {
/*      */       float x; float y; float w; float h;
/*  261 */       switch (this.current) {
/*      */         case -1:
/*      */         case 41:
/*  264 */           if (first) {
/*  265 */             reportUnexpectedCharacterError(this.current);
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 59:
/*  272 */           if (first) {
/*  273 */             reportUnexpectedCharacterError(this.current);
/*      */             break;
/*      */           } 
/*  276 */           this.current = this.reader.read();
/*      */           break;
/*      */         case 118:
/*  279 */           first = false;
/*  280 */           this.current = this.reader.read();
/*  281 */           if (this.current != 105) {
/*  282 */             reportCharacterExpectedError('i', this.current);
/*      */             break;
/*      */           } 
/*  285 */           this.current = this.reader.read();
/*  286 */           if (this.current != 101) {
/*  287 */             reportCharacterExpectedError('e', this.current);
/*      */             break;
/*      */           } 
/*  290 */           this.current = this.reader.read();
/*  291 */           if (this.current != 119) {
/*  292 */             reportCharacterExpectedError('w', this.current);
/*      */             break;
/*      */           } 
/*  295 */           this.current = this.reader.read();
/*      */           
/*  297 */           switch (this.current) {
/*      */             case 66:
/*  299 */               this.current = this.reader.read();
/*  300 */               if (this.current != 111) {
/*  301 */                 reportCharacterExpectedError('o', this.current);
/*      */                 break;
/*      */               } 
/*  304 */               this.current = this.reader.read();
/*  305 */               if (this.current != 120) {
/*  306 */                 reportCharacterExpectedError('x', this.current);
/*      */                 break;
/*      */               } 
/*  309 */               this.current = this.reader.read();
/*  310 */               if (this.current != 40) {
/*  311 */                 reportCharacterExpectedError('(', this.current);
/*      */                 break;
/*      */               } 
/*  314 */               this.current = this.reader.read();
/*      */               
/*  316 */               x = parseFloat();
/*  317 */               if (this.current != 44) {
/*  318 */                 reportCharacterExpectedError(',', this.current);
/*      */                 break;
/*      */               } 
/*  321 */               this.current = this.reader.read();
/*      */               
/*  323 */               y = parseFloat();
/*  324 */               if (this.current != 44) {
/*  325 */                 reportCharacterExpectedError(',', this.current);
/*      */                 break;
/*      */               } 
/*  328 */               this.current = this.reader.read();
/*      */               
/*  330 */               w = parseFloat();
/*  331 */               if (this.current != 44) {
/*  332 */                 reportCharacterExpectedError(',', this.current);
/*      */                 break;
/*      */               } 
/*  335 */               this.current = this.reader.read();
/*      */               
/*  337 */               h = parseFloat();
/*  338 */               if (this.current != 41) {
/*  339 */                 reportCharacterExpectedError(')', this.current);
/*      */                 break;
/*      */               } 
/*  342 */               this.current = this.reader.read();
/*  343 */               this.fragmentIdentifierHandler.viewBox(x, y, w, h);
/*  344 */               if (this.current != 41 && this.current != 59) {
/*  345 */                 reportCharacterExpectedError(')', this.current);
/*      */                 break;
/*      */               } 
/*      */               break;
/*      */             
/*      */             case 84:
/*  351 */               this.current = this.reader.read();
/*  352 */               if (this.current != 97) {
/*  353 */                 reportCharacterExpectedError('a', this.current);
/*      */                 break;
/*      */               } 
/*  356 */               this.current = this.reader.read();
/*  357 */               if (this.current != 114) {
/*  358 */                 reportCharacterExpectedError('r', this.current);
/*      */                 break;
/*      */               } 
/*  361 */               this.current = this.reader.read();
/*  362 */               if (this.current != 103) {
/*  363 */                 reportCharacterExpectedError('g', this.current);
/*      */                 break;
/*      */               } 
/*  366 */               this.current = this.reader.read();
/*  367 */               if (this.current != 101) {
/*  368 */                 reportCharacterExpectedError('e', this.current);
/*      */                 break;
/*      */               } 
/*  371 */               this.current = this.reader.read();
/*  372 */               if (this.current != 116) {
/*  373 */                 reportCharacterExpectedError('t', this.current);
/*      */                 break;
/*      */               } 
/*  376 */               this.current = this.reader.read();
/*  377 */               if (this.current != 40) {
/*  378 */                 reportCharacterExpectedError('(', this.current);
/*      */                 break;
/*      */               } 
/*  381 */               this.current = this.reader.read();
/*      */               
/*  383 */               this.fragmentIdentifierHandler.startViewTarget();
/*      */               
/*      */               while (true) {
/*  386 */                 this.bufferSize = 0;
/*  387 */                 if (this.current == -1 || !XMLUtilities.isXMLNameFirstCharacter((char)this.current)) {
/*      */                   
/*  389 */                   reportUnexpectedCharacterError(this.current);
/*      */                   break;
/*      */                 } 
/*  392 */                 bufferize();
/*  393 */                 this.current = this.reader.read();
/*  394 */                 parseIdentifier();
/*  395 */                 String s = getBufferContent();
/*      */                 
/*  397 */                 this.fragmentIdentifierHandler.viewTarget(s);
/*      */                 
/*  399 */                 this.bufferSize = 0;
/*  400 */                 switch (this.current) {
/*      */                   case 41:
/*  402 */                     this.current = this.reader.read();
/*      */                     break;
/*      */                   case 44:
/*      */                   case 59:
/*  406 */                     this.current = this.reader.read();
/*      */                     continue;
/*      */                   default:
/*  409 */                     reportUnexpectedCharacterError(this.current);
/*      */                     break;
/*      */                 } 
/*      */ 
/*      */                 
/*  414 */                 this.fragmentIdentifierHandler.endViewTarget();
/*      */               } 
/*      */               break;
/*      */           } 
/*  418 */           reportUnexpectedCharacterError(this.current);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 112:
/*  423 */           first = false;
/*  424 */           this.current = this.reader.read();
/*  425 */           if (this.current != 114) {
/*  426 */             reportCharacterExpectedError('r', this.current);
/*      */             break;
/*      */           } 
/*  429 */           this.current = this.reader.read();
/*  430 */           if (this.current != 101) {
/*  431 */             reportCharacterExpectedError('e', this.current);
/*      */             break;
/*      */           } 
/*  434 */           this.current = this.reader.read();
/*  435 */           if (this.current != 115) {
/*  436 */             reportCharacterExpectedError('s', this.current);
/*      */             break;
/*      */           } 
/*  439 */           this.current = this.reader.read();
/*  440 */           if (this.current != 101) {
/*  441 */             reportCharacterExpectedError('e', this.current);
/*      */             break;
/*      */           } 
/*  444 */           this.current = this.reader.read();
/*  445 */           if (this.current != 114) {
/*  446 */             reportCharacterExpectedError('r', this.current);
/*      */             break;
/*      */           } 
/*  449 */           this.current = this.reader.read();
/*  450 */           if (this.current != 118) {
/*  451 */             reportCharacterExpectedError('v', this.current);
/*      */             break;
/*      */           } 
/*  454 */           this.current = this.reader.read();
/*  455 */           if (this.current != 101) {
/*  456 */             reportCharacterExpectedError('e', this.current);
/*      */             break;
/*      */           } 
/*  459 */           this.current = this.reader.read();
/*  460 */           if (this.current != 65) {
/*  461 */             reportCharacterExpectedError('A', this.current);
/*      */             break;
/*      */           } 
/*  464 */           this.current = this.reader.read();
/*  465 */           if (this.current != 115) {
/*  466 */             reportCharacterExpectedError('s', this.current);
/*      */             break;
/*      */           } 
/*  469 */           this.current = this.reader.read();
/*  470 */           if (this.current != 112) {
/*  471 */             reportCharacterExpectedError('p', this.current);
/*      */             break;
/*      */           } 
/*  474 */           this.current = this.reader.read();
/*  475 */           if (this.current != 101) {
/*  476 */             reportCharacterExpectedError('e', this.current);
/*      */             break;
/*      */           } 
/*  479 */           this.current = this.reader.read();
/*  480 */           if (this.current != 99) {
/*  481 */             reportCharacterExpectedError('c', this.current);
/*      */             break;
/*      */           } 
/*  484 */           this.current = this.reader.read();
/*  485 */           if (this.current != 116) {
/*  486 */             reportCharacterExpectedError('t', this.current);
/*      */             break;
/*      */           } 
/*  489 */           this.current = this.reader.read();
/*  490 */           if (this.current != 82) {
/*  491 */             reportCharacterExpectedError('R', this.current);
/*      */             break;
/*      */           } 
/*  494 */           this.current = this.reader.read();
/*  495 */           if (this.current != 97) {
/*  496 */             reportCharacterExpectedError('a', this.current);
/*      */             break;
/*      */           } 
/*  499 */           this.current = this.reader.read();
/*  500 */           if (this.current != 116) {
/*  501 */             reportCharacterExpectedError('t', this.current);
/*      */             break;
/*      */           } 
/*  504 */           this.current = this.reader.read();
/*  505 */           if (this.current != 105) {
/*  506 */             reportCharacterExpectedError('i', this.current);
/*      */             break;
/*      */           } 
/*  509 */           this.current = this.reader.read();
/*  510 */           if (this.current != 111) {
/*  511 */             reportCharacterExpectedError('o', this.current);
/*      */             break;
/*      */           } 
/*  514 */           this.current = this.reader.read();
/*  515 */           if (this.current != 40) {
/*  516 */             reportCharacterExpectedError('(', this.current);
/*      */             break;
/*      */           } 
/*  519 */           this.current = this.reader.read();
/*      */           
/*  521 */           parsePreserveAspectRatio();
/*      */           
/*  523 */           if (this.current != 41) {
/*  524 */             reportCharacterExpectedError(')', this.current);
/*      */             break;
/*      */           } 
/*  527 */           this.current = this.reader.read();
/*      */           break;
/*      */         
/*      */         case 116:
/*  531 */           first = false;
/*  532 */           this.current = this.reader.read();
/*  533 */           if (this.current != 114) {
/*  534 */             reportCharacterExpectedError('r', this.current);
/*      */             break;
/*      */           } 
/*  537 */           this.current = this.reader.read();
/*  538 */           if (this.current != 97) {
/*  539 */             reportCharacterExpectedError('a', this.current);
/*      */             break;
/*      */           } 
/*  542 */           this.current = this.reader.read();
/*  543 */           if (this.current != 110) {
/*  544 */             reportCharacterExpectedError('n', this.current);
/*      */             break;
/*      */           } 
/*  547 */           this.current = this.reader.read();
/*  548 */           if (this.current != 115) {
/*  549 */             reportCharacterExpectedError('s', this.current);
/*      */             break;
/*      */           } 
/*  552 */           this.current = this.reader.read();
/*  553 */           if (this.current != 102) {
/*  554 */             reportCharacterExpectedError('f', this.current);
/*      */             break;
/*      */           } 
/*  557 */           this.current = this.reader.read();
/*  558 */           if (this.current != 111) {
/*  559 */             reportCharacterExpectedError('o', this.current);
/*      */             break;
/*      */           } 
/*  562 */           this.current = this.reader.read();
/*  563 */           if (this.current != 114) {
/*  564 */             reportCharacterExpectedError('r', this.current);
/*      */             break;
/*      */           } 
/*  567 */           this.current = this.reader.read();
/*  568 */           if (this.current != 109) {
/*  569 */             reportCharacterExpectedError('m', this.current);
/*      */             break;
/*      */           } 
/*  572 */           this.current = this.reader.read();
/*  573 */           if (this.current != 40) {
/*  574 */             reportCharacterExpectedError('(', this.current);
/*      */             
/*      */             break;
/*      */           } 
/*  578 */           this.fragmentIdentifierHandler.startTransformList();
/*      */           
/*      */           while (true) {
/*      */             try {
/*  582 */               this.current = this.reader.read();
/*  583 */               switch (this.current) {
/*      */                 case 44:
/*      */                   continue;
/*      */                 case 109:
/*  587 */                   parseMatrix();
/*      */                   continue;
/*      */                 case 114:
/*  590 */                   parseRotate();
/*      */                   continue;
/*      */                 case 116:
/*  593 */                   parseTranslate();
/*      */                   continue;
/*      */                 case 115:
/*  596 */                   this.current = this.reader.read();
/*  597 */                   switch (this.current) {
/*      */                     case 99:
/*  599 */                       parseScale();
/*      */                       continue;
/*      */                     case 107:
/*  602 */                       parseSkew();
/*      */                       continue;
/*      */                   } 
/*  605 */                   reportUnexpectedCharacterError(this.current);
/*  606 */                   skipTransform();
/*      */                   continue;
/*      */               } 
/*      */ 
/*      */               
/*      */               break;
/*  612 */             } catch (ParseException e) {
/*  613 */               this.errorHandler.error(e);
/*  614 */               skipTransform();
/*      */             } 
/*      */           } 
/*      */           
/*  618 */           this.fragmentIdentifierHandler.endTransformList();
/*      */           break;
/*      */         
/*      */         case 122:
/*  622 */           first = false;
/*  623 */           this.current = this.reader.read();
/*  624 */           if (this.current != 111) {
/*  625 */             reportCharacterExpectedError('o', this.current);
/*      */             break;
/*      */           } 
/*  628 */           this.current = this.reader.read();
/*  629 */           if (this.current != 111) {
/*  630 */             reportCharacterExpectedError('o', this.current);
/*      */             break;
/*      */           } 
/*  633 */           this.current = this.reader.read();
/*  634 */           if (this.current != 109) {
/*  635 */             reportCharacterExpectedError('m', this.current);
/*      */             break;
/*      */           } 
/*  638 */           this.current = this.reader.read();
/*  639 */           if (this.current != 65) {
/*  640 */             reportCharacterExpectedError('A', this.current);
/*      */             break;
/*      */           } 
/*  643 */           this.current = this.reader.read();
/*  644 */           if (this.current != 110) {
/*  645 */             reportCharacterExpectedError('n', this.current);
/*      */             break;
/*      */           } 
/*  648 */           this.current = this.reader.read();
/*  649 */           if (this.current != 100) {
/*  650 */             reportCharacterExpectedError('d', this.current);
/*      */             break;
/*      */           } 
/*  653 */           this.current = this.reader.read();
/*  654 */           if (this.current != 80) {
/*  655 */             reportCharacterExpectedError('P', this.current);
/*      */             break;
/*      */           } 
/*  658 */           this.current = this.reader.read();
/*  659 */           if (this.current != 97) {
/*  660 */             reportCharacterExpectedError('a', this.current);
/*      */             break;
/*      */           } 
/*  663 */           this.current = this.reader.read();
/*  664 */           if (this.current != 110) {
/*  665 */             reportCharacterExpectedError('n', this.current);
/*      */             break;
/*      */           } 
/*  668 */           this.current = this.reader.read();
/*  669 */           if (this.current != 40) {
/*  670 */             reportCharacterExpectedError('(', this.current);
/*      */             break;
/*      */           } 
/*  673 */           this.current = this.reader.read();
/*      */           
/*  675 */           switch (this.current) {
/*      */             case 109:
/*  677 */               this.current = this.reader.read();
/*  678 */               if (this.current != 97) {
/*  679 */                 reportCharacterExpectedError('a', this.current);
/*      */                 break;
/*      */               } 
/*  682 */               this.current = this.reader.read();
/*  683 */               if (this.current != 103) {
/*  684 */                 reportCharacterExpectedError('g', this.current);
/*      */                 break;
/*      */               } 
/*  687 */               this.current = this.reader.read();
/*  688 */               if (this.current != 110) {
/*  689 */                 reportCharacterExpectedError('n', this.current);
/*      */                 break;
/*      */               } 
/*  692 */               this.current = this.reader.read();
/*  693 */               if (this.current != 105) {
/*  694 */                 reportCharacterExpectedError('i', this.current);
/*      */                 break;
/*      */               } 
/*  697 */               this.current = this.reader.read();
/*  698 */               if (this.current != 102) {
/*  699 */                 reportCharacterExpectedError('f', this.current);
/*      */                 break;
/*      */               } 
/*  702 */               this.current = this.reader.read();
/*  703 */               if (this.current != 121) {
/*  704 */                 reportCharacterExpectedError('y', this.current);
/*      */                 break;
/*      */               } 
/*  707 */               this.current = this.reader.read();
/*  708 */               this.fragmentIdentifierHandler.zoomAndPan(true);
/*      */               break;
/*      */             
/*      */             case 100:
/*  712 */               this.current = this.reader.read();
/*  713 */               if (this.current != 105) {
/*  714 */                 reportCharacterExpectedError('i', this.current);
/*      */                 break;
/*      */               } 
/*  717 */               this.current = this.reader.read();
/*  718 */               if (this.current != 115) {
/*  719 */                 reportCharacterExpectedError('s', this.current);
/*      */                 break;
/*      */               } 
/*  722 */               this.current = this.reader.read();
/*  723 */               if (this.current != 97) {
/*  724 */                 reportCharacterExpectedError('a', this.current);
/*      */                 break;
/*      */               } 
/*  727 */               this.current = this.reader.read();
/*  728 */               if (this.current != 98) {
/*  729 */                 reportCharacterExpectedError('b', this.current);
/*      */                 break;
/*      */               } 
/*  732 */               this.current = this.reader.read();
/*  733 */               if (this.current != 108) {
/*  734 */                 reportCharacterExpectedError('l', this.current);
/*      */                 break;
/*      */               } 
/*  737 */               this.current = this.reader.read();
/*  738 */               if (this.current != 101) {
/*  739 */                 reportCharacterExpectedError('e', this.current);
/*      */                 break;
/*      */               } 
/*  742 */               this.current = this.reader.read();
/*  743 */               this.fragmentIdentifierHandler.zoomAndPan(false);
/*      */               break;
/*      */             
/*      */             default:
/*  747 */               reportUnexpectedCharacterError(this.current);
/*      */               break;
/*      */           } 
/*      */           
/*  751 */           if (this.current != 41) {
/*  752 */             reportCharacterExpectedError(')', this.current);
/*      */             break;
/*      */           } 
/*  755 */           this.current = this.reader.read();
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseIdentifier() throws ParseException, IOException {
/*  765 */     while (this.current != -1 && XMLUtilities.isXMLNameCharacter((char)this.current)) {
/*      */ 
/*      */ 
/*      */       
/*  769 */       bufferize();
/*  770 */       this.current = this.reader.read();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getBufferContent() {
/*  778 */     return new String(this.buffer, 0, this.bufferSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void bufferize() {
/*  786 */     if (this.bufferSize >= this.buffer.length) {
/*  787 */       char[] t = new char[this.buffer.length * 2];
/*  788 */       System.arraycopy(this.buffer, 0, t, 0, this.bufferSize);
/*  789 */       this.buffer = t;
/*      */     } 
/*  791 */     this.buffer[this.bufferSize++] = (char)this.current;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void skipSpaces() throws IOException {
/*  798 */     if (this.current == 44) {
/*  799 */       this.current = this.reader.read();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void skipCommaSpaces() throws IOException {
/*  807 */     if (this.current == 44) {
/*  808 */       this.current = this.reader.read();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseMatrix() throws ParseException, IOException {
/*  816 */     this.current = this.reader.read();
/*      */ 
/*      */     
/*  819 */     if (this.current != 97) {
/*  820 */       reportCharacterExpectedError('a', this.current);
/*  821 */       skipTransform();
/*      */       return;
/*      */     } 
/*  824 */     this.current = this.reader.read();
/*  825 */     if (this.current != 116) {
/*  826 */       reportCharacterExpectedError('t', this.current);
/*  827 */       skipTransform();
/*      */       return;
/*      */     } 
/*  830 */     this.current = this.reader.read();
/*  831 */     if (this.current != 114) {
/*  832 */       reportCharacterExpectedError('r', this.current);
/*  833 */       skipTransform();
/*      */       return;
/*      */     } 
/*  836 */     this.current = this.reader.read();
/*  837 */     if (this.current != 105) {
/*  838 */       reportCharacterExpectedError('i', this.current);
/*  839 */       skipTransform();
/*      */       return;
/*      */     } 
/*  842 */     this.current = this.reader.read();
/*  843 */     if (this.current != 120) {
/*  844 */       reportCharacterExpectedError('x', this.current);
/*  845 */       skipTransform();
/*      */       return;
/*      */     } 
/*  848 */     this.current = this.reader.read();
/*  849 */     skipSpaces();
/*  850 */     if (this.current != 40) {
/*  851 */       reportCharacterExpectedError('(', this.current);
/*  852 */       skipTransform();
/*      */       return;
/*      */     } 
/*  855 */     this.current = this.reader.read();
/*  856 */     skipSpaces();
/*      */     
/*  858 */     float a = parseFloat();
/*  859 */     skipCommaSpaces();
/*  860 */     float b = parseFloat();
/*  861 */     skipCommaSpaces();
/*  862 */     float c = parseFloat();
/*  863 */     skipCommaSpaces();
/*  864 */     float d = parseFloat();
/*  865 */     skipCommaSpaces();
/*  866 */     float e = parseFloat();
/*  867 */     skipCommaSpaces();
/*  868 */     float f = parseFloat();
/*      */     
/*  870 */     skipSpaces();
/*  871 */     if (this.current != 41) {
/*  872 */       reportCharacterExpectedError(')', this.current);
/*  873 */       skipTransform();
/*      */       
/*      */       return;
/*      */     } 
/*  877 */     this.fragmentIdentifierHandler.matrix(a, b, c, d, e, f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseRotate() throws ParseException, IOException {
/*  884 */     this.current = this.reader.read();
/*      */ 
/*      */     
/*  887 */     if (this.current != 111) {
/*  888 */       reportCharacterExpectedError('o', this.current);
/*  889 */       skipTransform();
/*      */       return;
/*      */     } 
/*  892 */     this.current = this.reader.read();
/*  893 */     if (this.current != 116) {
/*  894 */       reportCharacterExpectedError('t', this.current);
/*  895 */       skipTransform();
/*      */       return;
/*      */     } 
/*  898 */     this.current = this.reader.read();
/*  899 */     if (this.current != 97) {
/*  900 */       reportCharacterExpectedError('a', this.current);
/*  901 */       skipTransform();
/*      */       return;
/*      */     } 
/*  904 */     this.current = this.reader.read();
/*  905 */     if (this.current != 116) {
/*  906 */       reportCharacterExpectedError('t', this.current);
/*  907 */       skipTransform();
/*      */       return;
/*      */     } 
/*  910 */     this.current = this.reader.read();
/*  911 */     if (this.current != 101) {
/*  912 */       reportCharacterExpectedError('e', this.current);
/*  913 */       skipTransform();
/*      */       return;
/*      */     } 
/*  916 */     this.current = this.reader.read();
/*  917 */     skipSpaces();
/*      */     
/*  919 */     if (this.current != 40) {
/*  920 */       reportCharacterExpectedError('(', this.current);
/*  921 */       skipTransform();
/*      */       return;
/*      */     } 
/*  924 */     this.current = this.reader.read();
/*  925 */     skipSpaces();
/*      */     
/*  927 */     float theta = parseFloat();
/*  928 */     skipSpaces();
/*      */     
/*  930 */     switch (this.current) {
/*      */       case 41:
/*  932 */         this.fragmentIdentifierHandler.rotate(theta);
/*      */         return;
/*      */       case 44:
/*  935 */         this.current = this.reader.read();
/*  936 */         skipSpaces();
/*      */         break;
/*      */     } 
/*  939 */     float cx = parseFloat();
/*  940 */     skipCommaSpaces();
/*  941 */     float cy = parseFloat();
/*      */     
/*  943 */     skipSpaces();
/*  944 */     if (this.current != 41) {
/*  945 */       reportCharacterExpectedError(')', this.current);
/*  946 */       skipTransform();
/*      */       
/*      */       return;
/*      */     } 
/*  950 */     this.fragmentIdentifierHandler.rotate(theta, cx, cy);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseTranslate() throws ParseException, IOException {
/*  958 */     this.current = this.reader.read();
/*      */ 
/*      */     
/*  961 */     if (this.current != 114) {
/*  962 */       reportCharacterExpectedError('r', this.current);
/*  963 */       skipTransform();
/*      */       return;
/*      */     } 
/*  966 */     this.current = this.reader.read();
/*  967 */     if (this.current != 97) {
/*  968 */       reportCharacterExpectedError('a', this.current);
/*  969 */       skipTransform();
/*      */       return;
/*      */     } 
/*  972 */     this.current = this.reader.read();
/*  973 */     if (this.current != 110) {
/*  974 */       reportCharacterExpectedError('n', this.current);
/*  975 */       skipTransform();
/*      */       return;
/*      */     } 
/*  978 */     this.current = this.reader.read();
/*  979 */     if (this.current != 115) {
/*  980 */       reportCharacterExpectedError('s', this.current);
/*  981 */       skipTransform();
/*      */       return;
/*      */     } 
/*  984 */     this.current = this.reader.read();
/*  985 */     if (this.current != 108) {
/*  986 */       reportCharacterExpectedError('l', this.current);
/*  987 */       skipTransform();
/*      */       return;
/*      */     } 
/*  990 */     this.current = this.reader.read();
/*  991 */     if (this.current != 97) {
/*  992 */       reportCharacterExpectedError('a', this.current);
/*  993 */       skipTransform();
/*      */       return;
/*      */     } 
/*  996 */     this.current = this.reader.read();
/*  997 */     if (this.current != 116) {
/*  998 */       reportCharacterExpectedError('t', this.current);
/*  999 */       skipTransform();
/*      */       return;
/*      */     } 
/* 1002 */     this.current = this.reader.read();
/* 1003 */     if (this.current != 101) {
/* 1004 */       reportCharacterExpectedError('e', this.current);
/* 1005 */       skipTransform();
/*      */       return;
/*      */     } 
/* 1008 */     this.current = this.reader.read();
/* 1009 */     skipSpaces();
/* 1010 */     if (this.current != 40) {
/* 1011 */       reportCharacterExpectedError('(', this.current);
/* 1012 */       skipTransform();
/*      */       return;
/*      */     } 
/* 1015 */     this.current = this.reader.read();
/* 1016 */     skipSpaces();
/*      */     
/* 1018 */     float tx = parseFloat();
/* 1019 */     skipSpaces();
/*      */     
/* 1021 */     switch (this.current) {
/*      */       case 41:
/* 1023 */         this.fragmentIdentifierHandler.translate(tx);
/*      */         return;
/*      */       case 44:
/* 1026 */         this.current = this.reader.read();
/* 1027 */         skipSpaces();
/*      */         break;
/*      */     } 
/* 1030 */     float ty = parseFloat();
/*      */     
/* 1032 */     skipSpaces();
/* 1033 */     if (this.current != 41) {
/* 1034 */       reportCharacterExpectedError(')', this.current);
/* 1035 */       skipTransform();
/*      */       
/*      */       return;
/*      */     } 
/* 1039 */     this.fragmentIdentifierHandler.translate(tx, ty);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseScale() throws ParseException, IOException {
/* 1046 */     this.current = this.reader.read();
/*      */ 
/*      */     
/* 1049 */     if (this.current != 97) {
/* 1050 */       reportCharacterExpectedError('a', this.current);
/* 1051 */       skipTransform();
/*      */       return;
/*      */     } 
/* 1054 */     this.current = this.reader.read();
/* 1055 */     if (this.current != 108) {
/* 1056 */       reportCharacterExpectedError('l', this.current);
/* 1057 */       skipTransform();
/*      */       return;
/*      */     } 
/* 1060 */     this.current = this.reader.read();
/* 1061 */     if (this.current != 101) {
/* 1062 */       reportCharacterExpectedError('e', this.current);
/* 1063 */       skipTransform();
/*      */       return;
/*      */     } 
/* 1066 */     this.current = this.reader.read();
/* 1067 */     skipSpaces();
/* 1068 */     if (this.current != 40) {
/* 1069 */       reportCharacterExpectedError('(', this.current);
/* 1070 */       skipTransform();
/*      */       return;
/*      */     } 
/* 1073 */     this.current = this.reader.read();
/* 1074 */     skipSpaces();
/*      */     
/* 1076 */     float sx = parseFloat();
/* 1077 */     skipSpaces();
/*      */     
/* 1079 */     switch (this.current) {
/*      */       case 41:
/* 1081 */         this.fragmentIdentifierHandler.scale(sx);
/*      */         return;
/*      */       case 44:
/* 1084 */         this.current = this.reader.read();
/* 1085 */         skipSpaces();
/*      */         break;
/*      */     } 
/* 1088 */     float sy = parseFloat();
/*      */     
/* 1090 */     skipSpaces();
/* 1091 */     if (this.current != 41) {
/* 1092 */       reportCharacterExpectedError(')', this.current);
/* 1093 */       skipTransform();
/*      */       
/*      */       return;
/*      */     } 
/* 1097 */     this.fragmentIdentifierHandler.scale(sx, sy);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseSkew() throws ParseException, IOException {
/* 1104 */     this.current = this.reader.read();
/*      */ 
/*      */     
/* 1107 */     if (this.current != 101) {
/* 1108 */       reportCharacterExpectedError('e', this.current);
/* 1109 */       skipTransform();
/*      */       return;
/*      */     } 
/* 1112 */     this.current = this.reader.read();
/* 1113 */     if (this.current != 119) {
/* 1114 */       reportCharacterExpectedError('w', this.current);
/* 1115 */       skipTransform();
/*      */       return;
/*      */     } 
/* 1118 */     this.current = this.reader.read();
/*      */     
/* 1120 */     boolean skewX = false;
/* 1121 */     switch (this.current) {
/*      */       case 88:
/* 1123 */         skewX = true;
/*      */         break;
/*      */       case 89:
/*      */         break;
/*      */       default:
/* 1128 */         reportCharacterExpectedError('X', this.current);
/* 1129 */         skipTransform();
/*      */         return;
/*      */     } 
/* 1132 */     this.current = this.reader.read();
/* 1133 */     skipSpaces();
/* 1134 */     if (this.current != 40) {
/* 1135 */       reportCharacterExpectedError('(', this.current);
/* 1136 */       skipTransform();
/*      */       return;
/*      */     } 
/* 1139 */     this.current = this.reader.read();
/* 1140 */     skipSpaces();
/*      */     
/* 1142 */     float sk = parseFloat();
/*      */     
/* 1144 */     skipSpaces();
/* 1145 */     if (this.current != 41) {
/* 1146 */       reportCharacterExpectedError(')', this.current);
/* 1147 */       skipTransform();
/*      */       
/*      */       return;
/*      */     } 
/* 1151 */     if (skewX) {
/* 1152 */       this.fragmentIdentifierHandler.skewX(sk);
/*      */     } else {
/* 1154 */       this.fragmentIdentifierHandler.skewY(sk);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void skipTransform() throws IOException {
/*      */     do {
/* 1163 */       this.current = this.reader.read();
/* 1164 */       switch (this.current) {
/*      */         case 41:
/*      */           break;
/*      */       } 
/* 1168 */     } while (this.current != -1);
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
/*      */   protected void parsePreserveAspectRatio() throws ParseException, IOException {
/* 1180 */     this.fragmentIdentifierHandler.startPreserveAspectRatio();
/*      */     
/* 1182 */     switch (this.current) {
/*      */       case 110:
/* 1184 */         this.current = this.reader.read();
/* 1185 */         if (this.current != 111) {
/* 1186 */           reportCharacterExpectedError('o', this.current);
/* 1187 */           skipIdentifier();
/*      */           break;
/*      */         } 
/* 1190 */         this.current = this.reader.read();
/* 1191 */         if (this.current != 110) {
/* 1192 */           reportCharacterExpectedError('n', this.current);
/* 1193 */           skipIdentifier();
/*      */           break;
/*      */         } 
/* 1196 */         this.current = this.reader.read();
/* 1197 */         if (this.current != 101) {
/* 1198 */           reportCharacterExpectedError('e', this.current);
/* 1199 */           skipIdentifier();
/*      */           break;
/*      */         } 
/* 1202 */         this.current = this.reader.read();
/* 1203 */         skipSpaces();
/* 1204 */         this.fragmentIdentifierHandler.none();
/*      */         break;
/*      */       
/*      */       case 120:
/* 1208 */         this.current = this.reader.read();
/* 1209 */         if (this.current != 77) {
/* 1210 */           reportCharacterExpectedError('M', this.current);
/* 1211 */           skipIdentifier();
/*      */           break;
/*      */         } 
/* 1214 */         this.current = this.reader.read();
/* 1215 */         switch (this.current) {
/*      */           case 97:
/* 1217 */             this.current = this.reader.read();
/* 1218 */             if (this.current != 120) {
/* 1219 */               reportCharacterExpectedError('x', this.current);
/* 1220 */               skipIdentifier();
/*      */               break;
/*      */             } 
/* 1223 */             this.current = this.reader.read();
/* 1224 */             if (this.current != 89) {
/* 1225 */               reportCharacterExpectedError('Y', this.current);
/* 1226 */               skipIdentifier();
/*      */               break;
/*      */             } 
/* 1229 */             this.current = this.reader.read();
/* 1230 */             if (this.current != 77) {
/* 1231 */               reportCharacterExpectedError('M', this.current);
/* 1232 */               skipIdentifier();
/*      */               break;
/*      */             } 
/* 1235 */             this.current = this.reader.read();
/* 1236 */             switch (this.current) {
/*      */               case 97:
/* 1238 */                 this.current = this.reader.read();
/* 1239 */                 if (this.current != 120) {
/* 1240 */                   reportCharacterExpectedError('x', this.current);
/* 1241 */                   skipIdentifier();
/*      */                   break;
/*      */                 } 
/* 1244 */                 this.fragmentIdentifierHandler.xMaxYMax();
/* 1245 */                 this.current = this.reader.read();
/*      */                 break;
/*      */               case 105:
/* 1248 */                 this.current = this.reader.read();
/* 1249 */                 switch (this.current) {
/*      */                   case 100:
/* 1251 */                     this.fragmentIdentifierHandler.xMaxYMid();
/* 1252 */                     this.current = this.reader.read();
/*      */                     break;
/*      */                   case 110:
/* 1255 */                     this.fragmentIdentifierHandler.xMaxYMin();
/* 1256 */                     this.current = this.reader.read();
/*      */                     break;
/*      */                 } 
/* 1259 */                 reportUnexpectedCharacterError(this.current);
/* 1260 */                 skipIdentifier();
/*      */                 break;
/*      */             } 
/*      */             
/*      */             break;
/*      */           case 105:
/* 1266 */             this.current = this.reader.read();
/* 1267 */             switch (this.current) {
/*      */               case 100:
/* 1269 */                 this.current = this.reader.read();
/* 1270 */                 if (this.current != 89) {
/* 1271 */                   reportCharacterExpectedError('Y', this.current);
/* 1272 */                   skipIdentifier();
/*      */                   break;
/*      */                 } 
/* 1275 */                 this.current = this.reader.read();
/* 1276 */                 if (this.current != 77) {
/* 1277 */                   reportCharacterExpectedError('M', this.current);
/* 1278 */                   skipIdentifier();
/*      */                   break;
/*      */                 } 
/* 1281 */                 this.current = this.reader.read();
/* 1282 */                 switch (this.current) {
/*      */                   case 97:
/* 1284 */                     this.current = this.reader.read();
/* 1285 */                     if (this.current != 120) {
/* 1286 */                       reportCharacterExpectedError('x', this.current);
/* 1287 */                       skipIdentifier();
/*      */                       break;
/*      */                     } 
/* 1290 */                     this.fragmentIdentifierHandler.xMidYMax();
/* 1291 */                     this.current = this.reader.read();
/*      */                     break;
/*      */                   case 105:
/* 1294 */                     this.current = this.reader.read();
/* 1295 */                     switch (this.current) {
/*      */                       case 100:
/* 1297 */                         this.fragmentIdentifierHandler.xMidYMid();
/* 1298 */                         this.current = this.reader.read();
/*      */                         break;
/*      */                       case 110:
/* 1301 */                         this.fragmentIdentifierHandler.xMidYMin();
/* 1302 */                         this.current = this.reader.read();
/*      */                         break;
/*      */                     } 
/* 1305 */                     reportUnexpectedCharacterError(this.current);
/* 1306 */                     skipIdentifier();
/*      */                     break;
/*      */                 } 
/*      */                 
/*      */                 break;
/*      */               case 110:
/* 1312 */                 this.current = this.reader.read();
/* 1313 */                 if (this.current != 89) {
/* 1314 */                   reportCharacterExpectedError('Y', this.current);
/* 1315 */                   skipIdentifier();
/*      */                   break;
/*      */                 } 
/* 1318 */                 this.current = this.reader.read();
/* 1319 */                 if (this.current != 77) {
/* 1320 */                   reportCharacterExpectedError('M', this.current);
/* 1321 */                   skipIdentifier();
/*      */                   break;
/*      */                 } 
/* 1324 */                 this.current = this.reader.read();
/* 1325 */                 switch (this.current) {
/*      */                   case 97:
/* 1327 */                     this.current = this.reader.read();
/* 1328 */                     if (this.current != 120) {
/* 1329 */                       reportCharacterExpectedError('x', this.current);
/* 1330 */                       skipIdentifier();
/*      */                       break;
/*      */                     } 
/* 1333 */                     this.fragmentIdentifierHandler.xMinYMax();
/* 1334 */                     this.current = this.reader.read();
/*      */                     break;
/*      */                   case 105:
/* 1337 */                     this.current = this.reader.read();
/* 1338 */                     switch (this.current) {
/*      */                       case 100:
/* 1340 */                         this.fragmentIdentifierHandler.xMinYMid();
/* 1341 */                         this.current = this.reader.read();
/*      */                         break;
/*      */                       case 110:
/* 1344 */                         this.fragmentIdentifierHandler.xMinYMin();
/* 1345 */                         this.current = this.reader.read();
/*      */                         break;
/*      */                     } 
/* 1348 */                     reportUnexpectedCharacterError(this.current);
/* 1349 */                     skipIdentifier();
/*      */                     break;
/*      */                 } 
/*      */                 
/*      */                 break;
/*      */             } 
/* 1355 */             reportUnexpectedCharacterError(this.current);
/* 1356 */             skipIdentifier();
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/* 1361 */         reportUnexpectedCharacterError(this.current);
/* 1362 */         skipIdentifier();
/*      */         break;
/*      */       
/*      */       default:
/* 1366 */         if (this.current != -1) {
/* 1367 */           reportUnexpectedCharacterError(this.current);
/* 1368 */           skipIdentifier();
/*      */         } 
/*      */         break;
/*      */     } 
/* 1372 */     skipCommaSpaces();
/*      */     
/* 1374 */     switch (this.current) {
/*      */       case 109:
/* 1376 */         this.current = this.reader.read();
/* 1377 */         if (this.current != 101) {
/* 1378 */           reportCharacterExpectedError('e', this.current);
/* 1379 */           skipIdentifier();
/*      */           break;
/*      */         } 
/* 1382 */         this.current = this.reader.read();
/* 1383 */         if (this.current != 101) {
/* 1384 */           reportCharacterExpectedError('e', this.current);
/* 1385 */           skipIdentifier();
/*      */           break;
/*      */         } 
/* 1388 */         this.current = this.reader.read();
/* 1389 */         if (this.current != 116) {
/* 1390 */           reportCharacterExpectedError('t', this.current);
/* 1391 */           skipIdentifier();
/*      */           break;
/*      */         } 
/* 1394 */         this.fragmentIdentifierHandler.meet();
/* 1395 */         this.current = this.reader.read();
/*      */         break;
/*      */       case 115:
/* 1398 */         this.current = this.reader.read();
/* 1399 */         if (this.current != 108) {
/* 1400 */           reportCharacterExpectedError('l', this.current);
/* 1401 */           skipIdentifier();
/*      */           break;
/*      */         } 
/* 1404 */         this.current = this.reader.read();
/* 1405 */         if (this.current != 105) {
/* 1406 */           reportCharacterExpectedError('i', this.current);
/* 1407 */           skipIdentifier();
/*      */           break;
/*      */         } 
/* 1410 */         this.current = this.reader.read();
/* 1411 */         if (this.current != 99) {
/* 1412 */           reportCharacterExpectedError('c', this.current);
/* 1413 */           skipIdentifier();
/*      */           break;
/*      */         } 
/* 1416 */         this.current = this.reader.read();
/* 1417 */         if (this.current != 101) {
/* 1418 */           reportCharacterExpectedError('e', this.current);
/* 1419 */           skipIdentifier();
/*      */           break;
/*      */         } 
/* 1422 */         this.fragmentIdentifierHandler.slice();
/* 1423 */         this.current = this.reader.read();
/*      */         break;
/*      */     } 
/* 1426 */     this.fragmentIdentifierHandler.endPreserveAspectRatio();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void skipIdentifier() throws IOException {
/*      */     while (true) {
/* 1434 */       this.current = this.reader.read();
/* 1435 */       switch (this.current) { case 9: case 10: case 13:
/*      */         case 32:
/* 1437 */           this.current = this.reader.read();
/*      */           return;
/*      */         case -1:
/*      */           break; }
/*      */     
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/FragmentIdentifierParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */