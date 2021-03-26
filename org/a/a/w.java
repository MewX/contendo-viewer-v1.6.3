/*    */ package org.a.a;
/*    */ 
/*    */ import java.io.StringReader;
/*    */ import javax.json.Json;
/*    */ import javax.json.JsonReader;
/*    */ import javax.json.JsonValue;
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
/*    */ public final class w
/*    */ {
/*    */   public static JsonValue a(String jsonString) {
/* 71 */     StringBuilder builder = new StringBuilder();
/* 72 */     boolean single_context = false;
/* 73 */     for (int i = 0; i < jsonString.length(); i++) {
/* 74 */       char ch = jsonString.charAt(i);
/* 75 */       if (ch == '\\') {
/* 76 */         i++;
/* 77 */         if (i < jsonString.length()) {
/* 78 */           ch = jsonString.charAt(i);
/* 79 */           if (!single_context || ch != '\'')
/*    */           {
/* 81 */             builder.append('\\');
/*    */           }
/*    */         } 
/* 84 */       } else if (ch == '\'') {
/*    */         
/* 86 */         ch = '"';
/* 87 */         single_context = !single_context;
/*    */       } 
/* 89 */       builder.append(ch);
/*    */     } 
/*    */     
/* 92 */     JsonReader reader = Json.createReader(new StringReader(builder
/* 93 */           .toString()));
/* 94 */     JsonValue value = reader.readValue();
/* 95 */     reader.close();
/* 96 */     return value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/w.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */