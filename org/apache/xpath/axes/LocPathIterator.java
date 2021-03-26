/*      */ package org.apache.xpath.axes;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.Serializable;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMFilter;
/*      */ import org.apache.xml.dtm.DTMIterator;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.utils.PrefixResolver;
/*      */ import org.apache.xpath.ExpressionOwner;
/*      */ import org.apache.xpath.XPathContext;
/*      */ import org.apache.xpath.XPathVisitor;
/*      */ import org.apache.xpath.compiler.Compiler;
/*      */ import org.apache.xpath.objects.XNodeSet;
/*      */ import org.apache.xpath.objects.XObject;
/*      */ import org.apache.xpath.res.XPATHMessages;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class LocPathIterator
/*      */   extends PredicatedNodeTest
/*      */   implements Serializable, Cloneable, DTMIterator, PathComponent
/*      */ {
/*      */   protected LocPathIterator() {}
/*      */   
/*      */   protected LocPathIterator(PrefixResolver nscontext) {
/*   71 */     setLocPathIterator(this);
/*   72 */     this.m_prefixResolver = nscontext;
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
/*      */   protected LocPathIterator(Compiler compiler, int opPos, int analysis) throws TransformerException {
/*   90 */     this(compiler, opPos, analysis, true);
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
/*      */   protected LocPathIterator(Compiler compiler, int opPos, int analysis, boolean shouldLoadWalkers) throws TransformerException {
/*  112 */     setLocPathIterator(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAnalysisBits() {
/*  121 */     int axis = getAxis();
/*  122 */     int bit = WalkerFactory.getAnalysisBitFromAxes(axis);
/*  123 */     return bit;
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
/*      */   private void readObject(ObjectInputStream stream) throws IOException, TransformerException {
/*      */     
/*  139 */     try { stream.defaultReadObject();
/*  140 */       this.m_clones = new IteratorPool(this); } catch (ClassNotFoundException cnfe)
/*      */     
/*      */     { 
/*      */       
/*  144 */       throw new TransformerException(cnfe); }
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
/*      */   public void setEnvironment(Object environment) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  178 */     return this.m_execContext.getDTM(nodeHandle);
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
/*      */   public DTMManager getDTMManager() {
/*  190 */     return this.m_execContext.getDTMManager();
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
/*      */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*  209 */     XNodeSet iter = new XNodeSet(this.m_clones.getInstance());
/*      */     
/*  211 */     iter.setRoot(xctxt.getCurrentNode(), xctxt);
/*      */     
/*  213 */     return (XObject)iter;
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
/*      */   public void executeCharsToContentHandler(XPathContext xctxt, ContentHandler handler) throws TransformerException, SAXException {
/*  235 */     LocPathIterator clone = (LocPathIterator)this.m_clones.getInstance();
/*      */     
/*  237 */     int current = xctxt.getCurrentNode();
/*  238 */     clone.setRoot(current, xctxt);
/*      */     
/*  240 */     int node = clone.nextNode();
/*  241 */     DTM dtm = clone.getDTM(node);
/*  242 */     clone.detach();
/*      */     
/*  244 */     if (node != -1)
/*      */     {
/*  246 */       dtm.dispatchCharactersEvents(node, handler, false);
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
/*      */   public DTMIterator asIterator(XPathContext xctxt, int contextNode) throws TransformerException {
/*  269 */     XNodeSet iter = new XNodeSet(this.m_clones.getInstance());
/*      */     
/*  271 */     iter.setRoot(contextNode, xctxt);
/*      */     
/*  273 */     return (DTMIterator)iter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNodesetExpr() {
/*  284 */     return true;
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
/*      */   public int asNode(XPathContext xctxt) throws TransformerException {
/*  298 */     DTMIterator iter = this.m_clones.getInstance();
/*      */     
/*  300 */     int current = xctxt.getCurrentNode();
/*      */     
/*  302 */     iter.setRoot(current, xctxt);
/*      */     
/*  304 */     int next = iter.nextNode();
/*      */     
/*  306 */     iter.detach();
/*  307 */     return next;
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
/*      */   public boolean bool(XPathContext xctxt) throws TransformerException {
/*  322 */     return (asNode(xctxt) != -1);
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
/*      */   public void setIsTopLevel(boolean b) {
/*  336 */     this.m_isTopLevel = b;
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
/*      */   public boolean getIsTopLevel() {
/*  349 */     return this.m_isTopLevel;
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
/*      */   public void setRoot(int context, Object environment) {
/*  362 */     this.m_context = context;
/*      */     
/*  364 */     XPathContext xctxt = (XPathContext)environment;
/*  365 */     this.m_execContext = xctxt;
/*  366 */     this.m_cdtm = xctxt.getDTM(context);
/*      */     
/*  368 */     this.m_currentContextNode = context;
/*      */ 
/*      */     
/*  371 */     if (null == this.m_prefixResolver) {
/*  372 */       this.m_prefixResolver = xctxt.getNamespaceContext();
/*      */     }
/*  374 */     this.m_lastFetched = -1;
/*  375 */     this.m_foundLast = false;
/*  376 */     this.m_pos = 0;
/*  377 */     this.m_length = -1;
/*      */     
/*  379 */     if (this.m_isTopLevel) {
/*  380 */       this.m_stackFrame = xctxt.getVarStack().getStackFrame();
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
/*      */   protected void setNextPosition(int next) {
/*  393 */     assertion(false, "setNextPosition not supported in this iterator!");
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
/*      */   public final int getCurrentPos() {
/*  407 */     return this.m_pos;
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
/*      */   public void setShouldCacheNodes(boolean b) {
/*  420 */     assertion(false, "setShouldCacheNodes not supported by this iterater!");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMutable() {
/*  431 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCurrentPos(int i) {
/*  442 */     assertion(false, "setCurrentPos not supported by this iterator!");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void incrementCurrentPos() {
/*  450 */     this.m_pos++;
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
/*      */   public int size() {
/*  466 */     assertion(false, "size() not supported by this iterator!");
/*  467 */     return 0;
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
/*      */   public int item(int index) {
/*  481 */     assertion(false, "item(int index) not supported by this iterator!");
/*  482 */     return 0;
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
/*      */   public void setItem(int node, int index) {
/*  500 */     assertion(false, "setItem not supported by this iterator!");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLength() {
/*      */     LocPathIterator clone;
/*  512 */     boolean isPredicateTest = (this == this.m_execContext.getSubContextList());
/*      */ 
/*      */     
/*  515 */     int predCount = getPredicateCount();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  520 */     if (-1 != this.m_length && isPredicateTest && this.m_predicateIndex < 1) {
/*  521 */       return this.m_length;
/*      */     }
/*      */ 
/*      */     
/*  525 */     if (this.m_foundLast) {
/*  526 */       return this.m_pos;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  531 */     int pos = (this.m_predicateIndex >= 0) ? getProximityPosition() : this.m_pos;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  537 */     try { clone = (LocPathIterator)clone(); } catch (CloneNotSupportedException cnse)
/*      */     
/*      */     { 
/*      */       
/*  541 */       return -1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  547 */     if (predCount > 0 && isPredicateTest)
/*      */     {
/*      */       
/*  550 */       clone.m_predCount = this.m_predicateIndex;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     int next;
/*      */ 
/*      */     
/*  558 */     while (-1 != (next = clone.nextNode()))
/*      */     {
/*  560 */       pos++;
/*      */     }
/*      */     
/*  563 */     if (isPredicateTest && this.m_predicateIndex < 1) {
/*  564 */       this.m_length = pos;
/*      */     }
/*  566 */     return pos;
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
/*      */   public boolean isFresh() {
/*  578 */     return (this.m_pos == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int previousNode() {
/*  589 */     throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_CANNOT_ITERATE", null));
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
/*      */   public int getWhatToShow() {
/*  610 */     return -17;
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
/*      */   public DTMFilter getFilter() {
/*  623 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRoot() {
/*  634 */     return this.m_context;
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
/*      */   public boolean getExpandEntityReferences() {
/*  654 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean m_allowDetach = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void allowDetachToRelease(boolean allowRelease) {
/*  668 */     this.m_allowDetach = allowRelease;
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
/*      */   public void detach() {
/*  680 */     if (this.m_allowDetach) {
/*      */ 
/*      */ 
/*      */       
/*  684 */       this.m_execContext = null;
/*      */       
/*  686 */       this.m_cdtm = null;
/*  687 */       this.m_length = -1;
/*  688 */       this.m_pos = 0;
/*  689 */       this.m_lastFetched = -1;
/*  690 */       this.m_context = -1;
/*  691 */       this.m_currentContextNode = -1;
/*      */       
/*  693 */       this.m_clones.freeInstance(this);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reset() {
/*  702 */     assertion(false, "This iterator can not reset!");
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
/*      */   public DTMIterator cloneWithReset() throws CloneNotSupportedException {
/*  717 */     LocPathIterator clone = (LocPathIterator)this.m_clones.getInstanceOrThrow();
/*  718 */     clone.m_execContext = this.m_execContext;
/*  719 */     clone.m_cdtm = this.m_cdtm;
/*      */     
/*  721 */     clone.m_context = this.m_context;
/*  722 */     clone.m_currentContextNode = this.m_currentContextNode;
/*  723 */     clone.m_stackFrame = this.m_stackFrame;
/*      */ 
/*      */ 
/*      */     
/*  727 */     return clone;
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
/*      */   public abstract int nextNode();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int returnNextNode(int nextNode) {
/*  766 */     if (-1 != nextNode)
/*      */     {
/*  768 */       this.m_pos++;
/*      */     }
/*      */     
/*  771 */     this.m_lastFetched = nextNode;
/*      */     
/*  773 */     if (-1 == nextNode) {
/*  774 */       this.m_foundLast = true;
/*      */     }
/*  776 */     return nextNode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentNode() {
/*  786 */     return this.m_lastFetched;
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
/*      */   public void runTo(int index) {
/*  801 */     if (this.m_foundLast || (index >= 0 && index <= getCurrentPos())) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  806 */     if (-1 == index) { int n; do {
/*      */       
/*  808 */       } while (-1 != (n = nextNode())); }
/*      */     else
/*      */     { int i;
/*      */       
/*  812 */       while (-1 != (i = nextNode())) {
/*      */         
/*  814 */         if (getCurrentPos() >= index) {
/*      */           break;
/*      */         }
/*      */       }  }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean getFoundLast() {
/*  827 */     return this.m_foundLast;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final XPathContext getXPathContext() {
/*  838 */     return this.m_execContext;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getContext() {
/*  848 */     return this.m_context;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getCurrentContextNode() {
/*  859 */     return this.m_currentContextNode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setCurrentContextNode(int n) {
/*  869 */     this.m_currentContextNode = n;
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
/*      */   public final PrefixResolver getPrefixResolver() {
/*  891 */     if (null == this.m_prefixResolver)
/*      */     {
/*  893 */       this.m_prefixResolver = (PrefixResolver)getExpressionOwner();
/*      */     }
/*      */     
/*  896 */     return this.m_prefixResolver;
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
/*      */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/*  924 */     if (visitor.visitLocationPath(owner, this)) {
/*      */       
/*  926 */       visitor.visitStep(owner, this);
/*  927 */       callPredicateVisitors(visitor);
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
/*  939 */   protected transient IteratorPool m_clones = new IteratorPool(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient DTM m_cdtm;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  950 */   transient int m_stackFrame = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean m_isTopLevel = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  961 */   public transient int m_lastFetched = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  967 */   protected transient int m_context = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  975 */   protected transient int m_currentContextNode = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  980 */   protected transient int m_pos = 0;
/*      */   
/*  982 */   protected transient int m_length = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PrefixResolver m_prefixResolver;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient XPathContext m_execContext;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDocOrdered() {
/* 1005 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAxis() {
/* 1016 */     return -1;
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
/*      */   public int getLastPos(XPathContext xctxt) {
/* 1032 */     return getLength();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/LocPathIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */