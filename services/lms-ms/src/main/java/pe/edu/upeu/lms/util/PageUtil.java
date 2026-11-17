package pe.edu.upeu.lms.util;

import pe.edu.upeu.lms.dto.PageResponse;

import java.util.List;

public final class PageUtil {

    private PageUtil() {
    }

    public static <T> PageResponse<T> paginate(List<T> all, int page, int size) {
        int safePage = Math.max(page, 0);
        int safeSize = size <= 0 ? 20 : size;
        int totalElements = all.size();
        int totalPages = (int) Math.ceil(totalElements / (double) safeSize);
        int fromIndex = Math.min(safePage * safeSize, totalElements);
        int toIndex = Math.min(fromIndex + safeSize, totalElements);
        List<T> content = all.subList(fromIndex, toIndex);
        return PageResponse.<T>builder()
                .content(content)
                .page(safePage)
                .size(safeSize)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }
}
