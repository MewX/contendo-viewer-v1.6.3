/*      */ package org.apache.batik.css.engine;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.StringReader;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashSet;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import org.apache.batik.css.engine.sac.CSSConditionFactory;
/*      */ import org.apache.batik.css.engine.sac.CSSSelectorFactory;
/*      */ import org.apache.batik.css.engine.sac.ExtendedSelector;
/*      */ import org.apache.batik.css.engine.value.ComputedValue;
/*      */ import org.apache.batik.css.engine.value.InheritValue;
/*      */ import org.apache.batik.css.engine.value.ShorthandManager;
/*      */ import org.apache.batik.css.engine.value.Value;
/*      */ import org.apache.batik.css.engine.value.ValueManager;
/*      */ import org.apache.batik.css.parser.ExtendedParser;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.w3c.css.sac.CSSException;
/*      */ import org.w3c.css.sac.ConditionFactory;
/*      */ import org.w3c.css.sac.DocumentHandler;
/*      */ import org.w3c.css.sac.InputSource;
/*      */ import org.w3c.css.sac.LexicalUnit;
/*      */ import org.w3c.css.sac.SACMediaList;
/*      */ import org.w3c.css.sac.SelectorList;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.events.EventListener;
/*      */ import org.w3c.dom.events.EventTarget;
/*      */ import org.w3c.dom.events.MutationEvent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class CSSEngine
/*      */ {
/*      */   protected CSSEngineUserAgent userAgent;
/*      */   protected CSSContext cssContext;
/*      */   protected Document document;
/*      */   protected ParsedURL documentURI;
/*      */   protected boolean isCSSNavigableDocument;
/*      */   protected StringIntMap indexes;
/*      */   protected StringIntMap shorthandIndexes;
/*      */   protected ValueManager[] valueManagers;
/*      */   protected ShorthandManager[] shorthandManagers;
/*      */   protected ExtendedParser parser;
/*      */   protected String[] pseudoElementNames;
/*      */   
/*      */   public static Node getCSSParentNode(Node n) {
/*   71 */     if (n instanceof CSSNavigableNode) {
/*   72 */       return ((CSSNavigableNode)n).getCSSParentNode();
/*      */     }
/*   74 */     return n.getParentNode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Node getCSSFirstChild(Node n) {
/*   81 */     if (n instanceof CSSNavigableNode) {
/*   82 */       return ((CSSNavigableNode)n).getCSSFirstChild();
/*      */     }
/*   84 */     return n.getFirstChild();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Node getCSSNextSibling(Node n) {
/*   91 */     if (n instanceof CSSNavigableNode) {
/*   92 */       return ((CSSNavigableNode)n).getCSSNextSibling();
/*      */     }
/*   94 */     return n.getNextSibling();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Node getCSSPreviousSibling(Node n) {
/*  101 */     if (n instanceof CSSNavigableNode) {
/*  102 */       return ((CSSNavigableNode)n).getCSSPreviousSibling();
/*      */     }
/*  104 */     return n.getPreviousSibling();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CSSStylableElement getParentCSSStylableElement(Element elt) {
/*  111 */     Node n = getCSSParentNode(elt);
/*  112 */     while (n != null) {
/*  113 */       if (n instanceof CSSStylableElement) {
/*  114 */         return (CSSStylableElement)n;
/*      */       }
/*  116 */       n = getCSSParentNode(n);
/*      */     } 
/*  118 */     return null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  179 */   protected int fontSizeIndex = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  184 */   protected int lineHeightIndex = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  189 */   protected int colorIndex = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected StyleSheet userAgentStyleSheet;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected StyleSheet userStyleSheet;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SACMediaList media;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List styleSheetNodes;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  215 */   protected List fontFaces = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String styleNamespaceURI;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String styleLocalName;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String classNamespaceURI;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String classLocalName;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Set nonCSSPresentationalHints;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String nonCSSPresentationalHintsNamespaceURI;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  250 */   protected StyleDeclarationDocumentHandler styleDeclarationDocumentHandler = new StyleDeclarationDocumentHandler();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected StyleDeclarationUpdateHandler styleDeclarationUpdateHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  261 */   protected StyleSheetDocumentHandler styleSheetDocumentHandler = new StyleSheetDocumentHandler();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  268 */   protected StyleDeclarationBuilder styleDeclarationBuilder = new StyleDeclarationBuilder();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CSSStylableElement element;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ParsedURL cssBaseURI;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String alternateStyleSheet;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CSSNavigableDocumentHandler cssNavigableDocumentListener;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EventListener domAttrModifiedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EventListener domNodeInsertedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EventListener domNodeRemovedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EventListener domSubtreeModifiedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EventListener domCharacterDataModifiedListener;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean styleSheetRemoved;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Node removedStylableElementSibling;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  329 */   protected List listeners = Collections.synchronizedList(new LinkedList());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Set selectorAttributes;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int[] ALL_PROPERTIES;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CSSConditionFactory cssConditionFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CSSEngine(Document doc, ParsedURL uri, ExtendedParser p, ValueManager[] vm, ShorthandManager[] sm, String[] pe, String sns, String sln, String cns, String cln, boolean hints, String hintsNS, CSSContext ctx) {
/*  378 */     this.document = doc;
/*  379 */     this.documentURI = uri;
/*  380 */     this.parser = p;
/*  381 */     this.pseudoElementNames = pe;
/*  382 */     this.styleNamespaceURI = sns;
/*  383 */     this.styleLocalName = sln;
/*  384 */     this.classNamespaceURI = cns;
/*  385 */     this.classLocalName = cln;
/*  386 */     this.cssContext = ctx;
/*      */     
/*  388 */     this.isCSSNavigableDocument = doc instanceof CSSNavigableDocument;
/*      */     
/*  390 */     this.cssConditionFactory = new CSSConditionFactory(cns, cln, null, "id");
/*      */     
/*  392 */     int len = vm.length;
/*  393 */     this.indexes = new StringIntMap(len);
/*  394 */     this.valueManagers = vm;
/*      */     int i;
/*  396 */     for (i = len - 1; i >= 0; i--) {
/*  397 */       String pn = vm[i].getPropertyName();
/*  398 */       this.indexes.put(pn, i);
/*  399 */       if (this.fontSizeIndex == -1 && pn.equals("font-size"))
/*      */       {
/*  401 */         this.fontSizeIndex = i;
/*      */       }
/*  403 */       if (this.lineHeightIndex == -1 && pn.equals("line-height"))
/*      */       {
/*  405 */         this.lineHeightIndex = i;
/*      */       }
/*  407 */       if (this.colorIndex == -1 && pn.equals("color"))
/*      */       {
/*  409 */         this.colorIndex = i;
/*      */       }
/*      */     } 
/*      */     
/*  413 */     len = sm.length;
/*  414 */     this.shorthandIndexes = new StringIntMap(len);
/*  415 */     this.shorthandManagers = sm;
/*  416 */     for (i = len - 1; i >= 0; i--) {
/*  417 */       this.shorthandIndexes.put(sm[i].getPropertyName(), i);
/*      */     }
/*      */     
/*  420 */     if (hints) {
/*  421 */       this.nonCSSPresentationalHints = new HashSet(vm.length + sm.length);
/*  422 */       this.nonCSSPresentationalHintsNamespaceURI = hintsNS;
/*  423 */       len = vm.length;
/*  424 */       for (i = 0; i < len; i++) {
/*  425 */         String pn = vm[i].getPropertyName();
/*  426 */         this.nonCSSPresentationalHints.add(pn);
/*      */       } 
/*  428 */       len = sm.length;
/*  429 */       for (i = 0; i < len; i++) {
/*  430 */         String pn = sm[i].getPropertyName();
/*  431 */         this.nonCSSPresentationalHints.add(pn);
/*      */       } 
/*      */     } 
/*      */     
/*  435 */     if (this.cssContext.isDynamic() && this.document instanceof EventTarget) {
/*      */       
/*  437 */       addEventListeners((EventTarget)this.document);
/*  438 */       this.styleDeclarationUpdateHandler = new StyleDeclarationUpdateHandler();
/*      */     } 
/*      */ 
/*      */     
/*  442 */     this.ALL_PROPERTIES = new int[getNumberOfProperties()];
/*  443 */     for (i = getNumberOfProperties() - 1; i >= 0; i--) {
/*  444 */       this.ALL_PROPERTIES[i] = i;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addEventListeners(EventTarget doc) {
/*  452 */     if (this.isCSSNavigableDocument) {
/*  453 */       this.cssNavigableDocumentListener = new CSSNavigableDocumentHandler();
/*  454 */       CSSNavigableDocument cnd = (CSSNavigableDocument)doc;
/*  455 */       cnd.addCSSNavigableDocumentListener(this.cssNavigableDocumentListener);
/*      */     } else {
/*  457 */       this.domAttrModifiedListener = new DOMAttrModifiedListener();
/*  458 */       doc.addEventListener("DOMAttrModified", this.domAttrModifiedListener, false);
/*      */ 
/*      */       
/*  461 */       this.domNodeInsertedListener = new DOMNodeInsertedListener();
/*  462 */       doc.addEventListener("DOMNodeInserted", this.domNodeInsertedListener, false);
/*      */ 
/*      */       
/*  465 */       this.domNodeRemovedListener = new DOMNodeRemovedListener();
/*  466 */       doc.addEventListener("DOMNodeRemoved", this.domNodeRemovedListener, false);
/*      */ 
/*      */       
/*  469 */       this.domSubtreeModifiedListener = new DOMSubtreeModifiedListener();
/*  470 */       doc.addEventListener("DOMSubtreeModified", this.domSubtreeModifiedListener, false);
/*      */ 
/*      */       
/*  473 */       this.domCharacterDataModifiedListener = new DOMCharacterDataModifiedListener();
/*      */       
/*  475 */       doc.addEventListener("DOMCharacterDataModified", this.domCharacterDataModifiedListener, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeEventListeners(EventTarget doc) {
/*  485 */     if (this.isCSSNavigableDocument) {
/*  486 */       CSSNavigableDocument cnd = (CSSNavigableDocument)doc;
/*  487 */       cnd.removeCSSNavigableDocumentListener(this.cssNavigableDocumentListener);
/*      */     } else {
/*      */       
/*  490 */       doc.removeEventListener("DOMAttrModified", this.domAttrModifiedListener, false);
/*      */ 
/*      */       
/*  493 */       doc.removeEventListener("DOMNodeInserted", this.domNodeInsertedListener, false);
/*      */ 
/*      */       
/*  496 */       doc.removeEventListener("DOMNodeRemoved", this.domNodeRemovedListener, false);
/*      */ 
/*      */       
/*  499 */       doc.removeEventListener("DOMSubtreeModified", this.domSubtreeModifiedListener, false);
/*      */ 
/*      */       
/*  502 */       doc.removeEventListener("DOMCharacterDataModified", this.domCharacterDataModifiedListener, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispose() {
/*  512 */     setCSSEngineUserAgent(null);
/*  513 */     disposeStyleMaps(this.document.getDocumentElement());
/*  514 */     if (this.document instanceof EventTarget)
/*      */     {
/*  516 */       removeEventListeners((EventTarget)this.document);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void disposeStyleMaps(Node node) {
/*  524 */     if (node instanceof CSSStylableElement) {
/*  525 */       ((CSSStylableElement)node).setComputedStyleMap((String)null, (StyleMap)null);
/*      */     }
/*  527 */     Node n = getCSSFirstChild(node);
/*  528 */     for (; n != null; 
/*  529 */       n = getCSSNextSibling(n)) {
/*  530 */       if (n.getNodeType() == 1) {
/*  531 */         disposeStyleMaps(n);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSContext getCSSContext() {
/*  540 */     return this.cssContext;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Document getDocument() {
/*  547 */     return this.document;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFontSizeIndex() {
/*  554 */     return this.fontSizeIndex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLineHeightIndex() {
/*  561 */     return this.lineHeightIndex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getColorIndex() {
/*  568 */     return this.colorIndex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfProperties() {
/*  575 */     return this.valueManagers.length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPropertyIndex(String name) {
/*  582 */     return this.indexes.get(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getShorthandIndex(String name) {
/*  589 */     return this.shorthandIndexes.get(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPropertyName(int idx) {
/*  596 */     return this.valueManagers[idx].getPropertyName();
/*      */   }
/*      */   
/*      */   public void setCSSEngineUserAgent(CSSEngineUserAgent userAgent) {
/*  600 */     this.userAgent = userAgent;
/*      */   }
/*      */   
/*      */   public CSSEngineUserAgent getCSSEngineUserAgent() {
/*  604 */     return this.userAgent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUserAgentStyleSheet(StyleSheet ss) {
/*  611 */     this.userAgentStyleSheet = ss;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUserStyleSheet(StyleSheet ss) {
/*  618 */     this.userStyleSheet = ss;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ValueManager[] getValueManagers() {
/*  625 */     return this.valueManagers;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ShorthandManager[] getShorthandManagers() {
/*  632 */     return this.shorthandManagers;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getFontFaces() {
/*  640 */     return this.fontFaces;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMedia(String str) {
/*      */     try {
/*  648 */       this.media = this.parser.parseMedia(str);
/*  649 */     } catch (Exception e) {
/*  650 */       String m = e.getMessage();
/*  651 */       if (m == null) m = ""; 
/*  652 */       String s = Messages.formatMessage("media.error", new Object[] { str, m });
/*      */       
/*  654 */       throw new DOMException((short)12, s);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAlternateStyleSheet(String str) {
/*  662 */     this.alternateStyleSheet = str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void importCascadedStyleMaps(Element src, CSSEngine srceng, Element dest) {
/*  672 */     if (src instanceof CSSStylableElement) {
/*  673 */       CSSStylableElement csrc = (CSSStylableElement)src;
/*  674 */       CSSStylableElement cdest = (CSSStylableElement)dest;
/*      */       
/*  676 */       StyleMap sm = srceng.getCascadedStyleMap(csrc, null);
/*  677 */       sm.setFixedCascadedStyle(true);
/*  678 */       cdest.setComputedStyleMap((String)null, sm);
/*      */       
/*  680 */       if (this.pseudoElementNames != null) {
/*  681 */         int len = this.pseudoElementNames.length;
/*  682 */         for (String pe : this.pseudoElementNames) {
/*  683 */           sm = srceng.getCascadedStyleMap(csrc, pe);
/*  684 */           cdest.setComputedStyleMap(pe, sm);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  689 */     Node dn = getCSSFirstChild(dest), sn = getCSSFirstChild(src);
/*  690 */     for (; dn != null; 
/*  691 */       dn = getCSSNextSibling(dn), sn = getCSSNextSibling(sn)) {
/*  692 */       if (sn.getNodeType() == 1) {
/*  693 */         importCascadedStyleMaps((Element)sn, srceng, (Element)dn);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ParsedURL getCSSBaseURI() {
/*  702 */     if (this.cssBaseURI == null) {
/*  703 */       this.cssBaseURI = this.element.getCSSBase();
/*      */     }
/*  705 */     return this.cssBaseURI;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StyleMap getCascadedStyleMap(CSSStylableElement elt, String pseudo) {
/*  715 */     int props = getNumberOfProperties();
/*  716 */     final StyleMap result = new StyleMap(props);
/*      */ 
/*      */     
/*  719 */     if (this.userAgentStyleSheet != null) {
/*  720 */       ArrayList rules = new ArrayList();
/*  721 */       addMatchingRules(rules, this.userAgentStyleSheet, elt, pseudo);
/*  722 */       addRules(elt, pseudo, result, rules, (short)0);
/*      */     } 
/*      */ 
/*      */     
/*  726 */     if (this.userStyleSheet != null) {
/*  727 */       ArrayList rules = new ArrayList();
/*  728 */       addMatchingRules(rules, this.userStyleSheet, elt, pseudo);
/*  729 */       addRules(elt, pseudo, result, rules, (short)8192);
/*      */     } 
/*      */     
/*  732 */     this.element = elt;
/*      */     
/*      */     try {
/*  735 */       if (this.nonCSSPresentationalHints != null) {
/*  736 */         ShorthandManager.PropertyHandler ph = new ShorthandManager.PropertyHandler()
/*      */           {
/*      */             public void property(String pname, LexicalUnit lu, boolean important)
/*      */             {
/*  740 */               int idx = CSSEngine.this.getPropertyIndex(pname);
/*  741 */               if (idx != -1) {
/*  742 */                 ValueManager vm = CSSEngine.this.valueManagers[idx];
/*  743 */                 Value v = vm.createValue(lu, CSSEngine.this);
/*  744 */                 CSSEngine.this.putAuthorProperty(result, idx, v, important, (short)16384);
/*      */                 
/*      */                 return;
/*      */               } 
/*  748 */               idx = CSSEngine.this.getShorthandIndex(pname);
/*  749 */               if (idx == -1) {
/*      */                 return;
/*      */               }
/*  752 */               CSSEngine.this.shorthandManagers[idx].setValues(CSSEngine.this, this, lu, important);
/*      */             }
/*      */           };
/*      */ 
/*      */         
/*  757 */         NamedNodeMap attrs = elt.getAttributes();
/*  758 */         int len = attrs.getLength();
/*  759 */         for (int i = 0; i < len; i++) {
/*  760 */           Node attr = attrs.item(i);
/*  761 */           String an = attr.getNodeName();
/*  762 */           if (this.nonCSSPresentationalHints.contains(an)) {
/*      */             
/*      */             try {
/*  765 */               LexicalUnit lu = this.parser.parsePropertyValue(attr.getNodeValue());
/*  766 */               ph.property(an, lu, false);
/*  767 */             } catch (Exception e) {
/*  768 */               String m = e.getMessage();
/*  769 */               if (m == null) m = ""; 
/*  770 */               String u = (this.documentURI == null) ? "<unknown>" : this.documentURI.toString();
/*      */               
/*  772 */               String s = Messages.formatMessage("property.syntax.error.at", new Object[] { u, an, attr.getNodeValue(), m });
/*      */ 
/*      */               
/*  775 */               DOMException de = new DOMException((short)12, s);
/*  776 */               if (this.userAgent == null) throw de; 
/*  777 */               this.userAgent.displayError(de);
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  784 */       CSSEngine eng = this.cssContext.getCSSEngineForElement(elt);
/*  785 */       List snodes = eng.getStyleSheetNodes();
/*  786 */       int slen = snodes.size();
/*  787 */       if (slen > 0) {
/*  788 */         ArrayList rules = new ArrayList();
/*  789 */         for (Object snode : snodes) {
/*  790 */           CSSStyleSheetNode ssn = (CSSStyleSheetNode)snode;
/*  791 */           StyleSheet ss = ssn.getCSSStyleSheet();
/*  792 */           if (ss != null && (!ss.isAlternate() || ss.getTitle() == null || ss.getTitle().equals(this.alternateStyleSheet)) && mediaMatch(ss.getMedia()))
/*      */           {
/*      */ 
/*      */ 
/*      */             
/*  797 */             addMatchingRules(rules, ss, elt, pseudo);
/*      */           }
/*      */         } 
/*  800 */         addRules(elt, pseudo, result, rules, (short)24576);
/*      */       } 
/*      */ 
/*      */       
/*  804 */       if (this.styleLocalName != null) {
/*  805 */         String style = elt.getAttributeNS(this.styleNamespaceURI, this.styleLocalName);
/*      */         
/*  807 */         if (style.length() > 0) {
/*      */           try {
/*  809 */             this.parser.setSelectorFactory(CSSSelectorFactory.INSTANCE);
/*  810 */             this.parser.setConditionFactory((ConditionFactory)this.cssConditionFactory);
/*  811 */             this.styleDeclarationDocumentHandler.styleMap = result;
/*  812 */             this.parser.setDocumentHandler(this.styleDeclarationDocumentHandler);
/*      */             
/*  814 */             this.parser.parseStyleDeclaration(style);
/*  815 */             this.styleDeclarationDocumentHandler.styleMap = null;
/*  816 */           } catch (Exception e) {
/*  817 */             String m = e.getMessage();
/*  818 */             if (m == null) m = e.getClass().getName(); 
/*  819 */             String u = (this.documentURI == null) ? "<unknown>" : this.documentURI.toString();
/*      */             
/*  821 */             String s = Messages.formatMessage("style.syntax.error.at", new Object[] { u, this.styleLocalName, style, m });
/*      */ 
/*      */             
/*  824 */             DOMException de = new DOMException((short)12, s);
/*  825 */             if (this.userAgent == null) throw de; 
/*  826 */             this.userAgent.displayError(de);
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  832 */       StyleDeclarationProvider p = elt.getOverrideStyleDeclarationProvider();
/*      */       
/*  834 */       if (p != null) {
/*  835 */         StyleDeclaration over = p.getStyleDeclaration();
/*  836 */         if (over != null) {
/*  837 */           int ol = over.size();
/*  838 */           for (int i = 0; i < ol; i++) {
/*  839 */             int idx = over.getIndex(i);
/*  840 */             Value value = over.getValue(i);
/*  841 */             boolean important = over.getPriority(i);
/*  842 */             if (!result.isImportant(idx) || important) {
/*  843 */               result.putValue(idx, value);
/*  844 */               result.putImportant(idx, important);
/*  845 */               result.putOrigin(idx, (short)-24576);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } finally {
/*  851 */       this.element = null;
/*  852 */       this.cssBaseURI = null;
/*      */     } 
/*      */     
/*  855 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Value getComputedStyle(CSSStylableElement elt, String pseudo, int propidx) {
/*      */     ComputedValue computedValue;
/*  865 */     StyleMap sm = elt.getComputedStyleMap(pseudo);
/*  866 */     if (sm == null) {
/*  867 */       sm = getCascadedStyleMap(elt, pseudo);
/*  868 */       elt.setComputedStyleMap(pseudo, sm);
/*      */     } 
/*      */     
/*  871 */     Value value = sm.getValue(propidx);
/*  872 */     if (sm.isComputed(propidx)) {
/*  873 */       return value;
/*      */     }
/*  875 */     Value result = value;
/*  876 */     ValueManager vm = this.valueManagers[propidx];
/*  877 */     CSSStylableElement p = getParentCSSStylableElement(elt);
/*  878 */     if (value == null) {
/*  879 */       if (p == null || !vm.isInheritedProperty())
/*  880 */         result = vm.getDefaultValue(); 
/*  881 */     } else if (p != null && value == InheritValue.INSTANCE) {
/*  882 */       result = null;
/*      */     } 
/*  884 */     if (result == null) {
/*      */ 
/*      */       
/*  887 */       result = getComputedStyle(p, null, propidx);
/*  888 */       sm.putParentRelative(propidx, true);
/*  889 */       sm.putInherited(propidx, true);
/*      */     } else {
/*      */       
/*  892 */       result = vm.computeValue(elt, pseudo, this, propidx, sm, result);
/*      */     } 
/*      */     
/*  895 */     if (value == null) {
/*  896 */       sm.putValue(propidx, result);
/*  897 */       sm.putNullCascaded(propidx, true);
/*  898 */     } else if (result != value) {
/*  899 */       ComputedValue cv = new ComputedValue(value);
/*  900 */       cv.setComputedValue(result);
/*  901 */       sm.putValue(propidx, (Value)cv);
/*  902 */       computedValue = cv;
/*      */     } 
/*      */     
/*  905 */     sm.putComputed(propidx, true);
/*  906 */     return (Value)computedValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getStyleSheetNodes() {
/*  914 */     if (this.styleSheetNodes == null) {
/*  915 */       this.styleSheetNodes = new ArrayList();
/*  916 */       this.selectorAttributes = new HashSet();
/*      */       
/*  918 */       findStyleSheetNodes(this.document);
/*  919 */       int len = this.styleSheetNodes.size();
/*  920 */       for (Object styleSheetNode : this.styleSheetNodes) {
/*      */         
/*  922 */         CSSStyleSheetNode ssn = (CSSStyleSheetNode)styleSheetNode;
/*  923 */         StyleSheet ss = ssn.getCSSStyleSheet();
/*  924 */         if (ss != null) {
/*  925 */           findSelectorAttributes(this.selectorAttributes, ss);
/*      */         }
/*      */       } 
/*      */     } 
/*  929 */     return this.styleSheetNodes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void findStyleSheetNodes(Node n) {
/*  936 */     if (n instanceof CSSStyleSheetNode) {
/*  937 */       this.styleSheetNodes.add(n);
/*      */     }
/*  939 */     Node nd = getCSSFirstChild(n);
/*  940 */     for (; nd != null; 
/*  941 */       nd = getCSSNextSibling(nd)) {
/*  942 */       findStyleSheetNodes(nd);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void findSelectorAttributes(Set attrs, StyleSheet ss) {
/*  950 */     int len = ss.getSize();
/*  951 */     for (int i = 0; i < len; i++) {
/*  952 */       StyleRule style; SelectorList sl; int slen, j; MediaRule mr; Rule r = ss.getRule(i);
/*  953 */       switch (r.getType()) {
/*      */         case 0:
/*  955 */           style = (StyleRule)r;
/*  956 */           sl = style.getSelectorList();
/*  957 */           slen = sl.getLength();
/*  958 */           for (j = 0; j < slen; j++) {
/*  959 */             ExtendedSelector s = (ExtendedSelector)sl.item(j);
/*  960 */             s.fillAttributeSet(attrs);
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 1:
/*      */         case 2:
/*  966 */           mr = (MediaRule)r;
/*  967 */           if (mediaMatch(mr.getMediaList())) {
/*  968 */             findSelectorAttributes(attrs, mr);
/*      */           }
/*      */           break;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMainProperties(CSSStylableElement elt, final MainPropertyReceiver dst, String pname, String value, boolean important) {
/*      */     try {
/*  991 */       this.element = elt;
/*  992 */       LexicalUnit lu = this.parser.parsePropertyValue(value);
/*  993 */       ShorthandManager.PropertyHandler ph = new ShorthandManager.PropertyHandler()
/*      */         {
/*      */           public void property(String pname, LexicalUnit lu, boolean important)
/*      */           {
/*  997 */             int idx = CSSEngine.this.getPropertyIndex(pname);
/*  998 */             if (idx != -1) {
/*  999 */               ValueManager vm = CSSEngine.this.valueManagers[idx];
/* 1000 */               Value v = vm.createValue(lu, CSSEngine.this);
/* 1001 */               dst.setMainProperty(pname, v, important);
/*      */               return;
/*      */             } 
/* 1004 */             idx = CSSEngine.this.getShorthandIndex(pname);
/* 1005 */             if (idx == -1) {
/*      */               return;
/*      */             }
/* 1008 */             CSSEngine.this.shorthandManagers[idx].setValues(CSSEngine.this, this, lu, important);
/*      */           }
/*      */         };
/*      */       
/* 1012 */       ph.property(pname, lu, important);
/* 1013 */     } catch (Exception e) {
/* 1014 */       String m = e.getMessage();
/* 1015 */       if (m == null) m = ""; 
/* 1016 */       String u = (this.documentURI == null) ? "<unknown>" : this.documentURI.toString();
/*      */       
/* 1018 */       String s = Messages.formatMessage("property.syntax.error.at", new Object[] { u, pname, value, m });
/*      */ 
/*      */       
/* 1021 */       DOMException de = new DOMException((short)12, s);
/* 1022 */       if (this.userAgent == null) throw de; 
/* 1023 */       this.userAgent.displayError(de);
/*      */     } finally {
/* 1025 */       this.element = null;
/* 1026 */       this.cssBaseURI = null;
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
/*      */   public Value parsePropertyValue(CSSStylableElement elt, String prop, String value) {
/* 1038 */     int idx = getPropertyIndex(prop);
/* 1039 */     if (idx == -1) return null; 
/* 1040 */     ValueManager vm = this.valueManagers[idx];
/*      */     try {
/* 1042 */       this.element = elt;
/*      */       
/* 1044 */       LexicalUnit lu = this.parser.parsePropertyValue(value);
/* 1045 */       return vm.createValue(lu, this);
/* 1046 */     } catch (Exception e) {
/* 1047 */       String m = e.getMessage();
/* 1048 */       if (m == null) m = ""; 
/* 1049 */       String u = (this.documentURI == null) ? "<unknown>" : this.documentURI.toString();
/*      */       
/* 1051 */       String s = Messages.formatMessage("property.syntax.error.at", new Object[] { u, prop, value, m });
/*      */ 
/*      */       
/* 1054 */       DOMException de = new DOMException((short)12, s);
/* 1055 */       if (this.userAgent == null) throw de; 
/* 1056 */       this.userAgent.displayError(de);
/*      */     } finally {
/* 1058 */       this.element = null;
/* 1059 */       this.cssBaseURI = null;
/*      */     } 
/* 1061 */     return vm.getDefaultValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StyleDeclaration parseStyleDeclaration(CSSStylableElement elt, String value) {
/* 1070 */     this.styleDeclarationBuilder.styleDeclaration = new StyleDeclaration();
/*      */     try {
/* 1072 */       this.element = elt;
/* 1073 */       this.parser.setSelectorFactory(CSSSelectorFactory.INSTANCE);
/* 1074 */       this.parser.setConditionFactory((ConditionFactory)this.cssConditionFactory);
/* 1075 */       this.parser.setDocumentHandler(this.styleDeclarationBuilder);
/* 1076 */       this.parser.parseStyleDeclaration(value);
/* 1077 */     } catch (Exception e) {
/* 1078 */       String m = e.getMessage();
/* 1079 */       if (m == null) m = ""; 
/* 1080 */       String u = (this.documentURI == null) ? "<unknown>" : this.documentURI.toString();
/*      */       
/* 1082 */       String s = Messages.formatMessage("syntax.error.at", new Object[] { u, m });
/*      */       
/* 1084 */       DOMException de = new DOMException((short)12, s);
/* 1085 */       if (this.userAgent == null) throw de; 
/* 1086 */       this.userAgent.displayError(de);
/*      */     } finally {
/* 1088 */       this.element = null;
/* 1089 */       this.cssBaseURI = null;
/*      */     } 
/* 1091 */     return this.styleDeclarationBuilder.styleDeclaration;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StyleSheet parseStyleSheet(ParsedURL uri, String media) throws DOMException {
/* 1101 */     StyleSheet ss = new StyleSheet();
/*      */     try {
/* 1103 */       ss.setMedia(this.parser.parseMedia(media));
/* 1104 */     } catch (Exception e) {
/* 1105 */       String m = e.getMessage();
/* 1106 */       if (m == null) m = ""; 
/* 1107 */       String u = (this.documentURI == null) ? "<unknown>" : this.documentURI.toString();
/*      */       
/* 1109 */       String s = Messages.formatMessage("syntax.error.at", new Object[] { u, m });
/*      */       
/* 1111 */       DOMException de = new DOMException((short)12, s);
/* 1112 */       if (this.userAgent == null) throw de; 
/* 1113 */       this.userAgent.displayError(de);
/* 1114 */       return ss;
/*      */     } 
/* 1116 */     parseStyleSheet(ss, uri);
/* 1117 */     return ss;
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
/*      */   public StyleSheet parseStyleSheet(InputSource is, ParsedURL uri, String media) throws DOMException {
/* 1129 */     StyleSheet ss = new StyleSheet();
/*      */     try {
/* 1131 */       ss.setMedia(this.parser.parseMedia(media));
/* 1132 */       parseStyleSheet(ss, is, uri);
/* 1133 */     } catch (Exception e) {
/* 1134 */       String m = e.getMessage();
/* 1135 */       if (m == null) m = ""; 
/* 1136 */       String u = (this.documentURI == null) ? "<unknown>" : this.documentURI.toString();
/*      */       
/* 1138 */       String s = Messages.formatMessage("syntax.error.at", new Object[] { u, m });
/*      */       
/* 1140 */       DOMException de = new DOMException((short)12, s);
/* 1141 */       if (this.userAgent == null) throw de; 
/* 1142 */       this.userAgent.displayError(de);
/*      */     } 
/* 1144 */     return ss;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void parseStyleSheet(StyleSheet ss, ParsedURL uri) throws DOMException {
/* 1154 */     if (uri == null) {
/* 1155 */       String s = Messages.formatMessage("syntax.error.at", new Object[] { "Null Document reference", "" });
/*      */ 
/*      */       
/* 1158 */       DOMException de = new DOMException((short)12, s);
/* 1159 */       if (this.userAgent == null) throw de; 
/* 1160 */       this.userAgent.displayError(de);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*      */     try {
/* 1166 */       this.cssContext.checkLoadExternalResource(uri, this.documentURI);
/* 1167 */       parseStyleSheet(ss, new InputSource(uri.toString()), uri);
/* 1168 */     } catch (SecurityException e) {
/* 1169 */       throw e;
/* 1170 */     } catch (Exception e) {
/* 1171 */       String m = e.getMessage();
/* 1172 */       if (m == null) m = e.getClass().getName(); 
/* 1173 */       String s = Messages.formatMessage("syntax.error.at", new Object[] { uri.toString(), m });
/*      */       
/* 1175 */       DOMException de = new DOMException((short)12, s);
/* 1176 */       if (this.userAgent == null) throw de; 
/* 1177 */       this.userAgent.displayError(de);
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
/*      */   public StyleSheet parseStyleSheet(String rules, ParsedURL uri, String media) throws DOMException {
/* 1189 */     StyleSheet ss = new StyleSheet();
/*      */     try {
/* 1191 */       ss.setMedia(this.parser.parseMedia(media));
/* 1192 */     } catch (Exception e) {
/* 1193 */       String m = e.getMessage();
/* 1194 */       if (m == null) m = ""; 
/* 1195 */       String u = (this.documentURI == null) ? "<unknown>" : this.documentURI.toString();
/*      */       
/* 1197 */       String s = Messages.formatMessage("syntax.error.at", new Object[] { u, m });
/*      */       
/* 1199 */       DOMException de = new DOMException((short)12, s);
/* 1200 */       if (this.userAgent == null) throw de; 
/* 1201 */       this.userAgent.displayError(de);
/* 1202 */       return ss;
/*      */     } 
/* 1204 */     parseStyleSheet(ss, rules, uri);
/* 1205 */     return ss;
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
/*      */   public void parseStyleSheet(StyleSheet ss, String rules, ParsedURL uri) throws DOMException {
/*      */     try {
/* 1218 */       parseStyleSheet(ss, new InputSource(new StringReader(rules)), uri);
/* 1219 */     } catch (Exception e) {
/*      */       
/* 1221 */       String m = e.getMessage();
/* 1222 */       if (m == null) m = ""; 
/* 1223 */       String s = Messages.formatMessage("stylesheet.syntax.error", new Object[] { uri.toString(), rules, m });
/*      */ 
/*      */       
/* 1226 */       DOMException de = new DOMException((short)12, s);
/* 1227 */       if (this.userAgent == null) throw de; 
/* 1228 */       this.userAgent.displayError(de);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void parseStyleSheet(StyleSheet ss, InputSource is, ParsedURL uri) throws IOException {
/* 1239 */     this.parser.setSelectorFactory(CSSSelectorFactory.INSTANCE);
/* 1240 */     this.parser.setConditionFactory((ConditionFactory)this.cssConditionFactory);
/*      */     try {
/* 1242 */       this.cssBaseURI = uri;
/* 1243 */       this.styleSheetDocumentHandler.styleSheet = ss;
/* 1244 */       this.parser.setDocumentHandler(this.styleSheetDocumentHandler);
/* 1245 */       this.parser.parseStyleSheet(is);
/*      */ 
/*      */       
/* 1248 */       int len = ss.getSize();
/* 1249 */       for (int i = 0; i < len; i++) {
/* 1250 */         Rule r = ss.getRule(i);
/* 1251 */         if (r.getType() != 2) {
/*      */           break;
/*      */         }
/*      */         
/* 1255 */         ImportRule ir = (ImportRule)r;
/* 1256 */         parseStyleSheet(ir, ir.getURI());
/*      */       } 
/*      */     } finally {
/* 1259 */       this.cssBaseURI = null;
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
/*      */   protected void putAuthorProperty(StyleMap dest, int idx, Value sval, boolean imp, short origin) {
/* 1272 */     Value dval = dest.getValue(idx);
/* 1273 */     short dorg = dest.getOrigin(idx);
/* 1274 */     boolean dimp = dest.isImportant(idx);
/*      */     
/* 1276 */     boolean cond = (dval == null);
/* 1277 */     if (!cond) {
/* 1278 */       switch (dorg) {
/*      */         case 8192:
/* 1280 */           cond = !dimp;
/*      */           break;
/*      */         case 24576:
/* 1283 */           cond = (!dimp || imp);
/*      */           break;
/*      */         case -24576:
/* 1286 */           cond = false;
/*      */           break;
/*      */         default:
/* 1289 */           cond = true;
/*      */           break;
/*      */       } 
/*      */     }
/* 1293 */     if (cond) {
/* 1294 */       dest.putValue(idx, sval);
/* 1295 */       dest.putImportant(idx, imp);
/* 1296 */       dest.putOrigin(idx, origin);
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
/*      */   protected void addMatchingRules(List<StyleRule> rules, StyleSheet ss, Element elt, String pseudo) {
/* 1308 */     int len = ss.getSize();
/* 1309 */     for (int i = 0; i < len; i++) {
/* 1310 */       StyleRule style; SelectorList sl; int slen, j; MediaRule mr; Rule r = ss.getRule(i);
/* 1311 */       switch (r.getType()) {
/*      */         case 0:
/* 1313 */           style = (StyleRule)r;
/* 1314 */           sl = style.getSelectorList();
/* 1315 */           slen = sl.getLength();
/* 1316 */           for (j = 0; j < slen; j++) {
/* 1317 */             ExtendedSelector s = (ExtendedSelector)sl.item(j);
/* 1318 */             if (s.match(elt, pseudo)) {
/* 1319 */               rules.add(style);
/*      */             }
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 1:
/*      */         case 2:
/* 1326 */           mr = (MediaRule)r;
/* 1327 */           if (mediaMatch(mr.getMediaList())) {
/* 1328 */             addMatchingRules(rules, mr, elt, pseudo);
/*      */           }
/*      */           break;
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
/*      */   
/*      */   protected void addRules(Element elt, String pseudo, StyleMap sm, ArrayList rules, short origin) {
/* 1343 */     sortRules(rules, elt, pseudo);
/* 1344 */     int rlen = rules.size();
/*      */     
/* 1346 */     if (origin == 24576) {
/* 1347 */       for (Object rule : rules) {
/* 1348 */         StyleRule sr = (StyleRule)rule;
/* 1349 */         StyleDeclaration sd = sr.getStyleDeclaration();
/* 1350 */         int len = sd.size();
/* 1351 */         for (int i = 0; i < len; i++) {
/* 1352 */           putAuthorProperty(sm, sd.getIndex(i), sd.getValue(i), sd.getPriority(i), origin);
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1360 */       for (Object rule : rules) {
/* 1361 */         StyleRule sr = (StyleRule)rule;
/* 1362 */         StyleDeclaration sd = sr.getStyleDeclaration();
/* 1363 */         int len = sd.size();
/* 1364 */         for (int i = 0; i < len; i++) {
/* 1365 */           int idx = sd.getIndex(i);
/* 1366 */           sm.putValue(idx, sd.getValue(i));
/* 1367 */           sm.putImportant(idx, sd.getPriority(i));
/* 1368 */           sm.putOrigin(idx, origin);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void sortRules(ArrayList<StyleRule> rules, Element elt, String pseudo) {
/* 1379 */     int len = rules.size();
/* 1380 */     int[] specificities = new int[len]; int i;
/* 1381 */     for (i = 0; i < len; i++) {
/* 1382 */       StyleRule r = rules.get(i);
/* 1383 */       SelectorList sl = r.getSelectorList();
/* 1384 */       int spec = 0;
/* 1385 */       int slen = sl.getLength();
/* 1386 */       for (int k = 0; k < slen; k++) {
/* 1387 */         ExtendedSelector s = (ExtendedSelector)sl.item(k);
/* 1388 */         if (s.match(elt, pseudo)) {
/* 1389 */           int sp = s.getSpecificity();
/* 1390 */           if (sp > spec) {
/* 1391 */             spec = sp;
/*      */           }
/*      */         } 
/*      */       } 
/* 1395 */       specificities[i] = spec;
/*      */     } 
/* 1397 */     for (i = 1; i < len; i++) {
/* 1398 */       Object rule = rules.get(i);
/* 1399 */       int spec = specificities[i];
/* 1400 */       int j = i - 1;
/* 1401 */       while (j >= 0 && specificities[j] > spec) {
/* 1402 */         rules.set(j + 1, rules.get(j));
/* 1403 */         specificities[j + 1] = specificities[j];
/* 1404 */         j--;
/*      */       } 
/* 1406 */       rules.set(j + 1, rule);
/* 1407 */       specificities[j + 1] = spec;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean mediaMatch(SACMediaList ml) {
/* 1416 */     if (this.media == null || ml == null || this.media.getLength() == 0 || ml.getLength() == 0)
/*      */     {
/*      */ 
/*      */       
/* 1420 */       return true;
/*      */     }
/* 1422 */     for (int i = 0; i < ml.getLength(); i++) {
/* 1423 */       if (ml.item(i).equalsIgnoreCase("all"))
/* 1424 */         return true; 
/* 1425 */       for (int j = 0; j < this.media.getLength(); j++) {
/* 1426 */         if (this.media.item(j).equalsIgnoreCase("all") || ml.item(i).equalsIgnoreCase(this.media.item(j)))
/*      */         {
/* 1428 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/* 1432 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static interface MainPropertyReceiver
/*      */   {
/*      */     void setMainProperty(String param1String, Value param1Value, boolean param1Boolean);
/*      */   }
/*      */ 
/*      */   
/*      */   protected class StyleDeclarationDocumentHandler
/*      */     extends DocumentAdapter
/*      */     implements ShorthandManager.PropertyHandler
/*      */   {
/*      */     public StyleMap styleMap;
/*      */     
/*      */     public void property(String name, LexicalUnit value, boolean important) throws CSSException {
/* 1449 */       int i = CSSEngine.this.getPropertyIndex(name);
/* 1450 */       if (i == -1) {
/* 1451 */         i = CSSEngine.this.getShorthandIndex(name);
/* 1452 */         if (i == -1) {
/*      */           return;
/*      */         }
/*      */         
/* 1456 */         CSSEngine.this.shorthandManagers[i].setValues(CSSEngine.this, this, value, important);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1461 */         Value v = CSSEngine.this.valueManagers[i].createValue(value, CSSEngine.this);
/* 1462 */         CSSEngine.this.putAuthorProperty(this.styleMap, i, v, important, -32768);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class StyleDeclarationBuilder
/*      */     extends DocumentAdapter
/*      */     implements ShorthandManager.PropertyHandler
/*      */   {
/*      */     public StyleDeclaration styleDeclaration;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void property(String name, LexicalUnit value, boolean important) throws CSSException {
/* 1482 */       int i = CSSEngine.this.getPropertyIndex(name);
/* 1483 */       if (i == -1) {
/* 1484 */         i = CSSEngine.this.getShorthandIndex(name);
/* 1485 */         if (i == -1) {
/*      */           return;
/*      */         }
/*      */         
/* 1489 */         CSSEngine.this.shorthandManagers[i].setValues(CSSEngine.this, this, value, important);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1494 */         Value v = CSSEngine.this.valueManagers[i].createValue(value, CSSEngine.this);
/* 1495 */         this.styleDeclaration.append(v, i, important);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class StyleSheetDocumentHandler
/*      */     extends DocumentAdapter
/*      */     implements ShorthandManager.PropertyHandler
/*      */   {
/*      */     public StyleSheet styleSheet;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected StyleRule styleRule;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected StyleDeclaration styleDeclaration;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void startDocument(InputSource source) throws CSSException {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void endDocument(InputSource source) throws CSSException {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void ignorableAtRule(String atRule) throws CSSException {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void importStyle(String uri, SACMediaList media, String defaultNamespaceURI) throws CSSException {
/*      */       ParsedURL url;
/* 1540 */       ImportRule ir = new ImportRule();
/* 1541 */       ir.setMediaList(media);
/* 1542 */       ir.setParent(this.styleSheet);
/* 1543 */       ParsedURL base = CSSEngine.this.getCSSBaseURI();
/*      */       
/* 1545 */       if (base == null) {
/* 1546 */         url = new ParsedURL(uri);
/*      */       } else {
/* 1548 */         url = new ParsedURL(base, uri);
/*      */       } 
/* 1550 */       ir.setURI(url);
/* 1551 */       this.styleSheet.append(ir);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void startMedia(SACMediaList media) throws CSSException {
/* 1559 */       MediaRule mr = new MediaRule();
/* 1560 */       mr.setMediaList(media);
/* 1561 */       mr.setParent(this.styleSheet);
/* 1562 */       this.styleSheet.append(mr);
/* 1563 */       this.styleSheet = mr;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void endMedia(SACMediaList media) throws CSSException {
/* 1571 */       this.styleSheet = this.styleSheet.getParent();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void startPage(String name, String pseudo_page) throws CSSException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void endPage(String name, String pseudo_page) throws CSSException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void startFontFace() throws CSSException {
/* 1595 */       this.styleDeclaration = new StyleDeclaration();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void endFontFace() throws CSSException {
/* 1603 */       StyleMap sm = new StyleMap(CSSEngine.this.getNumberOfProperties());
/* 1604 */       int len = this.styleDeclaration.size();
/* 1605 */       for (int i = 0; i < len; i++) {
/* 1606 */         int idx = this.styleDeclaration.getIndex(i);
/* 1607 */         sm.putValue(idx, this.styleDeclaration.getValue(i));
/* 1608 */         sm.putImportant(idx, this.styleDeclaration.getPriority(i));
/*      */         
/* 1610 */         sm.putOrigin(idx, (short)24576);
/*      */       } 
/* 1612 */       this.styleDeclaration = null;
/*      */       
/* 1614 */       int pidx = CSSEngine.this.getPropertyIndex("font-family");
/* 1615 */       Value fontFamily = sm.getValue(pidx);
/* 1616 */       if (fontFamily == null)
/*      */         return; 
/* 1618 */       ParsedURL base = CSSEngine.this.getCSSBaseURI();
/* 1619 */       CSSEngine.this.fontFaces.add(new FontFaceRule(sm, base));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void startSelector(SelectorList selectors) throws CSSException {
/* 1627 */       this.styleRule = new StyleRule();
/* 1628 */       this.styleRule.setSelectorList(selectors);
/* 1629 */       this.styleDeclaration = new StyleDeclaration();
/* 1630 */       this.styleRule.setStyleDeclaration(this.styleDeclaration);
/* 1631 */       this.styleSheet.append(this.styleRule);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void endSelector(SelectorList selectors) throws CSSException {
/* 1639 */       this.styleRule = null;
/* 1640 */       this.styleDeclaration = null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void property(String name, LexicalUnit value, boolean important) throws CSSException {
/* 1649 */       int i = CSSEngine.this.getPropertyIndex(name);
/* 1650 */       if (i == -1) {
/* 1651 */         i = CSSEngine.this.getShorthandIndex(name);
/* 1652 */         if (i == -1) {
/*      */           return;
/*      */         }
/*      */         
/* 1656 */         CSSEngine.this.shorthandManagers[i].setValues(CSSEngine.this, this, value, important);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1661 */         Value v = CSSEngine.this.valueManagers[i].createValue(value, CSSEngine.this);
/* 1662 */         this.styleDeclaration.append(v, i, important);
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
/*      */ 
/*      */   
/*      */   protected static class DocumentAdapter
/*      */     implements DocumentHandler
/*      */   {
/*      */     public void startDocument(InputSource source) {
/* 1679 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void endDocument(InputSource source) {
/* 1687 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void comment(String text) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void ignorableAtRule(String atRule) {
/* 1703 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void namespaceDeclaration(String prefix, String uri) {
/* 1711 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void importStyle(String uri, SACMediaList media, String defaultNamespaceURI) {
/* 1721 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void startMedia(SACMediaList media) {
/* 1729 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void endMedia(SACMediaList media) {
/* 1737 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void startPage(String name, String pseudo_page) {
/* 1745 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void endPage(String name, String pseudo_page) {
/* 1753 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void startFontFace() {
/* 1760 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void endFontFace() {
/* 1767 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void startSelector(SelectorList selectors) {
/* 1775 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void endSelector(SelectorList selectors) {
/* 1783 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void property(String name, LexicalUnit value, boolean important) {
/* 1791 */       throwUnsupportedEx();
/*      */     }
/*      */ 
/*      */     
/*      */     private void throwUnsupportedEx() {
/* 1796 */       throw new UnsupportedOperationException("you try to use an empty method in Adapter-class");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1802 */   protected static final CSSEngineListener[] LISTENER_ARRAY = new CSSEngineListener[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCSSEngineListener(CSSEngineListener l) {
/* 1809 */     this.listeners.add(l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeCSSEngineListener(CSSEngineListener l) {
/* 1816 */     this.listeners.remove(l);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void firePropertiesChangedEvent(Element target, int[] props) {
/* 1823 */     CSSEngineListener[] ll = (CSSEngineListener[])this.listeners.toArray((Object[])LISTENER_ARRAY);
/*      */ 
/*      */     
/* 1826 */     int len = ll.length;
/* 1827 */     if (len > 0) {
/* 1828 */       CSSEngineEvent evt = new CSSEngineEvent(this, target, props);
/* 1829 */       for (CSSEngineListener aLl : ll) {
/* 1830 */         aLl.propertiesChanged(evt);
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
/*      */ 
/*      */ 
/*      */   
/*      */   protected void inlineStyleAttributeUpdated(CSSStylableElement elt, StyleMap style, short attrChange, String prevValue, String newValue) {
/* 1845 */     boolean removed, updated[] = this.styleDeclarationUpdateHandler.updatedProperties;
/* 1846 */     for (int i = getNumberOfProperties() - 1; i >= 0; i--) {
/* 1847 */       updated[i] = false;
/*      */     }
/*      */     
/* 1850 */     switch (attrChange) {
/*      */       case 1:
/*      */       case 2:
/* 1853 */         if (newValue.length() > 0) {
/* 1854 */           this.element = elt;
/*      */           try {
/* 1856 */             this.parser.setSelectorFactory(CSSSelectorFactory.INSTANCE);
/* 1857 */             this.parser.setConditionFactory((ConditionFactory)this.cssConditionFactory);
/* 1858 */             this.styleDeclarationUpdateHandler.styleMap = style;
/* 1859 */             this.parser.setDocumentHandler(this.styleDeclarationUpdateHandler);
/* 1860 */             this.parser.parseStyleDeclaration(newValue);
/* 1861 */             this.styleDeclarationUpdateHandler.styleMap = null;
/* 1862 */           } catch (Exception e) {
/* 1863 */             String m = e.getMessage();
/* 1864 */             if (m == null) m = ""; 
/* 1865 */             String u = (this.documentURI == null) ? "<unknown>" : this.documentURI.toString();
/*      */             
/* 1867 */             String s = Messages.formatMessage("style.syntax.error.at", new Object[] { u, this.styleLocalName, newValue, m });
/*      */ 
/*      */             
/* 1870 */             DOMException de = new DOMException((short)12, s);
/* 1871 */             if (this.userAgent == null) throw de; 
/* 1872 */             this.userAgent.displayError(de);
/*      */           } finally {
/* 1874 */             this.element = null;
/* 1875 */             this.cssBaseURI = null;
/*      */           } 
/*      */         } 
/*      */ 
/*      */       
/*      */       case 3:
/* 1881 */         removed = false;
/*      */         
/* 1883 */         if (prevValue != null && prevValue.length() > 0)
/*      */         {
/*      */           
/* 1886 */           for (int j = getNumberOfProperties() - 1; j >= 0; j--) {
/* 1887 */             if (style.isComputed(j) && !updated[j]) {
/* 1888 */               short origin = style.getOrigin(j);
/* 1889 */               if (origin >= Short.MIN_VALUE) {
/* 1890 */                 removed = true;
/* 1891 */                 updated[j] = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*      */         
/* 1897 */         if (removed) {
/* 1898 */           invalidateProperties(elt, null, updated, true);
/*      */         } else {
/* 1900 */           int count = 0;
/*      */           
/* 1902 */           boolean fs = (this.fontSizeIndex == -1) ? false : updated[this.fontSizeIndex];
/*      */ 
/*      */           
/* 1905 */           boolean lh = (this.lineHeightIndex == -1) ? false : updated[this.lineHeightIndex];
/*      */ 
/*      */           
/* 1908 */           boolean cl = (this.colorIndex == -1) ? false : updated[this.colorIndex];
/*      */ 
/*      */ 
/*      */           
/* 1912 */           for (int j = getNumberOfProperties() - 1; j >= 0; j--) {
/* 1913 */             if (updated[j]) {
/* 1914 */               count++;
/*      */             }
/* 1916 */             else if ((fs && style.isFontSizeRelative(j)) || (lh && style.isLineHeightRelative(j)) || (cl && style.isColorRelative(j))) {
/*      */ 
/*      */               
/* 1919 */               updated[j] = true;
/* 1920 */               clearComputedValue(style, j);
/* 1921 */               count++;
/*      */             } 
/*      */           } 
/*      */           
/* 1925 */           if (count > 0) {
/* 1926 */             int[] props = new int[count];
/* 1927 */             count = 0;
/* 1928 */             for (int k = getNumberOfProperties() - 1; k >= 0; k--) {
/* 1929 */               if (updated[k]) {
/* 1930 */                 props[count++] = k;
/*      */               }
/*      */             } 
/* 1933 */             invalidateProperties(elt, props, null, true);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */     } 
/*      */ 
/*      */     
/* 1940 */     throw new IllegalStateException("Invalid attrChangeType");
/*      */   }
/*      */ 
/*      */   
/*      */   private static void clearComputedValue(StyleMap style, int n) {
/* 1945 */     if (style.isNullCascaded(n)) {
/* 1946 */       style.putValue(n, null);
/*      */     } else {
/* 1948 */       Value v = style.getValue(n);
/* 1949 */       if (v instanceof ComputedValue) {
/* 1950 */         ComputedValue cv = (ComputedValue)v;
/* 1951 */         v = cv.getCascadedValue();
/* 1952 */         style.putValue(n, v);
/*      */       } 
/*      */     } 
/* 1955 */     style.putComputed(n, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void invalidateProperties(Node node, int[] properties, boolean[] updated, boolean recascade) {
/* 1966 */     if (!(node instanceof CSSStylableElement)) {
/*      */       return;
/*      */     }
/* 1969 */     CSSStylableElement elt = (CSSStylableElement)node;
/* 1970 */     StyleMap style = elt.getComputedStyleMap((String)null);
/* 1971 */     if (style == null) {
/*      */       return;
/*      */     }
/* 1974 */     boolean[] diffs = new boolean[getNumberOfProperties()];
/* 1975 */     if (updated != null) {
/* 1976 */       System.arraycopy(updated, 0, diffs, 0, updated.length);
/*      */     }
/* 1978 */     if (properties != null) {
/* 1979 */       for (int property : properties) {
/* 1980 */         diffs[property] = true;
/*      */       }
/*      */     }
/* 1983 */     int count = 0;
/* 1984 */     if (!recascade) {
/* 1985 */       for (boolean diff : diffs) {
/* 1986 */         if (diff) {
/* 1987 */           count++;
/*      */         }
/*      */       } 
/*      */     } else {
/* 1991 */       StyleMap newStyle = getCascadedStyleMap(elt, null);
/* 1992 */       elt.setComputedStyleMap((String)null, newStyle);
/* 1993 */       for (int i = 0; i < diffs.length; i++) {
/* 1994 */         if (diffs[i]) {
/* 1995 */           count++;
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/* 2000 */         Value nv = newStyle.getValue(i);
/* 2001 */         Value ov = null;
/* 2002 */         if (!style.isNullCascaded(i)) {
/* 2003 */           ov = style.getValue(i);
/* 2004 */           if (ov instanceof ComputedValue) {
/* 2005 */             ov = ((ComputedValue)ov).getCascadedValue();
/*      */           }
/*      */         } 
/*      */         
/* 2009 */         if (nv == ov)
/* 2010 */           continue;  if (nv != null && ov != null) {
/* 2011 */           if (nv.equals(ov))
/* 2012 */             continue;  String ovCssText = ov.getCssText();
/* 2013 */           String nvCssText = nv.getCssText();
/* 2014 */           if (nvCssText == ovCssText || (nvCssText != null && nvCssText.equals(ovCssText))) {
/*      */             continue;
/*      */           }
/*      */         } 
/* 2018 */         count++;
/* 2019 */         diffs[i] = true; continue;
/*      */       } 
/*      */     } 
/* 2022 */     int[] props = null;
/* 2023 */     if (count != 0) {
/* 2024 */       props = new int[count];
/* 2025 */       count = 0;
/* 2026 */       for (int i = 0; i < diffs.length; i++) {
/* 2027 */         if (diffs[i])
/* 2028 */           props[count++] = i; 
/*      */       } 
/*      */     } 
/* 2031 */     propagateChanges(elt, props, recascade);
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
/*      */   protected void propagateChanges(Node node, int[] props, boolean recascade) {
/* 2043 */     if (!(node instanceof CSSStylableElement))
/*      */       return; 
/* 2045 */     CSSStylableElement elt = (CSSStylableElement)node;
/* 2046 */     StyleMap style = elt.getComputedStyleMap((String)null);
/* 2047 */     if (style != null) {
/* 2048 */       boolean[] updated = this.styleDeclarationUpdateHandler.updatedProperties;
/*      */       int i;
/* 2050 */       for (i = getNumberOfProperties() - 1; i >= 0; i--) {
/* 2051 */         updated[i] = false;
/*      */       }
/* 2053 */       if (props != null) {
/* 2054 */         for (i = props.length - 1; i >= 0; i--) {
/* 2055 */           int idx = props[i];
/* 2056 */           updated[idx] = true;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 2061 */       boolean fs = (this.fontSizeIndex == -1) ? false : updated[this.fontSizeIndex];
/*      */ 
/*      */       
/* 2064 */       boolean lh = (this.lineHeightIndex == -1) ? false : updated[this.lineHeightIndex];
/*      */ 
/*      */       
/* 2067 */       boolean cl = (this.colorIndex == -1) ? false : updated[this.colorIndex];
/*      */ 
/*      */ 
/*      */       
/* 2071 */       int count = 0; int j;
/* 2072 */       for (j = getNumberOfProperties() - 1; j >= 0; j--) {
/* 2073 */         if (updated[j]) {
/* 2074 */           count++;
/*      */         }
/* 2076 */         else if ((fs && style.isFontSizeRelative(j)) || (lh && style.isLineHeightRelative(j)) || (cl && style.isColorRelative(j))) {
/*      */ 
/*      */           
/* 2079 */           updated[j] = true;
/* 2080 */           clearComputedValue(style, j);
/* 2081 */           count++;
/*      */         } 
/*      */       } 
/*      */       
/* 2085 */       if (count == 0) {
/* 2086 */         props = null;
/*      */       } else {
/* 2088 */         props = new int[count];
/* 2089 */         count = 0;
/* 2090 */         for (j = getNumberOfProperties() - 1; j >= 0; j--) {
/* 2091 */           if (updated[j]) {
/* 2092 */             props[count++] = j;
/*      */           }
/*      */         } 
/* 2095 */         firePropertiesChangedEvent(elt, props);
/*      */       } 
/*      */     } 
/*      */     
/* 2099 */     int[] inherited = props;
/* 2100 */     if (props != null) {
/*      */ 
/*      */       
/* 2103 */       int count = 0;
/* 2104 */       for (int i = 0; i < props.length; i++) {
/* 2105 */         ValueManager vm = this.valueManagers[props[i]];
/* 2106 */         if (vm.isInheritedProperty()) { count++; }
/* 2107 */         else { props[i] = -1; }
/*      */       
/*      */       } 
/* 2110 */       if (count == 0) {
/*      */         
/* 2112 */         inherited = null;
/*      */       } else {
/* 2114 */         inherited = new int[count];
/* 2115 */         count = 0;
/* 2116 */         for (int prop : props) {
/* 2117 */           if (prop != -1)
/* 2118 */             inherited[count++] = prop; 
/*      */         } 
/*      */       } 
/*      */     } 
/* 2122 */     Node n = getCSSFirstChild(node);
/* 2123 */     for (; n != null; 
/* 2124 */       n = getCSSNextSibling(n)) {
/* 2125 */       if (n.getNodeType() == 1)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 2130 */         invalidateProperties(n, inherited, null, recascade);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected class StyleDeclarationUpdateHandler
/*      */     extends DocumentAdapter
/*      */     implements ShorthandManager.PropertyHandler
/*      */   {
/*      */     public StyleMap styleMap;
/*      */     
/* 2142 */     public boolean[] updatedProperties = new boolean[CSSEngine.this.getNumberOfProperties()];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void property(String name, LexicalUnit value, boolean important) throws CSSException {
/* 2151 */       int i = CSSEngine.this.getPropertyIndex(name);
/* 2152 */       if (i == -1) {
/* 2153 */         i = CSSEngine.this.getShorthandIndex(name);
/* 2154 */         if (i == -1) {
/*      */           return;
/*      */         }
/*      */         
/* 2158 */         CSSEngine.this.shorthandManagers[i].setValues(CSSEngine.this, this, value, important);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2163 */         if (this.styleMap.isImportant(i)) {
/*      */           return;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2169 */         this.updatedProperties[i] = true;
/*      */         
/* 2171 */         Value v = CSSEngine.this.valueManagers[i].createValue(value, CSSEngine.this);
/* 2172 */         this.styleMap.putMask(i, (short)0);
/* 2173 */         this.styleMap.putValue(i, v);
/* 2174 */         this.styleMap.putOrigin(i, -32768);
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
/*      */   
/*      */   protected void nonCSSPresentationalHintUpdated(CSSStylableElement elt, StyleMap style, String property, short attrChange, String newValue) {
/* 2187 */     int invalid[], idx = getPropertyIndex(property);
/*      */     
/* 2189 */     if (style.isImportant(idx)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2195 */     if (style.getOrigin(idx) >= 24576) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 2200 */     switch (attrChange) {
/*      */       case 1:
/*      */       case 2:
/* 2203 */         this.element = elt;
/*      */         
/*      */         try {
/* 2206 */           LexicalUnit lu = this.parser.parsePropertyValue(newValue);
/* 2207 */           ValueManager vm = this.valueManagers[idx];
/* 2208 */           Value v = vm.createValue(lu, this);
/* 2209 */           style.putMask(idx, (short)0);
/* 2210 */           style.putValue(idx, v);
/* 2211 */           style.putOrigin(idx, (short)16384);
/* 2212 */         } catch (Exception e) {
/* 2213 */           String m = e.getMessage();
/* 2214 */           if (m == null) m = ""; 
/* 2215 */           String u = (this.documentURI == null) ? "<unknown>" : this.documentURI.toString();
/*      */           
/* 2217 */           String s = Messages.formatMessage("property.syntax.error.at", new Object[] { u, property, newValue, m });
/*      */ 
/*      */           
/* 2220 */           DOMException de = new DOMException((short)12, s);
/* 2221 */           if (this.userAgent == null) throw de; 
/* 2222 */           this.userAgent.displayError(de);
/*      */         } finally {
/* 2224 */           this.element = null;
/* 2225 */           this.cssBaseURI = null;
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/* 2231 */         invalid = new int[] { idx };
/* 2232 */         invalidateProperties(elt, invalid, null, true);
/*      */         return;
/*      */     } 
/*      */ 
/*      */     
/* 2237 */     boolean[] updated = this.styleDeclarationUpdateHandler.updatedProperties;
/* 2238 */     for (int i = getNumberOfProperties() - 1; i >= 0; i--) {
/* 2239 */       updated[i] = false;
/*      */     }
/* 2241 */     updated[idx] = true;
/*      */ 
/*      */     
/* 2244 */     boolean fs = (idx == this.fontSizeIndex);
/* 2245 */     boolean lh = (idx == this.lineHeightIndex);
/* 2246 */     boolean cl = (idx == this.colorIndex);
/* 2247 */     int count = 0;
/*      */     
/* 2249 */     for (int j = getNumberOfProperties() - 1; j >= 0; j--) {
/* 2250 */       if (updated[j]) {
/* 2251 */         count++;
/*      */       }
/* 2253 */       else if ((fs && style.isFontSizeRelative(j)) || (lh && style.isLineHeightRelative(j)) || (cl && style.isColorRelative(j))) {
/*      */ 
/*      */         
/* 2256 */         updated[j] = true;
/* 2257 */         clearComputedValue(style, j);
/* 2258 */         count++;
/*      */       } 
/*      */     } 
/*      */     
/* 2262 */     int[] props = new int[count];
/* 2263 */     count = 0;
/* 2264 */     for (int k = getNumberOfProperties() - 1; k >= 0; k--) {
/* 2265 */       if (updated[k]) {
/* 2266 */         props[count++] = k;
/*      */       }
/*      */     } 
/*      */     
/* 2270 */     invalidateProperties(elt, props, null, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasStyleSheetNode(Node n) {
/* 2278 */     if (n instanceof CSSStyleSheetNode) {
/* 2279 */       return true;
/*      */     }
/* 2281 */     n = getCSSFirstChild(n);
/* 2282 */     while (n != null) {
/* 2283 */       if (hasStyleSheetNode(n)) {
/* 2284 */         return true;
/*      */       }
/* 2286 */       n = getCSSNextSibling(n);
/*      */     } 
/* 2288 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleAttrModified(Element e, Attr attr, short attrChange, String prevValue, String newValue) {
/* 2299 */     if (!(e instanceof CSSStylableElement)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 2304 */     if (newValue.equals(prevValue)) {
/*      */       return;
/*      */     }
/*      */     
/* 2308 */     String attrNS = attr.getNamespaceURI();
/* 2309 */     String name = (attrNS == null) ? attr.getNodeName() : attr.getLocalName();
/*      */     
/* 2311 */     CSSStylableElement elt = (CSSStylableElement)e;
/* 2312 */     StyleMap style = elt.getComputedStyleMap((String)null);
/* 2313 */     if (style != null) {
/* 2314 */       if (attrNS == this.styleNamespaceURI || (attrNS != null && attrNS.equals(this.styleNamespaceURI)))
/*      */       {
/* 2316 */         if (name.equals(this.styleLocalName)) {
/*      */           
/* 2318 */           inlineStyleAttributeUpdated(elt, style, attrChange, prevValue, newValue);
/*      */           
/*      */           return;
/*      */         } 
/*      */       }
/*      */       
/* 2324 */       if (this.nonCSSPresentationalHints != null && (
/* 2325 */         attrNS == this.nonCSSPresentationalHintsNamespaceURI || (attrNS != null && attrNS.equals(this.nonCSSPresentationalHintsNamespaceURI))))
/*      */       {
/*      */         
/* 2328 */         if (this.nonCSSPresentationalHints.contains(name)) {
/*      */ 
/*      */           
/* 2331 */           nonCSSPresentationalHintUpdated(elt, style, name, attrChange, newValue);
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 2339 */     if (this.selectorAttributes != null && this.selectorAttributes.contains(name)) {
/*      */ 
/*      */ 
/*      */       
/* 2343 */       invalidateProperties(elt, null, null, true);
/* 2344 */       Node n = getCSSNextSibling(elt);
/* 2345 */       for (; n != null; 
/* 2346 */         n = getCSSNextSibling(n)) {
/* 2347 */         invalidateProperties(n, null, null, true);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleNodeInserted(Node n) {
/* 2356 */     if (hasStyleSheetNode(n)) {
/*      */       
/* 2358 */       this.styleSheetNodes = null;
/* 2359 */       invalidateProperties(this.document.getDocumentElement(), null, null, true);
/*      */     }
/* 2361 */     else if (n instanceof CSSStylableElement) {
/*      */ 
/*      */       
/* 2364 */       n = getCSSNextSibling(n);
/* 2365 */       while (n != null) {
/* 2366 */         invalidateProperties(n, null, null, true);
/* 2367 */         n = getCSSNextSibling(n);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleNodeRemoved(Node n) {
/* 2376 */     if (hasStyleSheetNode(n)) {
/*      */ 
/*      */       
/* 2379 */       this.styleSheetRemoved = true;
/* 2380 */     } else if (n instanceof CSSStylableElement) {
/*      */ 
/*      */       
/* 2383 */       this.removedStylableElementSibling = getCSSNextSibling(n);
/*      */     } 
/*      */     
/* 2386 */     disposeStyleMaps(n);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleSubtreeModified(Node ignored) {
/* 2395 */     if (this.styleSheetRemoved) {
/*      */       
/* 2397 */       this.styleSheetRemoved = false;
/* 2398 */       this.styleSheetNodes = null;
/* 2399 */       invalidateProperties(this.document.getDocumentElement(), null, null, true);
/*      */     }
/* 2401 */     else if (this.removedStylableElementSibling != null) {
/*      */ 
/*      */ 
/*      */       
/* 2405 */       Node n = this.removedStylableElementSibling;
/* 2406 */       while (n != null) {
/* 2407 */         invalidateProperties(n, null, null, true);
/* 2408 */         n = getCSSNextSibling(n);
/*      */       } 
/* 2410 */       this.removedStylableElementSibling = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleCharacterDataModified(Node n) {
/* 2418 */     if (getCSSParentNode(n) instanceof CSSStyleSheetNode) {
/*      */       
/* 2420 */       this.styleSheetNodes = null;
/* 2421 */       invalidateProperties(this.document.getDocumentElement(), null, null, true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class CSSNavigableDocumentHandler
/*      */     implements MainPropertyReceiver, CSSNavigableDocumentListener
/*      */   {
/*      */     protected boolean[] mainPropertiesChanged;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected StyleDeclaration declaration;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void nodeInserted(Node newNode) {
/* 2448 */       CSSEngine.this.handleNodeInserted(newNode);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void nodeToBeRemoved(Node oldNode) {
/* 2455 */       CSSEngine.this.handleNodeRemoved(oldNode);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void subtreeModified(Node rootOfModifications) {
/* 2463 */       CSSEngine.this.handleSubtreeModified(rootOfModifications);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void characterDataModified(Node text) {
/* 2470 */       CSSEngine.this.handleCharacterDataModified(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void attrModified(Element e, Attr attr, short attrChange, String prevValue, String newValue) {
/* 2481 */       CSSEngine.this.handleAttrModified(e, attr, attrChange, prevValue, newValue);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void overrideStyleTextChanged(CSSStylableElement elt, String text) {
/* 2490 */       StyleDeclarationProvider p = elt.getOverrideStyleDeclarationProvider();
/*      */       
/* 2492 */       StyleDeclaration declaration = p.getStyleDeclaration();
/* 2493 */       int ds = declaration.size();
/* 2494 */       boolean[] updated = new boolean[CSSEngine.this.getNumberOfProperties()]; int i;
/* 2495 */       for (i = 0; i < ds; i++) {
/* 2496 */         updated[declaration.getIndex(i)] = true;
/*      */       }
/* 2498 */       declaration = CSSEngine.this.parseStyleDeclaration(elt, text);
/* 2499 */       p.setStyleDeclaration(declaration);
/* 2500 */       ds = declaration.size();
/* 2501 */       for (i = 0; i < ds; i++) {
/* 2502 */         updated[declaration.getIndex(i)] = true;
/*      */       }
/* 2504 */       CSSEngine.this.invalidateProperties(elt, null, updated, true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void overrideStylePropertyRemoved(CSSStylableElement elt, String name) {
/* 2512 */       StyleDeclarationProvider p = elt.getOverrideStyleDeclarationProvider();
/*      */       
/* 2514 */       StyleDeclaration declaration = p.getStyleDeclaration();
/* 2515 */       int idx = CSSEngine.this.getPropertyIndex(name);
/* 2516 */       int ds = declaration.size();
/* 2517 */       for (int i = 0; i < ds; i++) {
/* 2518 */         if (idx == declaration.getIndex(i)) {
/* 2519 */           declaration.remove(i);
/* 2520 */           StyleMap style = elt.getComputedStyleMap((String)null);
/* 2521 */           if (style != null && style.getOrigin(idx) == -24576)
/*      */           {
/*      */             
/* 2524 */             CSSEngine.this.invalidateProperties(elt, new int[] { idx }, null, true);
/*      */           }
/*      */           break;
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
/*      */     public void overrideStylePropertyChanged(CSSStylableElement elt, String name, String val, String prio) {
/* 2538 */       boolean important = (prio != null && prio.length() != 0);
/* 2539 */       StyleDeclarationProvider p = elt.getOverrideStyleDeclarationProvider();
/*      */       
/* 2541 */       this.declaration = p.getStyleDeclaration();
/* 2542 */       CSSEngine.this.setMainProperties(elt, this, name, val, important);
/* 2543 */       this.declaration = null;
/* 2544 */       CSSEngine.this.invalidateProperties(elt, null, this.mainPropertiesChanged, true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setMainProperty(String name, Value v, boolean important) {
/* 2554 */       int idx = CSSEngine.this.getPropertyIndex(name);
/* 2555 */       if (idx == -1) {
/*      */         return;
/*      */       }
/*      */       
/*      */       int i;
/* 2560 */       for (i = 0; i < this.declaration.size() && 
/* 2561 */         idx != this.declaration.getIndex(i); i++);
/*      */ 
/*      */ 
/*      */       
/* 2565 */       if (i < this.declaration.size()) {
/* 2566 */         this.declaration.put(i, v, idx, important);
/*      */       } else {
/* 2568 */         this.declaration.append(v, idx, important);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DOMNodeInsertedListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/* 2579 */       CSSEngine.this.handleNodeInserted((Node)evt.getTarget());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DOMNodeRemovedListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/* 2589 */       CSSEngine.this.handleNodeRemoved((Node)evt.getTarget());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DOMSubtreeModifiedListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/* 2599 */       CSSEngine.this.handleSubtreeModified((Node)evt.getTarget());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class DOMCharacterDataModifiedListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/* 2608 */       CSSEngine.this.handleCharacterDataModified((Node)evt.getTarget());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DOMAttrModifiedListener
/*      */     implements EventListener
/*      */   {
/*      */     public void handleEvent(Event evt) {
/* 2618 */       MutationEvent mevt = (MutationEvent)evt;
/* 2619 */       CSSEngine.this.handleAttrModified((Element)evt.getTarget(), (Attr)mevt.getRelatedNode(), mevt.getAttrChange(), mevt.getPrevValue(), mevt.getNewValue());
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/CSSEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */