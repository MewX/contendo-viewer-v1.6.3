/*     */ package org.apache.batik.util.gui.xmleditor;
/*     */ 
/*     */ import org.apache.batik.xml.XMLUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLScanner
/*     */ {
/*     */   public static final int TEMP_ERROR_CONTEXT = -2;
/*     */   public static final int EOF_CONTEXT = -1;
/*     */   public static final int DEFAULT_CONTEXT = 0;
/*     */   public static final int COMMENT_CONTEXT = 1;
/*     */   public static final int ELEMENT_CONTEXT = 2;
/*     */   public static final int CHARACTER_DATA_CONTEXT = 3;
/*     */   public static final int ATTRIBUTE_NAME_CONTEXT = 4;
/*     */   public static final int ATTRIBUTE_VALUE_CONTEXT = 5;
/*     */   public static final int XML_DECLARATION_CONTEXT = 6;
/*     */   public static final int DOCTYPE_CONTEXT = 7;
/*     */   public static final int ENTITY_CONTEXT = 8;
/*     */   public static final int ELEMENT_DECLARATION_CONTEXT = 9;
/*     */   public static final int CDATA_CONTEXT = 10;
/*     */   public static final int PI_CONTEXT = 11;
/*     */   private int position;
/*     */   private String string;
/*     */   private int current;
/*     */   private int scanValue;
/*     */   private int startOffset;
/*     */   
/*     */   public XMLScanner() {
/*  54 */     reset();
/*     */   }
/*     */   
/*     */   public void reset() {
/*  58 */     this.position = 0;
/*  59 */     this.startOffset = 0;
/*     */   }
/*     */   
/*     */   public void setString(String string) {
/*  63 */     this.string = string;
/*     */   }
/*     */   
/*     */   protected int nextChar() {
/*     */     try {
/*  68 */       this.current = this.string.charAt(this.position);
/*  69 */       this.position++;
/*  70 */     } catch (Exception ex) {
/*  71 */       this.current = -1;
/*     */     } 
/*     */     
/*  74 */     return this.current;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int skipSpaces() {
/*     */     do {
/*  80 */       nextChar();
/*  81 */     } while (this.current != -1 && XMLUtilities.isXMLSpace((char)this.current));
/*  82 */     return this.current;
/*     */   }
/*     */   
/*     */   public int getScanValue() {
/*  86 */     return this.scanValue;
/*     */   }
/*     */   
/*     */   public int getStartOffset() {
/*  90 */     return this.startOffset;
/*     */   }
/*     */   
/*     */   public int scan(int context) {
/*  94 */     nextChar();
/*  95 */     switch (context)
/*     */     { case 6:
/*  97 */         this.scanValue = scanXMLDeclaration();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 121 */         return this.position;case 7: this.scanValue = scanDOCTYPE(); return this.position;case 1: this.scanValue = scanComment(); return this.position;case 2: this.scanValue = scanElement(); return this.position;case 4: this.scanValue = scanAttributeName(); return this.position;case 5: this.scanValue = scanAttributeValue(); return this.position;case 10: this.scanValue = scanCDATA(); return this.position; }  this.scanValue = scanCharacterData(); return this.position;
/*     */   }
/*     */   
/*     */   private int scanCharacterData() {
/* 125 */     while (this.current != -1) {
/* 126 */       if (this.current == 60) {
/* 127 */         nextChar();
/* 128 */         if (this.current == 63) {
/* 129 */           this.position -= 2;
/* 130 */           return 6;
/* 131 */         }  if (this.current == 33) {
/* 132 */           nextChar();
/* 133 */           if (this.current == 68) {
/* 134 */             this.position -= 3;
/* 135 */             return 7;
/* 136 */           }  if (this.current == 45) {
/* 137 */             nextChar();
/* 138 */             if (this.current == 45) {
/* 139 */               this.position -= 4;
/* 140 */               return 1;
/*     */             } 
/* 142 */           } else if (this.current == 91 && 
/* 143 */             nextChar() == 67 && 
/* 144 */             nextChar() == 68 && 
/* 145 */             nextChar() == 65 && 
/* 146 */             nextChar() == 84 && 
/* 147 */             nextChar() == 65 && 
/* 148 */             nextChar() == 91) {
/* 149 */             this.position -= 9;
/* 150 */             return 10;
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 159 */           this.position -= 2;
/* 160 */           return 2;
/*     */         } 
/*     */       } 
/* 163 */       nextChar();
/*     */     } 
/*     */     
/* 166 */     if (this.current == -1) {
/* 167 */       return -1;
/*     */     }
/*     */     
/* 170 */     return 3;
/*     */   }
/*     */   
/*     */   private int scanXMLDeclaration() {
/* 174 */     this.position += 2;
/* 175 */     while (this.current != -1) {
/* 176 */       if (this.current == 63) {
/* 177 */         if (nextChar() == 62) {
/* 178 */           return 3;
/*     */         }
/* 180 */         return -2;
/*     */       } 
/*     */       
/* 183 */       nextChar();
/*     */     } 
/*     */     
/* 186 */     if (this.current == -1) {
/* 187 */       return -1;
/*     */     }
/*     */     
/* 190 */     return 6;
/*     */   }
/*     */   
/*     */   private int scanDOCTYPE() {
/* 194 */     this.position += 3;
/* 195 */     boolean end = true;
/* 196 */     while (this.current != -1) {
/* 197 */       if (this.current == 91) {
/* 198 */         end = false;
/* 199 */       } else if (this.current == 93) {
/* 200 */         end = true;
/* 201 */       } else if (this.current == 62 && end) {
/* 202 */         return 3;
/*     */       } 
/* 204 */       nextChar();
/*     */     } 
/*     */     
/* 207 */     if (this.current == -1) {
/* 208 */       return -1;
/*     */     }
/*     */     
/* 211 */     return 7;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int scanComment() {
/* 217 */     while (this.current != -1) {
/* 218 */       if (this.current == 45 && 
/* 219 */         nextChar() == 45 && 
/* 220 */         nextChar() == 62) {
/* 221 */         return 3;
/*     */       }
/*     */ 
/*     */       
/* 225 */       nextChar();
/*     */     } 
/*     */     
/* 228 */     if (this.current == -1) {
/* 229 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 233 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int scanElement() {
/* 241 */     while (this.current != -1) {
/* 242 */       if (this.current == 62)
/* 243 */         return 3; 
/* 244 */       if (XMLUtilities.isXMLSpace((char)this.current)) {
/* 245 */         return 4;
/*     */       }
/*     */       
/* 248 */       nextChar();
/*     */     } 
/*     */     
/* 251 */     if (this.current == -1) {
/* 252 */       return -1;
/*     */     }
/*     */     
/* 255 */     return 2;
/*     */   }
/*     */   
/*     */   private int scanAttributeName() {
/* 259 */     while (this.current != -1) {
/* 260 */       if (this.current == 61)
/* 261 */         return 5; 
/* 262 */       if (this.current == 47) {
/* 263 */         this.position--;
/* 264 */         return 2;
/* 265 */       }  if (this.current == 62) {
/* 266 */         this.position--;
/* 267 */         return 2;
/*     */       } 
/* 269 */       nextChar();
/*     */     } 
/*     */     
/* 272 */     if (this.current == -1) {
/* 273 */       return -1;
/*     */     }
/*     */     
/* 276 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   private int scanAttributeValue() {
/* 281 */     int delim = 34;
/*     */ 
/*     */     
/* 284 */     while (this.current != -1) {
/* 285 */       if (this.current == 34 || this.current == 39) {
/* 286 */         delim = this.current;
/*     */         break;
/*     */       } 
/* 289 */       nextChar();
/*     */     } 
/*     */     
/* 292 */     nextChar();
/*     */ 
/*     */     
/* 295 */     while (this.current != -1) {
/* 296 */       if (this.current == delim) {
/* 297 */         return 2;
/*     */       }
/* 299 */       nextChar();
/*     */     } 
/*     */     
/* 302 */     if (this.current == -1) {
/* 303 */       return -1;
/*     */     }
/*     */     
/* 306 */     return 5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int scanCDATA() {
/* 312 */     while (this.current != -1) {
/* 313 */       if (this.current == 93 && 
/* 314 */         nextChar() == 93 && 
/* 315 */         nextChar() == 62) {
/* 316 */         return 3;
/*     */       }
/*     */ 
/*     */       
/* 320 */       nextChar();
/*     */     } 
/*     */     
/* 323 */     if (this.current == -1) {
/* 324 */       return -1;
/*     */     }
/*     */     
/* 327 */     return 10;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/xmleditor/XMLScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */