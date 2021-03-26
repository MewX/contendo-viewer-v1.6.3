/*     */ package org.apache.xalan.xsltc.trax;
/*     */ 
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Templates;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.URIResolver;
/*     */ import javax.xml.transform.sax.SAXTransformerFactory;
/*     */ import javax.xml.transform.sax.TemplatesHandler;
/*     */ import javax.xml.transform.sax.TransformerHandler;
/*     */ import org.xml.sax.XMLFilter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SmartTransformerFactoryImpl
/*     */   extends SAXTransformerFactory
/*     */ {
/*  52 */   private SAXTransformerFactory _xsltcFactory = null;
/*  53 */   private SAXTransformerFactory _xalanFactory = null;
/*  54 */   private SAXTransformerFactory _currFactory = null;
/*  55 */   private ErrorListener _errorlistener = null;
/*  56 */   private URIResolver _uriresolver = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createXSLTCTransformerFactory() {
/*  68 */     this._xsltcFactory = new TransformerFactoryImpl();
/*  69 */     this._currFactory = this._xsltcFactory;
/*     */   }
/*     */   
/*     */   private void createXalanTransformerFactory() {
/*  73 */     String xalanMessage = "org.apache.xalan.xsltc.trax.SmartTransformerFactoryImpl could not create an org.apache.xalan.processor.TransformerFactoryImpl.";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     try { Class xalanFactClass = ObjectFactory.findProviderClass("org.apache.xalan.processor.TransformerFactoryImpl", ObjectFactory.findClassLoader(), true);
/*     */ 
/*     */       
/*  82 */       this._xalanFactory = xalanFactClass.newInstance(); } catch (ClassNotFoundException e)
/*     */     
/*     */     { 
/*     */       
/*  86 */       System.err.println("org.apache.xalan.xsltc.trax.SmartTransformerFactoryImpl could not create an org.apache.xalan.processor.TransformerFactoryImpl."); } catch (InstantiationException e)
/*     */     
/*     */     { 
/*  89 */       System.err.println("org.apache.xalan.xsltc.trax.SmartTransformerFactoryImpl could not create an org.apache.xalan.processor.TransformerFactoryImpl."); } catch (IllegalAccessException e)
/*     */     
/*     */     { 
/*  92 */       System.err.println("org.apache.xalan.xsltc.trax.SmartTransformerFactoryImpl could not create an org.apache.xalan.processor.TransformerFactoryImpl."); }
/*     */     
/*  94 */     this._currFactory = this._xalanFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErrorListener(ErrorListener listener) throws IllegalArgumentException {
/* 100 */     this._errorlistener = listener;
/*     */   }
/*     */   
/*     */   public ErrorListener getErrorListener() {
/* 104 */     return this._errorlistener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getAttribute(String name) throws IllegalArgumentException {
/* 111 */     if (name.equals("translet-name") || name.equals("debug")) {
/* 112 */       if (this._xsltcFactory == null) {
/* 113 */         createXSLTCTransformerFactory();
/*     */       }
/* 115 */       return this._xsltcFactory.getAttribute(name);
/*     */     } 
/*     */     
/* 118 */     if (this._xalanFactory == null) {
/* 119 */       createXalanTransformerFactory();
/*     */     }
/* 121 */     return this._xalanFactory.getAttribute(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttribute(String name, Object value) throws IllegalArgumentException {
/* 128 */     if (name.equals("translet-name") || name.equals("debug")) {
/* 129 */       if (this._xsltcFactory == null) {
/* 130 */         createXSLTCTransformerFactory();
/*     */       }
/* 132 */       this._xsltcFactory.setAttribute(name, value);
/*     */     } else {
/*     */       
/* 135 */       if (this._xalanFactory == null) {
/* 136 */         createXalanTransformerFactory();
/*     */       }
/* 138 */       this._xalanFactory.setAttribute(name, value);
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
/*     */   public boolean getFeature(String name) {
/* 153 */     String[] features = { "http://javax.xml.transform.dom.DOMSource/feature", "http://javax.xml.transform.dom.DOMResult/feature", "http://javax.xml.transform.sax.SAXSource/feature", "http://javax.xml.transform.sax.SAXResult/feature", "http://javax.xml.transform.stream.StreamSource/feature", "http://javax.xml.transform.stream.StreamResult/feature" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     for (int i = 0; i < features.length; i++) {
/* 164 */       if (name.equals(features[i])) return true;
/*     */     
/*     */     } 
/*     */     
/* 168 */     return false;
/*     */   }
/*     */   
/*     */   public URIResolver getURIResolver() {
/* 172 */     return this._uriresolver;
/*     */   }
/*     */   
/*     */   public void setURIResolver(URIResolver resolver) {
/* 176 */     this._uriresolver = resolver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Source getAssociatedStylesheet(Source source, String media, String title, String charset) throws TransformerConfigurationException {
/* 183 */     if (this._currFactory == null) {
/* 184 */       createXSLTCTransformerFactory();
/*     */     }
/* 186 */     return this._currFactory.getAssociatedStylesheet(source, media, title, charset);
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
/*     */   public Transformer newTransformer() throws TransformerConfigurationException {
/* 198 */     if (this._xalanFactory == null) {
/* 199 */       createXalanTransformerFactory();
/*     */     }
/* 201 */     if (this._errorlistener != null) {
/* 202 */       this._xalanFactory.setErrorListener(this._errorlistener);
/*     */     }
/* 204 */     if (this._uriresolver != null) {
/* 205 */       this._xalanFactory.setURIResolver(this._uriresolver);
/*     */     }
/* 207 */     this._currFactory = this._xalanFactory;
/* 208 */     return this._currFactory.newTransformer();
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
/*     */   public Transformer newTransformer(Source source) throws TransformerConfigurationException {
/* 220 */     if (this._xalanFactory == null) {
/* 221 */       createXalanTransformerFactory();
/*     */     }
/* 223 */     if (this._errorlistener != null) {
/* 224 */       this._xalanFactory.setErrorListener(this._errorlistener);
/*     */     }
/* 226 */     if (this._uriresolver != null) {
/* 227 */       this._xalanFactory.setURIResolver(this._uriresolver);
/*     */     }
/* 229 */     this._currFactory = this._xalanFactory;
/* 230 */     return this._currFactory.newTransformer(source);
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
/*     */   public Templates newTemplates(Source source) throws TransformerConfigurationException {
/* 242 */     if (this._xsltcFactory == null) {
/* 243 */       createXSLTCTransformerFactory();
/*     */     }
/* 245 */     if (this._errorlistener != null) {
/* 246 */       this._xsltcFactory.setErrorListener(this._errorlistener);
/*     */     }
/* 248 */     if (this._uriresolver != null) {
/* 249 */       this._xsltcFactory.setURIResolver(this._uriresolver);
/*     */     }
/* 251 */     this._currFactory = this._xsltcFactory;
/* 252 */     return this._currFactory.newTemplates(source);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TemplatesHandler newTemplatesHandler() throws TransformerConfigurationException {
/* 263 */     if (this._xsltcFactory == null) {
/* 264 */       createXSLTCTransformerFactory();
/*     */     }
/* 266 */     if (this._errorlistener != null) {
/* 267 */       this._xsltcFactory.setErrorListener(this._errorlistener);
/*     */     }
/* 269 */     if (this._uriresolver != null) {
/* 270 */       this._xsltcFactory.setURIResolver(this._uriresolver);
/*     */     }
/* 272 */     return this._xsltcFactory.newTemplatesHandler();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformerHandler newTransformerHandler() throws TransformerConfigurationException {
/* 283 */     if (this._xalanFactory == null) {
/* 284 */       createXalanTransformerFactory();
/*     */     }
/* 286 */     if (this._errorlistener != null) {
/* 287 */       this._xalanFactory.setErrorListener(this._errorlistener);
/*     */     }
/* 289 */     if (this._uriresolver != null) {
/* 290 */       this._xalanFactory.setURIResolver(this._uriresolver);
/*     */     }
/* 292 */     return this._xalanFactory.newTransformerHandler();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformerHandler newTransformerHandler(Source src) throws TransformerConfigurationException {
/* 303 */     if (this._xalanFactory == null) {
/* 304 */       createXalanTransformerFactory();
/*     */     }
/* 306 */     if (this._errorlistener != null) {
/* 307 */       this._xalanFactory.setErrorListener(this._errorlistener);
/*     */     }
/* 309 */     if (this._uriresolver != null) {
/* 310 */       this._xalanFactory.setURIResolver(this._uriresolver);
/*     */     }
/* 312 */     return this._xalanFactory.newTransformerHandler(src);
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
/*     */   public TransformerHandler newTransformerHandler(Templates templates) throws TransformerConfigurationException {
/* 324 */     if (this._xsltcFactory == null) {
/* 325 */       createXSLTCTransformerFactory();
/*     */     }
/* 327 */     if (this._errorlistener != null) {
/* 328 */       this._xsltcFactory.setErrorListener(this._errorlistener);
/*     */     }
/* 330 */     if (this._uriresolver != null) {
/* 331 */       this._xsltcFactory.setURIResolver(this._uriresolver);
/*     */     }
/* 333 */     return this._xsltcFactory.newTransformerHandler(templates);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLFilter newXMLFilter(Source src) throws TransformerConfigurationException {
/* 344 */     if (this._xsltcFactory == null) {
/* 345 */       createXSLTCTransformerFactory();
/*     */     }
/* 347 */     if (this._errorlistener != null) {
/* 348 */       this._xsltcFactory.setErrorListener(this._errorlistener);
/*     */     }
/* 350 */     if (this._uriresolver != null) {
/* 351 */       this._xsltcFactory.setURIResolver(this._uriresolver);
/*     */     }
/* 353 */     Templates templates = this._xsltcFactory.newTemplates(src);
/* 354 */     if (templates == null) return null; 
/* 355 */     return newXMLFilter(templates);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLFilter newXMLFilter(Templates templates) throws TransformerConfigurationException {
/*     */     
/* 366 */     try { return new TrAXFilter(templates); } catch (TransformerConfigurationException e1)
/*     */     
/*     */     { 
/* 369 */       if (this._xsltcFactory == null) {
/* 370 */         createXSLTCTransformerFactory();
/*     */       }
/* 372 */       ErrorListener errorListener = this._xsltcFactory.getErrorListener();
/* 373 */       if (errorListener != null) {
/*     */         
/* 375 */         try { errorListener.fatalError(e1);
/* 376 */           return null; } catch (TransformerException e2)
/*     */         
/*     */         { 
/* 379 */           new TransformerConfigurationException(e2); }
/*     */       
/*     */       }
/* 382 */       throw e1; }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/SmartTransformerFactoryImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */