/*     */ package org.apache.batik.dom.traversal;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.traversal.NodeFilter;
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
/*     */ public class DOMNodeIterator
/*     */   implements NodeIterator
/*     */ {
/*     */   protected static final short INITIAL = 0;
/*     */   protected static final short INVALID = 1;
/*     */   protected static final short FORWARD = 2;
/*     */   protected static final short BACKWARD = 3;
/*     */   protected AbstractDocument document;
/*     */   protected Node root;
/*     */   protected int whatToShow;
/*     */   protected NodeFilter filter;
/*     */   protected boolean expandEntityReferences;
/*     */   protected short state;
/*     */   protected Node referenceNode;
/*     */   
/*     */   public DOMNodeIterator(AbstractDocument doc, Node n, int what, NodeFilter nf, boolean exp) {
/* 103 */     this.document = doc;
/* 104 */     this.root = n;
/* 105 */     this.whatToShow = what;
/* 106 */     this.filter = nf;
/* 107 */     this.expandEntityReferences = exp;
/*     */     
/* 109 */     this.referenceNode = this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getRoot() {
/* 116 */     return this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWhatToShow() {
/* 123 */     return this.whatToShow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeFilter getFilter() {
/* 130 */     return this.filter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getExpandEntityReferences() {
/* 137 */     return this.expandEntityReferences;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node nextNode() {
/* 144 */     switch (this.state) {
/*     */       case 1:
/* 146 */         throw this.document.createDOMException((short)11, "detached.iterator", null);
/*     */ 
/*     */       
/*     */       case 0:
/*     */       case 3:
/* 151 */         this.state = 2;
/* 152 */         return this.referenceNode;
/*     */     } 
/*     */ 
/*     */     
/*     */     do {
/* 157 */       unfilteredNextNode();
/* 158 */       if (this.referenceNode == null) {
/* 159 */         return null;
/*     */       }
/* 161 */     } while ((this.whatToShow & 1 << this.referenceNode.getNodeType() - 1) == 0 || (
/* 162 */       this.filter != null && this.filter.acceptNode(this.referenceNode) != 1));
/*     */     
/* 164 */     return this.referenceNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node previousNode() {
/* 174 */     switch (this.state) {
/*     */       case 1:
/* 176 */         throw this.document.createDOMException((short)11, "detached.iterator", null);
/*     */ 
/*     */       
/*     */       case 0:
/*     */       case 2:
/* 181 */         this.state = 3;
/* 182 */         return this.referenceNode;
/*     */     } 
/*     */ 
/*     */     
/*     */     do {
/* 187 */       unfilteredPreviousNode();
/* 188 */       if (this.referenceNode == null) {
/* 189 */         return this.referenceNode;
/*     */       }
/* 191 */     } while ((this.whatToShow & 1 << this.referenceNode.getNodeType() - 1) == 0 || (
/* 192 */       this.filter != null && this.filter.acceptNode(this.referenceNode) != 1));
/*     */     
/* 194 */     return this.referenceNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detach() {
/* 204 */     this.state = 1;
/* 205 */     this.document.detachNodeIterator(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nodeToBeRemoved(Node removedNode) {
/* 212 */     if (this.state == 1) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 217 */     Node node = this.referenceNode;
/* 218 */     for (; node != null && node != this.root; 
/* 219 */       node = node.getParentNode()) {
/* 220 */       if (node == removedNode) {
/*     */         break;
/*     */       }
/*     */     } 
/* 224 */     if (node == null || node == this.root) {
/*     */       return;
/*     */     }
/*     */     
/* 228 */     if (this.state == 3) {
/*     */       
/* 230 */       if (node.getNodeType() != 5 || this.expandEntityReferences) {
/*     */         
/* 232 */         Node node1 = node.getFirstChild();
/* 233 */         if (node1 != null) {
/* 234 */           this.referenceNode = node1;
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       
/* 240 */       Node n = node.getNextSibling();
/* 241 */       if (n != null) {
/* 242 */         this.referenceNode = n;
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 247 */       n = node;
/* 248 */       while ((n = n.getParentNode()) != null && n != this.root) {
/* 249 */         Node t = n.getNextSibling();
/* 250 */         if (t != null) {
/* 251 */           this.referenceNode = t;
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 256 */       this.referenceNode = null;
/*     */     } else {
/* 258 */       Node n = node.getPreviousSibling();
/*     */ 
/*     */       
/* 261 */       if (n == null) {
/* 262 */         this.referenceNode = node.getParentNode();
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 267 */       if (n.getNodeType() != 5 || this.expandEntityReferences) {
/*     */         Node t;
/*     */         
/* 270 */         while ((t = n.getLastChild()) != null) {
/* 271 */           n = t;
/*     */         }
/*     */       } 
/*     */       
/* 275 */       this.referenceNode = n;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void unfilteredNextNode() {
/* 283 */     if (this.referenceNode == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 288 */     if (this.referenceNode.getNodeType() != 5 || this.expandEntityReferences) {
/*     */       
/* 290 */       Node node = this.referenceNode.getFirstChild();
/* 291 */       if (node != null) {
/* 292 */         this.referenceNode = node;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 298 */     Node n = this.referenceNode.getNextSibling();
/* 299 */     if (n != null) {
/* 300 */       this.referenceNode = n;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 305 */     n = this.referenceNode;
/* 306 */     while ((n = n.getParentNode()) != null && n != this.root) {
/* 307 */       Node t = n.getNextSibling();
/* 308 */       if (t != null) {
/* 309 */         this.referenceNode = t;
/*     */         return;
/*     */       } 
/*     */     } 
/* 313 */     this.referenceNode = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void unfilteredPreviousNode() {
/* 320 */     if (this.referenceNode == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 325 */     if (this.referenceNode == this.root) {
/* 326 */       this.referenceNode = null;
/*     */       
/*     */       return;
/*     */     } 
/* 330 */     Node n = this.referenceNode.getPreviousSibling();
/*     */ 
/*     */     
/* 333 */     if (n == null) {
/* 334 */       this.referenceNode = this.referenceNode.getParentNode();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 339 */     if (n.getNodeType() != 5 || this.expandEntityReferences) {
/*     */       Node t;
/*     */       
/* 342 */       while ((t = n.getLastChild()) != null) {
/* 343 */         n = t;
/*     */       }
/*     */     } 
/*     */     
/* 347 */     this.referenceNode = n;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/traversal/DOMNodeIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */