/*     */ package jp.cssj.a;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.xerces.parsers.NonValidatingConfiguration;
/*     */ import org.apache.xerces.parsers.SAXParser;
/*     */ import org.apache.xerces.xni.parser.XMLParserConfiguration;
/*     */ import org.cyberneko.html.d;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */ {
/*     */   public static final int a = 1;
/*     */   public static final int b = 2;
/*     */   public static final int c = 4;
/*     */   public static final int d = 8;
/*     */   public static final int e = 16;
/*     */   public static final int f = 32;
/*     */   public static final int g = 0;
/*     */   public static final int h = 1;
/*     */   public static final int i = 2;
/*     */   public static final int j = 3;
/*     */   public static final int k = 4;
/*     */   public static final int l = 5;
/*     */   public static final int m = 6;
/*     */   public static final int n = 7;
/*     */   public static final int o = 8;
/*     */   public static final int p = 9;
/*     */   
/*     */   public static class a
/*     */   {
/*     */     public final short a;
/*     */     public final int b;
/* 117 */     public final short[][] c = new short[10][];
/*     */     
/*     */     public a(short code, int flags) {
/* 120 */       this.a = code;
/* 121 */       this.b = flags;
/*     */     }
/*     */     
/*     */     public boolean a(int flag) {
/* 125 */       return ((this.b & flag) != 0);
/*     */     }
/*     */     
/*     */     public boolean b(int set) {
/* 129 */       return (this.c[set] != null);
/*     */     }
/*     */     
/*     */     public boolean a(int set, short code) {
/* 133 */       short[] tags = this.c[set];
/* 134 */       for (int i = 0; i < tags.length; i++) {
/* 135 */         if (tags[i] == code) {
/* 136 */           return true;
/*     */         }
/*     */       } 
/* 139 */       return false;
/*     */     }
/*     */   }
/*     */   
/* 143 */   private static final Map<String, c> q = new HashMap<>(); private final a r;
/*     */   
/*     */   public static final c a(String name) {
/*     */     try {
/* 147 */       c config = q.get(name);
/* 148 */       if (config == null) {
/* 149 */         try (InputStream in = c.class.getResourceAsStream(name)) {
/* 150 */           config = new c(new InputSource(in));
/*     */         } 
/* 152 */         q.put(name, config);
/*     */       } 
/* 154 */       return config;
/* 155 */     } catch (RuntimeException e) {
/* 156 */       throw e;
/* 157 */     } catch (Exception e) {
/* 158 */       e.printStackTrace();
/* 159 */       throw new RuntimeException();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 164 */   private final a[] s = new a[150];
/*     */   
/*     */   private c(InputSource is) throws IOException, SAXException {
/* 167 */     SAXParser parser = new SAXParser((XMLParserConfiguration)new NonValidatingConfiguration());
/* 168 */     parser.setContentHandler(new DefaultHandler(this) {
/*     */           c.a a;
/* 170 */           StringBuffer b = null;
/*     */           String c;
/* 172 */           Map<String, List<String>> d = new HashMap<>();
/*     */           
/*     */           public void startElement(String uri, String qName, String lName, Attributes atts) throws SAXException {
/* 175 */             if (qName.equals("tagset")) {
/* 176 */               this.c = atts.getValue("name");
/* 177 */             } else if (qName.equals("tag")) {
/* 178 */               d.a e = d.a(atts.getValue("name"));
/* 179 */               int flags = 0;
/* 180 */               String flagStr = atts.getValue("flags");
/* 181 */               if (flagStr != null) {
/* 182 */                 for (StringTokenizer st = new StringTokenizer(flagStr, "|"); st.hasMoreTokens(); ) {
/* 183 */                   String flag = st.nextToken().trim();
/* 184 */                   if (flag.length() == 0) {
/*     */                     continue;
/*     */                   }
/* 187 */                   if (flag.equals("BODY")) {
/* 188 */                     flags |= 0x1; continue;
/* 189 */                   }  if (flag.equals("HEAD")) {
/* 190 */                     flags |= 0x2; continue;
/* 191 */                   }  if (flag.equals("EMPTY")) {
/* 192 */                     flags |= 0x4; continue;
/* 193 */                   }  if (flag.equals("END_TO_EMPTY")) {
/* 194 */                     flags |= 0x8; continue;
/* 195 */                   }  if (flag.equals("IGNORE_TEXT")) {
/* 196 */                     flags |= 0x10; continue;
/* 197 */                   }  if (flag.equals("CLOSE_BY_TEXT")) {
/* 198 */                     flags |= 0x20; continue;
/*     */                   } 
/* 200 */                   throw new SAXException("Unexpected flag: " + flag);
/*     */                 } 
/*     */               }
/*     */               
/* 204 */               this.a = new c.a(e.f, flags);
/* 205 */               c.a(this.e)[e.f] = this.a;
/*     */             } 
/* 207 */             this.b = null;
/*     */           }
/*     */           
/*     */           public void characters(char[] ch, int off, int len) throws SAXException {
/* 211 */             if (this.b == null) {
/* 212 */               this.b = new StringBuffer();
/*     */             }
/* 214 */             this.b.append(ch, off, len);
/*     */           }
/*     */           
/*     */           public void endElement(String uri, String qName, String lName) throws SAXException {
/* 218 */             if (this.b == null) {
/*     */               return;
/*     */             }
/* 221 */             List<String> list = new ArrayList<>();
/* 222 */             for (StringTokenizer st = new StringTokenizer(this.b.toString(), ","); st.hasMoreTokens(); ) {
/* 223 */               String str = st.nextToken().trim();
/* 224 */               if (str.length() == 0) {
/*     */                 continue;
/*     */               }
/* 227 */               if (str.startsWith("-")) {
/*     */                 
/* 229 */                 str = str.substring(1);
/* 230 */                 list.remove(str); continue;
/* 231 */               }  if (str.startsWith("$")) {
/*     */                 
/* 233 */                 str = str.substring(1);
/* 234 */                 List<String> tagset = this.d.get(str);
/* 235 */                 if (tagset != null) {
/* 236 */                   list.addAll(tagset);
/*     */                 }
/*     */                 continue;
/*     */               } 
/* 240 */               list.add(str);
/*     */             } 
/*     */             
/* 243 */             this.b = null;
/* 244 */             if (!list.isEmpty()) {
/* 245 */               short[] codes = new short[list.size()];
/* 246 */               for (int i = 0; i < list.size(); i++) {
/* 247 */                 d.a close = d.a(list.get(i));
/* 248 */                 codes[i] = close.f;
/*     */               } 
/* 250 */               if (qName.equals("tagset")) {
/* 251 */                 this.d.put(this.c, list);
/* 252 */               } else if (qName.equals("alternates")) {
/* 253 */                 this.a.c[0] = codes;
/* 254 */               } else if (qName.equals("digsFor")) {
/* 255 */                 this.a.c[1] = codes;
/* 256 */               } else if (qName.equals("insertParents")) {
/* 257 */                 this.a.c[2] = codes;
/* 258 */               } else if (qName.equals("openCloses")) {
/* 259 */                 this.a.c[3] = codes;
/* 260 */               } else if (qName.equals("closeCloses")) {
/* 261 */                 this.a.c[4] = codes;
/* 262 */               } else if (qName.equals("discardsOpen")) {
/* 263 */                 this.a.c[5] = codes;
/* 264 */               } else if (qName.equals("discardsClose")) {
/* 265 */                 this.a.c[6] = codes;
/* 266 */               } else if (qName.equals("openSplits")) {
/* 267 */                 this.a.c[7] = codes;
/* 268 */               } else if (qName.equals("stopCloseBy")) {
/* 269 */                 this.a.c[8] = codes;
/* 270 */               } else if (qName.equals("insertByText")) {
/* 271 */                 this.a.c[9] = codes;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/* 276 */     parser.parse(is);
/* 277 */     this.r = this.s[117];
/*     */   }
/*     */   
/*     */   public a a(short code) {
/* 281 */     if (code >= this.s.length) {
/* 282 */       return this.r;
/*     */     }
/* 284 */     a element = this.s[code];
/* 285 */     if (element == null) {
/* 286 */       return this.r;
/*     */     }
/* 288 */     return element;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */