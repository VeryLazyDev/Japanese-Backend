package com.dev.japanese_app.common.model;

public record PaginationInfo(
        int pageNumber,
        int size,
        long totalElement,
        int totalPages,
        boolean isLast
)
{}