package com.huggingsoft.hs_todo_manager.services.uitls;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

public class PageUtil {
    private static final String PAGE_INDEX = "pageIndex";
    private static final String PAGE_SIZE = "pageSize";
    public static Pageable buildPageRequest(Map<String, String> filters ) {
        int pageIndex = Integer.parseInt(Optional.ofNullable(filters.remove(PAGE_INDEX)).orElse("0"));
        int pageSize = Integer.parseInt(Optional.ofNullable(filters.remove(PAGE_SIZE)).orElse("10"));
        return PageRequest.of(pageIndex, pageSize);
    }

    private PageUtil() throws IllegalAccessException { throw new IllegalAccessException(); }
}
