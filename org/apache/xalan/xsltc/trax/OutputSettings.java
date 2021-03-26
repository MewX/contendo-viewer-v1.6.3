/*    */ package org.apache.xalan.xsltc.trax;
/*    */ 
/*    */ import java.util.Properties;
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
/*    */ public final class OutputSettings
/*    */ {
/* 29 */   private String _cdata_section_elements = null;
/* 30 */   private String _doctype_public = null;
/* 31 */   private String _encoding = null;
/* 32 */   private String _indent = null;
/* 33 */   private String _media_type = null;
/* 34 */   private String _method = null;
/* 35 */   private String _omit_xml_declaration = null;
/* 36 */   private String _standalone = null;
/* 37 */   private String _version = null;
/*    */   
/*    */   public Properties getProperties() {
/* 40 */     Properties properties = new Properties();
/* 41 */     return properties;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/OutputSettings.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */