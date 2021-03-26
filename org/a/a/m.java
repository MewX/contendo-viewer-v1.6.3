/*     */ package org.a.a;
/*     */ 
/*     */ import javax.json.Json;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonArrayBuilder;
/*     */ import javax.json.JsonPatch;
/*     */ import javax.json.JsonPatchBuilder;
/*     */ import javax.json.JsonStructure;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class m
/*     */   implements JsonPatchBuilder
/*     */ {
/*     */   private final JsonArrayBuilder a;
/*     */   
/*     */   public m(JsonArray patch) {
/*  83 */     this.a = Json.createArrayBuilder(patch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public m() {
/*  90 */     this.a = Json.createArrayBuilder();
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
/*     */   public <T extends JsonStructure> T a(T target) {
/* 104 */     return (T)build().apply((JsonStructure)target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder add(String path, JsonValue value) {
/* 115 */     this.a.add(Json.createObjectBuilder()
/* 116 */         .add("op", JsonPatch.Operation.ADD.operationName())
/* 117 */         .add("path", path)
/* 118 */         .add("value", value));
/*     */     
/* 120 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder add(String path, String value) {
/* 131 */     this.a.add(Json.createObjectBuilder()
/* 132 */         .add("op", JsonPatch.Operation.ADD.operationName())
/* 133 */         .add("path", path)
/* 134 */         .add("value", value));
/*     */     
/* 136 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder add(String path, int value) {
/* 147 */     this.a.add(Json.createObjectBuilder()
/* 148 */         .add("op", JsonPatch.Operation.ADD.operationName())
/* 149 */         .add("path", path)
/* 150 */         .add("value", value));
/*     */     
/* 152 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder add(String path, boolean value) {
/* 163 */     this.a.add(Json.createObjectBuilder()
/* 164 */         .add("op", JsonPatch.Operation.ADD.operationName())
/* 165 */         .add("path", path)
/* 166 */         .add("value", value));
/*     */     
/* 168 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder remove(String path) {
/* 178 */     this.a.add(Json.createObjectBuilder()
/* 179 */         .add("op", JsonPatch.Operation.REMOVE.operationName())
/* 180 */         .add("path", path));
/*     */     
/* 182 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder replace(String path, JsonValue value) {
/* 193 */     this.a.add(Json.createObjectBuilder()
/* 194 */         .add("op", JsonPatch.Operation.REPLACE.operationName())
/* 195 */         .add("path", path)
/* 196 */         .add("value", value));
/*     */     
/* 198 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder replace(String path, String value) {
/* 209 */     this.a.add(Json.createObjectBuilder()
/* 210 */         .add("op", JsonPatch.Operation.REPLACE.operationName())
/* 211 */         .add("path", path)
/* 212 */         .add("value", value));
/*     */     
/* 214 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder replace(String path, int value) {
/* 225 */     this.a.add(Json.createObjectBuilder()
/* 226 */         .add("op", JsonPatch.Operation.REPLACE.operationName())
/* 227 */         .add("path", path)
/* 228 */         .add("value", value));
/*     */     
/* 230 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder replace(String path, boolean value) {
/* 241 */     this.a.add(Json.createObjectBuilder()
/* 242 */         .add("op", JsonPatch.Operation.REPLACE.operationName())
/* 243 */         .add("path", path)
/* 244 */         .add("value", value));
/*     */     
/* 246 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder move(String path, String from) {
/* 257 */     this.a.add(Json.createObjectBuilder()
/* 258 */         .add("op", JsonPatch.Operation.MOVE.operationName())
/* 259 */         .add("path", path)
/* 260 */         .add("from", from));
/*     */     
/* 262 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder copy(String path, String from) {
/* 273 */     this.a.add(Json.createObjectBuilder()
/* 274 */         .add("op", JsonPatch.Operation.COPY.operationName())
/* 275 */         .add("path", path)
/* 276 */         .add("from", from));
/*     */     
/* 278 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder test(String path, JsonValue value) {
/* 289 */     this.a.add(Json.createObjectBuilder()
/* 290 */         .add("op", JsonPatch.Operation.TEST.operationName())
/* 291 */         .add("path", path)
/* 292 */         .add("value", value));
/*     */     
/* 294 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder test(String path, String value) {
/* 305 */     this.a.add(Json.createObjectBuilder()
/* 306 */         .add("op", JsonPatch.Operation.TEST.operationName())
/* 307 */         .add("path", path)
/* 308 */         .add("value", value));
/*     */     
/* 310 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder test(String path, int value) {
/* 321 */     this.a.add(Json.createObjectBuilder()
/* 322 */         .add("op", JsonPatch.Operation.TEST.operationName())
/* 323 */         .add("path", path)
/* 324 */         .add("value", value));
/*     */     
/* 326 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatchBuilder test(String path, boolean value) {
/* 337 */     this.a.add(Json.createObjectBuilder()
/* 338 */         .add("op", JsonPatch.Operation.TEST.operationName())
/* 339 */         .add("path", path)
/* 340 */         .add("value", value));
/*     */     
/* 342 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonArray a() {
/* 350 */     return this.a.build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonPatch build() {
/* 359 */     return new n(a());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/m.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */