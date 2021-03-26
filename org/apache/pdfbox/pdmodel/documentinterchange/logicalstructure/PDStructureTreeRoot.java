/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDStructureElementNameTreeNode;
/*     */ import org.apache.pdfbox.pdmodel.common.COSDictionaryMap;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
/*     */ import org.apache.pdfbox.pdmodel.common.PDNumberTreeNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDStructureTreeRoot
/*     */   extends PDStructureNode
/*     */ {
/*  46 */   private static final Log LOG = LogFactory.getLog(PDStructureTreeRoot.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String TYPE = "StructTreeRoot";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStructureTreeRoot() {
/*  56 */     super("StructTreeRoot");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStructureTreeRoot(COSDictionary dic) {
/*  66 */     super(dic);
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
/*     */   @Deprecated
/*     */   public COSArray getKArray() {
/*  81 */     COSBase k = getCOSObject().getDictionaryObject(COSName.K);
/*  82 */     if (k instanceof COSDictionary) {
/*     */       
/*  84 */       COSDictionary kdict = (COSDictionary)k;
/*  85 */       k = kdict.getDictionaryObject(COSName.K);
/*  86 */       if (k instanceof COSArray)
/*     */       {
/*  88 */         return (COSArray)k;
/*     */       }
/*     */     }
/*  91 */     else if (k instanceof COSArray) {
/*     */       
/*  93 */       return (COSArray)k;
/*     */     } 
/*     */     
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getK() {
/* 107 */     return getCOSObject().getDictionaryObject(COSName.K);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setK(COSBase k) {
/* 117 */     getCOSObject().setItem(COSName.K, k);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNameTreeNode<PDStructureElement> getIDTree() {
/* 127 */     COSBase base = getCOSObject().getDictionaryObject(COSName.ID_TREE);
/* 128 */     if (base instanceof COSDictionary)
/*     */     {
/* 130 */       return (PDNameTreeNode<PDStructureElement>)new PDStructureElementNameTreeNode((COSDictionary)base);
/*     */     }
/* 132 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIDTree(PDNameTreeNode<PDStructureElement> idTree) {
/* 142 */     getCOSObject().setItem(COSName.ID_TREE, (COSObjectable)idTree);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNumberTreeNode getParentTree() {
/* 152 */     COSBase base = getCOSObject().getDictionaryObject(COSName.PARENT_TREE);
/* 153 */     if (base instanceof COSDictionary)
/*     */     {
/* 155 */       return new PDNumberTreeNode((COSDictionary)base, PDParentTreeValue.class);
/*     */     }
/* 157 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParentTree(PDNumberTreeNode parentTree) {
/* 167 */     getCOSObject().setItem(COSName.PARENT_TREE, (COSObjectable)parentTree);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParentTreeNextKey() {
/* 177 */     return getCOSObject().getInt(COSName.PARENT_TREE_NEXT_KEY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParentTreeNextKey(int parentTreeNextkey) {
/* 187 */     getCOSObject().setInt(COSName.PARENT_TREE_NEXT_KEY, parentTreeNextkey);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getRoleMap() {
/* 197 */     COSBase rm = getCOSObject().getDictionaryObject(COSName.ROLE_MAP);
/* 198 */     if (rm instanceof COSDictionary) {
/*     */       
/*     */       try {
/*     */         
/* 202 */         return (Map<String, Object>)COSDictionaryMap.convertBasicTypesToMap((COSDictionary)rm);
/*     */       }
/* 204 */       catch (IOException e) {
/*     */         
/* 206 */         LOG.error(e, e);
/*     */       } 
/*     */     }
/* 209 */     return new Hashtable<String, Object>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRoleMap(Map<String, String> roleMap) {
/* 219 */     COSDictionary rmDic = new COSDictionary();
/* 220 */     for (Map.Entry<String, String> entry : roleMap.entrySet())
/*     */     {
/* 222 */       rmDic.setName(entry.getKey(), entry.getValue());
/*     */     }
/* 224 */     getCOSObject().setItem(COSName.ROLE_MAP, (COSBase)rmDic);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/logicalstructure/PDStructureTreeRoot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */