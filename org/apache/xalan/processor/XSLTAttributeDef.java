/*      */ package org.apache.xalan.processor;
/*      */ 
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xalan.res.XSLMessages;
/*      */ import org.apache.xalan.templates.AVT;
/*      */ import org.apache.xalan.templates.ElemTemplateElement;
/*      */ import org.apache.xml.utils.QName;
/*      */ import org.apache.xml.utils.StringToIntTable;
/*      */ import org.apache.xml.utils.StringVector;
/*      */ import org.apache.xml.utils.XMLChar;
/*      */ import org.apache.xpath.XPath;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class XSLTAttributeDef
/*      */ {
/*      */   static final int FATAL = 0;
/*      */   static final int ERROR = 1;
/*      */   static final int WARNING = 2;
/*      */   static final int T_CDATA = 1;
/*      */   static final int T_URL = 2;
/*      */   static final int T_AVT = 3;
/*      */   static final int T_PATTERN = 4;
/*      */   static final int T_EXPR = 5;
/*      */   static final int T_CHAR = 6;
/*      */   static final int T_NUMBER = 7;
/*      */   static final int T_YESNO = 8;
/*      */   static final int T_QNAME = 9;
/*      */   static final int T_QNAMES = 10;
/*      */   static final int T_ENUM = 11;
/*      */   static final int T_SIMPLEPATTERNLIST = 12;
/*      */   static final int T_NMTOKEN = 13;
/*      */   static final int T_STRINGLIST = 14;
/*      */   static final int T_PREFIX_URLLIST = 15;
/*      */   static final int T_ENUM_OR_PQNAME = 16;
/*      */   static final int T_NCNAME = 17;
/*      */   static final int T_AVT_QNAME = 18;
/*      */   static final int T_QNAMES_RESOLVE_NULL = 19;
/*      */   static final int T_PREFIXLIST = 20;
/*      */   
/*      */   XSLTAttributeDef(String namespace, String name, int type, boolean required, boolean supportsAVT, int errorType) {
/*  414 */     this.m_errorType = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  429 */     this.m_setterString = null; this.m_namespace = namespace; this.m_name = name; this.m_type = type; this.m_required = required; this.m_supportsAVT = supportsAVT; this.m_errorType = errorType; } XSLTAttributeDef(String namespace, String name, int type, boolean supportsAVT, int errorType, String defaultVal) { this.m_errorType = 2; this.m_setterString = null; this.m_namespace = namespace; this.m_name = name; this.m_type = type; this.m_required = false; this.m_supportsAVT = supportsAVT; this.m_errorType = errorType; this.m_default = defaultVal; } XSLTAttributeDef(String namespace, String name, boolean required, boolean supportsAVT, boolean prefixedQNameValAllowed, int errorType, String k1, int v1, String k2, int v2) { this.m_errorType = 2; this.m_setterString = null; this.m_namespace = namespace; this.m_name = name; this.m_type = prefixedQNameValAllowed ? 16 : 11; this.m_required = required; this.m_supportsAVT = supportsAVT; this.m_errorType = errorType; this.m_enums = new StringToIntTable(2); this.m_enums.put(k1, v1); this.m_enums.put(k2, v2); } XSLTAttributeDef(String namespace, String name, boolean required, boolean supportsAVT, boolean prefixedQNameValAllowed, int errorType, String k1, int v1, String k2, int v2, String k3, int v3) { this.m_errorType = 2; this.m_setterString = null; this.m_namespace = namespace; this.m_name = name; this.m_type = prefixedQNameValAllowed ? 16 : 11; this.m_required = required; this.m_supportsAVT = supportsAVT; this.m_errorType = errorType; this.m_enums = new StringToIntTable(3); this.m_enums.put(k1, v1); this.m_enums.put(k2, v2); this.m_enums.put(k3, v3); } XSLTAttributeDef(String namespace, String name, boolean required, boolean supportsAVT, boolean prefixedQNameValAllowed, int errorType, String k1, int v1, String k2, int v2, String k3, int v3, String k4, int v4) { this.m_errorType = 2; this.m_setterString = null;
/*      */     this.m_namespace = namespace;
/*      */     this.m_name = name;
/*      */     this.m_type = prefixedQNameValAllowed ? 16 : 11;
/*      */     this.m_required = required;
/*      */     this.m_supportsAVT = supportsAVT;
/*      */     this.m_errorType = errorType;
/*      */     this.m_enums = new StringToIntTable(4);
/*      */     this.m_enums.put(k1, v1);
/*      */     this.m_enums.put(k2, v2);
/*      */     this.m_enums.put(k3, v3);
/*      */     this.m_enums.put(k4, v4); }
/*      */   
/*      */   static XSLTAttributeDef m_foreignAttr = new XSLTAttributeDef("*", "*", 1, false, false, 2); static String S_FOREIGNATTR_SETTER = "setForeignAttr"; private String m_namespace; private String m_name; private int m_type; private StringToIntTable m_enums; private String m_default; private boolean m_required; private boolean m_supportsAVT; int m_errorType;
/*      */   public String getSetterMethodName() {
/*  444 */     if (null == this.m_setterString) {
/*      */       
/*  446 */       if (m_foreignAttr == this)
/*      */       {
/*  448 */         return S_FOREIGNATTR_SETTER;
/*      */       }
/*  450 */       if (this.m_name.equals("*")) {
/*      */         
/*  452 */         this.m_setterString = "addLiteralResultAttribute";
/*      */         
/*  454 */         return this.m_setterString;
/*      */       } 
/*      */       
/*  457 */       StringBuffer outBuf = new StringBuffer();
/*      */       
/*  459 */       outBuf.append("set");
/*      */       
/*  461 */       if (this.m_namespace != null && this.m_namespace.equals("http://www.w3.org/XML/1998/namespace"))
/*      */       {
/*      */         
/*  464 */         outBuf.append("Xml");
/*      */       }
/*      */       
/*  467 */       int n = this.m_name.length();
/*      */       
/*  469 */       for (int i = 0; i < n; i++) {
/*      */         
/*  471 */         char c = this.m_name.charAt(i);
/*      */         
/*  473 */         if ('-' == c) {
/*      */           
/*  475 */           i++;
/*      */           
/*  477 */           c = this.m_name.charAt(i);
/*  478 */           c = Character.toUpperCase(c);
/*      */         }
/*  480 */         else if (0 == i) {
/*      */           
/*  482 */           c = Character.toUpperCase(c);
/*      */         } 
/*      */         
/*  485 */         outBuf.append(c);
/*      */       } 
/*      */       
/*  488 */       this.m_setterString = outBuf.toString();
/*      */     } 
/*      */     
/*  491 */     return this.m_setterString;
/*      */   }
/*      */ 
/*      */   
/*      */   String m_setterString;
/*      */ 
/*      */   
/*      */   String getNamespace() {
/*      */     return this.m_namespace;
/*      */   }
/*      */ 
/*      */   
/*      */   String getName() {
/*      */     return this.m_name;
/*      */   }
/*      */ 
/*      */   
/*      */   int getType() {
/*      */     return this.m_type;
/*      */   }
/*      */   
/*      */   private int getEnum(String key) {
/*      */     return this.m_enums.get(key);
/*      */   }
/*      */   
/*      */   AVT processAVT(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*      */     
/*  518 */     try { AVT avt = new AVT(handler, uri, name, rawName, value, owner);
/*      */       
/*  520 */       return avt; } catch (TransformerException te)
/*      */     
/*      */     { 
/*      */       
/*  524 */       throw new SAXException(te); }
/*      */   
/*      */   }
/*      */   private String[] getEnumNames() {
/*      */     return this.m_enums.keys();
/*      */   }
/*      */   String getDefault() {
/*      */     return this.m_default;
/*      */   }
/*      */   void setDefault(String def) {
/*      */     this.m_default = def;
/*      */   }
/*      */   boolean getRequired() {
/*      */     return this.m_required;
/*      */   }
/*      */   boolean getSupportsAVT() {
/*      */     return this.m_supportsAVT;
/*      */   }
/*      */   int getErrorType() {
/*      */     return this.m_errorType;
/*      */   }
/*      */   Object processCDATA(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*  546 */     if (getSupportsAVT()) {
/*      */ 
/*      */       
/*  549 */       try { AVT avt = new AVT(handler, uri, name, rawName, value, owner);
/*  550 */         return avt; } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */         
/*  554 */         throw new SAXException(te); }
/*      */     
/*      */     }
/*  557 */     return value;
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
/*      */   Object processCHAR(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*  579 */     if (getSupportsAVT()) {
/*      */ 
/*      */       
/*  582 */       try { AVT avt = new AVT(handler, uri, name, rawName, value, owner);
/*      */ 
/*      */         
/*  585 */         if (avt.isSimple() && value.length() != 1) {
/*  586 */           handleError(handler, "INVALID_TCHAR", new Object[] { name, value }, null);
/*  587 */           return null;
/*      */         } 
/*  589 */         return avt; } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */         
/*  593 */         throw new SAXException(te); }
/*      */     
/*      */     }
/*  596 */     if (value.length() != 1) {
/*      */       
/*  598 */       handleError(handler, "INVALID_TCHAR", new Object[] { name, value }, null);
/*  599 */       return null;
/*      */     } 
/*      */     
/*  602 */     return new Character(value.charAt(0));
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
/*      */   Object processENUM(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*  625 */     AVT avt = null;
/*  626 */     if (getSupportsAVT()) {
/*      */ 
/*      */       
/*  629 */       try { avt = new AVT(handler, uri, name, rawName, value, owner);
/*      */ 
/*      */         
/*  632 */         if (!avt.isSimple()) return avt;  } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */         
/*  636 */         throw new SAXException(te); }
/*      */     
/*      */     }
/*      */     
/*  640 */     int retVal = getEnum(value);
/*      */     
/*  642 */     if (retVal == -10000) {
/*      */       
/*  644 */       StringBuffer enumNamesList = getListOfEnums();
/*  645 */       handleError(handler, "INVALID_ENUM", new Object[] { name, value, enumNamesList.toString() }, null);
/*  646 */       return null;
/*      */     } 
/*      */     
/*  649 */     if (getSupportsAVT()) return avt; 
/*  650 */     return new Integer(retVal);
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
/*      */   Object processENUM_OR_PQNAME(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*  675 */     Object objToReturn = null;
/*      */     
/*  677 */     if (getSupportsAVT()) {
/*      */ 
/*      */       
/*  680 */       try { AVT avt = new AVT(handler, uri, name, rawName, value, owner);
/*  681 */         if (!avt.isSimple()) return avt; 
/*  682 */         objToReturn = avt; } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */         
/*  686 */         throw new SAXException(te); }
/*      */     
/*      */     }
/*      */ 
/*      */     
/*  691 */     int key = getEnum(value);
/*      */     
/*  693 */     if (key != -10000) {
/*      */       
/*  695 */       if (objToReturn == null) objToReturn = new Integer(key);
/*      */     
/*      */     } else {
/*      */ 
/*      */ 
/*      */       
/*      */       try { 
/*      */         
/*  703 */         QName qname = new QName(value, handler, true);
/*  704 */         if (objToReturn == null) objToReturn = qname;
/*      */         
/*  706 */         if (qname.getPrefix() == null)
/*  707 */         { StringBuffer enumNamesList = getListOfEnums();
/*      */           
/*  709 */           enumNamesList.append(" <qname-but-not-ncname>");
/*  710 */           handleError(handler, "INVALID_ENUM", new Object[] { name, value, enumNamesList.toString() }, null);
/*  711 */           return null; }  } catch (IllegalArgumentException ie)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */         
/*  717 */         StringBuffer enumNamesList = getListOfEnums();
/*  718 */         enumNamesList.append(" <qname-but-not-ncname>");
/*      */         
/*  720 */         handleError(handler, "INVALID_ENUM", new Object[] { name, value, enumNamesList.toString() }, ie);
/*  721 */         return null; } catch (RuntimeException re)
/*      */       
/*      */       { 
/*      */ 
/*      */         
/*  726 */         StringBuffer enumNamesList = getListOfEnums();
/*  727 */         enumNamesList.append(" <qname-but-not-ncname>");
/*      */         
/*  729 */         handleError(handler, "INVALID_ENUM", new Object[] { name, value, enumNamesList.toString() }, re);
/*  730 */         return null; }
/*      */     
/*      */     } 
/*      */     
/*  734 */     return objToReturn;
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
/*      */   Object processEXPR(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*      */     
/*  761 */     try { XPath expr = handler.createXPath(value, owner);
/*      */       
/*  763 */       return expr; } catch (TransformerException te)
/*      */     
/*      */     { 
/*      */       
/*  767 */       SAXException se = new SAXException(te);
/*  768 */       throw new SAXException(te); }
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
/*      */   Object processNMTOKEN(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*  791 */     if (getSupportsAVT()) {
/*      */ 
/*      */       
/*  794 */       try { AVT avt = new AVT(handler, uri, name, rawName, value, owner);
/*      */ 
/*      */         
/*  797 */         if (avt.isSimple() && !XMLChar.isValidNmtoken(value)) {
/*  798 */           handleError(handler, "INVALID_NMTOKEN", new Object[] { name, value }, null);
/*  799 */           return null;
/*      */         } 
/*  801 */         return avt; } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */         
/*  805 */         throw new SAXException(te); }
/*      */     
/*      */     }
/*  808 */     if (!XMLChar.isValidNmtoken(value)) {
/*  809 */       handleError(handler, "INVALID_NMTOKEN", new Object[] { name, value }, null);
/*  810 */       return null;
/*      */     } 
/*      */     
/*  813 */     return value;
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
/*      */   Object processPATTERN(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*      */     
/*  840 */     try { XPath pattern = handler.createMatchPatternXPath(value, owner);
/*      */       
/*  842 */       return pattern; } catch (TransformerException te)
/*      */     
/*      */     { 
/*      */       
/*  846 */       throw new SAXException(te); }
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
/*      */   Object processNUMBER(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*  873 */     if (getSupportsAVT()) {
/*      */ 
/*      */       
/*  876 */       AVT avt = null;
/*      */ 
/*      */       
/*  879 */       try { avt = new AVT(handler, uri, name, rawName, value, owner);
/*      */ 
/*      */         
/*  882 */         if (avt.isSimple())
/*      */         {
/*  884 */           Double val = Double.valueOf(value); }  } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */ 
/*      */         
/*  889 */         throw new SAXException(te); } catch (NumberFormatException nfe)
/*      */       
/*      */       { 
/*      */         
/*  893 */         handleError(handler, "INVALID_NUMBER", new Object[] { name, value }, nfe);
/*  894 */         return null; }
/*      */       
/*  896 */       return avt;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  903 */     try { return Double.valueOf(value); } catch (NumberFormatException nfe)
/*      */     
/*      */     { 
/*      */       
/*  907 */       handleError(handler, "INVALID_NUMBER", new Object[] { name, value }, nfe);
/*  908 */       return null; }
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
/*      */   Object processQNAME(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*      */     
/*  936 */     try { QName qname = new QName(value, handler, true);
/*  937 */       return qname; } catch (IllegalArgumentException ie)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  942 */       handleError(handler, "INVALID_QNAME", new Object[] { name, value }, ie);
/*  943 */       return null; } catch (RuntimeException re)
/*      */     
/*      */     { 
/*      */       
/*  947 */       handleError(handler, "INVALID_QNAME", new Object[] { name, value }, re);
/*  948 */       return null; }
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
/*      */   Object processAVT_QNAME(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*  973 */     AVT avt = null;
/*      */ 
/*      */     
/*  976 */     try { avt = new AVT(handler, uri, name, rawName, value, owner);
/*      */ 
/*      */       
/*  979 */       if (avt.isSimple())
/*      */       
/*  981 */       { int indexOfNSSep = value.indexOf(':');
/*      */         
/*  983 */         if (indexOfNSSep >= 0) {
/*      */           
/*  985 */           String prefix = value.substring(0, indexOfNSSep);
/*  986 */           if (!XMLChar.isValidNCName(prefix)) {
/*      */             
/*  988 */             handleError(handler, "INVALID_QNAME", new Object[] { name, value }, null);
/*  989 */             return null;
/*      */           } 
/*      */         } 
/*      */         
/*  993 */         String localName = (indexOfNSSep < 0) ? value : value.substring(indexOfNSSep + 1);
/*      */ 
/*      */         
/*  996 */         if (localName == null || localName.length() == 0 || !XMLChar.isValidNCName(localName))
/*      */         
/*      */         { 
/*  999 */           handleError(handler, "INVALID_QNAME", new Object[] { name, value }, null);
/* 1000 */           return null; }  }  } catch (TransformerException te)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1007 */       throw new SAXException(te); }
/*      */ 
/*      */     
/* 1010 */     return avt;
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
/*      */   Object processNCNAME(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/* 1034 */     if (getSupportsAVT()) {
/*      */       
/* 1036 */       AVT avt = null;
/*      */ 
/*      */       
/* 1039 */       try { avt = new AVT(handler, uri, name, rawName, value, owner);
/*      */ 
/*      */         
/* 1042 */         if (avt.isSimple() && !XMLChar.isValidNCName(value)) {
/*      */           
/* 1044 */           handleError(handler, "INVALID_NCNAME", new Object[] { name, value }, null);
/* 1045 */           return null;
/*      */         } 
/* 1047 */         return avt; } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */ 
/*      */         
/* 1052 */         throw new SAXException(te); }
/*      */     
/*      */     } 
/*      */     
/* 1056 */     if (!XMLChar.isValidNCName(value)) {
/*      */       
/* 1058 */       handleError(handler, "INVALID_NCNAME", new Object[] { name, value }, null);
/* 1059 */       return null;
/*      */     } 
/* 1061 */     return value;
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
/*      */   Vector processQNAMES(StylesheetHandler handler, String uri, String name, String rawName, String value) throws SAXException {
/* 1087 */     StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
/* 1088 */     int nQNames = tokenizer.countTokens();
/* 1089 */     Vector qnames = new Vector(nQNames);
/*      */     
/* 1091 */     for (int i = 0; i < nQNames; i++)
/*      */     {
/*      */       
/* 1094 */       qnames.addElement(new QName(tokenizer.nextToken(), handler));
/*      */     }
/*      */     
/* 1097 */     return qnames;
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
/*      */   final Vector processQNAMESRNU(StylesheetHandler handler, String uri, String name, String rawName, String value) throws SAXException {
/* 1124 */     StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
/* 1125 */     int nQNames = tokenizer.countTokens();
/* 1126 */     Vector qnames = new Vector(nQNames);
/*      */     
/* 1128 */     String defaultURI = handler.getNamespaceForPrefix("");
/* 1129 */     for (int i = 0; i < nQNames; i++) {
/*      */       
/* 1131 */       String tok = tokenizer.nextToken();
/* 1132 */       if (tok.indexOf(':') == -1) {
/* 1133 */         qnames.addElement(new QName(defaultURI, tok));
/*      */       } else {
/* 1135 */         qnames.addElement(new QName(tok, handler));
/*      */       } 
/*      */     } 
/* 1138 */     return qnames;
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
/*      */   Vector processSIMPLEPATTERNLIST(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/*      */     
/* 1165 */     try { StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
/* 1166 */       int nPatterns = tokenizer.countTokens();
/* 1167 */       Vector patterns = new Vector(nPatterns);
/*      */       
/* 1169 */       for (int i = 0; i < nPatterns; i++) {
/*      */         
/* 1171 */         XPath pattern = handler.createMatchPatternXPath(tokenizer.nextToken(), owner);
/*      */ 
/*      */         
/* 1174 */         patterns.addElement(pattern);
/*      */       } 
/*      */       
/* 1177 */       return patterns; } catch (TransformerException te)
/*      */     
/*      */     { 
/*      */       
/* 1181 */       throw new SAXException(te); }
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
/*      */   StringVector processSTRINGLIST(StylesheetHandler handler, String uri, String name, String rawName, String value) {
/* 1201 */     StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
/* 1202 */     int nStrings = tokenizer.countTokens();
/* 1203 */     StringVector strings = new StringVector(nStrings);
/*      */     
/* 1205 */     for (int i = 0; i < nStrings; i++)
/*      */     {
/* 1207 */       strings.addElement(tokenizer.nextToken());
/*      */     }
/*      */     
/* 1210 */     return strings;
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
/*      */   StringVector processPREFIX_URLLIST(StylesheetHandler handler, String uri, String name, String rawName, String value) throws SAXException {
/* 1232 */     StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
/* 1233 */     int nStrings = tokenizer.countTokens();
/* 1234 */     StringVector strings = new StringVector(nStrings);
/*      */     
/* 1236 */     for (int i = 0; i < nStrings; i++) {
/*      */       
/* 1238 */       String prefix = tokenizer.nextToken();
/* 1239 */       String url = handler.getNamespaceForPrefix(prefix);
/*      */       
/* 1241 */       if (url != null) {
/* 1242 */         strings.addElement(url);
/*      */       } else {
/* 1244 */         throw new SAXException(XSLMessages.createMessage("ER_CANT_RESOLVE_NSPREFIX", new Object[] { prefix }));
/*      */       } 
/*      */     } 
/*      */     
/* 1248 */     return strings;
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
/*      */   StringVector processPREFIX_LIST(StylesheetHandler handler, String uri, String name, String rawName, String value) throws SAXException {
/* 1270 */     StringTokenizer tokenizer = new StringTokenizer(value, " \t\n\r\f");
/* 1271 */     int nStrings = tokenizer.countTokens();
/* 1272 */     StringVector strings = new StringVector(nStrings);
/*      */     
/* 1274 */     for (int i = 0; i < nStrings; i++) {
/*      */       
/* 1276 */       String prefix = tokenizer.nextToken();
/* 1277 */       String url = handler.getNamespaceForPrefix(prefix);
/* 1278 */       if (prefix.equals("#default") || url != null) {
/* 1279 */         strings.addElement(prefix);
/*      */       } else {
/* 1281 */         throw new SAXException(XSLMessages.createMessage("ER_CANT_RESOLVE_NSPREFIX", new Object[] { prefix }));
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1288 */     return strings;
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
/*      */   Object processURL(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/* 1312 */     if (getSupportsAVT()) {
/*      */ 
/*      */       
/* 1315 */       try { AVT avt = new AVT(handler, uri, name, rawName, value, owner);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1323 */         return avt; } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */         
/* 1327 */         throw new SAXException(te); }
/*      */     
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1334 */     return value;
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
/*      */   private Boolean processYESNO(StylesheetHandler handler, String uri, String name, String rawName, String value) throws SAXException {
/* 1358 */     if (!value.equals("yes") && !value.equals("no")) {
/*      */       
/* 1360 */       handleError(handler, "INVALID_BOOLEAN", new Object[] { name, value }, null);
/* 1361 */       return null;
/*      */     } 
/*      */     
/* 1364 */     return new Boolean(value.equals("yes"));
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
/*      */   Object processValue(StylesheetHandler handler, String uri, String name, String rawName, String value, ElemTemplateElement owner) throws SAXException {
/* 1386 */     int type = getType();
/* 1387 */     Object processedValue = null;
/*      */     
/* 1389 */     switch (type) {
/*      */       
/*      */       case 3:
/* 1392 */         processedValue = processAVT(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 1:
/* 1395 */         processedValue = processCDATA(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 6:
/* 1398 */         processedValue = processCHAR(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 11:
/* 1401 */         processedValue = processENUM(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 5:
/* 1404 */         processedValue = processEXPR(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 13:
/* 1407 */         processedValue = processNMTOKEN(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 4:
/* 1410 */         processedValue = processPATTERN(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 7:
/* 1413 */         processedValue = processNUMBER(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 9:
/* 1416 */         processedValue = processQNAME(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 10:
/* 1419 */         processedValue = processQNAMES(handler, uri, name, rawName, value);
/*      */         break;
/*      */       case 19:
/* 1422 */         processedValue = processQNAMESRNU(handler, uri, name, rawName, value);
/*      */         break;
/*      */       case 12:
/* 1425 */         processedValue = processSIMPLEPATTERNLIST(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       
/*      */       case 2:
/* 1429 */         processedValue = processURL(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 8:
/* 1432 */         processedValue = processYESNO(handler, uri, name, rawName, value);
/*      */         break;
/*      */       case 14:
/* 1435 */         processedValue = processSTRINGLIST(handler, uri, name, rawName, value);
/*      */         break;
/*      */       case 15:
/* 1438 */         processedValue = processPREFIX_URLLIST(handler, uri, name, rawName, value);
/*      */         break;
/*      */       
/*      */       case 16:
/* 1442 */         processedValue = processENUM_OR_PQNAME(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 17:
/* 1445 */         processedValue = processNCNAME(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 18:
/* 1448 */         processedValue = processAVT_QNAME(handler, uri, name, rawName, value, owner);
/*      */         break;
/*      */       case 20:
/* 1451 */         processedValue = processPREFIX_LIST(handler, uri, name, rawName, value);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1458 */     return processedValue;
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
/*      */   void setDefAttrValue(StylesheetHandler handler, ElemTemplateElement elem) throws SAXException {
/* 1473 */     setAttrValue(handler, getNamespace(), getName(), getName(), getDefault(), elem);
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
/*      */   private Class getPrimativeClass(Object obj) {
/* 1491 */     if (obj instanceof XPath) {
/* 1492 */       return XPath.class;
/*      */     }
/* 1494 */     Class cl = obj.getClass();
/*      */     
/* 1496 */     if (cl == Double.class)
/*      */     {
/* 1498 */       cl = double.class;
/*      */     }
/*      */     
/* 1501 */     if (cl == Float.class) {
/*      */       
/* 1503 */       cl = float.class;
/*      */     }
/* 1505 */     else if (cl == Boolean.class) {
/*      */       
/* 1507 */       cl = boolean.class;
/*      */     }
/* 1509 */     else if (cl == Byte.class) {
/*      */       
/* 1511 */       cl = byte.class;
/*      */     }
/* 1513 */     else if (cl == Character.class) {
/*      */       
/* 1515 */       cl = char.class;
/*      */     }
/* 1517 */     else if (cl == Short.class) {
/*      */       
/* 1519 */       cl = short.class;
/*      */     }
/* 1521 */     else if (cl == Integer.class) {
/*      */       
/* 1523 */       cl = int.class;
/*      */     }
/* 1525 */     else if (cl == Long.class) {
/*      */       
/* 1527 */       cl = long.class;
/*      */     } 
/*      */     
/* 1530 */     return cl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private StringBuffer getListOfEnums() {
/* 1539 */     StringBuffer enumNamesList = new StringBuffer();
/* 1540 */     String[] enumValues = getEnumNames();
/*      */     
/* 1542 */     for (int i = 0; i < enumValues.length; i++) {
/*      */       
/* 1544 */       if (i > 0)
/*      */       {
/* 1546 */         enumNamesList.append(' ');
/*      */       }
/* 1548 */       enumNamesList.append(enumValues[i]);
/*      */     } 
/* 1550 */     return enumNamesList;
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
/*      */   boolean setAttrValue(StylesheetHandler handler, String attrUri, String attrLocalName, String attrRawName, String attrValue, ElemTemplateElement elem) throws SAXException {
/* 1570 */     if (attrRawName.equals("xmlns") || attrRawName.startsWith("xmlns:")) {
/* 1571 */       return true;
/*      */     }
/* 1573 */     String setterString = getSetterMethodName();
/*      */ 
/*      */ 
/*      */     
/* 1577 */     if (null != setterString) {
/*      */       
/*      */       try { Method method;
/*      */ 
/*      */         
/*      */         Object[] arrayOfObject;
/*      */         
/* 1584 */         if (setterString.equals(S_FOREIGNATTR_SETTER)) {
/*      */ 
/*      */           
/* 1587 */           if (attrUri == null) attrUri = "";
/*      */           
/* 1589 */           Class sclass = attrUri.getClass();
/* 1590 */           Class[] argTypes = { sclass, sclass, sclass, sclass };
/*      */ 
/*      */           
/* 1593 */           method = elem.getClass().getMethod(setterString, argTypes);
/*      */           
/* 1595 */           arrayOfObject = new Object[] { attrUri, attrLocalName, attrRawName, attrValue };
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1600 */           Object value = processValue(handler, attrUri, attrLocalName, attrRawName, attrValue, elem);
/*      */ 
/*      */ 
/*      */           
/* 1604 */           if (null == value) return false;
/*      */ 
/*      */           
/* 1607 */           Class[] argTypes = { getPrimativeClass(value) };
/*      */ 
/*      */ 
/*      */           
/* 1611 */           try { method = elem.getClass().getMethod(setterString, argTypes); } catch (NoSuchMethodException nsme)
/*      */           
/*      */           { 
/*      */             
/* 1615 */             Class cl = value.getClass();
/*      */ 
/*      */             
/* 1618 */             argTypes[0] = cl;
/* 1619 */             method = elem.getClass().getMethod(setterString, argTypes); }
/*      */ 
/*      */           
/* 1622 */           arrayOfObject = new Object[] { value };
/*      */         } 
/*      */         
/* 1625 */         method.invoke(elem, arrayOfObject); } catch (NoSuchMethodException nsme)
/*      */       
/*      */       { 
/*      */         
/* 1629 */         if (!setterString.equals(S_FOREIGNATTR_SETTER))
/*      */         
/* 1631 */         { handler.error("ER_FAILED_CALLING_METHOD", new Object[] { setterString }, nsme);
/* 1632 */           return false; }  } catch (IllegalAccessException iae)
/*      */       
/*      */       { 
/*      */ 
/*      */         
/* 1637 */         handler.error("ER_FAILED_CALLING_METHOD", new Object[] { setterString }, iae);
/* 1638 */         return false; } catch (InvocationTargetException nsme)
/*      */       
/*      */       { 
/*      */         
/* 1642 */         handleError(handler, "WG_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "name", getName() }, nsme);
/*      */         
/* 1644 */         return false; }
/*      */     
/*      */     }
/*      */     
/* 1648 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   private void handleError(StylesheetHandler handler, String msg, Object[] args, Exception exc) throws SAXException {
/* 1653 */     switch (getErrorType()) {
/*      */       
/*      */       case 0:
/*      */       case 1:
/* 1657 */         handler.error(msg, args, exc);
/*      */         break;
/*      */       case 2:
/* 1660 */         handler.warn(msg, args);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/processor/XSLTAttributeDef.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */