package com.example.clientManager;


import com.example.clientManager.exception.ValidationDataException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class Paging {
    public static Pageable paging(Integer from, Integer size) {
        Pageable pageable;
        if (from < 0) {
            throw new ValidationDataException("Date is not valid.");
        } else if (from == 0) {
            pageable = PageRequest.of(0, size);
        } else {
            pageable = PageRequest.of(from / size, size);
        }
        return pageable;
    }
}
