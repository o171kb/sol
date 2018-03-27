package tt.module.admin.selfcheck;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tt.base.module.TtBaseService;
import tt.bean.VoBoard;
import tt.bean.VoPcCheck;
import tt.bean.VoSelfDiaGroup;
import tt.bean.VoSelfDiaItem;
import tt.bean.VoSelfDiagnostic;
import tt.com.CoTtObjParams;
import tt.com.module.file.SvCoFileService;
import tt.com.module.login.SvCoLoginService;
import tt.com.utils.UtCoDateUtils;
import tt.com.utils.UtCoStringUtils;
import tt.constant.CsConstDef;
import tt.module.CpDocument;


/**
 * <pre>
 * tt.module.admin.selfcheck
 *    |_ SvMyPcCheckService.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 5. 6. 오후 3:00:08
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 5. 6.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Service("svMyPcCheckService")
public class SvMyPcCheckService extends TtBaseService  {

        /**
         * 자가진단 그룹리스트<br/>
         * @param ttObjParams 검색 파라미터
         * @return 그룹리스트
         */
//        public List<VoSelfDiaGroup> getDiaGroup(CoTtObjParams ttObjParams) {
//
//            CoTtObjParams ttObjParamsSql = new CoTtObjParams();
//
//            return daoMyPcCheck.getDiaGroup(ttObjParamsSql);
//
//        }

        /**
         * 자가진단 아이템 리스트<br/>
         * @param ttObjParams 검색 파라미터
         * @return 그룹아이템 리스트
         */
        public List<VoSelfDiaItem> getDiaItem(CoTtObjParams ttObjParams) {
            VoSelfDiaItem vo = (VoSelfDiaItem) ttObjParams.get("voSelfDiaItem");

            CoTtObjParams ttObjParamsSql = new CoTtObjParams();

            return daoMyPcCheck.getDiaItem(ttObjParamsSql);
        }

        /**
         * 자가진단 아이템 리스트 총 갯수 취득<br/>
         * @param ttObjParams 검색 파라미터
         * @return 토탈카운트
         */
        public int getDiaItemTotCnt(CoTtObjParams ttObjParams) {

            VoSelfDiaGroup vo = (VoSelfDiaGroup) ttObjParams.get("voSelfDiaGroup");

            CoTtObjParams ttObjParamsSql = new CoTtObjParams();

            return daoMyPcCheck.getDiaItemTotCnt(ttObjParamsSql);
        }


        /**
         * 자가진단 결과리스트 PK <br/>
         * @param ttObjParams 검색 파라미터
         * @return SelfDiagnosticIdx list
         */
        public List<VoPcCheck> getResultPKList(CoTtObjParams ttObjParams) {
            VoPcCheck vo = (VoPcCheck) ttObjParams.get("voPcCheck");

            CoTtObjParams ttObjParamsSql = new CoTtObjParams();
            ttObjParamsSql.put("userId", vo.getUserId());

            return daoMyPcCheck.getResultPKList(ttObjParamsSql);
        }

        /**
         * 안전/취약 결과 <br/>
         * @param ttObjParams 검색 파라미터
         * @return 결과 카운트
         */
        public List<VoPcCheck> getSafetyList(CoTtObjParams ttObjParams) {
            VoPcCheck vo = (VoPcCheck) ttObjParams.get("voPcCheck");

            CoTtObjParams ttObjParamsSql = new CoTtObjParams();
            ttObjParamsSql.put("selfDiagnosticIdx", vo.getIdx());
            ttObjParamsSql.put("userId", vo.getUserId());

            return daoMyPcCheck.getSafetyList(ttObjParamsSql);
        }


        /**
         * 자가진단 결과리스트 <br/>
         * @param ttObjParams 검색 파라미터
         * @return 결과리스트
         */
        public List<VoPcCheck> getResultList(CoTtObjParams ttObjParams) {
            VoPcCheck vo = (VoPcCheck) ttObjParams.get("voPcCheck");

            CoTtObjParams ttObjParamsSql = new CoTtObjParams();
            ttObjParamsSql.put("userId", vo.getUserId());
            ttObjParamsSql.put("idx", vo.getIdx());
//            ttObjParamsSql.put("selfDiagnosticIdx", vo.getSelfDiagnosticIdx());

            return daoMyPcCheck.getResultList(ttObjParamsSql);
        }

        /**
         * 자가진단 결과리스트 총 갯수 취득 <br/>
         * @param ttObjParams 검색 파라미터
         * @return 토탈카운트
         */
        public int getResultListTotCnt(CoTtObjParams ttObjParams) {

            VoPcCheck vo = (VoPcCheck) ttObjParams.get("voPcCheck");
            CoTtObjParams ttObjParamsSql = new CoTtObjParams();
            ttObjParamsSql.put("userId", vo.getUserId());

            return daoMyPcCheck.getDiaItemTotCnt(ttObjParamsSql);
        }


        /**
         * 자가진단 PC정보 등록 <br/>
         * @param ttObjParams 검색 파라미터
         * @return 진단PC정보
         * @throws Exception 예외
         */
        @Transactional
        public int insertMyPcCheck(CoTtObjParams ttObjParams) throws Exception {
            VoPcCheck vo = (VoPcCheck) ttObjParams.get("voPcCheck");

            CoTtObjParams ttObjParamsSql = new CoTtObjParams();
            ttObjParamsSql.put("serialNumber", vo.getSerialNumber());
            ttObjParamsSql.put("userId", vo.getUserId());
            ttObjParamsSql.put("macAddress", vo.getMacAddress());
            ttObjParamsSql.put("osType", vo.getOsType());

            return daoMyPcCheck.insertMyPcCheck(ttObjParamsSql);
        }

        /**
         * 자가진단 PC점검 결과등록 <br/>
         * @param ttObjParams 검색 파라미터
         * @throws Exception 예외
         */
        @Transactional
        public void insertPcCheckResult(CoTtObjParams ttObjParams) throws Exception {
            VoPcCheck vo = (VoPcCheck) ttObjParams.get("voPcCheck");

            CoTtObjParams ttObjParamsSql = new CoTtObjParams();
            ttObjParamsSql.put("selfDiagnosticIdx", vo.getSelfDiagnosticIdx());
            ttObjParamsSql.put("itemId", vo.getItemId());
            ttObjParamsSql.put("isSafety", vo.getIsSafety());
            ttObjParamsSql.put("description", vo.getDescription());

            daoMyPcCheck.insertPcCheckResult(ttObjParamsSql);

        }

        /**
         * 자가진단 PC점검 안전PC결과 등록 <br/>
         * @param ttObjParams 검색 파라미터
         * @throws Exception 예외
         */
        public void updateAllSafety(CoTtObjParams ttObjParams) throws Exception {
            VoPcCheck vo = (VoPcCheck) ttObjParams.get("voPcCheck");

            CoTtObjParams ttObjParamsSql = new CoTtObjParams();

            ttObjParamsSql.put("selfDiagnosticIdx", vo.getSelfDiagnosticIdx());
            ttObjParamsSql.put("allSafety", vo.getAllSafety());

            daoMyPcCheck.updateAllSafety(ttObjParamsSql);
        }

        /**
         * 자가진단 그룹아이템 ChkValue 리스트 <br/>
         * @param params 검색 파라미터
         * @return 토탈카운트
         */
        public List<VoPcCheck> getItemChkValue(CoTtObjParams params) {

            return daoMyPcCheck.getItemChkValue(params);
        }

}