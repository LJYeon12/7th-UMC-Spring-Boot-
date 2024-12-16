package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;
/*
import umc.spring.apiPayload.code.ErrorReasonDTO;
*/

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    // foodCategory
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOODCATEGORY4001", "카테고리를 받지 못했습니다."),
    // Mission ERROR
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSSION4001", "미션을 찾을 수 없습니다."),
    MISSION_NOT_ASSOCIATED_WITH_STORE(HttpStatus.NOT_FOUND, "MISSION4002", "미션과 연관된 스토어가 없습니다"),
    // MemberMission
    MISSION_ALREADY_ADD_MEMBER(HttpStatus.CONFLICT, "MEMBERMISSION4009", "미션이 이미 멤버에 추가된 상태입니다"),

    MEMBERMISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBERMISSION4001", "멤버와 연결된 미션을 찾을 수 없습니다"),
    // Store
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "store 를 찾을 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}