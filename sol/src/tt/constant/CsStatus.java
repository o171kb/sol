package tt.constant;

/**
 * DESC : 작업 및 결과 상태에 관한 enum.
 *
 * Constans 상수에 의한 결과 처리도 magic number를 방지하는 방법이 될수도 있으나, type에 대해 보다 엄격한 체크가 가능하고
 * 의미 전달을 명확하게 할수 있는 enum의 사용을 권장한다.
 *
 * @Company think-tree.inc
 * @author ks-lee
 * @Date 2013. 1. 9. 오후 8:22:16
 */
public enum CsStatus {

    SUCCESS(1), START(1), RUN(1), EMPTY(0), WAIT(0), FAIL(-1), ERROR(-1), END(-1), STOP(-1);

    private int code;

    private CsStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
