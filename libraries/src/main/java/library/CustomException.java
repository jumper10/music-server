package library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomException extends  Exception{
    private  Exception originalException;
    public CustomException(String message){
        super(message);
    }
}
