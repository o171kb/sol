/*
 * Copyright (C) 2005-2013 THINK TREE. All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of korea.think-tree.com.,LTD. ("Confidential Information").
 */
package tt.com.bean;

/**
 * <pre>
 * tt.com.bean
 *    |_ VoCoUserGrpMenu.java
 *
 * DESC : 관리자 메뉴관리 VO 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 2. 27. 오후 7:18:02
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 2. 27.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class VoCoUserGrpMenu extends VoCoMenu {

    /** 시리얼 버젼ID */
    private static final long serialVersionUID = 1585079448795069355L;



    /** 관리자그룹번호 칼럼명 */
    public static final String COL_USER_GRP_NO = "USER_GRP_NO";
    /** 관리자메뉴레벨 칼럼명 */
    public static final String COL_MENU_LVL = "MENU_LVL";


    /** 관리자그룹번호 */
    private String userGrpNo;

    /** 관리자메뉴레벨 */
    private String menuLvl;


    /**
     * 관리자그룹번호 를 취득 <br />
     * @return userGrpNo 관리자그룹번호
     */
    public String getUserGrpNo() {
        return userGrpNo;
    }

    /**
     * 관리자그룹번호 를 설정 <br />
     * @param userGrpNo 관리자그룹번호 설정
     */
    public void setUserGrpNo(String userGrpNo) {
        this.userGrpNo = userGrpNo;
    }

    /**
     * 관리자메뉴레벨 를 취득 <br />
     * @return menuLvl 관리자메뉴레벨
     */
    public String getMenuLvl() {
        return menuLvl;
    }

    /**
     * 관리자메뉴레벨 를 설정 <br />
     * @param menuLvl 관리자메뉴레벨 설정
     */
    public void setMenuLvl(String menuLvl) {
        this.menuLvl = menuLvl;
    }

}
