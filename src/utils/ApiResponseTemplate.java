package utils;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ApiResponseTemplate<T>(
        int status,
        String message,
        LocalDate timeStamp,
        T data
){
}
