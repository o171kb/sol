package tt.com.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import tt.TTLog;
import tt.constant.CsStatus;

/**
 * DESC : 파일 및 디렉토리 복사, 삭제, 초기화 등에 관련한 편의 기능들을 제공한다.<br>
 * <br>
 * commons-io 2.2을 기반으로 제작되었으며, commons-io의 메소드 중에서 리턴 타입이 void인 메소드들 및 Exception들은<br>
 * enum 타입의 Status를 반환하도록 수정되었다.
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 2. 14. 오후 8:14:27
 */
public final class UtCoFileUtils {

    private UtCoFileUtils() {
    }

    /**
     * 대상 파일 또는 디렉토리를 읽을 수 있는지 여부를 체크한다.
     *
     * @param file 파일 또는 디렉토리 명
     * @return 대상 파일 또는 디렉토리를 읽을 수 있으면 true를 그렇지 않으면 false를 반환
     */
    public static boolean canRead(String file) {
        boolean check = false;
        try {
            File checkFile = new File(file);
            if (checkFile.exists()) {
                check = checkFile.canRead();
            }
        } catch (SecurityException e) {
            check = false;
        }
        return check;
    }

    /**
     * 대상 파일 또는 디렉토리를 쓸 수 있는지 여부를 체크한다.
     *
     * @param file 파일 또는 디렉토리 명
     * @return 대상 파일 또는 디렉토리를 쓸 수 있으면 true를 그렇지 않으면 false를 반환
     */
    public static boolean canWrite(String file) {
        boolean check = false;
        try {
            File checkFile = new File(file);
            if (checkFile.exists()) {
                check = checkFile.canWrite();
            }
        } catch (SecurityException e) {
            check = false;
        }
        return check;
    }

