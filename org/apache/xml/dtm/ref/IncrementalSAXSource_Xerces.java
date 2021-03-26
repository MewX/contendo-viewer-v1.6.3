/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import org.apache.xerces.parsers.SAXParser;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ import org.apache.xml.serialize.XMLSerializer;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.DTDHandler;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXNotRecognizedException;
/*     */ import org.xml.sax.SAXNotSupportedException;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IncrementalSAXSource_Xerces
/*     */   implements IncrementalSAXSource
/*     */ {
/*  52 */   Method fParseSomeSetup = null;
/*  53 */   Method fParseSome = null;
/*  54 */   Object fPullParserConfig = null;
/*  55 */   Method fConfigSetInput = null;
/*  56 */   Method fConfigParse = null;
/*  57 */   Method fSetInputSource = null;
/*  58 */   Constructor fConfigInputSourceCtor = null;
/*  59 */   Method fConfigSetByteStream = null;
/*  60 */   Method fConfigSetCharStream = null;
/*  61 */   Method fConfigSetEncoding = null;
/*  62 */   Method fReset = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SAXParser fIncrementalParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean fParseInProgress = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IncrementalSAXSource_Xerces() throws NoSuchMethodException {
/*     */     
/*  98 */     try { Class xniConfigClass = ObjectFactory.findProviderClass("org.apache.xerces.xni.parser.XMLParserConfiguration", ObjectFactory.findClassLoader(), true);
/*     */ 
/*     */       
/* 101 */       Class[] args1 = { xniConfigClass };
/* 102 */       Constructor ctor = SAXParser.class.getConstructor(args1);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 107 */       Class xniStdConfigClass = ObjectFactory.findProviderClass("org.apache.xerces.parsers.StandardParserConfiguration", ObjectFactory.findClassLoader(), true);
/*     */ 
/*     */       
/* 110 */       this.fPullParserConfig = xniStdConfigClass.newInstance();
/* 111 */       Object[] args2 = { this.fPullParserConfig };
/* 112 */       this.fIncrementalParser = ctor.newInstance(args2);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       Class fXniInputSourceClass = ObjectFactory.findProviderClass("org.apache.xerces.xni.parser.XMLInputSource", ObjectFactory.findClassLoader(), true);
/*     */ 
/*     */       
/* 120 */       Class[] args3 = { fXniInputSourceClass };
/* 121 */       this.fConfigSetInput = xniStdConfigClass.getMethod("setInputSource", args3);
/*     */       
/* 123 */       Class[] args4 = { String.class, String.class, String.class };
/* 124 */       this.fConfigInputSourceCtor = fXniInputSourceClass.getConstructor(args4);
/* 125 */       Class[] args5 = { InputStream.class };
/* 126 */       this.fConfigSetByteStream = fXniInputSourceClass.getMethod("setByteStream", args5);
/* 127 */       Class[] args6 = { Reader.class };
/* 128 */       this.fConfigSetCharStream = fXniInputSourceClass.getMethod("setCharacterStream", args6);
/* 129 */       Class[] args7 = { String.class };
/* 130 */       this.fConfigSetEncoding = fXniInputSourceClass.getMethod("setEncoding", args7);
/*     */       
/* 132 */       Class[] argsb = { boolean.class };
/* 133 */       this.fConfigParse = xniStdConfigClass.getMethod("parse", argsb);
/* 134 */       Class[] noargs = new Class[0];
/* 135 */       this.fReset = this.fIncrementalParser.getClass().getMethod("reset", noargs); } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       IncrementalSAXSource_Xerces dummy = new IncrementalSAXSource_Xerces(new SAXParser());
/* 144 */       this.fParseSomeSetup = dummy.fParseSomeSetup;
/* 145 */       this.fParseSome = dummy.fParseSome;
/* 146 */       this.fIncrementalParser = dummy.fIncrementalParser; }
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
/*     */   public IncrementalSAXSource_Xerces(SAXParser parser) throws NoSuchMethodException {
/* 169 */     this.fIncrementalParser = parser;
/* 170 */     Class me = parser.getClass();
/* 171 */     Class[] parms = { InputSource.class };
/* 172 */     this.fParseSomeSetup = me.getMethod("parseSomeSetup", parms);
/* 173 */     parms = new Class[0];
/* 174 */     this.fParseSome = me.getMethod("parseSome", parms);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IncrementalSAXSource createIncrementalSAXSource() {
/*     */     
/* 186 */     try { return new IncrementalSAXSource_Xerces(); } catch (NoSuchMethodException e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */       
/* 192 */       IncrementalSAXSource_Filter iss = new IncrementalSAXSource_Filter();
/* 193 */       iss.setXMLReader((XMLReader)new SAXParser());
/* 194 */       return iss; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static IncrementalSAXSource createIncrementalSAXSource(SAXParser parser) {
/*     */     
/* 202 */     try { return new IncrementalSAXSource_Xerces(parser); } catch (NoSuchMethodException e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */       
/* 208 */       IncrementalSAXSource_Filter iss = new IncrementalSAXSource_Filter();
/* 209 */       iss.setXMLReader((XMLReader)parser);
/* 210 */       return iss; }
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
/*     */   public void setContentHandler(ContentHandler handler) {
/* 223 */     this.fIncrementalParser.setContentHandler(handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLexicalHandler(LexicalHandler handler) {
/*     */     
/* 234 */     try { this.fIncrementalParser.setProperty("http://xml.org/sax/properties/lexical-handler", handler); } catch (SAXNotRecognizedException e)
/*     */     
/*     */     {  }
/*     */     
/* 238 */     catch (SAXNotSupportedException sAXNotSupportedException) {}
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
/*     */   public void setDTDHandler(DTDHandler handler) {
/* 252 */     this.fIncrementalParser.setDTDHandler(handler);
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
/*     */   public void startParse(InputSource source) throws SAXException {
/* 264 */     if (this.fIncrementalParser == null)
/* 265 */       throw new SAXException(XMLMessages.createXMLMessage("ER_STARTPARSE_NEEDS_SAXPARSER", null)); 
/* 266 */     if (this.fParseInProgress) {
/* 267 */       throw new SAXException(XMLMessages.createXMLMessage("ER_STARTPARSE_WHILE_PARSING", null));
/*     */     }
/* 269 */     boolean ok = false;
/*     */ 
/*     */ 
/*     */     
/* 273 */     try { ok = parseSomeSetup(source); } catch (Exception ex)
/*     */     
/*     */     { 
/*     */       
/* 277 */       throw new SAXException(ex); }
/*     */ 
/*     */     
/* 280 */     if (!ok) {
/* 281 */       throw new SAXException(XMLMessages.createXMLMessage("ER_COULD_NOT_INIT_PARSER", null));
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
/*     */   
/*     */   public Object deliverMoreNodes(boolean parsemore) {
/*     */     Object object;
/* 299 */     if (!parsemore) {
/*     */       
/* 301 */       this.fParseInProgress = false;
/* 302 */       return Boolean.FALSE;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 307 */     try { boolean keepgoing = parseSome();
/* 308 */       object = keepgoing ? Boolean.TRUE : Boolean.FALSE; } catch (SAXException ex)
/*     */     
/* 310 */     { object = ex; } catch (IOException ex)
/*     */     
/* 312 */     { object = ex; } catch (Exception ex)
/*     */     
/* 314 */     { object = new SAXException(ex); }
/*     */     
/* 316 */     return object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean parseSomeSetup(InputSource source) throws SAXException, IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
/* 325 */     if (this.fConfigSetInput != null) {
/*     */ 
/*     */ 
/*     */       
/* 329 */       Object[] parms1 = { source.getPublicId(), source.getSystemId(), null };
/* 330 */       Object xmlsource = this.fConfigInputSourceCtor.newInstance(parms1);
/* 331 */       Object[] parmsa = { source.getByteStream() };
/* 332 */       this.fConfigSetByteStream.invoke(xmlsource, parmsa);
/* 333 */       parmsa[0] = source.getCharacterStream();
/* 334 */       this.fConfigSetCharStream.invoke(xmlsource, parmsa);
/* 335 */       parmsa[0] = source.getEncoding();
/* 336 */       this.fConfigSetEncoding.invoke(xmlsource, parmsa);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 342 */       Object[] noparms = new Object[0];
/* 343 */       this.fReset.invoke(this.fIncrementalParser, noparms);
/*     */       
/* 345 */       parmsa[0] = xmlsource;
/* 346 */       this.fConfigSetInput.invoke(this.fPullParserConfig, parmsa);
/*     */ 
/*     */       
/* 349 */       return parseSome();
/*     */     } 
/*     */ 
/*     */     
/* 353 */     Object[] parm = { source };
/* 354 */     Object ret = this.fParseSomeSetup.invoke(this.fIncrementalParser, parm);
/* 355 */     return ((Boolean)ret).booleanValue();
/*     */   }
/*     */ 
/*     */   
/* 359 */   static final Object[] noparms = new Object[0];
/* 360 */   static final Object[] parmsfalse = new Object[] { Boolean.FALSE };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean parseSome() throws SAXException, IOException, IllegalAccessException, InvocationTargetException {
/* 366 */     if (this.fConfigSetInput != null) {
/*     */       
/* 368 */       Object object = this.fConfigParse.invoke(this.fPullParserConfig, parmsfalse);
/* 369 */       return ((Boolean)object).booleanValue();
/*     */     } 
/*     */ 
/*     */     
/* 373 */     Object ret = this.fParseSome.invoke(this.fIncrementalParser, noparms);
/* 374 */     return ((Boolean)ret).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 385 */     System.out.println("Starting...");
/*     */     
/* 387 */     CoroutineManager co = new CoroutineManager();
/* 388 */     int appCoroutineID = co.co_joinCoroutineSet(-1);
/* 389 */     if (appCoroutineID == -1) {
/*     */       
/* 391 */       System.out.println("ERROR: Couldn't allocate coroutine number.\n");
/*     */       return;
/*     */     } 
/* 394 */     IncrementalSAXSource parser = createIncrementalSAXSource();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 399 */     XMLSerializer trace = new XMLSerializer(System.out, null);
/* 400 */     parser.setContentHandler((ContentHandler)trace);
/* 401 */     parser.setLexicalHandler((LexicalHandler)trace);
/*     */ 
/*     */ 
/*     */     
/* 405 */     for (int arg = 0; arg < args.length; arg++) {
/*     */ 
/*     */ 
/*     */       
/* 409 */       try { InputSource source = new InputSource(args[arg]);
/* 410 */         Object result = null;
/* 411 */         boolean more = true;
/* 412 */         parser.startParse(source);
/* 413 */         result = parser.deliverMoreNodes(more);
/* 414 */         for (; result == Boolean.TRUE; 
/* 415 */           result = parser.deliverMoreNodes(more)) {
/*     */           
/* 417 */           System.out.println("\nSome parsing successful, trying more.\n");
/*     */ 
/*     */           
/* 420 */           if (arg + 1 < args.length && "!".equals(args[arg + 1])) {
/*     */             
/* 422 */             arg++;
/* 423 */             more = false;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 428 */         if (result instanceof Boolean && (Boolean)result == Boolean.FALSE)
/*     */         
/* 430 */         { System.out.println("\nParser ended (EOF or on request).\n"); }
/*     */         
/* 432 */         else if (result == null)
/* 433 */         { System.out.println("\nUNEXPECTED: Parser says shut down prematurely.\n"); }
/*     */         
/* 435 */         else if (result instanceof Exception)
/* 436 */         { throw new WrappedRuntimeException((Exception)result); }  } catch (SAXException e)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 445 */         e.printStackTrace(); }
/*     */     
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/IncrementalSAXSource_Xerces.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */