/*    */ package org.apache.fontbox.encoding;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public abstract class Encoding
/*    */ {
/* 33 */   protected Map<Integer, String> codeToName = new HashMap<Integer, String>(250);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   protected Map<String, Integer> nameToCode = new HashMap<String, Integer>(250);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void addCharacterEncoding(int code, String name) {
/* 48 */     this.codeToName.put(Integer.valueOf(code), name);
/* 49 */     this.nameToCode.put(name, Integer.valueOf(code));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer getCode(String name) {
/* 60 */     return this.nameToCode.get(name);
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
/*    */   public String getName(int code) {
/* 72 */     String name = this.codeToName.get(Integer.valueOf(code));
/* 73 */     if (name != null)
/*    */     {
/* 75 */       return name;
/*    */     }
/* 77 */     return ".notdef";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, String> getCodeToNameMap() {
/* 87 */     return Collections.unmodifiableMap(this.codeToName);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/encoding/Encoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */