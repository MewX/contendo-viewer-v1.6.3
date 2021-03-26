/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NodeCachingLinkedList
/*     */   extends AbstractLinkedList
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 6897789178562232073L;
/*     */   protected static final int DEFAULT_MAXIMUM_CACHE_SIZE = 20;
/*     */   protected transient AbstractLinkedList.Node firstCachedNode;
/*     */   protected transient int cacheSize;
/*     */   protected int maximumCacheSize;
/*     */   
/*     */   public NodeCachingLinkedList() {
/*  78 */     this(20);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeCachingLinkedList(Collection coll) {
/*  87 */     super(coll);
/*  88 */     this.maximumCacheSize = 20;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeCachingLinkedList(int maximumCacheSize) {
/*  98 */     this.maximumCacheSize = maximumCacheSize;
/*  99 */     init();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getMaximumCacheSize() {
/* 109 */     return this.maximumCacheSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setMaximumCacheSize(int maximumCacheSize) {
/* 118 */     this.maximumCacheSize = maximumCacheSize;
/* 119 */     shrinkCacheToMaximumSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void shrinkCacheToMaximumSize() {
/* 127 */     while (this.cacheSize > this.maximumCacheSize) {
/* 128 */       getNodeFromCache();
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
/*     */   protected AbstractLinkedList.Node getNodeFromCache() {
/* 140 */     if (this.cacheSize == 0) {
/* 141 */       return null;
/*     */     }
/* 143 */     AbstractLinkedList.Node cachedNode = this.firstCachedNode;
/* 144 */     this.firstCachedNode = cachedNode.next;
/* 145 */     cachedNode.next = null;
/*     */     
/* 147 */     this.cacheSize--;
/* 148 */     return cachedNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isCacheFull() {
/* 157 */     return (this.cacheSize >= this.maximumCacheSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addNodeToCache(AbstractLinkedList.Node node) {
/* 167 */     if (isCacheFull()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 172 */     AbstractLinkedList.Node nextCachedNode = this.firstCachedNode;
/* 173 */     node.previous = null;
/* 174 */     node.next = nextCachedNode;
/* 175 */     node.setValue(null);
/* 176 */     this.firstCachedNode = node;
/* 177 */     this.cacheSize++;
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
/*     */   protected AbstractLinkedList.Node createNode(Object value) {
/* 189 */     AbstractLinkedList.Node cachedNode = getNodeFromCache();
/* 190 */     if (cachedNode == null) {
/* 191 */       return super.createNode(value);
/*     */     }
/* 193 */     cachedNode.setValue(value);
/* 194 */     return cachedNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeNode(AbstractLinkedList.Node node) {
/* 205 */     super.removeNode(node);
/* 206 */     addNodeToCache(node);
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
/*     */   protected void removeAllNodes() {
/* 219 */     int numberOfNodesToCache = Math.min(this.size, this.maximumCacheSize - this.cacheSize);
/* 220 */     AbstractLinkedList.Node node = this.header.next;
/* 221 */     for (int currentIndex = 0; currentIndex < numberOfNodesToCache; currentIndex++) {
/* 222 */       AbstractLinkedList.Node oldNode = node;
/* 223 */       node = node.next;
/* 224 */       addNodeToCache(oldNode);
/*     */     } 
/* 226 */     super.removeAllNodes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 234 */     out.defaultWriteObject();
/* 235 */     doWriteObject(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 242 */     in.defaultReadObject();
/* 243 */     doReadObject(in);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/NodeCachingLinkedList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */