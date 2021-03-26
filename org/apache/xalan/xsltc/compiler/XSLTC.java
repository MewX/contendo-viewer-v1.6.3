/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Vector;
/*     */ import java.util.jar.Attributes;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarOutputStream;
/*     */ import java.util.jar.Manifest;
/*     */ import org.apache.bcel.classfile.JavaClass;
/*     */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*     */ import org.apache.xalan.xsltc.compiler.util.Util;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.XMLReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class XSLTC
/*     */ {
/*     */   private Parser _parser;
/*  60 */   private XMLReader _reader = null;
/*     */ 
/*     */   
/*  63 */   private SourceLoader _loader = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private Stylesheet _stylesheet;
/*     */ 
/*     */   
/*  70 */   private int _modeSerial = 1;
/*  71 */   private int _stylesheetSerial = 1;
/*  72 */   private int _stepPatternSerial = 1;
/*  73 */   private int _helperClassSerial = 0;
/*  74 */   private int _attributeSetSerial = 0;
/*     */   
/*     */   private int[] _numberFieldIndexes;
/*     */   
/*     */   private int _nextGType;
/*     */   
/*     */   private Vector _namesIndex;
/*     */   
/*     */   private Hashtable _elements;
/*     */   
/*     */   private Hashtable _attributes;
/*     */   
/*     */   private int _nextNSType;
/*     */   
/*     */   private Vector _namespaceIndex;
/*     */   
/*     */   private Hashtable _namespaces;
/*     */   
/*     */   private Hashtable _namespacePrefixes;
/*     */   
/*     */   private Vector m_characterData;
/*     */   
/*     */   public static final int FILE_OUTPUT = 0;
/*     */   
/*     */   public static final int JAR_OUTPUT = 1;
/*     */   
/*     */   public static final int BYTEARRAY_OUTPUT = 2;
/*     */   public static final int CLASSLOADER_OUTPUT = 3;
/*     */   public static final int BYTEARRAY_AND_FILE_OUTPUT = 4;
/*     */   public static final int BYTEARRAY_AND_JAR_OUTPUT = 5;
/*     */   private boolean _debug = false;
/* 105 */   private String _jarFileName = null;
/* 106 */   private String _className = null;
/* 107 */   private String _packageName = null;
/* 108 */   private File _destDir = null;
/* 109 */   private int _outputType = 0;
/*     */ 
/*     */   
/*     */   private Vector _classes;
/*     */ 
/*     */   
/*     */   private Vector _bcelClasses;
/*     */ 
/*     */   
/*     */   private boolean _callsNodeset = false;
/*     */ 
/*     */   
/*     */   private boolean _multiDocument = false;
/*     */   
/*     */   private boolean _hasIdCall = false;
/*     */   
/*     */   private boolean _templateInlining = false;
/*     */ 
/*     */   
/*     */   public XSLTC() {
/* 129 */     this._parser = new Parser(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Parser getParser() {
/* 136 */     return this._parser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOutputType(int type) {
/* 143 */     this._outputType = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Properties getOutputProperties() {
/* 150 */     return this._parser.getOutputProperties();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 157 */     reset();
/* 158 */     this._reader = null;
/* 159 */     this._classes = new Vector();
/* 160 */     this._bcelClasses = new Vector();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void reset() {
/* 167 */     this._nextGType = 14;
/* 168 */     this._elements = new Hashtable();
/* 169 */     this._attributes = new Hashtable();
/* 170 */     this._namespaces = new Hashtable();
/* 171 */     this._namespaces.put("", new Integer(this._nextNSType));
/* 172 */     this._namesIndex = new Vector(128);
/* 173 */     this._namespaceIndex = new Vector(32);
/* 174 */     this._namespacePrefixes = new Hashtable();
/* 175 */     this._stylesheet = null;
/* 176 */     this._parser.init();
/*     */     
/* 178 */     this._modeSerial = 1;
/* 179 */     this._stylesheetSerial = 1;
/* 180 */     this._stepPatternSerial = 1;
/* 181 */     this._helperClassSerial = 0;
/* 182 */     this._attributeSetSerial = 0;
/* 183 */     this._multiDocument = false;
/* 184 */     this._hasIdCall = false;
/* 185 */     this._numberFieldIndexes = new int[] { -1, -1, -1 };
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
/*     */   public void setSourceLoader(SourceLoader loader) {
/* 198 */     this._loader = loader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTemplateInlining(boolean templateInlining) {
/* 208 */     this._templateInlining = templateInlining;
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
/*     */   public void setPIParameters(String media, String title, String charset) {
/* 221 */     this._parser.setPIParameters(media, title, charset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compile(URL url) {
/*     */     
/* 231 */     try { InputStream stream = url.openStream();
/* 232 */       InputSource input = new InputSource(stream);
/* 233 */       input.setSystemId(url.toString());
/* 234 */       return compile(input, this._className); } catch (IOException e)
/*     */     
/*     */     { 
/* 237 */       this._parser.reportError(2, new ErrorMsg(e));
/* 238 */       return false; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compile(URL url, String name) {
/*     */     
/* 250 */     try { InputStream stream = url.openStream();
/* 251 */       InputSource input = new InputSource(stream);
/* 252 */       input.setSystemId(url.toString());
/* 253 */       return compile(input, name); } catch (IOException e)
/*     */     
/*     */     { 
/* 256 */       this._parser.reportError(2, new ErrorMsg(e));
/* 257 */       return false; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compile(InputStream stream, String name) {
/* 268 */     InputSource input = new InputSource(stream);
/* 269 */     input.setSystemId(name);
/* 270 */     return compile(input, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compile(InputSource input, String name) {
/*     */     
/* 282 */     try { reset();
/*     */ 
/*     */       
/* 285 */       String systemId = null;
/* 286 */       if (input != null) {
/* 287 */         systemId = input.getSystemId();
/*     */       }
/*     */ 
/*     */       
/* 291 */       if (this._className == null) {
/* 292 */         if (name != null) {
/* 293 */           setClassName(name);
/*     */         }
/* 295 */         else if (systemId != null && !systemId.equals("")) {
/* 296 */           setClassName(Util.baseName(systemId));
/*     */         } 
/*     */ 
/*     */         
/* 300 */         if (this._className == null || this._className.length() == 0) {
/* 301 */           setClassName("GregorSamsa");
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 306 */       SyntaxTreeNode element = null;
/* 307 */       if (this._reader == null) {
/* 308 */         element = this._parser.parse(input);
/*     */       } else {
/*     */         
/* 311 */         element = this._parser.parse(this._reader, input);
/*     */       } 
/*     */ 
/*     */       
/* 315 */       if (!this._parser.errorsFound() && element != null) {
/*     */         
/* 317 */         this._stylesheet = this._parser.makeStylesheet(element);
/* 318 */         this._stylesheet.setSourceLoader(this._loader);
/* 319 */         this._stylesheet.setSystemId(systemId);
/* 320 */         this._stylesheet.setParentStylesheet(null);
/* 321 */         this._stylesheet.setTemplateInlining(this._templateInlining);
/* 322 */         this._parser.setCurrentStylesheet(this._stylesheet);
/*     */ 
/*     */         
/* 325 */         this._parser.createAST(this._stylesheet);
/*     */       } 
/*     */       
/* 328 */       if (!this._parser.errorsFound() && this._stylesheet != null)
/* 329 */       { this._stylesheet.setCallsNodeset(this._callsNodeset);
/* 330 */         this._stylesheet.setMultiDocument(this._multiDocument);
/* 331 */         this._stylesheet.setHasIdCall(this._hasIdCall);
/*     */ 
/*     */         
/* 334 */         synchronized (getClass())
/* 335 */         { this._stylesheet.translate(); }  }  }
/* 336 */     catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/* 340 */       e.printStackTrace();
/* 341 */       this._parser.reportError(2, new ErrorMsg(e)); } catch (Error e)
/*     */     
/*     */     { 
/* 344 */       if (this._debug) e.printStackTrace(); 
/* 345 */       this._parser.reportError(2, new ErrorMsg(e)); }
/*     */     finally
/*     */     
/* 348 */     { this._reader = null; }
/*     */     
/* 350 */     return !this._parser.errorsFound();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compile(Vector stylesheets) {
/* 360 */     int count = stylesheets.size();
/*     */ 
/*     */     
/* 363 */     if (count == 0) return true;
/*     */ 
/*     */ 
/*     */     
/* 367 */     if (count == 1) {
/* 368 */       Object url = stylesheets.firstElement();
/* 369 */       if (url instanceof URL) {
/* 370 */         return compile((URL)url);
/*     */       }
/* 372 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 376 */     Enumeration urls = stylesheets.elements();
/* 377 */     while (urls.hasMoreElements()) {
/* 378 */       this._className = null;
/* 379 */       Object url = urls.nextElement();
/* 380 */       if (url instanceof URL && 
/* 381 */         !compile((URL)url)) return false;
/*     */     
/*     */     } 
/*     */     
/* 385 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[][] getBytecodes() {
/* 393 */     int count = this._classes.size();
/* 394 */     byte[][] result = new byte[count][1];
/* 395 */     for (int i = 0; i < count; i++)
/* 396 */       result[i] = this._classes.elementAt(i); 
/* 397 */     return result;
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
/*     */   public byte[][] compile(String name, InputSource input, int outputType) {
/* 409 */     this._outputType = outputType;
/* 410 */     if (compile(input, name)) {
/* 411 */       return getBytecodes();
/*     */     }
/* 413 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[][] compile(String name, InputSource input) {
/* 424 */     return compile(name, input, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLReader(XMLReader reader) {
/* 432 */     this._reader = reader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLReader getXMLReader() {
/* 439 */     return this._reader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getErrors() {
/* 447 */     return this._parser.getErrors();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getWarnings() {
/* 455 */     return this._parser.getWarnings();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printErrors() {
/* 462 */     this._parser.printErrors();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printWarnings() {
/* 469 */     this._parser.printWarnings();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setMultiDocument(boolean flag) {
/* 477 */     this._multiDocument = flag;
/*     */   }
/*     */   
/*     */   public boolean isMultiDocument() {
/* 481 */     return this._multiDocument;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setCallsNodeset(boolean flag) {
/* 489 */     if (flag) setMultiDocument(flag); 
/* 490 */     this._callsNodeset = flag;
/*     */   }
/*     */   
/*     */   public boolean callsNodeset() {
/* 494 */     return this._callsNodeset;
/*     */   }
/*     */   
/*     */   protected void setHasIdCall(boolean flag) {
/* 498 */     this._hasIdCall = flag;
/*     */   }
/*     */   
/*     */   public boolean hasIdCall() {
/* 502 */     return this._hasIdCall;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClassName(String className) {
/* 512 */     String base = Util.baseName(className);
/* 513 */     String noext = Util.noExtName(base);
/* 514 */     String name = Util.toJavaName(noext);
/*     */     
/* 516 */     if (this._packageName == null) {
/* 517 */       this._className = name;
/*     */     } else {
/* 519 */       this._className = this._packageName + '.' + name;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClassName() {
/* 526 */     return this._className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String classFileName(String className) {
/* 534 */     return className.replace('.', File.separatorChar) + ".class";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private File getOutputFile(String className) {
/* 541 */     if (this._destDir != null) {
/* 542 */       return new File(this._destDir, classFileName(className));
/*     */     }
/* 544 */     return new File(classFileName(className));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setDestDirectory(String dstDirName) {
/* 552 */     File dir = new File(dstDirName);
/* 553 */     if (dir.exists() || dir.mkdirs()) {
/* 554 */       this._destDir = dir;
/* 555 */       return true;
/*     */     } 
/*     */     
/* 558 */     this._destDir = null;
/* 559 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPackageName(String packageName) {
/* 567 */     this._packageName = packageName;
/* 568 */     if (this._className != null) setClassName(this._className);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJarFileName(String jarFileName) {
/* 576 */     String JAR_EXT = ".jar";
/* 577 */     if (jarFileName.endsWith(".jar")) {
/* 578 */       this._jarFileName = jarFileName;
/*     */     } else {
/* 580 */       this._jarFileName = jarFileName + ".jar";
/* 581 */     }  this._outputType = 1;
/*     */   }
/*     */   
/*     */   public String getJarFileName() {
/* 585 */     return this._jarFileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStylesheet(Stylesheet stylesheet) {
/* 592 */     if (this._stylesheet == null) this._stylesheet = stylesheet;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Stylesheet getStylesheet() {
/* 599 */     return this._stylesheet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int registerAttribute(QName name) {
/* 607 */     Integer code = (Integer)this._attributes.get(name.toString());
/* 608 */     if (code == null) {
/* 609 */       code = new Integer(this._nextGType++);
/* 610 */       this._attributes.put(name.toString(), code);
/* 611 */       String uri = name.getNamespace();
/* 612 */       String local = "@" + name.getLocalPart();
/* 613 */       if (uri != null && !uri.equals("")) {
/* 614 */         this._namesIndex.addElement(uri + ":" + local);
/*     */       } else {
/* 616 */         this._namesIndex.addElement(local);
/* 617 */       }  if (name.getLocalPart().equals("*")) {
/* 618 */         registerNamespace(name.getNamespace());
/*     */       }
/*     */     } 
/* 621 */     return code.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int registerElement(QName name) {
/* 630 */     Integer code = (Integer)this._elements.get(name.toString());
/* 631 */     if (code == null) {
/* 632 */       this._elements.put(name.toString(), code = new Integer(this._nextGType++));
/* 633 */       this._namesIndex.addElement(name.toString());
/*     */     } 
/* 635 */     if (name.getLocalPart().equals("*")) {
/* 636 */       registerNamespace(name.getNamespace());
/*     */     }
/* 638 */     return code.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int registerNamespacePrefix(QName name) {
/* 648 */     Integer code = (Integer)this._namespacePrefixes.get(name.toString());
/* 649 */     if (code == null) {
/* 650 */       code = new Integer(this._nextGType++);
/* 651 */       this._namespacePrefixes.put(name.toString(), code);
/* 652 */       String uri = name.getNamespace();
/* 653 */       if (uri != null && !uri.equals("")) {
/*     */         
/* 655 */         this._namesIndex.addElement("?");
/*     */       } else {
/* 657 */         this._namesIndex.addElement("?" + name.getLocalPart());
/*     */       } 
/*     */     } 
/* 660 */     return code.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int registerNamespace(String namespaceURI) {
/* 668 */     Integer code = (Integer)this._namespaces.get(namespaceURI);
/* 669 */     if (code == null) {
/* 670 */       code = new Integer(this._nextNSType++);
/* 671 */       this._namespaces.put(namespaceURI, code);
/* 672 */       this._namespaceIndex.addElement(namespaceURI);
/*     */     } 
/* 674 */     return code.intValue();
/*     */   }
/*     */   
/*     */   public int nextModeSerial() {
/* 678 */     return this._modeSerial++;
/*     */   }
/*     */   
/*     */   public int nextStylesheetSerial() {
/* 682 */     return this._stylesheetSerial++;
/*     */   }
/*     */   
/*     */   public int nextStepPatternSerial() {
/* 686 */     return this._stepPatternSerial++;
/*     */   }
/*     */   
/*     */   public int[] getNumberFieldIndexes() {
/* 690 */     return this._numberFieldIndexes;
/*     */   }
/*     */   
/*     */   public int nextHelperClassSerial() {
/* 694 */     return this._helperClassSerial++;
/*     */   }
/*     */   
/*     */   public int nextAttributeSetSerial() {
/* 698 */     return this._attributeSetSerial++;
/*     */   }
/*     */   
/*     */   public Vector getNamesIndex() {
/* 702 */     return this._namesIndex;
/*     */   }
/*     */   
/*     */   public Vector getNamespaceIndex() {
/* 706 */     return this._namespaceIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHelperClassName() {
/* 714 */     return getClassName() + '$' + this._helperClassSerial++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dumpClass(JavaClass clazz) {
/* 719 */     if (this._outputType == 0 || this._outputType == 4) {
/*     */ 
/*     */       
/* 722 */       File outFile = getOutputFile(clazz.getClassName());
/* 723 */       String parentDir = outFile.getParent();
/* 724 */       if (parentDir != null) {
/* 725 */         File parentFile = new File(parentDir);
/* 726 */         if (!parentFile.exists())
/* 727 */           parentFile.mkdirs(); 
/*     */       } 
/*     */     } 
/*     */     try {
/*     */       ByteArrayOutputStream out;
/* 732 */       switch (this._outputType) {
/*     */         case 0:
/* 734 */           clazz.dump(new BufferedOutputStream(new FileOutputStream(getOutputFile(clazz.getClassName()))));
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/* 740 */           this._bcelClasses.addElement(clazz);
/*     */           break;
/*     */         case 2:
/*     */         case 3:
/*     */         case 4:
/*     */         case 5:
/* 746 */           out = new ByteArrayOutputStream(2048);
/* 747 */           clazz.dump(out);
/* 748 */           this._classes.addElement(out.toByteArray());
/*     */           
/* 750 */           if (this._outputType == 4) {
/* 751 */             clazz.dump(new BufferedOutputStream(new FileOutputStream(getOutputFile(clazz.getClassName())))); break;
/*     */           } 
/* 753 */           if (this._outputType == 5)
/* 754 */             this._bcelClasses.addElement(clazz);  break;
/*     */       } 
/* 756 */     } catch (Exception e) {
/*     */ 
/*     */ 
/*     */       
/* 760 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String entryName(File f) throws IOException {
/* 768 */     return f.getName().replace(File.separatorChar, '/');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void outputToJar() throws IOException {
/* 776 */     Manifest manifest = new Manifest();
/* 777 */     Attributes atrs = manifest.getMainAttributes();
/* 778 */     atrs.put(Attributes.Name.MANIFEST_VERSION, "1.2");
/*     */     
/* 780 */     Map map = manifest.getEntries();
/*     */     
/* 782 */     Enumeration classes = this._bcelClasses.elements();
/* 783 */     String now = (new Date()).toString();
/* 784 */     Attributes.Name dateAttr = new Attributes.Name("Date");
/*     */     
/* 786 */     while (classes.hasMoreElements()) {
/* 787 */       JavaClass clazz = classes.nextElement();
/* 788 */       String className = clazz.getClassName().replace('.', '/');
/* 789 */       Attributes attr = new Attributes();
/* 790 */       attr.put(dateAttr, now);
/* 791 */       map.put(className + ".class", attr);
/*     */     } 
/*     */     
/* 794 */     File jarFile = new File(this._destDir, this._jarFileName);
/* 795 */     JarOutputStream jos = new JarOutputStream(new FileOutputStream(jarFile), manifest);
/*     */     
/* 797 */     classes = this._bcelClasses.elements();
/* 798 */     while (classes.hasMoreElements()) {
/* 799 */       JavaClass clazz = classes.nextElement();
/* 800 */       String className = clazz.getClassName().replace('.', '/');
/* 801 */       jos.putNextEntry(new JarEntry(className + ".class"));
/* 802 */       ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
/* 803 */       clazz.dump(out);
/* 804 */       out.writeTo(jos);
/*     */     } 
/* 806 */     jos.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDebug(boolean debug) {
/* 813 */     this._debug = debug;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean debug() {
/* 820 */     return this._debug;
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
/*     */   public String getCharacterData(int index) {
/* 833 */     return ((StringBuffer)this.m_characterData.elementAt(index)).toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCharacterDataCount() {
/* 841 */     return (this.m_characterData != null) ? this.m_characterData.size() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addCharacterData(String newData) {
/*     */     StringBuffer stringBuffer;
/* 853 */     if (this.m_characterData == null) {
/* 854 */       this.m_characterData = new Vector();
/* 855 */       stringBuffer = new StringBuffer();
/* 856 */       this.m_characterData.addElement(stringBuffer);
/*     */     } else {
/* 858 */       stringBuffer = this.m_characterData.elementAt(this.m_characterData.size() - 1);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 866 */     if (newData.length() + stringBuffer.length() > 21845) {
/* 867 */       stringBuffer = new StringBuffer();
/* 868 */       this.m_characterData.addElement(stringBuffer);
/*     */     } 
/*     */     
/* 871 */     int newDataOffset = stringBuffer.length();
/* 872 */     stringBuffer.append(newData);
/*     */     
/* 874 */     return newDataOffset;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/XSLTC.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */