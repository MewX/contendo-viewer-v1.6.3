/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xpath.XPathContext;
/*    */ import org.apache.xpath.objects.XObject;
/*    */ import org.apache.xpath.objects.XString;
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
/*    */ 
/*    */ public class FuncTranslate
/*    */   extends Function3Args
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 43 */     String theFirstString = this.m_arg0.execute(xctxt).str();
/* 44 */     String theSecondString = this.m_arg1.execute(xctxt).str();
/* 45 */     String theThirdString = this.m_arg2.execute(xctxt).str();
/* 46 */     int theFirstStringLength = theFirstString.length();
/* 47 */     int theThirdStringLength = theThirdString.length();
/*    */ 
/*    */ 
/*    */     
/* 51 */     StringBuffer sbuffer = new StringBuffer();
/*    */     
/* 53 */     for (int i = 0; i < theFirstStringLength; i++) {
/*    */       
/* 55 */       char theCurrentChar = theFirstString.charAt(i);
/* 56 */       int theIndex = theSecondString.indexOf(theCurrentChar);
/*    */       
/* 58 */       if (theIndex < 0) {
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 63 */         sbuffer.append(theCurrentChar);
/*    */       }
/* 65 */       else if (theIndex < theThirdStringLength) {
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 70 */         sbuffer.append(theThirdString.charAt(theIndex));
/*    */       } 
/*    */     } 
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
/* 83 */     return (XObject)new XString(sbuffer.toString());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncTranslate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */