/*
 * Copyright (C) 2005-2013 THINK TREE. All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of korea.think-tree.com.,LTD. ("Confidential Information").
 */
package tt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tt.bean.VoApprInfo;
import tt.bean.VoExctDraft;
import tt.bean.VoPcBasic;
import tt.com.CoTtObjParams;

/**
 * <pre>
 * tt.com.dao
 *    |_ DaoCoDept.java
 *
 * DESC : 부서관련 DAO 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 8. 오전 10:01:47
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 8.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
@Repository("daoSecuPrint")
public class DaoSecuPrint extends TtSecuPrintBaseDao {

    @SuppressWarnings("unchecked")
    public List<CoTtObjParams> getDeptList() {
        return list("secuPrint.dept.list." + dbType, null);
    }
    
    /*public void insertApprUserOutput(CoTtObjParams ttObjParamsSql) throws Exception {
        insert("DaoSecuPrint.insert.appr.user.output." + dbType , ttObjParamsSql);
    }*/
    
    /**
     * @param ttObjParamsSql 파라미터
     * @throws Exception Escort 예외신청
     */
    public void insertApprExceptionOutput(CoTtObjParams ttObjParamsSql) throws Exception {
        insert("DaoSecuPrint.insert.appr.exception.output." + dbType , ttObjParamsSql);
    }

}
