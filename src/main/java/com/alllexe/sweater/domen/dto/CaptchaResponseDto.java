package com.alllexe.sweater.domen.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDto {
  private boolean success;
  @JsonAlias("error-codes")
  private Set<String> errorCodes;

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public Set<String> getErrorCodes() {
    return errorCodes;
  }

  public void setErrorCodes(Set<String> errorCodes) {
    this.errorCodes = errorCodes;
  }
}
