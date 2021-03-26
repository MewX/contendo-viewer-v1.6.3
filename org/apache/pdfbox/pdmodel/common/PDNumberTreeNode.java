/*     */ package org.apache.pdfbox.pdmodel.common;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDNumberTreeNode
/*     */   implements COSObjectable
/*     */ {
/*  44 */   private static final Log LOG = LogFactory.getLog(PDNumberTreeNode.class);
/*     */   
/*     */   private final COSDictionary node;
/*  47 */   private Class<? extends COSObjectable> valueType = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNumberTreeNode(Class<? extends COSObjectable> valueClass) {
/*  56 */     this.node = new COSDictionary();
/*  57 */     this.valueType = valueClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDNumberTreeNode(COSDictionary dict, Class<? extends COSObjectable> valueClass) {
/*  68 */     this.node = dict;
/*  69 */     this.valueType = valueClass;
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
/*  80 */     return this.node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PDNumberTreeNode> getKids() {
/*  90 */     List<PDNumberTreeNode> retval = null;
/*  91 */     COSArray kids = (COSArray)this.node.getDictionaryObject(COSName.KIDS);
/*  92 */     if (kids != null) {
/*     */       
/*  94 */       List<PDNumberTreeNode> pdObjects = new ArrayList<PDNumberTreeNode>();
/*  95 */       for (int i = 0; i < kids.size(); i++)
/*     */       {
/*  97 */         pdObjects.add(createChildNode((COSDictionary)kids.getObject(i)));
/*     */       }
/*  99 */       retval = new COSArrayList<PDNumberTreeNode>(pdObjects, kids);
/*     */     } 
/*     */     
/* 102 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKids(List<? extends PDNumberTreeNode> kids) {
/* 112 */     if (kids != null && !kids.isEmpty()) {
/*     */       
/* 114 */       PDNumberTreeNode firstKid = kids.get(0);
/* 115 */       PDNumberTreeNode lastKid = kids.get(kids.size() - 1);
/* 116 */       Integer lowerLimit = firstKid.getLowerLimit();
/* 117 */       setLowerLimit(lowerLimit);
/* 118 */       Integer upperLimit = lastKid.getUpperLimit();
/* 119 */       setUpperLimit(upperLimit);
/*     */     }
/* 121 */     else if (this.node.getDictionaryObject(COSName.NUMS) == null) {
/*     */ 
/*     */       
/* 124 */       this.node.setItem(COSName.LIMITS, null);
/*     */     } 
/* 126 */     this.node.setItem(COSName.KIDS, (COSBase)COSArrayList.converterToCOSArray(kids));
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
/*     */   public Object getValue(Integer index) throws IOException {
/* 140 */     Map<Integer, COSObjectable> numbers = getNumbers();
/* 141 */     if (numbers != null)
/*     */     {
/* 143 */       return numbers.get(index);
/*     */     }
/* 145 */     Object retval = null;
/* 146 */     List<PDNumberTreeNode> kids = getKids();
/* 147 */     if (kids != null) {
/*     */       
/* 149 */       for (int i = 0; i < kids.size() && retval == null; i++)
/*     */       {
/* 151 */         PDNumberTreeNode childNode = kids.get(i);
/* 152 */         if (childNode.getLowerLimit().compareTo(index) <= 0 && childNode
/* 153 */           .getUpperLimit().compareTo(index) >= 0)
/*     */         {
/* 155 */           retval = childNode.getValue(index);
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 161 */       LOG.warn("NumberTreeNode does not have \"nums\" nor \"kids\" objects.");
/*     */     } 
/* 163 */     return retval;
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
/*     */   public Map<Integer, COSObjectable> getNumbers() throws IOException {
/* 177 */     Map<Integer, COSObjectable> indices = null;
/* 178 */     COSBase numBase = this.node.getDictionaryObject(COSName.NUMS);
/* 179 */     if (numBase instanceof COSArray) {
/*     */       
/* 181 */       COSArray numbersArray = (COSArray)numBase;
/* 182 */       indices = new HashMap<Integer, COSObjectable>();
/* 183 */       for (int i = 0; i < numbersArray.size(); i += 2) {
/*     */         
/* 185 */         COSBase base = numbersArray.getObject(i);
/* 186 */         if (!(base instanceof COSInteger)) {
/*     */           
/* 188 */           LOG.error("page labels ignored, index " + i + " should be a number, but is " + base);
/* 189 */           return null;
/*     */         } 
/* 191 */         COSInteger key = (COSInteger)base;
/* 192 */         COSBase cosValue = numbersArray.getObject(i + 1);
/* 193 */         indices.put(Integer.valueOf(key.intValue()), (cosValue == null) ? null : convertCOSToPD(cosValue));
/*     */       } 
/* 195 */       indices = Collections.unmodifiableMap(indices);
/*     */     } 
/* 197 */     return indices;
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
/*     */   protected COSObjectable convertCOSToPD(COSBase base) throws IOException {
/*     */     try {
/* 214 */       return this.valueType.getDeclaredConstructor(new Class[] { base.getClass() }).newInstance(new Object[] { base });
/*     */     }
/* 216 */     catch (Throwable t) {
/*     */       
/* 218 */       throw new IOException("Error while trying to create value in number tree:" + t.getMessage(), t);
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
/*     */   protected PDNumberTreeNode createChildNode(COSDictionary dic) {
/* 230 */     return new PDNumberTreeNode(dic, this.valueType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumbers(Map<Integer, ? extends COSObjectable> numbers) {
/* 241 */     if (numbers == null) {
/*     */       
/* 243 */       this.node.setItem(COSName.NUMS, (COSObjectable)null);
/* 244 */       this.node.setItem(COSName.LIMITS, (COSObjectable)null);
/*     */     }
/*     */     else {
/*     */       
/* 248 */       List<Integer> keys = new ArrayList<Integer>(numbers.keySet());
/* 249 */       Collections.sort(keys);
/* 250 */       COSArray array = new COSArray();
/* 251 */       for (Integer key : keys) {
/*     */         
/* 253 */         array.add((COSBase)COSInteger.get(key.intValue()));
/* 254 */         COSObjectable obj = numbers.get(key);
/* 255 */         array.add((obj == null) ? (COSObjectable)COSNull.NULL : obj);
/*     */       } 
/* 257 */       Integer lower = null;
/* 258 */       Integer upper = null;
/* 259 */       if (!keys.isEmpty()) {
/*     */         
/* 261 */         lower = keys.get(0);
/* 262 */         upper = keys.get(keys.size() - 1);
/*     */       } 
/* 264 */       setUpperLimit(upper);
/* 265 */       setLowerLimit(lower);
/* 266 */       this.node.setItem(COSName.NUMS, (COSBase)array);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getUpperLimit() {
/* 277 */     Integer retval = null;
/* 278 */     COSArray arr = (COSArray)this.node.getDictionaryObject(COSName.LIMITS);
/* 279 */     if (arr != null && arr.get(0) != null)
/*     */     {
/* 281 */       retval = Integer.valueOf(arr.getInt(1));
/*     */     }
/* 283 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setUpperLimit(Integer upper) {
/* 293 */     COSArray arr = (COSArray)this.node.getDictionaryObject(COSName.LIMITS);
/* 294 */     if (arr == null) {
/*     */       
/* 296 */       arr = new COSArray();
/* 297 */       arr.add(null);
/* 298 */       arr.add(null);
/* 299 */       this.node.setItem(COSName.LIMITS, (COSBase)arr);
/*     */     } 
/* 301 */     if (upper != null) {
/*     */       
/* 303 */       arr.setInt(1, upper.intValue());
/*     */     }
/*     */     else {
/*     */       
/* 307 */       arr.set(1, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getLowerLimit() {
/* 318 */     Integer retval = null;
/* 319 */     COSArray arr = (COSArray)this.node.getDictionaryObject(COSName.LIMITS);
/* 320 */     if (arr != null && arr.get(0) != null)
/*     */     {
/* 322 */       retval = Integer.valueOf(arr.getInt(0));
/*     */     }
/* 324 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLowerLimit(Integer lower) {
/* 334 */     COSArray arr = (COSArray)this.node.getDictionaryObject(COSName.LIMITS);
/* 335 */     if (arr == null) {
/*     */       
/* 337 */       arr = new COSArray();
/* 338 */       arr.add(null);
/* 339 */       arr.add(null);
/* 340 */       this.node.setItem(COSName.LIMITS, (COSBase)arr);
/*     */     } 
/* 342 */     if (lower != null) {
/*     */       
/* 344 */       arr.setInt(0, lower.intValue());
/*     */     }
/*     */     else {
/*     */       
/* 348 */       arr.set(0, null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/PDNumberTreeNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */