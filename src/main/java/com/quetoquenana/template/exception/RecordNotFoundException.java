package com.quetoquenana.template.exception;

import lombok.Getter;

@Getter
public class RecordNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE_KEY = "record.not.found";

    private final String messageKey;
    private final Object[] messageArgs;


    public RecordNotFoundException() {
        super();
        this.messageKey = DEFAULT_MESSAGE_KEY;
        this.messageArgs = null;
    }

    public RecordNotFoundException(String messageKey, Object... messageArgs) {
        super(messageKey);
        this.messageKey = messageKey;
        this.messageArgs = messageArgs;
    }

}
