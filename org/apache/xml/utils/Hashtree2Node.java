/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Hashtree2Node
/*     */ {
/*     */   public static void appendHashToNode(Hashtable hash, String name, Node container, Document factory) {
/*  67 */     if (null == container || null == factory || null == hash) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  73 */     String elemName = null;
/*  74 */     if (null == name || "".equals(name)) {
/*  75 */       elemName = "appendHashToNode";
/*     */     } else {
/*  77 */       elemName = name;
/*     */     } 
/*     */     
/*     */     try {
/*  81 */       Element hashNode = factory.createElement(elemName);
/*  82 */       container.appendChild(hashNode);
/*     */       
/*  84 */       Enumeration keys = hash.keys();
/*  85 */       Vector v = new Vector();
/*     */       
/*  87 */       while (keys.hasMoreElements()) {
/*     */         
/*  89 */         Object key = keys.nextElement();
/*  90 */         String keyStr = key.toString();
/*  91 */         Object item = hash.get(key);
/*     */         
/*  93 */         if (item instanceof Hashtable) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  98 */           v.addElement(keyStr);
/*  99 */           v.addElement(item);
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/* 106 */         try { Element node = factory.createElement("item");
/* 107 */           node.setAttribute("key", keyStr);
/* 108 */           node.appendChild(factory.createTextNode((String)item));
/* 109 */           hashNode.appendChild(node); } catch (Exception e)
/*     */         
/*     */         { 
/*     */           
/* 113 */           Element node = factory.createElement("item");
/* 114 */           node.setAttribute("key", keyStr);
/* 115 */           node.appendChild(factory.createTextNode("ERROR: Reading " + key + " threw: " + e.toString()));
/* 116 */           hashNode.appendChild(node); }
/*     */       
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 122 */       keys = v.elements();
/* 123 */       while (keys.hasMoreElements())
/*     */       {
/*     */         
/* 126 */         String n = keys.nextElement();
/* 127 */         Hashtable h = (Hashtable)keys.nextElement();
/*     */         
/* 129 */         appendHashToNode(h, n, hashNode, factory);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     catch (Exception e2) {
/*     */       
/* 136 */       e2.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/Hashtree2Node.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */