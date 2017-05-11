package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParserException extends Exception {
    private String msg;

    public ParserException() {
        super();
    }

    public ParserException(String msg) {
        super(msg);
    }

    public ParserException(Throwable cause) {
        super(cause);
    }
}
