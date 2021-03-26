/*      */ package org.apache.xalan.templates;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Properties;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.ErrorListener;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import javax.xml.transform.Templates;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.TransformerConfigurationException;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xalan.extensions.ExtensionNamespacesManager;
/*      */ import org.apache.xalan.processor.XSLTSchema;
/*      */ import org.apache.xalan.res.XSLMessages;
/*      */ import org.apache.xalan.transformer.TransformerImpl;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.ref.ExpandedNameTable;
/*      */ import org.apache.xml.utils.IntStack;
/*      */ import org.apache.xml.utils.QName;
/*      */ import org.apache.xpath.XPath;
/*      */ import org.apache.xpath.XPathContext;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class StylesheetRoot
/*      */   extends StylesheetComposed
/*      */   implements Serializable, Templates
/*      */ {
/*      */   private Hashtable m_availElems;
/*      */   private ExtensionNamespacesManager m_extNsMgr;
/*      */   private StylesheetComposed[] m_globalImportList;
/*      */   private OutputProperties m_outputProperties;
/*      */   private boolean m_outputMethodSet;
/*      */   private Hashtable m_attrSets;
/*      */   private Hashtable m_decimalFormatSymbols;
/*      */   private Vector m_keyDecls;
/*      */   private Hashtable m_namespaceAliasComposed;
/*      */   private TemplateList m_templateList;
/*      */   private Vector m_variables;
/*      */   private TemplateList m_whiteSpaceInfoList;
/*      */   private ElemTemplate m_defaultTextRule;
/*      */   private ElemTemplate m_defaultRule;
/*      */   private ElemTemplate m_defaultRootRule;
/*      */   private ElemTemplate m_startRule;
/*      */   XPath m_selectDefault;
/*      */   private ComposeState m_composeState;
/*      */   
/*      */   public StylesheetRoot(ErrorListener errorListener) throws TransformerConfigurationException {
/*   60 */     super(null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  117 */     this.m_extNsMgr = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  485 */     this.m_outputMethodSet = false; setStylesheetRoot(this); try { this.m_selectDefault = new XPath("node()", (SourceLocator)this, this, 0, errorListener); initDefaultRule(errorListener); } catch (TransformerException se) { throw new TransformerConfigurationException(XSLMessages.createMessage("ER_CANNOT_INIT_DEFAULT_TEMPLATES", null), se); }
/*      */   
/*      */   } public StylesheetRoot(XSLTSchema schema, ErrorListener listener) throws TransformerConfigurationException { this(listener); this.m_availElems = schema.getElemsAvailable(); } public boolean isRoot() { return true; }
/*      */   public Hashtable getAvailableElements() { return this.m_availElems; }
/*      */   public ExtensionNamespacesManager getExtensionNamespacesManager() { if (this.m_extNsMgr == null)
/*      */       this.m_extNsMgr = new ExtensionNamespacesManager();  return this.m_extNsMgr; }
/*      */   public Vector getExtensions() { return (this.m_extNsMgr != null) ? this.m_extNsMgr.getExtensions() : null; }
/*      */   public Transformer newTransformer() { return (Transformer)new TransformerImpl(this); }
/*      */   public Properties getDefaultOutputProps() { return this.m_outputProperties.getProperties(); }
/*      */   public Properties getOutputProperties() { return (Properties)getDefaultOutputProps().clone(); }
/*  495 */   public boolean isOutputMethodSet() { return this.m_outputMethodSet; }
/*      */   public void recompose() throws TransformerException { Vector recomposableElements = new Vector(); if (null == this.m_globalImportList) {
/*      */       Vector importList = new Vector(); addImports(this, true, importList); this.m_globalImportList = new StylesheetComposed[importList.size()]; for (int m = 0, i1 = importList.size() - 1; m < importList.size(); m++) {
/*      */         this.m_globalImportList[i1] = importList.elementAt(m); this.m_globalImportList[i1].recomposeIncludes(this.m_globalImportList[i1]); this.m_globalImportList[i1--].recomposeImports();
/*      */       } 
/*      */     }  int n = getGlobalImportCount(); for (int i = 0; i < n; i++) {
/*      */       StylesheetComposed imported = getGlobalImport(i); imported.recompose(recomposableElements);
/*      */     }  QuickSort2(recomposableElements, 0, recomposableElements.size() - 1); this.m_outputProperties = new OutputProperties(""); this.m_attrSets = new Hashtable(); this.m_decimalFormatSymbols = new Hashtable(); this.m_keyDecls = new Vector(); this.m_namespaceAliasComposed = new Hashtable(); this.m_templateList = new TemplateList(); this.m_variables = new Vector(); for (int j = recomposableElements.size() - 1; j >= 0; j--)
/*      */       ((ElemTemplateElement)recomposableElements.elementAt(j)).recompose(this);  initComposeState(); this.m_templateList.compose(this); this.m_outputProperties.compose(this); this.m_outputProperties.endCompose(this); n = getGlobalImportCount(); for (int k = 0; k < n; k++) {
/*      */       StylesheetComposed imported = getGlobalImport(k); int includedCount = imported.getIncludeCountComposed(); for (int m = -1; m < includedCount; m++) {
/*      */         Stylesheet included = imported.getIncludeComposed(m); composeTemplates(included);
/*      */       } 
/*      */     }  if (this.m_extNsMgr != null)
/*      */       this.m_extNsMgr.registerUnregisteredNamespaces();  clearComposeState(); }
/*      */   void composeTemplates(ElemTemplateElement templ) throws TransformerException { templ.compose(this); ElemTemplateElement child = templ.getFirstChildElem();
/*      */     for (; child != null; child = child.getNextSiblingElem())
/*      */       composeTemplates(child); 
/*  512 */     templ.endCompose(this); } void recomposeAttributeSets(ElemAttributeSet attrSet) { Vector attrSetList = (Vector)this.m_attrSets.get(attrSet.getName());
/*      */     
/*  514 */     if (null == attrSetList) {
/*      */       
/*  516 */       attrSetList = new Vector();
/*      */       
/*  518 */       this.m_attrSets.put(attrSet.getName(), attrSetList);
/*      */     } 
/*      */     
/*  521 */     attrSetList.addElement(attrSet); }
/*      */   protected void addImports(Stylesheet stylesheet, boolean addToList, Vector importList) { int n = stylesheet.getImportCount(); if (n > 0)
/*      */       for (int i = 0; i < n; i++) { Stylesheet imported = stylesheet.getImport(i); addImports(imported, true, importList); }
/*      */         n = stylesheet.getIncludeCount(); if (n > 0)
/*      */       for (int i = 0; i < n; i++) {
/*      */         Stylesheet included = stylesheet.getInclude(i); addImports(included, false, importList);
/*      */       }   if (addToList)
/*      */       importList.addElement(stylesheet);  }
/*      */   public StylesheetComposed getGlobalImport(int i) { return this.m_globalImportList[i]; }
/*      */   public int getGlobalImportCount() { return (this.m_globalImportList != null) ? this.m_globalImportList.length : 1; }
/*      */   public int getImportNumber(StylesheetComposed sheet) { if (this == sheet)
/*      */       return 0;  int n = getGlobalImportCount(); for (int i = 0; i < n; i++) {
/*      */       if (sheet == getGlobalImport(i))
/*      */         return i; 
/*      */     }  return -1; }
/*      */   void recomposeOutput(OutputProperties oprops) throws TransformerException { this.m_outputProperties.copyFrom(oprops); }
/*  537 */   public OutputProperties getOutputComposed() { return this.m_outputProperties; } public Vector getAttributeSetComposed(QName name) throws ArrayIndexOutOfBoundsException { return (Vector)this.m_attrSets.get(name); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void recomposeDecimalFormats(DecimalFormatProperties dfp) {
/*  553 */     DecimalFormatSymbols oldDfs = (DecimalFormatSymbols)this.m_decimalFormatSymbols.get(dfp.getName());
/*      */     
/*  555 */     if (null == oldDfs) {
/*      */       
/*  557 */       this.m_decimalFormatSymbols.put(dfp.getName(), dfp.getDecimalFormatSymbols());
/*      */     }
/*  559 */     else if (!dfp.getDecimalFormatSymbols().equals(oldDfs)) {
/*      */       String str;
/*      */       
/*  562 */       if (dfp.getName().equals(new QName(""))) {
/*      */ 
/*      */         
/*  565 */         str = XSLMessages.createWarning("WG_ONE_DEFAULT_XSLDECIMALFORMAT_ALLOWED", new Object[0]);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  572 */         str = XSLMessages.createWarning("WG_XSLDECIMALFORMAT_NAMES_MUST_BE_UNIQUE", new Object[] { dfp.getName() });
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  577 */       error(str);
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
/*      */   public DecimalFormatSymbols getDecimalFormatComposed(QName name) {
/*  599 */     return (DecimalFormatSymbols)this.m_decimalFormatSymbols.get(name);
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
/*      */   void recomposeKeys(KeyDeclaration keyDecl) {
/*  616 */     this.m_keyDecls.addElement(keyDecl);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vector getKeysComposed() {
/*  627 */     return this.m_keyDecls;
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
/*      */   void recomposeNamespaceAliases(NamespaceAlias nsAlias) {
/*  643 */     this.m_namespaceAliasComposed.put(nsAlias.getStylesheetNamespace(), nsAlias);
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
/*      */   public NamespaceAlias getNamespaceAliasComposed(String uri) {
/*  658 */     return (null == this.m_namespaceAliasComposed) ? null : (NamespaceAlias)this.m_namespaceAliasComposed.get(uri);
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
/*      */   void recomposeTemplates(ElemTemplate template) {
/*  675 */     this.m_templateList.setTemplate(template);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final TemplateList getTemplateListComposed() {
/*  686 */     return this.m_templateList;
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
/*      */   public final void setTemplateListComposed(TemplateList templateList) {
/*  698 */     this.m_templateList = templateList;
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
/*      */   public ElemTemplate getTemplateComposed(XPathContext xctxt, int targetNode, QName mode, boolean quietConflictWarnings, DTM dtm) throws TransformerException {
/*  723 */     return this.m_templateList.getTemplate(xctxt, targetNode, mode, quietConflictWarnings, dtm);
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
/*      */   public ElemTemplate getTemplateComposed(XPathContext xctxt, int targetNode, QName mode, int maxImportLevel, int endImportLevel, boolean quietConflictWarnings, DTM dtm) throws TransformerException {
/*  755 */     return this.m_templateList.getTemplate(xctxt, targetNode, mode, maxImportLevel, endImportLevel, quietConflictWarnings, dtm);
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
/*      */   public ElemTemplate getTemplateComposed(QName qname) {
/*  772 */     return this.m_templateList.getTemplate(qname);
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
/*      */   void recomposeVariables(ElemVariable elemVar) {
/*  789 */     if (getVariableOrParamComposed(elemVar.getName()) == null) {
/*      */       
/*  791 */       elemVar.setIsTopLevel(true);
/*  792 */       elemVar.setIndex(this.m_variables.size());
/*  793 */       this.m_variables.addElement(elemVar);
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
/*      */   public ElemVariable getVariableOrParamComposed(QName qname) {
/*  807 */     if (null != this.m_variables) {
/*      */       
/*  809 */       int n = this.m_variables.size();
/*      */       
/*  811 */       for (int i = 0; i < n; i++) {
/*      */         
/*  813 */         ElemVariable var = this.m_variables.elementAt(i);
/*  814 */         if (var.getName().equals(qname)) {
/*  815 */           return var;
/*      */         }
/*      */       } 
/*      */     } 
/*  819 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vector getVariablesAndParamsComposed() {
/*  830 */     return this.m_variables;
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
/*      */   void recomposeWhiteSpaceInfo(WhiteSpaceInfo wsi) {
/*  847 */     if (null == this.m_whiteSpaceInfoList) {
/*  848 */       this.m_whiteSpaceInfoList = new TemplateList();
/*      */     }
/*  850 */     this.m_whiteSpaceInfoList.setTemplate(wsi);
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
/*      */   public boolean shouldCheckWhitespace() {
/*  862 */     return (null != this.m_whiteSpaceInfoList);
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
/*      */   public WhiteSpaceInfo getWhiteSpaceInfo(XPathContext support, int targetElement, DTM dtm) throws TransformerException {
/*  880 */     if (null != this.m_whiteSpaceInfoList) {
/*  881 */       return (WhiteSpaceInfo)this.m_whiteSpaceInfoList.getTemplate(support, targetElement, null, false, dtm);
/*      */     }
/*      */     
/*  884 */     return null;
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
/*      */   public boolean shouldStripWhiteSpace(XPathContext support, int targetElement) throws TransformerException {
/*  901 */     if (null != this.m_whiteSpaceInfoList)
/*      */     {
/*  903 */       while (-1 != targetElement) {
/*      */         
/*  905 */         DTM dtm = support.getDTM(targetElement);
/*  906 */         WhiteSpaceInfo info = (WhiteSpaceInfo)this.m_whiteSpaceInfoList.getTemplate(support, targetElement, null, false, dtm);
/*      */         
/*  908 */         if (null != info) {
/*  909 */           return info.getShouldStripSpace();
/*      */         }
/*  911 */         int parent = dtm.getParent(targetElement);
/*  912 */         if (-1 != parent && 1 == dtm.getNodeType(parent)) {
/*  913 */           targetElement = parent; continue;
/*      */         } 
/*  915 */         targetElement = -1;
/*      */       } 
/*      */     }
/*  918 */     return false;
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
/*  929 */     return (null != this.m_whiteSpaceInfoList);
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
/*      */   public final ElemTemplate getDefaultTextRule() {
/*  950 */     return this.m_defaultTextRule;
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
/*      */   public final ElemTemplate getDefaultRule() {
/*  969 */     return this.m_defaultRule;
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
/*      */   public final ElemTemplate getDefaultRootRule() {
/*  993 */     return this.m_defaultRootRule;
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
/*      */   public final ElemTemplate getStartRule() {
/* 1011 */     return this.m_startRule;
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
/*      */   private void initDefaultRule(ErrorListener errorListener) throws TransformerException {
/* 1030 */     this.m_defaultRule = new ElemTemplate();
/*      */     
/* 1032 */     this.m_defaultRule.setStylesheet(this);
/*      */     
/* 1034 */     XPath defMatch = new XPath("*", (SourceLocator)this, this, 1, errorListener);
/*      */     
/* 1036 */     this.m_defaultRule.setMatch(defMatch);
/*      */     
/* 1038 */     ElemApplyTemplates childrenElement = new ElemApplyTemplates();
/*      */     
/* 1040 */     childrenElement.setIsDefaultTemplate(true);
/* 1041 */     childrenElement.setSelect(this.m_selectDefault);
/* 1042 */     this.m_defaultRule.appendChild(childrenElement);
/*      */     
/* 1044 */     this.m_startRule = this.m_defaultRule;
/*      */ 
/*      */     
/* 1047 */     this.m_defaultTextRule = new ElemTemplate();
/*      */     
/* 1049 */     this.m_defaultTextRule.setStylesheet(this);
/*      */     
/* 1051 */     defMatch = new XPath("text() | @*", (SourceLocator)this, this, 1, errorListener);
/*      */     
/* 1053 */     this.m_defaultTextRule.setMatch(defMatch);
/*      */     
/* 1055 */     ElemValueOf elemValueOf = new ElemValueOf();
/*      */     
/* 1057 */     this.m_defaultTextRule.appendChild(elemValueOf);
/*      */     
/* 1059 */     XPath selectPattern = new XPath(".", (SourceLocator)this, this, 0, errorListener);
/*      */     
/* 1061 */     elemValueOf.setSelect(selectPattern);
/*      */ 
/*      */     
/* 1064 */     this.m_defaultRootRule = new ElemTemplate();
/*      */     
/* 1066 */     this.m_defaultRootRule.setStylesheet(this);
/*      */     
/* 1068 */     defMatch = new XPath("/", (SourceLocator)this, this, 1, errorListener);
/*      */     
/* 1070 */     this.m_defaultRootRule.setMatch(defMatch);
/*      */     
/* 1072 */     childrenElement = new ElemApplyTemplates();
/*      */     
/* 1074 */     childrenElement.setIsDefaultTemplate(true);
/* 1075 */     this.m_defaultRootRule.appendChild(childrenElement);
/* 1076 */     childrenElement.setSelect(this.m_selectDefault);
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
/*      */   private void QuickSort2(Vector v, int lo0, int hi0) {
/* 1100 */     int lo = lo0;
/* 1101 */     int hi = hi0;
/*      */     
/* 1103 */     if (hi0 > lo0) {
/*      */ 
/*      */ 
/*      */       
/* 1107 */       ElemTemplateElement midNode = v.elementAt((lo0 + hi0) / 2);
/*      */ 
/*      */       
/* 1110 */       while (lo <= hi) {
/*      */ 
/*      */ 
/*      */         
/* 1114 */         while (lo < hi0 && ((ElemTemplateElement)v.elementAt(lo)).compareTo(midNode) < 0)
/*      */         {
/* 1116 */           lo++;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1121 */         while (hi > lo0 && ((ElemTemplateElement)v.elementAt(hi)).compareTo(midNode) > 0) {
/* 1122 */           hi--;
/*      */         }
/*      */ 
/*      */         
/* 1126 */         if (lo <= hi) {
/*      */           
/* 1128 */           ElemTemplateElement node = v.elementAt(lo);
/* 1129 */           v.setElementAt(v.elementAt(hi), lo);
/* 1130 */           v.setElementAt(node, hi);
/*      */           
/* 1132 */           lo++;
/* 1133 */           hi--;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1139 */       if (lo0 < hi)
/*      */       {
/* 1141 */         QuickSort2(v, lo0, hi);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1146 */       if (lo < hi0)
/*      */       {
/* 1148 */         QuickSort2(v, lo, hi0);
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
/*      */   void initComposeState() {
/* 1160 */     this.m_composeState = new ComposeState(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ComposeState getComposeState() {
/* 1169 */     return this.m_composeState;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void clearComposeState() {
/* 1177 */     this.m_composeState = null;
/*      */   }
/*      */   
/*      */   class ComposeState { private ExpandedNameTable m_ent;
/*      */     private Vector m_variableNames;
/*      */     IntStack m_marks;
/*      */     private int m_maxStackFrameSize;
/*      */     private final StylesheetRoot this$0;
/*      */     
/* 1186 */     ComposeState(StylesheetRoot this$0) { this.this$0 = this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1196 */       this.m_ent = new ExpandedNameTable();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1220 */       this.m_variableNames = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1271 */       this.m_marks = new IntStack(); int size = this$0.m_variables.size(); for (int i = 0; i < size; i++) { ElemVariable ev = this$0.m_variables.elementAt(i); this.m_variableNames.addElement(ev.getName()); }  } public int getQNameID(QName qname) { return this.m_ent.getExpandedTypeID(qname.getNamespace(), qname.getLocalName(), 1); } int addVariableName(QName qname) { int pos = this.m_variableNames.size(); this.m_variableNames.addElement(qname); int frameSize = this.m_variableNames.size() - getGlobalsSize(); if (frameSize > this.m_maxStackFrameSize) this.m_maxStackFrameSize++;  return pos; } void resetStackFrameSize() { this.m_maxStackFrameSize = 0; } int getFrameSize() { return this.m_maxStackFrameSize; }
/*      */     int getCurrentStackFrameSize() { return this.m_variableNames.size(); }
/*      */     void setCurrentStackFrameSize(int sz) { this.m_variableNames.setSize(sz); }
/*      */     int getGlobalsSize() { return this.this$0.m_variables.size(); }
/* 1275 */     void pushStackMark() { this.m_marks.push(getCurrentStackFrameSize()); }
/*      */ 
/*      */ 
/*      */     
/*      */     void popStackMark() {
/* 1280 */       int mark = this.m_marks.pop();
/* 1281 */       setCurrentStackFrameSize(mark);
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
/*      */     Vector getVariableNames() {
/* 1293 */       return this.m_variableNames;
/*      */     } }
/*      */ 
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/StylesheetRoot.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */