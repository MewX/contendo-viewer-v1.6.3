/*     */ package org.a.a;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.math.BigDecimal;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Spliterator;
/*     */ import java.util.Spliterators;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Stream;
/*     */ import java.util.stream.StreamSupport;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonArrayBuilder;
/*     */ import javax.json.JsonException;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonObjectBuilder;
/*     */ import javax.json.JsonValue;
/*     */ import javax.json.stream.JsonLocation;
/*     */ import javax.json.stream.JsonParser;
/*     */ import javax.json.stream.JsonParsingException;
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
/*     */ public class l
/*     */   implements JsonParser
/*     */ {
/*     */   private final a a;
/*  82 */   private b b = new c();
/*     */   
/*     */   private JsonParser.Event c;
/*  85 */   private final e d = new e();
/*     */   private final v e;
/*     */   
/*     */   public l(Reader reader, a bufferPool) {
/*  89 */     this.a = bufferPool;
/*  90 */     this.e = new v(reader, bufferPool);
/*     */   }
/*     */   
/*     */   public l(InputStream in, a bufferPool) {
/*  94 */     this.a = bufferPool;
/*  95 */     B uin = new B(in);
/*  96 */     this.e = new v(new InputStreamReader(uin, uin.a()), bufferPool);
/*     */   }
/*     */   
/*     */   public l(InputStream in, Charset encoding, a bufferPool) {
/* 100 */     this.a = bufferPool;
/* 101 */     this.e = new v(new InputStreamReader(in, encoding), bufferPool);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString() {
/* 106 */     if (this.c == JsonParser.Event.KEY_NAME || this.c == JsonParser.Event.VALUE_STRING || this.c == JsonParser.Event.VALUE_NUMBER)
/*     */     {
/* 108 */       return this.e.e();
/*     */     }
/* 110 */     throw new IllegalStateException(
/* 111 */         h.a(this.c));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIntegralNumber() {
/* 116 */     if (this.c != JsonParser.Event.VALUE_NUMBER) {
/* 117 */       throw new IllegalStateException(
/* 118 */           h.b(this.c));
/*     */     }
/* 120 */     return this.e.k();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt() {
/* 125 */     if (this.c != JsonParser.Event.VALUE_NUMBER) {
/* 126 */       throw new IllegalStateException(
/* 127 */           h.c(this.c));
/*     */     }
/* 129 */     return this.e.g();
/*     */   }
/*     */   
/*     */   boolean a() {
/* 133 */     return this.e.i();
/*     */   }
/*     */   
/*     */   boolean b() {
/* 137 */     return this.e.j();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong() {
/* 142 */     if (this.c != JsonParser.Event.VALUE_NUMBER) {
/* 143 */       throw new IllegalStateException(
/* 144 */           h.d(this.c));
/*     */     }
/* 146 */     return this.e.h();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getBigDecimal() {
/* 151 */     if (this.c != JsonParser.Event.VALUE_NUMBER) {
/* 152 */       throw new IllegalStateException(
/* 153 */           h.e(this.c));
/*     */     }
/* 155 */     return this.e.f();
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonArray getArray() {
/* 160 */     if (this.c != JsonParser.Event.START_ARRAY) {
/* 161 */       throw new IllegalStateException(
/* 162 */           h.f(this.c));
/*     */     }
/* 164 */     return a(new b(this.a));
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonObject getObject() {
/* 169 */     if (this.c != JsonParser.Event.START_OBJECT) {
/* 170 */       throw new IllegalStateException(
/* 171 */           h.g(this.c));
/*     */     }
/* 173 */     return a(new j(this.a));
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonValue getValue() {
/* 178 */     switch (null.a[this.c.ordinal()]) {
/*     */       case 1:
/* 180 */         return (JsonValue)a(new b(this.a));
/*     */       case 2:
/* 182 */         return (JsonValue)a(new j(this.a));
/*     */       case 3:
/*     */       case 4:
/* 185 */         return (JsonValue)new t(getString());
/*     */       case 5:
/* 187 */         if (a())
/* 188 */           return (JsonValue)i.a(getInt()); 
/* 189 */         if (b()) {
/* 190 */           return (JsonValue)i.a(getLong());
/*     */         }
/* 192 */         return (JsonValue)i.a(getBigDecimal());
/*     */       case 6:
/* 194 */         return JsonValue.TRUE;
/*     */       case 7:
/* 196 */         return JsonValue.FALSE;
/*     */       case 8:
/* 198 */         return JsonValue.NULL;
/*     */     } 
/*     */ 
/*     */     
/* 202 */     throw new IllegalStateException(h.h(this.c));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Stream<JsonValue> getArrayStream() {
/* 208 */     if (this.c != JsonParser.Event.START_ARRAY) {
/* 209 */       throw new IllegalStateException(
/* 210 */           h.f(this.c));
/*     */     }
/* 212 */     Spliterator<JsonValue> spliterator = new Spliterators.AbstractSpliterator<JsonValue>(this, Long.MAX_VALUE, 16)
/*     */       {
/*     */         public Spliterator<JsonValue> trySplit()
/*     */         {
/* 216 */           return null;
/*     */         }
/*     */         
/*     */         public boolean tryAdvance(Consumer<? super JsonValue> action) {
/* 220 */           if (action == null) {
/* 221 */             throw new NullPointerException();
/*     */           }
/* 223 */           if (!this.a.hasNext()) {
/* 224 */             return false;
/*     */           }
/* 226 */           if (this.a.next() == JsonParser.Event.END_ARRAY) {
/* 227 */             return false;
/*     */           }
/* 229 */           action.accept(this.a.getValue());
/* 230 */           return true;
/*     */         }
/*     */       };
/* 233 */     return StreamSupport.stream(spliterator, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public Stream<Map.Entry<String, JsonValue>> getObjectStream() {
/* 238 */     if (this.c != JsonParser.Event.START_OBJECT) {
/* 239 */       throw new IllegalStateException(
/* 240 */           h.g(this.c));
/*     */     }
/* 242 */     Spliterator<Map.Entry<String, JsonValue>> spliterator = new Spliterators.AbstractSpliterator<Map.Entry<String, JsonValue>>(this, Long.MAX_VALUE, 16)
/*     */       {
/*     */         public Spliterator<Map.Entry<String, JsonValue>> trySplit()
/*     */         {
/* 246 */           return null;
/*     */         }
/*     */         
/*     */         public boolean tryAdvance(Consumer<? super Map.Entry<String, JsonValue>> action) {
/* 250 */           if (action == null) {
/* 251 */             throw new NullPointerException();
/*     */           }
/* 253 */           if (!this.a.hasNext()) {
/* 254 */             return false;
/*     */           }
/* 256 */           JsonParser.Event e = this.a.next();
/* 257 */           if (e == JsonParser.Event.END_OBJECT) {
/* 258 */             return false;
/*     */           }
/* 260 */           if (e != JsonParser.Event.KEY_NAME) {
/* 261 */             throw new JsonException(h.a());
/*     */           }
/* 263 */           String key = this.a.getString();
/* 264 */           if (!this.a.hasNext()) {
/* 265 */             throw new JsonException(h.a());
/*     */           }
/* 267 */           this.a.next();
/* 268 */           JsonValue value = this.a.getValue();
/* 269 */           action.accept(new AbstractMap.SimpleImmutableEntry<>(key, value));
/* 270 */           return true;
/*     */         }
/*     */       };
/* 273 */     return StreamSupport.stream(spliterator, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public Stream<JsonValue> getValueStream() {
/* 278 */     if (!(this.b instanceof c)) {
/* 279 */       throw new IllegalStateException(
/* 280 */           h.c());
/*     */     }
/* 282 */     Spliterator<JsonValue> spliterator = new Spliterators.AbstractSpliterator<JsonValue>(this, Long.MAX_VALUE, 16)
/*     */       {
/*     */         public Spliterator<JsonValue> trySplit()
/*     */         {
/* 286 */           return null;
/*     */         }
/*     */         
/*     */         public boolean tryAdvance(Consumer<? super JsonValue> action) {
/* 290 */           if (action == null) {
/* 291 */             throw new NullPointerException();
/*     */           }
/* 293 */           if (!this.a.hasNext()) {
/* 294 */             return false;
/*     */           }
/* 296 */           this.a.next();
/* 297 */           action.accept(this.a.getValue());
/* 298 */           return true;
/*     */         }
/*     */       };
/* 301 */     return StreamSupport.stream(spliterator, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void skipArray() {
/* 306 */     if (this.c == JsonParser.Event.START_ARRAY) {
/* 307 */       this.b.b();
/* 308 */       this.b = e.a(this.d);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void skipObject() {
/* 314 */     if (this.c == JsonParser.Event.START_OBJECT) {
/* 315 */       this.b.b();
/* 316 */       this.b = e.a(this.d);
/*     */     } 
/*     */   }
/*     */   
/*     */   private JsonArray a(JsonArrayBuilder builder) {
/* 321 */     while (hasNext()) {
/* 322 */       JsonParser.Event event = next();
/* 323 */       if (event == JsonParser.Event.END_ARRAY) {
/* 324 */         return builder.build();
/*     */       }
/* 326 */       builder.add(getValue());
/*     */     } 
/* 328 */     throw a(v.a.l, "[CURLYOPEN, SQUAREOPEN, STRING, NUMBER, TRUE, FALSE, NULL, SQUARECLOSE]");
/*     */   }
/*     */   
/*     */   private JsonObject a(JsonObjectBuilder builder) {
/* 332 */     while (hasNext()) {
/* 333 */       JsonParser.Event event = next();
/* 334 */       if (event == JsonParser.Event.END_OBJECT) {
/* 335 */         return builder.build();
/*     */       }
/* 337 */       String key = getString();
/* 338 */       next();
/* 339 */       builder.add(key, getValue());
/*     */     } 
/* 341 */     throw a(v.a.l, "[STRING, CURLYCLOSE]");
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonLocation getLocation() {
/* 346 */     return this.e.d();
/*     */   }
/*     */   
/*     */   public JsonLocation c() {
/* 350 */     return this.e.c();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/* 355 */     return this.e.b();
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonParser.Event next() {
/* 360 */     if (!hasNext()) {
/* 361 */       throw new NoSuchElementException();
/*     */     }
/* 363 */     return this.c = this.b.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/* 369 */       this.e.close();
/* 370 */     } catch (IOException iOException) {
/* 371 */       throw new JsonException(h.d(), iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final class e {
/*     */     private l.b a;
/*     */     
/*     */     private e() {}
/*     */     
/*     */     private void a(l.b context) {
/* 381 */       context.b = this.a;
/* 382 */       this.a = context;
/*     */     }
/*     */     
/*     */     private l.b a() {
/* 386 */       if (this.a == null) {
/* 387 */         throw new NoSuchElementException();
/*     */       }
/* 389 */       l.b temp = this.a;
/* 390 */       this.a = this.a.b;
/* 391 */       return temp;
/*     */     }
/*     */     
/*     */     private l.b b() {
/* 395 */       return this.a;
/*     */     }
/*     */     
/*     */     private boolean c() {
/* 399 */       return (this.a == null);
/*     */     }
/*     */   }
/*     */   
/*     */   private abstract class b { b b;
/*     */     
/*     */     private b(l this$0) {}
/*     */     
/*     */     abstract JsonParser.Event a();
/*     */     
/*     */     abstract void b(); }
/*     */   
/*     */   private final class c extends b {
/*     */     public JsonParser.Event a() {
/* 413 */       v.a token = l.a(this.a).a();
/* 414 */       if (token == v.a.a) {
/* 415 */         l.e.a(l.c(this.a), l.b(this.a));
/* 416 */         l.a(this.a, new l.d());
/* 417 */         return JsonParser.Event.START_OBJECT;
/* 418 */       }  if (token == v.a.b) {
/* 419 */         l.e.a(l.c(this.a), l.b(this.a));
/* 420 */         l.a(this.a, new l.a());
/* 421 */         return JsonParser.Event.START_ARRAY;
/* 422 */       }  if (token.c()) {
/* 423 */         return token.b();
/*     */       }
/* 425 */       throw l.a(this.a, token, "[CURLYOPEN, SQUAREOPEN, STRING, NUMBER, TRUE, FALSE, NULL]");
/*     */     }
/*     */ 
/*     */     
/*     */     private c(l this$0) {}
/*     */     
/*     */     void b() {}
/*     */   }
/*     */   
/*     */   private JsonParsingException a(v.a token, String expectedTokens) {
/* 435 */     JsonLocation location = c();
/* 436 */     return new JsonParsingException(
/* 437 */         h.a(token, location, expectedTokens), location);
/*     */   }
/*     */   
/*     */   private final class d extends b { private d(l this$0) {
/* 441 */       this.d = true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean d;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonParser.Event a() {
/* 453 */       v.a token = l.a(this.a).a();
/* 454 */       if (l.d(this.a) == JsonParser.Event.KEY_NAME) {
/*     */         
/* 456 */         if (token != v.a.c) {
/* 457 */           throw l.a(this.a, token, "[COLON]");
/*     */         }
/* 459 */         token = l.a(this.a).a();
/* 460 */         if (token.c())
/* 461 */           return token.b(); 
/* 462 */         if (token == v.a.a) {
/* 463 */           l.e.a(l.c(this.a), l.b(this.a));
/* 464 */           l.a(this.a, new d());
/* 465 */           return JsonParser.Event.START_OBJECT;
/* 466 */         }  if (token == v.a.b) {
/* 467 */           l.e.a(l.c(this.a), l.b(this.a));
/* 468 */           l.a(this.a, new l.a());
/* 469 */           return JsonParser.Event.START_ARRAY;
/*     */         } 
/* 471 */         throw l.a(this.a, token, "[CURLYOPEN, SQUAREOPEN, STRING, NUMBER, TRUE, FALSE, NULL]");
/*     */       } 
/*     */       
/* 474 */       if (token == v.a.j) {
/* 475 */         l.a(this.a, l.e.a(l.c(this.a)));
/* 476 */         return JsonParser.Event.END_OBJECT;
/*     */       } 
/* 478 */       if (this.d) {
/* 479 */         this.d = false;
/*     */       } else {
/* 481 */         if (token != v.a.d) {
/* 482 */           throw l.a(this.a, token, "[COMMA]");
/*     */         }
/* 484 */         token = l.a(this.a).a();
/*     */       } 
/* 486 */       if (token == v.a.e) {
/* 487 */         return JsonParser.Event.KEY_NAME;
/*     */       }
/* 489 */       throw l.a(this.a, token, "[STRING]");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     void b() {
/*     */       v.a token;
/* 496 */       int depth = 1;
/*     */       do {
/* 498 */         token = l.a(this.a).a();
/* 499 */         switch (l.null.b[token.ordinal()]) {
/*     */           case 1:
/* 501 */             depth--;
/*     */             break;
/*     */           case 2:
/* 504 */             depth++;
/*     */             break;
/*     */         } 
/* 507 */       } while (token != v.a.j || depth != 0);
/*     */     } }
/*     */   
/*     */   private final class a extends b { private boolean d;
/*     */     
/*     */     private a(l this$0) {
/* 513 */       this.d = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonParser.Event a() {
/* 518 */       v.a token = l.a(this.a).a();
/* 519 */       if (token == v.a.k) {
/* 520 */         l.a(this.a, l.e.a(l.c(this.a)));
/* 521 */         return JsonParser.Event.END_ARRAY;
/*     */       } 
/* 523 */       if (this.d) {
/* 524 */         this.d = false;
/*     */       } else {
/* 526 */         if (token != v.a.d) {
/* 527 */           throw l.a(this.a, token, "[COMMA]");
/*     */         }
/* 529 */         token = l.a(this.a).a();
/*     */       } 
/* 531 */       if (token.c())
/* 532 */         return token.b(); 
/* 533 */       if (token == v.a.a) {
/* 534 */         l.e.a(l.c(this.a), l.b(this.a));
/* 535 */         l.a(this.a, new l.d());
/* 536 */         return JsonParser.Event.START_OBJECT;
/* 537 */       }  if (token == v.a.b) {
/* 538 */         l.e.a(l.c(this.a), l.b(this.a));
/* 539 */         l.a(this.a, new a());
/* 540 */         return JsonParser.Event.START_ARRAY;
/*     */       } 
/* 542 */       throw l.a(this.a, token, "[CURLYOPEN, SQUAREOPEN, STRING, NUMBER, TRUE, FALSE, NULL]");
/*     */     }
/*     */ 
/*     */     
/*     */     void b() {
/*     */       v.a token;
/* 548 */       int depth = 1;
/*     */       do {
/* 550 */         token = l.a(this.a).a();
/* 551 */         switch (l.null.b[token.ordinal()]) {
/*     */           case 3:
/* 553 */             depth--;
/*     */             break;
/*     */           case 4:
/* 556 */             depth++;
/*     */             break;
/*     */         } 
/* 559 */       } while (token != v.a.k || depth != 0);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */