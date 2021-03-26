/*      */ package org.apache.xml.serializer;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.io.Writer;
/*      */ import java.util.Properties;
/*      */ import org.apache.xml.res.XMLMessages;
/*      */ import org.apache.xml.utils.Trie;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.SAXException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ToHTMLStream
/*      */   extends ToStream
/*      */ {
/*      */   protected boolean m_inDTD = false;
/*      */   private boolean m_inBlockElem = false;
/*   53 */   protected static final CharInfo m_htmlcharInfo = CharInfo.getCharInfo(CharInfo.HTML_ENTITIES_RESOURCE, "html");
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   58 */   static final Trie m_elementFlags = new Trie();
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*   63 */     m_elementFlags.put("BASEFONT", new ElemDesc(2));
/*   64 */     m_elementFlags.put("FRAME", new ElemDesc(10));
/*      */ 
/*      */     
/*   67 */     m_elementFlags.put("FRAMESET", new ElemDesc(8));
/*   68 */     m_elementFlags.put("NOFRAMES", new ElemDesc(8));
/*   69 */     m_elementFlags.put("ISINDEX", new ElemDesc(10));
/*      */ 
/*      */     
/*   72 */     m_elementFlags.put("APPLET", new ElemDesc(2097152));
/*      */ 
/*      */     
/*   75 */     m_elementFlags.put("CENTER", new ElemDesc(8));
/*   76 */     m_elementFlags.put("DIR", new ElemDesc(8));
/*   77 */     m_elementFlags.put("MENU", new ElemDesc(8));
/*      */ 
/*      */     
/*   80 */     m_elementFlags.put("TT", new ElemDesc(4096));
/*   81 */     m_elementFlags.put("I", new ElemDesc(4096));
/*   82 */     m_elementFlags.put("B", new ElemDesc(4096));
/*   83 */     m_elementFlags.put("BIG", new ElemDesc(4096));
/*   84 */     m_elementFlags.put("SMALL", new ElemDesc(4096));
/*   85 */     m_elementFlags.put("EM", new ElemDesc(8192));
/*   86 */     m_elementFlags.put("STRONG", new ElemDesc(8192));
/*   87 */     m_elementFlags.put("DFN", new ElemDesc(8192));
/*   88 */     m_elementFlags.put("CODE", new ElemDesc(8192));
/*   89 */     m_elementFlags.put("SAMP", new ElemDesc(8192));
/*   90 */     m_elementFlags.put("KBD", new ElemDesc(8192));
/*   91 */     m_elementFlags.put("VAR", new ElemDesc(8192));
/*   92 */     m_elementFlags.put("CITE", new ElemDesc(8192));
/*   93 */     m_elementFlags.put("ABBR", new ElemDesc(8192));
/*   94 */     m_elementFlags.put("ACRONYM", new ElemDesc(8192));
/*   95 */     m_elementFlags.put("SUP", new ElemDesc(98304));
/*      */ 
/*      */     
/*   98 */     m_elementFlags.put("SUB", new ElemDesc(98304));
/*      */ 
/*      */     
/*  101 */     m_elementFlags.put("SPAN", new ElemDesc(98304));
/*      */ 
/*      */     
/*  104 */     m_elementFlags.put("BDO", new ElemDesc(98304));
/*      */ 
/*      */     
/*  107 */     m_elementFlags.put("BR", new ElemDesc(98314));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  115 */     m_elementFlags.put("BODY", new ElemDesc(8));
/*  116 */     m_elementFlags.put("ADDRESS", new ElemDesc(56));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  123 */     m_elementFlags.put("DIV", new ElemDesc(56));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  130 */     m_elementFlags.put("A", new ElemDesc(32768));
/*  131 */     m_elementFlags.put("MAP", new ElemDesc(98312));
/*      */ 
/*      */ 
/*      */     
/*  135 */     m_elementFlags.put("AREA", new ElemDesc(10));
/*      */ 
/*      */     
/*  138 */     m_elementFlags.put("LINK", new ElemDesc(131082));
/*      */ 
/*      */ 
/*      */     
/*  142 */     m_elementFlags.put("IMG", new ElemDesc(2195458));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  150 */     m_elementFlags.put("OBJECT", new ElemDesc(2326528));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  158 */     m_elementFlags.put("PARAM", new ElemDesc(2));
/*  159 */     m_elementFlags.put("HR", new ElemDesc(58));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  167 */     m_elementFlags.put("P", new ElemDesc(56));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  174 */     m_elementFlags.put("H1", new ElemDesc(262152));
/*      */ 
/*      */     
/*  177 */     m_elementFlags.put("H2", new ElemDesc(262152));
/*      */ 
/*      */     
/*  180 */     m_elementFlags.put("H3", new ElemDesc(262152));
/*      */ 
/*      */     
/*  183 */     m_elementFlags.put("H4", new ElemDesc(262152));
/*      */ 
/*      */     
/*  186 */     m_elementFlags.put("H5", new ElemDesc(262152));
/*      */ 
/*      */     
/*  189 */     m_elementFlags.put("H6", new ElemDesc(262152));
/*      */ 
/*      */     
/*  192 */     m_elementFlags.put("PRE", new ElemDesc(1048584));
/*      */ 
/*      */     
/*  195 */     m_elementFlags.put("Q", new ElemDesc(98304));
/*      */ 
/*      */     
/*  198 */     m_elementFlags.put("BLOCKQUOTE", new ElemDesc(56));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  205 */     m_elementFlags.put("INS", new ElemDesc(0));
/*  206 */     m_elementFlags.put("DEL", new ElemDesc(0));
/*  207 */     m_elementFlags.put("DL", new ElemDesc(56));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  214 */     m_elementFlags.put("DT", new ElemDesc(8));
/*  215 */     m_elementFlags.put("DD", new ElemDesc(8));
/*  216 */     m_elementFlags.put("OL", new ElemDesc(524296));
/*      */ 
/*      */     
/*  219 */     m_elementFlags.put("UL", new ElemDesc(524296));
/*      */ 
/*      */     
/*  222 */     m_elementFlags.put("LI", new ElemDesc(8));
/*  223 */     m_elementFlags.put("FORM", new ElemDesc(8));
/*  224 */     m_elementFlags.put("LABEL", new ElemDesc(16384));
/*  225 */     m_elementFlags.put("INPUT", new ElemDesc(18434));
/*      */ 
/*      */ 
/*      */     
/*  229 */     m_elementFlags.put("SELECT", new ElemDesc(18432));
/*      */ 
/*      */     
/*  232 */     m_elementFlags.put("OPTGROUP", new ElemDesc(0));
/*  233 */     m_elementFlags.put("OPTION", new ElemDesc(0));
/*  234 */     m_elementFlags.put("TEXTAREA", new ElemDesc(18432));
/*      */ 
/*      */     
/*  237 */     m_elementFlags.put("FIELDSET", new ElemDesc(24));
/*      */ 
/*      */     
/*  240 */     m_elementFlags.put("LEGEND", new ElemDesc(0));
/*  241 */     m_elementFlags.put("BUTTON", new ElemDesc(18432));
/*      */ 
/*      */     
/*  244 */     m_elementFlags.put("TABLE", new ElemDesc(56));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  251 */     m_elementFlags.put("CAPTION", new ElemDesc(8));
/*  252 */     m_elementFlags.put("THEAD", new ElemDesc(8));
/*  253 */     m_elementFlags.put("TFOOT", new ElemDesc(8));
/*  254 */     m_elementFlags.put("TBODY", new ElemDesc(8));
/*  255 */     m_elementFlags.put("COLGROUP", new ElemDesc(8));
/*  256 */     m_elementFlags.put("COL", new ElemDesc(10));
/*      */ 
/*      */     
/*  259 */     m_elementFlags.put("TR", new ElemDesc(8));
/*  260 */     m_elementFlags.put("TH", new ElemDesc(0));
/*  261 */     m_elementFlags.put("TD", new ElemDesc(0));
/*  262 */     m_elementFlags.put("HEAD", new ElemDesc(4194312));
/*      */ 
/*      */     
/*  265 */     m_elementFlags.put("TITLE", new ElemDesc(8));
/*  266 */     m_elementFlags.put("BASE", new ElemDesc(10));
/*      */ 
/*      */     
/*  269 */     m_elementFlags.put("META", new ElemDesc(131082));
/*      */ 
/*      */ 
/*      */     
/*  273 */     m_elementFlags.put("STYLE", new ElemDesc(131336));
/*      */ 
/*      */ 
/*      */     
/*  277 */     m_elementFlags.put("SCRIPT", new ElemDesc(229632));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  285 */     m_elementFlags.put("NOSCRIPT", new ElemDesc(56));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  292 */     m_elementFlags.put("HTML", new ElemDesc(8));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  297 */     m_elementFlags.put("FONT", new ElemDesc(4096));
/*      */ 
/*      */     
/*  300 */     m_elementFlags.put("S", new ElemDesc(4096));
/*  301 */     m_elementFlags.put("STRIKE", new ElemDesc(4096));
/*      */ 
/*      */     
/*  304 */     m_elementFlags.put("U", new ElemDesc(4096));
/*      */ 
/*      */     
/*  307 */     m_elementFlags.put("NOBR", new ElemDesc(4096));
/*      */ 
/*      */     
/*  310 */     m_elementFlags.put("IFRAME", new ElemDesc(56));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  318 */     m_elementFlags.put("LAYER", new ElemDesc(56));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  325 */     m_elementFlags.put("ILAYER", new ElemDesc(56));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  335 */     ElemDesc elemDesc = (ElemDesc)m_elementFlags.get("AREA");
/*      */     
/*  337 */     elemDesc.setAttr("HREF", 2);
/*  338 */     elemDesc.setAttr("NOHREF", 4);
/*      */     
/*  340 */     elemDesc = (ElemDesc)m_elementFlags.get("BASE");
/*      */     
/*  342 */     elemDesc.setAttr("HREF", 2);
/*      */     
/*  344 */     elemDesc = (ElemDesc)m_elementFlags.get("BLOCKQUOTE");
/*      */     
/*  346 */     elemDesc.setAttr("CITE", 2);
/*      */     
/*  348 */     elemDesc = (ElemDesc)m_elementFlags.get("Q");
/*      */     
/*  350 */     elemDesc.setAttr("CITE", 2);
/*      */     
/*  352 */     elemDesc = (ElemDesc)m_elementFlags.get("INS");
/*      */     
/*  354 */     elemDesc.setAttr("CITE", 2);
/*      */     
/*  356 */     elemDesc = (ElemDesc)m_elementFlags.get("DEL");
/*      */     
/*  358 */     elemDesc.setAttr("CITE", 2);
/*      */     
/*  360 */     elemDesc = (ElemDesc)m_elementFlags.get("A");
/*      */     
/*  362 */     elemDesc.setAttr("HREF", 2);
/*  363 */     elemDesc.setAttr("NAME", 2);
/*      */     
/*  365 */     elemDesc = (ElemDesc)m_elementFlags.get("LINK");
/*  366 */     elemDesc.setAttr("HREF", 2);
/*      */     
/*  368 */     elemDesc = (ElemDesc)m_elementFlags.get("INPUT");
/*      */     
/*  370 */     elemDesc.setAttr("SRC", 2);
/*  371 */     elemDesc.setAttr("USEMAP", 2);
/*  372 */     elemDesc.setAttr("CHECKED", 4);
/*  373 */     elemDesc.setAttr("DISABLED", 4);
/*  374 */     elemDesc.setAttr("ISMAP", 4);
/*  375 */     elemDesc.setAttr("READONLY", 4);
/*      */     
/*  377 */     elemDesc = (ElemDesc)m_elementFlags.get("SELECT");
/*      */     
/*  379 */     elemDesc.setAttr("DISABLED", 4);
/*  380 */     elemDesc.setAttr("MULTIPLE", 4);
/*      */     
/*  382 */     elemDesc = (ElemDesc)m_elementFlags.get("OPTGROUP");
/*      */     
/*  384 */     elemDesc.setAttr("DISABLED", 4);
/*      */     
/*  386 */     elemDesc = (ElemDesc)m_elementFlags.get("OPTION");
/*      */     
/*  388 */     elemDesc.setAttr("SELECTED", 4);
/*  389 */     elemDesc.setAttr("DISABLED", 4);
/*      */     
/*  391 */     elemDesc = (ElemDesc)m_elementFlags.get("TEXTAREA");
/*      */     
/*  393 */     elemDesc.setAttr("DISABLED", 4);
/*  394 */     elemDesc.setAttr("READONLY", 4);
/*      */     
/*  396 */     elemDesc = (ElemDesc)m_elementFlags.get("BUTTON");
/*      */     
/*  398 */     elemDesc.setAttr("DISABLED", 4);
/*      */     
/*  400 */     elemDesc = (ElemDesc)m_elementFlags.get("SCRIPT");
/*      */     
/*  402 */     elemDesc.setAttr("SRC", 2);
/*  403 */     elemDesc.setAttr("FOR", 2);
/*  404 */     elemDesc.setAttr("DEFER", 4);
/*      */     
/*  406 */     elemDesc = (ElemDesc)m_elementFlags.get("IMG");
/*      */     
/*  408 */     elemDesc.setAttr("SRC", 2);
/*  409 */     elemDesc.setAttr("LONGDESC", 2);
/*  410 */     elemDesc.setAttr("USEMAP", 2);
/*  411 */     elemDesc.setAttr("ISMAP", 4);
/*      */     
/*  413 */     elemDesc = (ElemDesc)m_elementFlags.get("OBJECT");
/*      */     
/*  415 */     elemDesc.setAttr("CLASSID", 2);
/*  416 */     elemDesc.setAttr("CODEBASE", 2);
/*  417 */     elemDesc.setAttr("DATA", 2);
/*  418 */     elemDesc.setAttr("ARCHIVE", 2);
/*  419 */     elemDesc.setAttr("USEMAP", 2);
/*  420 */     elemDesc.setAttr("DECLARE", 4);
/*      */     
/*  422 */     elemDesc = (ElemDesc)m_elementFlags.get("FORM");
/*      */     
/*  424 */     elemDesc.setAttr("ACTION", 2);
/*      */     
/*  426 */     elemDesc = (ElemDesc)m_elementFlags.get("HEAD");
/*      */     
/*  428 */     elemDesc.setAttr("PROFILE", 2);
/*      */ 
/*      */     
/*  431 */     elemDesc = (ElemDesc)m_elementFlags.get("FRAME");
/*      */     
/*  433 */     elemDesc.setAttr("SRC", 2);
/*  434 */     elemDesc.setAttr("LONGDESC", 2);
/*      */ 
/*      */     
/*  437 */     elemDesc = (ElemDesc)m_elementFlags.get("IFRAME");
/*      */     
/*  439 */     elemDesc.setAttr("SRC", 2);
/*  440 */     elemDesc.setAttr("LONGDESC", 2);
/*      */ 
/*      */     
/*  443 */     elemDesc = (ElemDesc)m_elementFlags.get("LAYER");
/*      */     
/*  445 */     elemDesc.setAttr("SRC", 2);
/*      */     
/*  447 */     elemDesc = (ElemDesc)m_elementFlags.get("ILAYER");
/*      */     
/*  449 */     elemDesc.setAttr("SRC", 2);
/*      */     
/*  451 */     elemDesc = (ElemDesc)m_elementFlags.get("DIV");
/*      */     
/*  453 */     elemDesc.setAttr("SRC", 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  459 */   private static final ElemDesc m_dummy = new ElemDesc(8);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_specialEscapeURLs = true;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_omitMetaTag = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSpecialEscapeURLs(boolean bool) {
/*  474 */     this.m_specialEscapeURLs = bool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOmitMetaTag(boolean bool) {
/*  484 */     this.m_omitMetaTag = bool;
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
/*      */   public void setOutputFormat(Properties format) {
/*  499 */     this.m_specialEscapeURLs = OutputPropertyUtils.getBooleanProperty("{http://xml.apache.org/xalan}use-url-escaping", format);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  504 */     this.m_omitMetaTag = OutputPropertyUtils.getBooleanProperty("{http://xml.apache.org/xalan}omit-meta-tag", format);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  509 */     super.setOutputFormat(format);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean getSpecialEscapeURLs() {
/*  519 */     return this.m_specialEscapeURLs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean getOmitMetaTag() {
/*  529 */     return this.m_omitMetaTag;
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
/*      */   public static final ElemDesc getElemDesc(String name) {
/*  545 */     Object obj = m_elementFlags.get(name);
/*  546 */     if (null != obj)
/*  547 */       return (ElemDesc)obj; 
/*  548 */     return m_dummy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ToHTMLStream() {
/*  558 */     this.m_charInfo = m_htmlcharInfo;
/*      */     
/*  560 */     this.m_prefixMap = new NamespaceMappings();
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
/*      */   protected void startDocumentInternal() throws SAXException {
/*  577 */     super.startDocumentInternal();
/*      */     
/*  579 */     this.m_needToCallStartDocument = false;
/*  580 */     this.m_needToOutputDocTypeDecl = true;
/*  581 */     this.m_startNewLine = false;
/*  582 */     setOmitXMLDeclaration(true);
/*      */     
/*  584 */     if (true == this.m_needToOutputDocTypeDecl) {
/*      */       
/*  586 */       String doctypeSystem = getDoctypeSystem();
/*  587 */       String doctypePublic = getDoctypePublic();
/*  588 */       if (null != doctypeSystem || null != doctypePublic) {
/*      */         
/*  590 */         Writer writer = this.m_writer;
/*      */ 
/*      */         
/*  593 */         try { writer.write("<!DOCTYPE HTML");
/*      */           
/*  595 */           if (null != doctypePublic) {
/*      */             
/*  597 */             writer.write(" PUBLIC \"");
/*  598 */             writer.write(doctypePublic);
/*  599 */             writer.write(34);
/*      */           } 
/*      */           
/*  602 */           if (null != doctypeSystem) {
/*      */             
/*  604 */             if (null == doctypePublic) {
/*  605 */               writer.write(" SYSTEM \"");
/*      */             } else {
/*  607 */               writer.write(34);
/*      */             } 
/*  609 */             writer.write(doctypeSystem);
/*  610 */             writer.write(34);
/*      */           } 
/*      */           
/*  613 */           writer.write(62);
/*  614 */           outputLineSep(); } catch (IOException e)
/*      */         
/*      */         { 
/*      */           
/*  618 */           throw new SAXException(e); }
/*      */       
/*      */       } 
/*      */     } 
/*      */     
/*  623 */     this.m_needToOutputDocTypeDecl = false;
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
/*      */   public final void endDocument() throws SAXException {
/*  637 */     flushPending();
/*  638 */     if (this.m_doIndent && !this.m_isprevtext) {
/*      */ 
/*      */       
/*      */       try { 
/*  642 */         outputLineSep(); } catch (IOException e)
/*      */       
/*      */       { 
/*      */         
/*  646 */         throw new SAXException(e); }
/*      */     
/*      */     }
/*      */     
/*  650 */     flushWriter();
/*  651 */     if (this.m_tracer != null) {
/*  652 */       fireEndDoc();
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
/*      */   
/*      */   public void startElement(String namespaceURI, String localName, String name, Attributes atts) throws SAXException {
/*  676 */     ElemContext elemContext = this.m_elemContext;
/*      */ 
/*      */     
/*  679 */     if (elemContext.m_startTagOpen) {
/*      */       
/*  681 */       closeStartTag();
/*  682 */       elemContext.m_startTagOpen = false;
/*      */     }
/*  684 */     else if (this.m_cdataTagOpen) {
/*      */       
/*  686 */       closeCDATA();
/*  687 */       this.m_cdataTagOpen = false;
/*      */     }
/*  689 */     else if (this.m_needToCallStartDocument) {
/*      */       
/*  691 */       startDocumentInternal();
/*  692 */       this.m_needToCallStartDocument = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  697 */     if (null != namespaceURI && namespaceURI.length() > 0) {
/*      */       
/*  699 */       super.startElement(namespaceURI, localName, name, atts);
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  706 */     try { ElemDesc elemDesc = getElemDesc(name);
/*  707 */       int elemFlags = elemDesc.getFlags();
/*      */ 
/*      */       
/*  710 */       if (this.m_doIndent) {
/*      */ 
/*      */         
/*  713 */         boolean isBlockElement = ((elemFlags & 0x8) != 0);
/*  714 */         if (this.m_ispreserve) {
/*  715 */           this.m_ispreserve = false;
/*  716 */         } else if (null != elemContext.m_elementName && (!this.m_inBlockElem || isBlockElement)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  722 */           this.m_startNewLine = true;
/*      */           
/*  724 */           indent();
/*      */         } 
/*      */         
/*  727 */         this.m_inBlockElem = !isBlockElement;
/*      */       } 
/*      */ 
/*      */       
/*  731 */       if (atts != null) {
/*  732 */         addAttributes(atts);
/*      */       }
/*  734 */       this.m_isprevtext = false;
/*  735 */       Writer writer = this.m_writer;
/*  736 */       writer.write(60);
/*  737 */       writer.write(name);
/*      */ 
/*      */ 
/*      */       
/*  741 */       if (this.m_tracer != null) {
/*  742 */         firePseudoAttributes();
/*      */       }
/*  744 */       if ((elemFlags & 0x2) != 0) {
/*      */ 
/*      */ 
/*      */         
/*  748 */         this.m_elemContext = elemContext.push();
/*      */ 
/*      */ 
/*      */         
/*  752 */         this.m_elemContext.m_elementName = name;
/*  753 */         this.m_elemContext.m_elementDesc = elemDesc;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  758 */       elemContext = elemContext.push(namespaceURI, localName, name);
/*  759 */       this.m_elemContext = elemContext;
/*  760 */       elemContext.m_elementDesc = elemDesc;
/*  761 */       elemContext.m_isRaw = ((elemFlags & 0x100) != 0);
/*      */ 
/*      */ 
/*      */       
/*  765 */       if ((elemFlags & 0x400000) != 0)
/*      */       
/*      */       { 
/*  768 */         closeStartTag();
/*  769 */         elemContext.m_startTagOpen = false;
/*  770 */         if (!this.m_omitMetaTag)
/*      */         
/*  772 */         { if (this.m_doIndent)
/*  773 */             indent(); 
/*  774 */           writer.write("<META http-equiv=\"Content-Type\" content=\"text/html; charset=");
/*      */           
/*  776 */           String encoding = getEncoding();
/*  777 */           String encode = Encodings.getMimeEncoding(encoding);
/*  778 */           writer.write(encode);
/*  779 */           writer.write("\">"); }  }  } catch (IOException e)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */       
/*  785 */       throw new SAXException(e); }
/*      */   
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
/*      */   public final void endElement(String namespaceURI, String localName, String name) throws SAXException {
/*  806 */     if (this.m_cdataTagOpen) {
/*  807 */       closeCDATA();
/*      */     }
/*      */     
/*  810 */     if (null != namespaceURI && namespaceURI.length() > 0) {
/*      */       
/*  812 */       super.endElement(namespaceURI, localName, name);
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  820 */     try { ElemContext elemContext = this.m_elemContext;
/*  821 */       ElemDesc elemDesc = elemContext.m_elementDesc;
/*  822 */       int elemFlags = elemDesc.getFlags();
/*  823 */       boolean elemEmpty = ((elemFlags & 0x2) != 0);
/*      */ 
/*      */       
/*  826 */       if (this.m_doIndent) {
/*      */         
/*  828 */         boolean isBlockElement = ((elemFlags & 0x8) != 0);
/*  829 */         boolean shouldIndent = false;
/*      */         
/*  831 */         if (this.m_ispreserve) {
/*      */           
/*  833 */           this.m_ispreserve = false;
/*      */         }
/*  835 */         else if (this.m_doIndent && (!this.m_inBlockElem || isBlockElement)) {
/*      */           
/*  837 */           this.m_startNewLine = true;
/*  838 */           shouldIndent = true;
/*      */         } 
/*  840 */         if (!elemContext.m_startTagOpen && shouldIndent)
/*  841 */           indent(elemContext.m_currentElemDepth - 1); 
/*  842 */         this.m_inBlockElem = !isBlockElement;
/*      */       } 
/*      */       
/*  845 */       Writer writer = this.m_writer;
/*  846 */       if (!elemContext.m_startTagOpen) {
/*      */         
/*  848 */         writer.write("</");
/*  849 */         writer.write(name);
/*  850 */         writer.write(62);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  857 */         if (this.m_tracer != null) {
/*  858 */           fireStartElem(name);
/*      */         }
/*      */ 
/*      */         
/*  862 */         int nAttrs = this.m_attributes.getLength();
/*  863 */         if (nAttrs > 0) {
/*      */           
/*  865 */           processAttributes(this.m_writer, nAttrs);
/*      */           
/*  867 */           this.m_attributes.clear();
/*      */         } 
/*  869 */         if (!elemEmpty) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  876 */           writer.write("></");
/*  877 */           writer.write(name);
/*  878 */           writer.write(62);
/*      */         }
/*      */         else {
/*      */           
/*  882 */           writer.write(62);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  887 */       if ((elemFlags & 0x200000) != 0)
/*  888 */         this.m_ispreserve = true; 
/*  889 */       this.m_isprevtext = false;
/*      */ 
/*      */       
/*  892 */       if (this.m_tracer != null) {
/*  893 */         fireEndElem(name);
/*      */       }
/*      */       
/*  896 */       if (elemEmpty) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  901 */         this.m_elemContext = elemContext.m_prev;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  906 */       if (!elemContext.m_startTagOpen)
/*      */       {
/*  908 */         if (this.m_doIndent && !this.m_preserves.isEmpty())
/*  909 */           this.m_preserves.pop(); 
/*      */       }
/*  911 */       this.m_elemContext = elemContext.m_prev; } catch (IOException e)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  916 */       throw new SAXException(e); }
/*      */   
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
/*      */   protected void processAttribute(Writer writer, String name, String value, ElemDesc elemDesc) throws IOException {
/*  937 */     writer.write(32);
/*      */     
/*  939 */     if ((value.length() == 0 || value.equalsIgnoreCase(name)) && elemDesc != null && elemDesc.isAttrFlagSet(name, 4)) {
/*      */ 
/*      */ 
/*      */       
/*  943 */       writer.write(name);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  950 */       writer.write(name);
/*  951 */       writer.write("=\"");
/*  952 */       if (elemDesc != null && elemDesc.isAttrFlagSet(name, 2)) {
/*      */         
/*  954 */         writeAttrURI(writer, value, this.m_specialEscapeURLs);
/*      */       } else {
/*  956 */         writeAttrString(writer, value, getEncoding());
/*  957 */       }  writer.write(34);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isASCIIDigit(char c) {
/*  967 */     return (c >= '0' && c <= '9');
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
/*      */   private static String makeHHString(int i) {
/*  981 */     String s = Integer.toHexString(i).toUpperCase();
/*  982 */     if (s.length() == 1)
/*      */     {
/*  984 */       s = "0" + s;
/*      */     }
/*  986 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isHHSign(String str) {
/*  997 */     boolean sign = true;
/*      */ 
/*      */     
/* 1000 */     try { char c = (char)Integer.parseInt(str, 16); } catch (NumberFormatException e)
/*      */     
/*      */     { 
/*      */       
/* 1004 */       sign = false; }
/*      */     
/* 1006 */     return sign;
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
/*      */   public void writeAttrURI(Writer writer, String string, boolean doURLEscaping) throws IOException {
/* 1038 */     int end = string.length();
/* 1039 */     if (end > this.m_attrBuff.length)
/*      */     {
/* 1041 */       this.m_attrBuff = new char[end * 2 + 1];
/*      */     }
/* 1043 */     string.getChars(0, end, this.m_attrBuff, 0);
/* 1044 */     char[] chars = this.m_attrBuff;
/*      */     
/* 1046 */     int cleanStart = 0;
/* 1047 */     int cleanLength = 0;
/*      */ 
/*      */     
/* 1050 */     char ch = Character.MIN_VALUE;
/* 1051 */     for (int i = 0; i < end; i++) {
/*      */       
/* 1053 */       ch = chars[i];
/*      */       
/* 1055 */       if (ch < ' ' || ch > '~') {
/*      */         
/* 1057 */         if (cleanLength > 0) {
/*      */           
/* 1059 */           writer.write(chars, cleanStart, cleanLength);
/* 1060 */           cleanLength = 0;
/*      */         } 
/* 1062 */         if (doURLEscaping) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1074 */           if (ch <= '')
/*      */           {
/* 1076 */             writer.write(37);
/* 1077 */             writer.write(makeHHString(ch));
/*      */           }
/* 1079 */           else if (ch <= '߿')
/*      */           {
/*      */ 
/*      */             
/* 1083 */             int high = ch >> 6 | 0xC0;
/* 1084 */             int low = ch & 0x3F | 0x80;
/*      */             
/* 1086 */             writer.write(37);
/* 1087 */             writer.write(makeHHString(high));
/* 1088 */             writer.write(37);
/* 1089 */             writer.write(makeHHString(low));
/*      */           }
/* 1091 */           else if (ToStream.isUTF16Surrogate(ch))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1101 */             int highSurrogate = ch & 0x3FF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1107 */             int wwww = (highSurrogate & 0x3C0) >> 6;
/* 1108 */             int uuuuu = wwww + 1;
/*      */ 
/*      */             
/* 1111 */             int zzzz = (highSurrogate & 0x3C) >> 2;
/*      */ 
/*      */             
/* 1114 */             int yyyyyy = (highSurrogate & 0x3) << 4 & 0x30;
/*      */ 
/*      */             
/* 1117 */             ch = chars[++i];
/*      */ 
/*      */             
/* 1120 */             int lowSurrogate = ch & 0x3FF;
/*      */ 
/*      */             
/* 1123 */             yyyyyy |= (lowSurrogate & 0x3C0) >> 6;
/*      */ 
/*      */             
/* 1126 */             int xxxxxx = lowSurrogate & 0x3F;
/*      */             
/* 1128 */             int byte1 = 0xF0 | uuuuu >> 2;
/* 1129 */             int byte2 = 0x80 | (uuuuu & 0x3) << 4 & 0x30 | zzzz;
/*      */             
/* 1131 */             int byte3 = 0x80 | yyyyyy;
/* 1132 */             int byte4 = 0x80 | xxxxxx;
/*      */             
/* 1134 */             writer.write(37);
/* 1135 */             writer.write(makeHHString(byte1));
/* 1136 */             writer.write(37);
/* 1137 */             writer.write(makeHHString(byte2));
/* 1138 */             writer.write(37);
/* 1139 */             writer.write(makeHHString(byte3));
/* 1140 */             writer.write(37);
/* 1141 */             writer.write(makeHHString(byte4));
/*      */           }
/*      */           else
/*      */           {
/* 1145 */             int high = ch >> 12 | 0xE0;
/* 1146 */             int middle = (ch & 0xFC0) >> 6 | 0x80;
/*      */             
/* 1148 */             int low = ch & 0x3F | 0x80;
/*      */             
/* 1150 */             writer.write(37);
/* 1151 */             writer.write(makeHHString(high));
/* 1152 */             writer.write(37);
/* 1153 */             writer.write(makeHHString(middle));
/* 1154 */             writer.write(37);
/* 1155 */             writer.write(makeHHString(low));
/*      */           }
/*      */         
/*      */         }
/* 1159 */         else if (escapingNotNeeded(ch)) {
/*      */           
/* 1161 */           writer.write(ch);
/*      */         }
/*      */         else {
/*      */           
/* 1165 */           writer.write("&#");
/* 1166 */           writer.write(Integer.toString(ch));
/* 1167 */           writer.write(59);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1173 */         cleanStart = i + 1;
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1178 */       else if (ch == '"') {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1190 */         if (cleanLength > 0) {
/*      */           
/* 1192 */           writer.write(chars, cleanStart, cleanLength);
/* 1193 */           cleanLength = 0;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1198 */         if (doURLEscaping) {
/* 1199 */           writer.write("%22");
/*      */         } else {
/* 1201 */           writer.write("&quot;");
/*      */         } 
/*      */ 
/*      */         
/* 1205 */         cleanStart = i + 1;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1211 */         cleanLength++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1217 */     if (cleanLength > 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1222 */       if (cleanStart == 0) {
/* 1223 */         writer.write(string);
/*      */       } else {
/* 1225 */         writer.write(chars, cleanStart, cleanLength);
/*      */       } 
/* 1227 */     } else if (cleanLength == 1) {
/*      */ 
/*      */ 
/*      */       
/* 1231 */       writer.write(ch);
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
/*      */   public void writeAttrString(Writer writer, String string, String encoding) throws IOException {
/* 1248 */     int end = string.length();
/* 1249 */     if (end > this.m_attrBuff.length)
/*      */     {
/* 1251 */       this.m_attrBuff = new char[end * 2 + 1];
/*      */     }
/* 1253 */     string.getChars(0, end, this.m_attrBuff, 0);
/* 1254 */     char[] chars = this.m_attrBuff;
/*      */ 
/*      */ 
/*      */     
/* 1258 */     int cleanStart = 0;
/* 1259 */     int cleanLength = 0;
/*      */     
/* 1261 */     char ch = Character.MIN_VALUE;
/* 1262 */     for (int i = 0; i < end; i++) {
/*      */       
/* 1264 */       ch = chars[i];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1270 */       if (escapingNotNeeded(ch) && !this.m_charInfo.isSpecialAttrChar(ch)) {
/*      */         
/* 1272 */         cleanLength++;
/*      */       }
/* 1274 */       else if ('<' == ch || '>' == ch) {
/*      */         
/* 1276 */         cleanLength++;
/*      */       }
/* 1278 */       else if ('&' == ch && i + 1 < end && '{' == chars[i + 1]) {
/*      */ 
/*      */         
/* 1281 */         cleanLength++;
/*      */       }
/*      */       else {
/*      */         
/* 1285 */         if (cleanLength > 0) {
/*      */           
/* 1287 */           writer.write(chars, cleanStart, cleanLength);
/* 1288 */           cleanLength = 0;
/*      */         } 
/* 1290 */         int pos = accumDefaultEntity(writer, ch, i, chars, end, false, false);
/*      */         
/* 1292 */         if (i != pos) {
/*      */           
/* 1294 */           i = pos - 1;
/*      */         }
/*      */         else {
/*      */           
/* 1298 */           if (ToStream.isUTF16Surrogate(ch)) {
/*      */ 
/*      */             
/* 1301 */             writeUTF16Surrogate(ch, chars, i, end);
/* 1302 */             i++;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1318 */           String entityName = this.m_charInfo.getEntityNameForChar(ch);
/* 1319 */           if (null != entityName) {
/*      */             
/* 1321 */             writer.write(38);
/* 1322 */             writer.write(entityName);
/* 1323 */             writer.write(59);
/*      */           }
/* 1325 */           else if (escapingNotNeeded(ch)) {
/*      */             
/* 1327 */             writer.write(ch);
/*      */           }
/*      */           else {
/*      */             
/* 1331 */             writer.write("&#");
/* 1332 */             writer.write(Integer.toString(ch));
/* 1333 */             writer.write(59);
/*      */           } 
/*      */         } 
/* 1336 */         cleanStart = i + 1;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1342 */     if (cleanLength > 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1347 */       if (cleanStart == 0) {
/* 1348 */         writer.write(string);
/*      */       } else {
/* 1350 */         writer.write(chars, cleanStart, cleanLength);
/*      */       } 
/* 1352 */     } else if (cleanLength == 1) {
/*      */ 
/*      */ 
/*      */       
/* 1356 */       writer.write(ch);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void characters(char[] chars, int start, int length) throws SAXException {
/* 1393 */     if (this.m_elemContext.m_isRaw) {
/*      */       
/*      */       try {
/*      */         
/* 1397 */         if (this.m_elemContext.m_startTagOpen) {
/*      */           
/* 1399 */           closeStartTag();
/* 1400 */           this.m_elemContext.m_startTagOpen = false;
/*      */         } 
/* 1402 */         this.m_ispreserve = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1411 */         writeNormalizedChars(chars, start, length, false, this.m_lineSepUse);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1416 */         if (this.m_tracer != null)
/* 1417 */           fireCharEvent(chars, start, length); 
/*      */         return;
/* 1419 */       } catch (IOException ioe) {
/*      */ 
/*      */ 
/*      */         
/* 1423 */         throw new SAXException(XMLMessages.createXMLMessage("ER_OIERROR", null), ioe);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1433 */     super.characters(chars, start, length);
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
/*      */   public final void cdata(char[] ch, int start, int length) throws SAXException {
/* 1468 */     if (null != this.m_elemContext.m_elementName && (this.m_elemContext.m_elementName.equalsIgnoreCase("SCRIPT") || this.m_elemContext.m_elementName.equalsIgnoreCase("STYLE"))) {
/*      */ 
/*      */ 
/*      */       
/*      */       try { 
/*      */         
/* 1474 */         if (this.m_elemContext.m_startTagOpen) {
/*      */           
/* 1476 */           closeStartTag();
/* 1477 */           this.m_elemContext.m_startTagOpen = false;
/*      */         } 
/*      */         
/* 1480 */         this.m_ispreserve = true;
/*      */         
/* 1482 */         if (shouldIndent()) {
/* 1483 */           indent();
/*      */         }
/*      */         
/* 1486 */         writeNormalizedChars(ch, start, length, true, this.m_lineSepUse); } catch (IOException ioe)
/*      */       
/*      */       { 
/*      */         
/* 1490 */         throw new SAXException(XMLMessages.createXMLMessage("ER_OIERROR", null), ioe);
/*      */         
/*      */          }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1500 */       super.cdata(ch, start, length);
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
/*      */   public void processingInstruction(String target, String data) throws SAXException {
/* 1520 */     flushPending();
/*      */ 
/*      */ 
/*      */     
/* 1524 */     if (target.equals("javax.xml.transform.disable-output-escaping")) {
/*      */       
/* 1526 */       startNonEscaping();
/*      */     }
/* 1528 */     else if (target.equals("javax.xml.transform.enable-output-escaping")) {
/*      */       
/* 1530 */       endNonEscaping();
/*      */     } else {
/*      */ 
/*      */ 
/*      */       
/*      */       try { 
/* 1536 */         if (this.m_elemContext.m_startTagOpen) {
/*      */           
/* 1538 */           closeStartTag();
/* 1539 */           this.m_elemContext.m_startTagOpen = false;
/*      */         }
/* 1541 */         else if (this.m_needToCallStartDocument) {
/* 1542 */           startDocumentInternal();
/*      */         } 
/* 1544 */         if (shouldIndent()) {
/* 1545 */           indent();
/*      */         }
/* 1547 */         Writer writer = this.m_writer;
/*      */         
/* 1549 */         writer.write("<?");
/* 1550 */         writer.write(target);
/*      */         
/* 1552 */         if (data.length() > 0 && !Character.isSpaceChar(data.charAt(0))) {
/* 1553 */           writer.write(32);
/*      */         }
/*      */         
/* 1556 */         writer.write(data);
/* 1557 */         writer.write(62);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1562 */         if (this.m_elemContext.m_currentElemDepth <= 0) {
/* 1563 */           outputLineSep();
/*      */         }
/* 1565 */         this.m_startNewLine = true; } catch (IOException e)
/*      */       
/*      */       { 
/*      */         
/* 1569 */         throw new SAXException(e); }
/*      */     
/*      */     } 
/*      */ 
/*      */     
/* 1574 */     if (this.m_tracer != null) {
/* 1575 */       fireEscapingEvent(target, data);
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
/*      */   public final void entityReference(String name) throws SAXException {
/*      */     
/* 1591 */     try { Writer writer = this.m_writer;
/* 1592 */       writer.write(38);
/* 1593 */       writer.write(name);
/* 1594 */       writer.write(59); } catch (IOException e)
/*      */     
/*      */     { 
/*      */       
/* 1598 */       throw new SAXException(e); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void endElement(String elemName) throws SAXException {
/* 1606 */     endElement(null, null, elemName);
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
/*      */   public void processAttributes(Writer writer, int nAttrs) throws IOException, SAXException {
/* 1626 */     for (int i = 0; i < nAttrs; i++)
/*      */     {
/* 1628 */       processAttribute(writer, this.m_attributes.getQName(i), this.m_attributes.getValue(i), this.m_elemContext.m_elementDesc);
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
/*      */   protected void closeStartTag() throws SAXException {
/*      */     
/* 1648 */     try { if (this.m_tracer != null) {
/* 1649 */         fireStartElem(this.m_elemContext.m_elementName);
/*      */       }
/* 1651 */       int nAttrs = this.m_attributes.getLength();
/* 1652 */       if (nAttrs > 0) {
/*      */         
/* 1654 */         processAttributes(this.m_writer, nAttrs);
/*      */         
/* 1656 */         this.m_attributes.clear();
/*      */       } 
/*      */       
/* 1659 */       this.m_writer.write(62);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1665 */       if (this.m_cdataSectionElements != null)
/* 1666 */         this.m_elemContext.m_isCdataSection = isCdataSection(); 
/* 1667 */       if (this.m_doIndent)
/*      */       
/* 1669 */       { this.m_isprevtext = false;
/* 1670 */         this.m_preserves.push(this.m_ispreserve); }  } catch (IOException e)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */       
/* 1676 */       throw new SAXException(e); }
/*      */   
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
/*      */   protected synchronized void init(OutputStream output, Properties format) throws UnsupportedEncodingException {
/* 1691 */     if (null == format)
/*      */     {
/* 1693 */       format = OutputPropertiesFactory.getDefaultMethodProperties("html");
/*      */     }
/* 1695 */     init(output, format, false);
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
/*      */   public void setOutputStream(OutputStream output) {
/*      */     
/*      */     try { Properties properties;
/* 1715 */       if (null == this.m_format) {
/* 1716 */         properties = OutputPropertiesFactory.getDefaultMethodProperties("html");
/*      */       } else {
/* 1718 */         properties = this.m_format;
/* 1719 */       }  init(output, properties, true); } catch (UnsupportedEncodingException unsupportedEncodingException) {}
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
/*      */   public void namespaceAfterStartElement(String prefix, String uri) throws SAXException {
/* 1742 */     if (this.m_elemContext.m_elementURI == null) {
/*      */       
/* 1744 */       String prefix1 = SerializerBase.getPrefixPart(this.m_elemContext.m_elementName);
/* 1745 */       if (prefix1 == null && "".equals(prefix))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1751 */         this.m_elemContext.m_elementURI = uri;
/*      */       }
/*      */     } 
/* 1754 */     startPrefixMapping(prefix, uri, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/* 1760 */     this.m_inDTD = true;
/* 1761 */     super.startDTD(name, publicId, systemId);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDTD() throws SAXException {
/* 1771 */     this.m_inDTD = false;
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
/*      */   public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void elementDecl(String name, String model) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void internalEntityDecl(String name, String value) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void externalEntityDecl(String name, String publicId, String systemId) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addUniqueAttribute(String name, String value, int flags) throws SAXException {
/*      */     
/* 1832 */     try { Writer writer = this.m_writer;
/* 1833 */       if ((flags & 0x1) > 0 && m_htmlcharInfo.onlyQuotAmpLtGt)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1840 */         writer.write(32);
/* 1841 */         writer.write(name);
/* 1842 */         writer.write("=\"");
/* 1843 */         writer.write(value);
/* 1844 */         writer.write(34); }
/*      */       
/* 1846 */       else if ((flags & 0x2) > 0 && (value.length() == 0 || value.equalsIgnoreCase(name)))
/*      */       
/*      */       { 
/*      */         
/* 1850 */         writer.write(32);
/* 1851 */         writer.write(name); }
/*      */       
/*      */       else
/*      */       
/* 1855 */       { writer.write(32);
/* 1856 */         writer.write(name);
/* 1857 */         writer.write("=\"");
/* 1858 */         if ((flags & 0x4) > 0) {
/*      */           
/* 1860 */           writeAttrURI(writer, value, this.m_specialEscapeURLs);
/*      */         }
/*      */         else {
/*      */           
/* 1864 */           writeAttrString(writer, value, getEncoding());
/*      */         } 
/* 1866 */         writer.write(34); }  } catch (IOException e)
/*      */     
/*      */     { 
/* 1869 */       throw new SAXException(e); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void comment(char[] ch, int start, int length) throws SAXException {
/* 1877 */     if (this.m_inDTD)
/*      */       return; 
/* 1879 */     super.comment(ch, start, length);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean reset() {
/* 1884 */     boolean ret = super.reset();
/* 1885 */     if (!ret)
/* 1886 */       return false; 
/* 1887 */     initToHTMLStream();
/* 1888 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void initToHTMLStream() {
/* 1894 */     this.m_inBlockElem = false;
/* 1895 */     this.m_inDTD = false;
/*      */     
/* 1897 */     this.m_omitMetaTag = false;
/* 1898 */     this.m_specialEscapeURLs = true;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/ToHTMLStream.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */