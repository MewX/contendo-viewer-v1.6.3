/*      */ package org.apache.batik.css.parser;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.util.Locale;
/*      */ import java.util.MissingResourceException;
/*      */ import java.util.StringTokenizer;
/*      */ import org.apache.batik.i18n.Localizable;
/*      */ import org.apache.batik.i18n.LocalizableSupport;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.w3c.css.sac.AttributeCondition;
/*      */ import org.w3c.css.sac.CSSException;
/*      */ import org.w3c.css.sac.CSSParseException;
/*      */ import org.w3c.css.sac.CombinatorCondition;
/*      */ import org.w3c.css.sac.Condition;
/*      */ import org.w3c.css.sac.ConditionFactory;
/*      */ import org.w3c.css.sac.ConditionalSelector;
/*      */ import org.w3c.css.sac.DescendantSelector;
/*      */ import org.w3c.css.sac.DocumentHandler;
/*      */ import org.w3c.css.sac.ElementSelector;
/*      */ import org.w3c.css.sac.ErrorHandler;
/*      */ import org.w3c.css.sac.InputSource;
/*      */ import org.w3c.css.sac.LangCondition;
/*      */ import org.w3c.css.sac.LexicalUnit;
/*      */ import org.w3c.css.sac.SACMediaList;
/*      */ import org.w3c.css.sac.Selector;
/*      */ import org.w3c.css.sac.SelectorFactory;
/*      */ import org.w3c.css.sac.SelectorList;
/*      */ import org.w3c.css.sac.SiblingSelector;
/*      */ import org.w3c.css.sac.SimpleSelector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   implements ExtendedParser, Localizable
/*      */ {
/*      */   public static final String BUNDLE_CLASSNAME = "org.apache.batik.css.parser.resources.Messages";
/*   63 */   protected LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.css.parser.resources.Messages", Parser.class.getClassLoader());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Scanner scanner;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int current;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   80 */   protected DocumentHandler documentHandler = DefaultDocumentHandler.INSTANCE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   86 */   protected SelectorFactory selectorFactory = DefaultSelectorFactory.INSTANCE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   92 */   protected ConditionFactory conditionFactory = DefaultConditionFactory.INSTANCE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   98 */   protected ErrorHandler errorHandler = DefaultErrorHandler.INSTANCE;
/*      */ 
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
/*      */   
/*      */   public String getParserVersion() {
/*  116 */     return "http://www.w3.org/TR/REC-CSS2";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocale(Locale locale) throws CSSException {
/*  123 */     this.localizableSupport.setLocale(locale);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Locale getLocale() {
/*  130 */     return this.localizableSupport.getLocale();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String formatMessage(String key, Object[] args) throws MissingResourceException {
/*  139 */     return this.localizableSupport.formatMessage(key, args);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentHandler(DocumentHandler handler) {
/*  147 */     this.documentHandler = handler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSelectorFactory(SelectorFactory factory) {
/*  155 */     this.selectorFactory = factory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConditionFactory(ConditionFactory factory) {
/*  163 */     this.conditionFactory = factory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setErrorHandler(ErrorHandler handler) {
/*  171 */     this.errorHandler = handler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseStyleSheet(InputSource source) throws CSSException, IOException {
/*  180 */     this.scanner = createScanner(source);
/*      */     
/*      */     try {
/*  183 */       this.documentHandler.startDocument(source);
/*      */       
/*  185 */       this.current = this.scanner.next();
/*  186 */       switch (this.current) {
/*      */         case 30:
/*  188 */           if (nextIgnoreSpaces() != 19) {
/*  189 */             reportError("charset.string"); break;
/*      */           } 
/*  191 */           if (nextIgnoreSpaces() != 8) {
/*  192 */             reportError("semicolon");
/*      */           }
/*  194 */           next();
/*      */           break;
/*      */         
/*      */         case 18:
/*  198 */           this.documentHandler.comment(this.scanner.getStringValue());
/*      */           break;
/*      */       } 
/*  201 */       skipSpacesAndCDOCDC();
/*      */       
/*  203 */       while (this.current == 28) {
/*  204 */         nextIgnoreSpaces();
/*  205 */         parseImportRule();
/*  206 */         nextIgnoreSpaces();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       while (true) {
/*  213 */         switch (this.current) {
/*      */           case 33:
/*  215 */             nextIgnoreSpaces();
/*  216 */             parsePageRule();
/*      */             break;
/*      */           case 32:
/*  219 */             nextIgnoreSpaces();
/*  220 */             parseMediaRule();
/*      */             break;
/*      */           case 31:
/*  223 */             nextIgnoreSpaces();
/*  224 */             parseFontFaceRule();
/*      */             break;
/*      */           case 29:
/*  227 */             nextIgnoreSpaces();
/*  228 */             parseAtRule();
/*      */             break;
/*      */           case 0:
/*      */             break;
/*      */           default:
/*  233 */             parseRuleSet(); break;
/*      */         } 
/*  235 */         skipSpacesAndCDOCDC();
/*      */       } 
/*      */     } finally {
/*  238 */       this.documentHandler.endDocument(source);
/*  239 */       this.scanner.close();
/*  240 */       this.scanner = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseStyleSheet(String uri) throws CSSException, IOException {
/*  249 */     parseStyleSheet(new InputSource(uri));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseStyleDeclaration(InputSource source) throws CSSException, IOException {
/*  259 */     this.scanner = createScanner(source);
/*  260 */     parseStyleDeclarationInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseStyleDeclarationInternal() throws CSSException, IOException {
/*  268 */     nextIgnoreSpaces();
/*      */     try {
/*  270 */       parseStyleDeclaration(false);
/*  271 */     } catch (CSSParseException e) {
/*  272 */       reportError(e);
/*      */     } finally {
/*  274 */       this.scanner.close();
/*  275 */       this.scanner = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseRule(InputSource source) throws CSSException, IOException {
/*  285 */     this.scanner = createScanner(source);
/*  286 */     parseRuleInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseRuleInternal() throws CSSException, IOException {
/*  293 */     nextIgnoreSpaces();
/*  294 */     parseRule();
/*  295 */     this.scanner.close();
/*  296 */     this.scanner = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SelectorList parseSelectors(InputSource source) throws CSSException, IOException {
/*  305 */     this.scanner = createScanner(source);
/*  306 */     return parseSelectorsInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SelectorList parseSelectorsInternal() throws CSSException, IOException {
/*  314 */     nextIgnoreSpaces();
/*  315 */     SelectorList ret = parseSelectorList();
/*  316 */     this.scanner.close();
/*  317 */     this.scanner = null;
/*  318 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public LexicalUnit parsePropertyValue(InputSource source) throws CSSException, IOException {
/*  327 */     this.scanner = createScanner(source);
/*  328 */     return parsePropertyValueInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LexicalUnit parsePropertyValueInternal() throws CSSException, IOException {
/*  336 */     nextIgnoreSpaces();
/*      */     
/*  338 */     LexicalUnit exp = null;
/*      */     
/*      */     try {
/*  341 */       exp = parseExpression(false);
/*  342 */     } catch (CSSParseException e) {
/*  343 */       reportError(e);
/*  344 */       throw e;
/*      */     } 
/*      */     
/*  347 */     CSSParseException exception = null;
/*  348 */     if (this.current != 0) {
/*  349 */       exception = createCSSParseException("eof.expected");
/*      */     }
/*  351 */     this.scanner.close();
/*  352 */     this.scanner = null;
/*      */     
/*  354 */     if (exception != null) {
/*  355 */       this.errorHandler.fatalError(exception);
/*      */     }
/*  357 */     return exp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean parsePriority(InputSource source) throws CSSException, IOException {
/*  366 */     this.scanner = createScanner(source);
/*  367 */     return parsePriorityInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean parsePriorityInternal() throws CSSException, IOException {
/*  375 */     nextIgnoreSpaces();
/*      */     
/*  377 */     this.scanner.close();
/*  378 */     this.scanner = null;
/*      */     
/*  380 */     switch (this.current) {
/*      */       case 0:
/*  382 */         return false;
/*      */       case 28:
/*  384 */         return true;
/*      */     } 
/*  386 */     reportError("token", new Object[] { Integer.valueOf(this.current) });
/*  387 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseRule() {
/*  395 */     switch (this.scanner.getType()) {
/*      */       case 28:
/*  397 */         nextIgnoreSpaces();
/*  398 */         parseImportRule();
/*      */         return;
/*      */       case 29:
/*  401 */         nextIgnoreSpaces();
/*  402 */         parseAtRule();
/*      */         return;
/*      */       case 31:
/*  405 */         nextIgnoreSpaces();
/*  406 */         parseFontFaceRule();
/*      */         return;
/*      */       case 32:
/*  409 */         nextIgnoreSpaces();
/*  410 */         parseMediaRule();
/*      */         return;
/*      */       case 33:
/*  413 */         nextIgnoreSpaces();
/*  414 */         parsePageRule();
/*      */         return;
/*      */     } 
/*  417 */     parseRuleSet();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseAtRule() {
/*  425 */     this.scanner.scanAtRule();
/*  426 */     this.documentHandler.ignorableAtRule(this.scanner.getStringValue());
/*  427 */     nextIgnoreSpaces();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseImportRule() {
/*      */     CSSSACMediaList ml;
/*  434 */     String uri = null;
/*  435 */     switch (this.current) {
/*      */       default:
/*  437 */         reportError("string.or.uri"); return;
/*      */       case 19:
/*      */       case 51:
/*      */         break;
/*  441 */     }  uri = this.scanner.getStringValue();
/*  442 */     nextIgnoreSpaces();
/*      */ 
/*      */ 
/*      */     
/*  446 */     if (this.current != 20) {
/*  447 */       ml = new CSSSACMediaList();
/*  448 */       ml.append("all");
/*      */     } else {
/*  450 */       ml = parseMediaList();
/*      */     } 
/*      */     
/*  453 */     this.documentHandler.importStyle(uri, ml, null);
/*      */     
/*  455 */     if (this.current != 8) {
/*  456 */       reportError("semicolon");
/*      */     } else {
/*  458 */       next();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CSSSACMediaList parseMediaList() {
/*  466 */     CSSSACMediaList result = new CSSSACMediaList();
/*  467 */     result.append(this.scanner.getStringValue());
/*  468 */     nextIgnoreSpaces();
/*      */     
/*  470 */     while (this.current == 6) {
/*  471 */       nextIgnoreSpaces();
/*      */       
/*  473 */       switch (this.current) {
/*      */ 
/*      */ 
/*      */         
/*      */         case 20:
/*  478 */           result.append(this.scanner.getStringValue());
/*  479 */           nextIgnoreSpaces(); break;
/*      */       } 
/*      */     } 
/*  482 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseFontFaceRule() {
/*      */     try {
/*  490 */       this.documentHandler.startFontFace();
/*      */       
/*  492 */       if (this.current != 1) {
/*  493 */         reportError("left.curly.brace");
/*      */       } else {
/*  495 */         nextIgnoreSpaces();
/*      */         
/*      */         try {
/*  498 */           parseStyleDeclaration(true);
/*  499 */         } catch (CSSParseException e) {
/*  500 */           reportError(e);
/*      */         } 
/*      */       } 
/*      */     } finally {
/*  504 */       this.documentHandler.endFontFace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parsePageRule() {
/*  512 */     String page = null;
/*  513 */     String ppage = null;
/*      */     
/*  515 */     if (this.current == 20) {
/*  516 */       page = this.scanner.getStringValue();
/*  517 */       nextIgnoreSpaces();
/*      */       
/*  519 */       if (this.current == 16) {
/*  520 */         nextIgnoreSpaces();
/*      */         
/*  522 */         if (this.current != 20) {
/*  523 */           reportError("identifier");
/*      */           return;
/*      */         } 
/*  526 */         ppage = this.scanner.getStringValue();
/*  527 */         nextIgnoreSpaces();
/*      */       } 
/*      */     } 
/*      */     
/*      */     try {
/*  532 */       this.documentHandler.startPage(page, ppage);
/*      */       
/*  534 */       if (this.current != 1) {
/*  535 */         reportError("left.curly.brace");
/*      */       } else {
/*  537 */         nextIgnoreSpaces();
/*      */         
/*      */         try {
/*  540 */           parseStyleDeclaration(true);
/*  541 */         } catch (CSSParseException e) {
/*  542 */           reportError(e);
/*      */         } 
/*      */       } 
/*      */     } finally {
/*  546 */       this.documentHandler.endPage(page, ppage);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseMediaRule() {
/*  554 */     if (this.current != 20) {
/*  555 */       reportError("identifier");
/*      */       
/*      */       return;
/*      */     } 
/*  559 */     CSSSACMediaList ml = parseMediaList();
/*      */     try {
/*  561 */       this.documentHandler.startMedia(ml);
/*      */       
/*  563 */       if (this.current != 1) {
/*  564 */         reportError("left.curly.brace");
/*      */       } else {
/*  566 */         nextIgnoreSpaces();
/*      */         
/*      */         while (true) {
/*  569 */           switch (this.current) {
/*      */             case 0:
/*      */             case 2:
/*      */               break;
/*      */           } 
/*  574 */           parseRuleSet();
/*      */         } 
/*      */ 
/*      */         
/*  578 */         nextIgnoreSpaces();
/*      */       } 
/*      */     } finally {
/*  581 */       this.documentHandler.endMedia(ml);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseRuleSet() {
/*  589 */     SelectorList sl = null;
/*      */     
/*      */     try {
/*  592 */       sl = parseSelectorList();
/*  593 */     } catch (CSSParseException e) {
/*  594 */       reportError(e);
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/*  599 */       this.documentHandler.startSelector(sl);
/*      */       
/*  601 */       if (this.current != 1) {
/*  602 */         reportError("left.curly.brace");
/*  603 */         if (this.current == 2) {
/*  604 */           nextIgnoreSpaces();
/*      */         }
/*      */       } else {
/*  607 */         nextIgnoreSpaces();
/*      */         
/*      */         try {
/*  610 */           parseStyleDeclaration(true);
/*  611 */         } catch (CSSParseException e) {
/*  612 */           reportError(e);
/*      */         } 
/*      */       } 
/*      */     } finally {
/*  616 */       this.documentHandler.endSelector(sl);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SelectorList parseSelectorList() {
/*  624 */     CSSSelectorList result = new CSSSelectorList();
/*  625 */     result.append(parseSelector());
/*      */     
/*      */     while (true) {
/*  628 */       if (this.current != 6) {
/*  629 */         return result;
/*      */       }
/*  631 */       nextIgnoreSpaces();
/*  632 */       result.append(parseSelector());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Selector parseSelector() {
/*      */     DescendantSelector descendantSelector;
/*  640 */     this.pseudoElement = null;
/*  641 */     SimpleSelector simpleSelector = parseSimpleSelector(); while (true) {
/*      */       DescendantSelector descendantSelector1;
/*      */       SiblingSelector siblingSelector;
/*  644 */       switch (this.current) {
/*      */         default:
/*      */           break;
/*      */         case 7:
/*      */         case 11:
/*      */         case 13:
/*      */         case 16:
/*      */         case 20:
/*      */         case 27:
/*  653 */           if (this.pseudoElement != null) {
/*  654 */             throw createCSSParseException("pseudo.element.position");
/*      */           }
/*  656 */           descendantSelector1 = this.selectorFactory.createDescendantSelector((Selector)simpleSelector, parseSimpleSelector());
/*      */           continue;
/*      */ 
/*      */         
/*      */         case 4:
/*  661 */           if (this.pseudoElement != null) {
/*  662 */             throw createCSSParseException("pseudo.element.position");
/*      */           }
/*  664 */           nextIgnoreSpaces();
/*  665 */           siblingSelector = this.selectorFactory.createDirectAdjacentSelector((short)1, (Selector)descendantSelector1, parseSimpleSelector());
/*      */           continue;
/*      */         
/*      */         case 9:
/*      */           break;
/*      */       } 
/*  671 */       if (this.pseudoElement != null) {
/*  672 */         throw createCSSParseException("pseudo.element.position");
/*      */       }
/*  674 */       nextIgnoreSpaces();
/*  675 */       descendantSelector = this.selectorFactory.createChildSelector((Selector)siblingSelector, parseSimpleSelector());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  680 */     if (this.pseudoElement != null) {
/*  681 */       descendantSelector = this.selectorFactory.createChildSelector((Selector)descendantSelector, (SimpleSelector)this.selectorFactory.createPseudoElementSelector(null, this.pseudoElement));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  686 */     return (Selector)descendantSelector;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected SimpleSelector parseSimpleSelector() {
/*      */     ElementSelector elementSelector;
/*      */     ConditionalSelector conditionalSelector;
/*      */     CombinatorCondition combinatorCondition;
/*  695 */     switch (this.current) {
/*      */       case 20:
/*  697 */         elementSelector = this.selectorFactory.createElementSelector(null, this.scanner.getStringValue());
/*      */         
/*  699 */         next();
/*      */         break;
/*      */       case 13:
/*  702 */         next();
/*      */       default:
/*  704 */         elementSelector = this.selectorFactory.createElementSelector(null, null); break;
/*      */     } 
/*  706 */     Condition cond = null; while (true) {
/*      */       AttributeCondition attributeCondition; LangCondition langCondition; String name; int op; String val, func, lang;
/*  708 */       Condition c = null;
/*  709 */       switch (this.current) {
/*      */         case 27:
/*  711 */           attributeCondition = this.conditionFactory.createIdCondition(this.scanner.getStringValue());
/*      */           
/*  713 */           next();
/*      */           break;
/*      */         case 7:
/*  716 */           if (next() != 20) {
/*  717 */             throw createCSSParseException("identifier");
/*      */           }
/*  719 */           attributeCondition = this.conditionFactory.createClassCondition(null, this.scanner.getStringValue());
/*      */           
/*  721 */           next();
/*      */           break;
/*      */         case 11:
/*  724 */           if (nextIgnoreSpaces() != 20) {
/*  725 */             throw createCSSParseException("identifier");
/*      */           }
/*  727 */           name = this.scanner.getStringValue();
/*  728 */           op = nextIgnoreSpaces();
/*  729 */           switch (op) {
/*      */             default:
/*  731 */               throw createCSSParseException("right.bracket");
/*      */             case 12:
/*  733 */               next();
/*  734 */               attributeCondition = this.conditionFactory.createAttributeCondition(name, null, false, null); break;
/*      */             case 3:
/*      */             case 25:
/*      */             case 26:
/*      */               break;
/*      */           } 
/*  740 */           val = null;
/*  741 */           switch (nextIgnoreSpaces())
/*      */           { default:
/*  743 */               throw createCSSParseException("identifier.or.string");
/*      */             case 19:
/*      */             case 20:
/*  746 */               break; }  val = this.scanner.getStringValue();
/*  747 */           nextIgnoreSpaces();
/*      */           
/*  749 */           if (this.current != 12) {
/*  750 */             throw createCSSParseException("right.bracket");
/*      */           }
/*  752 */           next();
/*  753 */           switch (op) {
/*      */             case 3:
/*  755 */               attributeCondition = this.conditionFactory.createAttributeCondition(name, null, false, val);
/*      */               break;
/*      */             
/*      */             case 26:
/*  759 */               attributeCondition = this.conditionFactory.createOneOfAttributeCondition(name, null, false, val);
/*      */               break;
/*      */           } 
/*      */           
/*  763 */           attributeCondition = this.conditionFactory.createBeginHyphenAttributeCondition(name, null, false, val);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 16:
/*  770 */           switch (nextIgnoreSpaces()) {
/*      */             case 20:
/*  772 */               val = this.scanner.getStringValue();
/*  773 */               if (isPseudoElement(val)) {
/*  774 */                 if (this.pseudoElement != null) {
/*  775 */                   throw createCSSParseException("duplicate.pseudo.element");
/*      */                 }
/*      */                 
/*  778 */                 this.pseudoElement = val;
/*      */               } else {
/*  780 */                 attributeCondition = this.conditionFactory.createPseudoClassCondition(null, val);
/*      */               } 
/*      */               
/*  783 */               next();
/*      */               break;
/*      */             case 52:
/*  786 */               func = this.scanner.getStringValue();
/*  787 */               if (nextIgnoreSpaces() != 20) {
/*  788 */                 throw createCSSParseException("identifier");
/*      */               }
/*  790 */               lang = this.scanner.getStringValue();
/*  791 */               if (nextIgnoreSpaces() != 15) {
/*  792 */                 throw createCSSParseException("right.brace");
/*      */               }
/*      */               
/*  795 */               if (!func.equalsIgnoreCase("lang")) {
/*  796 */                 throw createCSSParseException("pseudo.function");
/*      */               }
/*      */               
/*  799 */               langCondition = this.conditionFactory.createLangCondition(lang);
/*      */               
/*  801 */               next();
/*      */               break;
/*      */           } 
/*  804 */           throw createCSSParseException("identifier");
/*      */         
/*      */         default:
/*      */           break;
/*      */       } 
/*      */       
/*  810 */       if (langCondition != null) {
/*  811 */         LangCondition langCondition1; if (cond == null) {
/*  812 */           langCondition1 = langCondition; continue;
/*      */         } 
/*  814 */         combinatorCondition = this.conditionFactory.createAndCondition((Condition)langCondition1, (Condition)langCondition);
/*      */       } 
/*      */     } 
/*      */     
/*  818 */     skipSpaces();
/*  819 */     if (combinatorCondition != null) {
/*  820 */       conditionalSelector = this.selectorFactory.createConditionalSelector((SimpleSelector)elementSelector, (Condition)combinatorCondition);
/*      */     }
/*  822 */     return (SimpleSelector)conditionalSelector;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isPseudoElement(String s) {
/*  829 */     switch (s.charAt(0)) {
/*      */       case 'A':
/*      */       case 'a':
/*  832 */         return s.equalsIgnoreCase("after");
/*      */       case 'B':
/*      */       case 'b':
/*  835 */         return s.equalsIgnoreCase("before");
/*      */       case 'F':
/*      */       case 'f':
/*  838 */         return (s.equalsIgnoreCase("first-letter") || s.equalsIgnoreCase("first-line"));
/*      */     } 
/*      */     
/*  841 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseStyleDeclaration(boolean inSheet) throws CSSException {
/*      */     while (true) {
/*      */       String name;
/*      */       LexicalUnit exp;
/*  850 */       switch (this.current) {
/*      */         case 0:
/*  852 */           if (inSheet) {
/*  853 */             throw createCSSParseException("eof");
/*      */           }
/*      */           return;
/*      */         case 2:
/*  857 */           if (!inSheet) {
/*  858 */             throw createCSSParseException("eof.expected");
/*      */           }
/*  860 */           nextIgnoreSpaces();
/*      */           return;
/*      */         case 8:
/*  863 */           nextIgnoreSpaces();
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 20:
/*  870 */           name = this.scanner.getStringValue();
/*      */           
/*  872 */           if (nextIgnoreSpaces() != 16) {
/*  873 */             throw createCSSParseException("colon");
/*      */           }
/*  875 */           nextIgnoreSpaces();
/*      */           
/*  877 */           exp = null;
/*      */           
/*      */           try {
/*  880 */             exp = parseExpression(false);
/*  881 */           } catch (CSSParseException e) {
/*  882 */             reportError(e);
/*      */           } 
/*      */           
/*  885 */           if (exp != null) {
/*  886 */             boolean important = false;
/*  887 */             if (this.current == 23) {
/*  888 */               important = true;
/*  889 */               nextIgnoreSpaces();
/*      */             } 
/*  891 */             this.documentHandler.property(name, exp, important);
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected LexicalUnit parseExpression(boolean param) {
/*  901 */     LexicalUnit result = parseTerm(null);
/*  902 */     LexicalUnit curr = result;
/*      */     
/*      */     while (true) {
/*  905 */       boolean op = false;
/*  906 */       switch (this.current) {
/*      */         case 6:
/*  908 */           op = true;
/*  909 */           curr = CSSLexicalUnit.createSimple((short)0, curr);
/*      */           
/*  911 */           nextIgnoreSpaces();
/*      */           break;
/*      */         case 10:
/*  914 */           op = true;
/*  915 */           curr = CSSLexicalUnit.createSimple((short)4, curr);
/*      */           
/*  917 */           nextIgnoreSpaces(); break;
/*      */       } 
/*  919 */       if (param) {
/*  920 */         if (this.current == 15) {
/*  921 */           if (op) {
/*  922 */             throw createCSSParseException("token", new Object[] { Integer.valueOf(this.current) });
/*      */           }
/*      */           
/*  925 */           return result;
/*      */         } 
/*  927 */         curr = parseTerm(curr); continue;
/*      */       } 
/*  929 */       switch (this.current) {
/*      */         case 0:
/*      */         case 2:
/*      */         case 8:
/*      */         case 23:
/*  934 */           if (op) {
/*  935 */             throw createCSSParseException("token", new Object[] { Integer.valueOf(this.current) });
/*      */           }
/*      */           
/*  938 */           return result;
/*      */       } 
/*  940 */       curr = parseTerm(curr);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LexicalUnit parseTerm(LexicalUnit prev) {
/*      */     String sval, val;
/*      */     long lVal;
/*  950 */     boolean plus = true;
/*  951 */     boolean sgn = false;
/*      */     
/*  953 */     switch (this.current) {
/*      */       case 5:
/*  955 */         plus = false;
/*      */       case 4:
/*  957 */         next();
/*  958 */         sgn = true; break;
/*      */     } 
/*  960 */     switch (this.current) {
/*      */       case 24:
/*  962 */         sval = this.scanner.getStringValue();
/*  963 */         if (!plus) sval = "-" + sval;
/*      */         
/*  965 */         lVal = Long.parseLong(sval);
/*  966 */         if (lVal >= -2147483648L && lVal <= 2147483647L) {
/*      */           
/*  968 */           int iVal = (int)lVal;
/*  969 */           nextIgnoreSpaces();
/*  970 */           return CSSLexicalUnit.createInteger(iVal, prev);
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/*      */       case 54:
/*  976 */         return CSSLexicalUnit.createFloat((short)14, number(plus), prev);
/*      */       
/*      */       case 42:
/*  979 */         return CSSLexicalUnit.createFloat((short)23, number(plus), prev);
/*      */       
/*      */       case 45:
/*  982 */         return CSSLexicalUnit.createFloat((short)21, number(plus), prev);
/*      */       
/*      */       case 44:
/*  985 */         return CSSLexicalUnit.createFloat((short)22, number(plus), prev);
/*      */       
/*      */       case 46:
/*  988 */         return CSSLexicalUnit.createFloat((short)17, number(plus), prev);
/*      */       
/*      */       case 37:
/*  991 */         return CSSLexicalUnit.createFloat((short)19, number(plus), prev);
/*      */       
/*      */       case 38:
/*  994 */         return CSSLexicalUnit.createFloat((short)20, number(plus), prev);
/*      */       
/*      */       case 39:
/*  997 */         return CSSLexicalUnit.createFloat((short)18, number(plus), prev);
/*      */       
/*      */       case 36:
/* 1000 */         return CSSLexicalUnit.createFloat((short)15, number(plus), prev);
/*      */       
/*      */       case 35:
/* 1003 */         return CSSLexicalUnit.createFloat((short)16, number(plus), prev);
/*      */       
/*      */       case 47:
/* 1006 */         return CSSLexicalUnit.createFloat((short)28, number(plus), prev);
/*      */       
/*      */       case 48:
/* 1009 */         return CSSLexicalUnit.createFloat((short)30, number(plus), prev);
/*      */       
/*      */       case 49:
/* 1012 */         return CSSLexicalUnit.createFloat((short)29, number(plus), prev);
/*      */       
/*      */       case 43:
/* 1015 */         return CSSLexicalUnit.createFloat((short)32, number(plus), prev);
/*      */       
/*      */       case 40:
/* 1018 */         return CSSLexicalUnit.createFloat((short)31, number(plus), prev);
/*      */       
/*      */       case 41:
/* 1021 */         return CSSLexicalUnit.createFloat((short)33, number(plus), prev);
/*      */       
/*      */       case 50:
/* 1024 */         return CSSLexicalUnit.createFloat((short)34, number(plus), prev);
/*      */       
/*      */       case 34:
/* 1027 */         return dimension(plus, prev);
/*      */       case 52:
/* 1029 */         return parseFunction(plus, prev);
/*      */     } 
/* 1031 */     if (sgn) {
/* 1032 */       throw createCSSParseException("token", new Object[] { Integer.valueOf(this.current) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1037 */     switch (this.current) {
/*      */       case 19:
/* 1039 */         val = this.scanner.getStringValue();
/* 1040 */         nextIgnoreSpaces();
/* 1041 */         return CSSLexicalUnit.createString((short)36, val, prev);
/*      */       
/*      */       case 20:
/* 1044 */         val = this.scanner.getStringValue();
/* 1045 */         nextIgnoreSpaces();
/* 1046 */         if (val.equalsIgnoreCase("inherit")) {
/* 1047 */           return CSSLexicalUnit.createSimple((short)12, prev);
/*      */         }
/*      */         
/* 1050 */         return CSSLexicalUnit.createString((short)35, val, prev);
/*      */ 
/*      */       
/*      */       case 51:
/* 1054 */         val = this.scanner.getStringValue();
/* 1055 */         nextIgnoreSpaces();
/* 1056 */         return CSSLexicalUnit.createString((short)24, val, prev);
/*      */       
/*      */       case 27:
/* 1059 */         return hexcolor(prev);
/*      */     } 
/* 1061 */     throw createCSSParseException("token", new Object[] { Integer.valueOf(this.current) });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LexicalUnit parseFunction(boolean positive, LexicalUnit prev) {
/* 1071 */     String name = this.scanner.getStringValue();
/* 1072 */     nextIgnoreSpaces();
/*      */     
/* 1074 */     LexicalUnit params = parseExpression(true);
/*      */     
/* 1076 */     if (this.current != 15) {
/* 1077 */       throw createCSSParseException("token", new Object[] { Integer.valueOf(this.current) });
/*      */     }
/*      */ 
/*      */     
/* 1081 */     nextIgnoreSpaces();
/*      */     
/* 1083 */     switch (name.charAt(0)) {
/*      */       
/*      */       case 'R':
/*      */       case 'r':
/* 1087 */         if (name.equalsIgnoreCase("rgb")) {
/* 1088 */           LexicalUnit lu = params;
/* 1089 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1092 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 13:
/*      */             case 23:
/*      */               break; }
/* 1097 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1099 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1102 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1106 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1108 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1111 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 13:
/*      */             case 23:
/*      */               break; }
/* 1116 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1118 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1121 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1125 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1127 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1130 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 13:
/*      */             case 23:
/*      */               break; }
/* 1135 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1137 */           if (lu != null) {
/*      */             break;
/*      */           }
/* 1140 */           return CSSLexicalUnit.createPredefinedFunction((short)27, params, prev);
/*      */         } 
/* 1142 */         if (name.equalsIgnoreCase("rect")) {
/* 1143 */           LexicalUnit lu = params;
/* 1144 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1147 */           switch (lu.getLexicalUnitType()) {
/*      */             default:
/*      */               break;
/*      */             case 13:
/* 1151 */               if (lu.getIntegerValue() != 0) {
/*      */                 break;
/*      */               }
/* 1154 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 35:
/* 1157 */               if (!lu.getStringValue().equalsIgnoreCase("auto")) {
/*      */                 break;
/*      */               }
/* 1160 */               lu = lu.getNextLexicalUnit();
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
/* 1171 */               lu = lu.getNextLexicalUnit(); break;
/*      */           } 
/* 1173 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1176 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1180 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1182 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1185 */           switch (lu.getLexicalUnitType()) {
/*      */             default:
/*      */               break;
/*      */             case 13:
/* 1189 */               if (lu.getIntegerValue() != 0) {
/*      */                 break;
/*      */               }
/* 1192 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 35:
/* 1195 */               if (!lu.getStringValue().equalsIgnoreCase("auto")) {
/*      */                 break;
/*      */               }
/* 1198 */               lu = lu.getNextLexicalUnit();
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
/* 1209 */               lu = lu.getNextLexicalUnit(); break;
/*      */           } 
/* 1211 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1214 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1218 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1220 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1223 */           switch (lu.getLexicalUnitType()) {
/*      */             default:
/*      */               break;
/*      */             case 13:
/* 1227 */               if (lu.getIntegerValue() != 0) {
/*      */                 break;
/*      */               }
/* 1230 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 35:
/* 1233 */               if (!lu.getStringValue().equalsIgnoreCase("auto")) {
/*      */                 break;
/*      */               }
/* 1236 */               lu = lu.getNextLexicalUnit();
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
/* 1247 */               lu = lu.getNextLexicalUnit(); break;
/*      */           } 
/* 1249 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1252 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1256 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1258 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1261 */           switch (lu.getLexicalUnitType()) {
/*      */             default:
/*      */               break;
/*      */             case 13:
/* 1265 */               if (lu.getIntegerValue() != 0) {
/*      */                 break;
/*      */               }
/* 1268 */               lu = lu.getNextLexicalUnit();
/*      */               break;
/*      */             case 35:
/* 1271 */               if (!lu.getStringValue().equalsIgnoreCase("auto")) {
/*      */                 break;
/*      */               }
/* 1274 */               lu = lu.getNextLexicalUnit();
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
/* 1285 */               lu = lu.getNextLexicalUnit(); break;
/*      */           } 
/* 1287 */           if (lu != null) {
/*      */             break;
/*      */           }
/* 1290 */           return CSSLexicalUnit.createPredefinedFunction((short)38, params, prev);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 'C':
/*      */       case 'c':
/* 1296 */         if (name.equalsIgnoreCase("counter")) {
/* 1297 */           LexicalUnit lexicalUnit = params;
/* 1298 */           if (lexicalUnit == null) {
/*      */             break;
/*      */           }
/* 1301 */           switch (lexicalUnit.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 35:
/*      */               break; }
/* 1305 */            lexicalUnit = lexicalUnit.getNextLexicalUnit();
/*      */           
/* 1307 */           if (lexicalUnit == null) {
/*      */             break;
/*      */           }
/* 1310 */           switch (lexicalUnit.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1314 */            lexicalUnit = lexicalUnit.getNextLexicalUnit();
/*      */           
/* 1316 */           if (lexicalUnit == null) {
/*      */             break;
/*      */           }
/* 1319 */           switch (lexicalUnit.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 35:
/*      */               break; }
/* 1323 */            lexicalUnit = lexicalUnit.getNextLexicalUnit();
/*      */           
/* 1325 */           if (lexicalUnit != null) {
/*      */             break;
/*      */           }
/* 1328 */           return CSSLexicalUnit.createPredefinedFunction((short)25, params, prev);
/*      */         } 
/* 1330 */         if (name.equalsIgnoreCase("counters")) {
/* 1331 */           LexicalUnit lu = params;
/* 1332 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1335 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 35:
/*      */               break; }
/* 1339 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1341 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1344 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1348 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1350 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1353 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 36:
/*      */               break; }
/* 1357 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1359 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1362 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 0:
/*      */               break; }
/* 1366 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1368 */           if (lu == null) {
/*      */             break;
/*      */           }
/* 1371 */           switch (lu.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 35:
/*      */               break; }
/* 1375 */            lu = lu.getNextLexicalUnit();
/*      */           
/* 1377 */           if (lu != null) {
/*      */             break;
/*      */           }
/* 1380 */           return CSSLexicalUnit.createPredefinedFunction((short)26, params, prev);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 'A':
/*      */       case 'a':
/* 1386 */         if (name.equalsIgnoreCase("attr")) {
/* 1387 */           LexicalUnit lexicalUnit = params;
/* 1388 */           if (lexicalUnit == null) {
/*      */             break;
/*      */           }
/* 1391 */           switch (lexicalUnit.getLexicalUnitType()) { default:
/*      */               break;
/*      */             case 35:
/*      */               break; }
/* 1395 */            lexicalUnit = lexicalUnit.getNextLexicalUnit();
/*      */           
/* 1397 */           if (lexicalUnit != null) {
/*      */             break;
/*      */           }
/* 1400 */           return CSSLexicalUnit.createString((short)37, params.getStringValue(), prev);
/*      */         } 
/*      */         break;
/*      */     } 
/*      */     
/* 1405 */     return CSSLexicalUnit.createFunction(name, params, prev);
/*      */   }
/*      */   protected LexicalUnit hexcolor(LexicalUnit prev) {
/*      */     char rc, gc, bc;
/*      */     int t, r, g, b;
/*      */     LexicalUnit tmp;
/*      */     char rc1, rc2, gc1, gc2, bc1, bc2;
/* 1412 */     String val = this.scanner.getStringValue();
/* 1413 */     int len = val.length();
/* 1414 */     LexicalUnit params = null;
/* 1415 */     switch (len) {
/*      */       case 3:
/* 1417 */         rc = Character.toLowerCase(val.charAt(0));
/* 1418 */         gc = Character.toLowerCase(val.charAt(1));
/* 1419 */         bc = Character.toLowerCase(val.charAt(2));
/* 1420 */         if (!ScannerUtilities.isCSSHexadecimalCharacter(rc) || !ScannerUtilities.isCSSHexadecimalCharacter(gc) || !ScannerUtilities.isCSSHexadecimalCharacter(bc))
/*      */         {
/*      */           
/* 1423 */           throw createCSSParseException("rgb.color", new Object[] { val });
/*      */         }
/*      */ 
/*      */         
/* 1427 */         r = t = (rc >= '0' && rc <= '9') ? (rc - 48) : (rc - 97 + 10);
/* 1428 */         t <<= 4;
/* 1429 */         r |= t;
/* 1430 */         g = t = (gc >= '0' && gc <= '9') ? (gc - 48) : (gc - 97 + 10);
/* 1431 */         t <<= 4;
/* 1432 */         g |= t;
/* 1433 */         b = t = (bc >= '0' && bc <= '9') ? (bc - 48) : (bc - 97 + 10);
/* 1434 */         t <<= 4;
/* 1435 */         b |= t;
/* 1436 */         params = CSSLexicalUnit.createInteger(r, null);
/*      */         
/* 1438 */         tmp = CSSLexicalUnit.createSimple((short)0, params);
/*      */         
/* 1440 */         tmp = CSSLexicalUnit.createInteger(g, tmp);
/* 1441 */         tmp = CSSLexicalUnit.createSimple((short)0, tmp);
/*      */         
/* 1443 */         tmp = CSSLexicalUnit.createInteger(b, tmp);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1480 */         nextIgnoreSpaces();
/* 1481 */         return CSSLexicalUnit.createPredefinedFunction((short)27, params, prev);case 6: rc1 = Character.toLowerCase(val.charAt(0)); rc2 = Character.toLowerCase(val.charAt(1)); gc1 = Character.toLowerCase(val.charAt(2)); gc2 = Character.toLowerCase(val.charAt(3)); bc1 = Character.toLowerCase(val.charAt(4)); bc2 = Character.toLowerCase(val.charAt(5)); if (!ScannerUtilities.isCSSHexadecimalCharacter(rc1) || !ScannerUtilities.isCSSHexadecimalCharacter(rc2) || !ScannerUtilities.isCSSHexadecimalCharacter(gc1) || !ScannerUtilities.isCSSHexadecimalCharacter(gc2) || !ScannerUtilities.isCSSHexadecimalCharacter(bc1) || !ScannerUtilities.isCSSHexadecimalCharacter(bc2)) throw createCSSParseException("rgb.color");  r = (rc1 >= '0' && rc1 <= '9') ? (rc1 - 48) : (rc1 - 97 + 10); r <<= 4; r |= (rc2 >= '0' && rc2 <= '9') ? (rc2 - 48) : (rc2 - 97 + 10); g = (gc1 >= '0' && gc1 <= '9') ? (gc1 - 48) : (gc1 - 97 + 10); g <<= 4; g |= (gc2 >= '0' && gc2 <= '9') ? (gc2 - 48) : (gc2 - 97 + 10); b = (bc1 >= '0' && bc1 <= '9') ? (bc1 - 48) : (bc1 - 97 + 10); b <<= 4; b |= (bc2 >= '0' && bc2 <= '9') ? (bc2 - 48) : (bc2 - 97 + 10); params = CSSLexicalUnit.createInteger(r, null); tmp = CSSLexicalUnit.createSimple((short)0, params); tmp = CSSLexicalUnit.createInteger(g, tmp); tmp = CSSLexicalUnit.createSimple((short)0, tmp); tmp = CSSLexicalUnit.createInteger(b, tmp); nextIgnoreSpaces(); return CSSLexicalUnit.createPredefinedFunction((short)27, params, prev);
/*      */     } 
/*      */     throw createCSSParseException("rgb.color", new Object[] { val });
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Scanner createScanner(InputSource source) {
/* 1489 */     this.documentURI = source.getURI();
/* 1490 */     if (this.documentURI == null) {
/* 1491 */       this.documentURI = "";
/*      */     }
/*      */     
/* 1494 */     Reader r = source.getCharacterStream();
/* 1495 */     if (r != null) {
/* 1496 */       return new Scanner(r);
/*      */     }
/*      */     
/* 1499 */     InputStream is = source.getByteStream();
/* 1500 */     if (is != null) {
/* 1501 */       return new Scanner(is, source.getEncoding());
/*      */     }
/*      */     
/* 1504 */     String uri = source.getURI();
/* 1505 */     if (uri == null) {
/* 1506 */       throw new CSSException(formatMessage("empty.source", null));
/*      */     }
/*      */     
/*      */     try {
/* 1510 */       ParsedURL purl = new ParsedURL(uri);
/* 1511 */       is = purl.openStreamRaw("text/css");
/* 1512 */       return new Scanner(is, source.getEncoding());
/* 1513 */     } catch (IOException e) {
/* 1514 */       throw new CSSException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int skipSpaces() {
/* 1522 */     int lex = this.scanner.getType();
/* 1523 */     while (lex == 17) {
/* 1524 */       lex = next();
/*      */     }
/* 1526 */     return lex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int skipSpacesAndCDOCDC() {
/*      */     while (true) {
/* 1534 */       switch (this.current) {
/*      */         default:
/*      */           break;
/*      */         case 17:
/*      */         case 18:
/*      */         case 21:
/*      */         case 22:
/*      */           break;
/* 1542 */       }  this.scanner.clearBuffer();
/* 1543 */       next();
/*      */     } 
/* 1545 */     return this.current;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float number(boolean positive) {
/*      */     try {
/* 1553 */       float sgn = positive ? 1.0F : -1.0F;
/* 1554 */       String val = this.scanner.getStringValue();
/* 1555 */       nextIgnoreSpaces();
/* 1556 */       return sgn * Float.parseFloat(val);
/* 1557 */     } catch (NumberFormatException e) {
/* 1558 */       throw createCSSParseException("number.format");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LexicalUnit dimension(boolean positive, LexicalUnit prev) {
/*      */     try {
/* 1567 */       float sgn = positive ? 1.0F : -1.0F;
/* 1568 */       String val = this.scanner.getStringValue();
/*      */       int i;
/* 1570 */       for (i = 0; i < val.length(); i++)
/* 1571 */       { switch (val.charAt(i)) { default: break;
/*      */           case '.': case '0': case '1': case '2': case '3':
/*      */           case '4':
/*      */           case '5':
/*      */           case '6':
/*      */           case '7':
/*      */           case '8':
/*      */           case '9':
/* 1579 */             break; }  }  nextIgnoreSpaces();
/* 1580 */       return CSSLexicalUnit.createDimension(sgn * Float.parseFloat(val.substring(0, i)), val.substring(i), prev);
/*      */ 
/*      */     
/*      */     }
/* 1584 */     catch (NumberFormatException e) {
/* 1585 */       throw createCSSParseException("number.format");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int next() {
/*      */     try {
/*      */       while (true) {
/* 1595 */         this.scanner.clearBuffer();
/* 1596 */         this.current = this.scanner.next();
/* 1597 */         if (this.current == 18) {
/* 1598 */           this.documentHandler.comment(this.scanner.getStringValue());
/*      */           continue;
/*      */         } 
/*      */         break;
/*      */       } 
/* 1603 */       return this.current;
/* 1604 */     } catch (ParseException e) {
/* 1605 */       reportError(e.getMessage());
/* 1606 */       return this.current;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int nextIgnoreSpaces() {
/*      */     try {
/*      */       while (true) {
/* 1616 */         this.scanner.clearBuffer();
/* 1617 */         this.current = this.scanner.next();
/* 1618 */         switch (this.current) {
/*      */           case 18:
/* 1620 */             this.documentHandler.comment(this.scanner.getStringValue());
/*      */           default:
/*      */             break;
/*      */           case 17:
/*      */             break;
/*      */         } 
/*      */       } 
/* 1627 */       return this.current;
/* 1628 */     } catch (ParseException e) {
/* 1629 */       this.errorHandler.error(createCSSParseException(e.getMessage()));
/* 1630 */       return this.current;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportError(String key) {
/* 1638 */     reportError(key, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportError(String key, Object[] params) {
/* 1645 */     reportError(createCSSParseException(key, params));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void reportError(CSSParseException e) {
/* 1652 */     this.errorHandler.error(e);
/*      */     
/* 1654 */     int cbraces = 1;
/*      */     while (true) {
/* 1656 */       switch (this.current) {
/*      */         case 0:
/*      */           return;
/*      */         case 2:
/*      */         case 8:
/* 1661 */           if (--cbraces == 0) {
/* 1662 */             nextIgnoreSpaces();
/*      */             return;
/*      */           } 
/*      */         case 1:
/* 1666 */           cbraces++; break;
/*      */       } 
/* 1668 */       nextIgnoreSpaces();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CSSParseException createCSSParseException(String key) {
/* 1676 */     return createCSSParseException(key, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CSSParseException createCSSParseException(String key, Object[] params) {
/* 1684 */     return new CSSParseException(formatMessage(key, params), this.documentURI, this.scanner.getLine(), this.scanner.getColumn());
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
/*      */   public void parseStyleDeclaration(String source) throws CSSException, IOException {
/* 1699 */     this.scanner = new Scanner(source);
/* 1700 */     parseStyleDeclarationInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseRule(String source) throws CSSException, IOException {
/* 1707 */     this.scanner = new Scanner(source);
/* 1708 */     parseRuleInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SelectorList parseSelectors(String source) throws CSSException, IOException {
/* 1716 */     this.scanner = new Scanner(source);
/* 1717 */     return parseSelectorsInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public LexicalUnit parsePropertyValue(String source) throws CSSException, IOException {
/* 1725 */     this.scanner = new Scanner(source);
/* 1726 */     return parsePropertyValueInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean parsePriority(String source) throws CSSException, IOException {
/* 1734 */     this.scanner = new Scanner(source);
/* 1735 */     return parsePriorityInternal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SACMediaList parseMedia(String mediaText) throws CSSException, IOException {
/* 1743 */     CSSSACMediaList result = new CSSSACMediaList();
/* 1744 */     if (!"all".equalsIgnoreCase(mediaText)) {
/* 1745 */       StringTokenizer st = new StringTokenizer(mediaText, " ,");
/* 1746 */       while (st.hasMoreTokens()) {
/* 1747 */         result.append(st.nextToken());
/*      */       }
/*      */     } 
/* 1750 */     return result;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */