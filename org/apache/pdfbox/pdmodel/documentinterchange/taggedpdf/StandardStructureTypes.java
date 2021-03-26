/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardStructureTypes
/*     */ {
/*  38 */   private static final Log LOG = LogFactory.getLog(StandardStructureTypes.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String DOCUMENT = "Document";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String PART = "Part";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ART = "Art";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String SECT = "Sect";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String DIV = "Div";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String BLOCK_QUOTE = "BlockQuote";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String CAPTION = "Caption";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TOC = "TOC";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TOCI = "TOCI";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String INDEX = "Index";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String NON_STRUCT = "NonStruct";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String PRIVATE = "Private";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String P = "P";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String H = "H";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String H1 = "H1";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String H2 = "H2";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String H3 = "H3";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String H4 = "H4";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String H5 = "H5";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String H6 = "H6";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String L = "L";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String LI = "LI";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String LBL = "Lbl";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String L_BODY = "LBody";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TABLE = "Table";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TR = "TR";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TH = "TH";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String TD = "TD";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String T_HEAD = "THead";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String T_BODY = "TBody";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String T_FOOT = "TFoot";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String SPAN = "Span";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String QUOTE = "Quote";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String NOTE = "Note";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String REFERENCE = "Reference";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String BIB_ENTRY = "BibEntry";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String CODE = "Code";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String LINK = "Link";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ANNOT = "Annot";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String RUBY = "Ruby";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String RB = "RB";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String RT = "RT";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String RP = "RP";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String WARICHU = "Warichu";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String WT = "WT";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String WP = "WP";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String Figure = "Figure";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String FORMULA = "Formula";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String FORM = "Form";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public static List<String> types = new ArrayList<String>();
/*     */ 
/*     */   
/*     */   static {
/* 304 */     Field[] fields = StandardStructureTypes.class.getFields();
/* 305 */     for (Field field : fields) {
/*     */       
/* 307 */       if (Modifier.isFinal(field.getModifiers())) {
/*     */         
/*     */         try {
/*     */           
/* 311 */           types.add(field.get(null).toString());
/*     */         }
/* 313 */         catch (IllegalArgumentException e) {
/*     */           
/* 315 */           LOG.error(e, e);
/*     */         }
/* 317 */         catch (IllegalAccessException e) {
/*     */           
/* 319 */           LOG.error(e, e);
/*     */         } 
/*     */       }
/*     */     } 
/* 323 */     Collections.sort(types);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/taggedpdf/StandardStructureTypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */