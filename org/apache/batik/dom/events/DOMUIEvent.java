/*     */ package org.apache.batik.dom.events;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.xml.XMLUtilities;
/*     */ import org.w3c.dom.events.UIEvent;
/*     */ import org.w3c.dom.views.AbstractView;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DOMUIEvent
/*     */   extends AbstractEvent
/*     */   implements UIEvent
/*     */ {
/*     */   private AbstractView view;
/*     */   private int detail;
/*     */   
/*     */   public AbstractView getView() {
/*  46 */     return this.view;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDetail() {
/*  54 */     return this.detail;
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
/*     */   public void initUIEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int detailArg) {
/*  79 */     initEvent(typeArg, canBubbleArg, cancelableArg);
/*  80 */     this.view = viewArg;
/*  81 */     this.detail = detailArg;
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
/*     */   public void initUIEventNS(String namespaceURIArg, String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int detailArg) {
/*  93 */     initEventNS(namespaceURIArg, typeArg, canBubbleArg, cancelableArg);
/*  94 */     this.view = viewArg;
/*  95 */     this.detail = detailArg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String[] split(String s) {
/* 102 */     List<String> a = new ArrayList(8);
/*     */     
/* 104 */     int i = 0;
/* 105 */     int len = s.length();
/* 106 */     while (i < len) {
/* 107 */       char c = s.charAt(i++);
/* 108 */       if (XMLUtilities.isXMLSpace(c)) {
/*     */         continue;
/*     */       }
/* 111 */       StringBuffer sb = new StringBuffer();
/* 112 */       sb.append(c);
/* 113 */       while (i < len) {
/* 114 */         c = s.charAt(i++);
/* 115 */         if (XMLUtilities.isXMLSpace(c)) {
/* 116 */           a.add(sb.toString());
/*     */           break;
/*     */         } 
/* 119 */         sb.append(c);
/*     */       } 
/* 121 */       if (i == len) {
/* 122 */         a.add(sb.toString());
/*     */       }
/*     */     } 
/* 125 */     return a.<String>toArray(new String[a.size()]);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/DOMUIEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */