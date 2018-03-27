package tt.com.bean;

/**
 * DESC : 구성한 Page Navigator 정보를 담는 Helper Bean
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 1. 10. 오후 3:59:11
 */
public class VoCoPagerHelper {

    private static final int DEFAUTL_VIEW_PAGE_NO = 10;

    private static final int DEFAUTL_VIEW_RECORD_NO = 10;

    private String viewName;

    private int pageNo = 1;

    private int groupNo = 1;

    private int lastPageNo = 0;

    private int lastGroupNo = 0;

    private int totalRecordNo = 0;

    private int viewPageNo = DEFAUTL_VIEW_PAGE_NO;

    private int viewRecordNo = DEFAUTL_VIEW_RECORD_NO;

    private boolean existPrevGroup = false;

    private boolean existNextGroup = false;

    private int startRowNo;

    private int endRowNo;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public int getLastPageNo() {
        return lastPageNo;
    }

    public void setLastPageNo(int lastPageNo) {
        this.lastPageNo = lastPageNo;
    }

    public int getLastGroupNo() {
        return lastGroupNo;
    }

    public void setLastGroupNo(int lastGroupNo) {
        this.lastGroupNo = lastGroupNo;
    }

    public int getTotalRecordNo() {
        return totalRecordNo;
    }

    public void setTotalRecordNo(int totalRecordNo) {
        this.totalRecordNo = totalRecordNo;
    }

    public int getViewPageNo() {
        return viewPageNo;
    }

    public void setViewPageNo(int viewPageNo) {
        this.viewPageNo = viewPageNo;
    }

    public int getViewRecordNo() {
        return viewRecordNo;
    }

    public void setViewRecordNo(int viewRecordNo) {
        this.viewRecordNo = viewRecordNo;
    }

    public boolean isExistPrevGroup() {
        return existPrevGroup;
    }

    public void setExistPrevGroup(boolean existPrevGroup) {
        this.existPrevGroup = existPrevGroup;
    }

    public boolean isExistNextGroup() {
        return existNextGroup;
    }

    public void setExistNextGroup(boolean existNextGroup) {
        this.existNextGroup = existNextGroup;
    }

    public int getStartRowNo() {
        return startRowNo;
    }

    public void setStartRowNo(int startRowNo) {
        this.startRowNo = startRowNo;
    }

    public int getEndRowNo() {
        return endRowNo;
    }

    public void setEndRowNo(int endRowNo) {
        this.endRowNo = endRowNo;
    }

    public static int getDefautlViewPageNo() {
        return DEFAUTL_VIEW_PAGE_NO;
    }

    public static int getDefautlViewRecordNo() {
        return DEFAUTL_VIEW_RECORD_NO;
    }

}
