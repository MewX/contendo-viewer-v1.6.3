/*      */ package org.apache.xpath;
/*      */ 
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Stack;
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.ErrorListener;
/*      */ import javax.xml.transform.Source;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import javax.xml.transform.URIResolver;
/*      */ import org.apache.xalan.extensions.ExpressionContext;
/*      */ import org.apache.xalan.res.XSLMessages;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMFilter;
/*      */ import org.apache.xml.dtm.DTMIterator;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.DTMWSFilter;
/*      */ import org.apache.xml.dtm.ref.DTMNodeIterator;
/*      */ import org.apache.xml.dtm.ref.sax2dtm.SAX2RTFDTM;
/*      */ import org.apache.xml.utils.DefaultErrorHandler;
/*      */ import org.apache.xml.utils.IntStack;
/*      */ import org.apache.xml.utils.NodeVector;
/*      */ import org.apache.xml.utils.ObjectStack;
/*      */ import org.apache.xml.utils.PrefixResolver;
/*      */ import org.apache.xml.utils.QName;
/*      */ import org.apache.xml.utils.XMLString;
/*      */ import org.apache.xpath.axes.OneStepIteratorForward;
/*      */ import org.apache.xpath.axes.SubContextList;
/*      */ import org.apache.xpath.objects.XMLStringFactoryImpl;
/*      */ import org.apache.xpath.objects.XObject;
/*      */ import org.apache.xpath.objects.XString;
/*      */ import org.apache.xpath.res.XPATHMessages;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.traversal.NodeIterator;
/*      */ import org.xml.sax.XMLReader;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class XPathContext
/*      */   extends DTMManager
/*      */ {
/*   60 */   IntStack m_last_pushed_rtfdtm = new IntStack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   71 */   private Vector m_rtfdtm_stack = null;
/*      */   
/*   73 */   private int m_which_rtfdtm = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   79 */   private SAX2RTFDTM m_global_rtfdtm = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   87 */   protected DTMManager m_dtmManager = DTMManager.newInstance(XMLStringFactoryImpl.getFactory()); ObjectStack m_saxLocations; private Object m_owner; private Method m_ownerGetErrorListener; private VariableStack m_variableStacks; private SourceTreeManager m_sourceTreeManager; private ErrorListener m_errorListener; private ErrorListener m_defaultErrorListener; private URIResolver m_uriResolver; public XMLReader m_primaryReader; private Stack m_contextNodeLists; public static final int RECURSIONLIMIT = 4096;
/*      */   private IntStack m_currentNodes;
/*      */   private NodeVector m_iteratorRoots;
/*      */   private NodeVector m_predicateRoots;
/*      */   private IntStack m_currentExpressionNodes;
/*      */   private IntStack m_predicatePos;
/*      */   private ObjectStack m_prefixResolvers;
/*      */   private Stack m_axesIteratorStack;
/*      */   XPathExpressionContext expressionContext;
/*      */   
/*      */   public DTMManager getDTMManager() {
/*   98 */     return this.m_dtmManager;
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
/*      */   public DTM getDTM(Source source, boolean unique, DTMWSFilter wsfilter, boolean incremental, boolean doIndexing) {
/*  128 */     return this.m_dtmManager.getDTM(source, unique, wsfilter, incremental, doIndexing);
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
/*      */   public DTM getDTM(int nodeHandle) {
/*  141 */     return this.m_dtmManager.getDTM(nodeHandle);
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
/*      */   public int getDTMHandleFromNode(Node node) {
/*  154 */     return this.m_dtmManager.getDTMHandleFromNode(node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDTMIdentity(DTM dtm) {
/*  163 */     return this.m_dtmManager.getDTMIdentity(dtm);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTM createDocumentFragment() {
/*  172 */     return this.m_dtmManager.createDocumentFragment();
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
/*      */   public boolean release(DTM dtm, boolean shouldHardDelete) {
/*  191 */     if (this.m_rtfdtm_stack != null && this.m_rtfdtm_stack.contains(dtm))
/*      */     {
/*  193 */       return false;
/*      */     }
/*      */     
/*  196 */     return this.m_dtmManager.release(dtm, shouldHardDelete);
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
/*      */   public DTMIterator createDTMIterator(Object xpathCompiler, int pos) {
/*  213 */     return this.m_dtmManager.createDTMIterator(xpathCompiler, pos);
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
/*      */   public DTMIterator createDTMIterator(String xpathString, PrefixResolver presolver) {
/*  232 */     return this.m_dtmManager.createDTMIterator(xpathString, presolver);
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
/*      */   public DTMIterator createDTMIterator(int whatToShow, DTMFilter filter, boolean entityReferenceExpansion) {
/*  255 */     return this.m_dtmManager.createDTMIterator(whatToShow, filter, entityReferenceExpansion);
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
/*      */   public DTMIterator createDTMIterator(int node) {
/*  268 */     OneStepIteratorForward oneStepIteratorForward = new OneStepIteratorForward(13);
/*  269 */     oneStepIteratorForward.setRoot(node, this);
/*  270 */     return (DTMIterator)oneStepIteratorForward;
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
/*      */   
/*      */   public void reset() {
/*  309 */     if (this.m_rtfdtm_stack != null)
/*  310 */       for (Enumeration e = this.m_rtfdtm_stack.elements(); e.hasMoreElements();) {
/*  311 */         this.m_dtmManager.release(e.nextElement(), true);
/*      */       } 
/*  313 */     this.m_rtfdtm_stack = null;
/*  314 */     this.m_which_rtfdtm = -1;
/*      */     
/*  316 */     if (this.m_global_rtfdtm != null)
/*  317 */       this.m_dtmManager.release((DTM)this.m_global_rtfdtm, true); 
/*  318 */     this.m_global_rtfdtm = null;
/*      */     
/*  320 */     this.m_dtmManager = DTMManager.newInstance(XMLStringFactoryImpl.getFactory());
/*      */ 
/*      */     
/*  323 */     this.m_saxLocations.removeAllElements();
/*  324 */     this.m_axesIteratorStack.removeAllElements();
/*  325 */     this.m_contextNodeLists.removeAllElements();
/*  326 */     this.m_currentExpressionNodes.removeAllElements();
/*  327 */     this.m_currentNodes.removeAllElements();
/*  328 */     this.m_iteratorRoots.RemoveAllNoClear();
/*  329 */     this.m_predicatePos.removeAllElements();
/*  330 */     this.m_predicateRoots.RemoveAllNoClear();
/*  331 */     this.m_prefixResolvers.removeAllElements();
/*      */     
/*  333 */     this.m_prefixResolvers.push(null);
/*  334 */     this.m_currentNodes.push(-1);
/*  335 */     this.m_currentExpressionNodes.push(-1);
/*  336 */     this.m_saxLocations.push(null);
/*      */   }
/*      */   
/*      */   public XPathContext() {
/*  340 */     this.m_saxLocations = new ObjectStack(4096);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  419 */     this.m_variableStacks = new VariableStack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  447 */     this.m_sourceTreeManager = new SourceTreeManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  613 */     this.m_contextNodeLists = new Stack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  667 */     this.m_currentNodes = new IntStack(4096);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  798 */     this.m_iteratorRoots = new NodeVector();
/*      */ 
/*      */     
/*  801 */     this.m_predicateRoots = new NodeVector();
/*      */ 
/*      */     
/*  804 */     this.m_currentExpressionNodes = new IntStack(4096);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  810 */     this.m_predicatePos = new IntStack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  856 */     this.m_prefixResolvers = new ObjectStack(4096);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  916 */     this.m_axesIteratorStack = new Stack();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1002 */     this.expressionContext = new XPathExpressionContext(this); this.m_prefixResolvers.push(null); this.m_currentNodes.push(-1); this.m_currentExpressionNodes.push(-1); this.m_saxLocations.push(null); } public XPathContext(Object owner) { this.m_saxLocations = new ObjectStack(4096); this.m_variableStacks = new VariableStack(); this.m_sourceTreeManager = new SourceTreeManager(); this.m_contextNodeLists = new Stack(); this.m_currentNodes = new IntStack(4096); this.m_iteratorRoots = new NodeVector(); this.m_predicateRoots = new NodeVector(); this.m_currentExpressionNodes = new IntStack(4096); this.m_predicatePos = new IntStack(); this.m_prefixResolvers = new ObjectStack(4096); this.m_axesIteratorStack = new Stack(); this.expressionContext = new XPathExpressionContext(this); this.m_owner = owner; try { this.m_ownerGetErrorListener = this.m_owner.getClass().getMethod("getErrorListener", new Class[0]); } catch (NoSuchMethodException noSuchMethodException) {} this.m_prefixResolvers.push(null); this.m_currentNodes.push(-1); this.m_currentExpressionNodes.push(-1); this.m_saxLocations.push(null); }
/*      */   public void setSAXLocator(SourceLocator location) { this.m_saxLocations.setTop(location); }
/*      */   public void pushSAXLocator(SourceLocator location) { this.m_saxLocations.push(location); }
/*      */   public void pushSAXLocatorNull() { this.m_saxLocations.push(null); }
/*      */   public void popSAXLocator() { this.m_saxLocations.pop(); }
/*      */   public SourceLocator getSAXLocator() { return (SourceLocator)this.m_saxLocations.peek(); }
/*      */   public Object getOwnerObject() { return this.m_owner; }
/*      */   public final VariableStack getVarStack() { return this.m_variableStacks; }
/*      */   public final void setVarStack(VariableStack varStack) { this.m_variableStacks = varStack; }
/* 1011 */   public final SourceTreeManager getSourceTreeManager() { return this.m_sourceTreeManager; } public void setSourceTreeManager(SourceTreeManager mgr) { this.m_sourceTreeManager = mgr; } public final ErrorListener getErrorListener() { if (null != this.m_errorListener) return this.m_errorListener;  ErrorListener retval = null; try { if (null != this.m_ownerGetErrorListener) retval = (ErrorListener)this.m_ownerGetErrorListener.invoke(this.m_owner, new Object[0]);  } catch (Exception exception) {} if (null == retval) { if (null == this.m_defaultErrorListener) this.m_defaultErrorListener = (ErrorListener)new DefaultErrorHandler();  retval = this.m_defaultErrorListener; }  return retval; } public void setErrorListener(ErrorListener listener) throws IllegalArgumentException { if (listener == null) throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", null));  this.m_errorListener = listener; } public final URIResolver getURIResolver() { return this.m_uriResolver; } public void setURIResolver(URIResolver resolver) { this.m_uriResolver = resolver; } public final XMLReader getPrimaryReader() { return this.m_primaryReader; } public void setPrimaryReader(XMLReader reader) { this.m_primaryReader = reader; } private void assertion(boolean b, String msg) throws TransformerException { if (!b) { ErrorListener errorHandler = getErrorListener(); if (errorHandler != null) errorHandler.fatalError(new TransformerException(XSLMessages.createMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[] { msg }), getSAXLocator()));  }  } public Stack getContextNodeListsStack() { return this.m_contextNodeLists; } public void setContextNodeListsStack(Stack s) { this.m_contextNodeLists = s; } public final DTMIterator getContextNodeList() { if (this.m_contextNodeLists.size() > 0) return this.m_contextNodeLists.peek();  return null; } public final void pushContextNodeList(DTMIterator nl) { this.m_contextNodeLists.push(nl); } public final void popContextNodeList() { if (this.m_contextNodeLists.isEmpty()) { System.err.println("Warning: popContextNodeList when stack is empty!"); } else { this.m_contextNodeLists.pop(); }  } public IntStack getCurrentNodeStack() { return this.m_currentNodes; } public void setCurrentNodeStack(IntStack nv) { this.m_currentNodes = nv; } public final int getCurrentNode() { return this.m_currentNodes.peek(); } public final void pushCurrentNodeAndExpression(int cn, int en) { this.m_currentNodes.push(cn); this.m_currentExpressionNodes.push(cn); } public final void popCurrentNodeAndExpression() { this.m_currentNodes.quickPop(1); this.m_currentExpressionNodes.quickPop(1); } public final void pushExpressionState(int cn, int en, PrefixResolver nc) { this.m_currentNodes.push(cn); this.m_currentExpressionNodes.push(cn); this.m_prefixResolvers.push(nc); } public final void popExpressionState() { this.m_currentNodes.quickPop(1); this.m_currentExpressionNodes.quickPop(1); this.m_prefixResolvers.pop(); } public ExpressionContext getExpressionContext() { return this.expressionContext; }
/*      */   public final void pushCurrentNode(int n) { this.m_currentNodes.push(n); } public final void popCurrentNode() { this.m_currentNodes.quickPop(1); } public final void pushPredicateRoot(int n) { this.m_predicateRoots.push(n); } public final void popPredicateRoot() { this.m_predicateRoots.popQuick(); } public final int getPredicateRoot() { return this.m_predicateRoots.peepOrNull(); } public final void pushIteratorRoot(int n) { this.m_iteratorRoots.push(n); } public final void popIteratorRoot() { this.m_iteratorRoots.popQuick(); } public final int getIteratorRoot() { return this.m_iteratorRoots.peepOrNull(); } public IntStack getCurrentExpressionNodeStack() { return this.m_currentExpressionNodes; } public void setCurrentExpressionNodeStack(IntStack nv) { this.m_currentExpressionNodes = nv; } public final int getPredicatePos() { return this.m_predicatePos.peek(); } public final void pushPredicatePos(int n) { this.m_predicatePos.push(n); } public final void popPredicatePos() { this.m_predicatePos.pop(); } public final int getCurrentExpressionNode() { return this.m_currentExpressionNodes.peek(); } public final void pushCurrentExpressionNode(int n) { this.m_currentExpressionNodes.push(n); } public final void popCurrentExpressionNode() { this.m_currentExpressionNodes.quickPop(1); } public final PrefixResolver getNamespaceContext() { return (PrefixResolver)this.m_prefixResolvers.peek(); } public final void setNamespaceContext(PrefixResolver pr) { this.m_prefixResolvers.setTop(pr); } public final void pushNamespaceContext(PrefixResolver pr) { this.m_prefixResolvers.push(pr); } public final void pushNamespaceContextNull() { this.m_prefixResolvers.push(null); } public final void popNamespaceContext() { this.m_prefixResolvers.pop(); } public Stack getAxesIteratorStackStacks() { return this.m_axesIteratorStack; } public void setAxesIteratorStackStacks(Stack s) { this.m_axesIteratorStack = s; } public final void pushSubContextList(SubContextList iter) { this.m_axesIteratorStack.push(iter); } public final void popSubContextList() { this.m_axesIteratorStack.pop(); } public SubContextList getSubContextList() { return this.m_axesIteratorStack.isEmpty() ? null : this.m_axesIteratorStack.peek(); } public SubContextList getCurrentNodeList() { return this.m_axesIteratorStack.isEmpty() ? null : this.m_axesIteratorStack.elementAt(0); } public final int getContextNode() { return getCurrentNode(); } public final DTMIterator getContextNodes() { try { DTMIterator cnl = getContextNodeList(); if (null != cnl) return cnl.cloneWithReset();  return null; } catch (CloneNotSupportedException cnse) { return null; }  } public class XPathExpressionContext implements ExpressionContext
/*      */   {
/* 1014 */     private final XPathContext this$0; public XPathExpressionContext(XPathContext this$0) { this.this$0 = this$0; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public XPathContext getXPathContext() {
/* 1025 */       return this.this$0;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMManager getDTMManager() {
/* 1036 */       return this.this$0.m_dtmManager;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node getContextNode() {
/* 1045 */       int context = this.this$0.getCurrentNode();
/*      */       
/* 1047 */       return this.this$0.getDTM(context).getNode(context);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NodeIterator getContextNodes() {
/* 1057 */       return (NodeIterator)new DTMNodeIterator(this.this$0.getContextNodeList());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ErrorListener getErrorListener() {
/* 1066 */       return this.this$0.getErrorListener();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public double toNumber(Node n) {
/* 1077 */       int nodeHandle = this.this$0.getDTMHandleFromNode(n);
/* 1078 */       DTM dtm = this.this$0.getDTM(nodeHandle);
/* 1079 */       XString xobj = (XString)dtm.getStringValue(nodeHandle);
/* 1080 */       return xobj.num();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString(Node n) {
/* 1091 */       int nodeHandle = this.this$0.getDTMHandleFromNode(n);
/* 1092 */       DTM dtm = this.this$0.getDTM(nodeHandle);
/* 1093 */       XMLString strVal = dtm.getStringValue(nodeHandle);
/* 1094 */       return strVal.toString();
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
/*      */     public final XObject getVariableOrParam(QName qname) throws TransformerException {
/* 1107 */       return this.this$0.m_variableStacks.getVariableOrParam(this.this$0, qname);
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
/*      */ 
/*      */ 
/*      */   
/*      */   public DTM getGlobalRTFDTM() {
/* 1143 */     if (this.m_global_rtfdtm == null || this.m_global_rtfdtm.isTreeIncomplete())
/*      */     {
/* 1145 */       this.m_global_rtfdtm = (SAX2RTFDTM)this.m_dtmManager.getDTM(null, true, null, false, false);
/*      */     }
/* 1147 */     return (DTM)this.m_global_rtfdtm;
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
/*      */   public DTM getRTFDTM() {
/*      */     SAX2RTFDTM sAX2RTFDTM;
/* 1176 */     if (this.m_rtfdtm_stack == null) {
/*      */       
/* 1178 */       this.m_rtfdtm_stack = new Vector();
/* 1179 */       sAX2RTFDTM = (SAX2RTFDTM)this.m_dtmManager.getDTM(null, true, null, false, false);
/* 1180 */       this.m_rtfdtm_stack.addElement(sAX2RTFDTM);
/* 1181 */       this.m_which_rtfdtm++;
/*      */     }
/* 1183 */     else if (this.m_which_rtfdtm < 0) {
/*      */       
/* 1185 */       sAX2RTFDTM = this.m_rtfdtm_stack.elementAt(++this.m_which_rtfdtm);
/*      */     }
/*      */     else {
/*      */       
/* 1189 */       sAX2RTFDTM = this.m_rtfdtm_stack.elementAt(this.m_which_rtfdtm);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1197 */       if (sAX2RTFDTM.isTreeIncomplete())
/*      */       {
/* 1199 */         if (++this.m_which_rtfdtm < this.m_rtfdtm_stack.size()) {
/* 1200 */           sAX2RTFDTM = this.m_rtfdtm_stack.elementAt(this.m_which_rtfdtm);
/*      */         } else {
/*      */           
/* 1203 */           sAX2RTFDTM = (SAX2RTFDTM)this.m_dtmManager.getDTM(null, true, null, false, false);
/* 1204 */           this.m_rtfdtm_stack.addElement(sAX2RTFDTM);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1209 */     return (DTM)sAX2RTFDTM;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void pushRTFContext() {
/* 1218 */     this.m_last_pushed_rtfdtm.push(this.m_which_rtfdtm);
/* 1219 */     if (null != this.m_rtfdtm_stack) {
/* 1220 */       ((SAX2RTFDTM)getRTFDTM()).pushRewindMark();
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
/*      */   public void popRTFContext() {
/* 1239 */     int previous = this.m_last_pushed_rtfdtm.pop();
/* 1240 */     if (null == this.m_rtfdtm_stack) {
/*      */       return;
/*      */     }
/* 1243 */     if (this.m_which_rtfdtm == previous) {
/*      */       
/* 1245 */       if (previous >= 0)
/*      */       {
/* 1247 */         boolean bool = ((SAX2RTFDTM)this.m_rtfdtm_stack.elementAt(previous)).popRewindMark();
/*      */       }
/*      */     } else {
/* 1250 */       while (this.m_which_rtfdtm != previous) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1255 */         boolean isEmpty = ((SAX2RTFDTM)this.m_rtfdtm_stack.elementAt(this.m_which_rtfdtm)).popRewindMark();
/* 1256 */         this.m_which_rtfdtm--;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/XPathContext.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */