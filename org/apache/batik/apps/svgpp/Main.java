/*     */ package org.apache.batik.apps.svgpp;
/*     */ 
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.i18n.LocalizableSupport;
/*     */ import org.apache.batik.transcoder.Transcoder;
/*     */ import org.apache.batik.transcoder.TranscoderInput;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.svg2svg.SVGTranscoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Main
/*     */ {
/*     */   public static final String BUNDLE_CLASSNAME = "org.apache.batik.apps.svgpp.resources.Messages";
/*     */   
/*     */   public static void main(String[] args) {
/*  47 */     (new Main(args)).run();
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
/*  59 */   protected static LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.apps.svgpp.resources.Messages", Main.class.getClassLoader());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String[] arguments;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int index;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   protected Map handlers = new HashMap<Object, Object>();
/*     */   public Main(String[] args) {
/*  77 */     this.handlers.put("-doctype", new DoctypeHandler());
/*  78 */     this.handlers.put("-doc-width", new DocWidthHandler());
/*  79 */     this.handlers.put("-newline", new NewlineHandler());
/*  80 */     this.handlers.put("-public-id", new PublicIdHandler());
/*  81 */     this.handlers.put("-no-format", new NoFormatHandler());
/*  82 */     this.handlers.put("-system-id", new SystemIdHandler());
/*  83 */     this.handlers.put("-tab-width", new TabWidthHandler());
/*  84 */     this.handlers.put("-xml-decl", new XMLDeclHandler());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     this.transcoder = (Transcoder)new SVGTranscoder();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     this.arguments = args;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Transcoder transcoder;
/*     */   
/*     */   public void run() {
/* 104 */     if (this.arguments.length == 0) {
/* 105 */       printUsage(); return;
/*     */     } 
/*     */     try {
/*     */       TranscoderOutput out;
/*     */       while (true) {
/* 110 */         OptionHandler oh = (OptionHandler)this.handlers.get(this.arguments[this.index]);
/* 111 */         if (oh == null) {
/*     */           break;
/*     */         }
/* 114 */         oh.handleOption();
/*     */       } 
/*     */       
/* 117 */       TranscoderInput in = new TranscoderInput(new FileReader(this.arguments[this.index++]));
/*     */       
/* 119 */       if (this.index < this.arguments.length) {
/* 120 */         out = new TranscoderOutput(new FileWriter(this.arguments[this.index]));
/*     */       } else {
/* 122 */         out = new TranscoderOutput(new OutputStreamWriter(System.out));
/*     */       } 
/* 124 */       this.transcoder.transcode(in, out);
/* 125 */     } catch (Exception e) {
/* 126 */       e.printStackTrace();
/* 127 */       printUsage();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void printUsage() {
/* 135 */     printHeader();
/* 136 */     System.out.println(localizableSupport.formatMessage("syntax", null));
/* 137 */     System.out.println();
/* 138 */     System.out.println(localizableSupport.formatMessage("options", null));
/* 139 */     Iterator<String> it = this.handlers.keySet().iterator();
/* 140 */     while (it.hasNext()) {
/* 141 */       String s = it.next();
/* 142 */       System.out.println(((OptionHandler)this.handlers.get(s)).getDescription());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void printHeader() {
/* 150 */     System.out.println(localizableSupport.formatMessage("header", null));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static interface OptionHandler
/*     */   {
/*     */     void handleOption();
/*     */ 
/*     */ 
/*     */     
/*     */     String getDescription();
/*     */   }
/*     */ 
/*     */   
/*     */   protected class DoctypeHandler
/*     */     implements OptionHandler
/*     */   {
/*     */     protected final Map values;
/*     */ 
/*     */     
/*     */     protected DoctypeHandler() {
/* 172 */       this.values = new HashMap<Object, Object>(6);
/*     */       
/* 174 */       this.values.put("remove", SVGTranscoder.VALUE_DOCTYPE_REMOVE);
/* 175 */       this.values.put("change", SVGTranscoder.VALUE_DOCTYPE_CHANGE);
/*     */     }
/*     */     public void handleOption() {
/* 178 */       Main.this.index++;
/* 179 */       if (Main.this.index >= Main.this.arguments.length) {
/* 180 */         throw new IllegalArgumentException();
/*     */       }
/* 182 */       Object val = this.values.get(Main.this.arguments[Main.this.index++]);
/* 183 */       if (val == null) {
/* 184 */         throw new IllegalArgumentException();
/*     */       }
/* 186 */       Main.this.transcoder.addTranscodingHint(SVGTranscoder.KEY_DOCTYPE, val);
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 190 */       return Main.localizableSupport.formatMessage("doctype.description", null);
/*     */     }
/*     */   }
/*     */   
/*     */   protected class NewlineHandler implements OptionHandler {
/*     */     protected final Map values;
/*     */     
/*     */     protected NewlineHandler() {
/* 198 */       this.values = new HashMap<Object, Object>(6);
/*     */       
/* 200 */       this.values.put("cr", SVGTranscoder.VALUE_NEWLINE_CR);
/* 201 */       this.values.put("cr-lf", SVGTranscoder.VALUE_NEWLINE_CR_LF);
/* 202 */       this.values.put("lf", SVGTranscoder.VALUE_NEWLINE_LF);
/*     */     }
/*     */     public void handleOption() {
/* 205 */       Main.this.index++;
/* 206 */       if (Main.this.index >= Main.this.arguments.length) {
/* 207 */         throw new IllegalArgumentException();
/*     */       }
/* 209 */       Object val = this.values.get(Main.this.arguments[Main.this.index++]);
/* 210 */       if (val == null) {
/* 211 */         throw new IllegalArgumentException();
/*     */       }
/* 213 */       Main.this.transcoder.addTranscodingHint(SVGTranscoder.KEY_NEWLINE, val);
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 217 */       return Main.localizableSupport.formatMessage("newline.description", null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class NoFormatHandler
/*     */     implements OptionHandler
/*     */   {
/*     */     public void handleOption() {
/* 226 */       Main.this.index++;
/* 227 */       Main.this.transcoder.addTranscodingHint(SVGTranscoder.KEY_FORMAT, Boolean.FALSE);
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 231 */       return Main.localizableSupport.formatMessage("no-format.description", null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class PublicIdHandler
/*     */     implements OptionHandler
/*     */   {
/*     */     public void handleOption() {
/* 240 */       Main.this.index++;
/* 241 */       if (Main.this.index >= Main.this.arguments.length) {
/* 242 */         throw new IllegalArgumentException();
/*     */       }
/* 244 */       String s = Main.this.arguments[Main.this.index++];
/* 245 */       Main.this.transcoder.addTranscodingHint(SVGTranscoder.KEY_PUBLIC_ID, s);
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 249 */       return Main.localizableSupport.formatMessage("public-id.description", null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class SystemIdHandler
/*     */     implements OptionHandler
/*     */   {
/*     */     public void handleOption() {
/* 258 */       Main.this.index++;
/* 259 */       if (Main.this.index >= Main.this.arguments.length) {
/* 260 */         throw new IllegalArgumentException();
/*     */       }
/* 262 */       String s = Main.this.arguments[Main.this.index++];
/* 263 */       Main.this.transcoder.addTranscodingHint(SVGTranscoder.KEY_SYSTEM_ID, s);
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 267 */       return Main.localizableSupport.formatMessage("system-id.description", null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class XMLDeclHandler
/*     */     implements OptionHandler
/*     */   {
/*     */     public void handleOption() {
/* 276 */       Main.this.index++;
/* 277 */       if (Main.this.index >= Main.this.arguments.length) {
/* 278 */         throw new IllegalArgumentException();
/*     */       }
/* 280 */       String s = Main.this.arguments[Main.this.index++];
/* 281 */       Main.this.transcoder.addTranscodingHint(SVGTranscoder.KEY_XML_DECLARATION, s);
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 285 */       return Main.localizableSupport.formatMessage("xml-decl.description", null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class TabWidthHandler
/*     */     implements OptionHandler
/*     */   {
/*     */     public void handleOption() {
/* 294 */       Main.this.index++;
/* 295 */       if (Main.this.index >= Main.this.arguments.length) {
/* 296 */         throw new IllegalArgumentException();
/*     */       }
/* 298 */       Main.this.transcoder.addTranscodingHint(SVGTranscoder.KEY_TABULATION_WIDTH, Integer.valueOf(Main.this.arguments[Main.this.index++]));
/*     */     }
/*     */ 
/*     */     
/*     */     public String getDescription() {
/* 303 */       return Main.localizableSupport.formatMessage("tab-width.description", null);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected class DocWidthHandler
/*     */     implements OptionHandler
/*     */   {
/*     */     public void handleOption() {
/* 312 */       Main.this.index++;
/* 313 */       if (Main.this.index >= Main.this.arguments.length) {
/* 314 */         throw new IllegalArgumentException();
/*     */       }
/* 316 */       Main.this.transcoder.addTranscodingHint(SVGTranscoder.KEY_DOCUMENT_WIDTH, Integer.valueOf(Main.this.arguments[Main.this.index++]));
/*     */     }
/*     */ 
/*     */     
/*     */     public String getDescription() {
/* 321 */       return Main.localizableSupport.formatMessage("doc-width.description", null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgpp/Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */