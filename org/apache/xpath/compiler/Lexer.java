/*     */ package org.apache.xpath.compiler;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Lexer
/*     */ {
/*     */   private Compiler m_compiler;
/*     */   PrefixResolver m_namespaceContext;
/*     */   XPathParser m_processor;
/*     */   static final int TARGETEXTRA = 10000;
/*  62 */   private int[] m_patternMap = new int[100];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int m_patternMapSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Lexer(Compiler compiler, PrefixResolver resolver, XPathParser xpathProcessor) {
/*  82 */     this.m_compiler = compiler;
/*  83 */     this.m_namespaceContext = resolver;
/*  84 */     this.m_processor = xpathProcessor;
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
/*     */   void tokenize(String pat) throws TransformerException {
/*  96 */     tokenize(pat, null);
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
/*     */   void tokenize(String pat, Vector targetStrings) throws TransformerException {
/* 111 */     this.m_compiler.m_currentPattern = pat;
/* 112 */     this.m_patternMapSize = 0;
/*     */ 
/*     */     
/* 115 */     this.m_compiler.m_opMap = new OpMapVector(2500, 2500, 1);
/*     */     
/* 117 */     int nChars = pat.length();
/* 118 */     int startSubstring = -1;
/* 119 */     int posOfNSSep = -1;
/* 120 */     boolean isStartOfPat = true;
/* 121 */     boolean isAttrName = false;
/* 122 */     boolean isNum = false;
/*     */ 
/*     */ 
/*     */     
/* 126 */     int nesting = 0;
/*     */ 
/*     */     
/* 129 */     for (int i = 0; i < nChars; i++) {
/*     */       
/* 131 */       char c = pat.charAt(i);
/*     */       
/* 133 */       switch (c) {
/*     */ 
/*     */         
/*     */         case '"':
/* 137 */           if (startSubstring != -1) {
/*     */             
/* 139 */             isNum = false;
/* 140 */             isStartOfPat = mapPatternElemPos(nesting, isStartOfPat, isAttrName);
/* 141 */             isAttrName = false;
/*     */             
/* 143 */             if (-1 != posOfNSSep) {
/*     */               
/* 145 */               posOfNSSep = mapNSTokens(pat, startSubstring, posOfNSSep, i);
/*     */             }
/*     */             else {
/*     */               
/* 149 */               addToTokenQueue(pat.substring(startSubstring, i));
/*     */             } 
/*     */           } 
/*     */           
/* 153 */           startSubstring = i;
/*     */           
/* 155 */           for (; ++i < nChars && (c = pat.charAt(i)) != '"'; i++);
/*     */           
/* 157 */           if (c == '"' && i < nChars) {
/*     */             
/* 159 */             addToTokenQueue(pat.substring(startSubstring, i + 1));
/*     */             
/* 161 */             startSubstring = -1;
/*     */             
/*     */             break;
/*     */           } 
/* 165 */           this.m_processor.error("ER_EXPECTED_DOUBLE_QUOTE", null);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case '\'':
/* 171 */           if (startSubstring != -1) {
/*     */             
/* 173 */             isNum = false;
/* 174 */             isStartOfPat = mapPatternElemPos(nesting, isStartOfPat, isAttrName);
/* 175 */             isAttrName = false;
/*     */             
/* 177 */             if (-1 != posOfNSSep) {
/*     */               
/* 179 */               posOfNSSep = mapNSTokens(pat, startSubstring, posOfNSSep, i);
/*     */             }
/*     */             else {
/*     */               
/* 183 */               addToTokenQueue(pat.substring(startSubstring, i));
/*     */             } 
/*     */           } 
/*     */           
/* 187 */           startSubstring = i;
/*     */           
/* 189 */           for (; ++i < nChars && (c = pat.charAt(i)) != '\''; i++);
/*     */           
/* 191 */           if (c == '\'' && i < nChars) {
/*     */             
/* 193 */             addToTokenQueue(pat.substring(startSubstring, i + 1));
/*     */             
/* 195 */             startSubstring = -1;
/*     */             
/*     */             break;
/*     */           } 
/* 199 */           this.m_processor.error("ER_EXPECTED_SINGLE_QUOTE", null);
/*     */           break;
/*     */ 
/*     */         
/*     */         case '\t':
/*     */         case '\n':
/*     */         case '\r':
/*     */         case ' ':
/* 207 */           if (startSubstring != -1) {
/*     */             
/* 209 */             isNum = false;
/* 210 */             isStartOfPat = mapPatternElemPos(nesting, isStartOfPat, isAttrName);
/* 211 */             isAttrName = false;
/*     */             
/* 213 */             if (-1 != posOfNSSep) {
/*     */               
/* 215 */               posOfNSSep = mapNSTokens(pat, startSubstring, posOfNSSep, i);
/*     */             }
/*     */             else {
/*     */               
/* 219 */               addToTokenQueue(pat.substring(startSubstring, i));
/*     */             } 
/*     */             
/* 222 */             startSubstring = -1;
/*     */           } 
/*     */           break;
/*     */         case '@':
/* 226 */           isAttrName = true;
/*     */ 
/*     */         
/*     */         case '-':
/* 230 */           if ('-' == c) {
/*     */             
/* 232 */             if (!isNum && startSubstring != -1) {
/*     */               break;
/*     */             }
/*     */ 
/*     */             
/* 237 */             isNum = false;
/*     */           } 
/*     */ 
/*     */         
/*     */         case '!':
/*     */         case '$':
/*     */         case '(':
/*     */         case ')':
/*     */         case '*':
/*     */         case '+':
/*     */         case ',':
/*     */         case '/':
/*     */         case '<':
/*     */         case '=':
/*     */         case '>':
/*     */         case '[':
/*     */         case '\\':
/*     */         case ']':
/*     */         case '^':
/*     */         case '|':
/* 257 */           if (startSubstring != -1) {
/*     */             
/* 259 */             isNum = false;
/* 260 */             isStartOfPat = mapPatternElemPos(nesting, isStartOfPat, isAttrName);
/* 261 */             isAttrName = false;
/*     */             
/* 263 */             if (-1 != posOfNSSep) {
/*     */               
/* 265 */               posOfNSSep = mapNSTokens(pat, startSubstring, posOfNSSep, i);
/*     */             }
/*     */             else {
/*     */               
/* 269 */               addToTokenQueue(pat.substring(startSubstring, i));
/*     */             } 
/*     */             
/* 272 */             startSubstring = -1;
/*     */           }
/* 274 */           else if ('/' == c && isStartOfPat) {
/*     */             
/* 276 */             isStartOfPat = mapPatternElemPos(nesting, isStartOfPat, isAttrName);
/*     */           }
/* 278 */           else if ('*' == c) {
/*     */             
/* 280 */             isStartOfPat = mapPatternElemPos(nesting, isStartOfPat, isAttrName);
/* 281 */             isAttrName = false;
/*     */           } 
/*     */           
/* 284 */           if (0 == nesting)
/*     */           {
/* 286 */             if ('|' == c) {
/*     */               
/* 288 */               if (null != targetStrings)
/*     */               {
/* 290 */                 recordTokenString(targetStrings);
/*     */               }
/*     */               
/* 293 */               isStartOfPat = true;
/*     */             } 
/*     */           }
/*     */           
/* 297 */           if (')' == c || ']' == c) {
/*     */             
/* 299 */             nesting--;
/*     */           }
/* 301 */           else if ('(' == c || '[' == c) {
/*     */             
/* 303 */             nesting++;
/*     */           } 
/*     */           
/* 306 */           addToTokenQueue(pat.substring(i, i + 1));
/*     */           break;
/*     */         case ':':
/* 309 */           if (i > 0) {
/*     */             
/* 311 */             if (posOfNSSep == i - 1) {
/*     */               
/* 313 */               if (startSubstring != -1)
/*     */               {
/* 315 */                 if (startSubstring < i - 1) {
/* 316 */                   addToTokenQueue(pat.substring(startSubstring, i - 1));
/*     */                 }
/*     */               }
/* 319 */               isNum = false;
/* 320 */               isAttrName = false;
/* 321 */               startSubstring = -1;
/* 322 */               posOfNSSep = -1;
/*     */               
/* 324 */               addToTokenQueue(pat.substring(i - 1, i + 1));
/*     */ 
/*     */               
/*     */               break;
/*     */             } 
/*     */             
/* 330 */             posOfNSSep = i;
/*     */           } 
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 336 */           if (-1 == startSubstring) {
/*     */             
/* 338 */             startSubstring = i;
/* 339 */             isNum = Character.isDigit(c); break;
/*     */           } 
/* 341 */           if (isNum)
/*     */           {
/* 343 */             isNum = Character.isDigit(c);
/*     */           }
/*     */           break;
/*     */       } 
/*     */     } 
/* 348 */     if (startSubstring != -1) {
/*     */       
/* 350 */       isNum = false;
/* 351 */       isStartOfPat = mapPatternElemPos(nesting, isStartOfPat, isAttrName);
/*     */       
/* 353 */       if (-1 != posOfNSSep || (this.m_namespaceContext != null && this.m_namespaceContext.handlesNullPrefixes())) {
/*     */ 
/*     */         
/* 356 */         posOfNSSep = mapNSTokens(pat, startSubstring, posOfNSSep, nChars);
/*     */       }
/*     */       else {
/*     */         
/* 360 */         addToTokenQueue(pat.substring(startSubstring, nChars));
/*     */       } 
/*     */     } 
/*     */     
/* 364 */     if (0 == this.m_compiler.getTokenQueueSize()) {
/*     */       
/* 366 */       this.m_processor.error("ER_EMPTY_EXPRESSION", null);
/*     */     }
/* 368 */     else if (null != targetStrings) {
/*     */       
/* 370 */       recordTokenString(targetStrings);
/*     */     } 
/*     */     
/* 373 */     this.m_processor.m_queueMark = 0;
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
/*     */   private boolean mapPatternElemPos(int nesting, boolean isStart, boolean isAttrName) {
/* 391 */     if (0 == nesting) {
/*     */       
/* 393 */       if (this.m_patternMapSize >= this.m_patternMap.length) {
/*     */         
/* 395 */         int[] patternMap = this.m_patternMap;
/* 396 */         int len = this.m_patternMap.length;
/* 397 */         this.m_patternMap = new int[this.m_patternMapSize + 100];
/* 398 */         System.arraycopy(patternMap, 0, this.m_patternMap, 0, len);
/*     */       } 
/* 400 */       if (!isStart)
/*     */       {
/* 402 */         this.m_patternMap[this.m_patternMapSize - 1] = this.m_patternMap[this.m_patternMapSize - 1] - 10000;
/*     */       }
/* 404 */       this.m_patternMap[this.m_patternMapSize] = this.m_compiler.getTokenQueueSize() - (isAttrName ? 1 : 0) + 10000;
/*     */ 
/*     */       
/* 407 */       this.m_patternMapSize++;
/*     */       
/* 409 */       isStart = false;
/*     */     } 
/*     */     
/* 412 */     return isStart;
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
/*     */   private int getTokenQueuePosFromMap(int i) {
/* 425 */     int pos = this.m_patternMap[i];
/*     */     
/* 427 */     return (pos >= 10000) ? (pos - 10000) : pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void resetTokenMark(int mark) {
/* 438 */     int qsz = this.m_compiler.getTokenQueueSize();
/*     */     
/* 440 */     this.m_processor.m_queueMark = (mark > 0) ? ((mark <= qsz) ? (mark - 1) : mark) : 0;
/*     */ 
/*     */     
/* 443 */     if (this.m_processor.m_queueMark < qsz) {
/*     */       
/* 445 */       this.m_processor.m_token = (String)this.m_compiler.getTokenQueue().elementAt(this.m_processor.m_queueMark++);
/*     */       
/* 447 */       this.m_processor.m_tokenChar = this.m_processor.m_token.charAt(0);
/*     */     }
/*     */     else {
/*     */       
/* 451 */       this.m_processor.m_token = null;
/* 452 */       this.m_processor.m_tokenChar = Character.MIN_VALUE;
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
/*     */   final int getKeywordToken(String key) {
/*     */     boolean bool;
/*     */     
/* 470 */     try { Integer itok = (Integer)Keywords.m_keywords.get(key);
/*     */       
/* 472 */       bool = (null != itok) ? itok.intValue() : false; } catch (NullPointerException npe)
/*     */     
/*     */     { 
/*     */       
/* 476 */       bool = false; } catch (ClassCastException cce)
/*     */     
/*     */     { 
/*     */       
/* 480 */       bool = false; }
/*     */ 
/*     */     
/* 483 */     return bool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void recordTokenString(Vector targetStrings) {
/* 494 */     int tokPos = getTokenQueuePosFromMap(this.m_patternMapSize - 1);
/*     */     
/* 496 */     resetTokenMark(tokPos + 1);
/*     */     
/* 498 */     if (this.m_processor.lookahead('(', 1)) {
/*     */       
/* 500 */       int tok = getKeywordToken(this.m_processor.m_token);
/*     */       
/* 502 */       switch (tok) {
/*     */         
/*     */         case 1030:
/* 505 */           targetStrings.addElement("#comment");
/*     */           return;
/*     */         case 1031:
/* 508 */           targetStrings.addElement("#text");
/*     */           return;
/*     */         case 1033:
/* 511 */           targetStrings.addElement("*");
/*     */           return;
/*     */         case 35:
/* 514 */           targetStrings.addElement("/");
/*     */           return;
/*     */         case 36:
/* 517 */           targetStrings.addElement("*");
/*     */           return;
/*     */         case 1032:
/* 520 */           targetStrings.addElement("*");
/*     */           return;
/*     */       } 
/* 523 */       targetStrings.addElement("*");
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 528 */       if (this.m_processor.tokenIs('@')) {
/*     */         
/* 530 */         tokPos++;
/*     */         
/* 532 */         resetTokenMark(tokPos + 1);
/*     */       } 
/*     */       
/* 535 */       if (this.m_processor.lookahead(':', 1))
/*     */       {
/* 537 */         tokPos += 2;
/*     */       }
/*     */       
/* 540 */       targetStrings.addElement(this.m_compiler.getTokenQueue().elementAt(tokPos));
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
/*     */   private final void addToTokenQueue(String s) {
/* 552 */     this.m_compiler.getTokenQueue().addElement(s);
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
/*     */   private int mapNSTokens(String pat, int startSubstring, int posOfNSSep, int posOfScan) throws TransformerException {
/* 573 */     String str1, prefix = "";
/*     */     
/* 575 */     if (startSubstring >= 0 && posOfNSSep >= 0)
/*     */     {
/* 577 */       prefix = pat.substring(startSubstring, posOfNSSep);
/*     */     }
/*     */ 
/*     */     
/* 581 */     if (null != this.m_namespaceContext && !prefix.equals("*") && !prefix.equals("xmlns")) {
/*     */ 
/*     */ 
/*     */       
/*     */       try { 
/* 586 */         if (prefix.length() > 0)
/* 587 */         { str1 = this.m_namespaceContext.getNamespaceForPrefix(prefix);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */            }
/*     */         
/*     */         else
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 608 */           str1 = this.m_namespaceContext.getNamespaceForPrefix(prefix); }  } catch (ClassCastException cce)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 616 */         str1 = this.m_namespaceContext.getNamespaceForPrefix(prefix); }
/*     */ 
/*     */     
/*     */     } else {
/*     */       
/* 621 */       str1 = prefix;
/*     */     } 
/*     */     
/* 624 */     if (null != str1 && str1.length() > 0) {
/*     */       
/* 626 */       addToTokenQueue(str1);
/* 627 */       addToTokenQueue(":");
/*     */       
/* 629 */       String s = pat.substring(posOfNSSep + 1, posOfScan);
/*     */       
/* 631 */       if (s.length() > 0) {
/* 632 */         addToTokenQueue(s);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 638 */       this.m_processor.error("ER_PREFIX_MUST_RESOLVE", (Object[])new String[] { prefix });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 653 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/compiler/Lexer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */