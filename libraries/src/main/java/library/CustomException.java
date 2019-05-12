package library;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomException extends  Exception{
    private  Exception originalException;

    public CustomException(Exception original){
        super(original);
    }

    public CustomException(String message){
        super(message);
    }
}
