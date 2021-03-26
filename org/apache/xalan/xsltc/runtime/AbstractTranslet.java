/*     */ package org.apache.xalan.xsltc.runtime;
/*     */ 
/*     */ import java.io.FileWriter;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.Templates;
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.DOMCache;
/*     */ import org.apache.xalan.xsltc.DOMEnhancedForDTM;
/*     */ import org.apache.xalan.xsltc.Translet;
/*     */ import org.apache.xalan.xsltc.TransletException;
/*     */ import org.apache.xalan.xsltc.dom.DOMAdapter;
/*     */ import org.apache.xalan.xsltc.dom.KeyIndex;
/*     */ import org.apache.xalan.xsltc.runtime.output.TransletOutputHandlerFactory;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractTranslet
/*     */   implements Translet
/*     */ {
/*  54 */   public String _version = "1.0";
/*  55 */   public String _method = null;
/*  56 */   public String _encoding = "UTF-8";
/*     */   public boolean _omitHeader = false;
/*  58 */   public String _standalone = null;
/*  59 */   public String _doctypePublic = null;
/*  60 */   public String _doctypeSystem = null;
/*     */   public boolean _indent = false;
/*  62 */   public String _mediaType = null;
/*  63 */   public Vector _cdata = null;
/*     */ 
/*     */   
/*     */   public static final int FIRST_TRANSLET_VERSION = 100;
/*     */ 
/*     */   
/*     */   public static final int VER_SPLIT_NAMES_ARRAY = 101;
/*     */ 
/*     */   
/*     */   public static final int CURRENT_TRANSLET_VERSION = 101;
/*     */   
/*  74 */   protected int transletVersion = 100;
/*     */   
/*     */   protected String[] namesArray;
/*     */   
/*     */   protected String[] urisArray;
/*     */   
/*     */   protected int[] typesArray;
/*     */   
/*     */   protected String[] namespaceArray;
/*  83 */   protected Templates _templates = null;
/*     */ 
/*     */   
/*     */   protected boolean _hasIdCall = false;
/*     */ 
/*     */   
/*  89 */   protected StringValueHandler stringValueHandler = new StringValueHandler();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String EMPTYSTRING = "";
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String ID_INDEX_NAME = "##id";
/*     */ 
/*     */ 
/*     */   
/*     */   public void printInternalState() {
/* 102 */     System.out.println("-------------------------------------");
/* 103 */     System.out.println("AbstractTranslet this = " + this);
/* 104 */     System.out.println("pbase = " + this.pbase);
/* 105 */     System.out.println("vframe = " + this.pframe);
/* 106 */     System.out.println("paramsStack.size() = " + this.paramsStack.size());
/* 107 */     System.out.println("namesArray.size = " + this.namesArray.length);
/* 108 */     System.out.println("namespaceArray.size = " + this.namespaceArray.length);
/* 109 */     System.out.println("");
/* 110 */     System.out.println("Total memory = " + Runtime.getRuntime().totalMemory());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final DOMAdapter makeDOMAdapter(DOM dom) throws TransletException {
/* 120 */     return new DOMAdapter(dom, this.namesArray, this.urisArray, this.typesArray, this.namespaceArray);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   protected int pbase = 0; protected int pframe = 0;
/* 130 */   protected ArrayList paramsStack = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void pushParamFrame() {
/* 136 */     this.paramsStack.add(this.pframe, new Integer(this.pbase));
/* 137 */     this.pbase = ++this.pframe;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void popParamFrame() {
/* 144 */     if (this.pbase > 0) {
/* 145 */       int oldpbase = ((Integer)this.paramsStack.get(--this.pbase)).intValue();
/* 146 */       for (int i = this.pframe - 1; i >= this.pbase; i--) {
/* 147 */         this.paramsStack.remove(i);
/*     */       }
/* 149 */       this.pframe = this.pbase; this.pbase = oldpbase;
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
/*     */   public final Object addParameter(String name, Object value) {
/* 162 */     name = BasisLibrary.mapQNameToJavaName(name);
/* 163 */     return addParameter(name, value, false);
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
/*     */   public final Object addParameter(String name, Object value, boolean isDefault) {
/* 176 */     for (int i = this.pframe - 1; i >= this.pbase; i--) {
/* 177 */       Parameter param = this.paramsStack.get(i);
/*     */       
/* 179 */       if (param._name.equals(name)) {
/*     */ 
/*     */         
/* 182 */         if (param._isDefault || !isDefault) {
/* 183 */           param._value = value;
/* 184 */           param._isDefault = isDefault;
/* 185 */           return value;
/*     */         } 
/* 187 */         return param._value;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 192 */     this.paramsStack.add(this.pframe++, new Parameter(name, value, isDefault));
/* 193 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearParameters() {
/* 200 */     this.pbase = this.pframe = 0;
/* 201 */     this.paramsStack.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Object getParameter(String name) {
/* 210 */     name = BasisLibrary.mapQNameToJavaName(name);
/*     */     
/* 212 */     for (int i = this.pframe - 1; i >= this.pbase; i--) {
/* 213 */       Parameter param = this.paramsStack.get(i);
/* 214 */       if (param._name.equals(name)) return param._value; 
/*     */     } 
/* 216 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   private MessageHandler _msgHandler = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setMessageHandler(MessageHandler handler) {
/* 232 */     this._msgHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void displayMessage(String msg) {
/* 239 */     if (this._msgHandler == null) {
/* 240 */       System.err.println(msg);
/*     */     } else {
/*     */       
/* 243 */       this._msgHandler.displayMessage(msg);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public Hashtable _formatSymbols = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDecimalFormat(String name, DecimalFormatSymbols symbols) {
/* 260 */     if (this._formatSymbols == null) this._formatSymbols = new Hashtable();
/*     */ 
/*     */     
/* 263 */     if (name == null) name = "";
/*     */ 
/*     */     
/* 266 */     DecimalFormat df = new DecimalFormat();
/* 267 */     if (symbols != null) {
/* 268 */       df.setDecimalFormatSymbols(symbols);
/*     */     }
/* 270 */     this._formatSymbols.put(name, df);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final DecimalFormat getDecimalFormat(String name) {
/* 278 */     if (this._formatSymbols != null) {
/*     */       
/* 280 */       if (name == null) name = "";
/*     */       
/* 282 */       DecimalFormat df = (DecimalFormat)this._formatSymbols.get(name);
/* 283 */       if (df == null) df = (DecimalFormat)this._formatSymbols.get(""); 
/* 284 */       return df;
/*     */     } 
/* 286 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void prepassDocument(DOM document) {
/* 296 */     setIndexSize(document.getSize());
/* 297 */     buildIDIndex(document);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final void buildIDIndex(DOM document) {
/* 307 */     if (document instanceof DOMEnhancedForDTM) {
/* 308 */       DOMEnhancedForDTM enhancedDOM = (DOMEnhancedForDTM)document;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 313 */       if (enhancedDOM.hasDOMSource()) {
/* 314 */         buildKeyIndex("##id", document);
/*     */         
/*     */         return;
/*     */       } 
/* 318 */       Hashtable elementsByID = enhancedDOM.getElementsWithIDs();
/*     */       
/* 320 */       if (elementsByID == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 327 */       Enumeration idValues = elementsByID.keys();
/* 328 */       boolean hasIDValues = false;
/*     */       
/* 330 */       while (idValues.hasMoreElements()) {
/* 331 */         Object idValue = idValues.nextElement();
/* 332 */         int element = ((Integer)elementsByID.get(idValue)).intValue();
/*     */         
/* 334 */         buildKeyIndex("##id", element, idValue);
/* 335 */         hasIDValues = true;
/*     */       } 
/*     */       
/* 338 */       if (hasIDValues) {
/* 339 */         setKeyIndexDom("##id", document);
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
/*     */   public final void postInitialization() {
/* 352 */     if (this.transletVersion < 101) {
/* 353 */       int arraySize = this.namesArray.length;
/* 354 */       String[] newURIsArray = new String[arraySize];
/* 355 */       String[] newNamesArray = new String[arraySize];
/* 356 */       int[] newTypesArray = new int[arraySize];
/*     */       
/* 358 */       for (int i = 0; i < arraySize; i++) {
/* 359 */         String name = this.namesArray[i];
/* 360 */         int colonIndex = name.lastIndexOf(':');
/* 361 */         int lNameStartIdx = colonIndex + 1;
/*     */         
/* 363 */         if (colonIndex > -1) {
/* 364 */           newURIsArray[i] = name.substring(0, colonIndex);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 369 */         if (name.charAt(lNameStartIdx) == '@') {
/* 370 */           lNameStartIdx++;
/* 371 */           newTypesArray[i] = 2;
/* 372 */         } else if (name.charAt(lNameStartIdx) == '?') {
/* 373 */           lNameStartIdx++;
/* 374 */           newTypesArray[i] = 13;
/*     */         } else {
/* 376 */           newTypesArray[i] = 1;
/*     */         } 
/* 378 */         newNamesArray[i] = (lNameStartIdx == 0) ? name : name.substring(lNameStartIdx);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 383 */       this.namesArray = newNamesArray;
/* 384 */       this.urisArray = newURIsArray;
/* 385 */       this.typesArray = newTypesArray;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 391 */     if (this.transletVersion > 101) {
/* 392 */       BasisLibrary.runTimeError("UNKNOWN_TRANSLET_VERSION_ERR", getClass().getName());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 402 */   private Hashtable _keyIndexes = null;
/* 403 */   private KeyIndex _emptyKeyIndex = null;
/* 404 */   private int _indexSize = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIndexSize(int size) {
/* 411 */     if (size > this._indexSize) this._indexSize = size;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyIndex createKeyIndex() {
/* 418 */     return new KeyIndex(this._indexSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildKeyIndex(String name, int node, Object value) {
/* 428 */     if (this._keyIndexes == null) this._keyIndexes = new Hashtable();
/*     */     
/* 430 */     KeyIndex index = (KeyIndex)this._keyIndexes.get(name);
/* 431 */     if (index == null) {
/* 432 */       this._keyIndexes.put(name, index = new KeyIndex(this._indexSize));
/*     */     }
/* 434 */     index.add(value, node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildKeyIndex(String name, DOM dom) {
/* 443 */     if (this._keyIndexes == null) this._keyIndexes = new Hashtable();
/*     */     
/* 445 */     KeyIndex index = (KeyIndex)this._keyIndexes.get(name);
/* 446 */     if (index == null) {
/* 447 */       this._keyIndexes.put(name, index = new KeyIndex(this._indexSize));
/*     */     }
/* 449 */     index.setDom(dom);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyIndex getKeyIndex(String name) {
/* 458 */     if (this._keyIndexes == null) {
/* 459 */       return (this._emptyKeyIndex != null) ? this._emptyKeyIndex : (this._emptyKeyIndex = new KeyIndex(1));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 465 */     KeyIndex index = (KeyIndex)this._keyIndexes.get(name);
/*     */ 
/*     */     
/* 468 */     if (index == null) {
/* 469 */       return (this._emptyKeyIndex != null) ? this._emptyKeyIndex : (this._emptyKeyIndex = new KeyIndex(1));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 474 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildKeys(DOM document, DTMAxisIterator iterator, SerializationHandler handler, int root) throws TransletException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeyIndexDom(String name, DOM document) {
/* 492 */     getKeyIndex(name).setDom(document);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 501 */   private DOMCache _domCache = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDOMCache(DOMCache cache) {
/* 508 */     this._domCache = cache;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DOMCache getDOMCache() {
/* 516 */     return this._domCache;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SerializationHandler openOutputHandler(String filename, boolean append) throws TransletException {
/*     */     
/* 528 */     try { TransletOutputHandlerFactory factory = TransletOutputHandlerFactory.newInstance();
/*     */ 
/*     */       
/* 531 */       factory.setEncoding(this._encoding);
/* 532 */       factory.setOutputMethod(this._method);
/* 533 */       factory.setWriter(new FileWriter(filename, append));
/* 534 */       factory.setOutputType(0);
/*     */       
/* 536 */       SerializationHandler handler = factory.getSerializationHandler();
/*     */ 
/*     */       
/* 539 */       transferOutputSettings(handler);
/* 540 */       handler.startDocument();
/* 541 */       return handler; } catch (Exception e)
/*     */     
/*     */     { 
/* 544 */       throw new TransletException(e); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SerializationHandler openOutputHandler(String filename) throws TransletException {
/* 551 */     return openOutputHandler(filename, false);
/*     */   }
/*     */   
/*     */   public void closeOutputHandler(SerializationHandler handler) {
/*     */     
/* 556 */     try { handler.endDocument();
/* 557 */       handler.close(); } catch (Exception exception) {}
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
/*     */   public abstract void transform(DOM paramDOM, DTMAxisIterator paramDTMAxisIterator, SerializationHandler paramSerializationHandler) throws TransletException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void transform(DOM document, SerializationHandler handler) throws TransletException {
/* 580 */     transform(document, document.getIterator(), handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void characters(String string, SerializationHandler handler) throws TransletException {
/* 590 */     if (string != null) {
/*     */ 
/*     */       
/* 593 */       try { handler.characters(string); } catch (Exception e)
/*     */       
/* 595 */       { throw new TransletException(e); }
/*     */     
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCdataElement(String name) {
/* 604 */     if (this._cdata == null) {
/* 605 */       this._cdata = new Vector();
/*     */     }
/*     */     
/* 608 */     int lastColon = name.lastIndexOf(':');
/*     */     
/* 610 */     if (lastColon > 0) {
/* 611 */       String uri = name.substring(0, lastColon);
/* 612 */       String localName = name.substring(lastColon + 1);
/* 613 */       this._cdata.addElement(uri);
/* 614 */       this._cdata.addElement(localName);
/*     */     } else {
/* 616 */       this._cdata.addElement(null);
/* 617 */       this._cdata.addElement(name);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void transferOutputSettings(SerializationHandler handler) {
/* 625 */     if (this._method != null) {
/* 626 */       if (this._method.equals("xml")) {
/* 627 */         if (this._standalone != null) {
/* 628 */           handler.setStandalone(this._standalone);
/*     */         }
/* 630 */         if (this._omitHeader) {
/* 631 */           handler.setOmitXMLDeclaration(true);
/*     */         }
/* 633 */         handler.setCdataSectionElements(this._cdata);
/* 634 */         if (this._version != null) {
/* 635 */           handler.setVersion(this._version);
/*     */         }
/* 637 */         handler.setIndent(this._indent);
/* 638 */         if (this._doctypeSystem != null) {
/* 639 */           handler.setDoctype(this._doctypeSystem, this._doctypePublic);
/*     */         }
/*     */       }
/* 642 */       else if (this._method.equals("html")) {
/* 643 */         handler.setIndent(this._indent);
/* 644 */         handler.setDoctype(this._doctypeSystem, this._doctypePublic);
/* 645 */         if (this._mediaType != null) {
/* 646 */           handler.setMediaType(this._mediaType);
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 651 */       handler.setCdataSectionElements(this._cdata);
/* 652 */       if (this._version != null) {
/* 653 */         handler.setVersion(this._version);
/*     */       }
/* 655 */       if (this._standalone != null) {
/* 656 */         handler.setStandalone(this._standalone);
/*     */       }
/* 658 */       if (this._omitHeader) {
/* 659 */         handler.setOmitXMLDeclaration(true);
/*     */       }
/* 661 */       handler.setIndent(this._indent);
/* 662 */       handler.setDoctype(this._doctypeSystem, this._doctypePublic);
/*     */     } 
/*     */   }
/*     */   
/* 666 */   private Hashtable _auxClasses = null;
/*     */   
/*     */   public void addAuxiliaryClass(Class auxClass) {
/* 669 */     if (this._auxClasses == null) this._auxClasses = new Hashtable(); 
/* 670 */     this._auxClasses.put(auxClass.getName(), auxClass);
/*     */   }
/*     */   
/*     */   public void setAuxiliaryClasses(Hashtable auxClasses) {
/* 674 */     this._auxClasses = auxClasses;
/*     */   }
/*     */   
/*     */   public Class getAuxiliaryClass(String className) {
/* 678 */     if (this._auxClasses == null) return null; 
/* 679 */     return (Class)this._auxClasses.get(className);
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getNamesArray() {
/* 684 */     return this.namesArray;
/*     */   }
/*     */   
/*     */   public String[] getUrisArray() {
/* 688 */     return this.urisArray;
/*     */   }
/*     */   
/*     */   public int[] getTypesArray() {
/* 692 */     return this.typesArray;
/*     */   }
/*     */   
/*     */   public String[] getNamespaceArray() {
/* 696 */     return this.namespaceArray;
/*     */   }
/*     */   
/*     */   public boolean hasIdCall() {
/* 700 */     return this._hasIdCall;
/*     */   }
/*     */   
/*     */   public Templates getTemplates() {
/* 704 */     return this._templates;
/*     */   }
/*     */   
/*     */   public void setTemplates(Templates templates) {
/* 708 */     this._templates = templates;
/*     */   }
/*     */   
/*     */   public abstract void transform(DOM paramDOM, SerializationHandler[] paramArrayOfSerializationHandler) throws TransletException;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/AbstractTranslet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */