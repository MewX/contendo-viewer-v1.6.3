/*     */ package javax.json;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class JsonValueImpl
/*     */   implements Serializable, JsonValue
/*     */ {
/*     */   private final JsonValue.ValueType a;
/*     */   
/*     */   JsonValueImpl(JsonValue.ValueType valueType) {
/*  56 */     this.a = valueType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonValue.ValueType getValueType() {
/*  66 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  81 */     if (this == obj) {
/*  82 */       return true;
/*     */     }
/*  84 */     if (obj instanceof JsonValue) {
/*  85 */       return getValueType().equals(((JsonValue)obj).getValueType());
/*     */     }
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  99 */     return this.a.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 104 */     return this.a.name().toLowerCase();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/JsonValueImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */