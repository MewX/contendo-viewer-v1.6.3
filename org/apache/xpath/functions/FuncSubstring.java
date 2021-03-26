/*     */ package org.apache.xpath.functions;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.objects.XString;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FuncSubstring
/*     */   extends Function3Args
/*     */ {
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*     */     XMLString xMLString1;
/*     */     int i;
/*  46 */     XMLString s1 = this.m_arg0.execute(xctxt).xstr();
/*  47 */     double start = this.m_arg1.execute(xctxt).num();
/*  48 */     int lenOfS1 = s1.length();
/*     */ 
/*     */     
/*  51 */     if (lenOfS1 <= 0) {
/*  52 */       return (XObject)XString.EMPTYSTRING;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  57 */     if (Double.isNaN(start)) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  62 */       start = -1000000.0D;
/*  63 */       i = 0;
/*     */     }
/*     */     else {
/*     */       
/*  67 */       start = Math.round(start);
/*  68 */       i = (start > 0.0D) ? ((int)start - 1) : 0;
/*     */     } 
/*     */     
/*  71 */     if (null != this.m_arg2) {
/*     */       
/*  73 */       double len = this.m_arg2.num(xctxt);
/*  74 */       int end = (int)(Math.round(len) + start) - 1;
/*     */ 
/*     */       
/*  77 */       if (end < 0) {
/*  78 */         end = 0;
/*  79 */       } else if (end > lenOfS1) {
/*  80 */         end = lenOfS1;
/*     */       } 
/*  82 */       if (i > lenOfS1) {
/*  83 */         i = lenOfS1;
/*     */       }
/*  85 */       xMLString1 = s1.substring(i, end);
/*     */     }
/*     */     else {
/*     */       
/*  89 */       if (i > lenOfS1)
/*  90 */         i = lenOfS1; 
/*  91 */       xMLString1 = s1.substring(i);
/*     */     } 
/*     */ 
/*     */     
/*  95 */     return (XObject)xMLString1;
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
/*     */   public void checkNumberArgs(int argNum) throws WrongNumberArgsException {
/* 108 */     if (argNum < 2) {
/* 109 */       reportWrongNumberArgs();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reportWrongNumberArgs() throws WrongNumberArgsException {
/* 119 */     throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("ER_TWO_OR_THREE", null));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncSubstring.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */