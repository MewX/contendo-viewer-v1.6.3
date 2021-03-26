/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.apache.batik.anim.dom.XBLOMContentElement;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultContentSelector
/*     */   extends AbstractContentSelector
/*     */ {
/*     */   protected SelectedNodes selectedContent;
/*     */   
/*     */   public DefaultContentSelector(ContentManager cm, XBLOMContentElement content, Element bound) {
/*  49 */     super(cm, content, bound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeList getSelectedContent() {
/*  57 */     if (this.selectedContent == null) {
/*  58 */       this.selectedContent = new SelectedNodes();
/*     */     }
/*  60 */     return this.selectedContent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean update() {
/*  71 */     if (this.selectedContent == null) {
/*  72 */       this.selectedContent = new SelectedNodes();
/*  73 */       return true;
/*     */     } 
/*  75 */     return this.selectedContent.update();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class SelectedNodes
/*     */     implements NodeList
/*     */   {
/*  87 */     protected ArrayList nodes = new ArrayList(10);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SelectedNodes() {
/*  93 */       update();
/*     */     }
/*     */     
/*     */     protected boolean update() {
/*  97 */       ArrayList<E> oldNodes = (ArrayList)this.nodes.clone();
/*  98 */       this.nodes.clear();
/*  99 */       for (Node n = DefaultContentSelector.this.boundElement.getFirstChild(); n != null; n = n.getNextSibling()) {
/* 100 */         if (!DefaultContentSelector.this.isSelected(n))
/*     */         {
/*     */           
/* 103 */           this.nodes.add(n); } 
/*     */       } 
/* 105 */       int nodesSize = this.nodes.size();
/* 106 */       if (oldNodes.size() != nodesSize) {
/* 107 */         return true;
/*     */       }
/* 109 */       for (int i = 0; i < nodesSize; i++) {
/* 110 */         if (oldNodes.get(i) != this.nodes.get(i)) {
/* 111 */           return true;
/*     */         }
/*     */       } 
/* 114 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Node item(int index) {
/* 121 */       if (index < 0 || index >= this.nodes.size()) {
/* 122 */         return null;
/*     */       }
/* 124 */       return this.nodes.get(index);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getLength() {
/* 131 */       return this.nodes.size();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/DefaultContentSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */