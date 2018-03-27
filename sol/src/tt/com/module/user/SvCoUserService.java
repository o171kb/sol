package tt.com.module.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.base.module.TtBaseService;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoPagerHelper;
import tt.com.bean.VoCoUser;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;
import tt.utils.UtPagerUtils;


/**
 * <pre>
 * tt.com.module.user
 *    |_ SvCoUserService.java
 *
 * DESC : 관리자 관리 서비스 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 6. 오후 1:10:19
 * @history :
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용
 *  ----------- ------------------- ---------------------------------------
 *  2013. 3. 6.     ks-lee              최초 작성
 *  -----------------------------------------------------------------------
 *
 */
@Service("svCoUserService")
public class SvCoUserService extends TtBaseService  {

    /**
     * 사용자검색리스트 취득 <br />
     * @param ttObjParams 검색 파라미터
     * @return 검색리스트
     */
    public List<VoCoUser> getUserSearchListForPage(CoTtObjParams ttObjParams) {

        VoCoUser voCoUser = (VoCoUser) ttObjParams.get("voCoUser");

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voCoUser.getFirstIndex(), voCoUser.getRecordCountPerPage());

        ttObjParamsSql.put("searchCd", UtCoStringUtils.trim(voCoUser.getSearchCd()));
        ttObjParamsSql.put("searchKeyword", UtCoStringUtils.trim(voCoUser.getSearchKeyword()));

        if (!UtCoStringUtils.isEmpty(voCoUser.getSearchUserGrpNo())) {
            ttObjParamsSql.put("searchUserGrpNo", Integer.parseInt(voCoUser.getSearchUserGrpNo()));
        }

        return daoCoUser.getUserSearchListForPage(ttObjParamsSql);
    }

    /**
     * 사용자검색 총 갯수 취득 <br />
     *
     * @param ttObjParams 검색 파라미터
     * @return 토탈카운트
     */
    public int getUserSearchListTotCnt(CoTtObjParams ttObjParams) {

        VoCoUser voCoUser = (VoCoUser) ttObjParams.get("voCoUser");

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("searchCd", UtCoStringUtils.trim(voCoUser.getSearchCd()));
        ttObjParamsSql.put("searchKeyword", UtCoStringUtils.trim(voCoUser.getSearchKeyword()));

        if (!UtCoStringUtils.isEmpty(voCoUser.getSearchUserGrpNo())) {
            ttObjParamsSql.put("searchUserGrpNo", Integer.parseInt(voCoUser.getSearchUserGrpNo()));
        }

        return daoCoUser.getUserSearchListTotCnt(ttObjParamsSql);
    }

    /**
     * 대행결제자 이름 가져오기 <br />
     *
     * @param ttObjParams 검색 파라미터
     * @return 대행결제자 이름
     */
    public String getProxyName(CoTtObjParams ttObjParams) {
        VoCoUser voCoUser = (VoCoUser) ttObjParams.get("voCoUser");

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        System.out.println(voCoUser.getProxyApprId());
        ttObjParamsSql.put("userId", UtCoStringUtils.trim(voCoUser.getUserId()));
        ttObjParamsSql.put("proxyApprId", UtCoStringUtils.trim(voCoUser.getProxyApprId()));

        return daoCoUser.getProxyName(ttObjParamsSql);
    }

    /*#########################################################################################
     * DWR 사용자 선택
     *######################################################################################### */
    /**
     * 사용자 사번 중복을 조회한다 <br />
     *
     * @param ttObjParamsSql 검색정보
     * @return 체크 결과 -1:사용불가, 0:검색문자열"", 1:사용불가
     */
    public int checkUserId(CoTtObjParams ttObjParamsSql) {

        int rsCnt = daoCoUser.checkUserId(ttObjParamsSql);

        return rsCnt;
    }

    /**
     * 대행결재자리스트 취득 <br />
     * @param voCoUser 검색 파라미터
     * @return 검색리스트
     */
    public List<VoCoUser> getSearchUserDeptListForDwr(VoCoUser voCoUser) {

        if (voCoUser.getPageNo() > 1) {
            voCoUser.setFirstIndex((voCoUser.getPageNo() - 1) * voCoUser.getViewRecordNo());
        } else {
            voCoUser.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voCoUser.getFirstIndex(), voCoUser.getRecordCountPerPage());
        ttObjParamsSql.put(VoCoUser.COL_DEPT_NO, UtCoStringUtils.trim(voCoUser.getDeptNo()));

        return daoCoUser.getSearchUserDeptListForPage(ttObjParamsSql);
    }

    /**
     * 대행결재자리스트 총 갯수 취득 및 페이징<br />
     *
     * @param voCoUser 검색 파라미터
     * @return 토탈카운트
     */
    public String paginateUserDeptListTotCnt(VoCoUser voCoUser) {

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put(VoCoUser.COL_DEPT_NO, UtCoStringUtils.trim(voCoUser.getDeptNo()));

        int totalRecordNo = daoCoUser.paginateUserDeptListTotCnt(ttObjParamsSql);

        VoCoPagerHelper pagerHelper =  UtPagerUtils.paginate(voCoUser, totalRecordNo);
        String resultHtml = UtPagerUtils.generatePagerHtml(pagerHelper, "selectUserDeptListDwr");
        return resultHtml;
    }

    /**
     * 사용자 등록 <br />
     * @param ttObjParams 등록 파라미터
     */
    public void registUser(CoTtObjParams ttObjParams) {

        VoCoUser voCoUser = (VoCoUser) ttObjParams.get("voCoUser");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();

        String sysdate = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_PATTERN_DASH);

        ttObjParamsSql.put("userId", UtCoStringUtils.trim(voCoUser.getUserId()));
        ttObjParamsSql.put("userPwd", UtCoStringUtils.trim(voCoUser.getUserPwd()));
        ttObjParamsSql.put("userNm", UtCoStringUtils.trim(voCoUser.getUserNm()));
        ttObjParamsSql.put("userGrpNo", UtCoStringUtils.trim(voCoUser.getUserGrpNo()));
        ttObjParamsSql.put("deptNo", UtCoStringUtils.trim(voCoUser.getDeptNo()));
        ttObjParamsSql.put("userPosition", UtCoStringUtils.trim(voCoUser.getUserPosition()));
        ttObjParamsSql.put("userTel", UtCoStringUtils.trim(voCoUser.getUserTel()));
        ttObjParamsSql.put("userMobile", UtCoStringUtils.trim(voCoUser.getUserMobile()));
        ttObjParamsSql.put("userEmail", UtCoStringUtils.trim(voCoUser.getUserEmail()));
        ttObjParamsSql.put("proxyApprYn", UtCoStringUtils.trim(voCoUser.getProxyApprYn()));
        ttObjParamsSql.put("proxyApprId", UtCoStringUtils.trim(voCoUser.getProxyApprId()));
        ttObjParamsSql.put("regDm", sysdate);
        ttObjParamsSql.put("updDm", "");
        ttObjParamsSql.put("useYn", "1");
        ttObjParamsSql.put("regrId", voCoUser.getUserId());
        ttObjParamsSql.put("authority", "ROLE_SUPER"); //권한
        daoCoUser.registUser(ttObjParamsSql);
    }

    /**
     * 사용자 상세 <br />
     * @param ttObjParams 등록 파라미터
     * @return VoCoUser
     */
    public VoCoUser getUserDtl(CoTtObjParams ttObjParams) {

        VoCoUser voCoUser = (VoCoUser) ttObjParams.get("voCoUser");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();

        ttObjParamsSql.put("userId", UtCoStringUtils.trim(voCoUser.getUserId()));

        return (VoCoUser) daoCoUser.getUserDtl(ttObjParamsSql);
    }

    /**
     * 사용자 수정 <br />
     * @param ttObjParams 수정 파라미터
     */
    public void modifyUser(CoTtObjParams ttObjParams) {
        VoCoUser voCoUser = (VoCoUser) ttObjParams.get("voCoUser");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();

        String sysdate = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_PATTERN_DASH);

        ttObjParamsSql.put("userId", UtCoStringUtils.trim(voCoUser.getUserId()));
        ttObjParamsSql.put("userPwd", UtCoStringUtils.trim(voCoUser.getUserPwd()));
        ttObjParamsSql.put("userNm", UtCoStringUtils.trim(voCoUser.getUserNm()));
        ttObjParamsSql.put("userGrpNo", UtCoStringUtils.trim(voCoUser.getUserGrpNo()));
        ttObjParamsSql.put("deptNo", UtCoStringUtils.trim(voCoUser.getDeptNo()));
        ttObjParamsSql.put("userPosition", UtCoStringUtils.trim(voCoUser.getUserPosition()));
        ttObjParamsSql.put("userTel", UtCoStringUtils.trim(voCoUser.getUserTel()));
        ttObjParamsSql.put("userMobile", UtCoStringUtils.trim(voCoUser.getUserMobile()));
        ttObjParamsSql.put("userEmail", UtCoStringUtils.trim(voCoUser.getUserEmail()));
        ttObjParamsSql.put("proxyApprYn", UtCoStringUtils.trim(voCoUser.getProxyApprYn()));
        ttObjParamsSql.put("proxyApprId", UtCoStringUtils.trim(voCoUser.getProxyApprId()));
        ttObjParamsSql.put("updDm", sysdate);
        ttObjParamsSql.put("useYn", "1");
        ttObjParamsSql.put("regrId", voCoUser.getUserId());
        ttObjParamsSql.put("authority", "ROLE_SUPER"); //권한

        daoCoUser.modifyUser(ttObjParamsSql);
    }

    /**
     * 사용자 삭제 <br />
     * @param ttObjParams 삭제 정보
     */
    @Transactional
    public void deleteUser(CoTtObjParams ttObjParams) {
        VoCoUser voCoUser = (VoCoUser) ttObjParams.get("voCoUser");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        if (!UtCoStringUtils.isEmpty(voCoUser.getDelArr())) {
            String[] chkUser = voCoUser.getDelArr().split(",");
            for (int i = 0; i < chkUser.length; ++i) {
                ttObjParamsSql.put("userId", chkUser[i]);
                ttObjParamsSql.put("userYn", "0");
                daoCoUser.deleteUser(ttObjParamsSql);
            }
        }

    }

}