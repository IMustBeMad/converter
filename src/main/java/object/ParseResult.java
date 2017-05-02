package object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParseResult {
    private LcResult lcResult;
    private IsdResult isdResult;
}
