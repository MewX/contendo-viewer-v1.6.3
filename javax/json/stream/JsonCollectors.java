/*     */ package javax.json.stream;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BinaryOperator;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collector;
/*     */ import javax.json.Json;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonArrayBuilder;
/*     */ import javax.json.JsonException;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonObjectBuilder;
/*     */ import javax.json.JsonValue;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class JsonCollectors
/*     */ {
/*     */   public static Collector<JsonValue, JsonArrayBuilder, JsonArray> toJsonArray() {
/*  77 */     return Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::addAll, JsonArrayBuilder::build, new Collector.Characteristics[0]);
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
/*     */   public static Collector<Map.Entry<String, JsonValue>, JsonObjectBuilder, JsonObject> toJsonObject() {
/*  91 */     return Collector.of(Json::createObjectBuilder, (b, v) -> b.add((String)v.getKey(), (JsonValue)v.getValue()), JsonObjectBuilder::addAll, JsonObjectBuilder::build, new Collector.Characteristics[0]);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Collector<JsonValue, JsonObjectBuilder, JsonObject> toJsonObject(Function<JsonValue, String> keyMapper, Function<JsonValue, JsonValue> valueMapper) {
/* 110 */     return Collector.of(Json::createObjectBuilder, (b, v) -> b.add(keyMapper.apply(v), valueMapper.apply(v)), JsonObjectBuilder::addAll, JsonObjectBuilder::build, new Collector.Characteristics[] { Collector.Characteristics.UNORDERED });
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
/*     */   public static <T extends JsonArrayBuilder> Collector<JsonValue, Map<String, T>, JsonObject> groupingBy(Function<JsonValue, String> classifier, Collector<JsonValue, T, JsonArray> downstream) {
/* 136 */     BiConsumer<Map<String, T>, JsonValue> accumulator = (map, value) -> {
/*     */         String key = classifier.apply(value);
/*     */         
/*     */         if (key == null) {
/*     */           throw new JsonException("element cannot be mapped to a null key");
/*     */         }
/*     */         
/*     */         JsonArrayBuilder jsonArrayBuilder = map.computeIfAbsent(key, ());
/*     */         
/*     */         downstream.accumulator().accept(jsonArrayBuilder, value);
/*     */       };
/*     */     
/* 148 */     Function<Map<String, T>, JsonObject> finisher = map -> {
/*     */         JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
/*     */ 
/*     */ 
/*     */         
/*     */         map.forEach(());
/*     */ 
/*     */         
/*     */         return objectBuilder.build();
/*     */       };
/*     */ 
/*     */     
/* 160 */     BinaryOperator<Map<String, T>> combiner = (map1, map2) -> {
/*     */         map1.putAll(map2);
/*     */         
/*     */         return map1;
/*     */       };
/* 165 */     return Collector.of(java.util.HashMap::new, accumulator, combiner, finisher, new Collector.Characteristics[] { Collector.Characteristics.UNORDERED });
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
/*     */   
/*     */   public static Collector<JsonValue, Map<String, JsonArrayBuilder>, JsonObject> groupingBy(Function<JsonValue, String> classifier) {
/* 181 */     return groupingBy(classifier, toJsonArray());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/json/stream/JsonCollectors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */