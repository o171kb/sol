package tt.com.utils;

import org.apache.commons.io.FilenameUtils;

/**
 * DESC : 파일 명, 파일 패스, 파일 확장자등에 관련한 편의 기능들을 제공한다.<br>
 * <br>
 *
 * commons-io 2.2을 기반으로 제작되었으며, commons-io의 메소드 중에서 리턴 타입이 void인 메소드들 및 Exception들은<br>
 * enum 타입의 Status를 반환하도록 수정되었다.
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 1. 16. 오후 4:09:28
 */
public final class UtCoFileNameUtils {

    private UtCoFileNameUtils() {
    }

    /**
     * 파일 명으로부터 prefix를 제외한 패스를 반환한다.<br>
     * <br>
     *
     * FileNameUtils.getPath("C:\a\b\c.txt") = "a\b\"<br>
     * FileNameUtils.getPath("~/a/b/c.txt") = "a/b/"<br>
     * FileNameUtils.getPath("a.txt") = ""<br>
     * FileNameUtils.getPath("a/b/c") = "a/b/"<br>
     * FileNameUtils.getPath("a/b/c/") = "a/b/c/"
     *
     * @param filename 파일 이름
     * @return prefix를 제외한 파일 패스
     */
    public static String getPath(String filename) {
        return FilenameUtils.getPath(filename);
    }

    /**
     * 파일 명으로부터 prefix 및 마지막 디렉토리 구분자를 제외한 패스를 반환한다.<br>
     * <br>
     *
     * FileNameUtils.getPath("C:\a\b\c.txt") = "a\b"<br>
     * FileNameUtils.getPath("~/a/b/c.txt") = "a/b"<br>
     * FileNameUtils.getPath("a.txt") = ""<br>
     * FileNameUtils.getPath("a/b/c") = "a/b"<br>
     * FileNameUtils.getPath("a/b/c/") = "a/b/c"
     *
     * @param filename 파일 이름
     * @return prefix 및 마지막 디렉토리 구분자를 제외한 파일 패스
     */
    public static String getPathNoEndSeparator(String filename) {
        return FilenameUtils.getPathNoEndSeparator(filename);
    }

    /**
     * 파일 명으로부터 패스를 반환한다.<br>
     * <br>
     *
     * FileNameUtils.getFullPath("C:\a\b\c.txt") = "C:\a\b\"<br>
     * FileNameUtils.getFullPath("~/a/b/c.txt") = "~/a/b/"<br>
     * FileNameUtils.getFullPath("a.txt") = ""<br>
     * FileNameUtils.getFullPath("a/b/c") = "a/b/"<br>
     * FileNameUtils.getFullPath("a/b/c/") = "a/b/c/"<br>
     * FileNameUtils.getFullPath("C:") = "C:"<br>
     * FileNameUtils.getFullPath("C:\") = "C:\"<br>
     * FileNameUtils.getFullPath("~") = "~/"<br>
     * FileNameUtils.getFullPath("~/") = "~/"<br>
     * FileNameUtils.getFullPath("~user") = "~user/"<br>
     * FileNameUtils.getFullPath("~user/") = "~user/"
     *
     * @param filename 파일 이름
     * @return 파일 패스
     */
    public static String getFullPath(String filename) {
        return FilenameUtils.getFullPath(filename);
    }

    /**
     * 파일 명으로부터 마지막 디렉토리 구분자를 제외한 패스를 반환한다.<br>
     * <br>
     *
     * FileNameUtils.getFullPath("C:\a\b\c.txt") = "C:\a\b"<br>
     * FileNameUtils.getFullPath("~/a/b/c.txt") = "~/a/b"<br>
     * FileNameUtils.getFullPath("a.txt") = ""<br>
     * FileNameUtils.getFullPath("a/b/c") = "a/b"<br>
     * FileNameUtils.getFullPath("a/b/c/") = "a/b/c"<br>
     * FileNameUtils.getFullPath("C:") = "C:"<br>
     * FileNameUtils.getFullPath("C:\") = "C:\"<br>
     * FileNameUtils.getFullPath("~") = "~"<br>
     * FileNameUtils.getFullPath("~/") = "~"<br>
     * FileNameUtils.getFullPath("~user") = "~user"<br>
     * FileNameUtils.getFullPath("~user/") = "~user"
     *
     * @param filename 파일 이름
     * @return 마지막 디렉토리 구분자를 제외한 패스
     */
    public static String getFullPathNoEndSeparator(String filename) {
        return FilenameUtils.getFullPathNoEndSeparator(filename);
    }

    /**
     * 파일 이름으로부터 패스를 제외한 파일 명만 반환한다.<br>
     * <br>
     *
     * FileNameUtils.getName("a/b/c.txt") = "c.txt"<br>
     * FileNameUtils.getName("a.txt") = "a.txt"<br>
     * FileNameUtils.getName("a/b/c") = "c"<br>
     * FileNameUtils.getName("a/b/c/") = ""
     *
     * @param filename 파일 이름
     * @return 패스를 제외한 파일 명
     */
    public static String getName(String filename) {
        return FilenameUtils.getName(filename);
    }

    /**
     * 파일 이름으로부터 패스와 확장자를 제외한 파일 명만 반환한다.<br>
     * <br>
     *
     * FileNameUtils.getBaseName("C:/a/b/c.txt") = "c"<br>
     * FileNameUtils.getBaseName("a.txt") = "a"<br>
     * FileNameUtils.getBaseName("a/b/c") = "c"<br>
     * FileNameUtils.getBaseName("a/b/c/") = ""
     *
     * @param filename 파일 이름
     * @return 파일 이름으로부터 패스와 확장자를 제외한 파일 명
     */
    public static String getBaseName(String filename) {
        return FilenameUtils.getBaseName(filename);
    }

    /**
     * 파일 이름으로부터 확장자를 반환한다.<br>
     * <br>
     *
     * FileNameUtils.getExtension("foo.txt") = "txt"<br>
     * FileNameUtils.getExtension("a/b/c.jpg") = "jpg"<br>
     * FileNameUtils.getExtension("a/b.txt/c") = ""<br>
     * FileNameUtils.getExtension("a/b/c") = ""
     *
     * @param filename 파일 이름
     * @return 파일의 확장자
     */
    public static String getExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    /**
     * 파일 이름으로부터 확장자를 제외한 파일 명만 반환한다.<br>
     * <br>
     *
     * FileNameUtils.removeExtension("foo.txt") = "foo"<br>
     * FileNameUtils.removeExtension("a/b/c.jpg") = "a/b/c"<br>
     * FileNameUtils.removeExtension("a\b\c") = "a\b\c"<br>
     * FileNameUtils.removeExtension("a.b\c") = "a.b\c"
     *
     * @param filename 파일 이름
     * @return 확장자를 제외한 파일 명
     */
    public static String removeExtension(String filename) {
        return FilenameUtils.removeExtension(filename);
    }
}
