/*    */ package org.apache.xml.utils;
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
/*    */ public class XMLStringFactoryDefault
/*    */   extends XMLStringFactory
/*    */ {
/* 28 */   private static final XMLStringDefault EMPTY_STR = new XMLStringDefault("");
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
/* 40 */     return new XMLStringDefault(string);
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
/* 55 */     return new XMLStringDefault(fsb.getString(start, length));
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
/* 70 */     return new XMLStringDefault(new String(string, start, length));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public XMLString emptystr() {
/* 80 */     return EMPTY_STR;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/XMLStringFactoryDefault.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */