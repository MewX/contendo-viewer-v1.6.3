/*     */ package org.apache.batik.dom.xbl;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public class GenericXBLManager
/*     */   implements XBLManager
/*     */ {
/*     */   protected boolean isProcessing;
/*     */   
/*     */   public void startProcessing() {
/*  44 */     this.isProcessing = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopProcessing() {
/*  51 */     this.isProcessing = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isProcessing() {
/*  58 */     return this.isProcessing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getXblParentNode(Node n) {
/*  65 */     return n.getParentNode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeList getXblChildNodes(Node n) {
/*  72 */     return n.getChildNodes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeList getXblScopedChildNodes(Node n) {
/*  80 */     return n.getChildNodes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getXblFirstChild(Node n) {
/*  87 */     return n.getFirstChild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getXblLastChild(Node n) {
/*  94 */     return n.getLastChild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getXblPreviousSibling(Node n) {
/* 102 */     return n.getPreviousSibling();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getXblNextSibling(Node n) {
/* 110 */     return n.getNextSibling();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getXblFirstElementChild(Node n) {
/* 117 */     Node m = n.getFirstChild();
/* 118 */     while (m != null && m.getNodeType() != 1) {
/* 119 */       m = m.getNextSibling();
/*     */     }
/* 121 */     return (Element)m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getXblLastElementChild(Node n) {
/* 128 */     Node m = n.getLastChild();
/* 129 */     while (m != null && m.getNodeType() != 1) {
/* 130 */       m = m.getPreviousSibling();
/*     */     }
/* 132 */     return (Element)m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getXblPreviousElementSibling(Node n) {
/* 140 */     Node m = n;
/*     */     do {
/* 142 */       m = m.getPreviousSibling();
/* 143 */     } while (m != null && m.getNodeType() != 1);
/* 144 */     return (Element)m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getXblNextElementSibling(Node n) {
/* 152 */     Node m = n;
/*     */     do {
/* 154 */       m = m.getNextSibling();
/* 155 */     } while (m != null && m.getNodeType() != 1);
/* 156 */     return (Element)m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getXblBoundElement(Node n) {
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getXblShadowTree(Node n) {
/* 170 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeList getXblDefinitions(Node n) {
/* 177 */     return AbstractNode.EMPTY_NODE_LIST;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/xbl/GenericXBLManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */