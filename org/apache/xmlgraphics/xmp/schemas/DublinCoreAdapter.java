/*     */ package org.apache.xmlgraphics.xmp.schemas;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.apache.xmlgraphics.xmp.Metadata;
/*     */ import org.apache.xmlgraphics.xmp.XMPSchemaAdapter;
/*     */ import org.apache.xmlgraphics.xmp.XMPSchemaRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DublinCoreAdapter
/*     */   extends XMPSchemaAdapter
/*     */ {
/*     */   private static final String CONTRIBUTOR = "contributor";
/*     */   private static final String COVERAGE = "coverage";
/*     */   private static final String CREATOR = "creator";
/*     */   private static final String DATE = "date";
/*     */   private static final String DESCRIPTION = "description";
/*     */   private static final String FORMAT = "format";
/*     */   private static final String IDENTIFIER = "identifier";
/*     */   private static final String LANGUAGE = "language";
/*     */   private static final String PUBLISHER = "publisher";
/*     */   private static final String RELATION = "relation";
/*     */   private static final String RIGHTS = "rights";
/*     */   private static final String SOURCE = "source";
/*     */   private static final String SUBJECT = "subject";
/*     */   private static final String TITLE = "title";
/*     */   private static final String TYPE = "type";
/*     */   
/*     */   public DublinCoreAdapter(Metadata meta) {
/*  57 */     super(meta, XMPSchemaRegistry.getInstance().getSchema("http://purl.org/dc/elements/1.1/"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addContributor(String value) {
/*  65 */     addStringToBag("contributor", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeContributor(String value) {
/*  74 */     return removeStringFromArray("contributor", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getContributors() {
/*  82 */     return getStringArray("contributor");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCoverage(String value) {
/*  90 */     setValue("coverage", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCoverage() {
/*  98 */     return getValue("coverage");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCreator(String value) {
/* 106 */     addStringToSeq("creator", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeCreator(String value) {
/* 115 */     return removeStringFromArray("creator", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getCreators() {
/* 123 */     return getStringArray("creator");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDate(Date value) {
/* 132 */     addDateToSeq("date", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date[] getDates() {
/* 141 */     return getDateArray("date");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getDate() {
/* 150 */     Date[] dates = getDates();
/* 151 */     if (dates != null) {
/* 152 */       Date latest = null;
/* 153 */       for (int i = 0, c = dates.length; i < c; i++) {
/* 154 */         if (latest == null || dates[i].getTime() > latest.getTime()) {
/* 155 */           latest = dates[i];
/*     */         }
/*     */       } 
/* 158 */       return latest;
/*     */     } 
/* 160 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String lang, String value) {
/* 171 */     setLangAlt("description", lang, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 179 */     return getDescription((String)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription(String lang) {
/* 188 */     return getLangAlt(lang, "description");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String value) {
/* 198 */     setValue("format", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 206 */     return getValue("format");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIdentifier(String value) {
/* 214 */     setValue("identifier", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIdentifier() {
/* 222 */     return getValue("identifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLanguage(String value) {
/* 230 */     addStringToBag("language", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getLanguages() {
/* 238 */     return getStringArray("language");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPublisher(String value) {
/* 246 */     addStringToBag("publisher", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getPublisher() {
/* 254 */     return getStringArray("publisher");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRelation(String value) {
/* 262 */     addStringToBag("relation", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getRelations() {
/* 270 */     return getStringArray("relation");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRights(String lang, String value) {
/* 279 */     setLangAlt("rights", lang, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRights() {
/* 287 */     return getRights((String)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRights(String lang) {
/* 296 */     return getLangAlt(lang, "rights");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(String value) {
/* 304 */     setValue("source", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSource() {
/* 312 */     return getValue("source");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSubject(String value) {
/* 321 */     addStringToBag("subject", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getSubjects() {
/* 329 */     return getStringArray("subject");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(String value) {
/* 337 */     setTitle((String)null, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(String lang, String value) {
/* 346 */     setLangAlt("title", lang, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 354 */     return getTitle((String)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle(String lang) {
/* 363 */     return getLangAlt(lang, "title");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String removeTitle(String lang) {
/* 372 */     return removeLangAlt(lang, "title");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addType(String value) {
/* 380 */     addStringToBag("type", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getTypes() {
/* 388 */     return getStringArray("type");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/DublinCoreAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */