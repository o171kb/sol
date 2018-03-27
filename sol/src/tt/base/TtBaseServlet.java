/*
 * Copyright (C) 2005-2013 THINK TREE. All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of korea.think-tree.com.,LTD. ("Confidential Information").
 */
package tt.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.runtime.HttpJspBase;

import tt.com.CoTtStrParams;
import tt.com.bean.VoCoMenu;
import tt.com.constant.CsCoConstDef;


/**
 * <pre>
 * tt.base
 *    |_ TtBaseServlet.java
 *
 * DESC : JSP 의 부모인 베이스 서블릿 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 3. 25. 오후 6:13:57
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 3. 25.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
//public class TtBaseServlet {
public class TtBaseServlet extends HttpJspBase {

    private static final long serialVersionUID = 1L;
    
    /** 메뉴수정레벨 */
    public static final int MENU_ROLL_MOD_LVL = 8;
    /** 메뉴열람레벨 */
    public static final int MENU_ROLL_VIEW_LVL = 7;

    /**
     * 세션에 담겨있는 메뉴레벨을 체크후 메뉴열람 권한을 체크
     * @param tts 세션
     * @param menuAction 메뉴액션명
     * @return true:열람가능 false:열람불가
     */
    public boolean hasViewAuth(TtSession tts, String menuAction) {

        boolean isHasViewAuth = false;

        CoTtStrParams menuStrParams = (CoTtStrParams) tts.get(CsCoConstDef.SS_KEY_017);

        int myMenuLvl = Integer.parseInt(menuStrParams.get(menuAction));
        int menuLvl = 0;

        @SuppressWarnings("unchecked")
        List<VoCoMenu> userMenuList = (List<VoCoMenu>) tts.get(CsCoConstDef.SS_KEY_018);

        for (VoCoMenu vo : userMenuList) {
            if (menuAction.equals(vo.getMenuAction())) {
                menuLvl = Integer.parseInt(vo.getMenuCl());
                break;
            }
        }

        if (myMenuLvl >= menuLvl && myMenuLvl >= MENU_ROLL_VIEW_LVL) {
            isHasViewAuth = true;
        }

        return isHasViewAuth;
    }

    /**
     * 세션에 담겨있는 메뉴레벨을 체크후 메뉴수정 권한을 체크
     * @param tts 세션
     * @param menuAction 메뉴액션명
     * @return true:수정가능 false:수정불가
     */
    public boolean hasModAuth(TtSession tts, String menuAction) {

        boolean isHasModAuth = false;

        CoTtStrParams menuStrParams = (CoTtStrParams) tts.get(CsCoConstDef.SS_KEY_017);

        int myMenuLvl = Integer.parseInt(menuStrParams.get(menuAction));
        int menuLvl = 0;

        @SuppressWarnings("unchecked")
        List<VoCoMenu> userMenuList = (List<VoCoMenu>) tts.get(CsCoConstDef.SS_KEY_018);

        for (VoCoMenu vo : userMenuList) {
            if (menuAction.equals(vo.getMenuAction())) {
                menuLvl = Integer.parseInt(vo.getMenuCl());
                break;
            }
        }

        if (myMenuLvl >= menuLvl && myMenuLvl >= MENU_ROLL_MOD_LVL) {
            isHasModAuth = true;
        }

        return isHasModAuth;
    }

    @Override
    public void _jspService(HttpServletRequest arg0, HttpServletResponse arg1)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        
    }

//    @Override
//    public void _jspService(HttpServletRequest arg0, HttpServletResponse arg1)
//            throws ServletException, IOException {
//        // TODO Auto-generated method stub
//
//    }

}
