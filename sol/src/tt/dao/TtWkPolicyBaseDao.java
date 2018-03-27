/*
 * Copyright (C) 2005-2013 THINK TREE. All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of korea.think-tree.com.,LTD. ("Confidential Information").
 */
package tt.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


/**
 * <pre>
 * tt.dao
 *    |_ TtWkPolicyBaseDao.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author hk-kim
 * @Date 2013. 5. 20. 오후 8:38:49
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 5. 20.		hk-kim				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class TtWkPolicyBaseDao extends EgovAbstractDAO {

    /**
     * DB별 sqlMapClient 지정
     */
    /* (non-Javadoc)
     * @see egovframework.rte.psl.dataaccess.EgovAbstractDAO#setSuperSqlMapClient(com.ibatis.sqlmap.client.SqlMapClient)
     */
    @Resource(name = "sqlMapClient_wkPolicy")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
        super.setSuperSqlMapClient(sqlMapClient);
    }


    // 데이타베이스타입
    @Value("${db.type}")
    protected String dbType;

    /**
     * DWR용 변수설정 <br />
     *
     * @param dbType DWR용 변수설정
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

}
