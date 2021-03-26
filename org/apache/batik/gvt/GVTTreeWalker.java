/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GVTTreeWalker
/*     */ {
/*     */   protected GraphicsNode gvtRoot;
/*     */   protected GraphicsNode treeRoot;
/*     */   protected GraphicsNode currentNode;
/*     */   
/*     */   public GVTTreeWalker(GraphicsNode treeRoot) {
/*  46 */     this.gvtRoot = treeRoot.getRoot();
/*  47 */     this.treeRoot = treeRoot;
/*  48 */     this.currentNode = treeRoot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getRoot() {
/*  55 */     return this.treeRoot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getGVTRoot() {
/*  62 */     return this.gvtRoot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentGraphicsNode(GraphicsNode node) {
/*  73 */     if (node.getRoot() != this.gvtRoot) {
/*  74 */       throw new IllegalArgumentException("The node " + node + " is not part of the document " + this.gvtRoot);
/*     */     }
/*     */     
/*  77 */     this.currentNode = node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getCurrentGraphicsNode() {
/*  84 */     return this.currentNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode previousGraphicsNode() {
/*  93 */     GraphicsNode result = getPreviousGraphicsNode(this.currentNode);
/*  94 */     if (result != null) {
/*  95 */       this.currentNode = result;
/*     */     }
/*  97 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode nextGraphicsNode() {
/* 106 */     GraphicsNode result = getNextGraphicsNode(this.currentNode);
/* 107 */     if (result != null) {
/* 108 */       this.currentNode = result;
/*     */     }
/* 110 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode parentGraphicsNode() {
/* 120 */     if (this.currentNode == this.treeRoot) return null;
/*     */     
/* 122 */     GraphicsNode result = this.currentNode.getParent();
/* 123 */     if (result != null) {
/* 124 */       this.currentNode = result;
/*     */     }
/* 126 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getNextSibling() {
/* 135 */     GraphicsNode result = getNextSibling(this.currentNode);
/* 136 */     if (result != null) {
/* 137 */       this.currentNode = result;
/*     */     }
/* 139 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getPreviousSibling() {
/* 149 */     GraphicsNode result = getPreviousSibling(this.currentNode);
/* 150 */     if (result != null) {
/* 151 */       this.currentNode = result;
/*     */     }
/* 153 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode firstChild() {
/* 162 */     GraphicsNode result = getFirstChild(this.currentNode);
/* 163 */     if (result != null) {
/* 164 */       this.currentNode = result;
/*     */     }
/* 166 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode lastChild() {
/* 175 */     GraphicsNode result = getLastChild(this.currentNode);
/* 176 */     if (result != null) {
/* 177 */       this.currentNode = result;
/*     */     }
/* 179 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsNode getNextGraphicsNode(GraphicsNode node) {
/* 186 */     if (node == null) {
/* 187 */       return null;
/*     */     }
/*     */     
/* 190 */     GraphicsNode n = getFirstChild(node);
/* 191 */     if (n != null) {
/* 192 */       return n;
/*     */     }
/*     */ 
/*     */     
/* 196 */     n = getNextSibling(node);
/* 197 */     if (n != null) {
/* 198 */       return n;
/*     */     }
/*     */ 
/*     */     
/* 202 */     n = node;
/* 203 */     while ((n = n.getParent()) != null && n != this.treeRoot) {
/* 204 */       GraphicsNode t = getNextSibling(n);
/* 205 */       if (t != null) {
/* 206 */         return t;
/*     */       }
/*     */     } 
/* 209 */     return null;
/*     */   }
/*     */   
/*     */   protected GraphicsNode getPreviousGraphicsNode(GraphicsNode node) {
/* 213 */     if (node == null) {
/* 214 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 218 */     if (node == this.treeRoot) {
/* 219 */       return null;
/*     */     }
/*     */     
/* 222 */     GraphicsNode n = getPreviousSibling(node);
/*     */ 
/*     */     
/* 225 */     if (n == null) {
/* 226 */       return node.getParent();
/*     */     }
/*     */     
/*     */     GraphicsNode t;
/*     */     
/* 231 */     while ((t = getLastChild(n)) != null) {
/* 232 */       n = t;
/*     */     }
/* 234 */     return n;
/*     */   }
/*     */   
/*     */   protected static GraphicsNode getLastChild(GraphicsNode node) {
/* 238 */     if (!(node instanceof CompositeGraphicsNode)) {
/* 239 */       return null;
/*     */     }
/* 241 */     CompositeGraphicsNode parent = (CompositeGraphicsNode)node;
/* 242 */     List<GraphicsNode> children = parent.getChildren();
/* 243 */     if (children == null) {
/* 244 */       return null;
/*     */     }
/* 246 */     if (children.size() >= 1) {
/* 247 */       return children.get(children.size() - 1);
/*     */     }
/* 249 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static GraphicsNode getPreviousSibling(GraphicsNode node) {
/* 254 */     CompositeGraphicsNode parent = node.getParent();
/* 255 */     if (parent == null) {
/* 256 */       return null;
/*     */     }
/* 258 */     List<GraphicsNode> children = parent.getChildren();
/* 259 */     if (children == null) {
/* 260 */       return null;
/*     */     }
/* 262 */     int index = children.indexOf(node);
/* 263 */     if (index - 1 >= 0) {
/* 264 */       return children.get(index - 1);
/*     */     }
/* 266 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static GraphicsNode getFirstChild(GraphicsNode node) {
/* 271 */     if (!(node instanceof CompositeGraphicsNode)) {
/* 272 */       return null;
/*     */     }
/* 274 */     CompositeGraphicsNode parent = (CompositeGraphicsNode)node;
/* 275 */     List<GraphicsNode> children = parent.getChildren();
/* 276 */     if (children == null) {
/* 277 */       return null;
/*     */     }
/* 279 */     if (children.size() >= 1) {
/* 280 */       return children.get(0);
/*     */     }
/* 282 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static GraphicsNode getNextSibling(GraphicsNode node) {
/* 287 */     CompositeGraphicsNode parent = node.getParent();
/* 288 */     if (parent == null) {
/* 289 */       return null;
/*     */     }
/* 291 */     List<GraphicsNode> children = parent.getChildren();
/* 292 */     if (children == null) {
/* 293 */       return null;
/*     */     }
/* 295 */     int index = children.indexOf(node);
/* 296 */     if (index + 1 < children.size()) {
/* 297 */       return children.get(index + 1);
/*     */     }
/* 299 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/GVTTreeWalker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */