/*     */ package org.apache.batik.dom.events;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ import org.w3c.dom.events.MouseEvent;
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
/*     */ public class DOMMouseEvent
/*     */   extends DOMUIEvent
/*     */   implements MouseEvent
/*     */ {
/*     */   private int screenX;
/*     */   private int screenY;
/*     */   private int clientX;
/*     */   private int clientY;
/*     */   private short button;
/*     */   private EventTarget relatedTarget;
/*  47 */   protected HashSet modifierKeys = new HashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getScreenX() {
/*  55 */     return this.screenX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getScreenY() {
/*  64 */     return this.screenY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClientX() {
/*  73 */     return this.clientX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClientY() {
/*  82 */     return this.clientY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCtrlKey() {
/*  90 */     return this.modifierKeys.contains("Control");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getShiftKey() {
/*  98 */     return this.modifierKeys.contains("Shift");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAltKey() {
/* 107 */     return this.modifierKeys.contains("Alt");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getMetaKey() {
/* 116 */     return this.modifierKeys.contains("Meta");
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
/*     */   public short getButton() {
/* 130 */     return this.button;
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
/*     */   public EventTarget getRelatedTarget() {
/* 142 */     return this.relatedTarget;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getModifierState(String keyIdentifierArg) {
/* 150 */     return this.modifierKeys.contains(keyIdentifierArg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getModifiersString() {
/* 157 */     if (this.modifierKeys.isEmpty()) {
/* 158 */       return "";
/*     */     }
/* 160 */     StringBuffer sb = new StringBuffer(this.modifierKeys.size() * 8);
/* 161 */     Iterator<String> i = this.modifierKeys.iterator();
/* 162 */     sb.append(i.next());
/* 163 */     while (i.hasNext()) {
/* 164 */       sb.append(' ');
/* 165 */       sb.append(i.next());
/*     */     } 
/* 167 */     return sb.toString();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initMouseEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int detailArg, int screenXArg, int screenYArg, int clientXArg, int clientYArg, boolean ctrlKeyArg, boolean altKeyArg, boolean shiftKeyArg, boolean metaKeyArg, short buttonArg, EventTarget relatedTargetArg) {
/* 218 */     initUIEvent(typeArg, canBubbleArg, cancelableArg, viewArg, detailArg);
/*     */     
/* 220 */     this.screenX = screenXArg;
/* 221 */     this.screenY = screenYArg;
/* 222 */     this.clientX = clientXArg;
/* 223 */     this.clientY = clientYArg;
/* 224 */     if (ctrlKeyArg) {
/* 225 */       this.modifierKeys.add("Control");
/*     */     }
/* 227 */     if (altKeyArg) {
/* 228 */       this.modifierKeys.add("Alt");
/*     */     }
/* 230 */     if (shiftKeyArg) {
/* 231 */       this.modifierKeys.add("Shift");
/*     */     }
/* 233 */     if (metaKeyArg) {
/* 234 */       this.modifierKeys.add("Meta");
/*     */     }
/* 236 */     this.button = buttonArg;
/* 237 */     this.relatedTarget = relatedTargetArg;
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
/*     */   public void initMouseEventNS(String namespaceURIArg, String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int detailArg, int screenXArg, int screenYArg, int clientXArg, int clientYArg, short buttonArg, EventTarget relatedTargetArg, String modifiersList) {
/* 256 */     initUIEventNS(namespaceURIArg, typeArg, canBubbleArg, cancelableArg, viewArg, detailArg);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 262 */     this.screenX = screenXArg;
/* 263 */     this.screenY = screenYArg;
/* 264 */     this.clientX = clientXArg;
/* 265 */     this.clientY = clientYArg;
/* 266 */     this.button = buttonArg;
/* 267 */     this.relatedTarget = relatedTargetArg;
/* 268 */     this.modifierKeys.clear();
/* 269 */     String[] modifiers = split(modifiersList);
/* 270 */     for (String modifier : modifiers)
/* 271 */       this.modifierKeys.add(modifier); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/DOMMouseEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */