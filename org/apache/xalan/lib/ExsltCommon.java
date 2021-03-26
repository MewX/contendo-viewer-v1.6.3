/*     */ package org.apache.xalan.lib;
/*     */ 
/*     */ import org.apache.xalan.extensions.ExpressionContext;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.dtm.ref.DTMNodeIterator;
/*     */ import org.apache.xpath.NodeSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExsltCommon
/*     */ {
/*     */   public static String objectType(Object obj) {
/*  61 */     if (obj instanceof String)
/*  62 */       return "string"; 
/*  63 */     if (obj instanceof Boolean)
/*  64 */       return "boolean"; 
/*  65 */     if (obj instanceof Number)
/*  66 */       return "number"; 
/*  67 */     if (obj instanceof DTMNodeIterator) {
/*     */       
/*  69 */       DTMIterator dtmI = ((DTMNodeIterator)obj).getDTMIterator();
/*  70 */       if (dtmI instanceof org.apache.xpath.axes.RTFIterator) {
/*  71 */         return "RTF";
/*     */       }
/*  73 */       return "node-set";
/*     */     } 
/*     */     
/*  76 */     return "unknown";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeSet nodeSet(ExpressionContext myProcessor, Object rtf) {
/* 101 */     return Extensions.nodeset(myProcessor, rtf);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/ExsltCommon.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */