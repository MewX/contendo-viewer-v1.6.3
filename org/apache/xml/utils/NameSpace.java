/*    */ package org.apache.xml.utils;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class NameSpace
/*    */   implements Serializable
/*    */ {
/* 34 */   public NameSpace m_next = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String m_prefix;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String m_uri;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NameSpace(String prefix, String uri) {
/* 53 */     this.m_prefix = prefix;
/* 54 */     this.m_uri = uri;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/NameSpace.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */