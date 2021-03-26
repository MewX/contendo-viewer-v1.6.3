/*     */ package org.apache.pdfbox.util.filetypedetector;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ByteTrie<T>
/*     */ {
/*     */   static class ByteTrieNode<T>
/*     */   {
/*  42 */     private final Map<Byte, ByteTrieNode<T>> children = new HashMap<Byte, ByteTrieNode<T>>();
/*  43 */     private T value = null;
/*     */ 
/*     */     
/*     */     public void setValue(T value) {
/*  47 */       if (this.value != null)
/*     */       {
/*  49 */         throw new IllegalStateException("Value already set for this trie node");
/*     */       }
/*  51 */       this.value = value;
/*     */     }
/*     */ 
/*     */     
/*     */     public T getValue() {
/*  56 */       return this.value;
/*     */     }
/*     */   }
/*     */   
/*  60 */   private final ByteTrieNode<T> root = new ByteTrieNode<T>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int maxDepth;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T find(byte[] bytes) {
/*  72 */     ByteTrieNode<T> node = this.root;
/*  73 */     T val = node.getValue();
/*  74 */     for (byte b : bytes) {
/*     */       
/*  76 */       ByteTrieNode<T> child = (ByteTrieNode<T>)node.children.get(Byte.valueOf(b));
/*  77 */       if (child == null) {
/*     */         break;
/*     */       }
/*     */       
/*  81 */       node = child;
/*  82 */       if (node.getValue() != null)
/*     */       {
/*  84 */         val = node.getValue();
/*     */       }
/*     */     } 
/*  87 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPath(T value, byte[]... parts) {
/*  97 */     int depth = 0;
/*  98 */     ByteTrieNode<T> node = this.root;
/*  99 */     for (byte[] part : parts) {
/*     */       
/* 101 */       for (byte b : part) {
/*     */         
/* 103 */         ByteTrieNode<T> child = (ByteTrieNode<T>)node.children.get(Byte.valueOf(b));
/* 104 */         if (child == null) {
/*     */           
/* 106 */           child = new ByteTrieNode<T>();
/* 107 */           node.children.put(Byte.valueOf(b), child);
/*     */         } 
/* 109 */         node = child;
/* 110 */         depth++;
/*     */       } 
/*     */     } 
/* 113 */     node.setValue(value);
/* 114 */     this.maxDepth = Math.max(this.maxDepth, depth);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultValue(T defaultValue) {
/* 123 */     this.root.setValue(defaultValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxDepth() {
/* 132 */     return this.maxDepth;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/util/filetypedetector/ByteTrie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */