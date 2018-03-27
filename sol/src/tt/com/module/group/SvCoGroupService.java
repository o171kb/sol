package tt.com.module.group;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.TTLog;
import tt.base.module.TtBaseService;
import tt.com.CoTtObjParams;
import tt.com.CoTtStrParams;
import tt.com.bean.VoCoDept;
import tt.com.bean.VoCoPagerHelper;
import tt.com.bean.VoCoUser;
import tt.com.bean.VoCoUserGrp;
import tt.com.bean.VoCoUserGrpMenu;
import tt.com.constant.CsCoConstDef;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;
import tt.utils.UtPagerUtils;


/**
 * <pre>
 * tt.com.module.group
 *    |_ SvCoGroupService.java
 *
 * DESC : 그룹 관리 서비스 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 25. 오후 12:59:42
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 25.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Service("svCoGroupService")
public class SvCoGroupService extends TtBaseService  {


    /**
     * 그룹검색리스트 취득 <br />
     * @param ttObjParams 검색 파라미터
     * @return 검색리스트
     */
    public List<VoCoUserGrp> getUserGrpSearchListForPage(CoTtObjParams ttObjParams) {
        VoCoUserGrp voCoUserGrp = (VoCoUserGrp) ttObjParams.get("voCoUserGrp");

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voCoUserGrp.getFirstIndex(), voCoUserGrp.getRecordCountPerPage());
        ttObjParamsSql.put("searchStatus", UtCoStringUtils.trim(voCoUserGrp.getSearchStatus()));
        ttObjParamsSql.put("searchGrpId", UtCoStringUtils.trim(voCoUserGrp.getSearchGrpId()));
        //ttObjParamsSql.put("searchGrpNm", UtCoStringUtils.trim(voCoUserGrp.getSearchGrpNm()));

        return daoCoGroup.getGrpSearchListForPage(ttObjParamsSql);

    }

    /**
     * 그룹검색 총 갯수 취득 <br />
     *
     * @param ttObjParams 검색 파라미터
     * @return 토탈카운트
     */
    public int getGrpSearchListTotCnt(CoTtObjParams ttObjParams) {

        VoCoUserGrp voCoUserGrp = (VoCoUserGrp) ttObjParams.get("voCoUserGrp");

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("searchGrpId", UtCoStringUtils.trim(voCoUserGrp.getSearchGrpId()));
        ttObjParamsSql.put("searchGrpNm", UtCoStringUtils.trim(voCoUserGrp.getSearchGrpNm()));

        return daoCoGroup.getGrpSearchListTotCnt(ttObjParamsSql);
    }

    /**
     * 그룹의 상세를 조회한다 <br />
     * @param ttObjParams 검색 파라미터
     * @return 검색그룹
     */
    public VoCoUserGrp getGrpDtl(CoTtObjParams ttObjParams) {

        VoCoUserGrp voCoUserGrp = (VoCoUserGrp) ttObjParams.get("voCoUserGrp");
        TTLog.debug(voCoUserGrp.toString(), this.getClass());

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("userGrpNo", Integer.parseInt(voCoUserGrp.getUserGrpNo()));


        return daoCoGroup.getGrpDtl(ttObjParamsSql);
    }

    /**
     * 그룹메뉴를 취득한다 <br />
     * @param ttObjParams 검색 파라미터
     * @return 검색그룹
     */
    public CoTtStrParams getUserGrpMenu(CoTtObjParams ttObjParams) {

        VoCoUserGrp voCoUserGrp = (VoCoUserGrp) ttObjParams.get("voCoUserGrp");
        TTLog.debug(voCoUserGrp.toString(), this.getClass());

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("userGrpNo", Integer.parseInt(voCoUserGrp.getUserGrpNo()));

        List<VoCoUserGrpMenu> userGrpMenuList = daoCoGroup.getUserGrpMenu(ttObjParamsSql);


        CoTtStrParams menuStrParams = null;

        if (userGrpMenuList != null && userGrpMenuList.size() > 0) {
            menuStrParams = new CoTtStrParams();

            for (VoCoUserGrpMenu voCoUserGrpMenu : userGrpMenuList) {
                menuStrParams.put(voCoUserGrpMenu.getMenuAction(),
                        voCoUserGrpMenu.getMenuLvl());
            }
        }


        return menuStrParams;
    }

    /**
     * 그룹를 (논리)삭제한다 <br />
     * @param ttObjParams 삭제정보
     */
    @Transactional
    public void deleteGroup(CoTtObjParams ttObjParams) {

        VoCoUserGrp voCoUserGrp = (VoCoUserGrp) ttObjParams.get("voCoUserGrp");

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        if (!UtCoStringUtils.isEmpty(voCoUserGrp.getDelArr())) {
            String[] chkGrp = voCoUserGrp.getDelArr().split(",");

            for (int i = 0; i < chkGrp.length; ++i) {
                ttObjParamsSql.put("userGrpNo", Integer.parseInt(chkGrp[i]));
                daoCoGroup.deleteGroup(ttObjParamsSql);
            }
        }
    }

    /**
     * 기본그룹정보를 수정한다 <br />
     * @param ttObjParams 수정정보
     */
    @Transactional
    public void modifyGrpInfo(CoTtObjParams ttObjParams) {

        VoCoUserGrp voCoUserGrp = (VoCoUserGrp) ttObjParams.get("voCoUserGrp");
        TTLog.debug(voCoUserGrp.toString(), this.getClass());

        // 결재권한이 null일경우 0설정
        String tmpApprYn = voCoUserGrp.getApprYn();
        if ("on".equals(tmpApprYn)) {
            voCoUserGrp.setApprYn("1");
        } else {
            voCoUserGrp.setApprYn("0");
        }
        
        // 마스터관리권한이 null일경우 0설정
        String mstYn = voCoUserGrp.getMstYn();
        if ("on".equals(mstYn)) {
            voCoUserGrp.setMstYn("1");
        } else {
            voCoUserGrp.setMstYn("0");
        }
        
        // 그룹관리권한이 null일경우 0설정
        String grpYn = voCoUserGrp.getGrpYn();
        if ("on".equals(grpYn)) {
            voCoUserGrp.setGrpYn("1");
        } else {
            voCoUserGrp.setGrpYn("0");
        }
        
        // 자신결재권한이 null일경우 0설정
        String selfYn = voCoUserGrp.getSelfYn();
        if ("on".equals(selfYn)) {
            voCoUserGrp.setSelfYn("1");
        } else {
            voCoUserGrp.setSelfYn("0");
        }
        
        // 전산관리결재 null일경우 0설정
        String compAdmin = voCoUserGrp.getCompAdmin();
        if ("on".equals(compAdmin)) {
            voCoUserGrp.setCompAdmin("1");
        } else {
            voCoUserGrp.setCompAdmin("0");
        }
        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put(VoCoUser.COL_USER_GRP_NO, Integer.parseInt(voCoUserGrp.getUserGrpNo()));
        ttObjParamsSql.put(VoCoUser.COL_GRP_NM, voCoUserGrp.getGrpNm());
        ttObjParamsSql.put(VoCoUser.COL_GRP_EXP, voCoUserGrp.getGrpExp());
        ttObjParamsSql.put(VoCoUser.COL_APPR_YN, voCoUserGrp.getApprYn());
        ttObjParamsSql.put(VoCoUser.COL_MST_YN, voCoUserGrp.getMstYn());
        ttObjParamsSql.put(VoCoUser.COL_GRP_YN, voCoUserGrp.getGrpYn());
        ttObjParamsSql.put(VoCoUser.COL_SELF_YN, voCoUserGrp.getSelfYn());
        ttObjParamsSql.put(VoCoUser.COL_COMPADMIN, voCoUserGrp.getCompAdmin());
        ttObjParamsSql.put(VoCoUser.COL_COMPADCODE, voCoUserGrp.getCompAdCode());

        daoCoGroup.updateGrpInfo(ttObjParamsSql);
    }

    /**
     * 그룹메뉴정보를 수정한다 <br />
     * @param ttObjParams 수정정보
     */
    @Transactional
    public void modifyGrpMenu(CoTtObjParams ttObjParams) {

        //그룹정보
        VoCoUserGrp voCoUserGrp = (VoCoUserGrp) ttObjParams.get("voCoUserGrp");
        //그룹메뉴정보
        CoTtStrParams ttStrParamsMenu = (CoTtStrParams) ttObjParams.get("ttStrParamsMenu");
        TTLog.debug(voCoUserGrp.toString(), this.getClass());
        TTLog.debug(voCoUserGrp.toString(), this.getClass());

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put(VoCoUserGrp.COL_USER_GRP_NO, Integer.parseInt(voCoUserGrp.getUserGrpNo()));

        TTLog.debug(voCoUserGrp.getUserGrpNo(), this.getClass());

        //모든메뉴삭제
        daoCoGroup.deleteGrpMenuByUserGrpNo(ttObjParamsSql);

        //수정정보등록
        Object[] objKey = ttStrParamsMenu.keySet().toArray();
        for (int i = 0; i < objKey.length; i++) {
            ttObjParamsSql.put(VoCoUserGrpMenu.COL_MENU_ID, (String) objKey[i]);
            ttObjParamsSql.put(VoCoUserGrpMenu.COL_MENU_LVL, ttStrParamsMenu.get((String) objKey[i]));

            TTLog.debug("modifyGrpMenu || menuId   :: " + (String) objKey[i], this.getClass());
            TTLog.debug("modifyGrpMenu || menuLvl  :: " + ttStrParamsMenu.get((String) objKey[i]), this.getClass());

            daoCoGroup.insertGrpMenu(ttObjParamsSql);
        }

    }

    /**
     * 그룹정보를 등록한다 <br />
     * @param ttObjParams 등록정보
     * @return 그룹번호
     */
    @Transactional
    public int registGrpInfo(CoTtObjParams ttObjParams) {

        //◆그룹정보등록
        VoCoUserGrp voCoUserGrp = (VoCoUserGrp) ttObjParams.get("voCoUserGrp");
        TTLog.debug(voCoUserGrp.toString(), this.getClass());

        //등록시간
        String sysdate = UtCoDateUtils.getCurrentDateAsString(UtCoDateUtils.DATE_HMS_PATTERN);

        // 결재권한이 null일경우 0설정
        String tmpApprYn = voCoUserGrp.getApprYn();
        if ("on".equals(tmpApprYn)) {
            voCoUserGrp.setApprYn("1");
        } else {
            voCoUserGrp.setApprYn("0");
        }
        
        // 마스터관리권한이 null일경우 0설정
        String mstYn = voCoUserGrp.getMstYn();
        if ("on".equals(mstYn)) {
            voCoUserGrp.setMstYn("1");
        } else {
            voCoUserGrp.setMstYn("0");
        }
        
        // 그룹관리권한이 null일경우 0설정
        String grpYn = voCoUserGrp.getGrpYn();
        if ("on".equals(grpYn)) {
            voCoUserGrp.setGrpYn("1");
        } else {
            voCoUserGrp.setGrpYn("0");
        }
        
        // 자신결재권한이 null일경우 0설정
        String selfYn = voCoUserGrp.getSelfYn();
        if ("on".equals(selfYn)) {
            voCoUserGrp.setSelfYn("1");
        } else {
            voCoUserGrp.setSelfYn("0");
        }

        // 전산관리결재 null일경우 0설정
        String compAdmin = voCoUserGrp.getCompAdmin();
        if ("on".equals(compAdmin)) {
            voCoUserGrp.setCompAdmin("1");
        } else {
            voCoUserGrp.setCompAdmin("0");
        }

        //파라미터설정
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();

        //MAX 그룹번호취득 + 1
        int nextUserGrpNo = daoCoGroup.getUserGrpNo() + 1;

        ttObjParamsSql.put(VoCoUserGrp.COL_USER_GRP_NO  , nextUserGrpNo);
        ttObjParamsSql.put(VoCoUserGrp.COL_USER_GRP_ID  , voCoUserGrp.getUserGrpId());
        ttObjParamsSql.put(VoCoUserGrp.COL_GRP_NM       , voCoUserGrp.getGrpNm());
        ttObjParamsSql.put(VoCoUserGrp.COL_GRP_EXP      , voCoUserGrp.getGrpExp());
        ttObjParamsSql.put(VoCoUserGrp.COL_GRP_TP       , CsCoConstDef.DEFAULT_GRP_TP);
        ttObjParamsSql.put(VoCoUserGrp.COL_GRP_CL       , CsCoConstDef.DEFAULT_GRP_CL);
        ttObjParamsSql.put(VoCoUserGrp.COL_MEM_DEPT_NO  , voCoUserGrp.getMemDeptNo());
        ttObjParamsSql.put(VoCoUserGrp.COL_APPR_YN      , voCoUserGrp.getApprYn());
        ttObjParamsSql.put(VoCoUserGrp.COL_MST_YN      , voCoUserGrp.getMstYn());
        ttObjParamsSql.put(VoCoUserGrp.COL_GRP_YN      , voCoUserGrp.getGrpYn());
        ttObjParamsSql.put(VoCoUserGrp.COL_SELF_YN      , voCoUserGrp.getSelfYn());
        ttObjParamsSql.put(VoCoUserGrp.COL_REG_DM       , sysdate);
        ttObjParamsSql.put(VoCoUserGrp.COL_UPD_DM       , "");
        ttObjParamsSql.put(VoCoUserGrp.COL_USE_YN       , "1");
        ttObjParamsSql.put(VoCoUserGrp.COL_USER_ID      , voCoUserGrp.getUserId());
        ttObjParamsSql.put(VoCoUserGrp.COL_COMPADMIN      , voCoUserGrp.getCompAdmin());
        ttObjParamsSql.put(VoCoUserGrp.COL_COMPADCODE      , voCoUserGrp.getCompAdCode());

        daoCoGroup.insertUserGrpInfo(ttObjParamsSql);

        //◆그룹메뉴정보등록
        CoTtStrParams ttStrParamsMenu = (CoTtStrParams) ttObjParams.get("ttStrParamsMenu");
        //수정정보등록
        Object[] objKey = ttStrParamsMenu.keySet().toArray();
        for (int i = 0; i < objKey.length; i++) {
            ttObjParamsSql.put(VoCoUserGrpMenu.COL_MENU_ID, (String) objKey[i]);
            ttObjParamsSql.put(VoCoUserGrpMenu.COL_MENU_LVL, ttStrParamsMenu.get((String) objKey[i]));

            TTLog.debug("modifyGrpMenu || menuId   :: " + (String) objKey[i], this.getClass());
            TTLog.debug("modifyGrpMenu || menuLvl  :: " + ttStrParamsMenu.get((String) objKey[i]), this.getClass());

            daoCoGroup.insertGrpMenu(ttObjParamsSql);
        }

        return nextUserGrpNo;
    }

    /**
     * 사용자그룹ID 중복을 조회한다.
     * @param ttObjParamsSql 검색정보
     * @return 체크 결과 -1:사용불가, 0:검색문자열"", 1:사용불가
     */
    public int checkUserGrpId(CoTtObjParams ttObjParamsSql) {

        int rsCnt = daoCoGroup.checkUserGrpId(ttObjParamsSql);

        return rsCnt;
    }


    /**
     * 그룹검색리스트 취득 <br />
     * @param ttObjParams 검색 파라미터
     * @return 검색리스트
     */
    public List<VoCoDept> getDeptSearchListAll(CoTtObjParams ttObjParams) {

        //VoCoDept voCoDept = (VoCoDept) ttObjParams.get("voCoDept");

        //페이징파라미터설정
        //CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voCoDept.getFirstIndex(), voCoDept.getRecordCountPerPage());
        //ttObjParamsSql.put("searchDeptCd", UtCoStringUtils.trim(voCoDept.getSearchDeptCd()));
        //ttObjParamsSql.put("searchDeptNm", UtCoStringUtils.trim(voCoDept.getSearchDeptNm()));
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        return daoCoDept.getDeptSearchListAll(ttObjParamsSql);
    }

    /*#########################################################################################
     * DWR 사용자그룹 선택
     *######################################################################################### */
    /**
     * 사용자그룹리스트 취득 <br />
     * @param voCoUserGrp 검색 파라미터
     * @return 검색리스트
     */
    public List<VoCoUserGrp> getSearchUserGroupListForDwr(VoCoUserGrp voCoUserGrp) {

        if (voCoUserGrp.getPageNo() > 1) {
            voCoUserGrp.setFirstIndex((voCoUserGrp.getPageNo() - 1) * voCoUserGrp.getViewRecordNo());
        } else {
            voCoUserGrp.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voCoUserGrp.getFirstIndex(), voCoUserGrp.getRecordCountPerPage());
        ttObjParamsSql.put("searchStatus", UtCoStringUtils.trim(voCoUserGrp.getSearchStatus()));
        ttObjParamsSql.put("searchGrpId", UtCoStringUtils.trim(voCoUserGrp.getSearchGrpId()));
        ttObjParamsSql.put("searchGrpNm", UtCoStringUtils.trim(voCoUserGrp.getSearchGrpNm()));

        return daoCoGroup.getGrpSearchListForPage(ttObjParamsSql);
    }

    /**
     * 사용자그룹리스트 총 갯수 취득 및 페이징<br />
     *
     * @param voCoUserGrp 검색 파라미터
     * @return 토탈카운트
     */
    public String paginateUserGroupListForDwr(VoCoUserGrp voCoUserGrp) {

        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("searchStatus", UtCoStringUtils.trim(voCoUserGrp.getSearchStatus()));
        ttObjParamsSql.put("searchGrpId", UtCoStringUtils.trim(voCoUserGrp.getSearchGrpId()));
        ttObjParamsSql.put("searchGrpNm", UtCoStringUtils.trim(voCoUserGrp.getSearchGrpNm()));

        int totalRecordNo = daoCoGroup.getGrpSearchListTotCnt(ttObjParamsSql);

        VoCoPagerHelper pagerHelper =  UtPagerUtils.paginate(voCoUserGrp, totalRecordNo);
        String resultHtml = UtPagerUtils.generatePagerHtml(pagerHelper, "selectUserGrpListDwr");
        return resultHtml;
    }

    /**
     * 그룹에 속한 user list 총 갯수 <br />
     * @param ttObjParams 검색 파라미터
     * @return 토탈카운트
     */
    public int getUserYnCnt(CoTtObjParams ttObjParams) {

        VoCoUserGrp voCoUserGrp = (VoCoUserGrp) ttObjParams.get("voCoUserGrp");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        int result = 0;
        if (!UtCoStringUtils.isEmpty(voCoUserGrp.getDelArr())) {
            String[] chkUser = voCoUserGrp.getDelArr().split(",");

            for (int i = 0; i < chkUser.length; ++i) {
                ttObjParamsSql.put("userGrpNo", Integer.parseInt(chkUser[i]));
                result = daoCoGroup.getUserYnCnt(ttObjParamsSql);
                if (result > 0) {
                   break;
                }
            }
        }
        return result;
//        ttObjParamsSql.put("userGrpNo", UtCoStringUtils.trim(voCoUserGrp.getUserGrpNo()));

    }

}