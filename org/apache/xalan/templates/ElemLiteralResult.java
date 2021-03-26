/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xml.utils.StringVector;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.xml.sax.SAXException;
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
/*     */ public class ElemLiteralResult
/*     */   extends ElemUse
/*     */ {
/*     */   private boolean isLiteralResultAsStylesheet = false;
/*     */   
/*     */   public void setIsLiteralResultAsStylesheet(boolean b) {
/*  58 */     this.isLiteralResultAsStylesheet = b;
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
/*     */   public boolean getIsLiteralResultAsStylesheet() {
/*  71 */     return this.isLiteralResultAsStylesheet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/*  82 */     super.compose(sroot);
/*  83 */     StylesheetRoot.ComposeState cstate = sroot.getComposeState();
/*  84 */     Vector vnames = cstate.getVariableNames();
/*  85 */     if (null != this.m_avts) {
/*     */       
/*  87 */       int nAttrs = this.m_avts.size();
/*     */       
/*  89 */       for (int i = nAttrs - 1; i >= 0; i--) {
/*     */         
/*  91 */         AVT avt = this.m_avts.elementAt(i);
/*  92 */         avt.fixupVariables(vnames, cstate.getGlobalsSize());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   private Vector m_avts = null;
/*     */ 
/*     */ 
/*     */   
/* 107 */   private Vector m_xslAttr = null;
/*     */   
/*     */   private String m_namespace;
/*     */   private String m_localName;
/*     */   private String m_rawName;
/*     */   private StringVector m_ExtensionElementURIs;
/*     */   private String m_version;
/*     */   private StringVector m_excludeResultPrefixes;
/*     */   
/*     */   public void addLiteralResultAttribute(AVT avt) {
/* 117 */     if (null == this.m_avts) {
/* 118 */       this.m_avts = new Vector();
/*     */     }
/* 120 */     this.m_avts.addElement(avt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLiteralResultAttribute(String att) {
/* 131 */     if (null == this.m_xslAttr) {
/* 132 */       this.m_xslAttr = new Vector();
/*     */     }
/* 134 */     this.m_xslAttr.addElement(att);
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
/*     */   public void setXmlSpace(AVT avt) {
/* 151 */     addLiteralResultAttribute(avt);
/* 152 */     String val = avt.getSimpleString();
/* 153 */     if (val.equals("default")) {
/*     */       
/* 155 */       setXmlSpace(2);
/*     */     }
/* 157 */     else if (val.equals("preserve")) {
/*     */       
/* 159 */       setXmlSpace(1);
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
/*     */   public AVT getLiteralResultAttribute(String name) {
/* 175 */     if (null != this.m_avts) {
/*     */       
/* 177 */       int nAttrs = this.m_avts.size();
/*     */       
/* 179 */       for (int i = nAttrs - 1; i >= 0; i--) {
/*     */         
/* 181 */         AVT avt = this.m_avts.elementAt(i);
/*     */         
/* 183 */         if (avt.getRawName().equals(name))
/*     */         {
/* 185 */           return avt;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 190 */     return null;
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
/*     */   public boolean containsExcludeResultPrefix(String prefix, String uri) {
/* 206 */     if (uri == null || (null == this.m_excludeResultPrefixes && null == this.m_ExtensionElementURIs))
/*     */     {
/*     */ 
/*     */       
/* 210 */       return super.containsExcludeResultPrefix(prefix, uri);
/*     */     }
/* 212 */     if (prefix.length() == 0) {
/* 213 */       prefix = "#default";
/*     */     }
/*     */ 
/*     */     
/* 217 */     if (this.m_excludeResultPrefixes != null) {
/* 218 */       for (int i = 0; i < this.m_excludeResultPrefixes.size(); i++) {
/*     */         
/* 220 */         if (uri.equals(getNamespaceForPrefix(this.m_excludeResultPrefixes.elementAt(i)))) {
/* 221 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 225 */     if (this.m_ExtensionElementURIs != null && this.m_ExtensionElementURIs.contains(uri)) {
/* 226 */       return true;
/*     */     }
/* 228 */     return super.containsExcludeResultPrefix(prefix, uri);
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
/*     */   public void resolvePrefixTables() throws TransformerException {
/* 240 */     super.resolvePrefixTables();
/*     */     
/* 242 */     StylesheetRoot stylesheet = getStylesheetRoot();
/*     */     
/* 244 */     if (null != this.m_namespace && this.m_namespace.length() > 0) {
/*     */       
/* 246 */       NamespaceAlias nsa = stylesheet.getNamespaceAliasComposed(this.m_namespace);
/*     */       
/* 248 */       if (null != nsa) {
/*     */         
/* 250 */         this.m_namespace = nsa.getResultNamespace();
/*     */ 
/*     */         
/* 253 */         String resultPrefix = nsa.getStylesheetPrefix();
/*     */         
/* 255 */         if (null != resultPrefix && resultPrefix.length() > 0) {
/* 256 */           this.m_rawName = resultPrefix + ":" + this.m_localName;
/*     */         } else {
/* 258 */           this.m_rawName = this.m_localName;
/*     */         } 
/*     */       } 
/*     */     } 
/* 262 */     if (null != this.m_avts) {
/*     */       
/* 264 */       int n = this.m_avts.size();
/*     */       
/* 266 */       for (int i = 0; i < n; i++) {
/*     */         
/* 268 */         AVT avt = this.m_avts.elementAt(i);
/*     */ 
/*     */         
/* 271 */         String ns = avt.getURI();
/*     */         
/* 273 */         if (null != ns && ns.length() > 0) {
/*     */           
/* 275 */           NamespaceAlias nsa = stylesheet.getNamespaceAliasComposed(this.m_namespace);
/*     */ 
/*     */           
/* 278 */           if (null != nsa) {
/*     */             
/* 280 */             String namespace = nsa.getResultNamespace();
/*     */ 
/*     */             
/* 283 */             String resultPrefix = nsa.getStylesheetPrefix();
/* 284 */             String rawName = avt.getName();
/*     */             
/* 286 */             if (null != resultPrefix && resultPrefix.length() > 0) {
/* 287 */               rawName = resultPrefix + ":" + rawName;
/*     */             }
/* 289 */             avt.setURI(namespace);
/* 290 */             avt.setRawName(rawName);
/*     */           } 
/*     */         } 
/*     */       } 
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
/*     */   boolean needToCheckExclude() {
/* 307 */     if (null == this.m_excludeResultPrefixes && null == this.m_prefixTable && this.m_ExtensionElementURIs == null)
/*     */     {
/*     */       
/* 310 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 315 */     if (null == this.m_prefixTable) {
/* 316 */       this.m_prefixTable = new Vector();
/*     */     }
/* 318 */     return true;
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
/*     */   public void setNamespace(String ns) {
/* 339 */     if (null == ns)
/* 340 */       ns = ""; 
/* 341 */     this.m_namespace = ns;
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
/*     */   public String getNamespace() {
/* 355 */     return this.m_namespace;
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
/*     */   public void setLocalName(String localName) {
/* 372 */     this.m_localName = localName;
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
/*     */   public String getLocalName() {
/* 386 */     return this.m_localName;
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
/*     */   public void setRawName(String rawName) {
/* 403 */     this.m_rawName = rawName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRawName() {
/* 414 */     return this.m_rawName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/* 424 */     int len = this.m_rawName.length() - this.m_localName.length() - 1;
/* 425 */     return (len > 0) ? this.m_rawName.substring(0, len) : "";
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
/*     */   public void setExtensionElementPrefixes(StringVector v) {
/* 445 */     this.m_ExtensionElementURIs = v;
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
/*     */   public String getExtensionElementPrefix(int i) throws ArrayIndexOutOfBoundsException {
/* 462 */     if (null == this.m_ExtensionElementURIs) {
/* 463 */       throw new ArrayIndexOutOfBoundsException();
/*     */     }
/* 465 */     return this.m_ExtensionElementURIs.elementAt(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExtensionElementPrefixCount() {
/* 476 */     return (null != this.m_ExtensionElementURIs) ? this.m_ExtensionElementURIs.size() : 0;
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
/*     */   public boolean containsExtensionElementURI(String uri) {
/* 491 */     if (null == this.m_ExtensionElementURIs) {
/* 492 */       return false;
/*     */     }
/* 494 */     return this.m_ExtensionElementURIs.contains(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXSLToken() {
/* 505 */     return 77;
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
/*     */   public String getNodeName() {
/* 517 */     return this.m_rawName;
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
/*     */   public void setVersion(String v) {
/* 534 */     this.m_version = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVersion() {
/* 545 */     return this.m_version;
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
/*     */ 
/*     */   
/*     */   public void setExcludeResultPrefixes(StringVector v) {
/* 568 */     this.m_excludeResultPrefixes = v;
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
/*     */   private boolean excludeResultNSDecl(String prefix, String uri) throws TransformerException {
/* 586 */     if (null != this.m_excludeResultPrefixes)
/*     */     {
/* 588 */       return containsExcludeResultPrefix(prefix, uri);
/*     */     }
/*     */     
/* 591 */     return false;
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 609 */     SerializationHandler rhandler = transformer.getSerializationHandler();
/*     */ 
/*     */ 
/*     */     
/* 613 */     try { if (TransformerImpl.S_DEBUG) {
/*     */ 
/*     */         
/* 616 */         rhandler.flushPending();
/* 617 */         transformer.getTraceManager().fireTraceEvent(this);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 623 */       rhandler.startPrefixMapping(getPrefix(), getNamespace());
/*     */ 
/*     */       
/* 626 */       executeNSDecls(transformer);
/* 627 */       rhandler.startElement(getNamespace(), getLocalName(), getRawName()); } catch (SAXException se)
/*     */     
/*     */     { 
/*     */       
/* 631 */       throw new TransformerException(se); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 641 */     TransformerException tException = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 646 */     try { super.execute(transformer);
/*     */ 
/*     */ 
/*     */       
/* 650 */       if (null != this.m_avts) {
/*     */         
/* 652 */         int nAttrs = this.m_avts.size();
/*     */         
/* 654 */         for (int i = nAttrs - 1; i >= 0; i--) {
/*     */           
/* 656 */           AVT avt = this.m_avts.elementAt(i);
/* 657 */           XPathContext xctxt = transformer.getXPathContext();
/* 658 */           int sourceNode = xctxt.getCurrentNode();
/* 659 */           String stringedValue = avt.evaluate(xctxt, sourceNode, this);
/*     */ 
/*     */           
/* 662 */           if (null != stringedValue)
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 670 */             rhandler.addAttribute(avt.getURI(), avt.getName(), avt.getRawName(), "CDATA", stringedValue);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 682 */       transformer.executeChildTemplates(this, true); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 687 */       tException = te; } catch (SAXException se)
/*     */     
/*     */     { 
/*     */       
/* 691 */       tException = new TransformerException(se); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 701 */     try { if (TransformerImpl.S_DEBUG)
/*     */       {
/*     */ 
/*     */         
/* 705 */         transformer.getTraceManager().fireTraceEndEvent(this);
/*     */       }
/* 707 */       rhandler.endElement(getNamespace(), getLocalName(), getRawName()); } catch (SAXException se)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 715 */       if (tException != null) {
/* 716 */         throw tException;
/*     */       }
/* 718 */       throw new TransformerException(se); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 724 */     if (tException != null) {
/* 725 */       throw tException;
/*     */     }
/* 727 */     unexecuteNSDecls(transformer);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 732 */     try { rhandler.endPrefixMapping(getPrefix()); } catch (SAXException se)
/*     */     
/*     */     { 
/*     */       
/* 736 */       throw new TransformerException(se); }
/*     */   
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
/*     */   public Enumeration enumerateLiteralResultAttributes() {
/* 749 */     return (null == this.m_avts) ? null : this.m_avts.elements();
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
/*     */   protected boolean accept(XSLTVisitor visitor) {
/* 761 */     return visitor.visitLiteralResultElement(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void callChildVisitors(XSLTVisitor visitor, boolean callAttrs) {
/* 770 */     if (callAttrs && null != this.m_avts) {
/*     */       
/* 772 */       int nAttrs = this.m_avts.size();
/*     */       
/* 774 */       for (int i = nAttrs - 1; i >= 0; i--) {
/*     */         
/* 776 */         AVT avt = this.m_avts.elementAt(i);
/* 777 */         avt.callVisitors(visitor);
/*     */       } 
/*     */     } 
/* 780 */     super.callChildVisitors(visitor, callAttrs);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemLiteralResult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */