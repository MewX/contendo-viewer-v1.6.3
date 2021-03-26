/*    */ package org.a.a;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import javax.json.JsonArray;
/*    */ import javax.json.JsonArrayBuilder;
/*    */ import javax.json.JsonBuilderFactory;
/*    */ import javax.json.JsonObject;
/*    */ import javax.json.JsonObjectBuilder;
/*    */ import org.a.a.a.a;
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
/*    */ class c
/*    */   implements JsonBuilderFactory
/*    */ {
/*    */   private final Map<String, ?> a;
/*    */   private final a b;
/*    */   
/*    */   c(a bufferPool) {
/* 62 */     this.a = Collections.emptyMap();
/* 63 */     this.b = bufferPool;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonObjectBuilder createObjectBuilder() {
/* 68 */     return new j(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonObjectBuilder createObjectBuilder(JsonObject object) {
/* 73 */     return new j(object, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonObjectBuilder createObjectBuilder(Map<String, Object> object) {
/* 78 */     return new j(object, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonArrayBuilder createArrayBuilder() {
/* 83 */     return new b(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonArrayBuilder createArrayBuilder(JsonArray array) {
/* 88 */     return new b(array, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonArrayBuilder createArrayBuilder(Collection<?> collection) {
/* 93 */     return new b(collection, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, ?> getConfigInUse() {
/* 98 */     return this.a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */