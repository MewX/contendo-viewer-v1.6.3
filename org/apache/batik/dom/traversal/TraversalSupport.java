/*     */ package org.apache.batik.dom.traversal;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.traversal.NodeFilter;
/*     */ import org.w3c.dom.traversal.NodeIterator;
/*     */ import org.w3c.dom.traversal.TreeWalker;
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
/*     */ public class TraversalSupport
/*     */ {
/*     */   protected List iterators;
/*     */   
/*     */   public static TreeWalker createTreeWalker(AbstractDocument doc, Node root, int whatToShow, NodeFilter filter, boolean entityReferenceExpansion) {
/*  59 */     if (root == null) {
/*  60 */       throw doc.createDOMException((short)9, "null.root", null);
/*     */     }
/*     */     
/*  63 */     return new DOMTreeWalker(root, whatToShow, filter, entityReferenceExpansion);
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
/*     */   public NodeIterator createNodeIterator(AbstractDocument doc, Node root, int whatToShow, NodeFilter filter, boolean entityReferenceExpansion) throws DOMException {
/*  76 */     if (root == null) {
/*  77 */       throw doc.createDOMException((short)9, "null.root", null);
/*     */     }
/*     */     
/*  80 */     NodeIterator result = new DOMNodeIterator(doc, root, whatToShow, filter, entityReferenceExpansion);
/*     */ 
/*     */     
/*  83 */     if (this.iterators == null) {
/*  84 */       this.iterators = new LinkedList();
/*     */     }
/*  86 */     this.iterators.add(result);
/*     */     
/*  88 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nodeToBeRemoved(Node removedNode) {
/*  95 */     if (this.iterators != null) {
/*  96 */       for (Object iterator : this.iterators) {
/*  97 */         ((DOMNodeIterator)iterator).nodeToBeRemoved(removedNode);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detachNodeIterator(NodeIterator it) {
/* 106 */     this.iterators.remove(it);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/traversal/TraversalSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */