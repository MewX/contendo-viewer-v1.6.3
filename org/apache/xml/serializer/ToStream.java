/*      */ package org.apache.xml.serializer;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.io.Writer;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.Properties;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.Transformer;
/*      */ import org.apache.xml.res.XMLMessages;
/*      */ import org.apache.xml.utils.BoolStack;
/*      */ import org.apache.xml.utils.DOM2Helper;
/*      */ import org.apache.xml.utils.DOMHelper;
/*      */ import org.apache.xml.utils.FastStringBuffer;
/*      */ import org.apache.xml.utils.TreeWalker;
/*      */ import org.apache.xml.utils.WrappedRuntimeException;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
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
/*      */ public abstract class ToStream
/*      */   extends SerializerBase
/*      */ {
/*      */   private static final String COMMENT_BEGIN = "<!--";
/*      */   private static final String COMMENT_END = "-->";
/*   59 */   protected BoolStack m_disableOutputEscapingStates = new BoolStack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean m_triedToGetConverter = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Method m_canConvertMeth;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   75 */   Object m_charToByteConverter = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   86 */   protected BoolStack m_preserves = new BoolStack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_ispreserve = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_isprevtext = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  112 */   protected int m_maxCharacter = Encodings.getLastPrintable();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  117 */   protected final char[] m_lineSep = System.getProperty("line.separator").toCharArray();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_lineSepUse = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  129 */   protected final int m_lineSepLen = this.m_lineSep.length;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected CharInfo m_charInfo;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean m_shouldFlush = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_spaceBeforeClose = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean m_startNewLine;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_inDoctype = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean m_isUTF8 = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Properties m_format;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_cdataStartCalled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_escaping;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void closeCDATA() throws SAXException {
/*      */     
/*  187 */     try { this.m_writer.write("]]>");
/*      */       
/*  189 */       this.m_cdataTagOpen = false; } catch (IOException e)
/*      */     
/*      */     { 
/*      */       
/*  193 */       throw new SAXException(e); }
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
/*      */   public void serialize(Node node) throws IOException {
/*      */     
/*  211 */     try { TreeWalker walker = new TreeWalker(this, (DOMHelper)new DOM2Helper());
/*      */ 
/*      */       
/*  214 */       walker.traverse(node); } catch (SAXException se)
/*      */     
/*      */     { 
/*      */       
/*  218 */       throw new WrappedRuntimeException(se); }
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
/*      */   static final boolean isUTF16Surrogate(char c) {
/*  231 */     return ((c & 0xFC00) == 55296);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ToStream() {
/*  237 */     this.m_escaping = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void flushWriter() throws SAXException {
/*  246 */     Writer writer = this.m_writer;
/*  247 */     if (null != writer) {
/*      */ 
/*      */       
/*      */       try { 
/*  251 */         if (writer instanceof WriterToUTF8Buffered)
/*      */         {
/*  253 */           if (this.m_shouldFlush) {
/*  254 */             ((WriterToUTF8Buffered)writer).flush();
/*      */           } else {
/*  256 */             ((WriterToUTF8Buffered)writer).flushBuffer();
/*      */           }  } 
/*  258 */         if (writer instanceof WriterToASCI)
/*      */         
/*  260 */         { if (this.m_shouldFlush) {
/*  261 */             writer.flush();
/*      */           
/*      */           }
/*      */            }
/*      */         
/*      */         else
/*      */         
/*  268 */         { writer.flush(); }  } catch (IOException ioe)
/*      */       
/*      */       { 
/*      */ 
/*      */         
/*  273 */         throw new SAXException(ioe); }
/*      */     
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
/*      */   public OutputStream getOutputStream() {
/*  287 */     if (this.m_writer instanceof WriterToUTF8Buffered)
/*  288 */       return ((WriterToUTF8Buffered)this.m_writer).getOutputStream(); 
/*  289 */     if (this.m_writer instanceof WriterToASCI) {
/*  290 */       return ((WriterToASCI)this.m_writer).getOutputStream();
/*      */     }
/*  292 */     return null;
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
/*      */   public void elementDecl(String name, String model) throws SAXException {
/*  313 */     if (this.m_inExternalDTD) {
/*      */       return;
/*      */     }
/*      */     
/*  317 */     try { Writer writer = this.m_writer;
/*  318 */       if (this.m_needToOutputDocTypeDecl) {
/*      */         
/*  320 */         outputDocTypeDecl(this.m_elemContext.m_elementName, false);
/*  321 */         this.m_needToOutputDocTypeDecl = false;
/*      */       } 
/*  323 */       if (this.m_inDoctype) {
/*      */         
/*  325 */         writer.write(" [");
/*  326 */         writer.write(this.m_lineSep, 0, this.m_lineSepLen);
/*      */         
/*  328 */         this.m_inDoctype = false;
/*      */       } 
/*      */       
/*  331 */       writer.write("<!ELEMENT ");
/*  332 */       writer.write(name);
/*  333 */       writer.write(32);
/*  334 */       writer.write(model);
/*  335 */       writer.write(62);
/*  336 */       writer.write(this.m_lineSep, 0, this.m_lineSepLen); } catch (IOException e)
/*      */     
/*      */     { 
/*      */       
/*  340 */       throw new SAXException(e); }
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
/*      */   
/*      */   public void internalEntityDecl(String name, String value) throws SAXException {
/*  362 */     if (this.m_inExternalDTD) {
/*      */       return;
/*      */     }
/*      */     
/*  366 */     try { if (this.m_needToOutputDocTypeDecl) {
/*      */         
/*  368 */         outputDocTypeDecl(this.m_elemContext.m_elementName, false);
/*  369 */         this.m_needToOutputDocTypeDecl = false;
/*      */       } 
/*  371 */       if (this.m_inDoctype) {
/*      */         
/*  373 */         Writer writer = this.m_writer;
/*  374 */         writer.write(" [");
/*  375 */         writer.write(this.m_lineSep, 0, this.m_lineSepLen);
/*      */         
/*  377 */         this.m_inDoctype = false;
/*      */       } 
/*      */       
/*  380 */       outputEntityDecl(name, value); } catch (IOException e)
/*      */     
/*      */     { 
/*      */       
/*  384 */       throw new SAXException(e); }
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
/*      */   void outputEntityDecl(String name, String value) throws IOException {
/*  399 */     Writer writer = this.m_writer;
/*  400 */     writer.write("<!ENTITY ");
/*  401 */     writer.write(name);
/*  402 */     writer.write(" \"");
/*  403 */     writer.write(value);
/*  404 */     writer.write("\">");
/*  405 */     writer.write(this.m_lineSep, 0, this.m_lineSepLen);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void outputLineSep() throws IOException {
/*  416 */     this.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
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
/*  431 */     boolean shouldFlush = this.m_shouldFlush;
/*      */     
/*  433 */     init(this.m_writer, format, false, false);
/*      */     
/*  435 */     this.m_shouldFlush = shouldFlush;
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
/*      */   private synchronized void init(Writer writer, Properties format, boolean defaultProperties, boolean shouldFlush) {
/*  453 */     this.m_shouldFlush = shouldFlush;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  458 */     if (this.m_tracer != null && !(writer instanceof SerializerTraceWriter)) {
/*      */       
/*  460 */       this.m_writer = new SerializerTraceWriter(writer, this.m_tracer);
/*      */     } else {
/*  462 */       this.m_writer = writer;
/*      */     } 
/*      */     
/*  465 */     this.m_format = format;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  470 */     setCdataSectionElements("cdata-section-elements", format);
/*      */     
/*  472 */     setIndentAmount(OutputPropertyUtils.getIntProperty("{http://xml.apache.org/xalan}indent-amount", format));
/*      */ 
/*      */ 
/*      */     
/*  476 */     setIndent(OutputPropertyUtils.getBooleanProperty("indent", format));
/*      */ 
/*      */     
/*  479 */     boolean shouldNotWriteXMLHeader = OutputPropertyUtils.getBooleanProperty("omit-xml-declaration", format);
/*      */ 
/*      */ 
/*      */     
/*  483 */     setOmitXMLDeclaration(shouldNotWriteXMLHeader);
/*  484 */     setDoctypeSystem(format.getProperty("doctype-system"));
/*  485 */     String doctypePublic = format.getProperty("doctype-public");
/*  486 */     setDoctypePublic(doctypePublic);
/*      */ 
/*      */     
/*  489 */     if (format.get("standalone") != null) {
/*      */       
/*  491 */       String val = format.getProperty("standalone");
/*  492 */       if (defaultProperties) {
/*  493 */         setStandaloneInternal(val);
/*      */       } else {
/*  495 */         setStandalone(val);
/*      */       } 
/*      */     } 
/*  498 */     setMediaType(format.getProperty("media-type"));
/*      */     
/*  500 */     if (null != doctypePublic)
/*      */     {
/*  502 */       if (doctypePublic.startsWith("-//W3C//DTD XHTML")) {
/*  503 */         this.m_spaceBeforeClose = true;
/*      */       }
/*      */     }
/*      */     
/*  507 */     String encoding = getEncoding();
/*  508 */     if (null == encoding) {
/*      */       
/*  510 */       encoding = Encodings.getMimeEncoding(format.getProperty("encoding"));
/*      */ 
/*      */       
/*  513 */       setEncoding(encoding);
/*      */     } 
/*      */     
/*  516 */     this.m_isUTF8 = encoding.equals("UTF-8");
/*  517 */     this.m_maxCharacter = Encodings.getLastPrintable(encoding);
/*      */ 
/*      */ 
/*      */     
/*  521 */     String entitiesFileName = (String)format.get("{http://xml.apache.org/xalan}entities");
/*      */ 
/*      */     
/*  524 */     if (null != entitiesFileName) {
/*      */ 
/*      */       
/*  527 */       String method = (String)format.get("method");
/*      */ 
/*      */       
/*  530 */       this.m_charInfo = CharInfo.getCharInfo(entitiesFileName, method);
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
/*      */   private synchronized void init(Writer writer, Properties format) {
/*  544 */     init(writer, format, false, false);
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
/*      */   protected synchronized void init(OutputStream output, Properties format, boolean defaultProperties) throws UnsupportedEncodingException {
/*  565 */     String encoding = getEncoding();
/*  566 */     if (encoding == null) {
/*      */ 
/*      */       
/*  569 */       encoding = Encodings.getMimeEncoding(format.getProperty("encoding"));
/*      */ 
/*      */       
/*  572 */       setEncoding(encoding);
/*      */     } 
/*      */     
/*  575 */     if (encoding.equalsIgnoreCase("UTF-8")) {
/*      */       
/*  577 */       this.m_isUTF8 = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  594 */       init(new WriterToUTF8Buffered(output), format, defaultProperties, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  602 */     else if (encoding.equals("WINDOWS-1250") || encoding.equals("US-ASCII") || encoding.equals("ASCII")) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  607 */       init(new WriterToASCI(output), format, defaultProperties, true);
/*      */     } else {
/*      */       Writer writer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  615 */       try { writer = Encodings.getWriter(output, encoding); } catch (UnsupportedEncodingException uee)
/*      */       
/*      */       { 
/*      */         
/*  619 */         System.out.println("Warning: encoding \"" + encoding + "\" not supported" + ", using " + "UTF-8");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  626 */         encoding = "UTF-8";
/*  627 */         setEncoding(encoding);
/*  628 */         writer = Encodings.getWriter(output, encoding); }
/*      */ 
/*      */       
/*  631 */       this.m_maxCharacter = Encodings.getLastPrintable(encoding);
/*      */       
/*  633 */       init(writer, format, defaultProperties, true);
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
/*      */   public Properties getOutputFormat() {
/*  645 */     return this.m_format;
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
/*      */   public void setWriter(Writer writer) {
/*  659 */     if (this.m_tracer != null && !(writer instanceof SerializerTraceWriter)) {
/*      */       
/*  661 */       this.m_writer = new SerializerTraceWriter(writer, this.m_tracer);
/*      */     } else {
/*  663 */       this.m_writer = writer;
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
/*      */   public boolean setLineSepUse(boolean use_sytem_line_break) {
/*  680 */     boolean oldValue = this.m_lineSepUse;
/*  681 */     this.m_lineSepUse = use_sytem_line_break;
/*  682 */     return oldValue;
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
/*  702 */       if (null == this.m_format) {
/*  703 */         properties = OutputPropertiesFactory.getDefaultMethodProperties("xml");
/*      */       }
/*      */       else {
/*      */         
/*  707 */         properties = this.m_format;
/*  708 */       }  init(output, properties, true); } catch (UnsupportedEncodingException unsupportedEncodingException) {}
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
/*      */   public boolean setEscaping(boolean escape) {
/*  722 */     boolean temp = this.m_escaping;
/*  723 */     this.m_escaping = escape;
/*  724 */     return temp;
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
/*      */   protected void indent(int depth) throws IOException {
/*  740 */     if (this.m_startNewLine) {
/*  741 */       outputLineSep();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  746 */     if (this.m_indentAmount > 0) {
/*  747 */       printSpace(depth * this.m_indentAmount);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void indent() throws IOException {
/*  757 */     indent(this.m_elemContext.m_currentElemDepth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void printSpace(int n) throws IOException {
/*  768 */     Writer writer = this.m_writer;
/*  769 */     for (int i = 0; i < n; i++)
/*      */     {
/*  771 */       writer.write(32);
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
/*      */   public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException {
/*  804 */     if (this.m_inExternalDTD) {
/*      */       return;
/*      */     }
/*      */     
/*  808 */     try { Writer writer = this.m_writer;
/*  809 */       if (this.m_needToOutputDocTypeDecl) {
/*      */         
/*  811 */         outputDocTypeDecl(this.m_elemContext.m_elementName, false);
/*  812 */         this.m_needToOutputDocTypeDecl = false;
/*      */       } 
/*  814 */       if (this.m_inDoctype) {
/*      */         
/*  816 */         writer.write(" [");
/*  817 */         writer.write(this.m_lineSep, 0, this.m_lineSepLen);
/*      */         
/*  819 */         this.m_inDoctype = false;
/*      */       } 
/*      */       
/*  822 */       writer.write("<!ATTLIST ");
/*  823 */       writer.write(eName);
/*  824 */       writer.write(32);
/*      */       
/*  826 */       writer.write(aName);
/*  827 */       writer.write(32);
/*  828 */       writer.write(type);
/*  829 */       if (valueDefault != null) {
/*      */         
/*  831 */         writer.write(32);
/*  832 */         writer.write(valueDefault);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  837 */       writer.write(62);
/*  838 */       writer.write(this.m_lineSep, 0, this.m_lineSepLen); } catch (IOException e)
/*      */     
/*      */     { 
/*      */       
/*  842 */       throw new SAXException(e); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Writer getWriter() {
/*  853 */     return this.m_writer;
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
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean escapingNotNeeded(char ch) {
/*  884 */     if (ch < '') {
/*      */       
/*  886 */       if (ch >= ' ' || '\n' == ch || '\r' == ch || '\t' == ch) {
/*  887 */         return true;
/*      */       }
/*  889 */       return false;
/*      */     } 
/*      */     
/*  892 */     if (null == this.m_charToByteConverter && false == this.m_triedToGetConverter) {
/*      */       
/*  894 */       this.m_triedToGetConverter = true;
/*      */ 
/*      */       
/*  897 */       try { this.m_charToByteConverter = Encodings.getCharToByteConverter(getEncoding());
/*      */         
/*  899 */         if (null != this.m_charToByteConverter)
/*      */         
/*  901 */         { Class[] argsTypes = new Class[1];
/*  902 */           argsTypes[0] = char.class;
/*  903 */           Class convClass = this.m_charToByteConverter.getClass();
/*  904 */           this.m_canConvertMeth = convClass.getMethod("canConvert", argsTypes); }  } catch (Exception e)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  911 */         System.err.println("Warning: " + e.getMessage()); }
/*      */     
/*      */     } 
/*  914 */     if (null != this.m_charToByteConverter) {
/*      */ 
/*      */       
/*      */       try { 
/*  918 */         Object[] args = new Object[1];
/*  919 */         args[0] = new Character(ch);
/*  920 */         Boolean bool = (Boolean)this.m_canConvertMeth.invoke(this.m_charToByteConverter, args);
/*      */ 
/*      */ 
/*      */         
/*  924 */         return bool.booleanValue() ? (!Character.isISOControl(ch)) : false; } catch (InvocationTargetException ite)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  931 */         System.err.println("Warning: InvocationTargetException in canConvert!"); } catch (IllegalAccessException iae)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  937 */         System.err.println("Warning: IllegalAccessException in canConvert!"); }
/*      */     
/*      */     }
/*      */ 
/*      */     
/*  942 */     return (ch <= this.m_maxCharacter);
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
/*      */   protected void writeUTF16Surrogate(char c, char[] ch, int i, int end) throws IOException {
/*  961 */     int surrogateValue = getURF16SurrogateValue(c, ch, i, end);
/*      */     
/*  963 */     Writer writer = this.m_writer;
/*  964 */     writer.write(38);
/*  965 */     writer.write(35);
/*      */ 
/*      */     
/*  968 */     writer.write(Integer.toString(surrogateValue));
/*  969 */     writer.write(59);
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
/*      */   int getURF16SurrogateValue(char c, char[] ch, int i, int end) throws IOException {
/*  989 */     if (i + 1 >= end)
/*      */     {
/*  991 */       throw new IOException(XMLMessages.createXMLMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(c) }));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1001 */     int next = ch[++i];
/*      */     
/* 1003 */     if (56320 > next || next >= 57344) {
/* 1004 */       throw new IOException(XMLMessages.createXMLMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(c) + " " + Integer.toHexString(next) }));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1014 */     next = (c - 55296 << 10) + next - 56320 + 65536;
/*      */ 
/*      */     
/* 1017 */     return next;
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
/*      */   protected int accumDefaultEntity(Writer writer, char ch, int i, char[] chars, int len, boolean fromTextNode, boolean escLF) throws IOException {
/* 1047 */     if (!escLF && '\n' == ch) {
/*      */       
/* 1049 */       writer.write(this.m_lineSep, 0, this.m_lineSepLen);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1055 */     else if ((fromTextNode && this.m_charInfo.isSpecialTextChar(ch)) || (!fromTextNode && this.m_charInfo.isSpecialAttrChar(ch))) {
/*      */       
/* 1057 */       String entityRef = this.m_charInfo.getEntityNameForChar(ch);
/*      */       
/* 1059 */       if (null != entityRef) {
/*      */         
/* 1061 */         writer.write(38);
/* 1062 */         writer.write(entityRef);
/* 1063 */         writer.write(59);
/*      */       } else {
/*      */         
/* 1066 */         return i;
/*      */       } 
/*      */     } else {
/* 1069 */       return i;
/*      */     } 
/*      */     
/* 1072 */     return i + 1;
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
/*      */   void writeNormalizedChars(char[] ch, int start, int length, boolean isCData, boolean useSystemLineSeparator) throws IOException, SAXException {
/* 1096 */     Writer writer = this.m_writer;
/* 1097 */     int end = start + length;
/*      */     
/* 1099 */     for (int i = start; i < end; i++) {
/*      */       
/* 1101 */       char c = ch[i];
/*      */       
/* 1103 */       if ('\n' == c && useSystemLineSeparator) {
/*      */         
/* 1105 */         writer.write(this.m_lineSep, 0, this.m_lineSepLen);
/*      */       }
/* 1107 */       else if (isCData && !escapingNotNeeded(c)) {
/*      */ 
/*      */         
/* 1110 */         if (this.m_cdataTagOpen) {
/* 1111 */           closeCDATA();
/*      */         }
/*      */         
/* 1114 */         if (isUTF16Surrogate(c))
/*      */         {
/* 1116 */           writeUTF16Surrogate(c, ch, i, end);
/* 1117 */           i++;
/*      */         }
/*      */         else
/*      */         {
/* 1121 */           writer.write("&#");
/*      */           
/* 1123 */           String intStr = Integer.toString(c);
/*      */           
/* 1125 */           writer.write(intStr);
/* 1126 */           writer.write(59);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1136 */       else if (isCData && i < end - 2 && ']' == c && ']' == ch[i + 1] && '>' == ch[i + 2]) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1143 */         writer.write("]]]]><![CDATA[>");
/*      */         
/* 1145 */         i += 2;
/*      */ 
/*      */       
/*      */       }
/* 1149 */       else if (escapingNotNeeded(c)) {
/*      */         
/* 1151 */         if (isCData && !this.m_cdataTagOpen) {
/*      */           
/* 1153 */           writer.write("<![CDATA[");
/* 1154 */           this.m_cdataTagOpen = true;
/*      */         } 
/* 1156 */         writer.write(c);
/*      */ 
/*      */       
/*      */       }
/* 1160 */       else if (isUTF16Surrogate(c)) {
/*      */         
/* 1162 */         if (this.m_cdataTagOpen)
/* 1163 */           closeCDATA(); 
/* 1164 */         writeUTF16Surrogate(c, ch, i, end);
/* 1165 */         i++;
/*      */       }
/*      */       else {
/*      */         
/* 1169 */         if (this.m_cdataTagOpen)
/* 1170 */           closeCDATA(); 
/* 1171 */         writer.write("&#");
/*      */         
/* 1173 */         String intStr = Integer.toString(c);
/*      */         
/* 1175 */         writer.write(intStr);
/* 1176 */         writer.write(59);
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
/*      */   public void endNonEscaping() throws SAXException {
/* 1192 */     this.m_disableOutputEscapingStates.pop();
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
/*      */   public void startNonEscaping() throws SAXException {
/* 1207 */     this.m_disableOutputEscapingStates.push(true);
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
/*      */   protected void cdata(char[] ch, int start, int length) throws SAXException {
/*      */     
/* 1243 */     try { int old_start = start;
/* 1244 */       if (this.m_elemContext.m_startTagOpen) {
/*      */         
/* 1246 */         closeStartTag();
/* 1247 */         this.m_elemContext.m_startTagOpen = false;
/*      */       } 
/* 1249 */       this.m_ispreserve = true;
/*      */       
/* 1251 */       if (shouldIndent()) {
/* 1252 */         indent();
/*      */       }
/* 1254 */       boolean writeCDataBrackets = (length >= 1 && escapingNotNeeded(ch[start]));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1261 */       if (writeCDataBrackets && !this.m_cdataTagOpen) {
/*      */         
/* 1263 */         this.m_writer.write("<![CDATA[");
/* 1264 */         this.m_cdataTagOpen = true;
/*      */       } 
/*      */ 
/*      */       
/* 1268 */       if (isEscapingDisabled()) {
/*      */         
/* 1270 */         charactersRaw(ch, start, length);
/*      */       } else {
/*      */         
/* 1273 */         writeNormalizedChars(ch, start, length, true, this.m_lineSepUse);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1279 */       if (writeCDataBrackets)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1286 */         if (ch[start + length - 1] == ']') {
/* 1287 */           closeCDATA();
/*      */         }
/*      */       }
/*      */       
/* 1291 */       if (this.m_tracer != null)
/* 1292 */         fireCDATAEvent(ch, old_start, length);  } catch (IOException ioe)
/*      */     
/*      */     { 
/*      */       
/* 1296 */       throw new SAXException(XMLMessages.createXMLMessage("ER_OIERROR", null), ioe); }
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
/*      */   private boolean isEscapingDisabled() {
/* 1312 */     return this.m_disableOutputEscapingStates.peekOrFalse();
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
/*      */   protected void charactersRaw(char[] ch, int start, int length) throws SAXException {
/* 1329 */     if (this.m_inEntityRef) {
/*      */       return;
/*      */     }
/*      */     
/* 1333 */     try { if (this.m_elemContext.m_startTagOpen) {
/*      */         
/* 1335 */         closeStartTag();
/* 1336 */         this.m_elemContext.m_startTagOpen = false;
/*      */       } 
/*      */       
/* 1339 */       this.m_ispreserve = true;
/*      */       
/* 1341 */       this.m_writer.write(ch, start, length); } catch (IOException e)
/*      */     
/*      */     { 
/*      */       
/* 1345 */       throw new SAXException(e); }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void characters(char[] chars, int start, int length) throws SAXException {
/* 1380 */     if (this.m_elemContext.m_startTagOpen) {
/*      */       
/* 1382 */       closeStartTag();
/* 1383 */       this.m_elemContext.m_startTagOpen = false;
/*      */     }
/* 1385 */     else if (this.m_needToCallStartDocument) {
/*      */       
/* 1387 */       startDocumentInternal();
/*      */     } 
/*      */     
/* 1390 */     if (this.m_cdataStartCalled || this.m_elemContext.m_isCdataSection) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1395 */       cdata(chars, start, length);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1400 */     if (this.m_cdataTagOpen) {
/* 1401 */       closeCDATA();
/*      */     }
/*      */     
/* 1404 */     if (this.m_disableOutputEscapingStates.peekOrFalse() || !this.m_escaping) {
/*      */       
/* 1406 */       charactersRaw(chars, start, length);
/*      */ 
/*      */       
/* 1409 */       if (this.m_tracer != null) {
/* 1410 */         fireCharEvent(chars, start, length);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1415 */     if (this.m_elemContext.m_startTagOpen) {
/*      */       
/* 1417 */       closeStartTag();
/* 1418 */       this.m_elemContext.m_startTagOpen = false;
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
/* 1431 */     try { int end = start + length;
/* 1432 */       int lastDirty = start - 1;
/* 1433 */       int i = start;
/*      */       char ch1;
/* 1435 */       for (; i < end && ((ch1 = chars[i]) == ' ' || (ch1 == '\n' && this.m_lineSepUse) || ch1 == '\r' || ch1 == '\t'); 
/*      */ 
/*      */ 
/*      */         
/* 1439 */         i++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1446 */         if (!this.m_charInfo.isTextASCIIClean(ch1)) {
/*      */           
/* 1448 */           lastDirty = processDirty(chars, end, i, ch1, lastDirty, true);
/* 1449 */           i = lastDirty;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1455 */       if (i < end) {
/* 1456 */         this.m_ispreserve = true;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1463 */       for (; i < end; i++) {
/*      */         char ch2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1472 */         while (i < end && (ch2 = chars[i]) < '' && this.m_charInfo.isTextASCIIClean(ch2))
/* 1473 */           i++; 
/* 1474 */         if (i == end) {
/*      */           break;
/*      */         }
/*      */         
/* 1478 */         char ch = chars[i];
/* 1479 */         if ((!escapingNotNeeded(ch) || this.m_charInfo.isSpecialTextChar(ch)) && '"' != ch) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1488 */           lastDirty = processDirty(chars, end, i, ch, lastDirty, true);
/* 1489 */           i = lastDirty;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1495 */       int startClean = lastDirty + 1;
/* 1496 */       if (i > startClean) {
/*      */         
/* 1498 */         int lengthClean = i - startClean;
/* 1499 */         this.m_writer.write(chars, startClean, lengthClean);
/*      */       } 
/*      */ 
/*      */       
/* 1503 */       this.m_isprevtext = true; } catch (IOException e)
/*      */     
/*      */     { 
/*      */       
/* 1507 */       throw new SAXException(e); }
/*      */ 
/*      */ 
/*      */     
/* 1511 */     if (this.m_tracer != null) {
/* 1512 */       fireCharEvent(chars, start, length);
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
/*      */   private int processDirty(char[] chars, int end, int i, char ch, int lastDirty, boolean fromTextNode) throws IOException {
/* 1535 */     int startClean = lastDirty + 1;
/*      */ 
/*      */     
/* 1538 */     if (i > startClean) {
/*      */       
/* 1540 */       int lengthClean = i - startClean;
/* 1541 */       this.m_writer.write(chars, startClean, lengthClean);
/*      */     } 
/*      */ 
/*      */     
/* 1545 */     if ('\n' == ch && fromTextNode) {
/*      */       
/* 1547 */       this.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
/*      */     }
/*      */     else {
/*      */       
/* 1551 */       startClean = accumDefaultEscape(this.m_writer, ch, i, chars, end, fromTextNode, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1560 */       i = startClean - 1;
/*      */     } 
/*      */ 
/*      */     
/* 1564 */     return i;
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
/*      */   public void characters(String s) throws SAXException {
/* 1576 */     int length = s.length();
/* 1577 */     if (length > this.m_charsBuff.length)
/*      */     {
/* 1579 */       this.m_charsBuff = new char[length * 2 + 1];
/*      */     }
/* 1581 */     s.getChars(0, length, this.m_charsBuff, 0);
/* 1582 */     characters(this.m_charsBuff, 0, length);
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
/*      */   protected int accumDefaultEscape(Writer writer, char ch, int i, char[] chars, int len, boolean fromTextNode, boolean escLF) throws IOException {
/* 1613 */     int pos = accumDefaultEntity(writer, ch, i, chars, len, fromTextNode, escLF);
/*      */     
/* 1615 */     if (i == pos)
/*      */     {
/* 1617 */       if ('?' <= ch && ch < '?') {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1623 */         if (i + 1 >= len)
/*      */         {
/* 1625 */           throw new IOException(XMLMessages.createXMLMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(ch) }));
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1635 */         int next = chars[++i];
/*      */         
/* 1637 */         if (56320 > next || next >= 57344) {
/* 1638 */           throw new IOException(XMLMessages.createXMLMessage("ER_INVALID_UTF16_SURROGATE", new Object[] { Integer.toHexString(ch) + " " + Integer.toHexString(next) }));
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1649 */         next = (ch - 55296 << 10) + next - 56320 + 65536;
/*      */ 
/*      */         
/* 1652 */         writer.write("&#");
/* 1653 */         writer.write(Integer.toString(next));
/* 1654 */         writer.write(59);
/* 1655 */         pos += 2;
/*      */       }
/*      */       else {
/*      */         
/* 1659 */         if (!escapingNotNeeded(ch) || (fromTextNode && this.m_charInfo.isSpecialTextChar(ch)) || (!fromTextNode && this.m_charInfo.isSpecialAttrChar(ch))) {
/*      */ 
/*      */ 
/*      */           
/* 1663 */           writer.write("&#");
/* 1664 */           writer.write(Integer.toString(ch));
/* 1665 */           writer.write(59);
/*      */         }
/*      */         else {
/*      */           
/* 1669 */           writer.write(ch);
/*      */         } 
/* 1671 */         pos++;
/*      */       } 
/*      */     }
/*      */     
/* 1675 */     return pos;
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
/*      */   public void startElement(String namespaceURI, String localName, String name, Attributes atts) throws SAXException {
/* 1707 */     if (this.m_inEntityRef) {
/*      */       return;
/*      */     }
/* 1710 */     if (this.m_needToCallStartDocument) {
/*      */       
/* 1712 */       startDocumentInternal();
/* 1713 */       this.m_needToCallStartDocument = false;
/*      */     }
/* 1715 */     else if (this.m_cdataTagOpen) {
/* 1716 */       closeCDATA();
/*      */     } 
/*      */     
/* 1719 */     try { if (true == this.m_needToOutputDocTypeDecl && null != getDoctypeSystem())
/*      */       {
/*      */         
/* 1722 */         outputDocTypeDecl(name, true);
/*      */       }
/*      */       
/* 1725 */       this.m_needToOutputDocTypeDecl = false;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1730 */       if (this.m_elemContext.m_startTagOpen) {
/*      */         
/* 1732 */         closeStartTag();
/* 1733 */         this.m_elemContext.m_startTagOpen = false;
/*      */       } 
/*      */       
/* 1736 */       if (namespaceURI != null) {
/* 1737 */         ensurePrefixIsDeclared(namespaceURI, name);
/*      */       }
/* 1739 */       this.m_ispreserve = false;
/*      */       
/* 1741 */       if (shouldIndent() && this.m_startNewLine)
/*      */       {
/* 1743 */         indent();
/*      */       }
/*      */       
/* 1746 */       this.m_startNewLine = true;
/*      */       
/* 1748 */       Writer writer = this.m_writer;
/* 1749 */       writer.write(60);
/* 1750 */       writer.write(name); } catch (IOException e)
/*      */     
/*      */     { 
/*      */       
/* 1754 */       throw new SAXException(e); }
/*      */ 
/*      */ 
/*      */     
/* 1758 */     if (atts != null) {
/* 1759 */       addAttributes(atts);
/*      */     }
/* 1761 */     this.m_elemContext = this.m_elemContext.push(namespaceURI, localName, name);
/* 1762 */     this.m_isprevtext = false;
/*      */     
/* 1764 */     if (this.m_tracer != null) {
/* 1765 */       firePseudoAttributes();
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
/*      */   public void startElement(String elementNamespaceURI, String elementLocalName, String elementName) throws SAXException {
/* 1795 */     startElement(elementNamespaceURI, elementLocalName, elementName, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void startElement(String elementName) throws SAXException {
/* 1800 */     startElement(null, null, elementName, null);
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
/*      */   void outputDocTypeDecl(String name, boolean closeDecl) throws SAXException {
/* 1813 */     if (this.m_cdataTagOpen) {
/* 1814 */       closeCDATA();
/*      */     }
/*      */     
/* 1817 */     try { Writer writer = this.m_writer;
/* 1818 */       writer.write("<!DOCTYPE ");
/* 1819 */       writer.write(name);
/*      */       
/* 1821 */       String doctypePublic = getDoctypePublic();
/* 1822 */       if (null != doctypePublic) {
/*      */         
/* 1824 */         writer.write(" PUBLIC \"");
/* 1825 */         writer.write(doctypePublic);
/* 1826 */         writer.write(34);
/*      */       } 
/*      */       
/* 1829 */       String doctypeSystem = getDoctypeSystem();
/* 1830 */       if (null != doctypeSystem) {
/*      */         
/* 1832 */         if (null == doctypePublic) {
/* 1833 */           writer.write(" SYSTEM \"");
/*      */         } else {
/* 1835 */           writer.write(" \"");
/*      */         } 
/* 1837 */         writer.write(doctypeSystem);
/*      */         
/* 1839 */         if (closeDecl) {
/*      */           
/* 1841 */           writer.write("\">");
/* 1842 */           writer.write(this.m_lineSep, 0, this.m_lineSepLen);
/* 1843 */           closeDecl = false;
/*      */         } else {
/*      */           
/* 1846 */           writer.write(34);
/*      */         } 
/* 1848 */       }  boolean dothis = false;
/* 1849 */       if (dothis)
/*      */       {
/*      */ 
/*      */         
/* 1853 */         if (closeDecl)
/*      */         
/* 1855 */         { writer.write(62);
/* 1856 */           writer.write(this.m_lineSep, 0, this.m_lineSepLen); }  }  } catch (IOException e)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */       
/* 1862 */       throw new SAXException(e); }
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
/*      */ 
/*      */ 
/*      */   
/*      */   public void processAttributes(Writer writer, int nAttrs) throws IOException, SAXException {
/* 1886 */     String encoding = getEncoding();
/* 1887 */     for (int i = 0; i < nAttrs; i++) {
/*      */ 
/*      */       
/* 1890 */       String name = this.m_attributes.getQName(i);
/* 1891 */       String value = this.m_attributes.getValue(i);
/* 1892 */       writer.write(32);
/* 1893 */       writer.write(name);
/* 1894 */       writer.write("=\"");
/* 1895 */       writeAttrString(writer, value, encoding);
/* 1896 */       writer.write(34);
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
/*      */   public void writeAttrString(Writer writer, String string, String encoding) throws IOException {
/* 1915 */     int len = string.length();
/* 1916 */     if (len > this.m_attrBuff.length)
/*      */     {
/* 1918 */       this.m_attrBuff = new char[len * 2 + 1];
/*      */     }
/* 1920 */     string.getChars(0, len, this.m_attrBuff, 0);
/* 1921 */     char[] stringChars = this.m_attrBuff;
/*      */     
/* 1923 */     for (int i = 0; i < len; i++) {
/*      */       
/* 1925 */       char ch = stringChars[i];
/* 1926 */       if (escapingNotNeeded(ch) && !this.m_charInfo.isSpecialAttrChar(ch)) {
/*      */         
/* 1928 */         writer.write(ch);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1940 */         accumDefaultEscape(writer, ch, i, stringChars, len, false, true);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(String namespaceURI, String localName, String name) throws SAXException {
/* 1965 */     if (this.m_inEntityRef) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1970 */     this.m_prefixMap.popNamespaces(this.m_elemContext.m_currentElemDepth, null);
/*      */ 
/*      */ 
/*      */     
/* 1974 */     try { Writer writer = this.m_writer;
/* 1975 */       if (this.m_elemContext.m_startTagOpen)
/*      */       
/* 1977 */       { if (this.m_tracer != null)
/* 1978 */           fireStartElem(this.m_elemContext.m_elementName); 
/* 1979 */         int nAttrs = this.m_attributes.getLength();
/* 1980 */         if (nAttrs > 0) {
/*      */           
/* 1982 */           processAttributes(this.m_writer, nAttrs);
/*      */           
/* 1984 */           this.m_attributes.clear();
/*      */         } 
/* 1986 */         if (this.m_spaceBeforeClose) {
/* 1987 */           writer.write(" />");
/*      */         } else {
/* 1989 */           writer.write("/>");
/*      */         
/*      */         }
/*      */         
/*      */          }
/*      */       
/*      */       else
/*      */       
/*      */       { 
/* 1998 */         if (this.m_cdataTagOpen) {
/* 1999 */           closeCDATA();
/*      */         }
/* 2001 */         if (shouldIndent())
/* 2002 */           indent(this.m_elemContext.m_currentElemDepth - 1); 
/* 2003 */         writer.write(60);
/* 2004 */         writer.write(47);
/* 2005 */         writer.write(name);
/* 2006 */         writer.write(62); }  } catch (IOException e)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/* 2011 */       throw new SAXException(e); }
/*      */ 
/*      */     
/* 2014 */     if (!this.m_elemContext.m_startTagOpen && this.m_doIndent)
/*      */     {
/* 2016 */       this.m_ispreserve = this.m_preserves.isEmpty() ? false : this.m_preserves.pop();
/*      */     }
/*      */     
/* 2019 */     this.m_isprevtext = false;
/*      */ 
/*      */     
/* 2022 */     if (this.m_tracer != null)
/* 2023 */       fireEndElem(name); 
/* 2024 */     this.m_elemContext = this.m_elemContext.m_prev;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endElement(String name) throws SAXException {
/* 2035 */     endElement(null, null, name);
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
/*      */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/* 2057 */     startPrefixMapping(prefix, uri, true);
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
/*      */   public boolean startPrefixMapping(String prefix, String uri, boolean shouldFlush) throws SAXException {
/*      */     int i;
/* 2096 */     if (shouldFlush) {
/*      */       
/* 2098 */       flushPending();
/*      */       
/* 2100 */       i = this.m_elemContext.m_currentElemDepth + 1;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 2105 */       i = this.m_elemContext.m_currentElemDepth;
/*      */     } 
/* 2107 */     boolean pushed = this.m_prefixMap.pushNamespace(prefix, uri, i);
/*      */     
/* 2109 */     if (pushed)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2117 */       if ("".equals(prefix)) {
/*      */         
/* 2119 */         String name = "xmlns";
/* 2120 */         addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, name, "CDATA", uri);
/*      */ 
/*      */       
/*      */       }
/* 2124 */       else if (!"".equals(uri)) {
/*      */ 
/*      */         
/* 2127 */         String str = "xmlns:" + prefix;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2133 */         addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, str, "CDATA", uri);
/*      */       } 
/*      */     }
/*      */     
/* 2137 */     return pushed;
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
/*      */   public void comment(char[] ch, int start, int length) throws SAXException {
/* 2153 */     int start_old = start;
/* 2154 */     if (this.m_inEntityRef)
/*      */       return; 
/* 2156 */     if (this.m_elemContext.m_startTagOpen) {
/*      */       
/* 2158 */       closeStartTag();
/* 2159 */       this.m_elemContext.m_startTagOpen = false;
/*      */     }
/* 2161 */     else if (this.m_needToCallStartDocument) {
/*      */       
/* 2163 */       startDocumentInternal();
/* 2164 */       this.m_needToCallStartDocument = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2169 */     try { if (shouldIndent()) {
/* 2170 */         indent();
/*      */       }
/* 2172 */       int limit = start + length;
/* 2173 */       boolean wasDash = false;
/* 2174 */       if (this.m_cdataTagOpen)
/* 2175 */         closeCDATA(); 
/* 2176 */       Writer writer = this.m_writer;
/* 2177 */       writer.write("<!--");
/*      */       
/* 2179 */       for (int i = start; i < limit; i++) {
/*      */         
/* 2181 */         if (wasDash && ch[i] == '-') {
/*      */           
/* 2183 */           writer.write(ch, start, i - start);
/* 2184 */           writer.write(" -");
/* 2185 */           start = i + 1;
/*      */         } 
/* 2187 */         wasDash = (ch[i] == '-');
/*      */       } 
/*      */ 
/*      */       
/* 2191 */       if (length > 0) {
/*      */ 
/*      */         
/* 2194 */         int remainingChars = limit - start;
/* 2195 */         if (remainingChars > 0) {
/* 2196 */           writer.write(ch, start, remainingChars);
/*      */         }
/* 2198 */         if (ch[limit - 1] == '-')
/* 2199 */           writer.write(32); 
/*      */       } 
/* 2201 */       writer.write("-->"); } catch (IOException e)
/*      */     
/*      */     { 
/*      */       
/* 2205 */       throw new SAXException(e); }
/*      */ 
/*      */     
/* 2208 */     this.m_startNewLine = true;
/*      */     
/* 2210 */     if (this.m_tracer != null) {
/* 2211 */       fireCommentEvent(ch, start_old, length);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endCDATA() throws SAXException {
/* 2222 */     if (this.m_cdataTagOpen)
/* 2223 */       closeCDATA(); 
/* 2224 */     this.m_cdataStartCalled = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void endDTD() throws SAXException {
/*      */     
/* 2236 */     try { if (this.m_needToOutputDocTypeDecl) {
/*      */         
/* 2238 */         outputDocTypeDecl(this.m_elemContext.m_elementName, false);
/* 2239 */         this.m_needToOutputDocTypeDecl = false;
/*      */       } 
/* 2241 */       Writer writer = this.m_writer;
/* 2242 */       if (!this.m_inDoctype) {
/* 2243 */         writer.write("]>");
/*      */       } else {
/*      */         
/* 2246 */         writer.write(62);
/*      */       } 
/*      */       
/* 2249 */       writer.write(this.m_lineSep, 0, this.m_lineSepLen); } catch (IOException e)
/*      */     
/*      */     { 
/*      */       
/* 2253 */       throw new SAXException(e); }
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
/*      */   public void endPrefixMapping(String prefix) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
/* 2288 */     if (0 == length)
/*      */       return; 
/* 2290 */     characters(ch, start, length);
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
/*      */   public void skippedEntity(String name) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startCDATA() throws SAXException {
/* 2316 */     this.m_cdataStartCalled = true;
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
/*      */   public void startEntity(String name) throws SAXException {
/* 2336 */     if (name.equals("[dtd]"))
/* 2337 */       this.m_inExternalDTD = true; 
/* 2338 */     this.m_inEntityRef = true;
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
/*      */   protected void closeStartTag() throws SAXException {
/* 2350 */     if (this.m_elemContext.m_startTagOpen) {
/*      */ 
/*      */ 
/*      */       
/*      */       try { 
/* 2355 */         if (this.m_tracer != null)
/* 2356 */           fireStartElem(this.m_elemContext.m_elementName); 
/* 2357 */         int nAttrs = this.m_attributes.getLength();
/* 2358 */         if (nAttrs > 0) {
/*      */           
/* 2360 */           processAttributes(this.m_writer, nAttrs);
/*      */           
/* 2362 */           this.m_attributes.clear();
/*      */         } 
/* 2364 */         this.m_writer.write(62); } catch (IOException e)
/*      */       
/*      */       { 
/*      */         
/* 2368 */         throw new SAXException(e); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2375 */       if (this.m_cdataSectionElements != null) {
/* 2376 */         this.m_elemContext.m_isCdataSection = isCdataSection();
/*      */       }
/* 2378 */       if (this.m_doIndent) {
/*      */         
/* 2380 */         this.m_isprevtext = false;
/* 2381 */         this.m_preserves.push(this.m_ispreserve);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startDTD(String name, String publicId, String systemId) throws SAXException {
/* 2406 */     setDoctypeSystem(systemId);
/* 2407 */     setDoctypePublic(publicId);
/*      */     
/* 2409 */     this.m_elemContext.m_elementName = name;
/* 2410 */     this.m_inDoctype = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIndentAmount() {
/* 2419 */     return this.m_indentAmount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIndentAmount(int m_indentAmount) {
/* 2429 */     this.m_indentAmount = m_indentAmount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean shouldIndent() {
/* 2440 */     return (this.m_doIndent && !this.m_ispreserve && !this.m_isprevtext);
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
/*      */   private void setCdataSectionElements(String key, Properties props) {
/* 2462 */     String s = props.getProperty(key);
/*      */     
/* 2464 */     if (null != s) {
/*      */ 
/*      */       
/* 2467 */       Vector v = new Vector();
/* 2468 */       int l = s.length();
/* 2469 */       boolean inCurly = false;
/* 2470 */       FastStringBuffer buf = new FastStringBuffer();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2475 */       for (int i = 0; i < l; i++) {
/*      */         
/* 2477 */         char c = s.charAt(i);
/*      */         
/* 2479 */         if (Character.isWhitespace(c)) {
/*      */           
/* 2481 */           if (!inCurly) {
/*      */             
/* 2483 */             if (buf.length() > 0) {
/*      */               
/* 2485 */               addCdataSectionElement(buf.toString(), v);
/* 2486 */               buf.reset();
/*      */             } 
/*      */             
/*      */             continue;
/*      */           } 
/* 2491 */         } else if ('{' == c) {
/* 2492 */           inCurly = true;
/* 2493 */         } else if ('}' == c) {
/* 2494 */           inCurly = false;
/*      */         } 
/* 2496 */         buf.append(c);
/*      */         continue;
/*      */       } 
/* 2499 */       if (buf.length() > 0) {
/*      */         
/* 2501 */         addCdataSectionElement(buf.toString(), v);
/* 2502 */         buf.reset();
/*      */       } 
/*      */       
/* 2505 */       setCdataSectionElements(v);
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
/*      */   private void addCdataSectionElement(String URI_and_localName, Vector v) {
/* 2520 */     StringTokenizer tokenizer = new StringTokenizer(URI_and_localName, "{}", false);
/*      */ 
/*      */     
/* 2523 */     String s1 = tokenizer.nextToken();
/* 2524 */     String s2 = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
/*      */     
/* 2526 */     if (null == s2) {
/*      */ 
/*      */       
/* 2529 */       v.addElement(null);
/* 2530 */       v.addElement(s1);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 2535 */       v.addElement(s1);
/* 2536 */       v.addElement(s2);
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
/*      */   public void setCdataSectionElements(Vector URI_and_localNames) {
/* 2549 */     this.m_cdataSectionElements = URI_and_localNames;
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
/*      */   protected String ensureAttributesNamespaceIsDeclared(String ns, String localName, String rawName) throws SAXException {
/* 2568 */     if (ns != null && ns.length() > 0) {
/*      */ 
/*      */ 
/*      */       
/* 2572 */       int index = 0;
/* 2573 */       String prefixFromRawName = ((index = rawName.indexOf(":")) < 0) ? "" : rawName.substring(0, index);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2578 */       if (index > 0) {
/*      */ 
/*      */         
/* 2581 */         String uri = this.m_prefixMap.lookupNamespace(prefixFromRawName);
/* 2582 */         if (uri != null && uri.equals(ns))
/*      */         {
/*      */ 
/*      */           
/* 2586 */           return null;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2592 */         startPrefixMapping(prefixFromRawName, ns, false);
/* 2593 */         addAttribute("http://www.w3.org/2000/xmlns/", prefixFromRawName, "xmlns:" + prefixFromRawName, "CDATA", ns);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2599 */         return prefixFromRawName;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2606 */       String prefix = this.m_prefixMap.lookupPrefix(ns);
/* 2607 */       if (prefix == null) {
/*      */ 
/*      */ 
/*      */         
/* 2611 */         prefix = this.m_prefixMap.generateNextPrefix();
/* 2612 */         startPrefixMapping(prefix, ns, false);
/* 2613 */         addAttribute("http://www.w3.org/2000/xmlns/", prefix, "xmlns:" + prefix, "CDATA", ns);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2621 */       return prefix;
/*      */     } 
/*      */ 
/*      */     
/* 2625 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void ensurePrefixIsDeclared(String ns, String rawName) throws SAXException {
/* 2632 */     if (ns != null && ns.length() > 0) {
/*      */       int index;
/*      */       
/* 2635 */       String prefix = ((index = rawName.indexOf(":")) < 0) ? "" : rawName.substring(0, index);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2640 */       if (null != prefix) {
/*      */         
/* 2642 */         String foundURI = this.m_prefixMap.lookupNamespace(prefix);
/*      */         
/* 2644 */         if (null == foundURI || !foundURI.equals(ns)) {
/*      */           
/* 2646 */           startPrefixMapping(prefix, ns);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2651 */           addAttributeAlways("http://www.w3.org/2000/xmlns/", prefix, "xmlns" + ((prefix.length() == 0) ? "" : ":") + prefix, "CDATA", ns);
/*      */         } 
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
/*      */   public void flushPending() throws SAXException {
/* 2669 */     if (this.m_needToCallStartDocument) {
/*      */       
/* 2671 */       startDocumentInternal();
/* 2672 */       this.m_needToCallStartDocument = false;
/*      */     } 
/* 2674 */     if (this.m_elemContext.m_startTagOpen) {
/*      */       
/* 2676 */       closeStartTag();
/* 2677 */       this.m_elemContext.m_startTagOpen = false;
/*      */     } 
/*      */     
/* 2680 */     if (this.m_cdataTagOpen) {
/*      */       
/* 2682 */       closeCDATA();
/* 2683 */       this.m_cdataTagOpen = false;
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
/*      */   public void setContentHandler(ContentHandler ch) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addAttributeAlways(String uri, String localName, String rawName, String type, String value) {
/* 2718 */     int index = this.m_attributes.getIndex(rawName);
/* 2719 */     if (index >= 0) {
/*      */       
/* 2721 */       String old_value = null;
/* 2722 */       if (this.m_tracer != null) {
/*      */         
/* 2724 */         old_value = this.m_attributes.getValue(index);
/* 2725 */         if (value.equals(old_value)) {
/* 2726 */           old_value = null;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2733 */       this.m_attributes.setValue(index, value);
/* 2734 */       if (old_value != null) {
/* 2735 */         firePseudoAttributes();
/*      */       
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 2741 */       this.m_attributes.addAttribute(uri, localName, rawName, type, value);
/* 2742 */       if (this.m_tracer != null) {
/* 2743 */         firePseudoAttributes();
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
/*      */   protected void firePseudoAttributes() {
/* 2756 */     if (this.m_tracer != null) {
/*      */ 
/*      */       
/*      */       try { 
/*      */         
/* 2761 */         this.m_writer.flush();
/*      */ 
/*      */         
/* 2764 */         StringBuffer sb = new StringBuffer();
/* 2765 */         int nAttrs = this.m_attributes.getLength();
/* 2766 */         if (nAttrs > 0) {
/*      */ 
/*      */ 
/*      */           
/* 2770 */           Writer writer = new WritertoStringBuffer(this, sb);
/*      */ 
/*      */           
/* 2773 */           processAttributes(writer, nAttrs);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2778 */         sb.append('>');
/*      */ 
/*      */ 
/*      */         
/* 2782 */         char[] ch = sb.toString().toCharArray();
/* 2783 */         this.m_tracer.fireGenerateEvent(11, ch, 0, ch.length); } catch (IOException ioe)
/*      */       
/*      */       { 
/*      */ 
/*      */         
/*      */          }
/*      */       
/* 2790 */       catch (SAXException sAXException) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class WritertoStringBuffer
/*      */     extends Writer
/*      */   {
/*      */     private final StringBuffer m_stringbuf;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final ToStream this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     WritertoStringBuffer(ToStream this$0, StringBuffer sb) {
/* 2813 */       this.this$0 = this$0;
/* 2814 */       this.m_stringbuf = sb;
/*      */     }
/*      */ 
/*      */     
/*      */     public void write(char[] arg0, int arg1, int arg2) throws IOException {
/* 2819 */       this.m_stringbuf.append(arg0, arg1, arg2);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void flush() throws IOException {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void close() throws IOException {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void write(int i) {
/* 2836 */       this.m_stringbuf.append((char)i);
/*      */     }
/*      */ 
/*      */     
/*      */     public void write(String s) {
/* 2841 */       this.m_stringbuf.append(s);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransformer(Transformer transformer) {
/* 2849 */     super.setTransformer(transformer);
/* 2850 */     if (this.m_tracer != null && !(this.m_writer instanceof SerializerTraceWriter))
/*      */     {
/* 2852 */       this.m_writer = new SerializerTraceWriter(this.m_writer, this.m_tracer);
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
/*      */   public boolean reset() {
/* 2865 */     boolean wasReset = false;
/* 2866 */     if (super.reset()) {
/*      */       
/* 2868 */       resetToStream();
/* 2869 */       wasReset = true;
/*      */     } 
/* 2871 */     return wasReset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void resetToStream() {
/* 2880 */     this.m_canConvertMeth = null;
/* 2881 */     this.m_cdataStartCalled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2889 */     this.m_charToByteConverter = null;
/* 2890 */     this.m_disableOutputEscapingStates.clear();
/*      */     
/* 2892 */     this.m_escaping = true;
/*      */ 
/*      */     
/* 2895 */     this.m_inDoctype = false;
/* 2896 */     this.m_ispreserve = false;
/* 2897 */     this.m_ispreserve = false;
/* 2898 */     this.m_isprevtext = false;
/* 2899 */     this.m_isUTF8 = false;
/* 2900 */     this.m_maxCharacter = Encodings.getLastPrintable();
/* 2901 */     this.m_preserves.clear();
/* 2902 */     this.m_shouldFlush = true;
/* 2903 */     this.m_spaceBeforeClose = false;
/* 2904 */     this.m_startNewLine = false;
/* 2905 */     this.m_triedToGetConverter = false;
/* 2906 */     this.m_lineSepUse = true;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/ToStream.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */