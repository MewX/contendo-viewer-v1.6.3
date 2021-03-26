package org.apache.xerces.xpointer;

import java.util.HashMap;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLErrorHandler;

final class ElementSchemePointer implements XPointerPart {
  private String fSchemeName;
  
  private String fSchemeData;
  
  private String fShortHandPointerName;
  
  private boolean fIsResolveElement = false;
  
  private boolean fIsElementFound = false;
  
  private boolean fWasOnlyEmptyElementFound = false;
  
  boolean fIsShortHand = false;
  
  int fFoundDepth = 0;
  
  private int[] fChildSequence;
  
  private int fCurrentChildPosition = 1;
  
  private int fCurrentChildDepth = 0;
  
  private int[] fCurrentChildSequence;
  
  private boolean fIsFragmentResolved = false;
  
  private ShortHandPointer fShortHandPointer;
  
  protected XMLErrorReporter fErrorReporter;
  
  protected XMLErrorHandler fErrorHandler;
  
  private SymbolTable fSymbolTable;
  
  public ElementSchemePointer() {}
  
  public ElementSchemePointer(SymbolTable paramSymbolTable) {
    this.fSymbolTable = paramSymbolTable;
  }
  
  public ElementSchemePointer(SymbolTable paramSymbolTable, XMLErrorReporter paramXMLErrorReporter) {
    this.fSymbolTable = paramSymbolTable;
    this.fErrorReporter = paramXMLErrorReporter;
  }
  
  public void parseXPointer(String paramString) throws XNIException {
    init();
    Tokens tokens = new Tokens(this.fSymbolTable);
    Scanner scanner = new Scanner(this, this.fSymbolTable) {
        private final ElementSchemePointer this$0;
        
        protected void addToken(ElementSchemePointer.Tokens param1Tokens, int param1Int) throws XNIException {
          if (param1Int == 1 || param1Int == 0) {
            super.addToken(param1Tokens, param1Int);
            return;
          } 
          this.this$0.reportError("InvalidElementSchemeToken", new Object[] { ElementSchemePointer.Tokens.access$200(param1Tokens, param1Int) });
        }
      };
    int i = paramString.length();
    boolean bool = scanner.scanExpr(this.fSymbolTable, tokens, paramString, 0, i);
    if (!bool)
      reportError("InvalidElementSchemeXPointer", new Object[] { paramString }); 
    int[] arrayOfInt = new int[tokens.getTokenCount() / 2 + 1];
    byte b = 0;
    while (tokens.hasMore()) {
      int j = tokens.nextToken();
      switch (j) {
        case 0:
          j = tokens.nextToken();
          this.fShortHandPointerName = tokens.getTokenString(j);
          this.fShortHandPointer = new ShortHandPointer(this.fSymbolTable);
          this.fShortHandPointer.setSchemeName(this.fShortHandPointerName);
          continue;
        case 1:
          arrayOfInt[b] = tokens.nextToken();
          b++;
          continue;
      } 
      reportError("InvalidElementSchemeXPointer", new Object[] { paramString });
    } 
    this.fChildSequence = new int[b];
    this.fCurrentChildSequence = new int[b];
    System.arraycopy(arrayOfInt, 0, this.fChildSequence, 0, b);
  }
  
  public String getSchemeName() {
    return this.fSchemeName;
  }
  
  public String getSchemeData() {
    return this.fSchemeData;
  }
  
  public void setSchemeName(String paramString) {
    this.fSchemeName = paramString;
  }
  
  public void setSchemeData(String paramString) {
    this.fSchemeData = paramString;
  }
  
  public boolean resolveXPointer(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations, int paramInt) throws XNIException {
    boolean bool = false;
    if (this.fShortHandPointerName != null) {
      bool = this.fShortHandPointer.resolveXPointer(paramQName, paramXMLAttributes, paramAugmentations, paramInt);
      if (bool) {
        this.fIsResolveElement = true;
        this.fIsShortHand = true;
      } else {
        this.fIsResolveElement = false;
      } 
    } else {
      this.fIsResolveElement = true;
    } 
    if (this.fChildSequence.length > 0) {
      this.fIsFragmentResolved = matchChildSequence(paramQName, paramInt);
    } else if (bool && this.fChildSequence.length <= 0) {
      this.fIsFragmentResolved = bool;
    } else {
      this.fIsFragmentResolved = false;
    } 
    return this.fIsFragmentResolved;
  }
  
  protected boolean matchChildSequence(QName paramQName, int paramInt) throws XNIException {
    if (this.fCurrentChildDepth >= this.fCurrentChildSequence.length) {
      int[] arrayOfInt = new int[this.fCurrentChildSequence.length];
      System.arraycopy(this.fCurrentChildSequence, 0, arrayOfInt, 0, this.fCurrentChildSequence.length);
      this.fCurrentChildSequence = new int[this.fCurrentChildDepth * 2];
      System.arraycopy(arrayOfInt, 0, this.fCurrentChildSequence, 0, arrayOfInt.length);
    } 
    if (this.fIsResolveElement)
      if (paramInt == 0) {
        this.fCurrentChildSequence[this.fCurrentChildDepth] = this.fCurrentChildPosition;
        this.fCurrentChildDepth++;
        this.fCurrentChildPosition = 1;
        if (this.fCurrentChildDepth <= this.fFoundDepth || this.fFoundDepth == 0)
          if (checkMatch()) {
            this.fIsElementFound = true;
            this.fFoundDepth = this.fCurrentChildDepth;
          } else {
            this.fIsElementFound = false;
            this.fFoundDepth = 0;
          }  
      } else if (paramInt == 1) {
        if (this.fCurrentChildDepth == this.fFoundDepth) {
          this.fIsElementFound = true;
        } else if ((this.fCurrentChildDepth < this.fFoundDepth && this.fFoundDepth != 0) || (this.fCurrentChildDepth > this.fFoundDepth && this.fFoundDepth == 0)) {
          this.fIsElementFound = false;
        } 
        this.fCurrentChildSequence[this.fCurrentChildDepth] = 0;
        this.fCurrentChildDepth--;
        this.fCurrentChildPosition = this.fCurrentChildSequence[this.fCurrentChildDepth] + 1;
      } else if (paramInt == 2) {
        this.fCurrentChildSequence[this.fCurrentChildDepth] = this.fCurrentChildPosition;
        this.fCurrentChildPosition++;
        if (checkMatch()) {
          if (!this.fIsElementFound) {
            this.fWasOnlyEmptyElementFound = true;
          } else {
            this.fWasOnlyEmptyElementFound = false;
          } 
          this.fIsElementFound = true;
        } else {
          this.fIsElementFound = false;
          this.fWasOnlyEmptyElementFound = false;
        } 
      }  
    return this.fIsElementFound;
  }
  
  protected boolean checkMatch() {
    if (!this.fIsShortHand) {
      if (this.fChildSequence.length <= this.fCurrentChildDepth + 1) {
        for (byte b = 0; b < this.fChildSequence.length; b++) {
          if (this.fChildSequence[b] != this.fCurrentChildSequence[b])
            return false; 
        } 
      } else {
        return false;
      } 
    } else if (this.fChildSequence.length <= this.fCurrentChildDepth + 1) {
      for (byte b = 0; b < this.fChildSequence.length; b++) {
        if (this.fCurrentChildSequence.length < b + 2)
          return false; 
        if (this.fChildSequence[b] != this.fCurrentChildSequence[b + 1])
          return false; 
      } 
    } else {
      return false;
    } 
    return true;
  }
  
  public boolean isFragmentResolved() throws XNIException {
    return this.fIsFragmentResolved;
  }
  
  public boolean isChildFragmentResolved() {
    return (this.fIsShortHand && this.fShortHandPointer != null && this.fChildSequence.length <= 0) ? this.fShortHandPointer.isChildFragmentResolved() : (this.fWasOnlyEmptyElementFound ? (!this.fWasOnlyEmptyElementFound) : ((this.fIsFragmentResolved && this.fCurrentChildDepth >= this.fFoundDepth)));
  }
  
  protected void reportError(String paramString, Object[] paramArrayOfObject) throws XNIException {
    throw new XNIException(this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/XPTR").formatMessage(this.fErrorReporter.getLocale(), paramString, paramArrayOfObject));
  }
  
  protected void initErrorReporter() {
    if (this.fErrorReporter == null)
      this.fErrorReporter = new XMLErrorReporter(); 
    if (this.fErrorHandler == null)
      this.fErrorHandler = new XPointerErrorHandler(); 
    this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/XPTR", new XPointerMessageFormatter());
  }
  
  protected void init() {
    this.fSchemeName = null;
    this.fSchemeData = null;
    this.fShortHandPointerName = null;
    this.fIsResolveElement = false;
    this.fIsElementFound = false;
    this.fWasOnlyEmptyElementFound = false;
    this.fFoundDepth = 0;
    this.fCurrentChildPosition = 1;
    this.fCurrentChildDepth = 0;
    this.fIsFragmentResolved = false;
    this.fShortHandPointer = null;
    initErrorReporter();
  }
  
  private class Scanner {
    private static final byte CHARTYPE_INVALID = 0;
    
    private static final byte CHARTYPE_OTHER = 1;
    
    private static final byte CHARTYPE_MINUS = 2;
    
    private static final byte CHARTYPE_PERIOD = 3;
    
    private static final byte CHARTYPE_SLASH = 4;
    
    private static final byte CHARTYPE_DIGIT = 5;
    
    private static final byte CHARTYPE_LETTER = 6;
    
    private static final byte CHARTYPE_UNDERSCORE = 7;
    
    private static final byte CHARTYPE_NONASCII = 8;
    
    private final byte[] fASCIICharMap;
    
    private SymbolTable fSymbolTable;
    
    private final ElementSchemePointer this$0;
    
    private Scanner(ElementSchemePointer this$0, SymbolTable param1SymbolTable) {
      ElementSchemePointer.this = ElementSchemePointer.this;
      this.fASCIICharMap = new byte[] { 
          0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 
          1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
          0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 
          1, 1, 1, 1, 1, 2, 2, 4, 5, 5, 
          5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 
          1, 1, 1, 1, 1, 6, 6, 6, 6, 6, 
          6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
          6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
          6, 1, 1, 1, 1, 7, 1, 6, 6, 6, 
          6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
          6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
          6, 6, 6, 1, 1, 1, 1, 1 };
      this.fSymbolTable = param1SymbolTable;
    }
    
    private boolean scanExpr(SymbolTable param1SymbolTable, ElementSchemePointer.Tokens param1Tokens, String param1String, int param1Int1, int param1Int2) throws XNIException {
      String str = null;
      while (param1Int1 != param1Int2) {
        int i;
        int j;
        char c = param1String.charAt(param1Int1);
        boolean bool = (c >= '') ? true : this.fASCIICharMap[c];
        switch (bool) {
          case true:
            if (++param1Int1 == param1Int2)
              return false; 
            addToken(param1Tokens, 1);
            c = param1String.charAt(param1Int1);
            j = 0;
            while (c >= '0' && c <= '9') {
              j = j * 10 + c - 48;
              if (++param1Int1 == param1Int2)
                break; 
              c = param1String.charAt(param1Int1);
            } 
            if (j == 0) {
              ElementSchemePointer.this.reportError("InvalidChildSequenceCharacter", new Object[] { new Character((char)c) });
              return false;
            } 
            param1Tokens.addToken(j);
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
            i = param1Int1;
            param1Int1 = scanNCName(param1String, param1Int2, param1Int1);
            if (param1Int1 == i) {
              ElementSchemePointer.this.reportError("InvalidNCNameInElementSchemeData", new Object[] { param1String });
              return false;
            } 
            if (param1Int1 < param1Int2) {
              c = param1String.charAt(param1Int1);
            } else {
              byte b = -1;
            } 
            str = param1SymbolTable.addSymbol(param1String.substring(i, param1Int1));
            addToken(param1Tokens, 0);
            param1Tokens.addToken(str);
        } 
      } 
      return true;
    }
    
    private int scanNCName(String param1String, int param1Int1, int param1Int2) {
      char c = param1String.charAt(param1Int2);
      if (c >= '') {
        if (!XMLChar.isNameStart(c))
          return param1Int2; 
      } else {
        byte b = this.fASCIICharMap[c];
        if (b != 6 && b != 7)
          return param1Int2; 
      } 
      while (++param1Int2 < param1Int1) {
        c = param1String.charAt(param1Int2);
        if (c >= '') {
          if (!XMLChar.isName(c))
            break; 
          continue;
        } 
        byte b = this.fASCIICharMap[c];
        if (b != 6 && b != 5 && b != 3 && b != 2 && b != 7)
          break; 
      } 
      return param1Int2;
    }
    
    protected void addToken(ElementSchemePointer.Tokens param1Tokens, int param1Int) throws XNIException {
      param1Tokens.addToken(param1Int);
    }
  }
  
  private final class Tokens {
    private static final int XPTRTOKEN_ELEM_NCNAME = 0;
    
    private static final int XPTRTOKEN_ELEM_CHILD = 1;
    
    private final String[] fgTokenNames;
    
    private static final int INITIAL_TOKEN_COUNT = 256;
    
    private int[] fTokens;
    
    private int fTokenCount;
    
    private int fCurrentTokenIndex;
    
    private SymbolTable fSymbolTable;
    
    private HashMap fTokenNames;
    
    private final ElementSchemePointer this$0;
    
    private Tokens(ElementSchemePointer this$0, SymbolTable param1SymbolTable) {
      ElementSchemePointer.this = ElementSchemePointer.this;
      this.fgTokenNames = new String[] { "XPTRTOKEN_ELEM_NCNAME", "XPTRTOKEN_ELEM_CHILD" };
      this.fTokens = new int[256];
      this.fTokenCount = 0;
      this.fTokenNames = new HashMap();
      this.fSymbolTable = param1SymbolTable;
      this.fTokenNames.put(new Integer(0), "XPTRTOKEN_ELEM_NCNAME");
      this.fTokenNames.put(new Integer(1), "XPTRTOKEN_ELEM_CHILD");
    }
    
    private String getTokenString(int param1Int) {
      return (String)this.fTokenNames.get(new Integer(param1Int));
    }
    
    private Integer getToken(int param1Int) {
      return (Integer)this.fTokenNames.get(new Integer(param1Int));
    }
    
    private void addToken(String param1String) {
      Integer integer = (Integer)this.fTokenNames.get(param1String);
      if (integer == null) {
        integer = new Integer(this.fTokenNames.size());
        this.fTokenNames.put(integer, param1String);
      } 
      addToken(integer.intValue());
    }
    
    private void addToken(int param1Int) {
      try {
        this.fTokens[this.fTokenCount] = param1Int;
      } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        int[] arrayOfInt = this.fTokens;
        this.fTokens = new int[this.fTokenCount << 1];
        System.arraycopy(arrayOfInt, 0, this.fTokens, 0, this.fTokenCount);
        this.fTokens[this.fTokenCount] = param1Int;
      } 
      this.fTokenCount++;
    }
    
    private void rewind() {
      this.fCurrentTokenIndex = 0;
    }
    
    private boolean hasMore() {
      return (this.fCurrentTokenIndex < this.fTokenCount);
    }
    
    private int nextToken() throws XNIException {
      if (this.fCurrentTokenIndex == this.fTokenCount)
        ElementSchemePointer.this.reportError("XPointerElementSchemeProcessingError", null); 
      return this.fTokens[this.fCurrentTokenIndex++];
    }
    
    private int peekToken() throws XNIException {
      if (this.fCurrentTokenIndex == this.fTokenCount)
        ElementSchemePointer.this.reportError("XPointerElementSchemeProcessingError", null); 
      return this.fTokens[this.fCurrentTokenIndex];
    }
    
    private String nextTokenAsString() throws XNIException {
      String str = getTokenString(nextToken());
      if (str == null)
        ElementSchemePointer.this.reportError("XPointerElementSchemeProcessingError", null); 
      return str;
    }
    
    private int getTokenCount() {
      return this.fTokenCount;
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xpointer/ElementSchemePointer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */