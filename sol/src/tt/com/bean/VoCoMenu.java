/*
 * Copyright (C) 2005-2013 THINK TREE. All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of korea.think-tree.com.,LTD. ("Confidential Information").
 */
package tt.com.bean;

import tt.com.utils.UtCoFileNameUtils;

/**
 * <pre>
 * tt.com.bean
 *    |_ VoCoMenu.java
 *
 * DESC : 메뉴 VO 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 2. 27. 오후 8:00:04
 * @history :
 *          --------------------------------------------------------------------
 *          --- 변경일 작성자 변경내용 ----------- -------------------
 *          --------------------------------------- 2013. 2. 27. ks-lee 최초 작성
 *          ----
 *          -------------------------------------------------------------------
 *
 */
public class VoCoMenu extends VoCoBase {

    private static final long serialVersionUID = -2410748323561735492L;

    /** 메뉴아이디 칼럼명 */
    public static final String COL_MENU_ID = "MENU_ID";
    /** 메뉴그룹분류번호 칼럼명 */
    public static final String COL_MENU_GRP_NO = "MENU_GRP_NO";
    /** 메뉴그룹분류명 칼럼명 */
    public static final String COL_MENU_GRP_NM = "MENU_GRP_NM";
    /** 메뉴그룹분류아이콘 칼럼명 */
    public static final String COL_MENU_GRP_ICON = "MENU_GRP_ICON";
    /** 메뉴번호 칼럼명 */
    public static final String COL_MENU_SEQ_NO = "MENU_SEQ_NO";
    /** 메뉴명 칼럼명 */
    public static final String COL_MENU_NM = "MENU_NM";
    /** 메뉴아이콘 칼럼명 */
    public static final String COL_MENU_ICON = "MENU_ICON";
    /** 메뉴설명 칼럼명 */
    public static final String COL_MENU_EXP = "MENU_EXP";
    /** 메뉴액션 칼럼명 */
    public static final String COL_MENU_ACTION = "MENU_ACTION";
    /** 메뉴클래스 칼럼명 */
    public static final String COL_MENU_CL = "MENU_CL";

    /** 메뉴아이디 */
    private String menuId;
    /** 메뉴그룹분류번호 */
    private String menuGrpNo;
    /** 메뉴그룹분류명 */
    private String menuGrpNm;
    /** 메뉴그룹분류아이콘 */
    private String menuGrpIcon;
    /** 메뉴번호 */
    private String menuSeqNo;
    /** 메뉴명 */
    private String menuNm;
    /** 메뉴아이콘 */
    private String menuIcon;
    /** 메뉴설명 */
    private String menuExp;
    /** 메뉴액션 */
    private String menuAction;
    /** 메뉴클래스 */
    private String menuCl;
    /** 메뉴액션명 */
    private String menuActionNm;
    /** 메뉴레벨 */
    private String menuLvl;
    
    /** 마스터권한 */
    private String mstYn;
    
    /** 그룹관리권한 */
    private String grpYn;
    
    /** 자기결재레벨 */
    private String selfYn;


    /**
     * 메뉴액션명 를 취득 <br />
     * @return menuActionNm 메뉴액션명
     */
    public String getMenuActionNm() {
        UtCoFileNameUtils.getBaseName(menuAction);
        return menuActionNm;
    }

    /**
     * 메뉴아이디 를 취득 <br />
     * @return menuId 메뉴아이디
     */
    public String getMenuId() {
        return menuId;
    }
    /**
     * 메뉴아이디 를 설정 <br />
     * @param menuId 메뉴아이디 설정
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    /**
     * 메뉴그룹분류번호 를 취득 <br />
     * @return menuGrpNo 메뉴그룹분류번호
     */
    public String getMenuGrpNo() {
        return menuGrpNo;
    }
    /**
     * 메뉴그룹분류번호 를 설정 <br />
     * @param menuGrpNo 메뉴그룹분류번호 설정
     */
    public void setMenuGrpNo(String menuGrpNo) {
        this.menuGrpNo = menuGrpNo;
    }
    /**
     * 메뉴그룹분류명 를 취득 <br />
     * @return menuGrpNm 메뉴그룹분류명
     */
    public String getMenuGrpNm() {
        return menuGrpNm;
    }
    /**
     * 메뉴그룹분류명 를 설정 <br />
     * @param menuGrpNm 메뉴그룹분류명 설정
     */
    public void setMenuGrpNm(String menuGrpNm) {
        this.menuGrpNm = menuGrpNm;
    }
    /**
     * 메뉴그룹분류아이콘 를 취득 <br />
     * @return menuGrpIcon 메뉴그룹분류아이콘
     */
    public String getMenuGrpIcon() {
        return menuGrpIcon;
    }
    /**
     * 메뉴그룹분류아이콘 를 설정 <br />
     * @param menuGrpIcon 메뉴그룹분류아이콘 설정
     */
    public void setMenuGrpIcon(String menuGrpIcon) {
        this.menuGrpIcon = menuGrpIcon;
    }
    /**
     * 메뉴번호 를 취득 <br />
     * @return menuSeqNo 메뉴번호
     */
    public String getMenuSeqNo() {
        return menuSeqNo;
    }
    /**
     * 메뉴번호 를 설정 <br />
     * @param menuSeqNo 메뉴번호 설정
     */
    public void setMenuSeqNo(String menuSeqNo) {
        this.menuSeqNo = menuSeqNo;
    }
    /**
     * 메뉴명 를 취득 <br />
     * @return menuNm 메뉴명
     */
    public String getMenuNm() {
        return menuNm;
    }
    /**
     * 메뉴명 를 설정 <br />
     * @param menuNm 메뉴명 설정
     */
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }
    /**
     * 메뉴아이콘 를 취득 <br />
     * @return menuIcon 메뉴아이콘
     */
    public String getMenuIcon() {
        return menuIcon;
    }
    /**
     * 메뉴아이콘 를 설정 <br />
     * @param menuIcon 메뉴아이콘 설정
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
    /**
     * 메뉴설명 를 취득 <br />
     * @return menuExp 메뉴설명
     */
    public String getMenuExp() {
        return menuExp;
    }
    /**
     * 메뉴설명 를 설정 <br />
     * @param menuExp 메뉴설명 설정
     */
    public void setMenuExp(String menuExp) {
        this.menuExp = menuExp;
    }
    /**
     * 메뉴액션 를 취득 <br />
     * @return menuAction 메뉴액션
     */
    public String getMenuAction() {
        return menuAction;
    }
    /**
     * 메뉴액션 를 설정 <br />
     * @param menuAction 메뉴액션 설정
     */
    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
        this.menuActionNm = UtCoFileNameUtils.getBaseName(menuAction);
    }
    /**
     * 메뉴클래스 를 취득 <br />
     * @return menuCl 메뉴클래스
     */
    public String getMenuCl() {
        return menuCl;
    }
    /**
     * 메뉴클래스 를 설정 <br />
     * @param menuCl 메뉴클래스 설정
     */
    public void setMenuCl(String menuCl) {
        this.menuCl = menuCl;
    }

    /**
     * 메뉴레벨 반환한다. <br />
     * @return menuLvl 메뉴레벨
     */
    public String getMenuLvl() {
        return menuLvl;
    }

    /**
     * 메뉴레벨 설정한다. <br />
     * @param menuLvl 메뉴레벨
     */
    public void setMenuLvl(String menuLvl) {
        this.menuLvl = menuLvl;
    }

    public String getGrpYn() {
        return grpYn;
    }

    public void setGrpYn(String grpYn) {
        this.grpYn = grpYn;
    }

    public String getSelfYn() {
        return selfYn;
    }

    public void setSelfYn(String selfYn) {
        this.selfYn = selfYn;
    }

    public String getMstYn() {
        return mstYn;
    }

    public void setMstYn(String mstYn) {
        this.mstYn = mstYn;
    }
    

}
