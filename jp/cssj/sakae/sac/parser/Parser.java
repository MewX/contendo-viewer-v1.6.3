/*      */ package jp.cssj.sakae.sac.parser;
/*      */ 
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.net.URL;
/*      */ import java.util.HashMap;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.MissingResourceException;
/*      */ import java.util.StringTokenizer;
/*      */ import jp.cssj.sakae.sac.css.AttributeCondition;
/*      */ import jp.cssj.sakae.sac.css.CSSException;
/*      */ import jp.cssj.sakae.sac.css.CSSParseException;
/*      */ import jp.cssj.sakae.sac.css.CombinatorCondition;
/*      */ import jp.cssj.sakae.sac.css.Condition;
/*      */ import jp.cssj.sakae.sac.css.ConditionFactory;
/*      */ import jp.cssj.sakae.sac.css.ConditionalSelector;
/*      */ import jp.cssj.sakae.sac.css.DescendantSelector;
/*      */ import jp.cssj.sakae.sac.css.DocumentHandler;
/*      */ import jp.cssj.sakae.sac.css.ElementSelector;
/*      */ import jp.cssj.sakae.sac.css.ErrorHandler;
/*      */ import jp.cssj.sakae.sac.css.InputSource;
/*      */ import jp.cssj.sakae.sac.css.LangCondition;
/*      */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*      */ import jp.cssj.sakae.sac.css.SACMediaList;
/*      */ import jp.cssj.sakae.sac.css.Selector;
/*      */ import jp.cssj.sakae.sac.css.SelectorFactory;
/*      */ import jp.cssj.sakae.sac.css.SelectorList;
/*      */ import jp.cssj.sakae.sac.css.SiblingSelector;
/*      */ import jp.cssj.sakae.sac.css.SimpleSelector;
/*      */ import jp.cssj.sakae.sac.i18n.Localizable;
/*      */ import jp.cssj.sakae.sac.i18n.LocalizableSupport;
/*      */ import jp.cssj.sakae.sac.util.EncodingUtilities;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Parser
/*      */   implements Localizable, ExtendedParser
/*      */ {
/*   94 */   public static final String BUNDLE_CLASSNAME = Parser.class.getPackage().getName() + ".resources.Messages";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   99 */   protected LocalizableSupport localizableSupport = new LocalizableSupport(BUNDLE_CLASSNAME, Parser.class
/*  100 */       .getClassLoader());
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Scanner scanner;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int current;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  115 */   protected DocumentHandler documentHandler = DefaultDocumentHandler.INSTANCE;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  120 */   protected SelectorFactory selectorFactory = DefaultSelectorFactory.INSTANCE;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  125 */   protected ConditionFactory conditionFactory = DefaultConditionFactory.INSTANCE;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  130 */   protected ErrorHandler errorHandler = DefaultErrorHandler.INSTANCE;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String pseudoElement;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String documentURI;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  145 */   protected Map<String, String> prefixToURI = new HashMap<>();
/*      */   
/*  147 */   protected String defaultCharset = "UTF-8";
/*      */   
/*      */   public String getDefaultCharset() {
/*  150 */     return this.defaultCharset;
/*      */   }
/*      */   
/*      */   public void setDefaultCharset(String defaultCharset) {
/*  154 */     this.defaultCharset = defaultCharset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getParserVersion() {
/*  164 */     return "http://www.w3.org/TR/REC-CSS2";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocale(Locale locale) throws CSSException {
/*  172 */     this.localizableSupport.setLocale(locale);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Locale getLocale() {
/*  179 */     return this.localizableSupport.getLocale();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String formatMessage(String key, Object[] args) throws MissingResourceException {
/*  187 */     return this.localizableSupport.formatMessage(key, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentHandler(DocumentHandler handler) {
/*  195 */     this.documentHandler = handler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSelectorFactory(SelectorFactory factory) {
/*  203 */     this.selectorFactory = factory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConditionFactory(ConditionFactory factory) {
/*  211 */     this.conditionFactory = factory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setErrorHandler(ErrorHandler handler) {
/*  219 */     this.errorHandler = handler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseStyleSheet(InputSource source) throws CSSException, IOException {
/*      */     boolean checkCharset;
/*  228 */     this.documentURI = source.getURI();
/*  229 */     if (this.documentURI == null) {
/*  230 */       this.documentURI = "";
/*      */     }
/*      */     
/*  233 */     InputStream is = null;
/*  234 */     Reader r = source.getCharacterStream();
/*  235 */     if (r != null) {
/*  236 */       this.scanner = new Scanner(r);
/*  237 */       checkCharset = false;
/*      */     } else {
/*  239 */       is = source.getByteStream();
/*  240 */       if (is == null) {
/*  241 */         String uri = source.getURI();
/*  242 */         if (uri == null) {
/*  243 */           throw new CSSException(formatMessage("empty.source", null));
/*      */         }
/*      */         
/*  246 */         URL url = new URL(uri);
/*  247 */         is = url.openStream();
/*      */       } 
/*  249 */       String charset = EncodingUtilities.javaEncoding(source.getEncoding());
/*  250 */       checkCharset = (charset == null);
/*  251 */       if (checkCharset) {
/*  252 */         if (!is.markSupported()) {
/*  253 */           is = new BufferedInputStream(is);
/*      */         }
/*  255 */         is.mark(8192);
/*      */       } 
/*  257 */       if (charset == null) {
/*  258 */         charset = "UTF-8";
/*      */       }
/*      */       try {
/*  261 */         r = new InputStreamReader(is, charset);
/*  262 */       } catch (UnsupportedEncodingException e) {
/*  263 */         r = new InputStreamReader(is, "UTF-8");
/*      */       } 
/*  265 */       this.scanner = new Scanner(r);
/*      */     } 
/*      */     
/*      */     try {
/*  269 */       parseStyleSheet(source, false, checkCharset);
/*  270 */     } catch (ChangeCharacterEncodingException e) {
/*      */       try {
/*  272 */         is.reset();
/*      */         try {
/*  274 */           r = new InputStreamReader(is, e.getCharacterEncoding());
/*  275 */         } catch (UnsupportedEncodingException e1) {
/*  276 */           r = new InputStreamReader(is, "UTF-8");
/*      */         } 
/*  278 */         this.scanner = new Scanner(r);
/*  279 */         parseStyleSheet(source, true, false);
/*  280 */       } catch (ChangeCharacterEncodingException changeCharacterEncodingException) {}
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseStyleSheet(InputSource source, boolean rescan, boolean checkCharset) throws CSSException, IOException, ChangeCharacterEncodingException {
/*  288 */     boolean reset = false;
/*      */     try {
/*  290 */       if (!rescan) {
/*  291 */         this.documentHandler.startDocument(source);
/*      */       }
/*      */       
/*      */       try {
/*  295 */         this.current = this.scanner.next();
/*  296 */       } catch (ParseException e) {
/*  297 */         throw new CSSException();
/*      */       } 
/*  299 */       skipSpacesAndCDOCDC();
/*      */       
/*  301 */       if (this.current == 31) {
/*  302 */         if (nextIgnoreSpaces() != 20) {
/*  303 */           reportError("charset.string");
/*      */         } else {
/*  305 */           String charset = EncodingUtilities.javaEncoding(this.scanner.getStringValue());
/*  306 */           if (charset != null && checkCharset) {
/*  307 */             reset = true;
/*  308 */             throw new ChangeCharacterEncodingException(charset);
/*      */           } 
/*  310 */           if (nextIgnoreSpaces() != 8) {
/*  311 */             reportError("semicolon");
/*      */           }
/*  313 */           next();
/*      */         } 
/*      */       }
/*      */       
/*  317 */       skipSpacesAndCDOCDC();
/*      */       
/*  319 */       while (this.current == 29) {
/*  320 */         nextIgnoreSpaces();
/*  321 */         parseImportRule();
/*  322 */         nextIgnoreSpaces();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       while (true) {
/*  329 */         switch (this.current) {
/*      */           case 35:
/*  331 */             nextIgnoreSpaces();
/*  332 */             parseNamespaceRule();
/*      */             break;
/*      */           case 34:
/*  335 */             nextIgnoreSpaces();
/*  336 */             parsePageRule();
/*      */             break;
/*      */           case 33:
/*  339 */             nextIgnoreSpaces();
/*  340 */             parseMediaRule();
/*      */             break;
/*      */           case 32:
/*  343 */             nextIgnoreSpaces();
/*  344 */             parseFontFaceRule();
/*      */             break;
/*      */           case 30:
/*  347 */             nextIgnoreSpaces();
/*  348 */             parseAtRule();
/*      */             break;
/*      */           case 0:
/*      */             break;
/*      */           default:
/*  353 */             parseRuleSet(); break;
/*      */         } 
/*  355 */         skipSpacesAndCDOCDC();
/*      */       } 
/*      */     } finally {
/*  358 */       if (!reset) {
/*  359 */         this.documentHandler.endDocument(source);
/*      */       }
/*  361 */       this.scanner = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseStyleSheet(String uri) throws CSSException, IOException {
/*  370 */     parseStyleSheet(new InputSource(uri));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseStyleDeclaration(InputSource source) throws CSSException, IOException {
/*  378 */     this.scanner = createScanner(source);
/*  379 */     parseStyleDeclarationInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseStyleDeclarationInternal() throws CSSException, IOException {
/*  386 */     nextIgnoreSpaces();
/*      */     try {
/*  388 */       parseStyleDeclaration(false);
/*      */     } finally {
/*  390 */       this.scanner = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseRule(InputSource source) throws CSSException, IOException {
/*  399 */     this.scanner = createScanner(source);
/*  400 */     parseRuleInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseRuleInternal() throws CSSException, IOException {
/*  407 */     nextIgnoreSpaces();
/*  408 */     parseRule();
/*  409 */     this.scanner = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SelectorList parseSelectors(InputSource source) throws CSSException, IOException {
/*  417 */     this.scanner = createScanner(source);
/*  418 */     return parseSelectorsInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SelectorList parseSelectorsInternal() throws CSSException, IOException {
/*  425 */     nextIgnoreSpaces();
/*  426 */     SelectorList ret = parseSelectorList();
/*  427 */     this.scanner = null;
/*  428 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public LexicalUnit parsePropertyValue(InputSource source) throws CSSException, IOException {
/*  436 */     this.scanner = createScanner(source);
/*  437 */     return parsePropertyValueInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LexicalUnit parsePropertyValueInternal() throws CSSException, IOException {
/*  444 */     nextIgnoreSpaces();
/*      */     
/*  446 */     LexicalUnit exp = null;
/*      */     
/*      */     try {
/*  449 */       exp = parseExpression(false);
/*  450 */     } catch (CSSParseException e) {
/*  451 */       reportError(e);
/*  452 */       skipSemiColon();
/*  453 */       throw e;
/*      */     } 
/*      */     
/*  456 */     CSSParseException exception = null;
/*  457 */     if (this.current != 0) {
/*  458 */       exception = createCSSParseException("eof.expected");
/*      */     }
/*  460 */     this.scanner = null;
/*      */     
/*  462 */     if (exception != null) {
/*  463 */       this.errorHandler.fatalError(exception);
/*      */     }
/*  465 */     return exp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean parsePriority(InputSource source) throws CSSException, IOException {
/*  473 */     this.scanner = createScanner(source);
/*  474 */     return parsePriorityInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean parsePriorityInternal() throws CSSException, IOException {
/*  481 */     nextIgnoreSpaces();
/*      */     
/*  483 */     this.scanner = null;
/*      */     
/*  485 */     switch (this.current) {
/*      */       case 0:
/*  487 */         return false;
/*      */       case 29:
/*  489 */         return true;
/*      */     } 
/*  491 */     reportError("token", new Object[] { new Integer(this.current) });
/*  492 */     skipSemiColon();
/*  493 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseRule() throws IOException {
/*  501 */     switch (this.scanner.getType()) {
/*      */       case 29:
/*  503 */         nextIgnoreSpaces();
/*  504 */         parseImportRule();
/*      */         return;
/*      */       case 30:
/*  507 */         nextIgnoreSpaces();
/*  508 */         parseAtRule();
/*      */         return;
/*      */       case 32:
/*  511 */         nextIgnoreSpaces();
/*  512 */         parseFontFaceRule();
/*      */         return;
/*      */       case 33:
/*  515 */         nextIgnoreSpaces();
/*  516 */         parseMediaRule();
/*      */         return;
/*      */       case 34:
/*  519 */         nextIgnoreSpaces();
/*  520 */         parsePageRule();
/*      */         return;
/*      */       case 35:
/*  523 */         nextIgnoreSpaces();
/*  524 */         parseNamespaceRule();
/*      */         return;
/*      */     } 
/*  527 */     parseRuleSet();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseAtRule() throws IOException {
/*  535 */     this.scanner.scanAtRule();
/*  536 */     this.documentHandler.ignorableAtRule(this.scanner.getStringValue());
/*  537 */     nextIgnoreSpaces();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseImportRule() throws IOException {
/*      */     CSSSACMediaList ml;
/*  544 */     String uri = null;
/*  545 */     switch (this.current) {
/*      */       default:
/*  547 */         reportError("string.or.uri"); return;
/*      */       case 20:
/*      */       case 53:
/*      */         break;
/*  551 */     }  uri = this.scanner.getStringValue();
/*  552 */     nextIgnoreSpaces();
/*      */ 
/*      */ 
/*      */     
/*  556 */     if (this.current != 21) {
/*  557 */       ml = new CSSSACMediaList();
/*  558 */       ml.append("all");
/*      */     } else {
/*  560 */       ml = parseMediaList();
/*      */     } 
/*      */     
/*  563 */     this.documentHandler.importStyle(uri, ml, null);
/*      */     
/*  565 */     if (this.current != 8) {
/*  566 */       reportError("semicolon");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseNamespaceRule() throws IOException {
/*  574 */     String prefix = "";
/*  575 */     String uri = "";
/*  576 */     switch (this.current) {
/*      */       default:
/*  578 */         reportError("string.or.uri.or.identifer");
/*      */         return;
/*      */       case 20:
/*      */       case 53:
/*  582 */         uri = this.scanner.getStringValue();
/*  583 */         nextIgnoreSpaces();
/*      */         break;
/*      */       
/*      */       case 21:
/*  587 */         prefix = this.scanner.getStringValue();
/*  588 */         nextIgnoreSpaces();
/*  589 */         switch (this.current) {
/*      */           default:
/*  591 */             reportError("string.or.uri"); return;
/*      */           case 20:
/*      */           case 53:
/*      */             break;
/*  595 */         }  uri = this.scanner.getStringValue();
/*  596 */         nextIgnoreSpaces();
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  602 */     if (this.current != 8) {
/*  603 */       reportError("semicolon");
/*      */     } else {
/*  605 */       next();
/*      */     } 
/*      */     
/*  608 */     this.prefixToURI.put(prefix, uri);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CSSSACMediaList parseMediaList() throws IOException {
/*  615 */     CSSSACMediaList result = new CSSSACMediaList();
/*  616 */     result.append(this.scanner.getStringValue());
/*  617 */     nextIgnoreSpaces();
/*      */     
/*  619 */     while (this.current == 6) {
/*  620 */       nextIgnoreSpaces();
/*      */       
/*  622 */       switch (this.current) {
/*      */ 
/*      */ 
/*      */         
/*      */         case 21:
/*  627 */           result.append(this.scanner.getStringValue());
/*  628 */           nextIgnoreSpaces(); break;
/*      */       } 
/*      */     } 
/*  631 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseFontFaceRule() throws IOException {
/*      */     try {
/*  639 */       this.documentHandler.startFontFace();
/*      */       
/*  641 */       if (this.current != 1) {
/*  642 */         reportError("left.curly.brace");
/*  643 */         skipRightCurlyBrace();
/*      */       } else {
/*  645 */         nextIgnoreSpaces();
/*  646 */         parseStyleDeclaration(true);
/*      */       } 
/*      */     } finally {
/*  649 */       this.documentHandler.endFontFace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parsePageRule() throws IOException {
/*  657 */     String page = null;
/*  658 */     String ppage = null;
/*      */     
/*  660 */     if (this.current == 21) {
/*  661 */       page = this.scanner.getStringValue();
/*  662 */       nextIgnoreSpaces();
/*      */     } 
/*  664 */     if (this.current == 16) {
/*  665 */       nextIgnoreSpaces();
/*      */       
/*  667 */       if (this.current != 21) {
/*  668 */         reportError("identifier");
/*  669 */         skipRightCurlyBrace();
/*      */         return;
/*      */       } 
/*  672 */       ppage = this.scanner.getStringValue();
/*  673 */       nextIgnoreSpaces();
/*      */     } 
/*      */     
/*      */     try {
/*  677 */       this.documentHandler.startPage(page, ppage);
/*      */       
/*  679 */       if (this.current != 1) {
/*  680 */         reportError("left.curly.brace");
/*  681 */         skipRightCurlyBrace();
/*      */       } else {
/*  683 */         nextIgnoreSpaces();
/*  684 */         parseStyleDeclaration(true);
/*      */       } 
/*      */     } finally {
/*  687 */       this.documentHandler.endPage(page, ppage);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parsePageContentRule() throws IOException {
/*  695 */     String page = null;
/*      */     
/*  697 */     if (this.current == 21) {
/*  698 */       page = this.scanner.getStringValue();
/*  699 */       nextIgnoreSpaces();
/*      */     } 
/*      */     
/*      */     try {
/*  703 */       this.documentHandler.startPage(page, "-cssj-page-content");
/*      */       
/*  705 */       if (this.current != 1) {
/*  706 */         reportError("left.curly.brace");
/*  707 */         skipRightCurlyBrace();
/*      */       } else {
/*  709 */         nextIgnoreSpaces();
/*  710 */         parseStyleDeclaration(true);
/*      */       } 
/*      */     } finally {
/*  713 */       this.documentHandler.endPage(page, "-cssj-page-content");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseMediaRule() throws IOException {
/*  721 */     if (this.current != 21) {
/*  722 */       skipLeftCurlyBrace();
/*  723 */       reportError("identifier");
/*  724 */       skipRightCurlyBrace();
/*      */       
/*      */       return;
/*      */     } 
/*  728 */     CSSSACMediaList ml = parseMediaList();
/*      */     try {
/*  730 */       this.documentHandler.startMedia(ml);
/*      */       
/*  732 */       if (this.current != 1) {
/*  733 */         skipLeftCurlyBrace();
/*  734 */         reportError("left.curly.brace");
/*  735 */         skipRightCurlyBrace();
/*      */       } else {
/*  737 */         nextIgnoreSpaces();
/*      */         
/*      */         while (true) {
/*  740 */           switch (this.current) {
/*      */             case 0:
/*      */             case 2:
/*      */               break;
/*      */           } 
/*  745 */           parseRuleSet();
/*      */         } 
/*      */ 
/*      */         
/*  749 */         nextIgnoreSpaces();
/*      */       } 
/*      */     } finally {
/*  752 */       this.documentHandler.endMedia(ml);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void skipLeftCurlyBrace() throws IOException {
/*  757 */     while (this.current != 1) {
/*  758 */       if (this.current == 0) {
/*      */         return;
/*      */       }
/*  761 */       nextIgnoreSpaces();
/*      */     } 
/*  763 */     nextIgnoreSpaces();
/*      */   }
/*      */   
/*      */   private void skipRightCurlyBrace() throws IOException {
/*  767 */     int cbraces = 1;
/*      */     while (true) {
/*  769 */       switch (this.current) {
/*      */         case 0:
/*      */           return;
/*      */         case 2:
/*  773 */           if (--cbraces == 0) {
/*  774 */             nextIgnoreSpaces();
/*      */             return;
/*      */           } 
/*      */           break;
/*      */         case 1:
/*  779 */           cbraces++; break;
/*      */       } 
/*  781 */       nextIgnoreSpaces();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void skipSemiColon() throws IOException {
/*  786 */     int cbraces = 1;
/*      */     while (true) {
/*  788 */       switch (this.current) {
/*      */         case 0:
/*      */           return;
/*      */         case 8:
/*  792 */           if (cbraces - 1 == 0) {
/*  793 */             nextIgnoreSpaces();
/*      */             return;
/*      */           } 
/*      */           break;
/*      */         case 2:
/*  798 */           if (--cbraces == 0) {
/*      */             return;
/*      */           }
/*      */           break;
/*      */         case 1:
/*  803 */           cbraces++; break;
/*      */       } 
/*  805 */       nextIgnoreSpaces();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseRuleSet() throws IOException {
/*  813 */     SelectorList sl = null;
/*      */     
/*      */     try {
/*  816 */       sl = parseSelectorList();
/*  817 */     } catch (CSSParseException e) {
/*  818 */       skipLeftCurlyBrace();
/*  819 */       reportError(e);
/*  820 */       skipRightCurlyBrace();
/*      */       
/*      */       return;
/*      */     } 
/*  824 */     if (this.current != 1) {
/*  825 */       skipLeftCurlyBrace();
/*  826 */       reportError("left.curly.brace");
/*  827 */       skipRightCurlyBrace();
/*      */     } else {
/*      */       try {
/*  830 */         this.documentHandler.startSelector(sl);
/*  831 */         nextIgnoreSpaces();
/*  832 */         parseStyleDeclaration(true);
/*      */       } finally {
/*  834 */         this.documentHandler.endSelector(sl);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SelectorList parseSelectorList() throws IOException {
/*  843 */     CSSSelectorList result = new CSSSelectorList();
/*  844 */     result.append(parseSelector());
/*      */     
/*      */     while (true) {
/*  847 */       if (this.current != 6) {
/*  848 */         return result;
/*      */       }
/*  850 */       nextIgnoreSpaces();
/*  851 */       result.append(parseSelector());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Selector parseSelector() throws IOException {
/*      */     DescendantSelector descendantSelector;
/*  859 */     switch (this.current) {
/*      */       case 1:
/*      */       case 6:
/*  862 */         throw createCSSParseException("selector");
/*      */     } 
/*      */     
/*  865 */     this.pseudoElement = null;
/*  866 */     SimpleSelector ss = parseSimpleSelector();
/*  867 */     SimpleSelector simpleSelector1 = ss; while (true) {
/*      */       DescendantSelector descendantSelector1;
/*      */       SiblingSelector siblingSelector;
/*  870 */       switch (this.current) {
/*      */         default:
/*      */           break;
/*      */         case 7:
/*      */         case 11:
/*      */         case 13:
/*      */         case 16:
/*      */         case 21:
/*      */         case 28:
/*  879 */           descendantSelector1 = this.selectorFactory.createDescendantSelector((Selector)simpleSelector1, parseSimpleSelector());
/*      */           continue;
/*      */         case 4:
/*  882 */           nextIgnoreSpaces();
/*  883 */           siblingSelector = this.selectorFactory.createDirectAdjacentSelector((short)1, (Selector)descendantSelector1, 
/*  884 */               parseSimpleSelector()); continue;
/*      */         case 9:
/*      */           break;
/*  887 */       }  nextIgnoreSpaces();
/*  888 */       descendantSelector = this.selectorFactory.createChildSelector((Selector)siblingSelector, parseSimpleSelector());
/*      */     } 
/*      */     
/*  891 */     if (this.pseudoElement != null) {
/*  892 */       descendantSelector = this.selectorFactory.createChildSelector((Selector)descendantSelector, (SimpleSelector)this.selectorFactory
/*  893 */           .createPseudoElementSelector(null, this.pseudoElement));
/*      */     }
/*  895 */     return (Selector)descendantSelector;
/*      */   }
/*      */ 
/*      */   
/*      */   protected SimpleSelector parseSimpleSelector() throws IOException {
/*      */     ElementSelector elementSelector;
/*      */     ConditionalSelector conditionalSelector;
/*      */     CombinatorCondition combinatorCondition;
/*  903 */     if (this.pseudoElement != null) {
/*  904 */       elementSelector = this.selectorFactory.createPseudoElementSelector(null, this.pseudoElement);
/*  905 */       this.pseudoElement = null;
/*      */     } else {
/*  907 */       String prefix = null;
/*  908 */       String lName = null;
/*  909 */       switch (this.current) {
/*      */         case 17:
/*  911 */           lName = "";
/*      */           break;
/*      */         case 21:
/*  914 */           lName = this.scanner.getStringValue();
/*      */         case 13:
/*  916 */           next();
/*      */           break;
/*      */       } 
/*  919 */       switch (this.current) {
/*      */         case 17:
/*  921 */           prefix = lName;
/*  922 */           next();
/*  923 */           switch (this.current) {
/*      */             case 21:
/*  925 */               lName = this.scanner.getStringValue();
/*  926 */               next();
/*      */               break;
/*      */             case 13:
/*  929 */               next(); break;
/*      */           } 
/*  931 */           lName = null;
/*      */           break;
/*      */       } 
/*      */       
/*  935 */       String uri = (prefix == null) ? null : this.prefixToURI.get(prefix);
/*  936 */       elementSelector = this.selectorFactory.createElementSelector(uri, lName);
/*      */     } 
/*      */     
/*  939 */     Condition cond = null; while (true) {
/*      */       AttributeCondition attributeCondition; LangCondition langCondition; String prefix, lName, uri; int op; String val, func, lang;
/*  941 */       Condition c = null;
/*  942 */       switch (this.current) {
/*      */         case 28:
/*  944 */           attributeCondition = this.conditionFactory.createIdCondition(this.scanner.getStringValue());
/*  945 */           next();
/*      */           break;
/*      */         case 7:
/*  948 */           if (next() != 21) {
/*  949 */             throw createCSSParseException("identifier");
/*      */           }
/*  951 */           attributeCondition = this.conditionFactory.createClassCondition(null, this.scanner.getStringValue());
/*  952 */           next();
/*      */           break;
/*      */         case 36:
/*      */         case 37:
/*      */         case 38:
/*      */         case 39:
/*      */         case 40:
/*      */         case 41:
/*      */         case 42:
/*      */         case 43:
/*      */         case 44:
/*      */         case 45:
/*      */         case 46:
/*      */         case 47:
/*      */         case 49:
/*      */         case 50:
/*      */         case 51:
/*      */         case 52:
/*      */         case 56:
/*      */         case 58:
/*      */         case 59:
/*      */           break;
/*      */         case 11:
/*  975 */           nextIgnoreSpaces();
/*  976 */           prefix = null;
/*  977 */           lName = null;
/*  978 */           switch (this.current) {
/*      */             case 17:
/*  980 */               lName = "";
/*      */               break;
/*      */             case 21:
/*  983 */               lName = this.scanner.getStringValue();
/*      */             case 13:
/*  985 */               nextIgnoreSpaces();
/*      */               break;
/*      */             default:
/*  988 */               throw createCSSParseException("dash.or.identifier.or.any");
/*      */           } 
/*  990 */           switch (this.current) {
/*      */             case 17:
/*  992 */               prefix = lName;
/*  993 */               if (nextIgnoreSpaces() != 21) {
/*  994 */                 throw createCSSParseException("identifier");
/*      */               }
/*  996 */               lName = this.scanner.getStringValue();
/*  997 */               nextIgnoreSpaces(); break;
/*      */           } 
/*  999 */           if (lName == null) {
/* 1000 */             throw createCSSParseException("identifier");
/*      */           }
/* 1002 */           uri = (prefix == null) ? null : this.prefixToURI.get(prefix);
/*      */           
/* 1004 */           op = this.current;
/* 1005 */           switch (op) {
/*      */             default:
/* 1007 */               throw createCSSParseException("right.bracket");
/*      */             case 12:
/* 1009 */               nextIgnoreSpaces();
/* 1010 */               attributeCondition = this.conditionFactory.createAttributeCondition(lName, uri, false, null); break;
/*      */             case 3:
/*      */             case 26:
/*      */             case 27:
/*      */               break;
/* 1015 */           }  val = null;
/* 1016 */           switch (nextIgnoreSpaces())
/*      */           { default:
/* 1018 */               throw createCSSParseException("identifier.or.string");
/*      */             case 20:
/*      */             case 21:
/* 1021 */               break; }  val = this.scanner.getStringValue();
/* 1022 */           nextIgnoreSpaces();
/*      */           
/* 1024 */           if (this.current != 12) {
/* 1025 */             throw createCSSParseException("right.bracket");
/*      */           }
/* 1027 */           next();
/* 1028 */           switch (op) {
/*      */             case 3:
/* 1030 */               attributeCondition = this.conditionFactory.createAttributeCondition(lName, uri, true, val);
/*      */               break;
/*      */             case 27:
/* 1033 */               attributeCondition = this.conditionFactory.createOneOfAttributeCondition(lName, uri, false, val);
/*      */               break;
/*      */           } 
/* 1036 */           attributeCondition = this.conditionFactory.createBeginHyphenAttributeCondition(lName, uri, false, val);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 16:
/* 1041 */           switch (nextIgnoreSpaces()) {
/*      */             case 21:
/* 1043 */               val = this.scanner.getStringValue();
/* 1044 */               if (isPseudoElement(val)) {
/* 1045 */                 if (this.pseudoElement != null) {
/* 1046 */                   throw createCSSParseException("duplicate.pseudo.element");
/*      */                 }
/* 1048 */                 this.pseudoElement = val;
/* 1049 */                 next();
/*      */                 break;
/*      */               } 
/* 1052 */               attributeCondition = this.conditionFactory.createPseudoClassCondition(null, val);
/*      */               
/* 1054 */               next();
/*      */               break;
/*      */             case 54:
/* 1057 */               func = this.scanner.getStringValue();
/* 1058 */               if (nextIgnoreSpaces() != 21) {
/* 1059 */                 throw createCSSParseException("identifier");
/*      */               }
/* 1061 */               lang = this.scanner.getStringValue();
/* 1062 */               if (nextIgnoreSpaces() != 15) {
/* 1063 */                 throw createCSSParseException("right.brace");
/*      */               }
/*      */               
/* 1066 */               if (!func.equalsIgnoreCase("lang")) {
/* 1067 */                 throw createCSSParseException("pseudo.function");
/*      */               }
/*      */               
/* 1070 */               langCondition = this.conditionFactory.createLangCondition(lang);
/*      */               
/* 1072 */               next();
/*      */               break;
/*      */           } 
/* 1075 */           throw createCSSParseException("identifier");
/*      */         
/*      */         default:
/*      */           break;
/*      */       } 
/*      */       
/* 1081 */       if (langCondition != null) {
/* 1082 */         LangCondition langCondition1; if (cond == null) {
/* 1083 */           langCondition1 = langCondition; continue;
/*      */         } 
/* 1085 */         combinatorCondition = this.conditionFactory.createAndCondition((Condition)langCondition1, (Condition)langCondition);
/*      */       } 
/*      */     } 
/*      */     
/* 1089 */     skipSpaces();
/* 1090 */     if (combinatorCondition != null) {
/* 1091 */       conditionalSelector = this.selectorFactory.createConditionalSelector((SimpleSelector)elementSelector, (Condition)combinatorCondition);
/*      */     }
/* 1093 */     return (SimpleSelector)conditionalSelector;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isPseudoElement(String s) {
/* 1100 */     switch (s.charAt(0)) {
/*      */       case 'A':
/*      */       case 'a':
/* 1103 */         return s.equalsIgnoreCase("after");
/*      */       case 'B':
/*      */       case 'b':
/* 1106 */         return s.equalsIgnoreCase("before");
/*      */       case 'F':
/*      */       case 'f':
/* 1109 */         return (s.equalsIgnoreCase("first-letter") || s.equalsIgnoreCase("first-line"));
/*      */     } 
/* 1111 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseStyleDeclaration(boolean inSheet) throws IOException {
/*      */     while (true) {
/*      */       LexicalUnit exp;
/* 1119 */       switch (this.current) {
/*      */         case 0:
/* 1121 */           if (inSheet) {
/* 1122 */             throw createCSSParseException("eof");
/*      */           }
/*      */           return;
/*      */         case 2:
/* 1126 */           if (!inSheet) {
/* 1127 */             throw createCSSParseException("eof.expected");
/*      */           }
/* 1129 */           nextIgnoreSpaces();
/*      */           return;
/*      */         case 8:
/* 1132 */           nextIgnoreSpaces();
/*      */           continue;
/*      */         case 57:
/* 1135 */           nextIgnoreSpaces();
/* 1136 */           parsePageContentRule();
/*      */           break;
/*      */         default:
/* 1139 */           reportError(createCSSParseException("identifier"));
/* 1140 */           skipSemiColon();
/*      */           continue;
/*      */         case 21:
/*      */           break;
/*      */       } 
/* 1145 */       String name = this.scanner.getStringValue();
/* 1146 */       if (nextIgnoreSpaces() != 16) {
/* 1147 */         reportError(createCSSParseException("colon"));
/* 1148 */         skipSemiColon();
/*      */         continue;
/*      */       } 
/* 1151 */       nextIgnoreSpaces();
/*      */ 
/*      */       
/*      */       try {
/* 1155 */         exp = parseExpression(false);
/* 1156 */       } catch (CSSParseException e) {
/* 1157 */         reportError(e);
/* 1158 */         skipSemiColon();
/*      */         continue;
/*      */       } 
/* 1161 */       boolean important = false;
/* 1162 */       if (this.current == 24) {
/* 1163 */         important = true;
/* 1164 */         nextIgnoreSpaces();
/*      */       } 
/* 1166 */       this.documentHandler.property(name, exp, important);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LexicalUnit parseExpression(boolean param) throws IOException {
/* 1177 */     LexicalUnit result = parseTerm(null);
/* 1178 */     LexicalUnit curr = result;
/*      */     
/*      */     while (true) {
/* 1181 */       boolean op = false;
/* 1182 */       switch (this.current) {
/*      */         case 6:
/* 1184 */           op = true;
/* 1185 */           curr = CSSLexicalUnit.createSimple((short)0, curr);
/* 1186 */           nextIgnoreSpaces();
/*      */           break;
/*      */         case 10:
/* 1189 */           op = true;
/* 1190 */           curr = CSSLexicalUnit.createSimple((short)4, curr);
/* 1191 */           nextIgnoreSpaces(); break;
/*      */       } 
/* 1193 */       if (param) {
/* 1194 */         if (this.current == 15) {
/* 1195 */           if (op) {
/* 1196 */             throw createCSSParseException("token", new Object[] { new Integer(this.current) });
/*      */           }
/* 1198 */           return result;
/*      */         } 
/* 1200 */         curr = parseTerm(curr); continue;
/*      */       } 
/* 1202 */       switch (this.current) {
/*      */         case 0:
/*      */         case 2:
/*      */         case 8:
/*      */         case 24:
/* 1207 */           if (op) {
/* 1208 */             throw createCSSParseException("token", new Object[] { new Integer(this.current) });
/*      */           }
/* 1210 */           return result;
/*      */       } 
/* 1212 */       curr = parseTerm(curr);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LexicalUnit parseTerm(LexicalUnit prev) throws IOException {
/*      */     String sval, val;
/* 1222 */     boolean plus = true;
/* 1223 */     boolean sgn = false;
/*      */     
/* 1225 */     switch (this.current) {
/*      */       case 5:
/* 1227 */         plus = false;
/*      */       case 4:
/* 1229 */         next();
/* 1230 */         sgn = true; break;
/*      */     } 
/* 1232 */     switch (this.current) {
/*      */       case 25:
/* 1234 */         sval = this.scanner.getStringValue();
/* 1235 */         if (!plus)
/* 1236 */           sval = "-" + sval; 
/*      */         try {
/* 1238 */           int i = Integer.parseInt(sval);
/* 1239 */           nextIgnoreSpaces();
/* 1240 */           return CSSLexicalUnit.createInteger(i, prev);
/* 1241 */         } catch (NumberFormatException e) {
/* 1242 */           throw createCSSParseException("token", new Object[] { new Integer(this.current) });
/*      */         } 
/*      */       case 56:
/* 1245 */         return CSSLexicalUnit.createFloat((short)14, number(plus), prev);
/*      */       case 44:
/* 1247 */         return CSSLexicalUnit.createFloat((short)23, number(plus), prev);
/*      */       case 47:
/* 1249 */         return CSSLexicalUnit.createFloat((short)21, number(plus), prev);
/*      */       case 46:
/* 1251 */         return CSSLexicalUnit.createFloat((short)22, number(plus), prev);
/*      */       case 48:
/* 1253 */         return CSSLexicalUnit.createFloat((short)17, number(plus), prev);
/*      */       case 39:
/* 1255 */         return CSSLexicalUnit.createFloat((short)19, number(plus), prev);
/*      */       case 40:
/* 1257 */         return CSSLexicalUnit.createFloat((short)20, number(plus), prev);
/*      */       case 41:
/* 1259 */         return CSSLexicalUnit.createFloat((short)18, number(plus), prev);
/*      */       case 38:
/* 1261 */         return CSSLexicalUnit.createFloat((short)15, number(plus), prev);
/*      */       case 37:
/* 1263 */         return CSSLexicalUnit.createFloat((short)16, number(plus), prev);
/*      */       case 49:
/* 1265 */         return CSSLexicalUnit.createFloat((short)28, number(plus), prev);
/*      */       case 50:
/* 1267 */         return CSSLexicalUnit.createFloat((short)30, number(plus), prev);
/*      */       case 51:
/* 1269 */         return CSSLexicalUnit.createFloat((short)29, number(plus), prev);
/*      */       case 45:
/* 1271 */         return CSSLexicalUnit.createFloat((short)32, number(plus), prev);
/*      */       case 42:
/* 1273 */         return CSSLexicalUnit.createFloat((short)31, number(plus), prev);
/*      */       case 43:
/* 1275 */         return CSSLexicalUnit.createFloat((short)33, number(plus), prev);
/*      */       case 52:
/* 1277 */         return CSSLexicalUnit.createFloat((short)34, number(plus), prev);
/*      */       case 58:
/* 1279 */         return CSSLexicalUnit.createFloat((short)43, number(plus), prev);
/*      */       case 59:
/* 1281 */         return CSSLexicalUnit.createFloat((short)44, number(plus), prev);
/*      */       case 36:
/* 1283 */         return dimension(plus, prev);
/*      */       case 54:
/* 1285 */         return parseFunction(prev);
/*      */     } 
/* 1287 */     if (sgn) {
/* 1288 */       throw createCSSParseException("token", new Object[] { new Integer(this.current) });
/*      */     }
/*      */     
/* 1291 */     switch (this.current) {
/*      */       case 20:
/* 1293 */         val = this.scanner.getStringValue();
/* 1294 */         nextIgnoreSpaces();
/* 1295 */         return CSSLexicalUnit.createString((short)36, val, prev);
/*      */       case 55:
/* 1297 */         val = this.scanner.getStringValue();
/* 1298 */         nextIgnoreSpaces();
/* 1299 */         return CSSLexicalUnit.createString((short)39, val, prev);
/*      */       case 21:
/* 1301 */         val = this.scanner.getStringValue();
/* 1302 */         nextIgnoreSpaces();
/* 1303 */         if (val.equalsIgnoreCase("inherit")) {
/* 1304 */           return CSSLexicalUnit.createSimple((short)12, prev);
/*      */         }
/* 1306 */         return CSSLexicalUnit.createString((short)35, val, prev);
/*      */       
/*      */       case 53:
/* 1309 */         val = this.scanner.getStringValue();
/* 1310 */         nextIgnoreSpaces();
/* 1311 */         return CSSLexicalUnit.createString((short)24, val, prev);
/*      */       case 28:
/* 1313 */         return hexcolor(prev);
/*      */     } 
/* 1315 */     throw createCSSParseException("token", new Object[] { new Integer(this.current) });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LexicalUnit parseFunction(LexicalUnit prev) throws IOException {
/* 1323 */     String name = this.scanner.getStringValue();
/* 1324 */     nextIgnoreSpaces();
/*      */     
/* 1326 */     LexicalUnit params = parseExpression(true);
/*      */     
/* 1328 */     if (this.current != 15) {
/* 1329 */       throw createCSSParseException("token", new Object[] { new Integer(this.current) });
/*      */     }
/* 1331 */     nextIgnoreSpaces();
/*      */     
/* 1333 */     switch (name.charAt(0)) {
/*      */       
/*      */       case 'R':
/*      */       case 'r':
/* 1337 */         if (name.equalsIgnoreCase("rgb")) {
/* 1338 */           LexicalUnit lu = params;
/* 1339 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1342 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 13:
/*      */             case 23:
/*      */               break; }
/* 1347 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1349 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1352 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1356 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1358 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1361 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 13:
/*      */             case 23:
/*      */               break; }
/* 1366 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1368 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1371 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1375 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1377 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1380 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 13:
/*      */             case 23:
/*      */               break; }
/* 1385 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1387 */           if (lu != null) {
/*      */             break;
/*      */           }
/* 1390 */           return CSSLexicalUnit.createPredefinedFunction((short)27, params, prev);
/* 1391 */         }  if (name.equalsIgnoreCase("rect")) {
/* 1392 */           LexicalUnit lu = params;
/* 1393 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1396 */           switch (lu.getLexicalUnitType()) {
/*      */             default:
/*      */               break;
/*      */             case 13:
/* 1400 */               if (lu.getIntegerValue() != 0) {
/*      */                 break;
/*      */               }
/* 1403 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 35:
/* 1406 */               if (!lu.getStringValue().equalsIgnoreCase("auto")) {
/*      */                 break;
/*      */               }
/* 1409 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 15:
/*      */             case 16:
/*      */             case 17:
/*      */             case 18:
/*      */             case 19:
/*      */             case 20:
/*      */             case 21:
/*      */             case 22:
/*      */             case 23:
/*      */             case 43:
/*      */             case 44:
/* 1422 */               lu = lu.getNextLexicalUnit(); break;
/*      */           } 
/* 1424 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1427 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1431 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1433 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1436 */           switch (lu.getLexicalUnitType()) {
/*      */             default:
/*      */               break;
/*      */             case 13:
/* 1440 */               if (lu.getIntegerValue() != 0) {
/*      */                 break;
/*      */               }
/* 1443 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 35:
/* 1446 */               if (!lu.getStringValue().equalsIgnoreCase("auto")) {
/*      */                 break;
/*      */               }
/* 1449 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 15:
/*      */             case 16:
/*      */             case 17:
/*      */             case 18:
/*      */             case 19:
/*      */             case 20:
/*      */             case 21:
/*      */             case 22:
/*      */             case 23:
/* 1460 */               lu = lu.getNextLexicalUnit(); break;
/*      */           } 
/* 1462 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1465 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1469 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1471 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1474 */           switch (lu.getLexicalUnitType()) {
/*      */             default:
/*      */               break;
/*      */             case 13:
/* 1478 */               if (lu.getIntegerValue() != 0) {
/*      */                 break;
/*      */               }
/* 1481 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 35:
/* 1484 */               if (!lu.getStringValue().equalsIgnoreCase("auto")) {
/*      */                 break;
/*      */               }
/* 1487 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 15:
/*      */             case 16:
/*      */             case 17:
/*      */             case 18:
/*      */             case 19:
/*      */             case 20:
/*      */             case 21:
/*      */             case 22:
/*      */             case 23:
/* 1498 */               lu = lu.getNextLexicalUnit(); break;
/*      */           } 
/* 1500 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1503 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1507 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1509 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1512 */           switch (lu.getLexicalUnitType()) {
/*      */             default:
/*      */               break;
/*      */             case 13:
/* 1516 */               if (lu.getIntegerValue() != 0) {
/*      */                 break;
/*      */               }
/* 1519 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 35:
/* 1522 */               if (!lu.getStringValue().equalsIgnoreCase("auto")) {
/*      */                 break;
/*      */               }
/* 1525 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 15:
/*      */             case 16:
/*      */             case 17:
/*      */             case 18:
/*      */             case 19:
/*      */             case 20:
/*      */             case 21:
/*      */             case 22:
/*      */             case 23:
/* 1536 */               lu = lu.getNextLexicalUnit(); break;
/*      */           } 
/* 1538 */           if (lu != null) {
/*      */             break;
/*      */           }
/* 1541 */           return CSSLexicalUnit.createPredefinedFunction((short)38, params, prev);
/*      */         } 
/*      */         break;
/*      */       case 'C':
/*      */       case 'c':
/* 1546 */         if (name.equalsIgnoreCase("counter")) {
/* 1547 */           LexicalUnit lu = params;
/* 1548 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1551 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 35:
/*      */               break; }
/* 1555 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1557 */           if (lu != null) {
/* 1558 */             switch (lu.getLexicalUnitType()) { default:
/*      */                 break;
/*      */               case 0:
/*      */                 break; }
/* 1562 */              lu = lu.getNextLexicalUnit();
/*      */             
/* 1564 */             if (lu == null) {
/*      */               break;
/*      */             }
/* 1567 */             switch (lu.getLexicalUnitType()) { default:
/*      */                 break;
/*      */               case 35:
/*      */                 break; }
/* 1571 */              lu = lu.getNextLexicalUnit();
/*      */             
/* 1573 */             if (lu != null) {
/*      */               break;
/*      */             }
/*      */           } 
/* 1577 */           return CSSLexicalUnit.createPredefinedFunction((short)25, params, prev);
/* 1578 */         }  if (name.equalsIgnoreCase("counters")) {
/* 1579 */           LexicalUnit lu = params;
/* 1580 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1583 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 35:
/*      */               break; }
/* 1587 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1589 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1592 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1596 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1598 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1601 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 36:
/*      */               break; }
/* 1605 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1607 */           if (lu != null) {
/* 1608 */             switch (lu.getLexicalUnitType()) { default:
/*      */                 break;
/*      */               case 0:
/*      */                 break; }
/* 1612 */              lu = lu.getNextLexicalUnit();
/*      */             
/* 1614 */             if (lu == null) {
/*      */               break;
/*      */             }
/* 1617 */             switch (lu.getLexicalUnitType()) { default:
/*      */                 break;
/*      */               case 35:
/*      */                 break; }
/* 1621 */              lu = lu.getNextLexicalUnit();
/*      */             
/* 1623 */             if (lu != null) {
/*      */               break;
/*      */             }
/*      */           } 
/* 1627 */           return CSSLexicalUnit.createPredefinedFunction((short)26, params, prev);
/*      */         } 
/*      */         break;
/*      */       case 'A':
/*      */       case 'a':
/* 1632 */         if (name.equalsIgnoreCase("attr")) {
/* 1633 */           LexicalUnit lu = params;
/* 1634 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1637 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 35:
/*      */               break; }
/* 1641 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1643 */           if (lu != null) {
/*      */             break;
/*      */           }
/* 1646 */           return CSSLexicalUnit.createString((short)37, params.getStringValue(), prev);
/*      */         } 
/*      */         break;
/*      */     } 
/* 1650 */     return CSSLexicalUnit.createFunction(name, params, prev);
/*      */   }
/*      */   protected LexicalUnit hexcolor(LexicalUnit prev) throws IOException {
/*      */     char rc, gc, bc;
/*      */     int t, r, g, b;
/*      */     LexicalUnit tmp;
/*      */     char rc1, rc2, gc1, gc2, bc1, bc2;
/* 1657 */     String val = this.scanner.getStringValue();
/* 1658 */     int len = val.length();
/* 1659 */     LexicalUnit params = null;
/* 1660 */     switch (len) {
/*      */       case 3:
/* 1662 */         rc = Character.toLowerCase(val.charAt(0));
/* 1663 */         gc = Character.toLowerCase(val.charAt(1));
/* 1664 */         bc = Character.toLowerCase(val.charAt(2));
/* 1665 */         if (!ScannerUtilities.isCSSHexadecimalCharacter(rc) || !ScannerUtilities.isCSSHexadecimalCharacter(gc) || 
/* 1666 */           !ScannerUtilities.isCSSHexadecimalCharacter(bc)) {
/* 1667 */           throw createCSSParseException("rgb.color", new Object[] { val });
/*      */         }
/*      */         
/* 1670 */         r = t = (rc >= '0' && rc <= '9') ? (rc - 48) : (rc - 97 + 10);
/* 1671 */         t <<= 4;
/* 1672 */         r |= t;
/* 1673 */         g = t = (gc >= '0' && gc <= '9') ? (gc - 48) : (gc - 97 + 10);
/* 1674 */         t <<= 4;
/* 1675 */         g |= t;
/* 1676 */         b = t = (bc >= '0' && bc <= '9') ? (bc - 48) : (bc - 97 + 10);
/* 1677 */         t <<= 4;
/* 1678 */         b |= t;
/* 1679 */         params = CSSLexicalUnit.createInteger(r, null);
/*      */         
/* 1681 */         tmp = CSSLexicalUnit.createSimple((short)0, params);
/* 1682 */         tmp = CSSLexicalUnit.createInteger(g, tmp);
/* 1683 */         tmp = CSSLexicalUnit.createSimple((short)0, tmp);
/* 1684 */         tmp = CSSLexicalUnit.createInteger(b, tmp);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1718 */         nextIgnoreSpaces();
/* 1719 */         return CSSLexicalUnit.createPredefinedFunction((short)27, params, prev);case 6: rc1 = Character.toLowerCase(val.charAt(0)); rc2 = Character.toLowerCase(val.charAt(1)); gc1 = Character.toLowerCase(val.charAt(2)); gc2 = Character.toLowerCase(val.charAt(3)); bc1 = Character.toLowerCase(val.charAt(4)); bc2 = Character.toLowerCase(val.charAt(5)); if (!ScannerUtilities.isCSSHexadecimalCharacter(rc1) || !ScannerUtilities.isCSSHexadecimalCharacter(rc2) || !ScannerUtilities.isCSSHexadecimalCharacter(gc1) || !ScannerUtilities.isCSSHexadecimalCharacter(gc2) || !ScannerUtilities.isCSSHexadecimalCharacter(bc1) || !ScannerUtilities.isCSSHexadecimalCharacter(bc2)) throw createCSSParseException("rgb.color");  r = (rc1 >= '0' && rc1 <= '9') ? (rc1 - 48) : (rc1 - 97 + 10); r <<= 4; r |= (rc2 >= '0' && rc2 <= '9') ? (rc2 - 48) : (rc2 - 97 + 10); g = (gc1 >= '0' && gc1 <= '9') ? (gc1 - 48) : (gc1 - 97 + 10); g <<= 4; g |= (gc2 >= '0' && gc2 <= '9') ? (gc2 - 48) : (gc2 - 97 + 10); b = (bc1 >= '0' && bc1 <= '9') ? (bc1 - 48) : (bc1 - 97 + 10); b <<= 4; b |= (bc2 >= '0' && bc2 <= '9') ? (bc2 - 48) : (bc2 - 97 + 10); params = CSSLexicalUnit.createInteger(r, null); tmp = CSSLexicalUnit.createSimple((short)0, params); tmp = CSSLexicalUnit.createInteger(g, tmp); tmp = CSSLexicalUnit.createSimple((short)0, tmp); tmp = CSSLexicalUnit.createInteger(b, tmp); nextIgnoreSpaces(); return CSSLexicalUnit.createPredefinedFunction((short)27, params, prev);
/*      */     } 
/*      */     throw createCSSParseException("rgb.color", new Object[] { val });
/*      */   }
/*      */ 
/*      */   
/*      */   protected Scanner createScanner(InputSource source) throws IOException {
/* 1726 */     this.documentURI = source.getURI();
/* 1727 */     if (this.documentURI == null) {
/* 1728 */       this.documentURI = "";
/*      */     }
/*      */     
/* 1731 */     Reader r = source.getCharacterStream();
/* 1732 */     if (r != null) {
/* 1733 */       return new Scanner(r);
/*      */     }
/* 1735 */     String encoding = source.getEncoding();
/* 1736 */     if (encoding == null) {
/* 1737 */       encoding = this.defaultCharset;
/*      */     }
/*      */     
/* 1740 */     InputStream is = source.getByteStream();
/* 1741 */     if (is != null) {
/* 1742 */       return new Scanner(new InputStreamReader(is, encoding));
/*      */     }
/*      */     
/* 1745 */     String uri = source.getURI();
/* 1746 */     if (uri == null) {
/* 1747 */       throw new CSSException(formatMessage("empty.source", null));
/*      */     }
/*      */     
/* 1750 */     URL url = new URL(uri);
/* 1751 */     is = url.openStream();
/* 1752 */     return new Scanner(new InputStreamReader(is, encoding));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int skipSpaces() throws IOException {
/* 1759 */     int lex = this.scanner.getType();
/* 1760 */     while (lex == 18) {
/* 1761 */       lex = next();
/*      */     }
/* 1763 */     return lex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int skipSpacesAndCDOCDC() throws IOException {
/*      */     while (true) {
/* 1771 */       switch (this.current) {
/*      */         default:
/*      */           break;
/*      */         case 18:
/*      */         case 19:
/*      */         case 22:
/*      */         case 23:
/*      */           break;
/* 1779 */       }  this.scanner.clearBuffer();
/* 1780 */       next();
/*      */     } 
/* 1782 */     return this.current;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float number(boolean positive) throws IOException {
/*      */     try {
/* 1790 */       float sgn = positive ? 1.0F : -1.0F;
/* 1791 */       String val = this.scanner.getStringValue();
/* 1792 */       nextIgnoreSpaces();
/* 1793 */       return sgn * Float.parseFloat(val);
/* 1794 */     } catch (NumberFormatException e) {
/* 1795 */       throw createCSSParseException("number.format");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LexicalUnit dimension(boolean positive, LexicalUnit prev) throws IOException {
/*      */     try {
/* 1804 */       float sgn = positive ? 1.0F : -1.0F;
/* 1805 */       String val = this.scanner.getStringValue();
/*      */       int i;
/* 1807 */       for (i = 0; i < val.length(); i++) {
/* 1808 */         switch (val.charAt(i)) {
/*      */           default:
/*      */             break;
/*      */           case '.':
/*      */           case '0':
/*      */           case '1':
/*      */           case '2':
/*      */           case '3':
/*      */           case '4':
/*      */           case '5':
/*      */           case '6':
/*      */           case '7':
/*      */           case '8':
/*      */           case '9':
/*      */             break;
/*      */         } 
/* 1824 */       }  nextIgnoreSpaces();
/* 1825 */       return CSSLexicalUnit.createDimension(sgn * Float.parseFloat(val.substring(0, i)), val.substring(i), prev);
/* 1826 */     } catch (NumberFormatException e) {
/* 1827 */       throw createCSSParseException("number.format");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int next() throws IOException {
/*      */     try {
/*      */       while (true) {
/* 1837 */         this.scanner.clearBuffer();
/* 1838 */         this.current = this.scanner.next();
/* 1839 */         if (this.current == 19) {
/* 1840 */           this.documentHandler.comment(this.scanner.getStringValue());
/*      */           continue;
/*      */         } 
/*      */         break;
/*      */       } 
/* 1845 */       return this.current;
/* 1846 */     } catch (ParseException e) {
/* 1847 */       reportError(e.getMessage());
/* 1848 */       return this.current;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextIgnoreSpaces() throws IOException {
/*      */     try {
/*      */       while (true) {
/* 1858 */         this.scanner.clearBuffer();
/* 1859 */         this.current = this.scanner.next();
/* 1860 */         switch (this.current) {
/*      */           case 19:
/* 1862 */             this.documentHandler.comment(this.scanner.getStringValue());
/*      */           default:
/*      */             break;
/*      */           case 18:
/*      */             break;
/*      */         } 
/*      */       } 
/* 1869 */     } catch (ParseException e) {
/* 1870 */       reportError(e.getMessage());
/*      */     } 
/* 1872 */     return this.current;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportError(String key) throws IOException {
/* 1879 */     reportError(key, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportError(String key, Object[] params) throws IOException {
/* 1886 */     reportError(createCSSParseException(key, params));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportError(CSSParseException e) throws IOException {
/* 1893 */     this.errorHandler.error(e);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CSSParseException createCSSParseException(String key) {
/* 1900 */     return createCSSParseException(key, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CSSParseException createCSSParseException(String key, Object[] params) {
/* 1907 */     return new CSSParseException(formatMessage(key, params), this.documentURI, this.scanner.getLine(), this.scanner
/* 1908 */         .getColumn());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseStyleDeclaration(String source) throws CSSException, IOException {
/* 1919 */     this.scanner = new Scanner(source);
/* 1920 */     parseStyleDeclarationInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseRule(String source) throws CSSException, IOException {
/* 1927 */     this.scanner = new Scanner(source);
/* 1928 */     parseRuleInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SelectorList parseSelectors(String source) throws CSSException, IOException {
/* 1935 */     this.scanner = new Scanner(source);
/* 1936 */     return parseSelectorsInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public LexicalUnit parsePropertyValue(String source) throws CSSException, IOException {
/* 1943 */     this.scanner = new Scanner(source);
/* 1944 */     return parsePropertyValueInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean parsePriority(String source) throws CSSException, IOException {
/* 1951 */     this.scanner = new Scanner(source);
/* 1952 */     return parsePriorityInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SACMediaList parseMedia(String mediaText) throws CSSException, IOException {
/* 1959 */     CSSSACMediaList result = new CSSSACMediaList();
/* 1960 */     if (!"all".equalsIgnoreCase(mediaText)) {
/* 1961 */       StringTokenizer st = new StringTokenizer(mediaText, " ,");
/* 1962 */       while (st.hasMoreTokens()) {
/* 1963 */         result.append(st.nextToken());
/*      */       }
/*      */     } 
/* 1966 */     return result;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/Parser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */