/*    */ package org.apache.batik.transcoder.wmf.tosvg;
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
/*    */ public class GdiObject
/*    */ {
/*    */   int id;
/*    */   boolean used;
/*    */   Object obj;
/*    */   int type;
/*    */   
/*    */   GdiObject(int _id, boolean _used) {
/* 85 */     this.type = 0;
/*    */     this.id = _id;
/*    */     this.used = _used;
/*    */     this.type = 0;
/*    */   }
/*    */   
/*    */   public void clear() {
/*    */     this.used = false;
/*    */     this.type = 0;
/*    */   }
/*    */   
/*    */   public void Setup(int _type, Object _obj) {
/*    */     this.obj = _obj;
/*    */     this.type = _type;
/*    */     this.used = true;
/*    */   }
/*    */   
/*    */   public boolean isUsed() {
/*    */     return this.used;
/*    */   }
/*    */   
/*    */   public int getType() {
/*    */     return this.type;
/*    */   }
/*    */   
/*    */   public Object getObject() {
/*    */     return this.obj;
/*    */   }
/*    */   
/*    */   public int getID() {
/*    */     return this.id;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/GdiObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */