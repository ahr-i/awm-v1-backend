package com.example.teamproject.JpaClass;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOauthUser_Info is a Querydsl query type for OauthUser_Info
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOauthUser_Info extends EntityPathBase<OauthUser_Info> {

    private static final long serialVersionUID = 480151129L;

    public static final QOauthUser_Info oauthUser_Info = new QOauthUser_Info("oauthUser_Info");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id_key = createNumber("id_key", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath provider = createString("provider");

    public final StringPath providerId = createString("providerId");

    public final StringPath role = createString("role");

    public final StringPath sub = createString("sub");

    public QOauthUser_Info(String variable) {
        super(OauthUser_Info.class, forVariable(variable));
    }

    public QOauthUser_Info(Path<? extends OauthUser_Info> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOauthUser_Info(PathMetadata metadata) {
        super(OauthUser_Info.class, metadata);
    }

}

