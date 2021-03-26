/*      */ package org.apache.xalan.templates;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Stack;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xml.utils.QName;
/*      */ import org.apache.xml.utils.StringVector;
/*      */ import org.apache.xml.utils.SystemIDResolver;
/*      */ import org.apache.xml.utils.WrappedRuntimeException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Stylesheet
/*      */   extends ElemTemplateElement
/*      */   implements Serializable
/*      */ {
/*      */   public static final String STYLESHEET_EXT = ".lxc";
/*      */   private String m_XmlnsXsl;
/*      */   private StringVector m_ExtensionElementURIs;
/*      */   private StringVector m_ExcludeResultPrefixs;
/*      */   private String m_Id;
/*      */   private String m_Version;
/*      */   
/*      */   public Stylesheet(Stylesheet parent) {
/*   90 */     if (null != parent) {
/*      */       
/*   92 */       this.m_stylesheetParent = parent;
/*   93 */       this.m_stylesheetRoot = parent.getStylesheetRoot();
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
/*      */   public Stylesheet getStylesheet() {
/*  106 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAggregatedType() {
/*  117 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRoot() {
/*  127 */     return false;
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
/*      */   private void readObject(ObjectInputStream stream) throws IOException, TransformerException {
/*      */     
/*  150 */     try { stream.defaultReadObject(); } catch (ClassNotFoundException cnfe)
/*      */     
/*      */     { 
/*      */       
/*  154 */       throw new TransformerException(cnfe); }
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
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/*  172 */     stream.defaultWriteObject();
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
/*      */   public void setXmlnsXsl(String v) {
/*  193 */     this.m_XmlnsXsl = v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getXmlnsXsl() {
/*  204 */     return this.m_XmlnsXsl;
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
/*      */   public void setExtensionElementPrefixes(StringVector v) {
/*  222 */     this.m_ExtensionElementURIs = v;
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
/*      */   public String getExtensionElementPrefix(int i) throws ArrayIndexOutOfBoundsException {
/*  239 */     if (null == this.m_ExtensionElementURIs) {
/*  240 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/*  242 */     return this.m_ExtensionElementURIs.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExtensionElementPrefixCount() {
/*  253 */     return (null != this.m_ExtensionElementURIs) ? this.m_ExtensionElementURIs.size() : 0;
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
/*      */   public boolean containsExtensionElementURI(String uri) {
/*  268 */     if (null == this.m_ExtensionElementURIs) {
/*  269 */       return false;
/*      */     }
/*  271 */     return this.m_ExtensionElementURIs.contains(uri);
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
/*      */   public void setExcludeResultPrefixes(StringVector v) {
/*  294 */     this.m_ExcludeResultPrefixs = v;
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
/*      */   public String getExcludeResultPrefix(int i) throws ArrayIndexOutOfBoundsException {
/*  317 */     if (null == this.m_ExcludeResultPrefixs) {
/*  318 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/*  320 */     return this.m_ExcludeResultPrefixs.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExcludeResultPrefixCount() {
/*  331 */     return (null != this.m_ExcludeResultPrefixs) ? this.m_ExcludeResultPrefixs.size() : 0;
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
/*      */   public boolean containsExcludeResultPrefix(String prefix, String uri) {
/*  348 */     if (null == this.m_ExcludeResultPrefixs || uri == null) {
/*  349 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  353 */     for (int i = 0; i < this.m_ExcludeResultPrefixs.size(); i++) {
/*      */       
/*  355 */       if (uri.equals(getNamespaceForPrefix(this.m_ExcludeResultPrefixs.elementAt(i)))) {
/*  356 */         return true;
/*      */       }
/*      */     } 
/*  359 */     return false;
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
/*      */   public void setId(String v) {
/*  381 */     this.m_Id = v;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getId() {
/*  392 */     return this.m_Id;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean m_isCompatibleMode = false;
/*      */   
/*      */   private Vector m_imports;
/*      */   
/*      */   private Vector m_includes;
/*      */   
/*      */   Stack m_DecimalFormatDeclarations;
/*      */   
/*      */   private Vector m_whitespaceStrippingElements;
/*      */   private Vector m_whitespacePreservingElements;
/*      */   private Vector m_output;
/*      */   private Vector m_keyDeclarations;
/*      */   private Vector m_attributeSets;
/*      */   private Vector m_topLevelVariables;
/*      */   private Vector m_templates;
/*      */   private Vector m_prefix_aliases;
/*      */   private Hashtable m_NonXslTopLevel;
/*      */   
/*      */   public void setVersion(String v) {
/*  415 */     this.m_Version = v;
/*  416 */     this.m_isCompatibleMode = (Double.valueOf(v).doubleValue() > 1.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCompatibleMode() {
/*  426 */     return this.m_isCompatibleMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getVersion() {
/*  437 */     return this.m_Version;
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
/*      */   public void setImport(StylesheetComposed v) {
/*  455 */     if (null == this.m_imports) {
/*  456 */       this.m_imports = new Vector();
/*      */     }
/*      */ 
/*      */     
/*  460 */     this.m_imports.addElement(v);
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
/*      */   public StylesheetComposed getImport(int i) throws ArrayIndexOutOfBoundsException {
/*  477 */     if (null == this.m_imports) {
/*  478 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/*  480 */     return this.m_imports.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getImportCount() {
/*  491 */     return (null != this.m_imports) ? this.m_imports.size() : 0;
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
/*      */   public void setInclude(Stylesheet v) {
/*  509 */     if (null == this.m_includes) {
/*  510 */       this.m_includes = new Vector();
/*      */     }
/*  512 */     this.m_includes.addElement(v);
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
/*      */   public Stylesheet getInclude(int i) throws ArrayIndexOutOfBoundsException {
/*  528 */     if (null == this.m_includes) {
/*  529 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/*  531 */     return this.m_includes.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIncludeCount() {
/*  542 */     return (null != this.m_includes) ? this.m_includes.size() : 0;
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
/*      */   public void setDecimalFormat(DecimalFormatProperties edf) {
/*  560 */     if (null == this.m_DecimalFormatDeclarations) {
/*  561 */       this.m_DecimalFormatDeclarations = new Stack();
/*      */     }
/*      */ 
/*      */     
/*  565 */     this.m_DecimalFormatDeclarations.push(edf);
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
/*      */   public DecimalFormatProperties getDecimalFormat(QName name) {
/*  581 */     if (null == this.m_DecimalFormatDeclarations) {
/*  582 */       return null;
/*      */     }
/*  584 */     int n = getDecimalFormatCount();
/*      */     
/*  586 */     for (int i = n - 1; i >= 0; i++) {
/*      */       
/*  588 */       DecimalFormatProperties dfp = getDecimalFormat(i);
/*      */       
/*  590 */       if (dfp.getName().equals(name)) {
/*  591 */         return dfp;
/*      */       }
/*      */     } 
/*  594 */     return null;
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
/*      */   public DecimalFormatProperties getDecimalFormat(int i) throws ArrayIndexOutOfBoundsException {
/*  612 */     if (null == this.m_DecimalFormatDeclarations) {
/*  613 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/*  615 */     return this.m_DecimalFormatDeclarations.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDecimalFormatCount() {
/*  626 */     return (null != this.m_DecimalFormatDeclarations) ? this.m_DecimalFormatDeclarations.size() : 0;
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
/*      */   public void setStripSpaces(WhiteSpaceInfo wsi) {
/*  646 */     if (null == this.m_whitespaceStrippingElements)
/*      */     {
/*  648 */       this.m_whitespaceStrippingElements = new Vector();
/*      */     }
/*      */     
/*  651 */     this.m_whitespaceStrippingElements.addElement(wsi);
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
/*      */   public WhiteSpaceInfo getStripSpace(int i) throws ArrayIndexOutOfBoundsException {
/*  667 */     if (null == this.m_whitespaceStrippingElements) {
/*  668 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/*  670 */     return this.m_whitespaceStrippingElements.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStripSpaceCount() {
/*  681 */     return (null != this.m_whitespaceStrippingElements) ? this.m_whitespaceStrippingElements.size() : 0;
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
/*      */   public void setPreserveSpaces(WhiteSpaceInfo wsi) {
/*  701 */     if (null == this.m_whitespacePreservingElements)
/*      */     {
/*  703 */       this.m_whitespacePreservingElements = new Vector();
/*      */     }
/*      */     
/*  706 */     this.m_whitespacePreservingElements.addElement(wsi);
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
/*      */   public WhiteSpaceInfo getPreserveSpace(int i) throws ArrayIndexOutOfBoundsException {
/*  722 */     if (null == this.m_whitespacePreservingElements) {
/*  723 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/*  725 */     return this.m_whitespacePreservingElements.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPreserveSpaceCount() {
/*  736 */     return (null != this.m_whitespacePreservingElements) ? this.m_whitespacePreservingElements.size() : 0;
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
/*      */   public void setOutput(OutputProperties v) {
/*  755 */     if (null == this.m_output)
/*      */     {
/*  757 */       this.m_output = new Vector();
/*      */     }
/*      */     
/*  760 */     this.m_output.addElement(v);
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
/*      */   public OutputProperties getOutput(int i) throws ArrayIndexOutOfBoundsException {
/*  776 */     if (null == this.m_output) {
/*  777 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/*  779 */     return this.m_output.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOutputCount() {
/*  790 */     return (null != this.m_output) ? this.m_output.size() : 0;
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
/*      */   public void setKey(KeyDeclaration v) {
/*  809 */     if (null == this.m_keyDeclarations) {
/*  810 */       this.m_keyDeclarations = new Vector();
/*      */     }
/*  812 */     this.m_keyDeclarations.addElement(v);
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
/*      */   public KeyDeclaration getKey(int i) throws ArrayIndexOutOfBoundsException {
/*  828 */     if (null == this.m_keyDeclarations) {
/*  829 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/*  831 */     return this.m_keyDeclarations.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getKeyCount() {
/*  842 */     return (null != this.m_keyDeclarations) ? this.m_keyDeclarations.size() : 0;
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
/*      */   public void setAttributeSet(ElemAttributeSet attrSet) {
/*  860 */     if (null == this.m_attributeSets)
/*      */     {
/*  862 */       this.m_attributeSets = new Vector();
/*      */     }
/*      */     
/*  865 */     this.m_attributeSets.addElement(attrSet);
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
/*      */   public ElemAttributeSet getAttributeSet(int i) throws ArrayIndexOutOfBoundsException {
/*  882 */     if (null == this.m_attributeSets) {
/*  883 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/*  885 */     return this.m_attributeSets.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAttributeSetCount() {
/*  896 */     return (null != this.m_attributeSets) ? this.m_attributeSets.size() : 0;
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
/*      */   public void setVariable(ElemVariable v) {
/*  914 */     if (null == this.m_topLevelVariables) {
/*  915 */       this.m_topLevelVariables = new Vector();
/*      */     }
/*  917 */     this.m_topLevelVariables.addElement(v);
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
/*      */   public ElemVariable getVariableOrParam(QName qname) {
/*  931 */     if (null != this.m_topLevelVariables) {
/*      */       
/*  933 */       int n = getVariableOrParamCount();
/*      */       
/*  935 */       for (int i = 0; i < n; i++) {
/*      */         
/*  937 */         ElemVariable var = getVariableOrParam(i);
/*      */         
/*  939 */         if (var.getName().equals(qname)) {
/*  940 */           return var;
/*      */         }
/*      */       } 
/*      */     } 
/*  944 */     return null;
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
/*      */   public ElemVariable getVariable(QName qname) {
/*  959 */     if (null != this.m_topLevelVariables) {
/*      */       
/*  961 */       int n = getVariableOrParamCount();
/*      */       
/*  963 */       for (int i = 0; i < n; i++) {
/*      */         
/*  965 */         ElemVariable var = getVariableOrParam(i);
/*  966 */         if (var.getXSLToken() == 73 && var.getName().equals(qname))
/*      */         {
/*  968 */           return var;
/*      */         }
/*      */       } 
/*      */     } 
/*  972 */     return null;
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
/*      */   public ElemVariable getVariableOrParam(int i) throws ArrayIndexOutOfBoundsException {
/*  988 */     if (null == this.m_topLevelVariables) {
/*  989 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/*  991 */     return this.m_topLevelVariables.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVariableOrParamCount() {
/* 1002 */     return (null != this.m_topLevelVariables) ? this.m_topLevelVariables.size() : 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setParam(ElemParam v) {
/* 1013 */     setVariable(v);
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
/*      */   public ElemParam getParam(QName qname) {
/* 1027 */     if (null != this.m_topLevelVariables) {
/*      */       
/* 1029 */       int n = getVariableOrParamCount();
/*      */       
/* 1031 */       for (int i = 0; i < n; i++) {
/*      */         
/* 1033 */         ElemVariable var = getVariableOrParam(i);
/* 1034 */         if (var.getXSLToken() == 41 && var.getName().equals(qname))
/*      */         {
/* 1036 */           return (ElemParam)var;
/*      */         }
/*      */       } 
/*      */     } 
/* 1040 */     return null;
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
/*      */   public void setTemplate(ElemTemplate v) {
/* 1058 */     if (null == this.m_templates) {
/* 1059 */       this.m_templates = new Vector();
/*      */     }
/* 1061 */     this.m_templates.addElement(v);
/* 1062 */     v.setStylesheet(this);
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
/*      */   public ElemTemplate getTemplate(int i) throws TransformerException {
/* 1078 */     if (null == this.m_templates) {
/* 1079 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/* 1081 */     return this.m_templates.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTemplateCount() {
/* 1092 */     return (null != this.m_templates) ? this.m_templates.size() : 0;
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
/*      */   public void setNamespaceAlias(NamespaceAlias na) {
/* 1110 */     if (this.m_prefix_aliases == null) {
/* 1111 */       this.m_prefix_aliases = new Vector();
/*      */     }
/* 1113 */     this.m_prefix_aliases.addElement(na);
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
/*      */   public NamespaceAlias getNamespaceAlias(int i) throws ArrayIndexOutOfBoundsException {
/* 1130 */     if (null == this.m_prefix_aliases) {
/* 1131 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/* 1133 */     return this.m_prefix_aliases.elementAt(i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNamespaceAliasCount() {
/* 1144 */     return (null != this.m_prefix_aliases) ? this.m_prefix_aliases.size() : 0;
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
/*      */   public void setNonXslTopLevel(QName name, Object obj) {
/* 1163 */     if (null == this.m_NonXslTopLevel) {
/* 1164 */       this.m_NonXslTopLevel = new Hashtable();
/*      */     }
/* 1166 */     this.m_NonXslTopLevel.put(name, obj);
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
/*      */   public Object getNonXslTopLevel(QName name) {
/* 1179 */     return (null != this.m_NonXslTopLevel) ? this.m_NonXslTopLevel.get(name) : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1188 */   private String m_href = null;
/*      */ 
/*      */   
/*      */   private String m_publicId;
/*      */ 
/*      */   
/*      */   private String m_systemId;
/*      */ 
/*      */   
/*      */   private StylesheetRoot m_stylesheetRoot;
/*      */ 
/*      */   
/*      */   private Stylesheet m_stylesheetParent;
/*      */ 
/*      */ 
/*      */   
/*      */   public String getHref() {
/* 1205 */     return this.m_href;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHref(String baseIdent) {
/* 1215 */     this.m_href = baseIdent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocaterInfo(SourceLocator locator) {
/* 1226 */     if (null != locator) {
/*      */       
/* 1228 */       this.m_publicId = locator.getPublicId();
/* 1229 */       this.m_systemId = locator.getSystemId();
/*      */       
/* 1231 */       if (null != this.m_systemId) {
/*      */ 
/*      */         
/*      */         try { 
/* 1235 */           this.m_href = SystemIDResolver.getAbsoluteURI(this.m_systemId, null); } catch (TransformerException transformerException) {}
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1244 */       super.setLocaterInfo(locator);
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
/*      */   public StylesheetRoot getStylesheetRoot() {
/* 1263 */     return this.m_stylesheetRoot;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStylesheetRoot(StylesheetRoot v) {
/* 1274 */     this.m_stylesheetRoot = v;
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
/*      */   public Stylesheet getStylesheetParent() {
/* 1292 */     return this.m_stylesheetParent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStylesheetParent(Stylesheet v) {
/* 1303 */     this.m_stylesheetParent = v;
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
/*      */   public StylesheetComposed getStylesheetComposed() {
/* 1315 */     Stylesheet sheet = this;
/*      */     
/* 1317 */     while (!sheet.isAggregatedType())
/*      */     {
/* 1319 */       sheet = sheet.getStylesheetParent();
/*      */     }
/*      */     
/* 1322 */     return (StylesheetComposed)sheet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getNodeType() {
/* 1332 */     return 9;
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
/* 1344 */     return 25;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeName() {
/* 1354 */     return "stylesheet";
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
/*      */   public void replaceTemplate(ElemTemplate v, int i) throws TransformerException {
/* 1372 */     if (null == this.m_templates) {
/* 1373 */       throw new ArrayIndexOutOfBoundsException();
/*      */     }
/* 1375 */     replaceChild(v, this.m_templates.elementAt(i));
/* 1376 */     this.m_templates.setElementAt(v, i);
/* 1377 */     v.setStylesheet(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void callChildVisitors(XSLTVisitor visitor, boolean callAttrs) {
/* 1386 */     int s = getImportCount();
/* 1387 */     for (int j = 0; j < s; j++)
/*      */     {
/* 1389 */       getImport(j).callVisitors(visitor);
/*      */     }
/*      */     
/* 1392 */     s = getIncludeCount();
/* 1393 */     for (int i = 0; i < s; i++)
/*      */     {
/* 1395 */       getInclude(i).callVisitors(visitor);
/*      */     }
/*      */     
/* 1398 */     s = getOutputCount();
/* 1399 */     for (int k = 0; k < s; k++)
/*      */     {
/* 1401 */       visitor.visitTopLevelInstruction(getOutput(k));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1406 */     s = getAttributeSetCount();
/* 1407 */     for (int m = 0; m < s; m++) {
/*      */       
/* 1409 */       ElemAttributeSet attrSet = getAttributeSet(m);
/* 1410 */       if (visitor.visitTopLevelInstruction(attrSet))
/*      */       {
/* 1412 */         attrSet.callChildVisitors(visitor);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1417 */     s = getDecimalFormatCount();
/* 1418 */     for (int n = 0; n < s; n++)
/*      */     {
/* 1420 */       visitor.visitTopLevelInstruction(getDecimalFormat(n));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1425 */     s = getKeyCount();
/* 1426 */     for (int i1 = 0; i1 < s; i1++)
/*      */     {
/* 1428 */       visitor.visitTopLevelInstruction(getKey(i1));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1433 */     s = getNamespaceAliasCount();
/* 1434 */     for (int i2 = 0; i2 < s; i2++)
/*      */     {
/* 1436 */       visitor.visitTopLevelInstruction(getNamespaceAlias(i2));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1441 */     s = getTemplateCount();
/* 1442 */     for (int i3 = 0; i3 < s; i3++) {
/*      */ 
/*      */ 
/*      */       
/* 1446 */       try { ElemTemplate template = getTemplate(i3);
/* 1447 */         if (visitor.visitTopLevelInstruction(template))
/*      */         {
/* 1449 */           template.callChildVisitors(visitor); }  } catch (TransformerException te)
/*      */       
/*      */       { 
/*      */ 
/*      */         
/* 1454 */         throw new WrappedRuntimeException(te); }
/*      */     
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1460 */     s = getVariableOrParamCount();
/* 1461 */     for (int i4 = 0; i4 < s; i4++) {
/*      */       
/* 1463 */       ElemVariable var = getVariableOrParam(i4);
/* 1464 */       if (visitor.visitTopLevelVariableOrParamDecl(var))
/*      */       {
/* 1466 */         var.callChildVisitors(visitor);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1472 */     s = getStripSpaceCount();
/* 1473 */     for (int i5 = 0; i5 < s; i5++)
/*      */     {
/* 1475 */       visitor.visitTopLevelInstruction(getStripSpace(i5));
/*      */     }
/*      */     
/* 1478 */     s = getPreserveSpaceCount();
/* 1479 */     for (int i6 = 0; i6 < s; i6++)
/*      */     {
/* 1481 */       visitor.visitTopLevelInstruction(getPreserveSpace(i6));
/*      */     }
/*      */     
/* 1484 */     if (null != this.m_NonXslTopLevel) {
/*      */       
/* 1486 */       Enumeration elements = this.m_NonXslTopLevel.elements();
/* 1487 */       while (elements.hasMoreElements()) {
/*      */         
/* 1489 */         ElemTemplateElement elem = elements.nextElement();
/* 1490 */         if (visitor.visitTopLevelInstruction(elem))
/*      */         {
/* 1492 */           elem.callChildVisitors(visitor);
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
/*      */   protected boolean accept(XSLTVisitor visitor) {
/* 1509 */     return visitor.visitStylesheet(this);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/Stylesheet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */