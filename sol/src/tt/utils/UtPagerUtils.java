package tt.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tt.com.bean.VoCoPagerEntity;
import tt.com.bean.VoCoPagerHelper;

/**
 * DESC : 페이징 처리관련 Util 클래스. 페이징 처리는 컨트롤러단에서 처리하지 않고 DWR의 ajax를 이용하여 처리한다.
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 1. 10. 오후 2:35:45
 */
@Component
public class UtPagerUtils {

    private static String dbType;

    @PostConstruct
    public void init() {
        dbType = dbTypeSpEL;
    }

    @Value("${db.type}")
    private String dbTypeSpEL;

    private static final int DEFAULT_VIEW_PAGE_NO = 10;

    public static VoCoPagerHelper paginate(VoCoPagerEntity entity, int totalRecordNo) {

        VoCoPagerHelper pagerHelper = new VoCoPagerHelper();

        int pageNo = entity.getPageNo();
        int groupNo = entity.getGroupNo();
        int viewRecordNo = entity.getViewRecordNo();
        int viewPageNo = 10;

        boolean existPrevGroup = false;
        boolean existNextGroup = false;

        int lastPageNo = ((totalRecordNo % viewRecordNo) == 0) ? (totalRecordNo / viewRecordNo)
                : ((totalRecordNo / viewRecordNo) + 1);
        int lastGroupNo = ((lastPageNo % viewPageNo) == 0) ? (lastPageNo / viewPageNo)
                : ((lastPageNo / viewPageNo) + 1);

        if (groupNo == lastGroupNo) {
            viewPageNo = (lastPageNo <= (groupNo * viewPageNo)) ? (lastPageNo - ((groupNo - 1) * viewPageNo))
                    : viewPageNo;
        }

        if (lastGroupNo > 1) {
            existNextGroup = (groupNo == lastGroupNo) ? false : true;
            if (groupNo > 1) {
                existPrevGroup = true;
            } else {
                existPrevGroup = false;
            }
        }

        pagerHelper.setPageNo(pageNo);
        pagerHelper.setGroupNo(groupNo);
        pagerHelper.setLastPageNo(lastPageNo);
        pagerHelper.setLastGroupNo(lastGroupNo);

        pagerHelper.setExistPrevGroup(existPrevGroup);
        pagerHelper.setExistNextGroup(existNextGroup);

        pagerHelper.setTotalRecordNo(totalRecordNo);
        pagerHelper.setViewPageNo(viewPageNo);
        pagerHelper.setViewRecordNo(viewRecordNo);

        return pagerHelper;
    }

    public static VoCoPagerEntity initialize(VoCoPagerEntity pagerEntity) {

        int pageNo = pagerEntity.getPageNo();
        int viewRecordNo = pagerEntity.getViewRecordNo();

        int startRowNo = 0;
        int endRowNo = 0;

        if (("mysql").equals(dbType.toLowerCase())) {
            startRowNo = (pageNo - 1) * viewRecordNo;
            endRowNo = viewRecordNo;
        } else if (("oracle").equals(dbType.toLowerCase())) {
            startRowNo = (pageNo - 1) * viewRecordNo + 1;
            endRowNo = pageNo * viewRecordNo;
        }

        pagerEntity.setStartRowNo(startRowNo);
        pagerEntity.setEndRowNo(endRowNo);

        return pagerEntity;
    }

    public static String generatePagerHtml(VoCoPagerHelper pagerHelper, String javascriptName) {
        int totalRecordNo = pagerHelper.getTotalRecordNo();

        int pageNo = pagerHelper.getPageNo();
        int groupNo = pagerHelper.getGroupNo();
        int lastPageNo = pagerHelper.getLastPageNo();
        int lastGroupNo = pagerHelper.getLastGroupNo();

        int viewPageNo = pagerHelper.getViewPageNo();

        boolean isExistPrevGroup = pagerHelper.isExistPrevGroup();
        boolean isExistNextGroup = pagerHelper.isExistNextGroup();

        int startViewPageNo = ((groupNo - 1) * DEFAULT_VIEW_PAGE_NO) + 1;
        int lastViewPage = (startViewPageNo) + viewPageNo;

        StringBuilder builder = new StringBuilder();

        if (!(totalRecordNo <= pagerHelper.getViewRecordNo())) {
            builder.append("<div class=\"paginate_ext\">페이지:");
            if (isExistPrevGroup) {
                int prevGroupPage = ((groupNo - 2) * DEFAULT_VIEW_PAGE_NO) + 1;
                int prevGroupNo = groupNo - 1;

                builder.append("<a class=\"first\" onclick='" + javascriptName + "(1, 1)'>1</a>");
                builder.append("<a class=\"prev\" onclick='" + javascriptName + "(" + prevGroupPage + ", " + prevGroupNo
                        + ")'><</a>");
            }
            for (int i = startViewPageNo; i < lastViewPage; i++) {
                if (i == pageNo) {
                    builder.append("<span><a href=\"#\" class=\"choice\" onclick=\"return false;\">" + i + "</a></span>");
                } else {
                    builder.append("<span><a title='page " + i + "' onclick='" + javascriptName + "(" + i + ", " + groupNo
                            + ")'>" + i + "</a></span>");
                }
            }

            if (isExistNextGroup) {
                int nextGroupPage = (groupNo * viewPageNo) + 1;
                int nextGroupNo = groupNo + 1;

                builder.append("<a class=\"next\" onclick='" + javascriptName + "(" + nextGroupPage + ", " + nextGroupNo
                        + ")'></a>");
                builder.append("<a class=\"last\" onclick='" + javascriptName + "(" + lastPageNo + ", " + lastGroupNo + ")'>"
                        + lastPageNo + "</a>");
            }
        }

        builder.append("</div><div class=\"pagetotal_ext\">총건수:<span>" + totalRecordNo + "</span>건</div>");
        return builder.toString();
    }

}
