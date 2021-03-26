/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.NodeSorter;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.IntStack;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XNodeSet;
/*     */ import org.apache.xpath.objects.XObject;
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
/*     */ public class ElemForEach
/*     */   extends ElemTemplateElement
/*     */   implements ExpressionOwner
/*     */ {
/*     */   static final boolean DEBUG = false;
/*     */   public boolean m_doc_cache_off = false;
/*     */   protected Expression m_selectExpression;
/*     */   protected XPath m_xpath;
/*     */   protected Vector m_sortElems;
/*     */   
/*     */   public void setSelect(XPath xpath) {
/*     */     this.m_selectExpression = xpath.getExpression();
/*     */     this.m_xpath = xpath;
/*     */   }
/*     */   
/*     */   public ElemForEach() {
/*  80 */     this.m_selectExpression = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     this.m_xpath = null;
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
/* 185 */     this.m_sortElems = null;
/*     */   }
/*     */   
/*     */   public Expression getSelect() {
/*     */     return this.m_selectExpression;
/*     */   }
/*     */   
/*     */   public int getSortElemCount() {
/* 193 */     return (this.m_sortElems == null) ? 0 : this.m_sortElems.size();
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
/*     */   public ElemSort getSortElem(int i) {
/* 205 */     return this.m_sortElems.elementAt(i);
/*     */   }
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException { super.compose(sroot); int length = getSortElemCount(); for (int i = 0; i < length; i++)
/*     */       getSortElem(i).compose(sroot);  Vector vnames = sroot.getComposeState().getVariableNames();
/*     */     if (null != this.m_selectExpression) {
/*     */       this.m_selectExpression.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
/*     */     } else {
/*     */       this.m_selectExpression = (getStylesheetRoot()).m_selectDefault.getExpression();
/*     */     }  } public void endCompose(StylesheetRoot sroot) throws TransformerException { int length = getSortElemCount();
/*     */     for (int i = 0; i < length; i++)
/*     */       getSortElem(i).endCompose(sroot); 
/* 216 */     super.endCompose(sroot); } public void setSortElem(ElemSort sortElem) { if (null == this.m_sortElems) {
/* 217 */       this.m_sortElems = new Vector();
/*     */     }
/* 219 */     this.m_sortElems.addElement(sortElem); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXSLToken() {
/* 230 */     return 28;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 240 */     return "for-each";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 253 */     transformer.pushCurrentTemplateRuleIsNull(true);
/* 254 */     if (TransformerImpl.S_DEBUG) {
/* 255 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/*     */     
/*     */     try {
/* 259 */       transformSelectedNodes(transformer);
/*     */     }
/*     */     finally {
/*     */       
/* 263 */       if (TransformerImpl.S_DEBUG)
/* 264 */         transformer.getTraceManager().fireTraceEndEvent(this); 
/* 265 */       transformer.popCurrentTemplateRuleIsNull();
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
/*     */   protected ElemTemplateElement getTemplateMatch() {
/* 277 */     return this;
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
/*     */   public DTMIterator sortNodes(XPathContext xctxt, Vector keys, DTMIterator sourceNodes) throws TransformerException {
/* 297 */     NodeSorter sorter = new NodeSorter(xctxt);
/* 298 */     sourceNodes.setShouldCacheNodes(true);
/* 299 */     sourceNodes.runTo(-1);
/* 300 */     xctxt.pushContextNodeList(sourceNodes);
/*     */ 
/*     */     
/*     */     try {
/* 304 */       sorter.sort(sourceNodes, keys, xctxt);
/* 305 */       sourceNodes.setCurrentPos(0);
/*     */     }
/*     */     finally {
/*     */       
/* 309 */       xctxt.popContextNodeList();
/*     */     } 
/*     */     
/* 312 */     return sourceNodes;
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
/*     */   public void transformSelectedNodes(TransformerImpl transformer) throws TransformerException {
/* 328 */     XPathContext xctxt = transformer.getXPathContext();
/* 329 */     int sourceNode = xctxt.getCurrentNode();
/* 330 */     DTMIterator sourceNodes = this.m_selectExpression.asIterator(xctxt, sourceNode);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 336 */       Vector keys = (this.m_sortElems == null) ? null : transformer.processSortKeys(this, sourceNode);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 341 */       if (null != keys) {
/* 342 */         sourceNodes = sortNodes(xctxt, keys, sourceNodes);
/*     */       }
/* 344 */       if (TransformerImpl.S_DEBUG) {
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
/* 361 */         Expression expr = this.m_xpath.getExpression();
/* 362 */         XObject xObject = expr.execute(xctxt);
/* 363 */         int current = xctxt.getCurrentNode();
/* 364 */         transformer.getTraceManager().fireSelectedEvent(current, this, "select", this.m_xpath, xObject);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 374 */       xctxt.pushCurrentNode(-1);
/*     */       
/* 376 */       IntStack currentNodes = xctxt.getCurrentNodeStack();
/*     */       
/* 378 */       xctxt.pushCurrentExpressionNode(-1);
/*     */       
/* 380 */       IntStack currentExpressionNodes = xctxt.getCurrentExpressionNodeStack();
/*     */       
/* 382 */       xctxt.pushSAXLocatorNull();
/* 383 */       xctxt.pushContextNodeList(sourceNodes);
/* 384 */       transformer.pushElemTemplateElement(null);
/*     */ 
/*     */ 
/*     */       
/* 388 */       DTM dtm = xctxt.getDTM(sourceNode);
/* 389 */       int docID = sourceNode & 0xFFFF0000;
/*     */       
/*     */       int child;
/* 392 */       while (-1 != (child = sourceNodes.nextNode())) {
/*     */         
/* 394 */         currentNodes.setTop(child);
/* 395 */         currentExpressionNodes.setTop(child);
/*     */         
/* 397 */         if ((child & 0xFFFF0000) != docID) {
/*     */           
/* 399 */           dtm = xctxt.getDTM(child);
/* 400 */           docID = child & 0xFFFF0000;
/*     */         } 
/*     */ 
/*     */         
/* 404 */         int nodeType = dtm.getNodeType(child);
/*     */ 
/*     */         
/* 407 */         if (TransformerImpl.S_DEBUG)
/*     */         {
/* 409 */           transformer.getTraceManager().fireTraceEvent(this);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 415 */         for (ElemTemplateElement t = this.m_firstChild; t != null; 
/* 416 */           t = t.m_nextSibling) {
/*     */           
/* 418 */           xctxt.setSAXLocator((SourceLocator)t);
/* 419 */           transformer.setCurrentElement(t);
/* 420 */           t.execute(transformer);
/*     */         } 
/*     */         
/* 423 */         if (TransformerImpl.S_DEBUG) {
/*     */ 
/*     */ 
/*     */           
/* 427 */           transformer.setCurrentElement(null);
/* 428 */           transformer.getTraceManager().fireTraceEndEvent(this);
/*     */         } 
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
/* 442 */         if (this.m_doc_cache_off)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 450 */           xctxt.getSourceTreeManager().removeDocumentFromCache(dtm.getDocument());
/* 451 */           xctxt.release(dtm, false);
/*     */         }
/*     */       
/*     */       } 
/*     */     } finally {
/*     */       
/* 457 */       if (TransformerImpl.S_DEBUG) {
/* 458 */         transformer.getTraceManager().fireSelectedEndEvent(sourceNode, this, "select", new XPath(this.m_selectExpression), (XObject)new XNodeSet(sourceNodes));
/*     */       }
/*     */ 
/*     */       
/* 462 */       xctxt.popSAXLocator();
/* 463 */       xctxt.popContextNodeList();
/* 464 */       transformer.popElemTemplateElement();
/* 465 */       xctxt.popCurrentExpressionNode();
/* 466 */       xctxt.popCurrentNode();
/* 467 */       sourceNodes.detach();
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
/*     */ 
/*     */   
/*     */   public ElemTemplateElement appendChild(ElemTemplateElement newChild) {
/* 486 */     int type = newChild.getXSLToken();
/*     */     
/* 488 */     if (64 == type) {
/*     */       
/* 490 */       setSortElem((ElemSort)newChild);
/*     */       
/* 492 */       return newChild;
/*     */     } 
/*     */     
/* 495 */     return super.appendChild(newChild);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callChildVisitors(XSLTVisitor visitor, boolean callAttributes) {
/* 504 */     if (callAttributes && null != this.m_selectExpression) {
/* 505 */       this.m_selectExpression.callVisitors(this, visitor);
/*     */     }
/* 507 */     int length = getSortElemCount();
/*     */     
/* 509 */     for (int i = 0; i < length; i++)
/*     */     {
/* 511 */       getSortElem(i).callVisitors(visitor);
/*     */     }
/*     */     
/* 514 */     super.callChildVisitors(visitor, callAttributes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Expression getExpression() {
/* 522 */     return this.m_selectExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpression(Expression exp) {
/* 530 */     exp.exprSetParent(this);
/* 531 */     this.m_selectExpression = exp;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemForEach.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */