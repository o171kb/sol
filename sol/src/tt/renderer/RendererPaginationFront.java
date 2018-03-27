package tt.renderer;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

/**
 * @Class Name : PaginationRenderer.java
 * @Description : PaginationRenderer.java class
 *
 * @Modification Information @ @ Modify Date Modified by Description @ ------------- ------------
 *               --------------------------- @ 2012. 10. 23. Hyun Joong Kim The first generation
 *
 * @author Hyun Joong Kim
 * @since 2012. 10. 23.
 * @version 1.0
 *
 */
public class RendererPaginationFront extends AbstractPaginationRenderer {

    public RendererPaginationFront() {

        firstPageLabel = "<a href=\"#\" class=\"first\" onclick=\"{0}({1}); return false;\"></a>";
        previousPageLabel = "<a href=\"#\" class=\"prev\" onclick=\"{0}({1}); return false;\"></a>";

        currentPageLabel = "<span><a href=\"#\" title=\"page {0}\" class=\"choice\">{0}</a></span>";
        otherPageLabel = "<span><a href=\"#\" title=\"page {2}\" onclick=\"{0}({1}); return false;\">{2}</a></span>";

        nextPageLabel = "<a href=\"#\" class=\"next\" onclick=\"{0}({1}); return false;\"></a>";
        lastPageLabel = "<a href=\"#\" class=\"last\" onclick=\"{0}({1}); return false;\"></a>";

    }
}