    /**
     * 루트 디렉토리 내의 파일 및 하위 디렉토리를 삭제한다. 루트 디렉토리는 삭제하지 않는다.
     *
     * @param directory 루트 디렉토리 File
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus cleanDirectory(File directory) {

        if (!directory.exists()) {
            TTLog.error("FileUtils-cleanDirectory ERROR: " + directory + "가 존재하지 않습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (isNotDirectory(directory)) {
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.FileUtils.cleanDirectory(directory);
            return CsStatus.SUCCESS;
        } catch (IOException e) {
            TTLog.error("FileUtils-cleanDirectory ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * 루트 디렉토리 내의 파일 및 하위 디렉토리를 삭제한다. 루트 디렉토리는 삭제하지 않는다.
     *
     * @param directory 루트 디렉토리 명
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus cleanDirectory(String directory) {
        return cleanDirectory(new File(directory));
    }

    /**
     * 원본 디렉토리를 대상 디렉토리명으로 복사한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectory(File srcDir, File destDir) {
        return copyDirectory(srcDir, destDir, true);

    }

    /**
     * 원본 디렉토리를 대상 디렉토리명으로 복사한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectory(String srcDir, String destDir) {
        return copyDirectory(new File(srcDir), new File(destDir));
    }

    /**
     * 원본디렉토리를 대상 디렉토리명으로 복사한다.<br>
     * preserveFileDate가 true면 원본디렉토리의 파일 최종 수정일을 그대로 사용하고 false면 현재 일자를 최종 수정일로 설정한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @param preserveFileDate 수정일 설정 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectory(File srcDir, File destDir, boolean preserveFileDate) {

        if (!srcDir.exists()) {
            TTLog.error("FileUtils-copyDirectory ERROR: " + srcDir + "가 존재하지 않습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (isNotDirectory(srcDir)) {
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir, preserveFileDate);
        } catch (IOException e) {
            TTLog.error("FileUtils-copyDirectory ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 원본디렉토리를 대상 디렉토리명으로 복사한다.<br>
     * preserveFileDate가 true면 원본디렉토리의 파일 최종 수정일을 그대로 사용하고 false면 현재 일자를 최종 수정일로 설정한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @param preserveFileDate 수정일 설정 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectory(String srcDir, String destDir, boolean preserveFileDate) {
        return copyDirectory(new File(srcDir), new File(destDir), preserveFileDate);
    }

    /**
     * 원본디렉토리를 대상 디렉토리명으로 복사한다. fileDir가 'file'이면 파일만 'directory'면 디렉토리만 복사한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @param fileOrDir 파일인지 디렉토리인지를 결정하는 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectory(File srcDir, File destDir, String fileOrDir) {
        return copyDirectory(srcDir, destDir, fileOrDir, true);
    }

    /**
     * 원본디렉토리를 대상 디렉토리명으로 복사한다. fileDir가 'file'이면 파일만 'directory'면 디렉토리만 복사한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @param fileOrDir 파일인지 디렉토리인지를 결정하는 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectory(String srcDir, String destDir, String fileOrDir) {
        return copyDirectory(new File(srcDir), new File(destDir), fileOrDir, true);
    }

    /**
     * 원본디렉토리를 대상 디렉토리명으로 복사한다. fileOrDir가 'file'이면 파일만 'directory'면 디렉토리만 복사한다.<br>
     * preserveFileDate가 true면 원본디렉토리의 파일 최종 수정일을 그대로 사용하고 false면 현재 일자를 최종 수정일로 설정한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @param fileOrDir 파일인지 디렉토리인지를 결정하는 플래그
     * @param preserveFileDate 수정일 설정 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectory(File srcDir, File destDir, String fileOrDir, boolean preserveFileDate) {

        if (!srcDir.exists()) {
            TTLog.error("FileUtils-copyDirectory ERROR: " + srcDir + "가 존재하지 않습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (isNotDirectory(srcDir)) {
            return CsStatus.FAIL;
        }

        if (UtCoStringUtils.equalsIgnoreCase("file", fileOrDir)) {
            try {
                org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir, FileFileFilter.FILE, preserveFileDate);
            } catch (IOException e) {
                TTLog.error("FileUtils-copyDirectory ERROR: " + e.getMessage(), UtCoFileUtils.class);
                return CsStatus.FAIL;
            }
        } else if (UtCoStringUtils.equalsIgnoreCase("dir", fileOrDir)) {
            try {
                org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir, DirectoryFileFilter.DIRECTORY,
                        preserveFileDate);
            } catch (IOException e) {
                TTLog.error("FileUtils-copyDirectory ERROR: " + e.getMessage(), UtCoFileUtils.class);
                return CsStatus.FAIL;
            }
        } else {
            TTLog.error("FileUtils-copyDirectory ERROR: " + fileOrDir + "은 지원하지 않는 타입입니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 원본디렉토리를 대상 디렉토리명으로 복사한다. fileOrDir가 'file'이면 파일만 'directory'면 디렉토리만 복사한다.<br>
     * preserveFileDate가 true면 원본디렉토리의 파일 최종 수정일을 그대로 사용하고 false면 현재 일자를 최종 수정일로 설정한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @param fileOrDir 파일인지 디렉토리인지를 결정하는 플래그 "file" 또는 "directory"를 사용
     * @param preserveFileDate 수정일 설정 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectory(String srcDir, String destDir, String fileOrDir, boolean preserveFileDate) {
        return copyDirectory(new File(srcDir), new File(destDir), fileOrDir, preserveFileDate);
    }

    /**
     * 원본디렉토리를 대상 디렉토리명으로 복사한다. 입력된 파일 확장자랑 일치하는 파일들만 복사한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @param extension 파일 확장자
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectoryAfterCheckFileExt(String srcDir, String destDir, String extension) {
        return copyDirectoryAfterCheckFileExt(srcDir, destDir, extension, true);
    }

    /**
     * 원본디렉토리를 대상 디렉토리명으로 복사한다. 입력된 파일 확장자랑 일치하는 파일들만 복사한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @param extension 파일 확장자
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectoryAfterCheckFileExt(File srcDir, File destDir, String extension) {
        return copyDirectoryAfterCheckFileExt(srcDir, destDir, extension, true);
    }

    /**
     * 원본디렉토리를 대상 디렉토리명으로 복사한다. 입력된 파일 확장자랑 일치하는 파일들만 복사한다.<br>
     * preserveFileDate가 true면 원본디렉토리의 파일 최종 수정일을 그대로 사용하고 false면 현재 일자를 최종 수정일로 설정한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @param extension 파일 확장자
     * @param preserveFileDate 수정일 설정 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectoryAfterCheckFileExt(File srcDir, File destDir, String extension,
            boolean preserveFileDate) {

        if (!srcDir.exists()) {
            TTLog.error("FileUtils-copyDirectoryAfterCheckFileExt ERROR: " + srcDir + "가 존재하지 않습니다.",
                    UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (isNotDirectory(srcDir)) {
            return CsStatus.FAIL;
        }

        IOFileFilter suffixFilter = FileFilterUtils.suffixFileFilter(extension);
        FileFilter filter = FileFilterUtils.or(DirectoryFileFilter.DIRECTORY, suffixFilter);

        try {
            org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir, filter, preserveFileDate);
        } catch (IOException e) {
            TTLog.error("FileUtils-copyDirectoryAfterCheckFileExt ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 원본디렉토리를 대상 디렉토리명으로 복사한다. 입력된 파일 확장자랑 일치하는 파일들만 복사한다.<br>
     * preserveFileDate가 true면 원본디렉토리의 파일 최종 수정일을 그대로 사용하고 false면 현재 일자를 최종 수정일로 설정한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @param extension 파일 확장자
     * @param preserveFileDate 수정일 설정 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectoryAfterCheckFileExt(String srcDir, String destDir, String extension,
            boolean preserveFileDate) {
        return copyDirectoryAfterCheckFileExt(new File(srcDir), new File(destDir), extension, preserveFileDate);
    }

    /**
     * 원본 디렉토리를 대상 디렉토리의 하위로 복사한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectoryToDirectory(String srcDir, String destDir) {
        return UtCoFileUtils.copyDirectoryToDirectory(new File(srcDir), new File(destDir));
    }

    /**
     * 원본 디렉토리를 대상 디렉토리의 하위로 복사한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyDirectoryToDirectory(File srcDir, File destDir) {

        if (!srcDir.exists()) {
            TTLog.error("FileUtils-copyDirectoryToDirectory ERROR: " + srcDir + "가 존재하지 않습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (isNotDirectory(srcDir)) {
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.FileUtils.copyDirectoryToDirectory(srcDir, destDir);
        } catch (Exception e) {
            TTLog.error("FileUtils-copyDirectoryToDirectory ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 주어진 디렉토리명으로 디렉토리를 생성한다.
     *
     * @param dirPath 생성할 디렉토리 명
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus createDirectory(String dirPath) {

        File path = new File(dirPath);

        if (path.exists()) {
            return CsStatus.SUCCESS;
        }

        boolean success = path.getParentFile().exists() ? path.mkdir() : path.mkdirs();

        if (success) {
            return CsStatus.SUCCESS;
        } else {
            TTLog.error("FileUtils-createDirectory ERROR: directory 생성에 실패하였습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * 원본 디렉토리가 대상 디렉토리 명으로 이동한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus moveDirectory(String srcDir, String destDir) {
        return moveDirectory(new File(srcDir), new File(destDir));
    }

    /**
     * 원본 디렉토리가 대상 디렉토리 명으로 이동한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus moveDirectory(File srcDir, File destDir) {

        if (!srcDir.exists()) {
            TTLog.error("FileUtils-moveDirectory ERROR: " + srcDir + "가 존재하지 않습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (isNotDirectory(srcDir)) {
            return CsStatus.FAIL;
        }

        if (destDir.exists()) {
            deleteDirectory(destDir);
        }

        try {
            org.apache.commons.io.FileUtils.moveDirectory(srcDir, destDir);
        } catch (IOException e) {
            TTLog.error("FileUtils-moveDirectory ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 원본 디렉토리가 대상 디렉토리 하위로 이동한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus moveDirectoryToDirectory(String srcDir, String destDir) {
        return moveDirectoryToDirectory(new File(srcDir), new File(destDir));
    }

    /**
     * 원본 디렉토리가 대상 디렉토리 하위로 이동한다.
     *
     * @param srcDir 원본 디렉토리
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus moveDirectoryToDirectory(File srcDir, File destDir) {

        String subDir = destDir.getAbsolutePath() + "/" + srcDir.getName();
        File destSubDir = new File(subDir);

        if (destSubDir.exists() && destSubDir.isDirectory()) {
            deleteDirectory(destSubDir);
        }

        try {
            org.apache.commons.io.FileUtils.moveDirectoryToDirectory(srcDir, destDir, true);
        } catch (IOException e) {
            TTLog.error("FileUtils-moveDirectoryToDirectory ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 해당 디렉토리 및 하위 파일을 삭제한다.
     *
     * @param targetDir 삭제할 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus deleteDirectory(File targetDir) {

        if (!targetDir.exists()) {
            TTLog.error("FileUtils-deleteDirectory ERROR: " + targetDir + "가 존재하지 않습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (isNotDirectory(targetDir)) {
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.FileUtils.forceDelete(targetDir);
        } catch (IOException e) {
            TTLog.error("FileUtils-deleteDirectory ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 해당 디렉토리 및 하위 파일을 삭제한다.
     *
     * @param targetDir 삭제할 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus deleteDirectory(String targetDir) {
        return deleteDirectory(new File(targetDir));
    }

    /**
     * 해당파일을 삭제한다.
     *
     * @param targetFile 삭제할 File
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus deleteFile(String targetFile) {
        return deleteFile(new File(targetFile));
    }

    /**
     * 해당파일을 삭제한다.
     *
     * @param targetFile 삭제할 File
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus deleteFile(File targetFile) {

        if (!targetFile.exists()) {
            TTLog.error("FileUtils-deleteFile ERROR: " + targetFile + "가 존재하지 않습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (isNotFile(targetFile)) {
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.FileUtils.forceDelete(targetFile);
        } catch (IOException e) {
            TTLog.error("FileUtils-deleteFile ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 원본 파일을 대상 파일명으로 복사한다
     *
     * @param srcFile 원본 파일
     * @param destFile 대상 파일
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyFile(File srcFile, File destFile) {
        return copyFile(srcFile, destFile, true);
    }

    /**
     * 원본 파일을 대상 파일명으로 복사한다
     *
     * @param srcFile 원본 파일
     * @param destFile 대상 파일
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyFile(String srcFile, String destFile) {
        return copyFile(new File(srcFile), new File(destFile), true);
    }

    /**
     * 원본 파일을 대상 파일명으로 복사한다.<br>
     * preserveFileDate가 true면 원본디렉토리의 파일 최종 수정일을 그대로 사용하고 false면 현재 일자를 최종 수정일로 설정한다.
     *
     * @param srcFile 원본 파일
     * @param destFile 대상 파일
     * @param preserveFileDate 수정일 설정 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyFile(File srcFile, File destFile, boolean preserveFileDate) {

        if (!srcFile.exists()) {
            TTLog.error("FileUtils-copyFile ERROR: " + srcFile + "가 존재하지 않습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (isNotFile(srcFile)) {
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.FileUtils.copyFile(srcFile, destFile, preserveFileDate);
        } catch (IOException e) {
            TTLog.error("FileUtils-copyFile ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 원본 파일을 대상 파일명으로 복사한다.<br>
     * preserveFileDate가 true면 원본디렉토리의 파일 최종 수정일을 그대로 사용하고 false면 현재 일자를 최종 수정일로 설정한다.
     *
     * @param srcFile 원본 파일
     * @param destFile 대상 파일
     * @param preserveFileDate 수정일 설정 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyFile(String srcFile, String destFile, boolean preserveFileDate) {
        return copyFile(new File(srcFile), new File(destFile), preserveFileDate);
    }

    /**
     * 파일을 대상 디렉토리에 복사한다.<br>
     * preserveFileDate가 true면 원본디렉토리의 파일 최종 수정일을 그대로 사용하고 false면 현재 일자를 최종 수정일로 설정한다.
     *
     * @param srcFile 원본 파일
     * @param destDir 대상 디렉토리
     * @param preserveFileDate 수정일 설정 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyFileToDirectory(File srcFile, File destDir, boolean preserveFileDate) {

        if (!srcFile.exists()) {
            TTLog.error("FileUtils-copyFileToDirectory ERROR: " + srcFile + "가 존재하지 않습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (isNotFile(srcFile)) {
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.FileUtils.copyFileToDirectory(srcFile, destDir, preserveFileDate);
        } catch (Exception e) {
            TTLog.error("FileUtils-copyFileToDirectory ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 파일을 대상 디렉토리에 복사한다.<br>
     * preserveFileDate가 true면 원본디렉토리의 파일 최종 수정일을 그대로 사용하고 false면 현재 일자를 최종 수정일로 설정한다.
     *
     * @param srcFile 원본 파일
     * @param destDir 대상 디렉토리
     * @param preserveFileDate 수정일 설정 플래그
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyFileToDirectory(String srcFile, String destDir, boolean preserveFileDate) {
        return copyFileToDirectory(new File(srcFile), new File(destDir), preserveFileDate);
    }

    /**
     * 파일을 대상 디렉토리에 복사한다.
     *
     * @param srcFile 원본 파일
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyFileToDirectory(File srcFile, File destDir) {
        return copyFileToDirectory(srcFile, destDir, true);
    }

    /**
     * 파일을 대상 디렉토리에 복사한다.
     *
     * @param srcFile 원본 파일
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus copyFileToDirectory(String srcFile, String destDir) {
        return copyFileToDirectory(new File(srcFile), new File(destDir), true);
    }

    /**
     * 원본 파일을 대상 파일 명으로 이동한다.
     *
     * @param srcFile 원본 파일
     * @param destFile 대상 파일
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus moveFile(File srcFile, File destFile) {

        if (!srcFile.exists()) {
            TTLog.error("FileUtils-moveFile ERROR: " + srcFile + "가 존재하지 않습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (isNotFile(srcFile)) {
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.FileUtils.moveFile(srcFile, destFile);
        } catch (IOException e) {
            TTLog.error("FileUtils-moveFile ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 원본 파일을 대상 파일 명으로 이동한다.
     *
     * @param srcFile 원본 파일
     * @param destFile 대상 파일
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus moveFile(String srcFile, String destFile) {
        return moveFile(new File(srcFile), new File(destFile));
    }

    /**
     * 원본 파일을 대상 디렉토리 하위로 이동한다.
     *
     * @param srcFile 원본 파일
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus moveFileToDirectory(File srcFile, File destDir) {

        if (!srcFile.exists()) {
            TTLog.error("FileUtils-moveFileToDirectory ERROR: " + srcFile + "가 존재하지 않습니다.", UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        if (srcFile.exists()) {
            if (isNotFile(srcFile)) {
                return CsStatus.FAIL;
            }
        }

        boolean createDestDir = destDir.exists() ? false : true;

        try {
            org.apache.commons.io.FileUtils.moveFileToDirectory(srcFile, destDir, createDestDir);
        } catch (IOException e) {
            TTLog.error("FileUtils-moveFileToDirectory ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return CsStatus.FAIL;
        }

        return CsStatus.SUCCESS;
    }

    /**
     * 원본 파일을 대상 디렉토리 하위로 이동한다.
     *
     * @param srcFile 원본 파일
     * @param destDir 대상 디렉토리
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus moveFileToDirectory(String srcFile, String destDir) {
        return moveFileToDirectory(new File(srcFile), new File(destDir));
    }

    /**
     * 원본 파일명을 대상 파일 명으로 변경한다.<br />
     * 파일변경후 원본 파일 삭제
     *
     * @param srcFile 원본 파일
     * @param destFile 대상 파일
     * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
     */
    public static CsStatus rename(String srcFile, String destFile) {

        CsStatus result = copyFile(srcFile, destFile, false);

        if (result == CsStatus.SUCCESS) {

            result = deleteFile(srcFile);

            if (result == CsStatus.SUCCESS) {
                return CsStatus.SUCCESS;
            }
        }

        return CsStatus.FAIL;
    }

