package tt.com.bean;

import java.io.Serializable;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * DESC : Page Navigator 를 구성을 위한 Entity Bean
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 1. 10. 오후 3:58:49
 */
public class VoCoPagerEntity extends PaginationInfo implements Serializable {

    //
    private static final long serialVersionUID = -7474461624831217399L;

    private static final int DEFAUTL_VIEW_PAGE_NO = 10;

    private static final int DEFAUTL_VIEW_RECORD_NO = 10;

    private int pageNo = 1;

    private int pageNo1;

    private int groupNo = 1;

    private int totalRecordNo = 0;

    private int viewPageNo = DEFAUTL_VIEW_PAGE_NO;

    private int viewRecordNo = DEFAUTL_VIEW_RECORD_NO;

    private int startRowNo;

    private int endRowNo;

    private String sortRecord;

    private String sortType = "DESC";

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

    public String getSortRecord() {
        return sortRecord;
    }

    public void setSortRecord(String sortRecord) {
        this.sortRecord = sortRecord;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public int getPageNo1() {
        return pageNo1;
    }

    public void setPageNo1(int pageNo1) {
        this.pageNo1 = pageNo1;
    }


}