/*     */ package org.apache.xmlgraphics.ps;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PSDictionary
/*     */   extends HashMap
/*     */ {
/*     */   private static final long serialVersionUID = 815367222496219197L;
/*     */   
/*     */   private static class Maker
/*     */   {
/*     */     private Maker() {}
/*     */     
/*     */     private static class Token
/*     */     {
/*     */       private Token() {}
/*     */       
/*  48 */       private int startIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  53 */       private int endIndex = -1;
/*     */ 
/*     */       
/*     */       private String value;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  61 */     private static final String[][] BRACES = new String[][] { { "<<", ">>" }, { "[", "]" }, { "{", "}" }, { "(", ")" } };
/*     */ 
/*     */ 
/*     */     
/*     */     private static final int OPENING = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     private static final int CLOSING = 1;
/*     */ 
/*     */     
/*     */     private static final int DICTIONARY = 0;
/*     */ 
/*     */     
/*     */     private static final int ARRAY = 1;
/*     */ 
/*     */     
/*     */     private static final int PROCEDURE = 2;
/*     */ 
/*     */     
/*     */     private static final int STRING = 3;
/*     */ 
/*     */ 
/*     */     
/*     */     protected Token nextToken(String str, int fromIndex) {
/*  86 */       Token t = null;
/*  87 */       for (int i = fromIndex; i < str.length(); i++) {
/*  88 */         boolean isWhitespace = Character.isWhitespace(str.charAt(i));
/*     */         
/*  90 */         if (t == null && !isWhitespace) {
/*  91 */           t = new Token();
/*  92 */           t.startIndex = i;
/*     */         }
/*  94 */         else if (t != null && isWhitespace) {
/*  95 */           t.endIndex = i;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 100 */       if (t != null) {
/*     */         
/* 102 */         if (t.endIndex == -1) {
/* 103 */           t.endIndex = str.length();
/*     */         }
/* 105 */         t.value = str.substring(t.startIndex, t.endIndex);
/*     */       } 
/* 107 */       return t;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int indexOfMatchingBrace(String str, String[] braces, int fromIndex) throws PSDictionaryFormatException {
/* 126 */       int len = str.length();
/* 127 */       if (braces.length != 2) {
/* 128 */         throw new PSDictionaryFormatException("Wrong number of braces");
/*     */       }
/* 130 */       for (int openCnt = 0, closeCnt = 0; fromIndex < len; fromIndex++) {
/* 131 */         if (str.startsWith(braces[0], fromIndex)) {
/* 132 */           openCnt++;
/* 133 */         } else if (str.startsWith(braces[1], fromIndex)) {
/* 134 */           closeCnt++;
/* 135 */           if (openCnt > 0 && openCnt == closeCnt) {
/* 136 */             return fromIndex;
/*     */           }
/*     */         } 
/*     */       } 
/* 140 */       return -1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String stripBraces(String str, String[] braces) throws PSDictionaryFormatException {
/* 156 */       int firstIndex = str.indexOf(braces[0]);
/* 157 */       if (firstIndex == -1) {
/* 158 */         throw new PSDictionaryFormatException("Failed to find opening parameter '" + braces[0] + "");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 164 */       int lastIndex = indexOfMatchingBrace(str, braces, firstIndex);
/* 165 */       if (lastIndex == -1) {
/* 166 */         throw new PSDictionaryFormatException("Failed to find matching closing parameter '" + braces[1] + "'");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 172 */       int braceLen = braces[0].length();
/* 173 */       str = str.substring(firstIndex + braceLen, lastIndex).trim();
/* 174 */       return str;
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
/*     */     public PSDictionary parseDictionary(String str) throws PSDictionaryFormatException {
/* 186 */       PSDictionary dictionary = new PSDictionary();
/* 187 */       str = stripBraces(str.trim(), BRACES[0]);
/*     */       
/* 189 */       int len = str.length();
/*     */ 
/*     */       
/* 192 */       int currIndex = 0; Token keyToken;
/* 193 */       while ((keyToken = nextToken(str, currIndex)) != null && currIndex <= len) {
/* 194 */         if (keyToken.value == null) {
/* 195 */           throw new PSDictionaryFormatException("Failed to parse object key");
/*     */         }
/* 197 */         Token valueToken = nextToken(str, keyToken.endIndex + 1);
/* 198 */         String[] braces = null;
/* 199 */         for (int i = 0; i < BRACES.length; i++) {
/* 200 */           if (valueToken.value.startsWith(BRACES[i][0])) {
/* 201 */             braces = BRACES[i];
/*     */             break;
/*     */           } 
/*     */         } 
/* 205 */         Object<String> obj = null;
/* 206 */         if (braces != null) {
/*     */           
/* 208 */           valueToken.endIndex = indexOfMatchingBrace(str, braces, valueToken.startIndex) + braces[0].length();
/*     */ 
/*     */           
/* 211 */           if (valueToken.endIndex < 0) {
/* 212 */             throw new PSDictionaryFormatException("Closing value brace '" + braces[1] + "' not found for key '" + keyToken.value + "'");
/*     */           }
/*     */ 
/*     */           
/* 216 */           valueToken.value = str.substring(valueToken.startIndex, valueToken.endIndex);
/*     */         } 
/* 218 */         if (braces == null || braces == BRACES[2] || braces == BRACES[3]) {
/* 219 */           obj = (Object<String>)valueToken.value;
/* 220 */         } else if (BRACES[1] == braces) {
/* 221 */           List<String> objList = new ArrayList();
/* 222 */           String objString = stripBraces(valueToken.value, braces);
/* 223 */           StringTokenizer tokenizer = new StringTokenizer(objString, ",");
/* 224 */           while (tokenizer.hasMoreTokens()) {
/* 225 */             objList.add(tokenizer.nextToken());
/*     */           }
/* 227 */           obj = (Object<String>)objList;
/* 228 */         } else if (BRACES[0] == braces) {
/* 229 */           obj = (Object<String>)parseDictionary(valueToken.value);
/*     */         } 
/* 231 */         dictionary.put((K)keyToken.value, (V)obj);
/* 232 */         currIndex = valueToken.endIndex + 1;
/*     */       } 
/* 234 */       return dictionary;
/*     */     }
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
/*     */   public static PSDictionary valueOf(String str) throws PSDictionaryFormatException {
/* 247 */     return (new Maker()).parseDictionary(str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 256 */     if (!(obj instanceof PSDictionary)) {
/* 257 */       return false;
/*     */     }
/* 259 */     PSDictionary dictionaryObj = (PSDictionary)obj;
/* 260 */     if (dictionaryObj.size() != size()) {
/* 261 */       return false;
/*     */     }
/* 263 */     for (Object<K, V> e : entrySet()) {
/* 264 */       Map.Entry entry = (Map.Entry)e;
/* 265 */       String key = (String)entry.getKey();
/* 266 */       if (!dictionaryObj.containsKey(key)) {
/* 267 */         return false;
/*     */       }
/* 269 */       if (!dictionaryObj.get(key).equals(entry.getValue())) {
/* 270 */         return false;
/*     */       }
/*     */     } 
/* 273 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 278 */     int hashCode = 7;
/* 279 */     for (Iterator<V> it = values().iterator(); it.hasNext(); ) {
/* 280 */       Object value = it.next();
/* 281 */       hashCode += value.hashCode();
/*     */     } 
/* 283 */     return hashCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 288 */     if (isEmpty()) {
/* 289 */       return "";
/*     */     }
/* 291 */     StringBuffer sb = new StringBuffer("<<\n");
/* 292 */     for (Iterator<K> it = keySet().iterator(); it.hasNext(); ) {
/* 293 */       String key = (String)it.next();
/* 294 */       sb.append("  " + key + " ");
/* 295 */       Object obj = get(key);
/* 296 */       if (obj instanceof ArrayList) {
/* 297 */         List array = (List)obj;
/* 298 */         StringBuilder str = new StringBuilder("[");
/* 299 */         for (int i = 0; i < array.size(); i++) {
/* 300 */           Object element = array.get(i);
/* 301 */           str.append(element + " ");
/*     */         } 
/* 303 */         String str2 = str.toString().trim();
/* 304 */         str2 = str2 + "]";
/* 305 */         sb.append(str2 + "\n"); continue;
/*     */       } 
/* 307 */       sb.append(obj.toString() + "\n");
/*     */     } 
/*     */     
/* 310 */     sb.append(">>");
/* 311 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/PSDictionary.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */