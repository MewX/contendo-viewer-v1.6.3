package jp.cssj.homare.driver.auth;

import java.util.Map;
import jp.cssj.c.a;

public interface Authenticator extends a<Map<String, String>> {
  boolean authenticate(Map<String, String> paramMap);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/driver/auth/Authenticator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */