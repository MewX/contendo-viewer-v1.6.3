/*     */ package org.a.a;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import javax.json.JsonArrayBuilder;
/*     */ import javax.json.JsonObjectBuilder;
/*     */ import javax.json.JsonValue;
/*     */ import org.a.a.a.a;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class z
/*     */ {
/*     */   static JsonValue a(Object value, a bufferPool) {
/*  66 */     if (value == null) {
/*  67 */       return JsonValue.NULL;
/*     */     }
/*     */     
/*  70 */     if (value instanceof BigDecimal) {
/*  71 */       return (JsonValue)i.a((BigDecimal)value);
/*     */     }
/*  73 */     if (value instanceof BigInteger) {
/*  74 */       return (JsonValue)i.a((BigInteger)value);
/*     */     }
/*  76 */     if (value instanceof Boolean) {
/*  77 */       Boolean b = (Boolean)value;
/*  78 */       return b.booleanValue() ? JsonValue.TRUE : JsonValue.FALSE;
/*     */     } 
/*  80 */     if (value instanceof Double) {
/*  81 */       return (JsonValue)i.a(((Double)value).doubleValue());
/*     */     }
/*  83 */     if (value instanceof Integer) {
/*  84 */       return (JsonValue)i.a(((Integer)value).intValue());
/*     */     }
/*  86 */     if (value instanceof Long) {
/*  87 */       return (JsonValue)i.a(((Long)value).longValue());
/*     */     }
/*  89 */     if (value instanceof String) {
/*  90 */       return (JsonValue)new t((String)value);
/*     */     }
/*  92 */     if (value instanceof Collection) {
/*     */       
/*  94 */       Collection<?> collection = (Collection)value;
/*  95 */       JsonArrayBuilder jsonArrayBuilder = new b(collection, bufferPool);
/*  96 */       return (JsonValue)jsonArrayBuilder.build();
/*     */     } 
/*  98 */     if (value instanceof Map) {
/*     */       
/* 100 */       JsonObjectBuilder object = new j((Map<String, Object>)value, bufferPool);
/* 101 */       return (JsonValue)object.build();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     throw new IllegalArgumentException(String.format("Type %s is not supported.", new Object[] { value.getClass() }));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/z.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */