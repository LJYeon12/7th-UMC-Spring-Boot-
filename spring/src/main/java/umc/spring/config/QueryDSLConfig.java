package umc.spring.config;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QueryDSLConfig {
    private final EntityManager entityManager;

//    @Bean
//    public JPAQueryFactory jpaQueryFactory(){
//        return new JPAQueryFactory(entityManager);
//    }
}