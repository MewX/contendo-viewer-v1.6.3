/*    */ package org.apache.fontbox.cff;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.fontbox.encoding.Encoding;
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
/*    */ public abstract class CFFEncoding
/*    */   extends Encoding
/*    */ {
/* 32 */   private final Map<Integer, String> codeToName = new HashMap<Integer, String>(250);
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
/*    */   public String getName(int code) {
/* 50 */     String name = this.codeToName.get(Integer.valueOf(code));
/* 51 */     if (name == null)
/*    */     {
/* 53 */       return ".notdef";
/*    */     }
/* 55 */     return name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void add(int code, int sid, String name) {
/* 65 */     this.codeToName.put(Integer.valueOf(code), name);
/* 66 */     addCharacterEncoding(code, name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void add(int code, int sid) {
/* 74 */     String name = CFFStandardString.getName(sid);
/* 75 */     this.codeToName.put(Integer.valueOf(code), name);
/* 76 */     addCharacterEncoding(code, name);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CFFEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */