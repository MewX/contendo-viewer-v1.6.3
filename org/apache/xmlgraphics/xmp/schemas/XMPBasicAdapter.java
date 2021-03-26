/*     */ package org.apache.xmlgraphics.xmp.schemas;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.apache.xmlgraphics.xmp.Metadata;
/*     */ import org.apache.xmlgraphics.xmp.PropertyAccess;
/*     */ import org.apache.xmlgraphics.xmp.XMPArrayType;
/*     */ import org.apache.xmlgraphics.xmp.XMPConstants;
/*     */ import org.apache.xmlgraphics.xmp.XMPProperty;
/*     */ import org.apache.xmlgraphics.xmp.XMPSchemaAdapter;
/*     */ import org.apache.xmlgraphics.xmp.XMPSchemaRegistry;
/*     */ import org.apache.xmlgraphics.xmp.XMPStructure;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMPBasicAdapter
/*     */   extends XMPSchemaAdapter
/*     */ {
/*     */   private static final String ADVISORY = "Advisory";
/*     */   private static final String BASE_URL = "BaseURL";
/*     */   private static final String CREATE_DATE = "CreateDate";
/*     */   private static final String CREATOR_TOOL = "CreatorTool";
/*     */   private static final String IDENTIFIER = "Identifier";
/*     */   private static final String LABEL = "Label";
/*     */   private static final String METADATA_DATE = "MetadataDate";
/*     */   private static final String MODIFY_DATE = "ModifyDate";
/*     */   private static final String NICKNAME = "Nickname";
/*     */   private static final String RATING = "Rating";
/*     */   private static final String THUMBNAILS = "Thumbnails";
/*     */   
/*     */   public XMPBasicAdapter(Metadata meta, String namespace) {
/*  55 */     super(meta, XMPSchemaRegistry.getInstance().getSchema(namespace));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseUrl(String value) {
/*  63 */     setValue("BaseURL", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBaseUrl() {
/*  71 */     return getValue("BaseURL");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreateDate(Date creationDate) {
/*  79 */     setDateValue("CreateDate", creationDate);
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getCreateDate() {
/*  84 */     return getDateValue("CreateDate");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreatorTool(String value) {
/*  92 */     setValue("CreatorTool", value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreatorTool() {
/*  97 */     return getValue("CreatorTool");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addIdentifier(String value) {
/* 105 */     addStringToBag("Identifier", value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIdentifier(String value, String qualifier) {
/* 115 */     PropertyAccess pa = findQualifiedStructure("Identifier", XMPBasicSchema.SCHEME_QUALIFIER, qualifier);
/*     */     
/* 117 */     if (pa != null) {
/* 118 */       pa.setProperty(new XMPProperty(XMPConstants.RDF_VALUE, value));
/*     */     } else {
/* 120 */       XMPStructure struct = new XMPStructure();
/* 121 */       struct.setProperty(new XMPProperty(XMPConstants.RDF_VALUE, value));
/* 122 */       struct.setProperty(new XMPProperty(XMPBasicSchema.SCHEME_QUALIFIER, qualifier));
/* 123 */       addObjectToArray("Identifier", struct, XMPArrayType.BAG);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getIdentifiers() {
/* 133 */     return getStringArray("Identifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIdentifier(String qualifier) {
/* 143 */     Object value = findQualifiedValue("Identifier", XMPBasicSchema.SCHEME_QUALIFIER, qualifier);
/* 144 */     return (value != null) ? value.toString() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModifyDate(Date modifyDate) {
/* 152 */     setDateValue("ModifyDate", modifyDate);
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getModifyDate() {
/* 157 */     return getDateValue("ModifyDate");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMetadataDate(Date metadataDate) {
/* 165 */     setDateValue("MetadataDate", metadataDate);
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getMetadataDate() {
/* 170 */     return getDateValue("MetadataDate");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/XMPBasicAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */