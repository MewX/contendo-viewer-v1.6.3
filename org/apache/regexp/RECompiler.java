/*      */ package org.apache.regexp;
/*      */ 
/*      */ import java.util.Hashtable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RECompiler
/*      */ {
/*  101 */   static int brackets = 0;
/*  102 */   static int[] bracketStart = null;
/*  103 */   static int[] bracketEnd = null;
/*  104 */   static int[] bracketMin = null;
/*  105 */   static int[] bracketOpt = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  110 */   static Hashtable hashPOSIX = new Hashtable();
/*      */   
/*      */   static {
/*  113 */     hashPOSIX.put("alnum", new Character('w'));
/*  114 */     hashPOSIX.put("alpha", new Character('a'));
/*  115 */     hashPOSIX.put("blank", new Character('b'));
/*  116 */     hashPOSIX.put("cntrl", new Character('c'));
/*  117 */     hashPOSIX.put("digit", new Character('d'));
/*  118 */     hashPOSIX.put("graph", new Character('g'));
/*  119 */     hashPOSIX.put("lower", new Character('l'));
/*  120 */     hashPOSIX.put("print", new Character('p'));
/*  121 */     hashPOSIX.put("punct", new Character('!'));
/*  122 */     hashPOSIX.put("space", new Character('s'));
/*  123 */     hashPOSIX.put("upper", new Character('u'));
/*  124 */     hashPOSIX.put("xdigit", new Character('x'));
/*  125 */     hashPOSIX.put("javastart", new Character('j'));
/*  126 */     hashPOSIX.put("javapart", new Character('k'));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  135 */   char[] instruction = new char[128];
/*  136 */   int lenInstruction = 0;
/*      */   
/*      */   String pattern;
/*      */   int len;
/*      */   int idx;
/*      */   int parens;
/*      */   static final int NODE_NORMAL = 0;
/*      */   static final int NODE_NULLABLE = 1;
/*      */   static final int NODE_TOPLEVEL = 2;
/*      */   
/*      */   void ensure(int paramInt) {
/*  147 */     int i = this.instruction.length;
/*      */ 
/*      */     
/*  150 */     if (this.lenInstruction + paramInt >= i) {
/*      */ 
/*      */       
/*  153 */       while (this.lenInstruction + paramInt >= i)
/*      */       {
/*  155 */         i *= 2;
/*      */       }
/*      */ 
/*      */       
/*  159 */       char[] arrayOfChar = new char[i];
/*  160 */       System.arraycopy(this.instruction, 0, arrayOfChar, 0, this.lenInstruction);
/*  161 */       this.instruction = arrayOfChar;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   static final char ESC_MASK = '￰';
/*      */   
/*      */   static final char ESC_BACKREF = '￿';
/*      */   static final char ESC_COMPLEX = '￾';
/*      */   
/*      */   void emit(char paramChar) {
/*  172 */     ensure(1);
/*      */ 
/*      */     
/*  175 */     this.instruction[this.lenInstruction++] = paramChar;
/*      */   }
/*      */ 
/*      */   
/*      */   static final char ESC_CLASS = '�';
/*      */   
/*      */   static final int maxBrackets = 10;
/*      */   
/*      */   static final int bracketUnbounded = -1;
/*      */   
/*      */   static final int bracketFinished = -2;
/*      */   
/*      */   void nodeInsert(char paramChar, int paramInt1, int paramInt2) {
/*  188 */     ensure(3);
/*      */ 
/*      */     
/*  191 */     System.arraycopy(this.instruction, paramInt2, this.instruction, paramInt2 + 3, this.lenInstruction - paramInt2);
/*  192 */     this.instruction[paramInt2] = paramChar;
/*  193 */     this.instruction[paramInt2 + 1] = (char)paramInt1;
/*  194 */     this.instruction[paramInt2 + 2] = Character.MIN_VALUE;
/*  195 */     this.lenInstruction += 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setNextOfEnd(int paramInt1, int paramInt2) {
/*      */     char c;
/*  207 */     while ((c = this.instruction[paramInt1 + 2]) != '\000')
/*      */     {
/*  209 */       paramInt1 += c;
/*      */     }
/*      */ 
/*      */     
/*  213 */     this.instruction[paramInt1 + 2] = (char)(short)(paramInt2 - paramInt1);
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
/*      */   int node(char paramChar, int paramInt) {
/*  225 */     ensure(3);
/*      */ 
/*      */     
/*  228 */     this.instruction[this.lenInstruction] = paramChar;
/*  229 */     this.instruction[this.lenInstruction + 1] = (char)paramInt;
/*  230 */     this.instruction[this.lenInstruction + 2] = Character.MIN_VALUE;
/*  231 */     this.lenInstruction += 3;
/*      */ 
/*      */     
/*  234 */     return this.lenInstruction - 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void internalError() throws Error {
/*  244 */     throw new Error("Internal error!");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void syntaxError(String paramString) throws RESyntaxException {
/*  253 */     throw new RESyntaxException(paramString);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void allocBrackets() {
/*  262 */     if (bracketStart == null) {
/*      */ 
/*      */       
/*  265 */       bracketStart = new int[10];
/*  266 */       bracketEnd = new int[10];
/*  267 */       bracketMin = new int[10];
/*  268 */       bracketOpt = new int[10];
/*      */ 
/*      */       
/*  271 */       for (byte b = 0; b < 10; b++) {
/*      */         
/*  273 */         bracketOpt[b] = -1; bracketMin[b] = -1; bracketEnd[b] = -1; bracketStart[b] = -1;
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
/*      */   void bracket() throws RESyntaxException {
/*  285 */     if (this.idx >= this.len || this.pattern.charAt(this.idx++) != '{')
/*      */     {
/*  287 */       internalError();
/*      */     }
/*      */ 
/*      */     
/*  291 */     if (this.idx >= this.len || !Character.isDigit(this.pattern.charAt(this.idx)))
/*      */     {
/*  293 */       syntaxError("Expected digit");
/*      */     }
/*      */ 
/*      */     
/*  297 */     StringBuffer stringBuffer = new StringBuffer();
/*  298 */     while (this.idx < this.len && Character.isDigit(this.pattern.charAt(this.idx)))
/*      */     {
/*  300 */       stringBuffer.append(this.pattern.charAt(this.idx++));
/*      */     }
/*      */     
/*      */     try {
/*  304 */       bracketMin[brackets] = Integer.parseInt(stringBuffer.toString());
/*      */     }
/*  306 */     catch (NumberFormatException numberFormatException) {
/*      */       
/*  308 */       syntaxError("Expected valid number");
/*      */     } 
/*      */ 
/*      */     
/*  312 */     if (this.idx >= this.len)
/*      */     {
/*  314 */       syntaxError("Expected comma or right bracket");
/*      */     }
/*      */ 
/*      */     
/*  318 */     if (this.pattern.charAt(this.idx) == '}') {
/*      */       
/*  320 */       this.idx++;
/*  321 */       bracketOpt[brackets] = 0;
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  326 */     if (this.idx >= this.len || this.pattern.charAt(this.idx++) != ',')
/*      */     {
/*  328 */       syntaxError("Expected comma");
/*      */     }
/*      */ 
/*      */     
/*  332 */     if (this.idx >= this.len)
/*      */     {
/*  334 */       syntaxError("Expected comma or right bracket");
/*      */     }
/*      */ 
/*      */     
/*  338 */     if (this.pattern.charAt(this.idx) == '}') {
/*      */       
/*  340 */       this.idx++;
/*  341 */       bracketOpt[brackets] = -1;
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  346 */     if (this.idx >= this.len || !Character.isDigit(this.pattern.charAt(this.idx)))
/*      */     {
/*  348 */       syntaxError("Expected digit");
/*      */     }
/*      */ 
/*      */     
/*  352 */     stringBuffer.setLength(0);
/*  353 */     while (this.idx < this.len && Character.isDigit(this.pattern.charAt(this.idx)))
/*      */     {
/*  355 */       stringBuffer.append(this.pattern.charAt(this.idx++));
/*      */     }
/*      */     
/*      */     try {
/*  359 */       bracketOpt[brackets] = Integer.parseInt(stringBuffer.toString()) - bracketMin[brackets];
/*      */     }
/*  361 */     catch (NumberFormatException numberFormatException) {
/*      */       
/*  363 */       syntaxError("Expected valid number");
/*      */     } 
/*      */ 
/*      */     
/*  367 */     if (bracketOpt[brackets] <= 0)
/*      */     {
/*  369 */       syntaxError("Bad range");
/*      */     }
/*      */ 
/*      */     
/*  373 */     if (this.idx >= this.len || this.pattern.charAt(this.idx++) != '}')
/*      */     {
/*  375 */       syntaxError("Missing close brace");
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
/*      */   char escape() throws RESyntaxException {
/*      */     int i, j;
/*  391 */     if (this.pattern.charAt(this.idx) != '\\')
/*      */     {
/*  393 */       internalError();
/*      */     }
/*      */ 
/*      */     
/*  397 */     if (this.idx + 1 == this.len)
/*      */     {
/*  399 */       syntaxError("Escape terminates string");
/*      */     }
/*      */ 
/*      */     
/*  403 */     this.idx += 2;
/*  404 */     char c = this.pattern.charAt(this.idx - 1);
/*  405 */     switch (c) {
/*      */       
/*      */       case 'B':
/*      */       case 'b':
/*  409 */         return '￾';
/*      */       
/*      */       case 'D':
/*      */       case 'S':
/*      */       case 'W':
/*      */       case 'd':
/*      */       case 's':
/*      */       case 'w':
/*  417 */         return '�';
/*      */ 
/*      */ 
/*      */       
/*      */       case 'u':
/*      */       case 'x':
/*  423 */         i = (c == 'u') ? 4 : 2;
/*      */ 
/*      */         
/*  426 */         j = 0;
/*  427 */         for (; this.idx < this.len && i-- > 0; this.idx++) {
/*      */ 
/*      */           
/*  430 */           char c1 = this.pattern.charAt(this.idx);
/*      */ 
/*      */           
/*  433 */           if (c1 >= '0' && c1 <= '9') {
/*      */ 
/*      */             
/*  436 */             j = (j << 4) + c1 - 48;
/*      */           
/*      */           }
/*      */           else {
/*      */             
/*  441 */             c1 = Character.toLowerCase(c1);
/*  442 */             if (c1 >= 'a' && c1 <= 'f') {
/*      */ 
/*      */               
/*  445 */               j = (j << 4) + c1 - 97 + 10;
/*      */             
/*      */             }
/*      */             else {
/*      */ 
/*      */               
/*  451 */               syntaxError("Expected " + i + " hexadecimal digits after \\" + c);
/*      */             } 
/*      */           } 
/*      */         } 
/*  455 */         return (char)j;
/*      */ 
/*      */       
/*      */       case 't':
/*  459 */         return '\t';
/*      */       
/*      */       case 'n':
/*  462 */         return '\n';
/*      */       
/*      */       case 'r':
/*  465 */         return '\r';
/*      */       
/*      */       case 'f':
/*  468 */         return '\f';
/*      */ 
/*      */ 
/*      */       
/*      */       case '0':
/*      */       case '1':
/*      */       case '2':
/*      */       case '3':
/*      */       case '4':
/*      */       case '5':
/*      */       case '6':
/*      */       case '7':
/*      */       case '8':
/*      */       case '9':
/*  482 */         if ((this.idx < this.len && Character.isDigit(this.pattern.charAt(this.idx))) || c == '0') {
/*      */ 
/*      */           
/*  485 */           i = c - 48;
/*  486 */           if (this.idx < this.len && Character.isDigit(this.pattern.charAt(this.idx))) {
/*      */             
/*  488 */             i = (i << 3) + this.pattern.charAt(this.idx++) - 48;
/*  489 */             if (this.idx < this.len && Character.isDigit(this.pattern.charAt(this.idx)))
/*      */             {
/*  491 */               i = (i << 3) + this.pattern.charAt(this.idx++) - 48;
/*      */             }
/*      */           } 
/*  494 */           return (char)i;
/*      */         } 
/*      */ 
/*      */         
/*  498 */         return Character.MAX_VALUE;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  503 */     return c;
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
/*      */   int characterClass() throws RESyntaxException {
/*  515 */     if (this.pattern.charAt(this.idx) != '[')
/*      */     {
/*  517 */       internalError();
/*      */     }
/*      */ 
/*      */     
/*  521 */     if (this.idx + 1 >= this.len || this.pattern.charAt(++this.idx) == ']')
/*      */     {
/*  523 */       syntaxError("Empty or unterminated class");
/*      */     }
/*      */ 
/*      */     
/*  527 */     if (this.idx < this.len && this.pattern.charAt(this.idx) == ':') {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  533 */       int m = ++this.idx;
/*  534 */       while (this.idx < this.len && this.pattern.charAt(this.idx) >= 'a' && this.pattern.charAt(this.idx) <= 'z')
/*      */       {
/*  536 */         this.idx++;
/*      */       }
/*      */ 
/*      */       
/*  540 */       if (this.idx + 1 < this.len && this.pattern.charAt(this.idx) == ':' && this.pattern.charAt(this.idx + 1) == ']') {
/*      */ 
/*      */         
/*  543 */         String str = this.pattern.substring(m, this.idx);
/*      */ 
/*      */         
/*  546 */         Character character = (Character)hashPOSIX.get(str);
/*  547 */         if (character != null) {
/*      */ 
/*      */           
/*  550 */           this.idx += 2;
/*      */ 
/*      */           
/*  553 */           return node('P', character.charValue());
/*      */         } 
/*  555 */         syntaxError("Invalid POSIX character class '" + str + "'");
/*      */       } 
/*  557 */       syntaxError("Invalid POSIX character class syntax");
/*      */     } 
/*      */ 
/*      */     
/*  561 */     int i = node('[', 0);
/*      */ 
/*      */     
/*  564 */     char c1 = '￿';
/*  565 */     char c2 = c1;
/*  566 */     char c3 = Character.MIN_VALUE;
/*  567 */     int j = 1;
/*  568 */     boolean bool = false;
/*  569 */     int k = this.idx;
/*  570 */     byte b1 = 0;
/*      */     
/*  572 */     RERange rERange = new RERange(this);
/*  573 */     while (this.idx < this.len && this.pattern.charAt(this.idx) != ']') {
/*      */       char c;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  579 */       switch (this.pattern.charAt(this.idx)) {
/*      */         
/*      */         case '^':
/*  582 */           j ^= 0x1;
/*  583 */           if (this.idx == k)
/*      */           {
/*  585 */             rERange.include(0, 65535, true);
/*      */           }
/*  587 */           this.idx++;
/*      */           continue;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case '\\':
/*  594 */           switch (c = escape()) {
/*      */ 
/*      */ 
/*      */             
/*      */             case '￾':
/*      */             case '￿':
/*  600 */               syntaxError("Bad character class");
/*      */ 
/*      */ 
/*      */             
/*      */             case '�':
/*  605 */               if (bool)
/*      */               {
/*  607 */                 syntaxError("Bad character class");
/*      */               }
/*      */ 
/*      */               
/*  611 */               switch (this.pattern.charAt(this.idx - 1)) {
/*      */                 
/*      */                 case 'D':
/*      */                 case 'S':
/*      */                 case 'W':
/*  616 */                   syntaxError("Bad character class");
/*      */                 
/*      */                 case 's':
/*  619 */                   rERange.include('\t', j);
/*  620 */                   rERange.include('\r', j);
/*  621 */                   rERange.include('\f', j);
/*  622 */                   rERange.include('\n', j);
/*  623 */                   rERange.include('\b', j);
/*  624 */                   rERange.include(' ', j);
/*      */                   break;
/*      */                 
/*      */                 case 'w':
/*  628 */                   rERange.include(97, 122, j);
/*  629 */                   rERange.include(65, 90, j);
/*  630 */                   rERange.include('_', j);
/*      */ 
/*      */ 
/*      */                 
/*      */                 case 'd':
/*  635 */                   rERange.include(48, 57, j);
/*      */                   break;
/*      */               } 
/*      */ 
/*      */               
/*  640 */               c2 = c1;
/*      */               continue;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  646 */           c3 = c;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case '-':
/*  655 */           if (bool)
/*      */           {
/*  657 */             syntaxError("Bad class range");
/*      */           }
/*  659 */           bool = true;
/*      */ 
/*      */           
/*  662 */           b1 = (c2 == c1) ? 0 : c2;
/*      */ 
/*      */           
/*  665 */           if (this.idx + 1 < this.len && this.pattern.charAt(++this.idx) == ']') {
/*      */             
/*  667 */             c3 = '￿';
/*      */             break;
/*      */           } 
/*      */           continue;
/*      */         
/*      */         default:
/*  673 */           c3 = this.pattern.charAt(this.idx++);
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/*  678 */       if (bool) {
/*      */ 
/*      */         
/*  681 */         char c4 = c3;
/*      */ 
/*      */         
/*  684 */         if (b1 >= c4)
/*      */         {
/*  686 */           syntaxError("Bad character class");
/*      */         }
/*  688 */         rERange.include(b1, c4, j);
/*      */ 
/*      */         
/*  691 */         c2 = c1;
/*  692 */         bool = false;
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  697 */       if (this.idx + 1 >= this.len || this.pattern.charAt(this.idx + 1) != '-')
/*      */       {
/*  699 */         rERange.include(c3, j);
/*      */       }
/*  701 */       c2 = c3;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  706 */     if (this.idx == this.len)
/*      */     {
/*  708 */       syntaxError("Unterminated character class");
/*      */     }
/*      */ 
/*      */     
/*  712 */     this.idx++;
/*      */ 
/*      */     
/*  715 */     this.instruction[i + 1] = (char)rERange.num;
/*  716 */     for (byte b2 = 0; b2 < rERange.num; b2++) {
/*      */       
/*  718 */       emit((char)rERange.minRange[b2]);
/*  719 */       emit((char)rERange.maxRange[b2]);
/*      */     } 
/*  721 */     return i;
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
/*      */   int atom() throws RESyntaxException {
/*  735 */     int i = node('A', 0);
/*      */ 
/*      */     
/*  738 */     byte b = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  744 */     while (this.idx < this.len) {
/*      */       int j;
/*      */       char c;
/*  747 */       if (this.idx + 1 < this.len) {
/*      */         
/*  749 */         char c1 = this.pattern.charAt(this.idx + 1);
/*      */ 
/*      */         
/*  752 */         if (this.pattern.charAt(this.idx) == '\\') {
/*      */           
/*  754 */           int k = this.idx;
/*  755 */           escape();
/*  756 */           if (this.idx < this.len)
/*      */           {
/*  758 */             c1 = this.pattern.charAt(this.idx);
/*      */           }
/*  760 */           this.idx = k;
/*      */         } 
/*      */ 
/*      */         
/*  764 */         switch (c1) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case '*':
/*      */           case '+':
/*      */           case '?':
/*      */           case '{':
/*  773 */             if (b) {
/*      */               break;
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/*  781 */       switch (this.pattern.charAt(this.idx)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case '*':
/*      */         case '+':
/*      */         case '?':
/*      */         case '{':
/*  799 */           if (!b)
/*      */           {
/*      */             
/*  802 */             syntaxError("Missing operand to closure");
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case '\\':
/*  810 */           j = this.idx;
/*  811 */           c = escape();
/*      */ 
/*      */           
/*  814 */           if ((c & 0xFFF0) == 65520) {
/*      */ 
/*      */             
/*  817 */             this.idx = j;
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/*  822 */           emit(c);
/*  823 */           b++;
/*      */           break;
/*      */         
/*      */         case '$':
/*      */         case '(':
/*      */         case ')':
/*      */         case '.':
/*      */         case '[':
/*      */         case ']':
/*      */         case '^':
/*      */         case '|':
/*      */           break;
/*      */       } 
/*      */     } 
/*  837 */     if (b == 0)
/*      */     {
/*  839 */       internalError();
/*      */     }
/*      */ 
/*      */     
/*  843 */     this.instruction[i + 1] = (char)b;
/*  844 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int terminal(int[] paramArrayOfint) throws RESyntaxException {
/*      */     int i;
/*      */     char c;
/*  855 */     switch (this.pattern.charAt(this.idx)) {
/*      */       
/*      */       case '$':
/*      */       case '.':
/*      */       case '^':
/*  860 */         return node(this.pattern.charAt(this.idx++), 0);
/*      */       
/*      */       case '[':
/*  863 */         return characterClass();
/*      */       
/*      */       case '(':
/*  866 */         return expr(paramArrayOfint);
/*      */       
/*      */       case ')':
/*  869 */         syntaxError("Unexpected close paren");
/*      */       
/*      */       case '|':
/*  872 */         internalError();
/*      */       
/*      */       case ']':
/*  875 */         syntaxError("Mismatched class");
/*      */       
/*      */       case '\000':
/*  878 */         syntaxError("Unexpected end of input");
/*      */       
/*      */       case '*':
/*      */       case '+':
/*      */       case '?':
/*      */       case '{':
/*  884 */         syntaxError("Missing operand to closure");
/*      */ 
/*      */ 
/*      */       
/*      */       case '\\':
/*  889 */         i = this.idx;
/*      */ 
/*      */         
/*  892 */         switch (escape()) {
/*      */           
/*      */           case '�':
/*      */           case '￾':
/*  896 */             paramArrayOfint[0] = paramArrayOfint[0] & 0xFFFFFFFE;
/*  897 */             return node('\\', this.pattern.charAt(this.idx - 1));
/*      */ 
/*      */           
/*      */           case '￿':
/*  901 */             c = (char)(this.pattern.charAt(this.idx - 1) - 48);
/*  902 */             if (this.parens <= c)
/*      */             {
/*  904 */               syntaxError("Bad backreference");
/*      */             }
/*  906 */             paramArrayOfint[0] = paramArrayOfint[0] | 0x1;
/*  907 */             return node('#', c);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  914 */         this.idx = i;
/*  915 */         paramArrayOfint[0] = paramArrayOfint[0] & 0xFFFFFFFE;
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  923 */     paramArrayOfint[0] = paramArrayOfint[0] & 0xFFFFFFFE;
/*  924 */     return atom();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int closure(int[] paramArrayOfint) throws RESyntaxException {
/*      */     char c2;
/*  936 */     int i = this.idx;
/*      */ 
/*      */     
/*  939 */     int[] arrayOfInt = new int[1];
/*      */ 
/*      */     
/*  942 */     int j = terminal(arrayOfInt);
/*      */ 
/*      */     
/*  945 */     paramArrayOfint[0] = paramArrayOfint[0] | arrayOfInt[0];
/*      */ 
/*      */     
/*  948 */     if (this.idx >= this.len)
/*      */     {
/*  950 */       return j;
/*      */     }
/*  952 */     boolean bool = true;
/*  953 */     char c1 = this.pattern.charAt(this.idx);
/*  954 */     switch (c1) {
/*      */ 
/*      */ 
/*      */       
/*      */       case '*':
/*      */       case '?':
/*  960 */         paramArrayOfint[0] = paramArrayOfint[0] | 0x1;
/*      */ 
/*      */ 
/*      */       
/*      */       case '+':
/*  965 */         this.idx++;
/*      */ 
/*      */ 
/*      */       
/*      */       case '{':
/*  970 */         c2 = this.instruction[j];
/*  971 */         if (c2 == '^' || c2 == '$')
/*      */         {
/*  973 */           syntaxError("Bad closure operand");
/*      */         }
/*  975 */         if ((arrayOfInt[0] & 0x1) != 0)
/*      */         {
/*  977 */           syntaxError("Closure operand can't be nullable");
/*      */         }
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  983 */     if (this.idx < this.len && this.pattern.charAt(this.idx) == '?') {
/*      */       
/*  985 */       this.idx++;
/*  986 */       bool = false;
/*      */     } 
/*      */     
/*  989 */     if (bool) {
/*      */       int k;
/*      */       int m;
/*  992 */       switch (c1) {
/*      */ 
/*      */ 
/*      */         
/*      */         case '{':
/*  997 */           c2 = Character.MIN_VALUE;
/*      */           
/*  999 */           allocBrackets();
/* 1000 */           for (m = 0; m < brackets; m++) {
/*      */             
/* 1002 */             if (bracketStart[m] == this.idx) {
/*      */               
/* 1004 */               c2 = '\001';
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */           
/* 1010 */           if (c2 == '\000') {
/*      */             
/* 1012 */             if (brackets >= 10)
/*      */             {
/* 1014 */               syntaxError("Too many bracketed closures (limit is 10)");
/*      */             }
/* 1016 */             bracketStart[brackets] = this.idx;
/* 1017 */             bracket();
/* 1018 */             bracketEnd[brackets] = this.idx;
/* 1019 */             m = brackets++;
/*      */           } 
/*      */ 
/*      */           
/* 1023 */           bracketMin[m] = bracketMin[m] - 1; if (bracketMin[m] - 1 > 0) {
/*      */ 
/*      */             
/* 1026 */             this.idx = i;
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/* 1031 */           if (bracketOpt[m] == -2) {
/*      */ 
/*      */ 
/*      */             
/* 1035 */             c1 = '*';
/* 1036 */             bracketOpt[m] = 0;
/* 1037 */             this.idx = bracketEnd[m];
/*      */           } else {
/*      */             
/* 1040 */             if (bracketOpt[m] == -1) {
/*      */               
/* 1042 */               this.idx = i;
/* 1043 */               bracketOpt[m] = -2;
/*      */               
/*      */               break;
/*      */             } 
/* 1047 */             bracketOpt[m] = bracketOpt[m] - 1; if (bracketOpt[m] > 0) {
/*      */ 
/*      */               
/* 1050 */               this.idx = i;
/* 1051 */               c1 = '?';
/*      */             
/*      */             }
/*      */             else {
/*      */               
/* 1056 */               this.idx = bracketEnd[m];
/*      */               break;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case '*':
/*      */         case '?':
/* 1066 */           if (bool) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1071 */             if (c1 == '?') {
/*      */ 
/*      */               
/* 1074 */               nodeInsert('|', 0, j);
/* 1075 */               setNextOfEnd(j, node('|', 0));
/* 1076 */               int n = node('N', 0);
/* 1077 */               setNextOfEnd(j, n);
/* 1078 */               setNextOfEnd(j + 3, n);
/*      */             } 
/*      */             
/* 1081 */             if (c1 == '*') {
/*      */ 
/*      */               
/* 1084 */               nodeInsert('|', 0, j);
/* 1085 */               setNextOfEnd(j + 3, node('|', 0));
/* 1086 */               setNextOfEnd(j + 3, node('G', 0));
/* 1087 */               setNextOfEnd(j + 3, j);
/* 1088 */               setNextOfEnd(j, node('|', 0));
/* 1089 */               setNextOfEnd(j, node('N', 0));
/*      */             } 
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case '+':
/* 1097 */           k = node('|', 0);
/* 1098 */           setNextOfEnd(j, k);
/* 1099 */           setNextOfEnd(node('G', 0), j);
/* 1100 */           setNextOfEnd(k, node('|', 0));
/* 1101 */           setNextOfEnd(j, node('N', 0));
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } else {
/* 1109 */       setNextOfEnd(j, node('E', 0));
/*      */ 
/*      */       
/* 1112 */       switch (c1) {
/*      */         
/*      */         case '?':
/* 1115 */           nodeInsert('/', 0, j);
/*      */           break;
/*      */         
/*      */         case '*':
/* 1119 */           nodeInsert('8', 0, j);
/*      */           break;
/*      */         
/*      */         case '+':
/* 1123 */           nodeInsert('=', 0, j);
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/* 1128 */       setNextOfEnd(j, this.lenInstruction);
/*      */     } 
/* 1130 */     return j;
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
/*      */   int branch(int[] paramArrayOfint) throws RESyntaxException {
/* 1143 */     int i = node('|', 0);
/* 1144 */     int j = -1;
/* 1145 */     int[] arrayOfInt = new int[1];
/* 1146 */     boolean bool = true;
/* 1147 */     while (this.idx < this.len && this.pattern.charAt(this.idx) != '|' && this.pattern.charAt(this.idx) != ')') {
/*      */ 
/*      */       
/* 1150 */       arrayOfInt[0] = 0;
/* 1151 */       int k = closure(arrayOfInt);
/* 1152 */       if (arrayOfInt[0] == 0)
/*      */       {
/* 1154 */         bool = false;
/*      */       }
/*      */ 
/*      */       
/* 1158 */       if (j != -1)
/*      */       {
/* 1160 */         setNextOfEnd(j, k);
/*      */       }
/*      */ 
/*      */       
/* 1164 */       j = k;
/*      */     } 
/*      */ 
/*      */     
/* 1168 */     if (j == -1)
/*      */     {
/* 1170 */       node('N', 0);
/*      */     }
/*      */ 
/*      */     
/* 1174 */     if (bool)
/*      */     {
/* 1176 */       paramArrayOfint[0] = paramArrayOfint[0] | 0x1;
/*      */     }
/* 1178 */     return i;
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
/*      */   int expr(int[] paramArrayOfint) throws RESyntaxException {
/*      */     int m;
/* 1191 */     boolean bool = false;
/* 1192 */     int i = -1;
/* 1193 */     int j = this.parens;
/* 1194 */     if ((paramArrayOfint[0] & 0x2) == 0 && this.pattern.charAt(this.idx) == '(') {
/*      */       
/* 1196 */       this.idx++;
/* 1197 */       bool = true;
/* 1198 */       i = node('(', this.parens++);
/*      */     } 
/* 1200 */     paramArrayOfint[0] = paramArrayOfint[0] & 0xFFFFFFFD;
/*      */ 
/*      */     
/* 1203 */     int k = branch(paramArrayOfint);
/* 1204 */     if (i == -1) {
/*      */       
/* 1206 */       i = k;
/*      */     }
/*      */     else {
/*      */       
/* 1210 */       setNextOfEnd(i, k);
/*      */     } 
/*      */ 
/*      */     
/* 1214 */     while (this.idx < this.len && this.pattern.charAt(this.idx) == '|') {
/*      */       
/* 1216 */       this.idx++;
/* 1217 */       k = branch(paramArrayOfint);
/* 1218 */       setNextOfEnd(i, k);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1223 */     if (bool) {
/*      */       
/* 1225 */       if (this.idx < this.len && this.pattern.charAt(this.idx) == ')') {
/*      */         
/* 1227 */         this.idx++;
/*      */       }
/*      */       else {
/*      */         
/* 1231 */         syntaxError("Missing close paren");
/*      */       } 
/* 1233 */       m = node(')', j);
/*      */     }
/*      */     else {
/*      */       
/* 1237 */       m = node('E', 0);
/*      */     } 
/*      */ 
/*      */     
/* 1241 */     setNextOfEnd(i, m);
/*      */ 
/*      */     
/* 1244 */     for (int n = -1, i1 = i; n != 0; n = this.instruction[i1 + 2], i1 += n) {
/*      */ 
/*      */       
/* 1247 */       if (this.instruction[i1] == '|')
/*      */       {
/* 1249 */         setNextOfEnd(i1 + 3, m);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1254 */     return i;
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
/*      */   public REProgram compile(String paramString) throws RESyntaxException {
/* 1270 */     this.pattern = paramString;
/* 1271 */     this.len = paramString.length();
/* 1272 */     this.idx = 0;
/* 1273 */     this.lenInstruction = 0;
/* 1274 */     this.parens = 1;
/* 1275 */     brackets = 0;
/*      */ 
/*      */     
/* 1278 */     int[] arrayOfInt = { 2 };
/*      */ 
/*      */     
/* 1281 */     expr(arrayOfInt);
/*      */ 
/*      */     
/* 1284 */     if (this.idx != this.len) {
/*      */       
/* 1286 */       if (paramString.charAt(this.idx) == ')')
/*      */       {
/* 1288 */         syntaxError("Unmatched close paren");
/*      */       }
/* 1290 */       syntaxError("Unexpected input remains");
/*      */     } 
/*      */ 
/*      */     
/* 1294 */     char[] arrayOfChar = new char[this.lenInstruction];
/* 1295 */     System.arraycopy(this.instruction, 0, arrayOfChar, 0, this.lenInstruction);
/* 1296 */     return new REProgram(arrayOfChar);
/*      */   }
/*      */   
/*      */   class RERange { private final RECompiler this$0;
/*      */     
/*      */     RERange(RECompiler this$0) {
/* 1302 */       this.this$0 = this$0;
/*      */       
/* 1304 */       this.size = 16;
/* 1305 */       this.minRange = new int[this.size];
/* 1306 */       this.maxRange = new int[this.size];
/* 1307 */       this.num = 0;
/*      */     }
/*      */     
/*      */     int size;
/*      */     int[] minRange;
/*      */     int[] maxRange;
/*      */     int num;
/*      */     
/*      */     void delete(int param1Int) {
/* 1316 */       if (this.num == 0 || param1Int >= this.num) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1322 */       while (param1Int++ < this.num) {
/*      */         
/* 1324 */         if (param1Int - 1 >= 0) {
/*      */           
/* 1326 */           this.minRange[param1Int - 1] = this.minRange[param1Int];
/* 1327 */           this.maxRange[param1Int - 1] = this.maxRange[param1Int];
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1332 */       this.num--;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void merge(int param1Int1, int param1Int2) {
/* 1343 */       for (byte b = 0; b < this.num; b++) {
/*      */ 
/*      */         
/* 1346 */         if (param1Int1 >= this.minRange[b] && param1Int2 <= this.maxRange[b]) {
/*      */           return;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1352 */         if (param1Int1 <= this.minRange[b] && param1Int2 >= this.maxRange[b]) {
/*      */           
/* 1354 */           delete(b);
/* 1355 */           merge(param1Int1, param1Int2);
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 1360 */         if (param1Int1 >= this.minRange[b] && param1Int1 <= this.maxRange[b]) {
/*      */           
/* 1362 */           delete(b);
/* 1363 */           param1Int1 = this.minRange[b];
/* 1364 */           merge(param1Int1, param1Int2);
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 1369 */         if (param1Int2 >= this.minRange[b] && param1Int2 <= this.maxRange[b]) {
/*      */           
/* 1371 */           delete(b);
/* 1372 */           param1Int2 = this.maxRange[b];
/* 1373 */           merge(param1Int1, param1Int2);
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */       
/* 1379 */       if (this.num >= this.size) {
/*      */         
/* 1381 */         this.size *= 2;
/* 1382 */         int[] arrayOfInt1 = new int[this.size];
/* 1383 */         int[] arrayOfInt2 = new int[this.size];
/* 1384 */         System.arraycopy(this.minRange, 0, arrayOfInt1, 0, this.num);
/* 1385 */         System.arraycopy(this.maxRange, 0, arrayOfInt2, 0, this.num);
/* 1386 */         this.minRange = arrayOfInt1;
/* 1387 */         this.maxRange = arrayOfInt2;
/*      */       } 
/* 1389 */       this.minRange[this.num] = param1Int1;
/* 1390 */       this.maxRange[this.num] = param1Int2;
/* 1391 */       this.num++;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void remove(int param1Int1, int param1Int2) {
/* 1402 */       for (byte b = 0; b < this.num; b++) {
/*      */ 
/*      */         
/* 1405 */         if (this.minRange[b] >= param1Int1 && this.maxRange[b] <= param1Int2) {
/*      */           
/* 1407 */           delete(b);
/* 1408 */           b--;
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 1413 */         if (param1Int1 >= this.minRange[b] && param1Int2 <= this.maxRange[b]) {
/*      */           
/* 1415 */           int i = this.minRange[b];
/* 1416 */           int j = this.maxRange[b];
/* 1417 */           delete(b);
/* 1418 */           if (i < param1Int1 - 1)
/*      */           {
/* 1420 */             merge(i, param1Int1 - 1);
/*      */           }
/* 1422 */           if (param1Int2 + 1 < j)
/*      */           {
/* 1424 */             merge(param1Int2 + 1, j);
/*      */           }
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 1430 */         if (this.minRange[b] >= param1Int1 && this.minRange[b] <= param1Int2) {
/*      */           
/* 1432 */           this.minRange[b] = param1Int2 + 1;
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 1437 */         if (this.maxRange[b] >= param1Int1 && this.maxRange[b] <= param1Int2) {
/*      */           
/* 1439 */           this.maxRange[b] = param1Int1 - 1;
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void include(int param1Int1, int param1Int2, boolean param1Boolean) {
/* 1453 */       if (param1Boolean) {
/*      */         
/* 1455 */         merge(param1Int1, param1Int2);
/*      */       }
/*      */       else {
/*      */         
/* 1459 */         remove(param1Int1, param1Int2);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void include(char param1Char, boolean param1Boolean) {
/* 1470 */       include(param1Char, param1Char, param1Boolean);
/*      */     } }
/*      */ 
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/regexp/RECompiler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */