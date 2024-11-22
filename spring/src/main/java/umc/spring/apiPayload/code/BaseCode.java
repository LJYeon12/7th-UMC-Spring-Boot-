package umc.spring.apiPayload.code;

import java.security.cert.CertPathValidatorException;

public interface BaseCode {
    ReasonDTO getReason();

    ReasonDTO getReasonHttpStatus();
}
