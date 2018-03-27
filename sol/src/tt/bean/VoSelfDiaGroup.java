package tt.bean;

import tt.com.bean.VoCoBase;



/**
 * <pre>
 * tt.bean
 *    |_ VoSelfDiaGroup.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 5. 6. 오후 2:30:58
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 5. 6.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class VoSelfDiaGroup extends VoCoBase {

    private static final long serialVersionUID = 1315554918109341499L;

    /** id-pk */
    private String id;

    /** 점검항목 그룹명 */
    private String groupNm;

    /** 등록일 */
    private String insDate;

    /** 수정일 */
    private String updDate;

    /** 삭제여부 */
    private String isDel;

    /**  */
    private String isSys;

    private int totCnt;

    /**
     * XXXXX 를 취득 <br />
     * @return totCnt XXXXX
     */
    public int getTotCnt() {
        return totCnt;
    }

    /**
     * XXXXX 를 설정 <br />
     * @param totCnt XXXXX 설정
     */
    public void setTotCnt(int totCnt) {
        this.totCnt = totCnt;
    }


    /**
     * XXXXX 를 취득 <br />
     * @return groupNm XXXXX
     */
    public String getGroupNm() {
        return groupNm;
    }

    /**
     * XXXXX 를 설정 <br />
     * @param groupNm XXXXX 설정
     */
    public void setGroupNm(String groupNm) {
        this.groupNm = groupNm;
    }

    /**
     * XXXXX 를 취득 <br />
     * @return id XXXXX
     */
    public String getId() {
        return id;
    }

    /**
     * XXXXX 를 설정 <br />
     * @param id XXXXX 설정
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * XXXXX 를 취득 <br />
     * @return insDate XXXXX
     */
    public String getInsDate() {
        return insDate;
    }

    /**
     * XXXXX 를 설정 <br />
     * @param insDate XXXXX 설정
     */
    public void setInsDate(String insDate) {
        this.insDate = insDate;
    }

    /**
     * XXXXX 를 취득 <br />
     * @return updDate XXXXX
     */
    public String getUpdDate() {
        return updDate;
    }

    /**
     * XXXXX 를 설정 <br />
     * @param updDate XXXXX 설정
     */
    public void setUpdDate(String updDate) {
        this.updDate = updDate;
    }

    /**
     * XXXXX 를 취득 <br />
     * @return isDel XXXXX
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * XXXXX 를 설정 <br />
     * @param isDel XXXXX 설정
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    /**
     * XXXXX 를 취득 <br />
     * @return isSys XXXXX
     */
    public String getIsSys() {
        return isSys;
    }

    /**
     * XXXXX 를 설정 <br />
     * @param isSys XXXXX 설정
     */
    public void setIsSys(String isSys) {
        this.isSys = isSys;
    }


}
