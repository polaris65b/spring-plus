package org.example.expert.domain.todo.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.QUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TodoQueryRepositoryImpl implements TodoQueryRepository{
    private final JPAQueryFactory jpaQueryFactory;


    public TodoQueryRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Optional<Todo> findByIdWithUser(Long todoId){
        QTodo todo = QTodo.todo;
        QUser user = QUser.user;

        Todo result = jpaQueryFactory
                .selectFrom(todo)
                .leftJoin(todo.user, user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne();

        return Optional.ofNullable(result);

    }


}