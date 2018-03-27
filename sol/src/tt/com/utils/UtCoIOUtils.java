package tt.com.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Selector;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.LineIterator;
import org.apache.commons.io.output.ByteArrayOutputStream;

import tt.TTLog;
import tt.bean.VoApprInfo;
import tt.bean.VoExctDraft;
import tt.com.CoTtObjParams;
import tt.com.constant.CsCoConstDef;
import tt.constant.CsStatus;

/**
 * DESC : 스트림과 리더로 부터 String과 Byte Array를 생성하고, 안전하게 스트림을 닫는 기능을 제공한다.
 *
 * commons-io 2.2을 기반으로 제작되었으며, commons-io의 메소드 중에서 반환 타입이 void인 메소드들 및 Exception들은<br>
 * enum 타입의 CoStatus를 반환하도록 수정되었다.
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 1. 16. 오후 4:36:22
 */
public final class UtCoIOUtils {

    private UtCoIOUtils() {
    }

    /**
     * Closeable 를 무조건 닫는다.
     *
     * @param closeable 닫을 대상의 Closeable, null 이거나 이미 닫혀 있을수도 있다.
     */
    public static void closeQuietly(Closeable closeable) {
        org.apache.commons.io.IOUtils.closeQuietly(closeable);
    }

    /**
     * InputStream 를 무조건 닫는다.
     *
     * @param is 닫을 대상의 InputStream, null 이거나 이미 닫혀 있을수도 있다.
     */
    public static void closeQuietly(InputStream is) {
        org.apache.commons.io.IOUtils.closeQuietly(is);
    }

    /**
     * OutputStream 를 무조건 닫는다.
     *
     * @param os 닫을 대상의 OutputStream, null 이거나 이미 닫혀 있을수도 있다.
     */
    public static void closeQuietly(OutputStream os) {
        org.apache.commons.io.IOUtils.closeQuietly(os);
    }

    /**
     * Reader 를 무조건 닫는다.
     *
     * @param reader 닫을 대상의 Reader, null 이거나 이미 닫혀 있을수도 있다.
     */
    public static void closeQuietly(Reader reader) {
        org.apache.commons.io.IOUtils.closeQuietly(reader);
    }

    /**
     * Selector 를 무조건 닫는다.
     *
     * @param selector 닫을 대상의 Selector, null 이거나 이미 닫혀 있을수도 있다.
     */
    public static void closeQuietly(Selector selector) {
        org.apache.commons.io.IOUtils.closeQuietly(selector);
    }

    /**
     * ServerSocket 를 무조건 닫는다.
     *
     * @param sock 닫을 대상의 ServerSocket, null 이거나 이미 닫혀 있을수도 있다.
     */
    public static void closeQuietly(ServerSocket sock) {
        org.apache.commons.io.IOUtils.closeQuietly(sock);
    }

    /**
     * Socket 를 무조건 닫는다.
     *
     * @param sock 닫을 대상의 Socket, null 이거나 이미 닫혀 있을수도 있다.
     */
    public static void closeQuietly(Socket sock) {
        org.apache.commons.io.IOUtils.closeQuietly(sock);
    }

    /**
     * Writer 를 무조건 닫는다.
     *
     * @param output 닫을 대상의 Writer, null 이거나 이미 닫혀 있을수도 있다.
     */
    public static void closeQuietly(Writer output) {
        org.apache.commons.io.IOUtils.closeQuietly(output);
    }

