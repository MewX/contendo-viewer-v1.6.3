/*      */ package org.apache.regexp;
/*      */ 
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RE
/*      */ {
/*      */   public static final int MATCH_NORMAL = 0;
/*      */   public static final int MATCH_CASEINDEPENDENT = 1;
/*      */   public static final int MATCH_MULTILINE = 2;
/*      */   public static final int MATCH_SINGLELINE = 4;
/*      */   static final char OP_END = 'E';
/*      */   static final char OP_BOL = '^';
/*      */   static final char OP_EOL = '$';
/*      */   static final char OP_ANY = '.';
/*      */   static final char OP_ANYOF = '[';
/*      */   static final char OP_BRANCH = '|';
/*      */   static final char OP_ATOM = 'A';
/*      */   static final char OP_STAR = '*';
/*      */   static final char OP_PLUS = '+';
/*      */   static final char OP_MAYBE = '?';
/*      */   static final char OP_ESCAPE = '\\';
/*      */   static final char OP_OPEN = '(';
/*      */   static final char OP_CLOSE = ')';
/*      */   static final char OP_BACKREF = '#';
/*      */   static final char OP_GOTO = 'G';
/*      */   static final char OP_NOTHING = 'N';
/*      */   static final char OP_RELUCTANTSTAR = '8';
/*      */   static final char OP_RELUCTANTPLUS = '=';
/*      */   static final char OP_RELUCTANTMAYBE = '/';
/*      */   static final char OP_POSIXCLASS = 'P';
/*      */   static final char E_ALNUM = 'w';
/*      */   static final char E_NALNUM = 'W';
/*      */   static final char E_BOUND = 'b';
/*      */   static final char E_NBOUND = 'B';
/*      */   static final char E_SPACE = 's';
/*      */   static final char E_NSPACE = 'S';
/*      */   static final char E_DIGIT = 'd';
/*      */   static final char E_NDIGIT = 'D';
/*      */   static final char POSIX_CLASS_ALNUM = 'w';
/*      */   static final char POSIX_CLASS_ALPHA = 'a';
/*      */   static final char POSIX_CLASS_BLANK = 'b';
/*      */   static final char POSIX_CLASS_CNTRL = 'c';
/*      */   static final char POSIX_CLASS_DIGIT = 'd';
/*      */   static final char POSIX_CLASS_GRAPH = 'g';
/*      */   static final char POSIX_CLASS_LOWER = 'l';
/*      */   static final char POSIX_CLASS_PRINT = 'p';
/*      */   static final char POSIX_CLASS_PUNCT = '!';
/*      */   static final char POSIX_CLASS_SPACE = 's';
/*      */   static final char POSIX_CLASS_UPPER = 'u';
/*      */   static final char POSIX_CLASS_XDIGIT = 'x';
/*      */   static final char POSIX_CLASS_JSTART = 'j';
/*      */   static final char POSIX_CLASS_JPART = 'k';
/*      */   static final int maxNode = 65536;
/*      */   static final int maxParen = 16;
/*      */   static final int offsetOpcode = 0;
/*      */   static final int offsetOpdata = 1;
/*      */   static final int offsetNext = 2;
/*      */   static final int nodeSize = 3;
/*  446 */   static final String NEWLINE = System.getProperty("line.separator");
/*      */   
/*      */   REProgram program;
/*      */   
/*      */   CharacterIterator search;
/*      */   
/*      */   int idx;
/*      */   
/*      */   int matchFlags;
/*      */   
/*      */   int parenCount;
/*      */   
/*      */   int start0;
/*      */   
/*      */   int end0;
/*      */   
/*      */   int start1;
/*      */   
/*      */   int end1;
/*      */   
/*      */   int start2;
/*      */   
/*      */   int end2;
/*      */   
/*      */   int[] startn;
/*      */   
/*      */   int[] endn;
/*      */   
/*      */   int[] startBackref;
/*      */   int[] endBackref;
/*      */   public static final int REPLACE_ALL = 0;
/*      */   public static final int REPLACE_FIRSTONLY = 1;
/*      */   
/*      */   public RE(String paramString) throws RESyntaxException {
/*  480 */     this(paramString, 0);
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
/*      */   public RE(String paramString, int paramInt) throws RESyntaxException {
/*  495 */     this((new RECompiler()).compile(paramString));
/*  496 */     setMatchFlags(paramInt);
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
/*      */   public RE(REProgram paramREProgram, int paramInt) {
/*  520 */     setProgram(paramREProgram);
/*  521 */     setMatchFlags(paramInt);
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
/*      */   public RE(REProgram paramREProgram) {
/*  533 */     this(paramREProgram, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RE() {
/*  542 */     this((REProgram)null, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String simplePatternToFullRegularExpression(String paramString) {
/*  552 */     StringBuffer stringBuffer = new StringBuffer();
/*  553 */     for (byte b = 0; b < paramString.length(); b++) {
/*      */       
/*  555 */       char c = paramString.charAt(b);
/*  556 */       switch (c) {
/*      */         
/*      */         case '*':
/*  559 */           stringBuffer.append(".*");
/*      */           break;
/*      */         
/*      */         case '$':
/*      */         case '(':
/*      */         case ')':
/*      */         case '+':
/*      */         case '.':
/*      */         case '?':
/*      */         case '[':
/*      */         case '\\':
/*      */         case ']':
/*      */         case '^':
/*      */         case '{':
/*      */         case '|':
/*      */         case '}':
/*  575 */           stringBuffer.append('\\');
/*      */         default:
/*  577 */           stringBuffer.append(c);
/*      */           break;
/*      */       } 
/*      */     } 
/*  581 */     return stringBuffer.toString();
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
/*      */   public void setMatchFlags(int paramInt) {
/*  599 */     this.matchFlags = paramInt;
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
/*      */   public int getMatchFlags() {
/*  619 */     return this.matchFlags;
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
/*      */   public void setProgram(REProgram paramREProgram) {
/*  631 */     this.program = paramREProgram;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public REProgram getProgram() {
/*  641 */     return this.program;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getParenCount() {
/*  650 */     return this.parenCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getParen(int paramInt) {
/*      */     int i;
/*  661 */     if (paramInt < this.parenCount && (i = getParenStart(paramInt)) >= 0)
/*      */     {
/*  663 */       return this.search.substring(i, getParenEnd(paramInt));
/*      */     }
/*  665 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getParenStart(int paramInt) {
/*  675 */     if (paramInt < this.parenCount) {
/*      */       
/*  677 */       switch (paramInt) {
/*      */         
/*      */         case 0:
/*  680 */           return this.start0;
/*      */         
/*      */         case 1:
/*  683 */           return this.start1;
/*      */         
/*      */         case 2:
/*  686 */           return this.start2;
/*      */       } 
/*      */       
/*  689 */       if (this.startn == null)
/*      */       {
/*  691 */         allocParens();
/*      */       }
/*  693 */       return this.startn[paramInt];
/*      */     } 
/*      */     
/*  696 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getParenEnd(int paramInt) {
/*  706 */     if (paramInt < this.parenCount) {
/*      */       
/*  708 */       switch (paramInt) {
/*      */         
/*      */         case 0:
/*  711 */           return this.end0;
/*      */         
/*      */         case 1:
/*  714 */           return this.end1;
/*      */         
/*      */         case 2:
/*  717 */           return this.end2;
/*      */       } 
/*      */       
/*  720 */       if (this.endn == null)
/*      */       {
/*  722 */         allocParens();
/*      */       }
/*  724 */       return this.endn[paramInt];
/*      */     } 
/*      */     
/*  727 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getParenLength(int paramInt) {
/*  737 */     if (paramInt < this.parenCount)
/*      */     {
/*  739 */       return getParenEnd(paramInt) - getParenStart(paramInt);
/*      */     }
/*  741 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setParenStart(int paramInt1, int paramInt2) {
/*  751 */     if (paramInt1 < this.parenCount) {
/*      */       
/*  753 */       switch (paramInt1) {
/*      */         
/*      */         case 0:
/*  756 */           this.start0 = paramInt2;
/*      */           return;
/*      */         
/*      */         case 1:
/*  760 */           this.start1 = paramInt2;
/*      */           return;
/*      */         
/*      */         case 2:
/*  764 */           this.start2 = paramInt2;
/*      */           return;
/*      */       } 
/*      */       
/*  768 */       if (this.startn == null)
/*      */       {
/*  770 */         allocParens();
/*      */       }
/*  772 */       this.startn[paramInt1] = paramInt2;
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
/*      */   protected final void setParenEnd(int paramInt1, int paramInt2) {
/*  785 */     if (paramInt1 < this.parenCount) {
/*      */       
/*  787 */       switch (paramInt1) {
/*      */         
/*      */         case 0:
/*  790 */           this.end0 = paramInt2;
/*      */           return;
/*      */         
/*      */         case 1:
/*  794 */           this.end1 = paramInt2;
/*      */           return;
/*      */         
/*      */         case 2:
/*  798 */           this.end2 = paramInt2;
/*      */           return;
/*      */       } 
/*      */       
/*  802 */       if (this.endn == null)
/*      */       {
/*  804 */         allocParens();
/*      */       }
/*  806 */       this.endn[paramInt1] = paramInt2;
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
/*      */   protected void internalError(String paramString) throws Error {
/*  820 */     throw new Error("RE internal error: " + paramString);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void allocParens() {
/*  829 */     this.startn = new int[16];
/*  830 */     this.endn = new int[16];
/*      */ 
/*      */     
/*  833 */     for (byte b = 0; b < 16; b++) {
/*      */       
/*  835 */       this.startn[b] = -1;
/*  836 */       this.endn[b] = -1;
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
/*      */   protected int matchNodes(int paramInt1, int paramInt2, int paramInt3) {
/*  851 */     int i = paramInt3;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  856 */     char[] arrayOfChar = this.program.instruction;
/*  857 */     for (int j = paramInt1; j < paramInt2; ) {
/*      */       int m, n, i1, i2, i3; boolean bool; int i4;
/*  859 */       char c1 = arrayOfChar[j];
/*  860 */       int k = j + (short)arrayOfChar[j + 2];
/*  861 */       char c2 = arrayOfChar[j + 1];
/*      */       
/*  863 */       switch (c1) {
/*      */ 
/*      */         
/*      */         case '/':
/*  867 */           n = 0;
/*      */           
/*      */           do {
/*      */             int i5;
/*  871 */             if ((i5 = matchNodes(k, 65536, i)) != -1)
/*      */             {
/*  873 */               return i5;
/*      */             }
/*      */           }
/*  876 */           while (n++ == 0 && (i = matchNodes(j + 3, k, i)) != -1);
/*  877 */           return -1;
/*      */ 
/*      */         
/*      */         case '=':
/*  881 */           while ((i = matchNodes(j + 3, k, i)) != -1) {
/*      */             int i5;
/*      */             
/*  884 */             if ((i5 = matchNodes(k, 65536, i)) != -1)
/*      */             {
/*  886 */               return i5;
/*      */             }
/*      */           } 
/*  889 */           return -1;
/*      */ 
/*      */         
/*      */         case '8':
/*      */           while (true) {
/*      */             int i5;
/*  895 */             if ((i5 = matchNodes(k, 65536, i)) != -1)
/*      */             {
/*  897 */               return i5;
/*      */             }
/*      */             
/*  900 */             if ((i = matchNodes(j + 3, k, i)) == -1) {
/*  901 */               return -1;
/*      */             }
/*      */           } 
/*      */         
/*      */         case '(':
/*  906 */           if ((this.program.flags & 0x1) != 0)
/*      */           {
/*  908 */             this.startBackref[c2] = i;
/*      */           }
/*  910 */           if ((m = matchNodes(k, 65536, i)) != -1) {
/*      */ 
/*      */             
/*  913 */             if (c2 + 1 > this.parenCount)
/*      */             {
/*  915 */               this.parenCount = c2 + 1;
/*      */             }
/*      */ 
/*      */             
/*  919 */             if (getParenStart(c2) == -1)
/*      */             {
/*  921 */               setParenStart(c2, i);
/*      */             }
/*      */           } 
/*  924 */           return m;
/*      */ 
/*      */ 
/*      */         
/*      */         case ')':
/*  929 */           if ((this.program.flags & 0x1) != 0)
/*      */           {
/*  931 */             this.endBackref[c2] = i;
/*      */           }
/*  933 */           if ((m = matchNodes(k, 65536, i)) != -1) {
/*      */ 
/*      */             
/*  936 */             if (c2 + 1 > this.parenCount)
/*      */             {
/*  938 */               this.parenCount = c2 + 1;
/*      */             }
/*      */ 
/*      */             
/*  942 */             if (getParenEnd(c2) == -1)
/*      */             {
/*  944 */               setParenEnd(c2, i);
/*      */             }
/*      */           } 
/*  947 */           return m;
/*      */ 
/*      */ 
/*      */         
/*      */         case '#':
/*  952 */           n = this.startBackref[c2];
/*  953 */           i1 = this.endBackref[c2];
/*      */ 
/*      */           
/*  956 */           if (n == -1 || i1 == -1)
/*      */           {
/*  958 */             return -1;
/*      */           }
/*      */ 
/*      */           
/*  962 */           if (n != i1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  968 */             int i5 = i1 - n;
/*      */ 
/*      */             
/*  971 */             if (this.search.isEnd(i + i5 - 1))
/*      */             {
/*  973 */               return -1;
/*      */             }
/*      */ 
/*      */             
/*  977 */             if ((this.matchFlags & 0x1) != 0) {
/*      */ 
/*      */               
/*  980 */               for (byte b1 = 0; b1 < i5; b1++) {
/*      */                 
/*  982 */                 if (Character.toLowerCase(this.search.charAt(i++)) != Character.toLowerCase(this.search.charAt(n + b1)))
/*      */                 {
/*  984 */                   return -1;
/*      */                 }
/*      */               } 
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/*  991 */             for (byte b = 0; b < i5; b++) {
/*      */               
/*  993 */               if (this.search.charAt(i++) != this.search.charAt(n + b))
/*      */               {
/*  995 */                 return -1;
/*      */               }
/*      */             } 
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case '^':
/* 1005 */           if (i != 0) {
/*      */ 
/*      */             
/* 1008 */             if ((this.matchFlags & 0x2) == 2) {
/*      */ 
/*      */               
/* 1011 */               if (i <= 0 || !isNewline(i - 1)) {
/* 1012 */                 return -1;
/*      */               }
/*      */               
/*      */               break;
/*      */             } 
/* 1017 */             return -1;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case '$':
/* 1024 */           if (!this.search.isEnd(0) && !this.search.isEnd(i)) {
/*      */ 
/*      */             
/* 1027 */             if ((this.matchFlags & 0x2) == 2) {
/*      */ 
/*      */               
/* 1030 */               if (!isNewline(i)) {
/* 1031 */                 return -1;
/*      */               }
/*      */               
/*      */               break;
/*      */             } 
/* 1036 */             return -1;
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case '\\':
/* 1043 */           switch (c2) {
/*      */ 
/*      */ 
/*      */             
/*      */             case 'B':
/*      */             case 'b':
/* 1049 */               n = (i == getParenStart(0)) ? 10 : this.search.charAt(i - 1);
/* 1050 */               i1 = this.search.isEnd(i) ? 10 : this.search.charAt(i);
/* 1051 */               if (((Character.isLetterOrDigit(n) != Character.isLetterOrDigit(i1)) ? false : true) == ((c2 != 'b') ? false : true))
/*      */               {
/* 1053 */                 return -1;
/*      */               }
/*      */               break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case 'D':
/*      */             case 'S':
/*      */             case 'W':
/*      */             case 'd':
/*      */             case 's':
/*      */             case 'w':
/* 1067 */               if (this.search.isEnd(i))
/*      */               {
/* 1069 */                 return -1;
/*      */               }
/*      */ 
/*      */               
/* 1073 */               switch (c2) {
/*      */                 
/*      */                 case 'W':
/*      */                 case 'w':
/* 1077 */                   if (Character.isLetterOrDigit(this.search.charAt(i)) != (!(c2 != 'w')))
/*      */                   {
/* 1079 */                     return -1;
/*      */                   }
/*      */                   break;
/*      */                 
/*      */                 case 'D':
/*      */                 case 'd':
/* 1085 */                   if (Character.isDigit(this.search.charAt(i)) != (!(c2 != 'd')))
/*      */                   {
/* 1087 */                     return -1;
/*      */                   }
/*      */                   break;
/*      */                 
/*      */                 case 'S':
/*      */                 case 's':
/* 1093 */                   if (Character.isWhitespace(this.search.charAt(i)) != (!(c2 != 's')))
/*      */                   {
/* 1095 */                     return -1;
/*      */                   }
/*      */                   break;
/*      */               } 
/* 1099 */               i++;
/*      */               break;
/*      */           } 
/*      */           
/* 1103 */           internalError("Unrecognized escape '" + c2 + "'");
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case '.':
/* 1109 */           if ((this.matchFlags & 0x4) == 4) {
/*      */             
/* 1111 */             if (this.search.isEnd(i))
/*      */             {
/* 1113 */               return -1;
/*      */             }
/* 1115 */             i++;
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/* 1121 */           if (this.search.isEnd(i) || this.search.charAt(i++) == '\n')
/*      */           {
/* 1123 */             return -1;
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 'A':
/* 1131 */           if (this.search.isEnd(i))
/*      */           {
/* 1133 */             return -1;
/*      */           }
/*      */ 
/*      */           
/* 1137 */           n = c2;
/* 1138 */           i1 = j + 3;
/*      */ 
/*      */           
/* 1141 */           if (this.search.isEnd(n + i - 1))
/*      */           {
/* 1143 */             return -1;
/*      */           }
/*      */ 
/*      */           
/* 1147 */           if ((this.matchFlags & 0x1) != 0) {
/*      */             
/* 1149 */             for (byte b = 0; b < n; b++) {
/*      */               
/* 1151 */               if (Character.toLowerCase(this.search.charAt(i++)) != Character.toLowerCase(arrayOfChar[i1 + b]))
/*      */               {
/* 1153 */                 return -1;
/*      */               }
/*      */             } 
/*      */             
/*      */             break;
/*      */           } 
/* 1159 */           for (i2 = 0; i2 < n; i2++) {
/*      */             
/* 1161 */             if (this.search.charAt(i++) != arrayOfChar[i1 + i2])
/*      */             {
/* 1163 */               return -1;
/*      */             }
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 'P':
/* 1173 */           if (this.search.isEnd(i))
/*      */           {
/* 1175 */             return -1;
/*      */           }
/*      */           
/* 1178 */           switch (c2) {
/*      */             
/*      */             case 'w':
/* 1181 */               if (!Character.isLetterOrDigit(this.search.charAt(i)))
/*      */               {
/* 1183 */                 return -1;
/*      */               }
/*      */               break;
/*      */             
/*      */             case 'a':
/* 1188 */               if (!Character.isLetter(this.search.charAt(i)))
/*      */               {
/* 1190 */                 return -1;
/*      */               }
/*      */               break;
/*      */             
/*      */             case 'd':
/* 1195 */               if (!Character.isDigit(this.search.charAt(i)))
/*      */               {
/* 1197 */                 return -1;
/*      */               }
/*      */               break;
/*      */             
/*      */             case 'b':
/* 1202 */               if (!Character.isSpaceChar(this.search.charAt(i)))
/*      */               {
/* 1204 */                 return -1;
/*      */               }
/*      */               break;
/*      */             
/*      */             case 's':
/* 1209 */               if (!Character.isWhitespace(this.search.charAt(i)))
/*      */               {
/* 1211 */                 return -1;
/*      */               }
/*      */               break;
/*      */             
/*      */             case 'c':
/* 1216 */               if (Character.getType(this.search.charAt(i)) != 15)
/*      */               {
/* 1218 */                 return -1;
/*      */               }
/*      */               break;
/*      */             
/*      */             case 'g':
/* 1223 */               switch (Character.getType(this.search.charAt(i)))
/*      */               
/*      */               { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 default:
/* 1232 */                   return -1;
/*      */                 case 25: case 26: case 27:
/*      */                 case 28:
/*      */                   break; }  break;
/*      */             case 'l':
/* 1237 */               if (Character.getType(this.search.charAt(i)) != 2)
/*      */               {
/* 1239 */                 return -1;
/*      */               }
/*      */               break;
/*      */             
/*      */             case 'u':
/* 1244 */               if (Character.getType(this.search.charAt(i)) != 1)
/*      */               {
/* 1246 */                 return -1;
/*      */               }
/*      */               break;
/*      */             
/*      */             case 'p':
/* 1251 */               if (Character.getType(this.search.charAt(i)) == 15)
/*      */               {
/* 1253 */                 return -1;
/*      */               }
/*      */               break;
/*      */ 
/*      */             
/*      */             case '!':
/* 1259 */               n = Character.getType(this.search.charAt(i));
/* 1260 */               switch (n)
/*      */               
/*      */               { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 default:
/* 1270 */                   return -1;
/*      */                 case 20: case 21:
/*      */                 case 22:
/*      */                 case 23:
/*      */                 case 24:
/*      */                   break; }  break;
/*      */             case 'x':
/* 1277 */               n = ((this.search.charAt(i) < '0' || this.search.charAt(i) > '9') && (
/* 1278 */                 this.search.charAt(i) < 'a' || this.search.charAt(i) > 'f') && (
/* 1279 */                 this.search.charAt(i) < 'A' || this.search.charAt(i) > 'F')) ? 0 : 1;
/* 1280 */               if (n == 0)
/*      */               {
/* 1282 */                 return -1;
/*      */               }
/*      */               break;
/*      */ 
/*      */             
/*      */             case 'j':
/* 1288 */               if (!Character.isJavaIdentifierStart(this.search.charAt(i)))
/*      */               {
/* 1290 */                 return -1;
/*      */               }
/*      */               break;
/*      */             
/*      */             case 'k':
/* 1295 */               if (!Character.isJavaIdentifierPart(this.search.charAt(i)))
/*      */               {
/* 1297 */                 return -1;
/*      */               }
/*      */               break;
/*      */             
/*      */             default:
/* 1302 */               internalError("Bad posix class");
/*      */               break;
/*      */           } 
/*      */ 
/*      */           
/* 1307 */           i++;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case '[':
/* 1314 */           if (this.search.isEnd(i))
/*      */           {
/* 1316 */             return -1;
/*      */           }
/*      */ 
/*      */           
/* 1320 */           n = this.search.charAt(i);
/* 1321 */           i1 = ((this.matchFlags & 0x1) == 0) ? 0 : 1;
/* 1322 */           if (i1 != 0)
/*      */           {
/* 1324 */             n = Character.toLowerCase(n);
/*      */           }
/*      */ 
/*      */           
/* 1328 */           i2 = j + 3;
/* 1329 */           i3 = i2 + c2 * 2;
/* 1330 */           bool = false;
/* 1331 */           for (i4 = i2; i4 < i3; ) {
/*      */ 
/*      */             
/* 1334 */             char c3 = arrayOfChar[i4++];
/* 1335 */             char c4 = arrayOfChar[i4++];
/*      */ 
/*      */             
/* 1338 */             if (i1 != 0) {
/*      */               
/* 1340 */               c3 = Character.toLowerCase(c3);
/* 1341 */               c4 = Character.toLowerCase(c4);
/*      */             } 
/*      */ 
/*      */             
/* 1345 */             if (n >= c3 && n <= c4) {
/*      */               
/* 1347 */               bool = true;
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */           
/* 1353 */           if (!bool)
/*      */           {
/* 1355 */             return -1;
/*      */           }
/* 1357 */           i++;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case '|':
/* 1364 */           if (arrayOfChar[k] != '|') {
/*      */ 
/*      */             
/* 1367 */             j += 3;
/*      */ 
/*      */ 
/*      */             
/*      */             continue;
/*      */           } 
/*      */ 
/*      */           
/*      */           do {
/* 1376 */             if ((m = matchNodes(j + 3, 65536, i)) != -1)
/*      */             {
/* 1378 */               return m;
/*      */             }
/*      */ 
/*      */             
/* 1382 */             n = (short)arrayOfChar[j + 2];
/* 1383 */             j += n;
/*      */           }
/* 1385 */           while (n != 0 && arrayOfChar[j] == '|');
/*      */ 
/*      */           
/* 1388 */           return -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 'E':
/* 1400 */           setParenEnd(0, i);
/* 1401 */           return i;
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 1406 */           internalError("Invalid opcode '" + c1 + "'"); break;
/*      */         case 'G':
/*      */         case 'N':
/*      */           break;
/* 1410 */       }  j = k;
/*      */     } 
/*      */ 
/*      */     
/* 1414 */     internalError("Corrupt program");
/* 1415 */     return -1;
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
/*      */   protected boolean matchAt(int paramInt) {
/* 1428 */     this.start0 = -1;
/* 1429 */     this.end0 = -1;
/* 1430 */     this.start1 = -1;
/* 1431 */     this.end1 = -1;
/* 1432 */     this.start2 = -1;
/* 1433 */     this.end2 = -1;
/* 1434 */     this.startn = null;
/* 1435 */     this.endn = null;
/* 1436 */     this.parenCount = 1;
/* 1437 */     setParenStart(0, paramInt);
/*      */ 
/*      */     
/* 1440 */     if ((this.program.flags & 0x1) != 0) {
/*      */       
/* 1442 */       this.startBackref = new int[16];
/* 1443 */       this.endBackref = new int[16];
/*      */     } 
/*      */     
/*      */     int i;
/*      */     
/* 1448 */     if ((i = matchNodes(0, 65536, paramInt)) != -1) {
/*      */       
/* 1450 */       setParenEnd(0, i);
/* 1451 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1455 */     this.parenCount = 0;
/* 1456 */     return false;
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
/*      */   public boolean match(String paramString, int paramInt) {
/* 1468 */     return match(new StringCharacterIterator(paramString), paramInt);
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
/*      */   public boolean match(CharacterIterator paramCharacterIterator, int paramInt) {
/* 1481 */     if (this.program == null)
/*      */     {
/*      */ 
/*      */       
/* 1485 */       internalError("No RE program to run!");
/*      */     }
/*      */ 
/*      */     
/* 1489 */     this.search = paramCharacterIterator;
/*      */ 
/*      */     
/* 1492 */     if (this.program.prefix == null) {
/*      */ 
/*      */       
/* 1495 */       for (; !paramCharacterIterator.isEnd(paramInt - 1); paramInt++) {
/*      */ 
/*      */         
/* 1498 */         if (matchAt(paramInt))
/*      */         {
/* 1500 */           return true;
/*      */         }
/*      */       } 
/* 1503 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1508 */     boolean bool = ((this.matchFlags & 0x1) == 0) ? false : true;
/* 1509 */     char[] arrayOfChar = this.program.prefix;
/* 1510 */     for (; !paramCharacterIterator.isEnd(paramInt + arrayOfChar.length - 1); paramInt++) {
/*      */ 
/*      */       
/* 1513 */       boolean bool1 = false;
/* 1514 */       if (bool) {
/* 1515 */         bool1 = (Character.toLowerCase(paramCharacterIterator.charAt(paramInt)) != Character.toLowerCase(arrayOfChar[0])) ? false : true;
/*      */       } else {
/* 1517 */         bool1 = (paramCharacterIterator.charAt(paramInt) != arrayOfChar[0]) ? false : true;
/* 1518 */       }  if (bool1) {
/*      */ 
/*      */         
/* 1521 */         int i = paramInt++;
/*      */         byte b;
/* 1523 */         for (b = 1; b < arrayOfChar.length; ) {
/*      */ 
/*      */           
/* 1526 */           if (bool) {
/* 1527 */             bool1 = (Character.toLowerCase(paramCharacterIterator.charAt(paramInt++)) != Character.toLowerCase(arrayOfChar[b++])) ? false : true;
/*      */           } else {
/* 1529 */             bool1 = (paramCharacterIterator.charAt(paramInt++) != arrayOfChar[b++]) ? false : true;
/* 1530 */           }  if (!bool1) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1537 */         if (b == arrayOfChar.length)
/*      */         {
/*      */           
/* 1540 */           if (matchAt(i))
/*      */           {
/* 1542 */             return true;
/*      */           }
/*      */         }
/*      */ 
/*      */         
/* 1547 */         paramInt = i;
/*      */       } 
/*      */     } 
/* 1550 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean match(String paramString) {
/* 1561 */     return match(paramString, 0);
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
/*      */   public String[] split(String paramString) {
/* 1576 */     Vector vector = new Vector();
/*      */ 
/*      */     
/* 1579 */     int i = 0;
/* 1580 */     int j = paramString.length();
/*      */ 
/*      */     
/* 1583 */     while (i < j && match(paramString, i)) {
/*      */ 
/*      */       
/* 1586 */       int k = getParenStart(0);
/*      */ 
/*      */       
/* 1589 */       int m = getParenEnd(0);
/*      */ 
/*      */       
/* 1592 */       if (m == i) {
/*      */         
/* 1594 */         vector.addElement(paramString.substring(i, k + 1));
/* 1595 */         m++;
/*      */       }
/*      */       else {
/*      */         
/* 1599 */         vector.addElement(paramString.substring(i, k));
/*      */       } 
/*      */ 
/*      */       
/* 1603 */       i = m;
/*      */     } 
/*      */ 
/*      */     
/* 1607 */     String str = paramString.substring(i);
/* 1608 */     if (str.length() != 0)
/*      */     {
/* 1610 */       vector.addElement(str);
/*      */     }
/*      */ 
/*      */     
/* 1614 */     String[] arrayOfString = new String[vector.size()];
/* 1615 */     vector.copyInto((Object[])arrayOfString);
/* 1616 */     return arrayOfString;
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
/*      */   public String subst(String paramString1, String paramString2) {
/* 1646 */     return subst(paramString1, paramString2, 0);
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
/*      */   public String subst(String paramString1, String paramString2, int paramInt) {
/* 1669 */     StringBuffer stringBuffer = new StringBuffer();
/*      */ 
/*      */     
/* 1672 */     int i = 0;
/* 1673 */     int j = paramString1.length();
/*      */ 
/*      */     
/* 1676 */     while (i < j && match(paramString1, i)) {
/*      */ 
/*      */       
/* 1679 */       stringBuffer.append(paramString1.substring(i, getParenStart(0)));
/*      */ 
/*      */       
/* 1682 */       stringBuffer.append(paramString2);
/*      */ 
/*      */       
/* 1685 */       int k = getParenEnd(0);
/*      */ 
/*      */       
/* 1688 */       if (k == i)
/*      */       {
/* 1690 */         k++;
/*      */       }
/*      */ 
/*      */       
/* 1694 */       i = k;
/*      */ 
/*      */       
/* 1697 */       if ((paramInt & 0x1) != 0) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1704 */     if (i < j)
/*      */     {
/* 1706 */       stringBuffer.append(paramString1.substring(i));
/*      */     }
/*      */ 
/*      */     
/* 1710 */     return stringBuffer.toString();
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
/*      */   public String[] grep(Object[] paramArrayOfObject) {
/* 1724 */     Vector vector = new Vector();
/*      */ 
/*      */     
/* 1727 */     for (byte b = 0; b < paramArrayOfObject.length; b++) {
/*      */ 
/*      */       
/* 1730 */       String str = paramArrayOfObject[b].toString();
/*      */ 
/*      */       
/* 1733 */       if (match(str))
/*      */       {
/* 1735 */         vector.addElement(str);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1740 */     String[] arrayOfString = new String[vector.size()];
/* 1741 */     vector.copyInto((Object[])arrayOfString);
/* 1742 */     return arrayOfString;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isNewline(int paramInt) {
/* 1748 */     if (paramInt < NEWLINE.length() - 1) {
/* 1749 */       return false;
/*      */     }
/*      */     
/* 1752 */     if (this.search.charAt(paramInt) == '\n') {
/* 1753 */       return true;
/*      */     }
/*      */     
/* 1756 */     for (int i = NEWLINE.length() - 1; i >= 0; i--, paramInt--) {
/* 1757 */       if (NEWLINE.charAt(i) != this.search.charAt(paramInt)) {
/* 1758 */         return false;
/*      */       }
/*      */     } 
/* 1761 */     return true;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/regexp/RE.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */