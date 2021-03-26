/*      */ package org.apache.xalan.templates;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xalan.res.XSLMessages;
/*      */ import org.apache.xalan.transformer.TransformerImpl;
/*      */ import org.apache.xml.serializer.SerializationHandler;
/*      */ import org.apache.xml.utils.PrefixResolver;
/*      */ import org.apache.xml.utils.UnImplNode;
/*      */ import org.apache.xpath.ExpressionNode;
/*      */ import org.apache.xpath.WhitespaceStrippingElementMatcher;
/*      */ import org.apache.xpath.XPathContext;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.helpers.NamespaceSupport;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ElemTemplateElement
/*      */   extends UnImplNode
/*      */   implements Serializable, XSLTVisitable, PrefixResolver, ExpressionNode, WhitespaceStrippingElementMatcher
/*      */ {
/*      */   private int m_lineNumber;
/*      */   private int m_endLineNumber;
/*      */   private int m_columnNumber;
/*      */   private int m_endColumnNumber;
/*      */   
/*      */   public boolean isCompiledTemplate() {
/*   81 */     return false;
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
/*      */   public int getXSLToken() {
/*   93 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeName() {
/*  103 */     return "Unknown XSLT Element";
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
/*      */   public String getLocalName() {
/*  115 */     return getNodeName();
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
/*      */   public void runtimeInit(TransformerImpl transformer) throws TransformerException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void execute(TransformerImpl transformer) throws TransformerException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StylesheetComposed getStylesheetComposed() {
/*  153 */     return this.m_parentNode.getStylesheetComposed();
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
/*      */   public Stylesheet getStylesheet() {
/*  165 */     return (null == this.m_parentNode) ? null : this.m_parentNode.getStylesheet();
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
/*      */   public StylesheetRoot getStylesheetRoot() {
/*  178 */     return this.m_parentNode.getStylesheetRoot();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void recompose(StylesheetRoot root) throws TransformerException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void compose(StylesheetRoot sroot) throws TransformerException {
/*  197 */     resolvePrefixTables();
/*  198 */     ElemTemplateElement t = getFirstChildElem();
/*  199 */     this.m_hasTextLitOnly = (t != null && t.getXSLToken() == 78 && t.getNextSiblingElem() == null);
/*      */ 
/*      */ 
/*      */     
/*  203 */     StylesheetRoot.ComposeState cstate = sroot.getComposeState();
/*  204 */     cstate.pushStackMark();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endCompose(StylesheetRoot sroot) throws TransformerException {
/*  212 */     StylesheetRoot.ComposeState cstate = sroot.getComposeState();
/*  213 */     cstate.popStackMark();
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
/*      */   public void error(String msg, Object[] args) {
/*  225 */     String themsg = XSLMessages.createMessage(msg, args);
/*      */     
/*  227 */     throw new RuntimeException(XSLMessages.createMessage("ER_ELEMTEMPLATEELEM_ERR", new Object[] { themsg }));
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
/*      */   public void error(String msg) {
/*  240 */     error(msg, null);
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
/*      */   public Node appendChild(Node newChild) throws DOMException {
/*  261 */     if (null == newChild)
/*      */     {
/*  263 */       error("ER_NULL_CHILD", null);
/*      */     }
/*      */     
/*  266 */     ElemTemplateElement elem = (ElemTemplateElement)newChild;
/*      */     
/*  268 */     if (null == this.m_firstChild) {
/*      */       
/*  270 */       this.m_firstChild = elem;
/*      */     }
/*      */     else {
/*      */       
/*  274 */       ElemTemplateElement last = (ElemTemplateElement)getLastChild();
/*      */       
/*  276 */       last.m_nextSibling = elem;
/*      */     } 
/*      */     
/*  279 */     elem.m_parentNode = this;
/*      */     
/*  281 */     return newChild;
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
/*      */   public ElemTemplateElement appendChild(ElemTemplateElement elem) {
/*  299 */     if (null == elem)
/*      */     {
/*  301 */       error("ER_NULL_CHILD", null);
/*      */     }
/*      */     
/*  304 */     if (null == this.m_firstChild) {
/*      */       
/*  306 */       this.m_firstChild = elem;
/*      */     }
/*      */     else {
/*      */       
/*  310 */       ElemTemplateElement last = getLastChildElem();
/*      */       
/*  312 */       last.m_nextSibling = elem;
/*      */     } 
/*      */     
/*  315 */     elem.setParentElem(this);
/*      */     
/*  317 */     return elem;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasChildNodes() {
/*  328 */     return (null != this.m_firstChild);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getNodeType() {
/*  338 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeList getChildNodes() {
/*  348 */     return (NodeList)this;
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
/*      */   public ElemTemplateElement removeChild(ElemTemplateElement childETE) {
/*  368 */     if (childETE == null || childETE.m_parentNode != this) {
/*  369 */       return null;
/*      */     }
/*      */     
/*  372 */     if (childETE == this.m_firstChild) {
/*  373 */       this.m_firstChild = childETE.m_nextSibling;
/*      */     } else {
/*      */       
/*  376 */       ElemTemplateElement prev = childETE.getPreviousSiblingElem();
/*      */       
/*  378 */       prev.m_nextSibling = childETE.m_nextSibling;
/*      */     } 
/*      */ 
/*      */     
/*  382 */     childETE.m_parentNode = null;
/*  383 */     childETE.m_nextSibling = null;
/*      */     
/*  385 */     return childETE;
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
/*      */   public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
/*  401 */     if (oldChild == null || oldChild.getParentNode() != this) {
/*  402 */       return null;
/*      */     }
/*  404 */     ElemTemplateElement newChildElem = (ElemTemplateElement)newChild;
/*  405 */     ElemTemplateElement oldChildElem = (ElemTemplateElement)oldChild;
/*      */ 
/*      */     
/*  408 */     ElemTemplateElement prev = (ElemTemplateElement)oldChildElem.getPreviousSibling();
/*      */ 
/*      */     
/*  411 */     if (null != prev) {
/*  412 */       prev.m_nextSibling = newChildElem;
/*      */     }
/*      */     
/*  415 */     if (this.m_firstChild == oldChildElem) {
/*  416 */       this.m_firstChild = newChildElem;
/*      */     }
/*  418 */     newChildElem.m_parentNode = this;
/*  419 */     oldChildElem.m_parentNode = null;
/*  420 */     newChildElem.m_nextSibling = oldChildElem.m_nextSibling;
/*  421 */     oldChildElem.m_nextSibling = null;
/*      */ 
/*      */ 
/*      */     
/*  425 */     return (Node)newChildElem;
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
/*      */   public Node insertBefore(Node newChild, Node refChild) throws DOMException {
/*  440 */     if (null == refChild) {
/*      */       
/*  442 */       appendChild(newChild);
/*  443 */       return newChild;
/*      */     } 
/*      */     
/*  446 */     if (newChild == refChild)
/*      */     {
/*      */       
/*  449 */       return newChild;
/*      */     }
/*      */     
/*  452 */     ElemTemplateElement elemTemplateElement = this.m_firstChild;
/*  453 */     Node prev = null;
/*  454 */     boolean foundit = false;
/*      */     
/*  456 */     while (null != elemTemplateElement) {
/*      */ 
/*      */       
/*  459 */       if (newChild == elemTemplateElement) {
/*      */         
/*  461 */         if (null != prev) {
/*  462 */           ((ElemTemplateElement)prev).m_nextSibling = (ElemTemplateElement)elemTemplateElement.getNextSibling();
/*      */         } else {
/*      */           
/*  465 */           this.m_firstChild = (ElemTemplateElement)elemTemplateElement.getNextSibling();
/*  466 */         }  node = elemTemplateElement.getNextSibling();
/*      */         continue;
/*      */       } 
/*  469 */       if (refChild == node) {
/*      */         
/*  471 */         if (null != prev) {
/*      */           
/*  473 */           ((ElemTemplateElement)prev).m_nextSibling = (ElemTemplateElement)newChild;
/*      */         }
/*      */         else {
/*      */           
/*  477 */           this.m_firstChild = (ElemTemplateElement)newChild;
/*      */         } 
/*  479 */         ((ElemTemplateElement)newChild).m_nextSibling = (ElemTemplateElement)refChild;
/*  480 */         ((ElemTemplateElement)newChild).setParentElem(this);
/*  481 */         prev = newChild;
/*  482 */         node = node.getNextSibling();
/*  483 */         foundit = true;
/*      */         continue;
/*      */       } 
/*  486 */       prev = node;
/*  487 */       Node node = node.getNextSibling();
/*      */     } 
/*      */     
/*  490 */     if (!foundit) {
/*  491 */       throw new DOMException((short)8, "refChild was not found in insertBefore method!");
/*      */     }
/*      */     
/*  494 */     return newChild;
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
/*      */   public ElemTemplateElement replaceChild(ElemTemplateElement newChildElem, ElemTemplateElement oldChildElem) {
/*  512 */     if (oldChildElem == null || oldChildElem.getParentElem() != this) {
/*  513 */       return null;
/*      */     }
/*      */     
/*  516 */     ElemTemplateElement prev = oldChildElem.getPreviousSiblingElem();
/*      */ 
/*      */     
/*  519 */     if (null != prev) {
/*  520 */       prev.m_nextSibling = newChildElem;
/*      */     }
/*      */     
/*  523 */     if (this.m_firstChild == oldChildElem) {
/*  524 */       this.m_firstChild = newChildElem;
/*      */     }
/*  526 */     newChildElem.m_parentNode = this;
/*  527 */     oldChildElem.m_parentNode = null;
/*  528 */     newChildElem.m_nextSibling = oldChildElem.m_nextSibling;
/*  529 */     oldChildElem.m_nextSibling = null;
/*      */ 
/*      */ 
/*      */     
/*  533 */     return newChildElem;
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
/*      */   public int getLength() {
/*  547 */     int count = 0;
/*      */     
/*  549 */     for (ElemTemplateElement node = this.m_firstChild; node != null; 
/*  550 */       node = node.m_nextSibling)
/*      */     {
/*  552 */       count++;
/*      */     }
/*      */     
/*  555 */     return count;
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
/*      */   public Node item(int index) {
/*  571 */     ElemTemplateElement node = this.m_firstChild;
/*      */     
/*  573 */     for (int i = 0; i < index && node != null; i++)
/*      */     {
/*  575 */       node = node.m_nextSibling;
/*      */     }
/*      */     
/*  578 */     return (Node)node;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Document getOwnerDocument() {
/*  588 */     return (Document)getStylesheet();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ElemTemplate getOwnerXSLTemplate() {
/*  598 */     ElemTemplateElement el = this;
/*  599 */     int type = el.getXSLToken();
/*  600 */     while (null != el && type != 19) {
/*      */       
/*  602 */       el = el.getParentElem();
/*  603 */       if (null != el)
/*  604 */         type = el.getXSLToken(); 
/*      */     } 
/*  606 */     return (ElemTemplate)el;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTagName() {
/*  617 */     return getNodeName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasTextLitOnly() {
/*  626 */     return this.m_hasTextLitOnly;
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
/*      */   public String getBaseIdentifier() {
/*  638 */     return getSystemId();
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
/*      */   public int getEndLineNumber() {
/*  658 */     return this.m_endLineNumber;
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
/*      */   public int getLineNumber() {
/*  670 */     return this.m_lineNumber;
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
/*      */   public int getEndColumnNumber() {
/*  691 */     return this.m_endColumnNumber;
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
/*      */   public int getColumnNumber() {
/*  704 */     return this.m_columnNumber;
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
/*      */   public String getPublicId() {
/*  716 */     return (null != this.m_parentNode) ? this.m_parentNode.getPublicId() : null;
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
/*      */   public String getSystemId() {
/*  731 */     Stylesheet sheet = getStylesheet();
/*  732 */     return (sheet == null) ? null : sheet.getHref();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocaterInfo(SourceLocator locator) {
/*  742 */     this.m_lineNumber = locator.getLineNumber();
/*  743 */     this.m_columnNumber = locator.getColumnNumber();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEndLocaterInfo(SourceLocator locator) {
/*  753 */     this.m_endLineNumber = locator.getLineNumber();
/*  754 */     this.m_endColumnNumber = locator.getColumnNumber();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_defaultSpace = true;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_hasTextLitOnly = false;
/*      */ 
/*      */   
/*      */   protected boolean m_hasVariableDecl = false;
/*      */ 
/*      */   
/*      */   private Vector m_declaredPrefixes;
/*      */ 
/*      */   
/*      */   Vector m_prefixTable;
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasVariableDecl() {
/*  778 */     return this.m_hasVariableDecl;
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
/*      */   public void setXmlSpace(int v) {
/*  794 */     this.m_defaultSpace = (2 == v);
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
/*      */   public boolean getXmlSpace() {
/*  809 */     return this.m_defaultSpace;
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
/*      */   public Vector getDeclaredPrefixes() {
/*  827 */     return this.m_declaredPrefixes;
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
/*      */   public void setPrefixes(NamespaceSupport nsSupport) throws TransformerException {
/*  842 */     setPrefixes(nsSupport, false);
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
/*      */   public void setPrefixes(NamespaceSupport nsSupport, boolean excludeXSLDecl) throws TransformerException {
/*  860 */     Enumeration decls = nsSupport.getDeclaredPrefixes();
/*      */     
/*  862 */     while (decls.hasMoreElements()) {
/*      */       
/*  864 */       String prefix = decls.nextElement();
/*      */       
/*  866 */       if (null == this.m_declaredPrefixes) {
/*  867 */         this.m_declaredPrefixes = new Vector();
/*      */       }
/*  869 */       String uri = nsSupport.getURI(prefix);
/*      */       
/*  871 */       if (excludeXSLDecl && uri.equals("http://www.w3.org/1999/XSL/Transform")) {
/*      */         continue;
/*      */       }
/*      */       
/*  875 */       XMLNSDecl decl = new XMLNSDecl(prefix, uri, false);
/*      */       
/*  877 */       this.m_declaredPrefixes.addElement(decl);
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
/*      */   public String getNamespaceForPrefix(String prefix, Node context) {
/*  893 */     error("ER_CANT_RESOLVE_NSPREFIX", null);
/*      */     
/*  895 */     return null;
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
/*      */   public String getNamespaceForPrefix(String prefix) {
/*  919 */     Vector nsDecls = this.m_declaredPrefixes;
/*      */     
/*  921 */     if (null != nsDecls) {
/*      */       
/*  923 */       int n = nsDecls.size();
/*  924 */       if (prefix.equals("#default"))
/*      */       {
/*  926 */         prefix = "";
/*      */       }
/*      */       
/*  929 */       for (int i = 0; i < n; i++) {
/*      */         
/*  931 */         XMLNSDecl decl = nsDecls.elementAt(i);
/*      */         
/*  933 */         if (prefix.equals(decl.getPrefix())) {
/*  934 */           return decl.getURI();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  939 */     if (null != this.m_parentNode) {
/*  940 */       return this.m_parentNode.getNamespaceForPrefix(prefix);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  945 */     if ("xml".equals(prefix)) {
/*  946 */       return "http://www.w3.org/XML/1998/namespace";
/*      */     }
/*      */     
/*  949 */     return null;
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
/*      */   public Vector getPrefixes() {
/*  967 */     return this.m_prefixTable;
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
/*      */   public boolean containsExcludeResultPrefix(String prefix, String uri) {
/*  982 */     ElemTemplateElement parent = getParentElem();
/*  983 */     if (null != parent) {
/*  984 */       return parent.containsExcludeResultPrefix(prefix, uri);
/*      */     }
/*  986 */     return false;
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
/*      */   private boolean excludeResultNSDecl(String prefix, String uri) throws TransformerException {
/* 1005 */     if (uri != null) {
/*      */       
/* 1007 */       if (uri.equals("http://www.w3.org/1999/XSL/Transform") || getStylesheet().containsExtensionElementURI(uri))
/*      */       {
/* 1009 */         return true;
/*      */       }
/* 1011 */       if (containsExcludeResultPrefix(prefix, uri)) {
/* 1012 */         return true;
/*      */       }
/*      */     } 
/* 1015 */     return false;
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
/*      */   public void resolvePrefixTables() throws TransformerException {
/* 1032 */     this.m_prefixTable = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1038 */     if (null != this.m_declaredPrefixes) {
/*      */       
/* 1040 */       StylesheetRoot stylesheet = getStylesheetRoot();
/*      */ 
/*      */ 
/*      */       
/* 1044 */       int n = this.m_declaredPrefixes.size();
/*      */       
/* 1046 */       for (int i = 0; i < n; i++) {
/*      */         
/* 1048 */         XMLNSDecl decl = this.m_declaredPrefixes.elementAt(i);
/* 1049 */         String prefix = decl.getPrefix();
/* 1050 */         String uri = decl.getURI();
/* 1051 */         if (null == uri)
/* 1052 */           uri = ""; 
/* 1053 */         boolean shouldExclude = excludeResultNSDecl(prefix, uri);
/*      */ 
/*      */         
/* 1056 */         if (null == this.m_prefixTable) {
/* 1057 */           this.m_prefixTable = new Vector();
/*      */         }
/* 1059 */         NamespaceAlias nsAlias = stylesheet.getNamespaceAliasComposed(uri);
/* 1060 */         if (null != nsAlias) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1068 */           decl = new XMLNSDecl(nsAlias.getStylesheetPrefix(), nsAlias.getResultNamespace(), shouldExclude);
/*      */         }
/*      */         else {
/*      */           
/* 1072 */           decl = new XMLNSDecl(prefix, uri, shouldExclude);
/*      */         } 
/* 1074 */         this.m_prefixTable.addElement(decl);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1079 */     ElemTemplateElement parent = getParentNodeElem();
/*      */     
/* 1081 */     if (null != parent) {
/*      */ 
/*      */ 
/*      */       
/* 1085 */       Vector prefixes = parent.m_prefixTable;
/*      */       
/* 1087 */       if (null == this.m_prefixTable && !needToCheckExclude()) {
/*      */ 
/*      */ 
/*      */         
/* 1091 */         this.m_prefixTable = parent.m_prefixTable;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1097 */         int n = prefixes.size();
/*      */         
/* 1099 */         for (int i = 0; i < n; i++)
/*      */         {
/* 1101 */           XMLNSDecl decl = prefixes.elementAt(i);
/* 1102 */           boolean shouldExclude = excludeResultNSDecl(decl.getPrefix(), decl.getURI());
/*      */ 
/*      */           
/* 1105 */           if (shouldExclude != decl.getIsExcluded())
/*      */           {
/* 1107 */             decl = new XMLNSDecl(decl.getPrefix(), decl.getURI(), shouldExclude);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1112 */           addOrReplaceDecls(decl);
/*      */         }
/*      */       
/*      */       } 
/* 1116 */     } else if (null == this.m_prefixTable) {
/*      */ 
/*      */ 
/*      */       
/* 1120 */       this.m_prefixTable = new Vector();
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
/*      */   void addOrReplaceDecls(XMLNSDecl newDecl) {
/* 1132 */     int n = this.m_prefixTable.size();
/*      */     
/* 1134 */     for (int i = n - 1; i >= 0; i--) {
/*      */       
/* 1136 */       XMLNSDecl decl = this.m_prefixTable.elementAt(i);
/*      */       
/* 1138 */       if (decl.getPrefix().equals(newDecl.getPrefix())) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */     
/* 1143 */     this.m_prefixTable.addElement(newDecl);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean needToCheckExclude() {
/* 1153 */     return false;
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
/*      */   void executeNSDecls(TransformerImpl transformer) throws TransformerException {
/* 1166 */     executeNSDecls(transformer, null);
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
/*      */   void executeNSDecls(TransformerImpl transformer, String ignorePrefix) throws TransformerException {
/*      */     try {
/* 1182 */       if (null != this.m_prefixTable) {
/*      */         
/* 1184 */         SerializationHandler rhandler = transformer.getResultTreeHandler();
/* 1185 */         int n = this.m_prefixTable.size();
/*      */         
/* 1187 */         for (int i = n - 1; i >= 0; i--)
/*      */         {
/* 1189 */           XMLNSDecl decl = this.m_prefixTable.elementAt(i);
/*      */           
/* 1191 */           if (!decl.getIsExcluded() && (null == ignorePrefix || !decl.getPrefix().equals(ignorePrefix)))
/*      */           {
/* 1193 */             rhandler.startPrefixMapping(decl.getPrefix(), decl.getURI(), true);
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*      */     } catch (SAXException se) {
/*      */       
/* 1200 */       throw new TransformerException(se);
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
/*      */   void unexecuteNSDecls(TransformerImpl transformer) throws TransformerException {
/* 1214 */     unexecuteNSDecls(transformer, null);
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
/*      */   void unexecuteNSDecls(TransformerImpl transformer, String ignorePrefix) throws TransformerException {
/*      */     try {
/* 1231 */       if (null != this.m_prefixTable) {
/*      */         
/* 1233 */         SerializationHandler rhandler = transformer.getResultTreeHandler();
/* 1234 */         int n = this.m_prefixTable.size();
/*      */         
/* 1236 */         for (int i = 0; i < n; i++)
/*      */         {
/* 1238 */           XMLNSDecl decl = this.m_prefixTable.elementAt(i);
/*      */           
/* 1240 */           if (!decl.getIsExcluded() && (null == ignorePrefix || !decl.getPrefix().equals(ignorePrefix)))
/*      */           {
/* 1242 */             rhandler.endPrefixMapping(decl.getPrefix());
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*      */     } catch (SAXException se) {
/*      */       
/* 1249 */       throw new TransformerException(se);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1255 */   protected int m_docOrderNumber = -1;
/*      */   
/*      */   protected ElemTemplateElement m_parentNode;
/*      */   
/*      */   ElemTemplateElement m_nextSibling;
/*      */   ElemTemplateElement m_firstChild;
/*      */   private transient Node m_DOMBackPointer;
/*      */   
/*      */   public void setUid(int i) {
/* 1264 */     this.m_docOrderNumber = i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getUid() {
/* 1274 */     return this.m_docOrderNumber;
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
/*      */   public Node getParentNode() {
/* 1291 */     return (Node)this.m_parentNode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ElemTemplateElement getParentElem() {
/* 1301 */     return this.m_parentNode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setParentElem(ElemTemplateElement p) {
/* 1311 */     this.m_parentNode = p;
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
/*      */   public Node getNextSibling() {
/* 1327 */     return (Node)this.m_nextSibling;
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
/*      */   public Node getPreviousSibling() {
/* 1342 */     Node walker = getParentNode(), prev = null;
/*      */     
/* 1344 */     if (walker != null)
/* 1345 */       for (walker = walker.getFirstChild(); walker != null; 
/* 1346 */         prev = walker, walker = walker.getNextSibling()) {
/*      */         
/* 1348 */         if (walker == this) {
/* 1349 */           return prev;
/*      */         }
/*      */       }  
/* 1352 */     return null;
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
/*      */   public ElemTemplateElement getPreviousSiblingElem() {
/* 1367 */     ElemTemplateElement walker = getParentNodeElem();
/* 1368 */     ElemTemplateElement prev = null;
/*      */     
/* 1370 */     if (walker != null)
/* 1371 */       for (walker = walker.getFirstChildElem(); walker != null; 
/* 1372 */         prev = walker, walker = walker.getNextSiblingElem()) {
/*      */         
/* 1374 */         if (walker == this) {
/* 1375 */           return prev;
/*      */         }
/*      */       }  
/* 1378 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ElemTemplateElement getNextSiblingElem() {
/* 1389 */     return this.m_nextSibling;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ElemTemplateElement getParentNodeElem() {
/* 1399 */     return this.m_parentNode;
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
/*      */   public Node getFirstChild() {
/* 1416 */     return (Node)this.m_firstChild;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ElemTemplateElement getFirstChildElem() {
/* 1426 */     return this.m_firstChild;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getLastChild() {
/* 1437 */     ElemTemplateElement lastChild = null;
/*      */     
/* 1439 */     for (ElemTemplateElement node = this.m_firstChild; node != null; 
/* 1440 */       node = node.m_nextSibling)
/*      */     {
/* 1442 */       lastChild = node;
/*      */     }
/*      */     
/* 1445 */     return (Node)lastChild;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ElemTemplateElement getLastChildElem() {
/* 1456 */     ElemTemplateElement lastChild = null;
/*      */     
/* 1458 */     for (ElemTemplateElement node = this.m_firstChild; node != null; 
/* 1459 */       node = node.m_nextSibling)
/*      */     {
/* 1461 */       lastChild = node;
/*      */     }
/*      */     
/* 1464 */     return lastChild;
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
/*      */   public Node getDOMBackPointer() {
/* 1480 */     return this.m_DOMBackPointer;
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
/*      */   public void setDOMBackPointer(Node n) {
/* 1492 */     this.m_DOMBackPointer = n;
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
/*      */   public int compareTo(Object o) throws ClassCastException {
/* 1510 */     ElemTemplateElement ro = (ElemTemplateElement)o;
/* 1511 */     int roPrecedence = ro.getStylesheetComposed().getImportCountComposed();
/* 1512 */     int myPrecedence = getStylesheetComposed().getImportCountComposed();
/*      */     
/* 1514 */     if (myPrecedence < roPrecedence)
/* 1515 */       return -1; 
/* 1516 */     if (myPrecedence > roPrecedence) {
/* 1517 */       return 1;
/*      */     }
/* 1519 */     return getUid() - ro.getUid();
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
/*      */   public boolean shouldStripWhiteSpace(XPathContext support, Element targetElement) throws TransformerException {
/* 1537 */     StylesheetRoot sroot = getStylesheetRoot();
/* 1538 */     return (null != sroot) ? sroot.shouldStripWhiteSpace(support, targetElement) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canStripWhiteSpace() {
/* 1549 */     StylesheetRoot sroot = getStylesheetRoot();
/* 1550 */     return (null != sroot) ? sroot.canStripWhiteSpace() : false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canAcceptVariables() {
/* 1559 */     return true;
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
/*      */   public void exprSetParent(ExpressionNode n) {
/* 1572 */     setParentElem((ElemTemplateElement)n);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ExpressionNode exprGetParent() {
/* 1580 */     return getParentElem();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void exprAddChild(ExpressionNode n, int i) {
/* 1590 */     appendChild((ElemTemplateElement)n);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ExpressionNode exprGetChild(int i) {
/* 1597 */     return (ExpressionNode)item(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int exprGetNumChildren() {
/* 1603 */     return getLength();
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
/*      */   protected boolean accept(XSLTVisitor visitor) {
/* 1615 */     return visitor.visitInstruction(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void callVisitors(XSLTVisitor visitor) {
/* 1623 */     if (accept(visitor))
/*      */     {
/* 1625 */       callChildVisitors(visitor);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void callChildVisitors(XSLTVisitor visitor, boolean callAttributes) {
/* 1635 */     ElemTemplateElement node = this.m_firstChild;
/* 1636 */     for (; node != null; 
/* 1637 */       node = node.m_nextSibling)
/*      */     {
/* 1639 */       node.callVisitors(visitor);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void callChildVisitors(XSLTVisitor visitor) {
/* 1649 */     callChildVisitors(visitor, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean handlesNullPrefixes() {
/* 1657 */     return false;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemTemplateElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */