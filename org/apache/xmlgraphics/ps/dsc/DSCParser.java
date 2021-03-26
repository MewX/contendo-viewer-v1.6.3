/*     */ package org.apache.xmlgraphics.ps.dsc;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.ps.DSCConstants;
/*     */ import org.apache.xmlgraphics.ps.PSGenerator;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCAtend;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCComment;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCEvent;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.DSCHeaderComment;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.PostScriptComment;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.PostScriptLine;
/*     */ import org.apache.xmlgraphics.ps.dsc.events.UnparsedDSCComment;
/*     */ import org.apache.xmlgraphics.ps.dsc.tools.DSCTools;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DSCParser
/*     */   implements DSCParserConstants
/*     */ {
/*  50 */   private static final Log LOG = LogFactory.getLog(DSCParser.class);
/*     */   
/*     */   private InputStream in;
/*     */   
/*     */   private BufferedReader reader;
/*     */   
/*     */   private boolean eofFound;
/*     */   
/*     */   private boolean checkEOF = true;
/*     */   
/*     */   private DSCEvent currentEvent;
/*     */   
/*     */   private DSCEvent nextEvent;
/*     */   
/*     */   private DSCListener nestedDocumentHandler;
/*     */   
/*     */   private DSCListener filterListener;
/*     */   private List listeners;
/*     */   private boolean listenersDisabled;
/*     */   
/*     */   public DSCParser(InputStream in) throws IOException, DSCException {
/*  71 */     if (in.markSupported()) {
/*  72 */       this.in = in;
/*     */     } else {
/*     */       
/*  75 */       this.in = new BufferedInputStream(this.in);
/*     */     } 
/*  77 */     String encoding = "US-ASCII";
/*     */     try {
/*  79 */       this.reader = new BufferedReader(new InputStreamReader(this.in, encoding));
/*     */     }
/*  81 */     catch (UnsupportedEncodingException e) {
/*  82 */       throw new RuntimeException("Incompatible VM! " + e.getMessage());
/*     */     } 
/*  84 */     parseNext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getInputStream() {
/*  92 */     return this.in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void warn(String msg) {
/* 101 */     LOG.warn(msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String readLine() throws IOException, DSCException {
/* 112 */     String line = this.reader.readLine();
/* 113 */     checkLine(line);
/*     */     
/* 115 */     return line;
/*     */   }
/*     */   
/*     */   private void checkLine(String line) throws DSCException {
/* 119 */     if (line == null) {
/* 120 */       if (!this.eofFound) {
/* 121 */         throw new DSCException("%%EOF not found. File is not well-formed.");
/*     */       }
/* 123 */     } else if (line.length() > 255) {
/* 124 */       warn("Line longer than 255 characters. This file is not fully PostScript conforming.");
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isWhitespace(char c) {
/* 129 */     return (c == ' ' || c == '\t');
/*     */   }
/*     */   private DSCComment parseDSCLine(String line) throws IOException, DSCException {
/*     */     String name;
/* 133 */     int colon = line.indexOf(':');
/*     */     
/* 135 */     StringBuilder value = new StringBuilder();
/* 136 */     if (colon > 0) {
/* 137 */       name = line.substring(2, colon);
/* 138 */       int startOfValue = colon + 1;
/* 139 */       if (startOfValue < line.length()) {
/* 140 */         if (isWhitespace(line.charAt(startOfValue))) {
/* 141 */           startOfValue++;
/*     */         }
/* 143 */         value = new StringBuilder(line.substring(startOfValue).trim());
/* 144 */         if (value.toString().equals(DSCConstants.ATEND.toString())) {
/* 145 */           return (DSCComment)new DSCAtend(name);
/*     */         }
/*     */       } 
/*     */       
/*     */       while (true) {
/* 150 */         this.reader.mark(512);
/* 151 */         String nextLine = readLine();
/* 152 */         if (nextLine == null)
/*     */           break; 
/* 154 */         if (!nextLine.startsWith("%%+")) {
/*     */           break;
/*     */         }
/* 157 */         value.append(nextLine.substring(3));
/*     */       } 
/* 159 */       this.reader.reset();
/*     */     } else {
/* 161 */       name = line.substring(2);
/* 162 */       return parseDSCComment(name, null);
/*     */     } 
/* 164 */     return parseDSCComment(name, value.toString());
/*     */   }
/*     */   
/*     */   private DSCComment parseDSCComment(String name, String value) {
/* 168 */     DSCComment parsed = DSCCommentFactory.createDSCCommentFor(name);
/* 169 */     if (parsed != null) {
/*     */       try {
/* 171 */         parsed.parseValue(value);
/* 172 */         return parsed;
/* 173 */       } catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/* 177 */     UnparsedDSCComment unparsed = new UnparsedDSCComment(name);
/* 178 */     unparsed.parseValue(value);
/* 179 */     return (DSCComment)unparsed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parse(DSCHandler handler) throws IOException, DSCException {
/* 189 */     DSCHeaderComment header = DSCTools.checkAndSkipDSC30Header(this);
/* 190 */     handler.startDocument("%!" + header.getComment());
/*     */     
/* 192 */     while (hasNext()) {
/* 193 */       DSCEvent event = nextEvent();
/* 194 */       switch (event.getEventType()) {
/*     */         case 0:
/* 196 */           handler.startDocument("%!" + ((DSCHeaderComment)event).getComment());
/*     */           continue;
/*     */         case 1:
/* 199 */           handler.handleDSCComment(event.asDSCComment());
/*     */           continue;
/*     */         case 2:
/* 202 */           handler.comment(((PostScriptComment)event).getComment());
/*     */           continue;
/*     */         case 3:
/* 205 */           handler.line(getLine());
/*     */           continue;
/*     */         case 4:
/* 208 */           handler.endDocument();
/*     */           continue;
/*     */       } 
/* 211 */       throw new IllegalStateException("Illegal event type: " + event.getEventType());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/* 221 */     return (this.nextEvent != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int next() throws IOException, DSCException {
/* 232 */     if (hasNext()) {
/* 233 */       this.currentEvent = this.nextEvent;
/* 234 */       parseNext();
/*     */       
/* 236 */       processListeners();
/*     */       
/* 238 */       return this.currentEvent.getEventType();
/*     */     } 
/* 240 */     throw new NoSuchElementException("There are no more events");
/*     */   }
/*     */ 
/*     */   
/*     */   private void processListeners() throws IOException, DSCException {
/* 245 */     if (isListenersDisabled()) {
/*     */       return;
/*     */     }
/* 248 */     if (this.filterListener != null)
/*     */     {
/* 250 */       this.filterListener.processEvent(this.currentEvent, this);
/*     */     }
/* 252 */     if (this.listeners != null) {
/* 253 */       Iterator<DSCListener> iter = this.listeners.iterator();
/* 254 */       while (iter.hasNext()) {
/* 255 */         ((DSCListener)iter.next()).processEvent(this.currentEvent, this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DSCEvent nextEvent() throws IOException, DSCException {
/* 267 */     next();
/* 268 */     return getCurrentEvent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DSCEvent getCurrentEvent() {
/* 276 */     return this.currentEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DSCEvent peek() {
/* 284 */     return this.nextEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parseNext() throws IOException, DSCException {
/* 293 */     String line = readLine();
/* 294 */     if (line != null) {
/* 295 */       if (isCheckEOF() && this.eofFound && line.length() > 0) {
/* 296 */         throw new DSCException("Content found after EOF");
/*     */       }
/* 298 */       if (line.startsWith("%%")) {
/* 299 */         DSCComment comment = parseDSCLine(line);
/* 300 */         if (comment.getEventType() == 4) {
/* 301 */           this.eofFound = true;
/*     */         }
/* 303 */         this.nextEvent = (DSCEvent)comment;
/* 304 */       } else if (line.startsWith("%!")) {
/* 305 */         this.nextEvent = (DSCEvent)new DSCHeaderComment(line.substring(2));
/* 306 */       } else if (line.startsWith("%")) {
/* 307 */         this.nextEvent = (DSCEvent)new PostScriptComment(line.substring(1));
/*     */       } else {
/* 309 */         this.nextEvent = (DSCEvent)new PostScriptLine(line);
/*     */       } 
/*     */     } else {
/* 312 */       this.nextEvent = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLine() {
/* 322 */     if (this.currentEvent.getEventType() == 3) {
/* 323 */       return ((PostScriptLine)this.currentEvent).getLine();
/*     */     }
/* 325 */     throw new IllegalStateException("Current event is not a PostScript line");
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
/*     */   public DSCComment nextDSCComment(String name) throws IOException, DSCException {
/* 338 */     return nextDSCComment(name, null);
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
/*     */   public DSCComment nextDSCComment(String name, PSGenerator gen) throws IOException, DSCException {
/* 351 */     while (hasNext()) {
/* 352 */       DSCEvent event = nextEvent();
/* 353 */       if (event.isDSCComment()) {
/* 354 */         DSCComment comment = event.asDSCComment();
/* 355 */         if (name.equals(comment.getName())) {
/* 356 */           return comment;
/*     */         }
/*     */       } 
/* 359 */       if (gen != null) {
/* 360 */         event.generate(gen);
/*     */       }
/*     */     } 
/* 363 */     return null;
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
/*     */   public PostScriptComment nextPSComment(String prefix, PSGenerator gen) throws IOException, DSCException {
/* 380 */     while (hasNext()) {
/* 381 */       DSCEvent event = nextEvent();
/* 382 */       if (event.isComment()) {
/* 383 */         PostScriptComment comment = (PostScriptComment)event;
/* 384 */         if (comment.getComment().startsWith(prefix)) {
/* 385 */           return comment;
/*     */         }
/*     */       } 
/* 388 */       if (gen != null) {
/* 389 */         event.generate(gen);
/*     */       }
/*     */     } 
/* 392 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilter(DSCFilter filter) {
/* 400 */     if (filter != null) {
/* 401 */       this.filterListener = new FilteringEventListener(filter);
/*     */     } else {
/* 403 */       this.filterListener = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addListener(DSCListener listener) {
/* 412 */     if (listener == null) {
/* 413 */       throw new NullPointerException("listener must not be null");
/*     */     }
/* 415 */     if (this.listeners == null) {
/* 416 */       this.listeners = new ArrayList();
/*     */     }
/* 418 */     this.listeners.add(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeListener(DSCListener listener) {
/* 426 */     if (this.listeners != null) {
/* 427 */       this.listeners.remove(listener);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setListenersDisabled(boolean value) {
/* 437 */     this.listenersDisabled = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isListenersDisabled() {
/* 445 */     return this.listenersDisabled;
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
/*     */   public void setNestedDocumentHandler(NestedDocumentHandler handler) {
/* 458 */     if (handler == null) {
/* 459 */       removeListener(this.nestedDocumentHandler);
/*     */     } else {
/* 461 */       MyDSCListener l = new MyDSCListener();
/* 462 */       l.handler = handler;
/* 463 */       addListener(l);
/*     */     } 
/*     */   }
/*     */   
/*     */   static class MyDSCListener implements DSCListener {
/*     */     private NestedDocumentHandler handler;
/*     */     
/*     */     public void processEvent(DSCEvent event, DSCParser parser) throws IOException, DSCException {
/* 471 */       this.handler.handle(event, parser);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCheckEOF(boolean value) {
/* 481 */     this.checkEOF = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCheckEOF() {
/* 489 */     return this.checkEOF;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/DSCParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */