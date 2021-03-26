/*     */ package org.apache.batik.dom.events;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.events.Event;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DocumentEventSupport
/*     */ {
/*     */   public static final String EVENT_TYPE = "Event";
/*     */   public static final String MUTATION_EVENT_TYPE = "MutationEvent";
/*     */   public static final String MUTATION_NAME_EVENT_TYPE = "MutationNameEvent";
/*     */   public static final String MOUSE_EVENT_TYPE = "MouseEvent";
/*     */   public static final String UI_EVENT_TYPE = "UIEvent";
/*     */   public static final String KEYBOARD_EVENT_TYPE = "KeyboardEvent";
/*     */   public static final String TEXT_EVENT_TYPE = "TextEvent";
/*     */   public static final String CUSTOM_EVENT_TYPE = "CustomEvent";
/*     */   public static final String EVENT_DOM2_TYPE = "Events";
/*     */   public static final String MUTATION_EVENT_DOM2_TYPE = "MutationEvents";
/*     */   public static final String MOUSE_EVENT_DOM2_TYPE = "MouseEvents";
/*     */   public static final String UI_EVENT_DOM2_TYPE = "UIEvents";
/*     */   public static final String KEY_EVENT_DOM2_TYPE = "KeyEvents";
/*     */   protected HashMap<String, EventFactory> eventFactories;
/*     */   
/*     */   public DocumentEventSupport() {
/* 103 */     this.eventFactories = new HashMap<String, EventFactory>();
/*     */ 
/*     */     
/* 106 */     this.eventFactories.put("Event".toLowerCase(), new SimpleEventFactory());
/*     */     
/* 108 */     this.eventFactories.put("MutationEvent".toLowerCase(), new MutationEventFactory());
/*     */     
/* 110 */     this.eventFactories.put("MutationNameEvent".toLowerCase(), new MutationNameEventFactory());
/*     */     
/* 112 */     this.eventFactories.put("MouseEvent".toLowerCase(), new MouseEventFactory());
/*     */     
/* 114 */     this.eventFactories.put("KeyboardEvent".toLowerCase(), new KeyboardEventFactory());
/*     */     
/* 116 */     this.eventFactories.put("UIEvent".toLowerCase(), new UIEventFactory());
/*     */     
/* 118 */     this.eventFactories.put("TextEvent".toLowerCase(), new TextEventFactory());
/*     */     
/* 120 */     this.eventFactories.put("CustomEvent".toLowerCase(), new CustomEventFactory());
/*     */ 
/*     */     
/* 123 */     this.eventFactories.put("Events".toLowerCase(), new SimpleEventFactory());
/*     */     
/* 125 */     this.eventFactories.put("MutationEvents".toLowerCase(), new MutationEventFactory());
/*     */     
/* 127 */     this.eventFactories.put("MouseEvents".toLowerCase(), new MouseEventFactory());
/*     */     
/* 129 */     this.eventFactories.put("KeyEvents".toLowerCase(), new KeyEventFactory());
/*     */     
/* 131 */     this.eventFactories.put("UIEvents".toLowerCase(), new UIEventFactory());
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
/*     */   public Event createEvent(String eventType) throws DOMException {
/* 166 */     EventFactory ef = this.eventFactories.get(eventType.toLowerCase());
/* 167 */     if (ef == null) {
/* 168 */       throw new DOMException((short)9, "Bad event type: " + eventType);
/*     */     }
/*     */     
/* 171 */     return ef.createEvent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerEventFactory(String eventType, EventFactory factory) {
/* 179 */     this.eventFactories.put(eventType.toLowerCase(), factory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface EventFactory
/*     */   {
/*     */     Event createEvent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class SimpleEventFactory
/*     */     implements EventFactory
/*     */   {
/*     */     public Event createEvent() {
/* 201 */       return new DOMEvent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class MutationEventFactory
/*     */     implements EventFactory
/*     */   {
/*     */     public Event createEvent() {
/* 213 */       return new DOMMutationEvent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class MutationNameEventFactory
/*     */     implements EventFactory
/*     */   {
/*     */     public Event createEvent() {
/* 225 */       return new DOMMutationNameEvent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class MouseEventFactory
/*     */     implements EventFactory
/*     */   {
/*     */     public Event createEvent() {
/* 237 */       return new DOMMouseEvent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class KeyEventFactory
/*     */     implements EventFactory
/*     */   {
/*     */     public Event createEvent() {
/* 249 */       return new DOMKeyEvent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class KeyboardEventFactory
/*     */     implements EventFactory
/*     */   {
/*     */     public Event createEvent() {
/* 261 */       return new DOMKeyboardEvent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class UIEventFactory
/*     */     implements EventFactory
/*     */   {
/*     */     public Event createEvent() {
/* 273 */       return new DOMUIEvent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class TextEventFactory
/*     */     implements EventFactory
/*     */   {
/*     */     public Event createEvent() {
/* 285 */       return new DOMTextEvent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class CustomEventFactory
/*     */     implements EventFactory
/*     */   {
/*     */     public Event createEvent() {
/* 297 */       return new DOMCustomEvent();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/events/DocumentEventSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */