package com.example.demo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBloodCheck is a Querydsl query type for BloodCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBloodCheck extends EntityPathBase<BloodCheck> {

    private static final long serialVersionUID = -161755649L;

    public static final QBloodCheck bloodCheck = new QBloodCheck("bloodCheck");

    public final NumberPath<Long> bloodCheckId = createNumber("bloodCheckId", Long.class);

    public final NumberPath<Long> kang = createNumber("kang", Long.class);

    public final NumberPath<Long> lee = createNumber("lee", Long.class);

    public final NumberPath<Long> oh = createNumber("oh", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> song = createNumber("song", Long.class);

    public QBloodCheck(String variable) {
        super(BloodCheck.class, forVariable(variable));
    }

    public QBloodCheck(Path<? extends BloodCheck> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBloodCheck(PathMetadata metadata) {
        super(BloodCheck.class, metadata);
    }

}

