package tt.constant;


/**
 * <pre>
 * tt.constant
 *    |_ CsConstDef.java
 *
 * DESC :
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 4. 4. 오후 1:38:28
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 4. 4.		hk-kim				불필요한 상수 제거/변경
 *	-----------------------------------------------------------------------
 *
 */
public class CsConstDef {

    // 문자열 인코딩 코드
    // UTF8
    public static final String ENCODE_CD_UTF8 = "UTF-8";

    // 문자열 DOT
    public static final String STR_DOT = ".";

    // 문자열 DASH
    public static final String STR_DASH = "-";

    // 업로드폴더생성용
    public static final String STR_BOARD = "board";

    // 업로드폴더생성용
    public static final String STR_APPR = "appr";

    // 검색칼럼
    // 아이템
    public static final String NAVER_RS_ITEM = "item";
    // 타이틀
    public static final String NAVER_RS_COL_TITLE = "title";
    // 링크
    public static final String NAVER_RS_COL_LINK = "link";
    // 썸네일
    public static final String NAVER_RS_COL_THUMBNAIL = "thumbnail";
    // 이미지높이
    public static final String NAVER_RS_COL_SIZEHEIGHT = "sizeheight";
    // 이미지넓이
    public static final String NAVER_RS_COL_SIZEWIDTH = "sizewidth";
    // 설명
    public static final String NAVER_RS_COL_DESCRIPTION = "description";
    // URL
    public static final String NAVER_RS_COL_URL = "url";

    // 사이언스스토리 초기검색수설정
    public static final int SSTORY_DEF_END_DISP = 12;
    public static final int SSTORY_PAG_END_DISP = 18;

    // 파일업로드
    public static final String UPLOAD_FROM_EDITER = "edit";
    public static final String UPLOAD_REDIRECT_URL = "/module/smartEditor/popup/quick_photo/callback.jsp?callback_func=";
    public static final String UPLOAD_REDIRECT_URL_PARAM_FILE = "&sFileName=";
    public static final String UPLOAD_REDIRECT_URL_PARAM_FILE_URL = "&bNewLine=true&sFileURL=";
    public static final String UPLOAD_PARAM_CALLBACK_FUNC_NAME = "callback_func";
    public static final String UPLOAD_IMG_ROOT_DIR = "attach";
    public static final String UPLOAD_IMG_ROOT = "solupia";


}
