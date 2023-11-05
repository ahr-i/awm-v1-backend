package com.example.teamproject.JpaClass.UserTable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -652737884L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.time.LocalDateTime> creatAt = createDateTime("creatAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath image = createString("image");

    public final StringPath imageHash = createString("imageHash");

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath provider = createString("provider");

    public final NumberPath<Integer> rankScore = createNumber("rankScore", Integer.class);

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    public final StringPath userId = createString("userId");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

