/*     */ package org.apache.xmlgraphics.ps;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PSProcSets
/*     */ {
/*     */   public static final PSResource STD_PROCSET;
/*  40 */   public static final PSResource EPS_PROCSET = new EPSProcSet();
/*     */   
/*     */   public static final PSCommandMap STD_COMMAND_MAP;
/*     */ 
/*     */   
/*     */   static {
/*  46 */     StdProcSet stdProcSet = new StdProcSet();
/*  47 */     STD_PROCSET = stdProcSet;
/*  48 */     STD_COMMAND_MAP = stdProcSet;
/*     */   }
/*     */ 
/*     */   
/*     */   private static class StdProcSet
/*     */     extends PSProcSet
/*     */     implements PSCommandMap
/*     */   {
/*     */     private static final Map STANDARD_MACROS;
/*     */ 
/*     */     
/*     */     static {
/*  60 */       Map<Object, Object> macros = new HashMap<Object, Object>();
/*  61 */       macros.put("moveto", "M");
/*  62 */       macros.put("rmoveto", "RM");
/*  63 */       macros.put("curveto", "C");
/*  64 */       macros.put("lineto", "L");
/*  65 */       macros.put("show", "t");
/*  66 */       macros.put("ashow", "A");
/*  67 */       macros.put("closepath", "cp");
/*  68 */       macros.put("setrgbcolor", "RC");
/*  69 */       macros.put("setgray", "GC");
/*  70 */       macros.put("setcmykcolor", "CC");
/*  71 */       macros.put("newpath", "N");
/*  72 */       macros.put("setmiterlimit", "ML");
/*  73 */       macros.put("setlinewidth", "LC");
/*  74 */       macros.put("setlinewidth", "LW");
/*  75 */       macros.put("setlinejoin", "LJ");
/*  76 */       macros.put("grestore", "GR");
/*  77 */       macros.put("gsave", "GS");
/*  78 */       macros.put("fill", "f");
/*  79 */       macros.put("stroke", "S");
/*  80 */       macros.put("concat", "CT");
/*  81 */       STANDARD_MACROS = Collections.unmodifiableMap(macros);
/*     */     }
/*     */     
/*     */     public StdProcSet() {
/*  85 */       super("Apache XML Graphics Std ProcSet", 1.2F, 0);
/*     */     }
/*     */     
/*     */     public void writeTo(PSGenerator gen) throws IOException {
/*  89 */       gen.writeDSCComment("BeginResource", new Object[] { "procset", getName(), Float.toString(getVersion()), Integer.toString(getRevision()) });
/*     */ 
/*     */       
/*  92 */       gen.writeDSCComment("Version", new Object[] { Float.toString(getVersion()), Integer.toString(getRevision()) });
/*     */       
/*  94 */       gen.writeDSCComment("Copyright", "Copyright 2001-2003,2010 The Apache Software Foundation. License terms: http://www.apache.org/licenses/LICENSE-2.0");
/*     */ 
/*     */       
/*  97 */       gen.writeDSCComment("Title", "Basic set of procedures used by the XML Graphics project (Batik and FOP)");
/*     */ 
/*     */       
/* 100 */       gen.writeln("/bd{bind def}bind def");
/* 101 */       gen.writeln("/ld{load def}bd");
/* 102 */       Iterator<Map.Entry> iter = STANDARD_MACROS.entrySet().iterator();
/* 103 */       while (iter.hasNext()) {
/* 104 */         Map.Entry entry = iter.next();
/* 105 */         gen.writeln("/" + entry.getValue() + "/" + entry.getKey() + " ld");
/*     */       } 
/*     */       
/* 108 */       gen.writeln("/re {4 2 roll M");
/* 109 */       gen.writeln("1 index 0 rlineto");
/* 110 */       gen.writeln("0 exch rlineto");
/* 111 */       gen.writeln("neg 0 rlineto");
/* 112 */       gen.writeln("cp } bd");
/*     */       
/* 114 */       gen.writeln("/_ctm matrix def");
/* 115 */       gen.writeln("/_tm matrix def");
/*     */       
/* 117 */       gen.writeln("/BT { _ctm currentmatrix pop matrix _tm copy pop 0 0 moveto } bd");
/*     */       
/* 119 */       gen.writeln("/ET { _ctm setmatrix } bd");
/* 120 */       gen.writeln("/iTm { _ctm setmatrix _tm concat } bd");
/* 121 */       gen.writeln("/Tm { _tm astore pop iTm 0 0 moveto } bd");
/*     */       
/* 123 */       gen.writeln("/ux 0.0 def");
/* 124 */       gen.writeln("/uy 0.0 def");
/*     */ 
/*     */       
/* 127 */       gen.writeln("/F {");
/* 128 */       gen.writeln("  /Tp exch def");
/*     */       
/* 130 */       gen.writeln("  /Tf exch def");
/* 131 */       gen.writeln("  Tf findfont Tp scalefont setfont");
/* 132 */       gen.writeln("  /cf Tf def  /cs Tp def");
/* 133 */       gen.writeln("} bd");
/*     */       
/* 135 */       gen.writeln("/ULS {currentpoint /uy exch def /ux exch def} bd");
/* 136 */       gen.writeln("/ULE {");
/* 137 */       gen.writeln("  /Tcx currentpoint pop def");
/* 138 */       gen.writeln("  gsave");
/* 139 */       gen.writeln("  newpath");
/* 140 */       gen.writeln("  cf findfont cs scalefont dup");
/* 141 */       gen.writeln("  /FontMatrix get 0 get /Ts exch def /FontInfo get dup");
/* 142 */       gen.writeln("  /UnderlinePosition get Ts mul /To exch def");
/* 143 */       gen.writeln("  /UnderlineThickness get Ts mul /Tt exch def");
/* 144 */       gen.writeln("  ux uy To add moveto  Tcx uy To add lineto");
/* 145 */       gen.writeln("  Tt setlinewidth stroke");
/* 146 */       gen.writeln("  grestore");
/* 147 */       gen.writeln("} bd");
/*     */       
/* 149 */       gen.writeln("/OLE {");
/* 150 */       gen.writeln("  /Tcx currentpoint pop def");
/* 151 */       gen.writeln("  gsave");
/* 152 */       gen.writeln("  newpath");
/* 153 */       gen.writeln("  cf findfont cs scalefont dup");
/* 154 */       gen.writeln("  /FontMatrix get 0 get /Ts exch def /FontInfo get dup");
/* 155 */       gen.writeln("  /UnderlinePosition get Ts mul /To exch def");
/* 156 */       gen.writeln("  /UnderlineThickness get Ts mul /Tt exch def");
/* 157 */       gen.writeln("  ux uy To add cs add moveto Tcx uy To add cs add lineto");
/* 158 */       gen.writeln("  Tt setlinewidth stroke");
/* 159 */       gen.writeln("  grestore");
/* 160 */       gen.writeln("} bd");
/*     */       
/* 162 */       gen.writeln("/SOE {");
/* 163 */       gen.writeln("  /Tcx currentpoint pop def");
/* 164 */       gen.writeln("  gsave");
/* 165 */       gen.writeln("  newpath");
/* 166 */       gen.writeln("  cf findfont cs scalefont dup");
/* 167 */       gen.writeln("  /FontMatrix get 0 get /Ts exch def /FontInfo get dup");
/* 168 */       gen.writeln("  /UnderlinePosition get Ts mul /To exch def");
/* 169 */       gen.writeln("  /UnderlineThickness get Ts mul /Tt exch def");
/* 170 */       gen.writeln("  ux uy To add cs 10 mul 26 idiv add moveto Tcx uy To add cs 10 mul 26 idiv add lineto");
/*     */       
/* 172 */       gen.writeln("  Tt setlinewidth stroke");
/* 173 */       gen.writeln("  grestore");
/* 174 */       gen.writeln("} bd");
/*     */       
/* 176 */       gen.writeln("/QT {");
/* 177 */       gen.writeln("/Y22 exch store");
/* 178 */       gen.writeln("/X22 exch store");
/* 179 */       gen.writeln("/Y21 exch store");
/* 180 */       gen.writeln("/X21 exch store");
/* 181 */       gen.writeln("currentpoint");
/* 182 */       gen.writeln("/Y21 load 2 mul add 3 div exch");
/* 183 */       gen.writeln("/X21 load 2 mul add 3 div exch");
/* 184 */       gen.writeln("/X21 load 2 mul /X22 load add 3 div");
/* 185 */       gen.writeln("/Y21 load 2 mul /Y22 load add 3 div");
/* 186 */       gen.writeln("/X22 load /Y22 load curveto");
/* 187 */       gen.writeln("} bd");
/*     */       
/* 189 */       gen.writeln("/SSPD {");
/* 190 */       gen.writeln("dup length /d exch dict def");
/* 191 */       gen.writeln("{");
/* 192 */       gen.writeln("/v exch def");
/* 193 */       gen.writeln("/k exch def");
/* 194 */       gen.writeln("currentpagedevice k known {");
/* 195 */       gen.writeln("/cpdv currentpagedevice k get def");
/* 196 */       gen.writeln("v cpdv ne {");
/* 197 */       gen.writeln("/upd false def");
/* 198 */       gen.writeln("/nullv v type /nulltype eq def");
/* 199 */       gen.writeln("/nullcpdv cpdv type /nulltype eq def");
/* 200 */       gen.writeln("nullv nullcpdv or");
/* 201 */       gen.writeln("{");
/* 202 */       gen.writeln("/upd true def");
/* 203 */       gen.writeln("} {");
/* 204 */       gen.writeln("/sametype v type cpdv type eq def");
/* 205 */       gen.writeln("sametype {");
/* 206 */       gen.writeln("v type /arraytype eq {");
/* 207 */       gen.writeln("/vlen v length def");
/* 208 */       gen.writeln("/cpdvlen cpdv length def");
/* 209 */       gen.writeln("vlen cpdvlen eq {");
/* 210 */       gen.writeln("0 1 vlen 1 sub {");
/* 211 */       gen.writeln("/i exch def");
/* 212 */       gen.writeln("/obj v i get def");
/* 213 */       gen.writeln("/cpdobj cpdv i get def");
/* 214 */       gen.writeln("obj cpdobj ne {");
/* 215 */       gen.writeln("/upd true def");
/* 216 */       gen.writeln("exit");
/* 217 */       gen.writeln("} if");
/* 218 */       gen.writeln("} for");
/* 219 */       gen.writeln("} {");
/* 220 */       gen.writeln("/upd true def");
/* 221 */       gen.writeln("} ifelse");
/* 222 */       gen.writeln("} {");
/* 223 */       gen.writeln("v type /dicttype eq {");
/* 224 */       gen.writeln("v {");
/* 225 */       gen.writeln("/dv exch def");
/* 226 */       gen.writeln("/dk exch def");
/* 227 */       gen.writeln("/cpddv cpdv dk get def");
/* 228 */       gen.writeln("dv cpddv ne {");
/* 229 */       gen.writeln("/upd true def");
/* 230 */       gen.writeln("exit");
/* 231 */       gen.writeln("} if");
/* 232 */       gen.writeln("} forall");
/* 233 */       gen.writeln("} {");
/* 234 */       gen.writeln("/upd true def");
/* 235 */       gen.writeln("} ifelse");
/* 236 */       gen.writeln("} ifelse");
/* 237 */       gen.writeln("} if");
/* 238 */       gen.writeln("} ifelse");
/* 239 */       gen.writeln("upd true eq {");
/* 240 */       gen.writeln("d k v put");
/* 241 */       gen.writeln("} if");
/* 242 */       gen.writeln("} if");
/* 243 */       gen.writeln("} if");
/* 244 */       gen.writeln("} forall");
/* 245 */       gen.writeln("d length 0 gt {");
/* 246 */       gen.writeln("d setpagedevice");
/* 247 */       gen.writeln("} if");
/* 248 */       gen.writeln("} bd");
/*     */       
/* 250 */       gen.writeln("/RE { % /NewFontName [NewEncodingArray] /FontName RE -");
/* 251 */       gen.writeln("  findfont dup length dict begin");
/* 252 */       gen.writeln("  {");
/* 253 */       gen.writeln("    1 index /FID ne");
/* 254 */       gen.writeln("    {def} {pop pop} ifelse");
/* 255 */       gen.writeln("  } forall");
/* 256 */       gen.writeln("  /Encoding exch def");
/* 257 */       gen.writeln("  /FontName 1 index def");
/* 258 */       gen.writeln("  currentdict definefont pop");
/* 259 */       gen.writeln("  end");
/* 260 */       gen.writeln("} bind def");
/*     */       
/* 262 */       gen.writeDSCComment("EndResource");
/* 263 */       gen.getResourceTracker().registerSuppliedResource(this);
/*     */     }
/*     */ 
/*     */     
/*     */     public String mapCommand(String command) {
/* 268 */       String mapped = (String)STANDARD_MACROS.get(command);
/* 269 */       return (mapped != null) ? mapped : command;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class EPSProcSet
/*     */     extends PSProcSet
/*     */   {
/*     */     public EPSProcSet() {
/* 277 */       super("Apache XML Graphics EPS ProcSet", 1.0F, 0);
/*     */     }
/*     */     
/*     */     public void writeTo(PSGenerator gen) throws IOException {
/* 281 */       gen.writeDSCComment("BeginResource", new Object[] { "procset", getName(), Float.toString(getVersion()), Integer.toString(getRevision()) });
/*     */ 
/*     */       
/* 284 */       gen.writeDSCComment("Version", new Object[] { Float.toString(getVersion()), Integer.toString(getRevision()) });
/*     */       
/* 286 */       gen.writeDSCComment("Copyright", "Copyright 2002-2003 The Apache Software Foundation. License terms: http://www.apache.org/licenses/LICENSE-2.0");
/*     */ 
/*     */       
/* 289 */       gen.writeDSCComment("Title", "EPS procedures used by the Apache XML Graphics project (Batik and FOP)");
/*     */ 
/*     */       
/* 292 */       gen.writeln("/BeginEPSF { %def");
/* 293 */       gen.writeln("/b4_Inc_state save def         % Save state for cleanup");
/* 294 */       gen.writeln("/dict_count countdictstack def % Count objects on dict stack");
/* 295 */       gen.writeln("/op_count count 1 sub def      % Count objects on operand stack");
/* 296 */       gen.writeln("userdict begin                 % Push userdict on dict stack");
/* 297 */       gen.writeln("/showpage { } def              % Redefine showpage, { } = null proc");
/* 298 */       gen.writeln("0 setgray 0 setlinecap         % Prepare graphics state");
/* 299 */       gen.writeln("1 setlinewidth 0 setlinejoin");
/* 300 */       gen.writeln("10 setmiterlimit [ ] 0 setdash newpath");
/* 301 */       gen.writeln("/languagelevel where           % If level not equal to 1 then");
/* 302 */       gen.writeln("{pop languagelevel             % set strokeadjust and");
/* 303 */       gen.writeln("1 ne                           % overprint to their defaults.");
/* 304 */       gen.writeln("{false setstrokeadjust false setoverprint");
/* 305 */       gen.writeln("} if");
/* 306 */       gen.writeln("} if");
/* 307 */       gen.writeln("} bd");
/*     */       
/* 309 */       gen.writeln("/EndEPSF { %def");
/* 310 */       gen.writeln("count op_count sub {pop} repeat            % Clean up stacks");
/* 311 */       gen.writeln("countdictstack dict_count sub {end} repeat");
/* 312 */       gen.writeln("b4_Inc_state restore");
/* 313 */       gen.writeln("} bd");
/*     */       
/* 315 */       gen.writeDSCComment("EndResource");
/* 316 */       gen.getResourceTracker().registerSuppliedResource(this);
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
/*     */   public static void writeStdProcSet(PSGenerator gen) throws IOException {
/* 328 */     ((StdProcSet)STD_PROCSET).writeTo(gen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeFOPStdProcSet(PSGenerator gen) throws IOException {
/* 339 */     writeStdProcSet(gen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeEPSProcSet(PSGenerator gen) throws IOException {
/* 349 */     ((EPSProcSet)EPS_PROCSET).writeTo(gen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeFOPEPSProcSet(PSGenerator gen) throws IOException {
/* 359 */     writeEPSProcSet(gen);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/PSProcSets.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */