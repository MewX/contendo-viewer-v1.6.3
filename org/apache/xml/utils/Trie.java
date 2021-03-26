/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Trie
/*     */ {
/*     */   public static final int ALPHA_SIZE = 128;
/*     */   Node m_Root;
/*  38 */   private char[] m_charBuffer = new char[0];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Trie() {
/*  45 */     this.m_Root = new Node(this);
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
/*     */   public Object put(String key, Object value) {
/*  59 */     int len = key.length();
/*  60 */     if (len > this.m_charBuffer.length)
/*     */     {
/*     */       
/*  63 */       this.m_charBuffer = new char[len];
/*     */     }
/*     */     
/*  66 */     Node node = this.m_Root;
/*     */     
/*  68 */     for (int i = 0; i < len; i++) {
/*     */       
/*  70 */       Node nextNode = node.m_nextChar[Character.toUpperCase(key.charAt(i))];
/*     */       
/*  72 */       if (nextNode != null) {
/*     */         
/*  74 */         node = nextNode;
/*     */       }
/*     */       else {
/*     */         
/*  78 */         for (; i < len; i++) {
/*     */           
/*  80 */           Node newNode = new Node(this);
/*     */           
/*  82 */           node.m_nextChar[Character.toUpperCase(key.charAt(i))] = newNode;
/*  83 */           node.m_nextChar[Character.toLowerCase(key.charAt(i))] = newNode;
/*  84 */           node = newNode;
/*     */         } 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  90 */     Object ret = node.m_Value;
/*     */     
/*  92 */     node.m_Value = value;
/*     */     
/*  94 */     return ret;
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
/*     */   public Object get(String key) {
/*     */     char ch;
/* 107 */     int len = key.length();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     if (this.m_charBuffer.length < len) {
/* 113 */       return null;
/*     */     }
/* 115 */     Node node = this.m_Root;
/* 116 */     switch (len) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 0:
/* 123 */         return null;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 128 */         ch = key.charAt(0);
/* 129 */         if (ch < '') {
/*     */           
/* 131 */           node = node.m_nextChar[ch];
/* 132 */           if (node != null)
/* 133 */             return node.m_Value; 
/*     */         } 
/* 135 */         return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     key.getChars(0, len, this.m_charBuffer, 0);
/*     */     
/* 162 */     for (int i = 0; i < len; i++) {
/*     */       
/* 164 */       char c = this.m_charBuffer[i];
/* 165 */       if ('' <= c)
/*     */       {
/*     */         
/* 168 */         return null;
/*     */       }
/*     */       
/* 171 */       node = node.m_nextChar[c];
/* 172 */       if (node == null) {
/* 173 */         return null;
/*     */       }
/*     */     } 
/* 176 */     return node.m_Value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class Node
/*     */   {
/*     */     Node[] m_nextChar;
/*     */ 
/*     */     
/*     */     Object m_Value;
/*     */     
/*     */     private final Trie this$0;
/*     */ 
/*     */     
/*     */     Node(Trie this$0) {
/* 192 */       this.this$0 = this$0;
/* 193 */       this.m_nextChar = new Node[128];
/* 194 */       this.m_Value = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/Trie.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */