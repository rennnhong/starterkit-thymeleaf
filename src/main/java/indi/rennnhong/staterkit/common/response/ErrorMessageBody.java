package indi.rennnhong.staterkit.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PACKAGE)
@RequiredArgsConstructor
public class ErrorMessageBody {
    private final int code;
    private final String message;
    private Object details;
}
