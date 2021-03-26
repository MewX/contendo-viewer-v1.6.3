/*     */ package org.apache.xalan.trace;
/*     */ 
/*     */ import java.util.EventListener;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.utils.QName;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TracerEvent
/*     */   implements EventListener
/*     */ {
/*     */   public final ElemTemplateElement m_styleNode;
/*     */   public final TransformerImpl m_processor;
/*     */   public final Node m_sourceNode;
/*     */   public final QName m_mode;
/*     */   
/*     */   public TracerEvent(TransformerImpl processor, Node sourceNode, QName mode, ElemTemplateElement styleNode) {
/*  71 */     this.m_processor = processor;
/*  72 */     this.m_sourceNode = sourceNode;
/*  73 */     this.m_mode = mode;
/*  74 */     this.m_styleNode = styleNode;
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
/*     */   public static String printNode(Node n) {
/*  90 */     String r = n.hashCode() + " ";
/*     */     
/*  92 */     if (n instanceof org.w3c.dom.Element) {
/*     */       
/*  94 */       r = r + "<" + n.getNodeName();
/*     */       
/*  96 */       Node c = n.getFirstChild();
/*     */       
/*  98 */       while (null != c) {
/*     */         
/* 100 */         if (c instanceof org.w3c.dom.Attr)
/*     */         {
/* 102 */           r = r + printNode(c) + " ";
/*     */         }
/*     */         
/* 105 */         c = c.getNextSibling();
/*     */       } 
/*     */       
/* 108 */       r = r + ">";
/*     */ 
/*     */     
/*     */     }
/* 112 */     else if (n instanceof org.w3c.dom.Attr) {
/*     */       
/* 114 */       r = r + n.getNodeName() + "=" + n.getNodeValue();
/*     */     }
/*     */     else {
/*     */       
/* 118 */       r = r + n.getNodeName();
/*     */     } 
/*     */ 
/*     */     
/* 122 */     return r;
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
/*     */   public static String printNodeList(NodeList l) {
/* 139 */     String r = l.hashCode() + "[";
/* 140 */     int len = l.getLength() - 1;
/* 141 */     int i = 0;
/*     */     
/* 143 */     while (i < len) {
/*     */       
/* 145 */       Node n = l.item(i);
/*     */       
/* 147 */       if (null != n)
/*     */       {
/* 149 */         r = r + printNode(n) + ", ";
/*     */       }
/*     */       
/* 152 */       i++;
/*     */     } 
/*     */     
/* 155 */     if (i == len) {
/*     */       
/* 157 */       Node n = l.item(len);
/*     */       
/* 159 */       if (null != n)
/*     */       {
/* 161 */         r = r + printNode(n);
/*     */       }
/*     */     } 
/*     */     
/* 165 */     return r + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/trace/TracerEvent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */