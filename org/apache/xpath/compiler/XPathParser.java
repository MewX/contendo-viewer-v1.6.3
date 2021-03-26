/*      */ package org.apache.xpath.compiler;
/*      */ 
/*      */ import javax.xml.transform.ErrorListener;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xml.utils.PrefixResolver;
/*      */ import org.apache.xpath.XPathProcessorException;
/*      */ import org.apache.xpath.objects.XNumber;
/*      */ import org.apache.xpath.objects.XString;
/*      */ import org.apache.xpath.res.XPATHMessages;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class XPathParser
/*      */ {
/*      */   public static final String CONTINUE_AFTER_FATAL_ERROR = "CONTINUE_AFTER_FATAL_ERROR";
/*      */   private OpMap m_ops;
/*      */   transient String m_token;
/*   58 */   transient char m_tokenChar = Character.MIN_VALUE;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   63 */   int m_queueMark = 0;
/*      */   
/*      */   protected static final int FILTER_MATCH_FAILED = 0;
/*      */   
/*      */   protected static final int FILTER_MATCH_PRIMARY = 1;
/*      */   
/*      */   protected static final int FILTER_MATCH_PREDICATES = 2;
/*      */   
/*      */   PrefixResolver m_namespaceContext;
/*      */   
/*      */   private ErrorListener m_errorListener;
/*      */   SourceLocator m_sourceLocator;
/*      */   
/*      */   public XPathParser(ErrorListener errorListener, SourceLocator sourceLocator) {
/*   77 */     this.m_errorListener = errorListener;
/*   78 */     this.m_sourceLocator = sourceLocator;
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
/*      */   public void initXPath(Compiler compiler, String expression, PrefixResolver namespaceContext) throws TransformerException {
/*  103 */     this.m_ops = compiler;
/*  104 */     this.m_namespaceContext = namespaceContext;
/*      */     
/*  106 */     Lexer lexer = new Lexer(compiler, namespaceContext, this);
/*      */     
/*  108 */     lexer.tokenize(expression);
/*      */     
/*  110 */     this.m_ops.setOp(0, 1);
/*  111 */     this.m_ops.setOp(1, 2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  124 */     try { nextToken();
/*  125 */       Expr();
/*      */       
/*  127 */       if (null != this.m_token)
/*      */       
/*  129 */       { String extraTokens = "";
/*      */         
/*  131 */         while (null != this.m_token) {
/*      */           
/*  133 */           extraTokens = extraTokens + "'" + this.m_token + "'";
/*      */           
/*  135 */           nextToken();
/*      */           
/*  137 */           if (null != this.m_token) {
/*  138 */             extraTokens = extraTokens + ", ";
/*      */           }
/*      */         } 
/*  141 */         error("ER_EXTRA_ILLEGAL_TOKENS", new Object[] { extraTokens }); }  } catch (XPathProcessorException e)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  148 */       if ("CONTINUE_AFTER_FATAL_ERROR".equals(e.getMessage())) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  153 */         initXPath(compiler, "/..", namespaceContext);
/*      */       } else {
/*      */         
/*  156 */         throw e;
/*      */       }  }
/*      */     
/*  159 */     compiler.shrink();
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
/*      */   public void initMatchPattern(Compiler compiler, String expression, PrefixResolver namespaceContext) throws TransformerException {
/*  178 */     this.m_ops = compiler;
/*  179 */     this.m_namespaceContext = namespaceContext;
/*      */     
/*  181 */     Lexer lexer = new Lexer(compiler, namespaceContext, this);
/*      */     
/*  183 */     lexer.tokenize(expression);
/*      */     
/*  185 */     this.m_ops.setOp(0, 30);
/*  186 */     this.m_ops.setOp(1, 2);
/*      */     
/*  188 */     nextToken();
/*  189 */     Pattern();
/*      */     
/*  191 */     if (null != this.m_token) {
/*      */       
/*  193 */       String extraTokens = "";
/*      */       
/*  195 */       while (null != this.m_token) {
/*      */         
/*  197 */         extraTokens = extraTokens + "'" + this.m_token + "'";
/*      */         
/*  199 */         nextToken();
/*      */         
/*  201 */         if (null != this.m_token) {
/*  202 */           extraTokens = extraTokens + ", ";
/*      */         }
/*      */       } 
/*  205 */       error("ER_EXTRA_ILLEGAL_TOKENS", new Object[] { extraTokens });
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  210 */     this.m_ops.setOp(this.m_ops.getOp(1), -1);
/*  211 */     this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */     
/*  213 */     this.m_ops.shrink();
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
/*      */   public void setErrorHandler(ErrorListener handler) {
/*  233 */     this.m_errorListener = handler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErrorListener getErrorListener() {
/*  243 */     return this.m_errorListener;
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
/*      */   final boolean tokenIs(String s) {
/*  256 */     return (this.m_token != null) ? this.m_token.equals(s) : ((s == null));
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
/*      */   final boolean tokenIs(char c) {
/*  269 */     return (this.m_token != null) ? ((this.m_tokenChar == c)) : false;
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
/*      */   final boolean lookahead(char c, int n) {
/*      */     boolean bool;
/*  285 */     int pos = this.m_queueMark + n;
/*      */ 
/*      */     
/*  288 */     if (pos <= this.m_ops.getTokenQueueSize() && pos > 0 && this.m_ops.getTokenQueueSize() != 0) {
/*      */ 
/*      */       
/*  291 */       String tok = (String)this.m_ops.m_tokenQueue.elementAt(pos - 1);
/*      */       
/*  293 */       bool = (tok.length() == 1) ? ((tok.charAt(0) == c) ? true : false) : false;
/*      */     }
/*      */     else {
/*      */       
/*  297 */       bool = false;
/*      */     } 
/*      */     
/*  300 */     return bool;
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
/*      */   private final boolean lookbehind(char c, int n) {
/*      */     boolean bool;
/*  321 */     int lookBehindPos = this.m_queueMark - n + 1;
/*      */     
/*  323 */     if (lookBehindPos >= 0) {
/*      */       
/*  325 */       String lookbehind = (String)this.m_ops.m_tokenQueue.elementAt(lookBehindPos);
/*      */       
/*  327 */       if (lookbehind.length() == 1)
/*      */       {
/*  329 */         char c0 = (lookbehind == null) ? '|' : lookbehind.charAt(0);
/*      */         
/*  331 */         bool = (c0 == '|') ? false : ((c0 == c) ? true : false);
/*      */       }
/*      */       else
/*      */       {
/*  335 */         bool = false;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  340 */       bool = false;
/*      */     } 
/*      */     
/*  343 */     return bool;
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
/*      */   private final boolean lookbehindHasToken(int n) {
/*      */     boolean bool;
/*  363 */     if (this.m_queueMark - n > 0) {
/*      */       
/*  365 */       String lookbehind = (String)this.m_ops.m_tokenQueue.elementAt(this.m_queueMark - n - 1);
/*  366 */       char c0 = (lookbehind == null) ? '|' : lookbehind.charAt(0);
/*      */       
/*  368 */       bool = (c0 == '|') ? false : true;
/*      */     }
/*      */     else {
/*      */       
/*  372 */       bool = false;
/*      */     } 
/*      */     
/*  375 */     return bool;
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
/*      */   private final boolean lookahead(String s, int n) {
/*      */     boolean bool;
/*  394 */     if (this.m_queueMark + n <= this.m_ops.getTokenQueueSize()) {
/*      */       
/*  396 */       String lookahead = (String)this.m_ops.m_tokenQueue.elementAt(this.m_queueMark + n - 1);
/*      */       
/*  398 */       bool = (lookahead != null) ? lookahead.equals(s) : ((s == null) ? true : false);
/*      */     }
/*      */     else {
/*      */       
/*  402 */       bool = (null == s) ? true : false;
/*      */     } 
/*      */     
/*  405 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void nextToken() {
/*  415 */     if (this.m_queueMark < this.m_ops.getTokenQueueSize()) {
/*      */       
/*  417 */       this.m_token = (String)this.m_ops.m_tokenQueue.elementAt(this.m_queueMark++);
/*  418 */       this.m_tokenChar = this.m_token.charAt(0);
/*      */     }
/*      */     else {
/*      */       
/*  422 */       this.m_token = null;
/*  423 */       this.m_tokenChar = Character.MIN_VALUE;
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
/*      */   private final String getTokenRelative(int i) {
/*      */     String str;
/*  439 */     int relative = this.m_queueMark + i;
/*      */     
/*  441 */     if (relative > 0 && relative < this.m_ops.getTokenQueueSize()) {
/*      */       
/*  443 */       str = (String)this.m_ops.m_tokenQueue.elementAt(relative);
/*      */     }
/*      */     else {
/*      */       
/*  447 */       str = null;
/*      */     } 
/*      */     
/*  450 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void prevToken() {
/*  460 */     if (this.m_queueMark > 0) {
/*      */       
/*  462 */       this.m_queueMark--;
/*      */       
/*  464 */       this.m_token = (String)this.m_ops.m_tokenQueue.elementAt(this.m_queueMark);
/*  465 */       this.m_tokenChar = this.m_token.charAt(0);
/*      */     }
/*      */     else {
/*      */       
/*  469 */       this.m_token = null;
/*  470 */       this.m_tokenChar = Character.MIN_VALUE;
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
/*      */   
/*      */   private final void consumeExpected(String expected) throws TransformerException {
/*  486 */     if (tokenIs(expected)) {
/*      */       
/*  488 */       nextToken();
/*      */     }
/*      */     else {
/*      */       
/*  492 */       error("ER_EXPECTED_BUT_FOUND", new Object[] { expected, this.m_token });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  498 */       throw new XPathProcessorException("CONTINUE_AFTER_FATAL_ERROR");
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
/*      */   
/*      */   private final void consumeExpected(char expected) throws TransformerException {
/*  514 */     if (tokenIs(expected)) {
/*      */       
/*  516 */       nextToken();
/*      */     }
/*      */     else {
/*      */       
/*  520 */       error("ER_EXPECTED_BUT_FOUND", new Object[] { String.valueOf(expected), this.m_token });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  527 */       throw new XPathProcessorException("CONTINUE_AFTER_FATAL_ERROR");
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void warn(String msg, Object[] args) throws TransformerException {
/*  546 */     String fmsg = XPATHMessages.createXPATHWarning(msg, args);
/*  547 */     ErrorListener ehandler = getErrorListener();
/*      */     
/*  549 */     if (null != ehandler) {
/*      */ 
/*      */       
/*  552 */       ehandler.warning(new TransformerException(fmsg, this.m_sourceLocator));
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  557 */       System.err.println(fmsg);
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
/*      */   
/*      */   private void assertion(boolean b, String msg) {
/*  573 */     if (!b) {
/*      */       
/*  575 */       String fMsg = XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[] { msg });
/*      */ 
/*      */ 
/*      */       
/*  579 */       throw new RuntimeException(fMsg);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void error(String msg, Object[] args) throws TransformerException {
/*  599 */     String fmsg = XPATHMessages.createXPATHMessage(msg, args);
/*  600 */     ErrorListener ehandler = getErrorListener();
/*      */     
/*  602 */     TransformerException te = new TransformerException(fmsg, this.m_sourceLocator);
/*  603 */     if (null != ehandler) {
/*      */ 
/*      */       
/*  606 */       ehandler.fatalError(te);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  611 */       throw te;
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
/*      */   protected String dumpRemainingTokenQueue() {
/*      */     String str;
/*  625 */     int q = this.m_queueMark;
/*      */ 
/*      */     
/*  628 */     if (q < this.m_ops.getTokenQueueSize()) {
/*      */       
/*  630 */       String msg = "\n Remaining tokens: (";
/*      */       
/*  632 */       while (q < this.m_ops.getTokenQueueSize()) {
/*      */         
/*  634 */         String t = (String)this.m_ops.m_tokenQueue.elementAt(q++);
/*      */         
/*  636 */         msg = msg + " '" + t + "'";
/*      */       } 
/*      */       
/*  639 */       str = msg + ")";
/*      */     }
/*      */     else {
/*      */       
/*  643 */       str = "";
/*      */     } 
/*      */     
/*  646 */     return str;
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
/*      */   final int getFunctionToken(String key) {
/*      */     byte b;
/*      */     
/*  665 */     try { b = ((Integer)Keywords.m_functions.get(key)).intValue(); } catch (NullPointerException npe)
/*      */     
/*      */     { 
/*      */       
/*  669 */       b = -1; } catch (ClassCastException cce)
/*      */     
/*      */     { 
/*      */       
/*  673 */       b = -1; }
/*      */ 
/*      */     
/*  676 */     return b;
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
/*      */   void insertOp(int pos, int length, int op) {
/*  691 */     int totalLen = this.m_ops.getOp(1);
/*      */     
/*  693 */     for (int i = totalLen - 1; i >= pos; i--)
/*      */     {
/*  695 */       this.m_ops.setOp(i + length, this.m_ops.getOp(i));
/*      */     }
/*      */     
/*  698 */     this.m_ops.setOp(pos, op);
/*  699 */     this.m_ops.setOp(1, totalLen + length);
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
/*      */   void appendOp(int length, int op) {
/*  713 */     int totalLen = this.m_ops.getOp(1);
/*      */     
/*  715 */     this.m_ops.setOp(totalLen, op);
/*  716 */     this.m_ops.setOp(totalLen + 1, length);
/*  717 */     this.m_ops.setOp(1, totalLen + length);
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
/*      */   protected void Expr() throws TransformerException {
/*  732 */     OrExpr();
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
/*      */   protected void OrExpr() throws TransformerException {
/*  747 */     int opPos = this.m_ops.getOp(1);
/*      */     
/*  749 */     AndExpr();
/*      */     
/*  751 */     if (null != this.m_token && tokenIs("or")) {
/*      */       
/*  753 */       nextToken();
/*  754 */       insertOp(opPos, 2, 2);
/*  755 */       OrExpr();
/*      */       
/*  757 */       this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
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
/*      */ 
/*      */   
/*      */   protected void AndExpr() throws TransformerException {
/*  774 */     int opPos = this.m_ops.getOp(1);
/*      */     
/*  776 */     EqualityExpr(-1);
/*      */     
/*  778 */     if (null != this.m_token && tokenIs("and")) {
/*      */       
/*  780 */       nextToken();
/*  781 */       insertOp(opPos, 2, 3);
/*  782 */       AndExpr();
/*      */       
/*  784 */       this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int EqualityExpr(int addPos) throws TransformerException {
/*  807 */     int opPos = this.m_ops.getOp(1);
/*      */     
/*  809 */     if (-1 == addPos) {
/*  810 */       addPos = opPos;
/*      */     }
/*  812 */     RelationalExpr(-1);
/*      */     
/*  814 */     if (null != this.m_token)
/*      */     {
/*  816 */       if (tokenIs('!') && lookahead('=', 1)) {
/*      */         
/*  818 */         nextToken();
/*  819 */         nextToken();
/*  820 */         insertOp(addPos, 2, 4);
/*      */         
/*  822 */         int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
/*      */         
/*  824 */         addPos = EqualityExpr(addPos);
/*  825 */         this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
/*      */         
/*  827 */         addPos += 2;
/*      */       }
/*  829 */       else if (tokenIs('=')) {
/*      */         
/*  831 */         nextToken();
/*  832 */         insertOp(addPos, 2, 5);
/*      */         
/*  834 */         int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
/*      */         
/*  836 */         addPos = EqualityExpr(addPos);
/*  837 */         this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
/*      */         
/*  839 */         addPos += 2;
/*      */       } 
/*      */     }
/*      */     
/*  843 */     return addPos;
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
/*      */   protected int RelationalExpr(int addPos) throws TransformerException {
/*  867 */     int opPos = this.m_ops.getOp(1);
/*      */     
/*  869 */     if (-1 == addPos) {
/*  870 */       addPos = opPos;
/*      */     }
/*  872 */     AdditiveExpr(-1);
/*      */     
/*  874 */     if (null != this.m_token)
/*      */     {
/*  876 */       if (tokenIs('<')) {
/*      */         
/*  878 */         nextToken();
/*      */         
/*  880 */         if (tokenIs('=')) {
/*      */           
/*  882 */           nextToken();
/*  883 */           insertOp(addPos, 2, 6);
/*      */         }
/*      */         else {
/*      */           
/*  887 */           insertOp(addPos, 2, 7);
/*      */         } 
/*      */         
/*  890 */         int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
/*      */         
/*  892 */         addPos = RelationalExpr(addPos);
/*  893 */         this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
/*      */         
/*  895 */         addPos += 2;
/*      */       }
/*  897 */       else if (tokenIs('>')) {
/*      */         
/*  899 */         nextToken();
/*      */         
/*  901 */         if (tokenIs('=')) {
/*      */           
/*  903 */           nextToken();
/*  904 */           insertOp(addPos, 2, 8);
/*      */         }
/*      */         else {
/*      */           
/*  908 */           insertOp(addPos, 2, 9);
/*      */         } 
/*      */         
/*  911 */         int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
/*      */         
/*  913 */         addPos = RelationalExpr(addPos);
/*  914 */         this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
/*      */         
/*  916 */         addPos += 2;
/*      */       } 
/*      */     }
/*      */     
/*  920 */     return addPos;
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
/*      */   protected int AdditiveExpr(int addPos) throws TransformerException {
/*  942 */     int opPos = this.m_ops.getOp(1);
/*      */     
/*  944 */     if (-1 == addPos) {
/*  945 */       addPos = opPos;
/*      */     }
/*  947 */     MultiplicativeExpr(-1);
/*      */     
/*  949 */     if (null != this.m_token)
/*      */     {
/*  951 */       if (tokenIs('+')) {
/*      */         
/*  953 */         nextToken();
/*  954 */         insertOp(addPos, 2, 10);
/*      */         
/*  956 */         int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
/*      */         
/*  958 */         addPos = AdditiveExpr(addPos);
/*  959 */         this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
/*      */         
/*  961 */         addPos += 2;
/*      */       }
/*  963 */       else if (tokenIs('-')) {
/*      */         
/*  965 */         nextToken();
/*  966 */         insertOp(addPos, 2, 11);
/*      */         
/*  968 */         int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
/*      */         
/*  970 */         addPos = AdditiveExpr(addPos);
/*  971 */         this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
/*      */         
/*  973 */         addPos += 2;
/*      */       } 
/*      */     }
/*      */     
/*  977 */     return addPos;
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
/*      */   protected int MultiplicativeExpr(int addPos) throws TransformerException {
/* 1000 */     int opPos = this.m_ops.getOp(1);
/*      */     
/* 1002 */     if (-1 == addPos) {
/* 1003 */       addPos = opPos;
/*      */     }
/* 1005 */     UnaryExpr();
/*      */     
/* 1007 */     if (null != this.m_token)
/*      */     {
/* 1009 */       if (tokenIs('*')) {
/*      */         
/* 1011 */         nextToken();
/* 1012 */         insertOp(addPos, 2, 12);
/*      */         
/* 1014 */         int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
/*      */         
/* 1016 */         addPos = MultiplicativeExpr(addPos);
/* 1017 */         this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
/*      */         
/* 1019 */         addPos += 2;
/*      */       }
/* 1021 */       else if (tokenIs("div")) {
/*      */         
/* 1023 */         nextToken();
/* 1024 */         insertOp(addPos, 2, 13);
/*      */         
/* 1026 */         int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
/*      */         
/* 1028 */         addPos = MultiplicativeExpr(addPos);
/* 1029 */         this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
/*      */         
/* 1031 */         addPos += 2;
/*      */       }
/* 1033 */       else if (tokenIs("mod")) {
/*      */         
/* 1035 */         nextToken();
/* 1036 */         insertOp(addPos, 2, 14);
/*      */         
/* 1038 */         int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
/*      */         
/* 1040 */         addPos = MultiplicativeExpr(addPos);
/* 1041 */         this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
/*      */         
/* 1043 */         addPos += 2;
/*      */       }
/* 1045 */       else if (tokenIs("quo")) {
/*      */         
/* 1047 */         nextToken();
/* 1048 */         insertOp(addPos, 2, 15);
/*      */         
/* 1050 */         int opPlusLeftHandLen = this.m_ops.getOp(1) - addPos;
/*      */         
/* 1052 */         addPos = MultiplicativeExpr(addPos);
/* 1053 */         this.m_ops.setOp(addPos + 1, this.m_ops.getOp(addPos + opPlusLeftHandLen + 1) + opPlusLeftHandLen);
/*      */         
/* 1055 */         addPos += 2;
/*      */       } 
/*      */     }
/*      */     
/* 1059 */     return addPos;
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
/*      */   protected void UnaryExpr() throws TransformerException {
/* 1073 */     int opPos = this.m_ops.getOp(1);
/* 1074 */     boolean isNeg = false;
/*      */     
/* 1076 */     if (this.m_tokenChar == '-') {
/*      */       
/* 1078 */       nextToken();
/* 1079 */       appendOp(2, 16);
/*      */       
/* 1081 */       isNeg = true;
/*      */     } 
/*      */     
/* 1084 */     UnionExpr();
/*      */     
/* 1086 */     if (isNeg) {
/* 1087 */       this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
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
/*      */   protected void StringExpr() throws TransformerException {
/* 1101 */     int opPos = this.m_ops.getOp(1);
/*      */     
/* 1103 */     appendOp(2, 17);
/* 1104 */     Expr();
/*      */     
/* 1106 */     this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
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
/*      */   protected void BooleanExpr() throws TransformerException {
/* 1121 */     int opPos = this.m_ops.getOp(1);
/*      */     
/* 1123 */     appendOp(2, 18);
/* 1124 */     Expr();
/*      */     
/* 1126 */     int opLen = this.m_ops.getOp(1) - opPos;
/*      */     
/* 1128 */     if (opLen == 2)
/*      */     {
/* 1130 */       error("ER_BOOLEAN_ARG_NO_LONGER_OPTIONAL", null);
/*      */     }
/*      */     
/* 1133 */     this.m_ops.setOp(opPos + 1, opLen);
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
/*      */   protected void NumberExpr() throws TransformerException {
/* 1147 */     int opPos = this.m_ops.getOp(1);
/*      */     
/* 1149 */     appendOp(2, 19);
/* 1150 */     Expr();
/*      */     
/* 1152 */     this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
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
/*      */   protected void UnionExpr() throws TransformerException {
/* 1172 */     int opPos = this.m_ops.getOp(1);
/* 1173 */     boolean continueOrLoop = true;
/* 1174 */     boolean foundUnion = false;
/*      */ 
/*      */     
/*      */     while (true) {
/* 1178 */       PathExpr();
/*      */       
/* 1180 */       if (tokenIs('|'))
/*      */       
/* 1182 */       { if (false == foundUnion) {
/*      */           
/* 1184 */           foundUnion = true;
/*      */           
/* 1186 */           insertOp(opPos, 2, 20);
/*      */         } 
/*      */         
/* 1189 */         nextToken();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1198 */         if (!continueOrLoop)
/*      */           break;  continue; }  break;
/* 1200 */     }  this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
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
/*      */   protected void PathExpr() throws TransformerException {
/* 1218 */     int opPos = this.m_ops.getOp(1);
/*      */     
/* 1220 */     int filterExprMatch = FilterExpr();
/*      */     
/* 1222 */     if (filterExprMatch != 0) {
/*      */ 
/*      */ 
/*      */       
/* 1226 */       boolean locationPathStarted = (filterExprMatch == 2);
/*      */       
/* 1228 */       if (tokenIs('/')) {
/*      */         
/* 1230 */         nextToken();
/*      */         
/* 1232 */         if (!locationPathStarted) {
/*      */ 
/*      */           
/* 1235 */           insertOp(opPos, 2, 28);
/*      */           
/* 1237 */           locationPathStarted = true;
/*      */         } 
/*      */         
/* 1240 */         if (!RelativeLocationPath())
/*      */         {
/*      */           
/* 1243 */           error("ER_EXPECTED_REL_LOC_PATH", null);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1249 */       if (locationPathStarted)
/*      */       {
/* 1251 */         this.m_ops.setOp(this.m_ops.getOp(1), -1);
/* 1252 */         this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/* 1253 */         this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1259 */       LocationPath();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int FilterExpr() throws TransformerException {
/*      */     boolean bool;
/* 1283 */     int opPos = this.m_ops.getOp(1);
/*      */ 
/*      */ 
/*      */     
/* 1287 */     if (PrimaryExpr()) {
/*      */       
/* 1289 */       if (tokenIs('['))
/*      */       {
/*      */ 
/*      */         
/* 1293 */         insertOp(opPos, 2, 28);
/*      */         
/* 1295 */         while (tokenIs('['))
/*      */         {
/* 1297 */           Predicate();
/*      */         }
/*      */         
/* 1300 */         bool = true;
/*      */       }
/*      */       else
/*      */       {
/* 1304 */         bool = true;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1309 */       bool = false;
/*      */     } 
/*      */     
/* 1312 */     return bool;
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
/*      */   protected boolean PrimaryExpr() throws TransformerException {
/*      */     boolean bool;
/* 1340 */     int opPos = this.m_ops.getOp(1);
/*      */     
/* 1342 */     if (this.m_tokenChar == '\'' || this.m_tokenChar == '"') {
/*      */       
/* 1344 */       appendOp(2, 21);
/* 1345 */       Literal();
/*      */       
/* 1347 */       this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
/*      */ 
/*      */       
/* 1350 */       bool = true;
/*      */     }
/* 1352 */     else if (this.m_tokenChar == '$') {
/*      */       
/* 1354 */       nextToken();
/* 1355 */       appendOp(2, 22);
/* 1356 */       QName();
/*      */       
/* 1358 */       this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
/*      */ 
/*      */       
/* 1361 */       bool = true;
/*      */     }
/* 1363 */     else if (this.m_tokenChar == '(') {
/*      */       
/* 1365 */       nextToken();
/* 1366 */       appendOp(2, 23);
/* 1367 */       Expr();
/* 1368 */       consumeExpected(')');
/*      */       
/* 1370 */       this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
/*      */ 
/*      */       
/* 1373 */       bool = true;
/*      */     }
/* 1375 */     else if (null != this.m_token && (('.' == this.m_tokenChar && this.m_token.length() > 1 && Character.isDigit(this.m_token.charAt(1))) || Character.isDigit(this.m_tokenChar))) {
/*      */ 
/*      */       
/* 1378 */       appendOp(2, 27);
/* 1379 */       Number();
/*      */       
/* 1381 */       this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
/*      */ 
/*      */       
/* 1384 */       bool = true;
/*      */     }
/* 1386 */     else if (lookahead('(', 1) || (lookahead(':', 1) && lookahead('(', 3))) {
/*      */       
/* 1388 */       bool = FunctionCall();
/*      */     }
/*      */     else {
/*      */       
/* 1392 */       bool = false;
/*      */     } 
/*      */     
/* 1395 */     return bool;
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
/*      */   protected void Argument() throws TransformerException {
/* 1408 */     int opPos = this.m_ops.getOp(1);
/*      */     
/* 1410 */     appendOp(2, 26);
/* 1411 */     Expr();
/*      */     
/* 1413 */     this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
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
/*      */   protected boolean FunctionCall() throws TransformerException {
/* 1428 */     int opPos = this.m_ops.getOp(1);
/*      */     
/* 1430 */     if (lookahead(':', 1)) {
/*      */       
/* 1432 */       appendOp(4, 24);
/*      */       
/* 1434 */       this.m_ops.setOp(opPos + 1 + 1, this.m_queueMark - 1);
/*      */       
/* 1436 */       nextToken();
/* 1437 */       consumeExpected(':');
/*      */       
/* 1439 */       this.m_ops.setOp(opPos + 1 + 2, this.m_queueMark - 1);
/*      */       
/* 1441 */       nextToken();
/*      */     }
/*      */     else {
/*      */       
/* 1445 */       int funcTok = getFunctionToken(this.m_token);
/*      */       
/* 1447 */       if (-1 == funcTok)
/*      */       {
/* 1449 */         error("ER_COULDNOT_FIND_FUNCTION", new Object[] { this.m_token });
/*      */       }
/*      */ 
/*      */       
/* 1453 */       switch (funcTok) {
/*      */ 
/*      */         
/*      */         case 1030:
/*      */         case 1031:
/*      */         case 1032:
/*      */         case 1033:
/* 1460 */           return false;
/*      */       } 
/* 1462 */       appendOp(3, 25);
/*      */       
/* 1464 */       this.m_ops.setOp(opPos + 1 + 1, funcTok);
/*      */ 
/*      */       
/* 1467 */       nextToken();
/*      */     } 
/*      */     
/* 1470 */     consumeExpected('(');
/*      */     
/* 1472 */     while (!tokenIs(')') && this.m_token != null) {
/*      */       
/* 1474 */       if (tokenIs(','))
/*      */       {
/* 1476 */         error("ER_FOUND_COMMA_BUT_NO_PRECEDING_ARG", null);
/*      */       }
/*      */       
/* 1479 */       Argument();
/*      */       
/* 1481 */       if (!tokenIs(')')) {
/*      */         
/* 1483 */         consumeExpected(',');
/*      */         
/* 1485 */         if (tokenIs(')'))
/*      */         {
/* 1487 */           error("ER_FOUND_COMMA_BUT_NO_FOLLOWING_ARG", null);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1493 */     consumeExpected(')');
/*      */ 
/*      */     
/* 1496 */     this.m_ops.setOp(this.m_ops.getOp(1), -1);
/* 1497 */     this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/* 1498 */     this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
/*      */ 
/*      */     
/* 1501 */     return true;
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
/*      */   protected void LocationPath() throws TransformerException {
/* 1517 */     int opPos = this.m_ops.getOp(1);
/*      */ 
/*      */     
/* 1520 */     appendOp(2, 28);
/*      */     
/* 1522 */     boolean seenSlash = tokenIs('/');
/*      */     
/* 1524 */     if (seenSlash) {
/*      */       
/* 1526 */       appendOp(4, 50);
/*      */ 
/*      */       
/* 1529 */       this.m_ops.setOp(this.m_ops.getOp(1) - 2, 4);
/* 1530 */       this.m_ops.setOp(this.m_ops.getOp(1) - 1, 35);
/*      */       
/* 1532 */       nextToken();
/*      */     } 
/*      */     
/* 1535 */     if (this.m_token != null)
/*      */     {
/* 1537 */       if (!RelativeLocationPath() && !seenSlash)
/*      */       {
/*      */ 
/*      */         
/* 1541 */         error("ER_EXPECTED_LOC_PATH", new Object[] { this.m_token });
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1547 */     this.m_ops.setOp(this.m_ops.getOp(1), -1);
/* 1548 */     this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/* 1549 */     this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
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
/*      */   protected boolean RelativeLocationPath() throws TransformerException {
/* 1566 */     if (!Step())
/*      */     {
/* 1568 */       return false;
/*      */     }
/*      */     
/* 1571 */     while (tokenIs('/')) {
/*      */       
/* 1573 */       nextToken();
/*      */       
/* 1575 */       if (!Step())
/*      */       {
/*      */ 
/*      */         
/* 1579 */         error("ER_EXPECTED_LOC_STEP", null);
/*      */       }
/*      */     } 
/*      */     
/* 1583 */     return true;
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
/*      */   protected boolean Step() throws TransformerException {
/* 1597 */     int opPos = this.m_ops.getOp(1);
/*      */     
/* 1599 */     boolean doubleSlash = tokenIs('/');
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1604 */     if (doubleSlash) {
/*      */       
/* 1606 */       nextToken();
/*      */       
/* 1608 */       appendOp(2, 42);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1616 */       this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/* 1617 */       this.m_ops.setOp(this.m_ops.getOp(1), 1033);
/* 1618 */       this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */ 
/*      */       
/* 1621 */       this.m_ops.setOp(opPos + 1 + 1, this.m_ops.getOp(1) - opPos);
/*      */ 
/*      */ 
/*      */       
/* 1625 */       this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
/*      */ 
/*      */       
/* 1628 */       opPos = this.m_ops.getOp(1);
/*      */     } 
/*      */     
/* 1631 */     if (tokenIs(".")) {
/*      */       
/* 1633 */       nextToken();
/*      */       
/* 1635 */       if (tokenIs('['))
/*      */       {
/* 1637 */         error("ER_PREDICATE_ILLEGAL_SYNTAX", null);
/*      */       }
/*      */       
/* 1640 */       appendOp(4, 48);
/*      */ 
/*      */       
/* 1643 */       this.m_ops.setOp(this.m_ops.getOp(1) - 2, 4);
/* 1644 */       this.m_ops.setOp(this.m_ops.getOp(1) - 1, 1033);
/*      */     }
/* 1646 */     else if (tokenIs("..")) {
/*      */       
/* 1648 */       nextToken();
/* 1649 */       appendOp(4, 45);
/*      */ 
/*      */       
/* 1652 */       this.m_ops.setOp(this.m_ops.getOp(1) - 2, 4);
/* 1653 */       this.m_ops.setOp(this.m_ops.getOp(1) - 1, 1033);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1659 */     else if (tokenIs('*') || tokenIs('@') || tokenIs('_') || (this.m_token != null && Character.isLetter(this.m_token.charAt(0)))) {
/*      */ 
/*      */       
/* 1662 */       Basis();
/*      */       
/* 1664 */       while (tokenIs('['))
/*      */       {
/* 1666 */         Predicate();
/*      */       }
/*      */ 
/*      */       
/* 1670 */       this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1676 */       if (doubleSlash)
/*      */       {
/*      */         
/* 1679 */         error("ER_EXPECTED_LOC_STEP", null);
/*      */       }
/*      */       
/* 1682 */       return false;
/*      */     } 
/*      */     
/* 1685 */     return true;
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
/*      */   protected void Basis() throws TransformerException {
/*      */     byte b;
/* 1698 */     int opPos = this.m_ops.getOp(1);
/*      */ 
/*      */ 
/*      */     
/* 1702 */     if (lookahead("::", 1)) {
/*      */       
/* 1704 */       b = AxisName();
/*      */       
/* 1706 */       nextToken();
/* 1707 */       nextToken();
/*      */     }
/* 1709 */     else if (tokenIs('@')) {
/*      */       
/* 1711 */       b = 39;
/*      */       
/* 1713 */       appendOp(2, b);
/* 1714 */       nextToken();
/*      */     }
/*      */     else {
/*      */       
/* 1718 */       b = 40;
/*      */       
/* 1720 */       appendOp(2, b);
/*      */     } 
/*      */ 
/*      */     
/* 1724 */     this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */     
/* 1726 */     NodeTest(b);
/*      */ 
/*      */     
/* 1729 */     this.m_ops.setOp(opPos + 1 + 1, this.m_ops.getOp(1) - opPos);
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
/*      */   protected int AxisName() throws TransformerException {
/* 1745 */     Object val = Keywords.m_axisnames.get(this.m_token);
/*      */     
/* 1747 */     if (null == val)
/*      */     {
/* 1749 */       error("ER_ILLEGAL_AXIS_NAME", new Object[] { this.m_token });
/*      */     }
/*      */ 
/*      */     
/* 1753 */     int axesType = ((Integer)val).intValue();
/*      */     
/* 1755 */     appendOp(2, axesType);
/*      */     
/* 1757 */     return axesType;
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
/*      */   protected void NodeTest(int axesType) throws TransformerException {
/* 1773 */     if (lookahead('(', 1)) {
/*      */       
/* 1775 */       Object nodeTestOp = Keywords.m_nodetypes.get(this.m_token);
/*      */       
/* 1777 */       if (null == nodeTestOp)
/*      */       {
/* 1779 */         error("ER_UNKNOWN_NODETYPE", new Object[] { this.m_token });
/*      */       
/*      */       }
/*      */       else
/*      */       {
/* 1784 */         nextToken();
/*      */         
/* 1786 */         int nt = ((Integer)nodeTestOp).intValue();
/*      */         
/* 1788 */         this.m_ops.setOp(this.m_ops.getOp(1), nt);
/* 1789 */         this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */         
/* 1791 */         consumeExpected('(');
/*      */         
/* 1793 */         if (1032 == nt)
/*      */         {
/* 1795 */           if (!tokenIs(')'))
/*      */           {
/* 1797 */             Literal();
/*      */           }
/*      */         }
/*      */         
/* 1801 */         consumeExpected(')');
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1808 */       this.m_ops.setOp(this.m_ops.getOp(1), 34);
/* 1809 */       this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */       
/* 1811 */       if (lookahead(':', 1)) {
/*      */         
/* 1813 */         if (tokenIs('*')) {
/*      */           
/* 1815 */           this.m_ops.setOp(this.m_ops.getOp(1), -3);
/*      */         }
/*      */         else {
/*      */           
/* 1819 */           this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
/*      */ 
/*      */ 
/*      */           
/* 1823 */           if (!Character.isLetter(this.m_tokenChar) && !tokenIs('_'))
/*      */           {
/*      */             
/* 1826 */             error("ER_EXPECTED_NODE_TEST", null);
/*      */           }
/*      */         } 
/*      */         
/* 1830 */         nextToken();
/* 1831 */         consumeExpected(':');
/*      */       }
/*      */       else {
/*      */         
/* 1835 */         this.m_ops.setOp(this.m_ops.getOp(1), -2);
/*      */       } 
/*      */       
/* 1838 */       this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */       
/* 1840 */       if (tokenIs('*')) {
/*      */         
/* 1842 */         this.m_ops.setOp(this.m_ops.getOp(1), -3);
/*      */       }
/*      */       else {
/*      */         
/* 1846 */         if (49 == axesType) {
/*      */           
/* 1848 */           String prefix = (String)this.m_ops.m_tokenQueue.elementAt(this.m_queueMark - 1);
/* 1849 */           String namespace = this.m_namespaceContext.getNamespaceForPrefix(prefix);
/*      */ 
/*      */ 
/*      */           
/* 1853 */           this.m_ops.m_tokenQueue.setElementAt(namespace, this.m_queueMark - 1);
/*      */         } 
/*      */         
/* 1856 */         this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
/*      */ 
/*      */ 
/*      */         
/* 1860 */         if (!Character.isLetter(this.m_tokenChar) && !tokenIs('_'))
/*      */         {
/*      */           
/* 1863 */           error("ER_EXPECTED_NODE_TEST", null);
/*      */         }
/*      */       } 
/*      */       
/* 1867 */       this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */       
/* 1869 */       nextToken();
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
/*      */   protected void Predicate() throws TransformerException {
/* 1883 */     if (tokenIs('[')) {
/*      */       
/* 1885 */       nextToken();
/* 1886 */       PredicateExpr();
/* 1887 */       consumeExpected(']');
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
/*      */   protected void PredicateExpr() throws TransformerException {
/* 1901 */     int opPos = this.m_ops.getOp(1);
/*      */     
/* 1903 */     appendOp(2, 29);
/* 1904 */     Expr();
/*      */ 
/*      */     
/* 1907 */     this.m_ops.setOp(this.m_ops.getOp(1), -1);
/* 1908 */     this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/* 1909 */     this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
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
/*      */   protected void QName() throws TransformerException {
/* 1923 */     if (lookahead(':', 1)) {
/*      */       
/* 1925 */       this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
/* 1926 */       this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */       
/* 1928 */       nextToken();
/* 1929 */       consumeExpected(':');
/*      */     }
/*      */     else {
/*      */       
/* 1933 */       this.m_ops.setOp(this.m_ops.getOp(1), -2);
/* 1934 */       this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */     } 
/*      */ 
/*      */     
/* 1938 */     this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
/* 1939 */     this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */     
/* 1941 */     nextToken();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void NCName() {
/* 1951 */     this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
/* 1952 */     this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */     
/* 1954 */     nextToken();
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
/*      */   protected void Literal() throws TransformerException {
/* 1970 */     int last = this.m_token.length() - 1;
/* 1971 */     char c0 = this.m_tokenChar;
/* 1972 */     char cX = this.m_token.charAt(last);
/*      */     
/* 1974 */     if ((c0 == '"' && cX == '"') || (c0 == '\'' && cX == '\'')) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1979 */       int tokenQueuePos = this.m_queueMark - 1;
/*      */       
/* 1981 */       this.m_ops.m_tokenQueue.setElementAt(null, tokenQueuePos);
/*      */       
/* 1983 */       Object obj = new XString(this.m_token.substring(1, last));
/*      */       
/* 1985 */       this.m_ops.m_tokenQueue.setElementAt(obj, tokenQueuePos);
/*      */ 
/*      */       
/* 1988 */       this.m_ops.setOp(this.m_ops.getOp(1), tokenQueuePos);
/* 1989 */       this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */       
/* 1991 */       nextToken();
/*      */     }
/*      */     else {
/*      */       
/* 1995 */       error("ER_PATTERN_LITERAL_NEEDS_BE_QUOTED", new Object[] { this.m_token });
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
/*      */   protected void Number() throws TransformerException {
/* 2010 */     if (null != this.m_token) {
/*      */       double d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2020 */       try { if (this.m_token.indexOf('e') > -1 || this.m_token.indexOf('E') > -1)
/* 2021 */           throw new NumberFormatException(); 
/* 2022 */         d = Double.valueOf(this.m_token).doubleValue(); } catch (NumberFormatException nfe)
/*      */       
/*      */       { 
/*      */         
/* 2026 */         d = 0.0D;
/*      */         
/* 2028 */         error("ER_COULDNOT_BE_FORMATTED_TO_NUMBER", new Object[] { this.m_token }); }
/*      */ 
/*      */ 
/*      */       
/* 2032 */       this.m_ops.m_tokenQueue.setElementAt(new XNumber(d), this.m_queueMark - 1);
/* 2033 */       this.m_ops.setOp(this.m_ops.getOp(1), this.m_queueMark - 1);
/* 2034 */       this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */       
/* 2036 */       nextToken();
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
/*      */ 
/*      */ 
/*      */   
/*      */   protected void Pattern() throws TransformerException {
/*      */     while (true) {
/* 2055 */       LocationPathPattern();
/*      */       
/* 2057 */       if (tokenIs('|')) {
/*      */         
/* 2059 */         nextToken();
/*      */         continue;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void LocationPathPattern() throws TransformerException {
/* 2081 */     int opPos = this.m_ops.getOp(1);
/*      */     
/* 2083 */     int RELATIVE_PATH_NOT_PERMITTED = 0;
/* 2084 */     int RELATIVE_PATH_PERMITTED = 1;
/* 2085 */     int RELATIVE_PATH_REQUIRED = 2;
/*      */     
/* 2087 */     int relativePathStatus = 0;
/*      */     
/* 2089 */     appendOp(2, 31);
/*      */     
/* 2091 */     if (lookahead('(', 1) && (tokenIs("id") || tokenIs("key"))) {
/*      */ 
/*      */ 
/*      */       
/* 2095 */       IdKeyPattern();
/*      */       
/* 2097 */       if (tokenIs('/'))
/*      */       {
/* 2099 */         nextToken();
/*      */         
/* 2101 */         if (tokenIs('/')) {
/*      */           
/* 2103 */           appendOp(4, 52);
/*      */           
/* 2105 */           nextToken();
/*      */         }
/*      */         else {
/*      */           
/* 2109 */           appendOp(4, 53);
/*      */         } 
/*      */ 
/*      */         
/* 2113 */         this.m_ops.setOp(this.m_ops.getOp(1) - 2, 4);
/* 2114 */         this.m_ops.setOp(this.m_ops.getOp(1) - 1, 1034);
/*      */         
/* 2116 */         relativePathStatus = 2;
/*      */       }
/*      */     
/* 2119 */     } else if (tokenIs('/')) {
/*      */       
/* 2121 */       if (lookahead('/', 1)) {
/*      */         
/* 2123 */         appendOp(4, 52);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2129 */         nextToken();
/*      */         
/* 2131 */         relativePathStatus = 2;
/*      */       }
/*      */       else {
/*      */         
/* 2135 */         appendOp(4, 50);
/*      */         
/* 2137 */         relativePathStatus = 1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2142 */       this.m_ops.setOp(this.m_ops.getOp(1) - 2, 4);
/* 2143 */       this.m_ops.setOp(this.m_ops.getOp(1) - 1, 35);
/*      */       
/* 2145 */       nextToken();
/*      */     }
/*      */     else {
/*      */       
/* 2149 */       relativePathStatus = 2;
/*      */     } 
/*      */     
/* 2152 */     if (relativePathStatus != 0)
/*      */     {
/* 2154 */       if (!tokenIs('|') && null != this.m_token) {
/*      */         
/* 2156 */         RelativePathPattern();
/*      */       }
/* 2158 */       else if (relativePathStatus == 2) {
/*      */ 
/*      */         
/* 2161 */         error("ER_EXPECTED_REL_PATH_PATTERN", null);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2166 */     this.m_ops.setOp(this.m_ops.getOp(1), -1);
/* 2167 */     this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/* 2168 */     this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
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
/*      */   protected void IdKeyPattern() throws TransformerException {
/* 2183 */     FunctionCall();
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
/*      */   protected void RelativePathPattern() throws TransformerException {
/* 2200 */     boolean trailingSlashConsumed = StepPattern(false);
/*      */     
/* 2202 */     while (tokenIs('/')) {
/*      */       
/* 2204 */       nextToken();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2209 */       trailingSlashConsumed = StepPattern(!trailingSlashConsumed);
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
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean StepPattern(boolean isLeadingSlashPermitted) throws TransformerException {
/* 2227 */     return AbbreviatedNodeTestStep(isLeadingSlashPermitted);
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
/*      */   protected boolean AbbreviatedNodeTestStep(boolean isLeadingSlashPermitted) throws TransformerException {
/*      */     byte b;
/*      */     boolean bool;
/* 2245 */     int opPos = this.m_ops.getOp(1);
/*      */ 
/*      */ 
/*      */     
/* 2249 */     int matchTypePos = -1;
/*      */     
/* 2251 */     if (tokenIs('@')) {
/*      */       
/* 2253 */       b = 51;
/*      */       
/* 2255 */       appendOp(2, b);
/* 2256 */       nextToken();
/*      */     }
/* 2258 */     else if (lookahead("::", 1)) {
/*      */       
/* 2260 */       if (tokenIs("attribute")) {
/*      */         
/* 2262 */         b = 51;
/*      */         
/* 2264 */         appendOp(2, b);
/*      */       }
/* 2266 */       else if (tokenIs("child")) {
/*      */         
/* 2268 */         matchTypePos = this.m_ops.getOp(1);
/* 2269 */         b = 53;
/*      */         
/* 2271 */         appendOp(2, b);
/*      */       }
/*      */       else {
/*      */         
/* 2275 */         b = -1;
/*      */         
/* 2277 */         error("ER_AXES_NOT_ALLOWED", new Object[] { this.m_token });
/*      */       } 
/*      */ 
/*      */       
/* 2281 */       nextToken();
/* 2282 */       nextToken();
/*      */     }
/* 2284 */     else if (tokenIs('/')) {
/*      */       
/* 2286 */       if (!isLeadingSlashPermitted)
/*      */       {
/*      */         
/* 2289 */         error("ER_EXPECTED_STEP_PATTERN", null);
/*      */       }
/* 2291 */       b = 52;
/*      */       
/* 2293 */       appendOp(2, b);
/* 2294 */       nextToken();
/*      */     }
/*      */     else {
/*      */       
/* 2298 */       matchTypePos = this.m_ops.getOp(1);
/* 2299 */       b = 53;
/*      */       
/* 2301 */       appendOp(2, b);
/*      */     } 
/*      */ 
/*      */     
/* 2305 */     this.m_ops.setOp(1, this.m_ops.getOp(1) + 1);
/*      */     
/* 2307 */     NodeTest(b);
/*      */ 
/*      */     
/* 2310 */     this.m_ops.setOp(opPos + 1 + 1, this.m_ops.getOp(1) - opPos);
/*      */ 
/*      */     
/* 2313 */     while (tokenIs('['))
/*      */     {
/* 2315 */       Predicate();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2332 */     if (matchTypePos > -1 && tokenIs('/') && lookahead('/', 1)) {
/*      */       
/* 2334 */       this.m_ops.setOp(matchTypePos, 52);
/*      */       
/* 2336 */       nextToken();
/*      */       
/* 2338 */       bool = true;
/*      */     }
/*      */     else {
/*      */       
/* 2342 */       bool = false;
/*      */     } 
/*      */ 
/*      */     
/* 2346 */     this.m_ops.setOp(opPos + 1, this.m_ops.getOp(1) - opPos);
/*      */ 
/*      */     
/* 2349 */     return bool;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/compiler/XPathParser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */