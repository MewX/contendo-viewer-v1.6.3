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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NSInfo
/*    */ {
/*    */   public String m_namespace;
/*    */   public boolean m_hasXMLNSAttrs;
/*    */   public boolean m_hasProcessedNS;
/*    */   public int m_ancestorHasXMLNSAttrs;
/*    */   public static final int ANCESTORXMLNSUNPROCESSED = 0;
/*    */   public static final int ANCESTORHASXMLNS = 1;
/*    */   public static final int ANCESTORNOXMLNS = 2;
/*    */   
/*    */   public NSInfo(boolean hasProcessedNS, boolean hasXMLNSAttrs) {
/* 42 */     this.m_hasProcessedNS = hasProcessedNS;
/* 43 */     this.m_hasXMLNSAttrs = hasXMLNSAttrs;
/* 44 */     this.m_namespace = null;
/* 45 */     this.m_ancestorHasXMLNSAttrs = 0;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NSInfo(boolean hasProcessedNS, boolean hasXMLNSAttrs, int ancestorHasXMLNSAttrs) {
/* 65 */     this.m_hasProcessedNS = hasProcessedNS;
/* 66 */     this.m_hasXMLNSAttrs = hasXMLNSAttrs;
/* 67 */     this.m_ancestorHasXMLNSAttrs = ancestorHasXMLNSAttrs;
/* 68 */     this.m_namespace = null;
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
/*    */   public NSInfo(String namespace, boolean hasXMLNSAttrs) {
/* 82 */     this.m_hasProcessedNS = true;
/* 83 */     this.m_hasXMLNSAttrs = hasXMLNSAttrs;
/* 84 */     this.m_namespace = namespace;
/* 85 */     this.m_ancestorHasXMLNSAttrs = 0;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/NSInfo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */