/*     */ package org.apache.batik.dom.traversal;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.traversal.NodeFilter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DOMTreeWalker
/*     */   implements TreeWalker
/*     */ {
/*     */   protected Node root;
/*     */   protected int whatToShow;
/*     */   protected NodeFilter filter;
/*     */   protected boolean expandEntityReferences;
/*     */   protected Node currentNode;
/*     */   
/*     */   public DOMTreeWalker(Node n, int what, NodeFilter nf, boolean exp) {
/*  71 */     this.root = n;
/*  72 */     this.whatToShow = what;
/*  73 */     this.filter = nf;
/*  74 */     this.expandEntityReferences = exp;
/*     */     
/*  76 */     this.currentNode = this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getRoot() {
/*  83 */     return this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWhatToShow() {
/*  90 */     return this.whatToShow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeFilter getFilter() {
/*  97 */     return this.filter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getExpandEntityReferences() {
/* 104 */     return this.expandEntityReferences;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getCurrentNode() {
/* 111 */     return this.currentNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentNode(Node n) {
/* 118 */     if (n == null) {
/* 119 */       throw ((AbstractNode)this.root).createDOMException((short)9, "null.current.node", null);
/*     */     }
/*     */ 
/*     */     
/* 123 */     this.currentNode = n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node parentNode() {
/* 130 */     Node result = parentNode(this.currentNode);
/* 131 */     if (result != null) {
/* 132 */       this.currentNode = result;
/*     */     }
/* 134 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node firstChild() {
/* 141 */     Node result = firstChild(this.currentNode);
/* 142 */     if (result != null) {
/* 143 */       this.currentNode = result;
/*     */     }
/* 145 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node lastChild() {
/* 152 */     Node result = lastChild(this.currentNode);
/* 153 */     if (result != null) {
/* 154 */       this.currentNode = result;
/*     */     }
/* 156 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node previousSibling() {
/* 163 */     Node result = previousSibling(this.currentNode, this.root);
/* 164 */     if (result != null) {
/* 165 */       this.currentNode = result;
/*     */     }
/* 167 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node nextSibling() {
/* 174 */     Node result = nextSibling(this.currentNode, this.root);
/* 175 */     if (result != null) {
/* 176 */       this.currentNode = result;
/*     */     }
/* 178 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node previousNode() {
/* 185 */     Node result = previousSibling(this.currentNode, this.root);
/* 186 */     if (result == null) {
/* 187 */       result = parentNode(this.currentNode);
/* 188 */       if (result != null) {
/* 189 */         this.currentNode = result;
/*     */       }
/* 191 */       return result;
/*     */     } 
/* 193 */     Node n = lastChild(result);
/* 194 */     Node last = n;
/* 195 */     while (n != null) {
/* 196 */       last = n;
/* 197 */       n = lastChild(last);
/*     */     } 
/* 199 */     return this.currentNode = (last != null) ? last : result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node nextNode() {
/*     */     Node result;
/* 207 */     if ((result = firstChild(this.currentNode)) != null) {
/* 208 */       return this.currentNode = result;
/*     */     }
/* 210 */     if ((result = nextSibling(this.currentNode, this.root)) != null) {
/* 211 */       return this.currentNode = result;
/*     */     }
/* 213 */     Node parent = this.currentNode;
/*     */     while (true) {
/* 215 */       parent = parentNode(parent);
/* 216 */       if (parent == null) {
/* 217 */         return null;
/*     */       }
/* 219 */       if ((result = nextSibling(parent, this.root)) != null) {
/* 220 */         return this.currentNode = result;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node parentNode(Node n) {
/* 229 */     if (n == this.root) {
/* 230 */       return null;
/*     */     }
/* 232 */     Node result = n;
/*     */     do {
/* 234 */       result = result.getParentNode();
/* 235 */       if (result == null) {
/* 236 */         return null;
/*     */       }
/* 238 */     } while ((this.whatToShow & 1 << result.getNodeType() - 1) == 0 || (
/* 239 */       this.filter != null && this.filter.acceptNode(result) != 1));
/*     */     
/* 241 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node firstChild(Node n) {
/*     */     Node t;
/* 251 */     if (n.getNodeType() == 5 && !this.expandEntityReferences)
/*     */     {
/* 253 */       return null;
/*     */     }
/* 255 */     Node result = n.getFirstChild();
/* 256 */     if (result == null) {
/* 257 */       return null;
/*     */     }
/* 259 */     switch (acceptNode(result)) {
/*     */       case 1:
/* 261 */         return result;
/*     */       case 3:
/* 263 */         t = firstChild(result);
/* 264 */         if (t != null) {
/* 265 */           return t;
/*     */         }
/*     */         break;
/*     */     } 
/* 269 */     return nextSibling(result, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node lastChild(Node n) {
/*     */     Node t;
/* 277 */     if (n.getNodeType() == 5 && !this.expandEntityReferences)
/*     */     {
/* 279 */       return null;
/*     */     }
/* 281 */     Node result = n.getLastChild();
/* 282 */     if (result == null) {
/* 283 */       return null;
/*     */     }
/* 285 */     switch (acceptNode(result)) {
/*     */       case 1:
/* 287 */         return result;
/*     */       case 3:
/* 289 */         t = lastChild(result);
/* 290 */         if (t != null) {
/* 291 */           return t;
/*     */         }
/*     */         break;
/*     */     } 
/* 295 */     return previousSibling(result, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node previousSibling(Node n, Node root) {
/*     */     while (true) {
/*     */       Node t;
/* 304 */       if (n == root) {
/* 305 */         return null;
/*     */       }
/* 307 */       Node result = n.getPreviousSibling();
/* 308 */       if (result == null) {
/* 309 */         result = n.getParentNode();
/* 310 */         if (result == null || result == root) {
/* 311 */           return null;
/*     */         }
/* 313 */         if (acceptNode(result) == 3) {
/* 314 */           n = result;
/*     */           continue;
/*     */         } 
/* 317 */         return null;
/*     */       } 
/* 319 */       switch (acceptNode(result)) {
/*     */         case 1:
/* 321 */           return result;
/*     */         case 3:
/* 323 */           t = lastChild(result);
/* 324 */           if (t != null) {
/* 325 */             return t;
/*     */           }
/*     */           break;
/*     */       } 
/* 329 */       n = result;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node nextSibling(Node n, Node root) {
/*     */     while (true) {
/*     */       Node t;
/* 340 */       if (n == root) {
/* 341 */         return null;
/*     */       }
/* 343 */       Node result = n.getNextSibling();
/* 344 */       if (result == null) {
/* 345 */         result = n.getParentNode();
/* 346 */         if (result == null || result == root) {
/* 347 */           return null;
/*     */         }
/* 349 */         if (acceptNode(result) == 3) {
/* 350 */           n = result;
/*     */           continue;
/*     */         } 
/* 353 */         return null;
/*     */       } 
/*     */       
/* 356 */       switch (acceptNode(result)) {
/*     */         case 1:
/* 358 */           return result;
/*     */         case 3:
/* 360 */           t = firstChild(result);
/* 361 */           if (t != null) {
/* 362 */             return t;
/*     */           }
/*     */           break;
/*     */       } 
/* 366 */       n = result;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short acceptNode(Node n) {
/* 376 */     if ((this.whatToShow & 1 << n.getNodeType() - 1) != 0) {
/* 377 */       if (this.filter == null) {
/* 378 */         return 1;
/*     */       }
/* 380 */       return this.filter.acceptNode(n);
/*     */     } 
/*     */     
/* 383 */     return 3;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/traversal/DOMTreeWalker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */