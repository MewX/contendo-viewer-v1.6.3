/*     */ package org.apache.pdfbox.pdmodel.common;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PDNameTreeNode<T extends COSObjectable>
/*     */   implements COSObjectable
/*     */ {
/*  44 */   private static final Log LOG = LogFactory.getLog(PDNameTreeNode.class);
/*     */ 
/*     */   
/*     */   private final COSDictionary node;
/*     */ 
/*     */   
/*     */   private PDNameTreeNode<T> parent;
/*     */ 
/*     */   
/*     */   protected PDNameTreeNode() {
/*  54 */     this.node = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PDNameTreeNode(COSDictionary dict) {
/*  64 */     this.node = dict;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  75 */     return this.node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNameTreeNode<T> getParent() {
/*  85 */     return this.parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParent(PDNameTreeNode<T> parentNode) {
/*  95 */     this.parent = parentNode;
/*  96 */     calculateLimits();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRootNode() {
/* 106 */     return (this.parent == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PDNameTreeNode<T>> getKids() {
/* 116 */     List<PDNameTreeNode<T>> retval = null;
/* 117 */     COSArray kids = (COSArray)this.node.getDictionaryObject(COSName.KIDS);
/* 118 */     if (kids != null) {
/*     */       
/* 120 */       List<PDNameTreeNode<T>> pdObjects = new ArrayList<PDNameTreeNode<T>>();
/* 121 */       for (int i = 0; i < kids.size(); i++)
/*     */       {
/* 123 */         pdObjects.add(createChildNode((COSDictionary)kids.getObject(i)));
/*     */       }
/* 125 */       retval = new COSArrayList<PDNameTreeNode<T>>(pdObjects, kids);
/*     */     } 
/*     */     
/* 128 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKids(List<? extends PDNameTreeNode<T>> kids) {
/* 138 */     if (kids != null && kids.size() > 0) {
/*     */       
/* 140 */       for (PDNameTreeNode<T> kidsNode : kids)
/*     */       {
/* 142 */         kidsNode.setParent(this);
/*     */       }
/* 144 */       this.node.setItem(COSName.KIDS, (COSBase)COSArrayList.converterToCOSArray(kids));
/*     */       
/* 146 */       if (isRootNode())
/*     */       {
/* 148 */         this.node.setItem(COSName.NAMES, null);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 154 */       this.node.setItem(COSName.KIDS, null);
/*     */       
/* 156 */       this.node.setItem(COSName.LIMITS, null);
/*     */     } 
/* 158 */     calculateLimits();
/*     */   }
/*     */ 
/*     */   
/*     */   private void calculateLimits() {
/* 163 */     if (isRootNode()) {
/*     */       
/* 165 */       this.node.setItem(COSName.LIMITS, null);
/*     */     }
/*     */     else {
/*     */       
/* 169 */       List<PDNameTreeNode<T>> kids = getKids();
/* 170 */       if (kids != null && kids.size() > 0) {
/*     */         
/* 172 */         PDNameTreeNode<T> firstKid = kids.get(0);
/* 173 */         PDNameTreeNode<T> lastKid = kids.get(kids.size() - 1);
/* 174 */         String lowerLimit = firstKid.getLowerLimit();
/* 175 */         setLowerLimit(lowerLimit);
/* 176 */         String upperLimit = lastKid.getUpperLimit();
/* 177 */         setUpperLimit(upperLimit);
/*     */       } else {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 183 */           Map<String, T> names = getNames();
/* 184 */           if (names != null && names.size() > 0)
/*     */           {
/* 186 */             Set<String> strings = names.keySet();
/* 187 */             String[] keys = strings.<String>toArray(new String[strings.size()]);
/* 188 */             String lowerLimit = keys[0];
/* 189 */             setLowerLimit(lowerLimit);
/* 190 */             String upperLimit = keys[keys.length - 1];
/* 191 */             setUpperLimit(upperLimit);
/*     */           }
/*     */           else
/*     */           {
/* 195 */             this.node.setItem(COSName.LIMITS, null);
/*     */           }
/*     */         
/* 198 */         } catch (IOException exception) {
/*     */           
/* 200 */           this.node.setItem(COSName.LIMITS, null);
/* 201 */           LOG.error("Error while calculating the Limits of a PageNameTreeNode:", exception);
/*     */         } 
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
/*     */   
/*     */   public T getValue(String name) throws IOException {
/*     */     COSObjectable cOSObjectable;
/* 216 */     T retval = null;
/* 217 */     Map<String, T> names = getNames();
/* 218 */     if (names != null) {
/*     */       
/* 220 */       cOSObjectable = (COSObjectable)names.get(name);
/*     */     }
/*     */     else {
/*     */       
/* 224 */       List<PDNameTreeNode<T>> kids = getKids();
/* 225 */       if (kids != null) {
/*     */         
/* 227 */         for (int i = 0; i < kids.size() && cOSObjectable == null; i++)
/*     */         {
/* 229 */           PDNameTreeNode<T> childNode = kids.get(i);
/* 230 */           String upperLimit = childNode.getUpperLimit();
/* 231 */           String lowerLimit = childNode.getLowerLimit();
/* 232 */           if (upperLimit == null || lowerLimit == null || upperLimit
/* 233 */             .compareTo(lowerLimit) < 0 || (lowerLimit
/* 234 */             .compareTo(name) <= 0 && upperLimit.compareTo(name) >= 0))
/*     */           {
/* 236 */             cOSObjectable = (COSObjectable)childNode.getValue(name);
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 242 */         LOG.warn("NameTreeNode does not have \"names\" nor \"kids\" objects.");
/*     */       } 
/*     */     } 
/* 245 */     return (T)cOSObjectable;
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
/*     */   public Map<String, T> getNames() throws IOException {
/* 260 */     COSArray namesArray = (COSArray)this.node.getDictionaryObject(COSName.NAMES);
/* 261 */     if (namesArray != null) {
/*     */       
/* 263 */       Map<String, T> names = new LinkedHashMap<String, T>();
/* 264 */       for (int i = 0; i < namesArray.size(); i += 2) {
/*     */         
/* 266 */         COSString key = (COSString)namesArray.getObject(i);
/* 267 */         COSBase cosValue = namesArray.getObject(i + 1);
/* 268 */         names.put(key.getString(), convertCOSToPD(cosValue));
/*     */       } 
/* 270 */       return Collections.unmodifiableMap(names);
/*     */     } 
/*     */ 
/*     */     
/* 274 */     return null;
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
/*     */   public void setNames(Map<String, T> names) {
/* 305 */     if (names == null) {
/*     */       
/* 307 */       this.node.setItem(COSName.NAMES, (COSObjectable)null);
/* 308 */       this.node.setItem(COSName.LIMITS, (COSObjectable)null);
/*     */     }
/*     */     else {
/*     */       
/* 312 */       COSArray array = new COSArray();
/* 313 */       List<String> keys = new ArrayList<String>(names.keySet());
/* 314 */       Collections.sort(keys);
/* 315 */       for (String key : keys) {
/*     */         
/* 317 */         array.add((COSBase)new COSString(key));
/* 318 */         array.add((COSObjectable)names.get(key));
/*     */       } 
/* 320 */       this.node.setItem(COSName.NAMES, (COSBase)array);
/* 321 */       calculateLimits();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUpperLimit() {
/* 332 */     String retval = null;
/* 333 */     COSArray arr = (COSArray)this.node.getDictionaryObject(COSName.LIMITS);
/* 334 */     if (arr != null)
/*     */     {
/* 336 */       retval = arr.getString(1);
/*     */     }
/* 338 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setUpperLimit(String upper) {
/* 348 */     COSArray arr = (COSArray)this.node.getDictionaryObject(COSName.LIMITS);
/* 349 */     if (arr == null) {
/*     */       
/* 351 */       arr = new COSArray();
/* 352 */       arr.add(null);
/* 353 */       arr.add(null);
/* 354 */       this.node.setItem(COSName.LIMITS, (COSBase)arr);
/*     */     } 
/* 356 */     arr.setString(1, upper);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLowerLimit() {
/* 366 */     String retval = null;
/* 367 */     COSArray arr = (COSArray)this.node.getDictionaryObject(COSName.LIMITS);
/* 368 */     if (arr != null)
/*     */     {
/* 370 */       retval = arr.getString(0);
/*     */     }
/* 372 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLowerLimit(String lower) {
/* 382 */     COSArray arr = (COSArray)this.node.getDictionaryObject(COSName.LIMITS);
/* 383 */     if (arr == null) {
/*     */       
/* 385 */       arr = new COSArray();
/* 386 */       arr.add(null);
/* 387 */       arr.add(null);
/* 388 */       this.node.setItem(COSName.LIMITS, (COSBase)arr);
/*     */     } 
/* 390 */     arr.setString(0, lower);
/*     */   }
/*     */   
/*     */   protected abstract T convertCOSToPD(COSBase paramCOSBase) throws IOException;
/*     */   
/*     */   protected abstract PDNameTreeNode<T> createChildNode(COSDictionary paramCOSDictionary);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/PDNameTreeNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */