package tt.module.admin.selfcheck;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import tt.base.module.TtBaseService;
import tt.bean.VoPcBasic;
import tt.bean.VoPcCheck;
import tt.bean.VoSelfDiagnostic;
import tt.com.CoTtObjParams;
import tt.com.bean.VoCoCodeDtl;
import tt.com.bean.VoCoDept;
import tt.com.bean.VoCoPagerHelper;
import tt.com.bean.VoCoUserGrp;
import tt.com.utils.UtCoStringUtils;
import tt.utils.UtPagerUtils;



/**
 * <pre>
 * tt.module.admin.selfcheck
 *    |_ SvDeptPcCheckService.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author Administrator
 * @Date 2013. 5. 12. 오후 6:28:09
 * @history :
 *  -----------------------------------------------------------------------
 *  변경일             작성자                     변경내용
 *  ----------- ------------------- ---------------------------------------
 *  2013. 5. 12.        Administrator               최초 작성
 *  -----------------------------------------------------------------------
 *
 */
@Service("svDeptPcCheckService")
public class SvDeptPcCheckService extends TtBaseService  {


    /**
     * 부서PC점검현황 <br/>
     * @param ttObjParams 검색 파라미터
     * @return 부서명,부서번호
     * @throws Exception 예외
     */
    public VoCoDept getMyDept(CoTtObjParams ttObjParams) throws Exception {
        VoCoDept vo = (VoCoDept) ttObjParams.get("voCoDept");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();

        ttObjParamsSql.put("userId", vo.getUserId());
        return daoDeptPcCheck.getMyDept(ttObjParamsSql);
    }

    /**
     * 대상자 리스트 토탈 <br/>
     * @param ttObjParams 검색 파라미터
     * @return 대상자 토탈카운트
     * @throws Exception 예외
     */
    public int getDeptRegister(CoTtObjParams ttObjParams) throws Exception {
        VoCoDept vo = (VoCoDept) ttObjParams.get("myDept");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("deptCd", vo.getDeptCd());
        return daoDeptPcCheck.getDeptRegister(ttObjParamsSql);
    }


    //########################### DWR ##########################################
    /**
     * 대상자리스트 취득 <br />
     * @param voSelfDiagnostic 검색 파라미터
     * @return 검색리스트
     */
    public List<VoSelfDiagnostic> chkDeptUser(VoSelfDiagnostic voSelfDiagnostic) {

        if (voSelfDiagnostic.getPageNo() > 1) {
            voSelfDiagnostic.setFirstIndex((voSelfDiagnostic.getPageNo() - 1) * voSelfDiagnostic.getViewRecordNo());
        } else {
            voSelfDiagnostic.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voSelfDiagnostic.getFirstIndex(), voSelfDiagnostic.getRecordCountPerPage());
        ttObjParamsSql.put("deptCd", UtCoStringUtils.trim(voSelfDiagnostic.getDeptCd()));
        ttObjParamsSql.put("isExcel", UtCoStringUtils.trim(voSelfDiagnostic.getIsExcel()));
        ttObjParamsSql.put("searchDate", voSelfDiagnostic.getSearchDate());
        ttObjParamsSql.put("safeStandard", UtCoStringUtils.trim(voSelfDiagnostic.getSafeStandard()));

        return daoDeptPcCheck.chkDeptUser(ttObjParamsSql);
    }

    /**
     * 대상자리스트 총 갯수 취득 및 페이징 <br />
     *
     * @param voSelfDiagnostic 검색 파라미터
     * @throws Exception 예외
     * @return 토탈카운트
     */
    public String paginateChkDeptUser(VoSelfDiagnostic voSelfDiagnostic) throws Exception {
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("deptCd", UtCoStringUtils.trim(voSelfDiagnostic.getDeptCd()));
        ttObjParamsSql.put("isExcel", UtCoStringUtils.trim(voSelfDiagnostic.getIsExcel()));
        ttObjParamsSql.put("searchDate", voSelfDiagnostic.getSearchDate());
        ttObjParamsSql.put("safeStandard", UtCoStringUtils.trim(voSelfDiagnostic.getSafeStandard()));

        int totalRecordNo = daoDeptPcCheck.paginateChkDeptUser(ttObjParamsSql);

        VoCoPagerHelper pagerHelper =  UtPagerUtils.paginate(voSelfDiagnostic, totalRecordNo);
        String resultHtml = UtPagerUtils.generatePagerHtml(pagerHelper, "selectUserGrpListDwr");
        return resultHtml;
    }

    /**
     * 점검자리스트 취득 <br />
     * @param voSelfDiagnostic 검색 파라미터
     * @return 검색리스트
     */
    public List<VoSelfDiagnostic> chkDeptPcUser(VoSelfDiagnostic voSelfDiagnostic) {
        if (voSelfDiagnostic.getPageNo() > 1) {
            voSelfDiagnostic.setFirstIndex((voSelfDiagnostic.getPageNo() - 1) * voSelfDiagnostic.getViewRecordNo());
        } else {
            voSelfDiagnostic.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voSelfDiagnostic.getFirstIndex(), voSelfDiagnostic.getRecordCountPerPage());
        ttObjParamsSql.put("deptCd", UtCoStringUtils.trim(voSelfDiagnostic.getDeptCd()));
        ttObjParamsSql.put("isExcel", UtCoStringUtils.trim(voSelfDiagnostic.getIsExcel()));
        ttObjParamsSql.put("searchDate", voSelfDiagnostic.getSearchDate());
        ttObjParamsSql.put("safeStandard", UtCoStringUtils.trim(voSelfDiagnostic.getSafeStandard()));

        return daoDeptPcCheck.chkDeptPcUser(ttObjParamsSql);
    }

    /**
     * 점검자리스트 총 갯수 취득 및 페이징 <br />
     *
     * @param voSelfDiagnostic 검색 파라미터
     * @throws Exception 예외
     * @return 토탈카운트
     */
    public String paginateChkUser(VoSelfDiagnostic voSelfDiagnostic) throws Exception {
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("deptCd", UtCoStringUtils.trim(voSelfDiagnostic.getDeptCd()));
        ttObjParamsSql.put("isExcel", UtCoStringUtils.trim(voSelfDiagnostic.getIsExcel()));
        ttObjParamsSql.put("searchDate", voSelfDiagnostic.getSearchDate());
        ttObjParamsSql.put("safeStandard", UtCoStringUtils.trim(voSelfDiagnostic.getSafeStandard()));

        int totalRecordNo = daoDeptPcCheck.paginateChkUser(ttObjParamsSql);

        VoCoPagerHelper pagerHelper =  UtPagerUtils.paginate(voSelfDiagnostic, totalRecordNo);
        String resultHtml = UtPagerUtils.generatePagerHtml(pagerHelper, "selectUserGrpListDwr");
        return resultHtml;
    }

    /**
     * 점검자중 결과리스트 취득 <br />
     * @param ttObjParams 검색 파라미터
     * @return 개인PC 토탈카운트
     * @throws Exception 예외
     */
    public List<VoPcBasic> getIncops5MyPc(VoPcBasic voPcBasic) throws Exception {
        if (voPcBasic.getPageNo() > 1) {
            voPcBasic.setFirstIndex((voPcBasic.getPageNo() - 1) * voPcBasic.getViewRecordNo());
        } else {
            voPcBasic.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voPcBasic.getFirstIndex(), voPcBasic.getRecordCountPerPage());

        return daoIncops5.getIncops5MyPc(ttObjParamsSql);
    }


    /**
     * 점검 PC 리스트 취득 <br />
     * @param voSelfDiagnostic 검색 파라미터
     * @return 검색리스트
     */
    public List<VoSelfDiagnostic> chkDeptPc(VoSelfDiagnostic voSelfDiagnostic) {
        if (voSelfDiagnostic.getPageNo() > 1) {
            voSelfDiagnostic.setFirstIndex((voSelfDiagnostic.getPageNo() - 1) * voSelfDiagnostic.getViewRecordNo());
        } else {
            voSelfDiagnostic.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voSelfDiagnostic.getFirstIndex(), voSelfDiagnostic.getRecordCountPerPage());
        ttObjParamsSql.put("deptCd", UtCoStringUtils.trim(voSelfDiagnostic.getDeptCd()));
        ttObjParamsSql.put("isExcel", UtCoStringUtils.trim(voSelfDiagnostic.getIsExcel()));
        ttObjParamsSql.put("searchDate", voSelfDiagnostic.getSearchDate());
        ttObjParamsSql.put("safeStandard", UtCoStringUtils.trim(voSelfDiagnostic.getSafeStandard()));

        return daoDeptPcCheck.chkDeptPc(ttObjParamsSql);
    }

    /**
     * 점검 PC 리스트 총 갯수 취득 및 페이징 <br />
     *
     * @param voSelfDiagnostic 검색 파라미터
     * @throws Exception 예외
     * @return 토탈카운트
     */
    public String paginateChkDeptPc(VoSelfDiagnostic voSelfDiagnostic) throws Exception {
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("deptCd", UtCoStringUtils.trim(voSelfDiagnostic.getDeptCd()));
        ttObjParamsSql.put("isExcel", UtCoStringUtils.trim(voSelfDiagnostic.getIsExcel()));
        ttObjParamsSql.put("searchDate", voSelfDiagnostic.getSearchDate());
        ttObjParamsSql.put("safeStandard", UtCoStringUtils.trim(voSelfDiagnostic.getSafeStandard()));

        int totalRecordNo = daoDeptPcCheck.paginateChkDeptPc(ttObjParamsSql);

        VoCoPagerHelper pagerHelper =  UtPagerUtils.paginate(voSelfDiagnostic, totalRecordNo);
        String resultHtml = UtPagerUtils.generatePagerHtml(pagerHelper, "selectUserGrpListDwr");
        return resultHtml;
    }

    /**
     * 취약항목 리스트 취득 <br />
     * @param voSelfDiagnostic 검색 파라미터
     * @return 검색리스트
     */
    public List<VoSelfDiagnostic> chkDeptDanger(VoSelfDiagnostic voSelfDiagnostic) {
        if (voSelfDiagnostic.getPageNo() > 1) {
            voSelfDiagnostic.setFirstIndex((voSelfDiagnostic.getPageNo() - 1) * voSelfDiagnostic.getViewRecordNo());
        } else {
            voSelfDiagnostic.setFirstIndex(0);
        }

        //페이징파라미터설정
        CoTtObjParams ttObjParamsSql = getParamsObjOfPageInfoSetted(voSelfDiagnostic.getFirstIndex(), voSelfDiagnostic.getRecordCountPerPage());
        ttObjParamsSql.put("deptCd", UtCoStringUtils.trim(voSelfDiagnostic.getDeptCd()));
        ttObjParamsSql.put("isExcel", UtCoStringUtils.trim(voSelfDiagnostic.getIsExcel()));
        ttObjParamsSql.put("searchDate", voSelfDiagnostic.getSearchDate());
        ttObjParamsSql.put("safeStandard", UtCoStringUtils.trim(voSelfDiagnostic.getSafeStandard()));

        return daoDeptPcCheck.chkDeptDanger(ttObjParamsSql);
    }

    /**
     * 취약항목 총 갯수 취득 및 페이징 <br />
     * @throws Exception 예외
     * @param voSelfDiagnostic 검색 파라미터
     * @return 검색리스트
     */
    public String paginateChkDeptDanger(VoSelfDiagnostic voSelfDiagnostic) throws Exception {
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("deptCd", UtCoStringUtils.trim(voSelfDiagnostic.getDeptCd()));
        ttObjParamsSql.put("isExcel", UtCoStringUtils.trim(voSelfDiagnostic.getIsExcel()));
        ttObjParamsSql.put("searchDate", voSelfDiagnostic.getSearchDate());
        ttObjParamsSql.put("safeStandard", UtCoStringUtils.trim(voSelfDiagnostic.getSafeStandard()));

        int totalRecordNo = daoDeptPcCheck.paginateChkDeptDanger(ttObjParamsSql);

        VoCoPagerHelper pagerHelper =  UtPagerUtils.paginate(voSelfDiagnostic, totalRecordNo);
        String resultHtml = UtPagerUtils.generatePagerHtml(pagerHelper, "selectUserGrpListDwr");
        return resultHtml;
    }

    /**
     * 부서PC점검현황 점검자<br/>
     * @param ttObjParams 검색 파라미터
     * @return 점검자 토탈카운트
     * @throws Exception 예외
     */
    /*public int getPcChkRegister(CoTtObjParams ttObjParams) throws Exception {
        VoCoDept vo = (VoCoDept) ttObjParams.get("myDept");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("deptNo", vo.getDeptNo());
        return daoDeptPcCheck.getPcChkRegister(ttObjParamsSql);
    }
*/

    /**
     * 부서원 개인PC 보유수 <br/>
     * @param ttObjParams 검색 파라미터
     * @return 개인PC 토탈카운트
     * @throws Exception 예외
     */
    public int getIncops5MyPcCnt(CoTtObjParams ttObjParams) throws Exception {
        VoPcBasic vo = (VoPcBasic) ttObjParams.get("voPcBasic");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("empNo", vo.getEmpNo());

        return daoIncops5.getIncops5MyPcCnt(ttObjParamsSql);
    }

    /**
     * 부서원 개인PC 정보 <br/>
     * @param ttObjParams 검색 파라미터
     * @return 부서원 개인PC 정보
     * @throws Exception 예외
     */
    public List<VoPcBasic> getIncops5MyPcInfoList(CoTtObjParams ttObjParams) throws Exception {
        VoPcBasic vo = (VoPcBasic) ttObjParams.get("voPcBasic");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("empNo", vo.getEmpNo());

        return daoIncops5.getIncops5MyPcInfoList(ttObjParamsSql);
    }

    /**
     * 부서원 개인의 자가진단한 PC수 <br/>
     * @param ttObjParams 검색 파라미터
     * @return 부서원 개인의 자가진단한 PC수
     * @throws Exception 예외
     */
    public int getMyPcSelfChkCnt(CoTtObjParams ttObjParams) throws Exception {
        VoPcBasic vo = (VoPcBasic) ttObjParams.get("voPcBasic");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("empNo", vo.getEmpNo());
        ttObjParamsSql.put("mac", vo.getMac());
        ttObjParamsSql.put("searchDate", vo.getSearchDate());

        return daoDeptPcCheck.getMyPcSelfChkCnt(ttObjParamsSql);
    }


    /**
     * 각 부서원이 자가진단한 SELF_DIAGNOSTIC 결과 테이블의 IDX <br/>
     * @param ttObjParams 검색 파라미터
     * @return list
     * @throws Exception 예외
     */
    public List<VoPcCheck> getSelfIdx(CoTtObjParams ttObjParams) throws Exception {
        VoPcCheck vo = (VoPcCheck) ttObjParams.get("voPcCheck");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("macAddress", vo.getMacAddress());
        ttObjParamsSql.put("userId", vo.getUserId());
        ttObjParamsSql.put("searchDate", vo.getSearchDate());

        return daoDeptPcCheck.getSelfIdx(ttObjParamsSql);
    }

    /**
     * 각 부서원이 점검한 내역 (true / false 일때 카운트) <br/>
     * @param ttObjParams 검색 파라미터
     * @return 토탈 카운트
     * @throws Exception 예외
     */
    public int getSafetyIdx(CoTtObjParams ttObjParams) throws Exception {
        VoPcCheck vo = (VoPcCheck) ttObjParams.get("voPcCheck");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("selfDiagnosticIdx", vo.getIdx());
        ttObjParamsSql.put("isSafety", vo.getIsSafety());

        return daoDeptPcCheck.getSafetyIdx(ttObjParamsSql);

    }

    /**
     * 부서PC점검현황 해당부서원 아이디 리스트 <br/>
     * @param ttObjParams 검색 파라미터
     * @return 아이디 리스트
     * @throws Exception 예외
     */
    public List<VoCoDept> getRegisterIdList(CoTtObjParams ttObjParams) throws Exception {
        VoCoDept vo = (VoCoDept) ttObjParams.get("myDept");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        ttObjParamsSql.put("deptCd", vo.getDeptCd());
        return daoDeptPcCheck.getRegisterIdList(ttObjParamsSql);
    }

    /**
     * 부서PC점검현황<br/>
     * @param ttObjParams 검색 파라미터
     * @return 점검한 부서원들의 PC 토탈카운트
     * @throws Exception 예외
     */
    public int getRegisterEachPcCnt(CoTtObjParams ttObjParams) throws Exception {
        VoCoDept vo = (VoCoDept) ttObjParams.get("voCoDept");
        CoTtObjParams ttObjParamsSql = new CoTtObjParams();
        System.out.println("userId $$$$$$$$$$$$$$$$$$$$  : " +  vo.getUserId());
        ttObjParamsSql.put("userId", vo.getUserId());

        return daoDeptPcCheck.getRegisterEachPcCnt(ttObjParamsSql);
    }

}