    /**
     * 파일을 읽어 들인 후 바이트 배열로 반환한다.<br/>
     *
     * @param file 대상 파일
     * @return 원본 파일의 byte[]
     */
    public static byte[] readFileToByteArray(File file) {

        InputStream in = null;

        try {
            in = openInputStream(file);
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            TTLog.error("FileUtils-readFileToByteArray ERROR: " + e.getMessage(), UtCoFileUtils.class);
            return null;
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 대상 파일을 FileInputStream으로 반환한다.<br/>
     *
     * @param file 대상 파일
     * @return 대상 파일에 대한 FileInputStream
     */
    public static FileInputStream openInputStream(File file) {

        if (file.exists()) {

            if (file.isDirectory()) {
                TTLog.error("FileUtils-openInputStream ERROR: " + file + "이 존재하나 디렉토리입니다.", UtCoFileUtils.class);
                return null;
            }

            if (!file.canRead()) {
                TTLog.error("FileUtils-openInputStream ERROR: " + file + "은 읽을수 없습니다.", UtCoFileUtils.class);
                return null;
            }

        } else {
            TTLog.error("FileUtils-openInputStream ERROR: " + file + "이 존재하지 않습니다.", UtCoFileUtils.class);
            return null;
        }

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            TTLog.error("FileUtils-openInputStream ERROR: " + e.getMessage(), UtCoFileUtils.class);
        }

        return fis;
    }

    /**
     * 대상 파일을 FileInputStream으로 반환한다.<br/>
     *
     * @param filePath 대상 파일 경로
     * @return 대상 파일에 대한 FileInputStream
     */
    public static FileInputStream openInputStream(String filePath) {
        return openInputStream(new File(filePath));
    }

    /**
     * 대상 파일을 outputStream으로 반환한다.
     *
     * @param file 대상 파일
     * @return 대상 파일에 대한 FileOutputStream
     */
    public static FileOutputStream openOutputStream(File file) {
        return openOutputStream(file, false);
    }

    /**
     * 대상 파일을 outputStream으로 반환한다.
     *
     * @param filePath 대상 파일 경로
     * @return 대상 파일에 대한 FileOutputStream
     */
    public static FileOutputStream openOutputStream(String filePath) {
        return openOutputStream(new File(filePath), false);
    }

    /**
     * 대상 파일을 outputStream으로 반환한다. append가 true이면 출력시 기존 파일의 내용 마지막에 덧붙인다.
     *
     * @param file 대상 파일
     * @param append 기존 파일에 내용을 덧붙일지에 대한 플래그
     * @return 대상 파일에 대한 FileOutputStream
     */
    public static FileOutputStream openOutputStream(File file, boolean append) {

        if (file.exists()) {

            if (file.isDirectory()) {
                TTLog.error("FileUtils-openOutputStream ERROR: " + file + "이 존재하나 디렉토리입니다.", UtCoFileUtils.class);
                return null;
            }
            if (!file.canWrite()) {
                TTLog.error("FileUtils-openOutputStream ERROR: " + file + "은 쓸수 없습니다.", UtCoFileUtils.class);
                return null;
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    TTLog.error("FileUtils-openOutputStream ERROR: Directory '" + parent + "' 생성에 실패하였습니다.",
                            UtCoFileUtils.class);
                    return null;
                }
            }
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file, append);
        } catch (FileNotFoundException e) {
            TTLog.error("FileUtils-openOutputStream ERROR: " + e.getMessage(), UtCoFileUtils.class);
        }
        return fos;
    }

    /**
     * 대상 파일을 outputStream으로 반환한다. append가 true이면 출력시 기존 파일의 내용 마지막에 덧붙인다.
     *
     * @param filePath 대상 파일 경로
     * @param append 기존 파일에 내용을 덧붙일지에 대한 플래그
     * @return 대상 파일에 대한 FileOutputStream
     */
    public static FileOutputStream openOutputStream(String filePath, boolean append) {
        return openOutputStream(new File(filePath), append);
    }

    public static boolean existsDir(String dirPath) {

        File dir = new File(dirPath);

        if (dir.exists()) {
            return isNotDirectory(dir) ? false : true;
        } else {
            TTLog.error("FileUtils-existsDir ERROR: " + dir + "는 존재하지 않는 디렉토리입니다.", UtCoFileUtils.class);
            return false;
        }
    }

    public static boolean existsFile(String filePath) {

        File file = new File(filePath);

        if (file.exists()) {
            return isNotFile(file) ? false : true;
        } else {
            TTLog.error("FileUtils-existsFile ERROR: " + file + "는 존재하지 않는 파일입니다.", UtCoFileUtils.class);
            return false;
        }
    }

    private static boolean isNotDirectory(File directory) {
        if (!directory.isDirectory()) {
            TTLog.error("FileUtils-isNotDirectory ERROR: " + directory + "는 디렉토리가 아닙니다.", UtCoFileUtils.class);
            return true;
        }
        return false;
    }

    private static boolean isNotFile(File file) {
        if (!file.isFile()) {
            TTLog.error("FileUtils-isNotFile ERROR: " + file + "는 파일이 아닙니다.", UtCoFileUtils.class);
            return true;
        }
        return false;
    }

}
