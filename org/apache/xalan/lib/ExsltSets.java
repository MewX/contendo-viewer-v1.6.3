/*     */ package org.apache.xalan.lib;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import org.apache.xml.utils.DOMHelper;
/*     */ import org.apache.xpath.NodeSet;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExsltSets
/*     */   extends ExsltBase
/*     */ {
/*     */   public static NodeList leading(NodeList nl1, NodeList nl2) {
/*  59 */     if (nl2.getLength() == 0) {
/*  60 */       return nl1;
/*     */     }
/*  62 */     NodeSet ns1 = new NodeSet(nl1);
/*  63 */     NodeSet leadNodes = new NodeSet();
/*  64 */     Node endNode = nl2.item(0);
/*  65 */     if (!ns1.contains(endNode)) {
/*  66 */       return (NodeList)leadNodes;
/*     */     }
/*  68 */     for (int i = 0; i < nl1.getLength(); i++) {
/*     */       
/*  70 */       Node testNode = nl1.item(i);
/*  71 */       if (DOMHelper.isNodeAfter(testNode, endNode) && !DOMHelper.isNodeTheSame(testNode, endNode))
/*     */       {
/*  73 */         leadNodes.addElement(testNode); } 
/*     */     } 
/*  75 */     return (NodeList)leadNodes;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeList trailing(NodeList nl1, NodeList nl2) {
/*  94 */     if (nl2.getLength() == 0) {
/*  95 */       return nl1;
/*     */     }
/*  97 */     NodeSet ns1 = new NodeSet(nl1);
/*  98 */     NodeSet trailNodes = new NodeSet();
/*  99 */     Node startNode = nl2.item(0);
/* 100 */     if (!ns1.contains(startNode)) {
/* 101 */       return (NodeList)trailNodes;
/*     */     }
/* 103 */     for (int i = 0; i < nl1.getLength(); i++) {
/*     */       
/* 105 */       Node testNode = nl1.item(i);
/* 106 */       if (DOMHelper.isNodeAfter(startNode, testNode) && !DOMHelper.isNodeTheSame(startNode, testNode))
/*     */       {
/* 108 */         trailNodes.addElement(testNode); } 
/*     */     } 
/* 110 */     return (NodeList)trailNodes;
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
/*     */ 
/*     */   
/*     */   public static NodeList intersection(NodeList nl1, NodeList nl2) {
/* 126 */     NodeSet ns1 = new NodeSet(nl1);
/* 127 */     NodeSet ns2 = new NodeSet(nl2);
/* 128 */     NodeSet inter = new NodeSet();
/*     */     
/* 130 */     inter.setShouldCacheNodes(true);
/*     */     
/* 132 */     for (int i = 0; i < ns1.getLength(); i++) {
/*     */       
/* 134 */       Node n = ns1.elementAt(i);
/*     */       
/* 136 */       if (ns2.contains(n)) {
/* 137 */         inter.addElement(n);
/*     */       }
/*     */     } 
/* 140 */     return (NodeList)inter;
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
/*     */ 
/*     */   
/*     */   public static NodeList difference(NodeList nl1, NodeList nl2) {
/* 156 */     NodeSet ns1 = new NodeSet(nl1);
/* 157 */     NodeSet ns2 = new NodeSet(nl2);
/*     */     
/* 159 */     NodeSet diff = new NodeSet();
/*     */     
/* 161 */     diff.setShouldCacheNodes(true);
/*     */     
/* 163 */     for (int i = 0; i < ns1.getLength(); i++) {
/*     */       
/* 165 */       Node n = ns1.elementAt(i);
/*     */       
/* 167 */       if (!ns2.contains(n)) {
/* 168 */         diff.addElement(n);
/*     */       }
/*     */     } 
/* 171 */     return (NodeList)diff;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeList distinct(NodeList nl) {
/* 188 */     NodeSet dist = new NodeSet();
/* 189 */     dist.setShouldCacheNodes(true);
/*     */     
/* 191 */     Hashtable stringTable = new Hashtable();
/*     */     
/* 193 */     for (int i = 0; i < nl.getLength(); i++) {
/*     */       
/* 195 */       Node currNode = nl.item(i);
/* 196 */       String key = ExsltBase.toString(currNode);
/*     */       
/* 198 */       if (key == null) {
/* 199 */         dist.addElement(currNode);
/* 200 */       } else if (!stringTable.containsKey(key)) {
/*     */         
/* 202 */         stringTable.put(key, currNode);
/* 203 */         dist.addElement(currNode);
/*     */       } 
/*     */     } 
/*     */     
/* 207 */     return (NodeList)dist;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasSameNode(NodeList nl1, NodeList nl2) {
/* 226 */     NodeSet ns1 = new NodeSet(nl1);
/* 227 */     NodeSet ns2 = new NodeSet(nl2);
/*     */     
/* 229 */     for (int i = 0; i < ns1.getLength(); i++) {
/*     */       
/* 231 */       if (ns2.contains(ns1.elementAt(i)))
/* 232 */         return true; 
/*     */     } 
/* 234 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/ExsltSets.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */