/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionFactory;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDAdditionalActions;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
/*     */ import org.apache.pdfbox.util.XMLUtil;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FDFField
/*     */   implements COSObjectable
/*     */ {
/*     */   private COSDictionary field;
/*     */   
/*     */   public FDFField() {
/*  55 */     this.field = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFField(COSDictionary f) {
/*  65 */     this.field = f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFField(Element fieldXML) throws IOException {
/*  76 */     this();
/*  77 */     setPartialFieldName(fieldXML.getAttribute("name"));
/*  78 */     NodeList nodeList = fieldXML.getChildNodes();
/*  79 */     List<FDFField> kids = new ArrayList<FDFField>();
/*  80 */     for (int i = 0; i < nodeList.getLength(); i++) {
/*     */       
/*  82 */       Node node = nodeList.item(i);
/*  83 */       if (node instanceof Element) {
/*     */         
/*  85 */         Element child = (Element)node;
/*  86 */         if (child.getTagName().equals("value")) {
/*     */           
/*  88 */           setValue(XMLUtil.getNodeValue(child));
/*     */         }
/*  90 */         else if (child.getTagName().equals("value-richtext")) {
/*     */           
/*  92 */           setRichText(new COSString(XMLUtil.getNodeValue(child)));
/*     */         }
/*  94 */         else if (child.getTagName().equals("field")) {
/*     */           
/*  96 */           kids.add(new FDFField(child));
/*     */         } 
/*     */       } 
/*     */     } 
/* 100 */     if (kids.size() > 0)
/*     */     {
/* 102 */       setKids(kids);
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
/*     */ 
/*     */   
/*     */   public void writeXML(Writer output) throws IOException {
/* 116 */     output.write("<field name=\"" + getPartialFieldName() + "\">\n");
/* 117 */     Object value = getValue();
/*     */     
/* 119 */     if (value instanceof String) {
/*     */       
/* 121 */       output.write("<value>" + escapeXML((String)value) + "</value>\n");
/*     */     }
/* 123 */     else if (value instanceof List) {
/*     */       
/* 125 */       List<String> items = (List<String>)value;
/* 126 */       for (String item : items)
/*     */       {
/* 128 */         output.write("<value>" + escapeXML(item) + "</value>\n");
/*     */       }
/*     */     } 
/*     */     
/* 132 */     String rt = getRichText();
/* 133 */     if (rt != null)
/*     */     {
/* 135 */       output.write("<value-richtext>" + escapeXML(rt) + "</value-richtext>\n");
/*     */     }
/* 137 */     List<FDFField> kids = getKids();
/* 138 */     if (kids != null)
/*     */     {
/* 140 */       for (FDFField kid : kids)
/*     */       {
/* 142 */         kid.writeXML(output);
/*     */       }
/*     */     }
/* 145 */     output.write("</field>\n");
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
/* 156 */     return this.field;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<FDFField> getKids() {
/*     */     COSArrayList cOSArrayList;
/* 167 */     COSArray kids = (COSArray)this.field.getDictionaryObject(COSName.KIDS);
/* 168 */     List<FDFField> retval = null;
/* 169 */     if (kids != null) {
/*     */       
/* 171 */       List<FDFField> actuals = new ArrayList<FDFField>();
/* 172 */       for (int i = 0; i < kids.size(); i++)
/*     */       {
/* 174 */         actuals.add(new FDFField((COSDictionary)kids.getObject(i)));
/*     */       }
/* 176 */       cOSArrayList = new COSArrayList(actuals, kids);
/*     */     } 
/* 178 */     return (List<FDFField>)cOSArrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKids(List<FDFField> kids) {
/* 188 */     this.field.setItem(COSName.KIDS, (COSBase)COSArrayList.converterToCOSArray(kids));
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
/*     */   public String getPartialFieldName() {
/* 201 */     return this.field.getString(COSName.T);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPartialFieldName(String partial) {
/* 211 */     this.field.setString(COSName.T, partial);
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
/*     */   public Object getValue() throws IOException {
/* 224 */     COSBase value = this.field.getDictionaryObject(COSName.V);
/* 225 */     if (value instanceof COSName)
/*     */     {
/* 227 */       return ((COSName)value).getName();
/*     */     }
/* 229 */     if (value instanceof COSArray)
/*     */     {
/* 231 */       return COSArrayList.convertCOSStringCOSArrayToList((COSArray)value);
/*     */     }
/* 233 */     if (value instanceof COSString)
/*     */     {
/* 235 */       return ((COSString)value).getString();
/*     */     }
/* 237 */     if (value instanceof COSStream)
/*     */     {
/* 239 */       return ((COSStream)value).toTextString();
/*     */     }
/* 241 */     if (value != null)
/*     */     {
/* 243 */       throw new IOException("Error:Unknown type for field import" + value);
/*     */     }
/*     */ 
/*     */     
/* 247 */     return null;
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
/*     */   public COSBase getCOSValue() throws IOException {
/* 259 */     COSBase value = this.field.getDictionaryObject(COSName.V);
/*     */     
/* 261 */     if (value instanceof COSName)
/*     */     {
/* 263 */       return value;
/*     */     }
/* 265 */     if (value instanceof COSArray)
/*     */     {
/* 267 */       return value;
/*     */     }
/* 269 */     if (value instanceof COSString || value instanceof COSStream)
/*     */     {
/* 271 */       return value;
/*     */     }
/* 273 */     if (value != null)
/*     */     {
/* 275 */       throw new IOException("Error:Unknown type for field import" + value);
/*     */     }
/*     */ 
/*     */     
/* 279 */     return null;
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
/*     */   public void setValue(Object value) throws IOException {
/* 292 */     COSBase cos = null;
/* 293 */     if (value instanceof List) {
/*     */       
/* 295 */       COSArray cOSArray = COSArrayList.convertStringListToCOSStringCOSArray((List)value);
/*     */     }
/* 297 */     else if (value instanceof String) {
/*     */       
/* 299 */       COSString cOSString = new COSString((String)value);
/*     */     }
/* 301 */     else if (value instanceof COSObjectable) {
/*     */       
/* 303 */       cos = ((COSObjectable)value).getCOSObject();
/*     */     }
/* 305 */     else if (value != null) {
/*     */       
/* 307 */       throw new IOException("Error:Unknown type for field import" + value);
/*     */     } 
/* 309 */     this.field.setItem(COSName.V, cos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(COSBase value) {
/* 319 */     this.field.setItem(COSName.V, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getFieldFlags() {
/* 329 */     Integer retval = null;
/* 330 */     COSNumber ff = (COSNumber)this.field.getDictionaryObject(COSName.FF);
/* 331 */     if (ff != null)
/*     */     {
/* 333 */       retval = Integer.valueOf(ff.intValue());
/*     */     }
/* 335 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFieldFlags(Integer ff) {
/* 345 */     COSInteger value = null;
/* 346 */     if (ff != null)
/*     */     {
/* 348 */       value = COSInteger.get(ff.intValue());
/*     */     }
/* 350 */     this.field.setItem(COSName.FF, (COSBase)value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFieldFlags(int ff) {
/* 360 */     this.field.setInt(COSName.FF, ff);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getSetFieldFlags() {
/* 370 */     Integer retval = null;
/* 371 */     COSNumber ff = (COSNumber)this.field.getDictionaryObject(COSName.SET_FF);
/* 372 */     if (ff != null)
/*     */     {
/* 374 */       retval = Integer.valueOf(ff.intValue());
/*     */     }
/* 376 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSetFieldFlags(Integer ff) {
/* 386 */     COSInteger value = null;
/* 387 */     if (ff != null)
/*     */     {
/* 389 */       value = COSInteger.get(ff.intValue());
/*     */     }
/* 391 */     this.field.setItem(COSName.SET_FF, (COSBase)value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSetFieldFlags(int ff) {
/* 401 */     this.field.setInt(COSName.SET_FF, ff);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getClearFieldFlags() {
/* 411 */     Integer retval = null;
/* 412 */     COSNumber ff = (COSNumber)this.field.getDictionaryObject(COSName.CLR_FF);
/* 413 */     if (ff != null)
/*     */     {
/* 415 */       retval = Integer.valueOf(ff.intValue());
/*     */     }
/* 417 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClearFieldFlags(Integer ff) {
/* 427 */     COSInteger value = null;
/* 428 */     if (ff != null)
/*     */     {
/* 430 */       value = COSInteger.get(ff.intValue());
/*     */     }
/* 432 */     this.field.setItem(COSName.CLR_FF, (COSBase)value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClearFieldFlags(int ff) {
/* 442 */     this.field.setInt(COSName.CLR_FF, ff);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getWidgetFieldFlags() {
/* 452 */     Integer retval = null;
/* 453 */     COSNumber f = (COSNumber)this.field.getDictionaryObject("F");
/* 454 */     if (f != null)
/*     */     {
/* 456 */       retval = Integer.valueOf(f.intValue());
/*     */     }
/* 458 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWidgetFieldFlags(Integer f) {
/* 469 */     COSInteger value = null;
/* 470 */     if (f != null)
/*     */     {
/* 472 */       value = COSInteger.get(f.intValue());
/*     */     }
/* 474 */     this.field.setItem(COSName.F, (COSBase)value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWidgetFieldFlags(int f) {
/* 484 */     this.field.setInt(COSName.F, f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getSetWidgetFieldFlags() {
/* 494 */     Integer retval = null;
/* 495 */     COSNumber ff = (COSNumber)this.field.getDictionaryObject(COSName.SET_F);
/* 496 */     if (ff != null)
/*     */     {
/* 498 */       retval = Integer.valueOf(ff.intValue());
/*     */     }
/* 500 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSetWidgetFieldFlags(Integer ff) {
/* 511 */     COSInteger value = null;
/* 512 */     if (ff != null)
/*     */     {
/* 514 */       value = COSInteger.get(ff.intValue());
/*     */     }
/* 516 */     this.field.setItem(COSName.SET_F, (COSBase)value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSetWidgetFieldFlags(int ff) {
/* 527 */     this.field.setInt(COSName.SET_F, ff);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getClearWidgetFieldFlags() {
/* 537 */     Integer retval = null;
/* 538 */     COSNumber ff = (COSNumber)this.field.getDictionaryObject(COSName.CLR_F);
/* 539 */     if (ff != null)
/*     */     {
/* 541 */       retval = Integer.valueOf(ff.intValue());
/*     */     }
/* 543 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClearWidgetFieldFlags(Integer ff) {
/* 553 */     COSInteger value = null;
/* 554 */     if (ff != null)
/*     */     {
/* 556 */       value = COSInteger.get(ff.intValue());
/*     */     }
/* 558 */     this.field.setItem(COSName.CLR_F, (COSBase)value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClearWidgetFieldFlags(int ff) {
/* 568 */     this.field.setInt(COSName.CLR_F, ff);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAppearanceDictionary getAppearanceDictionary() {
/* 578 */     PDAppearanceDictionary retval = null;
/* 579 */     COSDictionary dict = (COSDictionary)this.field.getDictionaryObject(COSName.AP);
/* 580 */     if (dict != null)
/*     */     {
/* 582 */       retval = new PDAppearanceDictionary(dict);
/*     */     }
/* 584 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAppearanceDictionary(PDAppearanceDictionary ap) {
/* 594 */     this.field.setItem(COSName.AP, (COSObjectable)ap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFNamedPageReference getAppearanceStreamReference() {
/* 604 */     FDFNamedPageReference retval = null;
/* 605 */     COSDictionary ref = (COSDictionary)this.field.getDictionaryObject(COSName.AP_REF);
/* 606 */     if (ref != null)
/*     */     {
/* 608 */       retval = new FDFNamedPageReference(ref);
/*     */     }
/* 610 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAppearanceStreamReference(FDFNamedPageReference ref) {
/* 620 */     this.field.setItem(COSName.AP_REF, ref);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFIconFit getIconFit() {
/* 630 */     FDFIconFit retval = null;
/* 631 */     COSDictionary dic = (COSDictionary)this.field.getDictionaryObject(COSName.IF);
/* 632 */     if (dic != null)
/*     */     {
/* 634 */       retval = new FDFIconFit(dic);
/*     */     }
/* 636 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIconFit(FDFIconFit fit) {
/* 646 */     this.field.setItem(COSName.IF, fit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Object> getOptions() {
/*     */     COSArrayList cOSArrayList;
/* 657 */     List<Object> retval = null;
/* 658 */     COSArray array = (COSArray)this.field.getDictionaryObject(COSName.OPT);
/* 659 */     if (array != null) {
/*     */       
/* 661 */       List<Object> objects = new ArrayList();
/* 662 */       for (int i = 0; i < array.size(); i++) {
/*     */         
/* 664 */         COSBase next = array.getObject(i);
/* 665 */         if (next instanceof COSString) {
/*     */           
/* 667 */           objects.add(((COSString)next).getString());
/*     */         }
/*     */         else {
/*     */           
/* 671 */           COSArray value = (COSArray)next;
/* 672 */           objects.add(new FDFOptionElement(value));
/*     */         } 
/*     */       } 
/* 675 */       cOSArrayList = new COSArrayList(objects, array);
/*     */     } 
/* 677 */     return (List<Object>)cOSArrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(List<Object> options) {
/* 688 */     COSArray value = COSArrayList.converterToCOSArray(options);
/* 689 */     this.field.setItem(COSName.OPT, (COSBase)value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction getAction() {
/* 699 */     return PDActionFactory.createAction((COSDictionary)this.field.getDictionaryObject(COSName.A));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAction(PDAction a) {
/* 709 */     this.field.setItem(COSName.A, (COSObjectable)a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAdditionalActions getAdditionalActions() {
/* 719 */     PDAdditionalActions retval = null;
/* 720 */     COSDictionary dict = (COSDictionary)this.field.getDictionaryObject(COSName.AA);
/* 721 */     if (dict != null)
/*     */     {
/* 723 */       retval = new PDAdditionalActions(dict);
/*     */     }
/*     */     
/* 726 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAdditionalActions(PDAdditionalActions aa) {
/* 736 */     this.field.setItem(COSName.AA, (COSObjectable)aa);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRichText() {
/* 746 */     COSBase rv = this.field.getDictionaryObject(COSName.RV);
/* 747 */     if (rv == null)
/*     */     {
/* 749 */       return null;
/*     */     }
/* 751 */     if (rv instanceof COSString)
/*     */     {
/* 753 */       return ((COSString)rv).getString();
/*     */     }
/*     */ 
/*     */     
/* 757 */     return ((COSStream)rv).toTextString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRichText(COSString rv) {
/* 768 */     this.field.setItem(COSName.RV, (COSBase)rv);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRichText(COSStream rv) {
/* 778 */     this.field.setItem(COSName.RV, (COSBase)rv);
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
/*     */   private String escapeXML(String input) {
/* 790 */     StringBuilder escapedXML = new StringBuilder();
/* 791 */     for (int i = 0; i < input.length(); i++) {
/*     */       
/* 793 */       char c = input.charAt(i);
/* 794 */       switch (c) {
/*     */         
/*     */         case '<':
/* 797 */           escapedXML.append("&lt;");
/*     */           break;
/*     */         case '>':
/* 800 */           escapedXML.append("&gt;");
/*     */           break;
/*     */         case '"':
/* 803 */           escapedXML.append("&quot;");
/*     */           break;
/*     */         case '&':
/* 806 */           escapedXML.append("&amp;");
/*     */           break;
/*     */         case '\'':
/* 809 */           escapedXML.append("&apos;");
/*     */           break;
/*     */         default:
/* 812 */           if (c > '~') {
/*     */             
/* 814 */             escapedXML.append("&#").append(c).append(";");
/*     */             
/*     */             break;
/*     */           } 
/* 818 */           escapedXML.append(c);
/*     */           break;
/*     */       } 
/*     */     } 
/* 822 */     return escapedXML.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */