/*     */ package org.apache.batik.dom.events;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.apache.batik.dom.util.IntTable;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EventListenerList
/*     */ {
/*     */   protected int n;
/*     */   protected Entry head;
/*  50 */   protected IntTable counts = new IntTable();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Entry[] listeners;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected HashMap listenersNS = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addListener(String namespaceURI, Object group, EventListener listener) {
/*  68 */     for (Entry e = this.head; e != null; e = e.next) {
/*  69 */       if (((namespaceURI != null && namespaceURI.equals(e.namespaceURI)) || (namespaceURI == null && e.namespaceURI == null)) && e.listener == listener) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  76 */     this.head = new Entry(listener, namespaceURI, group, this.head);
/*  77 */     this.counts.inc(namespaceURI);
/*  78 */     this.n++;
/*  79 */     this.listeners = null;
/*  80 */     this.listenersNS.remove(namespaceURI);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeListener(String namespaceURI, EventListener listener) {
/*  88 */     if (this.head == null)
/*     */       return; 
/*  90 */     if (this.head != null && ((namespaceURI != null && namespaceURI.equals(this.head.namespaceURI)) || (namespaceURI == null && this.head.namespaceURI == null)) && listener == this.head.listener) {
/*     */ 
/*     */ 
/*     */       
/*  94 */       this.head = this.head.next;
/*     */     } else {
/*     */       
/*  97 */       Entry prev = this.head; Entry e;
/*  98 */       for (e = this.head.next; e != null; e = e.next) {
/*  99 */         if (((namespaceURI != null && namespaceURI.equals(e.namespaceURI)) || (namespaceURI == null && e.namespaceURI == null)) && e.listener == listener) {
/*     */ 
/*     */           
/* 102 */           prev.next = e.next;
/*     */           break;
/*     */         } 
/* 105 */         prev = e;
/*     */       } 
/* 107 */       if (e == null) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 112 */     this.counts.dec(namespaceURI);
/* 113 */     this.n--;
/* 114 */     this.listeners = null;
/* 115 */     this.listenersNS.remove(namespaceURI);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entry[] getEventListeners() {
/* 122 */     if (this.listeners != null) {
/* 123 */       return this.listeners;
/*     */     }
/* 125 */     this.listeners = new Entry[this.n];
/* 126 */     int i = 0;
/* 127 */     for (Entry e = this.head; e != null; e = e.next) {
/* 128 */       this.listeners[i++] = e;
/*     */     }
/* 130 */     return this.listeners;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entry[] getEventListeners(String namespaceURI) {
/* 137 */     if (namespaceURI == null) {
/* 138 */       return getEventListeners();
/*     */     }
/* 140 */     Entry[] ls = (Entry[])this.listenersNS.get(namespaceURI);
/* 141 */     if (ls != null) {
/* 142 */       return ls;
/*     */     }
/* 144 */     int count = this.counts.get(namespaceURI);
/* 145 */     if (count == 0) {
/* 146 */       return null;
/*     */     }
/* 148 */     ls = new Entry[count];
/* 149 */     this.listenersNS.put(namespaceURI, ls);
/* 150 */     int i = 0;
/* 151 */     for (Entry e = this.head; i < count; e = e.next) {
/* 152 */       if (namespaceURI.equals(e.namespaceURI)) {
/* 153 */         ls[i++] = e;
/*     */       }
/*     */     } 
/* 156 */     return ls;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasEventListener(String namespaceURI) {
/* 163 */     if (namespaceURI == null) {
/* 164 */       return (this.n != 0);
/*     */     }
/* 166 */     return (this.counts.get(namespaceURI) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 173 */     return this.n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class Entry
/*     */   {
/*     */     protected EventListener listener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String namespaceURI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object group;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean mark;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Entry next;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Entry(EventListener listener, String namespaceURI, Object group, Entry next) {
/* 213 */       this.listener = listener;
/* 214 */       this.namespaceURI = namespaceURI;
/* 215 */       this.group = group;
/* 216 */       this.next = next;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public EventListener getListener() {
/* 223 */       return this.listener;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getGroup() {
/* 230 */       return this.group;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getNamespaceURI() {
/* 237 */       return this.namespaceURI;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/EventListenerList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */