package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PipelineException extends RuntimeException {
    private String msg;

    public PipelineException() {
        super();
    }

    public PipelineException(String msg) {
        super(msg);
    }

    public PipelineException(Throwable cause) {
        super(cause);
    }

    public PipelineException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
