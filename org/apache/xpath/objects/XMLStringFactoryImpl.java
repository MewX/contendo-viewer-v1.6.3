/*    */ package org.apache.xpath.objects;
/*    */ 
/*    */ import org.apache.xml.utils.FastStringBuffer;
/*    */ import org.apache.xml.utils.XMLString;
/*    */ import org.apache.xml.utils.XMLStringFactory;
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
/*    */ public class XMLStringFactoryImpl
/*    */   extends XMLStringFactory
/*    */ {
/* 32 */   private static XMLStringFactory m_xstringfactory = new XMLStringFactoryImpl();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static XMLStringFactory getFactory() {
/* 43 */     return m_xstringfactory;
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
/*    */   public XMLString newstr(String string) {
/* 56 */     return new XString(string);
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
/*    */ 
/*    */   
/*    */   public XMLString newstr(FastStringBuffer fsb, int start, int length) {
/* 71 */     return new XStringForFSB(fsb, start, length);
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
/*    */ 
/*    */   
/*    */   public XMLString newstr(char[] string, int start, int length) {
/* 86 */     return new XStringForChars(string, start, length);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public XMLString emptystr() {
/* 96 */     return XString.EMPTYSTRING;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XMLStringFactoryImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */