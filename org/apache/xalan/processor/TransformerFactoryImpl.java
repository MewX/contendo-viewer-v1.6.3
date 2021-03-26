/*     */ package org.apache.xalan.processor;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.xml.parsers.FactoryConfigurationError;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Templates;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.URIResolver;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import javax.xml.transform.sax.SAXTransformerFactory;
/*     */ import javax.xml.transform.sax.TemplatesHandler;
/*     */ import javax.xml.transform.sax.TransformerHandler;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.transformer.TrAXFilter;
/*     */ import org.apache.xalan.transformer.TransformerIdentityImpl;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xml.dtm.ref.sax2dtm.SAX2DTM;
/*     */ import org.apache.xml.utils.DOM2Helper;
/*     */ import org.apache.xml.utils.DOMHelper;
/*     */ import org.apache.xml.utils.DefaultErrorHandler;
/*     */ import org.apache.xml.utils.StopParseException;
/*     */ import org.apache.xml.utils.StylesheetPIHandler;
/*     */ import org.apache.xml.utils.SystemIDResolver;
/*     */ import org.apache.xml.utils.TreeWalker;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.XMLFilter;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.XMLReaderFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransformerFactoryImpl
/*     */   extends SAXTransformerFactory
/*     */ {
/*     */   public static final String XSLT_PROPERTIES = "org/apache/xalan/res/XSLTInfo.properties";
/*     */   public static final String FEATURE_INCREMENTAL = "http://xml.apache.org/xalan/features/incremental";
/*     */   public static final String FEATURE_OPTIMIZE = "http://xml.apache.org/xalan/features/optimize";
/*     */   public static final String FEATURE_SOURCE_LOCATION = "http://xml.apache.org/xalan/properties/source-location";
/*     */   
/*     */   public Templates processFromNode(Node node) throws TransformerConfigurationException {
/*     */     
/* 107 */     try { TemplatesHandler builder = newTemplatesHandler();
/* 108 */       TreeWalker walker = new TreeWalker(builder, (DOMHelper)new DOM2Helper(), builder.getSystemId());
/*     */ 
/*     */ 
/*     */       
/* 112 */       walker.traverse(node);
/*     */       
/* 114 */       return builder.getTemplates(); } catch (SAXException se)
/*     */     
/*     */     { 
/*     */       
/* 118 */       if (this.m_errorListener != null) {
/*     */ 
/*     */ 
/*     */         
/* 122 */         try { this.m_errorListener.fatalError(new TransformerException(se)); } catch (TransformerException ex)
/*     */         
/*     */         { 
/*     */           
/* 126 */           throw new TransformerConfigurationException(ex); }
/*     */ 
/*     */         
/* 129 */         return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 136 */       throw new TransformerConfigurationException(XSLMessages.createMessage("ER_PROCESSFROMNODE_FAILED", null), se); } catch (TransformerConfigurationException tce)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */       
/* 142 */       throw tce; } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 151 */       if (this.m_errorListener != null) {
/*     */ 
/*     */ 
/*     */         
/* 155 */         try { this.m_errorListener.fatalError(new TransformerException(e)); } catch (TransformerException ex)
/*     */         
/*     */         { 
/*     */           
/* 159 */           throw new TransformerConfigurationException(ex); }
/*     */ 
/*     */         
/* 162 */         return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 169 */       throw new TransformerConfigurationException(XSLMessages.createMessage("ER_PROCESSFROMNODE_FAILED", null), e); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   private String m_DOMsystemID = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getDOMsystemID() {
/* 188 */     return this.m_DOMsystemID;
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
/*     */   Templates processFromNode(Node node, String systemID) throws TransformerConfigurationException {
/* 209 */     this.m_DOMsystemID = systemID;
/*     */     
/* 211 */     return processFromNode(node);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Source getAssociatedStylesheet(Source source, String media, String title, String charset) throws TransformerConfigurationException {
/*     */     String str;
/* 243 */     InputSource isource = null;
/* 244 */     Node node = null;
/* 245 */     XMLReader reader = null;
/*     */     
/* 247 */     if (source instanceof DOMSource) {
/*     */       
/* 249 */       DOMSource dsource = (DOMSource)source;
/*     */       
/* 251 */       node = dsource.getNode();
/* 252 */       str = dsource.getSystemId();
/*     */     }
/*     */     else {
/*     */       
/* 256 */       isource = SAXSource.sourceToInputSource(source);
/* 257 */       str = isource.getSystemId();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 263 */     StylesheetPIHandler handler = new StylesheetPIHandler(str, media, title, charset);
/*     */ 
/*     */ 
/*     */     
/* 267 */     if (this.m_uriResolver != null)
/*     */     {
/* 269 */       handler.setURIResolver(this.m_uriResolver);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 274 */     try { if (null != node)
/*     */       
/* 276 */       { TreeWalker walker = new TreeWalker((ContentHandler)handler, (DOMHelper)new DOM2Helper(), str);
/*     */         
/* 278 */         walker.traverse(node); }
/*     */       else
/*     */       
/*     */       { 
/*     */         
/*     */         try { 
/*     */ 
/*     */           
/* 286 */           SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */ 
/*     */           
/* 289 */           factory.setNamespaceAware(true);
/*     */           
/* 291 */           SAXParser jaxpParser = factory.newSAXParser();
/*     */           
/* 293 */           reader = jaxpParser.getXMLReader(); } catch (ParserConfigurationException ex)
/*     */         
/*     */         { 
/*     */           
/* 297 */           throw new SAXException(ex); } catch (FactoryConfigurationError ex1)
/*     */         
/*     */         { 
/*     */           
/* 301 */           throw new SAXException(ex1.toString()); } catch (NoSuchMethodError ex2)
/*     */         {  }
/* 303 */         catch (AbstractMethodError abstractMethodError) {}
/*     */ 
/*     */         
/* 306 */         if (null == reader)
/*     */         {
/* 308 */           reader = XMLReaderFactory.createXMLReader();
/*     */         }
/*     */ 
/*     */         
/* 312 */         reader.setContentHandler((ContentHandler)handler);
/* 313 */         reader.parse(isource); }  } catch (StopParseException spe)
/*     */     
/*     */     {  }
/*     */     
/* 317 */     catch (SAXException se)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */       
/* 323 */       throw new TransformerConfigurationException("getAssociatedStylesheets failed", se); } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 328 */       throw new TransformerConfigurationException("getAssociatedStylesheets failed", ioe); }
/*     */ 
/*     */ 
/*     */     
/* 332 */     return handler.getAssociatedStylesheet();
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
/*     */   public TemplatesHandler newTemplatesHandler() throws TransformerConfigurationException {
/* 351 */     return new StylesheetHandler(this);
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
/*     */   public boolean getFeature(String name) {
/* 372 */     if ("http://javax.xml.transform.dom.DOMResult/feature" == name || "http://javax.xml.transform.dom.DOMSource/feature" == name || "http://javax.xml.transform.sax.SAXResult/feature" == name || "http://javax.xml.transform.sax.SAXSource/feature" == name || "http://javax.xml.transform.stream.StreamResult/feature" == name || "http://javax.xml.transform.stream.StreamSource/feature" == name || "http://javax.xml.transform.sax.SAXTransformerFactory/feature" == name || "http://javax.xml.transform.sax.SAXTransformerFactory/feature/xmlfilter" == name)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 378 */       return true; } 
/* 379 */     if ("http://javax.xml.transform.dom.DOMResult/feature".equals(name) || "http://javax.xml.transform.dom.DOMSource/feature".equals(name) || "http://javax.xml.transform.sax.SAXResult/feature".equals(name) || "http://javax.xml.transform.sax.SAXSource/feature".equals(name) || "http://javax.xml.transform.stream.StreamResult/feature".equals(name) || "http://javax.xml.transform.stream.StreamSource/feature".equals(name) || "http://javax.xml.transform.sax.SAXTransformerFactory/feature".equals(name) || "http://javax.xml.transform.sax.SAXTransformerFactory/feature/xmlfilter".equals(name))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 387 */       return true;
/*     */     }
/* 389 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean m_optimize = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean m_source_location = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   URIResolver m_uriResolver;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttribute(String name, Object value) throws IllegalArgumentException {
/* 419 */     if (name.equals("http://xml.apache.org/xalan/features/incremental")) {
/*     */       
/* 421 */       if (value instanceof Boolean)
/*     */       {
/*     */         
/* 424 */         DTMManager.setIncremental(((Boolean)value).booleanValue());
/*     */       }
/* 426 */       else if (value instanceof String)
/*     */       {
/*     */         
/* 429 */         DTMManager.setIncremental((new Boolean((String)value)).booleanValue());
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 434 */         throw new IllegalArgumentException(XSLMessages.createMessage("ER_BAD_VALUE", new Object[] { name, value }));
/*     */       }
/*     */     
/* 437 */     } else if (name.equals("http://xml.apache.org/xalan/features/optimize")) {
/*     */       
/* 439 */       if (value instanceof Boolean)
/*     */       {
/*     */         
/* 442 */         m_optimize = ((Boolean)value).booleanValue();
/*     */       }
/* 444 */       else if (value instanceof String)
/*     */       {
/*     */         
/* 447 */         m_optimize = (new Boolean((String)value)).booleanValue();
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 452 */         throw new IllegalArgumentException(XSLMessages.createMessage("ER_BAD_VALUE", new Object[] { name, value }));
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 461 */     else if (name.equals("http://xml.apache.org/xalan/properties/source-location")) {
/*     */       
/* 463 */       if (value instanceof Boolean)
/*     */       {
/*     */         
/* 466 */         m_source_location = ((Boolean)value).booleanValue();
/*     */         
/* 468 */         SAX2DTM.setUseSourceLocation(m_source_location);
/*     */       }
/* 470 */       else if (value instanceof String)
/*     */       {
/*     */         
/* 473 */         m_source_location = (new Boolean((String)value)).booleanValue();
/* 474 */         SAX2DTM.setUseSourceLocation(m_source_location);
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 479 */         throw new IllegalArgumentException(XSLMessages.createMessage("ER_BAD_VALUE", new Object[] { name, value }));
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 485 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_NOT_SUPPORTED", new Object[] { name }));
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
/*     */   public Object getAttribute(String name) throws IllegalArgumentException {
/* 501 */     if (name.equals("http://xml.apache.org/xalan/features/incremental"))
/*     */     {
/* 503 */       return new Boolean(DTMManager.getIncremental());
/*     */     }
/* 505 */     if (name.equals("http://xml.apache.org/xalan/features/optimize"))
/*     */     {
/* 507 */       return new Boolean(m_optimize);
/*     */     }
/* 509 */     if (name.equals("http://xml.apache.org/xalan/properties/source-location"))
/*     */     {
/* 511 */       return new Boolean(m_source_location);
/*     */     }
/*     */     
/* 514 */     throw new IllegalArgumentException(XSLMessages.createMessage("ER_ATTRIB_VALUE_NOT_RECOGNIZED", new Object[] { name }));
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
/*     */   public XMLFilter newXMLFilter(Source src) throws TransformerConfigurationException {
/* 531 */     Templates templates = newTemplates(src);
/* 532 */     if (templates == null) return null;
/*     */     
/* 534 */     return newXMLFilter(templates);
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
/*     */   public XMLFilter newXMLFilter(Templates templates) throws TransformerConfigurationException {
/*     */     
/* 553 */     try { return (XMLFilter)new TrAXFilter(templates); } catch (TransformerConfigurationException ex)
/*     */     
/* 555 */     { if (this.m_errorListener != null) {
/*     */         
/* 557 */         try { this.m_errorListener.fatalError(ex);
/* 558 */           return null; } catch (TransformerException ex1)
/*     */         
/* 560 */         { new TransformerConfigurationException(ex1); }
/*     */       
/*     */       }
/* 563 */       throw ex; }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformerHandler newTransformerHandler(Source src) throws TransformerConfigurationException {
/* 582 */     Templates templates = newTemplates(src);
/* 583 */     if (templates == null) return null;
/*     */     
/* 585 */     return newTransformerHandler(templates);
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
/*     */   public TransformerHandler newTransformerHandler(Templates templates) throws TransformerConfigurationException {
/*     */     
/* 601 */     try { TransformerImpl transformer = (TransformerImpl)templates.newTransformer();
/*     */       
/* 603 */       transformer.setURIResolver(this.m_uriResolver);
/* 604 */       TransformerHandler th = (TransformerHandler)transformer.getInputContentHandler(true);
/*     */ 
/*     */       
/* 607 */       return th; } catch (TransformerConfigurationException ex)
/*     */     
/* 609 */     { if (this.m_errorListener != null) {
/*     */         
/* 611 */         try { this.m_errorListener.fatalError(ex);
/* 612 */           return null; } catch (TransformerException ex1)
/*     */         
/* 614 */         { ex = new TransformerConfigurationException(ex1); }
/*     */       
/*     */       }
/* 617 */       throw ex; }
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
/*     */   public TransformerHandler newTransformerHandler() throws TransformerConfigurationException {
/* 646 */     return (TransformerHandler)new TransformerIdentityImpl();
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
/*     */   public Transformer newTransformer(Source source) throws TransformerConfigurationException {
/*     */     
/* 666 */     try { Templates tmpl = newTemplates(source);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 673 */       if (tmpl == null) return null; 
/* 674 */       Transformer transformer = tmpl.newTransformer();
/* 675 */       transformer.setURIResolver(this.m_uriResolver);
/* 676 */       return transformer; } catch (TransformerConfigurationException ex)
/*     */     
/* 678 */     { if (this.m_errorListener != null) {
/*     */         
/* 680 */         try { this.m_errorListener.fatalError(ex);
/* 681 */           return null; } catch (TransformerException ex1)
/*     */         
/* 683 */         { ex = new TransformerConfigurationException(ex1); }
/*     */       
/*     */       }
/* 686 */       throw ex; }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Transformer newTransformer() throws TransformerConfigurationException {
/* 705 */     return (Transformer)new TransformerIdentityImpl();
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
/*     */   public Templates newTemplates(Source source) throws TransformerConfigurationException {
/* 726 */     String baseID = source.getSystemId();
/*     */     
/* 728 */     if (null != baseID) {
/* 729 */       baseID = SystemIDResolver.getAbsoluteURI(baseID);
/*     */     }
/*     */ 
/*     */     
/* 733 */     if (source instanceof DOMSource) {
/*     */       
/* 735 */       DOMSource dsource = (DOMSource)source;
/* 736 */       Node node = dsource.getNode();
/*     */       
/* 738 */       if (null != node) {
/* 739 */         return processFromNode(node, baseID);
/*     */       }
/*     */       
/* 742 */       String messageStr = XSLMessages.createMessage("ER_ILLEGAL_DOMSOURCE_INPUT", null);
/*     */ 
/*     */       
/* 745 */       throw new IllegalArgumentException(messageStr);
/*     */     } 
/*     */ 
/*     */     
/* 749 */     TemplatesHandler builder = newTemplatesHandler();
/* 750 */     builder.setSystemId(baseID);
/*     */ 
/*     */ 
/*     */     
/* 754 */     try { InputSource isource = SAXSource.sourceToInputSource(source);
/* 755 */       isource.setSystemId(baseID);
/* 756 */       XMLReader reader = null;
/*     */       
/* 758 */       if (source instanceof SAXSource) {
/* 759 */         reader = ((SAXSource)source).getXMLReader();
/*     */       }
/* 761 */       if (null == reader) {
/*     */ 
/*     */         
/*     */         try { 
/*     */ 
/*     */           
/* 767 */           SAXParserFactory factory = SAXParserFactory.newInstance();
/*     */ 
/*     */           
/* 770 */           factory.setNamespaceAware(true);
/*     */           
/* 772 */           SAXParser jaxpParser = factory.newSAXParser();
/*     */           
/* 774 */           reader = jaxpParser.getXMLReader(); } catch (ParserConfigurationException ex)
/*     */         
/*     */         { 
/*     */           
/* 778 */           throw new SAXException(ex); } catch (FactoryConfigurationError ex1)
/*     */         
/*     */         { 
/*     */           
/* 782 */           throw new SAXException(ex1.toString()); } catch (NoSuchMethodError ex2)
/*     */         {  }
/* 784 */         catch (AbstractMethodError abstractMethodError) {}
/*     */       }
/*     */ 
/*     */       
/* 788 */       if (null == reader) {
/* 789 */         reader = XMLReaderFactory.createXMLReader();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 794 */       reader.setContentHandler(builder);
/* 795 */       reader.parse(isource); } catch (SAXException se)
/*     */     
/*     */     { 
/*     */       
/* 799 */       if (this.m_errorListener != null)
/*     */       
/*     */       { 
/*     */         
/* 803 */         try { this.m_errorListener.fatalError(new TransformerException(se)); } catch (TransformerException ex1)
/*     */         
/*     */         { 
/*     */           
/* 807 */           throw new TransformerConfigurationException(ex1); }
/*     */          }
/*     */       else
/*     */       
/* 811 */       { throw new TransformerConfigurationException(se.getMessage(), se); }  } catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/* 815 */       if (this.m_errorListener != null) {
/*     */ 
/*     */         
/*     */         try { 
/* 819 */           this.m_errorListener.fatalError(new TransformerException(e));
/*     */           
/* 821 */           return null; } catch (TransformerException ex1)
/*     */         
/*     */         { 
/*     */           
/* 825 */           throw new TransformerConfigurationException(ex1); }
/*     */       
/*     */       }
/*     */       
/* 829 */       throw new TransformerConfigurationException(e.getMessage(), e); }
/*     */ 
/*     */     
/* 832 */     return builder.getTemplates();
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
/*     */   public void setURIResolver(URIResolver resolver) {
/* 850 */     this.m_uriResolver = resolver;
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
/*     */   public URIResolver getURIResolver() {
/* 862 */     return this.m_uriResolver;
/*     */   }
/*     */ 
/*     */   
/* 866 */   private ErrorListener m_errorListener = (ErrorListener)new DefaultErrorHandler();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErrorListener getErrorListener() {
/* 875 */     return this.m_errorListener;
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
/*     */   public void setErrorListener(ErrorListener listener) throws IllegalArgumentException {
/* 889 */     if (null == listener) {
/* 890 */       throw new IllegalArgumentException(XSLMessages.createMessage("ER_ERRORLISTENER", null));
/*     */     }
/*     */     
/* 893 */     this.m_errorListener = listener;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/TransformerFactoryImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */