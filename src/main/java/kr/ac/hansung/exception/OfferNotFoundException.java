package kr.ac.hansung.exception;

//예외 객체는 이러한 예외 정보를 포함하고,
// 예외 처리기가 이를 활용하여 적절한 조치를 취할 수 있도록 합니다.
public class OfferNotFoundException extends RuntimeException {

    private int offerId;

    public OfferNotFoundException(int id) {
        this.offerId = id;
    }

    public int getOfferId() {
        return offerId;
    }
}