    /**
     * 두 InputStream 의 내용을 비교하여 같거나 같지 않음을 결정한다.
     *
     * @param input1 첫번째 InputStream
     * @param input2 두번째 InputStream
     * @return 스트림의 내용이 동일하거나 둘 다 내용이 존재하지 않는 경우 true, 이외에는 false
     */
    public static boolean contentEquals(InputStream input1, InputStream input2) {
        try {
            return org.apache.commons.io.IOUtils.contentEquals(input1, input2);
        } catch (Exception e) {
            TTLog.error("IOUtils-contentEquals ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return false;
        }
    }

    /**
     * 두 Reader 의 내용을 비교하여 같거나 같지 않음을 결정한다.
     *
     * @param input1 첫번째 Reader
     * @param input2 두번째 Reader
     * @return 스트림의 내용이 동일하거나 둘 다 내용이 존재하지 않는 경우 true, 이외에는 false
     */
    public static boolean contentEquals(Reader input1, Reader input2) {
        try {
            return org.apache.commons.io.IOUtils.contentEquals(input1, input2);
        } catch (Exception e) {
            TTLog.error("IOUtils-contentEquals ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return false;
        }
    }

    /**
     * 두 Reader 의 내용을 비교하여 같거나 같지 않음을 결정한다. EOL (end of line) 문자는 무시.
     *
     * @param input1 첫번째 Reader
     * @param input2 두번째 Reader
     * @return 스트림의 내용이 동일 할 경우 true (EOL 이 다를경우 무시), 이외에는 false
     */
    public static boolean contentEqualsIgnoreEOL(Reader input1, Reader input2) {
        try {
            return org.apache.commons.io.IOUtils.contentEqualsIgnoreEOL(input1, input2);
        } catch (Exception e) {
            TTLog.error("IOUtils-contentEqualsIgnoreEOL ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return false;
        }
    }

    /**
     * InputStream 에서 bytes를 OutputStream 에 복사한다.
     *
     * @param is InputStream 를 읽는다.
     * @param os OutputStream 에 쓴다.
     * @return 복사한 bytes 수
     */
    public static int copy(InputStream is, OutputStream os) {

        try {
            return org.apache.commons.io.IOUtils.copy(is, os);
        } catch (Exception e) {
            TTLog.error("IOUtils-copy ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * Writer에 InputStream의 문자를 플랫폼의 기본 문자 인코딩을 사용하여 바이트 복사를 한다.
     *
     * @param is InputStream에서 읽는다
     * @param writer Writer 에 복사한다
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus copy(InputStream is, Writer writer) {

        try {
            org.apache.commons.io.IOUtils.copy(is, writer);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-copy ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * Writer에 InputStream의 문자를 플랫폼의 기본 문자 인코딩을 사용하여 바이트 복사를 한다.
     *
     * @param is InputStream에서 읽는다
     * @param writer Writer 에 복사한다
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus copy(InputStream is, Writer writer, String encoding) {

        try {
            org.apache.commons.io.IOUtils.copy(is, writer, encoding);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-copy ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * OutputStream에 Reader 의 문자를 기본 인코딩 방식을 사용하여 복사하고, flush 처리 한다.
     *
     * @param reader Reader 에서 읽는다
     * @param os OutputStream 에 복사한다
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus copy(Reader reader, OutputStream os) {

        try {
            org.apache.commons.io.IOUtils.copy(reader, os);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-copy ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * OutputStream에 Reader 의 문자를 지정된 인코딩 방식을 사용하여 복사하고, flush 처리 한다.
     *
     * @param reader Reader 에서 읽는다
     * @param os OutputStream 에 복사한다
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus copy(Reader reader, OutputStream os, String encoding) {

        try {
            org.apache.commons.io.IOUtils.copy(reader, os, encoding);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-copy ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * Writer에 Reader의 문자를 복사 한다.
     *
     * @param reader InputStream에서 읽는다
     * @param writer Writer 에 복사한다
     * @return 복사한 bytes 수
     */
    public static int copy(Reader reader, Writer writer) {

        try {
            return org.apache.commons.io.IOUtils.copy(reader, writer);
        } catch (Exception e) {
            TTLog.error("IOUtils-copy ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * 큰 InputStream (2GB 이상)에서 OutputStream으로 bytes를 복사한다.
     *
     * @param is InputStream에서 읽는다
     * @param os OutputStream 에 쓴다.
     * @return 복사한 bytes 수
     */
    public static long copyLarge(InputStream is, OutputStream os) {

        try {
            return org.apache.commons.io.IOUtils.copyLarge(is, os);
        } catch (Exception e) {
            TTLog.error("IOUtils-copyLarge ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * 큰 InputStream (2GB 이상)에서 OutputStream으로 bytes를 복사한다.
     *
     * @param is InputStream에서 읽는다
     * @param os OutputStream 에 쓴다.
     * @param buffer 복사시 사용하는 buffer
     * @return 복사한 bytes 수
     */
    public static long copyLarge(InputStream is, OutputStream os, byte[] buffer) {

        try {
            return org.apache.commons.io.IOUtils.copyLarge(is, os, buffer);
        } catch (Exception e) {
            TTLog.error("IOUtils-copyLarge ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * 큰 InputStream (2GB 이상)에서 OutputStream으로 bytes를 복사한다.
     *
     * @param is InputStream에서 읽는다
     * @param os OutputStream 에 쓴다.
     * @param inputOffset 시작을 bytes 수 만큼 건너 뛰고 복사를 시작한다.
     * @param length 복사할 bytes 수
     * @return 복사한 bytes 수
     */
    public static long copyLarge(InputStream is, OutputStream os, long inputOffset, long length) {

        try {
            return org.apache.commons.io.IOUtils.copyLarge(is, os, inputOffset, length);
        } catch (Exception e) {
            TTLog.error("IOUtils-copyLarge ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return 0;
        }
    }

    /**
     * 큰 InputStream (2GB 이상)에서 OutputStream으로 bytes를 복사한다.
     *
     * @param is InputStream에서 읽는다
     * @param os OutputStream 에 복사한다
     * @param inputOffset bytes 수 만큼 건너 뛰고 복사를 시작한다.
     * @param length 복사할 bytes 수
     * @param buffer 버퍼를 사용하여 복사한다.
     * @return 복사한 bytes 수
     */
    public static long copyLarge(InputStream is, OutputStream os, final long inputOffset, final long length,
            byte[] buffer) {

        try {
            return org.apache.commons.io.IOUtils.copyLarge(is, os, inputOffset, length, buffer);
        } catch (Exception e) {
            TTLog.error("IOUtils-copyLarge ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return 0;
        }
    }

    /**
     * Writer에 큰 사이즈 Reader(2GB 이상)의 문자를 복사 한다.
     *
     * @param reader Reader 에서 읽는다
     * @param writer Writer 에 복사한다
     * @return 복사한 char 수
     */
    public static long copyLarge(Reader reader, Writer writer) {

        try {
            return org.apache.commons.io.IOUtils.copyLarge(reader, writer);
        } catch (Exception e) {
            TTLog.error("IOUtils-copyLarge ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * Writer에 큰 사이즈 Reader(2GB 이상)의 문자를 복사 한다.
     *
     * @param reader Reader 에서 읽는다
     * @param writer Writer 에 복사한다
     * @param buffer 버퍼를 사용하여 복사한다
     * @return 복사한 char 수
     */
    public static long copyLarge(Reader reader, Writer writer, char[] buffer) {

        try {
            return org.apache.commons.io.IOUtils.copyLarge(reader, writer, buffer);
        } catch (Exception e) {
            TTLog.error("IOUtils-copyLarge ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * Writer에 큰 사이즈 Reader(2GB 이상)의 문자를 복사 한다.
     *
     * @param reader Reader 에서 읽는다
     * @param writer Writer 에 복사한다
     * @param inputOffset bytes 수 만큼 건너 뛰고 복사를 시작한다.
     * @param length 복사 할 bytes 수
     * @return 복사한 char 수
     */
    public static long copyLarge(Reader reader, Writer writer, final long inputOffset, final long length) {

        try {
            return org.apache.commons.io.IOUtils.copyLarge(reader, writer, inputOffset, length);
        } catch (Exception e) {
            TTLog.error("IOUtils-copyLarge ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * Writer에 큰 사이즈 Reader(2GB 이상)의 문자를 복사 한다.
     *
     * @param reader Reader 에서 읽는다
     * @param writer Writer 에 복사한다
     * @param inputOffset bytes 수 만큼 건너 뛰고 복사를 시작한다.
     * @param length 복사 할 bytes 수
     * @param buffer buffer를 사용하여 복사한다
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static long copyLarge(Reader reader, Writer writer, final long inputOffset, final long length, char[] buffer) {

        try {
            return org.apache.commons.io.IOUtils.copyLarge(reader, writer, inputOffset, length, buffer);
        } catch (Exception e) {
            TTLog.error("IOUtils-copyLarge ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * InputStream 의 내용을 지정된 문자 인코딩 방식으로 인코딩 후 LineIterator 로 반환한다.
     *
     * @param is 내용을 가져올 InputStream
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 스트링으로 구성된 List
     */
    public static LineIterator lineIterator(InputStream is, String encoding) {

        try {
            return org.apache.commons.io.IOUtils.lineIterator(is, encoding);
        } catch (Exception e) {
            TTLog.error("IOUtils-lineIterator ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * Reader 을 LineIterator 로 반환한다.
     *
     * @param reader 내용을 가져올 Reader
     * @return 스트링으로 구성된 List
     */
    public static LineIterator lineIterator(Reader reader) {

        try {
            return org.apache.commons.io.IOUtils.lineIterator(reader);
        } catch (Exception e) {
            TTLog.error("IOUtils-lineIterator ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }

    }

    /**
     * InputStream 로부터 문자를 읽는다.
     *
     * @param is 읽을 대상의 InputStream
     * @param buffer 입력 대상 버퍼
     * @return 실제 읽은 길이, EOF에 도달 할 경우에 요청된 길이보다 적을 수 있다.
     */
    public static int read(InputStream is, byte[] buffer) {

        if (is == null) {
            TTLog.error("IOUtils-read ERROR: InputStream is null.", UtCoIOUtils.class);
            return -1;
        }

        try {
            return org.apache.commons.io.IOUtils.read(is, buffer);
        } catch (Exception e) {
            TTLog.error("IOUtils-read ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * InputStream 에서 요청한 바이트 수 만큼 읽는다.
     *
     * @param is 읽을 대상의 InputStream
     * @param buffer 입력 대상 버퍼
     * @param offset 읽을 바이트가 시작되는 오프셋
     * @param length 읽을 수 있는 바이트 길이, 0보다 커야한다.
     * @return 실제 읽은 길이, EOF에 도달 할 경우에 요청된 길이보다 적을 수 있다.
     */
    public static int read(InputStream is, byte[] buffer, int offset, int length) {

        if (is == null) {
            TTLog.error("IOUtils-read ERROR: InputStream is null.", UtCoIOUtils.class);
            return -1;
        }

        try {
            return org.apache.commons.io.IOUtils.read(is, buffer, offset, length);
        } catch (Exception e) {
            TTLog.error("IOUtils-read ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * Reader 로부터 문자를 읽는다.
     *
     * @param reader 읽을 대상의 Reader
     * @param buffer 입력 대상 버퍼
     * @return 실제 읽은 길이, EOF에 도달 할 경우에 요청된 길이보다 적을 수 있다.
     */
    public static int read(Reader reader, char[] buffer) {

        if (reader == null) {
            TTLog.error("IOUtils-read ERROR: Reader is null.", UtCoIOUtils.class);
            return -1;
        }

        try {
            return org.apache.commons.io.IOUtils.read(reader, buffer);
        } catch (Exception e) {
            TTLog.error("IOUtils-read ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * Reader 에서 요청한 문자를 수 만큼 읽는다
     *
     * @param reader 읽을 대상의 Reader
     * @param buffer 입력 대상 버퍼
     * @param offset 읽을 문자가 시작되는 오프셋
     * @param length 읽을 수 있는 문자 길이, 0보다 커야한다.
     * @return 실제 읽은 길이, EOF에 도달 할 경우에 요청된 길이보다 적을 수 있다.
     */
    public static int read(Reader reader, char[] buffer, int offset, int length) {

        if (reader == null) {
            TTLog.error("IOUtils-read ERROR: InputStream is null.", UtCoIOUtils.class);
            return -1;
        }

        try {
            return org.apache.commons.io.IOUtils.read(reader, buffer, offset, length);
        } catch (Exception e) {
            TTLog.error("IOUtils-read ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * InputStream 에서 요청한 byte 수 만큼 읽는다. byte 길이가 실패값 일 경우 Fail 반환.
     *
     * @param is 읽을 대상의 InputStream
     * @param buffer 입력 대상 buffer
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus readFully(InputStream is, byte[] buffer) {

        if (is == null) {
            TTLog.error("IOUtils-readFully ERROR: InputStream is null.", UtCoIOUtils.class);
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.IOUtils.readFully(is, buffer);
            return CsStatus.SUCCESS;
        } catch (IOException e) {
            TTLog.error("IOUtils-readFully ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * InputStream 에서 요청한 byte 수 만큼 읽는다. byte 길이가 실패값 일 경우 Fail 반환.
     *
     * @param is 읽을 대상의 InputStream
     * @param buffer 입력 대상 버퍼
     * @param offset 읽을 byte가 시작되는 오프셋
     * @param length 읽을 수 있는 byte 길이, 0보다 커야한다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus readFully(InputStream is, byte[] buffer, int offset, int length) {

        if (is == null) {
            TTLog.error("IOUtils-readFully ERROR: InputStream is null.", UtCoIOUtils.class);
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.IOUtils.readFully(is, buffer, offset, length);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-readFully ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * Reader 에서 요청한 문자 수 만큼 읽는다. 문자 길이가 실패값 일 경우 Fail 반환.
     *
     * @param reader 읽을 대상의 Reader
     * @param buffer 입력 대상 버퍼
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus readFully(Reader reader, char[] buffer) {

        if (reader == null) {
            TTLog.error("IOUtils-readFully ERROR: Reader is null.", UtCoIOUtils.class);
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.IOUtils.readFully(reader, buffer);
            return CsStatus.SUCCESS;
        } catch (IOException e) {
            TTLog.error("IOUtils-readFully ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * Reader 에서 요청한 문자 수 만큼 읽는다. 문자 길이가 실패값 일 경우 Fail 반환.
     *
     * @param reader 읽을 대상의 Reader
     * @param buffer 입력 대상 버퍼
     * @param offset 읽을 문자가 시작되는 오프셋
     * @param length 읽을 수 있는 문자 길이, 0보다 커야한다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus readFully(Reader reader, char[] buffer, int offset, int length) {

        if (reader == null) {
            TTLog.error("IOUtils-readFully ERROR: Reader is null.", UtCoIOUtils.class);
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.IOUtils.readFully(reader, buffer, offset, length);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-readFully ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * InputStream의 내용을 기본 문자 인코딩 방식을 사용하여 한줄씩 읽고 List<String>로 반환한다.
     *
     * @param is 내용을 가져올 InputStream
     * @return 스트링으로 구성된 List
     */
    public static List<String> readLines(InputStream is) {

        try {
            return org.apache.commons.io.IOUtils.readLines(is);
        } catch (Exception e) {
            TTLog.error("IOUtils-readLines ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * InputStream의 내용을 지정된 문자 인코딩 방식을 사용하여 한줄씩 읽고 List<String>로 반환한다.
     *
     * @param is 내용을 가져올 InputStream
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 스트링으로 구성된 List
     */
    public static List<String> readLines(InputStream is, String encoding) {

        try {
            return org.apache.commons.io.IOUtils.readLines(is, encoding);
        } catch (Exception e) {
            TTLog.error("IOUtils-readLines ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * Reader 의 내용을 지정 문자 인코딩을 사용하여 한줄씩 읽고 List<String>로 반환한다.
     *
     * @param reader 내용을 가져올 InputStream
     * @return 스트링으로 구성된 List
     */
    public static List<String> readLines(Reader reader) {

        try {
            return org.apache.commons.io.IOUtils.readLines(reader);
        } catch (Exception e) {
            TTLog.error("IOUtils-readLines ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * InputStream에서 실제로 Skip 할 바이트 수를 계산하여 반환한다.
     *
     * @param is skip 할 대상
     * @param toSkip 건너 뛸 바이트의 수
     * @return 실제로 건너 뛸 바이트의 수
     */
    public static long skip(InputStream is, long toSkip) {

        if (is == null) {
            TTLog.error("IOUtils-skip ERROR: InputStream is null.", UtCoIOUtils.class);
            return -1;
        }

        try {
            return org.apache.commons.io.IOUtils.skip(is, toSkip);
        } catch (Exception e) {
            TTLog.error("IOUtils-skip ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return -1;
        }
    }

    /**
     * Reader에서 실제로 Skip 할 문자 수를 계산하여 반환한다.
     *
     * @param reader skip 할 대상
     * @param toSkip 건너 뛸 바이트의 수
     * @return 실제로 건너 뛸 바이트의 수
     */
    public static long skip(Reader reader, long toSkip) {

        if (reader == null) {
            TTLog.error("IOUtils-skip ERROR: Reader is null.", UtCoIOUtils.class);
            return -1;
        }

        try {
            return org.apache.commons.io.IOUtils.skip(reader, toSkip);
        } catch (Exception e) {
            TTLog.error("IOUtils-skip ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return 0;
        }
    }

    /**
     * InputStream 에서 Skip 할 바이트 수가 실패값 일 경우 Fail 반환.
     *
     * @param is Skip 할 대상
     * @param toSkip Skip 할 바이트의 수
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus skipFully(InputStream is, long toSkip) {

        if (is == null) {
            TTLog.error("IOUtils-skipFully ERROR: InputStream is null.", UtCoIOUtils.class);
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.IOUtils.skipFully(is, toSkip);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-skipFully ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * Reader 에서 Skip 할 문자 수가 실패값 일 경우 Fail 반환.
     *
     * @param reader Skip 할 대상
     * @param toSkip Skip 할 바이트의 수
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus skipFully(Reader reader, long toSkip) {

        if (reader == null) {
            TTLog.error("IOUtils-skipFully ERROR: Reader is null.", UtCoIOUtils.class);
            return CsStatus.FAIL;
        }

        try {
            org.apache.commons.io.IOUtils.skipFully(reader, toSkip);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-skipFully ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * InputStream 를 BufferedInputStream으로 변환한다.<br />
     *
     * @param is InputStream
     * @return 완충된 InputStream, 예외시 <code>null</code>
     */
    public static InputStream toBufferedInputStream(InputStream is) {

        if (is == null) {
            TTLog.error("IOUtils-toBufferedInputStream ERROR: InputStream is null.", UtCoIOUtils.class);
            return null;
        }

        try {
            return ByteArrayOutputStream.toBufferedInputStream(is);
        } catch (IOException e) {
            TTLog.error("IOUtils-toBufferedInputStream  ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * 주어진 Reader의 객체가 BufferedReader인가를 확인후 BufferedReader를 반환한다.<br />
     *
     * @param reader Reader의
     * @return 주어진 reader 또는 새로운 BufferedReader
     */
    public static BufferedReader toBufferedReader(Reader reader) {

        if (reader == null) {
            TTLog.error("IOUtils-toBufferedInputStream ERROR: Reader is null.", UtCoIOUtils.class);
            return null;
        }

        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    /**
     * InputStream 내용을 읽어 byte[]로 반환한다.
     *
     * @param is 내용을 가져올 InputStream
     * @return 요청한 바이트 배열
     */
    public static byte[] toByteArray(InputStream is) {

        try {
            return org.apache.commons.io.IOUtils.toByteArray(is);
        } catch (Exception e) {
            TTLog.error("IOUtils-toByteArray  ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * byte[]로 InputStream 내용을 읽어 byte[]로 반환한다. InputStream의 길이가 알려져 있을 때 사용한다. 길이(int)를 0보다 작거나 같은지 검사한다.
     *
     * @param is 내용을 가져올 InputStream
     * @param size InputStream 의 사이즈
     * @return 요청한 바이트 배열
     */
    public static byte[] toByteArray(InputStream is, int size) {

        try {
            return org.apache.commons.io.IOUtils.toByteArray(is, size);
        } catch (Exception e) {
            TTLog.error("IOUtils-toByteArray ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }

    }

    /**
     * InputStream 내용을 읽어 byte[]로 반환한다. InputStream의 길이가 알려져 있을 때 사용한다. 길이(long)를 int로 캐스팅 될 수 있는지 검사한다.
     *
     * @param is 내용을 가져올 InputStream
     * @param size InputStream 의 사이즈
     * @return 요청한 바이트 배열
     */
    public static byte[] toByteArray(InputStream is, long size) {

        try {
            return org.apache.commons.io.IOUtils.toByteArray(is, size);
        } catch (Exception e) {
            TTLog.error("IOUtils-toByteArray ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }

    }

    /**
     * Reader 내용을 기본 문자 인코딩을 사용하여 byte[]로 반환한다.
     *
     * @param reader 내용을 가져올 Reader
     * @return 요청한 바이트 배열
     */
    public static byte[] toByteArray(Reader reader) {

        try {
            return org.apache.commons.io.IOUtils.toByteArray(reader);
        } catch (Exception e) {
            TTLog.error("IOUtils-toByteArray ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * Reader 내용을 지정된 문자 인코딩을 사용하여 byte[]로 반환한다.
     *
     * @param reader 내용을 가져올 Reader
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 요청한 바이트 배열
     */
    public static byte[] toByteArray(Reader reader, String encoding) {

        try {
            return org.apache.commons.io.IOUtils.toByteArray(reader, encoding);
        } catch (Exception e) {
            TTLog.error("IOUtils-toByteArray ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * InputStream 내용을 기본 문자 인코딩을 사용하여 char[]로 내용을 읽어 byte[]로 반환한다.
     *
     * @param is 내용을 가져올 InputStream
     * @return 요청한 char 배열
     */
    public static char[] toCharArray(InputStream is) {

        try {
            return org.apache.commons.io.IOUtils.toCharArray(is);
        } catch (Exception e) {
            TTLog.error("IOUtils-toCharArray ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * InputStream 내용을 지정된 문자 인코딩을 사용하여 char[]로 반환한다.
     *
     * @param is 내용을 가져올 InputStream
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 요청한 char 배열
     */
    public static char[] toCharArray(InputStream is, String encoding) {

        try {
            return org.apache.commons.io.IOUtils.toCharArray(is);
        } catch (Exception e) {
            TTLog.error("IOUtils-toCharArray ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * Reader 내용을 읽어 char[]로 반환한다.
     *
     * @param reader 내용을 가져올 Reader
     * @return 요청한 char 배열
     */
    public static char[] toCharArray(Reader reader) {

        try {
            return org.apache.commons.io.IOUtils.toCharArray(reader);
        } catch (Exception e) {
            TTLog.error("IOUtils-toCharArray ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * CharSequence의 바이트를 기본 인코딩 방식을 사용하여 입력 스트림으로 변환한다.
     *
     * @param input 내용을 가져올 CharSequence
     * @return 변환한 입력 스트림
     */
    public static InputStream toInputStream(CharSequence input) {

        if (input == null) {
            TTLog.error("IOUtils-toInputStream ERROR: CharSequence is null.", UtCoIOUtils.class);
            return null;
        }
        return org.apache.commons.io.IOUtils.toInputStream(input);
    }

    /**
     * CharSequence의 바이트를 지정된 인코딩 방식을 사용하여 입력 스트림으로 변환한다.
     *
     * @param input 내용을 가져올 CharSequence
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 변환한 입력 스트림
     */
    public static InputStream toInputStream(CharSequence input, String encoding) {

        if (input == null) {
            TTLog.error("IOUtils-toInputStream ERROR: CharSequence is null.", UtCoIOUtils.class);
            return null;
        }

        try {
            return org.apache.commons.io.IOUtils.toInputStream(input, encoding);
        } catch (Exception e) {
            TTLog.error("IOUtils-toInputStream ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * 문자열의 바이트를 기본 인코딩 방식을 사용하여 입력 스트림으로 변환한다.
     *
     * @param input 내용을 변환할 String
     * @return 변환한 입력 스트림
     */
    public static InputStream toInputStream(String input) {

        if (input == null) {
            TTLog.error("IOUtils-toInputStream ERROR: String is null.", UtCoIOUtils.class);
            return null;
        }
        return org.apache.commons.io.IOUtils.toInputStream(input);
    }

    /**
     * 문자열의 바이트를 지정한 인코딩 방식을 사용하여 입력 스트림으로 변환한다.
     *
     * @param input 내용을 변환할 String
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 변환한 입력 스트림
     */
    public static InputStream toInputStream(String input, String encoding) {

        if (input == null) {
            TTLog.error("IOUtils-toInputStream ERROR: String is null.", UtCoIOUtils.class);
            return null;
        }

        try {
            return org.apache.commons.io.IOUtils.toInputStream(input, encoding);
        } catch (Exception e) {
            TTLog.error("IOUtils-toInputStream ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * InputStream 내용을 기본 문자 인코딩을 사용하여 String으로 반환한다.
     *
     * @param is 내용을 가져올 InputStream
     * @return 요청한 String
     */
    public static String toString(InputStream is) {

        try {
            return org.apache.commons.io.IOUtils.toString(is);
        } catch (Exception e) {
            TTLog.error("IOUtils-toString ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * InputStream 내용을 지정한 문자 인코딩을 사용하여 String으로 반환한다.
     *
     * @param is 내용을 가져올 InputStream
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 요청한 String
     */
    public static String toString(InputStream is, String encoding) {

        try {
            return org.apache.commons.io.IOUtils.toString(is, encoding);
        } catch (Exception e) {
            TTLog.error("IOUtils-toString ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * Reader 내용을 읽어 String으로 반환한다.
     *
     * @param reader 내용을 가져올 Reader
     * @return 요청한 String
     */
    public static String toString(Reader reader) {

        try {
            return org.apache.commons.io.IOUtils.toString(reader);
        } catch (Exception e) {
            TTLog.error("IOUtils-toString ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * 지정된 URI에 있는 내용을 읽어 반환한다.
     *
     * @param uri uri 주소
     * @return url의 내용
     */
    public static String toString(URI uri) {

        try {
            return org.apache.commons.io.IOUtils.toString(uri);
        } catch (Exception e) {
            TTLog.error("IOUtils-toString ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * 지정된 URI에 있는 내용을 읽어 지정한 문자 인코딩 방식으로 인코딩 후 반환한다.
     *
     * @param uri uri 주소
     * @param encoding url 내용을 encoding 할 문자 인코딩 방식
     * @return url의 내용
     */
    public static String toString(URI uri, String encoding) {

        try {
            return org.apache.commons.io.IOUtils.toString(uri, encoding);
        } catch (Exception e) {
            TTLog.error("IOUtils-toString ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * 지정된 URL에있는 내용을 String으로 반환한다.
     *
     * @param url url 주소
     * @return url의 내용
     */
    public static String toString(URL url) {

        try {
            return org.apache.commons.io.IOUtils.toString(url);
        } catch (Exception e) {
            TTLog.error("IOUtils-toString ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * 지정된 URL에있는 내용을 지정한 문자 인코딩을 사용하여 인코딩 후 반환한다.
     *
     * @param url url 주소
     * @param encoding url 내용을 encoding 할 문자 인코딩 방식
     * @return url의 내용
     */
    public static String toString(URL url, String encoding) {

        try {
            return org.apache.commons.io.IOUtils.toString(url);
        } catch (Exception e) {
            TTLog.error("IOUtils-toString ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return null;
        }
    }

    /**
     * byte[]에서 OutputStream 으로 바이트를 쓴다.
     *
     * @param data byte array 를 읽는다, null 은 무시
     * @param output OutputStream 에 쓴다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(byte[] data, OutputStream output) {
        try {
            org.apache.commons.io.IOUtils.write(data, output);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * byte[]에서 Writer 로 바이트를 쓴다.
     *
     * @param data byte array 를 읽는다, null 은 무시
     * @param output Writer 에 쓴다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(byte[] data, Writer output) {
        try {
            org.apache.commons.io.IOUtils.write(data, output);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * byte[]에서 Writer 로 바이트를 쓴다.
     *
     * @param data byte array 를 읽는다, null 은 무시
     * @param output Writer 에 쓴다.
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(byte[] data, Writer output, String encoding) {
        try {
            org.apache.commons.io.IOUtils.write(data, output, encoding);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * chars에서 OutputStream 로 기본 인코딩 방식을 사용하여 쓴다.
     *
     * @param data byte array 를 읽는다, null 은 무시
     * @param output OutputStream 에 쓴다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(char[] data, OutputStream output) {
        try {
            org.apache.commons.io.IOUtils.write(data, output);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * chars에서 OutputStream 로 지정된 인코딩 방식을 사용하여 쓴다.
     *
     * @param data byte array 를 읽는다, null 은 무시
     * @param output OutputStream 에 쓴다.
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(char[] data, OutputStream output, String encoding) {
        try {
            org.apache.commons.io.IOUtils.write(data, output, encoding);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * chars에서 Writer 로 기본 인코딩 방식을 사용하여 쓴다.
     *
     * @param data byte array 를 읽는다, null 은 무시
     * @param output Writer 에 쓴다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(char[] data, Writer output) {
        try {
            org.apache.commons.io.IOUtils.write(data, output);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * CharSequence에서 OutputStream 로 기본 인코딩 방식을 사용하여 chars를 쓴다.
     *
     * @param data chars 를 읽는다, null 은 무시
     * @param output OutputStream 에 쓴다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(CharSequence data, OutputStream output) {
        try {
            org.apache.commons.io.IOUtils.write(data, output);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * CharSequence에서 OutputStream 로 지정된 인코딩 방식을 사용하여 chars를 쓴다.
     *
     * @param data chars 를 읽는다, null 은 무시
     * @param output OutputStream 에 쓴다.
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(CharSequence data, OutputStream output, String encoding) {
        try {
            org.apache.commons.io.IOUtils.write(data, output, encoding);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * CharSequence에서 Writer 로 chars를 쓴다.
     *
     * @param data chars 를 읽는다, null 은 무시
     * @param output Writer 에 쓴다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(CharSequence data, Writer output) {
        try {
            org.apache.commons.io.IOUtils.write(data, output);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * String에서 OutputStream 로 기본 인코딩 방식을 사용하여 문자를 쓴다.
     *
     * @param data String 를 읽는다, null 은 무시
     * @param output OutputStream 에 쓴다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(String data, OutputStream output) {
        try {
            org.apache.commons.io.IOUtils.write(data, output);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * String에서 OutputStream 로 지정된 인코딩 방식을 사용하여 문자를 쓴다.
     *
     * @param data String 를 읽는다, null 은 무시
     * @param output OutputStream 에 쓴다.
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(String data, OutputStream output, String encoding) {
        try {
            org.apache.commons.io.IOUtils.write(data, output, encoding);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * String에서 Writer 로 문자를 쓴다.
     *
     * @param data String 를 읽는다, null 은 무시
     * @param output Writer 에 쓴다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus write(String data, Writer output) {
        try {
            org.apache.commons.io.IOUtils.write(data, output);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-write ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * Collection의 각 항목을 기본 인코딩 방식 및 지정된 라인 결말에 따라 OutputStream에 쓴다.
     *
     * @param lines Line을 쓴다. NULL 항목은 빈 라인을 생성
     * @param lineEnding 라인 구분자, default : null
     * @param os OutputStream에 쓴다. null 이거나 닫을수 없다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus writeLines(Collection<?> lines, String lineEnding, OutputStream os) {

        try {
            org.apache.commons.io.IOUtils.writeLines(lines, lineEnding, os);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-writeLines ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * Collection의 각 항목을 지정된 인코딩 방식 및 지정된 라인 결말에 따라 OutputStream에 쓴다.
     *
     * @param lines Line을 쓴다. NULL 항목은 빈 라인을 생성
     * @param lineEnding 라인 구분자, default : null
     * @param os OutputStream에 쓴다. null 이거나 닫을수 없다.
     * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus writeLines(Collection<?> lines, String lineEnding, OutputStream os, String encoding) {

        try {
            org.apache.commons.io.IOUtils.writeLines(lines, lineEnding, os, encoding);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-writeLines ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }

    /**
     * Collection의 각 항목을 기본 인코딩 방식 및 지정된 라인 결말에 따라 Writer에 쓴다.
     *
     * @param lines Line을 쓴다. NULL 항목은 빈 라인을 생성
     * @param lineEnding 라인 구분자, default : null
     * @param writer Writer에 쓴다. null 이거나 닫을수 없다.
     * @return 성공하면 enum 타입의 CoStatus.SUCCESS를 그렇지 않으면 CoStatus.FAIL을 반환
     */
    public static CsStatus writeLines(Collection<?> lines, String lineEnding, Writer writer) {

        try {
            org.apache.commons.io.IOUtils.writeLines(lines, lineEnding, writer);
            return CsStatus.SUCCESS;
        } catch (Exception e) {
            TTLog.error("IOUtils-writeLines ERROR: " + e.getMessage(), UtCoIOUtils.class);
            return CsStatus.FAIL;
        }
    }
    
    public static void sendMail(String toMailAddr, String fromMailAddr, String toUser, String fromUser, CoTtObjParams ttObjParams) {

        ///////////////////////메일 세팅//////////////////
        String email = toMailAddr;
        
        Properties msgProperties = new Properties();
        
        // props.put("cmsupporters.com", "121.254.169.221 ");
        //msgProperties.put("127.0.0.1", "localhost");  // 메일 서버지정
        //msgProperties.put("mail.smtp.host", "121.254.169.221");
        msgProperties.put("mail.smtp.host", "localhost");
        
        
        Session msgSession = Session.getDefaultInstance(msgProperties, null);
        
        MimeMessage msg = new MimeMessage(msgSession);
        
        try{
        
        String mailfrom_name = fromUser;  // 보낸이 이름
        
        String mailfrom =  fromMailAddr; //보낸이 메일 주소
        String mailfroms ="";
        
        try{
            mailfroms = new String(mailfrom.getBytes("utf-8"),"8859_1"); //euc-kr ==>> 8859-1
        }catch (Exception e) {
            // TODO: handle exception
        }
        
        
        InternetAddress from = new InternetAddress(mailfroms);
        
        msg.setFrom(from);
        
        String mailto = email; // 받는이 주소
        String mailtos =""; 
            
        try{
            mailtos = new String(mailto.getBytes("utf-8"),"8859_1"); //euc-kr ==>> 8859-1
        }catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("################## mailtos : " + mailtos);
        InternetAddress to = new InternetAddress(mailtos);
        
        msg.setRecipient(Message.RecipientType.TO, to);
        
        msg.setSubject("ESR-CENTER 결재 정보 메일 입니다.","EUC-KR");  // 메일 제목 
        
        String contents = "";
        
        /*contents += "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>";
        contents += "<html>";
        contents += " <head>";
        contents += "  <title> New Document </title>";
        contents += " </head>";
        contents += "   <style>";
        contents += "       .tbws1 { border-collapse:collapse; width: 100%; border: 1px solid #5290cd; border-top-width: 1px; border-bottom-width: 1px;}";
        contents += "       .tbws1 tr th { font-weight: bold; padding: 4px 0px 4px 0px; width:124px; text-align:center; border:1px solid #5290cd; background: #f0f4f7;  overflow: hidden; white-space: nowrap;}";
        contents += "       .tbws1 tr td { padding: 4px 0 4px 0px; text-align:center; border-left-width: 1px; border-right-width: 1px; border:1px solid #5290cd; overflow: hidden; white-space: nowrap;}";
        contents += "       .tbws1 tr td select { vertical-align:middle}";
        contents += "   </style>";
        contents += " <body>";
        contents += "   <span style='font-weight:bold '>■기안 내용</span>";
        contents += "    <table class='tbws1'>";
        contents += "        <tr>";
        contents += "           <th>순서</th>";
        contents += "           <th>기안자</th>";
        contents += "           <th>사용자</th>";
        contents += "           <th>신청항목</th>";
        contents += "           <th>신청기간</th>";
        contents += "        </tr>";
        contents += "        <tr>";
        contents += "           <td>1</td>";
        contents += "           <td>이정희</td>";
        contents += "           <td>이정희</td>";
        contents += "           <td>이동식 저장장치, 추가HDD, 무선랜, 스마트폰, 공유폴더</td>";
        contents += "           <td>2013-06-17~2013-06-29 (13일간)</td>";
        contents += "        </tr>";
        contents += "        <tr>";
        contents += "           <td style='background: #f0f4f7; font-weight: bold; ' colspan='2'>신청사유</td>";
        contents += "           <td colspan='3'>지방 출장으로 예외신청 합니다.</td>";
        contents += "        </tr>";
        contents += "    </table>";
        contents += "    <div>";
        contents += "           <a href=''><input type='button' style='float:right; margin-top:10px;' value='결재하기'/></a>";
        contents += "        </div>";
        contents += " </body>";
        contents += "</html>";*/
        
       
        VoApprInfo voApprInfo = new VoApprInfo();
        voApprInfo =  (VoApprInfo)ttObjParams.get("voApprInfo");
        String statDm = voApprInfo.getStartDm().substring(0,4)+ "-" +
        voApprInfo.getStartDm().substring(4,6)+ "-" +
        voApprInfo.getStartDm().substring(6,8)+ " " +
        voApprInfo.getStartDm().substring(8,10)+ ":" +
        voApprInfo.getStartDm().substring(10,12); 
        
        String endDm = voApprInfo.getEndDm().substring(0,4)+ "-" +
        voApprInfo.getEndDm().substring(4,6)+ "-" +
        voApprInfo.getEndDm().substring(6,8)+ " " +
        voApprInfo.getEndDm().substring(8,10)+ ":" +
        voApprInfo.getEndDm().substring(10,12); 
       // voExctDraft.get
        
        contents += "[기안자] : " + voApprInfo.getUserNm() + "<br>";
        contents += "[사용자] : " + voApprInfo.getChkObjNms() + "<br>";
        contents += "[신청항목] : " + voApprInfo.getChkExctNms() + "<br>";
        contents += "[신청기간] : " + statDm + " ~ " + endDm +"<br>";
        contents += "[신청사유] : " + voApprInfo.getReason() + "<br>";
        contents += "<a href='http://192.168.1.60:8080'>결재하기</a>";
        
        System.out.println(contents);
        msg.setContent(contents, "text/html; charset=EUC-KR");  // 메일 내용
        
        msg.setHeader("Content-type", "text/html; charset=EUC-KR"); // html 타입으로 지정
        
        
        }  catch(javax.mail.MessagingException ex) {
            System.out.println("메일 발송 실패1-1" + ex);
        ex.printStackTrace();
        }
        
        try{
            Transport.send(msg); // 메일전송
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ 메일 발송 성공1");
        }catch(javax.mail.MessagingException ex) {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ 메일 발송 실패1");
            ex.printStackTrace();
        }
        //////////////////메일 끝/////////////////////
        
    }
    
    public static void approvalMail(String toMailAddr, String fromMailAddr, String toUser, String fromUser) {

        ///////////////////////메일 세팅//////////////////
        String email = toMailAddr;
        
        Properties msgProperties = new Properties();
        
        // props.put("cmsupporters.com", "121.254.169.221 ");
        //msgProperties.put("127.0.0.1", "localhost");  // 메일 서버지정
        //msgProperties.put("121.254.169.221", "cmsupporters.com");  // 메일 서버지정
        //msgProperties.put("mail.smtp.host", "121.254.169.221");
        msgProperties.put("mail.smtp.host", "localhost");
        
        
        Session msgSession = Session.getDefaultInstance(msgProperties, null);
        
        MimeMessage msg = new MimeMessage(msgSession);
        
        try{
        
        String mailfrom_name = fromUser;  // 보낸이 이름
        
        String mailfrom =  fromMailAddr; //보낸이 메일 주소
        String mailfroms ="";
        
        try{
            mailfroms = new String(mailfrom.getBytes("utf-8"),"8859_1"); //euc-kr ==>> 8859-1
        }catch (Exception e) {
            // TODO: handle exception
        }
        
        
        InternetAddress from = new InternetAddress(mailfroms);
        
        msg.setFrom(from);
        
        String mailto = email; // 받는이 주소
        String mailtos =""; 
            
        try{
            mailtos = new String(mailto.getBytes("utf-8"),"8859_1"); //euc-kr ==>> 8859-1
        }catch (Exception e) {
            // TODO: handle exception
        }
        
        InternetAddress to = new InternetAddress(mailtos);
        
        msg.setRecipient(Message.RecipientType.TO, to);
        
        msg.setSubject("KRX 비밀번호 정보 메일 입니다.","EUC-KR");  // 메일 제목 
        
        String contents = "";
        
        contents += "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>";
        contents += "<html>";
        contents += " <head>";
        contents += "  <title> New Document </title>";
        contents += "  <meta name='Generator' content='EditPlus'>";
        contents += "  <meta name='Author' content=''>";
        contents += "  <meta name='Keywords' content=''>";
        contents += "  <meta name='Description' content=''>";
        contents += " </head>";
        contents += "   <style>";
        contents += "       .tbws1 { border-collapse:collapse; width: 100%; border: 1px solid #5290cd; border-top-width: 1px; border-bottom-width: 1px;}";
        contents += "       .tbws1 tr th { font-weight: bold; padding: 4px 0px 4px 0px; width:124px; text-align:center; border:1px solid #5290cd; background: #f0f4f7;  overflow: hidden; white-space: nowrap;}";
        contents += "       .tbws1 tr td { padding: 4px 0 4px 0px; text-align:center; border-left-width: 1px; border-right-width: 1px; border:1px solid #5290cd; overflow: hidden; white-space: nowrap;}";
        contents += "       .tbws1 tr td select { vertical-align:middle}";
        contents += "   </style>";
        contents += " <body>";
        contents += "   <span style='font-weight:bold '>■ 결재 내용</span>";
        contents += "    <table class='tbws1'>";
        contents += "        <tr>";
        contents += "           <th>순서</th>";
        contents += "           <th>결재자</th>";
        contents += "           <th>결재상태</th>";
        contents += "           <th>사용자</th>";
        contents += "           <th>신청항목</th>";
        contents += "           <th>신청기간</th>";
        contents += "        </tr>";
        contents += "        <tr>";
        contents += "           <td>1</td>";
        contents += "           <td>이정희</td>";
        contents += "           <td>결재완료</td>";
        contents += "           <td>이정희</td>";
        contents += "           <td>이동식 저장장치, 추가HDD, 무선랜, 스마트폰, 공유폴더</td>";
        contents += "           <td>2013-06-17~2013-06-29 (13일간)</td>";
        contents += "        </tr>";
        contents += "        <tr>";
        contents += "           <td style='background: #f0f4f7; font-weight: bold; ' colspan='2'>결재승인</td>";
        contents += "           <td colspan='3'>승인합니다.</td>";
        contents += "        </tr>";
        contents += "    </table>";
        contents += "    <div>";
        contents += "           <input type='button' style='float:right; margin-top:10px;' value='확인하기'/>";
        contents += "        </div>";
        contents += " </body>";
        contents += "</html>";

        
        msg.setContent(contents, "text/html; charset=EUC-KR");  // 메일 내용
        
        msg.setHeader("Content-type", "text/html; charset=EUC-KR"); // html 타입으로 지정
        
        
        }  catch(javax.mail.MessagingException ex) {
            System.out.println("메일 발송 실패1-1" + ex);
        ex.printStackTrace();
        }
        
        try{
            Transport.send(msg); // 메일전송
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ 메일 발송 성공1");
        }catch(javax.mail.MessagingException ex) {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ 메일 발송 실패1");
            ex.printStackTrace();
        }
        //////////////////메일 끝/////////////////////
        
    }

}