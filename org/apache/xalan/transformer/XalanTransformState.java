/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import javax.xml.transform.Transformer;
/*     */ import org.apache.xalan.templates.ElemTemplate;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.dtm.ref.DTMNodeIterator;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.traversal.NodeIterator;
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
/*     */ public class XalanTransformState
/*     */   implements TransformState
/*     */ {
/*  38 */   Node m_node = null;
/*  39 */   ElemTemplateElement m_currentElement = null;
/*  40 */   ElemTemplate m_currentTemplate = null;
/*  41 */   ElemTemplate m_matchedTemplate = null;
/*  42 */   int m_currentNodeHandle = -1;
/*  43 */   Node m_currentNode = null;
/*  44 */   int m_matchedNode = -1;
/*  45 */   DTMIterator m_contextNodeList = null;
/*     */   boolean m_elemPending = false;
/*  47 */   TransformerImpl m_transformer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentNode(Node n) {
/*  53 */     this.m_node = n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetState(Transformer transformer) {
/*  60 */     if (transformer != null && transformer instanceof TransformerImpl) {
/*  61 */       this.m_transformer = (TransformerImpl)transformer;
/*  62 */       this.m_currentElement = this.m_transformer.getCurrentElement();
/*  63 */       this.m_currentTemplate = this.m_transformer.getCurrentTemplate();
/*  64 */       this.m_matchedTemplate = this.m_transformer.getMatchedTemplate();
/*  65 */       int currentNodeHandle = this.m_transformer.getCurrentNode();
/*  66 */       DTM dtm = this.m_transformer.getXPathContext().getDTM(currentNodeHandle);
/*  67 */       this.m_currentNode = dtm.getNode(currentNodeHandle);
/*  68 */       this.m_matchedNode = this.m_transformer.getMatchedNode();
/*  69 */       this.m_contextNodeList = this.m_transformer.getContextNodeList();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElemTemplateElement getCurrentElement() {
/*  77 */     if (this.m_elemPending) {
/*  78 */       return this.m_currentElement;
/*     */     }
/*  80 */     return this.m_transformer.getCurrentElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCurrentNode() {
/*  87 */     if (this.m_currentNode != null) {
/*  88 */       return this.m_currentNode;
/*     */     }
/*  90 */     DTM dtm = this.m_transformer.getXPathContext().getDTM(this.m_transformer.getCurrentNode());
/*  91 */     return dtm.getNode(this.m_transformer.getCurrentNode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElemTemplate getCurrentTemplate() {
/*  99 */     if (this.m_elemPending) {
/* 100 */       return this.m_currentTemplate;
/*     */     }
/* 102 */     return this.m_transformer.getCurrentTemplate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElemTemplate getMatchedTemplate() {
/* 109 */     if (this.m_elemPending) {
/* 110 */       return this.m_matchedTemplate;
/*     */     }
/* 112 */     return this.m_transformer.getMatchedTemplate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getMatchedNode() {
/* 120 */     if (this.m_elemPending) {
/* 121 */       DTM dTM = this.m_transformer.getXPathContext().getDTM(this.m_matchedNode);
/* 122 */       return dTM.getNode(this.m_matchedNode);
/*     */     } 
/* 124 */     DTM dtm = this.m_transformer.getXPathContext().getDTM(this.m_transformer.getMatchedNode());
/* 125 */     return dtm.getNode(this.m_transformer.getMatchedNode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeIterator getContextNodeList() {
/* 133 */     if (this.m_elemPending) {
/* 134 */       return (NodeIterator)new DTMNodeIterator(this.m_contextNodeList);
/*     */     }
/* 136 */     return (NodeIterator)new DTMNodeIterator(this.m_transformer.getContextNodeList());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Transformer getTransformer() {
/* 143 */     return this.m_transformer;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/XalanTransformState.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */