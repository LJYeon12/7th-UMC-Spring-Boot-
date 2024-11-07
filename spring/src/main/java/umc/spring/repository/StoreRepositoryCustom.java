package umc.spring.repository;

import umc.spring.domain.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    // BooleanBuilder 역할은 QueryDSL 에서
    // 여러개의 조건을 조합하기 위해 제공되는 빌더 클래스
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}
