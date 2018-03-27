package tt.com;

import java.util.HashMap;

/**
 * <pre>
 * tt.com
 *    |_ CoTtObjParams.java
 *
 * DESC : 오브젝트 파라미터 전용 클래스 <br />
 * </pre>
 *
 * @Company korea.think-tree.com
 * @author ks-lee
 * @Date 2013. 2. 27. 오후 2:09:00
 * @history :
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용
 *	----------- ------------------- ---------------------------------------
 *	2013. 2. 27.		ks-lee				최초 작성
 *	-----------------------------------------------------------------------
 *
 */
public class CoTtObjParams extends HashMap<String, Object> {
    private static final long serialVersionUID = 5262204640123183888L;

    public String getString(String key) {
        return (String) get(key);
    }
}
