package com.thinkingbinding.exception;

import com.thinkingbinding.type.MemberErrorCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberException extends RuntimeException{

    private MemberErrorCode memberErrorCode;
    private String errorMessage;

    public MemberException(MemberErrorCode memberErrorCode) {
        this.memberErrorCode = memberErrorCode;
        this.errorMessage = memberErrorCode.getDescription();
    }

}
