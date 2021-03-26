/*     */ package org.apache.xmlgraphics.ps.dsc.events;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractDSCComment
/*     */   extends AbstractEvent
/*     */   implements DSCComment
/*     */ {
/*     */   private boolean isWhitespace(char c) {
/*  30 */     return (c == ' ' || c == '\t');
/*     */   }
/*     */   
/*     */   private int parseNextParam(String value, int pos, List<String> lst) {
/*  34 */     int startPos = pos;
/*  35 */     pos++;
/*  36 */     while (pos < value.length() && !isWhitespace(value.charAt(pos))) {
/*  37 */       pos++;
/*     */     }
/*  39 */     String param = value.substring(startPos, pos);
/*  40 */     lst.add(param);
/*  41 */     return pos;
/*     */   }
/*     */   
/*     */   private int parseNextParentheseString(String value, int pos, List<String> lst) {
/*  45 */     int nestLevel = 1;
/*  46 */     pos++;
/*  47 */     StringBuffer sb = new StringBuffer();
/*  48 */     while (pos < value.length() && nestLevel > 0) {
/*  49 */       char cnext; int code; char c = value.charAt(pos);
/*  50 */       switch (c) {
/*     */         case '(':
/*  52 */           nestLevel++;
/*  53 */           if (nestLevel > 1) {
/*  54 */             sb.append(c);
/*     */           }
/*     */           break;
/*     */         case ')':
/*  58 */           if (nestLevel > 1) {
/*  59 */             sb.append(c);
/*     */           }
/*  61 */           nestLevel--;
/*     */           break;
/*     */         case '\\':
/*  64 */           pos++;
/*  65 */           cnext = value.charAt(pos);
/*  66 */           switch (cnext) {
/*     */             case '\\':
/*  68 */               sb.append(cnext);
/*     */               break;
/*     */             case 'n':
/*  71 */               sb.append('\n');
/*     */               break;
/*     */             case 'r':
/*  74 */               sb.append('\r');
/*     */               break;
/*     */             case 't':
/*  77 */               sb.append('\t');
/*     */               break;
/*     */             case 'b':
/*  80 */               sb.append('\b');
/*     */               break;
/*     */             case 'f':
/*  83 */               sb.append('\f');
/*     */               break;
/*     */             case '(':
/*  86 */               sb.append('(');
/*     */               break;
/*     */             case ')':
/*  89 */               sb.append(')');
/*     */               break;
/*     */           } 
/*  92 */           code = Integer.parseInt(value.substring(pos, pos + 3), 8);
/*  93 */           sb.append((char)code);
/*  94 */           pos += 2;
/*     */           break;
/*     */         
/*     */         default:
/*  98 */           sb.append(c); break;
/*     */       } 
/* 100 */       pos++;
/*     */     } 
/* 102 */     lst.add(sb.toString());
/* 103 */     pos++;
/* 104 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List splitParams(String value) {
/* 113 */     List lst = new ArrayList();
/* 114 */     int pos = 0;
/* 115 */     value = value.trim();
/* 116 */     while (pos < value.length()) {
/* 117 */       if (isWhitespace(value.charAt(pos))) {
/* 118 */         pos++;
/*     */         continue;
/*     */       } 
/* 121 */       if (value.charAt(pos) == '(') {
/* 122 */         pos = parseNextParentheseString(value, pos, lst); continue;
/*     */       } 
/* 124 */       pos = parseNextParam(value, pos, lst);
/*     */     } 
/*     */     
/* 127 */     return lst;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAtend() {
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DSCComment asDSCComment() {
/* 141 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDSCComment() {
/* 148 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEventType() {
/* 155 */     return 1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/events/AbstractDSCComment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */