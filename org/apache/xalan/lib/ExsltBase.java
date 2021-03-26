/*    */ package org.apache.xalan.lib;
/*    */ 
/*    */ import org.apache.xml.dtm.ref.DTMNodeProxy;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.NodeList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ExsltBase
/*    */ {
/*    */   protected static String toString(Node n) {
/* 40 */     if (n instanceof DTMNodeProxy) {
/* 41 */       return ((DTMNodeProxy)n).getStringValue();
/*    */     }
/*    */     
/* 44 */     String value = n.getNodeValue();
/* 45 */     if (value == null) {
/*    */       
/* 47 */       NodeList nodelist = n.getChildNodes();
/* 48 */       StringBuffer buf = new StringBuffer();
/* 49 */       for (int i = 0; i < nodelist.getLength(); i++) {
/*    */         
/* 51 */         Node childNode = nodelist.item(i);
/* 52 */         buf.append(toString(childNode));
/*    */       } 
/* 54 */       return buf.toString();
/*    */     } 
/*    */     
/* 57 */     return value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected static double toNumber(Node n) {
/* 70 */     double d = 0.0D;
/* 71 */     String str = toString(n);
/*    */ 
/*    */     
/* 74 */     try { d = Double.valueOf(str).doubleValue(); } catch (NumberFormatException e)
/*    */     
/*    */     { 
/*    */       
/* 78 */       d = Double.NaN; }
/*    */     
/* 80 */     return d;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/ExsltBase.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */