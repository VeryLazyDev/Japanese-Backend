package com.dev.japanese_app.common.utils;

import com.dev.japanese_app.common.model.PaginationInfo;
import org.springframework.data.domain.Page;

import java.util.Map;

public class PaginationUtils {
    public static Map<String, Object> buildPaginationMetaData(Page<?> page) {
        PaginationInfo data = new PaginationInfo(page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(), page.isLast());
        return Map.of("pagination", data);
    }
}

