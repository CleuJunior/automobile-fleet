package com.automobilefleet.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class PageSearch {

    protected int page = 0;
    protected int size = 1;

    public Pageable pageable() {
        return PageRequest.of(page, size);
    }

    public static Pageable of(int page, int size) {
        return PageRequest.of(page, size);
    }
}
